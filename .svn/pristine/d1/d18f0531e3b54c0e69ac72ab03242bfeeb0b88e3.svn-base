package testscripts.Navigator;
/* Purpose		: SPG Search -Look & Feel 
 * TestCase Name: REG10_SPGSearch_LookAndFeel.java
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

public class REG10_SPGSearch_LookAndFeel {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void LookAndFeel()
	{
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");

		//Validating the Country code Edit box is present 
		if(SW.ObjectExists("NavigatorHomePage_PhoneCountry_EB"))
			Environment.loger.log(Level.INFO, "Country Code Field is present");
		else{
			Environment.loger.log(Level.INFO,"No Such feild has found");
			SW.FailCurrentTest("Validation Fails in checking Country code is present");
		}
		//Validating  the list of country has displayed...
		if(SW.ObjectExists("NavigatorHomePage_CountryList_DT"))
			Environment.loger.log(Level.INFO, "The List of country has dispalyed");
		else{
			Environment.loger.log(Level.INFO,"No Such feild has found");
			SW.FailCurrentTest("Validation Fails in checking Listing the country.");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
