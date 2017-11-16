package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose          : This Script is used for Validate associate messages
 * TestCase Name	: Validate associate messages
 * Created By 		: Pradeep Kumar
 * Modified By      : 
 * Modified Date	: 
 * Reviewed By      :      
 * Reviewed Date	:
 */

public class REG51_ValidateAssociateMessages {
	CHANNELS SW=new CHANNELS();
	String color="rgba(7, 38, 65, 1)";
	String Size ="17pt";
	String UserName,Password;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		UserName=SW.TestData("NavigatorUsername");
		Password=SW.TestData("NavigatorPassword");
	}
	@Test
	public void sample(){
		//Login into the application

		SW.SwitchToFrame("NavigatorLogin_Login_FR");
		SW.EnterValue("NavigatorLogin_Username_EB", UserName);
		SW.EnterValue("NavigatorLogin_Password_EB", Password);
		SW.WaitTillElementToBeClickable("NavigatorLogin_Login_BT");
		SW.Click("NavigatorLogin_Login_BT");     

		String ErrorMessage = "Login FAiled";
		if(SW.ObjectExists("NavigatorLogin_Username_EB")){
			Assert.fail("Login failed for Username: "+UserName+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			Environment.loger.log(Level.INFO, "Navigator Login Success");
			SW.WaitTillElementToBeClickable("NavigatorHomePage_Close_BT");
			//Verify Agent message Pop-up
			if(SW.ObjectExists("NavigatorHomePage_AssociateMessage_DT")){
				Environment.loger.log(Level.INFO, "Associate Message appears ");
				String CompColor = SW.GetCSSValue("NavigatorHomePage_Associate_DT", "background-color");
				//Verify Agent message font color
				if(SW.CompareTextContained("Checking the Color on By name Before Hover",color,CompColor )){             
					Environment.loger.log(Level.INFO, "Agent message font colour verifyed");
				}else{
					Assert.fail("Agent message colour is not matching");
				}
				//Verify Agent message font size
				String compsize = SW.GetCSSValue("NavigatorHomePage_Associate_DT", "font-size");
				if(SW.CompareTextContained("Checking the Color on By name Before Hover",Size,compsize )){
					Environment.loger.log(Level.INFO, "Agent message font size verifyed");
				}else{
					Assert.fail("Agent message font size not matching");
				}
			}
		}
		SW.Click("NavigatorHomePage_Close_BT"); // Closing the pop-up
		if(SW.ObjectExists("NavigatorHomePage_Close_BT"))
			SW.Click("NavigatorHomePage_Close_BT"); 
	}

	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();       
	}

}
