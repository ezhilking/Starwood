package testscripts.gcRegression;
/** Purpose		: This is to Validate Westin Instay arrival document in English language
 * TestCase Name: GC_REG58_ValidateWestinInstayArrivalDocEnglishlang
 * Created By	: Sharmila Begam
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 	
 * Reviewed Date:
 */
import java.util.Calendar;
import java.util.List;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SoapUtility;
public class GC_REG58_ValidateWestinInstayArrivalDocEnglishlang {

	CRM SW = new CRM();	
	String GEIOfferTitle,SPGOfferTitle;
	String ReservationNumber,sUserName,sPassword;
	String sPropertyID;
	String OfferTitle;
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
			RequestXMLFile= "GC_REG58_WestinEnglish_1967_QA3_New.xml";
			sPropertyID="1967";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "GC_REG58_QA2_WestinEnglish_1402.xml";
			sPropertyID="1402";
		}
	}
	@Test(priority=1)
	public void GCCreateINStayArrivaloffer1(){
		try{
			SW.GCLogin(sUserName,sPassword);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GCCreatePropertyOffer_LK");
			SW.Click("GCCreateInstayArrival_LK");
			SW.EnterValue("GCCreateResConf_PropertyId_EB",sPropertyID);
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			String sEndDate=SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 25, Calendar.DATE);
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB",sEndDate);
			SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
			SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
			SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
			SW.Click("GCCreateResConf_Continue_BN");
			//Eligibility page
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
			SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			if(SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgExpand_IC")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
				Reporter.Write("Eligibility Criteria Page", "Eligibility Criteria Page naviagation should be SuccessFull", "Eligibility Criteria Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			//Message Setup
			SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
			if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
				Reporter.Write("MessageSetup Page", "MessageSetup Page naviagation should be SuccessFull", "MessageSetup Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("MessageSetup Page naviagation Failed");
			}
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			if(!SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgslect_IC"))
				SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
			SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
			OfferTitle ="PropertyOffer"+SW.RandomString(15);
			SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB",OfferTitle);
			SW.Click("GCCreateResConf_Continue_BN");
			
			//ranking
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
				Reporter.Write("Landing Page", "Landing Page naviagation should be SuccessFull", "Landing Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Landing Page naviagation Failed");
			}
			SW.EnterValue("GCCreateResConf_Rank_EB","1");
			SW.Click("GCCreateResConf_RankMove_BT");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
			if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
				Environment.loger.log(Level.INFO,"Ranking naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Ranking Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Ranking Page naviagation Failed");
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
					SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
					if(SW.ObjectExists("GCOffer_Activate_IC")){
						SW.Click("GCOffer_Activate_IC");
						Environment.loger.log(Level.INFO,"Created OfferId "+sOfferId);
						Reporter.Write("Offer Creation", "Offer Creation should be SuccessFull", "Offer Creation is SuccessFull :"+sOfferId, "PASS");
						//SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
						SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", OfferTitle);
					/*	if(SW.ObjectExists("GCNavigation_SignOut_LK")){
							SW.Click("GCNavigation_SignOut_LK");
						}*/
					}else{
						Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
						SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
						SW.FailCurrentTest("Offer Id Activation Failed");
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
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured-",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Exception  Occured ");
		}
	}
	@Test(priority=2,dependsOnMethods={"GCCreateINStayArrivaloffer1"})
	public void GCCreateINStayArrivaloffer2(){
		
			SW.Click("GCCreateOffer_LK");
			SW.Click("GCCreatePropertyOffer_LK");
			SW.Click("GCCreateInstayArrival_LK");
			SW.EnterValue("GCCreateResConf_PropertyId_EB",sPropertyID);
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			String sEndDate=SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 25, Calendar.DATE);
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB",sEndDate);
			SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
			SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
			SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
			SW.Click("GCCreateResConf_Continue_BN");
			//Eligibility page
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
			SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			if(SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgExpand_IC")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
				Reporter.Write("Eligibility Criteria Page", "Eligibility Criteria Page naviagation should be SuccessFull", "Eligibility Criteria Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			//Message Setup
			SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
			if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
				Reporter.Write("MessageSetup Page", "MessageSetup Page naviagation should be SuccessFull", "MessageSetup Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("MessageSetup Page naviagation Failed");
			}
			
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			if(!SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgslect_IC"))
				SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
			
			SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
			OfferTitle ="PropertyOffer"+SW.RandomString(15);
			SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB",OfferTitle);
			SW.Click("GCCreateResConf_Continue_BN");
			
			//ranking
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
				Reporter.Write("Landing Page", "Landing Page naviagation should be SuccessFull", "Landing Page naviagation SuccessFull", "PASS");
			}	else{
				Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Landing Page naviagation Failed");
			}
			SW.EnterValue("GCCreateResConf_Rank_EB","2");
			SW.Click("GCCreateResConf_RankMove_BT");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
			if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
				Environment.loger.log(Level.INFO,"Ranking naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Ranking Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Ranking Page naviagation Failed");
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
					SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
					if(SW.ObjectExists("GCOffer_Activate_IC")){
						SW.Click("GCOffer_Activate_IC");
						Environment.loger.log(Level.INFO,"Created OfferId "+sOfferId);
						//SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
						SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", OfferTitle);
					/*	if(SW.ObjectExists("GCNavigation_SignOut_LK")){
							SW.Click("GCNavigation_SignOut_LK");
						}*/
					}else{
						Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
						SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
						SW.FailCurrentTest("Offer Id Activation Failed");
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
		}
		
	@Test(priority=3,dependsOnMethods={"GCCreateINStayArrivaloffer2"})
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
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 20, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 21, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);

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
			String UpdateQuery="update reservation_header set arrival_dt=sysdate + 1 where reservation_confirmation_num in ("+ReservationNumber+")";
			SW.UpdateQuery(UpdateQuery);
			Reporter.Write("Database Update", "Database should be updated successfully", "Database Update successfull ", "PASS");

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Failed during db Update");
		}	
	}

	@Test(priority=6, dependsOnMethods="DataBaseUpdate")
	public void RunInStayTask(){
		try{
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(sUserName,sPassword);
			SW.Click("BopHome_GCAdmin_Lk");
			SW.NavigateTo(Environment.BoBTaskRunner);
			SW.Click("BoBConfigFactory_Lk");
			String sOldValue=SW.GetText("BoB_Config_Property_EB");
			String sNewvalue=sOldValue+","+sPropertyID;
			SW.DropDown_SelectByText("//select[@name='selectedPropertyOffsetProvider']","com.starwood.gcc.service.offset.MockProperyOffsetProvider");
			SW.EnterValue("BoB_Config_Property_EB", sNewvalue);
			SW.NormalClick("BoB_TaskRunner_Update_BT");
			SW.Click("BoBTaskRunner_Lk");
			SW.EnterValue("BoB_TaskRunner_Pwd_EB","SGC");
			SW.Click("BOB_TaskRunner_InStayTask_BT");
			String sSucessMessagge="BoB_TaskRunner_InstaySuccessMsg_DT";
			if(SW.ObjectExists(sSucessMessagge)){
				Environment.loger.log(Level.INFO,"InStay Task Run SuccessFull");
				Reporter.Write("RunInStayTaskk", "RunInStayTask Run should be SuccessFull", "RunInStayTask Run SuccessFull", "PASS");
			}else
			{
				Environment.loger.log(Level.ERROR, "InStay Task Run failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("InStay Task Run failed");
			}

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed During InStayTaskRun",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("InStay Task Run failed");
		}
	}

	@Test(priority=7, dependsOnMethods="RunInStayTask")
	public void ValidateInStayDoc (){
		try{
			SW.Wait(300);// wait for 5 Min to reflect the document in OMT
			SW.LaunchBrowser(Environment.GCURL);
			SW.GCLogin(sUserName,sPassword);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCDocDelivery_LK");
			SW.EnterValue("GCInstay_DocDelivery_EB", sPropertyID);  
			SW.Click("GCInstay_DocDelivery_BT");
			if(SW.ObjectExists("GCCreateResConf_InstayDocArrTable_WT"))
			{
				List<String> sCnfNum=SW.GetAllText("GCCreateResConf_InstayDocArrResNum_WT");
				System.out.println(sCnfNum);

				if(sCnfNum.contains(ReservationNumber+" "))
				{
					Environment.loger.log(Level.INFO,"InStay Document Delivery SuccessFull  ");
					if(SW.ObjectExists("GCNavigation_SignOut_LK")){
						SW.Click("GCNavigation_SignOut_LK");
					}
				}
				else
				{
					Environment.loger.log(Level.ERROR, "InStay Document Delivery failed");
					SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
					SW.FailCurrentTest("InStay Document Delivery failed");
				}
			}
			else
			{
				Environment.loger.log(Level.ERROR, "NO Entry is Found");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("InStay Document Delivery failed");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed During InStayTaskRun",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("OutStay Task Run failed");
		}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
