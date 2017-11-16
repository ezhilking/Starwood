package testscripts.sgrRegression;
/** Purpose		: Valdiate the error message when event moved to In Progress
 * TestCase Name: Valdiate the error message when event moved to In Progress
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG35_ValidateErrorMessageWhenEventMovedInProgress {
	CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void ValidateMessageForSingleEvent(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Events_LK");
		String FirstEventID=SW.GetText("SGREvent_FirstEvent_LK");
		SW.CheckBox("SGREvent_FirstEvent_CB", "ON");
		SW.Click("SGREvent_MoveToInProgress_LK");
		String ErrorMessage=SW.GetText("SGREvents_ErrorMessage_DT");
		SW.CompareText("ErrorMessage", "Event #"+FirstEventID+" was successfully placed In Progress...", ErrorMessage);
	}
	@Test(priority=2, dependsOnMethods="ValidateMessageForSingleEvent")
	public void ValidateMessageForMultipleEvent(){
		SW.Click("SGRNavigation_Events_LK");
		String FirstEventID=SW.GetText("SGREvent_FirstEvent_LK");
		String SecondEventID= SW.GetText("SGREvent_SecondEvent_LK");
		SW.CheckBox("SGREvent_FirstEvent_CB", "ON");
		SW.CheckBox("SGREvent_SecondEvent_CB", "ON");
		SW.Click("SGREvent_MoveToInProgress_LK");
		String ErrorMessage=SW.GetText("SGREvents_ErrorMessage_DT");
		SW.CompareText("ErrorMessage", "Events #"+FirstEventID+", #"+SecondEventID+" were successfully placed In Progress...", ErrorMessage);
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
