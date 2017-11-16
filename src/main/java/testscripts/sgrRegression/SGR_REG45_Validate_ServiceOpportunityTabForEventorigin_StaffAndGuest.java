package testscripts.sgrRegression;
/** Purpose		: Validate the Service opportunity when a defect is created using event origin as Guest
				  Validate the Service opportunity when a defect is created using event origin as Staff
 * TestCase Name: Validate the SO when a defect is created using event origin as Guest and loaded again 
				  Validate the SO when a defect is created using event origin as Guest and edited with Staff and saved again
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

public class SGR_REG45_Validate_ServiceOpportunityTabForEventorigin_StaffAndGuest {
	CRM SW = new CRM();
	String EventNotes,EventID, Situation,Action,FollowUp,Recovery;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
		Situation=SW.RandomString(5);
		Action=SW.RandomString(5);
		FollowUp=SW.RandomString(5);
		Recovery=SW.RandomString(5);
	}
	@Test(priority=1)
	public void CreateEventForGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("GuestPresence", "No Guest present in Inhouse", "No Guest present in Inhouse", "FAIL");
		}
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "Cancellation");
		SW.Wait(5);	
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGREvent_EventOrigin_DD", "Staff");
		if(SW.ObjectExists("//img[@id='accordianPic2' and contains(@src,'toggle-expand.png')]")){
			Reporter.Write("ValidateDisbality", "ElementShouldDisable", "ElementIsNotDisabled", "FAIL");
		}else{
			Reporter.Write("ValidateDisbality", "ServiceOpportunityElementShouldDisable", "ServiceOpportunityElementDisabled", "PASS");
		}
		SW.DropDown_SelectByText("SGREvent_EventOrigin_DD", "Guest");
		if(SW.ObjectExists("//img[@id='accordianPic2' and contains(@src,'toggle-expand.png')]")){
			Reporter.Write("ValidateDisbality", "ElementShouldEnable", "ElementIsEnabled", "PASS");
		}else{
			Reporter.Write("ValidateDisbality", "ServiceOpportunityElementShouldEnable", "ServiceOpportunityElementNotEnabled", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateEventForGuest")
	public void ValidateSOWhenLoadedAgainWithGuest(){
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.NormalClick("SGREvent_ServiceOpAndCompensation+_IC");
		SW.CheckBox("SGREvent_ServiceOpportunity_CB", "ON");
		SW.EnterValue("SGREvent_ServiceOpportunitySituation_EB", Situation);
		SW.EnterValue("SGREvent_ServiceOpportunityActionTaken_EB", Action);
		SW.EnterValue("SGREvent_ServiceOpportunityFollowUpText_EB", FollowUp);
		SW.EnterValue("SGREvent_ServiceOpportunityRecoveryText_EB", Recovery);
		SW.DropDown_SelectByIndex("SGREvent_ServiceOpportunityDepartment_DD", 1);
		SW.DropDown_SelectByIndex("SGRCreateEvent_CompensationType_DD", 1);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Event ID - "+EventID);
		SW.Click("SGRNavigation_Events_LK");
		SW.CheckBox("//td[text()='"+EventID+"']//..//input", "ON");
		SW.Click("//td[text()='"+EventID+"']");
		SW.NormalClick("SGREvent_ServiceOpAndCompensation+_IC");
		SW.CheckBox("SGREvent_ServiceOpportunity_CB", "ON");
		SW.CompareText("CompareSituation", Situation, SW.GetText("SGREvent_ServiceOpportunitySituation_EB"));
		SW.CompareText("CompareAction", Action, SW.GetText("SGREvent_ServiceOpportunityActionTaken_EB"));
		SW.CompareText("CompareFollowUp", FollowUp, SW.GetText("SGREvent_ServiceOpportunityFollowUpText_EB"));
		SW.CompareText("CompareRecovery", Recovery, SW.GetText("SGREvent_ServiceOpportunityRecoveryText_EB"));
	}
	@Test(priority=3, dependsOnMethods="CreateEventForGuest")
	public void ValidateSOWhenChangedToStaffAndLoadedAgain(){
		SW.DropDown_SelectByText("SGREvent_EventOrigin_DD", "Staff");
		SW.Wait(2);
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.Click("SGRCreateEvent_Save_BN");
		SW.Click("SGRNavigation_Events_LK");
		SW.CheckBox("//td[text()='"+EventID+"']//..//input", "ON");
		SW.Click("//td[text()='"+EventID+"']");
		SW.DropDown_SelectByText("SGREvent_EventOrigin_DD", "Guest");
		SW.NormalClick("SGREvent_ServiceOpAndCompensation+_IC");
		SW.CheckBox("SGREvent_ServiceOpportunity_CB", "ON");
		SW.CompareText("CompareSituation", "", SW.GetText("SGREvent_ServiceOpportunitySituation_EB"));
		SW.CompareText("CompareAction", "", SW.GetText("SGREvent_ServiceOpportunityActionTaken_EB"));
		SW.CompareText("CompareFollowUp", "", SW.GetText("SGREvent_ServiceOpportunityFollowUpText_EB"));
		SW.CompareText("CompareRecovery", "", SW.GetText("SGREvent_ServiceOpportunityRecoveryText_EB"));
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
