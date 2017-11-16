package testscripts.resCon;
/* Purpose		: validating Welcome message for the Property user
 * TestCase Name: [1]TC1_Verify the background image and Welcome message
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date:
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;


public class OUTHERS02_WelcomeMessageForPropertyUser {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
		
	}@Test(priority=1)
	public void validatingWelcomemessageForAdminUser(){
		SW.ResConLogin(SW.TestData("Rescon_UserNamePropertyuser"), SW.TestData("Rescon_PasswordPropertyuser"));
		String HomePageWelcomeMessage = SW.GetText("ResconHomepage_WelcomeMessage_ST");
		String Expected = "WELCOME TO RESERVATION CONNECTION, SHALINI JAIKUMAR";//Displays as per login credentials
		SW.CompareText("WELCOMEMessageForAdminUser", Expected, HomePageWelcomeMessage);
	}
	
	@AfterClass
	public void EndTest(){
			Reporter.StopTest();		
	}
}
