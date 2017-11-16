package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Modify Contact Info On Merged account
 * TestCase Name: Modify Contact Info On Merged account
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG39_ModifyContactInfoOnMergedAccount {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,address1,address2,sPhno;
	String color="rgba(78, 129, 194, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("MergedMember");
		sPhno="";
		address1 = SW.RandomString(10);
		address2=SW.RandomString(10);
	}
	@Test(priority=0)
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String text=SW.GetText("NavigatorHomePage_Merge_DT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator","merged", text))
			Environment.loger.log(Level.INFO,"Merged member has found In Navigator are matched!!!!  "+actual_SPG_num);
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}
	}
	@Test(priority=1)
	public void modifyAddress_PhoneNumber(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name	  
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable
		SW.EnterValue("NavigatorSearchPage_HomeAddress1_EB",address1 ); //Providing Home Address 1
		SW.EnterValue("NavigatorSearchPage_HomeAddress2_EB",address2 ); //Providing Home Address 2
		SW.EnterValue("NavigatorSearchPage_HomeZipCode_EB", "560103");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeCountry_DD", "India");
		SW.EnterValue("NavigatorSearchPage_HomeCity_EB", "Bang");
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeState_DD", "Karnataka");
		SW.SelectRadioButton("NavigatorEditPage_PrimaryPhone_RB");
		SW.EnterValue("NavigatorEditPage_HomePhone_EB", sPhno);
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg))//Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
		String getAddress1 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress1_DT").replaceAll(",", ""); //Getting the Address1
		String getCountry = SW.GetText("NavigatorSerachPage_HomeLocationDetailsCountry_DT");

		if(SW.CompareTextContained("CompareAddress1",address1.toUpperCase(),getAddress1))
			Environment.loger.log(Level.INFO,"Address are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"Address is not matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking Addres in Navigator");
		}
		if(SW.CompareTextContained("CompareCountry", "India", getCountry))
			Environment.loger.log(Level.INFO,"Country In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"Country not Matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}

	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
