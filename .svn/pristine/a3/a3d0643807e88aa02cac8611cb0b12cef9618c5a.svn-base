package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: SPG Search -Screen Layout-Check Begin Search Button
 * TestCase Name: REG06_CheckBeginSearchButton.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG06_CheckBeginSearchButton {
	CHANNELS SW=new CHANNELS() ;
	String Before="rgba(139, 129, 129, 1)";
	String After="rgba(255, 255, 255, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	//Checking the Begin Search Button in By Number search Fields 
	@Test(priority=0)
	public void checkBeginSearchInByNumber()
	{
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();//selecting communication type
		SW.WaitTillElementToBeClickable("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_SPGnum_EB");
		if (SW.ObjectExists("NavigatorHomePage_Search_BT")) {// checking button exist
			if (SW.CompareText("Compare the Button Color Before enabled ",Before,SW.GetCSSValue("NavigatorHomePage_Search_BT", "color"))){//checking the color before enabledd
				Environment.loger.log(Level.INFO,"The BeginSearch Button is Present");
				SW.Click("NavigatorHomePage_SPGnum_EB");
				SW.EnterValue("NavigatorHomePage_SPGnum_EB", "510315272");
				SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT");
				if (SW.CompareText("Compare Button color after enabled",After,SW.GetCSSValue("NavigatorHomePage_Search_BT", "color"))){//checking the color after enabled
					SW.ClearValue("NavigatorHomePage_SPGnum_EB");
					Environment.loger.log(Level.INFO,"The BeginSearch Button has changed in color ");
				}else {
					Environment.loger.log(Level.ERROR,"The BeginSearch Button not changed");
					SW.FailCurrentTest("Validation Fails in checking button changed");	
				}
			}
		} else {
			Environment.loger.log(Level.ERROR,"The BeginSearch Button is not present");
			SW.FailCurrentTest("Validation Fails in checking button");
		}	
	}

	//Checking the Begin Search Button in By Name Search fields
	@Test(priority=1)
	public void checkBeginSearchInByName()
	{
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); // CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		if (SW.ObjectExists("NavigatorHomePage_Search_BT")) {// checking button exist
			if (SW.CompareText("Compare the Button Color Before enabled ",Before,SW.GetCSSValue("NavigatorHomePage_Search_BT", "color")))//checking the color before enabled
			{
				Environment.loger.log(Level.INFO,"The BeginSearch Button is Present");
				SW.EnterValue("NavigatorHomePage_LastName_EB","Test"); // Entering the last name
				SW.EnterValue("NavigatorHomePage_FirstName_EB","Test");
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
		} else {
			Environment.loger.log(Level.INFO,
					"The BeginSearch Button is not present");
			SW.FailCurrentTest("Validation Fails in checking button");
		}
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 

}
