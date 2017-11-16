package testscripts.resCon;
/* Purpose		: Validating the deletion of MP Event for Rescon application
 * TestCase Name:[1]TC111_Delete event file_event page
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN36_DeleteMPEvent {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
	}
	
	@Test(priority=1)
	public void DeleteMPEvent(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
		SW.Click("ResconMP_MpUpload_LK");
		SW.DropDown_SelectByValue("ResconMP_UploadListSelectPropertyDropdown_DD",SW.TestData("SGP_PID"));
		if (SW.IsEnabled("ResconMP_EventDeleteIcon_LK", "Enabled"))
			Reporter.Write("ResconMPDeleteEventcreated", "Event should not be deleted ", "Event is not deleted", "PASS");
		List<WebElement> DeleteIcon = SW.GetAllVisibleElements("ResconMP_EventDeleteIcon_LK");
		DeleteIcon.get(0).click();
		//		SW.ClickAndProceed("ResconMP_EventDeleteIcon_LK");
		//SW.NormalClick("ResconMP_EventDeleteIcon_LK");
		SW.HandleAlert(true);
		if (SW.IsEnabled("ResconMP_EventDeleteIcon_LK", "Enabled")){
			Reporter.Write("ResconMPDeleteEventcreated", "Event should be deleted ", "Event is deleted", "PASS");
		}else{
			Reporter.Write("ResconMPDeleteEventcreated", "Event should not be deleted ", "Event is not deleted", "Fail");
		}
	}
	@AfterClass		
	public void EndTest()
	{
		Reporter.StopTest();		
	}
}
