package testscripts.Navigator;
/* Purpose		: Searching Guest Profile 
 * TestCase Name: Search Guest By SPG Number
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 02/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */


import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE019_LocateGuestByEmailID {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchGuestByEmail() {

		//Expected Values 
		String exp_GuestFirstName=SW.TestData("SPGmember_FirstName").toUpperCase();
		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));

		SW.SelectCommunicationType();//selecting communication type
		SW.ClearValue("NavigatorHomePage_SPGnum_EB"); // Clearing the SPG number
		//SW.EnterValue("NavigatorHomePage_SPGnum_EB", SW.TestData("SPGNumber_BenefitTracker") +Keys.TAB); // Entering the SPG number
		SW.EnterValue("NavigatorHomePage_Email_EB", SW.TestData("SPGmember_EmailAddress"));
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT"); // WAit for the begin-search button to be enabled
		SW.Click("NavigatorHomePage_Search_BT"); //Click on the begin-search
		
		String firstName = SW.GetText("NavigatorSearchPage_FirstName_DT");
		SW.CompareTextContained("FIrstName_comparision-", exp_GuestFirstName, firstName);

	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
