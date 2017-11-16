package testscripts.SPGLink;
/** Purpose		: This is to enroll member for design hotels under Design Enroller role
 * TestCase Name: DesignEnroller
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

public class SPGLink_REG39_DesignEnroller {
	CRM SW = new CRM();
	String address;

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGLINK);
		address = SW.TestData("SPGLinkAddress1");
	}

	@Test
	public void validateDesignEnroller() {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),
				SW.TestData("SPGLinkPassword"),
				SW.TestData("SPGLinkPropId_DesignHotel"));

		 //Change UserRole
		 SW.SPGLinkChangeUserRole("designEnroller");

		// Enroll SPG Member
		 SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLinkEnroll_SPGEnrollment_BT");
		SW.DropDown_SelectByValue("SPGLinkEnroll_Title_DD", "Ms");
		SW.EnterValue("SPGLinkEnroll_FirstName_EB",
				SW.TestData("SPGLinkFirstName"));
		SW.EnterValue("SPGLinkEnroll_LastName_EB",
				SW.TestData("SPGLinkLastName"));
		SW.DropDown_SelectByValue("SPGLinkEnroll_Contry_DD", "IN");
		SW.WaitForPageload();

		SW.EnterValue("SPGLinkEnroll_Address1_EB", address);
		SW.EnterValue("SPGLinkEnroll_State_EB", SW.TestData("SPGLinkState"));
		SW.EnterValue("SPGLinkEnroll_City_EB", SW.TestData("SPGLinkCity"));
		SW.EnterValue("SPGLinkEnroll_ZIP_EB", SW.TestData("SPGLinkZIP"));
		SW.SelectRadioButton("SPGLinkEnroll_Contact_RB");
		SW.DropDown_SelectByValue("SPGLinkEnroll_PhoneConuntry_DD", "IN");
		SW.EnterValue("SPGLinkEnroll_PhoneNumber_EB",
				SW.TestData("ContactPhoneNumber"));
		SW.EnterValue("SPGLinkEnroll_Email_EB",
				SW.TestData("SPGLinkEmailid"));
		
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
	 public void EndTest() {
	 Reporter.StopTest();
	 }


}
