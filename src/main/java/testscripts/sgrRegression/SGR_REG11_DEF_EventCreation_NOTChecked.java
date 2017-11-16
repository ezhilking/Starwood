package testscripts.sgrRegression;
/* Purpose		: This is to validate that event should be created when DEF link is clicked in Master arrival report. And also to validate that the event gets scheduled at 5 AM on the arrival date of the reservation and 
 * This is to validate the event details in the Master arrival report when the event is closed and the location on the event is TBD
 * TestCase Name: Validate the event gets created in the guest profile screen when DEF link is clicked and validate that it gets scheduled at 5:00 AM on the arrival date of the reservation and 
 * Validate the  is NOT checked if the location detail is TBD, even if the event is closed
 * Created By	: Ezhilarasan.S 
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 * Prerequisite : We are assuming that '1965' property id has some guest in MAR.So not creating any reservation in branded web.  
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG11_DEF_EventCreation_NOTChecked {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	String Guestname, reservationNumber, Date;

	@Test(priority=1)
	public void pickReservationFromMAR(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		Date = SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", 4, Calendar.DATE);
		SW.RunJavaScript("document.getElementsByName('endDate3')[0].value='"+Date+"';");//Set the date
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(15);
		reservationNumber=SW.GetAttributeValue("(//table[@id='report6']//td[@aria-describedby='report6_guestName'])[1]//a", "href");
		reservationNumber=reservationNumber.substring(reservationNumber.indexOf("resConf=")+8,reservationNumber.indexOf("&roomSeq"));
		Environment.loger.log(Level.INFO, " Selected Guest Reservation Number :"+reservationNumber);
		
		//update the guest details in DB				
		//SW.EstablishConnection(Environment.getRunEnvironment());
		SW.EstablishConnection("QA3");
		String query="UPDATE ODSGUEST.ARRIVALS_MASTER SET confirmed_preferences='DEF:EL|FRST:Y|LWR:Y|' WHERE reservation_confirmation_num="+reservationNumber+" ";
		SW.UpdateQuery(query);

		SW.Click("SGRMAR_Refresh_BN");//Refresh the page.
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(15);
		SW.RunJavaScript("SGRArrivalReport_GridTableBottom_WB","document.getElementsByClassName('ui-jqgrid-bdiv')[0].style['overflow'] = 'visible';");

		if(!SW.ObjectExists("SGRMAR_DefeatherFirst_LK")){
			
			Environment.loger.log(Level.ERROR, "No guests are present with the DEF link hence updating the DB and checking");
			SW.FailCurrentTest("No guests are present with the DEF link hence updating the DB and checking");			
		}

		Guestname = SW.GetText("SGRMAR_DefeatherGuestName_LK");
		SW.ClickByJavascript("SGRMAR_DefeatherFirst_LK");//Click DEF link
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");	

	}

	@Test(priority=2, dependsOnMethods="pickReservationFromMAR")
	public void validate5AMScheduledInSGR(){ 
		//reservationNumber="597549221";
		//SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", reservationNumber);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WaitTillElementToBeClickable("SGRResSearch_Results_WT");

		if(!SW.ObjectExists("SGRResSearch_Results_WT")){
			Environment.loger.log(Level.ERROR, "Reservation is not Available in SGR ");
			SW.FailCurrentTest("Reservation is not Available in SGR");
		}

		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		SW.WaitTillInvisibilityOfElement("SGRSVO_LoadingMask_IC");
		SW.Click("SGRGuestProfile_EventRefresh_LK");

		// Click on Edit of the latest event created
		List<String> EventIds = new ArrayList<String>();
		EventIds=SW.WebTbl_GetText("SGRGuestProfile_EventList_WT", 2);
		Collections.sort(EventIds);//sort the events and take the large event id
		String ResentEventID= EventIds.get(EventIds.size()-1);
		SW.Click("//table[@id='guestEventsTBL']//td[text()='"+ResentEventID+"']//ancestor::tr//a[text()='Edit']");
		SW.WaitTillElementToBeClickable("SGRModifyEvent_ScheduledTime_DD");
		String SelectedHrs = SW.DropDown_GetSelectedText("SGRModifyEvent_ScheduledTime_DD");
		SW.CompareText("ScheduledAt5AM_DD", "5", SelectedHrs);
		SW.Wait(5);
	}

	@Test(priority=3, dependsOnMethods="validate5AMScheduledInSGR" )
	public void CheckForUnchekedImage(){
		//Change the Event to status as Closed.
		SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "HOUSEKEEPING / MDE");
		int size = SW.DropDown_GetSize("SGRCreateEvent_AssignTo_DD");
		SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", SW.RandomNumber(1, size-1));
		SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
		
		SW.Click("SGRCreateEvent_TriggerNow_BN");
		//SW.Click("SGRCreateEvent_Save_BN");

		String EventID = SW.GetEventNumbeID();
		Environment.loger.log(Level.INFO, "Event created:"+EventID);

		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		Date = SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", 4, Calendar.DATE);
		SW.RunJavaScript("document.getElementsByName('endDate3')[0].value='"+Date+"';");//Set the date		
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(15);
		/*SW.DropDown_SelectByValue("SGRMAR_FilterByStatus_DD", "ALL");
		SW.Click("SGRMAR_Refresh_BN");		
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");*/

		SW.RunJavaScript("SGRArrivalReport_GridTableBottom_WB","document.getElementsByClassName('ui-jqgrid-bdiv')[0].style['overflow'] = 'visible';");

		SW.Click("(//*[text()='"+Guestname+"']//ancestor::tr/td[1]/a)[last()]");//'+' icon
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(3);
		String TableXapth = "(//tr[@class='ui-subgrid']//table//table)[2]";

		if(!SW.ObjectExists(TableXapth)){

			Environment.loger.log(Level.ERROR, "Event Table is not displayed ");
			SW.FailCurrentTest("Event Table is not displayed ");
		}
		List<String> EventIds = new ArrayList<String>();
		EventIds=SW.WebTbl_GetText("(//tr[@class='ui-subgrid']//table//table)[2]", 1);

		StringBuilder sb = new StringBuilder();
		for (String s : EventIds){
			sb.append(s);
			sb.append(" ");
		}

		String EventsString = sb.toString();
		SW.CompareTextContained("ValidateTheEventExistance",EventID, EventsString);
	}

	@AfterClass
	public void EndTest(){
		// Sign out from the Application
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}

}
