package testscripts.sgrRegression;
/** Purpose		: Validate Guest Follow Up functionality for cancelled Event 
 * TestCase Name: Validate Guest Follow Up functionality for cancelled Event 
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

public class SGR_REG32_Validate_GuestFollowUp_EventCancelation {
	CRM SW = new CRM();
	String EventNotes,EventID;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateEventForGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1967");
		SW.Click("SGRNavigation_Home_LK");
		//SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
		}
		SW.Click(FirstGuestLink);
		//SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		//SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "hotel");
		SW.Wait(5);
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_GuestFollowup_CB", "ON");
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Event ID - "+EventID);
	}
	@Test(priority=2, dependsOnMethods="CreateEventForGuest")
	public void CancelEvent(){
		SW.Click("SGRNavigation_Events_LK");
		SW.CheckBox("//td[text()='"+EventID+"']//..//input", "ON");
		SW.Click("//td[text()='"+EventID+"']");
		SW.DropDown_SelectByValue("SGREvent_EventStatus_DD", "X");
		SW.Click("SGRCreateEvent_Save_BN");	
		if(SW.ObjectExists("//div[@id='errors']/span[text()='Event ID "+EventID+" was saved.']")){
			Environment.loger.log(Level.INFO, "Event cancelled successfully");
			SW.GetScreenshot("EventCancel");
		}else{
			Environment.loger.log(Level.INFO, "Event cancel Failed");
			SW.FailCurrentTest("Event cancel Failed");
		}
	}
	
	@Test(priority=3, dependsOnMethods="CancelEvent")
	public void ValidateInConcludeEvent(){
		
		SW.Click("SGREvents_CompletedCanceled_LK");
		SW.Wait(8);
		if(SW.ObjectExists("//td[text()='"+EventID+"']/..//td[last()]/img[@id='cross']")){
			Environment.loger.log(Level.INFO, "Event is present in the concluded/Cancel list and Cross mark is present for the event");
			SW.GetScreenshot("EventPresenceAfterFollowUp");
		}else{
			Environment.loger.log(Level.INFO, "Event is not present in the concluded/Cancel list after follow-up event Cancelation");
			SW.FailCurrentTest("Event is not present in the concluded/Cancel list after follow-up event Cancelation");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
