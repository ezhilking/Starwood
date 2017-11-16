package testscripts.SimplifiGRC;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		:
 * TestCase Name: elockerContent_SingleCheckIN
 * Created By	: Kumari Nitu 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */
public class MUSTRUN08_eLockerContent_MultipleCheckinZip {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SIMPLIFIGRCURL); 
	}

	@Test
	public void CorporateContractCheckIn(){
		SW.EnterValue("SimplifiGRCLogin_Username_LK","weblogic");
		SW.EnterValue("SimplifiGRCLogin_Password_LK","staRW00d11G");
		SW.Click("SimplifiGRCLogin_SignIn_BT");
		SW.Click("SimplifiGRCHome_AddDocument_LK");
		SW.Click("SimplifiGRCelocker_Content_LK");
		String title=SW.RandomString(5);
		SW.EnterValue("SimplifiGRCelocker_Content_LK", title);
		String FileName = "ABCD_TestReport.pdf";//upload document in primary file 
		SW.FileUpload("SimplifiGRCelocker_PrimaryFile_LK", FileName);
		SW.Wait(7);
		SW.Click("SimplifiGRCContract_Check In_BT");
		SW.CompareText("Check-In Confirmation for"+"'title'", "SimplifiGRCCorpSingleCheckIN_Msg_LK");
	
	
	}
	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	}
}

