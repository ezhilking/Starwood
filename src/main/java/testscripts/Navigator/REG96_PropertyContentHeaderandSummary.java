package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Property Content - Header and Summary
 * TestCase Name: 'Property Content - Header and Summary
 * Created By	: Dhivya
 * Modified By	:
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG96_PropertyContentHeaderandSummary {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test(priority=0)
	public void CheckPropertyContent(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_PropertyID_EB");
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", SW.TestData("PropertyID")+" ");
		SW.Wait(2);
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", Keys.ENTER);
		SW.NormalClick("NavigatorHomePage_BeginSearch_BT");
		SW.NormalClick("NavigatorHomePage_PropertyNTB_LK");
		SW.Click("NavigatorHomePage_PropertyNTB_LK");
		
		if(SW.ObjectExists("NavigatorPropPage_property details page address_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_property details page address_DT"), "Fail");	
		
		if(SW.ObjectExists("NavigatorPropPage_property details page phone_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "Pass");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_property details page phone_DT"), "Fail");

		
		if(SW.ObjectExists("NavigatorPropPage_property details page name_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "Pass");		
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_property details page name_DT"), "Fail");
		
		if(SW.ObjectExists("NavigatorPropPage_property details page logo_ic"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "Pass");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_property details page logo_ic"), "Fail");
		if(SW.ObjectExists("NavigatorPropPage_property details page close_BT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "Pass");		
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_property details page close_BT"), "Fail");

	}

	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}


