package testscripts.resCon;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ReporterType;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class OUTHERS26_CCAuthInviteListSubmissiondateandValidUntilDateSortinDesceOrder 
{
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber,SuccessfullMsg,FailurefullMsg;
	String lastName = SW.RandomString(5);
	String EventName = SW.RandomString(6);
	String password;
//	String password ="A2H788E2";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		//String EventName = SW.RandomString(6);
		SW.LaunchBrowser(Environment.RESCON);
	}

	@Test(priority=1)
	public void CreateECCAIndividualEnglishPropertyuserInvite(){
//		SW.ResConLogin(SW.TestData("Rescon_UserNamePropertyuser"), SW.TestData("Rescon_PasswordPropertyuser"));
//		SW.MoveToObject("ResconHomepage_ECCA_BT");
//		SW.Click("ResconHomepage_ECCANewInvite_BT");
//		SW.DropDown_SelectByText("ResconECCA_InviteType_DD", "Individual");
//		SW.DropDown_SelectByText("ResconECCA_LanguageType_DD", "English (United States)");
//		SW.Click("ResconECCA_Next_BT");
//		SW.DropDown_SelectByValue("ResconECCA_IndividualSelectProperty_DD", "1513");
//		SW.EnterValue("ResconECCA_IndividualGuestName_EB", EventName);
//	//	SW.EnterValue("ResconECCA_IndividualGuestName_EB", "Smoke test automation for IndividualEnglish");
//		SW.EnterValue("ResconECCA_IndividualHotelContacts_EB", "KEYSHotel");
//		SW.EnterValue("ResconECCA_IndividualArrivalDate_EB","13/Feb/2017");
//		SW.EnterValue("ResconEccaFlow_InviteeEmail_ET", SW.TestData("email"));
//	//	SW.EnterValue("ResconECCA_IndividualNotifyemail_EB", SW.TestData("email"));
//		SW.Click("ResconEccaFlow_Logout_BT");
//		password = SW.GetPassword(EventName);
//	//	password = "FKA99YMG";
//		Reporter.WriteLog(Level.INFO,password); 
//		//ECCA form submission
//		
//
//		SW.LaunchBrowser(Environment.ECCA);
//		SW.EnterValue("ResconECCA_LoginUsername_EB", SW.TestData("ECCA_UserName"));
//		SW.EnterValue("ResconECCA_LoginPassword_EB", password);
//		//SW.DropDown_SelectByText(("ResConECCA_IndividualLanguageSelection_DD"), "English (United States)");
//		SW.Click("ResconECCA_Loginbutton_BT");
////		SW.EnterValue("ResconECCA_IndividualSubmissionRetriveConfirmationNo_EB",SW.TestData("RetrieveComfirmationNumber"));
////		SW.EnterValue("ResconECCA_IndividualSubmissionRetriveLastName_EB",SW.TestData("RetrieveGuestLastName"));
////		SW.Click("ResconECCA_IndividualSubmissionRetriveButton_BT");
//		//Manual entry
//		SW.EnterValue("ResconECCA_IndividualSubmissionManualConfirmationNo_EB", SW.TestData("ManualEntryConfirmationNo"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionManualFirstName_EB", SW.TestData("ManualEntryFirstName"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionManualLastName_EB", SW.TestData("ManualEntryLastName"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionManualArrivalDate_EB", SW.DateAddDays(SW.GetTimeStamp("MM/DD/YYYY"),"MM/DD/YYYY",7, Calendar.DATE));
//		SW.EnterValue("ResconECCA_IndividualSubmissionManualDepartureDate_EB", SW.DateAddDays(SW.GetTimeStamp("MM/DD/YYYY"),"MM/DD/YYYY",17, Calendar.DATE));
//		SW.Click("ResconECCA_IndividualSubmissionCCRoom&TaxChargesCheckBox_CB");
////		SW.Click("ResconECCA_IndividualSubmissionSaveGuestButton_BT");
//		SW.EnterValue("ResconECCA_IndividualSubmissionInitial_BT", SW.TestData("ECCAsubmissionInitialfield"));
//		//CC session
//		SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCType_BT","Visa");
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCNumber_BT", SW.TestData("CCNumber"));
//		SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCExpMonth_BT", "12");
//		SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCExpYear_BT", "2026");
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCFirstName_BT", SW.TestData("CCFirstName"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCLastName_BT", SW.TestData("CCLastName"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCAddress_BT", SW.TestData("CCAddress"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCCity_BT", SW.TestData("CCCity"));	
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCZip_BT", SW.TestData("CCZipCode"));
//		SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCCountry_BT", "India");
//		SW.DropDown_SelectByValue("ResconECCA_IndividualSubmissionCCState_BT", "string:kar");
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCTelephone_BT",SW.TestData("CCTelephone"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCEmailAddress_BT", SW.TestData("CCEmailAddress"));
//		SW.EnterValue("ResconECCA_IndividualSubmissionCCFullName_BT", SW.TestData("CCFullNameOnCard"));
//		SW.Click("ResconECCA_IndividualSubmissionSubmitButton_BT");
//		String success= SW.GetText("ResconECCA_ECCASuccessPage_DT");
//		Reporter.Write("Success", "Thank you. Your credit card authorization has been submitted.",success,"pass");
//		SW.NavigateTo(Environment.RESCON);
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_ECCA_BT");
		SW.Click("ResconECCA_ViewECCAInvitationList_Lk");
		SW.DropDown_SelectByValue("ResconECCA_ViewECCAInvitationListSelectProperty_DD", "1513");
		SW.Click("ResconMB_SPGSelectPropertyViewButton_BT");
		String arrivalDate=SW.GetText("ResconECCA_ArrivalDate_DT");
		Reporter.WriteLog(Level.INFO,arrivalDate);
		String departureDate= SW.GetText("ResconECCA_DeptDate_DT");
		Reporter.WriteLog(Level.INFO,departureDate);	
		if (false==SW.CompareText( arrivalDate, departureDate)) {
			Reporter.Write(SuccessfullMsg, "comparingSuccessfull", "comparingSuccessfull", "pass");
			
		} else {
			Reporter.Write(FailurefullMsg, "comparingFailure", "comparingFailure", "fail");

		}
       
        
	}
	@AfterClass
	public void EndTest(){

		Reporter.StopTest();		
	}
}
