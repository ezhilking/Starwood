package testscripts.ABCD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN21_ORStatusReport {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	
	}

	@Test
	public void AddActivity(){
		//SW.ABCDLogin(SW.TestData("ABCD_Username"),SW.TestData("ABCD_Password"));
		
		SW.EnterValue("//input[@name='userName']", "poorman");
		SW.EnterValue("//input[@name='password']", "password");
		SW.Click("//input[@name='Submit']");	
		
		SW.MoveToObject("ABCDDealLog_Reportstab_LK");
		SW.Click("ABCDDealLog_ReportsSearch_LK");
		
		SW.DownloadFile("Contact Report","ABCDContactReport.xls");
		SW.DownloadFile("Status Report","ABCDStatusReport.xls");
	
		
		
	}

		
		
	}

