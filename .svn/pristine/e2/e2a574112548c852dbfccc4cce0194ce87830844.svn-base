package testscripts.sgrRegression;
/** Purpose		: Validate_Guest Reports_SPG Report_Microstrategy
 * TestCase Name: Validate_Guest Reports_SPG Report for Arriving Guest
 * 				  Validate_Guest Reports_SPG Report for In House Guest
 * 				  Validate_Guest Reports_SPG Report for Newly Enrolled Guest
 * Created By	: Sachin G
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

public class SGR_REG62_ValidateGuestSPGRprtMSTR_FrmArrivingInHouseAndEnrolling {
	CRM SW = new CRM();
	String ArrGuestName, ArrGuestSPGNumber, InHouseGstName, InHouseGstSPG, EnrolledGstName, EnrolledGstSPG;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void getArrivingGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.SwitchToFrame("SGRHome_GuestDashboard_FR");
		if(!SW.ObjectExists("SGRHome_ArrivingSPGGuestsEnrolled_LK")){
			Reporter.Write("Validate Availablility", "SPG Enrolled Guest should present", "SPG Enrolled Guests are not present", "FAIL");
		}
		SW.Click("SGRHome_ArrivingSPGGuestsEnrolled_LK");
		ArrGuestName= SW.GetText("SGRHome_FirstGuestInFilteredList_LK");
		SW.Click("SGRHome_FirstGuestInFilteredList_LK");
		ArrGuestSPGNumber = SW.GetText("SGRGuestProfile_SPGNumber_DT");
		ArrGuestSPGNumber=ArrGuestSPGNumber.substring(0, ArrGuestSPGNumber.indexOf(" "));
		Reporter.WriteLog(Level.INFO,"ArrGuestName  " + ArrGuestName);
		Reporter.WriteLog(Level.INFO,"ArrGuestSPGNumber  " + ArrGuestSPGNumber);
	}
	@Test(priority=2)
	public void getInhouseGuest(){
		SW.Click("SGRNavigation_Home_LK");
		SW.SwitchToFrame("SGRHome_GuestDashboard_FR");
		if(!SW.ObjectExists("SGRHome_InhouseSPGGuestsEnrolled_LK")){
			Reporter.Write("Validate Availablility", "SPG Enrolled Guest should present", "SPG Enrolled Guests are not present", "FAIL");
		}
		SW.Click("SGRHome_InhouseSPGGuestsEnrolled_LK");
		InHouseGstName= SW.GetText("SGRHome_FirstGuestInFilteredList_LK");
		SW.Click("SGRHome_FirstGuestInFilteredList_LK");
		InHouseGstSPG = SW.GetText("SGRGuestProfile_SPGNumber_DT");
		InHouseGstSPG=InHouseGstSPG.substring(0, InHouseGstSPG.indexOf(" "));
		Reporter.WriteLog(Level.INFO,"InHouseGstName  " + InHouseGstName);
		Reporter.WriteLog(Level.INFO,"InHouseGstSPG  " + InHouseGstSPG);
	}
	@Test(priority=3)
	public void getGuestByEnrolling(){
		SW.Click("SGRNavigation_Home_LK");
		SW.SwitchToFrame("SGRHome_GuestDashboard_FR");
		if(!SW.ObjectExists("SGRHome_SPGGuestsNotEnrolled_LK")){
			Reporter.Write("Validate Availablility", "SPG Enrolled Guest should present", "SPG Enrolled Guests are not present", "FAIL");
		}
		SW.Click("SGRHome_SPGGuestsNotEnrolled_LK");
		EnrolledGstName= SW.GetText("SGRHome_FirstGuestInNonSPGFilteredList_LK");
		SW.Click("SGRHome_FirstGuestInFilteredList_LK");
		SW.Click("SGRGuestProfile_EnrollSPG_LK");
		SW.SelectRadioButton("SGREnrollSPG_PrimaryPhone_RB");
		SW.DropDown_SelectByIndex("SGREnrollSPG_PhoneCountryCode_DD", 2);
		SW.EnterValue("SGREnrollSPG_PrimaryPhoneHome_EB", "9874563210");
		SW.DropDown_SelectByIndex("SGREnrollSPG_Country_DD", 3);
		SW.EnterValue("SGREnrollSPG_Address_EB", "ABS Town Automated");
		SW.EnterValue("SGREnrollSPG_City_EB", "Bangalore");
		SW.Click("SGREnrollSPG_Save_BT");
		EnrolledGstSPG = SW.GetText("SGRGuestProfile_SPGNumber_DT");
		EnrolledGstSPG=EnrolledGstSPG.substring(0, EnrolledGstSPG.indexOf(" "));
		Reporter.WriteLog(Level.INFO,"EnrolledGstName  " + EnrolledGstName);
		Reporter.WriteLog(Level.INFO,"EnrolledGstSPG  " + EnrolledGstSPG);
	}
	@Test(priority=4, dependsOnMethods="getArrivingGuest")
	public void ValidateSPGGuestInMSTR(){
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRReports_GuestReports_LK");
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "SPG Report");
		SW.WaitForPageload();
		SW.CheckBox("SGRReports_RunCachedReort_CB", "OFF");
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForPageload();
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Wait(30);
		String ReportText=SW.GetText("//div[@id='page']");
		SW.CompareTextContained("Validate SPG Number for Arriving guest", ArrGuestSPGNumber, ReportText);
		SW.CompareTextContained("Validate SPG Number for In House guest", InHouseGstSPG, ReportText);
		//Not comparing enrolled guest in MSTR as there is no real sync as of now
		//SW.CompareTextContained("Validate SPG Number for Enrolled guest", EnrolledGstSPG, ReportText); //TODO
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
