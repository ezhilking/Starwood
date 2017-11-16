/* Purpose		: //TODO
 * TestCase Name: Meeting end date selected after 10 years from present to validate corporate, wedding, social
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

public class MEETINGS_REG08_SocialWeddingEndDate10Years {
	CHANNELS SW = new CHANNELS();
	String errorMesaage = "Please make sure that the meeting end date is valid.";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void WeddingWebsitetoValidateEndDateAfter10Years(){
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
		SW.EnterValue("MeetingsWedding_meetingEndDate_EB", "2030-12-30");
		SW.EnterValue("MeetingsWedding_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_EventDate_EB", "2018-12-30");
		SW.EnterValue("MeetingsWedding_EventEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.ClickAndProceed("MeetingsCorporate_NextButton_BT");
		String mesaage = SW.GetAlertText();
		SW.HandleAlert(true);		
		if(SW.CompareText("WeddingWebsiteAfter10Year",errorMesaage, mesaage))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}
	
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2)
	public void SocialWebsitetoValidateEndDateAfter10Years(){

		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsWedding_SwitchTopFrame_FR");
		SW.Click("MeetingsWedding_HomePage_BT");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsWedding_SwitchMainFrame_FR");
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
		SW.EnterValue("MeetingsWedding_meetingEndDate_EB", "2030-12-30");
		SW.EnterValue("MeetingsSocial_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsSocial_EventEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.ClickAndProceed("MeetingsCorporate_NextButton_BT");
		String mesaage = SW.GetAlertText();
		SW.HandleAlert(true);		
		if(SW.CompareText("SocialWebsiteAfter10Year",errorMesaage, mesaage))		
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}
	
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=3)
	public void CorporateWebsitetoValidateEndDateAfter10Years(){

		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsWedding_SwitchTopFrame_FR");
		SW.Click("MeetingsWedding_HomePage_BT");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("MeetingsWedding_SwitchMainFrame_FR");
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
		SW.EnterValue("MeetingsCorporate_EventName_EB",  SW.RandomString(5));
		SW.EnterValue("MeetingsWedding_meetingEndDate_EB", "2030-12-30");
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.ClickAndProceed("MeetingsCorporate_NextButton_BT");		
		String mesaage = SW.GetAlertText();
		SW.HandleAlert(true);		
		if(SW.CompareText("CorporateWebsiteAfter10Year",errorMesaage, mesaage))		
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