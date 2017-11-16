package testscripts.resCon;
/* Purpose		: Verifying the deletion of files in the masterbill flow in new spg user page
 * TestCase Name:TC10_Verify  Master Bill_Deletion of files_Admin User
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


public class MUSTRUN16_MBDeletionOfFileNewSPG {
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

	}@Test(priority=1)
	public void CreateMasterbillSPGinvite(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHomepage_MBNewinvite_BT");
		SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
		SW.Click("ResconMB_Selectnext_BT");
		SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
		SW.EnterValue("ResconMB_SPGNumber_EB", SW.TestData("SPG_ConfirmationNumber"));
		//SW.TestData("SPG_ConfirmationNumber");
		SW.Click("ResconMB_SPGSearchbutton_BT");
		SW.EnterValue("ResconMB_SPGEventname_EB", SW.TestData("Rescon_SPG_Eventname"));
		SW.EnterValue("ResconMB_SPGCheckIN_EB",SW.TestData("SGP_CheckIn"));
		SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		SW.Click("ResconMB_ADDAttachmentNewPage_BT");
		SW.FileUpload("ResconMB_UploadNewFile_BT", "RL.xlsx");
		SW.EnterValue("ResconMB_SPGFileDescription1_EB", "Deletion of file in the SPGnewuser page");
		if(SW.ObjectExists("ResconMB_DeletionOfFileNewSpguser_BT")){
			Reporter.Write("ResconMB_BeforeDeleting_DT", "Deletion button should be displayed for newly uploaded  files", "Deletion button is displayed for newly uploaded files", "PASS");
			SW.NormalClick("ResconMB_DeletionOfFileNewSpguser_BT");
			Reporter.Write("ResconMB_AfterDeletion_DT", "Deletion button should be displayed for newly uploaded  files", "Deletion button is displayed for newly uploaded files", "PASS");
		}else{
			Reporter.Write("ResconMB_DeletionOfFile_NewSpguser_BT", "Deletion button should be Enabled for added files", "Deletion button is Enabled for added files", "FAIL");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
