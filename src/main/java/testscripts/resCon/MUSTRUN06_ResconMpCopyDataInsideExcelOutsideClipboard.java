/* Purpose		: Verifying copying data from inside the excel sheet and paste it anywhere outside the clipboard
 * TestCase Name: TC17_Verifying copying data from inside the excel sheet and paste it anywhere outside the clipboard
 * Created By	: Muneeb
 * Modified By	: MustrunResconMp_CopyDataInsideExcelOutsideClipboard
 * Modified Date: 
 * Reviewed By	:
 * Reviewed Date:
 */

package testscripts.resCon;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
import functions.Utility;

public class MUSTRUN06_ResconMpCopyDataInsideExcelOutsideClipboard {

	CHANNELS SW = new CHANNELS();
	// String EventName = SW.RandomString(6);
	String EventName = "Ujpesr";
	String MailID;
	String Xpath;
	String 	 password;
	String SecondWindowURL,MainWindowURL;
	String crsConf;


	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		MailID = SW.TestData("email");
	//	Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
	}

	//Login as Admin*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_

	@Test(priority=1)
	public void MPF(){
		SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password"));
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


	@Test(priority=2)
	public void Admin()
	{

		SW.NavigateTo(Environment.RESCON+"mp");
		SW.EnterValue("ResconLogin_Username_EB", MailID);//No need to navigateTo RESCON homepage
		SW.EnterValue("ResconLogin_Password_EB",password);
		SW.Click("ResconLogin_Login_BT");

		for (int i = 0; i < 3; i++) {
			SW.Click("ResconMpFlow_UploadFiles_BT");
			SW.SwitchToWindow(2);
			SW.FileUpload("ResconMpFlow_FileContentBrowse_BT", "RoomingListTemplate.xlsx");	
			SW.EnterValue("ResconMpFlow_FileDescription_EB","File description "+SW.RandomString(8));
			SW.Click("ResconMpFlow_Upload_BT");
			SW.Click("ResconMpFlow_UploadWindowClose_BT");
			SW.SwitchToWindow(1);
		}
		//	  Bring back the control to the primary window
		int rowCount  = SW.WebTbl_GetRowCount("ResconMpFlow_UploadTable_WT");
		SW.CompareText("ResconMpFlow_UploadTable_WT", "2",Integer.toString(rowCount));
		SW.ClickByJavascript("ResconMpFlow_LogoutImage_BT");//TODO
		SW.HandleAlert(true);//Handle Logout pop
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
		String Name=SW.GetText("ResconMpFlow_SelectFirstName_ST");
		Reporter.WriteLog(Level.INFO,Name.length() +Name);
		Reporter.Write("Property Content Searched", "Property Content of login","Searched", "pass");


	}

}
