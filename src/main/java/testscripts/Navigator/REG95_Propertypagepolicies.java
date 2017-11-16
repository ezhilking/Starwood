package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Property Content - Policies
 * TestCase Name: 'Property Content - policies
 * Created By	: Dhivya
 * Modified By	:
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG95_Propertypagepolicies{
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
		SW.NormalClick("NavigatorPropPage_Policies_LK");
		SW.NormalClick("NavigatorPropPage_ Policies alphabets_DT");
		SW.NormalClick("NavigatorPropPage_ policies list_DT");
		
		if(SW.ObjectExists("NavigatorPropPage_policies pop up header_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_policies pop up header_DT"), "Fail");
		
		if(SW.ObjectExists("NavigatorPropPage_policies desc_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_policies desc_DT"), "Fail");
		if(SW.ObjectExists("NavigatorPropPage_policies policy type_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_policies policy type_DT"), "Fail");
		if(SW.ObjectExists("NavigatorPropPage_policies policy _DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_policies policy _DT"), "Fail");
		if(SW.ObjectExists("NavigatorPropPage_policies amount_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_policies amount_DT"), "Fail");
		if(SW.ObjectExists("NavigatorPropPage_policies frequency_DT"))
			Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
		else
			Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_policies frequency_DT"), "Fail");
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}