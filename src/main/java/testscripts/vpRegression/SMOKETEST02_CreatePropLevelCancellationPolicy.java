/* Purpose		: Valhalla Portal Smoke Test
 * TestCase Name: VP_Smoke02_Reg_Rates_Create_Cancellation_Policy
 * Created By	: Mayuri Mittal
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST02_CreatePropLevelCancellationPolicy {
	CRS SW =new CRS();
	String Errmsg;
	String PolicyName="Automation Cancel Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");
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

		//Navigating to Cancel Policy Screen		
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Create Cancellation Policy");
		SW.Click("VPRates_MouseOverCreateCancelPolicy_LK");


		// OnSceen validations for Cancel Policy Screen
		
		SW.SwitchToFrame("VP_MainFrame_FR");
		//SW.WaitForObject("VPRates_CancelPolicyName_EB");
		SW.EnterValue("VPRates_CancelPolicyName_EB", PolicyName );
		SW.DropDown_SelectByIndex("VPRates_CancelPolicyTiming_DD", 1);
		SW.Click("VPRates_CancelPolicySave_BT");

		//Error message validation for cancel Policy Type and Value
		SW.CompareText("VPRates_CancelPolicyTypeErrorMessgae_DT", "Cancel Policy Type: is required", SW.GetText("VRPRated_GeneralFirstErrorMessage_DT").trim());
		SW.CompareText("VPRates_CancelPolicyTypeValueErrorMessage_DT", "Cancel Policy Type Value: is required", SW.GetText("VRPRated_GeneralSecondErrorMessage_DT").trim());

		//Error message validation for cancel Policy Name
		SW.DropDown_SelectByText("VPRates_CancelPolicyType_DD", "Fixed Amount");
		SW.EnterValue("VPRates_CancelPolicyAmount_EB", SW.RandomNumber(1, 1000));
		SW.ClearValue("VPRates_CancelPolicyName_EB");
		SW.Click("VPRates_CancelPolicySave_BT");
		SW.CompareText("VPRates_CancelPolicyNameValueErrorMessage_DT", "Cancel Policy Name: is required", SW.GetText("VRPRated_GeneralFirstErrorMessage_DT").trim());

		//Error message validation for cancel Policy Timing Drop Down

		SW.EnterValue("VPRates_CancelPolicyName_EB", PolicyName);
		Environment.loger.log(Level.INFO, "Policy Name used is "+PolicyName);
		SW.DropDown_SelectByIndex("VPRates_CancelPolicyTiming_DD", 0);		
		if(SW.HandleAlert(true)){
			Environment.loger.log(Level.INFO, "cancel Policy Timing Drop Down Popup Validation Sucuessful");
		}else{
			Environment.loger.log(Level.INFO, "cancel Policy Timing Drop Down Popup Validation Failed");
		}
		SW.Click("VPRates_CancelPolicySave_BT");
		SW.CompareText("VPRates_CancelPolicyTypeTimingErrorMessage_DT", "Cancel Policy Timing: is required", SW.GetText("VRPRated_GeneralFirstErrorMessage_DT").trim());

		//Error message validation for cancel Policy Timing value and HRS:Min and AM/PM	
		SW.DropDown_SelectByIndex("VPRates_CancelPolicyTimingHrs_DD", 1);
		SW.Click("VPRates_CancelPolicySave_BT");
		SW.CompareText("VPRates_CancelPolicyTypeTimingErrorMessage_DT","Please enter cancel policy timing value Hrs, Mins and AM/PM Indicator are required.",SW.GetText("VRPRated_GeneralErrorMessage_DT").trim());
		SW.DropDown_SelectByIndex("VPRates_CancelPolicyTimingMins_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_CancelPolicyTimingAmPm_DD", 1);
		SW.Click("VPRates_CancelPolicySave_BT");
		SW.CompareText("VPRates_CancelPolicyValueTimingErrorMessage_DT", "Please enter cancel policy timing value", SW.GetText("VRPRated_GeneralErrorMessage_DT").trim());
		SW.EnterValue("VPRates_CancelPolicyTiming_EB",SW.RandomNumber(1, 550));
		SW.EnterValue("VPRates_CancelPolicyName_EB", PolicyName);
		SW.Wait(2);;
		String CancelPolicyID=SW.GetText("VPRates_CancelPolicyID_EB");
		Environment.loger.log(Level.INFO, CancelPolicyID);
		SW.Click("VPRates_CancelPolicySave_BT");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");		
		SW.CompareText("VPRAtes_CancelPolicySuccessMsg", "Cancel Policy "+CancelPolicyID+" was created successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(Environment.getRunEnvironment());
		SW.RecordExists("select cancel_rule_name from rates.prop_cancel_rule where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and cancel_rule_name='"+PolicyName+"'");
		SW.CloseDBConnection();
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.DropDown_SelectByText("VPRates_CancelPolicySearch_DD", "Cancel Policy Name");
		SW.EnterValue("VPRates_CancelPolicySearch_EB", PolicyName);
		SW.Click("VPRates_CancelPolicySearch_BT");
		String PolicyId = SW.GetText("VPRates_CancelPolicyID_ST");
		Environment.loger.log(Level.INFO, PolicyId);
		SW.SelectRadioButton("VPRates_CancelPolicySearchResult_RB");
		SW.ClickAndProceed("VPRates_CancelPolicyDelete_BT");		
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Delete confirmation Alert sucessful");
		}else{
			Environment.loger.log(Level.INFO, "Delete confirmation Alert failed");
		}
		SW.CompareText("VPRates_CancelPolicyDelete_Confirmation", "Cancel Policy "+PolicyId+" was deleted successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(Environment.getRunEnvironment());
		SW.RecordExists("select cancel_rule_name from rates.prop_cancel_rule where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and cancel_rule_name='"+PolicyName+"'");
		SW.CloseDBConnection();
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
