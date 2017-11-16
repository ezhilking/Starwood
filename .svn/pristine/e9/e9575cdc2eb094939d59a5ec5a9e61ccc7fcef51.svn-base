package testscripts.resCon;
/* Purpose		: Uploading 15 files and validating the behaviour of attachement button
 * TestCase Name: TC3_Verify attaching less than 15 files_combination of both Excel and PDF file_create a new master bill_NONSPG
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

public class MUSTRUN32_02_MBUploadMoreThan15FilesNewMBNONSPG {CHANNELS SW = new CHANNELS();
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
public void CreateMasterbillNonSPGinvite(){
	SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
	SW.MoveToObject("ResconHomepage_Masterbill_BT");
	SW.Click("ResconHomepage_MBNewinvite_BT");
	SW.DropDown_SelectByText("ResconMB_Select_DD","Non-SPG/Non-SPP");
	SW.Click("ResconMB_Selectnext_BT");
	SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
	SW.EnterValue("ResconMB_NonSPGEmailid_EB", SW.TestData("NonSPGViewOnline_UserName"));
	SW.Click("ResconMB_NonSPGSearchButton_EB");
	SW.EnterValue("ResconMB_SPGEventname_EB", "NonSPG smoke test");
	SW.EnterValue("ResconMB_NonSPGCheckout_EB",SW.TestData ("SGP_CheckOut"));
	SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
	SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
	SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_ADDAttachmentNewPage_BT");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription1_EB", "1");
	SW.FileUpload("ResconMB_SPGFileUpload3_BT", "RL.xlsx");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription2_EB", "2");
	SW.FileUpload("ResconMB_SPGFileUpload4_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription3_EB", "3");
	SW.FileUpload("ResconMB_SPGFileUpload5_BT", "RL.xlsx");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription4_EB", "4");
	SW.FileUpload("ResconMB_SPGFileUpload6_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription5_EB", "5");
	SW.FileUpload("ResconMB_SPGFileUpload7_BT", "RL.xlsx");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription6_EB", "6");
	SW.FileUpload("ResconMB_SPGFileUpload8_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription7_EB", "7");
	SW.FileUpload("ResconMB_SPGFileUpload9_BT", "RL.xlsx");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription8_EB", "8");
	SW.FileUpload("ResconMB_SPGFileUpload10_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription9_EB", "9");
	SW.FileUpload("ResconMB_SPGFileUpload11_BT", "RL.xlsx");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription10_EB", "10");
	SW.FileUpload("ResconMB_SPGFileUpload12_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription11_EB", "11");
	SW.FileUpload("ResconMB_SPGFileUpload13_BT", "RL.xlsx");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription12_EB", "12");
	SW.FileUpload("ResconMB_SPGFileUpload14_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_SPGAddNewFile_BT");
	SW.EnterValue("ResconMB_SPGFileDescription13_EB", "13");
	SW.FileUpload("ResconMB_SPGFileUpload15_BT", "RL.xlsx");
	if(SW.IsEnabled("ResconMB_SPGAddNewFile_BT", "Disabled")){
		Reporter.Write("ResconMB_SPGAddNewFile_BT", "Attachment button should be disabled", "Attachment button is disabled", "PASS");
	}else{
		Reporter.Write("ResconMB_SPGAddNewFile_BT", "Attachment button should be disabled", "Attachment button is disabled", "FAIL");
	}
	SW.Click("ResconMB_NonSPGSubmitButton_BT");
}
@AfterClass
public void EndTest(){

	Reporter.StopTest();		
}



}
