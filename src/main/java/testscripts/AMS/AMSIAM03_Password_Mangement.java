package testscripts.AMS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class AMSIAM03_Password_Mangement {

	SALES SW = new SALES();
	String Position;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.OIMURL);
	}
	@Test
	public void Password_Mangement(){
		SW.EnterValue("IAMPasswordManagement_UserName_EB",SW.TestData("IAMUserID"));
		SW.EnterValue("IAMPasswordManagement_Password_EB",SW.TestData("IAMPassword"));
		SW.Click("IAMPasswordManagement_SignIn_LK");
		SW.Click("IAMPasswordManagement_Manage_LK");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_User_LK");
		SW.NormalClick("IAMPasswordManagement_User_LK");
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Action_LK");
		SW.DropDown_SelectByText("IAM_UserLogin_DD", "User Login");
		SW.Wait(5);
		SW.EnterValue("IAM_UserSearch_EB", "%z000");
		SW.NormalClick("IAM_UserSearch_IC");
		SW.NormalClick("IAM_UserSearch_IC");
		SW.Wait(5);
		int Userid = SW.RandomNumber(2, 25);
		String ID = SW.GetText("(//a[@class='xi8'])"+"["+Userid+"]");
		SW.WriteToTestData("IAM_Username", ID);
		SW.Wait(10);
		SW.Click("(//a[@class='xi8'])"+"["+Userid+"]");
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Attribute_LK");
		SW.Click("IAMPasswordManagement_Attribute_LK");
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Modify_LK");
		SW.NormalClick("IAMPasswordManagement_Modify_LK");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Email_EB");
		String email =SW.RandomString(8);
		SW.ClearValue("IAMPasswordManagement_Email_EB");
		SW.EnterValue("IAMPasswordManagement_Email_EB",email+"@accenture.com");
		SW.Click("//*[text()='Basic Information']");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Submit_LK");
		SW.Click("IAMPasswordManagement_Submit_LK");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Refresh_LK");
		SW.NormalClick("IAMPasswordManagement_Refresh_LK");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Reset_LK");
		SW.NormalClick("IAMPasswordManagement_Reset_LK");
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("IAMPasswordManagement_Radiobutton_RB");
		SW.SelectRadioButton("IAMPasswordManagement_Radiobutton_RB");
		SW.Wait(5);
		String Passwrd =SW.RandomString(8);
		int number = SW.RandomNumber(1, 20);
		SW.EnterValue("IAMPasswordManagement_NewPassword_EB", Passwrd+"#"+number);
		SW.EnterValue("IAMPasswordManagement_ConfirmPassword_EB",Passwrd+"#"+number);
		String Password= Passwrd+"#"+number;
		SW.WriteToTestData("IAM_Password", Password);
		SW.CheckBox("IAMPasswordManagement_Checkbox_CB","ON");
		SW.Click("IAMPasswordManagement_ResetPassword_LK");
		
	}
	@AfterClass
	public void StopTes(){
		SW.Click("IAMPasswordManagement_Arrowdown_DD");
		SW.WaitTillElementToBeClickable("IAM_Logout_LK");
		SW.Click("IAM_Logout_LK");
		Reporter.StopTest();
	}

}
