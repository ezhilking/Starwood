package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		: 
 * TestCase Name: UploadDocument_iframe 
 * Created By	: Kumari Nitu 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 	
 * Reviewed Date: 
 */

public class MUSTRUN19_UploadDocument_iframe {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
}
	@Test
	public void AddActivity(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		if(SW.IsAlertPresent()){//TODO 
			SW.HandleAlert(true);
		} 
				SW.MoveToObject("ABCD_Deal_LK");//Moving to deals
				SW.WaitTillElementToBeClickable("ABCDdeals_Search_Lk");
				SW.Click("ABCDdeals_Search_Lk");
				SW.Click("ABCDdeal_Division_LK");//Give Division value
				SW.Click("ABCD_DealDivision_LK");
				SW.Click("ABCDdeal_Search_LK");//Click on search
				SW.WebTbl_Click("ABCDdeal_SearchResult_LK",3,3);//Select value from record
				SW.WaitForPageload();
				SW.Click("ABCDDocUpload_OtherTab_LK");//Click on the Other tab 
				SW.Click("ABCDDocUpload_Documents_LK");//Click on the Document
				SW.Click("ABCD_DealAdd_BT");//Click on Add button
				SW.WaitForWindowCount(2);
				SW.SwitchToWindow(2);
				SW.SwitchToFrame("ABCDDeal_AddFrame_LK");
				String FileName = "ABCD_TestReport.pdf";//upload document in primary file 
				SW.FileUpload("ABCDDealAdd_PrimaryField_LK", FileName); 
				SW.EnterValue("ABCDDealAdd_Title_LK","Tes_"+SW.RandomString(4));//Enter Title
				SW.RunJavaScript("document.getElementById('xEffectiveDate-2853_img').value='"+SW.GetTimeStamp("d-MMM-yyyy")+"';");
				//Enter value in Category
				SW.DropDown_SelectByIndex("ABCDDealAdd_Category1_LK", 3);
				SW.DropDown_SelectByIndex("ABCDDealAdd_Category2_LK", 3);
				SW.DropDown_SelectByIndex("ABCDDealAdd_Category3_LK", 3);
				//Enter Comments 
				SW.EnterValue("ABCDDealAdd_Comments_LK", "Testing" );
				//Click CheckIn
				SW.Click("ABCDDealAdd_CheckIn_LK");
				SW.GetScreenshot("Confirmation msg");
				//String st=SW.GetText("")
				//SW.CompareText("Check-In Confirmation for", )				
	}
@AfterClass
public void StopTes(){
 SW.Click("ABCD_Logout_LK");
	Reporter.StopTest();
}
}
