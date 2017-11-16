package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: Default phone country from address
 * TestCase Name: Default Phone Country From Address Communications with 1 Phone Number
 * Created By	: dhivya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG45_DefaultPhoneCountryFromAddress {

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
		String address1 = SW.RandomString(10);
		String address2=SW.RandomString(10);
		String SPGNum=SW.TestData("SPGnum_created");
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SearchGuestBySPGnumber(SPGNum);
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name	 
		String beforeUpdate=SW.GetText("NavigatorHomePage_HomePhone_DT");
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
		String afterUpdate=SW.GetText("NavigatorHomePage_HomePhone_DT");
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		//checking message
		if(SW.CompareText("UpdateMsg","Updates to profile have been saved!",updateMsg))//Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");
		}
		//checking country
		String getCountry = SW.GetText("NavigatorSerachPage_HomeLocationDetailsCountry_DT");
		if(SW.CompareText("UpdateMsg",country,getCountry))//Comparing the message with the expected
			Environment.loger.log(Level.INFO, "country updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"country doesn't match");
			SW.FailCurrentTest("Validation Fails in checking profile update message");
		}
		//checking phone number
		if(SW.CompareText("UpdateMsg",beforeUpdate,afterUpdate))//Comparing the message with the expected
			Environment.loger.log(Level.INFO, "phone number doesnt change!!!");
		else{
			Environment.loger.log(Level.ERROR,"phone number changed successfully");
			SW.FailCurrentTest("Validation Fails in checking profile update message");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();	

	}
}
