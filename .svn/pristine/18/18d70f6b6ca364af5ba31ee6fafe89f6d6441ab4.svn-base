/* Purpose		: Verify the Invitee E-Mail  field  with more than one email id for Group type and english language_Admin user
 * TestCase Name: Verify the Invitee E-Mail  field  with more than one email id for Group type and english language_Admin user
 * Created By	: Muneeb
 * Modified By	: Muneeb
 * Modified Date: 2.22.2017
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

 public class MUSTRUN09_ResconEcca_InviteEmailGroupEng_Admin {
	 CHANNELS SW = new CHANNELS();


	 String GuestName,EventName,HotelContact,MailID,password;
	 @BeforeClass
	 public void StartTest(){
		 Environment.Tower = "CHANNELS";
		 Reporter.StartTest();
	//	 Environment.SetBrowserToUse("FF");
		 GuestName = SW.RandomString(6);
		 EventName = GuestName;
		 HotelContact = SW.RandomString(6);
		 MailID = SW.TestData("email");
	 }

	 //-------------------------------------------------------------------------------------------------------------------
	 //Admin Login
	 @Test(priority=1)
	 public void ECCA(){
		 SW.LaunchBrowser(Environment.RESCON);
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
		 SW.Click("ResconLogin_Login_BT");
		 SW.MoveToObject("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconEccaFlow_Invite_BT");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteType_DD", "2");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteLang_DD", "en_US");
		 SW.Click("ResconEccaFlow_InviteNext_BT");
		 SW.DropDown_SelectByValue("ResconECCA_IndividualSelectProperty_DD", SW.TestData("SGP_PID"));
		 SW.EnterValue("ResconECCA_IndividualGuestName_EB", GuestName);
		 SW.EnterValue("ResconECCA_IndividualHotelContacts_EB", HotelContact);
		 SW.EnterValue("ResconECCA_IndividualArrivalDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", "shalini.jaikumar@accenture.com,diksha.sharma@accenture.com,shalini.jaikumar@starwoodhotels.com,diksha.sharma@starwoodhotels.com");
		 SW.EnterValue("ResconECCA_ValidUntilDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));
		 //   SW.EnterValue("ResconECCA_IndividualNotifyEmail_EB", "MuneebAhamed.Shaik@starwoodhotels.com");
		 SW.Click("ResconEccaFlow_FinishButton_BT");

		 String Errormsg =SW.GetText("RsconEccaFlow_ErrorMsg-ST");
		 System.out.println(Errormsg);
		 Reporter.WriteLog(Level.INFO,Errormsg);
			Reporter.Write("Property Content Searched", "Property Content of login","Searched", "pass");

		 SW.CompareText("InviteEmailError", "RsconEccaFlow_ErrorMsg-ST", SW.GetText("RsconEccaFlow_ErrorMsg-ST")) ;  

		 SW.Click("ResconEccaFlow_Logout_BT");

	 }



	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }

 }
