package testscripts.CSF;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG24_Validate_UnreleasedCRTab_CRAddedToCSV_File {
	CRM SW = new CRM();
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
		SW.CompareTextContained(sGCD, SW.GetText("CSFDetail_AddGCD_WT"));
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
		SW.DropDown_SelectByText("CSFResolution_Type_DD","Check Requested");
		SW.EnterValue("CSFResolution_Amount_EB", "50");
		SW.EnterValue("CSFResolution_AddressLine_EB", "Ecospace");
		SW.EnterValue("CSFResolution_City_EB", "KARNATAKA");
		SW.EnterValue("CSFResolution_Country_EB", "US");
		SW.EnterValue("CSFResolution_State_EB", "CA");
		SW.EnterValue("CSFResolution_PayableTo_EB", "user");
		SW.EnterValue("CSFResolution_ZipCode_EB", "10001");
		SW.Click("CSFResolution_Add_BT");	
		String Resolutiontable=SW.GetText("CSFResolution_ResultTable_WT");
		if(SW.CompareTextContained("Compare Resolution Table", "GWG - CCS Will Pay", Resolutiontable))
			Environment.loger.log(Level.INFO, "Resolution Data matched");
		else
		{
			Environment.loger.log(Level.ERROR,"Resolution Data not Matched");
			SW.FailCurrentTest("Validation fails in Resolution Page");
		}
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
		SW.Click("CSFHome_Admin_LK");
		SW.Click("CSFAdmin_UserAdmin_LK");
		SW.EnterValue("CSFAdmin_NameFilter_EB", SW.TestData("CSFUsername"));
		SW.Click("CSFAdmin_Filter_BT");
		if(SW.CompareTextContained("billing", SW.GetText("CSFAdmin_userrole_DT")))
		{
			Environment.loger.log(Level.INFO, "Already the user in billing role");
		}
		else{
			SW.Click("CSFAdmin_userrole_DT");
			SW.DropDown_SelectByText("CSFAdmin_Role_DD", "Billing");
			SW.DropDown_SelectByIndex("CSFAdmin_CashPoint_DD", 3);
			SW.Click("CSFAdmin_Done_BT");
			SW.Click("CSFAdmin_SaveChanges_BT");
		}
		SW.Click("CSF_Home_LK");
		SW.Click("CSFHomePage_Unreleased_LK");
		SW.WaitTillElementToBeClickable("//table[@id='csfsTBL']//td[text()='"+CSFFileNo+"']");
		if(SW.ObjectExists("//table[@id='csfsTBL']//td[text()='"+CSFFileNo+"']"))
			Reporter.Write("Checking the file in unreleased Request", "CSF file should display", CSFFileNo, "PASS");
		else
			Reporter.Write("Checking the file in unreleased Request", "CSF file should display", CSFFileNo, "FAIL");
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
