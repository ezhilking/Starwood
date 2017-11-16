package testscripts.Discovery;
/** Purpose		: Validate Generating Survey Statistics Summary
 * TestCase Name: Validate Generating Survey Statistics Summary
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

public class REG07_ValidateGeneratingSurveyStatisticsSummary {
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
	public void GenerateSurveyStatisticSummaryReport(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscNavigationPanel_Reports_LK");
		SW.Click("DiscReports_microStrategy_LK");
		SW.Click("DiscReports_SurveyStatisticsSummaryOutlineFormat_LK");
		SW.Wait(10);
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		String SurveySelected= SW.GetText("DiscReports_SurveyStatisticsOutlineSurvey_LK");
		SW.DoubleClick("DiscReports_SurveyStatisticsOutlineSurvey_LK");
		
		SW.Click("DiscReports_RunReport_BT");
		SW.Wait(30);
		if(SW.ObjectExists("//div[text()='"+SurveySelected+"']")){
			Environment.loger.log(Level.INFO, "Survey Statistics summary report generated successfully");
			SW.GetScreenshot("SurveyStatisticsOutlineSurvey");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
		}else{
			Environment.loger.log(Level.ERROR, "Survey Statistics summary report failed to generate");
			SW.FailCurrentTest("Survey Statistics summary report failed to generate");
			SW.GetScreenshot("SurveyStatisticsOutlineSurvey");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
