/* Purpose		: //TODO
 * TestCase Name: Create Social Website, Modify Social Website
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

public class MEETINGS_REG03_Social {
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
	public void CreateSocialWebsite(){
		SW.MeetingsLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.Click("MeetingsCorporate_CreateWebsite_BT");
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD", SW.TestData("SGP_PID"));
		SW.SelectRadioButton("MeetingsCorporate_radioButton_BN");
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_GroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.DropDown_SelectByIndex("MeetingsSocial_MeetingType_DD", 2);
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsSocial_EventName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_Contact_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_EventEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsSocial_Attendee_BT", "ON");
		SW.CheckBox("MeetingsSocial_VIP_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
		int Random = SW.RandomNumber(0, Dropdownsize-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

		int Dropdownsize1 = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType1_DD");
		int Random1 = SW.RandomNumber(0, Dropdownsize1-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType1_DD",(Random1));

		SW.Click("MeetingsSocial_AssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsSocial_PropName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsSocial_SoldOutMessage_EB", "Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsSocial_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
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
			SW.WriteToTestData("URLSocial", AttendeeText);
			SW.WriteToTestData("SocialMeetingID", Number);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}	
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=2,dependsOnMethods="CreateSocialWebsite")
	public void ModifySocialWebsite(){
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsSocial_SwitchTopFrame_FR");
		SW.Click("MeetingsSocial_HomePage_BT");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsSocial_SwitchMainFrame_FR");
		SW.Click("MeetingsSocial_ModifyWebsite_BT");
		SW.EnterValue("MeetingsSocial_PID_EB", SW.TestData("SGP_PID"));
		SW.EnterValue("MeetingsSocial_MeetingID_EB", Number);
		SW.Click("MeetingsSocial_Search_BT");
		SW.Click("MeetingsSocial_MeetingName_BT");
		SW.Click("MeetingsSocial_EventEdit_BT");
		String Meetingname = SW.RandomString(5);
		SW.EnterValue("MeetingsSocial_MeetingName_EB", Meetingname);
		SW.EnterValue("MeetingsSocial_EventVenue_EB", SW.RandomString(5));
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsSocial_Staff_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		int Dropdownsize2 = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType2_DD");
		int Random2 = SW.RandomNumber(0, Dropdownsize2-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType2_DD",(Random2));

		SW.ClickAndProceed("MeetingsSocial_MoreAssignRoomType_BT");
		SW.ClickAndProceed("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsSocial_ModifyPropName_EB", "Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_ModifyPropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsSocial_ModifySaveWebsite_BT");

		if(SW.CompareText("PublishModifySocialWebsite_DT", "You have successfully published "+Meetingname+" website", SW.GetText("MeetingsSocial_PublishSite_ST")))
		{
			Reporter.WriteLog(Level.ERROR, "Pass");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "Fail");
		}
	}
	
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}	
