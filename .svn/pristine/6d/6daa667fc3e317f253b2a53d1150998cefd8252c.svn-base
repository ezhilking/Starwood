package testscripts.ABCD;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN14_ODIntelReport {
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
		SW.HandleAlert(true);
		//Moving to Accounts 
		SW.MoveToObject("ABCD_Account_LK");
		SW.WaitTillElementToBeClickable("ABCDAccount_Search_LK");
		SW.Click("ABCDAccount_Search_LK");
		if(SW.ObjectExists("ABCDAccount_Name_LK"))
			Environment.loger.log(Level.INFO, "Successful");
		else
			SW.NavigateTo("http://phxabcqas61.nssd.star:9080/accounts/AdvanceSearchAction.do");

		//Give account name 
		SW.EnterValue("ABCDAccount_Name_LK", "sam");
		//Click Submit
		SW.Click("ABCDAccount_Search_BT");
		//String Expect=SW.GetText("ABCDAccount_OneAccount_LK");
		//Click one record
		int rowcount=SW.WebTbl_GetRowCount("ABCD_AccountSearch_LK");
		SW.WebTbl_Click("ABCD_AccountSearch_LK", SW.RandomNumber(2, rowcount), 2);
		//SW.Click("ABCDAccount_OneAccount_LK");


		//Click O/D Intel report
		SW.SwitchToFrame("ABCDAccount_Frame_FR");
		SW.Click("ABCDAccount_ODIntel_LK");
		SW.Wait(5);
		SW.SwitchToFrame("");
		SW.SwitchToFrame("ABCDAccount_Reportframe_ST");
		if(SW.ObjectExists("(//div[@class='textLayer'])[1]//*[text()='Portfolio Overview']")){
			SW.GetScreenshot("ODIreport");
			Environment.loger.log(Level.INFO, "Successful");
		}else{
			Environment.loger.log(Level.ERROR, "");
			SW.FailCurrentTest("Failed due to ");
		}
		String Actual = SW.GetText("ABCDAccount_Actual_ST");
		SW.CompareText("AccountOverview_ST", "Account Overview", Actual);
		SW.SwitchToFrame("");
	}
	@AfterClass
	public void StopTest(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}
}