package testscripts.Navigator;
/* Purpose		: SPG Search -Search Using Keys 
 * TestCase Name: REG12_SearchUsingKeys.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG12_SearchUsingKeys {
	CHANNELS SW = new CHANNELS();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void SearchUsingKeys(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"), SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		//Selecting the country form list by using keys
		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		SW.EnterValue("NavigatorHomePage_PhoneCountry_EB", ""+Keys.DOWN+Keys.DOWN+Keys.ENTER);
		String Valid=SW.GetText("NavigatorHomePage_SelectedCountry_DT");
		Environment.loger.log(Level.INFO, "The Selected Country is "+Valid);
		if(!Valid.equalsIgnoreCase(""))
			Environment.loger.log(Level.INFO, "The Country is selected by using the Keys");
		else{
			Environment.loger.log(Level.INFO,"Country code is not selected");
			SW.FailCurrentTest("Validation Fails in checking Country code is Selected");
		}
	}

	//Quitting the intance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
