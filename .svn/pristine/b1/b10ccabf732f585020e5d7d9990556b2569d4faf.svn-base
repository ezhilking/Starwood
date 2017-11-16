package testscripts.Navigator;
/* Purpose		: Search Guest Profile 
 * TestCase Name: Searching Guest Profile by Last Name  and Zip COde
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 06/09/2016
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

public class SMOKE012_LocateGuestByLastNameAndCity {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchGuestSPGNameAndCity() {

		String exp_lastName=SW.TestData("SPGmember_LastName").toUpperCase();			
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		//Enter Name and Search
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.EnterValue("NavigatorHomePage_LastName_EB", exp_lastName); //Entering the last name
		SW.Click("NavigatorHomePage_CityOrZipCode_EB"); //Clicking on the  ZIp Code Edit Box
		SW.EnterValue("NavigatorHomePage_CityOrZipCode_EB", "bangalore"+ Keys.TAB); // Entering the City value
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_LastName_DT");
		String actual_LastName = SW.GetText("NavigatorSearchPage_LastName_DT");
		SW.CompareText("Last Name Validation",exp_lastName, actual_LastName);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
