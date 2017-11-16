package testscripts.SimplifiGRC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		:
 * TestCase Name: ABCD Document Upload 
 * Created By	: Kumari Nitu 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */
public class MUSTRUN03_ABCD_DocumentUpload {
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
		SW.Click("SimplifiGRCHome_ABCDDocument_LK");
		String FileName = "ABCD_TestReport.pdf";//upload document in primary file 
		SW.FileUpload("SimplifiGRCContract_PrimaryFile_LK", FileName); 
		String title = SW.RandomString(5);
		SW.EnterValue("SimplifiGRCContract_Title_ST", title);
		SW.RunJavaScript("document.getElementById('xEffectiveDate-710_img_link').value='"+SW.GetTimeStamp("d-MMM-yyyy")+"';");
		SW.DropDown_SelectByIndex("SimplifiGRCContract_DocumentCat1_DD", SW.RandomNumber(1, SW.DropDown_GetSize("SimplifiGRCContract_DocumentCat1_DD")-1));
		SW.DropDown_SelectByIndex("SimplifiGRCContract_DocumentCat2_DD", SW.RandomNumber(1, SW.DropDown_GetSize("SimplifiGRCContract_DocumentCat2_DD")-1));
		if(SW.DropDown_GetSize("SimplifiGRCContract_DocumentCat3_DD")>1)
		SW.DropDown_SelectByIndex("SimplifiGRCContract_DocumentCat3_DD", SW.RandomNumber(1, SW.DropDown_GetSize("SimplifiGRCContract_DocumentCat3_DD")-1));
		SW.Click("SimplifiGRCContract_Check In_BT");
		String msg= SW.GetText("SimplifiGRCContract_Check InMsg_BT");
		SW.CompareText("Check-In Confirmation for"+" 'title'", msg);    
}
	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	}
}

