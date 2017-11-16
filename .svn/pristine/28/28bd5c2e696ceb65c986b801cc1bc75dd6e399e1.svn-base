package testscripts.Discovery;
/** Purpose		: Validate_Survey Id_Manage Surveys.
 * TestCase Name: Validate_Survey Id_Manage Surveys.
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG13_ValidateSurveyIdManageSurveys {
	CRM SW = new CRM();
	String UserName;
	String Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");// Script will not work in IE Driver because of IE Driver issue
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");
	}

	@Test(priority=1)
	public void ValidateManageSurveyList(){
		SW.DiscoveryLogin(UserName, Password);
		SW.DropDown_SelectByValue("DiscCreateSurvey_Creator_DD", "All");
		SW.Click("DiscCreateSurvey_FilterApply_BT");
		String survyno = SW.GetText("DiscCreateSurvey_FirstSurveyID_DT");
		String subsurvyno = survyno.substring(0, 5);
		SW.DropDown_SelectByText("DiscCreateSurvery_Scope_DD", "Survey ID");
		SW.WaitForPageload();
		SW.DiscoveryEnterValue("DiscCreateSurvery_FilterText_EB", subsurvyno);
		if(SW.ObjectExists("//table[@id='tat_table']//td[contains(.,'"+survyno+"')]")){
			Environment.loger.log(Level.INFO, "Survey ID is located Successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Not Able To Locate The Survey ID");
			SW.FailCurrentTest("Not Able To Locate The Survey ID");
		}
	}
	@Test(priority=2,dependsOnMethods="ValidateManageSurveyList")
	public void ValidateSortOrder(){
		List<String> UIValue = new ArrayList<String>();
		List<String> trimmedValue = new ArrayList<String>();
		UIValue = SW.GetAllText("//table[@id='tat_table']//td");
		for(String UISingleValue:UIValue){
			trimmedValue.add(UISingleValue.substring(0,UISingleValue.indexOf("-")));
		}
		SW.ValidateIntegerSortOrder(trimmedValue, "A");
	}
	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}

