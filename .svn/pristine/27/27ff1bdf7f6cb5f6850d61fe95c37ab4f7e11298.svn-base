package testscripts.SPGLight;
/** Purpose		: This is to validate the member lookup role under SPGLight
 * TestCase Name: SPGLightMemberLookup
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

public class SPGLight_REG01_MemberLookup {
	
	CRM SW = new CRM();
	String Mbrshp_num;
	
	@BeforeClass
	public void startTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLIGHT);
	}

	
	@Test(priority=1)
	public void getDataSPGLightMemberLookup(){
		SW.EstablishConnection("QA3");
		String query="select * from freq_travel_extl_mbrshp where extl_mrktg_pgm_id='SVOO' and create_user_id='VEERMB' AND extl_mbrshp_lvl='Y'";
		Mbrshp_num=SW.GetColumnValues(query,"Mbrshp_num").get(0);
		System.out.println(Mbrshp_num);
	}
	
	@Test(priority=2,dependsOnMethods="getDataSPGLightMemberLookup")
	public void SPGLightMemberLookup(){
		SW.SPGLightLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		SW.Click("SPGLight_Home_Admin_BT");
		SW.Click("SPGLight_Home_Admin_SuperUser_BT");
		SW.DropDown_SelectByText("SPGLight_Home_SuperUser_DD", "vseRequester");
		SW.Click("SPGLight_Home_SuperUser_Submit_BT");
		SW.Click("SPGLight_MemberSearch_BT");
		SW.EnterValue("SPGLight_MemberSearch_Mbrshpnum_EB", Mbrshp_num);
		SW.Click("SPGLight_MemberSearch_Search_BT");
	
		
		if (SW.ObjectExists("SPGLight_MemberSearch_TxnReview_BT")){
			Environment.loger.log(Level.INFO, "Member Search Successfull");
		}
		else{
			Environment.loger.log(Level.ERROR, "Member Search fails");
			SW.FailCurrentTest("Member Search failed");
		}
		
	}
		
		
		
	@AfterClass
	public void endTest(){
		SW.Click("SPGLightLogout_BT");
		Reporter.StopTest();
	}

}

