package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Locate Last Booked Reservation By Associate And Cancel That Reservation
 * TestCase Name: Locate Last Booked Reservation By Associate And Cancel That Reservation
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG59_LocLastBookReservByAssociate_CancelReserv {
	CHANNELS SW = new CHANNELS();
	String exp_mailMessage = "Email successfully sent";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=0)
	public void LocateReservationByAssociate(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK");
		SW.Click("NavigatorReservationSearchPage_Associate_LK");
		SW.DropDown_SelectByText("NavigatorReservationSearchPage_AssociateBookingDate_DD", "Last Reservation Booked");
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_ConfirmCard_LK");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Reporter.Write("Getting ReservationCard", "Reservation Confirm Card", "Reservation Confirm Card`" , "PASS");
			Environment.loger.log(Level.INFO, "Resevation Card has found");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		}
		else {
			Environment.loger.log(Level.ERROR,"No Reservation has created ");
			Reporter.Write("Getting ReservationCard", "Reservation Confirm Card", "Reservation Confirm Card`" , "FAIL");
		}
	}
	@Test(priority=1, dependsOnMethods="LocateReservationByAssociate")
	public void cancelReservation(){
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
		if(!SW.IsAlertPresent()){
			SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
		}
		SW.HandleAlert(true);
		SW.Wait(3);
		SW.SwitchToFrame("SaratogaCreateReservationPage_Frame_FR");
		String CancelNum =SW.GetText("SaratogaReservationPage_CancelNumber_DT");
		if(SW.CompareTextContained("Your cancellation number is", CancelNum)){
			String can=CancelNum.trim().substring(24);
			Environment.loger.log(Level.INFO, "Cancellation Number has generated as"+can);
		}
		else {
			Environment.loger.log(Level.ERROR,"Cancellation number not generated");
		}
		SW.SwitchToFrame("");
	}
	@Test(priority=2,dependsOnMethods="cancelReservation")
	public void resendConfCancelREs(){
		SW.WaitTillElementToBeClickable("NavigatorHomePage_ReservationNTB_LK");
		SW.NormalClick("NavigatorHomePage_ReservationNTB_LK");
		SW.DoubleClick("NavigatorHomePage_ReservationNTB_LK");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		SW.EnterValue("NavigatorReserationPage_ResendEmail_EB", "test@test.com");
		SW.NormalClick("NavigatorReservationPage_PreferLangGuest_DD");
		SW.EnterValue("NavigationReservationPage_PrefLangGuest_EB", "ENGLISH"+Keys.ENTER) ;
		SW.CheckBox("NavigatorReservationDetailSearchPage_GuestEmail_CB", "ON");
		SW.NormalClick("NavigatorReservationDetailSearchPage_ResendEmail_LK"); //Click is not working
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		String mailMessage = SW.GetText("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		SW.CompareText("mailMessage_comparision", exp_mailMessage, mailMessage);
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
