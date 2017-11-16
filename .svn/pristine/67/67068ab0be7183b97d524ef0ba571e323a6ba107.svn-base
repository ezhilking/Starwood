package testscripts.SPGLink;
/** Purpose		: This is to validate Misc Starpoints Final Approver role to reject the starpoints request 
 * TestCase Name: GoodwillApproverReject
 * Created By	: Indushree Lokesh
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

public class SPGLink_REG36_GoodwillApproverReject {
CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLINK);
		
}
	
	@Test(priority=1)
	public void GoodwillApproverReject () {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("Misc Starpoints Final Approver");
		SW.Click("SPGLink_OtherStarPoints_ApproverHome_BT");
		SW.Click("SPGLink_OtherStarPoints_ApproverListing_BT");
		SW.NormalClick("SPGLink_OtherStarPoints_ApproverEdit_BT");
		SW.SwitchToFrame("SPGLink_OtherStarPoints_Approverframe_FR");
		SW.DropDown_SelectByText("SPGLink_OtherStarPoints_ApproverframeAr_DD", "Reject");
		SW.EnterValue("SPGLink_OtherStarPoints_ApproverframeRejectReason_EB","Transaction not valid");
		SW.Click("SPGLink_OtherStarPoints_ApproverframeSubmit_BT");
		SW.WaitTillElementToBeClickable("SPGLinkAdmin_RoleChangeAlert_DT");
		
		
		/*Misc Starpoints Final Approver should be able to reject the starpoints request with valid rejection reason */
		if(SW.CompareText("Success message", "Transaction processed successfully!!", SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT")))
			Environment.loger.log(Level.INFO, "Transaction processed successfully");
		else
		{
			Environment.loger.log(Level.ERROR,"Transaction failed ");
			SW.FailCurrentTest("Validation fails in Transaction Processing");
		}
	}
	
	@AfterClass
	public void stopTest(){
		SW.Click("SPGLinkLogout_BT");
	Reporter.StopTest();	
		
	}

}
