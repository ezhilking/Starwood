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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;
import functions.Utility;


public class GalaxyPMS_REG04_VIPLevelAndOptInStatus {
	CRM SW = new CRM();
	String PropertyNo = "1055";
	String ReservationNo = "";

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
	public void GalaxyPMSVIP(){
		SW.NavigateTo(Environment.GALAXYURL);
		SK.GalaxyPMSLogin(ReservationNo);
		String AttriValue = SW.GetAttributeValue("GalaxyPMS_GuestInformation_IC", "src");
		if(AttriValue.endsWith("/plus.gif"))
			SW.ClickByJavascript("GalaxyPMS_CommentsPlus_IC");
		String SelectedText = SW.DropDown_GetSelectedText("GalaxyPMS_GuestInformationVIP_DD");
		SW.CompareTextContained("VIP", "CEM Guest", SelectedText);
		SW.DropDown_GetSelectedText("GalaxyPMS_GuestInformationOptIn_DD");
		SW.CompareTextContained("OptIn", "Opted In", SW.DropDown_GetSelectedText("GalaxyPMS_GuestInformationOptIn_DD"));
	}

	@Test(priority=3,dependsOnMethods="GalaxyPMSVIP")
	public void UpdateVIPAndOptIn(){
		SW.DropDown_SelectByText("GalaxyPMS_GuestInformationVIP_DD", "1 - CEO/Site/Press/Head Of State");//Update the VIP
		SW.DropDown_SelectByText("GalaxyPMS_GuestInformationOptIn_DD", "Opted Out");
		SW.ClickByJavascript("GalaxyPMS_Save_BN");//Save
		SK.SikuliClick("Galaxy_Close_BN");
		Utility.CloseBrowser();
	}

	@Test(priority=4,dependsOnMethods="PMSOptIn")
	public void SGRVIPandOptInValidation(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		String VIPUpdatedText = SW.DropDown_GetSelectedText("SGRGuestProfile_VIPLevel_DD");
		String OptInUpdatedText = SW.DropDown_GetSelectedText("SGRGuestProfile_OptIn_DD");
		if(SW.CompareText("VIP 2", VIPUpdatedText)&&SW.CompareText("Opted Out", OptInUpdatedText)){
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
