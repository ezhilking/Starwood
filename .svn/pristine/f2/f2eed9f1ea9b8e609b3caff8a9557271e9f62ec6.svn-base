/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_MustRun05_Reg_PropMode_Create_SPGRatePlanGalaxy
 * Created By	:  Muneeb
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

public class MUSTRUN05_SPGRatePlanGalaxy {
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
	SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.VPClick("VP_Submit_BT");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Galaxy"));
		SW.Click("VP_PropClick_BT");
		SW.NormalClick("VP_MenuSearch_EB");//TODO
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan");
		SW.Click("VP_RatePlan_LK");
		//		SW.Wait(6);
		SW.SwitchToFrame("");
		//		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");

		List<WebElement> RPName = SW.GetAllElements("VPRates_RPName_EB");

		if(RPName.get(0).isEnabled()){
			SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");
		}else {
			SW.SwitchToFrame("");
			SW.WaitForObject("VP_MainFrame_FR");
			SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");			
		}
		SW.DropDown_SelectByText("VPRates_RPType_DD", "Transient");
//		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 3);		
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
		//SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);	


		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 0);
		SW.EnterValue("VPRates_RPId_EB", RatePlanID+Keys.TAB);
		Environment.loger.log(Level.INFO, "Rate Plan Id : "+RatePlanID);

		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 5);

		//SW.ClearValue("VPRates_RPCURCD_DD");
		//SW.EnterValue("VPRates_RPCURCD_DD","THB - Thailand Baht");
		//SW.DropDown_GetText("VP_PropCurrency");
		//SW.DropDown_SelectByText("VPRates_RPCURCD_DD", SW.TestData("VP_Currency"));
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 0); 

		SW.ClearValue("VPRates_RPMinLos_EB");
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);
		SW.EnterValue("VPRates_RPMinLos_EB", "1");	

		SW.EnterValue("VPRates_RPMaxLos_EB", Keys.TAB);
		if(SW.HandleAlert(true)){
			Environment.loger.log(Level.INFO, "Max los alert validation Passed");
			SW.EnterValue("VPRates_RPMaxLos_EB", 90);
		}else {
			Environment.loger.log(Level.INFO, "Max los alert validation failed");
			SW.FailCurrentTest("Max los alert validation failed");
		}

		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.SwitchToFrame("");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.CheckBox("VPRates_Season_DD","ON");
		SW.ClickAndProceed("VPRates_SeasonAdd_DD");//TODO
		//		SW.Wait(2);
		// SW.VPClick("VPRates_SeasonSave_DD");
		SW.VPClick("VPRates_RPAssociationSave_BT");
		SW.Wait(2);
		SW.CheckBox("VPRates_RoomType_BT", "ON");
		SW.EnterValue("VPRates_SingleRate_EB","2000"+Keys.TAB);//TODO
	//	SW.EnterValue("VPRates_ExtraChildRate_EB","30"+Keys.TAB);
		SW.EnterValue("VPRates_ExtraPerson_EB","40"+Keys.TAB);
		SW.VPClick("VPRates_DynamicSeasonSave_BT");
		SW.VPClick("VPRates_SellSequanceSave_BT");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		
		SW.EstablishConnection("QA3");
		
		if(SW.RecordExists("select rp_id from RATES.rp where rp_id='"+RatePlanID+"'and prop_id="+SW.TestData("NonRosPropID_Opera"))){
			
		}else{
			SW.CloseDBConnection();
			SW.FailCurrentTest("Record doesn't exists in DB");
		}
		SW.CloseDBConnection();
		SW.EstablishConnection("");
		
		{
			if(SW.RecordExists("select appl_data from ips_mail_in where txn_id='RP12' and appl_data like '%FN15%';")){
			//(SW.RecordExists(""))
		}else{
			SW.CloseDBConnection();
			SW.FailCurrentTest("Record doesn't exists in DB");
		}
		SW.CloseDBConnection();
	}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
