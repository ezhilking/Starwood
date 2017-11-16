package testscripts.SPGLink;
/** Purpose		: This is to Approve the event in SPG Link 2.0 under SPG Pro Approver
 * TestCase Name: EventProApprove
 * Created By	: Vaishali Krishna
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

public class SPGLink_REG17_EventProApprover {
	CRM SW = new CRM();
	String EventName;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	@Test(priority=0)
	public void EventProApprove(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("SPG Pro Approver");
		SW.WaitForPageload();
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLink_Event_BT");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("SPGLinkEventListing_StatusChange_DD", "3. Pending Approval");
		if(SW.ObjectExists("SPGLinkEventListing_FirstEvent_LK")==false)
			SW.FailCurrentTest("No Event Exists to approve");
		EventName = SW.GetText("SPGLinkEventListing_FirstEvent_LK");
		SW.Click("SPGLinkEventListing_FirstEvent_LK");
		if(SW.IsAlertPresent())
			SW.HandleAlert(true);
		SW.Click("SPGLinkEventPosting_Approve_BT");
		SW.WaitForPageload();
	
	if (SW.ObjectExists("SPGLinkEventPosting_ApproveSuccess_ST")) {
		Environment.loger.log(Level.INFO, "Your event" + EventName +" has been submitted for financial review. Thank You.");
	} else if (SW.ObjectExists("SPGLinkEventPosting_Error_DT")){
		String ErrorMsg = SW.GetText("SPGLinkEventPosting_Error_DT");
		Environment.loger.log(Level.ERROR, "Error in Event Creation" + ErrorMsg);
		SW.FailCurrentTest(ErrorMsg);
	}
}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SPGLinkEvent_LogOut_BT");
		Reporter.StopTest();		
	}

}
