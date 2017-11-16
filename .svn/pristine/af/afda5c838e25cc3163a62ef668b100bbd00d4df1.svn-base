package testscripts.sgrRegression;
/** Purpose		: Validate the sort order on the Event listing screen for all the columns
 * TestCase Name:   1. Validate the sort order on the Event listing screen for Time column
 * 				    2. Validate the sort order on the Event listing screen for ID column
 * 					3. Validate the sort order on the Event listing screen for ESC column
 * 					4. Validate the sort order on the Event listing screen for Priority column
 * 					5. Validate the sort order on the Event listing screen for Status column
 * 					6. Validate the sort order on the Event listing screen for Dispatched column
 * 					7. Validate the sort order on the Event listing screen for Type column
 * 					8. Validate the sort order on the Event listing screen for Origin column
 * 					9. Validate the sort order on the Event listing screen for Location column
 * 					10. Validate the sort order on the Event listing screen for GuestName column
 * 					11. Validate the sort order on the Event listing screen for Detail column
 * 					12. Validate the sort order on the Event listing screen for Department column
 * 					13. Validate the sort order on the Event listing screen for AssignTo column
 * 					14. Validate the sort order on the Event listing screen for Post Sts column
 * Created By	: Sachin G
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG44_ValidateSortOrderInEventPageForAllColumns {
	CRM SW = new CRM();

	@BeforeClass
	public void Intialize(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	
	@Test(priority=1)
	public void LoadEventScreen(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1047");
		SW.Click("SGRNavigation_Events_LK");
	}
	@Test(priority=2, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInTime(){
		SW.Click("SGREvent_EventsTableTimeColHead_LK");
		SW.Wait(5);
		SW.Click("SGREvent_EventsTableTimeColHead_LK");
		SW.Wait(5);
		//Get all the values from Time Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Time Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 2);//get all time column values
		SW.ValidateIntegerSortOrder(ValuesFromUI, "A");//Call function and sort order will be Ascending	
	}
	@Test(priority=3, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInEventID(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableIDColHead_LK");
		SW.Wait(5);
		//Get all the values from ID Column
		Environment.loger.log(Level.INFO, "Validating Sort order for ID Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 3);//get all ID column values
		SW.ValidateIntegerSortOrder(ValuesFromUI, "D");	//Call function and sort order will be Ascending	
	}
	@Test(priority=4, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInESC(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableESCColHead_LK");
		SW.Wait(5);
		//Get all the values from ESC Column
		Environment.loger.log(Level.INFO, "Validating Sort order for ESC Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 4);//get all ESC column values
		SW.ValidateIntegerSortOrder(ValuesFromUI, "D");	//Call function and sort order will be Descending	
	}
	@Test(priority=5, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInPriority(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTablePriorityColHead_LK");
		SW.Wait(5);
		//Get all the values from Priority Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Priority Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 5);//get all Priority column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be Descending
	}
	@Test(priority=6, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInType(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableTypeColHead_LK");
		SW.Wait(5);
		//Get all the values from Type Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Type Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 8);//get all Type column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");//Call function and sort order will be Descending	
	}
	@Test(priority=7, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInOrigin(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableOriginColHead_LK");
		SW.Wait(5);
		//Get all the values from Origin Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Origin Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 9);//get all Origin column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be Descending	
	}
	@Test(priority=8, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInLocation(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableLocationColHead_LK");
		SW.Wait(5);
		//Get all the values from Location Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Location Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 10);//get all Location column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be Descending
	}
	@Test(priority=9, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInGuestName(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableGuestNameColHead_LK");
		SW.Wait(5);
		//Get all the values from GuestName Column
		Environment.loger.log(Level.INFO, "Validating Sort order for GuestName Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 11);//get all Guest Name column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be ascending
	}
	@Test(priority=10, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInDetail(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableDetailColHead_LK");
		SW.Wait(5);
		//Get all the values from Detail Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Detail Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 12);//get all Detail column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be ascending
	}
	@Test(priority=11, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInDepartment(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableDepartmentColHead_LK");
		SW.Wait(5);
		//Get all the values from Department Column
		Environment.loger.log(Level.INFO, "Validating Sort order for Department Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 14);//get all Department column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be ascending
	}
	@Test(priority=12, dependsOnMethods="LoadEventScreen")
	public void ValidateSortOrderInAssignTo(){
		SW.Click("SGRNavigation_Events_LK");
		SW.Click("SGREvent_EventsTableAssignedToColHead_LK");
		SW.Wait(5);
		//Get all the values from AssignTo Column
		Environment.loger.log(Level.INFO, "Validating Sort order for AssignTo Column");
		List<String> ValuesFromUI = new ArrayList<String>(); 
		ValuesFromUI = SW.WebTbl_GetText("SGREvent_EventsTable_WT", 15);//get all AssignTo column values
		SW.ValidateStringSortOrder(ValuesFromUI, "D");	//Call function and sort order will be ascending
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
