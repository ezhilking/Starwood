
/* Purpose		:  Automation
 * TestCase Name:  MUSTRUN11_Reg_VP_VP_PropertyContent_AttractiveType
 * Created By	:  Muneeb
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN12_Reg_VP_PropertyContent_AttractiveType {
	CRS SW =new CRS();
	String SucessfullyCreated;
	String AttractType = SW.RandomString(4).toUpperCase();

//	String PolicyName="Automation Cancel Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");

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
		SW.EnterValue("VP_MenuSearch_EB","Attraction Type");
		SW.Click("VPPropContent_AttractionType_BT");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Click("VPPropContent_HSCreate_BT");
	}


	@Test(priority=3)
	public void CreateQS(){
		SW.NormalClick("VPPropContent_AttractionType_ST");
		SW.EnterValue("VPPropContent_AttractionType_ST", AttractType);
		SW.Click("VPPropContent_CreateHotelServSave_BT");
	}

	@Test(priority=4)
	public void SearchQS(){
		SucessfullyCreated= SW.GetText("VPRates_RPinfoMsg_DT");
		Reporter.WriteLog(Level.INFO,SucessfullyCreated);
		SW.TakeScreenshot("SuccessMessage");
	}

	@Test(priority=5)
	public  void Db(){
		try{	
			//SW.Wait(100);//for reflect into DB
			SW.EstablishConnection(Environment.getRunEnvironment());
			String query="select  attraction_type_name from attraction_type_lkup where attraction_type_name='"+ AttractType +"'";
			Reporter.WriteLog(Level.INFO, query);
			if(SW.RecordExists(query)){
				List<String> output = SW.GetColumnValues(query, 1);{
					if (SW.CompareText(output.get(0), AttractType)) {
						Reporter.Write("scenario", "Attraction type should present in DB", "Attraction type is present in DB", "PASS");
					} else {
						Reporter.Write("scenario", "Attraction type should present in DB", "Attraction type not present in DB", "FAIL");
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
