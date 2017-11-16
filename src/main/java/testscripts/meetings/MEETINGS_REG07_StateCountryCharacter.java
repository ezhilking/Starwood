/* Purpose		: //TODO
 * TestCase Name: State, country, speacial charachter of first name validation in booking
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

public class MEETINGS_REG07_StateCountryCharacter {

	CHANNELS SW = new CHANNELS();
	String firstName = "fwo@#hsal";
	String lastName = SW.RandomString(5);
	String address = SW.RandomString(5);
	String city = SW.RandomString(5);
	String cnfcNumber;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)	

	public void BookingCountryandState(){

		String url = SW.TestData("URL");
		SW.LaunchBrowser(url);
		SW.Click("MeetingsBooking_ClickonBook_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");
		SW.DropDown_SelectByValue("MeetingsBooking_Title_DD", "Mr.");
		SW.EnterValue("MeetingsBooking_FirstName_EB", firstName);
		SW.EnterValue("MeetingsBooking_LastName_EB", lastName);
		SW.EnterValue("MeetingsBooking_Address_EB", address);
		SW.EnterValue("MeetingsBooking_city_EB", city);
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

		try{
			String[] FirstSpecialCharacter = SW.GetText("MeetingsBooking_NameValue_DT").split("  ");
			SW.CompareText("MeetingsBooking_FirstNameValidation_DT", "Mr. "+firstName, FirstSpecialCharacter[0]);
			SW.CompareText("MeetingsBooking_LastNameValidation_DT", lastName, FirstSpecialCharacter[1]);
			String[] StateCountry = SW.GetText("MeetingsBooking_AddressValue_DT").split("\n");
			SW.CompareText("MeetingsBooking_Address_DT", address, StateCountry[0]);
			String[] SplitedValue = StateCountry[1].split(" ");
			if(SW.CompareText("MeetingsBooking_City_DT", city, SplitedValue[0]) &&
			SW.CompareText("MeetingsBooking_State_DT", "PA", SplitedValue[1]) &&
			SW.CompareText("MeetingsBooking_Country_DT", "US", SplitedValue[2]))
			{
				Reporter.WriteLog(Level.ERROR, "PASS");
			}
			else
			{
				Reporter.WriteLog(Level.ERROR, "FAIL");
			}

			String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
			String Isindex = "is";
			int StartingIndex = ConfNumber.indexOf(Isindex);
			int EndIndex  = ConfNumber.indexOf(".");
			cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
			Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="BookingCountryandState")

	public void CancelBookingValidationCountryandState(){
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

