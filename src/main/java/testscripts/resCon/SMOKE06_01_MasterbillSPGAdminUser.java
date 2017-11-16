package testscripts.resCon;
/* Purpose		: validating master bill flow for SPG_Admin User
 * TestCase Name: TC10_Verify the Master Bill Functionality_Admin User
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date:
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE06_01_MasterbillSPGAdminUser {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
	}

	@Test(priority=1)
	public void CreateMasterbillSPGinviteAdminUser(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHomepage_MBNewinvite_BT");
		SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
		SW.Click("ResconMB_Selectnext_BT");
		SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD",SW.TestData("SGP_PID"));
		SW.EnterValue("ResconMB_SPGNumber_EB", SW.TestData("SPG_ConfirmationNumber"));
		SW.TestData("SPG_ConfirmationNumber");
		SW.Click("ResconMB_SPGSearchbutton_BT");
		SW.EnterValue("ResconMB_SPGEventname_EB",SW.TestData("Rescon_SPG_Eventname"));
		SW.EnterValue("ResconMB_SPGCheckIN_EB","31/Oct/2017");
		SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		SW.Click("ResconMB_SPGFileFinish_BT");

	}@Test(priority=2)
	public void CreateMasterbillSPGinviteModifyAdminUser(){
		SW.DropDown_SelectByValue("ResconMB_SPGUploadlist_DD",SW.TestData("SGP_PID"));
		SW.Click("ResconMB_ViewUploadedFileSPG_BT");
		SW.Click("ResconMB_SPGModify_BT");
		SW.Click("ResconMB_SPGAddNewFile_BT");
		SW.EnterValue("ResconMB_SPGFileDescription1_EB", "Newly added file");
		SW.FileUpload("ResconMB_SPGFileUpload3_BT", "RL.xlsx");
		if (SW.ObjectExists("ResconMB_ErrorAlertWithoutData_ST")){
			Reporter.Write("BasicSmokeTestByAddingNewFileinViewSpgPage", "No Alert message should be displayed", "No Alert message is displayed", "Pass");
		}else{
			Reporter.Write("BasicSmokeTestByAddingNewFileinViewSpgPage", " Alert message should be displayed", " Alert message is displayed", "Fail");
		}
		SW.ClickAndProceed("ResconMB_SPGModifySubmit_BT");
		SW.GetAlertText();
		SW.HandleAlert(true);
	}

	/*@Test(priority=3)
public void CreateMasterbillSPGinvitePropertyUser(){	
	SW.NavigateTo("https://qa3.starwoodmeeting.nssd.star/ResConWeb/");
	SW.MeetingsResConLoginwithPropertyuser(SW.TestData("SGP_UserNamePropertyuser"), SW.TestData("Rescon_PasswordPropertyuser"));
	SW.MoveToObject("ResconHomepage_Masterbill_BT");
	SW.Click("ResconHomepage_MBNewinvite_BT");
	SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
	SW.Click("ResconMB_Selectnext_BT");
	SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
	SW.EnterValue("ResconMB_SPGNumber_EB", SW.TestData("SPG_ConfirmationNumber"));
	SW.TestData("SPG_ConfirmationNumber");
	SW.Click("ResconMB_SPGSearchbutton_BT");
	SW.EnterValue("ResconMB_SPGEventname_EB", "SPGUSERPROPERTYUSERLOGIN");
	SW.EnterValue("ResconMB_SPGCheckIN_EB","31/Oct/2017");
	SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
	SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
	SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGFileFinish_BT");

}

@Test(priority=4)
public void CreateMasterbillSPGinviteModifyPropertyUser(){
SW.DropDown_SelectByValue("ResconMB_SPGUploadlist_DD", "1513");
SW.Click("ResconMB_ViewUploadedFileSPGPropertyUser_BT");
SW.Click("ResconMB_SPGModify_BT");
SW.Click("ResconMB_SPGAddNewFile_BT");
SW.EnterValue("ResconMB_SPGFileDescription_EB", "Newly added file");
SW.FileUpload("ResconMB_SPGFileUpload3_BT", "RL.xlsx");
SW.ClickAndProceed("ResconMB_SPGModifySubmit_BT");
SW.GetAlertText();
SW.HandleAlert(true);
}*/
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}


}
