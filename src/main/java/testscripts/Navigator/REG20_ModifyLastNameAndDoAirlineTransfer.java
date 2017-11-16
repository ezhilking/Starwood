package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Guest Wants To Modify Last Name And Do Airline Transfer By Manually Entering Freq Flyer Number
 * TestCase Name: Guest Wants To Modify Last Name And Do Airline Transfer By Manually Entering Freq Flyer Number
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG20_ModifyLastNameAndDoAirlineTransfer {
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
		sPoints="2500";
	}
	@Test(priority=0)
	public void modifyLastName(){
		String sLastname="TEST";
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.Wait(5);
		SW.Click("NavigatorSearchPage_ShowGuest_FT");	
		SW.NormalClick("NavigatorSearchPage_EditInfoCard_LK");
		SW.NormalClick("NavigatorEditPage_LastName_EB");
		SW.EnterValue("NavigatorEditPage_LastName_EB", sLastname);
		SW.Click("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.WaitTillInvisibilityOfElement("NavigatorSearchPage_SaveInfoCardChanges_BT");
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		String GuestName=SW.GetText("NavigatorSearchPage_FullName_DT");
		if(SW.CompareTextContained("Compare last name updated",sLastname,GuestName))
			Environment.loger.log(Level.INFO, "The Last Name is reflecting in Profile Page!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated with last name");
			SW.FailCurrentTest("Validation Fails in checking profile Last name validation");	
		}
	}
	@Test(priority=1, dependsOnMethods="modifyLastName")
	public void doAirlineTransfer(){
		
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
		SW.WaitTillElementToBeClickable("//div[@class='awards-results history-results']//div[@class='results-body']//div//span[text()='"+awardId+"']");
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
