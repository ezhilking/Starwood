/* Purpose		:  Valhalla Portal Must Run Suit
 * TestCase Name:  Reg_Modify the Rate Category status to No Arrival from VP and verify the flow to Galaxy DB.NOTE Step 4 is Not Aplicable.
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */


package testscripts.vpRegression;


import java.util.Calendar;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import functions.CRS;
import functions.Environment;
import functions.Reporter;


public class MUSTRUN40_Reg_Modify_the_Rate_Category_status_to_Close_from_VP {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	String StartDate= SW.GetTimeStamp("MM/dd/yyyy");	
	String EndDate=SW.DateAddDays(StartDate, "MM/dd/yyyy", 1, Calendar.DATE);
	String DBStartDate=SW.GetTimeStamp("dd-MMM-yy");
	String DBEndDate= SW.DateAddDays(DBStartDate, "dd-MMM-yy", 30, Calendar.DATE);
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
	}
	//***************************************** Changing Rate Category Status to NA with All UI Validations ***************************************************************
	@Test(priority=0)
	    public void TransientRatePlan(){		
		SW.VPLogin("VP_Username", "VP_Password");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Category Allotment/Status/LOS");
		SW.Click("VPRates_RateCategoryAllotmentStatus_LK");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		//SW.EnterValue("VPRateCategoryStatus_EndDate_EB", EndDate);
		SW.Wait(1);
		SW.ClickAndProceed("VPRateCategoryStatus_ViewDateRange_BT");
		
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			SW.CompareText("Rate Category Madatory Field Validation", "Rate Category Alert Popup displayed successfully", "Rate Category Alert Popup displayed successfully");
		}else{
			SW.CompareText("Rate Category Madatory Field Validation", "Rate Category Alert Popup displayed susccessfully", "Rate Category Alert Popup Not displayed successfully");
		}
		
		SW.DropDown_SelectByValue("VPRateCategoryStatus_RateCategory_DD", "0");
		SW.ClearValue("VPRateCategoryStatus_StartDate_EB");
		SW.EnterValue("VPRateCategoryStatus_EndDate_EB", EndDate);
		SW.ClickAndProceed("VPRateCategoryStatus_ViewDateRange_BT");
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			SW.CompareText("Start Date Madatory Field Validation", "Start Date Required Alert Popup displayed successfully", "Start Date Required Alert Popup displayed successfully");
		}else{
			SW.CompareText("Start Date Madatory Field Validation", "Start Date Required Alert Popup displayed susccessfully", "Start Date Required Alert Popup Not displayed successfully");
		}
		
		SW.EnterValue("VPRateCategoryStatus_StartDate_EB", StartDate);
		SW.ClearValue("VPRateCategoryStatus_EndDate_EB");
		SW.ClickAndProceed("VPRateCategoryStatus_ViewDateRange_BT");
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			SW.CompareText("End Date Madatory Field Validation", "End Date Required Alert Popup displayed successfully", "End Date Required Alert Popup displayed successfully");
		}else{
			SW.CompareText("End Date Madatory Field Validation", "End Date Required Alert Popup displayed susccessfully", "End Date Required Alert Popup Not displayed successfully");
		}
		SW.EnterValue("VPRateCategoryStatus_EndDate_EB", EndDate);
		SW.Click("VPRateCategoryStatus_ViewDateRange_BT");
		SW.DropDown_SelectByText("VPRateCategoryStatus_Status_DD", "CL");
		SW.Click("VPRateCategoryStatus_Save_BT");
		SW.CompareText("RateCategoryStatusChange_SuccessMsgValidation", "Rate Category Room Type Status/MLOS/Allotment record was updated successfully!", SW.GetText("VPRates_RPinfoMsg_DT"));
		
		SW.DropDown_SelectByText("VPRateCategoryStatus_Status_DD", "OP");
		SW.Click("VPRateCategoryStatus_Save_BT");
		SW.CompareText("RateCategoryStatusChange_SuccessMsgValidation", "Rate Category Room Type Status/MLOS/Allotment record was updated successfully!", SW.GetText("VPRates_RPinfoMsg_DT"));
		
	}
}