package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN16_AddNotes_DealDetails {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);


	}
	@Test
	public void AddNotes(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.MoveToObject("ABCD_Deal_LK");//Moving to deals
		SW.WaitTillElementToBeClickable("ABCDdeals_Search_Lk");
		SW.Click("ABCDdeals_Search_Lk");//Click on Search	
		SW.DoubleClick("ABCDdeal_Division_LK");//Give Division value
		SW.Click("ABCD_DealDivision_LK");
		SW.Click("ABCDdeal_Search_LK");//Click on search
		SW.WebTbl_Click("ABCDdeal_SearchResult_LK",3,3);//Select value from record
		SW.WaitForPageload();
		SW.Click("ABCDdeal_Details_LK");//wait Deal detail page to open
		SW.DropDown_SelectByIndex("ABCD_AddNotes_DD",3);//Select value from DropDown
		SW.EnterValue("ABCD_AddNotesTextArea_EB", "ABCD_"+SW.RandomString(5));//Write in text area
		SW.Click("ABCD_AddNotesSave_BT");	//Click save
		SW.WaitTillPresenceOfElementLocated("ABCD_AddNotesTextArea_EB");
		SW.GetScreenshot("Addnotes");
	}
	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	
}
}