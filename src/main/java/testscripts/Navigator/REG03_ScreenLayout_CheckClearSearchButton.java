package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: SPG Search -Screen Layout-Check ClearSearchButton
 * TestCase Name: REG03_ScreenLayout_CheckClearSearchButton.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG03_ScreenLayout_CheckClearSearchButton {
	CHANNELS SW = new CHANNELS();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=1)
	public void checkClearButtonByNumber(){
		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();//selecting communication type
		SW.WaitTillElementToBeClickable("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_SPGnum_EB");
		if(!SW.ObjectExists("NavigatorHomePage_Clear_BT")){
			Environment.loger.log(Level.INFO,"The Clear Button is not present as Expected");
			SW.EnterValue("NavigatorHomePage_SPGnum_EB", "510315272");
			SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT");
			if(SW.ObjectExists("NavigatorHomePage_Clear_BT")){
				Environment.loger.log(Level.INFO,"The Clear Button is  present");
				SW.Click("NavigatorHomePage_Clear_BT");
			}
		}else {
			Environment.loger.log(Level.ERROR,"The Clear Button is  present");
			SW.FailCurrentTest("Validation Fails in checking Clear button");
		}	
	}
	@Test(priority=2,dependsOnMethods ="checkClearButtonByNumber")
	public void checkClearButtonByName(){	
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); // CLicking on the
		// link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		if(!SW.ObjectExists("NavigatorHomePage_Clear_BT")){
			Environment.loger.log(Level.INFO,"The Clear Button is not present as Expected");
			SW.EnterValue("NavigatorHomePage_LastName_EB", "Test"); //Entering the last name
			SW.EnterValue("NavigatorHomePage_FirstName_EB", "Test"+Keys.TAB);
			SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT");
			if(SW.ObjectExists("NavigatorHomePage_Clear_BT")){
				Environment.loger.log(Level.INFO,"The Clear Button is  present");
				SW.Click("NavigatorHomePage_Clear_BT");
			}else
				Environment.loger.log(Level.ERROR,"The Clear Button is not present");
		}
		else {
			Environment.loger.log(Level.ERROR,"The Clear Button is  present");
			SW.FailCurrentTest("Validation Fails in checking Clear button");
		}	
	}


	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
