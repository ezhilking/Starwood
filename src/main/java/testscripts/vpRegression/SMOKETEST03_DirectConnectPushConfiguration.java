/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name: VP_Smoke03_Direct_Connect_Push_Configuration
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
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST03_DirectConnectPushConfiguration {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	String StartDate= SW.GetTimeStamp("MM/dd/yyyy");	
	String EndDate=SW.DateAddDays(StartDate, "MM/dd/yyyy", 30, Calendar.DATE);
	String DBStartDate=SW.GetTimeStamp("dd-MMM-yy");
	String DBEndDate= SW.DateAddDays(DBStartDate, "dd-MMM-yy", 30, Calendar.DATE);
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);

	}

	@Test(priority=0)
	public void DirectConnectScreen_validation (){
		SW.VPLogin("VP_Username", "VP_Password");		
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Direct Connect Setup");
		SW.Click("VPEDG_DirectConnectSetupScreen_LK");		

		//Validating onScreen validations of DirectConnet Settings

		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VPEDG_DCSStartDate_EB", StartDate);
		SW.EnterValue("VPEDG_DCGEndDate_EB", EndDate);
		SW.ClickAndProceed("VPEDG_DCSSearch_BT");
		if(SW.HandleAlert(true)){
			Reporter.Write("SearchValue Field Validation", "PopUp should appear", "Popup Appeared", "PASS");
		}else {
			Reporter.Write("SearchValue Field Validation", "PopUp should appear", "Popup Didnt Appeared", "FAIL");
		}
		SW.EnterValue("VPEDG_DCSSearchValue_EB", "*");
		SW.ClearValue("VPEDG_DCSStartDate_EB");
		SW.ClickAndProceed("VPEDG_DCSSearch_BT");
		if(SW.HandleAlert(true)){
			Reporter.Write("StartDate Field Validation", "PopUp should appear", "Popup Appeared", "PASS");
		}else {
			Reporter.Write("StartDate Field Validation", "PopUp should appear", "Popup didnt Appeared", "FAIL");
		}
		SW.EnterValue("VPEDG_DCSStartDate_EB", StartDate);
		SW.ClearValue("VPEDG_DCGEndDate_EB");
		SW.ClickAndProceed("VPEDG_DCSSearch_BT");
		if(SW.HandleAlert(true)){
			Reporter.Write("EndDate Field Validation", "PopUp should appear", "Popup Appeared", "PASS");
		}else {
			Reporter.Write("EndDate Field Validation", "PopUp should appear", "Popup Didnt Appeared", "FAIL");
		}
		SW.EnterValue("VPEDG_DCGEndDate_EB", EndDate);
		SW.Click("VPEDG_DCSSearch_BT");
		SW.VPWaitForPageload();
		String GBName = SW.GetText("VPEDG_DCSSearchResultGBName_DT").trim();
		Environment.loger.log(Level.INFO, "Group Block Name Fecthed from DB="+GBName);

		if(SW.ObjectExists("VPEDG_DCSSearchResult_CB")){
			SW.CheckBox("VPEDG_DCSSearchResult_CB", "ON");			
		}else{
			Reporter.Write("Search Results for specific date range", "Results should get returned", "Results didnt got returned", "FAIL");
		}
		SW.Click("VPEDG_DCSAddToSelectedBlocks_BT");
		SW.VPWaitForPageload();
		SW.Wait(5);
		String title=SW.GetAttributeValue("VPEDG_DCSAddedResultsGBName_DT", "title").trim();
		Environment.loger.log(Level.INFO, title);
		if(SW.CompareText("Group Blockes Not Added to Selected Blocks Successfully",SW.GetAttributeValue("VPEDG_DCSAddedResultsGBName_DT", "title").trim(), GBName)){
			SW.CheckBox("VPEDG_DCSAddedResultsSelect_CB", "ON");
		}else{
			Reporter.Write("Group Blockes Validation", "Group Blockes Should get Added to Selected Blocks Successfully", "Group Blockes Not Added to Selected Blocks Successfully", "FAIL");
		}

		SW.Click("VPEDG_DCSModifyPartnersChannels_BT");
		if(SW.ObjectExists("VPEDG_DCSSelectChannel1_CB")){
			SW.CheckBox("VPEDG_DCSSelectChannel1_CB", "ON");
			SW.CheckBox("VPEDG_DCSSelectChannel2_CB", "ON");
			SW.CheckBox("VPEDG_DCSSelectChannel3_CB", "ON");
			SW.Click("VPEDG_DCSSelectChannelADD_BT");
		}else{
			SW.FailCurrentTest("No Channel Available to Associate");
		}
		
		if(SW.ObjectExists("VPEDG_DCSSelectAddedChannel2_CB")){
			Environment.loger.log(Level.INFO, "Multiple Group Blockes  Added to Selected Blocks Successfully");
			SW.CheckBox("VPEDG_DCSSelectAddedChannel2_CB", "ON");
			SW.CheckBox("VPEDG_DCSSelectAddedChannel2_CB", "ON");			
		}else {
				SW.FailCurrentTest("Multiple Group Blockes Not Added to Selected Blocks Successfully");
		}
		SW.Click("VPEDG_DCSSelectChannelRemove_BT");
		if(!SW.ObjectExists("VPEDG_DCSSelectAddedChannel2_CB")){
			Environment.loger.log(Level.INFO, "Channels  Removed from Selected Blocks Successfully");
		}
		if(SW.ObjectExists("VPEDG_DCSSelectAddedChannel1_CB")){
			SW.CheckBox("VPEDG_DCSSelectAddedChannel1Push_CB","ON");
		}else {
				SW.FailCurrentTest("No Group Blockes Was Added to Selected Blocks Successfully");

		}
		SW.Click("VPEDG_DCSApplyPartners_BT");
		SW.Wait(2);
		SW.Click("VPEDG_DCSSave_BT");
		SW.Wait(15);
		SW.CompareText("DirectConnectScreen_validation_SucessMessage", "Direct Connect Setup was updated successfully for property "+SW.TestData("NonRosPropID_Opera") , SW.GetText("VPRates_RPinfoMsg_DT").trim());
		Environment.loger.log(Level.INFO, "Channel was associated to Group Blocks Successfully");
		SW.CheckBox("VPEDG_DCSAddedResultsSelect_CB", "ON");
		SW.CheckBox("VPEDG_DCSAddedResultsPushRates_CB", "ON");
		SW.EnterValue("VPEDG_DCSreSyncToDate_EB", EndDate+Keys.TAB);
	
		
		SW.Click("VPEDG_DCSreSyncRates_BT");
		SW.CompareText("ReSync ARI Validation",SW.GetAttributeValue("VPEDG_DCSreSyncRates_BT", "disabled"), "");
		SW.CheckBox("VPEDG_DCSAddedResultsPushInventory_CB", "ON");
		SW.Click("VPEDG_DCSreSyncInventory_BT");
		SW.CompareText("ReSync Inventory Button validation",SW.GetAttributeValue("VPEDG_DCSreSyncInventory_BT", "disabled"), "");

		SW.Click("VPEDG_DCSreSyncProperty_BT");
		SW.CompareText("ReSync Property Button validation",SW.GetAttributeValue("VPEDG_DCSreSyncProperty_BT", "disabled"), "");
		
		SW.CheckBox("VPEDG_DCSAddedResultsSelect_CB", "ON");
		SW.Click("VPEDG_DCSRemoveFromSelectedBlocks_BT");
		
		SW.SwitchToWindow(2);
		SW.Click("VPEDG_DCSChannelDeleteConf_BT");
		SW.SwitchToWindow(1);
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Click("VPEDG_DCSSave_BT");
		SW.Wait(20);
		SW.CompareText("DirectConnectScreen_validation_SucessMessage_ChannelRemoval", "Direct Connect Setup was updated successfully for property "+SW.TestData("NonRosPropID_Opera") , SW.GetText("VPRates_RPinfoMsg_DT").trim());

	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
	
}
