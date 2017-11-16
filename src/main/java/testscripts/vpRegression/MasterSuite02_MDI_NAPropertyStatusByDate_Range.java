package testscripts.vpRegression;

import java.util.Calendar;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MasterSuite02_MDI_NAPropertyStatusByDate_Range {

	CRS SW = new CRS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}


	@Test(priority=0)
	public void viewModifyInventory()
	{
		// Login to VP
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.VPClick("VP_Submit_BT");

		SW.EnterValue("VP_PropertyID_EB", SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Manage Daily Inventory");
		SW.Click("VPInventory_MouseOverMDI_LK");		
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.SelectRadioButton("VPInventory_DateRangeSelection_RB");
		String fromDate = SW.GetTimeStamp("MM/dd/yyyy");
		String toDate = SW.DateAddDays(fromDate, "MM/dd/yyyy",2, Calendar.DATE);
		SW.EnterValue("VPInventory_ToDate_EB",toDate );
		SW.DropDown_SelectByText("VPInventory_ProductType_DD", "House");
		SW.Click("VPInventory_ViewModify_BT");
	}

	@Test(priority=1, dependsOnMethods={ "viewModifyInventory"})
	public void noArrivalInventory()
	{
		SW.DropDown_SelectByIndex("VPInventory_HouseLevelStatus_ED", 2);
		SW.NormalClick("VPInventory_Save_BT");
		//Validating the Change
		SW.CompareText("VPReferenceLevel_ValidationMessage_ST","Product inventory saved successfully", SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//validating DB
		SW.EstablishConnection(Environment.getRunEnvironment());
		List<String> inv_status = SW.GetColumnValues("select inv_sts_cd from inventory.prdct_inv where PRDCT_ID = '30926' and use_start_dtme >= SYSDATE and use_end_dtme <= SYSDATE + 3", "inv_sts_cd");
		int status = 0;
		for(String str :inv_status)
		{
			if(!(str.equals("NA")))
			{
				status = 1;
			}
		}
		if(status == 0)
			Reporter.Write("DB Validation", "Inventory Status should be NA sucessfully in DB", "Inventory Status has been NA sucessfully in DB", "PASS");
		else
			Reporter.Write("DB Validation", "Inventory Status should be NA sucessfully in DB", "Inventory Status not NA sucessfully in DB", "FAIL");


		List<String> status_chng_reason = SW.GetColumnValues("select sts_chng_reason_cd from inventory.prdct_inv where PRDCT_ID = '30926' and use_start_dtme >= SYSDATE and use_end_dtme <= SYSDATE + 3", "sts_chng_reason_cd");	
		int reason = 0; 
		for(String str :status_chng_reason)
		{
			if(!(str.equals("M")))
			{
				reason = 1;
			}
		}
		if(reason == 0)
			Reporter.Write("DB Validation", "Inventory Status Change Reason  should be Manual sucessfully in DB", "Inventory Status Change Reason is Manual  in DB", "PASS");
		else
			Reporter.Write("DB Validation", "Inventory Status Change Reason  should be Manual sucessfully in DB", "Inventory Status Change Reason is not Manual  in DB", "FAIL");
		SW.CloseDBConnection();

	}

	@Test(priority=2, dependsOnMethods={ "viewModifyInventory" , "noArrivalInventory" })
	public void openInventory()
	{
		SW.DropDown_SelectByIndex("VPInventory_HouseLevelStatus_ED", 1);
		SW.NormalClick("VPInventory_Save_BT");
		//Validating the Change
		SW.CompareText("VPReferenceLevel_ValidationMessage_ST","Product inventory saved successfully", SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//validating DB
		SW.EstablishConnection(Environment.getRunEnvironment());
		List<String> inv_status = SW.GetColumnValues("select inv_sts_cd from inventory.prdct_inv where PRDCT_ID = '30926' and use_start_dtme >= SYSDATE and use_end_dtme <= SYSDATE + 3", "inv_sts_cd");
		int status = 0;
		for(String str :inv_status)
		{
			if(!(str.equals("OP")))
			{
				status = 1;
			}
		}
		if(status == 0)
			Reporter.Write("DB Validation", "Inventory Status should be OPEN sucessfully in DB", "Inventory Status has been OPEN sucessfully in DB", "PASS");
		else
			Reporter.Write("DB Validation", "Inventory Status should be OPEN sucessfully in DB", "Inventory Status not O sucessfully in DB", "FAIL");


		List<String> status_chng_reason = SW.GetColumnValues("select sts_chng_reason_cd from inventory.prdct_inv where PRDCT_ID = '30926' and use_start_dtme >= SYSDATE and use_end_dtme <= SYSDATE + 3", "sts_chng_reason_cd");	
		int reason = 0; 
		for(String str :status_chng_reason)
		{
			if(!(str.equals("M")))
			{
				reason = 1;
			}
		}
		if(reason == 0)
			Reporter.Write("DB Validation", "Inventory Status Change Reason  should be Manual sucessfully in DB", "Inventory Status Change Reason is Manual  in DB", "PASS");
		else
			Reporter.Write("DB Validation", "Inventory Status Change Reason  should be Manual sucessfully in DB", "Inventory Status Change Reason is not Manual  in DB", "FAIL");


		SW.CloseDBConnection();
	}


	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
