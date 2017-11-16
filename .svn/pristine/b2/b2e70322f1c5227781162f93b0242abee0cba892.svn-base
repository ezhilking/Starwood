package testscripts.pmsRegression;
/* Purpose		: TestCase 1) Opera:
A) Add VIP level in SGR (no VIP level previously assigned)
B) Modify in Opera E9
Test case 2) Opera:
A) Modify Opt In status to Opted In in SGR
B) Modify Opt in status to Opted Out in Opera
 * Created By	: Ezhilarasan.S 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.sikuli.script.Key;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;


public class OperaPMS_REG03_VIPLevelAndOptInStatus {
	CRM SW = new CRM();
	String PropertyNo = "1965";
	String ReservationNo = "898945499";

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void SGRVIPAndOptIn(){
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PMSVIPLevelMapping_LK");

		SW.DropDown_SelectByIndex("SGRVIPMapping_First_DD", 1);//Dropdown value available for STAGE environment only.
		SW.SelectRadioButton("SGRVIPMapping_First_RB");

		SW.DropDown_SelectByIndex("SGRVIPMapping_Second_DD", 2);//Dropdown value available for STAGE environment only.
		SW.SelectRadioButton("SGRVIPMapping_Second_RB");
		SW.Click("SGRVIPMapping_Save_BN");
		//
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.DropDown_SelectByText("SGRGuestProfile_VIPLevel_DD", "VIP 1");
		SW.WaitTillPresenceOfElementLocated("SGRGuestProfile_VIPLevelSuccess_DT");//Wait for the Ajax success message

		SW.DropDown_SelectByText("SGRGuestProfile_OptIn_DD", "Opted In");
		SW.WaitTillPresenceOfElementLocated("SGRGuestProfile_OptInSuccess_DT");//Wait for the Ajax success message
		SW.Click("SGR_Logout_LK");
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=2,dependsOnMethods="SGRVIPAndOptIn")
	public void PMSVIP(){
		SW.NavigateTo(Environment.PMS_1965);
		SK.OperaPMSLogin(SW.TestData("PMSUsername"), SW.TestData("PMSPassword"),ReservationNo);
		SK.SikuliRegionType(Key.ENTER);//Instead of double click do a enter.
		SW.Wait(5);
		//		for(int tabcount=1;tabcount<=16;tabcount++){
		//			SW.SikuliRegionType(Key.TAB);
		//			SW.Wait(3);
		//		}

		SK.SikuliClick("ReservationDetails_VIP_EB");
		String VIPText = SK.SikuliCopyToClipboard();
		SK.SikuliCompareText("VIPMapping_DT", "WHO1", VIPText);
		SW.Wait(3);
		SK.SikuliClick("ReservationDetails_VIP_IC");//Click on the VIP icon
		SW.Wait(3);
		SK.SikuliRegionType(Key.DOWN);//Change the VIP to WHO2
		SW.Wait(2);
		SK.KeyboardStrokes("alt|o");//Save it
	}

	@Test(priority=3,dependsOnMethods="PMSVIP")
	public void PMSOptIn(){
		SW.Wait(3);
		SK.SikuliClick("ReservationDetial_OPTIn_EB");
		String SGOPTInText = SK.SikuliCopyToClipboard();
		SK.SikuliCompareText("SGOPTIn_DT", "Y", SGOPTInText);//Opt in displayed as 'Y'
		SK.SikuliClick("ReservationDetial_OPTIn_IC");
		SW.Wait(3);
		SK.SikuliRegionType(Key.DOWN);//Change the OPtin to Optout
		SW.Wait(2);
		SK.KeyboardStrokes("alt|o");//Save it
		SW.Wait(3);
		SK.KeyboardStrokes("alt|s");//overall Save button
		SW.Wait(3);
		SK.KeyboardStrokes("alt|c");//Close reservation detail page
		SW.Wait(3);
		SK.KeyboardStrokes("alt|c");//CLose Update reservation page
		SW.Wait(3);
		SK.KeyboardStrokes("alt|e");//Close 
	}
	
	@Test(priority=4,dependsOnMethods="PMSOptIn")
	public void SGRVIPandOptInValidation(){
		SW.NavigateTo(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		String VIPUpdatedText = SW.DropDown_GetSelectedText("SGRGuestProfile_VIPLevel_DD");
		String OptInUpdatedText = SW.DropDown_GetSelectedText("SGRGuestProfile_OptIn_DD");

		if(SW.CompareText("WHO2", VIPUpdatedText)&&SW.CompareText("N", OptInUpdatedText)){
			SW.CompareText("FianalValidationIn SGR", "Pass", "Pass");
		}else{
			SW.FailCurrentTest("Updated flow not happened in SGR");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
