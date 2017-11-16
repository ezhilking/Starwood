package testscripts.sgrRegression;
/** Purpose		: Validate the newly added Location in admin screen and in new events screen 
 * TestCase Name: Validate the newly added Location in admin screen and in new events screen 
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

public class SGR_REG58_ValidateAddDeleteLocationsInAdminPage  {

	CRM SW = new CRM();
	String Location;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void AddLocationAndPublish(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Wait(8);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Locations_LK");
		SW.DropDown_SelectByText("SGRLocations_LocationType_DD", "Guest Room");
		SW.WaitForPageload();
		Location= "Auto"+SW.RandomString(5);
		SW.EnterValue("SGRLocations_LocationDescription_EB", Location);
		SW.Click("SGRLocations_Save_BT");
		SW.Wait(10);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Reporter.Write("Validate publish", "Publish Success", "Publish Success", "PASS");
		}else{
			Reporter.Write("Validate publish", "Publish Success", "Publish Fail", "FAIL");
		}
	}

	@Test(priority=2, dependsOnMethods="AddLocationAndPublish")
	public void ValidateAddedlocationInEventPage(){
		SW.Wait(10);
		SW.Click("SGRCreateEvent_NewEvent_LK");
		SW.EnterValue("SGRCreateEvent_Where_EB", Location);
		SW.Wait(5);
		if(SW.ObjectExists("//li[contains(.,'"+Location+"')]")){
			Reporter.Write("Validate Location In Event Page", "Added Location should Present", "Added Location is Present", "PASS");
		}else{
			Reporter.Write("Validate Location In Event Page", "Added Location should Present", "Added Location is not Present", "FAIL");
		}
	}

	@Test(priority=3, dependsOnMethods="ValidateAddedlocationInEventPage")
	public void DeleteAddedLocation(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Locations_LK");
		SW.DropDown_SelectByText("SGRLocations_Locations_DD", Location);
		SW.WaitForPageload();
		SW.ClickAndProceed("SGRLocations_Delete_BT");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		if(!SW.ObjectExists("//select[@name='locID']//option[text()='"+Location+"']")){
			Reporter.Write("Validate Location In Event Page", "Deleted Location should not Present", "Deleted Location is not Present", "PASS");
		}else{
			Reporter.Write("Validate Location In Event Page", "Deleted Location should not Present", "Deleted Location is still Present", "FAIL");
		}
		
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

