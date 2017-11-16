package testscripts.sgrRegression;
/** Purpose		: Valdiate the event history screen when an existing event is loaded and edited
 * TestCase Name: Valdiate the event history screen when an existing event is loaded and edited
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

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG34_ValidateEventHistoryScreenAfterEditingEsistingEvent {
	CRM SW = new CRM();
	List<String> EventHistoryBeforeEdit,EventHistoryAfterEdit;
	String EventId;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void OpenExistingEventAndCheckEventHistory(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_FirstEvent_LK");
		SW.Wait(10);
		SW.NormalClick("SGREvent_Acknowledgement&Dispatch+_IC");
		SW.Click("SGREvent_EventHistory_LK");
		EventHistoryBeforeEdit=SW.GetAllText("SGREvent_EventHistoryContent_DT");
		SW.NormalClick("SGREvent_EventHistoryPopupClose_IC");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 0);
		
		SW.Click("SGRCreateEvent_Save_BN");
		if(SW.ObjectExists("//span/li[text()='Select Assignee is a required field']")){
			Environment.loger.log(Level.INFO, "Error Message is displayed for changing assignee");
			SW.GetScreenshot("ErrorMessage");
		}else{
			Environment.loger.log(Level.INFO, "Error Message is not displayed");
			SW.FailCurrentTest("Error Message is not displayed");
		}
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.Click("SGRCreateEvent_Save_BN");
		EventId=SW.GetEventNumbeID();
	}
	@Test(priority=2, dependsOnMethods="OpenExistingEventAndCheckEventHistory")
	public void ValidateEventHistory(){
		SW.Click("//table[@id='evts']//td[text()='"+EventId+"']");
		SW.NormalClick("SGREvent_Acknowledgement&Dispatch+_IC");
		SW.Click("SGREvent_EventHistory_LK");
		EventHistoryAfterEdit=SW.GetAllText("SGREvent_EventHistoryContent_DT");
		if(EventHistoryAfterEdit.equals(EventHistoryBeforeEdit)){
			Environment.loger.log(Level.INFO, "Event history is same after event edit");
			SW.GetScreenshot("EventHistory");
		}else{
			Environment.loger.log(Level.INFO, "Event history is not same after event edit");
			SW.FailCurrentTest("Event history is not same after event edit");
		}
		SW.NormalClick("SGREvent_EventHistoryPopupClose_IC");
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
