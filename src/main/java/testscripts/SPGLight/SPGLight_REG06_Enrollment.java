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

public class SPGLight_REG06_Enrollment {
	CRM SW = new CRM();
	
	@BeforeClass
	public void startTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLIGHT);
	}
	
	@Test
	public void validate(){
		SW.SPGLightLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		SW.Click("SPGLight_Home_Admin_BT");
		SW.Click("SPGLight_Home_Admin_SuperUser_BT");
		SW.DropDown_SelectByText("SPGLight_Home_SuperUser_DD", "abovePropertySeller");
		SW.Click("SPGLight_Home_SuperUser_Submit_BT");
		SW.Click("SPGLight_Enroll_Home_BT");
		SW.DropDown_SelectByText("SPGLight_Enroll_Title_DD", "MS");
		SW.EnterValue("SPGLight_Enroll_Fname_EB", SW.TestData("SPGLinkFirstName"));
		SW.EnterValue("SPGLight_Enroll_Lname_EB", SW.TestData("SPGLinkLastName"));
		SW.DropDown_SelectByText("SPGLight_Enroll_Country_DD", "India : IN" );
		SW.Wait(5);
		SW.EnterValue("SPGLight_Enroll_CompInfo_EB", "Benz");
		SW.EnterValue("SPGLight_Enroll_Addr1_EB", SW.TestData("SPGLinkAddress1"));
		SW.EnterValue("SPGLight_Enroll_Addr2_EB", SW.TestData("SPGLinkAddress1"));
		SW.EnterValue("SPGLight_Enroll_Statecd_EB",SW.TestData("SPGLinkState"));
		SW.EnterValue("SPGLight_Enroll_City_EB",SW.TestData("SPGLinkCity"));
		SW.EnterValue("SPGLight_Enroll_ZipCD_EB", SW.TestData("SPGLinkZIP"));
		SW.CheckBox("SPGLight_Enroll_HomeMobNum_CB", "ON");
		SW.DropDown_SelectByText("SPGLight_Enroll_HomeMobNumCD_DD", "India : IN");
		SW.EnterValue("SPGLight_Enroll_HomeMobileNum_EB",SW.TestData("ContactPhoneNumber"));
		SW.EnterValue("SPGLight_Enroll_Email_EB", SW.TestData("SPGLinkEmailid"));
		SW.CheckBox("SPGLight_Enroll_EmailPref_CB", "ON");
		SW.Click("SPGLinkEnroll_SubmitButton_BT");
		if (SW.ObjectExists("SPGLinkAdmin_RoleChangeAlert_DT")) {

			String Enrollnum = SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT");
			Enrollnum = Enrollnum.substring(Enrollnum.lastIndexOf(" ") + 1,
					Enrollnum.indexOf(".")).trim();
			Environment.loger.log(Level.INFO, "SPG mbr Enrolled Successfully!!"
					+ Enrollnum);

		} else {
			Environment.loger.log(Level.ERROR, "Enrollment fails");
			SW.FailCurrentTest("Validation fails for SPG enrollment");
		
		}
	}
	
	@AfterClass
	public void endTest(){
		SW.Click("SPGLightLogout_BT");
		Reporter.StopTest();
	}
	
}
