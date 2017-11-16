package testscripts.Navigator;
/* Purpose		: This script for Issue a Airline Transfer Award For The Guest With External Marketing Programs
 * TestCase Name: Issue a Airline Transfer Award For The Guest With External Marketing Programs
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

public class REG25_Issue_AirlineTransferAwardTheGuestWithEMP {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String AwardID,sPoints;
	String awardId;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("AirLineMember");
		AwardID="CHINAEAS";// china eastern award id
		sPoints="3000";
	}
	@Test(priority=0)
	public void AirLineTransferAward(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))// controlling the alert
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.Wait(5);
		SW.Click("NavigatorSearchPage_ShowGuest_FT");	
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Airline/Travel Transfers"); //Selecting the award type
		SW.EnterValue("NavigatorAwardPage_AwardId_EB", AwardID);
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");
		SW.Click("NavigatorAwardPage_ChearsAward_EB");
		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "TEST");
		SW.EnterValue("NavigatorInternalPointTransferPage_PointsToTransfer_EB", sPoints);
		SW.EnterValue("NavigatorAwardPage_FreqFlyerNumber_EB", SW.TestData("ExternalAirlineMember"));
		SW.Click("NavigatorInternalPointTransferPage_Order_BT");
		SW.WaitTillElementToBeClickable("NavigatorInternalPointTransferPage_AwardOrder_DT");
		if(SW.ObjectExists("NavigatorInternalPointTransferPage_AwardOrder_DT")){
			 awardId=SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
			 awardId=awardId.substring(awardId.indexOf(":")+1).trim();
			SW.Click("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
			Environment.loger.log(Level.INFO, "The Award has orderd"+awardId);
		}else {
			Environment.loger.log(Level.ERROR,"Award order has not created ");
			SW.FailCurrentTest("Validation Fails in checking Award Id Creation");
		}
		SW.NormalClick("NavigatorAwardPage_AwardHistory_LK");
		SW.Click("NavigatorAwardPage_AwardHistory_LK");
		if(SW.ObjectExists("//div[@class='awards-results history-results']//div[@class='results-body']//div//span[text()='"+awardId+"']"))
		{
			Environment.loger.log(Level.INFO, "The Award Id is present in Award history");
			SW.NormalClick("//div[@class='awards-results history-results']//div[@class='results-body']//div//span[text()='"+awardId+"']");
			if(SW.CompareTextContained("Compare Award id", AwardID, SW.GetText("NavigatorAwardPage_AwardHistoryID_DT")))
				Environment.loger.log(Level.INFO, "The Award Id are same in Award history");
			else {
				Environment.loger.log(Level.ERROR,"Mismatch of award id in award history page ");
				SW.FailCurrentTest("Validation Fails in checking Award Id in history");
			}
			if(SW.CompareTextContained("Compare point transfered", sPoints, SW.GetText("NavigatorAwardPage_PointTransfer_DT")))
				Environment.loger.log(Level.INFO, "The Point transfered are same in Award history");
			else {
				Environment.loger.log(Level.ERROR,"Mismatch of Points transfered in award history page ");
				SW.FailCurrentTest("Validation Fails in checking Points in history");
			}
		}else {
			Environment.loger.log(Level.ERROR,"No Such award in Award History Page");
			SW.FailCurrentTest("Validation Fails in checking Award in history");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
