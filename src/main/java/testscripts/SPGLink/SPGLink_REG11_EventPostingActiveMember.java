package testscripts.SPGLink;
/** Purpose		: This is to validate the Event Posting in SPG Link 2.0 under SPG Pro User Role with only Active Member with AverageRoomRate
 * TestCase Name: EventPostingActiveMember
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

public class SPGLink_REG11_EventPostingActiveMember {
	CRM SW = new CRM();
	String Mbrshp_num; // Active member Event should got posted successfully
	String EventName = SW.RandomString(5);
	String AbbrEventName = SW.RandomString(2);
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	@Test(priority=0)
	public void DataSetupActiveMember() {
		SW.EstablishConnection("QA3");
		List<String> MemberShip=SW.GetColumnValues("select * from freq_travel_mbrshp where mbrshp_sts='A' and mbrshp_lvl='A' and mrktg_pgm='5' and join_date > (sysdate-5) and gst_master_prof_id != 0", "Mbrshp_num");
			Mbrshp_num=MemberShip.get(0);
			
			if(Mbrshp_num != null)
				Environment.loger.log(Level.INFO, "Member Exists" +Mbrshp_num);
			else
				SW.FailCurrentTest("No Member Exists, Check Query criteria");
	}
	@Test(priority=1,dependsOnMethods="DataSetupActiveMember")
	public void EventPostingActiveMember(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("SPG Pro");
		SW.Click("SPGLinkHome_SPGProGroupsAndEvent_BT");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("SPGLinkEventListing_Create_BT");
		SW.NormalClick("SPGLinkEventListing_Create_BT");
		SW.EnterValue("SPGLinkEventPosting_EnterMemberNumber_EB", Mbrshp_num);
		SW.Wait(5);
		SW.SelectRadioButton("SPGLinkEventPosting_ThirdParty_RB");
		SW.Wait(5);
		SW.SelectRadioButton("SPGLinkEventPosting_StandardBonus_RB");
		SW.EnterValue("SPGLinkEventPosting_FlatBonus_EB", "100");
		SW.SelectRadioButton("SPGLinkEventPosting_GovEmp_RB");
		if(SW.IsAlertPresent())
		SW.HandleAlert(true);
		SW.WaitForPageload();
		SW.SelectRadioButton("SPGLinkEventPosting_PointsEligible_RB");
		if(SW.IsAlertPresent())
		SW.HandleAlert(true);
		SW.WaitForPageload();
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
		SW.EnterValue("SPGLinkEventPosting_AverageRoomRevenue_EB", "1000");
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
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SPGLinkEvent_LogOut_BT");
		SW.CloseDBConnection();
		Reporter.StopTest();		
	}
}
