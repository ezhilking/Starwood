package testscripts.gcRegression;
/** Purpose		: Validate Promo  FP Cancellation email in Turkish language
 * TestCase Name: Validate Promo  FP Cancellation email in Turkish language
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 * Comments: There is no availability for Four Points in QA3 hence checking for Westin brand 1005 prop
 */
import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;


public class GC_REG44_ValidatePromoFPCancellationEmailTurkishLanguage {

	CRM SW = new CRM();	
	String ReservationNumber,LastName,PhoneNum,CancelationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile,RequestXMLFileCancel,PropID;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG44_PromoWestinReservationQA3_1005.xml";
			RequestXMLFileCancel="SampleCancle_req_New_qa3.xml";
			PropID="1005";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "";//TODO Add prop for QA2 once get the data 
			RequestXMLFileCancel="SampleCancle_req_New_qa2.xml";
			PropID="";//TODO Add prop for QA2 once get the data
		}
	}

	@Test(priority=1)
	public void CreateNewReservation() {

		try {
			
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Get the values for the cancellation from the reservation req
			LastName=SoapUtility.getXMLElementText(soapRequest, "primCnct", "lastName");
			PhoneNum=SoapUtility.getXMLElementText(soapRequest, "itineraryDTO", "primaryContactInfo");
			
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 18, Calendar.DATE);

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
			String Cancelpolicydate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 14, Calendar.DATE);//one day prior to arrival date 
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "startTime", Cancelpolicydate+actualArrivalTimeStamp);
			
			
			//Set Departure dates
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "endTime", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "starlinkPromotion", "resDepartureDate", NewDepartureTimeStamp);
			
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
			SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
			boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
			if(result){
				ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
				Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
				SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
				
			}else{
				Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
				SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Error in SOAP Response see response file for more details");
			}
		} catch (Exception e) {
			Environment.loger.log(Level.INFO, "Exception Occured- "+e);
		}
	}
	@Test(priority=2, dependsOnMethods="CreateNewReservation")
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
