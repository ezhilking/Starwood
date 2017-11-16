package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Australlia 3 State Code Error - Edit Member Address
 * TestCase Name: US6616- Australlia 3 State Code Error - Edit Member Address
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG64_Australlia3StateCode_EditMemberAddress {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("Australia3StateMember");
	}
	@Test(priority=0)
	public void LocateSPGMember(){
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
		}
	}
	@Test(priority=1,dependsOnMethods="LocateSPGMember")
	public void EditMemberAddress(){
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name	  
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeCountry_DD", "Australia");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeState_DD", "Queensland");
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
		String getCountry = SW.GetText("NavigatorSerachPage_HomeLocationDetailsCountry_DT");
		if(SW.CompareTextContained("CompareCountry", "Australia", getCountry))
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}
}
