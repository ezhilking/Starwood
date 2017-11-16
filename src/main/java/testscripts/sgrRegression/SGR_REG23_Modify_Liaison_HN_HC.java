package testscripts.sgrRegression;
/** Purpose		: This is to validate the workflow of the propcomm  file when the file is with property
 * TestCase Name: Modify Liaison requests_HN_HC in SGR
 * Created By	: Sachin G
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

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG23_Modify_Liaison_HN_HC {
	CRM SW = new CRM();
	String CSFFileNo;
	String PropID="1965";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority=1)
	public void CreateliasonFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));//Login to CSF Application
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.Click("CSFNavigationPanel_Home_LK");
		SW.NormalClick("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResConf_PropertyID_EB",PropID);
		SW.EnterValue("CSFResConf_ArrivalDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -15,
				Calendar.DATE));
		SW.EnterValue("CSFResConf_ToDate_EB", SW.DateAddDays(
				SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -12,
				Calendar.DATE));
		SW.Click("CSFResConf_Find_BT");
		SW.WaitTillElementToBeClickable("CSFResConf_ResultTable_LK");
		SW.Click("CSFResConf_ResultTable_LK");
		SW.DropDown_SelectByValue("CSFHome_TypeCreate_DD", "LIA");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.Click("CSFLiaison_AddReq_LK");
		SW.DropDown_SelectByIndex("CSFLiaisonAddReq_SelectDetail_DD", 1);
		SW.EnterValue("CSFLiaisonAddReq_Comments_EB", "Automation Comment");
		SW.Click("CSFLiaisonAddReq_Continue_LK");
		SW.ClickAndProceed("CSFPropComm_SaveAndHome_BT");
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);
		SW.Click("CSFHome_CSFNumber_LK");
		String Check=SW.GetText("CSFLiaison_FileStatus_DT");
		if(SW.CompareText("Check Status", "HN", Check)){
			Environment.loger.log(Level.INFO,"Status Changed from HD to HN");
			SW.GetScreenshot("StatusChage");
			SW.ClickAndProceed("CSF_Logout_LK");
			SW.HandleAlert(true);
		}
		else {
			Environment.loger.log(Level.ERROR, "Status Doesn't Changed");
			SW.FailCurrentTest("Validation Fails in Status Change");
		}
	}

	@Test(priority=2, dependsOnMethods ="CreateliasonFile")
	public void ValidateInSGRHN(){
		SW.WaitTillElementToBeClickable("CSFLogin_Password_BN");//To avoid immediate navigate to SGRUURL, It will wait till CSF application log out successfully.
		SW.NavigateTo(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), PropID);//Login to SGR application
		SW.Click("SGRNavigation_LuxuryRequests_LK");
		SW.DoubleClick("//*[text()='"+CSFFileNo+" "+"']/..");
		SW.WaitForAppLoad();
		SW.Click("SGRGuestprofile_Edit_LK");
		SW.DropDown_SelectByValue("SGRLiaison_ChangeStatus_DD", "HC");
		SW.Click("SGRCCCFile_SaveChanges_LK");
		String Actual=SW.GetText("SGRCCCFile_FileStatus_DT");
		SW.CompareText("CompareFileStatus", "HC", Actual);
		SW.Click("SGR_Logout_LK");
	}

	@Test(priority=3, dependsOnMethods ="ValidateInSGRHN")
	public void ValidateInCSF(){
		SW.NavigateTo(Environment.CSFURL);
		SW.WaitTillPresenceOfElementLocated("CSFLogin_Password_BN");
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSF_EnterCSFID_EB", CSFFileNo);
		SW.Click("CSF_EnterCSFIDGo_BN");

		SW.CompareText("FinalStatus_DD","HC", SW.GetText("CSFliaisonFile_FileStatus_DT"));
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
		
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
