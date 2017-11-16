/* Purpose		:  Valhalla Portal Must Run Suit
 * TestCase Name:  Reg_Corp User_Create or Modify RP_Verify user is able to Create or Modify a rate plan which is associated to a single governor meeting all the specifications.
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */


package testscripts.vpRegression;


import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;


public class MUSTRUN34_CreateOrModifyARatePlanWhichIsAssociatedToASingleGovernor {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
	}
	
	@Test(priority=0)
	    public void TransientRatePlan(){
		//***************************************** Creating Single Governor ***************************************************************
		SW.VPLogin("VP_Username", "VP_Password");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan Governors");
		SW.Click("VP_CorpRPGoverners_Governers_Lk");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.WaitForObject("VP_CorpRPGoverners_CreateGov_BT");
		SW.VPClick("VP_CorpRPGoverners_CreateGov_BT");
		SW.Click("VP_CorpRPGoverners_Division_CB");			
		SW.EnterValue("VP_CorpRPGoverners_RPIdCorpLvl_EB", RatePlanID);
		SW.EnterValue("VP_CorpRPGoverners_MinPercentageAmount_EB", "10");
		SW.EnterValue("VP_CorpRPGoverners_MaxPercentageAmount_EB", "20"+Keys.TAB);
		SW.Click("VP_CorpRPGoverners_Submit_BN");
		SW.CompareTextContained("Governer Creation Sucess Msg Validation", "Governor Saved Successfully.",SW.GetText("VP_CorpRPGoverners_SuccessMsg_DT"));
		
		//**************************************** Creating a RatePlan Associating to above governor ******************************************
		
		SW.SwitchToFrame("");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.Click("VP_MenuSearch_EB");//TODO
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan");
		SW.Click("VP_RatePlan_LK");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.WaitForObject("VPRates_ParentRPId_EB");
		SW.EnterValue("VPRates_ParentRPId_EB", "BAR"+Keys.TAB);
		SW.Wait(3);	
		int wincount = SW.GetCurrentWindowsCount();
		if(wincount>0){
			SW.SwitchToWindow(2);		
			SW.SwitchToFrame("");
			SW.SelectRadioButton("VPRates_InheritParentRPFields_RB");
			SW.Click("VPRates_InheritParentRPFieldsOK_BT");
		}
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VPRates_RPId_EB", RatePlanID+Keys.TAB);
		SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.VPClick("VPRates_RPAssociationSave_BT");
		SW.EnterValue("VPRates_ChildRPSinglediff_EB", "15");
		SW.VPClick("VPRates_DynamicSeasonSave_BT");
		if(SW.ObjectExists("VPRates_PkgElementSavecontinue_BT")){
			SW.VPClick("VPRates_PkgElementSavecontinue_BT");
		}
		SW.VPClick("VPRates_SellSequanceSave_BT");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		
		SW.CompareTextContained("VP_CreateDynamicChildRP_RPPublishSucessMsgValidation", "published successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select rp_id from RATES.rp where rp_id='"+RatePlanID+"' and prop_id="+SW.TestData("NonRosPropID_Opera"))){
			
		}else{
			SW.CloseDBConnection();
			SW.FailCurrentTest("Record doesn't exists in DB");
		}
		SW.CloseDBConnection();
		
		//******************************************************Deleting the above Created Governor*************************************************
        SW.SwitchToFrame("");
		SW.Click("VP_BacktoCorporate_BT");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan Governors");
		SW.Click("VP_CorpRPGoverners_Governers_Lk");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");		
		SW.EnterValue("VP_CorpRPGoverners_RPidFilter_EB", RatePlanID);
		SW.EnterValue("VP_CorpRPGoverners_AssDivFilter_EB", "ASIA/PACIFIC DIV");
		SW.Wait(2);
		SW.Click("VP_CorpRPGoverners_PropResults_RB");			
		SW.Click("VP_CorpRPGoverners_DelectGov_BN");
		if(SW.ObjectExists("VP_CorpRPGoverners_DelectGovConfirm_BN")){			
			SW.ClickAndProceed("VP_CorpRPGoverners_DelectGovConfirm_BN");			
		}
		SW.CompareTextContained("Governer Deletion Sucess Msg Validation", "Governor deleted Successfully.",SW.GetText("VP_CorpRPGoverners_SuccessMsg_DT"));
		
		
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}
