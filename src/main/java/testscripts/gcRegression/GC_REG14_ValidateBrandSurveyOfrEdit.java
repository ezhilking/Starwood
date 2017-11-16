package testscripts.gcRegression;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		: This is to Validate editing of Aloft  Brand Survey Cancellation offer in English language.
 * TestCase Name: ValidateBrandSurveyOfrEdit
 * Created By	: Sharanya Bannuru
 * Modified By	: Sachin
 * Modified Date: 6/21/2016
 * Reviewed By	:	
 * Reviewed Date:
 */
public class GC_REG14_ValidateBrandSurveyOfrEdit {

	CRM SW = new CRM();	
	String sMessage,UserName,Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	@Test(priority=1)
	public void GCCreateBrandSurveyoffer(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GCCreateBrandOffer_LK");
			SW.Click("GCCreateBrandCancellationOfr_LK");
			SW.DropDown_SelectByIndex("GCCreateBrand_SelectBrand_DD",1);
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			SW.DropDown_SelectByText("GCCreateBrand_OfrPlacement_DD", "Brand Survey Offer");
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.DropDown_SelectByText("//*[@id='dropDown']", "English (United States)");
		//	SW.EnterValue("GC_EnterLanguageText_EB", "English");
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
			}else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			if(SW.ObjectExists("GCCreateResConf_DiffImg_LK")){
				SW.Click("GCCreateResConf_DiffImg_LK");
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
			//SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
			SW.Wait(20);
			
			SW.DropDown_SelectByIndex("GCCreateBrandSurveyType_DD",1);
			
			SW.SwitchToFrame("GCCreateBrandSurveyDesc_FR");
			SW.WaitTillElementToBeClickable("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			sMessage = SW.RandomString(15);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
			SW.SwitchToFrame("");

			SW.SwitchToFrame("GCCreateBrandSurveytitle_FR");
			SW.WaitTillElementToBeClickable("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			sMessage = SW.RandomString(15);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
			SW.SwitchToFrame("");
			
			SW.SelectRadioButton("GCCreateBrandSurvey_SupressActiosInd_RB");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
				SW.EnterValue("GCCreateResConf_Rank_EB","1");
				SW.Click("GCCreateResConf_RankMove_BT");
			}	
			SW.WaitTillElementToBeClickable("GCCreateResConf_Continue_BN");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf__Submit_BN");
			if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
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
					SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
					if(SW.ObjectExists("GCOffer_Activate_IC")){
						SW.Click("GCOffer_Activate_IC");
						Environment.loger.log(Level.INFO,"Created OfferId "+sOfferId);
						SW.NormalClick("GC_MyAccount_IC");
						SW.WaitTillElementToBeClickable("GC_MyAccount_SignOut_LK");
						if(SW.ObjectExists("GC_MyAccount_SignOut_LK")){
							SW.Click("GC_MyAccount_SignOut_LK");
						}
					}else{
						Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
						SW.FailCurrentTest("Offer Id Activation Failed");
					}											
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
					SW.FailCurrentTest("Offer Id Approval Failed");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Error Occured after Submit");
				SW.FailCurrentTest("Error Occured after Submit");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured-",e);
		}
	}	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
