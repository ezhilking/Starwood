package testscripts.SPGLink;
/** Purpose		: This is to Post a Stay in SPG Link 2.0 under Stay Poster Role
 * TestCase Name: VerifyMisStayApprove
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

public class SPGLink_REG28_VerifyMisStayApprove {
	CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	
	@Test(priority=0)
	public void VerifyMisStayApprove() {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("Verify Missing Stay");
		SW.WaitForPageload();
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLinkHome_StayPost_DD");
		SW.Click("SPGLinkHome_VerifyMisStay_BT");
		SW.Click("SPGLinkMissStayVerification_First");
		SW.SwitchToFrame("SPGLink_MisStayVerification_FR");
		SW.DropDown_SelectByText("SPGLink_Verification_DD", "Approve");
		int DD_Count = SW.DropDown_GetSize("SPGLink_MisStayVeri_WelcomeGift_BT");
		if(DD_Count != 0 && DD_Count != 1) 
			SW.DropDown_SelectByText("SPGLink_MisStayVeri_WelcomeGift_BT", "Starpoints");
		SW.Click("SPGLink_Verification_Submit_BT");
		SW.WaitForPageload();
		SW.SwitchToFrame("");
		
		if (SW.ObjectExists("SPGLinkEventPosting_Success_DT")) {
			Environment.loger.log(Level.INFO, "Stay Approved successfully for the member");
		} else if (SW.ObjectExists("SPGLink_MisStayVeri_Error_DT")){
			String ErrorMsg = SW.GetText("SPGLink_MisStayVeri_Error_DT");
			Environment.loger.log(Level.ERROR, "Stay Not Approved/Rejected has error" + ErrorMsg);
			SW.FailCurrentTest(ErrorMsg);
		}
		
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SPGLink_LogOut_BT");
		SW.CloseDBConnection();
		Reporter.StopTest();		
	}

	
}
