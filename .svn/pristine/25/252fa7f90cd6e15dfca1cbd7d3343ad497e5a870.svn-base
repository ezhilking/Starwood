package testscripts.CEM_AMB;
/** Purpose		: Validate Secure Number Vault 
 * TestCase Name: Validate Secure Number Vault 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




/** Purpose		: Validate Create Activity functionality 
 * TestCase Name: Validate Create Activity functionality 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class CEM_REG_03_ValidateSecureNumberVault {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String Desc;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
	}
	@Test(priority=1)
	public void createSecureNumVault(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(10);
		SW.RunJavaScript("scroll(0,600)");//Scroll down for visibility of the link 
		SW.Click("CEMAMBMyGuests_SecureNumVault_LK");
		SW.RunJavaScript("scroll(0,600)");
		SW.DropDown_SelectByText("CEMAMBSecureNumVault_CreditCard_DD", "CC");
		SW.Click("CEMAMBSecureNumVault_Add_BT");
		SW.Wait(10);
		Desc=SW.RandomString(5);
		SW.EnterValue("CEMAMBSecureNumVault_Description_EB",Desc );
		SW.EnterValue("CEMAMBSecureNumVault_NameOnCard_EB", SW.RandomString(5));
		SW.EnterValue("CEMAMBSecureNumVault_Number_EB", "132456421");
		SW.EnterValue("CEMAMBSecureNumVault_ExpMonth_EB", "12");
		SW.EnterValue("CEMAMBSecureNumVault_ExpYear_EB", "20");
		SW.Click("CEMAMBSecureNumVault_Save_BT");
		
		SW.Wait(10);
		if(SW.ObjectExists("//form[contains(@name,'svnForm')]//td[contains(.,'"+Desc+"')]")){
			Environment.loger.log(Level.INFO, "Credit card details addded successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to add Credit card details!");
			SW.FailCurrentTest("Failed to add Credit card details!");
		}
		
	}
	@Test(priority=2, dependsOnMethods="createSecureNumVault")
	public void ValidateView(){
		SW.Click("//form[contains(@name,'svnForm')]//td[contains(.,'"+Desc+"')]//..//td[5]/a[text()='View']");
		SW.Wait(5);
		SW.EnterValue("CEMAMBSecureNumVault_UserID_EB", UserName);
		SW.EnterValue("CEMAMBSecureNumVault_Password_EB", Password);
		SW.EnterValue("CEMAMBSecureNumVault_ReasonForAccess_EB", "Sample reason");
		SW.Click("CEMAMBSecureNumVault_Proceed_BT");
		SW.Wait(5);
		if(SW.ObjectExists("CEMAMBSecureNumVault_DetailsView_ST")){
			if(SW.ObjectExists("//div[contains(@id,'snvModal')]//td[contains(.,'"+Desc+"')]")){
				Environment.loger.log(Level.INFO, "View of CC is successfull");
				SW.Click("CEMAMBSecureNumVault_Close_BT");
			}
		}else{
			Environment.loger.log(Level.INFO, "View of CC is failed to load");
		}
		
	}
	@Test(priority=3, dependsOnMethods="createSecureNumVault")
	public void ValidateEdit(){
		SW.Click("//form[contains(@name,'svnForm')]//td[contains(.,'"+Desc+"')]//..//td[5]/a[text()='Edit']");
		SW.Wait(5);
		SW.EnterValue("CEMAMBSecureNumVault_UserID_EB", UserName);
		SW.EnterValue("CEMAMBSecureNumVault_Password_EB", Password);
		SW.EnterValue("CEMAMBSecureNumVault_ReasonForAccess_EB", "Sample reason");
		SW.Click("CEMAMBSecureNumVault_Proceed_BT");
		SW.Wait(5);
		Desc=Desc+"Updated";
		SW.EnterValue("CEMAMBSecureNumVault_Description_EB",Desc );
		SW.Click("CEMAMBSecureNumVault_Save_BT");
		SW.Wait(5);
		if(SW.ObjectExists("//form[contains(@name,'svnForm')]//td[contains(.,'"+Desc+"')]")){
			Environment.loger.log(Level.INFO, "Credit card details Edited successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to Edit Credit card details!");
			SW.FailCurrentTest("Failed to Edit Credit card details!");
		}
	}
	@Test(priority=4, dependsOnMethods="createSecureNumVault")
	public void ValidateDeactivate(){
		SW.Click("//form[contains(@name,'svnForm')]//td[contains(.,'"+Desc+"')]//..//td[5]/a[text()='Deactivate']");
		SW.Wait(5);
		SW.EnterValue("CEMAMBSecureNumVault_UserID_EB", UserName);
		SW.EnterValue("CEMAMBSecureNumVault_Password_EB", Password);
		SW.EnterValue("CEMAMBSecureNumVault_ReasonForAccess_EB", "Sample reason");
		SW.Click("CEMAMBSecureNumVault_Proceed_BT");
		SW.Wait(10);
		if(!SW.ObjectExists("//form[contains(@name,'svnForm')]//td[contains(.,'"+Desc+"')]")){
			Environment.loger.log(Level.INFO, "Credit card details Deactivated successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to deactivate Credit card details!");
			SW.FailCurrentTest("Failed to deactivate Credit card details!");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
