package testscripts.SPGCentro;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		: This is to maintain the existing marketing program properties
 * TestCase Name: MarketingProgramPropertiesMaintenance
 * Created By	: Jovith kiran
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */

public class SPGCentro_REG08_MarketingProgramPropertiesMaintenance {
	CRM SW = new CRM();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGCENTRO);
	}

	@Test(priority=0)
	public void MarketingProgramPropertiesMaintenance() {

		SW.SPGCentroLogin(SW.TestData("SPGCentroUsername"),SW.TestData("SPGCentroPassword"));
		SW.Click("SPGCentro_MarketingProgramPropertiesMaintenanceText_LK");
		SW.EnterValue("SPGCentro_Search_EB",SW.TestData("SPGCentroPropertyNumber"));
		SW.Click("SPGCentro_Search_BT");
		SW.Click("SPGCentro_PropertySelect_BT");
		SW.EnterValue("SPGCentro_PropMaintainence_BaseADRAmnt_EB","1000");
		SW.EnterValue("SPGCentro_PropMaintainence_MultipleFactor_EB","4");
		SW.EnterValue("SPGCentro_PropMaintainence_MaxEffectiveDate_BT", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -2, Calendar.DATE));
		SW.Click("SPGCentro_PropMaintainence_Update_BT");
		if(!SW.CompareText("Success message", "Changes saved.",SW.GetText("SPGCentro_PropMaintainence_Success_DT"))){
			Reporter.Write("Error message", "Error Occured", SW.GetText("SPGCentro_PropMaintainence_Error_DT"), "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("CentroLogout_BT");
		Reporter.StopTest();		
	}


}