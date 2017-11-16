/* Purpose		: //TODO
 * TestCase Name: Credit Card booking & modify with special character & copy paste 
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

public class MEETINGS_REG05_CrediCardNumber {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)

	public void BookingCreditCard(){

		String url = SW.TestData("URL");
		SW.LaunchBrowser(url);

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
		SW.EnterValue("MeetingsBooking_hypenCardNumber_DD", SW.TestData("SGP_HypenCardNumber"));
		//	SW.EnterValue("MeetingsBooking_cardNumber_DD", "4111111111111111");
		SW.DropDown_SelectByValue("MeetingsBooking_month_DD", "09");
		SW.DropDown_SelectByValue("MeetingsBooking_year_DD", "2020");
		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");
		SW.Click("MeetingsBooking_reviewReservation_BT");
		SW.Click("MeetingsBooking_confirmButton_BT");
		try{
			String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
			String Isindex = "is";
			int StartingIndex = ConfNumber.indexOf(Isindex);
			int EndIndex  = ConfNumber.indexOf(".");
			cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
			Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
			if(SW.CompareText("ConfirnmationNumberMessage_DT", "Your confirmation number is "+cnfcNumber+".", SW.GetText("MeetingsBooking_confNo_BT")))
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


	@Test(priority=2,dependsOnMethods="BookingCreditCard")

	public void BookingCreditCardModify(){

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.Click("MeetingsBooking_ModifyButton_BT");
		SW.EnterValue("MeetingsBooking_ModifyFirstName_EB", SW.RandomString(5));
		SW.Click("MeetingsBooking_ModifycreditCard_BT");
		SW.DropDown_SelectByValue("MeetingsBooking_ModifycardType_DD", "MC");
		SW.EnterValue("MeetingsBooking_ModifyhypenCardNumber_DD", SW.TestData("SGP_ModifyHypenCardNumber"));
		//	SW.EnterValue("MeetingsBooking_ModifycardNumber_DD", "5555555555554444");
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


	@Test(priority=3,dependsOnMethods="BookingCreditCardModify")

	public void CancelBookingCreditCard(){
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