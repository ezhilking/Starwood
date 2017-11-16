
	package testscripts.resCon;

	import java.util.Calendar;

	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import functions.CHANNELS;
	import functions.Environment;
	import functions.Reporter;
	import functions.Utility;

	public class OUTHERS45_VerifyTheEMailNotificationCheckBoxShouldAutomaticallyBeChecked {
		CHANNELS SW = new CHANNELS();
		String EventName = SW.RandomString(6);
		String MailID;
		String Xpath;
		String password;
//		String EventName = "PshbsW";
//		String password="NNCNW9EE";
		String SecondWindowURL,MainWindowURL;
		String crsConf;
		String errorMSg;

		@BeforeClass
		public void StartTest(){
			Environment.Tower = "CHANNELS";
			Reporter.StartTest();
			MailID = SW.TestData("email");
			Environment.SetBrowserToUse("GC");
			SW.LaunchBrowser(Environment.RESCON);
		}

		//Login as Admin*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_

		@Test(priority=1)
		public void MPF(){
			SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
			SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password"));
			SW.Click("ResconLogin_Login_BT");
			SW.MoveToObject("ResconMpFlow_SelectEvent_BT");
			SW.Click("ResconMpFlow_SelectMpFlowInvite_BT");
			SW.DropDown_SelectByValue("ResconMpFlow_SelectProperty_DD",SW.TestData("PID"));
			SW.EnterValue("ResconMpFlow_Event_EB", EventName);
			SW.EnterValue("ResconMpFlow_Email_EB", MailID);
			SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 1, Calendar.DATE));
			SW.CheckBoxIsSelected("ResconMpFlow_MailCheckbox_CB");
			SW.CheckBox("ResconMpFlow_MailCheckbox_CB", "ON");
			Reporter.Write("verification of checkbox", "ResconMpFlow_MailCheckbox_CB", "ResconMpFlow_MailCheckbox_CB", "pass");
			SW.Click("ResconMpFlow_Finish_BT");	
		}
		@AfterClass
		public void EndTest(){
			Reporter.StopTest();		
		}

}
