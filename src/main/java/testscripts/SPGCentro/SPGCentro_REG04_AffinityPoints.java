package testscripts.SPGCentro;
/** Purpose		: This is to validate the AffinityPoints for members in centro 
 * TestCase Name: AffinityPoints
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

public class SPGCentro_REG04_AffinityPoints {
	CRM SW = new CRM();
	String Mbrshp_num;
	
	@BeforeClass
	public void startTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGCENTRO);
	}
	
	@Test(priority=1)
	public void getDataAffinityPoints(){
		SW.EstablishConnection("QA3");
		String query="select * from freq_travel_mbrshp where mbrshp_lvl='A' and mbrshp_sts='A' and src_cd='GMAL' AND join_date='11=NOV-98'AND enroll_loc_id='0' and credit_card_opt_ind='N'";
		Mbrshp_num=SW.GetColumnValues(query, "Mbrshp_num").get(0);
		System.out.println(Mbrshp_num);
	
	}
	
	@Test(priority=2,dependsOnMethods="getDataAffinityPoints")
	public void AffinityPoints(){
		SW.SPGCentroLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		SW.Click("SPGCentro_AffinityPoint_BT");
		SW.EnterValue("SPGCentro_AffinityPoint_Mbrshpnum_EB", Mbrshp_num);
		SW.EnterValue("SPGCentro_AffinityPoint_Bonusnum_EB","2007");
		SW.Click("SPGCentro_AffinityPoint_Submit_BT");
		SW.Wait(5);
		SW.EnterValue("SPGCentro_AffinityPoints_PropID_EB", "421");
		SW.EnterValue("SPGCentro_AffinityPoints_Points_EB", "100");
		SW.EnterValue("SPGCentro_AffinityPoints_Arvdate_EB", "01-Oct-2016");
		SW.EnterValue("SPGCentro_AffinityPoints_Dprtdate_EB","05-Oct-2016");
		SW.Click("SPGCentro_AffinityPoints_SubmitCon_BT");
		
		if(SW.CompareTextContained("Success message", "Transaction processed successfully", SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT")))
			Environment.loger.log(Level.INFO, "Transaction processed successfully");
		else
		{
			Environment.loger.log(Level.ERROR,"Transaction failed ");
			SW.FailCurrentTest("Validation fails in Transaction Processing");
		}
		
	}
	
	@AfterClass
	public void endTest(){
		SW.Click("CentroLogout_BT");
		Reporter.StopTest();
		
	}
	

}
