/* Purpose		: //TODO
 * TestCase Name: AddInfo text validation for all languages for corporate in booking landing page
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

public class MEETINGS_REG11_CorporateEventAddInfoALLlanguages {

	CHANNELS SW = new CHANNELS();

	String eventDescEN = SW.RandomString(5);
	String eventDescES = SW.RandomString(5);
	String eventDescFR = SW.RandomString(5);
	String eventDescGE = SW.RandomString(5);
	String eventDescJP = SW.RandomString(5);
	String eventDescIT = SW.RandomString(5);
	String eventDescCH = SW.RandomString(5);

	String addInfoEN = SW.RandomString(5);
	String addInfoES = SW.RandomString(5);
	String addInfoFR = SW.RandomString(5);
	String addInfoGE = SW.RandomString(5);
	String addInfoJP = SW.RandomString(5);
	String addInfoIT = SW.RandomString(5);
	String addInfoCH = SW.RandomString(5);

	String eventTitleEN = SW.RandomString(5);
	String eventTitleES = SW.RandomString(5);
	String eventTitleFR = SW.RandomString(5);
	String eventTitleGE = SW.RandomString(5);
	String eventTitleJP = SW.RandomString(5);
	String eventTitleIT = SW.RandomString(5);
	String eventTitleCH = SW.RandomString(5);


	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)
	public void CorporateWebsiteALLlanguagesforEventAddInfo(){
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
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage1_BT", "ON");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage6_BT", "ON");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage7_BT", "ON");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage8_BT", "ON");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage5_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");

		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleEN);
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescEN);

		SW.Click("MeetingsCorporate_EventEspanish_EB");
		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleES);
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescES);

		SW.Click("MeetingsCorporate_EventFrench_EB");
		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleFR);
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescFR);

		SW.Click("MeetingsCorporate_EventGerman_EB");
		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleGE);
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescGE);

		SW.Click("MeetingsCorporate_EventJapanese_EB");
		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleJP);
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescJP);

		SW.Click("MeetingsCorporate_EventItalian_EB");
		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleIT);
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescIT);

		SW.Click("MeetingsCorporate_EventChinese_EB");
		SW.EnterValue("MeetingsCorporate_EventName_EB", eventTitleCH);
		SW.EnterValue("MeetingsWedding_EventDesc_EB",eventDescCH);

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

		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoEN);

		SW.EnterValue("MeetingsWedding_PropName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsWedding_SoldOutMessage_EB", "Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsWedding_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");


		SW.Click("MeetingsCorporate_PropertyEspanish_EB");
		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoES);

		SW.Click("MeetingsCorporate_PropertyFrench_EB");
		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoFR);

		SW.Click("MeetingsCorporate_PropertyGerman_EB");
		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoGE);

		SW.Click("MeetingsCorporate_PropertyJapanese_EB");
		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoJP);

		SW.Click("MeetingsCorporate_PropertyItalian_EB");
		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoIT);

		SW.Click("MeetingsCorporate_PropertyChinese_EB");
		SW.EnterValue("MeetingsCorporate_AddInfo_EB", addInfoCH);

		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsWedding_SaveWebsite_BT");	
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="CorporateWebsiteALLlanguagesforEventAddInfo")

	public void BookingLandingPageALLlanguagesValidateEventTitleAddInfo(){

		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyGerman_BT");

		if(SW.CompareText("EventDescription_DD", eventDescGE, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoGE, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleGE, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyEnglish_BT");

		if(SW.CompareText("EventDescription_DD", eventDescEN, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoEN, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleEN, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyEspanish_BT");

		if(SW.CompareText("EventDescription_DD", eventDescES, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoES, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleES, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyFrance_BT");

		if(SW.CompareText("EventDescription_DD", eventDescFR, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoFR, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleFR, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyItaly_BT");

		if(SW.CompareText("EventDescription_DD", eventDescIT, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoIT, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleIT, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
			
		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyJapanese_BT");

		if(SW.CompareText("EventDescription_DD", eventDescJP, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoJP, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleJP, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
		
		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBookingLandingPage_PropertyChinese_BT");

		if(SW.CompareText("EventDescription_DD", eventDescCH, SW.GetText("MeetingsCorporate_BookPageEvent_DD")) &&
		SW.CompareText("EventAddInformation_DD", addInfoCH, SW.GetText("MeetingsCorporate_BookPageAddInfo_DD")) &&
		SW.CompareText("EventTitle_DD", eventTitleCH, SW.GetText("MeetingsCorporate_BookPageEventTitle_DD")))
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
