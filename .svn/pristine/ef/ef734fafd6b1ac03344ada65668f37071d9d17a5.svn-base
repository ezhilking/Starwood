package testscripts.CSF;
/* Purpose		: Verify the Alerts hyperlink in summary screen by activate the General Alert.
 * TestCase Name: REG2_ValAlertsHyperlink
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


public class REG2_ValAlertsHyperlink {
	CRM SW = new CRM();
	String sAlertMsg="Automation Test";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void CSFCreateFile(){
		if (Environment.getRunEnvironment().equalsIgnoreCase("QA3")) {
			SW.CSFLogin(SW.TestData("CSFUsername"),
					SW.TestData("CSFPassword"));
		} else if (Environment.getRunEnvironment().equalsIgnoreCase("STAGE")) {
			SW.CSFLogin(SW.TestData("CSFUsername"),
					SW.TestData("CSFPassword"));
		}
		if (SW.ObjectExists("CSF_AcknowledgePopUp_LK")) {
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.Click("CSFHome_Admin_LK");
		SW.Click("CSFAdmin_CustomerServiceAlert_LK");
		SW.Click("CSFAlert_Add_BT");
		SW.EnterValue("CSFAlert_AlertName_EB","sample");
		SW.DropDown_SelectByText("CSFAlert_FileType_DD", "CSV");
		SW.DropDown_SelectByText("CSFAlert_AlertType_DD", "General");
		SW.EnterValue("CSFAlert_Message_EB", sAlertMsg);
		SW.Click("CSFAlert_Save_BT");
		String AlertStatus=SW.GetText("CSFAlert_SuccessMsg_DT");
		if(SW.CompareTextContained("The alert was successfully saved.", AlertStatus))
			Environment.loger.log(Level.INFO,"Alert Saved Sucessfully!!! ");
		 else {
			Environment.loger.log(Level.ERROR, "Alert has not added");
			SW.FailCurrentTest("Validation fails in checking Alert saved");
		}
		SW.Click("CSF_Home_LK");
		String AlertMSG=SW.GetText("CSFHome_Alert_DT");
		if(SW.CompareTextContained(sAlertMsg, AlertMSG))
			Environment.loger.log(Level.INFO,"General Alerted Sucessfully!!! ");
		 else {
			Environment.loger.log(Level.ERROR, "Alert Not Displayed");
			SW.FailCurrentTest("Validation fails in checking General Alert ");
		}
		SW.Click("CSF_AcknowledgePopUp_LK");
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
	
}
