package testscripts.Discovery;
/** Purpose		: Validate Generating Survey Response Report
 * TestCase Name: Validate Generating Survey Response Report
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG05_ValidateGeneratingSurveyResponseReport {
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
	public void GenerateSurveyResponseReport(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscNavigationPanel_Reports_LK");
		SW.Click("DiscReports_microStrategy_LK");
		SW.Click("DiscReports_SurveyResponse_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Click("DiscReports_SurveyResponseSurvey_RB");
		SW.Click("DiscReports_RunDocument_BT");
		SW.Wait(60);
		if(SW.ObjectExists("DiscReports_SurveyReportSurveyID_DT")){
			Environment.loger.log(Level.INFO, "Survey response report generated successfully");
			SW.GetScreenshot("SurveyResponseSurvey");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
		}else{
			Environment.loger.log(Level.ERROR, "Survey response report failed to generate");
			SW.FailCurrentTest("Survey response report failed to generate");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
