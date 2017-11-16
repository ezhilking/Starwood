package testscripts.sgrRegression;
/** Purpose		: Validate Generating Guest report with Service Opportunity
 * TestCase Name: Validate Generating Guest report with Service Opportunity
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG57_GenerateGuestReportServiceOpportunityAndValidateData {
	CRM SW = new CRM();
	String ReservationNo,Situation;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateServiceOpportunityForGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
		SW.Wait(8);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		if(!SW.ObjectExists(FirstGuestLink)){
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		ReservationNo=SW.GetAttributeValue(FirstGuestLink, "href");
		ReservationNo=ReservationNo.substring(ReservationNo.indexOf("resConf=")+8,ReservationNo.indexOf("&roomSeq"));
		Environment.loger.log(Level.INFO, "Reservation Number Selected is :"+ReservationNo);
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("//input[@value='Create a New Service Opportunity']");
		SW.Click("SGRGuestProfile_ServiceOpportunity_BT");

	    Situation=SW.RandomString(10);
		String Action=SW.RandomString(10);
		String FollowUp=SW.RandomString(10);
		String Recovery=SW.RandomString(10);

		SW.EnterValue("SGRServiceOpportunity_Situation_EB", Situation);
		SW.EnterValue("SGRServiceOpportunity_Action_EB", Action);
		SW.EnterValue("SGRServiceOpportunity_FollowUp_EB", FollowUp);
		SW.EnterValue("SGRServiceOpportunity_Recovery_EB",Recovery );

		SW.DropDown_SelectByIndex("SGRServiceOpportunity_Visibility_DD", 0);
		SW.DropDown_SelectByIndex("SGRServiceOpportunity_Department_DD", 1);
		SW.Click("SGRServiceOpportunity_Save_BT");
		if(SW.ObjectExists("//td[contains(.,'"+Situation+"')]")){
			Environment.loger.log(Level.INFO, "Service Opportunity is created successfully and present in guest profile");
			Reporter.Write("validate Service Opportunity", "Created Service Opportunity Should Present", "Created Service Opportunity is Present", "PASS");
		}else{
			Reporter.Write("validate Service Opportunity", "Created v Should Present", "Created Service Opportunity is not Present", "FAIL");
		}
		SW.Wait(60);// Wait for 60 seconds to reflect in report 
	}
	
	@Test(priority=2, dependsOnMethods="CreateServiceOpportunityForGuest")
	public void ValidateInEscalationReport(){
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRHome_GuestReport_LK");  
		SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Service Opportunity Report");
		SW.WaitForPageload();
		SW.EnterValue("SGREventReports_StartDate_EB", SW.GetTimeStamp("dd-MMM-YYYY"));	
		SW.EnterValue("SGREventReports_EndDate_EB", SW.GetTimeStamp("dd-MMM-YYYY"));
		SW.Click("SGREventReports_GenerateReport_BN");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();
		SW.Wait(30);
		if(SW.ObjectExists("//span[contains(.,'"+ReservationNo+"')]")){
			List<String> Text=SW.GetAllText("//span[contains(.,'"+ReservationNo+"')]");
			SW.CompareTextContained("Validate Service Opportunity", Situation, Text.toString());
			Reporter.Write("Validate Guest Service Opportunity Report", "Created Service Opportunity Should present", "Created Service Opportunity is present", "PASS");
		}else{
			Reporter.Write("Validate Guest Service Opportunity  Report", "Created Service Opportunity Should present", "Created Service Opportunity is not present", "FAIL");
		}
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
