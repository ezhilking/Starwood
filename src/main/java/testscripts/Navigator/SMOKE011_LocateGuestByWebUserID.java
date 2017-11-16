package testscripts.Navigator;
/* Purpose		: Search Guest Profile 
 * TestCase Name: Searching Guest Profile by Last Name  and Zip COde
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 06/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE011_LocateGuestByWebUserID {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.BWURL.substring(0,Environment.BWURL.indexOf("preferredguest/")));
	}

	String generatedSPGnum=null;
	String FirstName = null;
	String LastName = null;
	String WebUsername = null;
	String phoneNum = null;

	@Test(priority=0)
	public void EnrollGuestInBW() {

		FirstName = SW.RandomString(6);
		LastName = SW.RandomString(10);
		WebUsername = SW.RandomString(6);
		phoneNum = Integer.toString(SW.RandomInteger(5))+ Integer.toString(SW.RandomInteger(5));

		//Generation of SPG number from Branded Web	 
		SW.NormalClick("NavigatorBrandedWebPage_JoinNow_LK");//Clicking on the link Join Now
		SW.DropDown_SelectByText("NavigatorBrandedWebPage_salutation_DD", "Mr."); //Proving the salutation
		SW.EnterValue("NavigatorBrandedWebPage_FirstName_EB", FirstName); //Providing the First name
		SW.EnterValue("NavigatorBrandedWebPage_LastName_EB", LastName); //Providing the last name
		SW.DropDown_SelectByText("NavigatorBrandedWebPage_CountryCode_DD", "United States"); //Country
		SW.EnterValue("NavigatorBrandedWebPage_Address_EB", "Street 7 Cross Road"); //Address
		SW.EnterValue("NavigatorBrandedWebPage_City_EB", "LA"); //City
		SW.DropDown_SelectByText("NavigatorBrandedWebPage_State_DD", "California"); //State
		SW.EnterValue("NavigatorBrandedWebPage_ZipCode_EB", "90001"); //Zip Code
		SW.DropDown_SelectByText("NavigatorBrandedWebPage_PhoneType_DD", "Work"); //Phone Type
		SW.DropDown_SelectByText("NavigatorBrandedWebPage_PhoneCountryCode_DD", "United States(+1)"); // Phone Country Code
		SW.EnterValue("NavigatorBrandedWebPage_PhoneNumber_EB", phoneNum); //Phone number
		SW.EnterValue("NavigatorBrandedWebPage_EmailAddress_EB", "testuser@gmail.com"); //emailID
		SW.EnterValue("NavigatorBrandedWebPage_Username_EB", WebUsername); // WEb Username
		SW.EnterValue("NavigatorBrandedWebPage_Password_EB", "password@123"); //Web password
		SW.EnterValue("NavigatorBrandedWebPage_ConfirmPassword_EB", "password@123"); //password confirmation

		//SW.NormalClick("NavigatorBrandedWebPage_CompleteEnrollment_BT"); //Click on the Complete Enrollment button
		SW.Click("NavigatorBrandedWebPage_CompleteEnrollment_BT");
		/*String successMessage = SW.GetText("NavigatorBrandedWebPage_SuccessMessage_FT"); // Getting the success message
		SW.CompareText("MessageSPG_generation", "Congratulations!", successMessage); //COmparing the message */
		generatedSPGnum = SW.GetText("NavigatorBrandedWebPage_GeneratedSPGnum_DT"); //Getting the generated SPG number
	}

	@Test(priority=1,dependsOnMethods = { "EnrollGuestInBW" })
	public void searchGuestByWebUserID(){
		SW.NavigateTo(Environment.NAVIGATORURL);
		//SW.LaunchBrowser(Environment.NAVIGATORURL); // Login into Navigator
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga	  
		SW.SelectCommunicationType();//selecting communication type
		SW.EnterValue("NavigatorHomePage_WebUser_EB", WebUsername); //Entering the webuser ID
		SW.NormalClick("NavigatorHomePage_Search_BT"); //Clicking on rhe search button

		String firstName_actual = SW.GetText("NavigatorWebUserSearchPage_FirstName_DT"); // Retrieving the First name from the result
		SW.CompareText("FirstName_comparision", FirstName.toUpperCase(), firstName_actual); //Comparing the first name 
		String lastName_actual = SW.GetText("NavigatorWebUserSearchPage_LastName_DT"); // Retrieving the Last name from the result
		SW.CompareText("LastName_comparision", LastName.toUpperCase(), lastName_actual); //Comparing the Last name 
		String getSPGnumber = SW.GetText("NavigatorWebUserSearchPage_SPGnum_DT"); //Getting the SPG num text - Preferred  SPG : #42008426117
		String actualSPGnum = getSPGnumber.substring(getSPGnumber.indexOf("#")+1); //Getting only the SPG num from the above text
		SW.CompareText("SPGnum_verification", generatedSPGnum, actualSPGnum); //Comparing the SPG num
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
