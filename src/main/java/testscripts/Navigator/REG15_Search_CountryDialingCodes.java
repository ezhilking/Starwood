package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: SPG Search-Country Dialing Codes 
 * TestCase Name: REG15_Search_CountryDialingCodes.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG15_Search_CountryDialingCodes {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void SearchByCountryDailingCode(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		//Selecting Communication Type.
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		//entering garbage value in country code text box
		SW.EnterValue("NavigatorHomePage_PhoneCountry_EB", "(+91)"+Keys.ENTER);
		String sCountryCode=SW.GetText("NavigatorHomePage_SelectedCountry_DT");
		if(SW.CompareTextContained("Compare the country code", "(+91)", sCountryCode))
			Environment.loger.log(Level.INFO, "The Phone Country is selected by country code sucessfully");
		else{
			Environment.loger.log(Level.INFO,"Country is not selected by country code");
			SW.FailCurrentTest("Validation Fails in Checking the selected country with country code");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
