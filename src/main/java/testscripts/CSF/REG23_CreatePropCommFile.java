package testscripts.CSF;

import java.util.Calendar;
/* Purpose		: This Script is used for Create the PropComm request File
 * TestCase Name: REG23_CreatePropCommFile.java
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

public class REG23_CreatePropCommFile {
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
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", SW.RandomString(5));
		SW.EnterValue("CSFHome_Lastaname_EB", SW.RandomString(5));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("PropertyID"));
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "PropCom request form");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
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
		SW.ClickAndProceed("CSFPropComm_SaveAndHome_BT");
		if(!SW.IsAlertPresent())
			SW.ClickAndProceed("CSFPropComm_SaveAndHome_BT");
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
		}
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
