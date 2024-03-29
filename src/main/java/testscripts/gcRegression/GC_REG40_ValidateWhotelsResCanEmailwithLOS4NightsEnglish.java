package testscripts.gcRegression;
/** Purpose		: Validate  Indian Disclaimer in W hotels Cancellation email with Property offer Length of stay eligibility set to 4 nights in English language
 * TestCase Name: Validate  Indian Disclaimer in W hotels Cancellation email with Property offer Length of stay eligibility set to 4 nights in English language
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;


/** Purpose		: This is to Validate  Indian Disclaimer in W hotels Cancellation email with Property offer Length of stay eligibility set to 4 nights in English language
 * TestCase Name: ValidateWhotelsResCanEmailwithLOS4NightsEnglish
 * Created By	: Sharanya Bannuru
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
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

public class GC_REG40_ValidateWhotelsResCanEmailwithLOS4NightsEnglish {

	CRM SW = new CRM();	
	String OfferTitle;
	String ReservationNumber,UserName,Password,LastName,PhoneNum,CancelationNumber;
	String TestCaseName= getClass().getName();
	String RequestXMLFile,RequestXMLFileCancel,sPropId;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG40_ValidateWhotelsResCanEmailwithLOS4NightsEnglish.xml";
			RequestXMLFileCancel="SampleCancle_req_New_qa3.xml";
			sPropId="110";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= ".xml";
			RequestXMLFileCancel="SampleCancle_req_New_qa2.xml";
			sPropId="";
		}
	}
	@Test(priority=1)
	public void GCCreateResConfOfferWithLOS4(){
        try{
        	SW.GCLogin(UserName,Password);
    		if(SW.ObjectExists("GCHome_Message_DT")){
    			SW.NormalClick("GCHome_Message_Close_IC");
    		}
    		SW.Click("GCCreateOffer_LK");
    		SW.Click("GCCreatePropertyOffer_LK");
    		SW.Click("GCCreateResCanfOffer_LK");
    		SW.EnterValue("GCCreateResConf_PropertyId_EB",sPropId);
    		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
    		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
    		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
    		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
    		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
    		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
    		SW.Click("GCCreateResConf_Continue_BN");
    		SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
    		if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
    			Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");	
    		}else{
    			Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
    			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    			SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
    		}
    		SW.DropDown_SelectByText("GCCreateResConf_Eligibility_DD","Length Of Stay");	
    		SW.Wait(10);
    		SW.SelectRadioButton("GCCreateResConf_EligibleEqual_RB");
    		SW.EnterValue("GCCreateResConf_EligibleValue_EB","4");
    		SW.Click("GCCreateResConf_EligibleInclude_BT");
    		SW.Wait(10);
    		//SW.WaitForPageload();
    		SW.Click("GCCreateResConf_Continue_BN");
    		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
    		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
    			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
    		}	else{
    			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
    			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    			SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
    		}
    		
    		SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");
    		SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
    		if(!SW.ObjectExists("GCCreateResConf_PropertyImageSelect_IC"))
    			SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
    		SW.NormalClick("GCCreateResConf_PropertyImageSelect_IC");
    		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
    		SW.SwitchToFrame("GCCreateResConf_OfferTitle_FR");
    		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
    		OfferTitle = "Offer"+SW.RandomString(15);
    		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
    		SW.SwitchToFrame("");
    		SW.Click("GCCreateResConf_Continue_BN");
    		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
    		//SW.wait(10);
    		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
    			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
    		}	else{
    			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
    			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
    		}
    		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingImageExpand_IC");
    		SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
    		if(!SW.ObjectExists("GCCreateResConf_LandingImageSelect_IC"))
    			SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
    		SW.NormalClick("GCCreateResConf_LandingImageSelect_IC");
    		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
    		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
    		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
    		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
    		SW.SwitchToFrame("");
    		SW.Click("GCCreateResConf_Continue_BN");
    		SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
    		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
    			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
    		}	else{
    			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
    			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    			SW.FailCurrentTest("Landing Page naviagation Failed");
    		}
    		SW.EnterValue("GCCreateResConf_Rank_EB","1");
    		SW.Click("GCCreateResConf_RankMove_BT");
    		SW.Click("GCCreateResConf_Continue_BN");
    		SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
    		if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
    			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
    		}	else{
    			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
    			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    			SW.FailCurrentTest("Landing Page naviagation Failed");
    		}
    		SW.Click("GCCreateResConf__Submit_BN");
    		SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
    		if (SW.ObjectExists("GCHome_GreenMsg_DT")){
    			String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");

    			Environment.loger.log(Level.INFO,sSuccessMessage );
    			String sOfferId=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
    			Environment.loger.log(Level.INFO,"Offer is created successfully");	
    			SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
    			SW.Click("GCOfferCreate_Submit_BN");
    			SW.WaitTillElementToBeClickable("GCOffer_ApproveIt_IC");
    			if(SW.ObjectExists("GCOffer_ApproveIt_IC")){
    				SW.Click("GCOffer_ApproveIt_IC");
    				SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
    				SW.Click("GCOfferCreate_Submit_BN");
    				SW.WaitTillElementToBeClickable("GCOffer_Generate_IC");
    				if(SW.ObjectExists("GCOffer_Generate_IC")){
    					SW.Click("GCOffer_Generate_IC");
    					SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
    					SW.Click("GCOfferCreate_Submit_BN");
    					SW.WaitTillElementToBeClickable("GCOffer_Publish_IC");
    					if(SW.ObjectExists("GCOffer_Publish_IC")){
    						SW.Click("GCOffer_Publish_IC");
    						SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
    						SW.Click("GCOfferCreate_Submit_BN");
    						SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
    						if(SW.ObjectExists("GCOffer_Activate_IC")){
    							SW.Click("GCOffer_Activate_IC");
    							Environment.loger.log(Level.INFO,"Created OfferId "+sOfferId);
    							//SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
    							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", OfferTitle);
    							/*if(SW.ObjectExists("GCNavigation_SignOut_LK")){
    								SW.Click("GCNavigation_SignOut_LK");
    							}*/
    						}else{
    							Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
    							SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    							SW.FailCurrentTest("Offer Id Activation Failed");
    						}
    					}else
    					{
    						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
    						SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    						SW.FailCurrentTest("Offer Id Publish Failed");
    					}
    				}else{
    					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
    					SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    					SW.FailCurrentTest("Offer Id Generation Failed");
    				}
    			}else{
    				Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
    				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    				SW.FailCurrentTest("Offer Id Approval Failed");
    			}
    		}else{
    			Environment.loger.log(Level.ERROR,"Error Occured after Submit");
    			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
    			SW.FailCurrentTest("Error Occured after Submit");
    		}
        }catch(Exception e){
			Environment.loger.log(Level.ERROR, "Error Occured during offer creation",e);
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Error Occured during offer creation");
		}		
	}	

	@Test(priority=2,dependsOnMethods={"GCCreateResConfOfferWithLOS4"})
	public void BopRefresh(){
		SW.LaunchBrowser(Environment.BOB);
		SW.BopLogin(UserName,Password);
		SW.Click("BopHome_GCAdmin_Lk");
		SW.WaitTillElementToBeClickable("BopAdmin_Misc_Lk");
		SW.NormalClick("BopAdmin_Misc_Lk");
		SW.WaitTillElementToBeClickable("BopMisc_BeanShell_LK");
		SW.NormalClick("BopMisc_BeanShell_LK");
		SW.EnterValue("BopBeanShell_Query_EB", "com.starwood.gcp.app.offer.OffersCache.refreshCache();");
		if(SW.ObjectExists("BopeBeanShell_Execute_BN")){
			SW.Click("BopeBeanShell_Execute_BN");
			Environment.loger.log(Level.INFO,"Bop Refreshed successfully");
		}else{
			Environment.loger.log(Level.ERROR,"Bop Refresh failed");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Bop Refresh failed");
		}
		
	}
	@Test(priority=3, dependsOnMethods="BopRefresh")
	public void CreateReservation(){

		try {
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Get the values for the cancellation from the reservation req
			LastName=SoapUtility.getXMLElementText(soapRequest, "primCnct", "lastName");
			PhoneNum=SoapUtility.getXMLElementText(soapRequest, "itineraryDTO", "primaryContactInfo");
			
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 10, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 14, Calendar.DATE);

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
			String Cancelpolicydate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 8, Calendar.DATE);//one day prior to arrival date 
			newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "CancelDepositAmountDTO", "effectiveTimestamp", Cancelpolicydate+actualArrivalTimeStamp);
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
			
			//SoapUtility.printSOAPResponse(newSoapMessage);
			SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
			boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
			if(result){
				ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
				Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
				SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
				
			}else{
				Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
				SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Error in SOAP Response see response file for more details");
			}
		} catch (Exception e) {
			Environment.loger.log(Level.INFO, "Exception Occured- "+e);
		}
	}
	@Test(priority=2, dependsOnMethods="CreateReservation")
	public void cancelReservation(){
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFileCancel);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "cancelBookingRequest", "identificationNumber", ReservationNumber);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "primCnct", "lastName", LastName);
		newSoapMessage=SoapUtility.setXMLElementText(newSoapMessage, "cancelBookingRequest", "primContactInfo", PhoneNum);
		SW.Wait(20);//Explicit wait before cancellation 
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);

		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			CancelationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "cancelInfo");
			CancelationNumber=CancelationNumber.substring(0,9);
			
			Environment.loger.log(Level.INFO, "Reservation Cancelation Number= "+CancelationNumber);
			SW.WriteToEmailTestData(TestCaseName, "CancellationNumber", CancelationNumber);
			SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", CancelationNumber);
		
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
