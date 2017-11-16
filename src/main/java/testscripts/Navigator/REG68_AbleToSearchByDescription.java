package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG68_AbleToSearchByDescription {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,AwardID;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
		AwardID="HOTEL25";
	}
	
	@Test(priority=0)
	public void LocateSPGMemberByNumber(){
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
	@Test(priority=1, dependsOnMethods="LocateSPGMemberByNumber")
	public void ValidateSPGProAssistAward(){
		SW.Click("NavigatorSearchPage_ShowGuest_FT");	
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "SPG Pro Exec Assist Vouchers"); //Selecting the award type
		SW.EnterValue("NavigatorAwardPage_AwardId_EB", AwardID);
		SW.EnterValue("NavigatorAwardPage_Description_EB", "EXEC");
		SW.SelectRadioButton("NavigatorAwardPage_ShowAll_RB");
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		if(SW.ObjectExists("NAvigatorAwardPage_SPGPRoAssistAward_LK"))
		{
			Reporter.Write("Check the list of award","Award Displayed", "Award Displayed", "PASS");
			SW.NormalClick("NAvigatorAwardPage_SPGPRoAssistAward_LK");
		}
		else
			Reporter.Write("Check the list of award","Award Displayed", "Award Displayed", "FAIL");

	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}
}
