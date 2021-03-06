package testscripts.gcRegression;
/** Purpose		: Validate Atlas and Omniture tags in Element reservation email  in Trad Chinese
 * TestCase Name: Validate Atlas and Omniture tags in Element reservation email  in Trad Chinese
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 * Comments: There is no availability for Elements in QA3 hence checking for Westin brand
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

public class GC_REG37_ValidateAtlasAndOmnitureTagsElementResEmailTradChinese {

	CRM SW = new CRM();	
	String ReservationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG37_Westin_ReservationQA3_1967_ChineseTraditional.xml";	
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG37_QA2_Westin_Reservation_1315_ChineseTraditional.xml";
		}
	}

	@Test(priority=1)
	public void CreateReservationForProperty(){
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 17, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 18, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
		SoapUtility.printSOAPResponse(newSoapMessage);
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
