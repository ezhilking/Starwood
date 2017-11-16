package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for User Is Able To Manually Enter Stay Award Info
 * TestCase Name: US6539-User Is Able To Manually Enter Stay Award Info
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG63_UserIsAbleToManually_EnterStayAwardInfo {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test(priority=1)
	public void LocateSPGMember(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");

		}
	}
	@Test(priority=2,dependsOnMethods="LocateSPGMember")
	public void ValidateMismatchLOS(){
		SW.Click("NavigatorSearchPage_ShowGuest_FT");	
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Paid Night Upgrades"); //Selecting the award type
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("NavigatorAwardPage_PaidUpgarde_LK");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.EnterValue("NavigatorAwardPage_ConfirmationNum_EB", SW.TestData("AwardConfirmNumber"));
		SW.EnterValue("NavigatorAwardPage_PropID_EB", SW.TestData("AwardResProp"));
		SW.EnterValue("NavigatorAwardPage_RatePlan_EB", SW.TestData("AwardResRatePlanID"));
		SW.EnterValue("NavigatorAwardPage_StartDate_EB", SW.TestData("AwardResStartDate"));
		SW.EnterValue("NavigatorAwardPage_EndDate_EB", SW.TestData("AwardResEndDate"));
		SW.Click("NavigatorInternalPointTransferPage_Order_BT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			Reporter.Write("Order Sucess", "Order number generated", "Order number generated", "PASS");
			String awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else 
			Reporter.Write("Order Failed", "Order number not generated", "Order number not generated", "Fail");
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
