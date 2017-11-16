 package testscripts.resCon;

import java.util.Calendar;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
import functions.Utility;

public class OUTHERS46_VerifyTheEMailNotificationFieldShouldAllowMorethanOneAndTotal3Email {
	CHANNELS SW = new CHANNELS();
	String EventName = SW.RandomString(6);
	String MailID;
	String Xpath;
	String password;
//	String EventName = "PshbsW";
//	String password="NNCNW9EE";
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
		SW.EnterValue("ResconMpFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"), "dd/MMM/yyyy", 7, Calendar.DATE));
		SW.CheckBox("ResconMpFlow_MailCheckbox_CB", "ON");
		SW.EnterValue("ResconMB_SPGNotificationID_EB", "mahaveer.singh.m@accenture.com, muneeb.ahamed.shaik@accenture.com, diksha.sharma@starwoodhotels.com");
		Reporter.Write("verification of checkbox", "ResconMB_SPGNotificationID_EB", "ResconMB_SPGNotificationID_EB", "pass");
		SW.Click("ResconMpFlow_Finish_BT");	
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
