package testscripts.resCon;
/* Purpose		: validating error alert message when other than excel or PDF is uploaded in NewNONSPG user
 * TestCase Name: TC7_Verify  uploading other than Excel and PDF files_NEW MASTER BILL page
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

public class MUSTRUN19_02_MBOtherThanExcelOrPDFNewMBNONSPG {
	
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
		SW.EnterValue("ResconMB_SPGEventname_EB", "NonSPG smoke test");
		SW.EnterValue("ResconMB_NonSPGCheckout_EB",SW.TestData("SGP_CheckOut"));
		SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		SW.Click("ResconMB_ADDAttachmentNewPage_BT");
		SW.EnterValue("ResconMB_SPGFileDescription1_EB", "File other than PDF or Excel");
		SW.FileUpload("ResconMB_UploadNewFile_BT", "ethics and compliance traning.docx");
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
		String Erroralertforotherthanexcelorpdfuploaded = SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
			
		String Expectedline1 = "Please complete the following required fields:";
		String Expectedline2 = "Only files with extensions XLS PDF XLSX are allowed";
		
		SW.CompareText("Validation", Expectedline1+"\n\n"+Expectedline2,Erroralertforotherthanexcelorpdfuploaded);
		
		}@AfterClass
	public void EndTest(){
			
			Reporter.StopTest();		
		}
	

}
