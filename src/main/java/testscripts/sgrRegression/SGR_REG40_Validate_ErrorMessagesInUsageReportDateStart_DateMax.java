package testscripts.sgrRegression;
/** Purpose		: Validate error messages in Usage Report for Date Start and Date Max fields
 * TestCase Name: Validate error messages in Usage Report for Date Start and Date Max fields
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG40_Validate_ErrorMessagesInUsageReportDateStart_DateMax {
	CRM SW = new CRM();
	String EventNotes,SelectedDescription;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void ValidateErrorMessage365days(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_UsageReport_LK");
		SW.EnterValue("SGRUsageReport_StartDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-YYYY"), "dd-MMM-YYYY", -2, Calendar.YEAR));
		SW.EnterValue("SGRUsageReport_EndDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-YYYY"), "dd-MMM-YYYY", -2, Calendar.YEAR));
		SW.Click("SGRUsageReport_Submit_BT");
		
		if(SW.ObjectExists("//div[text()='Date Range is too far in the past; please select dates  within the last 365 days']")){
			Environment.loger.log(Level.INFO, "Error Message displayed for 365 days date range");
			SW.TakeScreenshot("ErrorMessage365");
		}else{
			Environment.loger.log(Level.ERROR, "Error message is not displayed for 365 days date range");
			Reporter.Write("NA", "Error message is not displayed for 365 days date range", "Error message is not displayed for 365 days date range","FAIL");
		}
	}
	@Test(priority= 2, dependsOnMethods="ValidateErrorMessage365days")
	public void ValidateErrorMessage32days(){
		SW.EnterValue("SGRUsageReport_StartDate_EB", SW.GetTimeStamp("dd-MMM-YYYY"));
		SW.EnterValue("SGRUsageReport_EndDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-YYYY"), "dd-MMM-YYYY", 1, Calendar.MONTH));
		SW.Click("SGRUsageReport_Submit_BT");
		
		if(SW.ObjectExists("//div[text()='Date Range is too large; please select a date range smaller than 32 days']")){
			Environment.loger.log(Level.INFO, "Error Message displayed for 32 days date range");
			SW.TakeScreenshot("ErrorMessage32");
		}else{
			Environment.loger.log(Level.ERROR, "Error message is not displayed for 32 days date range");
			Reporter.Write("NA", "Error message is not displayed for 32 days date range", "Error message is not displayed for 32 days date range","FAIL");
		}
	}
	//The Start Date should be before the Max Date
	@Test(priority= 3, dependsOnMethods="ValidateErrorMessage32days")
	public void ValidateErrorMessageStartDateGreaterThanEndDate(){
		SW.EnterValue("SGRUsageReport_StartDate_EB", SW.GetTimeStamp("dd-MMM-YYYY"));
		SW.EnterValue("SGRUsageReport_EndDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-YYYY"), "dd-MMM-YYYY", -1, Calendar.MONTH));
		SW.Click("SGRUsageReport_Submit_BT");
		
		if(SW.ObjectExists("//div[text()='The Start Date should be before the Max Date']")){
			Environment.loger.log(Level.INFO, "Error Message displayed for Invalid date range");
			SW.TakeScreenshot("ErrorMessage32");
		}else{
			Environment.loger.log(Level.ERROR, "Error message is not displayed for Invalid date range");
			Reporter.Write("NA", "Error message is not displayed for Invalid date range", "Error message is not displayed for Invalid date range","FAIL");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
