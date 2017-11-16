package testscripts.sgrRegression;

import org.apache.log4j.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR{
	CRM SW = new CRM();

	@BeforeClass
	public void Intialize(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	
	@Test(priority=1)
	public void GetGuestDataFromInhouse(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
