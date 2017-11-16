package testscripts.Navigator;
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Locate res by associate by entering different userID
 * TestCase Name: Locate res by associate by entering different userID
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG26_LocateResBuAssociate_EnteringDiffUserID {
	CHANNELS SW = new CHANNELS();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		
	}
	@Test(priority=0)
	public void LocateResByDiffUserID(){
		SW.NavigatorLogin(SW.TestData("AssociateUserID"),SW.TestData("AssociateUserPwd")); //Login into Saratoga
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_CallCountry_BT");
		SW.EnterValue("NavigatorHomePage_CallCountry_EB", "US"+Keys.ENTER);
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link
		SW.Click("NavigatorReservationSearchPage_Associate_LK"); //Clicking on the Associate TAB
		SW.EnterValue("NavigatorHomePage_UserID_EB", "testtes14");
		SW.DropDown_SelectByText("NavigatorReservationSearchPage_AssociateBookingDate_DD", "Last Reservation Booked");
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT");
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_ConfirmCard_LK");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Environment.loger.log(Level.INFO, "Resevation Card has found");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
			SW.Click("NavigatorResrvationDetailPage_Close_BT");
			Environment.loger.log(Level.INFO, "Resevation details get closed");// closing the reservation card
			SW.Wait(3);
			SW.NormalClick("NavigatorHomePage_ReservationClose_BT");//closing the reservation search
			Environment.loger.log(Level.INFO, "Resevation Results are closed");
			SW.Wait(3);
			SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
			SW.Click("NavigatorHomePage_ReservationResult_LK");//getting the result again
			Environment.loger.log(Level.INFO, "Getting the Search result again");
			SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.Click("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		}else {
			Environment.loger.log(Level.ERROR,"No Reservation has created ");
			SW.FailCurrentTest("Validation Fails in checking Reservation Available");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
