package testscripts.Navigator;
/* Purpose		: Search Guest Profile 
 * TestCase Name: Searching Guest Profile by First ANd Last NAme
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

public class SMOKE003_LocateGuestByNameFirstAndLast {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchGuestByName() {

		String exp_lastName=SW.TestData("SPGmember_LastName").toUpperCase();
		String exp_firstName=SW.TestData("SPGmember_FirstName").toUpperCase();

		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));

		//Enter Name and Search
		SW.SelectCommunicationType();		
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.EnterValue("NavigatorHomePage_LastName_EB", SW.TestData("SPGmember_LastName")); //Entering the last name

		SW.EnterValue("NavigatorHomePage_FirstName_EB", SW.TestData("SPGmember_FirstName")+Keys.TAB);
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");

		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_LastName_DT");
		String actual_LastName = SW.GetText("NavigatorSearchPage_LastName_DT");
		SW.CompareText("LastNameValidation",exp_lastName, actual_LastName);
		String actual_firstName = SW.GetText("NavigatorSearchPage_FirstName_DT");
		SW.CompareText("FirstNameValidation",exp_firstName, actual_firstName);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
