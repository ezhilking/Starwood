/* Purpose		:Verify the  E-Mail Notification field with more than 3 email id for Group type and english language_Admin user 
 * TestCase Name:Verify the  E-Mail Notification field with more than 3 email id for Group type and english language_Admin user 
 * Created By	: Muneeb
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:
 * Reviewed Date:
 */package testscripts.resCon;

 import java.util.Calendar;

import org.apache.log4j.Level;
 import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

 import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

 public class MUSTRUN08_01_ResconEccaGroupEnglishMailFaill {

	 CHANNELS SW = new CHANNELS();
	 String EventName = SW.RandomString(6);
	 String MailID;
	 String 	Errormsg;

	 @BeforeClass
	 public void StartTest(){
		 Environment.Tower = "CHANNELS";
		 Reporter.StartTest();
		 MailID = SW.TestData("email");
	//	 Environment.SetBrowserToUse("FF");
		 //String EventName = "AKZC9U42";
		 SW.LaunchBrowser(Environment.RESCON);
	 }


	 //-------------------------------------------------------------------------------------------------------------------
	 //Admin Login
	 @Test(priority=1)
	 public void EGCreation(){

		 //    SW.EnterValue("ResconLogin_Username_EB", SW.TestData("username1"));
		 //  	SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password1"));
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
		 SW.Click("ResconLogin_Login_BT");
		 SW.MoveToObject("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconEccaFlow_Invite_BT");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteType_DD", "1");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteLang_DD", "en_US");
		 SW.Click("ResconEccaFlow_InviteNext_BT");
		 SW.DropDown_SelectByValue("ResconEccaFlow_FetchEmail_DD",SW.TestData("PID"));
		 SW.EnterValue("ResconEccaFlow_FunctionName_ET", EventName);
		 SW.EnterValue("ResconEccaFlow_FunctionManager_ET", "Owner");
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", "shalini.jaikumar@accenture.com");
		 SW.EnterValue("ResconEccaFlow_Date_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));
		 SW.Click("ResconEccaFlow_FinishButton_BT");
		 Errormsg =SW.GetText("RsconEccaFlow_ErrorMsg-ST");
		 System.out.println(Errormsg);
		 Reporter.WriteLog(Level.INFO,Errormsg);
			Reporter.Write("Property Content Searched", "Property Content of login","Searched", "pass");


		 SW.Click("ResconEccaFlow_Logout_BT"); 

	 }
	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }
 }
