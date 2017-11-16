package testscripts.CSF;
/** Purpose		: Create a Best Rate Guarantee - BRG File
 * 				  Verify the Error message when BRG approved GCD is selected and the status of the file is pending 
 * TestCase Name: REG06_CreateBRGFile.java
 * Created By	: Sharmila Begam
 * Modified By	: Sachin
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG06_CreateBRGFile {
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
		SW.DropDown_SelectByValue("CSFDetails_Detail_DD", "7615");//select approved option
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
		SW.Wait(10);
		SW.Click("CSFHome_CSFNumber_LK");
		SW.Click("CSF_Resolution_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("CSFResolution_Resolution_DD", 1);
		SW.EnterValue("CSFResolution_Phone_EB", "9856474566");
		SW.EnterValue("CSFResolution_Email_EB", "abc@ac.com");
		SW.DropDown_SelectByText("CSFResolution_Comm_DD","Email");
		SW.CheckBox("CSFResolution_CompensationInd_CB", "OFF");
		SW.DropDown_SelectByText("CSFResolution_Charge_DD","GWG - CCS Will Pay");
		SW.Wait(3);
		//Verify the Error message when BRG approved GCD is selected and the status of the file is pending 
		SW.DropDown_SelectByText("CSFResolution_Type_DD","Credit - Hotel Authorization Form");
		SW.EnterValue("CSFResolution_AmountHotelAuth_EB", "100");
		SW.EnterValue("CSFResolution_HotelContactName_EB", "Sample name");
		String EmailAddress=SW.RandomString(5)+"@abc.com";
		SW.EnterValue("CSFResolution_HotelContactEmail_EB", EmailAddress);
		SW.EnterValue("CSFResolution_ExplanationOfEvent_EB", "Sample Explanation");
		
		SW.Click("CSFResolution_AddandSend_BT");	
		SW.Wait(5);
		String Resolutiontable=SW.GetText("CSFResolution_ResultTable_WT");
		if(SW.CompareTextContained("Compare Resolution Table", "Credit - Hotel Authorization Form", Resolutiontable))
			Environment.loger.log(Level.INFO, "Resolution Data matched");
		else
		{
			Environment.loger.log(Level.ERROR,"Resolution Data not Matched");
			SW.FailCurrentTest("Validation fails in Resolution Page");
		}
		
		SW.DropDown_SelectByText("CSF_StatusCode_DD", "Pending");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		String ErrorMessage= SW.GetText("//form[@id='viewCsf']//li");
		if(SW.CompareTextContained("Compare Resolution Table", "CSF - The GCD-Status combination of BRG Approved or BRG Denied and Pending status is invalid. If you have approved or denied the claim please enter the appropriate resolution and set status to Closed. If the claim is approved pending the guest's confirmation number please ensure the ONLY GCD on the file is BRG Pending and status is set to Pending.", ErrorMessage))
			Environment.loger.log(Level.INFO, "Error message is displayed for pending file ");
		else
		{
			Environment.loger.log(Level.ERROR,"Error message is not displayed for the pending file ");
			SW.FailCurrentTest("Error message is not displayed for the pending file ");
		}
		
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Closed");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(5);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);
		SW.Click("CSFHome_CSFNumber_LK");
		if(SW.CompareTextContained("Closed", SW.GetText("CSF_Status_DT")))
			Environment.loger.log(Level.INFO, "Status has changed to Closed Sucessfully!!! ");
		else
		{
			Environment.loger.log(Level.ERROR,"Status not Changed");
			SW.FailCurrentTest("Validation fails in checking Status");
		}
		SW.Click("CSFSummary_CSFHistoryView_LK");
		SW.Wait(5);
		if(SW.ObjectExists("//table[@id='evts']//tr//td[contains(.,'"+EmailAddress+"')]//..//td[text()='Sent']")){
			Environment.loger.log(Level.INFO, "CSF File staus is present in the csf history section");
		}
		else{
			Environment.loger.log(Level.ERROR,"CSF File status is not present in the CSF History section");
			SW.FailCurrentTest("CSF File status is not present in the CSF History section");
		}
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
