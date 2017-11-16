package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: SPG Search -Screen Layout-Check by Name
 * TestCase Name: REG01_ScreenLayout_CheckbyName.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG01_ScreenLayout_CheckbyName {
	CHANNELS SW = new CHANNELS();
	String Before="rgba(139, 129, 129, 1)";
	String After="rgba(255, 255, 255, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=1)
	public void checkByName() {
		// Login into the application
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();// selecting communication type
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); // CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		// Verify all the elements in By Name are present in the navigator
		if (SW.ObjectExists("NavigatorHomePage_LastName_EB"))
			Environment.loger.log(Level.INFO,"The Last Name TextBox is Present");

		if (SW.ObjectExists("NavigatorHomePage_FirstName_EB"))
			Environment.loger.log(Level.INFO,"The First Name TextBox is Present");

		if (SW.ObjectExists("NavigatorHomePage_CityOrZipCode_EB"))
			Environment.loger.log(Level.INFO,"The City or Zipcode TextBox is Present");

		if (SW.ObjectExists("NavigatorHomePage_State_EB"))
			Environment.loger.log(Level.INFO, "The State TextBox is Present");

		if (SW.ObjectExists("NavigatorHomePage_Country_EB"))
			Environment.loger.log(Level.INFO, "The County TextBox is Present");

		if (SW.ObjectExists("NavigatorHomePage_Search_BT")) {// checking button exist
			if (SW.CompareText("Compare the Button Color Before enabled ",Before,SW.GetCSSValue("NavigatorHomePage_Search_BT", "color"))){//checking the color before enabled
				Environment.loger.log(Level.INFO,"The BeginSearch Button is Present");
				SW.EnterValue("NavigatorHomePage_LastName_EB",SW.TestData("SPGmember_LastName")); // Entering the last name
				SW.EnterValue("NavigatorHomePage_FirstName_EB",SW.TestData("SPGmember_FirstName") + Keys.TAB);
				SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT");
				if (SW.CompareText("Compare Button color after enabled",After,SW.GetCSSValue("NavigatorHomePage_Search_BT", "color")))//checking the color after enabled
					Environment.loger.log(Level.INFO,"The BeginSearch Button has changed in color ");
				else {
					Environment.loger.log(Level.INFO,"The BeginSearch Button not changed");
					SW.FailCurrentTest("Validation Fails in checking button changed");
				}
				SW.Click("NavigatorHomePage_Search_BT");
				SW.Click("NavigatorHomePage_Search_BT");
			}
		}else{
			Environment.loger.log(Level.INFO,"The BeginSearch Button is not present");
			SW.FailCurrentTest("Validation Fails in checking button");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
