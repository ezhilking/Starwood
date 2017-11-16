package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: Guest Enrollment with Australia Zip code with 3 digits
 * TestCase Name: REG44_EnrollMemberAustralia3StateCode.java
 * Created By	: Roshan Sathyanarayan
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG44_EnrollMemberAustralia3StateCode 
{
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String firstName = SW.RandomString(10);
	String lastName = SW.RandomString(6);
	String city = "Bangalore";
	String zipCode = "560103";
	String phoneNumber = Integer.toString(SW.RandomInteger(5))+ Integer.toString(SW.RandomInteger(5));
	static String emailAddress = "starGuest.profile@gmail.com";
	static String SPGNumberCreated;
	String GetAttributeValue;
	
	@BeforeClass
	public void StartTest()
	{
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test
	public void NonSpgGetsEnrolled (){
		
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.Wait(2);
		SW.NormalClick("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.NormalClick("NavigatorHomePage_EnrollNewMember_LK"); //Clicking on Enroll new Member

		//Filling Personal Information
		SW.DropDown_SelectByText("NavigatorEnrollPage_Salutation_DD", "Mr.");
		SW.NormalClick("NavigatorEnrollPage_FirstName_EB");
		SW.EnterValue("NavigatorEnrollPage_FirstName_EB", firstName);
		SW.NormalClick("NavigatorEnrollPage_LastName_EB");
		SW.EnterValue("NavigatorEnrollPage_LastName_EB", lastName);


		//Filling Contact information
		SW.DropDown_SelectByText("NavigatorEnrollPage_AddressType_DD", "Home"); //Address type
		SW.EnterValue("NavigatorEnrollPage_AddressLine1_EB", "Ecospace"); //Address line1
		SW.EnterValue("NavigatorEnrollPage_zipcode_EB", "261"); // Zip code
		SW.Wait(2);
		SW.DropDown_SelectByText("NavigatorEnrollPage_Country_DD", "Australia"); //Country
		SW.NormalClick("NavigatorEnrollPage_city_EB");
		SW.EnterValue("NavigatorEnrollPage_city_EB", "Sydney"); //CIty
		SW.DropDown_SelectByText("NavigatorEnrollPage_State_DD","New South Wales"); //State

		//Filling Communications
		SW.DropDown_SelectByText("NavigatorEnrollPage_PhoneType_DD", "Work Phone"); //Phone Type
		SW.NormalClick("NavigatorEnrollPage_SelectCountry_BT"); //Clicking on the drop down list for Phone Country
		SW.EnterValue("NavigatorEnrollPage_PhoneCountry_EB", "Australia(+61)" +Keys.TAB); //Entering the code
		SW.Wait(2);
		SW.NormalClick("NavigatorEnrollPage_PhoneNumber_EB");
		SW.EnterValue("NavigatorEnrollPage_PhoneNumber_EB",SW.TestData("SPGmember_PhoneNum"));
		SW.DropDown_SelectByText("NavigatorEnrollPage_EmailType_DD", "Work Email");
		SW.NormalClick("NavigatorEnrollPage_Email_EB");
		SW.EnterValue("NavigatorEnrollPage_Email_EB", emailAddress);

		//Submitting the Enroll request
		SW.NormalClick("NavigatorEnrollPage_SubmitEnrollment_BT");
        
		SW.WaitTillElementToBeClickable("NavigatorEnrollPage_MemberEnrollMessage_ST"); //Waiting for the message confirmation
		//String EnrollMessage = GetText("NavigatorEnrollPage_MemberEnrollMessage_ST"); //Getting the enrollment message
		String SPGNumber = SW.GetText("NavigatorEnrollPage_SPGNum_DT").substring(13);
		SW.NormalClick("NavigatorEnrollPage_MemberConfirmClose_BT");// Closing the message box
		//from page
		
		SW.WriteToTestData("Australia3StateMember", SPGNumber); //Writing the SPG number into the test data
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		//after entering the number
		String SPGPreferredNum = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT").substring(15); // Getting the SPG number from the navigator - SPG preferred
		Environment.loger.info("SPG number created is - "+ SPGPreferredNum);
		//getting form test data
		SPGNumberCreated = SW.TestData("Australia3StateMember");
		if(SW.CompareText("SPGNumComparision", SPGNumberCreated, SPGPreferredNum))
		    Environment.loger.log(Level.INFO,"Completed execution - GuestEnrollment");
		else {
			Environment.loger.log(Level.INFO,"Execution Failed  - GuestEnrollment");
			SW.FailCurrentTest("Validation Fails in GuestEnrollment Australia3StateCode");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}
}
