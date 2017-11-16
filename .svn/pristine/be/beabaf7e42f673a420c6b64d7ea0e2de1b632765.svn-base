package testscripts.gcRegression;
/** Purpose		: Validate the Offer Expiration email (1 day prior) for PreStay offer
 * TestCase Name: Validate the Offer Expiration email (1 day prior) for PreStay offer
 * Created By	: Sharanya Bannuru
 * Modified By	: Sachin
 * Modified Date: 6/22/2016
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG25_ValidateOfferExpirationEmail1DayPrior {

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
	public void GCCreateGEIPreStayoffer(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCCreateOffer_LK");
			SW.Click("GC_CreateGEIOffer_LK");
			SW.Click("GC_GEIPreStayOffer_BN");
			SW.EnterValue("GCCreateResConf_InternalOfferName_EB", "AutomatedOfferName");
			SW.EnterValue("GCCreateResConf_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateResConf_presentationEndDate_EB", SW.DateAddDays( SW.GetTimeStamp("MM/dd/yyyy"),"MM/dd/yyyy",1,Calendar.DATE));
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
			if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
				Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");	
			}else{
				Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
				SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
			}
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCGEIPostStayOfferTitle_TB");
			SW.EnterValue("GCGEIPostStayOfferTitle_TB","Offer text");
			SW.EnterValue("GCCallToCopyAction_TB","Sample text");
			SW.SwitchToFrame("GC_GEIPostStayMsgSetUp_FR");
			SW.Click("GCCreateResConf_OfferTitleAndLandingPageBody_EB");
			sMessage = SW.RandomString(15);
			SW.EnterValue("GCCreateResConf_OfferTitleAndLandingPageBody_EB",sMessage);
			SW.SwitchToFrame("");
			SW.Click("GCCreateResConf_Continue_BN");
			SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
			if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
				Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
			}	else{
				Environment.loger.log(Level.ERROR,"MessageSetup Page naviagation Failed");
				SW.FailCurrentTest("MessageSetup Page naviagation Failed");
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
				SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
				if(SW.ObjectExists("GCOffer_Activate_IC")){
			    SW.Click("GCOffer_Activate_IC");
				Environment.loger.log(Level.INFO,"Created OfferId"+sOfferId);
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
					SW.FailCurrentTest("Offer Id Approval Failed");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Error Occured after Submit");
				SW.FailCurrentTest("Error Occured after Submit");
			}
			
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured ",e);
		}
	}	
	@Test(priority=2, dependsOnMethods={"GCCreateGEIPreStayoffer"})
	public void RunTask(){
		SW.Click("GCHome_Admin_LK");
		SW.Click("GC_GCAdmin_Lk");
		SW.Click("BopAdmin_Task_LK");
		if(SW.ObjectExists("BobAdmin_TaskExecute_BT"))
		{
		SW.Click("BobAdmin_TaskExecute_BT");
		Environment.loger.log(Level.INFO,"Task has Executed Successfully");
		}else{
			Environment.loger.log(Level.ERROR,"Task has NOT Executed Successfully");
			SW.FailCurrentTest("Task has NOT Executed Successfully");
		}
	}
	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}
