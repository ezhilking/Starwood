package testscripts.resCon;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN41_VerifySessionOutMessageForMasterBillFlow_SPG  {

	CHANNELS SW = new CHANNELS();
	String GuestName,EventName,HotelContact,password, MailID,Errormsg ;
	
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
	 public void ECCreation(){
		 SW.LaunchBrowser(Environment.RESCON);
		 SW.EnterValue("ResconLogin_Username_EB", SW.TestData("SGP_UserName"));
		 SW.EnterValue("ResconLogin_Password_EB", SW.TestData("SGP_Password"));
		 SW.Click("ResconLogin_Login_BT");
		 SW.MoveToObject("ResconHomepage_Masterbill_BT");
			SW.Click("ResconHomepage_MBNewinvite_BT");
			SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
			SW.Click("ResconMB_Selectnext_BT");
			SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513");
			SW.EnterValue("ResconMB_SPGNumber_EB", SW.TestData("SPG_ConfirmationNumber"));
			SW.Click("ResconMB_SPGSearchbutton_BT");
			SW.EnterValue("ResconMB_SPGEmailid_EB", SW.TestData("Rescon_SPG_NotificationemailID"));
			SW.EnterValue("ResconMB_SPGEventname_EB", SW.TestData("Rescon_SPG_Eventname"));
			SW.EnterValue("ResconMB_SPGCheckIN_EB",SW.TestData("SGP_CheckIn"));
			if (SW.ObjectExists("ResconMB_SPGNotificationID_EB")) {
				SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
			} else {
				SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));

			}
			SW.EnterValue("ResconMB_SPGNotificationID_EB",SW.TestData("Rescon_SPG_NotificationemailID"));
//			SW.EnterValue("ResconMB_SPGEventname_EB", "NonSPG smoke test");
//			SW.EnterValue("ResconMB_NonSPGCheckout_EB",SW.TestData("SGP_CheckOut"));
//			SW.EnterValue("ResconMB_NonSPGNotifyEmail_EB", SW.TestData("NonSPGViewOnline_UserName"));
			SW.FileUpload("ResconMB_SPGFileUpload1_BT", "RL.xlsx");
			SW.FileUpload("ResconMB_SPGFileUpload2_BT", "Protecting Accenture Top 10 Dos and Don’ts.pdf");
			SW.Wait(300);
			SW.ObjectExists("ResconMB_NonSPGSubmitButton_BT");
			SW.ClickAndProceed("ResconMB_NonSPGSubmitButton_BT");
			Errormsg=SW.GetText("ResconMB_SPGAndSSPSessionTimeout_DT");
			System.out.println(Errormsg);
			Reporter.WriteLog(Level.INFO,Errormsg);
			Reporter.Write("SessionTimeOut", "Errormsg", "Errormsg", "pass");	
	 }
	 @AfterClass
	 public void EndTest(){
		 Reporter.StopTest();		
	 }
 }
