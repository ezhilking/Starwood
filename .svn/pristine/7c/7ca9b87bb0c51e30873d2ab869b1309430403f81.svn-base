/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name: VP_Smoke01_Reg_CorpMode_Create_Rate_Template_Verify_the_screen_and_Validation
 * Created By	:  Disha Rawat
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */

package testscripts.vpRegression;

import java.util.Calendar;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;


public class SMOKETEST06_ShortRangeTLPeVerifyIfDBDRateplanIsEditableInEMD {

	CRS SW = new CRS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);

	}

	@Test
	public void File(){
		SW.VPLogin("VP_Username", "VP_Password");
		SW.EnterValue("VP_PropertyID_EB", SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.Wait(5);
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB", "BAR, DAILY, Hurdle Short Range");
		SW.Click("VPRates_ShortRange_LK");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Wait(15);
		SW.Click("VP_ShortRangeSubmit_BT");
		SW.Wait(20);
		SW.Click("VP_SREditmultipledays__BT");
		String TodatDate = SW.GetTimeStamp("MM/dd/yyyy");
		String FutureDate = SW.DateAddDays(TodatDate, "MM/dd/yyyy", 2, Calendar.DATE);
		SW.EnterValue("VP_EMDdate_EB", FutureDate);
		SW.SelectRadioButton("VP_EMDRatetype_RB");
		SW.ClearValue("VP_EMDprice_EB");
		SW.EnterValue("VP_EMDprice_EB", "1200");
		
		
		SW.ClickAndProceed("VP_EMDSave_BT");
		SW.HandleAlert(true);
		//if(SW.CompareText("Your changes have been saved.", SW.GetAlertText())){
			//SW.FailCurrentTest("");
		}
		
		
	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}

}
