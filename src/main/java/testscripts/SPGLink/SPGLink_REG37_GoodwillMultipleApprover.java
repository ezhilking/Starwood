package testscripts.SPGLink;
/** Purpose		: This is to validate Misc Starpoints Final Approver role to Approve multiple starpoints request 
 * TestCase Name: GoodwillMultipleApprover
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

public class SPGLink_REG37_GoodwillMultipleApprover {

CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLINK);
		
}
	
	@Test(priority=1)
	public void GoodwillMultipleApprover () {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("Misc Starpoints Final Approver");
		SW.Click("SPGLink_OtherStarPoints_ApproverHome_BT");
		SW.Click("SPGLink_OtherStarPoints_ApproverListing_BT");
		SW.CheckBox("SPGLink_OtherStarPoints_MultipleApproverFirst_CB","ON");
		SW.CheckBox("SPGLink_OtherStarPoints_MultipleApproverSecond_CB","ON");
		SW.NormalClick("SPGLink_OtherStarPoints_MultipleApprove_BT");
		SW.WaitTillElementToBeClickable("SPGLinkAdmin_RoleChangeAlert_DT");
		
		/*Misc Starpoints final approver should be able to approve multiple request at a time */
		if(SW.CompareTextContained("Success message", "Selected transactions were processed", SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT")))
			Environment.loger.log(Level.INFO, "Selected transactions were processed");
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
