package testscripts.gcRegression;
/** Purpose		: This is to Validate Sheraton Post-stay email in Japanese language with GEI and SPG offer  
 * TestCase Name: GC_REG55_ValidateSheratonPostStayEmailJapaneselangGEIandSPGoffer
 * Created By	: Sharmila Begam
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

public class GC_REG55_ValidateSheratonPostStayEmailJapaneselangGEIandSPGoffer {
	CRM SW = new CRM();	
	String GEIOfferTitle,SPGOfferTitle;
	String ReservationNumber,sPropertyId,sUserName,sPassword;
	String TestCaseName= getClass().getName();
	String RequestXMLFile;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		sUserName=SW.TestData("GCUsername");
		sPassword=SW.TestData("GCPassword");
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG55_Sheraton_1047_QA3.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG55_QA2_Sheraton_376.xml";
		}
	}

	@Test(priority=1)
	public void GCCreateGEIoffer(){
		try{
			SW.GCLogin(sUserName,sPassword);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GC_CreateGEIOffer_LK");
			SW.Click("GC_GEIPostStayOffer");
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			String sEndDate=SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 25, Calendar.DATE);
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB", sEndDate);
			SW.DropDown_SelectByText("GCCreateResConf_Language_DD", "Japanese (Japan)");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
			if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
				Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");	
				Reporter.Write("OfferSetUp Page", "OfferSetUp Page naviagation should be SuccessFull", "OfferSetUp Page naviagation SuccessFull", "PASS");
			}else{
				Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
			}	
			SW.Click("GCCreateResConf_Continue_BN");
			if(SW.ObjectExists("GCGEIPostStayOfferTitle_TB")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
				Reporter.Write("Eligibility Criteria Page", "Eligibility Criteria Page naviagation should be SuccessFull", "Eligibility Criteria Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			GEIOfferTitle = "GEIOffer"+SW.RandomString(10);
			SW.EnterValue("GCGEIPostStayOfferTitle_TB",GEIOfferTitle);
			SW.EnterValue("GCCallToCopyAction_TB","CallToAction"+GEIOfferTitle);
			SW.Wait(15);
			SW.SwitchToFrame("GC_GEIPostStayMsgSetUp_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB", "Description"+GEIOfferTitle);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
				Reporter.Write("MessageSetup Page", "MessageSetup Page naviagation should be SuccessFull", "MessageSetup Page naviagation SuccessFull", "PASS");
				SW.EnterValue("GCCreateResConf_Rank_EB","1");
				SW.Click("GCCreateResConf_RankMove_BT");
			}else{
				Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
				Reporter.Write("MessageSetup Page", "MessageSetup Page naviagation should be Failed", "MessageSetup Page naviagation Failed", "Failed");
			}
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
			if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
				Reporter.Write("Landing Page", "Landing Page naviagation should be SuccessFull", "Landing Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Landing Page naviagation Failed");
			}
			SW.Click("GCCreateResConf__Submit_BN");
			SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
			if (SW.ObjectExists("GCHome_GreenMsg_DT")){
				String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,sSuccessMessage );
				String sOfferId=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
				Environment.loger.log(Level.INFO,"Offer is created successfully");
				Reporter.Write("Offer Creation", "Offer Creation should be SuccessFull", "Offer Creation is SuccessFull :"+sOfferId, "PASS");
				SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
				SW.Click("GCOfferCreate_Submit_BN");
				SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
				if(SW.ObjectExists("GCOffer_Activate_IC")){
					SW.Click("GCOffer_Activate_IC");
					Environment.loger.log(Level.INFO,"Created OfferId "+sOfferId);
					SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", GEIOfferTitle);

				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
					SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
					SW.FailCurrentTest("Offer Id Approval Failed");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Error Occured after Submit");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Error Occured after Submit");
			}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured-",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Exception  Occured ");
		}
	}

	// Creating SPG POST STAY Offer
	@Test(priority=2,dependsOnMethods={"GCCreateGEIoffer"})
	public void GCCreateSPGPostStayoffer(){
		try{
			SW.Click("GCCreateOffer_LK");
			SW.Click("GC_CreateSPGOffer_LK");
			SW.Click("GCCreatePostStayOffer_LK");
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			String sEndDate=SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 25, Calendar.DATE);
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB", sEndDate);
			SW.DropDown_SelectByText("GCCreateResConf_Language_DD", "Japanese (Japan)");
			SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
			SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
			SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
			if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
				Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");
				Reporter.Write("OfferSetUp Page", "OfferSetUp Page naviagation should be SuccessFull", "OfferSetUp Page naviagation SuccessFull", "PASS");
			}else{
				Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
			}
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
			if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
				Reporter.Write("Eligibility Criteria Page", "Eligibility Criteria Page naviagation should be SuccessFull", "Eligibility Criteria Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			SW.WaitTillElementToBeClickable("GCCreateSPGPostStay_MsgSetUPIMgExpand_IC");
			SW.NormalClick("GCCreateSPGPostStay_MsgSetUPIMgExpand_IC");
			if(!SW.ObjectExists("GCCreateSPGPostStay_MsgSetUPIMgSelect_IC"))
				SW.NormalClick("GCCreateSPGPostStay_MsgSetUPIMgExpand_IC");
			SW.NormalClick("GCCreateSPGPostStay_MsgSetUPIMgSelect_IC");
			SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
			SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			SPGOfferTitle = "SPGOffer"+SW.RandomString(10);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",SPGOfferTitle);
			SW.SwitchToFrame("");
			SW.SelectRadioButton("GCCreateBrandSurvey_SupressActiosInd_RB");
			SW.Click("GCCreateResConf_Continue_BN");

			SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
			if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
				SW.FailCurrentTest("MessageSetup Page naviagation Failed");
			}
			SW.DropDown_SelectByText("GCCreateResConf_Style_DD","SHERATON");
			SW.WaitTillElementToBeClickable("GCCreateSPG_LandingImageExpand_IC");
			SW.NormalClick("GCCreateSPG_LandingImageExpand_IC");
			if(!SW.ObjectExists("GCCreateSPG_LandingImageSelect_IC"))
				SW.NormalClick("GCCreateSPG_LandingImageExpand_IC");
			SW.NormalClick("GCCreateSPG_LandingImageSelect_IC");
			SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
			SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",SPGOfferTitle);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
				Reporter.Write("MessageSetup Page", "MessageSetup Page naviagation should be SuccessFull", "MessageSetup Page naviagation SuccessFull", "PASS");
				SW.EnterValue("GCCreateResConf_Rank_EB","1");
				SW.Click("GCCreateResConf_RankMove_BT");
			}
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
			if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
				Reporter.Write("Landing Page", "Landing Page naviagation should be SuccessFull", "Landing Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Landing Page naviagation Failed");
			}
			SW.Click("GCCreateResConf__Submit_BN");
			SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
			if (SW.ObjectExists("GCHome_GreenMsg_DT")){
				String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,sSuccessMessage );
				String sOfferId=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
				Environment.loger.log(Level.INFO,"Offer is created successfully");
				Reporter.Write("Offer Creation", "Offer Creation should be SuccessFull", "Offer Creation is SuccessFull :"+sOfferId, "PASS");
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
							//	SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
								SW.WriteToEmailTestData(TestCaseName, "ValiadtionString2", SPGOfferTitle);
							/*	if(SW.ObjectExists("GCNavigation_SignOut_LK")){
									SW.Click("GCNavigation_SignOut_LK");
								}*/
							}

							else{
								Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
								SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
								SW.FailCurrentTest("Offer Id Activation Failed");
							}
						}else
						{
							Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
							SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
							SW.FailCurrentTest("Offer Id Publish Failed");
						}
					}	else{
						Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
						SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
						SW.FailCurrentTest("Offer Id Generation Failed");
					}
				}	else{
					Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
					SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
					SW.FailCurrentTest("Offer Id Approval Failed");
				}
			}
			else{
				Environment.loger.log(Level.ERROR,"Error Occured after Submit");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Error Occured after Submit");
			}	
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured-",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Exception  Occured ");
		}
	}
	@Test(priority=3,dependsOnMethods={"GCCreateSPGPostStayoffer"})
	public void BopRefresh(){
		SW.LaunchBrowser(Environment.BOB);
		SW.BopLogin(sUserName,sPassword);
		SW.Click("BopHome_GCAdmin_Lk");
		SW.WaitTillElementToBeClickable("BopAdmin_Misc_Lk");
		SW.NormalClick("BopAdmin_Misc_Lk");
		SW.WaitTillElementToBeClickable("BopMisc_BeanShell_LK");
		SW.NormalClick("BopMisc_BeanShell_LK");
		SW.EnterValue("BopBeanShell_Query_EB", "com.starwood.gcp.app.offer.OffersCache.refreshCache();");
		if(SW.ObjectExists("BopeBeanShell_Execute_BN")){
			SW.Click("BopeBeanShell_Execute_BN");
			Environment.loger.log(Level.INFO,"Bop Refreshed successfully");
			Reporter.Write("Bop Refresh", "Bop Refreshed should be successfull", "Bop Refreshed successfully", "PASS");
		}else{
			Environment.loger.log(Level.ERROR,"Bop Refresh failed");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Bop Refresh failed");
		}
	}
	@Test(priority=4,dependsOnMethods={"BopRefresh"})
	public void CreateReservation(){

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		sPropertyId=SoapUtility.getXMLElementText(soapRequest, "BinderDTO", "propertyId");
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
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

		//SoapUtility.printSOAPResponse(newSoapMessage);
		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
		boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
		if(result){
			ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
			Reporter.Write("Reservation Confirmation Number", "Reservation should be Created successfully ", "Reservation Created successfully : "+ReservationNumber, "PASS");
			SW.WriteToEmailTestData(TestCaseName, "ReservationNumber", ReservationNumber);

		}
		else{
			Environment.loger.log(Level.INFO, "Error in SOAP Response see response file for more details");
			SoapUtility.printSOAPResponse(soapResponse);// To print response in console 
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}
	}
	@Test(priority=5, dependsOnMethods="CreateReservation")
	public void DataBaseUpdate(){
		try{	
			//update the guest details in DB	
			SW.Wait(20);//Explicit wait before changing the Arrival and deparute dates
			SW.EstablishConnection(Environment.getRunEnvironment());
			String UpdateQuery="update reservation_header set arrival_dt=to_date( to_char(sysdate - 6,'mm/dd/yyyy'),'mm/dd/yyyy'),departure_dt=to_date( to_char(sysdate - 1, 'mm/dd/yyyy'),'mm/dd/yyyy'),guest_status='COUT' where reservation_confirmation_num IN("+ReservationNumber+")";
			SW.UpdateQuery(UpdateQuery);
			Reporter.Write("Database Update", "Database should be updated successfully", "Database Update successfull ", "PASS");

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Failed during db Update");
		}	
	}
	@Test(priority=6, dependsOnMethods="DataBaseUpdate")
	public void RunOutStayTask(){
		try{
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(sUserName,sPassword);
			SW.Click("BopHome_GCAdmin_Lk");
			SW.NavigateTo(Environment.BoBTaskRunner);
			SW.Click("BoBConfigFactory_Lk");
			String sOldValue=SW.GetText("BoB_Config_Property_EB");
			String sNewvalue=sOldValue+","+sPropertyId;
			SW.EnterValue("BoB_Config_Property_EB", sNewvalue);
			SW.NormalClick("BoB_TaskRunner_Update_BT");
			SW.Click("BoBTaskRunner_Lk");
			SW.EnterValue("BoB_TaskRunner_Pwd_EB","SGC");
			SW.Click("BOB_TaskRunner_OutStayTask_BT");
			String sSucessMessagge="BoB_TaskRunner_SuccessMsg_DT";
			if(SW.ObjectExists(sSucessMessagge)){
				Environment.loger.log(Level.INFO,"OutStay Task Run SuccessFull");
				Reporter.Write("OutStay Task", "OutStay Task Run should be SuccessFull", "OutStay Task Run SuccessFull", "PASS");
			}else
			{
				Environment.loger.log(Level.ERROR, "OutStay Task Run failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("OutStay Task Run failed");
			}

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed During OutStayTaskRun",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("OutStay Task Run failed");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
