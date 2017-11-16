package testscripts.Navigator;
/* Purpose		: This script for Load Previous Reservation From Navigator Previous Stays Tab and validate
 * TestCase Name: Load Previous Reservation From Navigator Previous Stays Tab
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

public class REG18_LoadPrevCancReservNavigatorPrevStaysTab {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String color="rgba(204, 204, 204, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test(priority=0)
	public void loadPreviousReservation(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");//Waiting for the GuestName to be clickable
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorSearchPage_PreviousStay_LK");
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_ConfirmCard_LK");
		SW.Click("NavigatorReservationSearchPage_ConfirmCard_LK");
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
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
