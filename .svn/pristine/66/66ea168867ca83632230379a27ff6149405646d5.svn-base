package testscripts.gcRegression;
/** Purpose		: Validate the Manager Title selected is highlighted in Offer OverView page
 * TestCase Name: GC_REG74_ValidateManagerTitleHighlightGCSetup
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

public class GC_REG74_ValidateManagerTitleHighlightGCSetup {

	CRM SW = new CRM();
	String PropertyID="1965";
	String Username,Password;

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
//Select the new Manager Title from the drop down
	@Test 
	public void SelectManagerTitle(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetUp_Active_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB",PropertyID );
		SW.Click("GCSetUp_Apply_BN");
		//SW.WaitTillElementToBeClickable("GCSetUp_EditActive_BN");
		if(!SW.ObjectExists("GCSetUp_EditActive_BN")){
			Environment.loger.log(Level.ERROR, "EditActive Icon is not present");
			SW.Click("GCSetUp_WorkInProgress_LK");
			SW.WaitTillElementToBeClickable("GCSetUp_Submit_LK");
			if(!SW.ObjectExists("GCSetUp_Submit_LK")){
				Environment.loger.log(Level.ERROR, "Submit Icon is not present");
				SW.Click("GCSetUp_Staging_LK");
				SW.WaitTillElementToBeClickable("GCSetUp_Approve_LK");
				if(!SW.ObjectExists("GCSetUp_Approve_LK")){
					Environment.loger.log(Level.ERROR, "Approve Icon is not present");
					SW.Click("GCSetUp_Approved_LK");
					SW.WaitTillElementToBeClickable("GCSetUp_Activate_LK");
					SW.Click("GCSetUp_Activate_LK");
				}

				else{
					SW.Click("GCSetUp_Approve_LK");
					SW.Click("GCSetUp_Activate_LK");
				}
			}	
			else {
				SW.Click("GCSetUp_Submit_LK");
				SW.Click("GCSetUp_Approve_LK");
				SW.Click("GCSetUp_Activate_LK");

			}
		}
		//Get the highlighted Manager Title from the  Overview page
		SW.WaitTillElementToBeClickable("GCSetUp_EditActive_BN");
		SW.Click("GCSetUp_EditActive_BN");
		int DropDownSize=SW.DropDown_GetSize("GCSetUp_ManagerTitle_DD");
		SW.DropDown_SelectByIndex("GCSetUp_ManagerTitle_DD", SW.RandomNumber(0, DropDownSize));
		String PreviousSelectedManagerTitle=SW.DropDown_GetSelectedText("GCSetUp_ManagerTitle_DD");

		Environment.loger.log(Level.INFO, "Selected Manager Title :"+PreviousSelectedManagerTitle );
		SW.Click("GCSetUp_Save_BN");
		SW.WaitTillElementToBeClickable("GCSetUp_Submit_BN");
		SW.Click("GCSetUp_Submit_BN");
		SW.WaitTillElementToBeClickable("GCSetUp_Approve_LK");
		if(!SW.ObjectExists("GCSetUp_Approve_LK")){
			Environment.loger.log(Level.ERROR, "Changes are not moved to Stage");
			Reporter.Write("Validate Approve icon", "Approve icon should be present", "Approve icon is not available", "FAIL");
		}
		SW.Click("GCSetUp_Approve_LK");
		SW.WaitTillElementToBeClickable("GCSetUp_Activate_LK");
		if(!SW.ObjectExists("GCSetUp_Activate_LK")){
			Environment.loger.log(Level.ERROR, "Changes are not moved to Approved");
			Reporter.Write("Validate Activate icon", "Activate icon should be present", "Activate icon is not available", "FAIL");
		}
		SW.Click("GCSetUp_Activate_LK");
		SW.WaitTillElementToBeClickable("GCSetUp_EditActivate_LK");
		if(!SW.ObjectExists("GCSetUp_EditActivate_LK")){
			Environment.loger.log(Level.ERROR, "EditActive icon is not present");
			Reporter.Write("Validate EditActive icon", "EditActive icon should be present", "EditActive icon is not available", "FAIL");
		}
		SW.Click("GCSetUp_EditActivate_LK");
		String CurrentSelectedManager= SW.DropDown_GetSelectedText("GCSetUp_ManagerTitle_DD");
		
		// Validate if the selected mManager Title is displayed in the Overview page
		SW.CompareText("CompareManagerTitle", PreviousSelectedManagerTitle, CurrentSelectedManager);
		Environment.loger.log(Level.INFO,"Selected Manager Title Is Highlighted in the list");
	}
	@AfterClass
	public void EndTest(){
		/*if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}*/
		Reporter.StopTest();		
	}
}
