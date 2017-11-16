package testscripts.gcRegression;
/** Purpose		: This is to Validate W Hotels Post-stay email in French language with GEI offer 
 * TestCase Name: ValidateGeiPostayEmailforWhotels
 * Created By	: Sharanya Bannuru
 * Modified By	: sachin	
 * Modified Date: 7/15/2016
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

public class GC_REG42_ValidateGeiPostayEmailforWhotels {
	CRM SW = new CRM();	
	String OfferTitle;
	String ReservationNumber,sUserName,sPassword;
	String PropertyID;
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
			RequestXMLFile= "GC_REG42_ValidateGeiPostayEmailforWhotels.xml";
			 PropertyID="1788";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "";//TODO give the XML name once get from test data team
			PropertyID="";
		}
	}
	@Test(priority=1)
	public void GCCreateGEIPostStayoffer(){
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
			SW.DropDown_SelectByText("GCCreateResConf_Language_DD","French (France)");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
			if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
				Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");	
			}else{
				Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
			}	
			SW.Click("GCCreateResConf_Continue_BN");
			if(SW.ObjectExists("GCGEIPostStayOfferTitle_TB")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			OfferTitle = "GEIOffer"+SW.RandomString(15);
			SW.EnterValue("GCGEIPostStayOfferTitle_TB",OfferTitle);
			SW.EnterValue("GCCallToCopyAction_TB","CallToAction"+OfferTitle);
			SW.SwitchToFrame("GC_GEIPostStayMsgSetUp_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB", "Description"+OfferTitle);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
				SW.EnterValue("GCCreateResConf_Rank_EB","1");
				SW.Click("GCCreateResConf_RankMove_BT");
			}
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
			if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
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
				SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
				SW.Click("GCOfferCreate_Submit_BN");
				SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
				if(SW.ObjectExists("GCOffer_Activate_IC")){
					SW.Click("GCOffer_Activate_IC");
					Environment.loger.log(Level.INFO,"Created OfferId "+sOfferId);
					SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", OfferTitle);
					if(SW.ObjectExists("GCNavigation_SignOut_LK")){
						SW.Click("GCNavigation_SignOut_LK");
					}
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

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured-",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Exception  Occured ");
		}
	}

	@Test(priority=2,dependsOnMethods={"GCCreateGEIPostStayoffer"})
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
		}
		else{
			Environment.loger.log(Level.ERROR,"Bop Refresh failed");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Bop Refresh failed");
		}
	}

	@Test(priority=3, dependsOnMethods="BopRefresh")
	public void CreateReservationForWProperty(){

		
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 12, Calendar.DATE);

		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 13, Calendar.DATE);


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

	@Test(priority=4, dependsOnMethods="CreateReservationForWProperty")
	public void DataBaseUpdate(){
		try{	
			//update the guest details in DB				
			SW.EstablishConnection(Environment.getRunEnvironment());
			String UpdateQuery="update reservation_header set arrival_dt=to_date( to_char(sysdate - 6,'mm/dd/yyyy'),'mm/dd/yyyy'),departure_dt=to_date( to_char(sysdate - 1, 'mm/dd/yyyy'),'mm/dd/yyyy'),guest_status='COUT' where reservation_confirmation_num IN("+ReservationNumber+")";
			System.out.println(UpdateQuery);
			SW.UpdateQuery(UpdateQuery);
			/*String query="commit";
			SW.UpdateQuery(query);*/

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
		}	
	}
	
	@Test(priority=5, dependsOnMethods="DataBaseUpdate")
	public void RunOutStayTask(){
		try{
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(sUserName,sPassword);
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
