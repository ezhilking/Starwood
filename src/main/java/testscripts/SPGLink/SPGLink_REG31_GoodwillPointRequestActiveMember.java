package testscripts.SPGLink;
/** Purpose		: This is to Request Goodwill starpoints for Active member in SPGLink 2.0 under Misc Starpoints role 
 * TestCase Name: GoodwillPointRequestActiveMember
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


public class SPGLink_REG31_GoodwillPointRequestActiveMember {
	CRM SW = new CRM();
	String Mbrshp_num;
	int ConfNum = SW.RandomInteger(6);
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	
	}
	@Test(priority=1)
	public void getDataGoodwillPointRequestActiveMember() {
		SW.EstablishConnection("QA3");
		String query = "select mbrshp_num from freq_Travel_mbrshp where mbrshp_lvl='A' and mbrshp_sts='A' and join_Date > (sysdate-6) and src_cd='WEBA'";
		Mbrshp_num=SW.GetColumnValues(query, "MBRSHP_NUM").get(3);
		System.out.println(Mbrshp_num);
	}
	
	@Test(priority=2,dependsOnMethods="getDataGoodwillPointRequestActiveMember")
	public void GoodwillPointRequestActiveMember() {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("Misc Starpoints"); //Changes UserRole
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLink_StarPointRequest_BT");
		SW.EnterValue("SPGLink_OtherStarPoint_Mbrshpnum_EB", Mbrshp_num);
		SW.Click("SPGLink_OtherStarPoint_AssociatedStay_CB");
		SW.EnterValue("SPGLink_OtherStarPoints_ConfNum_EB", ConfNum);
		SW.EnterValue("SPGLink_OtherStarPoints_Arvdate_EB","01-Nov_2016");
		SW.EnterValue("SPGLink_OtherStarPoints_Dprtdate_EB","03-Nov-2016");
		SW.DropDown_SelectByText("SPGLink_OtherStarPoint_StarPointsRequestReason_DD", "2061: GOODWILL BY HOTEL THRU LINK (A");
		SW.EnterValue("SPGLink_OtherStarPoints_StarPoints_EB","1000");
		SW.Click("SPGLink_OtherStarPoints_ConfNum_EB");
		SW.NormalClick("SPGLink_OtherStarPoints_Submit_BT");
		SW.Wait(5);
		SW.HandleAlert(true);
		
		/* Active member request for Goodwill points should be successful */
		if (SW.ObjectExists("SPGLinkEventPosting_Success_DT")) {
			Environment.loger.log(Level.INFO, "Request  has been saved successfully");
			System.out.println("Request saved successfull");
		} else {
			String ErrorMsg = SW.GetText("SPGLink_OtherStarPoints_Err_DT");
			Environment.loger.log(Level.ERROR, "Error in Request Creation" + ErrorMsg);
			System.out.println("Request has some error");
		}
		
	}
	
	 @AfterClass
	 public void EndTest() {
		 SW.Click("SPGLinkLogout_BT");
	 Reporter.StopTest();
	
	 }
}
