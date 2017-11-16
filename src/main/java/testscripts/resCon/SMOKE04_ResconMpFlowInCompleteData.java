/* Purpose		: Upload Excel spreadsheet for group with incomplete data _ rejected
 * TestCase Name: Upload Excel spreadsheet for group with incomplete data _ rejected
 * Created By	: Muneeb
 * Modified By	: 
 * Modified Date: 2.22.2017
 * Reviewed By	:
 * Reviewed Date:
 */package testscripts.resCon;

 import java.util.Calendar;

 import org.testng.annotations.AfterClass;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.Test;

 import functions.CHANNELS;
 import functions.Environment;
 import functions.Reporter;
 import functions.Utility;

 public class SMOKE04_ResconMpFlowInCompleteData {
	 CHANNELS SW = new CHANNELS();
	 String EventName = SW.RandomString(6);
	 String MailID;
	 String Xpath;
	 String password;
	 String url1,url2;
	 int rowCount;

	 @BeforeClass
	 public void StartTest(){
		 Environment.Tower = "CHANNELS";
		 Reporter.StartTest();
		 MailID = SW.TestData("email");
	//	 Environment.SetBrowserToUse("FF");
		 //String EventName = "AKZC9U42";
		 SW.LaunchBrowser(Environment.RESCON);
	 }

	 //Login as Admin
	 @Test(priority=1)
	 public void MPF(){
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
		 SW.Click("ResconLogin_Login_BT");
		 SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
		 SW.Click("ResconMpFlow_SelectMpFlowInvite_BT");
		 SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD",SW.TestData("PID"));
		 SW.EnterValue("ResconMpFlow_Event_EB", EventName);

		 SW.EnterValue("ResconMpFlow_Email_EB", MailID);
		 SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 1, Calendar.DATE));
		 SW.Click("ResconMpFlow_Finish_BT");
		 Xpath= "//*[text()[contains(.,'"+EventName+"')]]";
		 SW.Click("ResconMpFlow_Logout_BT");
		 password = SW.GetPassword(EventName);
	 }

	 //	Get the password
	 @Test(priority=2)
	 public void Admin(){
		 SW.NavigateTo(Environment.RESCON+"mp");

		 //	String password="CLI2D3DB";
		 SW.EnterValue("ResconLogin_Username_EB", MailID);//No need to navigateTo RESCON homepage
		 SW.EnterValue("ResconLogin_Password_EB",password);
		 SW.Click("ResconLogin_Login_BT");
		 SW.Click("ResconMpFlow_UploadFiles_BT");
		 SW.SwitchToWindow(2);
		 SW.FileUpload("ResconMpFlow_FileContentBrowse_BT", "Incomplete.xlsx");
		 SW.EnterValue("ResconMpFlow_FileDescription_EB","File description "+SW.RandomString(8));
		 SW.Click("ResconMpFlow_Upload_BT");
		 SW.Click("ResconMpFlow_UploadWindowClose_BT");
		 SW.SwitchToWindow(1);//Bring back the control to the primary window
		 rowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
		 SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(rowCount));
		 SW.ClickByJavascript("ResconMpFlow_LogoutImage_BT");//TODO
		 SW.HandleAlert(true);//Handle Logout pop
		 //		String str= SW.GetText("");
		 //		System.out.print(str);
		 //		String STR = SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
		 //		String STR1 = "Please complete the following required fields:";
		 //		String STR2 = "Only files with extensions XLS PDF XLSX are allowed";
		 //		SW.CompareTextContained("Validation", STR1+"\n\n"+STR2, STR); 

		 Utility.CloseBrowser();
	 }

	 //Login as Admin
	 @Test(priority=3)
	 public void MPFIdentity(){
		 Environment.SetBrowserToUse("IE");
		 SW.LaunchBrowser(Environment.RESCON);
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
		 SW.Click("ResconLogin_Login_BT");
		 SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
		 SW.Click("ResconMpFlow_MPUploadList_LK");
		 SW.Click(Xpath);
		 int FinalrowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
		 SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(FinalrowCount));
		 url2  = SW.GetCurrentURL();
		 SW.Click("ResconMpFlow_View_BT");
		 SW.SwitchToWindow(2);
		 //TODO comment
		 url1  = SW.GetCurrentURL();
		 SW.CloseOnlyThisBrowser();
		 SW.SwitchToWindow(1);
		 SW.NavigateTo(url1);
		 SW.Click("ResconMpFlow_SaveChanges_BT");
		 //	SW.ClickAndProceed("ResconMpFlow_Close_BT");
		 //	SW.HandleAlert(true);
		 SW.Wait(5);
		 SW.NavigateTo(url2);

		 //		SW.SwitchToWindow(1);
		 if(SW.IsEnabled("ResconMpFlow_Transfer_BT", "Enabled")){
			 Reporter.Write("ResconMpFlow_Transfer_BT", "Transfer button should be enabled", "Transfer button is enabled", "PASS");
		 }else{
			 Reporter.Write("ResconMpFlow_Transfer_BT", "Transfer button should be enabled", "Transfer button is enabled", "FAIL");
			 
		 }
	 }
	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }
 }



