package testscripts.sgrRegression;
/** Purpose		: Validate the newly Enabled/Disabled Department in new events screen 
 * TestCase Name: Validate the newly Enabled Department in new events screen
 * 				  Validate the Disabled Department in new events screen
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG60_ValidateEnableDisableDepartmentInAdminPage  {

	CRM SW = new CRM();
	String Department;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void EnableDepartmentAndPublish(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Wait(8);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Departments_LK");
		SW.CheckBox("SGRDepartments_FirstUnSelectedDepartment_CB", "ON");
		Department=SW.GetText("SGRDepartments_FirstUnSelectedDepartmentName_DT");
		SW.Click("SGRDepartments_DepartmentSave_BT");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Reporter.Write("Validate publish", "Publish Success", "Publish Success", "PASS");
		}else{
			Reporter.Write("Validate publish", "Publish Success", "Publish Fail", "FAIL");
		}
	}

	@Test(priority=2, dependsOnMethods="EnableDepartmentAndPublish")
	public void ValidateEnabledDepartmentInEventPage(){
		SW.Click("SGRCreateEvent_NewEvent_LK");
		List<String> Departments=SW.DropDown_GetText("SGRCreateEvent_Department_DD");
		if(SW.CompareTextContained(Department, Departments.toString())){
			Reporter.Write("Validate Existance of Department In Event Page", "Enabled Department should Present", "Enabled Department is Present", "PASS");
		}else{
			Reporter.Write("Validate Existance of Department In Event Page", "Enabled Department should Present", "Enabled Department is not Present", "FAIL");
		}
	}

	@Test(priority=3, dependsOnMethods="ValidateEnabledDepartmentInEventPage")
	public void DisableDepartment(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Departments_LK");
		SW.CheckBox("//td[text()='"+Department+" ']//ancestor::tr//input", "OFF");
		SW.Click("SGRDepartments_DepartmentSave_BT");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Reporter.Write("Validate publish", "Publish Success", "Publish Success", "PASS");
		}else{
			Reporter.Write("Validate publish", "Publish Success", "Publish Fail", "FAIL");
		}
		
	}
	@Test(priority=4, dependsOnMethods="DisableDepartment")
	public void ValidateDisabledDepartmentInEventPage(){
		SW.Click("SGRCreateEvent_NewEvent_LK");
		List<String> Departments=SW.DropDown_GetText("SGRCreateEvent_Department_DD");
		if(!SW.CompareTextContained(Department, Departments.toString())){
			Reporter.Write("Validate Non-Existance of Department In Event Page", "Disabled Department should Not Present", "Disabled Department is Not Present", "PASS");
		}else{
			Reporter.Write("Validate Non-Existance of Department In Event Page", "Disabled Department should Not Present", "Disabled Department is Present", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

