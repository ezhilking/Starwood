/* Purpose		: //TODO
 * TestCase Name: SPG create booking - Wedding Japanese
 * Created By	: Brij
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */

package testscripts.meetings;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;


public class MEETINGS_REG36_SPGCreateAndBookingWedding {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	int id = SW.RandomInteger(3);
	String id1 = SW.RandomString(3);

	int pwd = SW.RandomNumber(1, 2);
	String pwd1 = SW.RandomString(5);

	boolean IsPass = false;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)

	public void BookingWebsitenewSPGJapanese(){

		String url = SW.TestData("URLWedding");
		SW.LaunchBrowser(url);

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");
		SW.Click("MeetingsBooking_ChangeLanguage_BT1");
		SW.Click("MeetingsBooking_ClickonBookJP_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");

		SW.CheckBox("MeetingsCorWebsite_SPG_CB", "ON");
		SW.EnterValue("MeetingsCorWebsite_SPGmail_EB", lastName+"@gmail.com");

		SW.EnterValue("MeetingsBookingJapanese_FirstName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsBookingJapanese_LastName_EB", lastName);
		SW.EnterValue("MeetingsBookingJapanese_Address_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsBookingJapanese_city_EB", SW.RandomString(5));
		SW.DropDown_SelectByValue("MeetingsBooking_state_DD", "PA");
		SW.EnterValue("MeetingsBookingJapanese_zipCode_EB", "98562");
		SW.DropDown_SelectByValue("MeetingsBooking_telephone_DD", "0");
		SW.EnterValue("MeetingsBooking_phn_EB", "9985632103");
		SW.DropDown_SelectByValue("MeetingsBooking_cardType_DD", "VI");
		SW.EnterValue("MeetingsBooking_cardNumber_DD", "4111111111111111");
		SW.DropDown_SelectByValue("MeetingsBooking_month_DD", "09");
		SW.DropDown_SelectByValue("MeetingsBooking_year_DD", "2020");

		SW.EnterValue("MeetingsCorWebsite_SPGreMail_EB", lastName+"@gmail.com");

		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");
		SW.Click("MeetingsBooking_reviewReservation_BT");

		SW.EnterValue("MeetingsCorWebsite_SPGID_EB", id+id1+Keys.TAB);
		SW.EnterValue("MeetingsCorWebsite_SPGPwd_EB", pwd+pwd1+"^"+Keys.TAB);
		SW.EnterValue("MeetingsCorWebsite_SPGrePwd_EB", pwd+pwd1+"^");

		SW.Click("MeetingsBooking_confirmButton_BT");

		String spgNumber	= SW.GetText("MeetingsWebsiteBooking_SPGnumber_ST");
		System.out.println("SPG Number:" +spgNumber);

		if(spgNumber.contains("xxx"))
		{
			try{String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
			String Isindex = "は";
			int StartingIndex = ConfNumber.indexOf(Isindex);
			int EndIndex  = ConfNumber.indexOf("。");
			cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), (EndIndex-2)).trim();
			Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
			if(SW.CompareText("JapaneseBookingConfirnmationNumberMessage_DT", "お客様のご予約確認番号は"+cnfcNumber+"です。", SW.GetText("MeetingsBooking_confNo_BT")))
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
			Reporter.Write("SpgNumberBooking_ST", "SPG number generated", "SPG number generated", "PASS");
		}
		else
		{
			Reporter.Write("SpgNumberBooking_ST", "SPG number not generated", "SPG number not generated", "FAIL");
		}
	}
	
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=1,dependsOnMethods="BookingWebsitenewSPGJapanese")

	public void CancelSPGBooking(){
		String cancelNumber;

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.ClickAndProceed("MeetingsCancel_CancelButton_BT");
		SW.HandleAlert(true);
		try{
			String cancellationNumber = SW.GetText("MeetingsBooking_cancellationNumber_BT");	
			String Isindex = "は";
			int StartingIndex = cancellationNumber.indexOf(Isindex);
			int EndIndex  = cancellationNumber.indexOf("。");
			cancelNumber = cancellationNumber.substring(StartingIndex+Isindex.length(), (EndIndex-2)).trim();
			Environment.loger.log(Level.INFO, "Cancellation Number:"+cancelNumber);
			SW.TakeScreenshot("Japanese booking Cancel screenshot");
			if(SW.CompareText("JapaneseBookingCancellationNumberMessage_DT", "お客様のご予約取り消し番号は"+cancelNumber+"です。", SW.GetText("MeetingsBooking_confNo_BT")))
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