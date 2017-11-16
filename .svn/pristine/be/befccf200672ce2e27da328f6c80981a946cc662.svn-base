package testscripts.vpRegression;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN51_VVPropertyFloatingRateManagement {
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
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Floating Rate Plan Management");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.Click("VPPropContent_FloatingRatePlanManagement_BT");
		SW.SwitchToFrame("VP_MainFrame_FR");
		
		
	}
	@Test(priority=3)
	public void RatePlanHeader(){
		SW.Click("VPPropContent_Floating Rate Plan ManagementCreate_BT");	
	}
	
	@Test(priority=4)
	public void FloatingRatePlanTypes()
	{
		SW.DropDown_SelectByValue("VPPropContent_FloatingRatePlanDivision_DD", "NORTH AMERICA DIV");
		SW.EnterValue("VPPropContent_PropertyId_EB", "1005");
		SW.EnterValue("VPPropContent_FloatingPropertyId_EB", "1005");
		SW.DropDown_SelectByValue("VPPropContent_FloatingPropertyId_EB", "FLOAT9");
		SW.DropDown_SelectByValue("", "G15");
		SW.EnterValue("VPPropContent_FloatingPropertyGroupId_EB", "G15");
		SW.EnterValue("VPPropContent_PercentText_EB", "12");
		SW.EnterValue("VPPropContent_FixedAmtText_EB", "1234");
		
	}
	
	
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}


}
