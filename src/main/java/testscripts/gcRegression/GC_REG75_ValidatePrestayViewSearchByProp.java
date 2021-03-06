package testscripts.gcRegression;
/** Purpose		: Validate the Pre-Stay Offers by searching through Property Id
 * TestCase Name: GC_REG75_ValidatePrestayViewSearch byProp
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: Sachin	
 * Reviewed Date: 10/03/2017
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
public class GC_REG75_ValidatePrestayViewSearchByProp {

	CRM SW = new CRM();
	String PropertyID;
	String Username,Password, PropID;

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	@Test 
	public void PreStayViewSearchByPropperty(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GC_AdminTab_PreStayPostStaySuppression_LK");
		SW.WaitTillElementToBeClickable("GC_AdminTab_ViewSupressedRecords_LK");
		SW.Click("GC_AdminTab_ViewSupressedRecords_LK");
		SW.Click("GC_AdminTab_NavigateToLastPage_LK");
		PropertyID=SW.WebTbl_GetText("GC_AdminTab_SelectFirstObject_DT", 1, 1);
		SW.EnterValue("GC_AdminTab_PropIDOfSupressedRecords_EB",PropertyID );
		SW.Click("GC_AdminTab_ApplySupressedRecords_BN");
		PropID=SW.WebTbl_GetText("GC_AdminTab_SelectFirstObject_DT", 1, 1);
		if(SW.CompareText("CompareSupressedPropertyIDRecords", PropertyID, PropID)){
			Environment.loger.log(Level.INFO, "Records are displayed as per search criteria");
			Reporter.Write("CompareSupressedPropertyIDRecords", "Records should be displayed as per search criteria", "Records are displayed as per search criteria", "PASS");
		}
		else{
			Environment.loger.log(Level.ERROR, "Records are not displayed as per search criteria");
			Reporter.Write("CompareSupressedPropertyIDRecords", "Records should not be displayed as per search criteria", "Records are not displayed as per search criteria", "PASS");
		}
	}	
	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();	

	}
}
