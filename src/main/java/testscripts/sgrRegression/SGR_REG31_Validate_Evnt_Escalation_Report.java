package testscripts.sgrRegression;
/** Purpose		: Validate Event Escalation report
 * TestCase Name: Validate Event Escalation report
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

public class SGR_REG31_Validate_Evnt_Escalation_Report {
	CRM SW = new CRM();
	String EventNotes,EventID;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("GC");
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateEventForGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1967");	//	 Stage : 1965
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
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
	public void ValidateInEscalationTab(){
		SW.Wait(130);//wait for 2 min so that event shows in escalation tab
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EscalatedTab_LK");
		if(SW.ObjectExists("//table[@id='evts']//td[text()='"+EventID+"']")){
			Environment.loger.log(Level.INFO, "Created event is present in the Escalation tab");
			SW.GetScreenshot("EscalationEvent");
		}else{
			Environment.loger.log(Level.INFO, "Created event is not present in the Escalation tab");
			SW.FailCurrentTest("CreatedEventIsNotPresentinEscalationTab");
		}
	}
	@Test(priority=3, dependsOnMethods="ValidateInEscalationTab")
	public void ValidateInEscalationReport(){
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_EventReports_LK");
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Escalation Report");
		SW.WaitForPageload();
		String Date=SW.GetTimeStamp("dd-MMM-yyyy");
		SW.EnterValue("SGREventReports_StartDate_EB", Date);
		SW.EnterValue("SGREventReports_EndDate_EB", Date);
		SW.DropDown_SelectByValue("SGREscalationReport_EscalationLevel_DD", "1");
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForPageload();
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Wait(30);
		if(SW.ObjectExists("(//table[contains(@id,'table_grid')])[2]//td[text()='"+EventID+"']")){
			Environment.loger.log(Level.INFO, "Escalated Event is present in the escalation report");
			SW.GetScreenshot("EscalationEvent");
		}else{
			Environment.loger.log(Level.INFO, "Escalated Event is not present in the escalation report");
			SW.FailCurrentTest("EscalatedEventIsNotPresentinEscalationReport");
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
