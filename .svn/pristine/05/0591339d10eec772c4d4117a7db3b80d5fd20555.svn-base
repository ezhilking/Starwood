package testscripts.Navigator;
/* Purpose		: This script for Validate Look And Feel Of Main Menu And Search For Res
 * TestCase Name: Validate Look And Feel Of Main Menu And Search For Res
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG83_ValidateLookAndFeelOfMainMenuAndSearchForRes {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGnum_created");
	}
	@Test
	public void LookandFee(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.CompareText("Compare the main menu Text", "Main Menu - "+Environment.getRunEnvironment(), SW.GetText("NavigatorHomePage_MainMenu_BT"));
		SW.NormalClick("NavigatorHomePage_MainMenu_BT");
		//Validating look and feel
		if(SW.ObjectExists("NavigatorHomePage_SearchReservation_LK"))
			Reporter.Write("Check Reservation search link", "Reservation Search", "Reservation Search", "PASS");
		else
			Reporter.Write("Check Reservation search linkConfirmation card", "Reservation Search", "Reservation search not found", "Fail");
		if(SW.ObjectExists("NavigatorHomePage_EnrollNewMember_LK"))
			Reporter.Write("Check Enroll member link", "Enroll member", "Enroll member", "PASS");
		else
			Reporter.Write("Check Enroll member link", "Enroll member", "Enroll member not found", "Fail");
		if(SW.ObjectExists("NavigatorHomePage_PropComm_LK"))
			Reporter.Write("Check Prop comm link", "PropComm", "Prop Comm", "PASS");
		else
			Reporter.Write("Check Prop comm link", "Prop Comm", "propcomm  not found", "Fail");
		if(SW.ObjectExists("NavigatorHomePage_LogOff_LK"))
			Reporter.Write("Checking log off button", "LogOff ", SW.GetText("NavigatorHomePage_LogOff_LK"), "PASS");
		else
			Reporter.Write("Checking log off button", "Log off", SW.GetText("NavigatorHomePage_LogOff_LK"), "Fail");

		SW.NormalClick("NavigatorHomePage_SearchReservation_LK"); //CLicking on the search reservation link
		SW.Click("NavigatorReservatiobSearchPage_ByName_LK"); // clicking the By name link

		SW.EnterValue("NavigatorReservationSearchPage_LastName_EB", SW.TestData("ReservationLastName")); //Entering value and pressing TAB
		SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", SW.TestData("PropertyLocation") + Keys.ENTER );
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB",  Keys.ENTER ); //Entering value for the property or location
		SW.Click("NavigatorReservationSearchPage_FromDate_EB"); //Clicking on the from date
		SW.EnterValue("NavigatorReservationSearchPage_FromDate_EB", SW.TestData("ReservationArrivalDate") + Keys.TAB); // entering the from date
		SW.ClearValue("NavigatorReservationSearchPage_ToDate_EB"); //Clearing the to date
		SW.EnterValue("NavigatorReservationSearchPage_ToDate_EB", SW.TestData("ReservationDepDate") + Keys.TAB); // entering the to date, same as booking date
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Clicking on search
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK"))
			Reporter.Write("Confirmation card", "Card Found", "Card Found", "PASS");
		else
			Reporter.Write("Confirmation card", "Card Found", "Card not Found", "Fail");
		SW.Click("NavigatorReservationSearchPage_ShowAllTab_BT");
		SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB");
		SW.ClearValue("NavigatorReservationSearchPage_LocationProperty_EB");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", SW.TestData("PropertyLocation") + Keys.ENTER );
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB",  Keys.ENTER ); //Entering value for the property or location
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Clicking on search
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK"))
			Reporter.Write("Confirmation card", "Card Found", "Card Found", "PASS");
		else
			Reporter.Write("Confirmation card", "Card Found", "Card not Found", "Fail");
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num);

	}
}
