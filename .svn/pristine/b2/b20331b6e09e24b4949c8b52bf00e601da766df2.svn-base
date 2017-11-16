package testscripts.Navigator;
/* Purpose		: This script for SPG Guest Needs To Modify Contact Information 
 * TestCase Name: SPG Guest Needs To Modify Contact Information
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

public class REG19_SPGGuestNeeds_ModifyContactInformation {
	CHANNELS SW = new CHANNELS();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		
	}
	@Test(priority=0)
	public void SPGmemberByPhoneAndModifyContact(){
		String address1 = SW.RandomString(10);
		String address2=SW.RandomString(10);
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga
		SW.SelectCommunicationType();
		SW.WaitTillElementToBeClickable("NavigatorHomePage_SPGnum_EB");
		SW.DoubleClick("NavigatorHomePage_PhoneCountry_BT");
		SW.NormalClick("NavigatorHomePage_PhoneCountry_EB");
		SW.EnterValue("NavigatorHomePage_PhoneCountry_EB", "India(+91)" + Keys.TAB);
		SW.NormalClick("NavigatorHomePage_PhoneNumber_EB"); //CLicking on the phone number Edit box
		SW.EnterValue("NavigatorHomePage_PhoneNumber_EB", SW.TestData("SPGMemberPhoneNumber")); //Entering the phone number
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT"); // WAit for the begin-search button to be enabled
		SW.NormalClick("NavigatorHomePage_Search_BT"); 
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_FirstName_DT");
		SW.NormalClick("NavigatorSearchPage_FirstName_DT");
		if(SW.ObjectExists("NavigatorSearchPage_CheckVerification_CB")){
			SW.Click("NavigatorSearchPage_CheckVerification_CB");
		}
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.Wait(5);
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name	  
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable
		SW.ClearValue("NavigatorSearchPage_HomeAddress1_EB");// Clearing the value present in the address
		SW.EnterValue("NavigatorSearchPage_HomeAddress1_EB",address1 ); //Providing Home Address 1
		SW.ClearValue("NavigatorSearchPage_HomeAddress2_EB");
		SW.EnterValue("NavigatorSearchPage_HomeAddress2_EB",address2 ); //Providing Home Address 2
		SW.ClearValue("NavigatorSearchPage_HomeZipCode_EB");
		SW.EnterValue("NavigatorSearchPage_HomeZipCode_EB", "560103");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeCountry_DD", "India");
		SW.ClearValue("NavigatorSearchPage_HomeCity_EB");
		SW.EnterValue("NavigatorSearchPage_HomeCity_EB", "Banglore");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeState_DD", "Karnataka");
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
		String getAddress1 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress1_DT").replaceAll(",", ""); //Getting the Address1
		String getAddress2 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress2_DT").replaceAll(",", ""); //Getting the address2
		String getCountry = SW.GetText("NavigatorSerachPage_HomeLocationDetailsCountry_DT");

		if(SW.CompareTextContained("CompareAddress1",address1.toUpperCase(),getAddress1))
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		if(SW.CompareTextContained("CompareAddress2", address2.toUpperCase(), getAddress2))
		Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		if(SW.CompareTextContained("CompareCountry", "India", getCountry))
		Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
