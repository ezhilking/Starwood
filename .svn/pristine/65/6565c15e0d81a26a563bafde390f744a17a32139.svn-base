package testscripts.AMS;
// Note : 
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.GetText;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class AMSIAM04_ForgotPassword {
    
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.BRIDGE);
	}
	@Test
	public void ForgotPassword(){
		SW.Click("BRIDGE_Login_LK");
		SW.EnterValue("IAMPasswordManagement_UserName_EB",SW.TestData("IAM_Username"));
		SW.EnterValue("IAMPasswordManagement_Password_EB",SW.TestData("IAM_Password"));
		SW.Click("IAMPasswordManagement_SignIn_LK");
		SW.EnterValue("BRIDGE_OldPassword_EB",SW.TestData("IAM_Password"));
		String Passwrd =SW.RandomString(8);
		int number = SW.RandomNumber(1, 20);
		SW.EnterValue("BRIDGE_NewPassword_EB",Passwrd+"#"+number);
		SW.EnterValue("BRIDGE_ConfirmPassword_EB",Passwrd+"#"+number);
		String Password= Passwrd+"#"+number;
		SW.WriteToTestData("IAM_Password", Password);
		
		SW.DropDown_SelectByIndex("BRIDGE_FirstQuestion_DD", 0);
		String Questn1 = SW.GetText("pt1:_d_reg:region0:0:it14::content");
		String Substr1 =Questn1.substring(Questn1.lastIndexOf(" "), Questn1.indexOf("?")).trim();
		SW.EnterValue("BRIDGE_FirstAnswer_EB",Substr1);
		
		SW.DropDown_SelectByIndex("BRIDGE_SecondQuestion_DD", 1);
		String Questn2 = SW.GetText("pt1:_d_reg:region0:0:it15::content");
		String Substr2 =Questn2.substring(Questn2.lastIndexOf(" "), Questn2.indexOf("?")).trim();
		SW.EnterValue("BRIDGE_SecondAnswer_EB",Substr2);
		
		SW.DropDown_SelectByIndex("BRIDGE_ThirdQuestion_DD", 2);
		String Questn3 = SW.GetText("pt1:_d_reg:region0:0:it16::content");
		String Substr3 =Questn3.substring(Questn3.lastIndexOf(" "), Questn3.indexOf("?")).trim();
		SW.EnterValue("BRIDGE_ThirdAnswer_EB",Substr3);
		
		SW.DropDown_SelectByIndex("BRIDGE_FourthQuestion_DD", 3);
		String Questn4 = SW.GetText("pt1:_d_reg:region0:0:it17::content");
		String Substr4 =Questn4.substring(Questn4.lastIndexOf(" "), Questn4.indexOf("?")).trim();
		SW.EnterValue("BRIDGE_FourthAnswer_EB",Substr4);
		
		SW.DropDown_SelectByIndex("BRIDGE_FifthQuestion_DD", 4);
		String Questn5 = SW.GetText("pt1:_d_reg:region0:0:it18::content");
		String Substr5 =Questn5.substring(Questn5.lastIndexOf(" "), Questn5.indexOf("?")).trim();
		SW.EnterValue("BRIDGE_FifthAnswer_EB",Substr5);
		
		SW.DropDown_SelectByIndex("BRIDGE_SixthQuestion_DD", 5);
		String Questn6 = SW.GetText("pt1:_d_reg:region0:0:it19::content");
		String Substr6 =Questn6.substring(Questn6.lastIndexOf(" "), Questn6.indexOf("?")).trim();
		SW.EnterValue("BRIDGE_SixthAnswer_EB",Substr6);
		
		SW.Click("BRIDGE_Submit_LK");
		
		
		
	}
	@AfterClass
	public void StopTes(){
		SW.Click("IAM_Logout_LK");
		Reporter.StopTest();
	}
}
