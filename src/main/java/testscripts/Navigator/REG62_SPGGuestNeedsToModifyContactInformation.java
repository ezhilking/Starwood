package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: SPG member modify contact information
 * TestCase Name: REG62_SPGGuestNeedsToModifyContactInformation
 * Created By	: Roshan Sathyanarayan
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG62_SPGGuestNeedsToModifyContactInformation {
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
	public void ModifyMemberContactInfo (){

		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.EnterValue("NavigatorHomePage_SPGnum_EB", SW.TestData("SPGnum_created"));
		SW.Click("NavigatorReservationSearchPage_BeginSearchAfterSPG_BT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");

		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.Click("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT");
		SW.Click("NavigatorSearchPage_EditWorkFaxNumber_EB");
		SW.EnterValue("NavigatorSearchPage_EditWorkFaxNumber_EB",SW.TestData("NewWorkFaxNumber"));
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); 
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)){
			Environment.loger.log(Level.INFO,"Guest Contact Information Modified");
			Environment.loger.log(Level.INFO,"Completed execution - ModifyMemberContactInfo");
		}
		else{
			Environment.loger.log(Level.ERROR,"Execution Failed - ModifyMemberContactInfo");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}

}
