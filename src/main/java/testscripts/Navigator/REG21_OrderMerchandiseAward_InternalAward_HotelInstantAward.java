package testscripts.Navigator;
/* Purpose		: This script for Guest Wants To Order Merchandise Award, Internal Award and Hotel Instant Award
 * TestCase Name: Guest Wants To Order Merchandise Award, Internal Award and Hotel Instant Award
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

public class REG21_OrderMerchandiseAward_InternalAward_HotelInstantAward {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,AwardID;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
		AwardID="TRANSFER";
	}
	@Test(priority=0)
	public void doMerchandiseAward(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		
		SW.Click("NavigatorSearchPage_ShowGuest_FT");	
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Merchandise Awards"); //Selecting the award type
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.Click("NavigatorAwardPge_AmazonGift_LK");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
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
	}
	@Test(priority=1)
	public void doInternalTransferAward(){
		String getInitialStarpoints = SW.GetText("NavigatorInternalPointTransferPage_Starpoints_DT"); //Getting initial points in string
		int pointsTransfered = Integer.parseInt(SW.TestData("SPGnum_PointToTransfer")); //Getting the points to be transfered from test data
		int actualInitialPoints = Integer.parseInt(getInitialStarpoints.replace(",", "")); //removing the , from points and converting to integer
		int  actualFinalPoints = (actualInitialPoints - pointsTransfered); //Getting final points after subtracting the points from initial and converting to string 

		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Internal Transfers"); //Selecting the award type
		SW.EnterValue("NavigatorAwardPage_AwardId_EB", AwardID);
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("NavigatorSearchPage_InternalPntTransfer_LK"); //Clicking on the selected award
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "testuser"); //Providing the contact name
		SW.EnterValue("NavigatorInternalPointTransferPage_PointsToTransfer_EB", SW.TestData("SPGnum_PointToTransfer")); //Providing the points to transfer
		SW.EnterValue("NavigatorInternalPointTransferPage_SPGnum_EB", SW.TestData("SPGmemReceivePointTransfer")); //Providing SPG number
		SW.NormalClick("NavigatorInternalPointTransferPage_MemberSearch_BT"); //Click on search
		SW.WaitTillPresenceOfElementLocated("NavigatorInternalPointTransferPage_ConfSPGnum_DT");
		String actualSPGnum = SW.GetText("NavigatorInternalPointTransferPage_ConfSPGnum_DT");
		SW.CompareText("SPG_memberreceipient_comp", SW.TestData("SPGmemReceivePointTransfer"), actualSPGnum);
		SW.CheckBox("NavigatorAwardPage_VerifiyAddress_CB", "ON");
		SW.NormalClick("NavigatorInternalPointTransferPage_Order_BT");
		SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_AwardOrder_DT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			String awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else {
			Environment.loger.log(Level.ERROR,"Award order has not created ");
			SW.FailCurrentTest("Validation Fails in checking Award Id Creation");
		}
		String getFinalStarpoints = SW.GetText("NavigatorInternalPointTransferPage_Starpoints_DT");
		int expectedStarpoints = Integer.parseInt(getFinalStarpoints.replace(",", ""));
		//Comparing points
		SW.CompareText("CompareTransferPoints", Integer.toString(expectedStarpoints), Integer.toString(actualFinalPoints));
	}
	@Test(priority=2)
	public void doHotelInstantAward(){
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Hotel Instant Awards"); //Selecting the award type
		SW.ClearValue("NavigatorAwardPage_AwardId_EB");
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.NormalClick("NavigatorAwardPage_HotelInstantAward_LK");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.EnterValue("NavigatorAwardPage_PropertyID_EB", "1965");
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
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
