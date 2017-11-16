package testscripts.sgrRegression;
/** Purpose		: Validate if the error message is displayed when user saves a Starwood One associate by selecting event notification method as none 
 * TestCase Name: 1. Validate if the error message is displayed when user saves a Starwood One associate by selecting event notification method as none
 * 				  2. Validate if the error message is displayed when user saves a Non Starwood One associate by selecting event notification method as none
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG41_ValidateErrMsgSWOneAssociateSelectingEventNotificationNone {
	CRM SW = new CRM();
	String FirstName,LastName;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void ValidateErrorMessageSWuser(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Associate_LK");
		SW.NormalClick("SGRAssociate_FirstSWoneUser_IC");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("SGRAssociate_EventNotificaitonMethod_DD", "None");
		FirstName=SW.GetText("SGRAssociate_FirstName_EB");
		LastName=SW.GetText("SGRAssociate_LastName_EB");
		SW.DropDown_SelectByIndex("SGRAssociate_EscalationLevel_DD", 0);
		SW.Click("SGRAssociate_Save_BT");
		String SuccessMessage= SW.GetText("//span[@class='error']//li");
		SW.CompareText("ValidateSuccessMessage", ""+FirstName+" "+LastName+" was successfully updated!", SuccessMessage);
	}
	
	@Test(priority= 2, dependsOnMethods="ValidateErrorMessageSWuser")
	public void ValidateErrorMessageNonSWUser(){
		SW.NormalClick("SGRAssociate_FirstNonSWoneUser_IC");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("SGRAssociate_EventNotificaitonMethod_DD", "None");
		FirstName=SW.GetText("SGRAssociate_FirstName_EB");
		LastName=SW.GetText("SGRAssociate_LastName_EB");
		SW.DropDown_SelectByIndex("SGRAssociate_EscalationLevel_DD", 0);
		SW.Click("SGRAssociate_Save_BT");
		String SuccessMessage= SW.GetText("//span[@class='error']//li");
		SW.CompareText("ValidateSuccessMessage", ""+FirstName+" "+LastName+" was successfully updated!", SuccessMessage);
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
