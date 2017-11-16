package testscripts.CSF;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
/* Purpose		: Create a Starwood Vacation Ownership - SVO file
 * TestCase Name: REG05_CreateSVOFile.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG05_CreateSVOFile {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId="1965";
	WebElement ActionObject;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void CSFCreateSVOFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		SW.Click("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResConf_PropertyID_EB", sPropId);
		SW.EnterValue("CSFResConf_ArrivalDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -21, Calendar.DATE));
		SW.EnterValue("CSFResConf_ToDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -17, Calendar.DATE));
		SW.Click("CSFResConf_Find_BT");
		SW.WaitTillElementToBeClickable("CSFResConf_ResultTable_LK");
		SW.Click("CSFResConf_ResultTable_LK");
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Starwood Vacation Ownership");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", "Fname"+SW.RandomString(3));
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname"+SW.RandomString(3));
		
		SW.DropDown_SelectByIndex("CSFSummary_IntialContact_DD", 2);
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", "98574612");
		SW.EnterValue("CSFSummary_Email_EB", "test@accenture.com");
		SW.DropDown_SelectByText("CSFSummary_EmailStatus_DD", "Available");
		SW.EnterValue("CSFSummary_Country_EB", "US");
		SW.Wait(3);
		SW.Click("CSFNavigation_Details_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("CSFDetail_Top10Sel_DD", 1);
		SW.DropDown_SelectByIndex("CSFDetails_Detail_DD", 1);
		String GCDText="Starwood Vacations/SVO/"+SW.DropDown_GetSelectedText("CSFDetails_Detail_DD");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		if(SW.CompareTextContained("GCD ADDED ", GCDText, SW.GetText("CSFDetail_AddGCD_WT")))
			Environment.loger.log(Level.INFO, "GCD has Added ");
		else
		{
			Environment.loger.log(Level.ERROR,"GCD not Added");
			SW.FailCurrentTest("Validation fails in Adding GCD");
		}
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);
		SW.Click("CSFHome_CSFNumber_LK");
		SW.Click("CSF_Resolution_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("CSFResolution_Resolution_DD", 1);
		SW.EnterValue("CSFResolution_Phone_EB", "9856474566");
		SW.EnterValue("CSFResolution_Email_EB", "abc@ac.com");
		SW.DropDown_SelectByText("CSFResolution_Comm_DD","Email");
		SW.DropDown_SelectByText("CSFResolution_Charge_DD","GWG - CCS Will Pay");
		SW.DropDown_SelectByText("CSFResolution_Type_DD","SPG Points");
		SW.EnterValue("CSFResolution_Amount_EB", "50");
		SW.Click("CSFResolution_Add_BT");	
		String Resolutiontable=SW.GetText("CSFResolution_ResultTable_WT");
		if(SW.CompareTextContained("Compare Resolution Table", "SPG Points 50 Points GWG - CCS Will Pay", Resolutiontable))
			Environment.loger.log(Level.INFO, "Resolution Data matched");
		else
		{
			Environment.loger.log(Level.ERROR,"Resolution Data not Matched");
			SW.FailCurrentTest("Validation fails in Resolution Page");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		SW.Click("CSFHome_CSFNumber_LK");
		if(SW.CompareTextContained("Closed", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to Closed Sucessfully!!! ");
		else
		{
			Environment.loger.log(Level.ERROR,"Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
