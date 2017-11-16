package testscripts.CSF;
/* Purpose		: Validate the exception amount added in the View History screen  for HCAF when the charge to type is selected as System Error for a CSV file 
 * TestCase Name: REG09_ValidateExceptionAmt.java
 * Created By	: Sharmila Begam
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

public class REG09_ValidateExceptionAmt {
	CRM SW = new CRM();
	String sEmail="test@accenture.com";
	String sAmt="100";
	String CSFFileNo;
	String SysError;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority = 0)
	public void CSFCreateCSVFileExceptionAmount() {
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			SysError="Credit - Hotel Authorization Form";
		}
		else if(Environment.getRunEnvironment().equalsIgnoreCase("STAGE")){
			SysError="Hotel Credit Approval Form";
		}
		SW.Click("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResConf_PropertyID_EB", SW.TestData("PropertyID"));
		SW.EnterValue("CSFResConf_ArrivalDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -21,
				Calendar.DATE));
		SW.EnterValue("CSFResConf_ToDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -18,
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
		SW.EnterValue("CSFSummary_Email_EB", sEmail);
		SW.DropDown_SelectByText("CSFSummary_EmailStatus_DD", "Available");
		SW.EnterValue("CSFSummary_Country_EB", "US");
		SW.Wait(3);
		SW.Click("CSFNavigation_Details_LK");
		SW.WaitForPageload();
		SW.SelectRadioButton("CSFDetails_Compliment_RB");
		SW.DropDown_SelectByIndex("CSFDetail_Top10Sel_DD", 1);
		String GCDText = SW.DropDown_GetSelectedText("CSFDetail_Top10Sel_DD");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		if (SW.CompareTextContained("GCD ADDED ", GCDText,
				SW.GetText("CSFDetail_AddGCD_WT")))
			Environment.loger.log(Level.INFO, "GCD has Added ");
		else {
			Environment.loger.log(Level.ERROR, "GCD not Added");
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
		SW.Click("CSFHome_CSFNumber_LK");
		SW.Click("CSF_Resolution_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("CSFResolution_Resolution_DD", "Other");
		SW.EnterValue("CSFResolution_Phone_EB", "9856474566");
		SW.EnterValue("CSFResolution_Email_EB", sEmail);
		SW.DropDown_SelectByText("CSFResolution_Comm_DD", "Email");
		SW.CheckBox("CSFResolution_CompensationInd_CB", "OFF");
		SW.DropDown_SelectByText("CSFResolution_Exception_DD", "Guest Loyalty");
		SW.DropDown_SelectByText("CSFResolution_Charge_DD", "System Error");
		SW.DropDown_SelectByText("CSFResolution_SystemError_DD", "SYSTEM ERROR");
		SW.DropDown_SelectByText("CSFResolution_Type_DD",SysError);
		SW.EnterValue("CSFResolution_ExceptionAmount_EB", sAmt);
		SW.EnterValue("CSFResolution_HotelName_EB", "TestHotel");
		SW.EnterValue("CSFResolution_HotelEmail_EB", sEmail);
		SW.EnterValue("CSFResolution_Explanation_EB", "Validate the Exception Amount");
		SW.Click("CSFResolution_AddandSend_BT");
		String Resolutiontable = SW.GetText("CSFResolution_ResultTable_WT");
		if (SW.CompareTextContained("Compare Resolution Table",
				SysError+" 100 USD System Error",
				Resolutiontable))
			Environment.loger.log(Level.INFO, "Resolution Data matched");
		else {
			Environment.loger.log(Level.ERROR, "Resolution Data not Matched");
			SW.FailCurrentTest("Validation fails in Resolution Page");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		SW.Click("CSFHome_CSFNumber_LK");
		if (SW.CompareTextContained("Closed", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO,
					"Status has changed to Closed Sucessfully!!! ");
		else {
			Environment.loger.log(Level.ERROR, "Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		SW.Click("CSFSummary_View_LK");
		String sView = SW.GetText("//table[@id='evts']//tr/td[contains(text(),'"+sEmail+"')]");
		if (SW.CompareTextContained("Sent Hotel Credit Approval Form "+sEmail, sView))
			Environment.loger.log(Level.INFO,"Exception added in View History table");
		else {
			Environment.loger.log(Level.ERROR, "Exception doesn't added");
			SW.FailCurrentTest("Exception Doesn't added");
		}
		sView=SW.GetText("//table[@id='evts']//tr/td[contains(text(),'Exception Amount:')]");
		if(SW.CompareTextContained("Exception Amount: "+sAmt+".0",sView))
			Environment.loger.log(Level.INFO, "Exception amount added");
	}

	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}

}
