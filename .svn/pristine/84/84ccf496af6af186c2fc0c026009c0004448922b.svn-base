package testscripts.SPGLink;
/** Purpose		: This is to Approve the event in SPG Link 2.0 under SPG Pro Approver
 * TestCase Name: EventProFinalApprove
 * Created By	: Vaishali Krishna
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SPGLink_REG20_EventProFinalApprove {
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
	public void EventProFinalApprove(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("SPG Pro Final Approver");
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLinkEventHome_Event_BT");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("SPGLinkEventListing_StatusChange_DD","4. Pending SPG Pro Final Approval");
		if(SW.ObjectExists("SPGLinkEventListing_FirstEvent_LK")==false)
			SW.FailCurrentTest("No Event Exists");
		EventName = SW.GetText("SPGLinkEventListing_FirstEvent_LK");
		SW.Click("SPGLinkEventListing_FirstEvent_LK");
		SW.WaitForPageload();
		if(SW.IsAlertPresent())
			SW.HandleAlert(true);
		SW.EnterValue("SPGLinkEventPosting_DateOfPayment_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -12,	 Calendar.DATE));
		SW.Click("SPGLinkEventPosting_RandomClick_ST");
		SW.Click("SPGLinkEventPosting_FinalApprove_BT");
		SW.WaitForPageload();
		
		if (SW.ObjectExists("SPGLinkEventPosting_Success_DT")) {
			Environment.loger.log(Level.INFO, "Your event" + EventName +" has been successfully Approved by the Final Approver");
			System.out.println("Event Approved Successfully by Final Approver. Event Name is" + EventName);
		} else if (SW.ObjectExists("SPGLinkEventPosting_Error_DT")){
			String ErrorMsg = SW.GetText("SPGLinkEventPosting_Error_DT");
			Environment.loger.log(Level.ERROR, "Error in Event Approval" + ErrorMsg);
			SW.FailCurrentTest(ErrorMsg);
		}

	}
	@AfterClass
	public void EndTest(){
		SW.Click("SPGLinkEvent_LogOut_BT");
		Reporter.StopTest();
	}

}
