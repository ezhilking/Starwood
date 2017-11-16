package testscripts.CEM_AMB;
/** Purpose		: Validate Request section creating a CSV file with Hotel takeover Status from CSF 
 * TestCase Name: Validate Request section creating a CSV file with Hotel takeover Status from CSF
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;





/** Purpose		: Validate Create Activity functionality 
 * TestCase Name: Validate Create Activity functionality 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class CEM_REG_06_ValidateRequestSectionCSVfromCSF {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String ResNum;
	String CSFFileNo;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");

	}
	@Test(priority=1)
	public void CreateCSVFromCSF(){

		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(10);
		SW.RunJavaScript("scroll(0,600)");
		SW.Click("CEMAMBMyGuests_Reservations_LK");
		SW.Wait(10);
		ResNum=SW.GetText("CEMAMBReservations_FirstGuestReservation_LK");
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		SW.NavigateTo(Environment.CSFURL);
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		SW.EnterValue("CEMHome_ResConfNum_TB", ResNum+Keys.ENTER);
		SW.WaitForPageload();
		SW.Wait(5);
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.NormalClick("CSFReservationPage_GuestYes_RB");
		SW.DropDown_SelectByText("CSFSummary_IntialContact_DD", "Email");
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "12345");
		SW.EnterValue("CSFSummary_Email_EB", "test@accenture.com");
		SW.DropDown_SelectByText("CSFNewCSFPage_EmailStatus_DD", "Available");

		//SW.DropDown_SelectByText("CSFSummary_NoRESconf_DD", "No res conf# found");
		SW.Click("CSFNavigation_Details_LK");
		SW.EnterValue("CSFDetails_LookUp_EB", "hotel");
		SW.Click("CSFAddGCD_LookUp_LK");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Cancelled");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Created CSV file in CSF successfully-- "+CSFFileNo);
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);

	}
	@Test(priority=2, dependsOnMethods="CreateCSVFromCSF")
	public void ValidateInRequestSection(){
		SW.NavigateTo(Environment.CEM_AMBURL);
		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(10);
		SW.RunJavaScript("scroll(0,600)");
		SW.Click("CEMAMBMyGuests_Requests_LK");
		SW.Wait(5);
		if(SW.ObjectExists("//table[contains(@id,'reqData_')]//td[2][contains(.,'"+CSFFileNo+"')]")){
			Environment.loger.log(Level.INFO, "Created Request is present in the Request section");
		}else{
			Environment.loger.log(Level.ERROR, "Created Request is not present in the Request section");
			SW.FailCurrentTest("Created Request is not present in the Request section");
		}
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
