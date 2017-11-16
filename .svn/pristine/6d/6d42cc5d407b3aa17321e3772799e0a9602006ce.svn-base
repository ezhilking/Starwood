package testscripts.ABCD;

import java.util.Calendar;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN18_ChangeLog_DealLogReport {

	SALES SW = new SALES();

	@BeforeClass
	public void startTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void LogReports(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.MoveToObject("ABCDDealLog_Reportstab_LK");
		SW.Click("ABCDDealLog_ReportsSearch_LK");
		SW.Click("ABCD_ChangeLog_LK");
		SW.EnterValue("ABCD_ChangeLogBeginDate_LK",SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -1, Calendar.DATE));
		SW.EnterValue("ABCD_ChangeLogEndDate_LK", SW.GetTimeStamp("dd-MMM-yyyy"));
		SW.Click("ABCDChnageLog_Submit_BT");
		String NothingFoundToDisplay= SW.GetText("ABCDChnageLog_Submit_ST");
		String ExpectedText = "Nothing Found to Display";
		SW.ObjectExists("ABCDChangelog_WithData_WT");
		if(!(SW.CompareText(ExpectedText, NothingFoundToDisplay) || SW.ObjectExists("ABCDChangelog_WithData_WT"))){
			SW.FailCurrentTest("Application not landed in expected page!");
		}
		SW.Click("ABCDDealLogReport_LK");		 
		SW.SwitchToFrame("ABCD_DealLogReportScreen_FR");
		int Division = SW.DropDown_GetSize("ABCDDealLog_DivisonSelect_DD");
		SW.DropDown_SelectByIndex("ABCDDealLog_DivisonSelect_DD", SW.RandomNumber(0, Division-1));

	}
	@AfterClass
 	public void StopTes(){
	SW.Click("ABCD_Logout_LK");
	Reporter.StopTest();
}
}