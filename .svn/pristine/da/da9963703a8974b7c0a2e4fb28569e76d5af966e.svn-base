/* Purpose		: //TODO
 * TestCase Name: Wedding create single then Multi property & ceremony validation in booking for multi property
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

public class MEETINGS_REG39_WeddingMultiProSingEvent {
	CHANNELS SW = new CHANNELS();

	String ceremonyStartDate = "2020-12-30";
	String ceremonyVenue = SW.RandomString(5);
	String Number;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void WeddingMultiWebsite(){
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

		int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
		int Random = SW.RandomNumber(0, Dropdownsize-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

		SW.Click("MeetingsWedding_AssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsWedding_PropName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsWedding_SoldOutMessage_EB", "Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsWedding_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsWedding_SaveWebsite_BT");	
		SW.TakeScreenshot("Publishing Wedding Website");

		String AttendeeText = SW.GetText("MeetingsCorporate_AttendeeLink_BT");	
		try{
			String IDIndex = "id=";
			int StartingIndex = AttendeeText.indexOf(IDIndex);
			int EndIndex  = AttendeeText.indexOf("&");
			Environment.loger.log(Level.INFO, "Attendee:"+AttendeeText);
			Number = AttendeeText.substring(StartingIndex+IDIndex.length(), EndIndex);
			Environment.loger.log(Level.INFO, "ID:"+Number);
			//			SW.WriteToTestData("URLWedding", AttendeeText);
			//			SW.WriteToTestData("WeddingMeetingID", Number);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=2, dependsOnMethods="WeddingMultiWebsite")	

	public void ModifyWeddingMultiProperty(){
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsWedding_SwitchTopFrame_FR");
		SW.Click("MeetingsWedding_HomePage_BT");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsWedding_SwitchMainFrame_FR");
		SW.Click("MeetingsWedding_ModifyWebsite_BT");
		SW.EnterValue("MeetingsWedding_PID_EB", SW.TestData("SGP_PID"));
		SW.EnterValue("MeetingsWedding_MeetingID_EB", Number);
		SW.Click("MeetingsWedding_Search_BT");
		SW.Click("MeetingsWedding_MeetingName_BT");		
		SW.ClickAndProceed("MeetingsWedding_EditGB_BT");
		SW.Click("MeetingsCorWebsite_GroupBlocks_LK");		
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD",SW.TestData("SGP_SecondPID"));
		SW.SelectRadioButton("MeetingsCorporate_radioButton_BN");
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_SecondGroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");		
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");		
		String eventVenue = SW.RandomString(5);
		SW.EnterValue("MeetingsWedding_MeetingName_EB", ceremonyVenue);
		SW.EnterValue("MeetingsWedding_EventDate_EB", ceremonyStartDate);
		SW.EnterValue("MeetingsWedding_EventVenue_EB", eventVenue);
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");

		int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
		int Random = SW.RandomNumber(0, Dropdownsize-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

		SW.ClickAndProceed("MeetingsWedding_MoreAssignRoomType_BT");
		SW.ClickAndProceed("MeetingsCorporate_NextButton_BT");			
		SW.EnterValue("MeetingsWedding_ModifyPropName_EB", "Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_ModifyPropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsCorWebsite_MutliPropertyEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsWedding_ModifySaveWebsite_BT");

		if(SW.CompareText("PublishModifyWeddingWebsite_DT", "You have successfully published "+ceremonyVenue+" website", SW.GetText("MeetingsWedding_PublishSite_ST")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=3, dependsOnMethods="ModifyWeddingMultiProperty")

	public void WeddingMultiPropCeremony(){
		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);

		String[] Ceremony = SW.GetText("MeetingsWedding_BookingLandingCeremony_DD").split("\n");
		System.out.println(Ceremony[0]);
		System.out.println(Ceremony[1]);

		String[] CeremonyValue = Ceremony[1].split(",");
		System.out.println(CeremonyValue[0]);
		System.out.println(CeremonyValue[1]);
		System.out.println(CeremonyValue[2]);

		if(SW.CompareText("WeddingMultiPropertyCeremonyPageValidation_DD","Ceremony:"+"\n"+"December 30"+"\n "+"2020"+"\n "+"12:00 AM", Ceremony[0]+"\n"+CeremonyValue[0].trim()+"\n "+CeremonyValue[1].trim()+"\n "+CeremonyValue[2].trim()))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.MoveToObject("MeetingsSocial_ChooseHotel_BT");
		SW.Click("MeetingsWedding_ChooseHotelFH_BT");

		if(SW.CompareText("WeddingFirstPropertyCeremonyPageValidation_DD","Ceremony:"+"\n"+"December 30"+"\n "+"2020"+"\n "+"12:00 AM", Ceremony[0]+"\n"+CeremonyValue[0].trim()+"\n "+CeremonyValue[1].trim()+"\n "+CeremonyValue[2].trim()))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.MoveToObject("MeetingsSocial_ChooseHotel_BT");
		SW.Click("MeetingsWedding_ChooseHotelSH_BT");

		if(SW.CompareText("WeddingSecondPropertyCeremonyPageValidation_DD","Ceremony:"+"\n"+"December 30"+"\n "+"2020"+"\n "+"12:00 AM", Ceremony[0]+"\n"+CeremonyValue[0].trim()+"\n "+CeremonyValue[1].trim()+"\n "+CeremonyValue[2].trim()))
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