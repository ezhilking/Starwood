package testscripts.CSF;
/* Purpose		: Validate the property details when property id is entered in the summary screen
 * TestCase Name: REG01__ValPropDetails
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;


public class REG01__ValPropDetails {
	CRM SW = new CRM();
	String CSFFileNo;
	String owner;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
		
	}
	@Test(priority=0)
	public void CSFCreateFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			owner="Franchise";
		}
		else if(Environment.getRunEnvironment().equalsIgnoreCase("STAGE")){
			owner="Owned";
		}
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
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
		SW.EnterValue("CSFSummary_PropertyID_EB", SW.TestData("PropertyID"));
		SW.Click("CSFSummary_PropertySearch_Lk");
		SW.Wait(3);
		String sPropName=SW.GetText("CSFSummary_PropertyName_DT");
		//validating property name 
		if(SW.CompareText("Check Property name", "Sheraton Suites Philadelphia Airport", sPropName))
			Environment.loger.log(Level.INFO, "Property Name matched with Property ID");
		else{
				Environment.loger.log(Level.ERROR,"Property name not Matched");
				SW.FailCurrentTest("Validation fails in Property name");
			}
		//validating Property address
		String sPropAddress=SW.GetText("CSFSummary_PropertyAddress_DT");
		if(SW.CompareText("Check Property Address", "Philadelphia, Pennsylvania United States", sPropAddress))
			Environment.loger.log(Level.INFO, "Property Address has matched with PropertyId");
		else{
				Environment.loger.log(Level.ERROR,"Property Address is not Matched with Property Id");
				SW.FailCurrentTest("Validation Fails in Property Address");
			}
		//validating Ownership
		String Ownership=SW.GetText("CSFSummary_PropertyOwnership_DT");
		if(SW.CompareText("Check Property Ownership", owner, Ownership))
			Environment.loger.log(Level.INFO, "Property OwnerShip has matched");
		else{
				Environment.loger.log(Level.ERROR,"Ownership detail not matched");
				SW.FailCurrentTest("Validation fails in owner ship details");
			}
		SW.ClickAndProceed("CSF_Cancel_BT");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
