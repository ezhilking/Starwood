package testscripts.CSF;
/** Purpose		: Validate the error message when file  has BRG Pending GCD and BRG Approved GCD is added with appropriate resolution- BRG
 * 				   
 * TestCase Name: Validate the error message when file  has BRG Pending GCD and BRG Approved GCD is added with appropriate resolution- BRG
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG27_ValidateErrorMsgBRGPendingGCDAndBRGApprovedGCDAdded {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId="1965";
	String compare;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void CSFCreateBRGFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			compare="Reservation/Best Rate Guarante/";
		}
		else if(Environment.getRunEnvironment().equalsIgnoreCase("STAGE")){
			compare="Reservation/Best Rate Guarantee/";
		}		
		SW.Click("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResConf_PropertyID_EB", sPropId);
		SW.EnterValue("CSFResConf_ArrivalDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -21, Calendar.DATE));
		SW.EnterValue("CSFResConf_ToDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd MMM yyyy"), "dd MMM yyyy", -17, Calendar.DATE));
		SW.Click("CSFResConf_Find_BT");
		SW.WaitTillElementToBeClickable("CSFResConf_ResultTable_LK");
		SW.Click("CSFResConf_ResultTable_LK");
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Best Rate Guarantee");
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
		SW.EnterValue("CSFSummary_StarwoodRate_EB", "100");
		SW.EnterValue("CSFSummary_CompetitorRate_EB", "100");
		SW.EnterValue("CSFSummary_CompetitorID_EB", "409");
		SW.EnterValue("CSFSummary_GuestQty_EB", "4");
		SW.EnterValue("CSFSummary_RoomQty_EB", "2");
		SW.EnterValue("CSFSummary_CompetitorWebId_EB", "WWW.abc.com");
		SW.DropDown_SelectByText("CSFSummary_StarwoodCurrency_DD", "USD");
		SW.DropDown_SelectByText("CSFSummary_CompetitorCurrency_DD", "USD");
		SW.Wait(3);
		SW.Click("CSFNavigation_Details_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("CSFDetail_Top10Sel_DD", 1);
		SW.DropDown_SelectByIndex("CSFDetails_Detail_DD", 1);
		String GCDText=compare+SW.DropDown_GetSelectedText("CSFDetails_Detail_DD");
		SW.DropDown_SelectByValue("CSFDetails_Detail_DD", "7759");//select Pending option
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		if(SW.CompareTextContained("GCD ADDED ", GCDText, SW.GetText("CSFDetail_AddGCD_WT")))
			Reporter.Write("Validate Added GCD", "Added GCD should present", "Added GCD is present", "PASS");
		else
			Reporter.Write("Validate Added GCD", "Added GCD should present", "Added GCD is not present", "FAIL");
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Pending");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(5);
		SW.HandleAlert(true);
	}

	@Test(priority=2, dependsOnMethods="CSFCreateBRGFile")
	public void ValidateErrorMessage(){
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		SW.Click("CSFHome_CSFNumber_LK");
		SW.Click("CSFNavigation_Details_LK");
		SW.DropDown_SelectByIndex("CSFDetails_Detail_DD", 1);
		SW.DropDown_SelectByValue("CSFDetails_Detail_DD", "7615");//select approved option
		SW.ClickAndProceed("CSFDetails_AddGCD_BN");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(5);
		SW.HandleAlert(true);
		if(SW.ObjectExists("//li[text()='CSF - You have finalized this BRG claim. Please move the file to the Closed status. ']")){
			Reporter.Write("Validate Error Message", "CSF - You have finalized this BRG claim. Please move the file to the Closed status. ", SW.GetText("//li[text()='CSF - You have finalized this BRG claim. Please move the file to the Closed status. ']"), "PASS");
		}else{
			Reporter.Write("Validate Error Message", "Error Message should display", "Error message is not displayed ", "FAIL");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Cancelled");

		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(5);
		SW.HandleAlert(true);

		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
