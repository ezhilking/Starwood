package testscripts.vpRegression;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN22_CreateWEBGDSOffice {
	CRS SW = new CRS();
	int Officeid = SW.RandomNumber(0, 999);
	String OpenDate= SW.GetTimeStamp("MM/dd/yyyy");

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
	}
	
	@Test(priority=0)
	    public void TransientRatePlan(){
		SW.VPLogin("VP_Username", "VP_Password");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Manage Corporate Offices");
		SW.Click("VPCorp_ManageCorpOff_LK");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.DropDown_SelectByText("VPCorp_CorpOfficeType_DD", "WEB/GDS OFFICE");
		SW.VPClick("VPCorp_CreateOffice_BN");
		if(SW.ObjectExists("VPCorp_CreateOffice_BN")){
			SW.VPClick("VPCorp_CreateOffice_BN");
		}
		//Validating Small office name Error Message
		SW.EnterValue("VPCorp_FullOfficeName_EB", "Automated WEBGDS Office-"+Officeid);
		SW.EnterValue("VPCorp_OfficeAddress_EB", "Banglore India");
		SW.DropDown_SelectByIndex("VPCorp_ResBillingCodeWebGDS_DD", 1);
		SW.DropDown_SelectByIndex("VPCorp_DistributionCode_DD", 1);
		SW.DropDown_SelectByIndex("VPCorp_MasterChannelId_DD", 1);
		SW.DropDown_SelectByIndex("VPCorp_ChannelId_DD", 1);
		SW.DropDown_SelectByIndex("VPCorp_GroupingLoc_DD", 1);
		SW.Click("VPCorp_CreateOfficeSave_BN");
		SW.CompareText("MUSTRUN10_CreateWEBGDSOffice_SmallOfficeNameErrMgs_DT","Please enter the short office name, this cannot be blank", SW.GetText("VPRates_ErrMsg_DT").trim());
		
		//Validating Open Date Error Message
		SW.EnterValue("VPCorp_ShortOfficeName_EB", "AutoWEBGDSOff-"+Officeid);
		SW.ClearValue("VPCorp_OfficeOpenDate_EB");
		SW.Click("VPCorp_CreateOfficeSave_BN");
		SW.CompareText("MUSTRUN10_CreateWEBGDSOffice_OpenDateErrMgs_DT","Please enter the office opening date, this cannot be blank", SW.GetText("VPRates_ErrMsg_DT").trim());
		
		//Validating Full office name Error Message
		SW.EnterValue("VPCorp_OfficeOpenDate_EB", OpenDate);
		SW.ClearValue("VPCorp_FullOfficeName_EB");
		SW.Click("VPCorp_CreateOfficeSave_BN");
		SW.CompareText("MUSTRUN10_CreateWEBGDSOffice_FullOfficeNameErrMgs_DT","Please enter the full office name, this cannot be blank", SW.GetText("VPRates_ErrMsg_DT").trim());
		
		//Validating Address Field Error Message
		SW.EnterValue("VPCorp_FullOfficeName_EB", "Automated WEBGDS Office-"+Officeid);
		SW.ClearValue("VPCorp_OfficeAddress_EB");
		SW.Click("VPCorp_CreateOfficeSave_BN");
		SW.CompareText("MUSTRUN10_CreateWEBGDSOffice_AddressfiledErrMgs_DT","Please enter the first line of the address. This cannot be blank", SW.GetText("VPRates_ErrMsg_DT").trim());
		
		//Validating Res Billing Code Error Message
		SW.EnterValue("VPCorp_OfficeAddress_EB", "Banglore India");
		SW.DropDown_SelectByIndex("VPCorp_ResBillingCodeWebGDS_DD", 0);
		SW.CompareText("MUSTRUN10_CreateWEBGDSOffice_AddressfiledErrMgs_DT","Please Select Res Billing Code. Res Billing Code is mandatory when Reservation Fee Indicator is set to Yes.", SW.GetText("VPRates_ErrMsg_DT").trim());
		
		//validating  
		//Creating Divisional office and validating Success Msg
		
		SW.Click("VPCorp_CreateOfficeSave_BN");
		String ActualMsg = SW.GetText("VRPRated_GeneralErrorMessage_DT").trim();
		if(ActualMsg.contains("Created Successfully")){
			Reporter.Write("MUSTRUN10_CreateWEBGDSOffice_SucessMessage_DT", "Corporate Office Created Successfully", ActualMsg, "Pass");
		}else{
			Reporter.Write("MUSTRUN10_CreateWEBGDSOffice_ErrorMessage_DT", "Corporate Office Created Successfully", ActualMsg, "Fail");
			
		} 
		
		//View the Above Created Office
		SW.DropDown_SelectByText("VPCorp_SearchBy_DD", "Short Office Name");
		SW.EnterValue("VPCorp_SearchBy_EB", "AutoWEBGDSOff--"+Officeid);
		SW.VPClick("VPCorp_Search_BN");
		SW.SelectRadioButton("VPCorp_SearchResult_RB");
		SW.VPClick("VPCorp_View_BN");
		SW.CompareText("MUSTRUN10_CreateWEBGDSOffice_ViewOffice", "AutoWEBGDSOff--"+Officeid, SW.GetAttributeValue("VPCorp_ViewSmallOfficeName_EB", "value"));
		SW.Click("VPCorp_ReturnToList_BN");
		
		//Modifying above created office
		SW.SelectRadioButton("VPCorp_SearchResult_RB");
		SW.VPClick("VPCorp_Modify_BN");
		SW.EnterValue("VPCorp_OfficeAddress_EB", "Banglore India 560066");
		SW.VPClick("VPCorp_CreateOfficeSave_BN");
		String ActualMsg2 = SW.GetText("VRPRated_GeneralErrorMessage_DT").trim();
		if(ActualMsg2.contains("Modified Successfully")){
			Reporter.Write("MUSTRUN10_CreateWEBGDSOffice_ModifySucessMessage_DT", "Corporate Office Modified Successfully", ActualMsg2, "Pass");
		}else{
			Reporter.Write("MUSTRUN10_CreateWEBGDSOffice_ModifyErrorMessage_DT", "Corporate Office Modified Successfully", ActualMsg2, "Fail");
			
		}		
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}