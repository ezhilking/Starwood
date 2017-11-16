package testscripts.Navigator;

/* Purpose		: This script for Guest Has Upcoming Reservation and Wants To Get Confirmation Details
 * TestCase Name: Guest Has Upcoming Reservation and Wants To Get Confirmation Details
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG29_GuestUpcomingReservation_GetConfirmationDetails {
	CHANNELS SW = new CHANNELS();
	String exp_mailMessage;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		 exp_mailMessage = "Email successfully sent";
	}
	@Test(priority=0)
	public void LocateReservation(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK");

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB");
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", SW.TestData("ConfNum_LocateGuest")+ Keys.TAB);
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorHomePage_UpcommingStays_LK");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Environment.loger.log(Level.INFO,"Reservation Found by name tab");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.Wait(3);
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		}
	}
	@Test(priority=1,dependsOnMethods="LocateReservation")
	public void ResendConf(){
		SW.CheckBox("NavigatorReservationDetailSearchPage_GuestEmail_CB", "ON");
		SW.NormalClick("NavigatorReservationDetailSearchPage_ResendEmail_LK"); //Click is not working
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		String mailMessage = SW.GetText("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		if(SW.CompareText("mailMessage_comparision", exp_mailMessage, mailMessage))
			Environment.loger.log(Level.INFO,"ReConfirmation sent Sucessfully");
		else{
			Environment.loger.log(Level.ERROR,"Reservation not Found by name tab");
			SW.FailCurrentTest("Validation Fails in checking Reservation card");
		}
	}
	@Test(priority=2, dependsOnMethods="ResendConf")
	public void removeCreditCard(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.NormalClick("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.CheckBox("NavigatorEditPage_CreditCardRemove_CB", "ON");
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.WaitTillInvisibilityOfElement("NavigatorSearchPage_SaveInfoCardChanges_BT");
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
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

