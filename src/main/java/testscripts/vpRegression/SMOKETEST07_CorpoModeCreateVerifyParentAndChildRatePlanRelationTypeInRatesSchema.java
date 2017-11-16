/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_Smoke07_Reg_CorpoMode_Create_or_Update_Verify_whether_relation_Type_is_saved_in_Rates_schema_for_Parent_and_Child_Rate_Plan
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import java.util.List;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST07_CorpoModeCreateVerifyParentAndChildRatePlanRelationTypeInRatesSchema {
	CRS SW = new CRS();
	String ParentRatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	String ChildRatePlanID= SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();		
		SW.LaunchBrowser(Environment.VPURL);
	}

	@Test(priority=0)
	public void TemplateValidation (){
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.Click("VP_Submit_BT");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Template");
		SW.Click("VP_RatesRPTemplate_LK");

		// Testing the RP Id Field Err Message
		SW.SwitchToFrame("");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");

		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 5);	
		SW.EnterValue("VPRates_RPId_EB", ParentRatePlanID+Keys.TAB);
		Environment.loger.log(Level.INFO, "Rate Plan Id : "+ParentRatePlanID);
		SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);		
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.VPClick("VPRates_RPAssociationSave_BT");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		SW.SwitchToFrame("");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");		
		SW.GetLocator("VPRates_RPinfoMsg_DT");
		SW.GetText("VPRates_RPinfoMsg_DT");
		SW.CompareText("VPInventory_RatePlanPublishSuccessMsg_DT", "Rate Plan "+ParentRatePlanID+" published successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select rp_id from RATES.corp_rp_tmplt where rp_id='"+ParentRatePlanID+"'")){
		 Environment.loger.log(Level.INFO, "DB Validation was Successful");	
		}else{
			SW.FailCurrentTest("Record doesn't exists in DB");
		}
		SW.CloseDBConnection();		
		SW.SwitchToFrame("");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Template");
		SW.Click("VP_RatesRPTemplate_LK");
		
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VPRates_RPChildId_EB", ParentRatePlanID+Keys.TAB);
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
		}else{
			SW.FailCurrentTest("Child Rate Plan alert didnt show up");
		}
		SW.EnterValue("VPRates_RPId_EB", ChildRatePlanID);
		SW.EnterValue("VPRates_RPName_EB", "Automated Child RP Temp");
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.VPClick("VPRates_RPAssociationSave_BT");
		SW.EnterValue("VPRates_ChildSingleDiff_EB", "50");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.CompareText("VPInventory_RatePlanPublishSuccessMsg_DT", "Rate Plan "+ChildRatePlanID+" published successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select rp_id from RATES.corp_rp_tmplt where rp_id='"+ChildRatePlanID+"'")){
		 Environment.loger.log(Level.INFO, "Child RP Record Created in DB");	
		}else{
			SW.FailCurrentTest("Record doesn't exists in DB");
		}
		List<String> DBResults = SW.GetColumnValues("select RP_REL_TYPE_CD from RATES.corp_rp_tmplt where rp_id='"+ChildRatePlanID+"'", 1);
		Environment.loger.log(Level.INFO, DBResults.get(0));
		if(DBResults.get(0).contentEquals("F")){
			Environment.loger.log(Level.INFO, "Relation Type Verified Sucessfully in Rates Schema");
		}else{
			SW.FailCurrentTest("DB Validation Failed for Relation type code in Rates Schema");
		}
		SW.CloseDBConnection();
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}

