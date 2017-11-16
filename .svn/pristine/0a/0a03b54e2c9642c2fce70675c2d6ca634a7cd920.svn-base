package testscripts.gcRegression;
/** Purpose		: Validate that the customized greeting text is displayed in the prestay email when the DB has greeting text value as null and mail has property offer
 * TestCase Name: GC_REG96_ValidateCustomizedGreetingTextInPrestayEmailWhenDBHasCustomizedGreetingTextValue
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.ArrayList;
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
public class GC_REG96_ValidateCustomizedGreetingTextInPrestayEmailWhenDBHasCustomizedGreetingTextValue {

	CRM SW = new CRM();
	String Username, Password, EmailId;
	String TestCaseName, sMessage;
	String ReservationNumber;
	String RequestXMLFile;

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
	//	TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		TestCaseName="GC_REG96_ValidateCustomizedGreetingTextInPrestayEmailWhenDBHasCustomizedGreetingTextValue";
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		EmailId=SW.TestData("EmailID");
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG96_BookingRequest_QA3_1471.xml";
		}
	}

	
	//Create Pre-Stay Offer and activate the offer
	@Test(priority=1)
	public void PropertyPreStayOfferCreation(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreatePreStayOffer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",SW.TestData("GCPropertyID"));
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
			Reporter.Write("Validate Offer Setup Page Navigation", "OfferSetUp Page naviagation SuccessFull", "OfferSetUp Page naviagation Failed", "Fail");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreatePreStayOffer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",SW.TestData("GCPropertyID"));
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
			Reporter.Write("Validate Offer Setup Page Navigation", "OfferSetUp Page naviagation SuccessFull", "OfferSetUp Page naviagation Failed", "Fail");
		}
		SW.DropDown_SelectByText("GCCreateResConf_Eligibility_DD","Total Starwood Nights");	
		SW.Wait(10);
		SW.SelectRadioButton("GCCreateResConf_EligibleGT_RB");
		SW.EnterValue("GCCreateResConf_EligibleValue_EB","75");
		SW.Click("GCCreateResConf_EligibleInclude_BT");
		//SW.WaitForPageload();
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			Reporter.Write("Validate Eligibility Criteria Page Navigation", "Eligibility Criteria Page naviagation SuccessFull", "Eligibility Criteria Page naviagation Failed", "Fail");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");

		SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		if(!SW.ObjectExists("GCCreateResConf_PropertyImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		SW.NormalClick("GCCreateResConf_PropertyImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateSPGPostStay_OfferTitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		sMessage = SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			Reporter.Write("Validate Message Setup Page Navigation", "MessageSetup Page naviagation SuccessFull", "MessageSetup Page naviagation Failed", "Fail");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		if(!SW.ObjectExists("GCCreateResConf_LandingImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
			Reporter.Write("Validate Landing Page Navigation", "Landing Page naviagation should be SuccessFull", "Landing Page naviagation SuccessFull", "PASS");
		}	else{
			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
			Reporter.Write("Validate Landing Page Navigation", "Landing Page naviagation SuccessFull", "Landing Page naviagation Failed", "Fail");
		}
		SW.EnterValue("GCCreateResConf_Rank_EB","1");
		SW.Click("GCCreateResConf_RankMove_BT");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
		if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
			Environment.loger.log(Level.INFO,"Ranking Page naviagation SuccessFull");
			Reporter.Write("Validate Ranking Page Navigation", "Ranking Page naviagation should be SuccessFull", "Ranking Page naviagation SuccessFull", "PASS");
		}	else{
			Environment.loger.log(Level.ERROR,"Ranking Page naviagation Failed");
			Reporter.Write("Validate Ranking Page Navigation", "Ranking Page naviagation SuccessFull", "Ranking Page naviagation Failed", "Fail");
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
							if(SW.ObjectExists("GCNavigation_SignOut_LK")){
								SW.Click("GCNavigation_SignOut_LK");
							}
						}else{
							Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
							Reporter.Write("Validate Offer ID Activation", "Offer Id Activation Successfull", "Offer Id Activation Failed", "Fail");
						}
					}else{
						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
						Reporter.Write("Validate Offer ID Publish", "Offer Id Publish Failed", "Offer Id Publish Failed", "Fail");
					}
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
					Reporter.Write("Validate Offer ID Generation", "Offer Id Generation Successfull", "Offer Id Generation Failed", "Failed");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
				Reporter.Write("Validate Offer ID Approval", "Offer Id Approval Successfull", "Offer Id Approval Failed", "Fail");
				}
		}else{
			Environment.loger.log(Level.ERROR,"Error Occured after Submit");
			Reporter.Write("Validate Offer ID Submission", "EOffer ID Submission Successfull", "Error Occured after Submit", "Fail");
		}
	}	
	
	
	//Create a reservation for the dates the Pre-Stay Offer is active
	@Test(priority=2, dependsOnMethods="PropertyPreStayOfferCreation")
	public void CreateReservation(){

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
			Reporter.Write("Validate Create Reservation", "Reservation should be Created Successfully", "Reservation Created Successfully : "+ReservationNumber, "PASS");
			SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);
		}else{
			System.out.println("Error in SOAP Response see response file for more details");
			Environment.loger.log(Level.ERROR, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);
		}
	}
	
	
	//Update the reservation number with the property thresh hold value
	@Test(priority=3, dependsOnMethods="CreateReservation")
	public void UpdateResHeaderWithResConfNum(){
		String query="Update reservation_header set arrival_dt=to_date (to_char(sysdate + 3, 'mm/dd/yyyy'),'mm/dd/yyyy') where reservation_confirmation_num IN ("+ReservationNumber+")";
		SW.EstablishConnection("qa3");
		SW.UpdateQuery(query);
	}
	
	//Run 'SGC-OutStay task' batch job in BOP to trigger the Pre-Stay email
	@Test(priority=4,dependsOnMethods="UpdateResHeaderWithResConfNum")
	public void BopRefresh(){
		SW.LaunchBrowser(Environment.BOB);
		SW.BopLogin(Username,Password);
		SW.Click("BopHome_GCAdmin_Lk");
		SW.WaitTillElementToBeClickable("BopAdmin_Misc_Lk");
		SW.NormalClick("BopAdmin_Misc_Lk");
		SW.WaitTillElementToBeClickable("BopMisc_BeanShell_LK");
		SW.NormalClick("BopMisc_BeanShell_LK");
		SW.EnterValue("BopBeanShell_Query_EB", "com.starwood.gcp.app.offer.OffersCache.refreshCache();");
		if(SW.ObjectExists("BopeBeanShell_Execute_BN")){
			SW.Click("BopeBeanShell_Execute_BN");
			Environment.loger.log(Level.INFO,"Bop Refreshed successfully");
			Reporter.Write("BOP Refresh", "BOP should be refreshed successfully", "BOP Refresh Successful", "PASS");
		}else{
			Environment.loger.log(Level.ERROR,"Bop Refresh failed");
			Reporter.Write("Validate BOP Refresh", "Bop Refreshed successfully", "Bop Refresh failed", "Fail");
		}
	}
	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}


