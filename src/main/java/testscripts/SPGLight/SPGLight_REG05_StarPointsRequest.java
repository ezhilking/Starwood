package testscripts.SPGLight;
/** Purpose		: This is to validate the member lookup role under SPGLight
 * TestCase Name: SPGLightMemberLookup
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

public class SPGLight_REG05_StarPointsRequest {
	CRM SW = new CRM();
	String Mbrshp_num;
	
	@BeforeClass
	public void starTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLIGHT);
	}
	
	@Test(priority=1)
	public void getData(){
		SW.EstablishConnection("QA3");
		String query="select * from freq_travel_extl_mbrshp where extl_mrktg_pgm_id='SVOO'and create_user_id='ABHATTAC'";
		Mbrshp_num=SW.GetColumnValues(query, "Mbrshp_num").get(0);
		System.out.println(Mbrshp_num);
	}
	
	@Test(priority=2)
	public void validate(){
		SW.SPGLightLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		/*SW.Click("SPGLight_MainMenu_ST");*/
		SW.Click("SPGLight_Home_Admin_BT");
		SW.Click("SPGLight_Home_Admin_SuperUser_BT");
		SW.DropDown_SelectByText("SPGLight_Home_SuperUser_DD", "vseRequester");
		SW.Click("SPGLight_Home_SuperUser_Submit_BT");
		SW.Click("SPGLight_Starpoint_Request_Home_BT");
		SW.Click("SPGLight_Starpoint_Request_Create_BT");
		SW.SwitchToFrame("SPGLight_Starpoint_frame_FR");
		SW.EnterValue("SPGLight_Starpoint_Request_Mbrshpnum_EB", Mbrshp_num);
		SW.EnterValue("SPGLight_Starpoint_Request_Bonusnum_EB", "9320");
		SW.Click("SPGLight_Starpoint_Request_Mbrshpnum_EB");
		SW.WaitTillElementToBeClickable("SPGLight_Starpoint_Request_Propid_EB");
		SW.EnterValue("SPGLight_Starpoint_Request_Propid_EB", "130");
		SW.WaitTillElementToBeClickable("SPGLight_Starpoint_Request_Starpoints_EB");
		SW.EnterValue("SPGLight_Starpoint_Request_Starpoints_EB", "100");
		SW.Click("SPGLight_Starpoint_Request_Propid_EB");
		SW.WaitTillElementToBeClickable("SPGLight_Starpoint_Request_comment_EB");
		SW.Click("SPGLight_Starpoint_Request_comment_EB");
		SW.NormalClick("SPGLight_Starpoint_Request_Submit_BT");
		SW.HandleAlert(true);
	}
	
	@AfterClass
	public void endTest(){
		SW.Click("SPGLightLogout_BT");
	Reporter.StopTest();
	}
	
}
