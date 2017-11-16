package testscripts.Navigator;
/* Purpose		: Search Guest Profile 
 * TestCase Name: Searching Guest Profile by First ANd Last NAme
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 02/09/2016
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

public class SMOKE028_ModifyGuestEditInformationCardByFirstAndLastName {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	String guestSuffix = SW.RandomString(4);

	@Test
	public void editInformationCard() {

		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));//Login into the application
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.EnterValue("NavigatorHomePage_LastName_EB", SW.TestData("InformationCardLastName")); //Entering the last name
		SW.EnterValue("NavigatorHomePage_FirstName_EB", SW.TestData("InformationCardFirstName")+Keys.TAB); //providing the First name
		SW.Click("NavigatorHomePage_Search_BT"); //Click on the search
		SW.Click("NavigatorHomePage_Search_BT"); //Again clicking due to IE issue

		//Editing the Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_FirstName_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorSearchPage_FirstName_DT");
		/*if(CHANNELS.CommGroup.equalsIgnoreCase("CommTypeGroup1")){
			SW.Click("NavigatorSearchPage_CheckVerification_CB");
		}*/
		//SW.NormalClick("NavigatorSearchPage_ClosePopUp_BT"); //removed the alert by the developer
		SW.NormalClick("NavigatorSearchPage_EditInfoCard_LK");
		SW.NormalClick("NavigatorSearchPage_GUestNameSuffix_EB");
		SW.EnterValue("NavigatorSearchPage_GUestNameSuffix_EB", guestSuffix);
		SW.Click("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.WaitTillInvisibilityOfElement("NavigatorSearchPage_SaveInfoCardChanges_BT");
		String GuestFullName = SW.GetText("NavigatorSearchPage_SearchedGuestFullName_DT");
		String actual_GuestSuffix = GuestFullName.substring(GuestFullName.indexOf(",")+1).toUpperCase().trim();
		SW.CompareText("comparision_GuestSuffix", guestSuffix.toUpperCase(), actual_GuestSuffix);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
