package testscripts.gcRegression;
/** Purpose		: Validate Westin Pre-Stay email in Bahasa language
 * TestCase Name: Validate Westin Pre-Stay email in Bahasa language
 * Created By	: Sachin
 * Modified By	: Sachin
 * Modified Date: 10/03/2016
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

public class GC_REG47_ValidateWestinPreStayEmailBahasaLang {

	CRM SW = new CRM();	
	String ReservationNumber,UserName,Password,PropertyId;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG47_WestinBahasa_1967_QA3.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG47_QA2_WestinBahasa_1967.xml";
		}
	}

	@Test(priority=1)
	public void CreateReservation(){
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		PropertyId=SoapUtility.getXMLElementText(soapRequest, "BinderDTO", "propertyId");
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
		//For Your 24 reservations change the Arrival and departure dates 

		//Get Arrival Time Stamp from Request 
		String actualArrivalDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ArrivalInfoDTO", "pickupTimestamp");
		String actualArrivalTimeStamp=actualArrivalDateStamp.substring(10, actualArrivalDateStamp.length());

		//Get Departure Time Stamp from Request
		String actualDepartureDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "DepartureDTO", "departureTimestamp");
		String actualDepartureTimeStamp=actualDepartureDateStamp.substring(10, actualDepartureDateStamp.length());

		//Get Cancel Deposit amount timestamp
		if(Environment.getRunEnvironment()=="QA3"){
			String actualCancelDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "effectiveTimestamp");
			String actualCancelTimeStamp=actualCancelDateStamp.substring(10, actualCancelDateStamp.length());
			String Cancelpolicydate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 14, Calendar.DATE);//one day prior to arrival date
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "effectiveTimestamp", Cancelpolicydate+actualCancelTimeStamp);
		}

		String NewArrivalTimeStamp=FutureArrivalDate+actualArrivalTimeStamp;
		String NewDepartureTimeStamp=FutureDepartureDate+actualDepartureTimeStamp;

		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ArrivalInfoDTO", "pickupTimestamp", NewArrivalTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "DepartureDTO", "departureTimestamp", NewDepartureTimeStamp);
		

		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
			//SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", ReservationNumber);
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}
	}

	@Test(priority=2, dependsOnMethods="CreateReservation")
	public void DataBaseUpdate(){
		try{	
			//update the guest details in DB	
			SW.Wait(20);//Explicit wait before changing the Arrival and deparute dates
			SW.EstablishConnection(Environment.getRunEnvironment());
			String UpdateQuery="Update reservation_header set arrival_dt=to_date (to_char(sysdate + 3, 'mm/dd/yyyy'),'mm/dd/yyyy') where reservation_confirmation_num IN ("+ReservationNumber+")";
			SW.UpdateQuery(UpdateQuery);

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Failed during db Update");
		}	
	}

	@Test(priority=3, dependsOnMethods="DataBaseUpdate")
	public void RunOutStayTask(){
		try{
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(UserName,Password);
			SW.Click("BopHome_GCAdmin_Lk");
			SW.NavigateTo(Environment.BoBTaskRunner);
			SW.Click("BoBConfigFactory_Lk");
			String sOldValue=SW.GetText("BoB_Config_Property_EB");
			String sNewvalue=sOldValue+","+PropertyId;
			SW.EnterValue("BoB_Config_Property_EB", sNewvalue);
			SW.NormalClick("BoB_TaskRunner_Update_BT");
			SW.Click("BoBTaskRunner_Lk");
			SW.EnterValue("BoB_TaskRunner_Pwd_EB","SGC");
			SW.Click("BOB_TaskRunner_OutStayTask_BT");
			String sSucessMessagge="BoB_TaskRunner_SuccessMsg_DT";
			if(SW.ObjectExists(sSucessMessagge)){
				Environment.loger.log(Level.INFO,"OutStay Task Run SuccessFull");
			}else
			{
				Environment.loger.log(Level.ERROR, "OutStay Task Run failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("OutStay Task Run failed");
			}
			
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed During OutStayTaskRun",e);
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("OutStay Task Run failed");
		}
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
