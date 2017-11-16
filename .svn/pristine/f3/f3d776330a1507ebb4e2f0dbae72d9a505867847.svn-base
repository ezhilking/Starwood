package testscripts.CEM_AMB;
/** Purpose		: Validate Create Activity functionality 
 * TestCase Name: Validate Create Activity functionality 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




/** Purpose		: Validate Create Activity functionality 
 * TestCase Name: Validate Create Activity functionality 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class CEM_REG_02_ValidateCreateAncillaryForGuet {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String Notes;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
	}
	@Test(priority=1)
	public void createAncillary(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(5);
		SW.RunJavaScript("scroll(0,600)");//Scroll down for visibility of the link 
		SW.Click("CEMAMBMyGuests_GuestAncillary_LK");
		SW.Click("CEMAMBMyGuests_CreateAncillary_BT");
		SW.DropDown_SelectByText("CEMAMBAddAncillary_Category_DD", "Anniversary");
		String Date= SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", 1, Calendar.DATE);
		SW.EnterValue("CEMAMBAddAncillary_Date_EB",Date+Keys.TAB);
		Notes= "Anci_"+SW.RandomString(5);
		SW.EnterValue("CEMAMBAddAncillary_notes_EB", Notes);
		//SW.RunJavaScript("document.getElementsByName(\"ancillaryProfile.ancillaryProfileRemark\").value = "+Notes+";");
		SW.Click("CEMAMBAddAncillary_Save_BT");
		//SW.RunJavaScript("document.getElementsByClassName(\"save btn btn-sm btn-default\").click();");
		SW.Wait(10);
		if(SW.ObjectExists("//div[@class='guestAncillary']//div[text()='"+Notes+"']")){
			Environment.loger.log(Level.INFO, "Ancillary info added successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Ancillary info Not added successfully!!!");
			SW.FailCurrentTest("Ancillary info Not added!!!");
		}
		
	}
	@Test(priority=2, dependsOnMethods="createAncillary")
	public void ValidateAncillaryInTask(){
		SW.Wait(10);
		SW.Click("CEMAMB_TASKS_LK");
		SW.RefreshPage();
		SW.Click("CEMAMB_TASKS_LK");
//		/SW.Click("CEMAMBGuestTask_RefreshSpecialEvents_LK");
		SW.Click("CEMAMBGuestTask_SpecialEvents_LK");
		SW.Wait(10);
		if(SW.ObjectExists("//table[@id='allAncillariesTasksTable']//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Added Ancillary is present in the Task ");
		}else{
			Environment.loger.log(Level.ERROR, "Added Ancillary is not present in the Task ");
			SW.FailCurrentTest("Added Ancillary is not present in the Task ");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
