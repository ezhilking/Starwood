package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Upgrade A Member
 * TestCase Name: US5347-Upgrade A Member
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG56_UpgradeAMember {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String expected_SPGLabel;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("Upgrade_Downgrade_Member");
	}	
	@Test(priority=0)
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14).trim(); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
		}
	}
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void upgradeMember(){
		expected_SPGLabel="SPG "+SW.TestData("SPGLeveltoUpgrade")+" :";
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditSPGInformation_LK");
		SW.NormalClick("NavigatorSearchPage_EditSPGInformation_LK");
		SW.DropDown_SelectByText("NavigatorSearchPage_SPGLevel_DD", SW.TestData("SPGLeveltoUpgrade"));
		SW.Wait(3);
		SW.DropDown_SelectByIndex("NavigatorSearchPage_SPGChangeReason_DD", 1);
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.WaitTillInvisibilityOfElement("NavigatorSearchPage_SaveInfoCardChanges_BT");
		// Checking for updated message
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg); //Comparing the message with the expected
			
		//Verifications
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetSPGType_DT");
		String actual_SPGType = SW.GetText("NavigatorSearchPage_GetSPGType_DT").toUpperCase();
		actual_SPGType=	actual_SPGType.substring(0, actual_SPGType.lastIndexOf(":")-1).trim();
		SW.CompareTextContained("Comparing the level", expected_SPGLabel, actual_SPGType);
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	} 
}
