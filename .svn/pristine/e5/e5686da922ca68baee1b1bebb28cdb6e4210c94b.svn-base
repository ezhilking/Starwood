package testscripts.Navigator;
/* Purpose		: This script for Guest Needs To Update Web Password And Modify Email On Profile
 * TestCase Name: Guest Needs To Update Web Password And Modify Email On Profile
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG24_UpdateWebPasswordAndModifyEmailOnProfile {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String sEmailId;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("WebUserID");	
		sEmailId="sample@abc.com";
	}
	@Test(priority=0)
	public void updateWebPassword(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_AccAccessInfo_LK");	
		SW.NormalClick("NavigatorSearchPage_AccAccessInfo_LK");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_AccAccessMail_DT");
		if(SW.CompareTextContained("Compare mail sent", "Email Sent", SW.GetText("NavigatorSearchPage_AccAccessMail_DT")))
			Environment.loger.log(Level.INFO, "Mail has sent Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Unable to sent a mail for this user");
			SW.FailCurrentTest("Validation Fails in checking mail sent  Success message");
		}
	}
	@Test(priority=1)
	public void modifyPrimaryEamail(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable
		SW.EnterValue("NavigatorSearchPage_PrimaryEmail_EB", sEmailId);
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
		if(SW.CompareTextContained("CompareAddress1",sEmailId,SW.GetText("NavigatorSerachPage_HomeLocationEmailID_DT")))
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
