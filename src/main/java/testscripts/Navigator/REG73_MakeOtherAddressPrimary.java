package testscripts.Navigator;

/* Purpose		: This script for Make  Other  Address Primary
 * TestCase Name: US5418- Make  Other  Address Primary
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG73_MakeOtherAddressPrimary {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,Address,Address2;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGnum_created");
	}
	@Test(priority=0)
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num);
	}
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void ChangePrimaryAddress(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.Click("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable
		SW.SelectRadioButton("NavigatorEditPage_OtherPrimaryAddr_RB");
		Address=SW.RandomString(7);
		Address2=SW.RandomString(7);
		SW.EnterValue("NavigatorEditPage_OtherAddress1_EB", Address);
		SW.EnterValue("NavigatorEditPgae_OtherAddr2_EB", Address2);
		SW.EnterValue("NavigatorEditPage_OtherZipcode_EB",SW.RandomInteger(5));
		SW.DropDown_SelectByIndex("NavigatorEditPage_OtherCountry_DD", 14);
		SW.EnterValue("NavigatorEditPage_OtherCity_EB", SW.RandomString(5));
		SW.DropDown_SelectByIndex("NavigatorEditPage_OtherState_DD", 1);
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactUpdateMsg_FT");
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		//checking message
		SW.CompareText("UpdateMsg","Updates to profile have been saved!",updateMsg);//Comparing the message with the expected
		//Checking communication Address
		SW.NormalClick("NavigatorSearchPage_CommPrefAddress_LK");
		String CommAddress=SW.GetText("NavigatorReservationDetailsPage_Address_DT");
		Address=Address+", "+Address2;
		SW.CompareTextContained("Validate Address",Address.toUpperCase(),CommAddress);//Comparing the message with the expected			
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
