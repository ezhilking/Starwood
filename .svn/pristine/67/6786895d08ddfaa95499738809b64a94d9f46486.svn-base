package testscripts.sgrRegression;
/* Purpose		: Validate_Event Reports_Event Type Report - CORP Report_Property_Group_Category_Details_Microstrategy_Switch_True
 * TestCase Name: This is to validate that the report generates the event details correctly based on the selected event type
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

public class SGR_REG18_Validate_EventReports_EventType_Report {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Environment.SetBrowserToUse("GC");
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	String EventID,  Date;
	int ExistingEventCount, CurrentEventCount;
	@Test
	public void Validate_Service_Opport_in_MSTR(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1513");  // Change property for STage(1965)
		SW.Wait(8);
		//Initialize the existing event count to 0
		ExistingEventCount=0;
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_EventReports_LK");
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Event Type Report");
		SW.WaitForPageload();
		SW.WaitTillElementToBeClickable("SGREventReports_GenerateReport_BN");
		Date=SW.GetTimeStamp("dd-MMM-yyyy");
		SW.EnterValue("SGREventReports_StartDate_EB", Date);
		SW.EnterValue("SGREventReports_EndDate_EB", Date);
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForPageload();
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Wait(30);
	/*	SW.WaitTillElementToBeClickable("//input[@value='Continue']");
		if(SW.ObjectExists("//input[@value='Continue']")){
			SW.Click("//input[@value='Continue']");
		}*/
		SW.WaitForPageload();
		SW.WaitTillInvisibilityOfElement("//div[@class='mstrIconWait']");
		SW.Wait(30);
		SW.WaitTillElementToBeClickable("(//span[@id='K139'])[last()]");
		if(SW.ObjectExists("(//span[@id='K139'])[last()]")){
			String Count=SW.GetText("(//span[@id='K139'])[last()]");
			ExistingEventCount=Integer.parseInt(Count.trim());
			Environment.loger.log(Level.INFO, "Event Count before creating a event: "+ExistingEventCount);
		}else{
			Environment.loger.log(Level.ERROR, "Event Report is not present");
			SW.FailCurrentTest("Event Report is not present");
		}

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		SW.Click("SGRNavigation_Home_LK");
		SW.WaitForPageload();
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";

		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
		}

		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		SW.Click("SGRGuestProfile_CreateNewEvent_BT");
		SW.WaitForPageload();

		SW.EnterValue("SGRAddEvent_What_EB", "cancellation");
		SW.Click("SGRAddEvent_WhatFirstSuggestion_LK");//Suggestion box
		//SW.DropDown_SelectByText("SGRCreateEvent_EventType_DD", "Defect");
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", SW.RandomNumber(0, SW.DropDown_GetSize("SGRCreateEvent_AssignTo_DD")-1));
		SW.Click("SGRAddEvent_ServiceOppAndCompensation_LK");
		SW.Wait(5);
		//SW.DropDown_SelectByText("SGRCreateEvent_CompensationType_DD", "10 Meal Coupon");
		SW.DropDown_SelectByIndex("SGRCreateEvent_CompensationType_DD", 1);
		SW.Click("SGRCreateEvent_Save_BN");
		//		SW.CreateEvent("Defect", "tbd", "tbd", "Billing", "Accuracy", "email", "FRONT DESK", "", "");
		String EventID = SW.GetEventNumbeID();

		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_EventReports_LK");
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Event Type Report");
		SW.WaitForPageload();
		SW.WaitTillElementToBeClickable("SGREventReports_GenerateReport_BN");
		Date=SW.GetTimeStamp("dd-MMM-yyyy");
		SW.EnterValue("SGREventReports_StartDate_EB", Date);
		SW.EnterValue("SGREventReports_EndDate_EB", Date);
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForPageload();
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Wait(30);
		//SW.WaitTillElementToBeClickable("//input[@value='Continue']");
		if(SW.ObjectExists("//input[@value='Continue']")){
			SW.Click("//input[@value='Continue']");
		}
		SW.WaitForPageload();
		SW.WaitTillInvisibilityOfElement("//div[@class='mstrIconWait']");
		SW.Wait(30);
		SW.WaitTillElementToBeClickable("(//span[@id='K139'])[last()]");
		if(SW.ObjectExists("(//span[@id='K139'])[last()]")){
			String Count=SW.GetText("(//span[@id='K139'])[last()]");
			CurrentEventCount=Integer.parseInt(Count.trim());
			Environment.loger.log(Level.INFO, "Event Count After creating a event: "+CurrentEventCount);
		}else{
			Environment.loger.log(Level.ERROR, "Event Report is not present");
			SW.FailCurrentTest("Event Report is not present");
		}

		//Add one to existing event count 
		ExistingEventCount=ExistingEventCount+1;
		if(ExistingEventCount==CurrentEventCount){
			Environment.loger.log(Level.INFO, "Event Count is increased by one");
		}else{
			Environment.loger.log(Level.ERROR, "Event Count is not increased by one");
			SW.FailCurrentTest("Event Count is not increased by one");
		}

	}
	@AfterClass
	public void EndTest(){
		SW.SwitchToWindow(1);
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
