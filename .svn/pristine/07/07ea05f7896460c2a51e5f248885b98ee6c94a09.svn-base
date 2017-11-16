package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Locate Reservation By Number Tab And Modify Search To By Name Tab
 * TestCase Name: Locate Reservation By Number Tab And Modify Search To By Name Tab
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG27_LocReservByNumberTab_ModifySearchToByNameTab {
	CHANNELS SW = new CHANNELS();
	String sLastname;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		sLastname="TEST";
	}
	@Test(priority=0)
	public void locateResevationByNumTab(){
	SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
	SW.SelectCommunicationType();//selecting communication type
	SW.NormalClick("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
	SW.NormalClick("NavigatorHomePage_SearchReservation_LK"); //CLicking on the search reservation link
	SW.Click("NavigatorReservationSearchPage_CountryCode_FT");
	SW.EnterValue("NavigatorReservationSearchPage_CountryCode_EB",SW.TestData("CountryCode_LocateReservation")+ Keys.TAB);
	SW.EnterValue("NavigatorReservationSearchPage_PhoneNum_EB", SW.TestData("PhoneNum_LocateReservation"));
	SW.NormalClick("NavigatorReservationSearchPage_BeginSearch_BT");
	SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_ConfirmCard_LK");
	if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK"))
		Environment.loger.log(Level.INFO, "Resevation Card has found for the phone number");
	else {
		Environment.loger.log(Level.ERROR,"No Reservation has created ");
		SW.FailCurrentTest("Validation Fails in checking Reservation Available");
	}
	SW.Click("NavigatorReservationSearchPage_ShowAllTab_BT");
	SW.Click("NavigatorReservationSearchPage_Clear_BT");
	SW.Click("NavigatorReservatiobSearchPage_ByName_LK"); // clicking the By name link

	SW.EnterValue("NavigatorReservationSearchPage_LastName_EB", SW.TestData("ReservationLastName")); //Entering value and pressing TAB
	SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB");
	SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", SW.TestData("ReservationProperty") + Keys.ENTER );
	SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB",  Keys.ENTER ); //Entering value for the property or location
	SW.Click("NavigatorReservationSearchPage_FromDate_EB"); //Clicking on the from date
	SW.EnterValue("NavigatorReservationSearchPage_FromDate_EB", SW.TestData("ReservationArrivalDate") + Keys.TAB); // entering the from date
	SW.ClearValue("NavigatorReservationSearchPage_ToDate_EB"); //Clearing the to date
	SW.EnterValue("NavigatorReservationSearchPage_ToDate_EB", SW.TestData("ReservationDepDate") + Keys.TAB); // entering the to date, same as booking date
	SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Clicking on search
	SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
	SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
	SW.Wait(10);
	if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
		SW.Click("NavigatorReservationSearchPage_Ack_BT");
	SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
	SW.Click("NavigatorResrvationDetailPage_Close_BT");
	Environment.loger.log(Level.INFO, "Resevation details get closed");// closing the reservation card
	SW.Wait(3);
	}
	@Test(priority=1)
	public void ModifyProfileDetails(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
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
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
