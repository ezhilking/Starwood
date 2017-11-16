package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN16_CorpUserRoles {
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
	SW.EnterValue("VP_MenuSearch_EB","Assign Corporate User Roles");
	SW.Click("VP_Corp_AssCorpUSRoles_LK");
	SW.WaitForObject("VP_MainFrame_FR");
	SW.SwitchToFrame("VP_MainFrame_FR");
	if(SW.IsEnabled("VP_CorpUSRoles_LoginId_EB", "ENABLED")){
		Reporter.Write("CorpUserRolessScreen_Validation", "CorpUserRolesScreen Loaded sucessfully", "CorpUserRolesScreen Loaded sucessfully", "PASS");
	}else{
		Reporter.Write("CorpUserRolessScreen_Validation", "CorpUserRolesScreen Loaded sucessfully", "CorpUserRolesScreen didn't Loaded sucessfully", "FAIL");
	}
	SW.EnterValue("VP_CorpUSRoles_LoginId_EB", "ttestus1");
	SW.VPClick("VP_CorpUSRoles_ModifyUser_BN");
	SW.CompareText("NewUserErrorMsg_Validation", "User ttestus1 is not associated to the system, please select Add User", SW.GetText("VPRates_ErrMsg_DT").trim());
	SW.VPClick("VP_CorpUSRoles_AddUser_BN");
	SW.CompareText("PropUserErrorMsg_Validation", "Corporate user role cannot be assigned if Property user role is already assigned to the user. Please delete the Property user role first if you would like to continue with this assignment", SW.GetText("VPRates_CriticalErrMsg_BT").trim());
	SW.EnterValue("VP_CorpUSRoles_LoginId_EB", SW.TestData("VP_Username"));
	SW.VPClick("VP_CorpUSRoles_AddUser_BN");
	SW.CompareText("AlreadyAddedErrorMsg_Validation", "User has roles already assigned, please select Modify/Delete User", SW.GetText("VPRates_ErrMsg_DT").trim());
	SW.VPClick("VP_CorpUSRoles_ModifyUser_BN");
	SW.CheckBox("VP_CorpUSRoles_DeleteRole_CB", "ON");
	SW.ClickAndProceed("VP_CorpUSRoles_Submit_BN");
	SW.HandleAlert(true);
	SW.CompareText("CorpUserRole_DeleteRoleSucessMsg_Validation", "User roles successfully Modified.", SW.GetText("VPRates_RPinfoMsg_DT").trim());
	SW.CheckBox("VP_CorpUSRoles_AssignRole_CB", "ON");
	SW.Click("VP_CorpUSRoles_Submit_BN");
	SW.CompareText("CorpUserRole_AssignRoleSucessMsg_Validation", "User roles successfully Modified.", SW.GetText("VPRates_RPinfoMsg_DT").trim());
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
	
}
