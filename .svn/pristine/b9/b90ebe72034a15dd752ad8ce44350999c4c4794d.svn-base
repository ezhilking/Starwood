package testscripts.resCon;
/* Purpose		: To Validating Alert message on the Home page of the ResCon application as Property User
 * TestCase Name: TC2_ Verify the alert message on the home page when you login for Property_User
 * Created By	: Shalini.jaikumar
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

public class MUSTRUN15_AlertMessageOnHomePropertyUser {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String EventName = SW.RandomString(6);
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
		//String EventName = SW.RandomString(6);
	}@Test(priority=1)
	public void CreateECCAGroupEnglishInvite(){
		SW.ResConLogin(SW.TestData("Rescon_UserNamePropertyuser"), SW.TestData("Rescon_PasswordPropertyuser"));
		String HomePageAlertMessage = SW.GetText("Rescon_AlertMessageAdminUser_ST");
		String Expected = "Starwood strives to protect our customers'''' information & privacy. Rooming List files may contain personal and sensitive financial information of guests. Help protect our customers by ensuring the original file is uploaded by the Meeting Planner directly into ResCon via a secure login. Please do not copy any information from the file outside of the ResCon application while processing the file.";
		SW.CompareText("Rescon_HomePageAlert_DT", Expected, HomePageAlertMessage);

	}
	@AfterClass
	public void EndTest(){
			Reporter.StopTest();		
	}

}
