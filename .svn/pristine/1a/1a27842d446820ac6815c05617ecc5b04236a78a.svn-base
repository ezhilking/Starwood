package testscripts.resCon;
/* Purpose		: Verify uploading PDF file which is less than 10 MB for master bill new NONSPG user page
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

public class MUSTRUN29_02_MB_UploadLessThan10MB_PDFFile_NONSPG {
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
		SW.EnterValue("ResconMB_NonSPGCheckout_EB","31/Oct/2017");
		SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		if (SW.ObjectExists("ResconMB_ErrorAlertWithoutData_ST"))
		{
			Reporter.Write("uploadlessthan10mbExcelfile", " Alert message should be displayed", " Alert message is displayed", "Fail");
			
		}
		else
		{
			Reporter.Write("uploadlessthan10mbExcelfile", "No Alert message should be displayed", "No Alert message is displayed", "Pass");
		}
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
		}
	@AfterClass
	public void EndTest(){
		
		Reporter.StopTest();		
	}
}
