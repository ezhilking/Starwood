package testscripts.sgrRegression;
/** Purpose		: Validate_Event Report - Concluded Actions by Dept Report_Department_Category_Details_Microstrategy_Switch_True
 * TestCase Name: Validate_Event Report - Concluded Actions by Dept Report_Department_Category_Details_Microstrategy_Switch_True
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

public class SGR_REG25_Validate_Evnt_Rprt_Cnclud_MSTR {
	CRM SW = new CRM();
	String EventNotes,ReservationNo,EventID;
	int RecordCount1,RecordCount2;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("GC");
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateEvent1ForGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1513"); // Stage : 1965
		//SW.Click("SGRNavigation_Home_LK");
		//SW.Wait(8);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
		}

		ReservationNo=SW.GetAttributeValue(FirstGuestLink, "href");
		ReservationNo=ReservationNo.substring(ReservationNo.indexOf("resConf=")+8,ReservationNo.indexOf("&roomSeq"));
		Environment.loger.log(Level.INFO, "Reservation Number Selected is :"+ReservationNo);
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "event");
		SW.Wait(5);
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Event ID- "+EventID);
	}

	@Test(priority=2, dependsOnMethods="CreateEvent1ForGuest")
	public void ConcludeEvent1(){
		SW.Click("SGRNavigation_Events_LK");
		SW.CheckBox("//td[text()='"+EventID+"']//..//input", "ON");
		SW.Click("SGREvents_CompleteEvent_LK");
		SW.Wait(8);
		SW.Click("SGREvents_CompletedCanceled_LK");
		if(SW.ObjectExists("//td[text()='"+EventID+"']")){
			Environment.loger.log(Level.INFO, "Event is present in the concluded list");
			SW.GetScreenshot("EventPresence");
		}else{
			Environment.loger.log(Level.INFO, "Event is not present in the concluded list");
			SW.FailCurrentTest("Event is not present in the concluded list");
		}
	}

	@Test(priority=3, dependsOnMethods="ConcludeEvent1")
	public void GenerateEvent1Report(){
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_EventReports_LK");
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Concluded Actions by Dept");
		SW.WaitForPageload();
		String Date=SW.GetTimeStamp("dd-MMM-yyyy");
		SW.EnterValue("SGREventReports_StartDate_EB", Date);
		SW.EnterValue("SGREventReports_EndDate_EB", Date);
		SW.DropDown_SelectByText("//*[@name='reportCriteria.reportView']", "Department/Category/Detail");  //added
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForPageload();
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		//SW.Wait(30);
		RecordCount1= Integer.parseInt(SW.GetText("//span[@id='K139']").trim());
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
	}
	@Test(priority=4,dependsOnMethods="GenerateEvent1Report")
	public void CreateEvent2ForGuest(){
		
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
		}

		ReservationNo=SW.GetAttributeValue(FirstGuestLink, "href");
		ReservationNo=ReservationNo.substring(ReservationNo.indexOf("resConf=")+8,ReservationNo.indexOf("&roomSeq"));
		Environment.loger.log(Level.INFO, "Reservation Number Selected is :"+ReservationNo);
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
		SW.EnterValue("SGRAddEvent_What_EB", "event");
		SW.Wait(5);
		SW.Click("//ul[@class='ac_results']//li[1]/span");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		EventNotes=SW.RandomString(10);
		SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
		SW.Click("SGRCreateEvent_Save_BN");
		EventID=SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Created Event ID- "+EventID);
	}

	@Test(priority=5, dependsOnMethods="CreateEvent2ForGuest")
	public void ConcludeEvent2(){
		SW.Click("SGRNavigation_Events_LK");
		SW.CheckBox("//td[text()='"+EventID+"']//..//input", "ON");
		SW.Click("SGREvents_CompleteEvent_LK");
		SW.Wait(8);
		SW.Click("SGREvents_CompletedCanceled_LK");
		if(SW.ObjectExists("//td[text()='"+EventID+"']")){
			Environment.loger.log(Level.INFO, "Event is present in the concluded list");
			SW.GetScreenshot("EventPresence");
		}else{
			Environment.loger.log(Level.INFO, "Event is not present in the concluded list");
			SW.FailCurrentTest("Event is not present in the concluded list");
		}
	}

	@Test(priority=6, dependsOnMethods="ConcludeEvent2")
	public void GenerateEvent2Report(){
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_EventReports_LK");
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Concluded Actions by Dept");
		SW.WaitForPageload();
		String Date=SW.GetTimeStamp("dd-MMM-yyyy");
		SW.EnterValue("SGREventReports_StartDate_EB", Date);
		SW.EnterValue("SGREventReports_EndDate_EB", Date);
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForPageload();
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		//SW.Wait(30);
		RecordCount2= Integer.parseInt(SW.GetText("//span[@id='K139']").trim());
		if((RecordCount1+1)==RecordCount2){
			Environment.loger.log(Level.INFO, "Record count is increased by one");
			SW.GetScreenshot("EventReportConcludedActions");
		}else{
			Environment.loger.log(Level.ERROR, "Record count is not increased/Added Event is not showing in the MSTR");
			SW.FailCurrentTest("Added Event is not showing in the MSTR");
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
