package testscripts.gcRegression;
/** Purpose		: Validate the Sheraton English  Fax is sent to the mailbox when requested in saratoga while booking a reservation
 * TestCase Name: GC_REG73_ValidateSheratonEnglishWithFaxSentMailBox
 * Created By	: Sharmila Begam
 * Modified By	: sachin
 * Modified Date: 10/04/2016
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.commons.io.input.SwappedDataInputStream;
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;
public class GC_REG73_ValidateSheratonEnglishWithFaxSentMailBox {
	CRM SW = new CRM();	
	String ReservationNumber,LastName,PhoneNum,CancelationNumber,UserName,Password;
	String TestCaseName= getClass().getName();
	String PropertyID;
	String RequestXMLFile;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG73_SheratonEnglishWithFax_1047_QA3.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG73_QA2_SheratonEnglishWithFax_373.xml";
		}
	}

	@Test(priority=1)
	public void CreateReservation(){

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		 PropertyID = SoapUtility.getXMLElementText(soapRequest, "BinderDTO", "propertyId");
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 17, Calendar.DATE);
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
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "RateDTO", "startDate", NewArrivalTimeStamp);
		//Set Departure dates
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "endTime", NewDepartureTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "RateDTO", "endDate", NewArrivalTimeStamp);
		
	
		
		//SoapUtility.printSOAPResponse(newSoapMessage);
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			//Reporter.Write("Reservation Confirmation", "Reservation should be created successfully", "Reservation is created successfully"+ReservationNumber, "PASS");
			//SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
			
		}
		else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}
	}
	
	@Test(priority=2)
	public void ValidateFax(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(UserName,Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCDocDelivery_LK");
		SW.Click("//*[@id='filterLink1']/a");
		//SW.WaitTillElementToBeVisible("//select[@name='selectedInstayDay']");
		//SW.DropDown_SelectByIndex("//select[@name='selectedInstayDay']", 1);
		SW.EstablishConnection(Environment.getRunEnvironment());
		String ValidateReservation="SELECT obm_status_id FROM OFFER.outbound_message where reservation_confirmation_num in ("+ReservationNumber+") and dlvry_type_id=1";
		if(SW.RecordExists(ValidateReservation))
		{
		SW.Wait(200); // Needs atleast 2 mins to reflect In UI
		SW.WaitTillElementToBeVisible("//input[@value='Apply']");
		SW.Click("//input[@value='Apply']");
		String Res = SW.GetText("(//*[@id='resMsg']//td[3])[last()]");
		if(SW.CompareText(ReservationNumber, Res))
		{
			Environment.loger.log(Level.INFO,"Reservation is Created successfully");
			Reporter.Write("Reservation Validation", "Reservation should be created successfully", "Reservation is Created successfully"+Res, "PASS");
		}
		else{
			Environment.loger.log(Level.ERROR,"Reservation is not Created successfully");
			Reporter.Write("Reservation Validation", "Reservation should not be created successfully", "Reservation is not Created successfully", "FAIL");
		}
		
		}
			
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
