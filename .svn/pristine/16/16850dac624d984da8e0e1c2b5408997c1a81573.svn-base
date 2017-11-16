package testscripts.CSF;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG15_Validate_CSF_TyepCode {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId = "1965";
	String sGCD = "General Manager";

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority = 0)
	public void ValidateMatchingGCD() {
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));

		if (SW.ObjectExists("CSF_AcknowledgePopUp_LK")) {
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.Click("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResConf_PropertyID_EB", sPropId);
		SW.EnterValue("CSFResConf_ArrivalDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -10,
				Calendar.DATE));
		SW.EnterValue("CSFResConf_ToDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -8,
				Calendar.DATE));
		SW.Click("CSFResConf_Find_BT");
		SW.WaitTillElementToBeClickable("CSFResConf_ResultTable_LK");
		SW.Click("CSFResConf_ResultTable_LK");
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Customer Service");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if (SW.ObjectExists("CSF_AcknowledgePopUp_LK")) {
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", "Fname" + SW.RandomString(3));
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname" + SW.RandomString(3));

		SW.DropDown_SelectByIndex("CSFSummary_IntialContact_DD", 2);
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "98574612");
		SW.EnterValue("CSFSummary_Email_EB", "test@accenture.com");
		SW.DropDown_SelectByText("CSFSummary_EmailStatus_DD", "Available");
		SW.EnterValue("CSFSummary_Country_EB", "US");
		SW.Wait(3);
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
		SW.HandleAlert(true);
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
		SW.Click("CSF_Billing_LK");
		SW.Click("CSFBilling_CreateCSV_BT");
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
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
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
		
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
