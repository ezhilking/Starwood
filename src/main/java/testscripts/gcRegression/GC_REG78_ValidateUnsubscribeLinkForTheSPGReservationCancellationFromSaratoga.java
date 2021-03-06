package testscripts.gcRegression;
/** Purpose		: Validate the Unsubscribe link for the Spg Reservation Cancellation from the Saratoga
 * TestCase Name: GC_REG78_ValidateUnsubscribeLinkForTheSPGReservationCancellationFromSaratoga
 * Created By	: Sindhu SR
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
public class GC_REG78_ValidateUnsubscribeLinkForTheSPGReservationCancellationFromSaratoga {

	CRM SW = new CRM();	
	String ReservationNumber,UserName,Password,LastName,PhoneNum,CancelationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile,RequestXMLFileCancel;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG78_Booking Request_QA3_1965.xml";
			RequestXMLFileCancel= "SampleCancle_req_New_qa3.xml";
		}
	}

	@Test(priority=1)
	public void CreateReservation() {

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		// Process the SOAP Response
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Created Successfully");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
		}else{
			System.out.println("Error in SOAP Response see response file for more details");
			Environment.loger.log(Level.ERROR, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);
		}
	}

	@Test(priority=2, dependsOnMethods="CreateReservation")
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
			Reporter.WriteLog(Level.ERROR, "Error in SOAP Response see response file for more details");
		}

	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}

