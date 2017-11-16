package testscripts.Discovery;
/** Purpose		: Validate Generating Survey Response with Free Form Answers
 * TestCase Name: Validate Generating Survey Response with Free Form Answers
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

public class REG06_ValidateGeneratingSurveyResponseFreeFormAnswers {
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
	public void GenerateSurveyResponseFreeFormReport(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscNavigationPanel_Reports_LK");
		SW.Click("DiscReports_microStrategy_LK");
		SW.Click("DiscReports_SurveyResponseWithFreeFormAns_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("DiscReports_SurveyResponseSurvey_RB");
		SW.Click("DiscReports_RunDocument_BT");
		SW.Wait(30);
		if(SW.ObjectExists("DiscReports_SurveyReportSurveyID_DT")){
			Environment.loger.log(Level.INFO, "Survey response with free form ans report generated successfully");
			SW.GetScreenshot("SurveyResponseWithFreeFormAns");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
		}else{
			Environment.loger.log(Level.ERROR, "Survey response with free form ans report failed to generate");
			SW.FailCurrentTest("Survey response report with free form ans failed to generate");
			SW.GetScreenshot("SurveyStatisticsOutlineSurvey");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
