package testscripts.sgrRegression;
/* Purpose		: Validation of Service Opportunity  tab in Master Arrival Report.
 * TestCase Name: This test case is to validate that service opportunities that are created in the past for the 
 * guests who are arriving today or the next day will  display in the service opportunity tab
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

public class SGR_REG17_Validate_Service_Opport_in_MSTR {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	String ReservationNo;
	@Test
	public void Validate_Service_Opport_in_MSTR(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1513"); // Select property which has guests
		SW.Wait(8);

		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";

		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
		}

		ReservationNo=SW.GetAttributeValue(FirstGuestLink, "href");
		ReservationNo=ReservationNo.substring(ReservationNo.indexOf("resConf=")+8,ReservationNo.indexOf("&roomSeq"));
		System.out.println(ReservationNo);
		Environment.loger.log(Level.INFO, "Reservation Number Selected is :"+ReservationNo);
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("//input[@value='Create a New Service Opportunity']");
		SW.Click("SGRGuestProfile_ServiceOpportunity_BT");

		String Situation=SW.RandomString(10);
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
		SW.WaitTillElementToBeClickable("SGRNavigation_Reports_LK");

		SW.EstablishConnection(Environment.getRunEnvironment());
		String Query = "call odsguest.pkg_guest_arvl_data_syncup.p_populate_by_resconf("+ReservationNo+")";
		SW.CallProcedure(Query);

		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Click("SGRMAR_ServiceOpportunities_LK");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.WaitTillElementToBeClickable("//tr[@id='1']/td[2]/a//span");


		String ActualReportTextXpath="//tr[@class='ui-widget-content ui-subtblcell']";

		String ActualReportText=SW.GetText(ActualReportTextXpath);
		if(SW.CompareTextContained(Situation, ActualReportText)){
			Environment.loger.log(Level.INFO, "Situation Is Present in the Report ");
		}else{
			Environment.loger.log(Level.ERROR, "Situation Is not Present in the Report ");
			SW.FailCurrentTest("Situation Is not Present in the Report");
		}

		if(SW.CompareTextContained(Action, ActualReportText)){
			Environment.loger.log(Level.INFO, "Action Is Present in the Report ");
		}else{
			Environment.loger.log(Level.ERROR, "Action Is not Present in the Report ");
			SW.FailCurrentTest("Action Is not Present in the Report");
		}

		if(SW.CompareTextContained(FollowUp, ActualReportText)){
			Environment.loger.log(Level.INFO, "FollowUp Is Present in the Report ");
		}else{
			Environment.loger.log(Level.ERROR, "FollowUp Is not Present in the Report ");
			SW.FailCurrentTest("FollowUp Is not Present in the Report");
		}

		if(SW.CompareTextContained(Recovery, ActualReportText)){
			Environment.loger.log(Level.INFO, "Recovery Is Present in the Report ");
		}else{
			Environment.loger.log(Level.ERROR, "Recovery Is not Present in the Report ");
			SW.FailCurrentTest("Recovery Is not Present in the Report");
		}

	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
