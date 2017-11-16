package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: SPG Search -Screen Layout-SPG Search Validation Messages- Negative - SPG with Name Search 
 * TestCase Name: REG09_Validate_SPGNumberwithNameSearch.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG09_Validate_SPGNumberwithNameSearch {
	CHANNELS SW=new CHANNELS() ;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void SPGNumberWithNameSearch(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"), SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		// Entering the SPG Number in Number Search
		SW.Click("NavigatorHomePage_SPGnum_EB");
		SW.EnterValue("NavigatorHomePage_SPGnum_EB", SW.TestData("SPGnum_created"));
		//Entering Last name in Name Search
		SW.NormalClick("NavigatorHomePage_SearchByName_LK");
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		SW.EnterValue("NavigatorHomePage_LastName_EB", SW.TestData("SPGmember_LastName"));
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		//Validating the Error message
		SW.WaitTillElementToBeClickable("NavigatorHomePage_ErrorMessageByName_DT");
		if(SW.ObjectExists("NavigatorHomePage_ErrorMessageByName_DT")){
			// comparing the error message
			if(SW.CompareText("Compare the error message","Name Search requires Last Name and 1 other search criteria entry.",SW.GetText("NavigatorHomePage_ErrorMessageByName_DT")))
				Environment.loger.log(Level.INFO,"The Error message has displayed as Expected--->"+SW.GetText("NavigatorHomePage_ErrorMessageByName_DT"));
			else{
				Environment.loger.log(Level.INFO,"Error messgae has not present");
				SW.FailCurrentTest("Validation Fails in checking Error message");
			}
			// validating the SPG Number in Number Search
			SW.Click("NavigatorHomePage_SearchByNumber_LK");
			SW.DoubleClick("NavigatorHomePage_SearchByNumber_LK");
			if(SW.CompareText("The SPG number should clear", "", SW.GetText("NavigatorHomePage_SPGnum_EB")))
				Environment.loger.log(Level.INFO, "The SPG number get Ignored by using Name Search");
			else{
				Environment.loger.log(Level.INFO,"SPG Member feild failed to ignored");
				SW.FailCurrentTest("Validation Fails in ignoring SPG Number");
			}
		}
		else{
			Environment.loger.log(Level.INFO,"No Such feild has found");
			SW.FailCurrentTest("Validation Fails in checking Error message");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
