/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name: VP_Smoke01_Reg_CorpMode_Create_Rate_Template_Verify_the_screen_and_Validation
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

public class SMOKETEST01_CreateRateplanTemplate {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);

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

		List<WebElement> RPName = SW.GetAllElements("VPRates_RPName_EB");

		if(RPName.get(0).isEnabled()){
			SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");
		}else {
			SW.SwitchToFrame("");
			SW.WaitForObject("VP_MainFrame_FR");
			SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");			
		}

		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 5);		
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);		
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanIDErrorMSg_DT", "Please enter Rate Plan ID before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP Type Field Error Message
		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 0);
		SW.EnterValue("VPRates_RPId_EB", RatePlanID+Keys.TAB);
		Environment.loger.log(Level.INFO, "Rate Plan Id : "+RatePlanID);
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);	
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanTypeErrorMSg_DT", "Please enter Rate Plan Type before continuing.", SW.GetText("VPRates_ErrMsg_DT"));	

		// Testing the RP Name Error Message
		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 5);
		SW.ClearValue("VPRates_RPName_EB");
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanNameErrorMSg_DT", "Please enter Rate Plan Name before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP Category Error Message
		SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",0);
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanCatgErrorMSg_DT", "Please enter Rate Category before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP Market Seg Error Message
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 0);
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanMktSegErrorMSg_DT", "Please enter Starwood Market Segment before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP Currency Code Error Message
		SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD",0);
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanCurCodeErrorMSg_DT", "Please enter Currency Code before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP Yieldabilty Error Message
		SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD",1);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 0);
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanYieldabiltyErrorMSg_DT", "Please enter Yieldability Setting before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP MinLos Error Message
		SW.ClearValue("VPRates_RPMinLos_EB");
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.CompareText("VPInventory_RatePlanMinLosErrorMSg_DT", "Please enter Min. Length of Stay before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

		// Testing the RP MaxLos Error Message
		SW.EnterValue("VPRates_RPMinLos_EB", "1");			
		//		SW.ClearValue("VPRates_RPMaxLos_EB");
		SW.EnterValue("VPRates_RPMaxLos_EB", Keys.TAB);
		if(SW.HandleAlert(true)){
			Environment.loger.log(Level.INFO, "Max los alert validation Passed");
			SW.EnterValue("VPRates_RPMaxLos_EB", 90);
		}else {
			Environment.loger.log(Level.INFO, "Max los alert validation failed");
			SW.FailCurrentTest("Max los alert validation failed");
		}
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.VPClick("VPRates_RPAssociationSave_BT");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		SW.SwitchToFrame("");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");		
		SW.GetLocator("VPRates_RPinfoMsg_DT");
		SW.GetText("VPRates_RPinfoMsg_DT");
		SW.CompareText("VPInventory_RatePlanPublishSuccessMsg_DT", "Rate Plan "+RatePlanID+" published successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select rp_id from RATES.corp_rp_tmplt where rp_id='"+RatePlanID+"'")){
		 Environment.loger.log(Level.INFO, "DB Validation was Successful");	
		}else{
			SW.FailCurrentTest("Record doesn't exists in DB");
		}
		SW.CloseDBConnection();
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
