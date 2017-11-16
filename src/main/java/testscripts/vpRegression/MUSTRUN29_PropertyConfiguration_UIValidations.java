package testscripts.vpRegression;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN29_PropertyConfiguration_UIValidations {
	
    CRS SW =new CRS();
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
		
		//navigating to Property configuration screen
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Property Configuration");
		SW.Click("VPPropContent_MouseOverPropConfg_LT");
		
		//Onscreen validations for Property Configuration screen
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.DropDown_SelectByIndex("VPPropContent_PropConfgPMSType_DD",0);
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgPMSTypeErrorMsg","Error: Error Code = SHK20011 Message = Property System cannot be null.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.DropDown_SelectByIndex("VPPropContent_PropConfgPMSType_DD", 2);
		
		SW.ClearValue("VPPropContent_PropConfgMaxRoomsPerResv_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgMaxRoomsPerResvErrorMsg","Maximum Rooms Per Reservation is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.EnterValue("VPPropContent_PropConfgMaxRoomsPerResv_EB", 15);
		
		SW.ClearValue("VPPropContent_PropConfgAgeofChildRates_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgAgeofChildRatesErrorMesg", "Age of Child for Rates is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.EnterValue("VPPropContent_PropConfgAgeofChildRates_EB", 2);
		
		SW.CheckBox("VPPropContent_PropConfgDoesNotAllowChild_CB","OFF");
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Child Age Band 1 Alert sucessful");
		}else{
			Environment.loger.log(Level.INFO, "Child Age Band 1 Alert failed");
		}
		SW.DropDown_SelectByText("VPPropContent_PropConfgChildAgeBand1_DD", "Infant");
		
		SW.EnterValue("VPPropContent_PropConfgChildAgeBand1_EB", 5);
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Age of Child for Rates and Child Age Band is equal");
		}else{
			Environment.loger.log(Level.INFO, "Age of Child for Rates and Child Age Band not equal");
		}
		SW.ClearValue("VPPropContent_PropConfgChildAgeBand1_EB");
		SW.EnterValue("VPPropContent_PropConfgChildAgeBand1_EB", 2);
		
		SW.DropDown_SelectByIndex("VPPropContent_PropConfgPrimaryThreshold_DD", 0);
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Select Primary Currency of Minimum Threshold successful");
		}else{
			Environment.loger.log(Level.INFO, "Select Primary Currency of Minimum Threshold failed");
		}
		SW.DropDown_SelectByText("VPPropContent_PropConfgPrimaryThreshold_DD","THB - Thailand Baht");
		
		SW.DropDown_SelectByIndex("VPPropContent_PropConfgSecondaryThreshold_DD",0);
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Select Secondary Currency of Minimum Threshold successful");
		}else{
			Environment.loger.log(Level.INFO, "Select Secondary Currency of Minimum Threshold failed");
		}
		SW.DropDown_SelectByText("VPPropContent_PropConfgSecondaryThreshold_DD","USD - United States Dollar");
		
		SW.DropDown_SelectByIndex("VPPropContent_PropConfgPreferredUOM_DD", 0);
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgPreferredUOMErrorMsg", "Property Preferred UOM is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.DropDown_SelectByIndex("VPPropContent_PropConfgPreferredUOM_DD", 1);
		
		SW.ClearValue("VPPropContent_PropConfgTransientSellThru_EB");
		SW.ClearValue("VPPropContent_PropConfgTransientNights_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgTransientSellThruErrorMsg", "Transient Sell Through Policy Percentage is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.CompareText("VPPropContent_PropConfgTransientNights","Transient Sell Through Policy Nights is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage2_DT"));
		SW.EnterValue("VPPropContent_PropConfgTransientSellThru_EB", 50);
		SW.EnterValue("VPPropContent_PropConfgTransientNights_EB", 8);
		
		SW.ClearValue("VPPropContent_PropConfgGroupSellThru_EB");
		SW.ClearValue("VPPropContent_PropConfgGroupNights_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgGroupSellThruErrorMsg","Group Sell Through Policy Percentage is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.CompareText("VPPropContent_PropConfgGroupNightsErrorMsg","Group Sell Through Policy Nights is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage2_DT"));
		SW.EnterValue("VPPropContent_PropConfgGroupSellThru_EB", 50);
		SW.EnterValue("VPPropContent_PropConfgGroupNights_EB", 8);
		
		SW.ClearValue("VPPropContent_PropConfgCancelLeadTime_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgCancelLeadTimeErrorMsg", " Cancellation Lead Time is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.EnterValue("VPPropContent_PropConfgCancelLeadTime_EB",5);
		SW.ClearValue("VPPropContent_PropConfgCancelPenalty_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgCancelPenaltyErrorMsg"," Cancellation Penalty is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.EnterValue("VPPropContent_PropConfgCancelPenalty_EB",1);
		
		SW.ClearValue("VPPropContent_PropConfgDepositWaiver_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		SW.CompareText("VPPropContent_PropConfgDepositWaiverErrorMsg"," Deposit Waiver Days is required. Please complete this field.",SW.GetText("VPPropContent_PropConfgErrorMessage_DT"));
		SW.EnterValue("VPPropContent_PropConfgDepositWaiver_EB",0);
		
		SW.CheckBoxSetOptionForAll("VPPropContent_PropConfgMethodofGuarantee_CB","OFF");
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Select method of Guarantee is successful");
		}else{
			Environment.loger.log(Level.INFO, "Select method of Guarantee is failed");
		}
		SW.CheckBox("VPPropContent_PropConfgMethodofGuarantee_CB","ON");
		
		SW.CheckBoxSetOptionForAll("VPPropContent_PropConfgMethodofPayments_EB","OFF");
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Select method of Payments is successful");
		}else{
			Environment.loger.log(Level.INFO, "Select method of Payments is failed");
		}
		SW.CheckBox("VPPropContent_PropConfgMethodofPayments_EB","ON");
		
		SW.ClearValue("VPPropContent_PropConfgResRecovEmailID_EB");
		SW.Click("VPPropContent_PropConfgSave_BN");
		if(SW.HandleAlert(true)){		
			Environment.loger.log(Level.INFO, "Reservation Recovery Email ID is successful");
		}else{
			Environment.loger.log(Level.INFO, "Reservation Recovery Email ID  is failed");
		}
		SW.EnterValue("VPPropContent_PropConfgResRecovEmailID_EB","mayuri.a.mittal@accenture.com");
		
	
	
	}	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}

}
