/* Purpose		:  Valhalla Portal Must Run Suit
 * TestCase Name:  Reg_Corp User_Verify the existence of new menu item Rate Plan Governors under Rates tab
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */


package testscripts.vpRegression;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;


public class MUSTRUN32_CreateGovernerAtCorpLevel {
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
		SW.VPLogin("VP_Username", "VP_Password");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan Governors");
		SW.Click("VP_CorpRPGoverners_Governers_Lk");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.VPClick("VP_CorpRPGoverners_CreateGov_BT");
		if(SW.ObjectExists("VP_CorpRPGoverners_RPIdCorpLvl_EB")){
			Reporter.Write("CreateGovernerScreen_Validation", "Divisions tab should get displayed sucessfully", "Divisions tab displayed sucessfully", "PASS");
		}else{
			Reporter.Write("CreateGovernerScreen_Validation", "Divisions tab should get displayed sucessfully", "Divisions tab didnt displayed sucessfully", "FAIL");
		}
		SW.Click("VP_CorpRPGoverners_Division_CB");		
		SW.Click("VP_CorpRPGoverners_ChainCode_CB");		
		SW.EnterValue("VP_CorpRPGoverners_RPIdCorpLvl_EB", RatePlanID);
		SW.EnterValue("VP_CorpRPGoverners_MinPercentageAmount_EB", "10");
		SW.EnterValue("VP_CorpRPGoverners_MaxPercentageAmount_EB", "20");
		SW.ClearValue("VP_CorpRPGoverners_MinPercentAmountSun_EB");
		SW.Click("VP_CorpRPGoverners_Submit_BN");
		SW.CompareTextContained("Min Value Mandatory Filed validation", "Governor Min Value for Sunday cannot be blank", SW.GetText("VP_CorpRPGoverners_ErrMsg_DT"));
		SW.ClearValue("VP_CorpRPGoverners_MaxPercentAmountSun_EB");
		SW.EnterValue("VP_CorpRPGoverners_MinPercentAmountSun_EB", "10");
		SW.Click("VP_CorpRPGoverners_Submit_BN");
		SW.CompareTextContained("Max Value Mandatory Filed validation", "Governor Max Value for Sunday cannot be blank", SW.GetText("VP_CorpRPGoverners_ErrMsg_DT"));
		SW.ClearValue("VP_CorpRPGoverners_MinPercentAmountSun_EB");
		SW.EnterValue("VP_CorpRPGoverners_MinPercentAmountSun_EB", "20");
		SW.EnterValue("VP_CorpRPGoverners_MaxPercentAmountSun_EB", "10");
		SW.Click("VP_CorpRPGoverners_Submit_BN");
		SW.CompareTextContained("Min Value > Max Value validation", "Governor Max Value must be greater than or equal to Min Value for Sunday", SW.GetText("VP_CorpRPGoverners_ErrMsg_DT"));
		SW.EnterValue("VP_CorpRPGoverners_MinPercentAmountSun_EB", "10");
		SW.EnterValue("VP_CorpRPGoverners_MaxPercentAmountSun_EB", "20");
		SW.Click("VP_CorpRPGoverners_Submit_BN");
		SW.CompareTextContained("Governer Creation Sucess Msg Validation", "Governor Saved Successfully.",SW.GetText("VP_CorpRPGoverners_SuccessMsg_DT"));
		SW.EnterValue("VP_CorpRPGoverners_RPidFilter_EB", RatePlanID);
		SW.EnterValue("VP_CorpRPGoverners_AssDivFilter_EB", "ASIA/PACIFIC DIV");
		SW.EnterValue("VP_CorpRPGoverners_AssChainFilter_EB", "ALOFT");
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
