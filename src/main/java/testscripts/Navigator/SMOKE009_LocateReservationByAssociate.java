package testscripts.Navigator;

import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;

public class SMOKE009_LocateReservationByAssociate {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	
	String ReservationNumber ;
	@Test(priority=0)
	public void createReservationInSOAP(){
		String RequestXMLFile="ReservationQA3_saratoga.xml";
		//String EndPointURL = "http://booking.qa3.nssd.star:9245/BookingWeb/services/BookingPort";
		
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);
		
		String FutureArrivalDate= SW.GetTimeStamp("yyyy-MM-dd");
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 1, Calendar.DATE);
		
		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		// Process the SOAP Response
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Created Successfully");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			SW.WriteToTestData("ConfNum_createReservation", ReservationNumber);
		}else{
			System.out.println("Error in SOAP Response see response file for more details");
			Environment.loger.log(Level.ERROR, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);
		}		
	}
	
	@Test(priority=1)
	public void LocateReservationAssociateLastReservationBooked()  {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservationSearchPage_Associate_LK");
		SW.DropDown_SelectByText("NavigatorReservationSearchPage_AssociateBookingDate_DD", "Last Reservation Booked");
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		//Create a reservation and then verification point has to be implemented
		String Actual_reservationNum = SW.GetText("NavigatorReservationSearchPage_CardConfirm_LK");
		SW.CompareText("ReservationNum_comp-", ReservationNumber, Actual_reservationNum);
		
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
