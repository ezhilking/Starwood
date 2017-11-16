/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_MustRun04_Rates_Create Deposit Policy
 * Created By	:  Mayuri Mittal
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;


import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN04_CreatePropLevelDepositPolicy {
	
	CRS SW =new CRS();
	String Errmsg;
	String DepositPolicyName="Automation Deposit Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");
	@BeforeClass 
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);

}
	@Test
	public void VPLogin(){
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.Click("VP_Submit_BT");		
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");	
		
		//Navigating to Deposit Policy Screen
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Create Deposit Policy");
		SW.Click("VPRates_MouseOverCreateDepositPolicy_LT");
		
		//OnScreen validations for Deposit Policy Screen
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VPRates_DepositPolicyName_EB",DepositPolicyName) ;
		SW.DropDown_SelectByIndex("VPRates_DepositPolicyTiming_DD", 1);		
		SW.Click("VPRates_DepositPolicySave_BN");
		
		//Error message validation for deposit Policy Type and Value
		SW.CompareText("VPRates_DepositPolicyTypeErrorMessage", "Deposit Policy Type: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT").trim());
		SW.CompareText("VPRates_DepositPolicyTypeValueErrorMessage_DT","Deposit Policy Type Value: is required",SW.GetText("VRPRated_GeneralSecondErrorMessage_DT").trim());
		
		//Error message validation for Deposit Policy Name
		SW.DropDown_SelectByText("VPRates_DepositPolicyType_DD","Fixed Amount");
		SW.EnterValue("VPRates_DepositPolicyTypeValue_EB", SW.RandomNumber(0, 1000));
		SW.ClearValue("VPRates_DepositPolicyName_EB");
		SW.Click("VPRates_DepositPolicySave_BN");
		SW.CompareText("VPRates_DepositPolicyNameValueErrorMessage_DT","Deposit Policy Name: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT").trim());
		
		//Error message validation for Deposit Policy Timing
		SW.EnterValue("VPRates_DepositPolicyName_EB",DepositPolicyName);
		Environment.loger.log(Level.INFO,"Deposit Policy used is"+DepositPolicyName);
		SW.DropDown_SelectByIndex("VPRates_DepositPolicyTiming_DD", 0);
		if(SW.HandleAlert(true)){
			Environment.loger.log(Level.INFO, "Deposit Policy Timing Drop Down Popup Validation Sucuessful");
		}else{
			Environment.loger.log(Level.INFO, "Deposit Policy Timing Drop Down Popup Validation Failed");
		}
		SW.Click("VPRates_DepositPolicySave_BN");
		SW.CompareText("VPRates_DepositPolicyTimingErrorMessage_DT", "Deposit Policy Timing: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT").trim());
		
		//Error message validation for Deposit Policy Timing Value
		SW.DropDown_SelectByIndex("VPRates_DepositPolicyTiming_DD", 1);
		SW.Click("VPRates_DepositPolicySave_BN");
		SW.CompareText("VPRates_DepositPolicyTimingValueErrorMessage_DT","Please enter deposit policy timing value",SW.GetText("VRPRated_GeneralErrorMessage_DT").trim());
		
		SW.EnterValue("VPRates_DepositPolicyTimingValue_EB",SW.RandomNumber(1, 500));
		SW.EnterValue("VPRates_DepositPolicyAddOnPercent_EB",SW.RandomNumber(1, 20)) ;
		SW.EnterValue("VPRates_DepositPolicyAddOnAmount_EB",SW.RandomNumber(1, 100));
		SW.Wait(2);
		String DepositPolicyID=SW.GetText("VPRates_DepositPolicyID_ST");
		Environment.loger.log(Level.INFO, DepositPolicyID);
		SW.Click("VPRates_DepositPolicySave_BN");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");		
		SW.CompareText("VPRates_CancelPolicySuccessMsg", "Deposit Policy "+DepositPolicyID+" was created successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(SW.TestData("DBConnectionEnvironment"));
		SW.RecordExists("select dpst_rule_name from rates.prop_dpst_rule where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and dpst_rule_name='"+DepositPolicyName+"'");
		SW.CloseDBConnection();
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.DropDown_SelectByText("VPRates_DepositPolicySearch_DD","Deposit Policy Name");
		SW.EnterValue("VPRates_DepositPolicySearch_EB",DepositPolicyName);
		SW.Click("VPRates_DepositPolicySearch_BN");
		String PolicyId = SW.GetText("VPRates_DepositPolicyID_ST");
		Environment.loger.log(Level.INFO, PolicyId);
		SW.SelectRadioButton("VPRates_DepositPolicySearchResult_RB");
		SW.ClickAndProceed("VPRates_DepositPolicyDelete_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Delete confirmation Alert sucessful");
		}else{
			Environment.loger.log(Level.INFO, "Delete confirmation Alert failed");
		}
		SW.CompareText("VPRates_DepositPolicyDelete_Confirmation", "Deposit Policy "+PolicyId+" was deleted successfully!",SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(SW.TestData("DBConnectionEnvironment"));
		SW.RecordExists("select dpst_rule_name from rates.prop_dpst_rule where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and dpst_rule_name='"+DepositPolicyName+"'");
		SW.CloseDBConnection();
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}
