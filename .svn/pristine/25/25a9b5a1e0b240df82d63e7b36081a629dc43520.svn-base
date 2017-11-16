package testscripts.sgrRegression;
/** Purpose		: Validate the closed COM file in the Closed tab in SGR
 * TestCase Name: Validate the closed COM file in the Closed tab in SGR
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

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG36_ValidateCOMfileInClosedTabInSGR {
	CRM SW = new CRM();
	String CSFFileNo;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority=1)
	public void CSFCreateFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));//Login to CSF Application
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", "Fname");
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname");
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("SGRPropertyID"));
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Commission");
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
		SW.EnterValue("CSFConfirmation_ArrivalDate_EB", SW.GetTimeStamp("dd MMM yyyy"));
		SW.EnterValue("CSFConfirmation_DepartureDate_EB", SW.GetTimeStamp("dd MMM yyyy"));
		SW.EnterValue("CSFConfirmation_ConfirmedRate_EB", "12324");
		SW.EnterValue("CSFConfirmation_NoOfRooms_EB", "2");
		SW.EnterValue("CSFConfirmation_RatePlanId1_EB", "asdas");
		
		SW.Wait(3);
		SW.Click("CSFNavigation_Details_LK");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("CSFDetail_Top10Sel_DD", 1);
		String GCDText=SW.DropDown_GetSelectedText("CSFDetail_Top10Sel_DD");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		if(SW.CompareTextContained("GCD ADDED ", GCDText, SW.GetText("CSFDetail_AddGCD_WT")))
			Environment.loger.log(Level.INFO, "GCD has Added ");
		else
		{
			Environment.loger.log(Level.ERROR,"GCD not Added");
			SW.FailCurrentTest("Validation fails in Adding GCD");
		}
		SW.DropDown_SelectByText("CSFDetails_OnExitSetStatus_DD", "Hotel New");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);

	}

	@Test(priority=2, dependsOnMethods ="CSFCreateFile")
	public void ValidateInSGR(){
		SW.WaitTillElementToBeClickable("CSFLogin_Password_BN");//To avoid immediate navigate to SGRUURL, It will wait till CSF application log out successfully.
		SW.NavigateTo(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));//Login to SGR application
		SW.Click("SGRNavigation_CSFS_LK");
		SW.DoubleClick("//*[text()='"+CSFFileNo+" "+"']/..");
		SW.WaitForAppLoad();
		SW.EnterValue("SGRViewCSF_PropertyComment_EB", "Comments-"+SW.RandomString(5));
		SW.ClickAndProceed("SGRViewCSF_Save_BT");
		SW.HandleAlert(true);
		SW.WaitForAppLoad();
		SW.CheckBox("SGRViewCSF_NoCompensationRequired_CB", "ON");
		SW.DropDown_SelectByText("SGRViewCSF_CurrentStatus_DD", "Closed");
		
		SW.EnterValue("SGRViewCSF_PropertyComment_EB", "Comments-"+SW.RandomString(5));
		
		SW.DropDown_SelectByText("SGRViewCSF_Resolution_DD", "No Reservation");
		
		SW.DropDown_SelectByText("SGRViewCSF_GuestCommunication_DD", "Email");
		SW.ClickAndProceed("SGRViewCSF_Save_BT");
		SW.Wait(8);
		SW.HandleAlert(true);
		//Validate closed file in closed tab inside csf page
		SW.Click("SGRNavigation_CSFS_LK");
		SW.Click("SGRCSF_ClosedTab_LK");
		SW.Wait(10);
		if(SW.ObjectExists("//td[text()='"+CSFFileNo+" ']")){
			Environment.loger.log(Level.INFO, "Closed CSF-COM File is present in the closed tab");
			SW.GetScreenshot("ClosedCSVFile");
		}else{
			Environment.loger.log(Level.INFO, "Closed CSF-COM File is not present in the closed tab");
			SW.FailCurrentTest("Closed CSF-CSV File is not present in the closed tab");
		}
		SW.Click("SGR_Logout_LK");
	}

	@Test(priority=3, dependsOnMethods ="CSFCreateFile")
	public void ValidateInCSF(){
		SW.NavigateTo(Environment.CSFURL);
		SW.WaitTillPresenceOfElementLocated("CSFLogin_Password_BN");
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		SW.EnterValue("CSF_EnterCSFID_EB", CSFFileNo);
		SW.Click("CSF_EnterCSFIDGo_BN");
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.CompareText("FinalStatus_DD","Closed", SW.DropDown_GetSelectedText("CSF_StatusCode_DD"));
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
		
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
