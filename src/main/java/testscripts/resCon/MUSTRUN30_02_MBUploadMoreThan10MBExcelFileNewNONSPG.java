package testscripts.resCon;
/* Purpose		: Validating error alert message when more than 10 mb Excel file is uploaded in MB new NONSPG user page
 * TestCase Name: TC18_Verify  uploading an Excel file_Size greater  than 10MB_New Master Bill page
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

public class MUSTRUN30_02_MBUploadMoreThan10MBExcelFileNewNONSPG {
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
	SW.FileUpload("ResconMB_SPGFileUpload1_BT", "mb10.xlsx");
	SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
	SW.Click("ResconMB_NonSPGSubmitButton_BT");
	String erroralertforuploadingmorethan10MBExcelfile  = SW.GetText("ResconMB_ErrorAlertFor10MBExcelFile_ST");
	String Expected ="Error: The file size of mb10.xlsx exceeds the maximum allowed size(10 MB) for any upload . ";
	SW.CompareText("ResconMBerroralertforuploadingmorethan10MBExcelfile", Expected+"\n",erroralertforuploadingmorethan10MBExcelfile);
	}
@AfterClass
public void EndTest(){
	
	Reporter.StopTest();		
}



}
