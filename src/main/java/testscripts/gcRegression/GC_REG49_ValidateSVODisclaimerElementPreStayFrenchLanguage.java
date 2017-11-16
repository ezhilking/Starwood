package testscripts.gcRegression;
/** Purpose		: Validate SVO disclaimer in Element Pre-Stay email in French language
 * TestCase Name: Validate SVO disclaimer in Element Pre-Stay email in French language
 * Created By	: Noopur
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 * Comments: There is no availability for Elements in QA3 hence checking for Sheraton brand
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

public class GC_REG49_ValidateSVODisclaimerElementPreStayFrenchLanguage {

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
			RequestXMLFile= "GC_REG49_Sheraton_ReservationQA3_5607_French.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "";//TODO Need to change the value once we get xml from the test data team 
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

