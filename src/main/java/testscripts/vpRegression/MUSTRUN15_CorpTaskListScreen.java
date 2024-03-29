package testscripts.vpRegression;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN15_CorpTaskListScreen {
	CRS SW = new CRS();
	int Categoryid = SW.RandomNumber(0, 999);
	String StartDate= SW.GetTimeStamp("MM/dd/yyyy");
	String CategoryName="AutoCat"+Categoryid;
	
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
	SW.EnterValue("VP_MenuSearch_EB","Task List");
	SW.Click("VP_Corp_TaskList_LK");
	SW.SwitchToFrame("VP_MainFrame_FR");
	List<WebElement> Frames = SW.GetAllElements("VP_TLScreen_TaskListScreenLayouts_LK");
	if(Frames.size() == 3){
		Reporter.Write("TaskListScreen_Frames_Validation", "TaskListScreen Should contain all 3 frames", "TaskListScreen has all 3 frames", "PASS");
	}else{
		Reporter.Write("TaskListScreen_Frames_Validation", "TaskListScreen Should contain all 3 frames", "TaskListScreen doesnt have all 3 frames", "FAIL");
	}
	
	SW.Click("VP_TLScreen_LookUp_LK");
	if(SW.GetCurrentWindowsCount() == 2){
		SW.SwitchToWindow(2);
		if(SW.GetBrowserInfo().startsWith("IE") && SW.GetTitle().startsWith("Certificate Error")){	
		 SW.RunJavaScript("javascript:document.getElementById('overridelink').click();");
		}
		if(SW.ObjectExists("VP_TLScreen_PropertySearchValidation_ST")){
			Reporter.Write("TaskListScreen_PropertyLookup_PopupValidation", "Property Search Popup Should Get Opened", "Property Search Popup  Opened Sucessfully", "PASS");
		}
		else{
			Reporter.Write("TaskListScreen_PropertyLookUp_PopupValidation", "Property Search Popup Should Get Opened", "Property Search Popup Didn't Opened Sucessfully", "FAIL");
		}
		SW.CloseOnlyThisBrowser();
	}
	SW.SwitchToWindow(1);
	SW.SwitchToFrame("");
	SW.SwitchToFrame("VP_MainFrame_FR");
	
	int UserDDsize = SW.DropDown_GetSize("VP_TLScreen_User_DD");
	if(UserDDsize>1){
		SW.Click("VP_TLScreen_User_DD");
		Reporter.Write("TaskListScreen_UserDropDown_Validation", "User DropDown Should be present with the Dropdown values", "User DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("TaskListScreen_UserDropDown_Validation", "User DropDown Should be present with the Dropdown values","User DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int CTA_DDsize = SW.DropDown_GetSize("VP_TLScreen_ContentApproval_DD");
	if(CTA_DDsize>1){
		SW.Click("VP_TLScreen_ContentApproval_DD");
		Reporter.Write("TaskListScreen_ContentApprovalDropDown_Validation", "ContentApproval DropDown Should be present with the Dropdown values", "ContentApproval DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("TaskListScreen_ContentApprovalDropDown_Validation", "ContentApproval DropDown Should be present with the Dropdown values","ContentApproval DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int StatusDDsize = SW.DropDown_GetSize("VP_TLScreen_Status_DD");
	if(StatusDDsize>1){
		SW.Click("VP_TLScreen_Status_DD");
		Reporter.Write("TaskListScreen_StatusDropDown_Validation", "Status DropDown Should be present with the Dropdown values", "Status DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("TaskListScreen_StatusDropDown_Validation", "Status DropDown Should be present with the Dropdown values","Status DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int CTTDDsize = SW.DropDown_GetSize("VP_TLScreen_ContentType_DD");
	if(CTTDDsize>1){
		SW.Click("VP_TLScreen_ContentType_DD");
		Reporter.Write("TaskListScreen_ContentTypeDropDown_Validation", "ContentType DropDown Should be present with the Dropdown values", "ContentType DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("TaskListScreen_ContentTypeDropDown_Validation", "ContentType DropDown Should be present with the Dropdown values","ContentType DropDown Doesnt have  Dropdown values", "FAIL");
	}
	SW.DropDown_SelectByText("VP_TLScreen_ContentType_DD", "All");
	int WebDDsize = SW.DropDown_GetSize("VP_TLScreen_Web_DD");
	if(WebDDsize>1){
		SW.Click("VP_TLScreen_Web_DD");
		Reporter.Write("TaskListScreen_WebDropDown_Validation", "Web DropDown Should be present with the Dropdown values", "Web DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("TaskListScreen_WebDropDown_Validation", "Web DropDown Should be present with the Dropdown values","Web DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	int CopyWriteDDsize = SW.DropDown_GetSize("VP_TLScreen_Copywrite_DD");
	if(CopyWriteDDsize>1){
		SW.Click("VP_TLScreen_Copywrite_DD");
		Reporter.Write("TaskListScreen_CopywriteDropDown_Validation", "Copywrite DropDown Should be present with the Dropdown values", "Copywrite DropDown is present with the Dropdown values", "PASS");
	}else{
		Reporter.Write("TaskListScreen_CopywriteDropDown_Validation", "Copywrite DropDown Should be present with the Dropdown values","Copywrite DropDown Doesnt have  Dropdown values", "FAIL");
	}
	
	SW.EnterValue("VP_TLScreen_PropId_EB", SW.TestData("NonRosPropID_Opera"));
	SW.EnterValue("VP_TLScreen_OwnerUserID_EB", SW.TestData("VP_Username"));
	SW.Click("VP_TLScreen_Reset_BN");
	SW.HandleAlert(true);
	String Propid = SW.GetText("VP_TLScreen_PropId_EB");
	if(Propid.isEmpty()){
		Reporter.Write("TaskListScreen_ResetButtom_Validation", "All the fields should get Reset sucessfully", "All the fields got Reset sucessfully", "PASS");
	}else{
		Reporter.Write("TaskListScreen_ResetButtom_Validation", "All the fields should get Reset sucessfully","All the fields didnt got Reset sucessfully", "FAIL");
	
	}
	SW.Click("VP_TLScreen_AdvanceSearch_BN");
	if(SW.IsEnabled("VP_TLScreen_AdvanceSearchValidation_DD", "Enabled")){
		Reporter.Write("TaskListScreen_AdvanceSearch_Validation", "All the AdvanceSearch fields should get displayed sucessfully", "All the AdvanceSearch fields got displayed sucessfully", "PASS");
	}else{
		Reporter.Write("TaskListScreen_AdvanceSearch_Validation", "All the AdvanceSearch fields should get displayed sucessfully","All the AdvanceSearch fields weren't displayed sucessfully", "FAIL");
	}
	SW.Click("VP_TLScreen_AdvanceSearch_BN");
	SW.DropDown_SelectByText("VP_TLScreen_Status_DD", "Submitted");
	SW.VPClick("VP_TLScreen_Search_BN");
	SW.CheckBox("VP_TLScreen_SearchResult_CB", "ON");
	SW.VPClick("VP_TLScreen_Assume_BN");
	SW.CompareText("TaskListScreen_AssumeTaskSucessMsg_Validation", "Task is successfully Assumed", SW.GetText("VPRates_RPinfoMsg_DT").trim());
	SW.DropDown_SelectByText("VP_TLScreen_Status_DD", "Submitted");
	SW.VPClick("VP_TLScreen_Search_BN");
	SW.CheckBox("VP_TLScreen_SearchResult_CB", "ON");
	SW.VPClick("VP_TLScreen_ViewTask_BN");
	if(SW.IsEnabled("VP_TLScreen_ViewTaskScreenValidation_DD", "Enabled")){
		Reporter.Write("ViewTaskScreen_Validation", "View Task Screen should get displayed sucessfully", "View Task Screen  displayed sucessfully", "PASS");
	}else{
		Reporter.Write("ViewTaskScreen_Validation", "View Task Screen should get displayed sucessfully","View Task Screen wasn't displayed sucessfully", "FAIL");
	}
	SW.VPClick("VP_TLScreen_ViewTaskAssume_BN");
	SW.CompareText("ViewTaskScreen_AssumeTaskButton_Validation", "Task is successfully Assumed", SW.GetText("VPRates_RPinfoMsg_DT").trim());
	SW.DropDown_SelectByText("VP_TLScreen_Status_DD", "Submitted");
	SW.VPClick("VP_TLScreen_Search_BN");
	SW.CheckBox("VP_TLScreen_SearchResult_CB", "ON");
	SW.VPClick("VP_TLScreen_ViewTask_BN");
	SW.Click("VP_TLScreen_ReturnToList_BN");
	List<WebElement> Frames1 = SW.GetAllElements("VP_TLScreen_TaskListScreenLayouts_LK");
	if(Frames1.size() == 3){
		Reporter.Write("ViewTaskScreen_ReturnToListButton_Validation", "UserTask Screen should get displayed sucessfully", "UserTask Screen  displayed sucessfully", "PASS");
	}else{
		Reporter.Write("ViewTaskScreen_ReturnToListButton_Validation", "UserTask Screen should get displayed sucessfully","UserTask Screen wasn't displayed sucessfully", "FAIL");
	
	}
	
	}
	
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
	
}
