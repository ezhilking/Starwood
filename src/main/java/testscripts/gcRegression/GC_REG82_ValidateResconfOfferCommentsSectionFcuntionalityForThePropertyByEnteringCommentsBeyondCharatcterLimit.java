package testscripts.gcRegression;
/** Purpose		: Validate the Res conf Email  Remarks section for the Property Opted IN_Sheraton_and Guest Opted IN beyond charatcter  limit  in Japanese language
 * TestCase Name: GC_REG82_ValidateResconfOfferCommentsSectionFcuntionalityForThePropertyByEnteringCommentsBeyondCharatcterLimit
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 	
 * Reviewed Date: 
 */

import java.util.List;
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG82_ValidateResconfOfferCommentsSectionFcuntionalityForThePropertyByEnteringCommentsBeyondCharatcterLimit {

	CRM SW = new CRM();
	String Username,Password,sMessage,sMessage1;
	String ErrorMsg="[Error...Please Correct The Following:\nMaxlength For Comments Cannot Exceed 1000 Characters.]";
	List<String> ErrorMsg1;

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");

	}

	@Test 
	public void CommentsCharacterLenght(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");	
		SW.Click("GCCreateResConfOffer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB","110");
		SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
		SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitForPageload();
		SW.Click("GCCreateResConf_Continue_BN");
		SW.Wait(10);
		if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
			Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
			Reporter.Write("Validate navigation from Eligibility Criteria Page to Message SetUp page", "Eligibility Criteria Page naviagation should be SuccessFull", "Eligibility Criteria Page naviagation is SuccessFull", "PASS");
		}	else{
			Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
			Reporter.Write("Validate navigation from Eligibility Criteria Page to Message SetUp page", "Eligibility Criteria Page naviagation SuccessFull", "Eligibility Criteria Page naviagation is failed", "Fail");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyImageExpand_IC");
		SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		SW.Wait(10);
		if(!SW.ObjectExists("GCCreateResConf_PropertyImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_PropertyImageExpand_IC");
		SW.NormalClick("GCCreateResConf_PropertyImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL_RB");
		SW.SwitchToFrame("GC_MessageGreetingFrame_FR");
		SW.Click("DiscSelectTemplate_Entertext_EB");
		sMessage = SW.RandomString(15);
		SW.EnterValue("DiscSelectTemplate_Entertext_EB", sMessage);
		SW.SwitchToFrame("");
		SW.Wait(4);
		SW.SwitchToFrame("GCCreateResConf_OfferTitle_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		sMessage = SW.RandomString(15);
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		if(SW.ObjectExists("GCCreateResConf_suppressCallToActionInd_RB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
			Reporter.Write("Validate navigation from MessageSetup Page to Landing page", "MessageSetupPage naviagation should be SuccessFull", "MessageSetu Page naviagation is SuccessFull", "PASS");
		}	else{
			Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
			Reporter.Write("Validate navigation from Message SetUp page to Landing Page", "MessageSetup Page naviagation SuccessFull", "MessageSetup Page naviagation Failed", "Fail");
		}
		SW.WaitTillElementToBeClickable("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		SW.Wait(10);
		if(!SW.ObjectExists("GCCreateResConf_LandingImageSelect_IC"))
			SW.NormalClick("GCCreateResConf_LandingImageExpand_IC");
		SW.NormalClick("GCCreateResConf_LandingImageSelect_IC");
		SW.SelectRadioButton("GCCreateResConf_suppressCallToActionInd_RB");
		SW.SwitchToFrame("GCCreateResConf_LandingPageDescription_FR");
		SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
		SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
		SW.SwitchToFrame("");
		SW.Click("GCCreateResConf_Continue_BN");
		SW.WaitTillElementToBeVisible("GCCreateResConf_Rank_EB");
		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
			Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Landing Page naviagation Failed");
			Reporter.Write("Validate navigation from Landing page to Ranking page", "Landing Page naviagation SuccessFull", "Landing Page naviagation Failed", "Fail");
		}
		SW.EnterValue("GCCreateResConf_Rank_EB","1");
		SW.Click("GCCreateResConf_RankMove_BT");
		SW.Click("GCCreateResConf_Continue_BN");
		if(SW.ObjectExists("GCCreateResConf__Submit_BN")){
			Environment.loger.log(Level.INFO,"Ranking naviagation SuccessFull");
		}	else{
			Environment.loger.log(Level.ERROR,"Ranking Page naviagation Failed");
			Reporter.Write("Validate navigation from Ranking page to Offer Overview page", "Ranking page naviagation SuccessFull", "Ranking page naviagation Failed", "Fail");
		}
		SW.Click("GCOfferOverView_Comments_EB");
		sMessage = SW.RandomString(1002);
		SW.EnterValue("GCOfferOverView_Comments_EB",sMessage);
		SW.Click("GCCreateResConf__Submit_BN");

		ErrorMsg1=SW.GetAllText("GC_PreHeaderErrorMsg_DT");
		SW.CompareText("Error message validation", ErrorMsg, ErrorMsg1.toString());

		SW.ClearValue("GCOfferOverView_Comments_EB");

		SW.Click("GCOfferOverView_Comments_EB");
		sMessage1 = SW.RandomString(1000);
		SW.EnterValue("GCOfferOverView_Comments_EB",sMessage1);
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
						//	SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
							/*if(SW.ObjectExists("GCNavigation_SignOut_LK")){
								SW.Click("GCNavigation_SignOut_LK");
							}*/
						}else{
							Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
							Reporter.Write("Offer Activation", "Offer Id Activation should be successfull", "Offer Id Activation Failed", "Fail");
						}
					}else{
						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
						Reporter.Write("Offer Publishing", "Offer Id Publishing should be successfull", "Offer Id Publishing Failed", "Fail");
					}
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
					Reporter.Write("Offer Generation", "Offer Id Generation should be successfull", "Offer Id Generation Failed", "Fail");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
				Reporter.Write("Offer Approval", "Offer Id Approval should be successfull", "Offer Id Approval Failed", "Fail");
			}
		}else{
			Environment.loger.log(Level.ERROR,"Error Occured after Submit");
			Reporter.Write("Offer Submission", "Offer submitted successfully", "Error Occured after Submit", "Fail");

		}
	}	

	@AfterClass
	public void EndTest(){

		Reporter.StopTest();		
	}
}


