package testscripts.gcRegression;
/** Purpose		: Validate STARHOT/STARFRIEND Tribute Pre-Stay email in Korean language with EGN offer
 * TestCase Name: Validate STARHOT/STARFRIEND Tribute Pre-Stay email in Korean language with EGN offer
 * Created By	: Noopur
 * Modified By	: Sachin
 * Modified Date: 7/13/2016
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

public class GC_REG50_ValidateSTARHOTTributePreStayKoreanWithEGN {
	
	CRM SW = new CRM();  
	String OfferTitle,UserName, Password;
	String TestCaseName= getClass().getName();
	String ReservationNumber;
	String PropertyID,RequestXMLFile;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();

		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG50_STARHOTTributeKorian_1471_QA3.xml";
			PropertyID="1471";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "";//TODO Need to change the value once we get xml from the test data team 
			PropertyID="";//TODO Need to change the value once we get xml from the test data team
		}
	}
	@Test(priority=1)
	public void GCCreateEGNoffer(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GCCreatePropertyOffer_LK");
			SW.Click("GCCreateEGNOffer_LK");
			SW.EnterValue("GCCreateResConf_PropertyId_EB",PropertyID);
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.DropDown_SelectByText("GCCreateResConf_Language_DD","Korean (Korea)");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
			if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
				Environment.loger.log(Level.INFO,"EGNSetUp Page naviagation SuccessFull");	
			}else{
				Environment.loger.log(Level.ERROR,"EGNSetUp Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("EGNSetUp Page naviagation Failed");
			}
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitForPageload();
			SW.Wait(10);
			SW.WaitTillPresenceOfElementLocated("GC_EGNText_FR");
			SW.Wait(10);
			if(SW.ObjectExists("GC_EGNText_FR")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			SW.SwitchToFrame("GC_EGNText_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			OfferTitle ="Offer"+SW.RandomString(15);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"Message SetUP SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Message SetUP Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Message SetUP Failed");
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
				String sOfferId=sSuccessMessage.substring(sSuccessMessage.indexOf("EGN")+3, sSuccessMessage.indexOf("'s")).trim();
				Environment.loger.log(Level.INFO,"Offer is created successfully");
				SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
				SW.Click("GCOfferCreate_Submit_BN");
				SW.WaitTillElementToBeClickable("GCOffer_ApproveIt_IC");
				if(SW.ObjectExists("GCOffer_ApproveIt_IC")){
					SW.Click("GCOffer_ApproveIt_IC");
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

	@Test(priority=2,dependsOnMethods={"GCCreateEGNoffer"})
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
	@Test(priority=3,dependsOnMethods="BopRefresh")
	public void CreateNewReservation() {
		try {
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Get the values for the cancellation from the reservation req
			//SoapUtility.printSOAPResponse(soapRequest);
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			//PropertyId=SoapUtility.getXMLElementText(soapRequest, "BinderDTO", "propertyId");

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 10, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 11, Calendar.DATE);
			
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
		} catch (Exception e) {
			Environment.loger.log(Level.INFO, "Exception Occured- "+e);
		}
	}

	@Test(priority=4, dependsOnMethods="CreateNewReservation")
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

	@Test(priority=5, dependsOnMethods="DataBaseUpdate")
	public void RunOutStayTask(){
		try{
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(UserName,Password);
			SW.Click("BopHome_GCAdmin_Lk");
			SW.NavigateTo(Environment.BoBTaskRunner);
			SW.Click("BoBConfigFactory_Lk");
			String sOldValue=SW.GetText("BoB_Config_Property_EB");
			String sNewvalue=sOldValue+","+PropertyID;
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
