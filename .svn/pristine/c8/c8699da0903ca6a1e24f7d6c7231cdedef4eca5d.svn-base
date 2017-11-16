package testscripts.Discovery;
/** Purpose		: Validate the initial display and functionality of the Language drop down list values in the custom search screen
 * TestCase Name: 1. Validate the initial display and functionality of the Language drop down list values in the custom search screen
 * 				  2. Validate the report generation from Custom search screen of the discovery metric report by selecting one custom search field
 * 				  3. Validate the Survey response Report in French  Language
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG08_ValidateCustomSearchScreen {
	CRM SW = new CRM();
	String UserName;
	String Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");// Script will not work in IE Driver because of IE Driver issue
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");
	
	}

	@Test(priority=1)
	public void ValidateLangugaesDropDown(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscNavigationPanel_Reports_LK");
		SW.Click("DiscReports_microStrategy_LK");
		SW.Click("DiscReports_CustomSearch_LK");
		List<String> UILanguages= SW.DropDown_GetText("DiscReports_CustomSearchLanguage_DD");
		List<String> ExpLanguages=new ArrayList<String>();
		//Add expected languages in the list
		ExpLanguages.add("None");
		ExpLanguages.add("ALL");
		ExpLanguages.add("Arabic");
		ExpLanguages.add("Bahasa Indonesia");
		ExpLanguages.add("Chinese (Simplified)");
		ExpLanguages.add("Chinese (Traditional)");
		ExpLanguages.add("Dutch");
		ExpLanguages.add("English");
		ExpLanguages.add("French");
		ExpLanguages.add("German");
		ExpLanguages.add("Italian");
		ExpLanguages.add("Japanese");
		ExpLanguages.add("Korean");
		ExpLanguages.add("Polish");
		ExpLanguages.add("Portuguese");
		ExpLanguages.add("Russian");
		ExpLanguages.add("Spanish");
		ExpLanguages.add("Thai");
		ExpLanguages.add("Turkish");
		if(UILanguages.equals(ExpLanguages)){
			Environment.loger.log(Level.INFO, "All Languages are present in the list ");
			SW.GetScreenshot("Languages_validation");

		}else{
			Environment.loger.log(Level.ERROR, "All languages are not present in the list ");
			SW.FailCurrentTest("All languages are not present in the list ");
		}
	}
	@Test(priority=2, dependsOnMethods="ValidateLangugaesDropDown")
	public void GenerateReportForFrench(){
		SW.DiscoveryEnterValue("DiscCustomReports_Startdate_EB", "12/01/2016");
		SW.DiscoveryEnterValue("DiscCustomReports_Enddate_EB", "12/15/2016");
		SW.DropDown_SelectByText("DiscReports_CustomSearchLanguage_DD", "French");
		SW.Click("DiscCustomReports_GenerateReports_BT");
		SW.Wait(10);
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Wait(30);
		if(SW.ObjectExists("//span[text()='Discovery Survey Report']")){
			Environment.loger.log(Level.INFO, "Custom Survey report is generated successfully");
			SW.GetScreenshot("CustomSurveyReport");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
		}else{
			Environment.loger.log(Level.ERROR, "Custom Survey report is not generated successfully");
			SW.FailCurrentTest("Custom Survey report is not generated successfully");
		}
		
	}

	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
