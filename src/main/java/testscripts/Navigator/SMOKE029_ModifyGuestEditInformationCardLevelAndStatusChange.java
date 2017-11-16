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

public class SMOKE029_ModifyGuestEditInformationCardLevelAndStatusChange {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	String expected_SPGLabel = "SPG Preferred :";
	String expected_SPGStatus = "";

	@Test(priority=0)
	public void editInformationCardOfGuestLevel() {
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		String Lastname = SW.TestData("SPGmember_LastName"); //Getting the LastName 	
		String FirstName = SW.TestData("SPGmember_FirstName");//Getting the FirstName 
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.EnterValue("NavigatorHomePage_LastName_EB", Lastname); //Entering the last name
		SW.EnterValue("NavigatorHomePage_FirstName_EB", FirstName +Keys.TAB); //providing the First name
		SW.NormalClick("NavigatorHomePage_Search_BT"); //Click on the search
		SW.NormalClick("NavigatorHomePage_Search_BT"); //Again clicking due to IE issue

		//Navigating to Edit  Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_FirstName_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorSearchPage_FirstName_DT");
		/*if(CHANNELS.CommGroup.equalsIgnoreCase("CommTypeGroup1")){
			SW.Click("NavigatorSearchPage_CheckVerification_CB");
		}*/
		
		//SW.NormalClick("NavigatorSearchPage_ClosePopUp_BT"); //removed the alert by the developer

		//Edit Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditSPGInformation_LK");
		SW.NormalClick("NavigatorSearchPage_EditSPGInformation_LK");
		SW.DropDown_SelectByText("NavigatorSearchPage_SPGLevel_DD", SW.TestData("SPGLevelToUpdate"));
		SW.Wait(3);
		SW.DropDown_SelectByText("NavigatorSearchPage_SPGChangeReason_DD", SW.TestData("SPG_reasonToUpdate"));
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");

		//Verifications
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetSPGType_DT");
		String actual_SPGType = SW.GetText("NavigatorSearchPage_GetSPGType_DT");
		//actual_SPGType = actual_SPGType.replaceAll("[: ]", "");
		SW.CompareText("SPGType_comparision", expected_SPGLabel, actual_SPGType);

	}

	/*//Scenario to edit the SPG status and verify
	@Test(priority=1,dependsOnMethods = {"editInformationCardOfGuestLevel"},alwaysRun=false)
	public void editSPGStatus(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditSPGInformation_LK");
		SW.NormalClick("NavigatorSearchPage_EditSPGInformation_LK");
		SW.DropDown_SelectByText("NavigatorSearchPage_SPGStatus_DD", SW.TestData("SPGStatusToUpdate"));
		SW.Wait(3);
		SW.DropDown_SelectByText("NavigatorSearchPage_SPGChangeReason_DD", SW.TestData(""));
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetSPGStatus_DT");
		String actual_SPGStatus = SW.GetText("NavigatorSearchPage_GetSPGStatus_DT");
		SW.CompareText("SPGStatus_comparision", expected_SPGStatus, actual_SPGStatus);
	}*/

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
