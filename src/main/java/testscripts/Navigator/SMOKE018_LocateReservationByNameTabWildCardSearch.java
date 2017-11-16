package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE018_LocateReservationByNameTabWildCardSearch {
	CHANNELS SW = new CHANNELS();
	String exp_GuestName;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		exp_GuestName=SW.TestData("ReservationGuestName");
	}
	

	@Test
	public void LocateReservationByBookingDate()  {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservatiobSearchPage_ByName_LK"); // clicking the By name link

		SW.EnterValue("NavigatorReservationSearchPage_LastName_EB", SW.TestData("ReservationLastName")); //Entering Guest Last name
		SW.NormalClick("NavigatorReservationSearchPage_WildCardSearch_CB"); //Clicking the WildCard search
		SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB"); // Clicking on the Property of location field
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", SW.TestData("ReservationProperty") ); //Entering value for the property or location
		SW.Wait(3);
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", Keys.ENTER); //Selecting the property displayed by Enter
		SW.Click("NavigatorReservationSearchPage_FromDate_EB"); //Clicking on the from date
		SW.EnterValue("NavigatorReservationSearchPage_FromDate_EB", SW.TestData("ReservationArrivalDate")); // entering the from date
		SW.NormalClick("NavigatorReservationSearchPage_ToDate_EB"); //Clicking to date
		SW.EnterValue("NavigatorReservationSearchPage_ToDate_EB", SW.TestData("ReservationDepDate") ); //Entering the to date  
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
		String actual_GuestNameSearched = SW.GetText("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT"); //Getting the actual Guest Name
		SW.CompareText("Guest_ComparisionByWildCardSearch", exp_GuestName, actual_GuestNameSearched);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
