package testscripts.pmsRegression;
/* Purpose		: 
 * TestCase	Name: Galaxy:
A) Create event in SGR (Save & Events)
B) Pend in SGR
C) Close task in Lightspeed
 * Created By	: Ezhilarasan.S 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.sikuli.script.Key;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;
import functions.Utility;

public class GalaxyPMS_REG01_CreateEvent {
	String GCD;
	String PMSConfNum;
	String PropertyNo = "1005";
	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GALAXYURL);
	}

	@Test(priority=1)	
	public void DB(){
		SW.EstablishConnection("stage");
		String CreatedDate1 = SW.DateAddDays(SW.GetTimeStamp("MMM-yy"), "MMM-yy", -2, Calendar.MONTH).toUpperCase();
		String CreatedDate2 = SW.DateAddDays(SW.GetTimeStamp("MMM-yy"), "MMM-yy", -1, Calendar.MONTH).toUpperCase();
		PMSConfNum = SW.GetColumnValues("select * from (select  reservation_confirmation_num from reservation_room_sharer where pms_confirmation_num is not null "
				+ "and pms_confirmation_num >0 AND PROPERTY_master_id IN('"+PropertyNo+"') and (create_dt like ('%"+CreatedDate1+"%') or "
				+ "create_dt like ('%"+CreatedDate2+"%')) order by create_dt desc)", "reservation_confirmation_number").get(0);	
		if(PMSConfNum==null){
			SW.FailCurrentTest("Reservation not available create manually and proceed with the execution");
		}
	}

	@Test(enabled=false,dependsOnMethods="DB",priority=2)	
	public void CreateEventInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("QA3_SGRUsername"), SW.TestData("QA3_SGRPassword"), PropertyNo);

		//Mapping
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PMSEvenetDeptMapping_LK");

		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", PMSConfNum);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		//New UI
		SW.EnterValue("SGRAddEvent_What_EB", "cancellation policy");
		//Suggestion box
		SW.Click("SGRAddEvent_WhatFirstSuggestion_LK");
		GCD = SW.GetText("SGRAddEvent_What_EB");
		SW.DropDown_SelectByText("SGRCreateEvent_EventType_DD", "Defect");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", SW.RandomNumber(0, SW.DropDown_GetSize("SGRCreateEvent_AssignTo_DD")-1));
		SW.Click("SGRAddEvent_ServiceOppAndCompensation_LK");
		SW.Wait(5);
		SW.DropDown_SelectByText("SGRCreateEvent_CompensationType_DD", "10 Meal Coupon");
		SW.Click("SGRCreateEvent_Save_BN");
		Environment.loger.log(Level.INFO, SW.GetText("General_ErrorMessage_DT"));
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=3)	
	public void GalaxyLogin(){
		SK.GalaxyPMSLogin(PMSConfNum);
		if(!SW.ObjectExists("GalaxyPMS_TaskTracker_BN")){
			SW.FailCurrentTest("Event still not flown to Galaxy!");
		}
		//		SW.ClickByJavascript("GalaxyPMS_TaskTracker_BN");

		//Check for that object//TODO

		//		SW.ClickByJavascript("GalaxyPMS_OpenReservation_BN");//Open for later navigation in 'CloseAnEventInGalaxy' method
	}

	@Test(dependsOnMethods="GalaxyLogin",priority=4)	
	public void SGRPendAnEvent(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", PMSConfNum);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		//		SW.WebTbl_Click("SGRGuestEvent_Edit_LK", 3, 12);
		//		SW.WaitForPageload();
		SW.Click("SGRGuestprofile_Edit_LK");
		SW.DropDown_SelectByText("SGRAddEvent_EventStatus_DD", "Pending");
		SW.GetScreenshot("PendAnEvent");
		SW.Click("SGRCreateEvent_Save_BN");
		Environment.loger.log(Level.INFO, SW.GetText("General_ErrorMessage_DT"));
		SW.GetScreenshot("PendErrorMessage");
		SW.Click("SGR_Logout_LK");
		Utility.CloseBrowser();
	}

	@Test(dependsOnMethods="SGRPendAnEvent",priority=5)
	public void CloseAnEventInGalaxy(){
		SW.Wait(10);
		SK.SikuliFocusRegionWindow();
		for(int i=0;i<=5;i++){
			SW.Wait(1);
			SK.SikuliRegionType(Key.TAB);
		}
		SK.SikuliRegionType(PMSConfNum);
		SW.Wait(3);
		SK.KeyboardStrokes("alt|s");//TODO chk for the pending and close the event
	}

	@Test(dependsOnMethods="CloseAnEventInGalaxy",priority=6)	
	public void finalValidationInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", PMSConfNum);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		//		SW.WebTbl_Click("SGRGuestEvent_Edit_LK", 3, 12);
		//		SW.WaitForPageload();
		SW.Click("SGRGuestprofile_Edit_LK");
		SW.CompareText("ClosedStatus", "Closed", SW.DropDown_GetSelectedText("SGRAddEvent_EventStatus_DD"));
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
