package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: SPG Search -Screen Layout-Check SPG Search UI 
 * TestCase Name: REG04_ScreenLayout_CheckSPG_SearchUI.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG04_ScreenLayout_CheckSPG_SearchUI {
	String Before="rgba(0, 136, 204, 1)";
	String After="rgba(0, 85, 128, 1)";
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test(priority=0)
	public void CheckSearchUIByName() {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));//Login into the application
		SW.SelectCommunicationType();//selecting communication type
		if(SW.CompareText("Checking the Color on By name Before Hover",Before, SW.GetCSSValue("NavigatorHomePage_SearchByName_LK", "color"))){
			Environment.loger.log(Level.INFO,"The Color of By Name before hover is as expected");
			SW.MoveToObject("NavigatorHomePage_SearchByName_LK");
			if(SW.CompareText("Checking the Color on By name After Hover",After, SW.GetCSSValue("NavigatorHomePage_SearchByName_LK", "color")))
				Environment.loger.log(Level.INFO,"The Color of By Name before hover is as expected");
			else {
				Environment.loger.log(Level.ERROR,"The Color Doesn't Change after hover");
				SW.FailCurrentTest("Validation Fails in checking Hover over in By name");
			}
		}else {
			Environment.loger.log(Level.ERROR,"The Color Doesnt match with orginal color");
			SW.FailCurrentTest("Validation Fails in checking Orginal color in By Name");
		}
	}
	@Test (priority=1)
	public void CheckSearchUIByNumber()
	{
		SW.Click("NavigatorHomePage_SearchByName_LK");
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		if(SW.CompareText("Checking the Color on By name Before Hover",Before, SW.GetCSSValue("NavigatorHomePage_SearchByNumber_LK", "color"))){
			Environment.loger.log(Level.INFO,"The Color of By Name before hover is as expected");
			SW.MoveToObject("NavigatorHomePage_SearchByNumber_LK");
			if(SW.CompareText("Checking the Color on By name After Hover",After, SW.GetCSSValue("NavigatorHomePage_SearchByNumber_LK", "color")))
				Environment.loger.log(Level.INFO,"The Color of By Name before hover is as expected");
			else {
				Environment.loger.log(Level.ERROR,"The Color Doesn't Change after hover");
				SW.FailCurrentTest("Validation Fails in checking Hover over in By name");
			}
		}else{
			Environment.loger.log(Level.ERROR,"The Color Doesnt match with orginal color");
			SW.FailCurrentTest("Validation Fails in checking Orginal color in By Name");
		}

	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
