package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for SPG Guest Checks Point Balance
 * TestCase Name: SPG Guest Checks Point Balance
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG47_SPGGuestChecksPointBalance {
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
	@Test(priority=0)
	public void checkPointBalance(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		String StarPoint=SW.GetText("NavigatorHomePage_StarPoints_DT");
		String points=StarPoint.replace(",", "").replace(" StarPoints", "").trim();
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		StarPoint = SW.GetText("NavigatorInternalPointTransferPage_Starpoints_DT");
		String expectedStarpoints =StarPoint.replace(",", "").trim();
		if(SW.CompareText("Comparing the Points", points, expectedStarpoints)){
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
			Environment.loger.log(Level.INFO,"The Point Balance of SPG Member is "+points);
		}else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
