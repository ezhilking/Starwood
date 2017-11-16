package testscripts.CSF;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/* Purpose		: Validate the DropDown in the details screen
 * TestCase Name: Validate the Drop Down
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG20_ValidateProfileDropDown {
	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void validateDropDown(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
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
		String test=SW.GetText("CSFDetail_Title_DD");
		if(SW.CompareText("Comparing the title_", "Mr Mrs Ms Miss Dr", test)){
			Environment.loger.log(Level.INFO, "Title in the drop down are matched");
		}else{
			Environment.loger.log(Level.ERROR,"Titles are not matched");
		}
		SW.ClickAndProceed("CSF_Cancel_BT");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
		}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
