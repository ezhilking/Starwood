package testscripts.sgrRegression;
/** Purpose		: CCC, LUX, AMB Dist Lists_validate that a list can be created, add/remove an associate, contact schedule can be created, GCDs can be mapped, and all saved
 * TestCase Name: CCC, LUX, AMB Dist Lists_validate that a list can be created, add/remove an associate, contact schedule can be created, GCDs can be mapped, and all saved
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

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG21_Validate_CCC_LUX_AMB_Dist_Lists  {

	CRM SW = new CRM();
	String DistName,DistDesc;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void AddCCCDisstList(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Wait(20);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_CCCDisList_LK");
		DistName= SW.RandomString(5);
		DistDesc= SW.RandomString(5);
		SW.EnterValue("SGRDistList_DistlistName_EB", DistName);
		SW.EnterValue("SGRDistList_DistlistDesc_EB", DistDesc);
		SW.Click("SGRDistList_Create_BT");
		SW.DropDown_SelectByIndex("SGRDistList_AvailableContacts_DD", 1);
		SW.Click("SGRDistList_AddEmailID<<_BT");
		SW.CheckBox("SGRDistList_24X7_CB", "ON");
		SW.Click("SGRDistList_SAVE_BT");
		if(SW.ObjectExists("SGRDistList_SAVEConfirmation_ST")){
			Environment.loger.log(Level.INFO, "CCC Distribution list is added successfully!");
			SW.GetScreenshot("CCCDistList");
		}else{
			Environment.loger.log(Level.INFO, "CCC Distribution list Failed to save");
			SW.FailCurrentTest("CCC Distribution list Failed to save");
		}

	}

	@Test(priority=2, dependsOnMethods="AddCCCDisstList")
	public void AddCCCDisstListGCDMapping(){

		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_CCCDisListGCDMaping_LK");
		SW.Click("//select[@id='selDistList']/option[contains(.,'"+DistName+"')]");
		SW.CheckBox("SGRDistList_CheckAllGCD_CB", "ON");
		SW.Click("SGRDistList_SaveMapping_BT");
		if(SW.ObjectExists("SGRDistList_SAVEConfirmation_ST")){
			Environment.loger.log(Level.INFO, "CCC Distribution list GCD mapping success");
			SW.GetScreenshot("CCCDistList");
		}else{
			Environment.loger.log(Level.INFO, "CCC Distribution list GCD Mapping Failed");
			SW.FailCurrentTest("CCC Distribution list GCD Mapping Failed");
		}
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_CCCDisList_LK");
		SW.DropDown_SelectByText("SGRDistList_SelectDistributionList_DD", DistName+" - "+DistDesc);
		SW.WaitForPageload();
		SW.Wait(10);
		if(SW.ObjectExists("//td[text()=' All GCDs mapped.']")){
			Environment.loger.log(Level.INFO, "Mapped GCD present in the list");
			SW.GetScreenshot("CCCDistListMapping");
		}else{
			Environment.loger.log(Level.INFO, "Mapped GCD is not present in distribution list");
			SW.FailCurrentTest("Mapped GCD is not present in distribution list");
		}

	}
	@Test(priority=3, dependsOnMethods="AddCCCDisstListGCDMapping")
	public void AddLuxDisstList(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_LuxDisList_LK");
		DistName= SW.RandomString(5);
		DistDesc= SW.RandomString(5);
		SW.EnterValue("SGRDistList_DistlistName_EB", DistName);
		SW.EnterValue("SGRDistList_DistlistDesc_EB", DistDesc);
		SW.Click("SGRDistList_Create_BT");
		SW.DropDown_SelectByIndex("SGRDistList_AvailableContacts_DD", 1);
		SW.Click("SGRDistList_AddEmailID<<_BT");
		SW.CheckBox("SGRDistList_24X7_CB", "ON");
		SW.Click("SGRDistList_SAVE_BT");
		if(SW.ObjectExists("SGRDistList_SAVEConfirmation_ST")){
			Environment.loger.log(Level.INFO, "LUX Distribution list is added successfully!");
			SW.GetScreenshot("LUXDistList");
		}else{
			Environment.loger.log(Level.INFO, "LUX Distribution list Failed to save");
			SW.FailCurrentTest("LUX Distribution list Failed to save");
		}

	}

	@Test(priority=4, dependsOnMethods="AddLuxDisstList")
	public void AddLuxDisstListGCDMapping(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_LuxDisListGCDMaping_LK");
		SW.Click("//select[@id='selDistList']/option[contains(.,'"+DistName+"')]");
		SW.CheckBox("SGRDistList_CheckAllGCD_CB", "ON");
		SW.Click("SGRDistList_SaveMapping_BT");
		if(SW.ObjectExists("SGRDistList_SAVEConfirmation_ST")){
			Environment.loger.log(Level.INFO, "Lux Distribution list GCD mapping success");
			SW.GetScreenshot("LuxDistList");
		}else{
			Environment.loger.log(Level.INFO, "Lux Distribution list GCD Mapping Failed");
			SW.FailCurrentTest("Lux Distribution list GCD Mapping Failed");
		}
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_LuxDisList_LK");
		SW.DropDown_SelectByText("SGRDistList_SelectDistributionList_DD", DistName+" - "+DistDesc);
		SW.WaitForPageload();
		SW.Wait(10);
		if(SW.ObjectExists("//td[text()=' All GCDs mapped.']")){
			Environment.loger.log(Level.INFO, "Mapped GCD present in the list");
			SW.GetScreenshot("LuxDistListMapping");
		}else{
			Environment.loger.log(Level.INFO, "Mapped GCD is not present in distribution list");
			SW.FailCurrentTest("Mapped GCD is not present in distribution list");
		}

	}
	@Test(priority=5, dependsOnMethods="AddLuxDisstListGCDMapping")
	public void AddAMBDistlist(){ 
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_AMBDisList_LK");
		SW.DropDown_SelectByText("SGRDistList_SelectDistributionList_DD", "AMB Requests - AMB Requests");
		SW.WaitForAppLoad();
		SW.DropDown_SelectByIndex("SGRDistList_AvailableContacts_DD", 1);
		SW.Click("SGRDistList_AddEmailID<<_BT");
		SW.Click("SGRDistList_SAVE_BT");
		if(SW.ObjectExists("SGRDistList_SAVEConfirmation_ST")){
			Environment.loger.log(Level.INFO, "AMB Distribution list is added successfully!");
			SW.GetScreenshot("AMBDistList");
		}else{
			Environment.loger.log(Level.INFO, "AMB Distribution list Failed to save");
			SW.FailCurrentTest("AMB Distribution list Failed to save");
		}

	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

