package testscripts.resCon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN26_MBRequiredFileTypesForNewNONSPG {
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
		SW.EnterValue("ResconMB_NonSPGCheckout_EB",SW.TestData("SGP_CheckOut"));
		SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
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
