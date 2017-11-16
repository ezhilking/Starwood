package testscripts.gcRegression;
/** Purpose		: Validate Reservation Confirmation SMS is sent to the guest for Sheraton brand in english language when requested in Saratoga
 * TestCase Name: Validate Reservation Confirmation SMS is sent to the guest for Sheraton brand in english language when requested in Saratoga
 * Created By	: Sachin
 * Modified By	: sachin
 * Modified Date: 10/04/2016
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

public class GC_REG30_ValidateReservationSMSSentToTheGuestSaratoga {

	CRM SW = new CRM();	
	String ReservationNumber;
	String RequestXMLFile;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG30_Booking Request_QA3_1967.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG30_QA2_Booking Request_376.xml";
		}
	}

	@Test(priority=1)
	public void CreateNewReservation() {
		
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
		}else{
			System.out.println("Error in SOAP Response see response file for more details");
			Environment.loger.log(Level.ERROR, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);
		}
	}
	@Test(priority=2, dependsOnMethods="CreateNewReservation")
	public void ValidateSMSStatusInOMT(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Wait(90);//Wait for some time to reflect the message status in OMT 
		SW.Click("GCNavigation_StayRelatedHistory_LK");
		SW.EnterValue("GCStayRelatedHistory_ConfirmationNum_EB", ReservationNumber);
		SW.Click("GCStayRelatedHistory_Search_BT");
		SW.Wait(10);
		if(SW.ObjectExists("//table[@id='row']/tbody//td[2]//a[contains(.,'"+ReservationNumber +"')]")){
			Environment.loger.log(Level.INFO, "Search results are returned successfully for given reservation number");
		}
		else{
			Environment.loger.log(Level.INFO, "Search results are not returned for given reservation number");
			SW.FailCurrentTest("Search results are not returned for given reservation number");
		}
		String UIConfNum=SW.GetText("GCNavigation_ResNum_WT");
		if(SW.CompareText(ReservationNumber, UIConfNum.trim())){
			
			String SMSStatus=SW.GetText("GCNavigation_MSGSent_WT");
			if(SMSStatus.equalsIgnoreCase("MESSAGE_SENT ")){
				SMSStatus=SW.GetText("GCNavigation_SMSSent_WT");
			}
			if(SW.CompareText("SMS Sent ", SMSStatus)){
				Environment.loger.log(Level.INFO, "SMS Sent to the guest");
			}else{
				Environment.loger.log(Level.ERROR, "SMS sending Failed having status - " + SMSStatus);
				SW.FailCurrentTest("SMS sending Failed");
			}
		}
	}
	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}
