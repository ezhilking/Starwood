package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Load a guest and issue the Hotel Instant Award then verify the award 
 * TestCase Name: Load A Guest And Issue Hotel Instant Award
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG16_LoadAGuestAndHotelInstantAward {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test(priority=0)
	public void HotelInstantAward()
	{
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Hotel Instant Awards"); //Selecting the award type
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("NavigatorAwardPage_HotelInstantAward_LK");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.EnterValue("NavigatorAwardPage_PropertyID_EB", "110");
		SW.Click("NavigatorInternalPointTransferPage_Order_BT");
		SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_AwardOrder_DT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			String awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else {
			Environment.loger.log(Level.ERROR,"Award order has not created ");
			SW.FailCurrentTest("Validation Fails in checking Award Id Creation");
		}
		SW.NormalClick("NavigatorAwardPage_EarnedAwards_LK");
		if(SW.ObjectExists("NavigatorAwardPage_EarnedAwardsDetail_DT")){
			Environment.loger.log(Level.INFO, "The Earned award"+SW.GetText("NavigatorAwardPage_EarnedAwardsDetail_DT"));
		}else {
			Environment.loger.log(Level.INFO,"No Earned Award in Award Earned Page");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
