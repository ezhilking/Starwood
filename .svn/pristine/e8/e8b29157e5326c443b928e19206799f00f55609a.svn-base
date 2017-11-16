package testscripts.resCon;
/* Purpose		: Validating error alert message when more than 10 mb PDF file is uploaded in MB new NONSPG user page
 * TestCase Name: TC15_Verify  uploading a PDF file_Size greater  than 10MB_New Master Bill page
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

public class MUSTRUN31_02_MBUploadMoreThan10MBPDFFileNewNONSPG {
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
		SW.EnterValue("ResconMB_SPGEventname_EB", "NONSPGUSER8");
		SW.EnterValue("ResconMB_NonSPGCheckout_EB",SW.TestData ("SGP_CheckOut"));
		SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "annual_report_2009.pdf");
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
		String uploadmorethan10mbexcelfile = SW.GetText("ResconMB_ErrorAlertFor10MBPdfFile_ST");
		String Expected ="Error: The file size of annual_report_2009.pdf exceeds the maximum allowed size(10 MB) for any upload . ";
		SW.CompareText("Erroralertwhenmorethan10mbPdffileuploadedforNewMB", Expected+"\n",uploadmorethan10mbexcelfile);
		}@AfterClass
	public void EndTest(){
			
			Reporter.StopTest();		
		}


}
