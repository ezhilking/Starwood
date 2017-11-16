package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE022_ResendConfirmationFromNavigator {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	String exp_mailMessage = "Email successfully sent";
	@Test
	public void ResendConfirmation()  {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.EnterValue("NavigatorReservationSearchPage_SPGNum_EB", SW.TestData("SPGNum_ModifyReservation"));
		//SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", SW.TestData("ConfNum_LocateGuest")); //Entering the confirmation number from test data
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.Click("NavigatorReservationSearchPage_CardConfirm_LK");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
	/*	SW.CheckBox("NavigatorSearchPage_CheckVerification_CB","ON");*/
		SW.EnterValue("NavigatorReserationPage_ResendEmail_EB", "test@test.com");
		SW.NormalClick("NavigatorReservationPage_PreferLangGuest_DD");
		SW.EnterValue("NavigationReservationPage_PrefLangGuest_EB", "ENGLISH"+Keys.ENTER) ;
		SW.CheckBox("NavigatorReservationDetailSearchPage_GuestEmail_CB", "ON");
		SW.NormalClick("NavigatorReservationDetailSearchPage_ResendEmail_LK"); //Click is not working
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		SW.Wait(3);
		String mailMessage = SW.GetText("NavigatorReservationDetailSearchPage_ResendEmailMessage_FT");
		SW.CompareText("mailMessage_comparision", exp_mailMessage, mailMessage);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
