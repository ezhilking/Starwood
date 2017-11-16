package testscripts.gcRegression;
/** Purpose		:Validate P100 Element Post-stay email in English language with Property Offer and 3 social media icons
 * TestCase Name: Validate P100 Element Post-stay email in English language with Property Offer and 3 social media icons
 * Created By	: Sharmila Begam
 * Modified By	: sachin	
 * Modified Date: 7/21/2016
 * Reviewed By	: 	
 * Reviewed Date:
 * Comments: No Availability for Element property hence using Westin 1967 
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

public class GC_REG57_ValidateP100ElementPostStayEmailEnglangPropOffand3icons {
	
	CRM SW = new CRM();	
	String OfferTitle;
	String ReservationNumber,sUserName,sPassword;
	String PropertyID,RequestXMLFile;
	String TestCaseName= getClass().getName();
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		sUserName=SW.TestData("GCUsername");
		sPassword=SW.TestData("GCPassword");
		
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			RequestXMLFile= "GC_REG57_P100WestinEnglish_1967_QA3.xml";
			PropertyID="1967";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= "";//TODO Need to update the value once we get the xml from the test data team
			PropertyID="";//TODO Need to update the value once we get the xml from the test data team
		}
	}
	@Test(priority=1)
	public void GCCreatePostStayPropertyoffer(){

		SW.GCLogin(sUserName,sPassword);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreatePostStayOffer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",PropertyID);
		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
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
		//message page
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
			Reporter.Write("Eligibility Criteria Page", "Eligibility Criteria Page naviagation should be SuccessFull", "Eligibility Criteria Page naviagation SuccessFull", "PASS");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
		}
		SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");

		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgslect_IC"))
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
		
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SelectRadioButton("GCCreateBrandSurvey_SupressActiosInd_RB");
		SW.SwitchToFrame("GCCreateSPGPostStay_OfferTitleB_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		OfferTitle ="PropertyOffer"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
			Reporter.Write("MessageSetup Page", "MessageSetup Page naviagation should be SuccessFull", "MessageSetup Page naviagation SuccessFull", "PASS");
		}	else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
		}
		//landing page setup 
		SW.WaitTillElementToBeClickable("GCCreateBrandRescon_LandingPageIMgExpand_IC");
		SW.NormalClick("GCCreateBrandRescon_LandingPageIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateBrandRescon_LandingPageIMgSelect_IC"))
			SW.NormalClick("GCCreateBrandRescon_LandingPageIMgExpand_IC");
		SW.NormalClick("GCCreateBrandRescon_LandingPageIMgSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
		SW.SwitchToFrame("");
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
					}else
					{
						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
						SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
						SW.FailCurrentTest("Offer Id Publish Failed");
					}
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
					SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
					SW.FailCurrentTest("Offer Id Generation Failed");
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
	@Test(priority=2,dependsOnMethods={"GCCreatePostStayPropertyoffer"})
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
	@Test(priority=3,dependsOnMethods={"BopRefresh"})
	
	public void ValidateEditGCSetup(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(sUserName,sPassword);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetUp_Active_LK");
		//SW.EnterValue("GCSetUp_PropertyID_EB", PropertyID);
		SW.EnterValue("GCOffer_SearchCriteria_EB", PropertyID);
		//SW.Click("GCSetUp_Apply_BN");
		SW.Click("GCSetUp_Submit_BN");
		if(!SW.ObjectExists("GCOffer_Overview_IC")){
			Environment.loger.log(Level.ERROR, "Overview Icon is not present");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Overview Icon is not present");
		}
		SW.Click("GCOffer_Overview_IC");
		SW.WaitTillElementToBeVisible("GCSetUp_EditActive_BN");
		SW.Click("GCSetUp_EditActive_BN");
		if(SW.ObjectExists("GCSetUp_FacebookImagePostStay_CB")&& SW.ObjectExists("GCSetUp_FlickerImagePostStay_CB")){
			
			SW.CheckBox("GCSetUp_FacebookImagePostStay_CB", "ON");
			String FbURL="https://facebook.com";
			SW.EnterValue("GCSetUp_FBURL_EB",FbURL );
			//SW.WriteToEmailTestData(TestCaseName, "ValiadtionString2", FbURL);

			SW.CheckBox("GCSetUp_FlickerImagePostStay_CB","ON");
			String FlickerURL="https://flicker.com";
			SW.EnterValue("GCSetUp_FlickerURL_EB", FlickerURL);
			//SW.WriteToEmailTestData(TestCaseName, "ValiadtionString3", TwiterURL);
			
						
			SW.CheckBox("GCSetUp_InstagramImagePostStay_CB","ON");
			String InstagramURL="https://instagram.com";
			SW.EnterValue("GCSetUp_InstagramURL_EB", InstagramURL);
			//SW.WriteToEmailTestData(TestCaseName, "ValiadtionString5", InstagramURL);
			
			Environment.loger.log(Level.INFO, "Facebook Flicker and Instagram buttons are selected ");
			
			}
		else{
			Environment.loger.log(Level.ERROR, "Social Media Icons are not selected" );
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Social Media Icons are not selected");
		}

		SW.Click("GCSetUp_Submit_BN");
		SW.WaitTillElementToBeClickable("GCSetUp_Approve_LK");
		if(!SW.ObjectExists("GCSetUp_Approve_LK")){
			Environment.loger.log(Level.ERROR, "Changes are not moved to Stage");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Changes are not moved to Stage");
		}
		SW.Click("GCSetUp_Approve_LK");
		SW.WaitTillElementToBeClickable("GCSetUp_Activate_LK");
		if(!SW.ObjectExists("GCSetUp_Activate_LK")){
			Environment.loger.log(Level.ERROR, "Changes are not moved to Approved");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Changes are not moved to Approved");
		}
		SW.Click("GCSetUp_Activate_LK");
		SW.WaitTillElementToBeClickable("GCSetUp_EditActivate_LK");
		if(!SW.ObjectExists("GCSetUp_EditActivate_LK")){
			Environment.loger.log(Level.ERROR, "Changes are not moved to Active");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Changes are not moved to Active");
		}
		Environment.loger.log(Level.INFO, "GC Setup has updated and activated ");
		Reporter.Write("GC Setup", "GC Setup should has to be updated and activated ", "GC Setup has updated and activated ", "PASS");
	}
	
	@Test(priority=4, dependsOnMethods="ValidateEditGCSetup")
	public void RefreshGCSetupCache(){

		SW.Click("GCHome_Admin_LK");
		SW.Click("GC_GCAdmin_Lk");
		if(SW.ObjectExists("GC_CacheRefreshTable_TB")){
			SW.Click("GCSetup_SetupCache_LK");
			SW.Click("GCSetup_Content_LK");
			//SW.Click("GCSetup_update_BT");
			Environment.loger.log(Level.INFO, "Cache refreshed successfully");
			Reporter.Write("Cache Refresh", "Cache should be refreshed successfully", "Cache refreshed successfully", "PASS");
			/*SW.Click("GCSetup_Logout_LK");
			Environment.loger.log(Level.INFO, "logout Successfull");*/
		}
	}
	@Test(priority=5, dependsOnMethods="ValidateEditGCSetup")
	public void RefreshGripCache(){
		boolean node1Login=false;
		boolean node2Login=false;
		SW.LaunchBrowser(Environment.GRIP1URL);
		if(SW.LoginToGrip(sUserName,sPassword)){
			boolean Cache1=SW.GripCacheRefresh("com.starwood.gc.app.SgcSetupVersionsCache");
			boolean Cache2=SW.GripCacheRefresh("com.starwood.gcc.cache.SocialMediumMessageLkupCache");
			boolean Cache3=SW.GripCacheRefresh("com.starwood.corona.services.system.data.jdbc.cache.PropertyMasterCache");
			if(Cache1 && Cache2 && Cache3){
				Environment.loger.log(Level.INFO, "All the cache's in grip node 1 refreshed successfully ");
				node1Login=true;
			}else{
				Environment.loger.log(Level.ERROR, "Node 1 Cache refresh Failed");
				node1Login=false;
			}
		}else{
			Environment.loger.log(Level.ERROR, "Grip Node 1 Login Failed");
			node1Login=false;
		}

		//Refresh Second node
		SW.LaunchBrowser(Environment.GRIP2URL);
		if(SW.LoginToGrip(sUserName,sPassword)){

			boolean Cache11=SW.GripCacheRefresh("com.starwood.gc.app.SgcSetupVersionsCache");
			boolean Cache22=SW.GripCacheRefresh("com.starwood.gcc.cache.SocialMediumMessageLkupCache");
			boolean Cache33=SW.GripCacheRefresh("com.starwood.corona.services.system.data.jdbc.cache.PropertyMasterCache");
			if(Cache11 && Cache22 && Cache33){
				Environment.loger.log(Level.INFO, "All the cache's in grip node 2 refreshed successfully ");
				node2Login=true;
			}else{
				Environment.loger.log(Level.ERROR, "Node 2 Cache refresh Failed");
				node2Login=false;
			}
		}else{
			Environment.loger.log(Level.ERROR, "Grip Node 2 Login Failed");
			node1Login=false;
		}
		if(node1Login||node2Login){//if nay one node cache is refreshed successfully
			Environment.loger.log(Level.INFO, "All the cache's in grip nodes refreshed successfully ");
			Reporter.Write("Grip Cache", "All the cache's in grip nodes should has to be refreshed successfully ", "All the cache's in grip nodes refreshed successfully ", "PASS");
		}else{
			Environment.loger.log(Level.ERROR, "Grip Cache refresh Failed in Both the Nodes");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Grip Cache refresh Failed in Both the Nodes");
		}
	}
	@Test(priority=6, dependsOnMethods="ValidateEditGCSetup")
	public void RefreshSeasCache(){
		boolean node1SeasLogin=false;
		boolean node2SeasLogin=false;
		SW.LaunchBrowser(Environment.SEAS1URL);
		if(SW.LoginToSeas(sUserName,sPassword)){
			if(SW.SeasCacheRefresh("com.starwood.corona.services.system.data.jdbc.cache.PropertyMasterCache")){
				Environment.loger.log(Level.INFO, "All the cache's in Seas node 1 refreshed successfully ");
				node1SeasLogin=true;
			}else{
				Environment.loger.log(Level.ERROR, "Seas Node 1 Cache refresh Failed");
				node1SeasLogin=false;
			}
		}else{
			Environment.loger.log(Level.ERROR, "Seas Node 1 Login Failed");
			node1SeasLogin=false;
		}

		//Refresh Second node
		SW.LaunchBrowser(Environment.SEAS2URL);
		if(SW.LoginToSeas(sUserName,sPassword)){
			if(SW.SeasCacheRefresh("com.starwood.corona.services.system.data.jdbc.cache.PropertyMasterCache")){
				Environment.loger.log(Level.INFO, "All the cache's in Seas node 2 refreshed successfully ");
				node2SeasLogin=true;
			}else{
				Environment.loger.log(Level.ERROR, "Seas Node 2 Cache refresh Failed");
				node2SeasLogin=false;
			}
		}else{
			Environment.loger.log(Level.ERROR, "Seas Node 2 Login Failed");
			node2SeasLogin=false;
		}
		if(node1SeasLogin||node2SeasLogin){//if nay one node cache is refreshed successfully
			Environment.loger.log(Level.INFO, "All the cache's in Seas nodes refreshed successfully ");
			Reporter.Write("Seas Cache", "All the cache's in Seas nodes should has to be refreshed successfully ", "All the cache's in Seas nodes refreshed successfully ", "PASS");
		}else{
			Environment.loger.log(Level.ERROR, "Seas Cache refresh Failed in Both the Nodes");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Seas Cache refresh Failed in Both the Nodes");
		}
	}

	@Test(priority=7,dependsOnMethods="RefreshSeasCache")
	public void CreateReservation(){

		try {
			
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Get the values for the cancellation from the reservation req
			
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			//PropertyId=SoapUtility.getXMLElementText(soapRequest, "BinderDTO", "propertyId");

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 20, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 21, Calendar.DATE);
			
			newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);
			//SoapUtility.printSOAPResponse(newSoapMessage);
			SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, Environment.SOAPEndPointURL);
			
			boolean result=SoapUtility.validateSOAPResponseForFault(soapResponse);
			if(result){
				ReservationNumber=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
				Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ReservationNumber);
				Reporter.Write("Reservation Confirmation Number", "Reservation should be Created successfully ", "Reservation Created successfully : "+ReservationNumber, "PASS");
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
	@Test(priority=8, dependsOnMethods="CreateReservation")
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
	@Test(priority=9, dependsOnMethods="DataBaseUpdate")
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
