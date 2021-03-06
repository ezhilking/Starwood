/* Purpose		: //TODO
 * TestCase Name: Create Corporate Website, Modify Corporate Website, Booking Corporate, Modify Booking, Cancel Booking
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

public class MEETINGS_REG01_Corporate {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

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
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.Click("MeetingsCorporate_NextButton_BT");
		SW.EnterValue("MeetingsCorporate_EventName_EB",  SW.RandomString(5));
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
			SW.WriteToTestData("URLCorporate", AttendeeText);
			SW.WriteToTestData("CorporateMeetingID", Number);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}


	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=2,dependsOnMethods="CreateCorporateWebsite")
	public void ModifyCorporateWebsite(){
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

		String Meetingname = SW.RandomString(5);

		SW.EnterValue("MeetingsCorporate_MeetingName_EB", Meetingname);
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

		if(SW.CompareText("PublishModifyCorporateWebsite_DT", "You have successfully published "+Meetingname+" website", SW.GetText("MeetingsCorporate_PublishSite_ST")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}	

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=3,dependsOnMethods="ModifyCorporateWebsite")

	public void BookingCorporateWebsite(){
		SW.Click("MeetingsCorporateCreate_ClickonLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("MeetingsBooking_ClickonBook_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");
		SW.DropDown_SelectByValue("MeetingsBooking_Title_DD", "Mr.");
		SW.EnterValue("MeetingsBooking_FirstName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsBooking_LastName_EB", lastName);
		SW.EnterValue("MeetingsBooking_Address_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsBooking_city_EB", SW.RandomString(5));
		SW.DropDown_SelectByValue("MeetingsBooking_state_DD", "PA");
		SW.EnterValue("MeetingsBooking_zipCode_EB", "98562");
		SW.DropDown_SelectByValue("MeetingsBooking_telephone_DD", "0");
		SW.EnterValue("MeetingsBooking_phn_EB", "9985632103");
		SW.DropDown_SelectByValue("MeetingsBooking_cardType_DD", "VI");
		SW.EnterValue("MeetingsBooking_cardNumber_DD", "4111111111111111");
		SW.DropDown_SelectByValue("MeetingsBooking_month_DD", "09");
		SW.DropDown_SelectByValue("MeetingsBooking_year_DD", "2020");
		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");
		SW.Click("MeetingsBooking_reviewReservation_BT");
		SW.Click("MeetingsBooking_confirmButton_BT");

		try{String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
		String Isindex = "is";
		int StartingIndex = ConfNumber.indexOf(Isindex);
		int EndIndex  = ConfNumber.indexOf(".");
		cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
		Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
		if(SW.CompareText("BookingConfirnmationNumberMessage_DT", "Your confirmation number is "+cnfcNumber+".", SW.GetText("MeetingsBooking_confNo_BT")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=4,dependsOnMethods="BookingCorporateWebsite")

	public void BookingCorporateModify(){

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.Click("MeetingsBooking_ModifyButton_BT");
		SW.EnterValue("MeetingsBooking_ModifyFirstName_EB", SW.RandomString(5));
		SW.Click("MeetingsBooking_ModifycreditCard_BT");
		SW.DropDown_SelectByValue("MeetingsBooking_ModifycardType_DD", "MC");
		SW.EnterValue("MeetingsBooking_ModifycardNumber_DD", "5555555555554444");
		SW.DropDown_SelectByValue("MeetingsBooking_Modifymonth_DD", "05");
		SW.DropDown_SelectByValue("MeetingsBooking_Modifyyear_DD", "2020");
		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");
		SW.Click("MeetingsBooking_ModifyreviewReservation_BT");
		SW.Click("MeetingsBooking_confirmButton_BT");

		try{
			String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
			String Isindex = "is";
			int StartingIndex = ConfNumber.indexOf(Isindex);
			int EndIndex  = ConfNumber.indexOf(".");
			cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
			Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
			if(SW.CompareText("ModifyConfirnmationNumberMessage_DT", "Your confirmation number is "+cnfcNumber+".", SW.GetText("MeetingsBooking_confNo_BT")))
			{
				Reporter.WriteLog(Level.ERROR, "PASS");
			}
			else
			{
				Reporter.WriteLog(Level.ERROR, "FAIL");
			}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@Test(priority=5,dependsOnMethods="BookingCorporateModify")

	public void CancelBookingCorporate(){
		String cancelNumber;

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.ClickAndProceed("MeetingsCancel_CancelButton_BT");
		SW.HandleAlert(true);
		try{
			String cancellationNumber = SW.GetText("MeetingsBooking_cancellationNumber_BT");	
			String Isindex = "is";
			int StartingIndex = cancellationNumber.indexOf(Isindex);
			int EndIndex  = cancellationNumber.indexOf(".");
			cancelNumber = cancellationNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
			Environment.loger.log(Level.INFO, "Cancellation Number:"+cancelNumber);
			if(SW.CompareText("CancellationNumberMessage_DT", "Your cancellation number is "+cancelNumber+".", SW.GetText("MeetingsBooking_cancellationNumber_BT")))
			{
				Reporter.WriteLog(Level.ERROR, "PASS");
			}
			else
			{
				Reporter.WriteLog(Level.ERROR, "FAIL");
			}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
		
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
