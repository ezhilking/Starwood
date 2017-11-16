package testscripts.SPGCentro;
/** PreRequiste: Change the Membership number and external membership number in the "SVO.CSV" file in the Document/CRM folder
				 The mbrship number should be active and it should not have any EXTERNAL_MARKETING_PROGRAM_ID as Start-with SVO 	
 * Purpose		: This is to validate the SVO External Bulk loader
 * TestCase Name: SVOBulkUpload
 * Created By	: Indushree Lokesh
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SPGCentro_REG07_SVOBulkUpload {
	
	CRM SW = new CRM();
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGCENTRO);
	}
	
	@Test(priority=1)
	public void SVOBulkUpload (){
	SW.SPGCentroLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
	SW.NormalClick("SPGCentro_MainMenu_ST");
	SW.Click("SPGCentro_BulkLoader_DD");
	SW.Click("SPGCentro_SVOBulkLoader_BT");
	SW.EnterValue("SPGCentro_SVOBulkLoader_Browse_BT",Environment.Documents+"\\CRM\\Svo.csv");
	SW.Wait(5);
	SW.Click("SPGCentro_SVOBulkLoader_Next_BT");
	SW.Wait(5);
	SW.Click("SPGCentro_SVOBulkLoader_Load_BT");
	SW.Wait(5);
	SW.Click("SPGCentro_SVOBulkLoader_Submit_BT");
	SW.CompareText("Success message", "Bulk load has completed. Any rows that could not be processed will be displayed below.", SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT"));
	}
	
	@AfterClass
	public void StopTest(){
		SW.Click("CentroLogout_BT");
		Reporter.StopTest();
	}
	
	

}
