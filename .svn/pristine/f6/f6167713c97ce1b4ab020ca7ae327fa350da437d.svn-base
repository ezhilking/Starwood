package testscripts.SPGCentro;

/** PreRequiste:Change the Membership number and external membership number in the "genericextlbulkloader.CSV" file in the Document/CRM folder
				 The mbrship number should be active 
 * Purpose		: Validate the success message for the generic external bulk loader for valid data
 * TestCase Name: GenericExternalBulkLoader
 * Created By	: Jovith Kiran
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: sachin
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SPGCentro_REG06_GenericExtlBulkLoader {
	CRM SW = new CRM();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGCENTRO);
	}

	@Test(priority=0)
	public void GenericExtlBulkLoader() {

		SW.SPGCentroLogin(SW.TestData("SPGCentroUsername"),SW.TestData("SPGCentroPassword"));
		SW.Click("SPGCentroMainMenu_BT");
		SW.Click("SPGCentroBulkLoader_DD");
		SW.Click("SPGCentroGenereicExtlBulkLoader_BT");
		SW.EnterValue("SPGCentroGenereicExtlBulkLoader_Filename_BT",Environment.Documents+"\\CRM\\genericextlbulkloader.csv");
		SW.Click("SPGCentro_GenericExtlBulkLoader_Next_BT");
		SW.Click("SPGCentro_GenericExtlBulkLoader_Load_BT");
		SW.Click("SPGCentro_GenericExtlBulkLoader_Submit_BT");
		SW.CompareText("Success message", "Bulk load has completed. Any rows that could not be processed will be displayed below.", SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT"));
		if(SW.ObjectExists("SPGCentro_GenericExtlBulkLoader_Error_DT")){
			Reporter.Write("error message", "error message is displayed", "error message is displayed", "Fail");
		//	Reporter.WriteLog(Level.INFO, "Error message Displayed"+SW.GetText("SPGCentro_GenericExtlBulkLoader_Errormsg_DT"));
		}
		else{
			Reporter.Write("success message", "success message displayed", "success message is displayed", "Pass");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("CentroLogout_BT");
		Reporter.StopTest();		
	}

}
