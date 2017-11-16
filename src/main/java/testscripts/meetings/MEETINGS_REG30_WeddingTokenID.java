/* Purpose		: //TODO
 * TestCase Name: TokenID - Z email text Wedding
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

public class MEETINGS_REG30_WeddingTokenID {

	CHANNELS SW = new CHANNELS();
	String TokenIDURL;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.MEETING);
	}

	@Test(priority=1)	
	public void TokenIDEmailCorporate(){
		SW.MeetingsLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.Click("MeetingsCorporate_ModifyWebsite_BT");
		SW.EnterValue("MeetingsCorporate_PID_EB", SW.TestData("SGP_PID"));
		SW.EnterValue("MeetingsCorporate_MeetingID_EB", SW.TestData("WeddingMeetingID"));
		SW.Click("MeetingsCorporate_Search_BT");
		SW.Click("MeetingsCorporate_SendEmail_LK");
		SW.SwitchToWindow(2);
		TokenIDURL = SW.GetCurrentURL();
		String tokenID = TokenIDURL+"DWH$%2097j";
		SW.Click("MeetingsCorporate_SendEmail_BT");		
		if(SW.CompareText("VerifyTokenIDEmailTextSend_DT", "Your mail has been sent.", SW.GetText("MeetingsCorporate_EmailText_ST")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
		SW.Click("MeetingsCorporate_EmailClose_BT");	
		SW.SwitchToWindow(1);
		SW.NavigateTo(tokenID);
		if((SW.ObjectExists("MeetingsLogin_Username_EB"))){
			Environment.loger.log(Level.INFO, "Object exists");
		}else{
			Environment.loger.log(Level.INFO, "Object doesn't exists");
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
