package testscripts.SPGLink;
/** Purpose		: This is to SPG member lookup in SPG Link 2.0 under SPG Pro User Role
 * TestCase Name: MemberLookup
 * Created By	: Veeresh
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

public class SPGLink_REG01_MemberLookup {

	CRM SW = new CRM();
	String Mbrshp_num;

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	@Test(priority=0)
	public void DataSetup_AllFieldPopulated() {
		SW.EstablishConnection(Environment.getRunEnvironment());
		String Query="select * from freq_travel_mbrshp where mbrshp_sts='A' and mbrshp_lvl='A' and mrktg_pgm='5' and join_date > (sysdate-15) and gst_master_prof_id != 0";
		Mbrshp_num=SW.GetColumnValues(Query, "Mbrshp_num").get(0);

		if(Mbrshp_num != null )
			Environment.loger.log(Level.INFO, "Member Exists " +Mbrshp_num);
		else
			SW.FailCurrentTest("No Member Exists, Check Query criteria");
	}
	@Test(priority=1,dependsOnMethods="DataSetup_AllFieldPopulated")
	public void Memberlookup() {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),
				SW.TestData("SPGLinkPassword"),
				SW.TestData("SPGLinkPropId"));

		// Change UserRole
		SW.SPGLinkChangeUserRole("SPG Pro");

		// Member Search
		SW.Click("SPGLinkLookUp_Lookup_BT");
		SW.EnterValue("SPGLinkLookUp_EnterMbrNumber_EB",Mbrshp_num);
		SW.Click("SPGLinkLookUp_ClickSearch_BT");
		Environment.loger.log(Level.INFO, "MemberLookup Successfull!!");
	}

	@AfterClass
	public void EndTest() {
		SW.Click("SPGLink_LogOut_BT");
		SW.CloseDBConnection();
		Reporter.StopTest(); 
	}

}
