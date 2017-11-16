package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: SPG Search Validation Messages- Search By Last Name-Negative
 * TestCase Name: REG08_SearchByOnlyLastName
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG08_SearchByOnlyLastName {
	CHANNELS SW=new CHANNELS() ;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void searchOnlyByLastName(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"), SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SearchByName_LK");
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		SW.EnterValue("NavigatorHomePage_LastName_EB", "Test"+Keys.TAB);
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		// comparing the error message...
		if(SW.CompareText("Compare the error message","Name Search requires Last Name and 1 other search criteria entry.",SW.GetText("NavigatorHomePage_ErrorMessageByName_DT")))
			Environment.loger.log(Level.INFO,"The Error message has displayed as Expected--->"+SW.GetText("NavigatorHomePage_ErrorMessageByName_DT"));
		else{
			Environment.loger.log(Level.INFO,"Error message is not dispayed ");
			SW.FailCurrentTest("Validation Fails in checking Error message");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
