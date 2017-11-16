package testscripts.SPGLight;
/** Purpose		: This is to validate the starpointsrequest approver role under SPGLight
 * TestCase Name: SPGLightMemberLookup
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

public class SPGLight_REG02_StarPointsRequestApprove {
	CRM SW = new CRM();
	int num;

	@BeforeClass
	public void startTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLIGHT);
		num=SW.RandomInteger(1);
	}
	@Test
	public void StarPointsRequestApprove(){
		SW.SPGLightLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		SW.Click("SPGLight_Home_Admin_BT");
		SW.Click("SPGLight_Home_Admin_SuperUser_BT");
		SW.DropDown_SelectByText("SPGLight_Home_SuperUser_DD", "vseRequester");
		SW.Click("SPGLight_Home_SuperUser_Submit_BT");
		SW.Click("SPGLight_PointRequest_BT");
		SW.CheckBox("//*[@id='23752253641440961']/tbody/tr["+num+"]/td[1]/input", "ON");
		SW.NormalClick("//*[@id='23752253641440961']/tbody/tr["+num+"]/td[2]/a/img");
		SW.SwitchToFrame("SPGLink_OtherStarPoints_Approverframe_FR");
		SW.Wait(5);
		SW.DropDown_SelectByText("SPGLight_PointRequest_Approve_DD", "Approve");
		SW.NormalClick("SPGLight_PointRequest_Submit_BT");
		if(SW.CompareText("Success message", "Transaction processed successfully!!", SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT")))
			Environment.loger.log(Level.INFO, "Transaction processed successfully");
		else
		{
			Environment.loger.log(Level.ERROR,"Transaction failed ");
			SW.FailCurrentTest("Validation fails in Transaction Processing");
		}
	}

	@AfterClass
	public void endTest(){
		SW.Click("SPGLightLogout_BT");
		Reporter.StopTest();
	}
}
