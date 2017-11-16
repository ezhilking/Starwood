/* Purpose		:  Valhalla Portal Musy Run Suit
 * TestCase Name:  Reg_Corp User_Verify the existence of new menu item Rate Plan Governors under Rates tab
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */


package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRS;
import functions.Environment;
import functions.Reporter;


public class MUSTRUN30_VerifyTheExistenceOfNewMenuItemRatePlanGovernors {
	CRS SW = new CRS();
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
	}
	
	@Test(priority=0)
	    public void verifyGoverner(){
		SW.VPLogin("VP_Username", "VP_Password");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan Governors");
		String S1=SW.GetAttributeValue("VP_PropRPGoverners_Governers_Lk", "href");
		if(S1.equalsIgnoreCase("https://valhallaportal.qa3.nssd.star/ECP_NEW/jsp-secure/ECP_MenuItemRedirect.jsp?id=ECP_Rates_Rate_Plan_Governor")){
			Reporter.Write("Corp User Governers Menu Validation", "Rate Plan Governers Option should be Enable in Menu for Corp User", "Rate Plan Governers Option is Enabled in Menu for Corp User", "PASS");
			SW.Click("VP_CorpRPGoverners_Governers_Lk");
			SW.SwitchToFrame("VP_MainFrame_FR");
			if(SW.IsEnabled("VP_CorpRPGoverners_CreateGov_BT", "ENABLED")){
			Reporter.Write("Corp User Governers Menu Option Functionality Validation", "Rate Plan Governers Screen should get loaded sucessfully", "Rate Plan Governers Screen got loaded sucessfully", "PASS");
			}else{
				Reporter.Write("Corp User Governers Menu Option Functionality Validation", "Rate Plan Governers Screen should get loaded sucessfully", "Rate Plan Governers Screen didnt got loaded sucessfully", "FAIL");
			}
		}else{
			Reporter.Write("Corp User Governers Menu Validation", "Rate Plan Governers Option should be Enable in Menu for Corp User", "Rate Plan Governers Option is not Enabled in Menu for Corp User", "Fail");
		}
		SW.SwitchToFrame("");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan Governors");
		String S2=SW.GetAttributeValue("VP_PropRPGoverners_Governers_Lk", "href");
		if(S2.equalsIgnoreCase("https://valhallaportal.qa3.nssd.star/ECP_RATES_NEW/jsp-secure/ECP_Application.xhtml#")){
			Reporter.Write("Prop User Governers Menu Validation", "Rate Plan Governers Option should be disabled in Menu for Prop User", "Rate Plan Governers Option is disabled in Menufor prop User", "PASS");
		}else{
			Reporter.Write("Prop User Governers Menu Validation", "Rate Plan Governers Option should be disabled in Menu for Prop User", "Rate Plan Governers Option is Enabled in Menufor prop User", "FAIL");
		}
		
	}
	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}
