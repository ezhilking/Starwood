package testscripts.sgrRegression;
/** Purpose		: Validation of Service Opportunity
 * TestCase Name: Create Service Opportunity from GuestMasterProfile page in SGR
				  Modify Service Opportunity  from GuestMasterProfile page in SGR
				  Delete Service Opportunity  from GuestMasterProfile page in SGR
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

public class SGR_REG46_Validate_Create_Edit_Delete_Service_Opportunity {

	CRM SW = new CRM();
	String Situation,Action,FollowUp,Recovery;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void CreateServiceOpportunity(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
		SW.Wait(8);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("//input[@value='Create a New Service Opportunity']");
		SW.Click("SGRGuestProfile_ServiceOpportunity_BT");

		Situation="Situation_"+SW.RandomString(6);
		Action="Action_"+SW.RandomString(6);
		FollowUp="FollowUp_"+SW.RandomString(6);
		Recovery="Recovery_"+SW.RandomString(6);

		SW.EnterValue("SGRServiceOpportunity_Situation_EB", Situation);
		SW.EnterValue("SGRServiceOpportunity_Action_EB", Action);
		SW.EnterValue("SGRServiceOpportunity_FollowUp_EB", FollowUp);
		SW.EnterValue("SGRServiceOpportunity_Recovery_EB",Recovery );

		SW.DropDown_SelectByIndex("SGRServiceOpportunity_Visibility_DD", 0);
		SW.DropDown_SelectByIndex("SGRServiceOpportunity_Department_DD", 1);
		SW.Click("SGRServiceOpportunity_Save_BT");
		if(SW.ObjectExists("//td[text()='"+Situation+"']")){
			Environment.loger.log(Level.INFO, "ServiceOpportunity is created successfully and present in guest profile");
			Reporter.Write("validateServiceOpportunity", "CreatedSOShouldPresent", "CreatedSOisPresent", "PASS");
		}else{
			Reporter.Write("validateServiceOpportunity", "CreatedSOShouldPresent", "CreatedSOIsNotPresent", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateServiceOpportunity")
	public void EditServiceOpportunity(){
		
		SW.Click("//td[text()='"+Situation+"']//..//a[text()='Edit']");
		Situation="SituationUpdated_"+SW.RandomString(6);
		SW.EnterValue("SGRServiceOpportunity_Situation_EB", Situation);
		SW.Click("SGRServiceOpportunity_Save_BT");
		if(SW.ObjectExists("//td[text()='"+Situation+"']")){
			Environment.loger.log(Level.INFO, "ServiceOpportunity is Edited successfully and present in guest profile");
			Reporter.Write("validateServiceOpportunity", "CreatedSOShouldPresent", "CreatedSOisPresent", "PASS");
		}else{
			Reporter.Write("validateServiceOpportunity", "CreatedSOShouldPresent", "CreatedSOIsNotPresent", "FAIL");
		}
	}
	@Test(priority=3, dependsOnMethods="CreateServiceOpportunity")
	public void DeleteServiceOpportunity(){
		
		SW.ClickAndProceed("//td[text()='"+Situation+"']//..//a[text()='Delete']");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		if(!SW.ObjectExists("//td[text()='"+Situation+"']")){
			Environment.loger.log(Level.INFO, "ServiceOpportunity is Deleted successfully and not present in guest profile");
			Reporter.Write("validateServiceOpportunity", "CreatedSOShouldNotPresent", "CreatedSOIsNotPresent", "PASS");
		}else{
			Reporter.Write("validateServiceOpportunity", "CreatedSOShouldNotPresent", "CreatedSOIsPresent", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
