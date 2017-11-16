package testscripts.CSF;
/* Purpose		: Validate the report generated with the file type as CSV Status  as ALL Alert type as Property and  search criteria as Alert Matrix
 * TestCase Name: REG08_CSVStatus_AlertMatrix.java
 * Created By	: Sharmila Begam
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

public class REG08_CSVStatus_AlertMatrix {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId = "1965";

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority = 0)
	public void CSFCreateCSVStatusFile() {
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		SW.Click("CSF_Reports_LK");
		SW.Click("CSFReports_CSFAlertReport_LK");
		SW.EnterValue("CSFReports_AlertName_EB", "Sample");
		SW.DropDown_SelectByText("CSFReports_FileType_DD", "CSV");
		SW.DropDown_SelectByText("CSFReports_AlertType_DD", "Property");
		SW.EnterValue("CSFReports_PropertyID_EB", sPropId);
		SW.SelectRadioButton("CSFReports_Report_RB");
		SW.CheckBox("CSFReports_EmailIndicator_CB", "ON");
		SW.EnterValue("CSFReprots_EmailId_EB", "sample@SWH.com");
		SW.Click("CSFReports_Go_BT");
		SW.WaitTillElementToBeClickable("CSFReports_Feedback_DT");
		if (SW.ObjectExists("CSFReports_Feedback_DT")) {
			String sCheck = SW.GetText("CSFReports_Feedback_DT");
			if (SW.CompareTextContained("Your report is ready.", sCheck)) {
				SW.WaitTillElementToBeClickable("CSFReports_ClickHere_LK");
				Environment.loger.log(Level.INFO, "Report has created ");
				SW.Click("CSFReports_ClickHere_LK");
				SW.Wait(5);
			} else {
				Environment.loger.log(Level.ERROR, "Report not yet ready");
				SW.FailCurrentTest("Validation fails in Checking the Report");
			}
		} else {
			Environment.loger.log(Level.ERROR, "No such text has found");
			SW.FailCurrentTest("Validation fails in Checking the reports");
		}
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}

	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
