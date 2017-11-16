package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Property Content - Header and Summary
 * TestCase Name: 'Property Content - services and Amenities
 * Created By	: Dhivya
 * Modified By	:
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG98_PropertyContentservicesandAmenities{
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
		SW.NormalClick("NavigatorPropPage_services and amenities_LK");
		if(SW.CompareText("Comparing the Panel header", "Services and Amenities", SW.GetText("NavigatorPropPage_Services and Communities Header_DT"))){
			SW.NormalClick("NavigatorPropPage_Services and Communities Header alphabets_DT");
			SW.NormalClick("NavigatorPropPage_Services and Communities list_DT");
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities pop up header_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities pop up header_DT"), "Fail");
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities type_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities type_DT"), "Fail");
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities P.N_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities P.N_DT"), "Fail");	
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities indicator_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities indicator_DT"), "Fail");	
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities e-mail_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities e-mail_DT"), "Fail");
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities fee description_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities fee description_DT"), "Fail");
			if(SW.ObjectExists("NavigatorPropPage_Services and Communities close_DT"))
				Reporter.Write("Checking all the data in property details page", "Data Found", "Data Found", "PASS");
			else
				Reporter.Write("Checking all the data in property details page", "Object not found", SW.GetLocator("NavigatorPropPage_Services and Communities close_DT"), "Fail");
		}
	}
			@AfterClass
			public void EndTest(){
				SW.NavigatorLogOut();
				Reporter.StopTest();		
			}
}
		

