package testscripts.gcRegression;

import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;


public class GC_CreateReservationAndCancelFromSoap {

	CRM SW = new CRM();	
	String TestCaseName,ReservationNumber,LastName,PhoneNum,CancelationNumber;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
	}

	@Test(priority=1)
	public void CreateNewReservation() {

		String RequestXMLFile= "GC_REG32_DesignHotel_Booking RequestQA3.xml";
		String EndPointURL = "http://booking.qa3.nssd.star:9245/BookingWeb/services/BookingPort";
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Get the values for the cancellation from the reservation req
		LastName=SoapUtility.getXMLElementText(soapRequest, "primCnct", "lastName");
		PhoneNum=SoapUtility.getXMLElementText(soapRequest, "itineraryDTO", "primaryContactInfo");

		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);

		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, EndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			//SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
			//SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", ReservationNumber);
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			//SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			//SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateNewReservation")
	public void cancelReservation(){
		String RequestXMLFile= "SampleCancle_req_New_qa3.xml";
		String EndPointURL = "http://booking.qa3.nssd.star:9245/BookingWeb/services/BookingPort";
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "cancelBookingRequest", "identificationNumber", ReservationNumber);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "primCnct", "lastName", LastName);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "cancelBookingRequest", "primContactInfo", PhoneNum);
		SW.Wait(20);//Explicit wait before cancellation 
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, EndPointURL);

		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			CancelationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "cancelInfo");
			CancelationNumber=CancelationNumber.substring(0,9);
			
			Environment.loger.log(Level.INFO, "Reservation Cancelation Number= "+CancelationNumber);
			//SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
			//SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", ReservationNumber);
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			//SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			//SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}

	}
}
