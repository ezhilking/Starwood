package testscripts.sgrRegression;
/** Purpose		: Validate Time column in Guest Profile under Event listing 
 * TestCase Name: Validate the time column header and time increments 
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

public class SGR_REG47_ValidateTimeColumnInGuestProfileEventSection {
	CRM SW = new CRM();
	String GuestName,EventNotes,EventID;
	
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
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("ValidateGuestAvailability", "Guest should present in list", "Guest is not present in the list", "FAIL");
		}
		GuestName=SW.GetText(FirstGuestLink);
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
		SW.Wait(60);// Wait for one minute to check event time in the guest profile
	}
	@Test(priority=2, dependsOnMethods="CreateEventForGuest")
	public void ValidateTimeColumnInGuestProfile(){
		SW.Click("//a[text()='"+GuestName+"']");
		if(SW.ObjectExists("//table[@id='guestEventsTBL']//td[text()='"+EventID+"']")){
			Reporter.Write("Validate Created event", "Event should present in Guest profile", "Created Event is present in guest profile", "PASS");
			SW.CompareText("ValidateTimeColumnHeader","Time", SW.GetText("//table[@id='guestEventsTBL']/thead//th[1]"));
			SW.CompareText("ValidateTimeValue", "1", SW.GetText("//td[text()='"+EventID+"']//..//td[contains(@id,'eventTimeToComplete')]"));
		}else{
			Reporter.Write("Validate Created event", "Event should present in Guest profile", "Created Event is not present in guest profile", "FAIL");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
