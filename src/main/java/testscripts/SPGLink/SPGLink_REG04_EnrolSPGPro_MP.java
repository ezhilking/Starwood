package testscripts.SPGLink;
/** Purpose		: This is to Enroll SPGPro Meeting Planner member in SPG Link 2.0 under SPG Pro User Role
 * TestCase Name: EnrolSPGProEnrollment_MP
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

public class SPGLink_REG04_EnrolSPGPro_MP {

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
		//SPGPro
		SW.SelectRadioButton("SPGLinkEnroll_BookMeeting_RB");
		SW.SelectRadioButton("SPGLinkEnroll_MeetingPlanner_RB");
		SW.SelectRadioButton("SPGLinkEnroll_ThirdPartyComp_RB");
		SW.DropDown_SelectByValue("SPGLinkEnroll_ThirdPartyCompName_DD", "02");
		String ThirdPartyCompName = SW
				.DropDown_GetSelectedText("SPGLinkEnroll_ThirdPartyCompName_DD");
		SW.DropDown_SelectByValue("SPGLinkEnroll_MeetingsBookedPerYear_DD",
				"02");

		String MeetingsBookedPerYear = SW
				.DropDown_GetSelectedText("SPGLinkEnroll_MeetingsBookedPerYear_DD");
		SW.DropDown_SelectByValue("SPGLinkEnroll_RoomNightsBookedPerYear_DD",
				"03");
		String RoomNightsBookedPerYear = SW
				.DropDown_GetSelectedText("SPGLinkEnroll_RoomNightsBookedPerYear_DD");
		SW.Click("SPGLinkEnroll_SubmitButton_BT");
		if (SW.ObjectExists("SPGLinkAdmin_RoleChangeAlert_DT")) {

			String Enrollnum = SW.GetText("SPGLinkAdmin_RoleChangeAlert_DT");
			Enrollnum = Enrollnum.substring(Enrollnum.lastIndexOf(" ") + 1,
					Enrollnum.indexOf(".")).trim();
			Environment.loger.log(Level.INFO, "Meeting Planner Enrolled Successfully!!"
					+ Enrollnum);

		} else {
			Environment.loger.log(Level.ERROR, "Enrollment fails");
			SW.FailCurrentTest("Validation fails for Meeting Planner enrollment");
		}


		if (SW.CompareTextContained(ThirdPartyCompName, SW.GetText("SPGLinkEnrollConf_ThirdPartyComp_DT")))
			Environment.loger.log(Level.INFO, "Third Party Company matched");

		else {
			Environment.loger.log(Level.ERROR, "Third Party Company not Matched");
			SW.FailCurrentTest("Validation fails Third Party Company");

		}
		if (SW.CompareTextContained(MeetingsBookedPerYear, SW.GetText("SPGLinkEnrollConf_MeetingBookedPerYear_DT")))
			Environment.loger.log(Level.INFO, "MeetingsBookedPerYear matched");

		else {
			Environment.loger.log(Level.ERROR, "MeetingsBookedPerYear not Matched");
			SW.FailCurrentTest("Validation fails for MeetingsBookedPerYear");

		}
		if (SW.CompareTextContained(RoomNightsBookedPerYear, SW.GetText("SPGLinkEnrollConf_RoomNightsBookedPerYear_DT")))
			Environment.loger.log(Level.INFO, "RoomNightsBookedPerYear matched");

		else {
			Environment.loger.log(Level.ERROR, "RoomNightsBookedPerYear not Matched");
			SW.FailCurrentTest("Validation fails for RoomNightsBookedPerYear");

		}
	}

	@AfterClass
	public void EndTest() {
		SW.Click("SPGLink_LogOut_BT");
		Reporter.StopTest();
	}

}
