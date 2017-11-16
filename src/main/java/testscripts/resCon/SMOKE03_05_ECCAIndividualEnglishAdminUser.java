package testscripts.resCon;
/* Purpose		: Validating ECCA Individual English_Admin user
 * TestCase Name: 
 * Created By	: Shalini.jaikumar
 * Modified By	:
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE03_05_ECCAIndividualEnglishAdminUser {CHANNELS SW = new CHANNELS();
String Number;
String cnfcNumber;
String lastName = SW.RandomString(5);
String EventName = SW.RandomString(6);
String password;
@BeforeClass
public void StartTest(){
	Environment.Tower = "CHANNELS";
	Reporter.StartTest();
	Environment.SetBrowserToUse("FF");
	//String EventName = SW.RandomString(6);
	SW.LaunchBrowser(Environment.RESCON);
}
@Test(priority=1)
public void CreateECCAIndividualEnglishPropertyUserInvite(){
	//rescon login to create invite for individual english

	SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
	SW.MoveToObject("ResconHomepage_ECCA_BT");
	SW.Click("ResconHomepage_ECCANewInvite_BT");
	SW.DropDown_SelectByText("ResconECCA_InviteType_DD", "Individual");
	SW.DropDown_SelectByText("ResconECCA_LanguageType_DD", "English (United States)");
	SW.Click("ResconECCA_Next_BT");
	SW.DropDown_SelectByValue("ResconECCA_IndividualSelectProperty_DD", "1513");
	SW.EnterValue("ResconECCA_IndividualGuestName_EB", EventName);
	//SW.EnterValue("ResconECCA_IndividualGuestName_EB", "Smoke test automation for IndividualEnglish");
	SW.EnterValue("ResconECCA_IndividualHotelContacts_EB", "KEYSHotel");
	SW.EnterValue("ResconECCA_IndividualArrivalDate_EB","13/Feb/2017");
	SW.EnterValue("ResconECCA_IndividualInviteemail_EB", "shalini.jaikumar@starwoodhotels.com");
	SW.EnterValue("ResconECCA_IndividualNotifyemail_EB", "shalini.jaikumar@accenture.com");
	SW.Click("ResconECCA_IndividualFinish_BT");
	//CHANNELS.CloseBrowser();

	//SMOKE01_01_OutlookPassword pwd = new SMOKE01_01_OutlookPassword();
	//String password = pwd.getPassword(EventName); 
	//ECCA form submission

	SW.LaunchBrowser(Environment.ECCA);
	SW.EnterValue("ResconECCA_LoginUsername_EB", SW.TestData("ECCA_UserName"));
	SW.EnterValue("ResconECCA_LoginPassword_EB", password);
	//SW.DropDown_SelectByText(("ResConECCA_IndividualLanguageSelection_DD"), "English (United States)");
	SW.Click("ResconECCA_Loginbutton_BT");
	SW.EnterValue("ResconECCA_IndividualSubmissionRetriveConfirmationNo_EB",SW.TestData("RetrieveComfirmationNumber"));
	SW.EnterValue("ResconECCA_IndividualSubmissionRetriveLastName_EB",SW.TestData("RetrieveGuestLastName"));
	SW.Click("ResconECCA_IndividualSubmissionRetriveButton_BT");
	SW.Click("ResconECCA_IndividualSubmissionADDGuestButton2_LK");
	//Manual entry
	SW.Click("ResconECCA_IndividualSubmissionADDGuestButton_LK");
	SW.EnterValue("ResconECCA_IndividualSubmissionManualConfirmationNo_EB", SW.TestData("ManualEntryConfirmationNo"));
	SW.EnterValue("ResconECCA_IndividualSubmissionManualFirstName_EB", SW.TestData("ManualEntryFirstName"));
	SW.EnterValue("ResconECCA_IndividualSubmissionManualLastName_EB", SW.TestData("ManualEntryLastName"));
	SW.EnterValue("ResconECCA_IndividualSubmissionManualArrivalDate_EB", SW.TestData("ManualEntryArrivalDate"));
	SW.EnterValue("ResconECCA_IndividualSubmissionManualDepartureDate_EB", SW.TestData("ManualEntryDepartureDate"));
	SW.Click("ResconECCA_IndividualSubmissionCCRoom&TaxChargesCheckBox_CB");
	SW.Click("ResconECCA_IndividualSubmissionSaveGuestButton_BT");
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
	SW.EnterValue("ResconECCA_IndividualSubmissionCCZip_BT", SW.TestData("CCZipCode"));
	SW.DropDown_SelectByText("ResconECCA_IndividualSubmissionCCCountry_BT", "India");
	SW.DropDown_SelectByValue("ResconECCA_IndividualSubmissionCCState_BT", "string:kar");
	SW.EnterValue("ResconECCA_IndividualSubmissionCCTelephone_BT",SW.TestData("CCTelephone"));
	SW.EnterValue("ResconECCA_IndividualSubmissionCCEmailAddress_BT", SW.TestData("CCEmailAddress"));
	SW.EnterValue("ResconECCA_IndividualSubmissionCCFullName_BT", SW.TestData("CCFullNameOnCard"));
	SW.Click("ResconECCA_IndividualSubmissionSubmitButton_BT");	
}

@AfterClass
public void EndTest(){
	Reporter.StopTest();		
}

}
