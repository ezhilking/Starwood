package testscripts.resCon;
/* Purpose		: validating error alert message when other than excel or PDF is uploaded in NONSPG user in view MB page
 * TestCase Name: TC7_Verify  uploading other than Excel and PDF files_NEW MASTER BILL page
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date:
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.testng.annotations.AfterClass;
/* Purpose		: validating error alert message when other than excel or PDF is uploaded in NewNONSPG user in view MB page
 * TestCase Name: TC7_Verify  uploading other than Excel and PDF files_NEW MASTER BILL page
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date:
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN20_02_MBOtherThanExcelOrPDFViewMBNONSPG {
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
		public void CreateMasterbillSPGinviteModify(){
			SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
			SW.MoveToObject("ResconHomepage_Masterbill_BT");
			SW.Click("ResconMB_UploadListpage_LK");
			SW.DropDown_SelectByValue("ResconMB_SPGUploadlist_DD", "1513");
			SW.Click("ResconFileDescription_NONSPGuploadPage_BT");
			SW.Click("ResconMB_SPGModify_BT");
			SW.Click("ResconMB_SPGAddNewFile_BT");
			SW.EnterValue("ResconMB_SPGFileDescription1_EB", "File other than PDF or Excel");
			//SW.FileUpload("ResconMB_SPGFileUpload3_BT", "RL.xlsx");
			SW.FileUpload("ResconMB_UploadNewFile_BT", "ethics and compliance traning.docx");
			SW.Click("ResconMB_NonSPGSubmitButton_BT");
			String Erroralertforfileotherthanexcelorpdf = SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
			String Expected1 = "Please complete the following required fields:";
			String Expected2 = "Only files with extensions XLS PDF XLSX are allowed";
			SW.CompareTextContained("Validation", Expected1+"\n\n"+Expected2,Erroralertforfileotherthanexcelorpdf);
			}@AfterClass
		public void EndTest(){
				
				Reporter.StopTest();		
			}
		
			
	
	}


