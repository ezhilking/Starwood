package testscripts.sgrRegression;
/** Purpose		: Validate Activate Deactivate and Delete of VIP level from admin screen 
 * TestCase Name: 1. Validate Creation of VIP level in admin screen and validate in guest profile
 * 				  2. Validate Deactivation of VIP level in admin screen and validate in guest profile
 * 				  3. Validate Deletion of VIP level in admin screen and validate in guest profile 
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */

import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG70_ValidateActivateDeactivateAndDeleteOfVIPLevelAdminPage  {

	CRM SW = new CRM();
	String VIPLevelCode=SW.RandomString(4);
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SGRURL);
		
	}
	@Test(priority=1)
	public void AddVIPLevel(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Wait(20);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_VIP_LK");
		SW.EnterValue("SGRVIP_LevelCode_EB", VIPLevelCode);
		SW.EnterValue("SGRVIP_LevelDescription_EB", "AutoDesc");
		SW.Click("SGRVIP_AddVIPLevel_BT");
		//Validate in Guest profile 
		if(SW.ObjectExists("//span[text()='Successfully created new VIP Level.']")){
			Reporter.Write("Validate Success Message", "Successfully created new VIP Level.", "Successfully created new VIP Level.", "PASS");
		}else{
			Reporter.Write("Validate Success Message", "Successfully created new VIP Level.", "Success message is not displayed", "FAIL");
		}
		SW.Click("SGRNavigation_Home_LK");
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		SW.Click(FirstGuestLink);
		List<String> VIPLevels=SW.DropDown_GetText("SGRGuestProfile_VIPLevel_DD");
		SW.CompareTextContained("Validate VIP Levels",VIPLevelCode, VIPLevels.toString());
	}
	@Test(priority=2, dependsOnMethods="AddVIPLevel")
	public void DeactivateVIPLevel(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_VIP_LK");
		//Validate in VIP Page
		SW.Click("//td[text()='"+VIPLevelCode+"']//ancestor::tr//a[text()='edit']");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.CheckBox("SGRVIP_ActivateVIPLevel_CB", "OFF");
		SW.ClickAndProceed("SGRVIP_SaveVIPLevel_BT");
		SW.SwitchToWindow(1);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[text()='Successfully updated VIP Levels.']")){
			Reporter.Write("Validate Update Message", "Successfully updated VIP Levels.", "Successfully updated VIP Levels.", "PASS");
		}else{
			Reporter.Write("Validate Update Message", "Successfully updated VIP Levels.", "Success message is not displayed", "FAIL");
		}
		String ActiveCode=SW.GetText("//td[text()='"+VIPLevelCode+"']//ancestor::tr//td[7]");
		SW.CompareText("Validate is Active", "N", ActiveCode );
		//Validate in Guest Profile
		SW.Click("SGRNavigation_Home_LK");
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		SW.Click(FirstGuestLink);
		List<String> VIPLevels=SW.DropDown_GetText("SGRGuestProfile_VIPLevel_DD");
		if(!SW.CompareTextContained(VIPLevelCode, VIPLevels.toString())){
			Reporter.Write("Validate VIP Level in Guest Profile", "VIP Level should not present", "VIP Level is not present", "PASS");
		}else{
			Reporter.Write("Validate VIP Level in Guest Profile", "VIP Level should not present", "VIP Level is present after deactivationt", "FAIL");
		}
		
	}
	@Test(priority=3, dependsOnMethods="DeactivateVIPLevel")
	public void DeleteVIPLevel(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_VIP_LK");
		//Validate in VIP Page
		SW.Click("//td[text()='"+VIPLevelCode+"']//ancestor::tr//a[text()='edit']");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.CheckBox("SGRVIP_DeleteVIPLevel_CB", "ON");
		SW.ClickAndProceed("SGRVIP_SaveVIPLevel_BT");
		SW.SwitchToWindow(1);
		SW.WaitForPageload();
		
		//Validate success message
		if(SW.ObjectExists("//span[text()='Successfully updated VIP Levels.']")){
			Reporter.Write("Validate Update Message", "Successfully updated VIP Levels.", "Successfully updated VIP Levels.", "PASS");
		}else{
			Reporter.Write("Validate Update Message", "Successfully updated VIP Levels.", "Success message is not displayed", "FAIL");
		}
		//Validate existence of VIP in table
		if(!SW.ObjectExists("//td[text()='"+VIPLevelCode+"']")){
			Reporter.Write("Validate Deleted VIP in Table", "VIP Level should not be present", "VIP Level is not present", "PASS");
		}else{
			Reporter.Write("Validate Deleted VIP in Table", "VIP Level should not be present", "VIP Level is present after deletion", "FAIL");
		}
		//Validate in Guest Profile
		SW.Click("SGRNavigation_Home_LK");
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		SW.Click(FirstGuestLink);
		List<String> VIPLevels=SW.DropDown_GetText("SGRGuestProfile_VIPLevel_DD");
		if(!SW.CompareTextContained(VIPLevelCode, VIPLevels.toString())){
			Reporter.Write("Validate VIP Level in Guest Profile", "VIP Level should not present", "VIP Level is not present", "PASS");
		}else{
			Reporter.Write("Validate VIP Level in Guest Profile", "VIP Level should not present", "VIP Level is present after deactivationt", "FAIL");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

