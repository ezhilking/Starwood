package testscripts.CSF;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
/* Purpose		: Validate on entering the SPG number in the SPG field present in the guest section of the Summary page for a CSV file
 * TestCase Name: REG12_ValidatingSPGMemberInSummaryPage.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG12_ValidatingSPGMemberInSummaryPage {
	CRM SW = new CRM();
	String CSFFileNo;
	String sSPGMember;
	String sLevel;
	String sStatus;
	String sLOS;
	String sNights;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	
	@Test(priority=0)
	public void ValidateSPGMember(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			 sSPGMember="42716962550";
			 sLevel="PLATINUM (test, Adrian)";
			 sStatus="ACTIVE";
			 sLOS="5";
			 sNights="170";
		}
		else if(Environment.getRunEnvironment().equalsIgnoreCase("STAGE")){
			 sSPGMember="50847105140";
			 sLevel="PLATINUM (Vaishali, Krishna)";
			 sStatus="ACTIVE";
			 sLOS="203";
			 sNights="76";
		}
		SW.EnterValue("CSFHome_Firstname_EB", "Fname"+SW.RandomString(3));
		SW.EnterValue("CSFHome_Lastaname_EB", "Lname"+SW.RandomString(3));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Customer Service");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFSummary_SPGNumber_EB", sSPGMember);
		SW.DropDown_SelectByIndex("CSFSummary_IntialContact_DD", 2);
		SW.Wait(5);
		if(SW.CompareText(sLevel, SW.GetText("CSFSummary_SPGLevel_DT")))
				Environment.loger.log(Level.INFO, "SPG Level Matched");
			else
			{
				Environment.loger.log(Level.ERROR,"SPG Level Not Matched");
				SW.FailCurrentTest("Validation fails in Level checking");
			}		
		if(SW.CompareText(sStatus, SW.GetText("CSFSummary_SPGStatus_DT")))
			Environment.loger.log(Level.INFO, "SPG Staus Matched");
		else
		{
			Environment.loger.log(Level.ERROR,"SPG Status Not Matched");
			SW.FailCurrentTest("Validation fails in Status checking");
		}	
		if(SW.CompareText(sLOS, SW.GetText("CSFSummary_SPGStay_DT")))
			Environment.loger.log(Level.INFO, "SPG Stay Matched");
		else
		{
			Environment.loger.log(Level.ERROR,"SPG Stays Not Matched");
			SW.FailCurrentTest("Validation fails in Stays checking");
		}	
		if(SW.CompareText(sNights, SW.GetText("CSFSummary_SPGNights_DT")))
			Environment.loger.log(Level.INFO, "SPG No of Nights Matched");
		
		else
		{
			Environment.loger.log(Level.ERROR,"SPG Nights Not Matched");
			SW.FailCurrentTest("Validation fails in Nights checking");
		}
		SW.ClickAndProceed("CSF_Cancel_BT");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.Wait(5);
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
