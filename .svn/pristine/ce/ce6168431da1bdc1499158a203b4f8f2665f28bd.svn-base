/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name: SMOKETEST09_CorpSup_Saratoga_SearchMsg_CreateConditionalScriptWithCategory
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST09_CorpSup_Saratoga_SearchMsg_CreateConditionalScriptWithCategory {
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
	
	
	// Creating Category
	SW.Click("VP_MenuSearch_EB");
	SW.EnterValue("VP_MenuSearch_EB","Manage Categories");
	SW.Click("VPCorp_SaratogaManageCategory_LK");
	SW.SwitchToFrame("VP_MainFrame_FR");
	SW.Click("VPCorp_CreateCategory_BN");
	SW.EnterValue("VPCorp_CategoryDesc_EB", "Automation Category");
	SW.ClickAndProceed("VPCorp_CategorySave_BN");
	if(SW.IsAlertPresent()){
		SW.HandleAlert(true);
		Reporter.Write("CatogoryNameValidation", "Please Enter Category Name","Please Enter Category Name", "PASS");
	}else{
		Reporter.Write("CatogoryNameValidation", "Please Enter Category Name","Alert Pop Up Not Displayed", "FAIL");
	}
    SW.EnterValue("VPCorp_CategoryName_EB", CategoryName);
	SW.Click("VPCorp_CategorySave_BN");
	SW.CompareText("Category Creation Success Msg Validation", "Category Saved Successfully", SW.GetText("VPRates_RPinfoMsg_DT").trim());
	
	// Creating Conditional Message
	SW.SwitchToFrame("");
	SW.NormalClick("VP_MenuSearch_EB");
	SW.EnterValue("VP_MenuSearch_EB","Search Message");
	SW.Click("VPCorp_SearchMessage_LK");
	SW.SwitchToFrame("VP_MainFrame_FR");
	SW.Click("VPCorp_NewMessage_BN");
	SW.DropDown_SelectByText("VPCorp_ScriptTypeAssocation_DD", "Conditional Script");
	SW.EnterValue("VPCorp_ScriptStartDate_EB", StartDate);
	SW.DropDown_SelectByText("VPCorp_ScriptAgebtSubCategory_DD", CategoryName);
	SW.EnterValue("VPCorp_ScriptMsgDescription_EB", "Automated Conditional Type Script "+CategoryName);
	SW.SelectRadioButton("VPCorp_ScriptUserRole_RB");
	SW.DropDown_SelectByIndex("VPCorp_ScriptUserRole_DD", 1);
	SW.DropDown_SelectByIndex("VPCorp_ScriptPageName_DD", 1);
	SW.DropDown_SelectByIndex("VPCorp_ScriptDisplayType_DD", 1);
	SW.DropDown_SelectByIndex("VPCorp_ScriptPriority_DD", SW.RandomNumber(1, 3));
	SW.DropDown_SelectByIndex("VPCorp_ScriptAttachRule_DD", 1);
	SW.Click("VPCorp_ScriptSave_BN");	
	SW.CompareText("Conditional Script Success Msg Validation", "Conditional Script is Successfully Created", SW.GetText("VPRates_RPinfoMsg_DT").trim());
	SW.CompareText("Created Script Subcatogory Validation", CategoryName, SW.DropDown_GetSelectedText("VPCorp_ScriptAgebtSubCategory_DD"));
	SW.CompareText("Created Script Given Message Validation","Automated Conditional Type Script "+CategoryName,SW.GetText("VPCorp_ScriptMsgDescription_EB"));
	
	// Deleting Created Message
		SW.Click("VPCorp_ScriptCancel_BN");
		SW.DropDown_SelectByText("VPCorp_ScriptType_DD", "Conditional Script");
		SW.EnterValue("VPCorp_ScriptMsgText_EB", "Automated Conditional Type Script "+CategoryName);
		SW.Click("VPCorp_ScriptSearch_BN");
		SW.SelectRadioButton("VPCorp_ScriptSearchResult_RB");
		SW.ClickAndProceed("VPCorp_ScriptDelete_BN");
		SW.HandleAlert(true);
		SW.CompareText("Script Deleted Message Validation", "Script is Successfully Deleted",SW.GetText("VPRates_RPinfoMsg_DT").trim());
		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}
