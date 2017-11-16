package testscripts.CSF;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG18_GssRequestFlow {
	CRM SW = new CRM();
	String sEmail="test@accenture.com";
	String sAmt="100";
	String CSFFileNo,sUsername,sPassword;
	String sPropId="1965";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority = 0)
	public void CreatePropComm() {
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			sUsername="kishkum";
			sPassword="abc";
		}
		else if(Environment.getRunEnvironment().equalsIgnoreCase("STAGE")){
			sUsername="";
			sPassword="";
		}
		//Changing the PropAdmin CheckBoxes
		SW.Click("CSFHome_Admin_LK");
		SW.Click("CSFPropAdmin_PropAdmin_LK");
		SW.EnterValue("CSFHome_PropertyID_EB", sPropId);
		SW.Click("CSFPropAdmin_Load_BT");
		SW.CheckBoxSetOptionForAll("//input[@type='checkbox']","OFF");
		SW.Wait(5);
		SW.CheckBox("CSFPropComm_SRS_CB", "ON");
		SW.CheckBox("CSFPropAdmin_ESS_CB", "ON");
		SW.Wait(2);
		SW.Click("CSF_Home_LK");
		//Creating PropComm 
		SW.EnterValue("CSFHome_Firstname_EB", "Fname"+SW.RandomString(3));
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname"+SW.RandomString(3));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", sPropId);
		SW.Click("CSFHome_FindPropCom_BT");
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "9872456130");
		SW.EnterValue("CSFSummary_Email_EB", "Test@test.com");
		SW.DropDown_SelectByIndex("CSFPropComm_FollowTime_DD", 1);
		SW.DropDown_SelectByIndex("CSFPropComm_FollowMethod_DD", 1);
		SW.DropDown_SelectByIndex("CSFPropComm_FollowLang_DD", 2);
		SW.DropDown_SelectByText("CSFPropComm_Detail_DD", "Folio/Bill Request");
		SW.Click("CSFPropComm_Comment_EB");
		SW.EnterValue("CSFPropComm_CommentPopUp_EB", "Automation Test");
		SW.Click("CSFPropComm_CommentOK_BT");
		SW.Click("CSFPropComm_AddRequest_LK");
		SW.WaitTillElementToBeClickable("CSFPropComm_Event_WT");
		SW.Click("CSFPropComm_SaveAndHome_BT");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);
		SW.Click("CSFHome_CSFNumber_LK");
		String Check=SW.GetText("CSFPropComm_Event_WT");
		if(SW.CompareText("Check Status", "GP", Check))
			Environment.loger.log(Level.INFO,"Status Changed from HD to GP");
		else {
			Environment.loger.log(Level.ERROR, "Status Doesn't Changed");
			SW.FailCurrentTest("Validation Fails in Status Change");
		}
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
		//login with billing supervisor
		SW.CSFLogin(sUsername, sPassword);
		SW.EnterValue("CSFHome_CSFSearch_EB", CSFFileNo);
		SW.Click("CSFHome_CSFSearchGo_BT");
		SW.Click("CSFPropComm_Edit_LK");
		SW.WaitTillElementToBeClickable("CSFPRopComm_Continue_LK");
		SW.EnterValue("CSFPropComm_Note_EB", "sample");
		SW.Click("CSFPRopComm_Continue_LK");
		SW.Wait(10);
		SW.Click("CSFPropComm_Save_BT");
		SW.Wait(5);
		SW.HandleAlert(true);
		SW.Wait(5);
		SW.WaitTillElementToBeClickable("CSFPropComm_Event_WT");
		Check=SW.GetText("CSFPropComm_Event_WT");
		if(SW.CompareText("Check Status", "GIP", Check))
			Environment.loger.log(Level.INFO,"Status Changed from GP to GIP");
		else {
			Environment.loger.log(Level.ERROR, "Status Doesn't Changed");
			SW.FailCurrentTest("Validation Fails in Status Change");
		}
		SW.Click("CSFPropComm_Edit_LK");
		SW.WaitTillElementToBeClickable("CSFPRopComm_Continue_LK");
		SW.DropDown_SelectByText("CSFPropComm_Status_DD", "Pending Property Response");
		SW.EnterValue("CSFPropComm_Note_EB", SW.RandomString(5));
		SW.Click("CSFPRopComm_Continue_LK");
		SW.Wait(10);
		SW.Click("CSFPropComm_Save_BT");
		SW.Wait(5);
		SW.HandleAlert(true);
		SW.Wait(5);
		SW.WaitTillElementToBeClickable("CSFPropComm_Event_WT");
		Check=SW.GetText("CSFPropComm_Event_WT");
		if(SW.CompareText("Check Status", "PP", Check))
			Environment.loger.log(Level.INFO,"Status Changed from GIP to PP");
		else {
			Environment.loger.log(Level.ERROR, "Status Doesn't Changed");
			SW.FailCurrentTest("Validation Fails in Status Change");
		}
		SW.Click("CSFPropComm_Edit_LK");
		SW.WaitTillElementToBeClickable("CSFPRopComm_Continue_LK");
		SW.DropDown_SelectByText("CSFPropComm_Status_DD", "Closed");
		SW.EnterValue("CSFPropComm_Note_EB", SW.RandomString(5));
		SW.Click("CSFPRopComm_Continue_LK");
		SW.Wait(10);
		SW.Click("CSFPropComm_Save_BT");
		SW.Wait(5);
		SW.HandleAlert(true);
		SW.Wait(5);
		SW.WaitTillElementToBeClickable("CSFPropComm_Event_WT");
		Check=SW.GetText("CSFPropComm_Event_WT");
		if(SW.CompareText("Check Status", "CL", Check))
			Environment.loger.log(Level.INFO,"Status Changed from PP to CL");
		else {
			Environment.loger.log(Level.ERROR, "Status Doesn't Changed");
			SW.FailCurrentTest("Validation Fails in Status Change");
		}
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
