package testscripts.resCon;
/* Purpose		: Validating error alert message when more than 10 mb PDF file is uploaded in MB new SPG user page
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

public class MUSTRUN31_01_MBUploadMoreThan10MBPDFFileNewSPG {
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
		SW.Click("ResconMB_SPGSearchbutton_BT");
		SW.EnterValue("ResconMB_SPGEventname_EB", SW.TestData("Rescon_SPG_Eventname"));
		SW.EnterValue("ResconMB_SPGCheckIN_EB",SW.TestData("SGP_CheckIn"));
		SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "annual_report_2009.pdf");
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
		String ResconMBErroralertformorethan10MBPdf = SW.GetText("ResconMB_ErrorAlertFor10MBPdfFile_ST");
		String Expected ="Error: The file size of annual_report_2009.pdf exceeds the maximum allowed size(10 MB) for any upload . ";
		SW.CompareText("Erroralertwhenmorethan10mbPdffileuploadedforNewMB", Expected+"\n",ResconMBErroralertformorethan10MBPdf);
		}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}



}