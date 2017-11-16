package testscripts.sgrRegression;
/** Purpose		: Validate Event Auto assignment switch in Admin screen    
 * TestCase Name: Validate Event Auto assignment switch in Admin screen 
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG65_ValidateEventAutoAssignmentSwitchInAdminScreen  {

	CRM SW = new CRM();
	String Location;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void SelectNewEventV2(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PropertyPreference_LK");
		SW.SelectRadioButton("SGRPropPref_EventAutoAssignYes_RB");
		SW.Click("SGRPropPref_SAVE_BT");
		if(SW.ObjectExists("//li[text()='Your changes were saved!']")){
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are saved successfully", "PASS");
		}else{
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are not saved successfully", "FAIL");
		}
	}

	@Test(priority=2, dependsOnMethods="SelectNewEventV2")
	public void ValidateNewEventPage(){
		SW.Click("SGRNavigation_Events_LK");
		if(SW.ObjectExists("SGREvents_AutoAssignSchedule_LK")){
			Reporter.Write("Validate Auto Assign link", "Auto Assign link Should be displayed", "Auto Assign link is Displayed", "PASS");
		}else{
			Reporter.Write("Validate Auto Assign link", "SGRPropPref_EventAutoAssignNo_RB", "Auto Assign link is not Displayed", "FAIL");
		}
	}

	@Test(priority=3, dependsOnMethods="ValidateNewEventPage")
	public void ChangeSelectedOption(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PropertyPreference_LK");
		SW.SelectRadioButton("SGRPropPref_EventAutoAssignNo_RB");
		SW.Click("SGRPropPref_SAVE_BT");
		if(SW.ObjectExists("//li[text()='Your changes were saved!']")){
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are saved successfully", "PASS");
		}else{
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are not saved successfully", "FAIL");
		}
	}
	@Test(priority=4, dependsOnMethods="ChangeSelectedOption")
	public void ValidateEventPage(){
		SW.Click("SGRNavigation_Events_LK");
		if(!SW.ObjectExists("SGREvents_AutoAssignSchedule_LK")){
			Reporter.Write("Validate Auto Assign link", "Auto Assign link should not Displayed", "Auto Assign link is not Displayed", "PASS");
		}else{
			Reporter.Write("Validate Auto Assign link", "Auto Assign link should not Displayed", "Auto Assign link is  Displayed", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

