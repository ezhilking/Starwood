package testscripts.Navigator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE021_LocateReservationByAssociateOfThisWeek {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	String ReservationCreationDate = SW.GetTimeStamp("ddMMMYYYY");
	@Test
	public void LocateReservationAssociateOfThisWeek()  {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservationSearchPage_Associate_LK"); //Clicking on the Associate TAB
		SW.DropDown_SelectByText("NavigatorReservationSearchPage_AssociateBookingDate_DD", "Last Reservation Booked");
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		String actualCreationDate = SW.GetText("NavigatorReservationSearchPage_ReservationCreationDate_DT");
		SW.CompareText("CreationDateComp_", ReservationCreationDate, actualCreationDate);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
