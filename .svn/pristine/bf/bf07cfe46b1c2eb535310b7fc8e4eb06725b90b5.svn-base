package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE016_LocateReservationByNumTabUsingPhoneNum {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}


	@Test
	public void LocateReservationInNumTabUsingPhoneNum() {
		String exp_GuestName = SW.TestData("ExpectedGuestAsPerPhoneNum").toUpperCase();
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.NormalClick("NavigatorHomePage_SearchReservation_LK"); //CLicking on the search reservation link

		SW.Click("NavigatorReservationSearchPage_CountryCode_FT");
		SW.EnterValue("NavigatorReservationSearchPage_CountryCode_EB",SW.TestData("CountryCode_LocateReservation")+ Keys.TAB);
		SW.EnterValue("NavigatorReservationSearchPage_PhoneNum_EB", SW.TestData("PhoneNum_LocateReservation"));
		SW.NormalClick("NavigatorReservationSearchPage_BeginSearch_BT");
		//SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_Ack_BT");
		//SW.Click("NavigatorReservationSearchPage_Ack_BT");

		String actual_GuestName = SW.GetText("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
		SW.CompareText("Comparision_GuestName", exp_GuestName, actual_GuestName);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
