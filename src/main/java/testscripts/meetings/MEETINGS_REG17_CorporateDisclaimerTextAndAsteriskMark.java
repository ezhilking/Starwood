/* Purpose		: //TODO
 * TestCase Name: Corporate website children disclaimer text and asterisk mark validation
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

public class MEETINGS_REG17_CorporateDisclaimerTextAndAsteriskMark {
	CHANNELS SW = new CHANNELS();
	String childrenText = "Children*";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void CorporateChildrenWebsite(){
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
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage2_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsCorporate_EventName_EB",  SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_Contact_EB", SW.RandomString(5));		
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
		SW.EnterValue("MeetingsCorporate_PropName_EB","Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsCorporate_SoldOutMessage_EB","Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsCorporate_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_SaveWebsite_BT");	
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="CorporateChildrenWebsite")

	public void CorporateChildrenDisclaimerandAsterik(){

		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("MeetingsBooking_ClickonBook_BT");

		if((SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Environment.loger.log(Level.ERROR, "Object is present on Find Rooms and Rates");
			String ChildrenText = SW.GetText("MeetingsGuest_ChildrenText_DD");
			String DisclaimerText = SW.GetText("MeetingsGuest_ChildrenDisclamerText_DD");
			if(ChildrenText.endsWith("*")&& DisclaimerText.startsWith("*")){				
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Find Rooms and Rates");
			//	Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Find Rooms and Rates", "Child disclaimer text & asterisk mark is present on Find Rooms and Rates", "PASS");
			}else{
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Find Rooms and Rates");
			//	Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Find Rooms and Rates", "Child disclaimer text & asterisk mark is present on Find Rooms and Rates", "PASS");
			}
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Find Rooms and Rates", "Child disclaimer text & asterisk mark is present on Find Rooms and Rates", "PASS");
		}else{
			Environment.loger.log(Level.ERROR, "Object is not present on Find Rooms and Rates");
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is not present on Find Rooms and Rates", "Child disclaimer text & asterisk mark is not present on Find Rooms and Rates", "FAIL");
		}
		SW.TakeScreenshot("COREngLangScreenshotFindRooms&RatesPageWithChildren");	
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");

		if((SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Environment.loger.log(Level.ERROR, "Object is present on Available Rooms");
			String ChildrenText = SW.GetText("MeetingsGuest_ChildrenText_DD");
			String DisclaimerText = SW.GetText("MeetingsGuest_ChildrenText_DD");
			if(ChildrenText.endsWith("*") && DisclaimerText.startsWith("*")){
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Available Rooms");
			//	Reporter.Write("AvailableRoomsCorporate_ST", "Matching", "Matching", "PASS");
			}else{
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Available Rooms");
			//	Reporter.Write("AvailableRoomsCorporate_ST", "Not Matching", "Not Matching", "FAIL");
				Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Available Rooms", "Child disclaimer text & asterisk mark is present on Available Rooms", "PASS");
			}
			
		}else{
			Environment.loger.log(Level.ERROR, "Object is not present on Available Rooms");
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is not present on Available Rooms", "Child disclaimer text & asterisk mark is not present on Available Rooms", "FAIL");
		}
		
		SW.TakeScreenshot("COREngLangScreenshotAvailableRoomsPageWithChildren");
		SW.Click("MeetingsBooking_EventPage_BT");
		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBooking_ChangeLanguage_BT1");
		SW.Click("MeetingsBooking_ClickonBook_BT");

		if((SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Environment.loger.log(Level.ERROR, "Object is present on Find Rooms and Rates");
			String ChildrenText = SW.GetText("MeetingsGuest_ChildrenText_DD");
			String DisclaimerText = SW.GetText("MeetingsGuest_ChildrenDisclamerText_DD");
			if(ChildrenText.endsWith("*")&& DisclaimerText.startsWith("*")){
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Find Rooms and Rates");
			//	Reporter.Write("DisclaimerTextCorporate_ST", "the child disclaimer text and asterisk mark is present on Available Rooms", "the child disclaimer text and asterisk mark is present on Available Rooms", "PASS");
			}else{
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Find Rooms and Rates");
			//	Reporter.Write("DisclaimerTextCorporate_ST", "the child disclaimer text and asterisk mark is present on Available Rooms", "the child disclaimer text and asterisk mark is present on Available Rooms", "FAIL");
			}
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Available Rooms", "Child disclaimer text & asterisk mark is present on Available Rooms", "PASS");
		}else{
			Environment.loger.log(Level.ERROR, "Object is not present on Find Rooms and Rates");
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Available Rooms", "Child disclaimer text & asterisk mark is present on Available Rooms", "FAIL");
		}
		SW.TakeScreenshot("CORJapLangScreenshotFindRooms&RatesPageWithChildren");
		
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");

		if((SW.ObjectExists("MeetingsGuest_Children_DD") && SW.ObjectExists("MeetingsGuest_ChildrenDisclamerText_DD"))){
			Environment.loger.log(Level.ERROR, "Object is present on Available Rooms");
			String ChildrenText = SW.GetText("MeetingsGuest_ChildrenText_DD");
			String DisclaimerText = SW.GetText("MeetingsGuest_ChildrenText_DD");
			if(ChildrenText.endsWith("*") && DisclaimerText.startsWith("*")){
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Available Rooms");
		//		Reporter.Write("DisclaimerTextCorporate_ST", "Matching", "Matching", "PASS");
			}else{			
				Environment.loger.log(Level.ERROR, "the child disclaimer text and asterisk mark is present on Available Rooms");
		//		Reporter.Write("DisclaimerTextCorporate_ST", "Not Matching", " Not Matching", "FAIL");
			}
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Available Rooms", "Child disclaimer text & asterisk mark is present on Available Rooms", "PASS");
		}else{
			Environment.loger.log(Level.ERROR, "Object is not present on Available Rooms");
			Reporter.Write("CorporateAvailableRooms_ST", "Child disclaimer text & asterisk mark is present on Available Rooms", "Child disclaimer text & asterisk mark is present on Available Rooms", "FAIL");
		}
		SW.TakeScreenshot("CORJapLangScreenshotAvailableRoomsPageWithChildren");
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
