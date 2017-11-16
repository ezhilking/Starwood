/* Purpose		: //TODO
 * TestCase Name: InvalidSPGPassword & InvalidSPGuserName validation for booking
 * Created By	: Brij
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */

package testscripts.meetings;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MEETINGS_REG13_InvalidSPGuserNamePassword {

	CHANNELS SW = new CHANNELS();

	String errorMessagePWD = "The login information you provided is invalid. Please try again or request a new password.";
	String errorMessageLogIN = "Correct any errors and try again or contact your nearest Customer Care Center."+"\n"+"If this is the first time you are accessing your account online, activate your account";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)		
	public void InvalidSPGPassword(){			
		String url = SW.TestData("URL");
		SW.LaunchBrowser(url);			
		SW.Click("MeetingsBooking_ClickonBook_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");
		SW.EnterValue("MeetingsBookingSPG_Username_EB", SW.TestData("SGP_SPGUserName"));
		SW.EnterValue("MeetingsBookingSPG_Password_EB", "hg$&135@!Yuy");
		SW.Click("MeetingsBookingSPG_LogIN_BT");
		String errorMessage = SW.GetText("MeetingsBookingSPG_SPGError_DD");
		System.out.println("Password =" + errorMessage);
		
		if(SW.CompareText("InValidSPGPassword_ErrorMessageValidation_DT", errorMessagePWD, SW.GetText("MeetingsBookingSPG_SPGError_DD")))		
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="InvalidSPGPassword")

	public void InvalidSPGuserName(){

		String url = SW.TestData("URL");
		SW.LaunchBrowser(url);			
		SW.Click("MeetingsBooking_ClickonBook_BT");
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");
		SW.Click("MeetingsBooking_reserveLink_BT");
		SW.EnterValue("MeetingsBookingSPG_Username_EB", "478569853");
		SW.EnterValue("MeetingsBookingSPG_Password_EB", SW.TestData("SGP_SPGPassword"));
		SW.Click("MeetingsBookingSPG_LogIN_BT");
		String errorMessage = SW.GetText("MeetingsBookingSPG_SPGError_DD");
		System.out.println("userName = " +errorMessage);	
		
		if(SW.CompareText("InValidSPGPassword_ErrorMessageValidation_DT", errorMessageLogIN, SW.GetText("MeetingsBookingSPG_SPGError_DD")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
