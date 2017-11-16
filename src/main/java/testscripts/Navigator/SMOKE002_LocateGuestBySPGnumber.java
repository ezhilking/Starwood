package testscripts.Navigator;
/* Purpose		: Searching Guest Profile 
 * TestCase Name: Search Guest By SPG Number
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 02/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE002_LocateGuestBySPGnumber {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchGuestSPGNum() {

		//Expected Values 
		String exp_SPGNUm = SW.TestData("SPGnum_created");
		String exp_GuestFirstName=SW.TestData("SPGmember_FirstName");
		String exp_GuestLastName = SW.TestData("SPGmember_LastName");

		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));

		//Enter SPG number and Search
		SW.SearchGuestBySPGnumber(SW.TestData("SPGnum_created"));

		//Verification -1 ------> Checking SPG number in the Navigator screen
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(15); // retrieving the number from the entire text
		SW.CompareText("SPGnum_validationInNavigator",exp_SPGNUm, actual_SPG_num);

		//Verification -2 -------> Checking SPG number in the saratoga screen
		SW.SwitchToFrame("NavigatorHomePage_SaratogaFrame_FR"); //Switching the frame
		SW.WaitTillPresenceOfElementLocated("NavigatorHomePage_SaratogaMbrNum_DT");
		String Sar_SPGRetrieved = SW.GetAttributeValue("NavigatorHomePage_SaratogaMbrNum_DT","value");
		SW.CompareText("SPGnum_validationInSaratoga",exp_SPGNUm, Sar_SPGRetrieved);

		//Verification -3 -----> Checking the FIrst and Last Name
		SW.WaitTillPresenceOfElementLocated("NavigatorHomePage_SaratogaFirstName_DT"); //Waiting for the First name to appear in the saratoga screen
		String getFirstName_actualResult = SW.GetAttributeValue("NavigatorHomePage_SaratogaFirstName_DT", "value"); //Getting firstName
		SW.CompareText("FirstName",exp_GuestFirstName, getFirstName_actualResult); //Comparing the Guest First Name
		String getLastName_actaulResult = SW.GetAttributeValue("NavigatorHomePage_SaratogaLastName_DT", "value"); //Getting LastName
		SW.CompareText("LastName",exp_GuestLastName, getLastName_actaulResult); //Comparing the Guest Last name				

	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
