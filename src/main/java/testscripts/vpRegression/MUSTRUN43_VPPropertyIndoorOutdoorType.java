package testscripts.vpRegression;

import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN43_VPPropertyIndoorOutdoorType {
	CRS SW =new CRS();
	String SucessfullyCreated;
	String IndoorOutdoorType= SW.RandomString(5).toUpperCase();
	String IndoorOutdoorTypeID= SW.RandomString(2).toUpperCase();

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
		SW.EnterValue("VP_MenuSearch_EB","Indoor Outdoor Type");
		SW.Click("VPPropContent_IndoorOutdoorType_BT");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Click("VPPropContent_HSCreate_BT");
		if (SW.ObjectExists("VPPropContent_CompassID_ET")) {
			Reporter.Write("Property Content Created","Property Content of login"," created", "pass");	
		} else {
			Reporter.Write("Property Content not Created","Property Content of login","not created", "fail");
		}
	
	}
	
	@Test(priority=3)
	public void CreateQS(){
		SW.NormalClick("VPPropContent_CompassID_ET");
	//	SW.DropDown_SelectByValue("VPPropContent_CatogeryTypeOwnerId_DD", "2");
		SW.EnterValue("VPPropContent_CompassID_ET", IndoorOutdoorTypeID);
		SW.EnterValue("VPPropContent_AttractionType_ST",IndoorOutdoorType );
		
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
			//SW.Wait(100);//for reflect into DB
			SW.EstablishConnection(Environment.getRunEnvironment());
			String query="select indoor_outdoor_description from prop.indoor_outdoor_lkup where indoor_outdoor_description='"+ IndoorOutdoorType +"'";
			Reporter.WriteLog(Level.INFO, query);
			if(SW.RecordExists(query)){
				List<String> output = SW.GetColumnValues(query, 1);{
					if (SW.CompareText(output.get(0), IndoorOutdoorType)) {
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