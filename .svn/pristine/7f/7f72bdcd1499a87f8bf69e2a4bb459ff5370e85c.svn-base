package testscripts.SPGLink;
/** Purpose		: This is to validate the Event Posting in SPG Link 2.0 under SPG Pro User Role
 * TestCase Name: EventPostingComplete
 * Created By	: Vaishali Krishna
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */ 
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SPGLink_REG09_EventPostingComplete {
	CRM SW = new CRM();
	String Mbrshp_num ; //Active membership# 
	String SecondMbrshp ; //Second membership
	String ThridMrshp ; //Third membership
	String ThirdPartyMbrshp; // ThirdParty membership#
	String EventName = SW.RandomString(5);
	String AbbrEventName = SW.RandomString(2);
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	@Test(priority=0)
	public void DataSetup_AllFieldPopulated() {
		SW.EstablishConnection("QA3");
		List<String> MemberShip=SW.GetColumnValues("select * from freq_travel_mbrshp where mbrshp_sts='A' and mrktg_pgm='5' and join_date > (sysdate-4) and gst_master_prof_id != 0", "Mbrshp_num");
			Mbrshp_num=MemberShip.get(0);
			SecondMbrshp=MemberShip.get(1);
			ThirdPartyMbrshp=MemberShip.get(2);
			ThridMrshp=MemberShip.get(3);
			
			if(Mbrshp_num != null && SecondMbrshp != null && ThirdPartyMbrshp != null && ThridMrshp != null)
				Environment.loger.log(Level.INFO, "Member Exists " +Mbrshp_num +" " +SecondMbrshp + " " +ThirdPartyMbrshp +" " +ThridMrshp);
			else
				SW.FailCurrentTest("No Member Exists, Check Query criteria");
	}
	@Test(priority=1,dependsOnMethods="DataSetup_AllFieldPopulated")
	public void EventPosting_AllFieldPopulated(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("SPG Pro");
		SW.WaitForPageload();
		SW.WaitTillElementToBeClickable("SPGLinkHome_SPGProGroupsAndEvent_BT");
		SW.Click("SPGLinkHome_SPGProGroupsAndEvent_BT");
		SW.WaitTillElementToBeClickable("SPGLinkEventListing_Create_BT");
		SW.Click("SPGLinkEventListing_Create_BT");
		SW.WaitForPageload();
		SW.EnterValue("SPGLinkEventPosting_EnterMemberNumber_EB", Mbrshp_num);
		SW.Click("SPGLinkEventPosting_AddAnother_BT");
		SW.EnterValue("SPGLinkEventPosting_AddAnother_EB", SecondMbrshp);
		SW.Click("SPGLinkEventPosting_AddAnotherThird_BT");
		SW.EnterValue("SPGLinkEventPosting_ThirdMember_EB", ThridMrshp);
		SW.SelectRadioButton("SPGLinkEventPosting_GovEmpNO_RB");
		if(SW.IsAlertPresent())
		SW.HandleAlert(true);
		SW.WaitForPageload();	
		SW.SelectRadioButton("SPGLinkEventPosting_PointsEligibleYES_RB");
		if(!SW.IsAlertPresent())
			SW.Click("SPGLinkEventPosting_PointsEligibleYES_RB");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		SW.SelectRadioButton("SPGLinkEventPosting_ThirdPartyYES_RB");
		SW.EnterValue("SPGLinkEventPosting_ThirdPartyMbrshp_EB", ThirdPartyMbrshp);
		SW.SelectRadioButton("SPGLinkEventPosting_StandardBonus_RB");
		SW.EnterValue("SPGLinkEventPosting_FlatBonus_EB", "100");
		SW.DropDown_SelectByText("SPGLinkEventPosting_MarketSegment_DD", "Government");
		SW.DropDown_SelectByText("SPGLinkEventPosting_EventType_DD", "Catering Only");
		SW.EnterValue("SPGLinkEventPosting_EventName_EB", EventName);
		SW.EnterValue("SPGLinkEventPosting_EventNameAbbrivaated_EB", AbbrEventName);
		SW.EnterValue("SPGLinkEventPosting_Attendees_EB", "100");
		SW.EnterValue("SPGLinkEventPosting_PeakRoom_EB", "10");
		SW.EnterValue("SPGLinkEventPosting_RoomNightConsumed_EB", "10");
		SW.EnterValue("SPGLinkEventPosting_EventBookingDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -100,	 Calendar.DATE));
		SW.EnterValue("SPGLinkEventPosting_ArvDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -100,	 Calendar.DATE));
		SW.EnterValue("SPGLinkEventPosting_DeptDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -99,	 Calendar.DATE));
		SW.EnterValue("SPGLinkEventPosting_PrimarySeller_EB", "vaiskri");
		SW.EnterValue("SPGLinkEventPosting_SSOAccountManager_EB", "veermb");
		SW.EnterValue("SPGLinkEventPosting_RoomRevenue_EB", "100");
		SW.EnterValue("SPGLinkEventPosting_CateringRevenue_EB", "100");
		SW.EnterValue("SPGLinkEventPosting_RoomRental_EB", "250");
		SW.Click("SPGLinkEventPosting_Submit_BT");
		
		SW.WaitForPageload();
		
		if (SW.ObjectExists("SPGLinkEventPosting_Success_DT")) {
			Environment.loger.log(Level.INFO, "Your event" + EventName +" has been submitted for review. Thank You.");
		} else if (SW.ObjectExists("SPGLinkEventPosting_Error_DT")){
			String ErrorMsg = SW.GetText("SPGLinkEventPosting_Error_DT");
			Environment.loger.log(Level.ERROR, "Error in Event Creation" + ErrorMsg);
			SW.FailCurrentTest(ErrorMsg);
		}
		SW.Click("SPGLink_LogOut_BT");
}
	@AfterClass
	public void EndTest(){
		SW.CloseDBConnection();
		Reporter.StopTest();		
	}
}