package testscripts.Navigator;
/* Purpose		: New Enrollment,searching and updating of Guest Profile 
 * TestCase Name: Guest Enrollment
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 01/09/2016
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

public class SMOKE001_Enrollment {
	CHANNELS SW = new CHANNELS();

	String firstName = SW.RandomString(10);
	String lastName = SW.RandomString(6);
	String city = "Bangalore";
	String zipCode = "560103";
	String phoneNumber = Integer.toString(SW.RandomInteger(5))+ Integer.toString(SW.RandomInteger(5));
	static String emailAddress = "starGuest.profile@gmail.com";
	static String SPGNumberCreated;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	//SMOKE 1 - Creating an Enrollment for a member
	@Test(priority=1)
	public void GuestEnrollment(){
		
		SW.GuestEnrollment(); //Enroll new member
			
		//Verification of Verified Message in the Navigator pane
		SW.WaitTillPresenceOfElementLocated("NavigatorHomePage_SecurityVerify_ST");
		String SecurityVerificationText = SW.GetText("NavigatorHomePage_SecurityVerify_ST"); // Getting the security Verified message from the home page - 
		//Comparing the message received with actual
		SW.CompareText("SecurityMessageVerification","Security Verified", SecurityVerificationText);

		//Comparing the SPG number in the Navigator pane
		String SPGPreferredNum = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT").substring(15); // Getting the SPG number from the navigator - SPG preferred
		Environment.loger.info("SPG number created is - "+ SPGPreferredNum);
		SPGNumberCreated = SW.TestData("SPGnum_created");
		SW.CompareText("SPGNumComparision", SPGNumberCreated, SPGPreferredNum);
		Environment.loger.info("Completed execution - GuestEnrollment");

	}

	//SMOKE 2 - Searching the Guest as per the SPG number generated
	@Test(priority=2,dependsOnMethods = { "GuestEnrollment" },enabled=false)
	public void SearchGuestSPGNum() {
		boolean NavigatorSPG_compResult,SaratogaSPG_compResult;
		//Login into the application	
		//SW.NavigatorLoginQA4();

		//Enter SPG number and Search
		SW.WaitTillElementToBeClickable("NavigatorHomePage_TakeCall_BT");
		SW.NormalClick("NavigatorHomePage_TakeCall_BT");
		SW.WaitTillElementToBeClickable("NavigatorHomePage_CommuType_BT");
		SW.SearchGuestBySPGnumber(SPGNumberCreated);

		//Verification -1 ------> Checking SPG number in the Navigator screen
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(15); // retrieving the number from the entire text
		NavigatorSPG_compResult = SW.CompareText(SPGNumberCreated, actual_SPG_num);

		//Verification -2 -------> Checking SPG number in the saratoga screen
		SW.SwitchToFrame("NavigatorHomePage_SaratogaFrame_FR"); //Switching the frame
		SW.WaitTillPresenceOfElementLocated("NavigatorHomePage_SaratogaMbrNum_DT");
		String Sar_SPGRetrieved = SW.GetAttributeValue("NavigatorHomePage_SaratogaMbrNum_DT","value");
		SaratogaSPG_compResult = SW.CompareText(SPGNumberCreated, Sar_SPGRetrieved);

		//Verification -3 -----> Checking the FIrst and Last Name
		SW.WaitTillPresenceOfElementLocated("NavigatorHomePage_SaratogaFirstName_DT"); //Waiting for the First name to appear in the saratoga screen
		String getFirstName_actualResult = SW.GetAttributeValue("NavigatorHomePage_SaratogaFirstName_DT", "value"); //Getting firstName
		SW.CompareText(firstName, getFirstName_actualResult);
		String getLastName_actaulResult = SW.GetAttributeValue("NavigatorHomePage_SaratogaLastName_DT", "value"); //Getting LastName
		SW.CompareText(firstName, getLastName_actaulResult);
		Environment.loger.info("Completed execution - SearchGuestSPGNum");

	}

	//SMOKE 3 - Searching the Guest by providing the LastName and the FirstName
	@Test(priority=3,dependsOnMethods = { "GuestEnrollment" },enabled=false)
	public void SearchGuestByFirstAndLastName() {
		String exp_lastName = lastName.toUpperCase();	
		String exp_firstName = firstName.toUpperCase();	

		SW.SwitchToFrame("");  
		//SW.WaitTillElementToBeClickable("NavigatorHomePage_TakeCall_BT");
		SW.NormalClick("NavigatorHomePage_TakeCall_BT");
		SW.WaitTillElementToBeClickable("NavigatorHomePage_CommuType_BT");
		SW.NormalClick("NavigatorHomePage_CommuType_BT"); //Clicking on the select a type
		SW.EnterValue("NavigatorHomePage_CommType_EB", SW.TestData("CommunicationType")+ Keys.TAB); //entering the entire-value and pressing tab

		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.NormalClick("NavigatorHomePage_LastName_EB"); //Clicking on the Last name edit box
		SW.EnterValue("NavigatorHomePage_LastName_EB", lastName); //Entering the last name
		SW.NormalClick("NavigatorHomePage_FirstName_EB"); //CLicking on the first name edit box
		SW.EnterValue("NavigatorHomePage_FirstName_EB", firstName +Keys.TAB); //Entering the First name and TAB
		SW.NormalClick("NavigatorHomePage_Search_BT"); // Clicking on Begin Search
		SW.NormalClick("NavigatorHomePage_Search_BT"); // Clicking once again on Begin Search	- Due to IE issue	
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_LastName_DT"); //Waiting for the saratoga page for the Last Name
		String actual_LastName = SW.GetText("NavigatorSearchPage_LastName_DT"); //Getting the last NAme from the saratoga page
		SW.CompareText("LastNameValidation",exp_lastName, actual_LastName); //COmparing the last name with the expected Value
		String actual_firstName = SW.GetText("NavigatorSearchPage_FirstName_DT");
		SW.CompareText("FirstNameValidation",exp_firstName, actual_firstName);
		Environment.loger.info("Completed execution - SearchGuestByFirstAndLastName");

	}

	//SMOKE 4 - Searching Guest by providing LastName and ZipCOde
	@Test(priority=4,dependsOnMethods = { "GuestEnrollment" },enabled=false)
	public void SearchGuestByNameAndZipCode() {

		String exp_lastName = lastName.toUpperCase();		  
		//Login into the application	
		//SW.NavigatorLoginQA4();

		//Enter Name and Search
		SW.WaitTillElementToBeClickable("NavigatorHomePage_TakeCall_BT");
		SW.NormalClick("NavigatorHomePage_TakeCall_BT");
		SW.WaitTillElementToBeClickable("NavigatorHomePage_CommuType_BT");
		SW.Click("NavigatorHomePage_CommuType_BT"); //Clicking on the select a type
		SW.EnterValue("NavigatorHomePage_CommType_EB", SW.TestData("CommunicationType")+ Keys.TAB); //entering the entire-value and pressing tab	
		
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.NormalClick("NavigatorHomePage_LastName_EB");// Clicking on the last name edit box
		SW.EnterValue("NavigatorHomePage_LastName_EB", lastName); //Entering the last name
		SW.NormalClick("NavigatorHomePage_CityOrZipCode_EB"); //Clicking on the  ZIp Code Edit Box
		
		SW.EnterValue("NavigatorHomePage_CityOrZipCode_EB", zipCode + Keys.TAB); // Entering the Zip COde value		
		SW.NormalClick("NavigatorHomePage_Search_BT");
		SW.NormalClick("NavigatorHomePage_Search_BT");
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_LastName_DT");		
		String actual_LastName = SW.GetText("NavigatorSearchPage_LastName_DT");
		SW.CompareText("lastNameComparision", exp_lastName, actual_LastName);
		Environment.loger.info("Completed execution - SearchGuestByNameAndZipCode");

	}

	//SMOKE 5 - Updating Guest profile
	@Test(priority=5,dependsOnMethods = { "GuestEnrollment" },enabled=false)
	public void updateGuestProfile(){

		SW.WaitTillElementToBeClickable("NavigatorHomePage_TakeCall_BT");
		SW.NormalClick("NavigatorHomePage_TakeCall_BT");
		SW.WaitTillElementToBeClickable("NavigatorHomePage_CommuType_BT");
		//Search Guest by SPG number
		SW.SearchGuestBySPGnumber(SPGNumberCreated);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name

		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.NormalClick("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable

		SW.EnterValue("NavigatorSearchPage_HomeAddress1_EB", "Test Address 1"); //Providing Home Address 1

		SW.EnterValue("NavigatorSearchPage_HomeAddress2_EB", "Test Address 2"); //Providing Home Address 2
		SW.EnterValue("NavigatorSearchPage_HomeZipCode_EB", "560103"); // Providing the zip code
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeCountry_DD", "India"); //Providing country
		SW.EnterValue("NavigatorSearchPage_HomeCity_EB", "Bang"); //Providing the city
		SW.DropDown_SelectByText("NavigatorSearchPage_HomeState_DD", "Karnataka"); //Providing the state

		SW.NormalClick("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg); //Comparing the message with the expected

		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
		String getAddress1 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress1_DT"); //Getting the Address1
		String getAddress2 = SW.GetText("NavigatorSerachPage_HomeLocationDetailsAddress2_DT"); //Getting the address2
		String getCountry = SW.GetText("NavigatorSerachPage_HomeLocationDetailsCountry_DT"); //Getting the country

		SW.CompareTextContained("CompareAddress1","Test Address 1",getAddress1);
		SW.CompareTextContained("CompareAddress2", "Test Address 2", getAddress2);
		SW.CompareTextContained("CompareCountry", "India", getCountry);	
		Environment.loger.info("Completed execution - updateGuestProfile");
	}

	@Test(priority=6,dependsOnMethods = { "GuestEnrollment" },enabled=false)
	public void benefitTracker() {
		SW.WaitTillElementToBeClickable("NavigatorHomePage_TakeCall_BT");
		SW.NormalClick("NavigatorHomePage_TakeCall_BT");
		SW.WaitTillElementToBeClickable("NavigatorHomePage_CommuType_BT");

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SW.TestData("SPGNumber_BenefitTracker")); //Getting the SPG number from TestData sheet 
		String getNumOfStays = SW.GetText("NavigatorHomePage_NumStays_DT")+" YTD"; //Getting number of stays and concact YTD
		String getNumNights = SW.GetText("NavigatorHomePage_NumNights_DT") + " YTD"; //Getting NUmber of nights and concat YTD 

		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Benefit_LK");
		SW.NormalClick("NavigatorSearchPage_Benefit_LK"); //Clicking the benefit button		  
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_NumOfStays_DT");

		String actualNumOfStays = SW.GetText("NavigatorSearchPage_NumOfStays_DT"); //Getting the number of stays from benefit section
		String actualNumOfNights = SW.GetText("NavigatorSearchPage_NumOfNights_DT"); //Getting the number of nights from the benefit section

		SW.CompareText("compareNumOfStays", getNumOfStays, actualNumOfStays); //Comparing the number of stays
		SW.CompareText("compareNumOfNights", getNumNights, actualNumOfNights); //Comparing the number of nights	  
		SW.NormalClick("NavigatorSearchPage_BenefitCancel_BT");
		Environment.loger.info("Completed execution - benefitTracker");
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 

}
