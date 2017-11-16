package testscripts.gcRegression;
import org.apache.commons.io.input.SwappedDataInputStream;
/** Purpose		: Validate the image loading while editing brand rescan offer in Staging state and on selecting new image while editing
 * TestCase Name: Validate the image loading while editing brand rescan offer in Staging state and on selecting new image while editing
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG21_ValidateImageLoadingWhileEditingBrandRescanOfferStagingState {

	CRM SW = new CRM();	
	String UserName,Password,OfferTitle, LandingPageDescription, OfferID,SelectedOfferImageName1,SelectedLandingPageImageName1,SelectedOfferImageName2,SelectedLandingPageImageName2;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
		
	}
	@Test(priority=1)
	public void GCBrandResConOfferEnglish(){

		SW.GCLogin(UserName,Password);

		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreateBrandOffer_LK");
		SW.Click("GCCreateResCanfOffer_LK");
		SW.DropDown_SelectByValue("GCCreateBrand_SelectBrand_DD", "27");//27 for selecting the LM
		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
		SW.DropDown_SelectByText("GCCreateBrand_OfrPlacement_DD", "Brand Offer");
		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		//SW.DropDown_SelectByText("GCCreateResConf_Language_DD","English (United States)");
		SW.DropDown_SelectByText("//*[@id='dropDown']", "English (United States)");
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
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
		}
		//SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");
		SW.Wait(5);
		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgslect_IC"))
			SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");

		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslect_IC");

		//SelectedOfferImageName1=SW.GetText("GCCreateCRMRescon_MsgSetUPIMgExpandSelectedImage_ST");
		SelectedOfferImageName1=SW.GetText("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		OfferTitle = "SampleOffer"+SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
		}
		//SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");
		SW.Wait(8);
		SW.NormalClick("GCCreateBrandRescon_LandingPageIMgExpand_IC");
		if(!SW.ObjectExists("GCCreateBrandRescon_LandingPageIMgSelect_IC"))
			SW.NormalClick("GCCreateBrandRescon_LandingPageIMgExpand_IC");
		SW.NormalClick("GCCreateBrandRescon_LandingPageIMgSelect_IC");

		//SelectedLandingPageImageName1=SW.GetText("GCCreateCRMRescon_MsgLandingExpandSelectedImage_ST");
		//SelectedLandingPageImageName1=SW.GetText("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
		SelectedLandingPageImageName1=SW.GetText("//li[@id='landing.brand.images1']//a");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		LandingPageDescription="SampleLandingPage"+ SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",LandingPageDescription);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		//SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
		SW.Wait(6);
		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
			SW.FailCurrentTest("Landing Page naviagation Failed");
		}
		SW.EnterValue("GCCreateResConf_Rank_EB","1");
		SW.Click("GCCreateResConf_RankMove_BT");
		SW.Click("GCCreateResConf_Continue_BN");
		//SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
		SW.Wait(6);
		if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
			SW.FailCurrentTest("Landing Page naviagation Failed");
		}
		SW.Click("GCCreateResConf__Submit_BN");
		//SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
		SW.Wait(6);
		if (SW.ObjectExists("GCHome_GreenMsg_DT")){
			String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
			Environment.loger.log(Level.INFO,sSuccessMessage );
			OfferID=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
			Environment.loger.log(Level.INFO,"Offer is created successfully");
		}			
	}	
	@Test(priority=2,dependsOnMethods={"GCBrandResConOfferEnglish"})
	public void ValidateImageChanges(){
		SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
		SW.Click("GCOfferCreate_Submit_BN");
		SW.Wait(6);
		//SW.WaitTillElementToBeClickable("GCOffer_Overview_IC");
		if(SW.ObjectExists("GCOffer_Overview_IC")){
			SW.Click("GCOffer_Overview_IC");
			SW.WaitForPageload();
		}
		else{
			Environment.loger.log(Level.ERROR,"Overview button is not present");
			SW.FailCurrentTest("Overview button is not present");
		}

					//Change Image selection in offer page
		SW.WaitTillElementToBeClickable("GCOfferOverview_MessageOverview_IC");
		SW.Click("GCOfferOverview_MessageOverview_IC");
		SW.WaitTillElementToBeClickable("GCOfferMessageSetup_ImageSelection_LK");
		SW.Click("GCOfferMessageSetup_ImageSelection_LK");
		SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
		SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslectSecond_IC");
		SelectedOfferImageName2=SW.GetText("GCCreateCRMRescon_MsgSetUPIMgExpandSelectedImage_ST");
		SW.Click("GCCreateResConf_Continue_BN");

		//change image selection in Offer landing page
		SW.WaitTillElementToBeClickable("GCOfferMessageSetup_ImageSelection_LK");
		SW.Click("GCOfferMessageSetup_ImageSelection_LK");
		SW.WaitTillElementToBeClickable("GCCreateBrandRescon_LandingPageIMgExpand_IC");
		SW.NormalClick("GCCreateBrandRescon_LandingPageIMgSelectSecond_IC");
		SelectedLandingPageImageName2=SW.GetText("GCCreateBrandRescon_LandingPageIMgSelectSecond_IC");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_Continue_BN");
		SW.Click("GCCreateResConf_Continue_BN");

		Environment.loger.log(Level.INFO,"First time selected Image in Offer creation page :"+SelectedOfferImageName1);
		Environment.loger.log(Level.INFO,"Second time selected Image in Offer Creation Page :"+SelectedOfferImageName2);

		//TODO validate the image selected second time

		if(SW.ObjectExists("//form[@name='offerForm']//table//td[1]//div[3]//img[contains(@src,'"+SelectedOfferImageName2+"')]")){
			Environment.loger.log(Level.INFO,"Second time selected Image is present in the overview page");
		}
		else{
			Environment.loger.log(Level.ERROR,"Second time selected image is not present in the overview page");
			SW.FailCurrentTest("");
		}
		Environment.loger.log(Level.INFO,"First time selected Image in Offer Landing page :"+SelectedLandingPageImageName1);
		Environment.loger.log(Level.INFO,"Second time selected Image in Offer Landing Page :"+SelectedLandingPageImageName2);
		if(SW.ObjectExists("//form[@name='offerForm']//table//td[2]//div[2]//img[contains(@src,'"+SelectedLandingPageImageName2+"')]")){
			Environment.loger.log(Level.INFO,"Second time selected Image is present in the overview page");
		}
		else{
			Environment.loger.log(Level.ERROR,"Second time selected image is not present in the overview page");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("//*[@id='dropDownContent']//b");
		Reporter.StopTest();		
	}
}
