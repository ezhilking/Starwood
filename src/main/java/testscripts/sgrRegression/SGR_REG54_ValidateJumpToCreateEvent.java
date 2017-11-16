package testscripts.sgrRegression;
/** Purpose		: Validate Jump to functionality
 * TestCase Name: Create Event - Defect/Request/Incident/Workorder using jump to  in SGR Home page
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

public class SGR_REG54_ValidateJumpToCreateEvent {
	CRM SW = new CRM();
	String EventNotes,EventID;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateIncidentEventForGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("ValidateGuestAvailability", "Guest should present in list", "Guest is not present in the list", "FAIL");
		}
		
		String FristNLastName=SW.GetText(FirstGuestLink);
		String LastName=FristNLastName.substring(0, FristNLastName.indexOf(",")).trim();
		SW.SwitchToFrame("");
		SW.EnterValue("SGRHome_JumpTo_EB", LastName);
		SW.NormalClick("SGRHome_JumpToFirstMatch_ST");	
		SW.Click("SGRHome_JumpToEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "Guest Safety");
		SW.Wait(5);	
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByText("SGREvent_EventOrigin_DD", "Staff");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Incident Event ID - "+EventID);
		if(EventID.isEmpty()){
			Reporter.Write("ValidateCreateEvent", "Event should Created", "Event is not Created", "FAIL");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
