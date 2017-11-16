package testscripts.SPGLink;

import org.apache.log4j.Level;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		: This is to validate Design 
 * TestCase Name: DesignEventReject
 * Created By	: Indushree Lokesh
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */

public class SPGLink_REG42_DesignEventReject {
	CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	
	@Test(priority=0)
	public void DesignEventReject(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId_DesignHotel"));
		SW.SPGLinkChangeUserRole("designMiStayApprover");
		SW.Click("SPGLink_DesignMistay_Home_BT");
		SW.Click("SPGLink_DesignMistay_DD");
		SW.Click("SPGLink_DesignMistay_MistayVerification_BT");
		SW.NormalClick("SPGLink_DesignMistay_MistayEdit_BT");
		SW.SwitchToFrame("SPGLink_DesignMistay_Frame_FR");
		SW.DropDown_SelectByText("SPGLink_DesignMistay_Verification_DD", "Reject");
		SW.DropDown_SelectByText("SPGLink_DesignMistay_RejectReason_DD", "Stay already posted");
		SW.Click("SPGLink_DesignMistay_Submit_BT");
		if (SW.ObjectExists("SPGLink_DesignMistay_success_ST")) {
            Environment.loger.log(Level.INFO, "A missing stay verification is processed successfully");
        }
		else if (SW.ObjectExists("SPGLink_DesignMistay_Error_ST")){
            String ErrorMsg = SW.GetText("SPGLink_DesignMistay_Error_ST");
            Environment.loger.log(Level.ERROR, "missing stay verification has some error" + ErrorMsg);
            SW.FailCurrentTest(ErrorMsg);
}

		}
	
	@AfterTest
	public void stopTest(){
		SW.Click("SPGLinkLogout_BT");
	Reporter.StopTest();
	}

}