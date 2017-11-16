package testscripts.Navigator;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Validation Message - Mismatched Rate Plan Award Category
 * TestCase Name: US6539-Validation Message - Mismatched Rate Plan Award Category
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG87_ValidationMSGMismatchRatePlanAwrdCategory {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,ReservationNumber,RatePlanId,awardId;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		ReservationNumber=SW.TestData("AwardConfirmNumber");
		RatePlanId=SW.TestData("AwardResRatePlanID");
	}
	@Test(priority=0)
	public void MisMatchRatePlan(){
	SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
	SW.SelectCommunicationType();//selecting communication type
	SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
	SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
	SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB"); //WAiting for the confirmation tab to be present
	SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", ReservationNumber+ Keys.TAB); //Entering the confirmation number
	SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Search
	SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_Ack_BT"); //Wait for the acknoledgement
	SW.Click("NavigatorReservationSearchPage_Ack_BT"); //Click on Ack
	SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
	SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
	SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
	SW.NormalClick("NavigatorSearchPage_Award_LK"); 
	SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
	SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Free Night Awards"); //Selecting the award type
	SW.EnterValue("NavigatorAwardPage_AwardId_EB", RatePlanId);
	SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
	SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
	SW.NormalClick("//span[text()='"+RatePlanId+"-1N']");
	SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_ContactName_EB");
	SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
	SW.EnterValue("NavigatorAwardPage_ConfirmationNum_EB", SW.TestData("AwardConfirmNumber"));
	SW.EnterValue("NavigatorAwardPage_PropID_EB", SW.TestData("AwardResProp"));
	SW.EnterValue("NavigatorAwardPage_RatePlan_EB", "RACK");
	SW.EnterValue("NavigatorAwardPage_StartDate_EB", SW.TestData("AwardResStartDate"));
	SW.EnterValue("NavigatorAwardPage_EndDate_EB", SW.TestData("AwardResEndDate"));
	SW.Click("NavigatorInternalPointTransferPage_Order_BT");
	SW.CompareText("Comparing the error message", "Invalid rate plan id.", SW.GetText("NavigatorAwardPAge_SystemMessage_DT"));
}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
