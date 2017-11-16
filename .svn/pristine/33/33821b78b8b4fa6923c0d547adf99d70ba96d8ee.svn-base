package testscripts.CEM_AMB;
/** Purpose		: Validate the Adding and removing email in to the Notification Email Distribution from SGR
 * TestCase Name: Validate the Adding and removing email in to the Notification Email Distribution from SGR 
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

public class CEM_REG_08_ValidateAddingAndRemovingEmailNotificationDistributionFromSGR {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String AddedUser,AddedUserEmail;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
	}
	@Test(priority=1)
	public void AddEmailInSGR(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_AmbassadorDistList_LK");
		SW.DropDown_SelectByText("SGRAMBDistList_SelectDistList_DD", "AMB Notification - AMB Notification");
		SW.WaitForPageload();
		SW.DropDown_SelectByIndex("SGRAMBDistList_ContactList_DD", 1);
		SW.Click("SGRAMBDistList_AddContact<<_BT");
		AddedUser=SW.DropDown_GetSelectedText("SGRAMBDistList_UserAdded_DD");
		AddedUserEmail=AddedUser.substring(AddedUser.indexOf("[")+1, AddedUser.indexOf("-")).trim();
		Environment.loger.log(Level.INFO, "Added Mail ID--"+AddedUser);
		SW.Click("SGRAMBDistList_Save_BT");
		if(SW.ObjectExists("//li[text()='Changes are saved.']")){
			Environment.loger.log(Level.INFO, "Email Added from SGR Successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to add email");
			SW.FailCurrentTest("Failed to add email");
		}
		SW.Click("SGR_Logout_LK");
	}
	@Test(priority=2, dependsOnMethods="AddEmailInSGR")
	public void ValidateInCEM(){
		SW.NavigateTo(Environment.CEM_AMBURL);
		SW.CEMAMBLogin(UserName,Password);
		SW.Wait(5);
		SW.EnterValue("CEMAMBSearch_Search_EB", "W HONG KONG");
		SW.WaitTillElementToBeClickable("CEMAMBSearch_WHongKong_LK");
		SW.Click("CEMAMBSearch_WHongKong_LK");
		SW.Wait(15);
		SW.Click("CEMAMBPropertyAlert_Refresh_BT");
		SW.Wait(15);
		SW.Click("CEMAMBProperty_NotificationEmailDist_LK");
		SW.Wait(5);
		if(SW.ObjectExists("//li[text()='"+AddedUserEmail+"']")){
			Environment.loger.log(Level.INFO, "Added Email is present in the AMB list");
		}else{
			Environment.loger.log(Level.ERROR, "Email is not present in the AMB list");
			SW.FailCurrentTest("Email is not present in the AMB list");
		}
	}
	@Test(priority=3,dependsOnMethods="AddEmailInSGR" )
	public void RemoveEmailInSGR(){
		SW.NavigateTo(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_AmbassadorDistList_LK");
		SW.DropDown_SelectByText("SGRAMBDistList_SelectDistList_DD", "AMB Notification - AMB Notification");
		SW.WaitForPageload();
		
		SW.DropDown_SelectByText("SGRAMBDistList_UserAdded_DD", AddedUser);//Select added guest from the list to remove
		SW.Click("SGRAMBDistList_RemoveContact>>_BT");
		
		SW.Click("SGRAMBDistList_Save_BT");
		
		if(SW.ObjectExists("//li[text()='Changes are saved.']")){
			Environment.loger.log(Level.INFO, "Email Added from SGR Successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to add email");
			SW.FailCurrentTest("Failed to add email");
		}
		SW.Click("SGR_Logout_LK");
	}
	@Test(priority=4, dependsOnMethods="RemoveEmailInSGR")
	public void ValidateInCEMForRemoval(){
		SW.NavigateTo(Environment.CEM_AMBURL);
		SW.CEMAMBLogin(UserName,Password);
		SW.Wait(5);
		SW.EnterValue("CEMAMBSearch_Search_EB", "W HONG KONG");
		SW.WaitTillElementToBeClickable("CEMAMBSearch_WHongKong_LK");
		SW.Click("CEMAMBSearch_WHongKong_LK");
		SW.Wait(15);
		SW.Click("CEMAMBPropertyAlert_Refresh_BT");
		SW.Wait(10);
		SW.Click("CEMAMBProperty_NotificationEmailDist_LK");
		SW.Wait(5);
		if(!SW.ObjectExists("//li[text()='"+AddedUserEmail+"']")){
			Environment.loger.log(Level.INFO, "Added Email is not present in the AMB list and removed successfully from SGR");
		}else{
			Environment.loger.log(Level.ERROR, "Email ID is still present in the AMB list after removal from SGR");
			SW.FailCurrentTest("Email ID is still present in the AMB list after removal from SGR");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
