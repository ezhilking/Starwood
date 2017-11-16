package testscripts.resCon;
/* Purpose		: Validating ECCA GroupEnglish_Property user
 * TestCase Name: Verify viewing the eCCA form contents_Group Type_English Language_CCFORM View Role_Property User
 * Created By	: Shalini.jaikumar
 * Modified By	:
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Level;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE03_04_ECCAGroupEnglishPropertyUser 
{CHANNELS SW = new CHANNELS();
String Number;
String cnfcNumber;
String EventName = SW.RandomString(6);
String lastName = SW.RandomString(5);
String password;

@BeforeClass
public void StartTest(){
	Environment.Tower = "CHANNELS";
	Reporter.StartTest();
	Environment.SetBrowserToUse("FF");
	SW.LaunchBrowser(Environment.RESCON);
	//String EventName = SW.RandomString(6);
}
@Test(priority=1)
public void CreateECCAGroupEnglishInvite(){
	SW.ResConLogin(SW.TestData("SGP_UserNamePropertyuser"), SW.TestData("Rescon_PasswordPropertyuser"));
	SW.MoveToObject("ResconHomepage_ECCA_BT");
	SW.Click("ResconHomepage_ECCANewInvite_BT");
	SW.DropDown_SelectByText("ResconECCA_InviteType_DD", "Group");
	SW.DropDown_SelectByText("ResconECCA_LanguageType_DD", "English (United States)");
	SW.Click("ResconECCA_Next_BT");
	SW.DropDown_SelectByValue("ResconECCA_GroupSelectProperty_DD", "1513");
	SW.EnterValue("ResconECCA_GroupFunctionname_EB", EventName);
	SW.EnterValue("ResconECCA_GroupServicesalesmanager_EB", "test automation for GroupEnglish");
	SW.EnterValue("ResconECCA_GroupInviteemail_EB", "shalini.jaikumar@starwoodhotels.com");
	SW.EnterValue("ResconECCA_GroupNotifyemail_EB","shalini.jaikumar@accenture.com");
	SW.Click("ResconECCA_GroupFinish_BT");	
	password = SW.GetPassword(EventName);
	Reporter.WriteLog(Level.INFO,password); 
	//CHANNELS.CloseBrowser();
	//SMOKE01_02_OutlookPasswordForGroup pwd = new SMOKE01_02_OutlookPasswordForGroup();
	//String password = pwd.getPassword(EventName);
	//ECCA submission form 
	SW.LaunchBrowser(Environment.ECCA);
	SW.EnterValue("ResconECCA_LoginUsername_EB", SW.TestData("ECCA_UserName"));
	SW.EnterValue("ResconECCA_LoginPassword_EB", password);
	SW.Click("ResconECCA_Loginbutton_BT");

	SW.EnterValue("ResconECCA_GroupSubmissionEventDate_EB", "01/31/2017");
	SW.Click("ResconECCA_GroupSubmissionEventChargesBanquet/Catering Event Charges_CB");
	SW.Click("ResconECCA_GroupSubmissionEventChargesRoom & Tax Charges_CK");
	SW.Click("ResconECCA_GroupSubmissionAddGuest_LK");
	SW.EnterValue("ResconECCA_GroupSubmissionConfirmationNumber_EB", SW.TestData("RetrieveComfirmationNumber"));
	SW.EnterValue("ResconECCA_GroupSubmissionRetriveLastName_EB",SW.TestData( "RetrieveGuestLastName"));
	SW.Click("ResconECCA_GroupSubmissionRetriveGuest_BT");
	SW.Click("ResconECCA_GroupSubmissionSaveGuest_BT");
	SW.Click("ResconECCA_GroupSubmissionAddGuest_LK");
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
}
@AfterClass
public void EndTest(){
	Reporter.StopTest();

}
}