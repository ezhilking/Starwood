package testscripts.gcRegression;
/** Purpose		: Validate the Reservation search results by entering Confirmation Number  for Westin English email
 * TestCase Name: Validate the Reservation search results by entering Confirmation Number  for Westin English email
 * Created By	: Sachin
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
import org.w3c.dom.NodeList;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;

public class GC_REG23_ValidateReservationSearchResultsByConfirmationNumberWestinEnglish {
	
	CRM SW = new CRM();	
	String ReservationNumber,LastName,PhoneNum,CancelationNumber,OfferTitle,UserName,Password;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;
	String RequestXMLFileCancel;
	String PropID;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		/*SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");*/
		
		
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG43_SNAWestinReservationQA3_1005.xml";
			RequestXMLFileCancel="SampleCancle_req_New_qa3.xml";
			PropID="1005";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "";//TODO Add prop for QA2 once get the data 
			RequestXMLFileCancel="SampleCancle_req_New_qa2.xml";
			PropID="";//TODO Add prop for QA2 once get the data
		}
	}
	
	
	@Test(priority=1)
	public void CreateNewReservation() {

		try {
			
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Get the values for the cancellation from the reservation req
			LastName=SoapUtility.getXMLElementText(soapRequest, "primCnct", "lastName");
			PhoneNum=SoapUtility.getXMLElementText(soapRequest, "itineraryDTO", "primaryContactInfo");
			
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 10, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 13, Calendar.DATE);
			System.out.println(FutureArrivalDate);

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
			String Cancelpolicydate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 9, Calendar.DATE);//one day prior to arrival date 
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "startTime", Cancelpolicydate+actualArrivalTimeStamp);
			
			
			//Set Departure dates
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "ProductDTO", "endTime", NewDepartureTimeStamp);
			
			
			NodeList returnList =newSoapMessage.getSOAPBody().getElementsByTagName("RateDTO");
			for(int parent=0;parent<returnList.getLength();parent++){
				NodeList innerResultList = returnList.item(parent).getChildNodes();
				for (int node = 0; node < innerResultList.getLength(); node++) {
					if (innerResultList.item(node).getNodeName().equalsIgnoreCase("startDate")) {
						innerResultList.item(node).setTextContent(SW.DateAddDays(FutureArrivalDate, "yyyy-MM-dd", parent, Calendar.DATE)+actualArrivalTimeStamp);

					}
					if (innerResultList.item(node).getNodeName().equalsIgnoreCase("endDate")) {
						innerResultList.item(node).setTextContent(SW.DateAddDays(FutureArrivalDate, "yyyy-MM-dd", parent, Calendar.DATE)+actualDepartureTimeStamp);

					}

				}
			}
			
			SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
			boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
			if(result){
				ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
				Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			}
		} catch (Exception e) {
			
		}
	}

	@Test(priority=2, dependsOnMethods="CreateNewReservation")
	public void ValidateReservationSearch(){
		
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCNavigation_StayRelatedHistory_LK");
		SW.EnterValue("GCStayRelatedHistory_ConfirmationNum_EB", ReservationNumber);
		SW.Click("GCStayRelatedHistory_Search_BT");
		if(SW.ObjectExists("//table[@id='row']/tbody//td[2]//a[contains(.,'"+ReservationNumber +"')]")){
			Environment.loger.log(Level.INFO, "Search results are returned successfully for given reservation number");
		}
		else{
			Environment.loger.log(Level.INFO, "Search results are not returned for given reservation number");
			SW.FailCurrentTest("Search results are not returned for given reservation number");
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
