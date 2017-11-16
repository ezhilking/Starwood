package testscripts.CSF;
/* Purpose		: Validate if the modal popup is displayed on the Details page when a Property alert does not have a GCD for CSV files
 * TestCase Name: REG17_Validate_Property_alert.java
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

public class REG17_Validate_Property_alert {
	CRM SW = new CRM();
	String CSFFileNo;
	String sAlertMsg="Automation Test GCD";
	String GCD="property / property";
	String sPropId="1965";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void CSFCreateFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if (SW.ObjectExists("CSF_AcknowledgePopUp_LK")) {
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.Click("CSFHome_Admin_LK");
		SW.Click("CSFAdmin_CustomerServiceAlert_LK");
		SW.Click("CSFAlert_Add_BT");
		SW.EnterValue("CSFAlert_AlertName_EB","sample");
		SW.DropDown_SelectByText("CSFAlert_FileType_DD", "CSV");
		SW.DropDown_SelectByText("CSFAlert_AlertType_DD", "Property");
		SW.EnterValue("CSFAlert_GCD_EB",GCD );
		SW.Wait(5);
		SW.Click("CSFAddGCD_LookUp_LK");
		SW.EnterValue("CSFAlert_PropID_EB", sPropId);
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
		SW.EnterValue("CSFHome_Firstname_EB", "Fname"+SW.RandomString(3));
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname"+SW.RandomString(3));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", sPropId);
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Customer Service");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		//summary page
		SW.DropDown_SelectByIndex("CSFSummary_IntialContact_DD", 2);
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "98574612");
		SW.EnterValue("CSFSummary_Email_EB", "test@accenture.com");
		SW.DropDown_SelectByText("CSFSummary_EmailStatus_DD", "Available");
		SW.EnterValue("CSFSummary_Country_EB", "US");
		SW.Click("CSFNavigation_Details_LK");
		SW.WaitForPageload();
		//detail page
		SW.EnterValue("CSFDetails_LookUp_EB", GCD);
		SW.Click("CSFAddGCD_LookUp_LK");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		SW.Wait(5);
		String AlertMSG=SW.GetText("CSFHome_Alert_DT");
		if(SW.CompareTextContained(sAlertMsg, AlertMSG))
			Environment.loger.log(Level.INFO,"General Alerted Sucessfully!!! ");
		 else {
			Environment.loger.log(Level.ERROR, "Alert Not Displayed");
			SW.FailCurrentTest("Validation fails in checking General Alert ");
		}
		SW.Click("CSF_AcknowledgePopUp_LK");
		if(SW.CompareTextContained("GCD ADDED ", GCD, SW.GetText("CSFDetail_AddGCD_WT")))
			Environment.loger.log(Level.INFO, "GCD has Added ");
		else
		{
			Environment.loger.log(Level.ERROR,"GCD not Added");
			SW.FailCurrentTest("Validation fails in Adding GCD");
		}
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
