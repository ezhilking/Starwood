package tbd;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class PasswordManagement_ResetpasswordforTerminateduser {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		//		SW.WriteToTestData("ExtendedTitle", "tyagiTheboss");
		SW.LaunchBrowser("http://phxiamqws61.nssd.star:7777/identity");
	}

	@Test(priority=1)
	public void PasswordMgmnt(){
		SW.EnterValue("IMAIdentity_LoginUsername_BT", "padmyal");	
		SW.EnterValue("IMAIdentity_LoginPassword_BT", "Woods#3");
		SW.Click("IMAIdentity_LoginButton_BT");
		SW.Click("IAMIdentity_Manage_BN");
		SW.NormalClick("IAMIdentity_Users_LK");
		SW.Wait(30);
//		SW.WaitTillElementToBeClickable("IMAIdentity_Advanced_LK");
		SW.NormalClick("IMAIdentity_Advanced_LK");
		//		SW.Wait(30);
		SW.DropDown_SelectByIndex("IMAIdentityUsers_IdentitySts_DD", 3);	
		SW.Wait(40);
		SW.NormalClick("IMAIdentityUsers_Search_BT");
		SW.Wait(40);
		SW.WebTbl_Click("IMAIdentityUsers_Summary_WT", 1, 1);
		SW.Click("IMAIdentityUsers_Resetpassword_LK");
		SW.SelectRadioButton("IMAIdentityUsers_ManualChange_RB");
		SW.Wait(10);
		String NewPassword = "TEST_"+SW.RandomInteger(4);
		SW.EnterValue("IMAIdentityUsers_NewPasswrd_EB", NewPassword);
		SW.EnterValue("IMAIdentityUsers_ConfirmNewPasswrd_EB", NewPassword);
		SW.Click("IMAIdentityUsers_ResetButton_BN");
		SW.CompareText("IAMIdentity_SuccessMessage_DT", "Password has been reset successfully", SW.GetText("IMAIdentityUsers_SuucessMsg_DT"));
		
		
		
	}
	@AfterClass
	public void StopTesT(){
		Reporter.StopTest();
	}
}