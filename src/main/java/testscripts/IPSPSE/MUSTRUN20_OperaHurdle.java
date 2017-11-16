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

public class MUSTRUN20_OperaHurdle {

	CRS SW = new CRS();
	String RatePlanID = SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
	String HurdleRatePlan;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}
	@Test(priority=0)
	public void TransientRatePlan(){
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.Click("VP_Submit_BT");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
	}
	@Test(priority=1)
	public void RateListForm(){
		SW.Click("VP_MenuSearch_EB");		
		SW.EnterValue("VP_MenuSearch_EB","Rate Plan List");
		if (SW.ObjectExists("VPProp_RatePlanlist_ST")) {
			SW.Click("VPProp_RatePlanlist_ST");		
		} else {
			SW.Click("VP_MenuSearch_EB");
		}
SW.Wait(20);
		SW.SwitchToFrame("VP_MainFrame_FR");
		if (SW.ObjectExists("VpRates_RateListForm_EB")) {
			SW.EnterValue("VpRates_RateListForm_EB","Hurdle");		
		} else {
			SW.Wait(20);
			SW.EnterValue("VpRates_RateListForm_EB","Hurdle");
		}
		SW.WaitTillElementToBeClickable("VpRates_Search_BT");
		SW.Click("VpRates_Search_BT");
	}
	@Test(priority=2) 			
	public void ManageRatePlan(){
		SW.CheckBox("VpRates_RatePlanId_CB", "ON");
		if(SW.IsEnabled("VpRates_Modify_BT", "Enabled")){
			Reporter.Write("VpRates_Modify_BT", "Modify button should be enabled", "Modify button is enabled", "PASS");
		}else{
			Reporter.Write("VpRates_Modify_BT", "Modify button should be enabled", "Modify button is not enabled", "FAIL");
			SW.DropDown_SelectByValue("VpRates_SelectStatus_DD", "Work In Progress");
			SW.VPClick("VpRates_Search_BT");
			SW.CheckBox("VpRates_RatePlanId_CB", "ON");
			List<WebElement> DeleteButton = SW.GetAllElements("VpRates_Delete_BT");
			DeleteButton.get(0).sendKeys(Keys.SPACE);
			SW.HandleAlert(true);
			String ErrorMsgInfo= SW.GetText("VPRates_RPinfoMsg_DT");//del
			Reporter.WriteLog(Level.INFO,ErrorMsgInfo);//del
			SW.DropDown_SelectByValue("VpRates_SelectStatus_DD", "Active");
			SW.VPClick("VpRates_Search_BT");	
			SW.CheckBox("VpRates_RatePlanId_CB", "ON");
		}
		SW.VPClick("VpRates_Modify_BT");
		SW.EnterValue("Vprates_ChangeDiscription_EB", "Successfull");
		SW.VPClick("VPRates_RPHeaderSave_BT");//
		SW.VPClick("VPRates_RPAssociationSave_BT");
	}
	@Test(priority=3)
	public void RateSeasonPricing(){	
		
		SW.CheckBox("VpRates_ActiveAll_CB","OFF");
	//	SW.VPClick("VpRates_SuperiorRoomClass_CB");//remove
		SW.CheckBox("Vprates_Active1_CB", "ON");//issue
		//	SW.HandleAlert(true);
		SW.DropDown_SelectByText("VPRATES_PRODUCTDIFF1_DD", "Fixed");
		SW.EnterValue("VPRates_SinglePersonRateR1_EB", 300);
		SW.EnterValue("VPRates_DoublePersonRateR1_EB", 300);
		SW.EnterValue("VPRates_ExtraPersonRateR1_EB", 300);
		SW.EnterValue("VPRates_ExtraChildRateR1_EB", 300);//block this line for Galaxay
		//		SW.CheckBox("Vprates_Active2_CB", "ON");
		//		SW.HandleAlert(true);
		//		SW.DropDown_SelectByValue("Vprates_ProductDiff2_DD", "Fixed");
		//		SW.EnterValue("VPRates_SinglePersonRateR2_EB", 300);
		//		SW.EnterValue("VPRates_DoublePersonRateR2_EB", 300);
		//		SW.EnterValue("VPRates_ExtraPersonRateR2_EB", 300);
		//		SW.EnterValue("VPRates_ExtraChildRateR2_EB", 300);//block this line for Galaxay
		
		//		SW.CheckBox("Vprates_Active3_CB", "ON");
		//		SW.HandleAlert(true);
		//		SW.DropDown_SelectByValue("Vprates_ProductDiff3_DD", "Fixed");
		//		SW.EnterValue("VPRates_SinglePersonRateR3_EB", 300);
		//		SW.EnterValue("VPRates_DoublePersonRateR3_EB", 300);
		//		SW.EnterValue("VPRates_ExtraPersonRateR3_EB", 300);
		//		SW.EnterValue("VPRates_ExtraChildRateR3_EB", 300);//block this line for Galaxay
		
	}
	@Test(priority=4)
	public void saveSenario(){
		SW.VPClick("VPRates_DynamicSeasonSave_BT");//todo Webpage Dialog and validate and print msg
	//	System.out.println(SW.GetTitle());
	//	SW.WaitForWindowCount(2);
	//	SW.SwitchToWindow(2);
	//	System.out.println(SW.GetTitle());
	//	SW.ClickAndProceed("VPPropContent_RatePLanInfoWIndow_BT");
	//	SW.HandleAlert(true);
	//	SW.SwitchToWindow(1);
	//TODO Click
	//	SW.Click("VPRates_DynamicSeasonSave_BT");
		SW.Click("VPRates_SellSequanceSave_BT");
		SW.Click("VPRates_Save&Publish_BT");
		
		
		String SucessInfo1= SW.GetText("VPRates_RPinfoMsg_DT");
		Reporter.WriteLog(Level.INFO,SucessInfo1);

	//	SW.VPClick("VPRates_SellSequanceSave_BT");
	//	SW.VPClick("VPRates_Save&Publish_BT");	
	//	String ErrMsg= SW.GetText("VPRates_CriticalErrMsg_BT");

	//	Reporter.WriteLog(Level.INFO,ErrMsg);
		if (SW.ObjectExists("VPRates_RPinfoMsg_DT")) {
			Reporter.Write("Property Content Serv created", "Property Content of login","created", "pass");	
		} else {
			Reporter.Write("Property Content Serv notcreated", "Property Content of didnt login","not created", "fail");
		}	

	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}


}
