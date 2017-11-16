package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE024_LocateReservationBySPGGuestPreviousReservations {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	
	@Test
	public void LocateReservationByPreviousReservation()  {
		
		String exp_FromDate = SW.TestData("PreviousReservationFromDate");
		String exp_ToDate = SW.TestData("PreviousReservationToDate");
		
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	
		SW.SelectCommunicationType();//selecting communication type
		//SW.SearchGuestBySPGnumber(SW.TestData("Saratoga_newReservation"));
		SW.SearchGuestBySPGnumber(SW.TestData("Saratoga_previousReservation"));
		SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorHomePage_UpcommingStays_LK");
		String actual_fromDate = SW.GetText("NavigatorReservationSearchPage_ReservationFromDate_DT");
		String actual_ToDate = SW.GetText("NavigatorReservationSearchPage_ReservationToDate_DT");
		SW.CompareText("UpcommingReservationFromDate", exp_FromDate, actual_fromDate);
		SW.CompareText("UpcommingReservationToDate", exp_ToDate, actual_ToDate);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
