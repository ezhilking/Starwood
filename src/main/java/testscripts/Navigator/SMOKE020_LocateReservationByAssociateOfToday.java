package testscripts.Navigator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE020_LocateReservationByAssociateOfToday {
	CHANNELS SW = new CHANNELS();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		//SW.LaunchBrowser(Environment.BWURL+"3131");
	}

	String ReservationCreationDate = SW.GetTimeStamp("ddMMMYYYY");
	
	@Test(priority=0)
	public void LocateReservationAssociateOfToday()  {
		String expectedReservationNum = SW.GetTimeStamp("ddMMMyyyy");
		
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservationSearchPage_Associate_LK"); //Clicking on the Associate TAB
		SW.DropDown_SelectByText("NavigatorReservationSearchPage_AssociateBookingDate_DD", "Today");
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		String actualCreationDate = SW.GetText("NavigatorReservationSearchPage_ReservationCreationDate_DT");
		SW.CompareText("CreationDateComp_", expectedReservationNum, actualCreationDate);
	}
	
	//Quitting the instance of IE browser
		@AfterClass
		public void EndTest(){
			Reporter.StopTest();		
		} 
}
