package testscripts.CSF;
/** Purpose		: Validate the Unreleased check requests tab when Check requests resolution is added to CSV file.
 * TestCase Name: Validate the Unreleased check requests tab when Check requests resolution is added to CSV file.
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 * Comments		: Need to provide Billing user ID as prerequisite 
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG30_ValidateUnreleasedCheckRequestsTabCSVFile {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId = "1965";
	String UserName;
	String Password;
	String BillingUserName;
	String BillingPassword;

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
		UserName=SW.TestData("CSFUsername");
		Password=SW.TestData("CSFPassword");
	}

	@Test(priority=0)
	public void CreateCSV(){
		SW.CSFLogin(UserName, Password);
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			BillingUserName="ttest4";
			BillingPassword="saratoga1";
		}
		else if(Environment.getRunEnvironment().equalsIgnoreCase("STAGE")){
			BillingUserName="ttest4";
			BillingPassword="saratoga1";
		}
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", SW.RandomString(5));
		SW.EnterValue("CSFHome_Lastaname_EB", SW.RandomString(5));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("PropertyID"));
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Customer Service");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.DropDown_SelectByText("CSFSummary_IntialContact_DD", "Email");
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", SW.RandomInteger(8));
		SW.EnterValue("CSFSummary_Email_EB", "test@accenture.com");
		SW.DropDown_SelectByText("CSFSummary_NoRESconf_DD", "No res conf# found");
		SW.Click("CSFNavigation_Details_LK");
		SW.SelectRadioButton("CSFDetails_Compliment_RB");
		SW.DropDown_SelectByIndex("CSFDetail_Top10Sel_DD", 1);
		String GCDText=SW.DropDown_GetSelectedText("CSFDetail_Top10Sel_DD");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		if(SW.CompareTextContained("GCD ADDED ", GCDText, SW.GetText("CSFDetail_AddGCD_WT")))
			Environment.loger.log(Level.INFO, "GCD has Added ");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);
		SW.Click("CSFHome_CSFNumber_LK");
		SW.Click("CSF_Resolution_LK");
		SW.WaitForPageload();
		if(SW.ObjectExists("CSFResolutionPage_Modify_BT"))
			SW.Click("CSFResolutionPage_Modify_BT");
		SW.DropDown_SelectByIndex("CSFResolution_Resolution_DD", 1);
		SW.WaitForPageload();
		SW.EnterValue("CSFResolution_Phone_EB", "9856474566");
		SW.EnterValue("CSFResolution_Email_EB", "abc@ac.com");
		SW.DropDown_SelectByText("CSFResolution_Comm_DD","Email");
		SW.DropDown_SelectByIndex("CSFResolution_Exception_DD", 2);
		SW.DropDown_SelectByText("CSFResolution_Charge_DD","GWG - CCS Will Pay");
		SW.DropDown_SelectByText("CSFResolution_Type_DD","Check Requested");
		SW.EnterValue("CSFResolution_Amount_EB", "50");
		SW.EnterValue("CSFResolution_PayableTo_EB", "30");	
		SW.EnterValue("CSFResolution_AddressLine_EB", "Ecospace");
		SW.EnterValue("CSFResolution_City_EB", "KARNATAKA");
		SW.EnterValue("CSFResolution_Country_EB", "US");
		SW.EnterValue("CSFResolution_State_EB", "CA");
		SW.EnterValue("CSFResolution_PayableTo_EB", "user");
		SW.EnterValue("CSFResolution_ZipCode_EB", "10001");
		SW.Click("CSFResolution_Add_BT");	
		String Resolutiontable=SW.GetText("CSFResolution_ResultTable_WT");
		if(SW.CompareTextContained("Compare Resolution Table", "GWG - CCS Will Pay", Resolutiontable))
			Reporter.Write("Validate Resolution ", "Resolution should be added successfully", "Resolution is added", "PASS");
		else
		{
			Reporter.Write("Validate Resolution ", "Resolution should be added successfully", "Resolution is not added", "FAIL");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		//make sure user role is billing
		SW.Click("CSFHome_Admin_LK");
		SW.Click("CSFAdmin_UserAdmin_LK");
		SW.EnterValue("CSFAdmin_NameFilter_EB", BillingUserName);
		SW.Click("CSFAdmin_Filter_BT");
		if(SW.CompareTextContained("billing", SW.GetText("CSFAdmin_userrole_DT")))
		{
			Environment.loger.log(Level.INFO, "Already the user in billing role");
		}
		else{
			SW.Click("CSFAdmin_userrole_DT");
			SW.DropDown_SelectByText("CSFAdmin_Role_DD", "Billing");
			SW.DropDown_SelectByIndex("CSFAdmin_CashPoint_DD", 3);
			SW.Click("CSFAdmin_Done_BT");
			SW.WaitTillElementToBeClickable("CSFAdmin_SaveChanges_BT");
			SW.Click("CSFAdmin_SaveChanges_BT");
		}

		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);

	}
	@Test(priority=3, dependsOnMethods="CreateCSV")
	public void ValidateForBillingUser(){

		SW.CSFLogin(BillingUserName, BillingPassword);
		SW.Click("CSFHomePage_Unreleased_LK");
		SW.WaitTillElementToBeClickable("//table[@id='csfsTBL']//td[text()='"+CSFFileNo+"']");
		if(SW.ObjectExists("//table[@id='csfsTBL']//td[text()='"+CSFFileNo+"']"))
			Reporter.Write("Checking the file in unreleased Request", "CSF file should display", "CSF File is shown in table" + CSFFileNo, "PASS");
		else
			Reporter.Write("Checking the file in unreleased Request", "CSF file should display", "CSF File is not shown in table"+CSFFileNo, "FAIL");

		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
