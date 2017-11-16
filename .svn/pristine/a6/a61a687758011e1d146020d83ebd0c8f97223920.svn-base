/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_Smoke04_Reg_PropMode_Create_Group_RatePlan
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

import com.thoughtworks.selenium.webdriven.commands.IsAlertPresent;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST04_PropModeCreateGroupRatePlan {
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
		// Testing the RP Id Field Err Message
				SW.VPLogin("VP_Username", "VP_Password");
				SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
				SW.Click("VP_PropClick_BT");
				SW.NormalClick("VP_MenuSearch_EB");//TODO
				SW.EnterValue("VP_MenuSearch_EB","Rate Plan");
				SW.Click("VP_RatePlan_LK");
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
				SW.DropDown_SelectByText("VPRates_RPType_DD", "Group");		
				SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
				SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
				SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);		
				SW.VPClick("VPRates_RPHeaderSave_BT");
				SW.CompareText("VPInventory_RatePlanIDErrorMSg_DT", "Please enter Rate Plan ID before continuing.", SW.GetText("VPRates_ErrMsg_DT"));

				// Testing the RP Type Field Error Message
				SW.DropDown_SelectByIndex("VPRates_RPType_DD", 0);
				SW.EnterValue("VPRates_RPId_EB", RatePlanID+Keys.TAB);
				Environment.loger.log(Level.INFO, "Rate Plan Id : "+RatePlanID);
				SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
				SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
				SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);	
				SW.VPClick("VPRates_RPHeaderSave_BT");
				SW.CompareText("VPInventory_RatePlanTypeErrorMSg_DT", "Please enter Rate Plan Type before continuing.", SW.GetText("VPRates_ErrMsg_DT"));	

				// Testing the RP Name Error Message
				SW.DropDown_SelectByText("VPRates_RPType_DD", "Group");	
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

			
				// Testing the RP Yieldabilty Error Message
				SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
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
				
				//Moving to RatePlan Association Screen
				SW.VPClick("VPRates_RPHeaderSave_BT");
				SW.SwitchToFrame("");
				SW.WaitForObject("VP_MainFrame_FR");
				SW.SwitchToFrame("VP_MainFrame_FR");
				SW.VPClick("VPRates_RPAssociationSave_BT");
				//Validating Rate Plan Season Error Message
				SW.CompareText("VP_Group RP_Season Msg_Validation", "Please select at least one season.", SW.GetText("VPRates_ErrMsg_DT").trim());
				SW.CheckBox("VPRates_Season_DD","ON");
				SW.ClickAndProceed("VPRates_SeasonAdd_DD");				
				
				//Moving to RatePlan Seasons Screen
				SW.VPClick("VPRates_RPAssociationSave_BT");
				SW.SwitchToFrame("");
				SW.WaitForObject("VP_MainFrame_FR");
				SW.SwitchToFrame("VP_MainFrame_FR");
				SW.VPClick("VPRates_DynamicSeasonSave_BT");
				SW.CompareText("VPRates_GroupRatePlan_Pricing_Msg_Validation", "Pricing is required for at least one Room Type.", SW.GetText("VPRates_ErrMsg_DT").trim());
				SW.Wait(3);
				SW.CheckBox("VPRates_RoomType_BT", "ON");
				SW.EnterValue("VPRates_SingleRate_EB","2000"+Keys.TAB);
				SW.EnterValue("VPRates_ExtraPerson_EB","30"+Keys.TAB);
				SW.EnterValue("VPRates_ExtraChildRate_EB","40"+Keys.TAB);
				SW.VPClick("VPRates_DynamicSeasonSave_BT");
				SW.VPClick("VPRates_SellSequanceSave_BT");
				SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
				//DB Validation
				SW.EstablishConnection(Environment.getRunEnvironment());
				if(SW.RecordExists("select rp_id from RATES.rp where rp_id='"+RatePlanID+"' and prop_id="+SW.TestData("NonRosPropID_Opera"))){
					
				}else{
					SW.CloseDBConnection();
					SW.FailCurrentTest("Record doesn't exists in DB");
				}
				SW.CloseDBConnection();
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
	}
