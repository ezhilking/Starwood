package testscripts.Wechat;

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

public class REG17_CashAndPointAward {
	CRM SW = new CRM();	
	String TestCaseName,ReservationNumber,spgMem;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
	}

	@Test(priority=1)
	public void CreateCashAndPointReservation() {
		try
		{
		String RequestXMLFile= "WC_REG17CashandPointReservation_QA4.xml";
		String EndPointURL = "http://booking.qa4.nssd.star:9245/BookingWeb/services/BookingPort";
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 17, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 19, Calendar.DATE);
		//Get Award Start date
				String actualAwardStartTime=SoapUtility.getXMLElementText(soapRequest, "AwardDTO", "awardStartTime");
				String actualAwardStartTimeStamp=actualAwardStartTime.substring(10, actualAwardStartTime.length());
				//SPG MEMBER
				spgMem=SoapUtility.getXMLElementText(newSoapMessage, "ConfirmationDTO", "membershipNumber");
				//Get AwardEndDate from Request
				String actualAwardEndStamp=SoapUtility.getXMLElementText(soapRequest, "AwardDTO", "awardEndTime");
				String actualAwardEndTimeStamp=actualAwardEndStamp.substring(10, actualAwardEndStamp.length());

				String NewAwardStartTimeStamp=FutureArrivalDate+actualAwardStartTimeStamp;

				String NewAwardEndTimeStamp=FutureDepartureDate+actualAwardEndTimeStamp;
				//Set Award dates
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "AwardDTO", "awardStartTime", NewAwardStartTimeStamp);
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "AwardDTO", "awardEndTime", NewAwardEndTimeStamp);
		//Get Arrival Date from Request 
				String actualArrivalDateStamp=SoapUtility.getXMLElementText(soapRequest, "ConfirmationDTO", "arrivalDate");
				String actualArrivalTimeStamp=actualArrivalDateStamp.substring(10, actualArrivalDateStamp.length());
				// Getting SPG number
				spgMem=SoapUtility.getXMLElementText(newSoapMessage, "AffiliationDTO", "membershipNumber");
				//Get DepartureDate from Request
				String actualDepartureDateStamp=SoapUtility.getXMLElementText(soapRequest, "ConfirmationDTO", "departureDate");
				String actualDepartureTimeStamp=actualDepartureDateStamp.substring(10, actualDepartureDateStamp.length());

				String NewArrivalTimeStamp=FutureArrivalDate+actualArrivalTimeStamp;

				String NewDepartureTimeStamp=FutureDepartureDate+actualDepartureTimeStamp;
				//Set Arrival dates
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "ConfirmationDTO", "arrivalDate", NewArrivalTimeStamp);
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "ConfirmationDTO", "startTime", NewArrivalTimeStamp);
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "ProductDTO", "startTime", NewArrivalTimeStamp);
				
				//Set Departure dates
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
				soapRequest=SoapUtility.setXMLElementText(soapRequest, "ProductDTO", "endTime", NewDepartureTimeStamp);
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
				
		//cancel deposite date
		String actualCancelDateStamp=SoapUtility.getXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "effectiveTimestamp");
		String actualCancelTimeStamp=actualCancelDateStamp.substring(10, actualCancelDateStamp.length());
		String Cancelpolicydate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);//one day prior to arrival date
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "effectiveTimestamp", Cancelpolicydate+actualCancelTimeStamp);
		SoapUtility.printSOAPResponse(newSoapMessage);
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, EndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			
		}
		} catch (Exception e) {
			Environment.loger.log(Level.INFO, "Exception Occured- " + e);
		}
	}
	@Test(priority=2, dependsOnMethods="CreateCashAndPointReservation")
	public void dbValidtion()
	{
		try{	

			SW.Wait(10);//for reflect into DB
			SW.EstablishConnection("qa4");
			String CheckQuery="select * from odsft.freq_travel_notf_hist where mbrshp_num in ("+spgMem+") and message_type_id ='896'";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Notification History table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from freq_travel_call_comm where call_comm_Cd='WCT' and mbrshp_num in ("+spgMem+")and call_comm_text ='AWARDS CASH  POINTS-CAT 5 FOR 1NT ISSUED.'";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Freq_travel_call_comm  table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from AQADM.SPGPUSHNOTF_QUEUE_TABLE order by enq_time desc";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the table Before Batch job");
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
			SW.FailCurrentTest("Failed during db Validation");
		}		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
