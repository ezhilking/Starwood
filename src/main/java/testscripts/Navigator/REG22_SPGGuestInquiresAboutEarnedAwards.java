package testscripts.Navigator;
/* Purpose		: This script for SPG Guest Inquires About Earned Awards
 * TestCase Name: SPG Guest Inquires About Earned Awards
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

public class REG22_SPGGuestInquiresAboutEarnedAwards {
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
	public void InquiresEarnedAwards()
	{
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga
		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); 
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");
		SW.NormalClick("NavigatorAwardPage_EarnedAwards_LK");
		SW.Click("NavigatorAwardPage_EarnedAwards_LK");
		if(SW.ObjectExists("NavigatorAwardPage_EarnedAwardsDetail_DT")){
			Environment.loger.log(Level.INFO, "The Earned award for the member are:");
			Environment.loger.log(Level.INFO, SW.GetText("NavigatorAwardPage_EarnedAwardsDetail_DT"));
		}else {
			Environment.loger.log(Level.INFO,"There is No Earned Award in Award Earned Page  PLEASE TRY WITH SOME OTHER MEMBER");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
