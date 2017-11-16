package testscripts.CSF;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/* Purpose		: Changing the user role 
 * TestCase Name: Changing the user role for a particular user
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG19_ChangeUserRole {
	CRM SW = new CRM();
	String user;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
		user=SW.TestData("CSFChangeUser");
	}
	@Test
	public void changeuserrole(){
			SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
			SW.Click("CSFHome_Admin_LK");
			SW.Click("CSFAdmin_UserAdmin_LK");
			SW.EnterValue("CSFAdmin_NameFilter_EB", user);
			SW.Click("CSFAdmin_Filter_BT");
			SW.Click("CSFAdmin_Result_WT");
			SW.DropDown_SelectByText("CSFAdmin_Role_DD", SW.TestData("CSFRole"));
			SW.Click("CSFAdmin_Done_BT");
			SW.Click("CSFAdmin_SaveChanges_BT");
			SW.EnterValue("CSFAdmin_NameFilter_EB", user);
			SW.Click("CSFAdmin_Filter_BT");
			String role=SW.GetText("CSFAdmin_userrole_DT");
			if(SW.CompareText("Comaring the role", SW.TestData("CSFRole"), role))
				Environment.loger.log(Level.INFO, "Role has updated Succesfully!!!");
			else
			{
				Environment.loger.log(Level.ERROR,"Role not updated");
				SW.FailCurrentTest("Validation fails in Validating role");
			}
			SW.ClickAndProceed("CSF_Logout_LK");
			SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
