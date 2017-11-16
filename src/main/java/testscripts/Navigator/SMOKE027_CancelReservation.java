package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE027_CancelReservation {
	CHANNELS SW = new CHANNELS();
	String color="rgba(204, 204, 204, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	
	String GuestName = "vWorxe  YrJlpIaeCD";
	String phoneNum = "4364562857";
	
	@Test(priority=0)
	public void CancelReservation() {
		String confirmationNum = SW.TestData("ConfNum_createReservation");
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK");

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB"); // waiting for the confirmation Edit box
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", confirmationNum); //Entering the confirmation number
		SW.NormalClick("NavigatorReservationSearchPage_BeginSearch_BT"); //Search the reservation search
		//String confirmationLocator = "//span[text()='"+ confirmationNum + "']"; //CLicking 
		//SW.NormalClick(confirmationLocator);
		//Remediated for flow change
	/*	SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_ContactNameForCancel_EB"); //Waiting to provide the contact name
		SW.NormalClick("NavigatorReservationSearchPage_ContactNameForCancel_EB"); //CLicking on the contact name edit-box
		SW.EnterValue("NavigatorReservationSearchPage_ContactNameForCancel_EB", GuestName); //providing the Contact name
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_PhoneNumForCancel_EB");
		SW.NormalClick("NavigatorReservationSearchPage_PhoneNumForCancel_EB");
		SW.EnterValue("NavigatorReservationSearchPage_PhoneNumForCancel_EB", phoneNum); //providing phone number
		*/
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT"); 
		SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
		SW.HandleAlert(true);
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK");

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB");
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", confirmationNum); //Entering the confirmation number
		SW.NormalClick("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		if(SW.CompareText("Check Modify button color", color, SW.GetCSSValue("NavigatorReservationDetailSearchPage_Modify_BT", "color")))
			Environment.loger.log(Level.INFO, "The Modify Button color is disabled as "+SW.GetCSSValue("NavigatorReservationDetailSearchPage_Modify_BT", "color"));
		else{
			Environment.loger.log(Level.ERROR,"Modify button is not disabled");
			SW.FailCurrentTest("Validation Fails in checking Modify Button");
		}
		if(SW.CompareText("Check Modify button color", color, SW.GetCSSValue("NavigatorReservationDetailSearchPage_Cancel_BT", "color")))
			Environment.loger.log(Level.INFO, "The Cancel Button color is disabled as "+SW.GetCSSValue("NavigatorReservationDetailSearchPage_Cancel_BT", "color"));
		else{
			Environment.loger.log(Level.ERROR,"Cancel button is not disabled");
			SW.FailCurrentTest("Validation Fails in checking Cancel Button");
		}
		/*String errorMessage = SW.GetText("NavigatorReservationSearchPage_SerschNotFoundConfNumMsg_FT");
		String expectedMsg= " No results found.  Refine search criteria and try again.";
		SW.CompareText("ErrorMsg_", expectedMsg, errorMessage);*/

	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
