package testscripts.Navigator;
/* Purpose		: SPG Search- Alpha & Numeric -Negative 
 * TestCase Name: REG14_SearchWithAlphaAndNumeric.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG14_SearchWithAlphaAndNumeric {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchwithAlphaNumeric(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		//Selecting Communication Type.
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		//entering garbage value in country code text box
		SW.EnterValue("NavigatorHomePage_PhoneCountry_EB", "5A100Si");
		//comparing that no result has found
		String sCountry=SW.GetText("NavigatorHomePage_SelectCountry_DT");
		if (SW.CompareText("Validate the result of search", "No results",sCountry))
			Environment.loger.log(Level.INFO,"No results has found as expected....");
		else {
			Environment.loger.log(Level.INFO,"Some country has displayed as a result");
			SW.FailCurrentTest("Validation Fails in Checking the result found");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
