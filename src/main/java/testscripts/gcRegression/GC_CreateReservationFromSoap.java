package testscripts.gcRegression;

import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;


public class GC_CreateReservationFromSoap {

	CRM SW = new CRM();	
	String TestCaseName,ReservationNumber;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
	}

	@Test(priority=1)
	public void CreateNewReservation() {

		try {
			String RequestXMLFile= "GC_REG17_ValidateMultiRateResResconwithspgPromo.xml";
			String EndPointURL = "http://booking.qa3.nssd.star:9245/BookingWeb/services/BookingPort";
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 17, Calendar.DATE);

			String actualArrivalDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "arrivalDate");
			String actualArrivalTimeStamp=actualArrivalDateStamp.substring(10, actualArrivalDateStamp.length());
			
			//Get DepartureDate from Request
			String actualDepartureDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate");
			String actualDepartureTimeStamp=actualDepartureDateStamp.substring(10, actualDepartureDateStamp.length());
			
			String NewArrivalTimeStamp=FutureArrivalDate+actualArrivalTimeStamp;
			
			String NewDepartureTimeStamp=FutureDepartureDate+actualDepartureTimeStamp;
			//Set Arrival dates
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "arrivalDate", NewArrivalTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "startTime", NewArrivalTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "startTime", NewArrivalTimeStamp);
			
			//Set Departure dates
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "endTime", NewDepartureTimeStamp);
			
			NodeList returnList =newSoapMessage.getSOAPBody().getElementsByTagName("RateDTO");
			for(int parent=0;parent<returnList.getLength();parent++){
				NodeList innerResultList = returnList.item(parent).getChildNodes();
				for (int node = 0; node < innerResultList.getLength(); node++) {
					if (innerResultList.item(node).getNodeName().equalsIgnoreCase("startDate")) {
						innerResultList.item(node).setTextContent(SW.DateAddDays(FutureArrivalDate, "yyyy-MM-dd", parent, Calendar.DATE)+actualArrivalTimeStamp);

					}
					if (innerResultList.item(node).getNodeName().equalsIgnoreCase("endDate")) {
						innerResultList.item(node).setTextContent(SW.DateAddDays(FutureArrivalDate, "yyyy-MM-dd", parent, Calendar.DATE)+actualDepartureTimeStamp);

					}

				}
			}
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
		} catch (Exception e) {
			Environment.loger.log(Level.INFO, "Exception Occured- "+e);
		}
	}
}
