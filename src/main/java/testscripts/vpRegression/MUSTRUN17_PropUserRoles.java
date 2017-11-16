package testscripts.vpRegression;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN17_PropUserRoles {
	CRS SW = new CRS();
	int Categoryid = SW.RandomNumber(0, 999);
	String StartDate= SW.GetTimeStamp("MM/dd/yyyy");
		
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}
	
	@Test(priority=0)
    public void TransientRatePlan(){
	SW.VPLogin("VP_Username", "VP_Password");
	SW.Click("VP_MenuSearch_EB");
	SW.EnterValue("VP_MenuSearch_EB","Assign Property User Roles");
	SW.Click("VP_Corp_AssPropUSRoles_LK");
	SW.WaitForObject("VP_MainFrame_FR");
	SW.SwitchToFrame("VP_MainFrame_FR");
	List<WebElement> Frames = SW.GetAllElements("VP_PropUSRoles_ScreenFramesValidation_WT");
	if(Frames.size() == 3){
		Reporter.Write("PropUserRolessScreen_Frames_Validation", "PropUserRolessScreen Should contain all 3 frames", "PropUserRolessScreen has all 3 frames", "PASS");
	}else{
		Reporter.Write("PropUserRolessScreen_Frames_Validation", "PropUserRolessScreen Should contain all 3 frames", "PropUserRolessScreen doesnt have all 3 frames", "FAIL");
	}
	
	int BrandDDsize = SW.DropDown_GetSize("VP_PropUSRoles_Brand_DD");
	if(BrandDDsize>1){
		SW.Click("VP_PropUSRoles_Brand_DD");
		Reporter.Write("PropUserRolessScreen_BrandDropDown_Validation", "Brand DropDown Should be present with the Dropdown values", "Brand DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("PropUserRolessScreen_BrandDropDown_Validation", "Brand DropDown Should be present with the Dropdown values","Brand DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int DivisionDDsize = SW.DropDown_GetSize("VP_PropUSRoles_Division_DD");
	if(DivisionDDsize>1){
		SW.Click("VP_PropUSRoles_Division_DD");
		Reporter.Write("PropUserRolessScreen_DivisionDropDown_Validation", "Division DropDown Should be present with the Dropdown values", "Division DropDown is present with the Dropdown values", "PASS");
		SW.DropDown_SelectByIndex("VP_PropUSRoles_Division_DD", 1);
		SW.Wait(5);
	}else{
		Reporter.Write("PropUserRolessScreen_DivisionDropDown_Validation", "Division DropDown Should be present with the Dropdown values","Division DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int RegionDDsize = SW.DropDown_GetSize("VP_PropUSRoles_Region_DD");
	if(RegionDDsize>1){
		SW.Click("VP_PropUSRoles_Region_DD");
		Reporter.Write("PropUserRolessScreen_RegionDropDown_Validation", "Region DropDown Should be present with the Dropdown values", "Region DropDown is present with the Dropdown values", "PASS");
		SW.DropDown_SelectByIndex("VP_PropUSRoles_Division_DD", 0);
		SW.Wait(5);
	}else{
		Reporter.Write("PropUserRolessScreen_RegionDropDown_Validation", "Region DropDown Should be present with the Dropdown values","Region DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int OwnerShipDDsize = SW.DropDown_GetSize("VP_PropUSRoles_OwnerShip_DD");
	if(OwnerShipDDsize>1){
		SW.Click("VP_PropUSRoles_OwnerShip_DD");
		Reporter.Write("PropUserRolessScreen_OwnerShipDropDown_Validation", "OwnerShip DropDown Should be present with the Dropdown values", "OwnerShip DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("PropUserRolessScreen_OwnerShipDropDown_Validation", "OwnerShip DropDown Should be present with the Dropdown values","OwnerShip DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int ProptypeDDsize = SW.DropDown_GetSize("VP_PropUSRoles_Proptype_DD");
	if(ProptypeDDsize>1){
		SW.Click("VP_PropUSRoles_Proptype_DD");
		Reporter.Write("PropUserRolessScreen_ProptypeDropDown_Validation", "Proptype DropDown Should be present with the Dropdown values", "Proptype DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("PropUserRolessScreen_ProptypeDropDown_Validation", "Proptype DropDown Should be present with the Dropdown values","Proptype DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int StateDDsize = SW.DropDown_GetSize("VP_PropUSRoles_State_DD");
	if(StateDDsize>1){
		SW.Click("VP_PropUSRoles_State_DD");
		Reporter.Write("PropUserRolessScreen_StateDropDown_Validation", "State DropDown Should be present with the Dropdown values", "State DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("PropUserRolessScreen_StateDropDown_Validation", "State DropDown Should be present with the Dropdown values","State DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int CountryDDsize = SW.DropDown_GetSize("VP_PropUSRoles_Country_DD");
	if(CountryDDsize>1){
		SW.Click("VP_PropUSRoles_Country_DD");
		Reporter.Write("PropUserRolessScreen_CountryDropDown_Validation", "Country DropDown Should be present with the Dropdown values", "Country DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("PropUserRolessScreen_CountryDropDown_Validation", "Country DropDown Should be present with the Dropdown values","Country DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	
	SW.Click("VP_PropUSRoles_UserLookup_LK");
	if(SW.GetCurrentWindowsCount() == 2){
		SW.SwitchToWindow(2);
		if(SW.GetBrowserInfo().startsWith("IE") && SW.GetTitle().startsWith("Certificate Error")){	
		 SW.RunJavaScript("javascript:document.getElementById('overridelink').click();");
		}
		if(SW.ObjectExists("VP_MainFrame_FR")){
			SW.SwitchToFrame("VP_MainFrame_FR");
		}
		if(SW.ObjectExists("VP_Username_EB")){
			SW.VPLogin("VP_Username", "VP_Password");
		}
		if(SW.ObjectExists("VP_PropUSRoles_LookupUserId_EB")){
			Reporter.Write("PropUserRolessScreen_UserLookup_PopupValidation", "User Search Popup Should Get Opened", "User Search Popup  Opened Sucessfully", "PASS");
		}
		else{
			Reporter.Write("PropUserRolessScreen_UserLookUp_PopupValidation", "User Search Popup Should Get Opened", "User Search Popup Didn't Opened Sucessfully", "FAIL");
		}
		SW.CloseOnlyThisBrowser();
	}
	SW.SwitchToWindow(1);
	SW.SwitchToFrame("");
	SW.SwitchToFrame("VP_MainFrame_FR");
	
//	SW.EnterValue("VP_PropUSRoles_LoginId_EB", "ttestus1");
//	SW.VPClick("VP_PropUSRoles_AddUser_BN");
//	SW.CompareText("NewUserErrorMsg_Validation", "User has roles already assigned, please select Modify/Delete User", SW.GetText("VPRates_ErrMsg_DT").trim());
//	
	SW.EnterValue("VP_PropUSRoles_LoginId_EB", "ttestus1");
	SW.VPClick("VP_PropUSRoles_ModifyUser_BN");
	
	SW.CheckBox("VP_PropUSRoles_AddedPropid_CB", "ON");
	SW.Click("VP_PropUSRoles_Remove_BN");
	
	SW.CheckBox("VP_PropUSRoles_SelectedPropid_CB", "ON");
	SW.Click("VP_PropUSRoles_Add_BN");
	
	SW.CheckBox("VP_PropUSRoles_AssignRole_CB", "ON");
	SW.Click("VP_PropUSRoles_Submit_CB");
	SW.CompareText("AssignRole_Success_Msg_Validation", "User Successfully Modified.",SW.GetText("VPRates_RPinfoMsg_DT").trim());
	
	SW.CheckBox("VP_PropUSRoles_DeleteRole_CB", "ON");
	SW.VPClick("VP_PropUSRoles_Submit_CB");
	if(SW.IsAlertPresent()){
		SW.HandleAlert(true);
	}
	SW.CompareText("AssignRole_Success_Msg_Validation", "User Successfully Modified.",SW.GetText("VPRates_RPinfoMsg_DT").trim());
	
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
	
}
