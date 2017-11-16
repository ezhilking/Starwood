package testscripts.gcRegression;
/** Purpose		: Validate Sheraton Non TCP Multi Rate reservation email in English language with Property Offer eligibility with SPG promo and 3 social media icons
 * TestCase Name: GC_REG17_ValidateMultiRateResResconwithspgPromo
 * Created By	: Sharanya Bannuru
 * Modified By	: Sindhu SR
 * Modified Date: 5/19/2017
 * Reviewed By	:	
 * Reviewed Date:
 * Comments 	: SPG Promo can not be added for the multiple rate plans hence removing the promo and checking for multi rate 
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

public class GC_REG17_ValidateMultiRateResResconwithspgPromo {

	CRM SW = new CRM();  
	String OfferTitle;
	String ReservationNumber,UserName,Password;
	String PropID;
	String TestCaseName= getClass().getName();
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
			PropID="110";
			RequestXMLFile= "GC_REG17_ValidateMultiRateResResconwithspgPromo.xml";	
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			PropID="110";
			RequestXMLFile= "GC_REG17_QA2_ValidateMultiRateResResconwithspgPromo_376.xml";
		}
	}

	
	@Test(priority=1)
	public void GCCreateResConfOfferWithPromo(){

		SW.GCLogin(UserName,Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreateResConfOffer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",PropID);
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
		//SW.DropDown_SelectByText("GCCreateResConf_Eligibility_DD","SPG Promo"); 
		//SW.WaitForPageload();
		//SW.Wait(10);
		//SW.EnterValue("//input[@name='moduletextTBL']","DAT - DELOITTE & TOUCHE 4 PTS PLEASANTON");
		//SW.Wait(10);
		//SW.Click("//input[@name='addbutton22']");
		//SW.Click("//input[@value='Include']");
		//SW.WaitForPageload();
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
		}else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
		}
		//SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");
		SW.Wait(10);
		SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		/*if(!SW.ObjectExists("GCCreateResConf_PropertyImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");*/
		SW.NormalClick("GCCreateResConf_PropertyImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateResConf_OfferTitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		OfferTitle = "PropOffer"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
		}else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		/*if(!SW.ObjectExists("GCCreateResConf_LandingImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_LandingImageExpand_IC"); */
		SW.NormalClick("GCCreateResConf_LandingImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){

			SW.EnterValue("GCCreateResConf_Rank_EB","1");
			SW.Click("GCCreateResConf_RankMove_BT");
		}

		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
		if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
		}else{
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
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString4", OfferTitle);
							SW.NormalClick("GC_MyAccount_IC");
							SW.WaitTillElementToBeClickable("GC_MyAccount_SignOut_LK");
							if(SW.ObjectExists("GC_MyAccount_SignOut_LK")){
								SW.Click("GC_MyAccount_SignOut_LK");
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

	}    

	@Test(priority=2,dependsOnMethods={"GCCreateResConfOfferWithPromo"})
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
			SW.FailCurrentTest("Bop Refresh failed");
		}                                                
	}

	@Test(priority=3,dependsOnMethods={"BopRefresh"})
	public void ValidateEditGCSetup(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(UserName,Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetUp_Active_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", PropID);
		SW.Click("GCSetUp_Apply_BN");
		if(!SW.ObjectExists("GCOffer_Overview_IC")){
			Environment.loger.log(Level.ERROR, "Overview Icon is not present");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Overview Icon is not present");
		}
		
		
		// Edited code
		
		if(!SW.ObjectExists("GCSetUp_EditActive_BN")){
			SW.Click("//span[@id='filterLink5']/a");
			SW.EnterValue("//input[id='searchCriteriaValue']", PropID);
			SW.Wait(6);
			SW.Click("//input[value='Submit']");
			SW.Wait(6);
			SW.Click("GCSetUp_Approve_LK");
			SW.Click("GCSetUp_Activate_LK");
			SW.Click("GCSetUp_Active_LK");
			SW.EnterValue("//input[id='searchCriteriaValue']", PropID);
			SW.Wait(6);
			SW.Click("//input[value='Submit']");
			SW.Wait(6);	
			
		}
		
		
		// Edited code
		//SW.Click("GCOffer_Overview_IC");
		SW.Wait(5);
		SW.Click("GCSetUp_EditActive_BN");
		if(SW.ObjectExists("GCSetUp_TwiterImageResConf_CB")&& SW.ObjectExists("GCSetUp_FacebookImageResConf_CB")){

			SW.CheckBox("GCSetUp_FacebookImageResConf_CB", "ON");
			String FbURL="https://facebook.com";
			SW.EnterValue("GCSetUp_FBURL_EB",FbURL );
			SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", FbURL);

			SW.CheckBox("GCSetUp_TwiterImageResConf_CB","ON");
			String TwiterURL="https://twiter.com";
			SW.EnterValue("GCSetUp_TwitterURL_EB", TwiterURL);
			SW.WriteToEmailTestData(TestCaseName, "ValiadtionString2", TwiterURL);

			SW.CheckBox("GCSetUp_FlickerImageResConf_CB","ON");
			String FlickerURL="https://flicker.com";
			SW.EnterValue("GCSetUp_FlickerURL_EB", FlickerURL);
			SW.WriteToEmailTestData(TestCaseName, "ValiadtionString3", FlickerURL);

			Environment.loger.log(Level.INFO, "Facebook Twiter and Flicker buttons are selected ");


		}else{
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
			//SW.Click("GCSetup_Logout_LK");
			SW.Click("//div[@id='dropDownContent']//a//b[text()='Sign Out']");
			Environment.loger.log(Level.INFO, "logout Successfull");
		}
	}

	@Test(priority=5, dependsOnMethods="ValidateEditGCSetup")
	public void RefreshGripCache(){
		boolean node1Login=false;
		boolean node2Login=false;
		SW.LaunchBrowser(Environment.GRIP1URL);
		if(SW.LoginToGrip(UserName,Password)){
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
		if(SW.LoginToGrip(UserName,Password)){

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
		if(SW.LoginToSeas(UserName,Password)){
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
		if(SW.LoginToSeas(UserName,Password)){
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
		}else{
			Environment.loger.log(Level.ERROR, "Seas Cache refresh Failed in Both the Nodes");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Seas Cache refresh Failed in Both the Nodes");
		}
	}

	@Test(priority=7, dependsOnMethods="RefreshSeasCache")
	public void CreateReservationForEditedProperty(){

		try {
			//call to soap server 
			SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
			//Modify the SOAP Request as per the requirement 
			SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

			String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
			String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 17, Calendar.DATE);

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
			SoapUtility.printSOAPResponse(newSoapMessage);
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
		} catch (Exception e) {
			Environment.loger.log(Level.INFO, "Exception Occured- "+e);
		}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();                      
	}
}
