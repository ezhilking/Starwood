package testscripts.SPGCentro;
/** Purpose		: This is to validate the member to register for the promo under centro
 * TestCase Name: PromoReg
 * Created By	: Indushree Lokesh
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SPGCentro_REG05_PromoReg {
	CRM SW = new CRM();
	String Mbrshp_num;
	
	@BeforeClass
	public void startTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGCENTRO);
	}
	
	@Test(priority=1)
	public void getDataPromoReg(){
		SW.EstablishConnection("QA3");
		String query="select * from freq_travel_mbrshp where mbrshp_lvl='A' and mbrshp_sts='A' and src_cd='GMAL' AND join_date='11=NOV-98'AND enroll_loc_id='0' and credit_card_opt_ind='N'";
		Mbrshp_num=SW.GetColumnValues(query, "Mbrshp_num").get(1);
		System.out.println(Mbrshp_num);
	}
	
	@Test(priority=2,dependsOnMethods="getDataPromoReg")
	public void PromoReg(){
		SW.SPGCentroLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		SW.Click("SPGCentro_PromoReg_Home_BT");
		SW.EnterValue("SPGCentro_PromoReg_Mbrshpnum_EB", Mbrshp_num);
		SW.Click("SPGCentro_PromoReg_Mbrshpnum_submit_BT");
		SW.Click("SPGCentro_PromoReg_Reg_BT");
		SW.Wait(10);
	}	
		
	@AfterClass
	public void endTest(){
		SW.Click("CentroLogout_BT");
		Reporter.StopTest();
	}

}
