package testscripts.Navigator;
/* Purpose		: This script for SPG Guest Asked For Upcoming Stay Property Details
 * TestCase Name: SPG Guest Asked For Upcoming Stay Property Details
 * Created By	: Roshan
 * Modified By	: Sharmila Begam
 * Modified Date: 24/02/17
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

public class REG42_SPGGuestAskedForUpcomingStayPropertyDetails 
{
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest()
	{
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test
	public void ValidateReservation(){

		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.Click("NavigatorHomePage_UpcommingStays_LK");
		SW.Click("NavigatorReservationSearchPage_CardConfirm_LK");
		SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");

		if(SW.ObjectExists("NavigatorReservationDetailSearchPage_ConfNum_DT")){
			Reporter.Write("Validating ReservationCard", "Card Available", "Card Available", "PASS");
			Environment.loger.log(Level.INFO,"Reservation confirmation number is Validated");
			Environment.loger.log(Level.INFO,"Reservation is validated");	
		}
		else
			Reporter.Write("Validating ReservationCard", "Card Avilable", "Card Available", "Fail");

	} 
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}
}