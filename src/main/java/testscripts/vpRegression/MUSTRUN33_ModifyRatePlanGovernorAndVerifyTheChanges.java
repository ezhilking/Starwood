/* Purpose		:  Valhalla Portal Must Run Suit
 * TestCase Name:  Reg_Corp User_Verify the existence of new menu item Rate Plan Governors under Rates tab
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */


package testscripts.vpRegression;


import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;


public class MUSTRUN33_ModifyRatePlanGovernorAndVerifyTheChanges {
	CRS SW = new CRS();
	int MinValue=SW.RandomNumber(10, 90);
	int MaxValue=MinValue+10;
	String Mxvalue = Integer.toString(MaxValue);
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
		
	}
	
	@Test(priority=0)
	    public void TransientRatePlan(){
		SW.VPLogin("VP_Username", "VP_Password");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan Governors");
		SW.Click("VP_CorpRPGoverners_Governers_Lk");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.SelectRadioButton("VP_ManageGoverners_SelectedGov_RB");
		String SelectedRP = SW.GetText("VP_ManageGoverners_SelectedRPid_DT");
		SW.Click("VP_ManageGoverners_Modify_BT");
		SW.EnterValue("VP_CorpRPGoverners_MinPercentageAmount_EB", MinValue);
		SW.EnterValue("VP_CorpRPGoverners_MaxPercentageAmount_EB", Mxvalue+Keys.TAB);
		SW.Click("VP_CorpRPGoverners_Submit_BN");
		SW.CompareText("Modify Sucess Msg Validation","Rate Plan ID ["+SelectedRP+"] Governor Saved Successfully.", SW.GetText("VP_CorpRPGoverners_SuccessMsg_DT").trim());
		
	}
	
	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}
