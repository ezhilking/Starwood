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

public class SMOKE036_LocateGuestByPhoneNum {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchGuestSPGNum() {

		//Expected Values 
		String exp_SPGNUm = SW.TestData("SPGnum_created");
		String phoneNum = SW.TestData("SPGmember_PhoneNum");

		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));

		SW.SelectCommunicationType();//selecting communication type
		//SW.WaitTillElementToBeClickable("NavigatorHomePage_Country_BT");
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		SW.DoubleClick("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		SW.EnterValue("NavigatorHomePage_PhoneCountry_EB", "India(+91)" + Keys.TAB);
		
		SW.NormalClick("NavigatorHomePage_PhoneNumber_EB"); //CLicking on the phone number Edit box
		SW.EnterValue("NavigatorHomePage_PhoneNumber_EB", SW.TestData("SPGmember_PhoneNum")); //Entering the phone number
		
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT"); // WAit for the begin-search button to be enabled
		SW.NormalClick("NavigatorHomePage_Search_BT"); //Click on the begin-search

		String SPGNumber = SW.GetText("NavigatorSearchPage_SPGnumber_DT"); //Preferred  SPG : #42008470124 
		String actualSPGnumber = SPGNumber.substring(19);
		SW.CompareText("SPGnum_verification", exp_SPGNUm, actualSPGnumber);		
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
