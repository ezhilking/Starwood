package testscripts.gcRegression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.Utility;


public class GC_EmailValidation {

	CRM SW = new CRM();	
	String sMessage;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//Environment.SetBrowserToUse("FF");
		//SW.LaunchBrowser("https://login.microsoftonline.com");
	}

	@Test(priority=1)
	public void EmailLogin() throws InterruptedException{
		//if(SW.ObjectExists("//input[@id='cred_userid_inputtext']"))
		//{
			//SW.EnterValue("//input[@id='cred_userid_inputtext']", "sharanya.bannuru@starwoodhotels.com");
			//SW.Click("//input[@id='cred_password_inputtext']");
		//}
		//SW.EnterValue("//input[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']",SW.TestData("StarmailUserId"));
		//SW.EnterValue("//input[@id='ctl00_ContentPlaceHolder1_PasswordTextBox']",SW.TestData("StarmailPwd"));
		//SW.Click("//input[@id='ctl00_ContentPlaceHolder1_SubmitButton']");
		//if(SW.ObjectExists("//a[@id='ShellMail_link']"))
			//SW.Click("//a[@id='ShellMail_link']");	
		//SW.Click("//*[@id='primaryContainer']/div[4]/div/div[1]/div[2]/div[1]/div[1]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/button");

		//WebElement ActionObject = SW.GetObject("//*[@id='primaryContainer']/div[4]/div/div[1]/div[2]/div[1]/div[1]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/div/div[1]/div/form/div/input");
		//ActionObject.clear();
		//ActionObject.sendKeys(String.valueOf("QA3:test emergency message Regression R4"));
		//ActionObject.sendKeys(String.valueOf(Keys.ENTER));
		//SW.Click("//*[@id='primaryContainer']/div[4]/div/div[1]/div[2]/div[1]/div[1]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/div/div[1]/div/button[1]");
        //SW.CheckBox("html/body/div[2]/div/div[3]/div[4]/div/div[1]/div[2]/div[5]/div[2]/div[1]/div/div/div[5]/div[2]/div[2]/div[1]/div/div/div[1]/div[2]/div[1]","ON");
        
		SW.LaunchBrowser("http://phxaplqas71.nssd.star:9168/bop");
		SW.BopLogin("sbannuru", "Ammu16$$");
		//SW.wait(10);
		SW.Click("//a[text()='SGCADMIN']");
	//	SW.Click("//a[@linktext='SGCADMIN']");
		SW.NormalClick("BopAdmin_Misc_Lk");
		SW.NormalClick("BopMisc_BeanShell_LK");
		SW.EnterValue("BopBeanShell_Query_EB", "com.starwood.gcp.app.offer.OffersCache.refreshCache();");
		SW.Click("BopeBeanShell_Execute_BN");
		Utility.CloseBrowser();
        


	}
}
