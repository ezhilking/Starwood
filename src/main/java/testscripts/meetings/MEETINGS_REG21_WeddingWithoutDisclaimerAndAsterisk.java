package testscripts.meetings;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MEETINGS_REG21_WeddingWithoutDisclaimerAndAsterisk {

	CHANNELS SW = new CHANNELS();
	String Number;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void WeddingChildrenWebsite(){
		SW.MeetingsLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.Click("MeetingsCorporate_CreateWebsite_BT");
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD", SW.TestData("SGP_PID"));
		SW.SelectRadioButton("MeetingsCorporate_radioButton_BN");
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_GroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.DropDown_SelectByIndex("MeetingsWedding_MeetingType_DD", 3);
		SW.DropDown_SelectByValue("MeetingsWedding_PrimaryLanguage_DD", "en:English");
		SW.CheckBox("MeetingsWedding_SecondaryLanguage_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsWedding_EventName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_EventDate_EB", "2017-09-28");
		SW.EnterValue("MeetingsCorporate_Contact_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_EventEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsWedding_Attendee_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		try{
			int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
			int Random = SW.RandomNumber(0, Dropdownsize-1);
			SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
		SW.Click("MeetingsWedding_AssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsWedding_PropName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsWedding_SoldOutMessage_EB", "Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsWedding_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.CheckBox("MeetingsCorporate_ShowChildren_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsWedding_SaveWebsite_BT");	
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="WeddingChildrenWebsite")

	public void WeddingChildrenDisclaimerandAsterik(){

		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("MeetingsBookingWedding_BookRoom_BT");

		if(!(SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is not present on Available Rooms", "Object is not present on Available Rooms", "PASS");
		}else{
			Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is present on Available Rooms", "Object is present on Available Rooms", "FAIL");
		}

		SW.TakeScreenshot("WEDDEngLangScreenshotFindRooms&RatesPageWithoutChildren");	
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");

		if(!(SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is not present on Available Rooms", "Object is not present on Available Rooms", "PASS");
			}else{
				Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is present on Available Rooms", "Object is present on Available Rooms", "FAIL");
		}
		SW.TakeScreenshot("WEDDEngLangScreenshotAvailableRoomsPageWithoutChildren");
		SW.Click("MeetingsBookingWedding_EventPage_BT");
		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBooking_ChangeLanguage_BT1");
		SW.Click("MeetingsBookingWedding_BookRoom_BT");

		if(!(SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is not present on Available Rooms", "Object is not present on Available Rooms", "PASS");
			}else{
				Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is present on Available Rooms", "Object is present on Available Rooms", "FAIL");
		}

		SW.TakeScreenshot("WEDDJapLangScreenshotFindRooms&RatesPageWithoutChildren");	
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");

		if(!(SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is not present on Available Rooms", "Object is not present on Available Rooms", "PASS");
		}else{
			Reporter.Write("WeddingChildrenDisclamerText_ST", "Object is present on Available Rooms", "Object is present on Available Rooms", "FAIL");
			}
		SW.TakeScreenshot("WEDDJapLangScreenshotAvailableRoomsPageWithoutChildren");
	} 

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}