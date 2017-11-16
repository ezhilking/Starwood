package testscripts.Navigator;

import java.util.Calendar;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;

/* Purpose		: This script for Locate Confirmation # Without SPG # And Enroll Primary Guest
 * TestCase Name: Locate Confirmation # Without SPG # And Enroll Primary Guest
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG58_LocateConfNumWithoutSPG_EnrollPrimaryGuest {
	CHANNELS SW = new CHANNELS();
	String ReservationNumber,emailAddress;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		emailAddress="test@gmail.com";
	}	
	@Test(priority=0)
	public void createReservation(){
		String RequestXMLFile="SaratogaReservationWithoutSPG_QA3.xml";
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
			SW.WriteToTestData("ConfNum_createReservation", ReservationNumber);
		}else{
			System.out.println("Error in SOAP Response see response file for more details");
			Environment.loger.log(Level.ERROR, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);
		}		
	}
	
	@Test(priority=1,dependsOnMethods="createReservation")
	public void locateReservation(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK");

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB");
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", SW.TestData("ConfNum_createReservation")+ Keys.TAB);
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		if(SW.ObjectExists("NavigatorReservationDetailSearchPage_Cancel_BT"))
			Reporter.Write("Reservation has loaded", "Cancelbutton ", "Cancelbutton", "PASS");
		else
			Reporter.Write("Reservation has loaded", "Cancelbutton ", "Cancelbutton", "Fail");
	}
	
	@Test(priority=2,dependsOnMethods="locateReservation")
	public void enrollMember(){
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.NormalClick("NavigatorHomePage_EnrollNewMember_LK"); //Clicking on Enroll new Member
		SW.Click("NavigatorHomePage_yes_BT");
		SW.WaitTillElementToBeClickable("NavigatorEnrollPage_FirstName_EB");
		SW.DropDown_SelectByText("NavigatorEnrollPage_EmailType_DD", "Work Email");
		SW.NormalClick("NavigatorEnrollPage_Email_EB");
		SW.EnterValue("NavigatorEnrollPage_Email_EB", emailAddress);
		//Submitting the Enroll request
		SW.NormalClick("NavigatorEnrollPage_SubmitEnrollment_BT");
		//Getting values from message box
		SW.WaitTillElementToBeClickable("NavigatorEnrollPage_MemberEnrollMessage_ST"); //Waiting for the message confirmation
		String SPGNumberCreated = SW.GetText("NavigatorEnrollPage_SPGNum_DT").substring(13); // Getting the SPG number from the message box - SPG Number : 42008341111
		SW.NormalClick("NavigatorEnrollPage_MemberConfirmClose_BT"); // Closing the message box
		//Writing the values into the Excel
		SW.WriteToTestData("SPGnum_created", SPGNumberCreated); //Writing the SPG number into the test data
		SW.Click("NavigatorHomePage_TakeCall_BT");
		SW.SearchGuestBySPGnumber(SW.TestData("SPGnum_created"));
		String SPGPreferredNum = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT").substring(15); // Getting the SPG number from the navigator - SPG preferred
		Environment.loger.info("SPG number created is - "+ SPGPreferredNum);
		//getting form test data
		SPGNumberCreated = SW.TestData("SPGnum_created");
		if(SW.CompareText("SPGNumComparision", SPGNumberCreated, SPGPreferredNum))
			Environment.loger.log(Level.INFO,"The Enrolled number has displayed successfully");
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}
}
