package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Change Address Line
 * TestCase Name: US5418- Change Address Line
 * Created By	: dhivya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG74_CHANGEADDRESSLINE {

	CHANNELS SW = new CHANNELS();
	String country;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		country="Indonesia";
	}

	@Test
	public void updateProfile() {
		String address1 = SW.RandomString(5);
		String address2=SW.RandomString(6);

		String SPGNum=SW.TestData("SPGnum_created");
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SearchGuestBySPGnumber(SPGNum);
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name	 
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable

		SW.ClearValue("NavigatorSearchPage_HomeAddress1_EB");// Clearing the value present in the address
		SW.EnterValue("NavigatorSearchPage_HomeAddress1_EB",address1 ); //Providing Home Address 1

		SW.ClearValue("NavigatorSearchPage_HomeAddress2_EB");
		SW.EnterValue("NavigatorSearchPage_HomeAddress2_EB",address2 ); //Providing Home Address 2
		SW.ClearValue("NavigatorSearchPage_HomeZipCode_EB");
		SW.EnterValue("NavigatorSearchPage_HomeZipCode_EB", "560103");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeCountry_DD", country);
		SW.ClearValue("NavigatorSearchPage_HomeCity_EB");
		SW.EnterValue("NavigatorSearchPage_HomeCity_EB", "Bang");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeState_DD", "Bali");
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		//checking message
		if(SW.CompareText("UpdateMsg","Updates to profile have been saved!",updateMsg))//Comparing the message with the expected
			SW.NormalClick("NavigatorSearchPage_CommPrefAddress_LK");
		String sample=SW.GetText("NavigatorReservationDetailsPage_Address_DT");
		String Address=address1+", "+address2;
		SW.CompareTextContained("Validate Address",Address.toUpperCase(),sample);//Comparing the message with the expected			
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	} 
}
