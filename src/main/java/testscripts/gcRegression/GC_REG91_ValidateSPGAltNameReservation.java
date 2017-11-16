package testscripts.gcRegression;
/** Purpose		: Validate last name of the guest in the reservation email by making a reservation with the SPG number which has alternate language 
 * TestCase Name: GC_REG91_ValidateSPGAltNameReservation
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: Sachin
 * Reviewed Date: 20/04/2017
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
public class GC_REG91_ValidateSPGAltNameReservation {
	CRM SW = new CRM();
	String Username, Password;
	String ReservationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;

	@BeforeClass
	public void StartTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG91_BookingRequest_QA3_1967.xml";
		}
	}

	@Test(priority=1)
	public void CreateReservation(){

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

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}

