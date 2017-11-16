package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN17_GoalSettingAdd {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}
	@Test
	public void GoalSettingAdd(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.HandleAlert(true);
		//Moving to Development Goal

		SW.MoveToObject("ABCD_DevelopmentGoal_LK");
		SW.WaitTillElementToBeClickable("ABCDDevelopmentGoal_GoalSetting_LK");
		//Click Goal Setting 
		SW.Click("ABCDDevelopmentGoal_GoalSetting_LK");
		/*if(SW.ObjectExists(ObjectName))
			Environment.loger.log(Level.INFO, "Successful");
			else
			SW.NavigateTo(String URL);
		 */
		//int Countrow = SW.WebTbl_GetRowCount("ABCDDevelopmentGoalSetting_TrackerTable_LK")-1;//TODO
		SW.Click("ABCDDevelopmentGoal_AddRowClick_BT");
		//String xpath = "//*[@id='_js_goalRow["+Countrow+"]_devTeamId_value']";

		//Select value from Division DD
		SW.DropDown_SelectByIndex("ABCDDevelopmentGoal_LastRowDivision_DD", SW.RandomNumber(1,SW.DropDown_GetSize("ABCDDevelopmentGoal_LastRowDivision_DD")-1));
		//Select value from DevTeam
		SW.DropDown_SelectByIndex("ABCDDevelopmentGoal_LastRowDevTeam_DD", SW.RandomNumber(1,SW.DropDown_GetSize("ABCDDevelopmentGoal_LastRowDevTeam_DD")-1));
		SW.NormalClick("ABCDDevelopmentGoal_LastRowDealExec_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		int row=SW.WebTbl_GetRowCount("ABCDDevelopmentGoal_ContactSearch_LK");
		SW.WebTbl_Click("ABCDDevelopmentGoal_ContactSearch_LK", SW.RandomNumber(1, row-1),1);
		SW.WaitForWindowCount(1);
		SW.SwitchToWindow(1);
		SW.EnterValue("ABCDDevelopmentGoal_IncDeals_LK", "23");
		SW.EnterValue("ABCDDevelopmentGoal_NonIncDeals_LK", "43");
		String BTNPV = SW.RandomString(10);
		SW.EnterValue("ABCDDevelopmentGoal_BtnpvInc_EB", BTNPV);
		SW.EnterValue("ABCDDevelopmentGoal_BtnpvnonInc_EB", "987785");
		SW.EnterValue("ABCDDevelopmentGoal_Target_LK", "34535");
		SW.DropDown_SelectByIndex("ABCDDevelopmentGoal_TargetCurrency_DD",3);
		SW.Click("ABCDDevelopmentGoal_Save_LK");
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			do{
				int i=0;
				SW.DropDown_SelectByIndex("ABCDDevelopmentGoal_LastRowDevTeam_DD",(2+i));
				SW.Click("ABCDDevelopmentGoal_Save_LK");
				i++;
			}while(SW.IsAlertPresent());
		}

		//TODO Capture the error msg and validate.
		SW.CompareText("TestResult", "Changes successfully saved.","ABCDGoal_SuccessMsg_EB");
		int AddedGoalRow = SW.WebTbl_GetRowIndex("ABCDDevelopmentGoal_BtnpvInc_EB", 7, BTNPV);
		String TempXapth = SW.GetXPath("ABCDGoal_Delete_IM")+"[AddedGoalRow]";
		SW.ClickAndProceed(TempXapth);
		SW.HandleAlert(true);
	}
	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}
}

