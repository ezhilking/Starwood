package testscripts.vpRegression;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN24_CreateModifyDeleteRatePlanSeasonAtPropLevel {
	
	
	CRS SW =new CRS();
	String RateSeasonName = "RS NM"+SW.RandomString(3).toUpperCase();
	String RateSeasonID="RS ID"+SW.RandomString(3).toUpperCase();
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
		
	    //Navigating to rate season screen
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Create Rate Season");	
		SW.Click("VPRates_MouseOverCreateRateSeason_LT");
		
		//OnScreen validation for rate season screen
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VPRates_RateSeasonName_EB",RateSeasonName);
		SW.Click("VPRates_RateSeasonSave_BN");
		
		SW.CompareText("VPRates_RateSeasonIDErrorMessage","Please enter Season Id and try again",SW.GetText("VPRates_RateSeasonErrorMessage_DT"));
		
		SW.EnterValue("VPRates_RateSeasonID_EB",RateSeasonID);
		SW.ClearValue("VPRates_RateSeasonName_EB");
		SW.Click("VPRates_RateSeasonSave_BN");
		
		SW.CompareText("VPRates_RateSeasonNameErrorMessage", "Please enter Season Name and try again",SW.GetText("VPRates_RateSeasonErrorMessage_DT"));
		SW.EnterValue("VPRates_RateSeasonName_EB",RateSeasonName);
		String ToDate=SW.GetTimeStamp("MM/dd/yyyy");
		SW.EnterValue("VPRates_RateSeasonOccupancyStartDate_EB", ToDate);
		SW.Click("VPRates_RateSeasonEndDate_LK");
		
		String FutureDate = SW.DateAddDays(ToDate, "MM/dd/yyyy", 2, Calendar.DATE); 
		SW.EnterValue("VPRates_RateSeasonOccupancyEndDate_EB", FutureDate);
		
		SW.CheckBox("VPRates_RateSeasonAssociateCancelPolicy_CB", "ON");
		SW.Click("VPRates_RateSeasonAddButtonC_BN");
		
		SW.CheckBox("VPRates_RateSeasonAssociateDepositPolicy_CB", "ON");
		SW.Click("VPRates_RateSeasonAddButtonD_BN");
		
		SW.Click("VPRates_RateSeasonSave_BN");
		String Msg=SW.GetText("VPRates_RPinfoMsg_DT").trim();
		Environment.loger.log(Level.INFO, Msg);
		
		SW.CompareText("VPRates_RateSeasonSuccessMsg", "Rate Season "+RateSeasonID+" created successfully!",SW.GetText("VPRates_RPinfoMsg_DT").trim());
		
		SW.EstablishConnection("QA3");
		SW.RecordExists("select prop_seas_id from rates.prop_seas_lkup where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and prop_seas_id='"+RateSeasonID+"'");
		SW.CloseDBConnection();	
		
		//Searching created Rate Season
		
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.DropDown_SelectByText("VPRates_RateSeasonSearch_DD", "Rate Season ID");
		SW.EnterValue("VPRates_RateSeasonSearch_EB",RateSeasonID);
		SW.Click("VPRates_RateSeasonSearch_BN");
		
		//Modify Created Rate Season
		
		SW.SelectRadioButton("VPRates_RateSeasonSearchResult_RB");
		SW.ClickAndProceed("VPRates_RateSeasonModify_BN");
		SW.ClearValue("VPRates_RateSeasonOccupancyStartDate_EB");
		String ToDate1=SW.DateAddDays(ToDate, "MM/dd/yyyy", 1, Calendar.DATE); 
		SW.EnterValue("VPRates_RateSeasonOccupancyStartDate_EB", ToDate1);
		SW.Click("VPRates_RateSeasonModifySave_BN");
		SW.CompareText("VPRates_RateSeasonModifySuccessMsg", "Rate Season "+RateSeasonID+" modified successfully!",SW.GetText("VPRates_RPinfoMsg_DT").trim());
		
		
		
		//Deleting Searched Rate Season
		
		SW.DropDown_SelectByText("VPRates_RateSeasonSearch_DD", "Rate Season ID");
		SW.EnterValue("VPRates_RateSeasonSearch_EB",RateSeasonID);
		SW.Click("VPRates_RateSeasonSearch_BN");
		SW.SelectRadioButton("VPRates_RateSeasonSearchResult_RB");
		SW.ClickAndProceed("VPRates_RateSeasonDelete_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Delete confirmation Alert sucessful");
		}else{
			Environment.loger.log(Level.INFO, "Delete confirmation Alert failed");
		}
		SW.CompareText("VPRates_RateSeasonDelete_Confirmation", "Rate Season "+RateSeasonID+" was deleted successfully!", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		SW.EstablishConnection("QA3");
		SW.RecordExists("select prop_seas_id from rates.prop_seas_lkup where Prop_id="+SW.TestData("NonRosPropID_Opera")+"and prop_seas_id='"+RateSeasonID+"'");
		SW.CloseDBConnection();	
		
		
	}	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
