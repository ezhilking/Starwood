package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for SPG Search -Screen Layout- by Number & by Name works Separately
 * TestCase Name: SPG Search -Screen Layout- by Number & by Name works Separately
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG48_ScreenLayout_ByNumber_ByName_WorkSeparately {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String exp_lastName;
	String exp_firstName;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
		exp_lastName=SW.TestData("SPGmember_LastName").toUpperCase();
		exp_firstName=SW.TestData("SPGmember_FirstName").toUpperCase();
	}
	@Test(priority=0)
	public void byNumber_ByNameSearch(){

		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();		//selecting communication
		//Enter SPG number in By Number Tab
		SW.WaitTillElementToBeClickable("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_SPGnum_EB");
		SW.EnterValue("NavigatorHomePage_SPGnum_EB", SPGNUMBER);
		//Search By Name Tag
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.EnterValue("NavigatorHomePage_LastName_EB", SW.TestData("SPGmember_LastName")); //Entering the last name
		SW.EnterValue("NavigatorHomePage_FirstName_EB", SW.TestData("SPGmember_FirstName")+Keys.TAB);
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_LastName_DT");
		String actual_LastName = SW.GetText("NavigatorSearchPage_LastName_DT");
		if(SW.CompareText("LastNameValidation",exp_lastName, actual_LastName))
			Environment.loger.log(Level.INFO,"The LastName Of the Search has Matched");
		else {
			Environment.loger.log(Level.INFO,"Last Name of the Search Not Matched");
			SW.FailCurrentTest("Validation Fails in checking Last Name");
		}
		String actual_firstName = SW.GetText("NavigatorSearchPage_FirstName_DT");
		if(SW.CompareText("FirstNameValidation",exp_firstName, actual_firstName))
			Environment.loger.log(Level.INFO,"The First Name of the search has Matched");
		else {
			Environment.loger.log(Level.INFO,"The FirstName of the serach not Matched");
			SW.FailCurrentTest("Validation Fails in checking FirstName");
		}
	}
	@Test(priority=1)
	public void ByName_ByNumberSearch(){
		SW.EnterValue("NavigatorHomePage_LastName_EB", SW.TestData("SPGmember_LastName")); //Entering the last name
		SW.EnterValue("NavigatorHomePage_FirstName_EB", SW.TestData("SPGmember_FirstName")+Keys.TAB);
		SW.Click("NavigatorHomePage_SearchByNumber_LK");
		SW.DoubleClick("NavigatorHomePage_SearchByNumber_LK");
		//Enter SPG number in By Number Tab
		SW.WaitTillElementToBeClickable("NavigatorHomePage_SPGnum_EB");
		SW.Click("NavigatorHomePage_SPGnum_EB");
		SW.EnterValue("NavigatorHomePage_SPGnum_EB", SPGNUMBER);
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
