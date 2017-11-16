package testscripts.vpRegression;

import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN46_VPPropertyDimension {
	CRS SW =new CRS();
	String SucessfullyCreated;
	String DimensionName= SW.RandomString(5).toUpperCase();
	String DimensionID= SW.RandomString(2).toUpperCase();

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
		SW.EnterValue("VP_MenuSearch_EB","Compass Point Codes");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.Click("VPPropContent_CompassPointCodes_BT");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Click("VPPropContent_HSCreate_BT");
		if (SW.ObjectExists("VPPropContent_CompassPointCode_EB")) {
			Reporter.Write("Property Content Created","Property Content of login"," created", "pass");	
		} else {
			Reporter.Write("Property Content not Created","Property Content of login","not created", "fail");
		}

	}

	@Test(priority=3)
	public void CreateQS(){
		SW.EnterValue("VPPropContent_CompassPointCode_EB", DimensionID);
		SW.EnterValue("VPPropContent_CompassPointDesc_EB",DimensionName);
		SW.Click("VPPropContent_CreateHotelServSave_BT");
		if (SW.ObjectExists("VPRates_RPinfoMsg_DT")) {
			Reporter.Write("Property Content Serv created", "Property Content of login","created", "pass");	
		} else {
			Reporter.Write("Property Content Serv notcreated", "Property Content of didnt login","not created", "fail");
		}	
	}	

	@Test(priority=4)
	public void SearchQS(){
		SucessfullyCreated= SW.GetText("VPRates_RPinfoMsg_DT");
		Reporter.WriteLog(Level.INFO,SucessfullyCreated);
		SW.TakeScreenshot("SuccessMessage");
		Reporter.Write("Property Content Searched", "Property Content of login","Searched", "pass");
	}

	@Test(priority=5)
	public  void Db(){
		try{	
			SW.EstablishConnection(Environment.getRunEnvironment());
			String query="select compass_point_name from odsref.compass_point_lkup where compass_point_name='"+ DimensionName +"'";
			Reporter.WriteLog(Level.INFO, query);
			if(SW.RecordExists(query)){
				List<String> output = SW.GetColumnValues(query, 1);{
					if (SW.CompareText(output.get(0), DimensionName)) {
						Reporter.Write("Attractive Serv Type Created", "Attraction Serv type should present in DB", "Attraction Servtype is present in DB", "PASS");
					} else {
						Reporter.Write("Attractive Serv Type not Created", "Attraction Serv type should present in DB", "Attraction Serv type not present in DB", "FAIL");
					}
				}
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
		} 
		SW.CloseDBConnection();
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}