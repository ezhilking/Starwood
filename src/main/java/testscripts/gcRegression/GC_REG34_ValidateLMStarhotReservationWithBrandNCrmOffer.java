package testscripts.gcRegression;
/** Purpose		:Validate LM StarHot reservation email with new policies added from VP in English language with Brand and CRM offer 
 * TestCase Name: Validate LM StarHot reservation email with new policies added from VP in English language with Brand and CRM offer
 * Created By	: Sharanya
 * Modified By	: Sachin
 * Modified Date: 7/5/2016
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

public class GC_REG34_ValidateLMStarhotReservationWithBrandNCrmOffer {

	CRM SW = new CRM();	
	String OfferTitle;
	String SelectedOfferDesc, LandingPageDesc ,ReservationNumber,UserName,Password;
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
			RequestXMLFile= "GC_REG34_LMReservation_1848_QA3.xml";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			RequestXMLFile= ".xml";
		}
	}
	@Test(priority=1)
	public void GCBrandResConOfferEnglish(){

		SW.GCLogin(UserName,Password);

		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreateBrandOffer_LK");
		SW.Click("GCCreateResConfOffer_LK");
		SW.DropDown_SelectByValue("GCCreateBrand_SelectBrand_DD", "27");
		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
		SW.DropDown_SelectByText("GCCreateBrand_OfrPlacement_DD", "Brand Offer");
		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 16, Calendar.DATE));
		//SW.DropDown_SelectByText("GCCreateResConf_Language_DD","English (United States)");
		SW.DropDown_SelectByText("//select[@name='localeName']", "English (United States)");
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
		SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");

		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgslect_IC"))
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		OfferTitle ="Offer"+SW.RandomString(15);//TODO Add Click
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
							//SW.WaitTillElementToBeClickable("//a//b[text()='Sign Out']");
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", OfferTitle);
							/*if(SW.ObjectExists("//img[@id='accountImage']")){
								SW.Click("//img[@id='accountImage']");
								SW.Click("//a//b[text()='Sign Out']");
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

	}	

	@Test(priority=2,dependsOnMethods={"GCBrandResConOfferEnglish"})
	public void CrmResconOffer(){
		/*SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		if(SW.ObjectExists("//div[@id='message1']")){
			SW.NormalClick("//div[@id='message1']//img[@title='Close']");
		}*/
		SW.Click("GCCreateOffer_LK");
		SW.Click("GC_CreateCRMOffer_LK");
		SW.Click("GCCreateResConfOffer_LK");
		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");

		SW.DropDown_SelectByText("GCCreateBrand_OfrPlacement_DD", "OMA First Placement");
		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 16, Calendar.DATE));
		//SW.DropDown_SelectByText("GCCreateResConf_Language_DD","English (United States)");
		SW.DropDown_SelectByText("//select[@name='localeName']", "English (United States)");
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
		SW.DropDown_SelectByText("GCCreateResConf_Eligibility_DD","Brand");	
		SW.Wait(10);
		SW.DropDown_SelectByText("GCCRMBrandEligibility_DD","Le Méridien");
		SW.Click("GCCreateResConf_EligibleAdd_BT");
		SW.Click("GCCreateResConf_EligibleInclude_BT");
		SW.WaitForPageload();
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitForPageload();
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
		}
		SW.WaitTillElementToBeClickable("GCCreateCRMRescon_MsgSetUPIMgExpand_IC");

		SW.NormalClick("GCCreateCRMRescon_MsgSetUPIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateCRMRescon_MsgSetUPIMgExpand_IC"))
			SW.NormalClick("GCCreateCRMRescon_MsgSetUPIMgExpand_IC");
		SW.NormalClick("GCCreateCRMRescon_MsgSetUPIMgExpandSelect_IC");
		//SelectedOfferImageName=SW.GetText("GCCreateCRMRescon_MsgSetUPIMgExpandSelectedImage_ST");

		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SelectedOfferDesc = "Offer"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",SelectedOfferDesc);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitForPageload();
		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
		}else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
		}

		SW.WaitTillElementToBeClickable("GCCreateCRMRescon_MsgLandingIMgExpand_IC");
		SW.NormalClick("GCCreateCRMRescon_MsgLandingIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateCRMRescon_MsgLandingIMgExpandSelect_IC"))
			SW.NormalClick("GCCreateCRMRescon_MsgLandingIMgExpand_IC");
		SW.NormalClick("GCCreateCRMRescon_MsgLandingIMgExpandSelect_IC");
		//SelectedLandingPageImageName=SW.GetText("GCCreateCRMRescon_MsgLandingExpandSelectedImage_ST");

		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		LandingPageDesc="Langing page"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",LandingPageDesc);
		SW.SwitchToFrame("");
		SW.DropDown_SelectByText("GCCreateCRMRescon_TemplateStyle_DD", "LE MERIDIEN");
		//SW.RunJavaScript("popupPreview('newLandingPagePreview','42612005');");
		SW.Click("GCCreateResConf_Continue_BN");
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
		SW.WaitTillElementToBeClickable("GCCreateResConf_Approve_BT");
		if(SW.ObjectExists("GCCreateResConf_Approve_BT")){
			Environment.loger.log(Level.INFO,"Ranking Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Ranking Page naviagation Failed");
			SW.FailCurrentTest("Ranking Page naviagation Failed");
		}
		SW.Click("GCCreateResConf_Approve_BT");
		if (SW.ObjectExists("GCHome_GreenMsg_DT")){
			String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
			Environment.loger.log(Level.INFO,sSuccessMessage );
			String sOfferId=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
			Environment.loger.log(Level.INFO,"Offer is created successfully");
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
					//	SW.WaitTillElementToBeClickable("//a//b[text()='Sign Out']");
						SW.WriteToEmailTestData(TestCaseName, "ValiadtionString2", SelectedOfferDesc);
						/*if(SW.ObjectExists("//img[@id='accountImage']")){
							SW.Click("//img[@id='accountImage']");
							SW.Click("//a//b[text()='Sign Out']");
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
			Environment.loger.log(Level.ERROR,"Error Occured aftr aproval");
			SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
			SW.FailCurrentTest("Error Occured aftr aproval");
		}

	}
	@Test(priority=3,dependsOnMethods={"CrmResconOffer"})
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
	@Test(priority=4, dependsOnMethods="BopRefresh")
	public void CreateReservationForEditedProperty(){
		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 15, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 16, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);

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
		Reporter.StopTest();		
	}
}
