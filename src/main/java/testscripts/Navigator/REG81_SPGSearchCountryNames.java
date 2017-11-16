package testscripts.Navigator;
/* Purpose		: This script is for search for country names
 * TestCase Name: SPG search_country names
 * Created By	: Dhivya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG81_SPGSearchCountryNames{
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}

	@Test 
	public void phonecountry(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		//Selecting Communication Type.
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		SW.EnterValue("NavigatorHomePage_PhoneCountry_EB", "ang");
		List<String> Countrys1=SW.GetAllText("NavigatorHomePage_phonecountry_DD");
		int length=Countrys1.size();
		for(int i=0;i<length;i++)
		{
			SW.CompareTextContained("validate the result","ang",Countrys1.get(i).toLowerCase());	
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
