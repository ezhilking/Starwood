/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_MustRun04_Rates_CreateTax
 * Created By	:  Mayuri Mittal
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN03_Rates_CreateTaxfromVP  {

	CRS SW =new CRS();
	String TaxName = "TAX-"+SW.RandomString(3).toUpperCase();
	@BeforeClass 
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
}
	@Test
	public void VPLogin(){
		SW.VPLogin("VP_Username", "VP_Password");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");	
		
		//Navigating to tax/charge screen
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Create Tax/Charge");
		SW.Click("VPRates_MouseOverCreateTax/Charge_LT");
		
		//OnScreen validations for tax/charge screen
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VPRates_TaxChargeDescription_EB",TaxName);
		SW.Click("VPRates_TaxChargeSave_BT");
		
		//Error message validation for Sub-category, Inclusion Selector and Posting Rhythm
		SW.CompareText("VPRates_TaxChargeSubCategoryErrorMessage","Sub-Category: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT"));
		SW.CompareText("VPRates_TaxChargeInclusionSelectorErrorMessage","Inclusion Selector: is required",SW.GetText("VRPRated_GeneralSecondErrorMessage_DT"));
		SW.CompareText("VPRates_TaxChargePostingRhythmErrorMessage","Posting Rhythm: is required",SW.GetText("VPRated_GeneralThirdErrorMessage_DT"));
		
		//Error message validation for Description and Posting Rhythm
		//Random n=new Random();
		SW.DropDown_SelectByIndex("VPRates_TaxChargeSubCategory_DD",SW.RandomNumber(0, SW.DropDown_GetSize("VPRates_TaxChargeSubCategory_DD")-1));
		SW.ClearValue("VPRates_TaxChargeDescription_EB");
		SW.DropDown_SelectByIndex("VPRates_TaxChargeInclusionSelector_DD",1);
		SW.Click("VPRates_TaxChargeSave_BT");
		
		SW.CompareText("VPRates_TaxChargeDescriptionErrorMessage","Description: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT"));
		SW.CompareText("VPRates_TaxChargePostingRhythmErrorMessage","Posting Rhythm: is required",SW.GetText("VRPRated_GeneralSecondErrorMessage_DT"));
		
		//Error message validation for posting Rhythm
		SW.EnterValue("VPRates_TaxChargeDescription_EB",TaxName);
		SW.Click("VPRates_TaxChargeSave_BT");
		SW.CompareText("VPRates_TaxChargePostingRhythmErrorMessage","Posting Rhythm: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT"));
		
		//Error message validation for Posting Rhythm Value
		SW.DropDown_SelectByIndex("VPRates_TaxChargePostingRhythm_DD",1);
		SW.ClearValue("VPRates_TaxChargePostingRhythmValue_EB");
		SW.Click("VPRates_TaxChargeSave_BT");
		
		SW.CompareText("VPRates_TaxChargePostingRhythmValueErrorMessgae","Amount: is required",SW.GetText("VRPRated_GeneralFirstErrorMessage_DT"));
		SW.EnterValue("VPRates_TaxChargePostingRhythmValue_EB",SW.RandomNumber(1, 100));
		String ToDate=SW.GetTimeStamp("MM/dd/yyyy");
		SW.EnterValue("VPRates_TaxChargeEffectiveDateFrom_EB",ToDate);
		
		SW.Click("VPRates_TaxChargeSave_BT");

		String Msg=SW.GetText("VPRates_RPinfoMsg_DT").trim();
		Environment.loger.log(Level.INFO, Msg);
		SW.CompareText("VPRates_TaxChargeSuccessMsg","Tax "+TaxName+" was created successfully!",SW.GetText("VPRates_RPinfoMsg_DT").trim() );
		
		SW.EstablishConnection(Environment.getRunEnvironment());
		SW.RecordExists("select tax_desc from rates.prop_tax where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and tax_desc='"+TaxName+"'");
		SW.CloseDBConnection();
		
			
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}