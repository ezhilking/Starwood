package testscripts.sgrRegression;
/** Purpose		: Validate new event switch v2 in Admin screen   
 * TestCase Name: Validate new event switch v2 in Admin screen   
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

public class SGR_REG64_ValidateNewEventSwitchV2InAdminScreen  {

	CRM SW = new CRM();
	
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
		SW.SelectRadioButton("SGRPropPref_NewEventScreenV2Yes_RB");
		SW.Click("SGRPropPref_SAVE_BT");
		if(SW.ObjectExists("//li[text()='Your changes were saved!']")){
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are saved successfully", "PASS");
		}else{
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are not saved successfully", "FAIL");
		}
	}

	@Test(priority=2, dependsOnMethods="SelectNewEventV2")
	public void ValidateNewEventPage(){
		SW.Click("SGRCreateEvent_NewEvent_LK");
		if(SW.ObjectExists("SGRCreateEvent_Where_EB")){
			Reporter.Write("Validate New Event Page", "New Event page is Displayed", "New Event page is Displayed", "PASS");
		}else{
			Reporter.Write("Validate New Event Page", "New Event page is Displayed", "New Event page is not Displayed", "FAIL");
		}
	}

	@Test(priority=3, dependsOnMethods="ValidateNewEventPage")
	public void ChangeSelectedOption(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PropertyPreference_LK");
		SW.SelectRadioButton("SGRPropPref_NewEventScreenV2No_RB");
		SW.Click("SGRPropPref_SAVE_BT");
		if(SW.ObjectExists("//li[text()='Your changes were saved!']")){
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are saved successfully", "PASS");
		}else{
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are not saved successfully", "FAIL");
		}
	}
	
	@Test(priority=4, dependsOnMethods="ChangeSelectedOption")
	public void ValidateOldEventPage(){
		SW.Click("SGRCreateEvent_NewEvent_LK");
		if(SW.ObjectExists("SGRNewEvent_EvenOrigin_DD")){
			Reporter.Write("Validate Old Event Page", "Old Event page is Displayed", "Old Event page is Displayed", "PASS");
		}else{
			Reporter.Write("Validate Old Event Page", "Old Event page is Displayed", "Old Event page is not Displayed", "FAIL");
		}
	}
	
	@Test(priority=5, dependsOnMethods="ValidateOldEventPage")
	public void ChangeSelectedOptionBackToNew(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PropertyPreference_LK");
		SW.SelectRadioButton("SGRPropPref_NewEventScreenV2Yes_RB");
		SW.Click("SGRPropPref_SAVE_BT");
		if(SW.ObjectExists("//li[text()='Your changes were saved!']")){
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are saved successfully", "PASS");
		}else{
			Reporter.Write("Validate Changes Saved", "Changes are saved successfully", "Changes are not saved successfully", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

