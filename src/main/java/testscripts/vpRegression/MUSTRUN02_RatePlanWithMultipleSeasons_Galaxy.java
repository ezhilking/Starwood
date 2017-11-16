/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_Smoke04_Reg_PropMode_Create_RatePlan_SeasonCrossover_Opera
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import java.util.Calendar;
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

public class MUSTRUN02_RatePlanWithMultipleSeasons_Galaxy {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	String CurrentDate = SW.GetTimeStamp("MM/dd/yyyy");
	
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
				SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Galaxy"));
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
				SW.DropDown_SelectByText("VPRates_RPType_DD", "Transient");	
				SW.EnterValue("VPRates_RPId_EB", RatePlanID+Keys.TAB);
				SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
				SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
//				SW.DropDown_SelectByIndex("VPRates_RPCURCD_DD", 1);
				SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);		
				SW.VPClick("VPRates_RPHeaderSave_BT");				
								
				//Moving to RatePlan Association Screen
				SW.SwitchToFrame("");
				SW.WaitForObject("VP_MainFrame_FR");
				SW.SwitchToFrame("VP_MainFrame_FR");
				SW.Click("VPRates_CreateSeason_LK");
				SW.EnterValue("VPRates_SeasonName_EB", "S1");
				SW.EnterValue("VPRates_SeasonStartDate_EB", CurrentDate);
				SW.EnterValue("VPRates_SeasonEndDate_EB", SW.DateAddDays(CurrentDate, "MM/dd/yyyy", 30, Calendar.DATE));
				SW.Click("VPRates_SeasonCreate_BN");
			
				
				SW.EnterValue("VPRates_SeasonName_EB", "S2");
				SW.EnterValue("VPRates_SeasonStartDate_EB", SW.DateAddDays(CurrentDate, "MM/dd/yyyy", 31, Calendar.DATE));
				SW.EnterValue("VPRates_SeasonEndDate_EB", SW.DateAddDays(CurrentDate, "MM/dd/yyyy", 90, Calendar.DATE));
				SW.Click("VPRates_SeasonCreate_BN");
				SW.VPClick("VPRates_RPAssociationSave_BT");
				

				
				//Moving to RatePlan Seasons Screen
				SW.SwitchToFrame("");
				SW.WaitForObject("VP_MainFrame_FR");
				SW.SwitchToFrame("VP_MainFrame_FR");
		//		SW.Click("VPRates_clearDynamicSeason_BN");
				SW.MoveToObject("VPRates_SeasonOffsetstable_WT");
				SW.MoveToObject("VPRates_RoomType_BT");
			   // SW.DropDown_SelectByText("VPRates_SeasonDiffType_DD", "Differentials by Room Class / Room Type");
				SW.CheckBox("VPRates_RoomType_BT", "ON");
				SW.EnterValue("VPRates_SingleRate_EB","2000"+Keys.TAB);
				SW.EnterValue("VPRates_ExtraPerson_EB","30"+Keys.TAB);
				SW.VPClick("VPRates_DynamicSeasonSave_BT");
				SW.CompareText("VPRates_SeasonRatesMissing_Msg_validation", "Season S2 is missing required values in Room Class(s)/Room Type(s).", SW.GetText("VPRates_ErrMsg_DT").trim());
				SW.CheckBox("VPRates_DynamicSeasonCopy_CB", "ON");
				SW.DropDown_SelectByText("VPRates_DynamicSeasonList_DD", "S2");
				SW.ClickAndProceed("VPRates_DyanmicSeasonCopy_BN");
				if(SW.IsAlertPresent()){
					SW.HandleAlert(true);
				}
				SW.CompareText("VPRates_SeasonCopySuccess_Msg_validation", "Season : S1 copied to Season : S2 successfully", SW.GetText("VPRates_RPinfoMsg_DT").trim());
				SW.VPClick("VPRates_DynamicSeasonSave_BT");
				SW.VPClick("VPRates_SellSequanceSave_BT");
				SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
				SW.CompareTextContained("VPRates_RatePlanPublish_SucessMsg_validation", "published successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
				//DB Validation
				SW.EstablishConnection(Environment.getRunEnvironment());
				if(SW.RecordExists("select rp_id from RATES.rp where rp_id='"+RatePlanID+"' and prop_id="+SW.TestData("NonRosPropID_Galaxy"))){
					
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
