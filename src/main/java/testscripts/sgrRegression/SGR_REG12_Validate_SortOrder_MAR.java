package testscripts.sgrRegression;
/* Purpose		: Validate the sort order of the records
 * TestCase Name: Validate the sort order of the records in the Room Assignment tab when Filter by Preference is applied
 * Created By	: Sachin G
 * Modified By	:
 * Modified Date:
 * Reviewed By	: Ezhilarasa.S
 * Reviewed Date: 04-Apr-2016
 * Prerequisite : We are assuming that '1965' property id has some guest in MAR.So not creating any reservation in branded web.
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG12_Validate_SortOrder_MAR {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test(priority=0)
	public void validateSortOrder(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
			SW.Click("SGRNavigation_Reports_LK");
			SW.Click("SGRNavigation_ArrivalReport_LK");
			SW.Click("SGRArrivalReport_GenerateReport_BN");
			SW.DropDown_SelectByText("SGRMAR_FilterByStatus_DD", "All Guests");
			String Date = SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", 4, Calendar.DATE);
			SW.RunJavaScript("document.getElementsByName('endDate2')[0].value='"+Date+"';");//Set the date 
			//SW.DropDown_SelectByValue("SGRMAR_PreferenceFilter_DD", "LWR");
			SW.Click("SGRMAR_Refresh_BN");
			SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
			//Click two times to sort in descending order and wait till table loads 
			SW.NormalClick("SGRMAR_DepartureDatesHeader_WT");//
			SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
			SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
			SW.Wait(8);
			SW.NormalClick("SGRMAR_DepartureDatesHeader_WT");
			SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
			SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
			SW.Wait(8);
			//Get all the dates from the Departure Date Column
			List<String> DepartureDatesFromUI = new ArrayList<String>(); 
			DepartureDatesFromUI = SW.WebTbl_GetText("SGRMAR_DepartureDates_DT", 7);
			if(DepartureDatesFromUI.size()==1){
				Environment.loger.log(Level.INFO, "No Records found in the MAR check for deferent property!");
				SW.FailCurrentTest("No Records found in the MAR check for deferent property!");
			}
			// Convert the String formatted Date to Date format 
			List<Date> datesSorted= new ArrayList<Date>();
			List<Date> datesfromUI= new ArrayList<Date>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			for (int CurrentRow=1;CurrentRow<DepartureDatesFromUI.size();CurrentRow++){
				datesSorted.add(dateFormat.parse(DepartureDatesFromUI.get(CurrentRow)));
				datesfromUI.add(dateFormat.parse(DepartureDatesFromUI.get(CurrentRow)));
			}
			//Sort one List in Descending order
			Collections.sort(datesSorted, Collections.reverseOrder());
			//Compare the date values of both the lists(Sorted and values from UI)
			Boolean flag=true;
			for(int rowindex=0;rowindex<datesSorted.size();rowindex++){
				if(!datesSorted.get(rowindex).equals(datesfromUI.get(rowindex))){
					flag=false;
					Environment.loger.log(Level.ERROR, "Departure dates are not in Descending order");
					SW.FailCurrentTest("Departure dates are not in Descending order");
					break;
				}
			}
			if(flag)
				Environment.loger.log(Level.INFO, "Departure dates are in Descending order");
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
			SW.FailCurrentTest("Exception occured!!");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}

}
