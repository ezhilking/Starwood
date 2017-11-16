package testscripts.Navigator;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Account With CA Address
 * TestCase Name: US6698-Account With CA Address
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG85_AccountWithCAAddress {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	List<String> Awards;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("CAAddressMember");
		Awards=Arrays.asList("AMZN100","AMZN150","AVIS$50","AMZN50","BANA100","BANA25","BANA50","BROOK100","BROOK50","BROOK150","GAP$25","GAP$50","MPI-100","MPI-25","NORD100","NORD150","NORD50","PB25","PB100","CNSLF","PB50","SBUCK100","SBUCK25","SBUCK50","PCMA-100","PCMA-25","SIXT$50","WS25","WS50","WS100");
	}
	@Test(priority=1)
	public void LocateSPGMember(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
		}
	}
	@Test(priority=2, dependsOnMethods="LocateSPGMember")
	public void MerchandiseAward(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ShowGuest_FT");
		SW.Click("NavigatorSearchPage_ShowGuest_FT");	
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Merchandise Awards"); //Selecting the award type
		SW.SelectRadioButton("NavigatorAwardPage_ShowAll_RB");
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		List<String> GetAwards=SW.GetAllText("NavigatorAwardPage_SearchResult_DT");
		Environment.loger.log(Level.INFO, GetAwards.get(0));
		for(int i=0;i<Awards.size();i++){
			for(int j=0;j<GetAwards.size();j++){
				SW.CompareTextContained("Checking the Awards", Awards.get(i), GetAwards.get(j));
			}
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}