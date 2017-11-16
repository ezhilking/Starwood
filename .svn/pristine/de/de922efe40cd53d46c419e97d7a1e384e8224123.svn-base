package testscripts.resCon;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
import functions.Utility;

public class OTHERS18_UploadExcelSpreadSheetAllMandatoryAndNonMandatoryFields {
	CHANNELS SW = new CHANNELS();
	String EventName = SW.RandomString(6);
	String Xpath;
	String password;
//	String EventName = "PshbsW";
//	String password="NNCNW9EE";
	String SecondWindowURL,MainWindowURL;
	String crsConf;
	String MailID;
	
	@BeforeClass
	public void StartTest()
	{ 
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		MailID = SW.TestData("email");
		Environment.SetBrowserToUse("GC");
		SW.LaunchBrowser(Environment.RESCON);
	}

	//Login as Admin*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_	
	@Test(priority=1)
	public void MPF(){
		SW.EnterValue("ResconLoginPage_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLoginPage_Password_EB", SW.TestData("SGP_Password"));
		SW.Click("ResconLogin_Login_BT");
		SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
		SW.Click("ResconMpFlow_SelectMpFlowInvite_BT");
		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD","1005");
		SW.EnterValue("ResconMpFlow_Event_EB", EventName);
		
	//.. ....this two steps are not req it is going to next screen without this steps............................................//
		SW.ClearValue("ResconMpFlow_Date_EB");
		SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 1, Calendar.DATE));
		SW.NormalClick("ResconMpFlow_Email_EB");
		String Jscript="document.getElementsByName('emailAddress')[0].value='"+MailID+"';";
		SW.RunJavaScript(Jscript);
		//SW.EnterValue("//input[@name='emailAddress']", MailID);
    	SW.Click("ResconMpFlow_Finish_BT");
		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD","1005");
		Xpath= "//*[text()[contains(.,'"+EventName+"')]]";
//		SW.ObjectExists(Xpath+"td");
//		SW.Click("ResconMpFlow_Resend_BT");
//		SW.Click("ResconMpFlow_ResendFrwd_BT");
		SW.Click("ResconMpFlow_Logout_BT");
		password = SW.GetPassword(EventName);
		Reporter.Write("username &password",EventName, password,"pass");
		}
	@Test(priority=2)
	public void Admin()
 	{
		SW.NavigateTo(Environment.RESCON+"mp");
		SW.EnterValue("ResconLogin_Username_EB",SW.TestData("email"));//No need to navigateTo RESCON homepage
		SW.EnterValue("ResconLogin_Password_EB",password);
		SW.Click("ResconLogin_Login_BT");
		SW.Click("ResconMpFlow_UploadFiles_BT");
		SW.SwitchToWindow(2);
		SW.FileUpload("ResconMpFlow_FileContentBrowse_BT", "RoomingListTemplate.xlsx");
		SW.EnterValue("ResconMpFlow_FileDescription_EB","File description "+SW.RandomString(8));
		SW.Click("ResconMpFlow_Upload_BT");
		SW.Click("ResconMpFlow_UploadWindowClose_BT");
		SW.SwitchToWindow(1);//Bring back the control to the primary window
	//	int rowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
		//SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(rowCount));
		SW.ClickByJavascript("ResconMpFlow_LogoutImage_BT");//TODO
		//SW.HandleAlert(true);//Handle Logout pop
		Utility.CloseBrowser();

	}

	//Login as Admin____________________________________________________________________________________________________________________

	@Test(priority=3)
	public void MPFIdentity(){
		Environment.SetBrowserToUse("IE");
		SW.LaunchBrowser(Environment.RESCON);
		SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password"));
		SW.Click("ResconLogin_Login_BT");
		SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
		SW.Click("ResconMpFlow_MPUploadList_LK");
		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD","1005");
		SW.Click(Xpath);
//		int FinalrowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
//		SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(FinalrowCount));
		SecondWindowURL  = SW.GetCurrentURL();
		SW.Click("ResconMpFlow_View_BT");
		SW.SwitchToWindow(2);
		//TODO comment
		MainWindowURL  = SW.GetCurrentURL();
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		SW.NavigateTo(MainWindowURL);
//		SW.Click("ResconMpFlow_View_BT");
		SW.Click("ResconMpFlow_ExcelAlertHandle_BT");
		SW.Click("ResconMpFlow_SaveChanges_BT");
		//	SW.ClickAndProceed("ResconMpFlow_Close_BT");
		//	SW.HandleAlert(true);
		SW.Wait(5);
		SW.NavigateTo(SecondWindowURL);
		SW.RefreshPage();
		//		SW.SwitchToWindow(1);
		if(SW.IsEnabled("ResconMpFlow_Transfer_BT", "Enabled")){
			Reporter.Write("ResconMpFlow_Transfer_BT", "Transfer button should be enabled", "Transfer button is enabled", "PASS");
		}else{
			Reporter.Write("ResconMpFlow_Transfer_BT", "Transfer button should be enabled", "Transfer button is enabled", "FAIL");
		}
		SW.Click("ResconMpFlow_Transfer_BT");
		SW.DropDown_SelectByValue("ResconMpFlow_SelectGroupBlock_DD", "20208");
		SW.Wait(20);
		SW.EnterValue("ResconMpFlow_SelectEventname_ET", EventName);
		SW.EnterValue("ResconMpFlow_SelectNotificationEmail_ET", MailID);
		SW.Click("ResconMpFlow_SelectNextBtn_BT");
		SW.DropDown_SelectByValue("ResconMpFlow_AddRoomType_DD", "AKS");
		SW.Click("ResconMpFlow_NextBtnAddNewTransfer_BT");
		SW.Click("ResconMpFlow_NextBtnAddNewTransfer_BT");
		SW.SelectRadioButton("ResonMpFlow_ConfirmRadioBtnRTS_RB");
		SW.Click("ResconMpFlow_ProcessBtnReviewTransferPg_BT");
		SW.RefreshPage();
		SW.Click(Xpath);
		SW.Click("ResconMpFlow_SummaryPgBtn_BT");
		SW.Click("ResconMpFlow_ShowSuccessBtnTransferInfoPg_BT");
		crsConf=SW.GetText("ResconMpFlow_CrsConfNumber_BT");
		System.out.println(crsConf);
		Reporter.WriteLog(Level.INFO,crsConf);
		Reporter.Write("Property Content Searched", "Property Content of login","Searched", "pass");
		SW.Click("ResconMpFlow_Logout_BT");
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
