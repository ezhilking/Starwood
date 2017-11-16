package testscripts.resCon;
/* Purpose		: validating error alert message when Excel file is uploaded without file description NONSPG user in view master bill page
 * TestCase Name: TC11_Verify  attaching an Excel file_without File Description_VIEW MASTER BILL page
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date:
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
/* Purpose		: validating error alert message when Excel file is uploaded without file description NONSPG user in view master bill flow
 * TestCase Name: TC11_Verify  attaching an Excel file_without File Description_VIEW MASTER BILL page
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

public class MUSTRUN18_02_MBExcelFileWithoutDescriptionViewMBNONSPG {
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
			SW.FileUpload("ResconMB_SPGFileUpload3_BT", "RL.xlsx");
			SW.ClickAndProceed("ResconMB_SPGModifySubmit_BT");
			//SW.GetAlertText();
			//SW.HandleAlert(true);
			String FileWithoutDescriptionOnMBPage = SW.GetText("ResconMB_ErrorAlertWithoutData_ST");
			String [] Expected =   FileWithoutDescriptionOnMBPage.split(":");
			SW.CompareText("ResconMBNewSPGUseruploadExcelfilewithoutFileDescriptioninviewMBpage", Expected[0].trim()+":"+"\n"+Expected[1].trim(), FileWithoutDescriptionOnMBPage);
			
	}@AfterClass
	public void EndTest(){
		
		Reporter.StopTest();		
	}


}
