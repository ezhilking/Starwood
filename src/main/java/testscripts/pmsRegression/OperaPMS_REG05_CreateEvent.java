package testscripts.pmsRegression;
/* Purpose		: 
 * TestCase Name:Opera:
A) Create event in SGR (Save & Events)
B) Pend in SGR
C) Resolve in Opera
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

public class OperaPMS_REG05_CreateEvent {
	CRM SW = new CRM();
	String PMSConfNum="184097700";
	String PropertyNo = "1965";
	String GCD;
	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
	}

	@Test(priority=1)	
	public void DB(){
		SW.EstablishConnection("qa3");

		String CreatedDate1 = SW.DateAddDays(SW.GetTimeStamp("MMM-yy"), "MMM-yy", -2, Calendar.MONTH).toUpperCase();
		String CreatedDate2 = SW.DateAddDays(SW.GetTimeStamp("MMM-yy"), "MMM-yy", -1, Calendar.MONTH).toUpperCase();
		PMSConfNum = SW.GetColumnValues("select * from (select  reservation_confirmation_num from reservation_room_sharer where pms_confirmation_num is not null "
				+ "and pms_confirmation_num >0 AND PROPERTY_master_id IN('1965') and (create_dt like ('%"+CreatedDate1+"%') or "
				+ "create_dt like ('%"+CreatedDate2+"%')) order by create_dt desc)", "reservation_confirmation_number").get(0);	
	}

	@Test(priority=2)	
	public void CreateEventInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);

		//Mapping
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PMSEvenetDeptMapping_LK");

		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", PMSConfNum);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		
		//Old Event creation
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.DropDown_SelectByText("SGRCreateEvent_EventType_DD", "Billing");
		SW.DropDown_SelectByIndex("SGRCreateEvent_Group_DD", 1);
		SW.DropDown_SelectByIndex("SGRCreateEvent_Category_DD",1);
		SW.DropDown_SelectByIndex("SGRCreateEvent_Detail_DD", 1);
		//Notes section
		SW.EnterValue("SGRCreateEvent_Noted_EB", "Comment"+SW.RandomString(5));
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		int size = SW.DropDown_GetSize("SGRCreateEvent_AssignTo_DD");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", SW.RandomNumber(1, size-1));
		SW.CheckBox("SGRCreateEvent_NotifyAssignee_CB", "OFF");
		if(SW.ObjectExists("SGRCreateEvent_EscalationDisable_BN")){
			SW.Click("SGRCreateEvent_EscalationDisable_BN");
		}
		
		//New event screen
		//		SW.EnterValue("SGRAddEvent_What_EB", "cancellation policy");
		//		SW.Click("SGRAddEvent_WhatFirstSuggestion_LK");//Suggestion box
		//		GCD = SW.GetText("SGRAddEvent_What_EB");
		//		SW.DropDown_SelectByText("SGRCreateEvent_EventType_DD", "Defect");
		//		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		//		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", SW.RandomNumber(0, SW.DropDown_GetSize("SGRCreateEvent_AssignTo_DD")-1));
		//		SW.Click("SGRAddEvent_ServiceOppAndCompensation_LK");
		//		SW.Wait(5);
		//		SW.DropDown_SelectByText("SGRCreateEvent_CompensationType_DD", "10 Meal Coupon");
		SW.Click("SGRCreateEvent_Save_BN");

		
		Environment.loger.log(Level.INFO, SW.GetText("General_ErrorMessage_DT"));
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=3)	
	public void PMS(){
		//		SW.LaunchBrowser(Environment.PMS_1965);
		SW.NavigateTo(Environment.PMS_1965);
		SK.OperaPMSLogin(SW.TestData("PMSUsername"), SW.TestData("PMSPassword"),PMSConfNum);
		//		SW.SikuliFocusRegionWindow();
		SW.Wait(3);
		//Traces icon
		if(SK.SikuliRegionObjectExists("Traces_IC")){
			SK.SikuliClick("Traces_IC");
		}else{
			SW.FailCurrentTest("Events not flowed to PMS");
		}
		SW.Wait(5);
		//		SW.SikuliRegionType(Key.DOWN);//TODO
		//		SW.Wait(3);
		//		SW.SikuliRegionType(Key.DOWN);

		//		SW.KeyboardStrokes("alt|e");
		//		SW.Wait(3);
		SK.SikuliClick("Traces_CommentsSection");
		SW.Wait(5);
		String Comments = SK.SikuliCopyToClipboard();
		SK.SikluliCompareTextContained("CommentCompariosion_DT",Comments,GCD);
		SK.KeyboardStrokes("alt|c");
		SW.Wait(3);
		SK.KeyboardStrokes("alt|c");//Not closing the primary window.later validation we can use the same session of window
	}

	@Test(priority=4)	
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

	@Test(priority=5)	
	public void CloseAnEventInPMS(){
		SW.Wait(2);
		SK.KeyboardStrokes("alt|r");
		SW.Wait(1);
		SK.KeyboardStrokes("u");
		SW.Wait(5);
		SK.SikuliFocusRegionWindow();
		SK.SikuliRegionWaitForObject("UpdateReservation_Wait");
		for(int i=0;i<=4;i++){
			SW.Wait(2);
			SK.SikuliRegionType(Key.TAB);
		}
		SK.SikuliRegionType(PMSConfNum);
		SW.Wait(3);
		SK.SikuliRegionType(Key.ENTER);
		SW.Wait(16);
		if(SK.SikuliRegionObjectExists("Traces_IC")){
			SK.SikuliClick("Traces_IC");
		}else{
			SW.FailCurrentTest("Events not flowed to PMS");
		}
		SW.Wait(5);
		//		SW.SikuliRegionType(Key.DOWN);//TODO
		//		SW.Wait(5);
		//		SW.SikuliRegionType(Key.DOWN);
		//		SW.KeyboardStrokes("alt|e");
		//		SW.Wait(3);
		SK.SikuliClick("Traces_CommentsSection");
		SW.Wait(5);
		String Comments = SK.SikuliCopyToClipboard();
		SK.SikluliCompareTextContained("CommentCompariosion_DT",Comments,"Pending");
		SW.Wait(15);
		SK.SikuliClick("Traces_Resolve_BN");
		SK.KeyboardStrokes("alt|s");//Click resolve button
		SW.Wait(3);
		SK.KeyboardStrokes("alt|c");
		SW.Wait(3);
		SK.KeyboardStrokes("alt|c");
		SW.Wait(3);
		SK.KeyboardStrokes("alt|e");
	}
	@Test(priority=6)	
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
