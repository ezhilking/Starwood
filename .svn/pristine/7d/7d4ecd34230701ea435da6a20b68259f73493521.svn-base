package testscripts.sgrRegression;
/** Purpose		: Validate Notes column in Event listing screen
 * TestCase Name: 1. Validate Notes column presence in Event listing screen
 * 				  2. Validate updating notes in event listing screen 
 * 				  3. Validate saving notes by hitting enter
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG67_ValidateNotesColumnInEventListingScreen {
	CRM SW = new CRM();
	String EventNotes,EventID, UpdatedEventNotes;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
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
			Reporter.Write("Validate Guest Presence", "Guest should present", "Guests are not present", "FAIL");
		}
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "house");
		SW.Wait(5);	
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Event ID - "+EventID);
	}
	@Test(priority=2, dependsOnMethods="CreateEventForGuest")
	public void ValidateNotesColumn(){
		SW.Click("SGRNavigation_Events_LK");
		if(SW.ObjectExists("//td[@id='"+EventID+"']")){
			Reporter.Write("Validate Notes Column", "Notes should present for created event", "Notes present for created evnt", "PASS");
			SW.Click("//td[@id='"+EventID+"']");
			if(SW.ObjectExists("//td[@id='"+EventID+"']//textarea[@id='editTextNote']")){
				UpdatedEventNotes=SW.RandomString(20);
				SW.EnterValue("//td[@id='"+EventID+"']//textarea[@id='editTextNote']",UpdatedEventNotes+Keys.ENTER);
				SW.WaitForPageload();
				SW.Click("//table[@id='evts']//td[text()='"+EventID+"']");
				String Notes= SW.GetText("SGRCreateEvent_Noted_EB");
				SW.CompareText("Validate Updated Notes", UpdatedEventNotes, Notes);
			}
			
		}else{
			Reporter.Write("Validate Notes Column", "Notes should present for created event", "Notes not present for created evnt", "FAIL");
		}
		
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
