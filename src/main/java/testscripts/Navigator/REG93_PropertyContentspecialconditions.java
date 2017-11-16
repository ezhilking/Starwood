package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Property Content - special conditions
 * TestCase Name: 'Property Content - special conditions
 * Created By	: Dhivya
 * Modified By	:
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG93_PropertyContentspecialconditions{
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
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", SW.TestData("PropertyID1")+" ");
		SW.Wait(2);
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", Keys.ENTER);
		SW.NormalClick("NavigatorHomePage_BeginSearch_BT");
		SW.NormalClick("NavigatorHomePage_PropertyNTB_LK");
		SW.Click("NavigatorHomePage_PropertyNTB_LK");
		SW.NormalClick("NavigatorPropPage_specialconditions_LK");
		if(SW.ObjectExists("NavigatorPropPage_ special conditions item_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_ special conditions item_DT"), "Fail");
		
		if(SW.ObjectExists("NavigatorPropPage_ special conditions  des_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_ special conditions  des_DT"), "Fail");
		
		if(SW.ObjectExists("NavigatorPropPage_ special conditions B.date_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
		Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_ special conditions B.date_DT"), "Fail");
		
		if(SW.ObjectExists("NavigatorPropPage_ special conditions E.date_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_ special conditions E.date_DT"), "Fail");
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}