/* Purpose		: //TODO
 * TestCase Name: SPGBooking & Remove SPG number from third step & validate the error
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

public class MEETINGS_REG14_SPGBookingAndRemoveSPG {
	CHANNELS SW = new CHANNELS();
	String cnfcNumber;
	String errorMessage = "SPG membership number is not found. Please try again with a valid SPG user or proceed with booking without using SPG";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)

	public void SPGBooking(){
		String url = SW.TestData("URL");
		SW.LaunchBrowser(url);	
		SW.Click("MeetingsBooking_ClickonBook_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");
		SW.EnterValue("MeetingsBookingSPG_Username_EB", SW.TestData("SGP_SPGUserName"));
		SW.EnterValue("MeetingsBookingSPG_Password_EB", SW.TestData("SGP_SPGPassword"));
		SW.Click("MeetingsBookingSPG_LogIN_BT");
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
			String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
			String Isindex = "is";
			int StartingIndex = ConfNumber.indexOf(Isindex);
			int EndIndex  = ConfNumber.indexOf(".");
			cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
			Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
			
			if(SW.CompareText("SPGBookingConfirnmationNumberMessage_DT", "Your confirmation number is "+cnfcNumber+".", SW.GetText("MeetingsBooking_confNo_BT")))
			{
				Reporter.WriteLog(Level.ERROR, "PASS");
			}
			else
			{
				Reporter.WriteLog(Level.ERROR, "FAIL");
			}

			String[] StateCountry = SW.GetText("MeetingsBooking_AddressValue_DT").split("\n");
			String[] SplitedValue = StateCountry[1].split(" ");
			if(SW.CompareText("MeetingsBooking_Country_DT", "IN", SplitedValue[2]))
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


	@Test(priority=2,dependsOnMethods="SPGBooking")

	public void SPGCancelBooking(){

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", SW.TestData("SGP_SPGlastName"));
		SW.Click("MeetingsLocate_Submit_BT");
		SW.ClickAndProceed("MeetingsCancel_CancelButton_BT");
		SW.HandleAlert(true);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=3,dependsOnMethods="SPGCancelBooking")

	public void RemoveSPGBooking(){

		String url = SW.TestData("URL");
		SW.LaunchBrowser(url);				
		SW.Click("MeetingsBooking_ClickonBook_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");
		SW.EnterValue("MeetingsBookingSPG_Username_EB", SW.TestData("SGP_SPGUserName"));
		SW.EnterValue("MeetingsBookingSPG_Password_EB", SW.TestData("SGP_SPGPassword"));
		SW.Click("MeetingsBookingSPG_LogIN_BT");
		SW.DropDown_SelectByValue("MeetingsBooking_telephone_DD", "0");
		SW.EnterValue("MeetingsBooking_phn_EB", "9985632103");
		SW.DropDown_SelectByValue("MeetingsBooking_cardType_DD", "VI");
		SW.EnterValue("MeetingsBooking_cardNumber_DD", "4111111111111111");
		SW.DropDown_SelectByValue("MeetingsBooking_month_DD", "09");
		SW.DropDown_SelectByValue("MeetingsBooking_year_DD", "2020");
		SW.EnterValue("MeetingsBookingSPG_RemoveSPG_EB", "9856321478");
		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");
		SW.Click("MeetingsBooking_reviewReservation_BT");
		SW.Click("MeetingsBooking_confirmButton_BT");

		String errorMessageRemove = SW.GetText("MeetingsBookingSPG_SPGError_DD");
		System.out.println("userName = " +errorMessageRemove);
		if(SW.CompareText("RemoveSPGProvideInvalid_ErrorMessageValidation_DT", errorMessage, SW.GetText("MeetingsBookingSPG_SPGError_DD")))
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
