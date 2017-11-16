package testscripts.SPGLink;
/** Purpose		: This is to Enroll SPGPro Travel Pro member in SPG Link 2.0 under SPG Pro User Role
 * TestCase Name: EnrolSPGProEnrollment_TP
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

public class SPGLink_REG05_EnrolSPGPro_TP {

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
	public void SPGProEnrollment() {
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),
				SW.TestData("SPGLinkPassword"),
				SW.TestData("SPGLinkPropId"));

		//Change UserRole
		SW.SPGLinkChangeUserRole("SPG Pro");

		// Enroll SPGPro MP Member
		SW.Click("SPGLInkHome_SPGProEnroll_BT");
		SW.DropDown_SelectByValue("SPGLinkEnroll_Title_DD", "Mr");
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


		SW.SelectRadioButton("SPGLinkEnroll_BookMeetingNo_RB");
		SW.SelectRadioButton("SPGLinkEnroll_BookBehalfOfOthers_RB");
		SW.SelectRadioButton("SPGLinkEnroll_TravelProfessional_RB");
		SW.EnterValue("SPGLinkEnroll_AccreditationNumber_EB", SW.TestData("AccreditationNumber"));
		SW.SelectRadioButton("SPGLinkEnroll_ConsortiaList_RB");
		SW.DropDown_SelectByValue("SPGLinkEnroll_ConsortiaName_DD", "02");
		SW.DropDown_SelectByValue("SPGLinkEnroll_TP_CustomerBookedPerYear_DD", "02");
		SW.DropDown_SelectByValue("SPGLinkEnroll_TP_RoomNightsBookedPerYear_DD", "02");
		SW.SelectRadioButton("SPGLinkEnroll_TP_TandC_RB");

		SW.Click("SPGLinkEnroll_SubmitButton_BT");
		if (SW.ObjectExists("SPGLinkAdmin_RoleChangeAlert_DT")) {

			String Enrollnum = SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT");
			Enrollnum = Enrollnum.substring(Enrollnum.lastIndexOf(" ") + 1,
					Enrollnum.indexOf(".")).trim();
			Environment.loger.log(Level.INFO, "Travel Professional mbr Enrolled Successfully!!"
					+ Enrollnum);

		} else {
			Environment.loger.log(Level.ERROR, "Enrollment fails");
			SW.FailCurrentTest("Validation fails for Travel Professional enrollment");

		}
	}

	@AfterClass
	public void EndTest() {
		SW.Click("SPGLink_LogOut_BT");
		Reporter.StopTest();
	}

}
