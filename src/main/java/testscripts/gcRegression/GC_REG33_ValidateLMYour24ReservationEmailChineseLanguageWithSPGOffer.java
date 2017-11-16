package testscripts.gcRegression;
/** Purpose		: Validate LM P100/Your 24 reservation email  in Chinese language with  SPG Offer
 * TestCase Name: Validate LM P100/Your 24 reservation email  in Chinese language with  SPG Offer
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

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;

public class GC_REG33_ValidateLMYour24ReservationEmailChineseLanguageWithSPGOffer {

	CRM SW = new CRM();	
	String ReservationNumber,UserName,Password;
	String TestCaseName= getClass().getName();
	String OfferTitle,OfferID;
	String RequestXMLFile;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG33_LM_Your24ReservationQA3_1848.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG33_QA2_LM_Your24Reservation_1905.xml";
		}
	}

	@Test(priority=1)
	public void CreateSPGResConfOffer(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GC_CreateSPGOffer_LK");
			SW.Click("GCCreateResConfOffer_LK");
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			//SW.DropDown_SelectByText("GCCreateResConf_Language_DD","Chinese (Simplified)");
			SW.DropDown_SelectByText("//select[@name='localeName']", "Chinese (Simplified)");
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

			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
			if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			if(SW.ObjectExists("GCCreateResConf_NoImage_LK")){
				SW.Click("GCCreateResConf_NoImg_None_LK");

			}else{

				SW.WaitTillElementToBeClickable("GCCreateSPGPostStay_MsgSetUPIMgExpand_IC");
				SW.NormalClick("GCCreateSPGPostStay_MsgSetUPIMgExpand_IC");
				if(!SW.ObjectExists("GCCreateSPGPostStay_MsgSetUPIMgSelect_IC"))
					SW.NormalClick("GCCreateSPGPostStay_MsgSetUPIMgExpand_IC");
				SW.NormalClick("GCCreateSPGPostStay_MsgSetUPIMgSelect_IC");
			}
			SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
			SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			OfferTitle = "SPGOffer"+SW.RandomString(15);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
			if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("MessageSetup Page naviagation Failed");
			}
			SW.DropDown_SelectByIndex("GCCreateResConf_Style_DD",4);
			SW.WaitTillElementToBeClickable("GCCreateSPG_LandingImageExpand_IC");
			SW.NormalClick("GCCreateSPG_LandingImageExpand_IC");
			if(!SW.ObjectExists("GCCreateSPG_LandingImageSelect_IC"))
				SW.NormalClick("GCCreateSPG_LandingImageSelect_IC");
			SW.NormalClick("GCCreateSPG_LandingImageSelect_IC");
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
				OfferID=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
				Environment.loger.log(Level.INFO,"Offer is created successfully");

				SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
				SW.Click("GCOfferCreate_Submit_BN");
				SW.WaitTillElementToBeClickable("GCOffer_ApproveIt_IC");
				if(SW.ObjectExists("GCOffer_ApproveIt_IC")){
					SW.Click("GCOffer_ApproveIt_IC");
					SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
					SW.Click("GCOfferCreate_Submit_BN");
					SW.WaitTillElementToBeClickable("GCOffer_Generate_IC");
					if(SW.ObjectExists("GCOffer_Generate_IC")){
						SW.Click("GCOffer_Generate_IC");
						SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
						SW.Click("GCOfferCreate_Submit_BN");
						SW.WaitTillElementToBeClickable("GCOffer_Publish_IC");
						if(SW.ObjectExists("GCOffer_Publish_IC")){
							SW.Click("GCOffer_Publish_IC");
							SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
							SW.Click("GCOfferCreate_Submit_BN");
							SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
							if(SW.ObjectExists("GCOffer_Activate_IC")){
								SW.Click("GCOffer_Activate_IC");
								//SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
								Environment.loger.log(Level.INFO,"Created OfferId - "+OfferID);
								SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", OfferTitle);
								if(SW.ObjectExists("//img[@id='accountImage']")){
									SW.Click("//img[@id='accountImage']");
									SW.Click("//a//b[text()='Sign Out']");
								}
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
			Environment.loger.log(Level.ERROR, "Exception Occured - ",e);
		}
	}
	@Test(priority=2,dependsOnMethods={"CreateSPGResConfOffer"})
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

	@Test(priority=3, dependsOnMethods="CreateSPGResConfOffer")
	public void CreateReservationForLMProperty(){

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
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
			SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", ReservationNumber);
		}else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Error in SOAP Response see response file for more details");
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
