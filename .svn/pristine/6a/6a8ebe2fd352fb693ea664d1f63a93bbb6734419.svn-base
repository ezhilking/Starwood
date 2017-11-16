package testscripts.Navigator;



import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: Select the country code and clear the country code, Then again select another country code.
 * TestCase Name: REG11_SPGSearch_ClearAndReEnter.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG11_SPGSearch_ClearAndReEnter {
	CHANNELS SW=new CHANNELS() ;
	int random;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void ClearandReEnter() {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"), SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		//Selecting the country form list
		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		SW.Wait(5);
		random=SW.RandomNumber(1,10);
		SW.Click("(//span[text()='Select a Country']//..//..//div[2]//ul)//li['"+random+"']");
		//clearing the selected country
		String Before=SW.GetText("NavigatorHomePage_SelectedCountry_DT");
		Environment.loger.log(Level.INFO, "The Country Selected is "+Before);
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Clear_BT");
		SW.Click("NavigatorHomePage_Clear_BT");
		String After =SW.GetText("NavigatorHomePage_PhoneCountry_BT");
		if(SW.CompareText("Text has Cleared ", "Select a Country", After))
			Environment.loger.log(Level.INFO, "Country code is cleared");
		else{
			Environment.loger.log(Level.INFO,"Country cod is not cleared");
			SW.FailCurrentTest("Validation Fails in checking Country code is Cleared");
		}
		//Reselecting the country form list

		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		random=SW.RandomNumber(1,12);
		SW.Wait(5);
		SW.Click("(//span[text()='Select a Country']//..//..//div[2]//ul)//li['"+random+"']");
		//clearing the selected country
		Before=SW.GetText("NavigatorHomePage_SelectedCountry_DT");
		Environment.loger.log(Level.INFO, "The Country Selected is "+Before);
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Clear_BT");
		SW.Click("NavigatorHomePage_Clear_BT");
		After =SW.GetText("NavigatorHomePage_PhoneCountry_BT");
		if(SW.CompareText("Text has Cleared ", "Select a Country", After))
			Environment.loger.log(Level.INFO, "Country code is cleared");
		else{
			Environment.loger.log(Level.INFO,"Country cod is not cleared");
			SW.FailCurrentTest("Validation Fails in checking Country code is Cleared");
		}
	}

	//Quitting the intance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}