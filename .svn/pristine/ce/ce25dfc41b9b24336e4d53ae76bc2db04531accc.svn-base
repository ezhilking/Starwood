package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Locate Reservation With Name And Resend Confirmation
 * TestCase Name: Locate Reservation With Name And Resend Confirmation
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG28_LocateReserWithName_ResendConfirm {
	CHANNELS SW = new CHANNELS();
	String exp_mailMessage = "Email successfully sent";
	String Arrivaldate,DepDate;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		Arrivaldate="27Jan2017";
		DepDate="28Jan2017";
	}
	@Test(priority=0)
	public void LocateReservByName(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservatiobSearchPage_ByName_LK"); // clicking the By name link
		SW.EnterValue("NavigatorReservationSearchPage_LastName_EB", SW.TestData("ReservationLastName")); //Entering value and pressing TAB
		SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", SW.TestData("ReservationProperty") );
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_SelectProperty_DT");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB",  Keys.ENTER ); //Entering value for the property or location
		SW.Click("NavigatorReservationSearchPage_FromDate_EB"); //Clicking on the from date
		SW.EnterValue("NavigatorReservationSearchPage_FromDate_EB", SW.TestData("ReservationArrivalDate")+ Keys.TAB); // entering the from date
		SW.ClearValue("NavigatorReservationSearchPage_ToDate_EB"); //Clearing the to date
		SW.EnterValue("NavigatorReservationSearchPage_ToDate_EB", SW.TestData("ReservationDepDate")+ Keys.TAB); // entering the to date, same as booking date
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Clicking on search
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Environment.loger.log(Level.INFO,"Reservation Found by name tab");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.Wait(10);
			if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
				SW.Click("NavigatorReservationSearchPage_Ack_BT");
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		}
		else{
			Environment.loger.log(Level.ERROR,"Reservation not Found by name tab");
		}

	}
	@Test(priority=1)
	public void ResendConfirmation(){
		SW.CheckBox("NavigatorReservationDetailSearchPage_GuestEmail_CB", "ON");
		SW.NormalClick("NavigatorReservationDetailSearchPage_ResendEmail_LK"); //Click is not working
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		String mailMessage = SW.GetText("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		if(SW.CompareText("mailMessage_comparision", exp_mailMessage, mailMessage))
			Environment.loger.log(Level.INFO,"ReConfirmation sent Sucessfully");
		else{
			Environment.loger.log(Level.ERROR,"Reservation not Found by name tab");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
