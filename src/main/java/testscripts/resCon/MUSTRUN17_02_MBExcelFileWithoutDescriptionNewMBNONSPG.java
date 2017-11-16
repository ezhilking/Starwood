package testscripts.resCon;
/* Purpose		: validating error alert message when Excel file is uploaded without file description NONSPG user
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

public class MUSTRUN17_02_MBExcelFileWithoutDescriptionNewMBNONSPG {
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
	public void CreateMasterbillNonSPGinvite(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHomepage_MBNewinvite_BT");
		SW.DropDown_SelectByText("ResconMB_Select_DD","Non-SPG/Non-SPP");
		SW.Click("ResconMB_Selectnext_BT");
		SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
		SW.EnterValue("ResconMB_NonSPGEmailid_EB", SW.TestData("NonSPGViewOnline_UserName"));
		SW.Click("ResconMB_NonSPGSearchButton_EB");
		SW.EnterValue("ResconMB_SPGEventname_EB", "nonspg");
		SW.EnterValue("ResconMB_NonSPGCheckout_EB",SW.TestData("SGP_CheckOut"));
		SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		SW.Click("ResconMB_ADDAttachmentNewPage_BT");
		SW.FileUpload("ResconMB_UploadNewFile_BT", "RL.xlsx");
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
		String FileWithoutDescriptionOnMBPage = SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
		String [] Expected =   FileWithoutDescriptionOnMBPage.split(":");
		SW.CompareText("ResconMBerroralertwithoutfiledescription",Expected[0].trim()+":"+"\n"+Expected[1].trim(), FileWithoutDescriptionOnMBPage);
		}
	@AfterClass
	public void EndTest(){
			Reporter.StopTest();		
	}
}