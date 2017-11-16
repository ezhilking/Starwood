package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE015_LocateReservationByNumTabUsingSPGnum {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void LocateReservation() {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.NormalClick("NavigatorHomePage_SearchReservation_LK"); //CLicking on the search reservation link
		SW.EnterValue("NavigatorReservationSearchPage_SPGNum_EB", SW.TestData("SPGNum_LocateGuest"));
		SW.NormalClick("NavigatorReservationSearchPage_BeginSearch_BT");

		String exp_SPGnumber = SW.TestData("SPGNum_LocateGuest");
		String actualSPGnumber = SW.GetText("NavigatorReservationSearchPage_SPGNumberBySPGsearch_DT");
		SW.CompareText("SPG_Number Validation", exp_SPGnumber, actualSPGnumber);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
