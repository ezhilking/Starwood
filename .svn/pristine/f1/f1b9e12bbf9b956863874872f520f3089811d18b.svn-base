package testscripts.gcRegression;
/** Purpose		: Validate St.Regis Promo reservation email in Russian language 
 * TestCase Name: Validate St.Regis Promo reservation email in Russian language 
 * Created By	: Sharanya Bannuru
 * Modified By	: Sachin
 * Modified Date: 6/22/2016
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

public class GC_REG31_ValidateStRegisPromoReservation {

	CRM SW = new CRM();	
	String ReservationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;
	String EndPointURL;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG31_ValidateStRegisPromoReservation.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= ".xml";
		}
	}

	@Test(priority=1)
	public void CreateReservationForSTProperty(){

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);

		//Get Arrival Date from Request 
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
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "RateDTO", "startDate", NewArrivalTimeStamp);
		//Set Departure dates
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "endTime", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "RateDTO", "endDate", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "PromotionDTO", "resDepartureDate", NewDepartureTimeStamp);


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
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
