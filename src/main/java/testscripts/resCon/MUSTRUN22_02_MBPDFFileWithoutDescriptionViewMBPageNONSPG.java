package testscripts.resCon;
/* Purpose		: validating error alert message when Excel file is uploaded without file description NONSPG user in view masterbill page
 * TestCase Name: TC12_Verify  attaching a PDF file_without File Description_VIEW MASTER BILL page
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

public class MUSTRUN22_02_MBPDFFileWithoutDescriptionViewMBPageNONSPG {
	
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
		//SW.EnterValue("ResconMB_SPGFileDescription_EB", "Newly added file");
		SW.FileUpload("ResconMB_SPGFileUpload3_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		SW.ClickAndProceed("ResconMB_SPGModifySubmit_BT");
		//SW.GetAlertText();
		//SW.HandleAlert(true);
		String PDFfilewithoutfiledescription = SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
		String [] Expected =   PDFfilewithoutfiledescription.split(":");
		SW.CompareText("filedescriptionerroralertinviewMBpage", Expected[0].trim()+":"+"\n"+Expected[1].trim(), PDFfilewithoutfiledescription);
		
}@AfterClass
	public void EndTest(){
	
	Reporter.StopTest();		
}
}