package testscripts.resCon;

/* Purpose		: verifying required file types in Master bill for New SPG user
 * TestCase Name: TC1_Verify the file types required to create a New Master Bill
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

public class MUSTRUN26_01_MB_RequiredFileTypesForNewSPG {
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
		SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
		SW.Click("ResconMB_Selectnext_BT");
		SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
		SW.EnterValue("ResconMB_SPGNumber_EB", SW.TestData("SPG_ConfirmationNumber"));
		//SW.TestData("SPG_ConfirmationNumber");
		SW.Click("ResconMB_SPGSearchbutton_BT");
		SW.EnterValue("ResconMB_SPGEventname_EB", SW.TestData("Rescon_SPG_Eventname"));
		SW.EnterValue("ResconMB_SPGCheckIN_EB",SW.TestData("SGP_CheckIn"));
		SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
		SW.FileUpload("ResconMB_SPGFileUpload1_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT", "RL.xlsx");
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
		String ErroralertforMisplacefiles = SW.GetText("ResconMB_ErrorAlertforReplacedfile_ST");
		String Expected = "Please complete the following required fields:\nIncorrect file type selected. Please select either .xls or .xlsx file.\nIncorrect file type selected. Please select .pdf file.";
        SW.CompareText("Erroralertforuploadingpdffileplaceintheexcelfilefieldandviseversa", Expected, ErroralertforMisplacefiles);
        SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
		SW.FileUpload("ResconMB_SPGFileUpload2_BT","Protecting Accenture Top 10 Dos and Don’ts.pdf" );
		SW.Click("ResconMB_NonSPGSubmitButton_BT");
	}
	
	@AfterClass
	public void EndTest(){
			
			Reporter.StopTest();		
		}
	
}
