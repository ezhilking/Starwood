package testscripts.resCon;
/* Purpose		: Verify uploading PDF file which is less than 10 MB for master bill new SPG user page
 * TestCase Name: Verify  uploading an Excel file_Size less than 10MB_New Master Bill page
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

public class MUSTRUN29_01_MBUploadLessThan10MBPDFFileSPG {
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
	public void CreateMasterbillSPGinviteAdminUser(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHomepage_MBNewinvite_BT");
		SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
		SW.Click("ResconMB_Selectnext_BT");
		SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
		SW.EnterValue("ResconMB_SPGNumber_EB", SW.TestData("SPG_ConfirmationNumber"));
		SW.TestData("SPG_ConfirmationNumber");
		SW.Click("ResconMB_SPGSearchbutton_BT");
		SW.EnterValue("ResconMB_SPGEventname_EB","SPGUSER1");
		SW.EnterValue("ResconMB_SPGCheckIN_EB","31/Oct/2017");
		SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		if (SW.ObjectExists("ResconMB_ErrorAlertWithoutData_ST"))
		{
			
			Reporter.Write("uploadlessthan10mbExcelfile", "No Alert message should be displayed", "No Alert message is displayed", "Pass");
		}
		else
		{
			Reporter.Write("uploadlessthan10mbExcelfile", " Alert message should be displayed", " Alert message is displayed", "Fail");
		}
		SW.Click("ResconMB_SPGFileFinish_BT");
		
}@AfterClass
	public void EndTest(){
	
	Reporter.StopTest();		
}

}
