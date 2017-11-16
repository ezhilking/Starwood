package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE006_LocateReservationByNameTab {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	String exp_GuestName = "RoshanS";
	@Test
	public void LocateReservationByArrivalDate()  {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.NormalClick("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		//SW.WaitTillElementToBeClickable("NavigatorReservatiobSearchPage_ByName_LK"); //Waiting for the By name link to be clickable
		SW.NormalClick("NavigatorReservatiobSearchPage_ByName_LK"); // clicking the By name link

		//SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_LastName_EB"); //WAiting for the LastName text box to appear
		SW.EnterValue("NavigatorReservationSearchPage_LastName_EB", "S"); //Entering value and pressing TAB
		SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", "3131 " ); //Entering value for the property or location

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_SelectProperty_DT");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", Keys.ENTER);
		//SW.NormalClick("NavigatorReservationSearchPage_SelectProperty_DT");
		SW.NormalClick("NavigatorReservationSearchPage_FromDate_EB"); //Clicking on the from date
		SW.EnterValue("NavigatorReservationSearchPage_FromDate_EB", "22Sep2016"); // entering the from date
		SW.NormalClick("NavigatorReservationSearchPage_ToDate_EB");
		SW.EnterValue("NavigatorReservationSearchPage_ToDate_EB", "23Sep2016");
		SW.NormalClick("NavigatorReservationSearchPage_BeginSearch_BT");

		String actual_GuestNameSearched = SW.GetText("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT"); //Getting the actual Guest Name
		SW.CompareText("GuestName_comparision-searchByArrivalDate", exp_GuestName, actual_GuestNameSearched); //Comparing the GUest NAme
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
