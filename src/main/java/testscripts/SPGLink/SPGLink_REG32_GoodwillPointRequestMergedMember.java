package testscripts.SPGLink;
/** Purpose		: This is to Request Goodwill starpoints for Merged member in SPGLink 2.0 under Misc Starpoints role 
 * TestCase Name: GoodwillPointRequestMergedMember
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

public class SPGLink_REG32_GoodwillPointRequestMergedMember {
    CRM SW = new CRM();
	String Mbrshp_num;
	int ConfNum = SW.RandomInteger(4);
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
		
	}
	@Test(priority=1)
	public void getDataGoodwillPointRequestMergedMember() {
		SW.EstablishConnection("QA3");
		String query = "select * from freq_travel_mbrshp where mbrshp_sts='M' and mbrshp_lvl='A'and src_cd='1AA' and join_date='20-MAR-04'and mail_sts_cd='A'and stmt_mbrshp_lvl='G'";
		Mbrshp_num=SW.GetColumnValues(query, "MBRSHP_NUM").get(1);
		System.out.println(Mbrshp_num);
	}
	
	@Test(priority=2,dependsOnMethods="getDataGoodwillPointRequestMergedMember")
	public void GoodwillPointRequestMergedMember() {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("Misc Starpoints"); //Changes UserRole
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLink_StarPointRequest_BT");
		SW.EnterValue("SPGLink_OtherStarPoint_Mbrshpnum_EB", Mbrshp_num);//Application will notify with Active membership number
		SW.CheckBox("SPGLink_OtherStarPoint_AssociatedStay_CB","ON");
		SW.Wait(5);
		SW.HandleAlert(true);
		SW.EnterValue("SPGLink_OtherStarPoints_ConfNum_EB", ConfNum);
		SW.EnterValue("SPGLink_OtherStarPoints_Arvdate_EB","01-Nov_2016");
		SW.EnterValue("SPGLink_OtherStarPoints_Dprtdate_EB","03-Nov-2016");
		SW.DropDown_SelectByText("SPGLink_OtherStarPoint_StarPointsRequestReason_DD", "2061: GOODWILL BY HOTEL THRU LINK (A");
		SW.EnterValue("SPGLink_OtherStarPoints_StarPoints_EB","1000");
		SW.Click("SPGLink_OtherStarPoints_ConfNum_EB");
		SW.ClickAndProceed("SPGLink_OtherStarPoints_Submit_BT");
		SW.HandleAlert(true);
		
		/* If merged member requests for Goodwill points it reflects in Active members account */
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
