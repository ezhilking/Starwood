package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN50_VVPropertyDailyRatesDiscount {
	CRS SW =new CRS();
	String SucessfullyCreated;
	String DimensionName= SW.RandomString(5).toUpperCase();
	String DiscountID= SW.RandomString(2).toUpperCase();
	int DiscountAmt= SW.RandomNumber(1, 99);

	String PolicyName="Automation Cancel Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");

	@BeforeClass 
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}
	@Test(priority=1)
	public void VPLogin(){
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.VPClick("VP_Submit_BT");
	}
	@Test(priority=2)
	public void PropertyContent(){
		//	SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Daily Rates Discount");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.Click("VPPropContent_DailyRatesDiscount_EB");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Click("VPPropContent_HSCreate_BT");
		if (SW.ObjectExists("VPPropContent_ParentRatePlanId_EB")) {
			Reporter.Write("Property Content Created","Property Content of login"," created", "pass");	
		} else {
			Reporter.Write("Property Content not Created","Property Content of login","not created", "fail");
		}
	}
	@Test(priority=3)
	public void CreateNewDailyRateDiscount(){
		SW.EnterValue("VPPropContent_ParentRatePlanId_EB", DiscountID );
		SW.EnterValue("VPPropContent_ParentRatePlandiscountAmt_EB",DiscountAmt );
		SW.DropDown_SelectByValue("VPPropContent_ParentRatePlanDiscountType_DD", "P");
		SW.EnterValue("VPPropContent_ParentRatePlanProp_DD", "1513");
		SW.Click("VPPropContent_ParentRatePlanDiscountSave_BT");
		SW.CompareText("VPPropContent_DailyDiscountCreation_DT", "The Daily Rate discount for "+DiscountID+" was created successfully.", SW.GetText("VPRates_RPinfoMsg_DT"));
		
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}