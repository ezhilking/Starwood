package testscripts.Navigator;

/* Purpose		: This script for SPG Guest Needs To Cancel a Reservation 
 * TestCase Name: SPG Guest Needs To Cancel a Reservation
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG70_SPGGuestNeedsToCancelReservation {
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
	public void LocateSPGMemberByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
		}
	}
	@Test(priority=1, dependsOnMethods="LocateSPGMemberByNumber")
	public void cancelUpComingReservation(){
		SW.WaitTillElementToBeClickable("NavigatorHomePage_UpcommingStays_LK");
		SW.Click("NavigatorHomePage_UpcommingStays_LK");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Reporter.Write("Reservation Card", "UpCome Reservation Found", "UpComing ReservationFound", "TRUE");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.Wait(3);
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
			SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
			if(!SW.IsAlertPresent()){
				SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
			}
			SW.HandleAlert(true);
			SW.Wait(3);
			SW.SwitchToFrame("SaratogaCreateReservationPage_Frame_FR");
			String CancelNum =SW.GetText("SaratogaReservationPage_CancelNumber_DT");
			if(SW.CompareTextContained("Your cancellation number is", CancelNum)){
				String can=CancelNum.trim().substring(24);
				Environment.loger.log(Level.INFO, "Cancellation Number has generated as"+can);
			}
			else {
				Environment.loger.log(Level.ERROR,"Cancellation number not generated");
			}
			SW.SwitchToFrame("");
		}
	}
	@Test(priority=2,dependsOnMethods="cancelUpComingReservation")
	public void checkHistoryLog(){
		SW.WaitTillElementToBeClickable("NavigatorHomePage_ReservationNTB_LK");
		SW.NormalClick("NavigatorHomePage_ReservationNTB_LK");
		SW.DoubleClick("NavigatorHomePage_ReservationNTB_LK");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
		SW.Click("NavigatorReservtionDetailPage_HistoryLog_LK");
		SW.DropDown_SelectByText("NavigatorReservationDetailPage_View_DD", "Cancel");
		SW.CompareTextContained("Compare the Log", "Cancel", SW.GetText("NavigatorReservationDetailPage_LogCancel_DT"));

	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}

