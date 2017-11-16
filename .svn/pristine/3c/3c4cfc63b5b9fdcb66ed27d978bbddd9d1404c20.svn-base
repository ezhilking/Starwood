package testscripts.CSF;
/** Purpose		: Validate the closed files in Review tab-CSV for CAED user role
 * TestCase Name: Validate the closed files in Review tab-CSV for CAED user role
 * Created By	: Sachin
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

public class REG28_ValidateClosedFilesInReviewTabCSV_CAEDUser {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId = "1965";
	String CEADUserName="ttest5";
	String CAEDPassword="saratoga1";

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority=0)
	public void CreateCSV(){
		SW.CSFLogin(CEADUserName, CAEDPassword);
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
		SW.Click("CSFResolutionPage_Modify_BT");
		SW.DropDown_SelectByIndex("CSFResolution_Resolution_DD", 1);
		SW.WaitForPageload();
		SW.EnterValue("CSFResolution_Phone_EB", "9856474566");
		SW.EnterValue("CSFResolution_Email_EB", "abc@ac.com");
		SW.DropDown_SelectByText("CSFResolution_Comm_DD","Email");
		SW.DropDown_SelectByIndex("CSFResolution_Exception_DD", 2);
		SW.DropDown_SelectByText("CSFResolution_Charge_DD","GWG - CCS Will Pay");
		SW.DropDown_SelectByText("CSFResolution_Type_DD","SPG Points");
		SW.EnterValue("CSFResolution_Amount_EB", "50");
		SW.Click("CSFResolution_Add_BT");	
		String Resolutiontable=SW.GetText("CSFResolution_ResultTable_WT");
		if(SW.CompareTextContained("Compare Resolution Table", "SPG Points 50 Points GWG - CCS Will Pay", Resolutiontable))
			Environment.loger.log(Level.INFO, "Resolution Data matched");
		else{
			Environment.loger.log(Level.ERROR,"Resolution Data not Matched");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		SW.Click("CSFHome_CSFNumber_LK");

		//Validate CSF file in Review tab
		SW.Click("CSFHomePage_home_LK");
		SW.Click("CSFHomePage_ReviewTab_LK");
		SW.WaitTillElementToBeVisible("//table[@id='csfsTBL']/tbody//td[10][text()='"+CSFFileNo+"']");
		if(SW.ObjectExists("//table[@id='csfsTBL']/tbody//td[10][text()='"+CSFFileNo+"']")){
			Reporter.Write("Validate File in Review tab", "File should be present ", "File is Present ", "PASS");
		}else
		{
			Reporter.Write("Validate File in Review tab", "File should be present ", "File is not Present ", "FAIL");
		}

		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
