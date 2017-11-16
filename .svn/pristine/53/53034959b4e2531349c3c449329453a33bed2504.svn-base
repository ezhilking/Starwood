package testscripts.resCon;

import java.util.Calendar;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class OUTHERS07_ValidateUserEnterDataArrivalDateDepartureDateColumnsManually {
	CHANNELS SW = new CHANNELS();
	String EventName = SW.RandomString(6);
	String Xpath;
	String password;
//	String EventName = "PshbsW";
//	String password="NNCNW9EE";
	String SecondWindowURL,MainWindowURL;
	String crsConf;
	String MailID;
	
	@BeforeClass
	public void StartTest()
	{ 
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		MailID = SW.TestData("email");
		Environment.SetBrowserToUse("GC");
		SW.LaunchBrowser(Environment.RESCON);
	}

	//Login as Admin*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_	
	@Test(priority=1)
	public void MPF(){
	SW.EnterValue("ResconLoginPage_Username_EB", SW.TestData("SGP_UserName"));
	SW.EnterValue("ResconLoginPage_Password_EB", SW.TestData("SGP_Password"));
	SW.Click("ResconLogin_Login_BT");
	SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
	SW.Click("ResconMpFlow_SelectMpFlowInvite_BT");
	SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD","1005");
	SW.EnterValue("ResconMpFlow_Event_EB", EventName);
	
//.. ....this two steps are not req it is going to next screen without this steps............................................//
	SW.ClearValue("ResconMpFlow_Date_EB");
//SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 1, Calendar.DATE));
	SW.Click("ResconMpFlow_Date_EB");
	SW.NormalClick("ResconMpFlow_Email_EB");
	SW.Click("ResconMpFlow_DateLink_LK");
	String Jscript="document.getElementsByName('emailAddress')[0].value='"+MailID+"';";
	SW.RunJavaScript(Jscript);
	//SW.EnterValue("//input[@name='emailAddress']", MailID);
	SW.Click("ResconMpFlow_Finish_BT");
}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
	
}
