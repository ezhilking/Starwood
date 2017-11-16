package testscripts.resCon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class OUTHERS35_VerifyShowFileButton {
	CHANNELS SW = new CHANNELS();
//	String EventName = SW.RandomString(6);
	String MailID;
	String Xpath;
//	String password;
	String EventName = "PshbsW";
	String password="NNCNW9EE";
	String SecondWindowURL,MainWindowURL;
	String crsConf;
	String errorMSg;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		MailID = SW.TestData("email");
		Environment.SetBrowserToUse("FF");
//		SW.LaunchBrowser(Environment.RESCON);
	}

	//Login as Admin*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_

	@Test(priority=1)
	public void MPF(){
//		SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
//		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password"));
//		SW.Click("ResconLogin_Login_BT");
//		SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
//		SW.Click("ResconMpFlow_SelectMpFlowInvite_BT");
//		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD",SW.TestData("PID"));
//		SW.EnterValue("ResconMpFlow_Event_EB", EventName);
//		SW.EnterValue("ResconMpFlow_Email_EB", MailID);
//		//		SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 1, Calendar.DATE));
//		//		SW.Click("ResconMpFlow_Finish_BT");
//		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD",SW.TestData("PID"));
//		Xpath= "//*[text()[contains(.,'"+EventName+"')]]";
//		SW.Click("ResconMpFlow_Logout_BT");
//		password = SW.GetPassword(EventName);
//		System.out.println(EventName);
//		System.out.println(password);
	}
	@Test(priority=2)
	public void Admin()
	{
//		SW.NavigateTo(Environment.RESCON+"mp");
//		SW.EnterValue("ResconLogin_Username_EB", MailID);
//		SW.EnterValue("ResconLogin_Password_EB",password);
//		SW.Click("ResconLogin_Login_BT");
//		for (int i = 0; i < 6; i++) {
//			SW.Click("ResconMpFlow_UploadFiles_BT");
//			SW.SwitchToWindow(2);
//			SW.FileUpload("ResconMpFlow_FileContentBrowse_BT", "RoomingListTemplate.xlsx");	
//			SW.EnterValue("ResconMpFlow_FileDescription_EB","File description "+SW.RandomString(8));
//			SW.Click("ResconMpFlow_Upload_BT");
//			SW.Click("ResconMpFlow_UploadWindowClose_BT");
//			SW.SwitchToWindow(1);
//		}
//		
//		//  Bring back the control to the primary window
//		//	int rowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
//		//	SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(rowCount));
//		SW.ClickByJavascript("ResconMpFlow_LogoutImage_BT");//TODO
//		//	SW.HandleAlert(true);//Handle Logout pop
//		Utility.CloseBrowser();
 	}
	@Test(priority=3)
	public void MPFIdentity(){
		Environment.SetBrowserToUse("IE");
		SW.LaunchBrowser(Environment.RESCON);
		SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password"));
		SW.Click("ResconLogin_Login_BT");
		SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
		SW.Click("ResconMpFlow_MPUploadList_LK");      	
		for (int i = 0; i < 10; i++) {
			if (!SW.ObjectExists("ResconMpFlow_AddNewEvent_BT")){
				SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
				SW.Click("ResconMpFlow_MPUploadList_LK");
			}
			else{
				break;
			}
		}
		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD",SW.TestData("PID"));
		Xpath= "//*[text()[contains(.,'"+EventName+"')]]";
		SW.Click(Xpath);
		int FinalrowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
		SW.CompareText("ResconMpFlow_UploadTable_WT", "8",Integer.toString(FinalrowCount));
		SecondWindowURL  = SW.GetCurrentURL();
		SW.Click("ResconMpFlow_View_BT");
		SW.SwitchToWindow(2);
		MainWindowURL  = SW.GetCurrentURL();
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		//issue
		SW.NavigateTo(MainWindowURL);	
		SW.Click("ResconMpFlow_ExcelAlertHandle_BT");
		SW.DropDown_SelectByValue("ResconMpFlow_SelectFileEventForNewFile_DD", "4236550:0");
		if (SW.ObjectExists("ResconMpFlow_SelectFileEventForNewFile_BT")) {
			Reporter.Write("ShowButton", "ResconMpFlow_SelectFileEventForNewFile_BT", "ResconMpFlow_SelectFileEventForNewFile_BT", "successfull");
			
		} else {
			Reporter.Write("ShowButton", "ResconMpFlow_SelectFileEventForNewFile_BT", "ResconMpFlow_SelectFileEventForNewFile_BT", "Fail");

		}
		SW.ObjectExists("ResconMpFlow_SelectFileEventForNewFile_BT");
		SW.Click("ResconMpFlow_SelectFileEventForNewFile_BT");
		SW.Click("ResconMpFlow_SaveChanges_BT");
	//	SW.Click("ResconMpFlow_errorMsg_DT");	
		String successMSg=SW.GetText("ResconMpFlow_successMsg_DT");
		Reporter.Write("MpflowMoreThan2Room", successMSg, successMSg, "pass");
		
//		SW.Click("ResconMpFlow_Close_BT");		
//		SW.HandleAlert(true);
//			 SW.Wait(5);
//			 SW.NavigateTo(SecondWindowURL);
//			 SW.RefreshPage();
//	 //		 SW.SwitchToWindow(1);
//			 if(SW.IsEnabled("ResconMpFlow_Transfer_BT", "Enabled")){
//				 Reporter.Write("ResconMpFlow_Transfer_BT", "Transfer button should be enabled", "Transfer button is enabled", "PASS");
//			 }else{
//				 Reporter.Write("ResconMpFlow_Transfer_BT", "Transfer button should be enabled", "Transfer button is enabled", "FAIL");
//			 }
//			 SW.Click("ResconMpFlow_Transfer_BT");
//			 SW.DropDown_SelectByValue("ResconMpFlow_SelectGroupBlock_DD", "20208");
//			 SW.EnterValue("ResconMpFlow_SelectEventname_ET", EventName);
//			 SW.EnterValue("ResconMpFlow_SelectNotificationEmail_ET", MailID);
//			 SW.Wait(20);
//			 SW.Click("ResconMpFlow_SelectNextBtn_BT");
//
//			 SW.DropDown_SelectByValue("ResconMpFlow_AddRoomType_DD", "AKS");
//			 SW.Wait(20);
//			 SW.Click("ResconMpFlow_NextBtnAddNewTransfer_BT");
//
//			 SW.SelectRadioButton("ResonMpFlow_ConfirmRadioBtnRTS_RB");
//			 SW.Click("ResconMpFlow_ProcessBtnReviewTransferPg_BT");
//			 SW.Click(Xpath);
//			 SW.Click("ResconMpFlow_SummaryPgBtn_BT");
//			 SW.Click("ResconMP_DownloadRl_ST");
//			
//			 //		SW.Click("ResconMpFlow_ShowSuccessBtnTransferInfoPg_BT");
//			 //	    crsConf=SW.GetText("ResconMpFlow_CrsConfNumber_BT");
//			 //	    System.out.println(crsConf);
//			 //	    SW.Click("ResconMpFlow_ShowSuccessBtnTransferInfoPg_BT");
//			 SW.Click("ResconMpFlow_TransferSummaryBtn_BT");
//			 String  ErrorMsg=SW.GetText("ResconMpFlow_ErrorMsgTransferInfoPg_ST");
//			 System.out.println(ErrorMsg);
//			 System.out.println("No more Reservations");
//			 SW.Click("ResconMpFlow_TransferSummaryBtn_BT");
//			 SW.Click("ResconMpFlow_Logout_BT");
	}
	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
