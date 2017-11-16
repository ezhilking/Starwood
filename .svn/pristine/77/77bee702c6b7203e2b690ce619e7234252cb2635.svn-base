package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
/* Purpose		: This script for Issue a Speciality And Upgrade Award For SPG Guest
 * TestCase Name: Issue a Speciality And Upgrade Award For SPG Guest
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import functions.Environment;
import functions.Reporter;

public class REG49_IssueSpecialityAndUpgradeAwardForSPGGuest {
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
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}
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
		SW.Click("NavigatorInternalPointTransferPage_Order_BT");
		SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_AwardOrder_DT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			String awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else{
			Environment.loger.log(Level.ERROR,"Award order has not created ");
			SW.FailCurrentTest("Validation Fails in checking Award Id Creation");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
