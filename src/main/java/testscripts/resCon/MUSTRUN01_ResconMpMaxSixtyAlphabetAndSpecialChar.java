/* Purpose		: TC3_Verify submitting and Viewing the eCCA FORM for eCCA invite created with the Individual Guest Name
 *                and Hotel Contact Fields with max 60 alpha and special characters_Individual Type_English Language
 * TestCase Name: TC3_Verify submitting and Viewing the eCCA FORM for eCCA invite created with the Individual Guest Name
 *                and Hotel Contact Fields with max 60 alpha and special characters_Individual Type_English Language
 * Created By	: Muneeb
 * Modified By	: 2.22.2017
 * Modified Date: 
 * Reviewed By	:
 * Reviewed Date:
 */package testscripts.resCon;

 import java.util.Calendar;

 import org.testng.annotations.AfterClass;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.Test;

 import functions.CHANNELS;
 import functions.Environment;
 import functions.Reporter;
 import functions.Utility;

 public class MUSTRUN01_ResconMpMaxSixtyAlphabetAndSpecialChar {
	 CHANNELS SW = new CHANNELS();
	 String GuestName,EventName,HotelContact,MailID,password;
	 @BeforeClass
	 public void StartTest(){
		 Environment.Tower = "CHANNELS";
		 Reporter.StartTest();
		 Environment.SetBrowserToUse("FF");
		 GuestName    = SW.RandomString(6);
		 EventName    = GuestName;
		 HotelContact = SW.RandomString(6);
		 MailID 	  = SW.TestData("email");
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
		 SW.DropDown_SelectByValue("ResconEccaFlow_FetchEmail_DD",SW.TestData("PID"));
		 SW.EnterValue("ResconECCA_IndividualGuestName_EB", "qwertyuiopasdfghjklzxcvbnm,./;'[]!@#$%^&*()_++_)(*&^%$#@!<>:");
		 SW.EnterValue("ResconECCA_IndividualHotelContacts_EB", ",./;'[]!@#$%^&*()_++_)(*&^%$#@!<>:qwertyuiopasdfghjklzxcvbnm");
		 SW.EnterValue("ResconECCA_IndividualArrivalDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", "shalini.jaikumar@accenture.com");
		 SW.EnterValue("ResconECCA_ValidUntilDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));
		 //   SW.EnterValue("ResconECCA_IndividualNotifyEmail_EB", "MuneebAhamed.Shaik@starwoodhotels.com");
		 SW.Click("ResconEccaFlow_FinishButton_BT");
		 password = SW.GetPassword(EventName);
	 }
	 //------------------------------------------------------------

	 @Test(priority=2,dependsOnMethods="ECCA")
	 public void Admin(){
		 SW.LaunchBrowser(Environment.ECCA);
		 //	String password="JHI8HISW";
		 SW.EnterValue("ResconEccaFlow_Username_ET", MailID );
		 SW.EnterValue("ResconEccaFlow_Password_ET", password );
		 SW.DropDown_SelectByValue("ResconEccaFlow_LanguageID_ST", "en_EN");
		 SW.Click("ResconEccaLogin_Login_BT");

		 SW.EnterValue("ResconECCA_IndividualSubmissionManualConfirmationNo_EB", SW.TestData("ManualEntryConfirmationNo"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionManualFirstName_EB", SW.TestData("ManualEntryFirstName"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionManualLastName_EB", SW.TestData("ManualEntryLastName"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionManualArrivalDate_EB", SW.TestData("ManualEntryArrivalDate"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionManualDepartureDate_EB", SW.TestData("ManualEntryDepartureDate"));
		 SW.Click("ResconECCA_IndividualSubmissionCCRoom&TaxChargesCheckBox_CB");
		 SW.Click("ResconECCA_IndividualSubmissionADDGuestButton2_LK");
		 //SW.Click("ResconECCA_IndividualSubmissionSaveGuestButton_BT");
		 SW.EnterValue("ResconECCA_IndividualSubmissionInitial_BT", SW.TestData("ECCAsubmissionInitialfield"));
		 //CC session
		 SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCType_BT","Visa");
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCNumber_BT", SW.TestData("CCNumber"));
		 SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCExpMonth_BT", "12");
		 SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCExpYear_BT", "2026");
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCFirstName_BT", SW.TestData("CCFirstName"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCLastName_BT", SW.TestData("CCLastName"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCAddress_BT", SW.TestData("CCAddress"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCCity_BT", SW.TestData("CCCity"));             
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCZip_BT", "CCZipCode");
		 SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCCountry_BT", "India");
		 SW.DropDown_SelectByValue("ResconECCA_IndividualSubmissionCCState_BT", "string:kar");
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCTelephone_BT",SW.TestData("CCTelephone"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCEmailAddress_BT", SW.TestData("CCEmailAddress"));
		 SW.EnterValue("ResconECCA_IndividualSubmissionCCFullName_BT", SW.TestData("CCFullNameOnCard"));
		 SW.Click("ResconECCA_IndividualSubmissionSubmitButton_BT");     

	 }

	 //*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_
	 // View Page..


	 @Test(priority=3)
	 public void MPFIdentity(){
		 SW.LaunchBrowser(Environment.RESCON);
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
		 SW.Click("ResconLogin_Login_BT");
		 
		 SW.MoveToObject("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconECCA_ViewECCAInvitationList_Lk");
		 if (SW.ObjectExists("ResconECCA_ViewECCAInvitationList_Lk")) {Reporter.Write("SelectEvent Created","SelectEvent of login"," created", "pass");	
			} else {
				Reporter.Write("SelectEvent not Created","SelectEvent of login","not created", "fail");
		}
		 SW.DropDown_SelectByValue("ResconECCA_ViewECCAInvitationListSelectProperty_DD","1005");
		 SW.Click("ResconECCA_ViewECCAViewButton_BT");
		 Utility.CloseBrowser();
	 }
	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }
 }