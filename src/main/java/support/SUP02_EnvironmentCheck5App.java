package support;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SUP02_EnvironmentCheck5App {
	CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*CRM Applications-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=1)
	public void SGR(){
		if(SW.ObjectExists("SGRLogin_Username_EB")){
			Reporter.Write("SGRApplicationLoginPage", "SGR login page should work as expected", "SGR login page working as expected", "PASS");
		}else{
			Reporter.Write("SGRApplicationLoginPage", "SGR login page should work as expected", "SGR login page not working as expected", "FAIL");
		}
		SW.EnterValue("SGRLogin_Username_EB", SW.TestData("SGRUsername"));
		SW.EnterValue("SGRLogin_Password_EB", SW.TestData("SGRPassword"));
		SW.Click("SGRLogin_LoginOrContinue_BN");
		SW.DropDown_SelectByValue("SGRLogin_Property_DD", "1965");
		SW.Click("SGRLogin_LoginOrContinue_BN");
		if(SW.ObjectExists("SGRHomePage_JumpTo_EB")){
			Reporter.Write("SGRApplicationHomePage", "SGR home page should work as expected", "SGR home page working as expected", "PASS");
		}else{
			Reporter.Write("SGRApplicationHomePage", "SGR home page should work as expected", "SGR home page not workinhg as expected", "FAIL");
		}
	}

	@Test(priority=2)
	public void CEM(){
		SW.NavigateTo(Environment.CEMURL);
		if(SW.ObjectExists("CEMLogin_Username_BN")){
			Reporter.Write("CEMApplicationLoginPage", "CEM login page should work as expected", "CEM login page working as expected", "PASS");
		}else{
			Reporter.Write("CEMApplicationLoginPage", "CEM login page should work as expected", "CEM login page not working as expected", "FAIL");
		}
		SW.EnterValue("CEMLogin_Username_BN", SW.TestData("CEMUsername") );
		SW.EnterValue("CEMLogin_Password_BN",  SW.TestData("CEMPassword"));
		SW.Click("CEMLogin_Submit_BN");
		//		SW.ClickAndProceed("CEMNavigation_Home_LK");
		//		if(SW.ObjectExists("CEMHome_AcknowledgeMsg_LK"))
		//			SW.ClickAndProceed("CEMHome_AcknowledgeMsg_LK");
		//		SW.HandleAlert(true);
		if(SW.ObjectExists("SGRHomePage_JumpTo_EB")){
			Reporter.Write("CEMApplicationHomePage", "CEM home page should work as expected", "CEM home page working as expected", "PASS");
		}else{
			Reporter.Write("CEMApplicationHomePage", "CEM home page should work as expected", "CEM home page not workinhg as expected", "FAIL");
		}
	}
	
	@Test(priority=3)
	public void GC(){//TODO QA#3 based
		SW.NavigateTo(Environment.GCURL);
		if(SW.ObjectExists("GCLogin_Username_EB")){
			Reporter.Write("GCApplicationLoginPage", "GC login page should work as expected", "GC login page working as expected", "PASS");
		}else{
			Reporter.Write("GCApplicationLoginPage", "GC login page should work as expected", "GC login page not working as expected", "FAIL");
		}
		SW.EnterValue("GCLogin_Username_EB",SW.TestData("GCUsername") );
		SW.EnterValue("GCLogin_Password_EB", SW.TestData("GCPassword"));
		SW.Click("GCLogin_Login_BN");
		if(SW.ObjectExists("GCHome_Search_EB")){
			Reporter.Write("GCApplicationHomePage", "GC home page should work as expected", "GC home page working as expected", "PASS");
		}else{
			Reporter.Write("GCApplicationHomePage", "GC home page should work as expected", "GC home page not workinhg as expected", "FAIL");
		}
	}

	@Test(priority=4)
	public void Discovery(){//QA#3
		SW.NavigateTo(Environment.DISCOVERYURL);
		if(SW.ObjectExists("DiscLogin_UserName_EB")){
			Reporter.Write("DiscoveryApplicationLoginPage", "Discovery login page should work as expected", "Discovery login page working as expected", "PASS");
		}else{
			Reporter.Write("DiscoveryApplicationLoginPage", "Discovery login page should work as expected", "Discovery login page not working as expected", "FAIL");
		}
		SW.EnterValue("DiscLogin_UserName_EB", SW.TestData("DisUsername"));
		SW.EnterValue("DiscLogin_Password_EB", SW.TestData("DisPassword"));
		SW.Click("DiscLogin_Login_BT");
		if(SW.ObjectExists("DiscManageSurvey_CreateSurvey_BT")){
			Reporter.Write("DiscoveryApplicationHomePage", "Discovery home page should work as expected", "Discovery home page working as expected", "PASS");
		}else{
			Reporter.Write("DiscoveryApplicationHomePage", "Discovery home page should work as expected", "Discovery home page not workinhg as expected", "FAIL");
		}
	}

	@Test(priority=5)
	public void BOB(){//TODO QA#3 based
		SW.NavigateTo(Environment.BOB);
		if(SW.ObjectExists("BopLogin_UserName_EB")){
			Reporter.Write("BOBApplicationLoginPage", "BOB login page should work as expected", "BOB login page working as expected", "PASS");
		}else{
			Reporter.Write("BOBApplicationLoginPage", "Discovery login page should work as expected", "Discovery login page not working as expected", "FAIL");
		}
		SW.EnterValue("BopLogin_UserName_EB", SW.TestData("GCUsername"));
		SW.EnterValue("BopLogin_Password_EB", SW.TestData("GCPassword"));
		SW.Click("BopLogin_Login_BN");
		if(SW.ObjectExists("BopHome_GCAdmin_Lk")){
			Reporter.Write("BOBApplicationHomePage", "BOB home page should work as expected", "BOB home page working as expected", "PASS");
		}else{
			Reporter.Write("BOBApplicationHomePage", "BOB home page should work as expected", "BOB home page not workinhg as expected", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
