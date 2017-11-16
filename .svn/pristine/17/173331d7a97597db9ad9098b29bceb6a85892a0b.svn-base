package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for checking the Partnership Icons Display
 * TestCase Name: Partnership Icons Display
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG40_PartnershipIconsDisplay {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String color="rgba(78, 129, 194, 1)";
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("Platinum100Member");
		}
	@Test(priority=0)
	public void LocateGuestByNumber(){
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
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}
	}
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void checkPartnershipIcon(){
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorSearchPage_Partnership_LK")){
			Environment.loger.log(Level.INFO, "PartnerShip Icon available for this member");
			String sColor=SW.GetCSSValue("NavigatorSearchPage_Partnership_LK", "color");
			Environment.loger.log(Level.INFO, "PartnerShip Benifits are enabled"+sColor);
			SW.MoveToObject("NavigatorSearchPage_Partnership_LK");
			sColor=SW.GetCSSValue("NavigatorSearchPage_Partnership_LK", "color");
			if(SW.CompareText(color, sColor))
				Environment.loger.log(Level.INFO, "PartnerShip Benifits is available for this member");
			else 
				Environment.loger.log(Level.INFO,"No PartnerShips Benifits are available for this member");	
		}else {
			Environment.loger.log(Level.INFO,"No PartnerShips are available for this member");	
			SW.FailCurrentTest("Validation Fails in checking Expected company name");
		}
		String member=SW.GetText("NavigatorSearchPage_ExternalMember_DT");
		Environment.loger.log(Level.INFO," PartnerShips are available for this member "+member);	
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
