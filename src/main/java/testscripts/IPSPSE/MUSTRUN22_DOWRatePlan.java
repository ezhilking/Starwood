package testscripts.IPSPSE;

import java.util.List;
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRS;
import functions.Environment;
import functions.Reporter;

@Test
public class MUSTRUN22_DOWRatePlan {
	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	String HurdleRatePlan;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}
	@Test(priority=1)
	public void TransientRatePlan(){
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.Click("VP_Submit_BT");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.NormalClick("VP_MenuSearch_EB");//TODO
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan");
		SW.Click("VP_RatePlan_LK");
		SW.Wait(6);
		SW.SwitchToFrame("");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		List<WebElement> RPName = SW.GetAllElements("VPRates_RPName_EB");
		if(RPName.get(0).isEnabled()){
			SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");
		}else {
			SW.SwitchToFrame("");
			SW.WaitForObject("VP_MainFrame_FR");
			SW.EnterValue("VPRates_RPName_EB", "Automated RP Temp");			
		}
		SW.DropDown_SelectByText("VPRates_RPType_DD", "Transient");
		SW.DropDown_SelectByIndex("VPRates_RPRateCateg_DD",1);		
		SW.DropDown_SelectByIndex("VPRates_RPMKTSEG_DD", 1);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);	
		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 0);
		SW.EnterValue("VPRates_RPId_EB", RatePlanID+Keys.TAB);
		Environment.loger.log(Level.INFO, "Rate Plan Id : "+RatePlanID);
		SW.DropDown_SelectByIndex("VPRates_RPType_DD", 3);
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 0); 
		SW.ClearValue("VPRates_RPMinLos_EB");
		SW.DropDown_SelectByIndex("VPRates_RPYLT_DD", 2);
		SW.EnterValue("VPRates_RPMinLos_EB", "1");	
		SW.EnterValue("VPRates_RPMaxLos_EB", Keys.TAB);
		if(SW.HandleAlert(true)){
			Environment.loger.log(Level.INFO, "Max los alert validation Passed");
			SW.EnterValue("VPRates_RPMaxLos_EB", 90);
		}else {
			Environment.loger.log(Level.INFO, "Max los alert validation failed");
			SW.FailCurrentTest("Max los alert validation failed");
		}
		SW.VPClick("VPRates_RPHeaderSave_BT");
		SW.SwitchToFrame("");
		SW.WaitForObject("VP_MainFrame_FR");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.CheckBox("VPRates_Season_DD","ON");
		SW.ClickAndProceed("VPRates_SeasonAdd_DD");//TODO		
		SW.VPClick("VPRates_RPAssociationSave_BT");	
		SW.Wait(2);
		SW.DropDown_SelectByValue("VPRates_SeasonDiffType_DD", "DDOW");
		SW.CheckBox("VpRates_RateInfoDOWAll_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWAllsingle_EB","2000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWAllDouble_EB","200"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWAllExtraP_EB","20"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWAllExtraC_EB", "2"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWSun_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWSunsingle_EB","3000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWSunDouble_EB","300"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWSunExtraP_EB","30"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWSunExtraC_EB", "3"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWMon_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWMonsingle_EB","4000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWMonDouble_EB","400"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWMonExtraP_EB","40"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWMonExtraC_EB", "4"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWTue_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWTuesingle_EB","5000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWTueDouble_EB","500"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWTueExtraP_EB","50"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWTueExtraC_EB", "5"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWWed_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWWedsingle_EB","6000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWWedDouble_EB","600"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWWedExtraP_EB","60"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWWedExtraC_EB", "6"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWThr_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWThrsingle_EB","7000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWThrDouble_EB","700"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWThrExtraP_EB","70"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWThrExtraC_EB", "7"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWFri_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWFrisingle_EB","8000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWFriDouble_EB","800"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWFriExtraP_EB","80"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWFriExtraC_EB", "8"+Keys.TAB);

		SW.CheckBox("VpRates_RateInfoDOWSat_CB", "ON");
		SW.EnterValue("VpRates_RateInfoDOWSatsingle_EB","9000"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWSatDouble_EB","900"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWSatExtraP_EB","90"+Keys.TAB);
		SW.EnterValue("VpRates_RateInfoDOWSatExtraC_EB", "9"+Keys.TAB);
		List<WebElement> DeleteButton = SW.GetAllElements("VPRates_DynamicSeasonSave_BT");
		DeleteButton.get(0).sendKeys(Keys.SPACE);
		SW.HandleAlert(true);
//		SW.VPClick("VPRates_DynamicSeasonSave_BT");//issue it is clicking but not going to next screen.
//		SW.VPNormalClick("VPRates_DynamicSeasonSave_BT");		
//		if(SW.HandleAlert(true)){
//			Environment.loger.log(Level.INFO, "Max los alert validation Passed");
//		}else {
//			Environment.loger.log(Level.INFO, "Max los alert validation failed");	
//		}
//		SW.VPClick("VPRates_DynamicSeasonSave_BT");
		SW.VPClick("VPRates_SellSequanceSave_BT");
		SW.VPClick("VPRates_RPAssociateChannelsSave_BT");
		String SuccuessInfo =SW.GetText("VPRates_RPinfoMsg_DT");
		
		Reporter.Write("Successfull Creation",SuccuessInfo , SuccuessInfo, "success");
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}




