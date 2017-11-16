/* Purpose		: Check the email validation message in the pop up in eCCA Authorization form_Group French
 * TestCase Name: Check the email validation message in the pop up in eCCA Authorization form_Group French
 * Created By	: Muneeb
 * Modified By	: 
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
import functions.Utility;

 public class MUSTRUN04_ResconEccaEmailValidationEccaAuthForm {
	 CHANNELS SW = new CHANNELS();


	 String GuestName,EventName,HotelContact,MailID,password, errormailmsg;
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
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteType_DD", "1");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteLang_DD", "fr_FR");
		 SW.Click("ResconEccaFlow_InviteNext_BT");
		 SW.DropDown_SelectByValue("ResconECCA_IndividualSelectProperty_DD", SW.TestData("SGP_PID"));
		 SW.EnterValue("ResconECCA_IndividualGuestName_EB", GuestName);
		 SW.EnterValue("ResconECCA_IndividualHotelContacts_EB", HotelContact);
		 SW.EnterValue("ResconECCA_IndividualArrivalDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", MailID);
		 SW.ClearValue("ResconEccaFlow_InviteeEmail_ET");
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", MailID);
		 SW.Click("ResconECCA_IndividualFinish_BT");
		 SW.Click("ResconEccaFlow_Logout_BT"); 
		 password = SW.GetPassword(EventName);


	 }


	 @Test(priority=2,dependsOnMethods="ECCA")
	 public void Admin(){

		 SW.LaunchBrowser(Environment.ECCA);



		 //	String password="JHI8HISW";
		 SW.EnterValue("ResconEccaFlow_Username_ET", "abc@gmail.com" );
		 SW.EnterValue("ResconEccaFlow_Password_ET", password );
		 SW.DropDown_SelectByValue("ResconEccaFlow_LanguageID_ST", "en_EN");
		 SW.Click("ResconEccaLogin_Login_BT");
		 errormailmsg=SW.GetText("ResconECCA_EmailAuthFail_EB");
		 if (SW.ObjectExists("ResconECCA_EmailAuthFail_EB")) {Reporter.Write("EmailAuth Created","EmailAuth of login"," created", "pass");	
			} else {
				Reporter.Write("EmailAuth not Created","EmailAuth of login","EmailAuth not created", "fail");
		}
		 System.out.println(errormailmsg);
		 Reporter.WriteLog(Level.INFO, "pass");
		 Utility.CloseBrowser();


	 }


	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }
 }
