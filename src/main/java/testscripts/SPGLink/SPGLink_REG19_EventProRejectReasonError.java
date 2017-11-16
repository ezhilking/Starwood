package testscripts.SPGLink;
/** Purpose		: This is to Approve the event in SPG Link 2.0 under SPG Pro Approver
 * TestCase Name: EventProRejectReason
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

public class SPGLink_REG19_EventProRejectReasonError {
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
	public void EventProRejectReason(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("SPG Pro Approver");
		SW.WaitForPageload();
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLink_Event_BT");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("SPGLinkEventListing_StatusChange_DD", "3. Pending Approval");
		if(SW.ObjectExists("SPGLinkEventListing_FirstEvent_LK")==false)
			SW.FailCurrentTest("No Event Exists");
		EventName = SW.GetText("SPGLinkEventListing_FirstEvent_LK");
		SW.Click("SPGLinkEventListing_FirstEvent_LK");
		if(SW.IsAlertPresent())
			SW.HandleAlert(true);
		/*SW.DropDown_SelectByText("SPGLinkEventPosting_RejectReason_DD", "Duplicate Event Form");*/
		SW.Click("SPGLinkEventPosting_RejectEvent_BT");
		SW.WaitForPageload();
	
	if (SW.ObjectExists("SPGLinkEventPosting_RejectSuccess_FR")) {
		Environment.loger.log(Level.INFO, "Your event" + EventName +" has been rejected by SPG Pro Approver");
		System.out.println("Event rejected successful. Event Name is" + EventName);
		SW.FailCurrentTest("RejectReason");
	} else if (SW.ObjectExists("SPGLinkEventPosting_RejectError_DT")){
		String ErrorMsg = SW.GetText("SPGLinkEventPosting_RejectError_DT");
		Environment.loger.log(Level.ERROR, "Error in Event Rejection" + ErrorMsg);
	}
}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SPGLinkEvent_LogOut_BT");
		Reporter.StopTest();		
	}

}
