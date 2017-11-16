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

public class OUTHERS24_VerifyDelColumnRightClick {
	CHANNELS SW = new CHANNELS();
	//String EventName = SW.RandomString(6);
	String EventName = "MuGhZJ";
	String MailID;
	String Xpath;
	String password;
	String SecondWindowURL,MainWindowURL;
	String crsConf;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		MailID = SW.TestData("email");
		Environment.SetBrowserToUse("FF");
	//	SW.LaunchBrowser(Environment.RESCON);
	}

//	@Test(priority=1)
//	public void MPF(){
//		SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
//		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
//		SW.Click("ResconLogin_Login_BT");
//		SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
//		SW.Click("ResconMpFlow_SelectMpFlowInvite_BT");
//		SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD",SW.TestData("PID"));
//		SW.EnterValue("ResconMpFlow_Event_EB", EventName);
//		SW.EnterValue("ResconMpFlow_Email_EB", MailID);
//		SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 1, Calendar.DATE));
//		SW.Click("ResconMpFlow_Finish_BT");
//		Xpath= "//*[text()[contains(.,'"+EventName+"')]]";
//		SW.Click("ResconMpFlow_Logout_BT");
//		password = SW.GetPassword(EventName);
//		Reporter.WriteLog(Level.INFO,password);
//	}
	@Test(priority=2)
	public void Admin()
	{
		SW.NavigateTo(Environment.RESCON+"mp");
		SW.EnterValue("ResconLogin_Username_EB", MailID);//No need to navigateTo RESCON homepage
		SW.EnterValue("ResconLogin_Password_EB","KDV4EM7C");
		SW.Click("ResconLogin_Login_BT");
		SW.Click("ResconMpFlow_UploadFiles_BT");
		SW.SwitchToWindow(2);
		SW.FileUpload("ResconMpFlow_FileContentBrowse_BT", "RoomingListTemplate2.xlsx");	
		SW.EnterValue("ResconMpFlow_FileDescription_EB","File description "+SW.RandomString(8));
		SW.Click("ResconMpFlow_Upload_BT");
		SW.Click("ResconMpFlow_UploadWindowClose_BT");
		SW.SwitchToWindow(1);
		//  Bring back the control to the primary window
		//	int rowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
		//	SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(rowCount));
		SW.Click("ResconMpFlow_LogoutImage_BT");//TODO
		//	SW.HandleAlert(true);//Handle Logout pop
		Utility.CloseBrowser();
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
		SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(FinalrowCount));
		SecondWindowURL  = SW.GetCurrentURL();
		SW.Click("ResconMpFlow_View_BT");
		SW.SwitchToWindow(2);
		//TODO comment
		MainWindowURL  = SW.GetCurrentURL();
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		SW.NavigateTo(MainWindowURL);
		SW.DoubleClick("ResconMpFlow_ExcelAlertHandle_BT");
		if (SW.IsAlertPresent()) {
			SW.HandleAlert(false);
		} 
		SW.Click("ResconMpFlow_ExcelDeptDate_BT");
		SW.DoubleClick("ResconMpFlow_ExcelBtnColumns_BT");
		SW.MoveToObject("ResconMpFlow_ExcelRemoveColumn_BT");
		SW.DoubleClick("ResconMpFlow_ExcelRemoveColumn_BT");

		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
