package testscripts.Navigator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE008_GuestBenefitTracker {

	CHANNELS SW = new CHANNELS();
	//String SPGNum = SMOKE001_Enrollment.SPGNumberCreated;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void benefitTracker() {
		String SPGNum=SW.TestData("SPGnum_created"); //"42008352261"
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNum);
		String getNumOfStays = SW.GetText("NavigatorHomePage_NumStays_DT")+" YTD"; //Getting number of stays and concact YTD
		String getNumNights = SW.GetText("NavigatorHomePage_NumNights_DT") + " YTD"; //Getting NUmber of nights and concat YTD 

		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Benefit_LK");
		SW.Click("NavigatorSearchPage_Benefit_LK"); //Clicking the benefit button

		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_NumOfStays_DT");
		String actualNumOfStays = SW.GetText("NavigatorSearchPage_NumOfStays_DT");
		String actualNumOfNights = SW.GetText("NavigatorSearchPage_NumOfNights_DT");

		SW.CompareText("compareNumOfStays", getNumOfStays, actualNumOfStays);
		SW.CompareText("compareNumOfNights", getNumNights, actualNumOfNights);

		SW.Click("NavigatorSearchPage_BenefitCancel_BT");
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
