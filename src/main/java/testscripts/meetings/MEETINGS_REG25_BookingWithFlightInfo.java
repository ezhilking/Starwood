package testscripts.meetings;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MEETINGS_REG25_BookingWithFlightInfo {

	CHANNELS SW = new CHANNELS();
	String firstName = SW.RandomString(5);
	String lastName = SW.RandomString(5);
	String cnfcNumber;

	@BeforeClass
	public void StartTest(){

		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)	

	public void BookingFlightAMPMvalidation(){

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

		// value emter
		SW.EnterValue("MeetingsBooking_FlightAirline_EB", "AB");
		SW.EnterValue("MeetingsBooking_FlightNumber_EB", "987");
		SW.DropDown_SelectByValue("MeetingsBooking_FlightArrTime_DD", "0300");
		SW.DropDown_SelectByValue("MeetingsBooking_FlightDepTime_DD", "0500");


		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");

		SW.Click("MeetingsBooking_reviewReservation_BT");

		String Flight = SW.GetText("MeetingsBooking_reviewReservationFlight_ST");
		String [] flight = Flight.split("  ");
		System.out.println(flight[0]);
		System.out.println(flight[1].trim());

		if(SW.CompareText("BookingreviewReservationValidation_DD",flight[0]+"  "+flight[1].trim(), Flight))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightAT = SW.GetText("MeetingsBooking_reviewReservationFlightArrTime_ST");
		if(SW.CompareText("BookingreviewReservationFlightArrTimeValidation_DD","Flight Arrival Time:03:00 AM", FlightAT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightDT = SW.GetText("MeetingsBooking_reviewReservationFlightDepTime_ST");
		if(SW.CompareText("BookingreviewReservationFlightDepTimeValidation_DD","Flight Departure Time:05:00 AM", FlightDT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
		

		SW.Click("MeetingsBooking_confirmButton_BT");


		String FlightCB = SW.GetText("MeetingsBooking_confirmReservationFlight_ST");
		String [] flightCB = FlightCB.split(" ");
		System.out.println(flightCB[0]);
		System.out.println(flightCB[1]);
		System.out.println(flightCB[2]);
		System.out.println(flightCB[3]);

		if(SW.CompareText("BookingconfirmReservationFlightValidation_DD",flightCB[0]+" "+flightCB[1]+" "+flightCB[2]+" "+flightCB[3], FlightCB))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightCBAT = SW.GetText("MeetingsBooking_confirmReservationFlightArrTime_ST");
		if(SW.CompareText("BookingconfirmReservationFlightArrTime_DD","Flight Arrival Time:03:00 AM", FlightCBAT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}


		String FlightCBDT = SW.GetText("MeetingsBooking_confirmReservationFlightDepTime_ST");
		if(SW.CompareText("BookingconfirmReservationFlightDepTimeValidation_DD","Flight Departure Time:05:00 AM", FlightCBDT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}


		try{String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
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

	@Test(priority=2,dependsOnMethods="BookingFlightAMPMvalidation")

	public void ModifyBookingFlightAMPMvalidation(){

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.Click("MeetingsBooking_ModifyButton_BT");

		SW.EnterValue("MeetingsModifyBooking_FlightAirline_EB", "BA");
		SW.EnterValue("MeetingsModifyBooking_FlightNumber_EB", "123");
		SW.DropDown_SelectByValue("MeetingsModifyBooking_FlightArrTime_DD", "1700");
		SW.DropDown_SelectByValue("MeetingsModifyBooking_FlightDepTime_DD", "2300");

		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");
		SW.Click("MeetingsBooking_ModifyreviewReservation_BT");

		String Flight = SW.GetText("MeetingsBooking_reviewReservationFlight_ST");
		String [] flight = Flight.split("  ");
		System.out.println(flight[0]);
		System.out.println(flight[1].trim());

		if(SW.CompareText("CeremonyBookingPageValidation_DD",flight[0]+"  "+flight[1].trim(), Flight))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightAT = SW.GetText("MeetingsBooking_reviewReservationFlightArrTime_ST");
		if(SW.CompareText("CeremonyBookingPageValidation_DD","Flight Arrival Time:05:00 PM", FlightAT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightDT = SW.GetText("MeetingsBooking_reviewReservationFlightDepTime_ST");
		if(SW.CompareText("CeremonyBookingPageValidation_DD","Flight Departure Time:11:00 PM", FlightDT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		SW.Click("MeetingsBooking_confirmButton_BT");

		String FlightCB = SW.GetText("MeetingsBooking_confirmReservationFlight_ST");
		String [] flightCB = FlightCB.split(" ");
		System.out.println(flightCB[0]);
		System.out.println(flightCB[1]);
		System.out.println(flightCB[2]);
		System.out.println(flightCB[3]);

		if(SW.CompareText("CeremonyBookingPageValidation_DD",flightCB[0]+" "+flightCB[1]+" "+flightCB[2]+" "+flightCB[3], FlightCB))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightCBAT = SW.GetText("MeetingsBooking_confirmReservationFlightArrTime_ST");
		if(SW.CompareText("CeremonyBookingPageValidation_DD","Flight Arrival Time:05:00 PM", FlightCBAT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		String FlightCBDT = SW.GetText("MeetingsBooking_confirmReservationFlightDepTime_ST");
		if(SW.CompareText("CeremonyBookingPageValidation_DD","Flight Departure Time:11:00 PM", FlightCBDT))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}

		try{
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

	@Test(priority=3,dependsOnMethods="ModifyBookingFlightAMPMvalidation")

	public void CancelBookingFlightAMPM(){

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.ClickAndProceed("MeetingsCancel_CancelButton_BT");
		SW.HandleAlert(true);
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}	
}