 	package testscripts.gcRegression;
/** Purpose		: Validate LM CRM Reservation Confirmation landing page while creating offer in English language
 * TestCase Name: Validate LM CRM Reservation Confirmation landing page while creating offer in English language
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 * Comments 	: Partial Automation(open issue in selenium IE driver, Cookies are getting cleared in new window hence application will ask for login) 
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG20_ValidateLMCRMBrandResConOfferLandingPageEnglish {

	CRM SW = new CRM();	
	String SelectedOfferDesc,SelectedOfferImageName, SelectedLandingPageImageName, LandingPageDesc;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
	}
	@Test(priority=1)
	public void GCCRMResConOfferEnglish(){

		SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));

		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GC_CreateCRMOffer_LK");
		SW.Click("GCCreateResConfOffer_LK");
		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
		
		SW.DropDown_SelectByText("GCCreateBrand_OfrPlacement_DD", "OMA First Placement");
		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("//select[@name='localeName']","English (United States)");			// localeName
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
		if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
			Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");	
		}else{
			Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
			SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
		}
		
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.WaitForPageload();
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
		}
		SW.WaitTillElementToBeClickable("GCCreateCRMRescon_MsgSetUPIMgExpand_IC");

		SW.NormalClick("GCCreateCRMRescon_MsgSetUPIMgExpand_IC");
		/*if(!SW.ObjectExists("GCCreateCRMRescon_MsgSetUPIMgExpand_IC"))
			SW.NormalClick("GCCreateCRMRescon_MsgSetUPIMgExpand_IC");*/
		SW.NormalClick("GCCreateCRMRescon_MsgSetUPIMgExpandSelect_IC");
		SelectedOfferImageName=SW.GetText("GCCreateCRMRescon_MsgSetUPIMgExpandSelectedImage_ST");
		
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SelectedOfferDesc = "Sample Offer"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",SelectedOfferDesc);
		
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		//SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		SW.WaitForPageload();
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
		}
		
		SW.WaitTillElementToBeClickable("GCCreateCRMRescon_MsgLandingIMgExpand_IC");
		SW.NormalClick("GCCreateCRMRescon_MsgLandingIMgExpand_IC");
		/*if(!SW.ObjectExists("GCCreateCRMRescon_MsgLandingIMgExpandSelect_IC"))
			SW.NormalClick("GCCreateCRMRescon_MsgLandingIMgExpand_IC");*/
		SW.NormalClick("GCCreateCRMRescon_MsgLandingIMgExpandSelect_IC");
		SelectedLandingPageImageName=SW.GetText("GCCreateCRMRescon_MsgLandingIMgExpandSelect_IC");  //
		
		//SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		LandingPageDesc="Langing page"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",LandingPageDesc);
		SW.SwitchToFrame("");
		SW.DropDown_SelectByText("GCCreateCRMRescon_TemplateStyle_DD", "LE MERIDIEN");
		SW.RunJavaScript("popupPreview('newLandingPagePreview','42612005');");
		SW.Click("GCCreateCRMRescon_Save&Preview_BT");
		SW.Wait(10);
	}
	@Test(priority=2,dependsOnMethods="GCCRMResConOfferEnglish")
	public void ValidateEmailPreviewInLandingPage()
	{
		
		//SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		if(SW.ObjectExists("//td[@id='headerImage']//img[contains(@src,'"+SelectedLandingPageImageName+"')]")){
			Environment.loger.log(Level.INFO,"Same image is displayed in the Preview");
		}else{
			Environment.loger.log(Level.ERROR,"Same image is not displayed in the Preview");
			SW.FailCurrentTest("Same image is not displayed in the Preview");
		}
		
		String PreviewText=SW.GetText("GCCreateResConf_Welcome_ST");
		if(SW.CompareTextContained(LandingPageDesc, PreviewText)){
			Environment.loger.log(Level.INFO,"landing page Description is present in the Preview");
		}else{
			Environment.loger.log(Level.ERROR,"landing page Description is present in the Preview");
			SW.FailCurrentTest("landing page Description is present in the Preview");
		}
		
		SW.CloseOnlyThisBrowser();
	}
	
	@Test(priority=3,dependsOnMethods="GCCRMResConOfferEnglish")
	public void ValidateEmailPreviewInOfferOverviewPage(){
		
		SW.SwitchToWindow(1);
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
			SW.FailCurrentTest("Landing Page naviagation Failed");
		}
		SW.EnterValue("GCCreateResConf_Rank_EB","1");
		SW.Click("GCCreateResConf_RankMove_BT");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateCRMRescon_Save&Preview_BT");
		if(SW.ObjectExists("GCCreateCRMRescon_Save&Preview_BT")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
			SW.FailCurrentTest("Landing Page naviagation Failed");
		}
		SW.Click("GCCreateCRMRescon_Save&Preview_BT");
		SW.WaitForWindowCount(2);
		SW.WaitForPageload();
		SW.SwitchToWindow(2);
		SW.Click("//a[text()='"+SelectedOfferDesc+"']");
		SW.WaitForWindowCount(3);
		SW.SwitchToWindow(3);
		
		if(SW.ObjectExists("//td[@id='headerImage']//img[contains(@src,'"+SelectedOfferImageName+"')]")){
			Environment.loger.log(Level.INFO,"Same image is displayed in the Preview");
		}else{
			Environment.loger.log(Level.ERROR,"Same image is not displayed in the Preview");
			SW.FailCurrentTest("Same image is not displayed in the Preview");
		}
		
		String PreviewText=SW.GetText("GCCreateResConf_Welcome_ST");
		if(SW.CompareTextContained(LandingPageDesc, PreviewText)){
			Environment.loger.log(Level.INFO,"landing page Description is present in the Preview");
		}else{
			Environment.loger.log(Level.ERROR,"landing page Description is present in the Preview");
			SW.FailCurrentTest("landing page Description is present in the Preview");
		}
		SW.CloseAllChildBroswer();
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("");
		
		
	}	
	
	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}
