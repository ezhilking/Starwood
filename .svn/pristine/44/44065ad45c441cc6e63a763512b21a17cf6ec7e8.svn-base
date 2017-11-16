package testscripts.CSF;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script is used for create Liaison File
 * TestCase Name: REG22_CreateLaisionRequest.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG22_CreateLaisionRequest {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId = "1965";
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void CreateCSV(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", SW.RandomString(5));
		SW.EnterValue("CSFHome_Lastaname_EB", SW.RandomString(5));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("PropertyID"));
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Liaison Request");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.DropDown_SelectByIndex("CSFLaisionPage_Title_DD", 1);
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "9787451236");
		SW.DropDown_SelectByIndex("CSFLaisionPage_PrimaryPhone_DD", 1);
		SW.EnterValue("CSFSummary_Email_EB", SW.RandomString(5)+"@test.com");
		SW.DropDown_SelectByIndex("CSFSummary_EmailStatus_DD", 1);
		SW.EnterValue("CSFSummary_Country_EB", "US");
		SW.EnterValue("CSFLaisionPage_City_EB", "New York");
		SW.EnterValue("CSFLaisionPage_PostalCode_EB", "10014");
		SW.EnterValue("CSFLaisionPage_State_EB", "NY");
		SW.Click("CSFLaisionPage_AddRequest_LK");
		SW.WaitTillElementToBeClickable("CSFLaisionPage_Detail_DD");
		SW.DropDown_SelectByIndex("CSFLaisionPage_Detail_DD", 2);
		String detail=SW.DropDown_GetSelectedText("CSFLaisionPage_Detail_DD");
		SW.EnterValue("CSFLaisionPage_Comment_EB", SW.RandomString(10));
		SW.Click("CSFLaisionPage_Continue_LK");
		SW.CompareText("Checking the Description", detail, SW.GetText("CSFLaisionPage_RequestDescription_DT"));
		SW.ClickAndProceed("CSFPropComm_SaveAndHome_BT");
		if(!SW.IsAlertPresent()){
			SW.ClickAndProceed("CSFPropComm_SaveAndHome_BT");
		}
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
	public void EndTest() {
		Reporter.StopTest();
	}
}
