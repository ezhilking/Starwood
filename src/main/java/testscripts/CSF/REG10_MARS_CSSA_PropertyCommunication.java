package testscripts.CSF;
/* Purpose		: MARS- CSSA Property Communication - Create Propcom file through CSF for MARS Property
 * TestCase Name: REG10_MARS_CSSA_PropertyCommunication.java
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

public class REG10_MARS_CSSA_PropertyCommunication {
	CRM SW = new CRM();
	String sEmail="test@accenture.com";
	String sAmt="100";
	String CSFFileNo;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority = 0)
	public void CreatePropComm() {
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		//Changing the PropAdmin CheckBoxes
		SW.Click("CSFHome_Admin_LK");
		SW.Click("CSFPropAdmin_PropAdmin_LK");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("PropertyID"));
		SW.Click("CSFPropAdmin_Load_BT");
		SW.CheckBoxSetOptionForAll("//input[@type='checkbox']","OFF");
		SW.Wait(5);
		SW.CheckBox("CSFPropComm_SRS_CB", "ON");
		SW.Wait(2);
		SW.Click("CSF_Home_LK");
		//Creating PropComm 
		SW.Click("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResConf_PropertyID_EB", SW.TestData("PropertyID"));
		SW.EnterValue("CSFResConf_ArrivalDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -15,
				Calendar.DATE));
		SW.EnterValue("CSFResConf_ToDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -12,
				Calendar.DATE));
		SW.Click("CSFResConf_Find_BT");
		SW.WaitTillElementToBeClickable("CSFResConf_ResultTable_LK");
		SW.Click("CSFResConf_ResultTable_LK");
		SW.Click("CSFHome_FindPropCom_BT");
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "9872456130");
		SW.EnterValue("CSFSummary_Email_EB", "Test@test.com");
		SW.DropDown_SelectByIndex("CSFPropComm_FollowTime_DD", 1);
		SW.DropDown_SelectByIndex("CSFPropComm_FollowMethod_DD", 1);
		SW.DropDown_SelectByIndex("CSFPropComm_FollowLang_DD", 2);
		SW.DropDown_SelectByIndex("CSFPropComm_Detail_DD", 2);
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
		if(SW.CompareText("Check Status", "HN", Check))
			Environment.loger.log(Level.INFO,"Status Changed from HD to HN");
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
