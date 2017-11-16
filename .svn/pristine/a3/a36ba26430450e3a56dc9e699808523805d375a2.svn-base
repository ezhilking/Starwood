package testscripts.Navigator;
/* Purpose		: Search Guest Profile 
 * TestCase Name: Searching Guest Profile by First ANd Last NAme
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 02/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */


import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE031_ModifyGuestEditMemberPreferenceCard {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	
	@Test(priority=0)
	public void editPreference() {

		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SW.TestData("SPGnum_created")); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorSearchPage_GuestNAme_DT");
		//SW.NormalClick("NavigatorSearchPage_ClosePopUp_BT"); //removed the alert by the developer

		//Edit Preference Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditMemberPreference_LK"); //Waiting till the link is clickable
		SW.NormalClick("NavigatorSearchPage_EditMemberPreference_LK"); //Clicking the Edit preference link
		SW.DropDown_SelectByText("NavigatorSearchPage_EditMemberPreferenceSmoking_DD", SW.TestData("MemberSmokingPreference_DD")); //Selecting the smoking prefernce taking value from test data
		SW.DropDown_SelectByText("NavigatorSearchPage_EditMemberPreferenceAccesibility_DD", SW.TestData("MemberAccessibilityPreference_DD")); //Selecting the accessible preference - value from test data

		List<String> AccessPreferenceList = new ArrayList<String>();
		List<String> LifeStylePreferenceList = new ArrayList<String>();	
		if(SW.TestData("MemberPreference_NearElevator_CB").equalsIgnoreCase("Y")){ //Checking whether Near Elevator is provided as Y in testdata to be selected
			SW.NormalClick("NavigatorSearchPage_EditMemberPreferenceNearElevator_CB");
			AccessPreferenceList.add("Near the elevator");
		}
		if(SW.TestData("MemberPreference_OnFloor_CB").equalsIgnoreCase("Y")){ //Checking whether On Floor is provided as Y in testdata to be selected
			SW.NormalClick("NavigatorSearchPage_EditMemberPreferenceLowFloor_CB");
			AccessPreferenceList.add("On a low floor");
		}
		if(SW.TestData("MemberPreference_Running_CB").equalsIgnoreCase("Y")){ //Checking whether Running is provided as Y in testdata to be selected
			SW.NormalClick("NavigatorSearchPage_EditMemberPreferenceRunning_CB");
			LifeStylePreferenceList.add("Running");
		}
		if(SW.TestData("MemberPreference_WorkingOut_CB").equalsIgnoreCase("Y")){ //Checking whether Working Out is provided as Y in testdata to be selected
			SW.NormalClick("NavigatorSearchPage_EditMemberPreferenceWorkingOut_CB");
			LifeStylePreferenceList.add("Working out");
		}
		if(SW.TestData("MemberPreference_SpaceToWork_CB").equalsIgnoreCase("Y")){ //Checking whether Space To work is provided as Y in testdata to be selected
			SW.NormalClick("NavigatorSearchPage_EditMemberPreferenceSpaceToWork_CB");
			LifeStylePreferenceList.add("Space to work");
		}

		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");

		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetSmokingPreferenceSet_CB");
		String getSmokingPreference = SW.GetText("NavigatorSearchPage_GetSmokingPreferenceSet_CB");
		SW.CompareText("SmokingPreference_comparision", SW.TestData("MemberSmokingPreference_DD"), getSmokingPreference);
		String getAccesibilityPreference = SW.GetText("NavigatorSearchPage_GetAccesibilityPreferenceSet_CB");
		SW.CompareText("AccesibilityPreference_comparision", SW.TestData("MemberAccessibilityPreference_DD"), getAccesibilityPreference);

		String lifestyleXPATH;
		String expectedLifeStyleValue;
		String actualLifeStyleValue;
		for(int cnt=0;cnt<LifeStylePreferenceList.size();cnt++){
			expectedLifeStyleValue = LifeStylePreferenceList.get(cnt);
			lifestyleXPATH = "//label[text()='Lifestyle Stay Preferences']//..//div[" + (cnt+1) + "]";
			actualLifeStyleValue = SW.GetText(lifestyleXPATH);
			SW.CompareText("LifeStyleCompare_" , expectedLifeStyleValue, actualLifeStyleValue);
		}
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
