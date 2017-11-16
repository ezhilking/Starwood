package testscripts.sgrRegression;
/** Purpose		: Validate Selected Compensation in Create Event screen when GCD of type defect is selected
 * TestCase Name: Validate Selected Compensation in Create Event screen when GCD of type defect is selected
 * 				  Validate DeSelected Compensation in Create Event screen when GCD of type defect is selected
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG39_Validate_SelectedCompensationInEventScreen {
	CRM SW = new CRM();
	String EventNotes,SelectedDescription;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void SelectCompensation(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1967");	//	Stage : 1965
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Compensation_LK");
		SW.CheckBox("SGRCompensation_Unchecked_LK", "ON");
		SelectedDescription= SW.GetText("SGRCompensation_checkedDescription_LK");
		Environment.loger.log(Level.INFO, "Selected Compensation- "+SelectedDescription);
		SW.Click("SGRCompensation_Save_BT");
		
		if(SW.ObjectExists("//span[text()='Changes saved successfully.']")){
			Environment.loger.log(Level.INFO, "Compensation saved successfully!");
			SW.TakeScreenshot("CompensationChanges");
		}else{
			Environment.loger.log(Level.INFO, "Faile d to update Compensation ");
			
			Reporter.Write("NA", "Failed to update Compensation", "Failed to update Compensation","FAIL");
		}
		SW.Click("SGRNavigation_Admin_LK");
		//Publish the changes
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Environment.loger.log(Level.INFO, "Successfully published changes!");
			SW.TakeScreenshot("PublishSuccess");
		}else{
			Environment.loger.log(Level.ERROR, "Publish failed!");
			Reporter.Write("NA", "Publish failed!", "Publish failed!","FAIL");
		}
	}
	@Test(priority=2 , dependsOnMethods="SelectCompensation")
	public void validateInEventScreen()
	{
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("NA", "No Guest present in Inhouse", "No Guest present in Inhouse","FAIL");
		}

		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "Cancellation");
		SW.Wait(5);
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.NormalClick("SGREvent_ServiceOpAndCompensation+_IC");
		String Compensations=SW.DropDown_GetText("SGREvent_CompansationType_DD").toString();
		if(SW.CompareTextContained("CheckCompensation", SelectedDescription, Compensations)){
			Environment.loger.log(Level.INFO, "Added Compensation is present in the drop down");
		}else{
			Reporter.Write("NA", "Added Compensation is not present in the drop down", "Added Compensation is not present in the drop down","FAIL");
		}
		
	}
	
	@Test(priority=3, dependsOnMethods="validateInEventScreen")
	public void DeSelectCompensation(){
		
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Compensation_LK");
		SW.CheckBox("//td[text()='"+SelectedDescription+" ']//ancestor::tr//td[2]/input", "OFF");
		SW.Click("SGRCompensation_Save_BT");
		
		if(SW.ObjectExists("//span[text()='Changes saved successfully.']")){
			Environment.loger.log(Level.INFO, "Compensation saved successfully!");
			SW.TakeScreenshot("CompensationChanges");
		}else{
			Environment.loger.log(Level.INFO, "Faile d to update Compensation ");
			Reporter.Write("NA", "Failed to update Compensation", "Failed to update Compensation","FAIL");
		}
		SW.Click("SGRNavigation_Admin_LK");
		//Publish the changes
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Environment.loger.log(Level.INFO, "Successfully published changes!");
			SW.TakeScreenshot("PublishSuccess");
		}else{
			Environment.loger.log(Level.ERROR, "Publish failed!");
			Reporter.Write("NA", "Publish failed!", "Publish failed!","FAIL");
			
		}
	}
	@Test(priority=4 , dependsOnMethods="DeSelectCompensation")
	public void validateInEventScreenAfterDeselect()
	{
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("NA", "No Guest present in Inhouse", "No Guest present in Inhouse","FAIL");
			
		}
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "Cancellation");
		SW.Wait(5);
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.NormalClick("SGREvent_ServiceOpAndCompensation+_IC");
		String Compensations=SW.DropDown_GetText("SGREvent_CompansationType_DD").toString();
		if(!SW.CompareTextContained(SelectedDescription, Compensations)){
			Reporter.Write("ValidateCompansation", "Removed Compensation should not present in the drop down", "Removed Compensation is not present in the drop down","PASS");
		}else{
			Reporter.Write("NA", "Removed Compensation is still present in the drop down", "Removed Compensation is still present in the drop down","FAIL");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
