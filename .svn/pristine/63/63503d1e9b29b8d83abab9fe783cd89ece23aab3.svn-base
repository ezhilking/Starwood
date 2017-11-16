/* Purpose		:  Valhalla Portal Must Run Suit
 * TestCase Name:  
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


public class MasterSuite01_CreationOfDynamicChildRatePlan {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
	}
	
	@Test(priority=0)
	    public void DynamicChildRatePlan(){
		SW.VPLogin("VP_Username", "VP_Password"); //Logging in TO VP
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.Click("VP_MenuSearch_EB");//TODO
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan");
		SW.Click("VP_RatePlan_LK");
		SW.SwitchToFrame("VP_MainFrame_FR");
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
		SW.VPClick("VPRates_DynamicSeasonSave_BT");
		SW.CompareText("VP_ChildRPCreation_SingleDIffErrMsgValidation", "Single Differential is required .", SW.GetText("VPRates_ErrMsg_DT").trim());
		SW.EnterValue("VPRates_ChildRPSinglediff_EB", "10");
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
		
		}
		
	}
