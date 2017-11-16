/* Purpose		: //TODO
 * TestCase Name: Social Multi property single event email validation & event special character validation
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

public class MEETINGS_REG40_SocialMultiPropertySingEvent {
	CHANNELS SW = new CHANNELS();
	String Number;
	String Meetingname = SW.RandomString(5)+"#*&#ghs"+"()@$GK";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void CreateSocialMultiWebsite(){
		SW.MeetingsLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.Click("MeetingsCorporate_CreateWebsite_BT");
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD", SW.TestData("SGP_PID"));
		SW.SelectRadioButton("MeetingsCorporate_radioButton_BN");
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_GroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.DropDown_SelectByIndex("MeetingsSocial_MeetingType_DD", 2);
		SW.Click("MeetingsCorWebsite_GroupBlocks_LK");
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD", SW.TestData("SGP_SecondPID"));
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_SecondGroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.DropDown_SelectByIndex("MeetingsSocial_MeetingType_DD", 2);
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsSocial_EventName_EB", Meetingname);
		SW.EnterValue("MeetingsSocial_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_Contact_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_EventEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsSocial_Attendee_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
		int Random = SW.RandomNumber(0, Dropdownsize-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

		SW.Click("MeetingsSocial_AssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsSocial_PropName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsSocial_SoldOutMessage_EB", "Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsSocial_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsCorWebsite_MutliPropertyEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsSocial_SaveWebsite_BT");	
		SW.TakeScreenshot("Publishing Social Website");

		String AttendeeText = SW.GetText("MeetingsCorporate_AttendeeLink_BT");	
		try{
			String IDIndex = "id=";
			int StartingIndex = AttendeeText.indexOf(IDIndex);
			int EndIndex  = AttendeeText.indexOf("&");
			Environment.loger.log(Level.INFO, "Attendee:"+AttendeeText);
			Number = AttendeeText.substring(StartingIndex+IDIndex.length(), EndIndex);
			Environment.loger.log(Level.INFO, "ID:"+Number);
			//		SW.WriteToTestData("URLSocial", AttendeeText);
			//		SW.WriteToTestData("SocialMeetingID", Number);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}	

		SW.Click("MeetingsCorporateWebsite_Email_LK");	
		SW.SwitchToWindow(2);
		SW.TakeScreenshot("Create Email this info");
		SW.EnterValue("MeetingsCorporateWebsite_EmailWindow_ST", "shalini.jaikumar@accenture.com");
		SW.Click("MeetingsCorporateWebsite_EmailSend_LK");	
		SW.Click("MeetingsCorporateWebsite_EmailSendClose_LK");
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("MeetingsCorporate_SwitchMainFrame_FR");

		if(SW.CompareText("CorporateWebsiteEmailInfo_DT", "You have successfully published "+Meetingname+" website", SW.GetText("MeetingsCorporate_PublishSite_ST")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}	
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="CreateSocialMultiWebsite")

	public void SocialWebsiteEmailThisInfo(){

		SW.Click("MeetingsCorporateWebsite_Email_LK");	
		SW.SwitchToWindow(2);
		SW.TakeScreenshot("Create Email this info");
		SW.EnterValue("MeetingsCorporateWebsite_EmailWindow_ST", "shalini.jaikumar@accenture.com");
		SW.Click("MeetingsCorporateWebsite_EmailSend_LK");	
		SW.Click("MeetingsCorporateWebsite_EmailSendClose_LK");
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("MeetingsCorporate_SwitchMainFrame_FR");

		if(SW.CompareText("SocialMultiPropEmailInfo_DT", "You have successfully published "+Meetingname+" website", SW.GetText("MeetingsCorporate_PublishSite_ST")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}		
	}	

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=3,dependsOnMethods="SocialWebsiteEmailThisInfo")

	public void ScoialEventSpecialValid(){
		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);

		SW.MoveToObject("MeetingsSocial_ChooseHotel_BT");
		SW.Click("MeetingsSocial_ChooseHotelFH_BT");
		String socialChar = SW.GetText("MeetingsSocial_EventSpecialChar_BT");

		if(SW.CompareText("SocialFirstPropSpeacialNameValidation_ST", Meetingname, socialChar))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}	

		SW.MoveToObject("MeetingsSocial_ChooseHotel_BT");
		SW.Click("MeetingsSocial_ChooseHotelSH_BT");
		String socialChar1 = SW.GetText("MeetingsSocial_EventSpecialChar_BT");
		if(SW.CompareText("SocialSecondPropSpeacialNameValidation_ST", Meetingname, socialChar1))
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