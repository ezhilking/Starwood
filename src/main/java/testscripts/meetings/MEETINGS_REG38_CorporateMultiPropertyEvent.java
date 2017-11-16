/* Purpose		: //TODO
 * TestCase Name: Create multi property website with pdf, image and link & validate for both and booking landing page 
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

public class MEETINGS_REG38_CorporateMultiPropertyEvent {
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
	public void CreateCorporateWebsite(){

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
		SW.Click("MeetingsCorWebsite_GroupBlocks_LK");
		SW.DropDown_SelectByValue("MeetingsCorporate_SelectPID_DD", SW.TestData("SGP_SecondPID"));
		SW.EnterValue("MeetingsCorporate_GroupBlockID_EB", SW.TestData("SGP_SecondGroupBlockID"));
		SW.Click("MeetingsCorporate_FindGbid_BT");
		SW.CheckBox("MeetingsCorporate_SelectgroupBlock_CB", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.DropDown_SelectByIndex("MeetingsCorporate_MeetingType_DD", 1);
		SW.DropDown_SelectByValue("MeetingsCorporate_PrimaryLanguage_DD", "en:English");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage_BT", "ON");
		SW.CheckBox("MeetingsCorporate_SecondaryLanguage1_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsCorWebsite_MutliEvent_CB", "ON");
		SW.EnterValue("MeetingsCorporate_EventName_EB",  SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_EventVenue_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorWebsite_MutliEventFN_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorWebsite_MutliEventFSD_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsCorWebsite_MutliEventFED_EB", SW.TestData("SGP_CheckOut"));
		SW.EnterValue("MeetingsCorWebsite_MutliEventSN_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsCorWebsite_MutliEventSSD_EB", SW.TestData("SGP_SecondCheckIn"));
		SW.EnterValue("MeetingsCorWebsite_MutliEventSED_EB", SW.TestData("SGP_SecondCheckOut"));
		SW.EnterValue("MeetingsCorporate_Contact_EB", SW.RandomString(5));
		SW.Click("MeetingsCorporate_DownloadUpload_LK");
		SW.SwitchToWindow(2);
		SW.Click("MeetingsCorporate_DownloadUploadClick_LK");
		SW.EnterValue("MeetingsCorporate_TextUploadClick_ST", "StarGroups");
		SW.FileUpload("MeetingsCorporate_PDFUploadClick_LK", "StarGroups.pdf");
		SW.ClickAndProceed("MeetingsCorporate_PDFUploadClick_BT");
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("Meetings_SwitchFrame_FR");
		SW.Click("MeetingsCorporate_ImageUpload_LK");
		SW.SwitchToWindow(2);
		SW.Click("MeetingsCorporate_DownloadUploadClick_LK");
		SW.FileUpload("MeetingsCorporate_ImageUploadClick_LK", "Rose.jpg");
		SW.ClickAndProceed("MeetingsCorporate_ImageUploadClick_BT");
		SW.Click("MeetingsCorporate_UploadClose_LK");
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("Meetings_SwitchFrame_FR");
		SW.EnterValue("MeetingsCorporate_LinkText_LK", "Google");
		SW.EnterValue("MeetingsCorporate_LinkURL_LK", "https://www.google.com/");
		SW.Click("MeetingsCorporate_EventLinkAdd_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.CheckBox("MeetingsCorporate_Attendee_BT", "ON");
		SW.Click("MeetingsCorporate_NextButton_BT");

		int Dropdownsize = SW.DropDown_GetSize("MeetingsCorporate_AttendeeType_DD");
		int Random = SW.RandomNumber(0, Dropdownsize-1);
		SW.DropDown_SelectByIndex("MeetingsCorporate_AttendeeType_DD",(Random));

		SW.Click("MeetingsCorporate_AssignRoomType_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");	
		SW.EnterValue("MeetingsCorporate_PropName_EB","Contactname"+SW.RandomString(5));
		SW.EnterValue("MeetingsCorporate_PropEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.EnterValue("MeetingsCorporate_SoldOutMessage_EB","Sold out"+SW.RandomString(6));
		SW.EnterValue("MeetingsCorporate_SoldOutEmail_BT", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsCorWebsite_MutliPropertyEmail_EB", SW.RandomString(5)+"@yahoo.com");
		SW.Click("MeetingsCorporate_SaveWebsite_BT");	

		String AttendeeText = SW.GetText("MeetingsCorporate_AttendeeLink_BT");	
		try{
			String IDIndex = "id=";
			int StartingIndex = AttendeeText.indexOf(IDIndex);
			int EndIndex  = AttendeeText.indexOf("&");
			Environment.loger.log(Level.INFO, "Attendee:"+AttendeeText);
			Number = AttendeeText.substring(StartingIndex+IDIndex.length(), EndIndex);
			Environment.loger.log(Level.INFO, "ID:"+Number);
			//			SW.WriteToTestData("CorporateImageURL", AttendeeText);
			//			SW.WriteToTestData("CorporateImageMeetingID", Number);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="CreateCorporateWebsite")

	public void BookingCorporateWebsite(){
		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);	
		String url = SW.GetCurrentURL();
		if(SW.GetAttributeValue("MeetingsCorporate_Image_LK", "src").endsWith(".jpg")){
			Reporter.Write("MeetingsWedding_Validate_ST_", "Rose image is present", "Rose image is present", "PASS");
		}else{
			Reporter.Write("MeetingsWedding_Validate_ST_", "Rose image is not present", "Rose image is not present", "FAIL");
		}
		SW.Click("MeetingsCorporate_Link_LK");
		SW.SwitchToWindow(3);	
		if(SW.ObjectExists("MeetingsCorporate_Link_ST")){
			Environment.loger.log(Level.ERROR, "Pass: Google link is present");
			Reporter.Write("MeetingsCorporateLink_Validate_ST", "Google link is present", "Google link is present", "PASS");
			Reporter.WriteLog(Level.ERROR, "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Fail: Google link is not present");	
			Reporter.Write("MeetingsCorporateLink_Validate_ST", "Google link is not present", "Google link is not present", "FAIL");
		}		
		SW.NavigateTo(url);
		SW.Click("MeetingsCorporate_Page_ST");
		SW.SwitchToWindow(3);
		String pdfText = SW.GetText("MeetingsCorporate_pdfPage_ST");
		System.out.println(pdfText);
		if(SW.CompareText("ValidationCorporatePDF_ST", "HISTORICAL PICKUP", pdfText))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}	
		SW.NavigateTo(url);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=3,dependsOnMethods="BookingCorporateWebsite")

	public void FirstProperty(){

		String url = SW.GetCurrentURL();	
		SW.Click("MeetingsCorBooking_MultiProFirst_BT");
		SW.Click("MeetingsCorBooking_MultiEveFirstGo_BT");
		if(SW.GetAttributeValue("MeetingsCorporate_Image_LK", "src").endsWith(".jpg")){
			Reporter.Write("MeetingsWedding_Validate_ST_", "Rose image is present", "Rose image is present", "PASS");
		}else{
			Reporter.Write("MeetingsWedding_Validate_ST_", "Rose image is not present", "Rose image is not present", "FAIL");
		}

		SW.Click("MeetingsCorporate_Link_LK");
		SW.SwitchToWindow(3);	
		if(SW.ObjectExists("MeetingsCorporate_Link_ST")){
			Environment.loger.log(Level.ERROR, "Pass: Google link is present");
			Reporter.Write("MeetingsCorporateLink_Validate_ST", "Google link is present", "Google link is present", "PASS");
			Reporter.WriteLog(Level.ERROR, "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Fail: Google link is not present");	
			Reporter.Write("MeetingsCorporateLink_Validate_ST", "Google link is not present", "Google link is not present", "FAIL");
		}		
		SW.NavigateTo(url);
		SW.Click("MeetingsCorporate_Page_ST");
		SW.SwitchToWindow(3);
		String pdfText = SW.GetText("MeetingsCorporate_pdfPage_ST");
		System.out.println(pdfText);
		if(SW.CompareText("ValidationCorporatePDF_ST", "HISTORICAL PICKUP", pdfText))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.NavigateTo(url);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=4, dependsOnMethods="FirstProperty" )

	public void SecondProperty(){


		SW.Click("MeetingsCorBooking_MultiProSecond_BT");
		SW.Click("MeetingsCorBooking_MultiProSecondGo_BT");
		if(SW.GetAttributeValue("MeetingsCorporate_Image_LK", "src").endsWith(".jpg")){
			Reporter.Write("MeetingsWedding_Validate_ST_", "Rose image is present", "Rose image is present", "PASS");
		}else{
			Reporter.Write("MeetingsWedding_Validate_ST_", "Rose image is not present", "Rose image is not present", "FAIL");
		}

		String url = SW.GetCurrentURL();

		SW.Click("MeetingsCorporate_Link_LK");
		SW.SwitchToWindow(3);	
		if(SW.ObjectExists("MeetingsCorporate_Link_ST")){
			Environment.loger.log(Level.ERROR, "Pass: Google link is present");
			Reporter.Write("MeetingsCorporateLink_Validate_ST", "Google link is present", "Google link is present", "PASS");
			Reporter.WriteLog(Level.ERROR, "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Fail: Google link is not present");	
			Reporter.Write("MeetingsCorporateLink_Validate_ST", "Google link is not present", "Google link is not present", "FAIL");
		}		
		SW.NavigateTo(url);
		SW.Click("MeetingsCorporate_Page_ST");
		SW.SwitchToWindow(3);
		String pdfText = SW.GetText("MeetingsCorporate_pdfPage_ST");
		System.out.println(pdfText);
		if(SW.CompareText("ValidationCorporatePDF_ST", "HISTORICAL PICKUP", pdfText))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.NavigateTo(url);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}