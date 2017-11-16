package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Clear Search
 * TestCase Name: US6539-Clear Search
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG71_ClearSearch {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,giftFname,giftLname;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
		giftFname=SW.RandomString(5);
		giftLname=SW.RandomString(5);
	}
	@Test(priority=0)
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num);
	}
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void IssueUpgradeAward(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.Click("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Paid Night Upgrades"); //Selecting the award type
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("NavigatorAwardPage_PaidUpgarde_LK");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.DropDown_SelectByIndex("NavigatorAwardPage_Reservation_DD", 1);
		SW.EnterValue("NavigatorAwardPage_GiftReceipFName", giftFname);
		SW.EnterValue("NavigatorAwardPage_GiftReceipLName", giftLname);
		SW.Click("NavigatorAwardPage_ClearStay_LK");
		//Checking the Stay details are cleared
		SW.CompareText("Clear the Conf number", "", SW.GetText("NavigatorAwardPage_ConfirmationNum_EB"));
		SW.CompareText("Clear the Property id", "", SW.GetText("NavigatorAwardPage_PropID_EB"));
		SW.CompareText("Clear the RatePlan id", "", SW.GetText("NavigatorAwardPage_RatePlan_EB"));
		SW.CompareText("Clear the Arrival date", "", SW.GetText("NavigatorAwardPage_StartDate_EB"));
		SW.CompareText("Clear the Depature Date", "", SW.GetText("NavigatorAwardPage_EndDate_EB"));
		SW.CompareText("Check the Gift Recipient First Name", giftFname, SW.GetText("NavigatorAwardPage_GiftReceipFName"));
		SW.CompareText("Check the Gift Recipient Last Name", giftLname, SW.GetText("NavigatorAwardPage_GiftReceipLName"));
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
