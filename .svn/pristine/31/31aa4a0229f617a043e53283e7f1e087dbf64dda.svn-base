package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE005_LocateReservationWithConfirmationTab {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void LocateReservationByConfirmationNum() {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK");

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB");
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", SW.TestData("ConfNum_LocateGuest")+ Keys.TAB);
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorReservationSearchPage_Ack_BT");

		//Verifications of Guest Name
		String exp_GuestLastName = SW.GetText("NavigatorReservationSearchPage_NavGuestLastName_DT").toUpperCase();
		String exp_GuestFirstName = SW.GetText("NavigatorReservationSearchPage_NavGuestFirstName_DT").toUpperCase();
		String exp_GuestName = exp_GuestFirstName + " " + exp_GuestLastName;	  
		String actualGuestName = SW.GetText("NavigatorReservationSearchPage_GuestName_DT").toUpperCase();
		SW.CompareText("GuestNameValidation", exp_GuestName, actualGuestName);

		String exp_SPGnumber = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actualSPGnumber = SW.GetText("NavigatorReservationSearchPage_SPGNumber_DT");
		SW.CompareText("SPG_Number Validation", exp_SPGnumber, actualSPGnumber);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
