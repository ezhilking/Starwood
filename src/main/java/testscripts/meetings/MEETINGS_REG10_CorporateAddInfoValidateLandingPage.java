/* Purpose		: //TODO
 * TestCase Name: Event Description, event title, addInfo text validation for corporate in booking landing page
 * Created By	: Brij
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */

package testscripts.meetings;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MEETINGS_REG10_CorporateAddInfoValidateLandingPage {
	CHANNELS SW = new CHANNELS();
	String eventDesc = SW.RandomString(5);
	String addInfo = SW.RandomString(5);
	String eventTitle = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void CorporateWebsiteforAddInfo(){
		SW.MeetingsLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.Click("MeetingsCorporate_CreateWebsite_BT");
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD", SW.TestData("SGP_PID"));
		SW.SelectRadioButton("MeetingsCorporate_radioButton_BN");
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_GroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.DropDown_SelectByIndex("MeetingsCorporate_MeetingType_DD", 1);
		SW.DropDown_SelectByValue("MeetingsCorporate_PrimaryLanguage_DD", "en:English");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage_BT", "ON");

		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");

		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitle);

		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDesc);
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsCorporate_Attendee_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		try{
			int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
			int Random = SW.RandomNumber(0, Dropdownsize-1);
			SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
		SW.Click("MeetingsCorporate_AssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	

		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfo);		

		SW.EnterValue("MeetingsCorporate_PropName_EB","Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsCorporate_SoldOutMessage_EB","Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsCorporate_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_SaveWebsite_BT");	
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="CorporateWebsiteforAddInfo")

	public void BookingPageValidateAddInfo(){
		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);

		if(SW.CompareText("EventDescription_DD", eventDesc, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfo, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitle, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
