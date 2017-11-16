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

public class REG35_IssueaFreeNightAwardGuest {
	CHANNELS SW = new CHANNELS();
	String ReservationNumber,RequestXMLFile;
	String FutureArrivalDate,RatePlanId;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		RequestXMLFile=SW.TestData("FNIReservationXML");
		ReservationNumber="694126723";
		RatePlanId="SPG2";
	}
	@Test(priority=0)
	public void CreateReservation(){
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Get the values for the cancellation from the reservation req

		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);
		//Changing Award Dates
		FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);
		String actualArrivalDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "arrivalDate");
		String actualArrivalTimeStamp=actualArrivalDateStamp.substring(10, actualArrivalDateStamp.length());
		String actualDepartureDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate");
		String actualDepartureTimeStamp=actualDepartureDateStamp.substring(10, actualDepartureDateStamp.length());
		String NewArrivalTimeStamp=FutureArrivalDate+actualArrivalTimeStamp;
		String NewDepartureTimeStamp=FutureDepartureDate+actualDepartureTimeStamp;
		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "AwardDTO", "awardStartTime", NewArrivalTimeStamp);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "AwardDTO", "awardEndTime", NewDepartureTimeStamp);
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		SoapUtility.printSOAPResponse(soapResponse);
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			SW.WriteToTestData("AwardConfirmNumber", ReservationNumber);
			String ResArrivalDate=SW.ChangeDateFormat(FutureArrivalDate, "yyyy-MM-dd", "ddMMMyyyy");
			String ResDepDate=SW.ChangeDateFormat(FutureDepartureDate, "yyyy-MM-dd", "ddMMMyyyy");
			String PropID=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "propertyId");
			RatePlanId=SoapUtility.getXMLElementText(soapResponse, "RateDTO", "rateId");
			SW.WriteToTestData("AwardResStartDate", ResArrivalDate);
			SW.WriteToTestData("AwardResEndDate", ResDepDate);
			SW.WriteToTestData("AwardResProp", PropID);
			SW.WriteToTestData("AwardResRatePlanID", RatePlanId);
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
		}
	}
	@Test(priority=1 ,dependsOnMethods="CreateReservation")
	public void IssueFNAward(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB"); //WAiting for the confirmation tab to be present
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", ReservationNumber+ Keys.TAB); //Entering the confirmation number
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Search
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_Ack_BT"); //Wait for the acknoledgement
		SW.Click("NavigatorReservationSearchPage_Ack_BT"); //Click on Ack
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Free Night Awards"); //Selecting the award type
		SW.EnterValue("NavigatorAwardPage_AwardId_EB", RatePlanId);
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("//span[text()='"+RatePlanId+"-1N']");
		SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_ContactName_EB");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.EnterValue("NavigatorAwardPage_ConfirmationNum_EB", SW.TestData("AwardConfirmNumber"));
		SW.EnterValue("NavigatorAwardPage_PropID_EB", SW.TestData("AwardResProp"));
		SW.EnterValue("NavigatorAwardPage_RatePlan_EB", SW.TestData("AwardResRatePlanID"));
		SW.EnterValue("NavigatorAwardPage_StartDate_EB", SW.TestData("AwardResStartDate"));
		SW.EnterValue("NavigatorAwardPage_EndDate_EB", SW.TestData("AwardResEndDate"));
		SW.Click("NavigatorInternalPointTransferPage_Order_BT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			Reporter.Write("Order Sucess", "Order number generated", "Order number generated", "PASS");
			String awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else 
			Reporter.Write("Order Failed", "Order number not generated", "Order number not generated", "Fail");
		
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
