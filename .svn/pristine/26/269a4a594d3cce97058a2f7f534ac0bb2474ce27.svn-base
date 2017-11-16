package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Multiple rate plan
 * TestCase Name: multiple rate plan
 * Created By	: dhivya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG79_Multiplerateplan{
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
	public void IssueUpgradeAward1(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.Click("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Paid Night Upgrades"); //Selecting the award type
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("NavigatorAwardPage_PaidUpgarde2NT_LK");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.DropDown_SelectByText("NavigatorAwardPage_Reservation_DD",SW.TestData("MultiRateplanReservationNumber")); 
		SW.EnterValue("NavigatorAwardPage_RatePlan_EB",SW.TestData("MultiRatePlan2")); 
		SW.Click("NavigatorInternalPointTransferPage_Order_BT");
		SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_AwardOrder_DT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			Reporter.Write("Check the list of award","Award Displayed", "Award Displayed", "PASS");
			String awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else{
			Reporter.Write("Check the list of award","Award Displayed", "Award Displayed", "FAIL");

		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}