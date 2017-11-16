package testscripts.gcRegression;
/** Purpose		: Validate Omniture Code creator for Cancellation Offer
 * TestCase Name: Validate Omniture Code creator for Cancellation Offer
 * Created By	: Sharmila Begam
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

public class GC_REG70_ValidateOmnitureCodeCreatorCancellationOffer {
	CRM SW = new CRM();	
	String ReservationNumber,LastName,PhoneNum,CancelationNumber,OfferTitle,UserName,Password;
	String Prop1="1047";
	String URL;
	String URL1,URL2;
	String TestCaseName= getClass().getName();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	@Test(priority=1)
	public void CreateOmnitureCodeCancellationOffer(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GCCreatePropertyOffer_LK");
			SW.Click("GCCreateBrandCancellationOfr_LK");
			SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop1);
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
			SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
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
			SW.WaitTillElementToBeClickable("GCCreateResConf_LandingPageDestinationURL2_RB");
			if(SW.ObjectExists("GCCreateResConf_LandingPageDestinationURL_RB")){
				Environment.loger.log(Level.INFO,"Eligibility Criteria Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"Eligibility Criteria Page naviagation Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Eligibility Criteria Page naviagation Failed");
			}
			if(SW.ObjectExists("GCCreateResConf_NoImage_LK")){
				SW.Click("GCCreateResConf_NoImg_None_LK");
			}else{
				SW.WaitTillElementToBeClickable("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
				SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
				if(!SW.ObjectExists("GCCreateBrandRescon_MsgSetUPIMgslect_IC"))
					SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgExpand_IC");
				SW.NormalClick("GCCreateBrandRescon_MsgSetUPIMgslect_IC");
			}
			SW.SelectRadioButton("GCCreateResConf_LandingPageDestinationURL2_RB");
			SW.EnterValue("GCCreateCRMRescon_SecondaryPageUrl_EB", "https://www.samp.com");
			SW.EnterValue("GCCreateResConf_Offername_EB", "offer"+SW.RandomString(5));
			SW.EnterValue("GCCreateResConf_Regionname_EB", "Region"+SW.RandomString(5));
			URL=SW.GetOnlyMyText("GCCreateResConf_URL_EB");
			URL=URL.trim();
			System.out.println(URL);
			SW.SwitchToFrame("GCCreateResConf_OfferTitle_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			OfferTitle ="Offer"+SW.RandomString(15);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",OfferTitle);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"Landing Page naviagation SuccessFull");
			}else{
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
			URL1=SW.GetOnlyMyText("GCCreateResConf_EmailPreview_URL_LK");
			URL1=URL1.trim();
			
			if(SW.CompareText("Validate the URL", URL, URL1))
			{
				Environment.loger.log(Level.INFO,"The Landing Page URL is same");
			}
			else{
				Environment.loger.log(Level.ERROR,"Landing Page URL Not Match");
				SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
				SW.FailCurrentTest("Landing Page URL Not Match");
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
								SW.EnterValue("GCOffer_SearchCriteria_EB",sOfferId);
								SW.Click("GCOfferCreate_Submit_BN");
								SW.WaitTillElementToBeClickable("GCOffer_Overview_IC");
								if(SW.ObjectExists("GCOffer_Overview_IC"))
								{
									SW.Click("GCOffer_Overview_IC");
								URL2=SW.GetOnlyMyText("GCCreateResConf_EmailPreview_URL_LK");
								URL2=URL2.trim();
								if(SW.CompareText("Validate the URL", URL, URL2))
								{
									Environment.loger.log(Level.INFO,"The Landing Page URL is same");
								}
								else{
									Environment.loger.log(Level.ERROR,"Landing Page URL Not Match");
									SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
									SW.FailCurrentTest("Landing Page URL Not Match");
								}
							//	SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
							/*	if(SW.ObjectExists("GCNavigation_SignOut_LK")){
									SW.Click("GCNavigation_SignOut_LK");
								}*/
								}else{
									Environment.loger.log(Level.ERROR,"Offer Id Overview Failed");
									SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
									SW.FailCurrentTest("Offer Id Activation Failed");
								}
							}else{
								Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
								SW.WriteToEmailTestData(TestCaseName, "ExeecutionFlag", "N");
								SW.FailCurrentTest("Offer Id Overview Failed");
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
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured-",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Exception  Occured ");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
