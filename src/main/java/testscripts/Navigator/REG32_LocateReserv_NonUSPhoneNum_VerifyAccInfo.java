package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Locate Reservation With Non-US Phone Num And Verify Some Account Info
 * TestCase Name: Locate Reservation With Non-US Phone Num And Verify Some Account Info
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG32_LocateReserv_NonUSPhoneNum_VerifyAccInfo {
	CHANNELS SW = new CHANNELS();
	String Partnercolor="rgba(78, 129, 194, 1)";
	String PartnerBeforeColor="rgba(11, 40, 64, 1)";
	String color="rgba(86, 188, 76, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=0)
	public void LocateReservationByPhoneNum(){
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
		SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
		SW.Wait(10);
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		SW.Click("NavigatorResrvationDetailPage_Close_BT");
	}
	@Test(priority=1,dependsOnMethods="LocateReservationByPhoneNum")
	public void checkPatnership(){
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorSearchPage_Partnership_LK")){
			Environment.loger.log(Level.INFO, "PartnerShip is available for this member");
			String sColor=SW.GetCSSValue("NavigatorSearchPage_Partnership_LK", "color");
			Environment.loger.log(Level.INFO, "PartnerShip Benifits are enabled"+sColor);
			SW.MoveToObject("NavigatorSearchPage_Partnership_LK");
			sColor=SW.GetCSSValue("NavigatorSearchPage_Partnership_LK", "color");
			if(SW.CompareText( Partnercolor, sColor))
				Environment.loger.log(Level.INFO, "PartnerShip Benifits is available for this member");
			else 
				Environment.loger.log(Level.INFO,"No PartnerShips Benifits are available for this member");	
		}else {
			Environment.loger.log(Level.INFO,"No PartnerShips are available for this member");	
			SW.FailCurrentTest("Validation Fails in checking Expected company name");
		}
		String member=SW.GetText("NavigatorSearchPage_ExternalMember_DT");
		Environment.loger.log(Level.INFO,"Available partner ships are "+member);	
		
	}
	@Test(priority=2,dependsOnMethods="checkPatnership")
	public void GoldBenifitTracker(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable	
		String getNumOfStays = SW.GetText("NavigatorHomePage_NumStays_DT")+" YTD"; //Getting number of stays and concact YTD
		String getNumNights = SW.GetText("NavigatorHomePage_NumNights_DT") + " YTD"; //Getting NUmber of nights and concat YTD 
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Benefit_LK");
		SW.Click("NavigatorSearchPage_Benefit_LK"); //Clicking the benefit button
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_NumOfStays_DT");
		String actualNumOfStays = SW.GetText("NavigatorSearchPage_NumOfStays_DT");
		String actualNumOfNights = SW.GetText("NavigatorSearchPage_NumOfNights_DT");
		if(SW.CompareText("compareNumOfStays", getNumOfStays, actualNumOfStays)){
			Environment.loger.log(Level.INFO, "The Number of Stays are matched");
		}else {
			Environment.loger.log(Level.ERROR,"Number of Stays not Matched");
			SW.FailCurrentTest("Validation Fails in checking Number of Stay");
		}		
		if(SW.CompareText("compareNumOfNights", getNumNights, actualNumOfNights)){
			Environment.loger.log(Level.INFO, "The Number of Stays are matched");
		}else {
			Environment.loger.log(Level.ERROR,"Number of Stays not Matched");
			SW.FailCurrentTest("Validation Fails in checking Number of Stay");
		}	
		if(SW.ObjectExists("NavigatorSearchPage_Platinum_DT")){
			String scolor=SW.GetCSSValue("NavigatorSearchPage_Platinum_DT", "background-color");
			String Benifits=SW.GetText("NavigatorSearchPage_Platinum_DT");
			if(SW.CompareText("Checking enabled color", color, scolor) && SW.CompareTextContained("Compare Benifit name ", "Gold Preferred Benefits", Benifits)){
				Environment.loger.log(Level.INFO, "Gold Benefits are enabled" );
			}else {
				Environment.loger.log(Level.ERROR,"There is no Gold Prefered benifit for the member");
				SW.FailCurrentTest("Validation Fails in checking Gold benifits");
			}	
		}else {
			Environment.loger.log(Level.ERROR,"There is no Gold Prefered benifit for the member");
			SW.FailCurrentTest("Validation Fails in checking platinum benifits");
		}	
		SW.Click("NavigatorSearchPage_BenefitCancel_BT");
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
