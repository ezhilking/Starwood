package testscripts.resCon;
/* Purpose		: validating error alert message when Excel file is uploaded without file description SPG user
 * TestCase Name: TC9_Verify  creating a New Master Bill by uploading an Excel file_without File Description_NEW MASTER BILL page
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

public class MUSTRUN17_01_MBExcelFileWithoutDescriptionNewMBSPG {
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
	    SW.Click("ResconMB_SPGFileFinish_BT");
	    String FileWithoutDescriptionOnMBPage =   SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
	 String [] Expected =   FileWithoutDescriptionOnMBPage.split(":");
//	 System.out.println("first line = " +str1[0].trim());
//	 System.out.println("second line = " +str1[1].trim());
	 
	 SW.CompareText("ResconMBNewSPGUseruploadExcelfilewithoutFileDescription", Expected[0].trim()+":"+"\n"+Expected[1].trim(), FileWithoutDescriptionOnMBPage);
}
	@AfterClass
	public void EndTest(){
			Reporter.StopTest();		
	}
}
