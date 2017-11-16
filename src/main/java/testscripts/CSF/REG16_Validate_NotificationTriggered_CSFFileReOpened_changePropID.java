package testscripts.CSF;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
/* Purpose		: Validate the notification triggered to the old property when CSF file is re-opened due to Change in property ID. Hold_Hotel new_close_Reopen_Hold Hotel   new_Close
 * TestCase Name: REG16_Validate_NotificationTriggered_CSFFileReOpened_changePropID.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG16_Validate_NotificationTriggered_CSFFileReOpened_changePropID {
	CRM SW = new CRM();
	String sPropID2="1965";
	String CSFFileNo;
	String sGCD="Property";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void validateNotificationTrigger()
	{
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if (SW.ObjectExists("CSF_AcknowledgePopUp_LK")) {
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		//home page
		SW.EnterValue("CSFHome_Firstname_EB", "Fname");
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname");
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("PropertyID"));
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
		SW.Wait(3);
		SW.DropDown_SelectByText("CSFSummary_NoRESconf_DD", "No res conf# found");
		SW.Click("CSFNavigation_Details_LK");
		SW.WaitForPageload();
		SW.EnterValue("CSFDetails_LookUp_EB", sGCD);
		SW.Wait(3);
		if (SW.ObjectExists("CSFAddGCD_LookUp_LK")) {
			Environment.loger.log(Level.INFO,
					"Matching GCD are Displayed Sucessfully!!! ");
			SW.Click("CSFAddGCD_LookUp_LK");
		} else {
			Environment.loger.log(Level.ERROR, "No Such GCD Has found");
			SW.FailCurrentTest("Validation fails in checking Matching GCD");
		}

		SW.Click("CSFDetails_AddGCD_BN");
		if (SW.CompareTextContained(sGCD, SW.GetText("CSFDetail_AddGCD_WT"))) {
			Environment.loger.log(Level.INFO, "Respective GCD has Added!!! ");
		} else {
			Environment.loger.log(Level.ERROR, "GCD not ADDED");
			SW.FailCurrentTest("Validation fails in checking Added GCD");
		}
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(") + 1,CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+ CSFFileNo);
		SW.Click("CSFHome_CSFNumber_LK");
	//resolution page
		SW.Click("CSF_Resolution_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("CSFResolution_Resolution_DD", 1);
		SW.EnterValue("CSFResolution_Phone_EB", "9856474566");
		SW.EnterValue("CSFResolution_Email_EB", "abc@ac.com");
		SW.DropDown_SelectByText("CSFResolution_Comm_DD","Email");
		SW.CheckBox("CSFResolution_CompensationInd_CB", "OFF");
		SW.DropDown_SelectByText("CSFResolution_Charge_DD","GWG - CCS Will Pay");
		SW.DropDown_SelectByText("CSFResolution_Type_DD","SPG Points");
		SW.EnterValue("CSFResolution_Amount_EB", "50");
		SW.Click("CSFResolution_Add_BT");	
		String Resolutiontable=SW.GetText("CSFResolution_ResultTable_WT");
		if(SW.CompareTextContained("Compare Resolution Table", "SPG Points 50 Points GWG - CCS Will Pay", Resolutiontable))
			Environment.loger.log(Level.INFO, "Resolution Data matched");
		else
		{
			Environment.loger.log(Level.ERROR,"Resolution Data not Matched");
			SW.FailCurrentTest("Validation fails in Resolution Page");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Hotel New");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.Click("CSFHome_CSFNumber_LK");
		//checking hotel new status
		if(SW.CompareTextContained("Hotel New", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to HOTEL NEW Sucessfully!!! ");
		else if(SW.CompareTextContained("Takeover",SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to TAKE OVER Sucessfully!!! ");
		else
		{
			Environment.loger.log(Level.ERROR,"Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		//changing closed state
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.Click("CSFHome_CSFNumber_LK");
		if(SW.CompareTextContained("Closed", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to Closed Sucessfully!!! ");
		else
		{
			Environment.loger.log(Level.ERROR,"Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.Click("CSFHome_CSFNumber_LK");
		//reopen the CSV
		SW.Click("CSFSummary_Reopen_BT");
		SW.SelectRadioButton("CSFSummary_ChangeProp_RB");
		SW.Click("CSFSummary_PropReopen_BT");
		SW.HandleAlert(true);
		SW.EnterValue("CSFSummary_PropertyID_EB", sPropID2);
		//status as Hotel new
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Hotel New");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.Click("CSFHome_CSFNumber_LK");
		if(SW.CompareTextContained("Hotel New", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to HOTEL NEW Sucessfully!!! ");
		else if(SW.CompareTextContained("Takeover",SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to TAKE OVER Sucessfully!!! ");
		else
		{
			Environment.loger.log(Level.ERROR,"Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		//status as closed
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.Click("CSFHome_CSFNumber_LK");
		if(SW.CompareTextContained("Closed", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to Closed Sucessfully!!! ");
		else
		{
			Environment.loger.log(Level.ERROR,"Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	
	}
}
