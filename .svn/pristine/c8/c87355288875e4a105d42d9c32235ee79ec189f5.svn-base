/* Purpose		: //TODO
 * TestCase Name: Create Corporate Website Email this Info, Modify Corporate Website email this info
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

public class MEETINGS_REG33_CorporateWebsiteEmailthisInfo {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);
	String Meetingname = SW.RandomString(5);
	String Meetingname1 = SW.RandomString(5);
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void CorporateWebsiteEmailThisInfo(){
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
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsCorporate_EventName_EB",  Meetingname);
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_Contact_EB", SW.RandomString(5));
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsCorporate_Attendee_BT", "ON");
		SW.CheckBox("MeetingsCorporate_VIP_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		try{
			int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
			int Random = SW.RandomNumber(0, Dropdownsize-1);
			SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

			int Dropdownsize1 = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType1_DD");
			int Random1 = SW.RandomNumber(0, Dropdownsize1-1);
			SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType1_DD",(Random1));
		}
		catch(Exception e){
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
		SW.TakeScreenshot("Published Corporate Site");
		String AttendeeText = SW.GetText("MeetingsCorporate_AttendeeLink_BT");	
		try{
			String IDIndex = "id=";
			int StartingIndex = AttendeeText.indexOf(IDIndex);
			int EndIndex  = AttendeeText.indexOf("&");
			Environment.loger.log(Level.INFO, "Attendee:"+AttendeeText);
			Number = AttendeeText.substring(StartingIndex+IDIndex.length(), EndIndex);
			Environment.loger.log(Level.INFO, "ID:"+Number);
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
	
	@Test(priority=2,dependsOnMethods="CorporateWebsiteEmailThisInfo")
	public void ModifyCorporateWebsiteEmailThisInfo(){
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsCorporate_SwitchTopFrame_FR");
		SW.Click("MeetingsCorporate_HomePage_BT");

		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsCorporate_SwitchMainFrame_FR");
		SW.Click("MeetingsCorporate_ModifyWebsite_BT");
		SW.EnterValue("MeetingsCorporate_PID_EB", SW.TestData("SGP_PID"));
		SW.EnterValue("MeetingsCorporate_MeetingID_EB", Number);
		SW.Click("MeetingsCorporate_Search_BT");
		SW.ClickByJavascript("MeetingsCorporate_MeetingName_BT");
		SW.ClickByJavascript("MeetingsCorporate_EventEdit_BT");
		
		SW.EnterValue("MeetingsCorporate_MeetingName_EB", Meetingname1);
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsCorporate_Staff_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		try{
			int Dropdownsize2 = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType2_DD");
			int Random2 = SW.RandomNumber(0, Dropdownsize2-1);
			SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType2_DD",(Random2));
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}

		SW.Click("MeetingsCorporate_MoreAssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsCorporate_ModifyPropName_EB", "Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_ModifyPropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_ModifySaveWebsite_BT");
		
		SW.Click("MeetingsCorporateWebsite_Email_LK");	
		SW.SwitchToWindow(2);
		SW.EnterValue("MeetingsCorporateWebsite_EmailWindow_ST", "shalini.jaikumar@accenture.com");
		SW.TakeScreenshot("Modify Email this info");
		SW.Click("MeetingsCorporateWebsite_EmailSend_LK");	
		SW.Click("MeetingsCorporateWebsite_EmailSendClose_LK");
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("MeetingsCorporate_SwitchMainFrame_FR");
		
		if(SW.CompareText("ModifyCorporateWebsiteEmailInfo_DT", "You have successfully published "+Meetingname1+" website", SW.GetText("MeetingsCorporate_PublishSite_ST")))
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