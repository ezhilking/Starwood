/* Purpose		: 
 * TestCase Name: Verify viewing the eCCA form contents_Individual Type_French Language
 * Created By	: Muneeb
 * Modified By	: 
 * Modified Date: 2.22.17
 * Reviewed By	:
 * Reviewed Date:
 */package testscripts.resCon;

 import java.util.Calendar;

import org.testng.annotations.AfterClass;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.Test;
 import org.apache.log4j.Level; 

 import functions.CHANNELS;
 import functions.Environment;
 import functions.Reporter;

 public class SMOKE03_02_EccaIndividualFrench {

	 CHANNELS SW = new CHANNELS();

	 String GuestName,EventName,HotelContact,MailID;
	 String password;
	 @BeforeClass
	 public void StartTest(){
		 Environment.Tower = "CHANNELS";
		 Reporter.StartTest();
	//	 Environment.SetBrowserToUse("FF");
		 SW.LaunchBrowser(Environment.RESCON);
	 }	

	 //-------------------------------------------------------------------------------------------------------------------
	 //Admin Login
	 @Test(priority=1)
	 public void ECCA(){
		 SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		 SW.MoveToObject("ResconHomepage_ECCA_BT");
		 SW.Click("ResconHomepage_ECCANewInvite_BT");
		 SW.MoveToObject("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconEccaFlow_Invite_BT");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteType_DD", "2");
		 SW.DropDown_SelectByValue("ResconEccaFlow_InviteLang_DD", "fr_FR");
		 SW.Click("ResconEccaFlow_InviteNext_BT");
		 SW.DropDown_SelectByValue("ResconECCA_IndividualSelectProperty_DD", SW.TestData("SGP_PID"));
		 SW.EnterValue("ResconECCA_IndividualGuestName_EB",EventName);
		 SW.EnterValue("ResconECCA_IndividualHotelContacts_EB", "HotelContact");
		 SW.EnterValue("ResconECCA_IndividualArrivalDate_EB",SW.DateAddDays(SW.GetTimeStamp("dd/MMM/yyyy"),"dd/MMM/yyyy",7, Calendar.DATE));;
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", SW.TestData("email"));
		 SW.ClearValue("ResconEccaFlow_InviteeEmail_ET");
		 SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", SW.TestData("email"));
		 SW.Click("ResconECCA_IndividualFinish_BT");
		 SW.Click("ResconEccaFlow_Logout_BT"); 
		  password = SW.GetPassword(EventName);
			Reporter.WriteLog(Level.INFO,password);
	 }
	 
	 //   CHANNELS.CloseBrowser();
	 //		
	 //--------------------------------------------------------------------------------------------------------------------
	 //User Login
	 // -*-*-*-*-*-*-*-*-*-*-*-*Open web mail and fetch the password-*-*-*-*-*-*-*-*-*-*-*-*




	 //	//*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_**_*_*
	 //	
	 @Test(priority=2,dependsOnMethods="ECCA")
	 public void Admin(){

		 SW.LaunchBrowser(Environment.ECCA);
		// String password = SW.GetPassword(EventName);
		 //	String password="JHI8HISW";
		 SW.EnterValue("ResconEccaFlow_Username_ET", MailID );
		 SW.EnterValue("ResconEccaFlow_Password_ET", password );
		 SW.DropDown_SelectByValue("ResconEccaFlow_LanguageID_ST", "fr_FR");
		 SW.Click("ResconEccaLogin_Login_BT");
		 SW.EnterValue("ResconECCA_GroupSubmissionEventDate_EB", "01/31/2017");
		 SW.Click("ResconECCA_GroupSubmissionEventChargesBanquet/Catering Event Charges_CB");
		 SW.Click("ResconECCA_GroupSubmissionEventChargesRoom & Tax Charges_CK");
		 // *************************************************************************************************************** 
		 SW.Click("ResconECCA_GroupSubmissionAddGuest_LK");
		 SW.EnterValue("ResconECCA_GroupSubmissionConfirmationNumber_EB", SW.TestData("RetrieveComfirmationNumber"));
		 SW.EnterValue("ResconECCA_GroupSubmissionRetriveLastName_EB",SW.TestData( "RetrieveGuestLastName"));
		 SW.Click("ResconECCA_GroupSubmissionRetriveGuest_BT");

		 //    SW.Wait(200);

		 SW.Click("ResconECCA_GroupSubmissionSaveGuest_BT");
		 SW.Click("ResconECCA_GroupSubmissionAddGuest_LK");
		 //*************************************************************************************************************** 
		 SW.EnterValue("ResconECCA_GroupSubmissionManualEntryConfirmationNumber_EB", SW.TestData("ManualEntryConfirmationNo"));
		 SW.EnterValue("ResconECCA_GroupSubmissionManualEntryFirstName_EB", SW.TestData("ManualEntryFirstName"));
		 SW.EnterValue("ResconECCA_GroupSubmissionManualEntryLastName_EB", SW.TestData("ManualEntryLastName"));
		 SW.EnterValue("ResconECCA_GroupSubmissionManualEntryArrivalDate_EB", SW.TestData("ManualEntryArrivalDate"));
		 SW.EnterValue("ResconECCA_GroupSubmissionManualEntryDepartureDate_EB", SW.TestData("ManualEntryDepartureDate"));
		 SW.Click("ResconECCA_GroupSubmissionApplyCreditcardChargesRoom & Tax Charges_CB");
		 SW.Click("ResconECCA_GroupSubmissionSaveGuest_BT");
		 SW.EnterValue("ResconECCA_GroupSubmissionInitial_EB",SW.TestData("ECCAsubmissionInitialfield"));
		 SW.DropDown_SelectByText("ResconECCA_GroupSubmissionCreditcardSelect_DD", "Visa");
		 SW.EnterValue("ResconECCA_GroupSubmissionCreditcardNumber_EB",SW.TestData("CCNumber"));              
		 SW.DropDown_SelectByText("ResconECCA_GroupSubmissionCCExpirationMonth_DD","12");
		 SW.DropDown_SelectByText("ResconECCA_GroupSubmissionCCExpirationYear_DD","2026");
		 SW.EnterValue("ResconECCA_GroupSubmissionCCFirstName_EB",SW.TestData("CCFirstName"));
		 SW.EnterValue("ResconECCA_GroupSubmissionCCLastName_EB",SW.TestData("CCLastName"));
		 SW.EnterValue("ResconECCA_GroupSubmissionCCAddress_EB",SW.TestData("CCAddress"));
		 SW.EnterValue("ResconECCA_GroupSubmissionCCCity_EB",SW.TestData("CCCity"));
		 SW.EnterValue("ResconECCA_GroupSubmissionCCZipcode_EB",SW.TestData("CCZipCode"));
		 SW.DropDown_SelectByText("ResconECCA_GroupSubmissionCCCountry_EB","India");
		 SW.DropDown_SelectByText("ResconECCA_GroupSubmissionCCState_EB","Kerala");
		 SW.EnterValue("ResconECCA_GroupSubmissionCCTelephone_EB",SW.TestData("CCTelephone"));
		 SW.EnterValue("ResconECCA_GroupSubmissionCCEmailAddress_EB",SW.TestData("CCEmailAddress"));
		 SW.EnterValue("ResconECCA_GroupSubmissionCCFullNameonCreditCard_EB",SW.TestData("CCFullNameOnCard"));
		 SW.Click("ResconECCA_GroupSubmissionSubmission_BT");  
		 SW.CloseOnlyThisBrowser();

	 }


	 //*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_
	 // View Page..

	 @Test(priority=3)
	 public void MPFIdentity(){
		 SW.LaunchBrowser(Environment.RESCON);
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("username1"));
		 SW.EnterValue("ResconLogin_Password_EB", SW.TestData("password1"));
		 SW.Click("ResconLogin_Login_BT");
		 SW.MoveToObject("ResconEccaFlow_SelectEvent_BT");
		 SW.Click("ResconECCA_ViewECCAInvitationList_Lk");
		 SW.DropDown_SelectByValue("ResconECCA_ViewECCAInvitationListSelectProperty_DD","1005");
		 SW.Click("ResconECCA_ViewECCAViewButton_BT");
		 if (SW.ObjectExists("ResconECCA_ViewECCAViewButton_BT")) {
			 Reporter.Write("ViewECCAViewButton", "Save button should be enabled", "save button is enabled", "PASS");
			}else{
				Reporter.Write("ViewECCAViewButton", "save button shouldn't be enabled", "save button is disabled", "FAIL");
			}

		
		 CHANNELS.CloseBrowser();

	 }
	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }



 }

