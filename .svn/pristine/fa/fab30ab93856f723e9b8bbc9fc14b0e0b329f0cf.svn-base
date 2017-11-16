/* Purpose		: //TODO
 * TestCase Name: Create Wedding Website, Modify Wedding Website
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

public class MEETINGS_REG02_Wedding {
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
	public void CreateWeddingWebsite(){
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
		SW.CheckBox("MeetingsWedding_VIP_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
		int Random = SW.RandomNumber(0, Dropdownsize-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

		int Dropdownsize1 = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType1_DD");
		int Random1 = SW.RandomNumber(0, Dropdownsize1-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType1_DD",(Random1));

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
			SW.WriteToTestData("URLWedding", AttendeeText);
			SW.WriteToTestData("WeddingMeetingID", Number);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=2,dependsOnMethods="CreateWeddingWebsite")
	public void ModifyWeddingWebsite(){
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
		SW.Click("MeetingsWedding_EventEdit_BT");
		String Meetingname = SW.RandomString(5);
		SW.EnterValue("MeetingsWedding_MeetingName_EB", Meetingname);
		SW.EnterValue("MeetingsWedding_EventVenue_EB", SW.RandomString(5));
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsWedding_Staff_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		try{
			int Dropdownsize2 = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType2_DD");
			int Random2 = SW.RandomNumber(0, Dropdownsize2-1);
			SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType2_DD",(Random2));
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}

		SW.ClickAndProceed("MeetingsWedding_MoreAssignRoomType_BT");
		SW.ClickAndProceed("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsWedding_ModifyPropName_EB", "Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_ModifyPropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsWedding_ModifySaveWebsite_BT");

		if(SW.CompareText("PublishModifyWeddingWebsite_DT", "You have successfully published "+Meetingname+" website", SW.GetText("MeetingsWedding_PublishSite_ST")))
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
