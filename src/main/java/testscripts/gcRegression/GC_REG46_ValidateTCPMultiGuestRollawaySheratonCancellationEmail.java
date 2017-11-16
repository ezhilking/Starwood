package testscripts.gcRegression;
/** Purpose		: Validate  TCP Multi-Guest with rollaway Sheraton Cancellation email in Polish language
 * TestCase Name: Validate  TCP Multi-Guest with rollaway Sheraton Cancellation email in Polish language
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;
public class GC_REG46_ValidateTCPMultiGuestRollawaySheratonCancellationEmail {

	CRM SW = new CRM();	
	String ReservationNumber,LastName,PhoneNum,CancelationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;
	String RequestXMLFileCancel;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG46_SheratonMultiguestRollaway_1047_QA3.xml";
			RequestXMLFileCancel="SampleCancle_req_New_qa3.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG46_QA2_SheratonMultiguestRollaway_756.xml";
			RequestXMLFileCancel="SampleCancle_req_New_qa2.xml";
			
		}
	}

	@Test(priority=1)
	public void CreateReservationForProperty(){

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		LastName=SoapUtility.getXMLElementText(soapRequest, "primCnct", "lastName");
		PhoneNum=SoapUtility.getXMLElementText(soapRequest, "BinderDTO", "primContactInfo");
		//Modify the SOAP Request as per the requirement 
		
		//Modify the transaction ID
		try {
			String TransactionID=SoapUtility.getXMLElementText(soapRequest,"ns3:RequestContextBean", "ns2:RequestTransactionID");
			String NewTransactionID=TransactionID.substring(0, 25)+SW.GetTimeStamp("yyyyMMddhhmmss");
			SOAPMessage newSoapMessage=SoapUtility.setXMLElementText(soapRequest, "ns3:RequestContextBean", "ns2:RequestTransactionID",NewTransactionID);
			// Always keep 4 days gap in arrival and departure dates because initial XML has 4 day difference 
			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE); 
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 17, Calendar.DATE);

			//Get Arrival Date from Request 
			String actualArrivalDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "arrivalDate");
			String actualArrivalTimeStamp=actualArrivalDateStamp.substring(10, actualArrivalDateStamp.length());

			//Get DepartureDate from Request
			String actualDepartureDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate");
			String actualDepartureTimeStamp=actualDepartureDateStamp.substring(10, actualDepartureDateStamp.length());

			String NewArrivalTimeStamp=FutureArrivalDate+actualArrivalTimeStamp;
			String NewDepartureTimeStamp=FutureDepartureDate+actualDepartureTimeStamp;

			//Set Arrival dates other than Rate DTO elements 
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "arrivalDate", NewArrivalTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "startTime", NewArrivalTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "startTime", NewArrivalTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "RateDTO", "startDate", NewArrivalTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "RateDTO", "endDate", NewArrivalTimeStamp);
			//Set Departure dates other than Rate DTO elements 
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "endTime", NewDepartureTimeStamp);

			
			SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
			boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
			if(result){
				ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
				Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
				SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
				SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", ReservationNumber);
			}else{
				Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
				SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Error in SOAP Response see response file for more details");
			}
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured - ");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateReservationForProperty")
	public void cancelReservation(){
		
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFileCancel);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "cancelBookingRequest", "identificationNumber", ReservationNumber);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "primCnct", "lastName", LastName);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "cancelBookingRequest", "primContactInfo", PhoneNum);
		SW.Wait(20);//Explicit wait before cancellation 
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);

		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			CancelationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "cancelInfo");
			CancelationNumber=CancelationNumber.substring(0,9);
			
			Environment.loger.log(Level.INFO, "Reservation Cancelation Number= "+CancelationNumber);
			SW.WriteToEmailTestData(TestCaseName, "CancellationNumber", CancelationNumber);
			SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", CancelationNumber);
			
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
