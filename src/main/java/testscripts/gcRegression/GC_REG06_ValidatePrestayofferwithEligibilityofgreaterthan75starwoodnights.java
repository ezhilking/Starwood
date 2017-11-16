package testscripts.gcRegression;
/** Purpose		: This is to Validate FP Property Pre-Stay Offer creation  with Property offer Total Starwood Nights eligibility greater than 75  in Italian language
 * TestCase Name: ValidatePrestayofferwithEligibilityofgreaterthan75starwoodnights
 * Created By	: Sharanya Bannuru
 * Modified By	: Sachin
 * Modified Date: 6/21/2016
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

public class GC_REG06_ValidatePrestayofferwithEligibilityofgreaterthan75starwoodnights {

	CRM SW = new CRM();	
	String sMessage,UserName, Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	@Test(priority=1)
	public void GCCreateResPrestayoffer(){

		SW.GCLogin(UserName,Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");

		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreatePreStayOffer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",SW.TestData("GCPropertyID"));
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
			Reporter.Write("Validate Offer Setup Page Navigation", "OfferSetUp Page naviagation SuccessFull", "OfferSetUp Page naviagation Failed", "Fail");
		}
		SW.DropDown_SelectByText("GCCreateResConf_Eligibility_DD","Total Starwood Nights");	
		SW.Wait(10);
		SW.SelectRadioButton("GCCreateResConf_EligibleGT_RB");
		SW.EnterValue("GCCreateResConf_EligibleValue_EB","75");
		SW.Click("GCCreateResConf_EligibleInclude_BT");
		//SW.WaitForPageload();
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL_RB");
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			Reporter.Write("Validate Eligibility Criteria Page Navigation", "Eligibility Criteria Page naviagation SuccessFull", "Eligibility Criteria Page naviagation Failed", "Fail");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");

		SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		if(!SW.ObjectExists("GCCreateResConf_PropertyImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		SW.NormalClick("GCCreateResConf_PropertyImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GCCreateSPGPostStay_OfferTitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		sMessage = SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeClickable("GCCreateResConf_suppressCallToActionInd_RB");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			SW.FailCurrentTest("MessageSetup Page naviagation Failed");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		if(!SW.ObjectExists("GCCreateResConf_LandingImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
		SW.SwitchToFrame("");
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
						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
						SW.FailCurrentTest("Offer Id Publish Failed");
					}
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
					SW.FailCurrentTest("Offer Id Generation Failed");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
				SW.FailCurrentTest("Offer Id Approval Failed");
			}
		}else{
			Environment.loger.log(Level.ERROR,"Error Occured after Submit");
			SW.FailCurrentTest("Error Occured after Submit");
		}
	}	
	@Test(priority=2,dependsOnMethods={"GCCreateResPrestayoffer"})
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
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
