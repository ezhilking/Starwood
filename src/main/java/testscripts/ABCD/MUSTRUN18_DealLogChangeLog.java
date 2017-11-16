package testscripts.ABCD;

import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN18_DealLogChangeLog {
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
		//SW.ABCDLogin(SW.TestData("ABCD_Username"),SW.TestData("ABCD_Password"));
		
		SW.EnterValue("//input[@name='userName']", "poorman");
		SW.EnterValue("//input[@name='password']", "password");
		SW.Click("//input[@name='Submit']");	
		
		/*SW.MoveToObject("ABCDDealLog_Reportstab_LK");
		SW.Click("ABCDDealLog_ReportsSearch_LK"); // Clicks on Report dashboard
		
		SW.Click("ABCDDealLogReport_LK"); // Clicks on deal log report
		
		SW.SwitchToFrame("ABCD_DealLogReportScreen_FR");//switching to iframe
		
				
		int div = SW.DropDown_GetSize("ABCD_DivisionDropDown_DD");
		SW.DropDown_SelectByIndex("ABCD_DivisionDropDown_DD", SW.RandomNumber(0, div-1)); // selecting from division dropdown
		SW.Click("ABCD_DivisionClick_LK");
		
		int dealsts = SW.DropDown_GetSize("ABCD_DealStatusDropdown_DD");
		SW.DropDown_SelectByIndex("ABCD_DealStatusDropdown_DD", SW.RandomNumber(0, dealsts-1)); // selecting from Deal status dropdown
		SW.Click("ABCD_DealStatusDropdown_LK");
		
		int dealtype = SW.DropDown_GetSize("ABCD_DealTypeDropdown_DD");
		SW.DropDown_SelectByIndex("ABCD_DealTypeDropdown_DD", SW.RandomNumber(0, dealtype-1)); // selecting from Deal type  dropdown
		SW.Click("ABCD_DealTypeDropdown_LK");
		
		
		SW.Click("ABCD_DealLogReportSubmit_LK");// clicking on submit 
		SW.SwitchToFrame("");							
		SW.SwitchToFrame("ABCD_DealLogReportScreen_FR");
		
		String Actual = SW.GetText("ABCD_DealLogReport_ST");
		SW.CompareText("DealReportSuccess", "DEAL LOG REPORT", Actual); // comparing results
		SW.GetScreenshot("deallogreport");//take screenshots
		
		*/
		
		SW.SwitchToFrame("");
		//SW.SwitchToFrame("ABCD_ChangeLogReportPageHover_FR");
		SW.MoveToObject("ABCDDealLog_Reportstab_LK");//hover on Reports
		SW.Click("ABCDDealLog_ReportsSearch_LK"); // Clicks on Report dashboard
		
		SW.Click("ABCD_ChangeLogReportPage_LK"); //clicks on change report log
		
		//SW.Click("ABCD_ChangeLogReportBeginDate_EB");
		//SW.SwitchToFrame("ABCD_ChangeLogBeginDateSel_LK");
		//SW.Click("");
		SW.Click("ABCDChangeLogBeginDate_LK");
		SW.EnterValue("ABCDChangeLogBeginDate_LK", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -5, Calendar.DATE) ); // begin date
		
		SW.Click("ABCDChangeLogEndDate_LK");
		SW.EnterValue("ABCDChangeLogEndDate_LK", SW.GetTimeStamp("dd-MMM-yyyy")); // end date
		
		
		SW.Click("ABCDChangeLogSubmit_LK");
		
		String Actual1 = SW.GetText("ABCDChangeLogSubmitVerify_ST");
		SW.CompareText("ChangeLogReportSuccess", "CHANGE LOG ID", Actual1);
		
		}
}
