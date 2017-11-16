package testscripts.Navigator;
/* Purpose		: Update Guest Profile 
 * TestCase Name: GuestProfile Update
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 07/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE007_UpdateGuestProfile {
	CHANNELS SW = new CHANNELS();
	//String SPGNum = SMOKE001_Enrollment.SPGNumberCreated;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void updateProfile() {
		String address1 = SW.RandomString(10);
		String address2=SW.RandomString(10);
		
		String SPGNum=SW.TestData("SPGnum_created");
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SearchGuestBySPGnumber(SPGNum);
		/*SW.SelectCommunicationType();//selecting communication type
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");*/	  
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
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeCountry_DD", "India");
		SW.ClearValue("NavigatorSearchPage_HomeCity_EB");
		SW.EnterValue("NavigatorSearchPage_HomeCity_EB", "Bang");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeState_DD", "Karnataka");

		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg); //Comparing the message with the expected

		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
		String getAddress1 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress1_DT").replaceAll(",", ""); //Getting the Address1
		String getAddress2 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress2_DT").replaceAll(",", ""); //Getting the address2
		String getCountry = SW.GetText("NavigatorSerachPage_HomeLocationDetailsCountry_DT");

		SW.CompareTextContained("CompareAddress1",address1.toUpperCase(),getAddress1);
		SW.CompareTextContained("CompareAddress2", address2.toUpperCase(), getAddress2);
		SW.CompareTextContained("CompareCountry", "India", getCountry);

	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
