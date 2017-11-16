package testscripts.Navigator;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Locate NON-DCG res and validate the flow
 * TestCase Name: Locate NON-DCG res and validate the flow
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG33_LocateNON_DCGres_Validateflow {
	CHANNELS SW = new CHANNELS();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=0)
	public void LocateReservation(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservatiobSearchPage_ByName_LK"); // clicking the By name link
		SW.EnterValue("NavigatorReservationSearchPage_LastName_EB", SW.TestData("ReservationLastName")); //Entering value and pressing TAB
		SW.NormalClick("NavigatorReservationSearchPage_WildCardSearch_CB"); //Clicking the WildCard search
		SW.NormalClick("NavigatorReservationSearchPage_LocationProperty_EB");
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB", SW.TestData("ReservationProperty") + Keys.ENTER );
		SW.EnterValue("NavigatorReservationSearchPage_LocationProperty_EB",  Keys.ENTER ); //Entering value for the property or location
		SW.Click("NavigatorReservationSearchPage_FromDate_EB"); //Clicking on the from date
		SW.EnterValue("NavigatorReservationSearchPage_FromDate_EB", SW.TestData("ReservationArrivalDate")+ Keys.TAB); // entering the from date
		SW.ClearValue("NavigatorReservationSearchPage_ToDate_EB"); //Clearing the to date
		SW.EnterValue("NavigatorReservationSearchPage_ToDate_EB", SW.TestData("ReservationDepDate")+ Keys.TAB); // entering the to date, same as booking date
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Clicking on search
		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_GuestNameByPhoneSearch_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Environment.loger.log(Level.INFO,"Reservation Found by name tab");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.Wait(5);
			if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
				SW.Click("NavigatorReservationSearchPage_Ack_BT");
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		}
		else{
			Environment.loger.log(Level.ERROR,"Reservation not Found by name tab");
			SW.FailCurrentTest("Validation Fails in checking Reservation card");
		}
	}
	@Test(priority=1 ,dependsOnMethods="LocateReservation")
	public void sendPropCom(){
		//Actual Values
				String actualConfNum=SW.TestData("ConfNum_LocateGuest");
				String SPGnum = SW.GetText("NavigatorReservationDetailSearchPage_SPGnum_DT");
				String actualSPGnum = SPGnum.substring(SPGnum.indexOf(":")+1).trim();
				String ArrivalDay = SW.GetText("NavigatorReservationDetailSearchPage_ArrivalDate_DT");
				String actalArrivalDate = ArrivalDay.substring(ArrivalDay.indexOf("-")+1).trim();
				String DepartDay = SW.GetText("NavigatorReservationDetailSearchPage_DepartDate_DT");
				String actualDepartDate = DepartDay.substring(DepartDay.indexOf("-")+1).trim();
				String actualPropertyID = SW.GetText("NavigatorReservationDetailSearchPage_PropertyID_DT");
				//Navigating to Prop Comm
				SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
				SW.Click("NavigatorHomePage_PropComm_LK"); //Clicking on Prop Comm
				SW.SwitchToWindow(1); //Switching to the new window
				SW.WaitForPageload();
				if(SW.ObjectExists("NavigatorSearchPage_StarguestCSSAPassword_EB")){
					SW.EnterValue("NavigatorSearchPage_StarguestCSSAPassword_EB", SW.TestData("NavigatorPassword"));
					SW.NormalClick("NavigatorSearchPage_StarguestCSSALogin_BT");
				}
				String ResConfNumber = SW.GetText("NavigatorReservationStarGuestPage_ResConfNumber_DT");
				String ArrivalDate = SW.GetText("NavigatorReservationStarGuestPage_ArrivalDate_DT").trim();
				String DepartureDate = SW.GetText("NavigatorReservationStarGuestPage_DepartDate_DT").trim();
				String PropertyID = SW.GetText("NavigatorReservationStarGuestPage_PropertyID_DT");
				String expectedPropertyID = (PropertyID.replace("(", "")).replace(")", "");
				String SPGNumber = SW.GetAttributeValue("NavigatorReservationStarGuestPage_SPGNum_DT", "value");

				//Comparing values
				if(SW.CompareText("ConfNumber_verification", ResConfNumber, actualConfNum))
					Environment.loger.log(Level.INFO,"Reservation Confirmation number matched");
				else{
					Environment.loger.log(Level.ERROR,"Reservation Confirmation number not matched");
					SW.FailCurrentTest("Validation Fails in checking Reservation Confirmation number");
				}
				if(SW.CompareText("SPGnum_verification", SPGNumber, actualSPGnum))
				Environment.loger.log(Level.INFO,"Reservation Confirmation number matched");
				else{
					Environment.loger.log(Level.ERROR,"Reservation Confirmation number not matched");
					SW.FailCurrentTest("Validation Fails in checking Reservation Confirmation number");
				}
				if(SW.CompareText("ArrivalDate_verification", ArrivalDate, actalArrivalDate))
				Environment.loger.log(Level.INFO,"Reservation Confirmation number matched");
				else{
					Environment.loger.log(Level.ERROR,"Reservation Confirmation number not matched");
					SW.FailCurrentTest("Validation Fails in checking Reservation Confirmation number");
				}
				if(SW.CompareText("DEpartDate_verification", DepartureDate, actualDepartDate))
				Environment.loger.log(Level.INFO,"Reservation Confirmation number matched");
				else{
					Environment.loger.log(Level.ERROR,"Reservation Confirmation number not matched");
					SW.FailCurrentTest("Validation Fails in checking Reservation Confirmation number");
				}
				if(SW.CompareText("PropertyID_verification", expectedPropertyID, actualPropertyID))
				Environment.loger.log(Level.INFO,"Reservation Confirmation number matched");
				else{
					Environment.loger.log(Level.ERROR,"Reservation Confirmation number not matched");
					SW.FailCurrentTest("Validation Fails in checking Reservation Confirmation number");
				}

	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
