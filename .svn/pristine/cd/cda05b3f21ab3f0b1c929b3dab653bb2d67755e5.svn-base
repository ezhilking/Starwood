package testscripts.sgrRegression;
/** Purpose		: Validate Generating Query report
 * TestCase Name: Validate Generating Query report
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

public class SGR_REG56_GenerateQueryReportAndValidateData {
	CRM SW = new CRM();
	String EventNotes,EventID;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
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
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "house");
		SW.Wait(5);	
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "ON");
		SW.DropDown_SelectByIndex("SGRCreateEvent_Escalation2_DD", 1);
		SW.DropDown_SelectByIndex("SGRCreateEvent_Escalation3_DD", 2);
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Event ID - "+EventID);
	}
	
	@Test(priority=2, dependsOnMethods="CreateEventForGuest")
	public void ValidateInEscalationReport(){
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRHome_query_LK");  
		SW.EnterValue("SGRQuery_CreatedDateFrom_EB", SW.GetTimeStamp("dd-MMM-YYYY"));	
		SW.EnterValue("SGRQuery_CreatedDateTo_EB", SW.GetTimeStamp("dd-MMM-YYYY"));
		SW.Click("SGRQuery_Search_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Wait(30);
		if(SW.ObjectExists("//span[text()='"+EventID+"']")){
			Environment.loger.log(Level.INFO, "Created Event is present in the Query report");
			Reporter.Write("Validate Query Report", "Created Event Should present", "Created Event is present", "PASS");
		}else{
			Reporter.Write("Validate Query Report", "Created Event Should present", "Created Event is not present", "FAIL");
		}
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
