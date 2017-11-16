package testscripts.Navigator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Validate Partnerships and preferences in Navigator
 * TestCase Name: Validate Partnerships and preferences in Navigator
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG34_ValidatePartnerships_PreferencesNavigator {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String color="rgba(78, 129, 194, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("Platinum100Member");
	}
	@Test(priority=0)
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
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
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void checkPartnership(){
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorSearchPage_Partnership_LK")){
			Environment.loger.log(Level.INFO, "PartnerShip is available for this member");
			String sColor=SW.GetCSSValue("NavigatorSearchPage_Partnership_LK", "color");
			Environment.loger.log(Level.INFO, "PartnerShip Benifits are enabled"+sColor);
			SW.MoveToObject("NavigatorSearchPage_Partnership_LK");
			sColor=SW.GetCSSValue("NavigatorSearchPage_Partnership_LK", "color");
			if(SW.CompareText(color, sColor))
				Environment.loger.log(Level.INFO, "PartnerShip Benifits is available for this member");
			else 
				Environment.loger.log(Level.INFO,"No PartnerShips Benifits are available for this member");	
		}else {
			Environment.loger.log(Level.INFO,"No PartnerShips are available for this member");	
			SW.FailCurrentTest("Validation Fails in checking Expected company name");
		}
		String member=SW.GetText("NavigatorSearchPage_ExternalMember_DT");
		Environment.loger.log(Level.INFO," PartnerShips are available for this member "+member);	
		SW.NormalClick("NavigatorSearchPage_Partnership_LK");
		SW.NormalClick("NavigatorSearchPage_PartnerCancel_BT");
	}
	@Test(priority=2,dependsOnMethods="checkPartnership")
	public void editMemberPreference(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditMemberPreference_LK"); //Waiting till the link is clickable
		SW.NormalClick("NavigatorSearchPage_EditMemberPreference_LK"); //Clicking the Edit preference link
		SW.DropDown_SelectByText("NavigatorSearchPage_EditMemberPreferenceSmoking_DD", SW.TestData("MemberSmokingPreference_DD")); //Selecting the smoking prefernce taking value from test data
		SW.DropDown_SelectByText("NavigatorSearchPage_EditMemberPreferenceAccesibility_DD", SW.TestData("MemberAccessibilityPreference_DD")); //Selecting the accessible preference - value from test data
		//removing all checkbox clicked
		SW.CheckBoxSetOptionForAll("//input[@type='checkbox']", "OFF");
		List<String> AccessPreferenceList = new ArrayList<String>();
		List<String> LifeStylePreferenceList = new ArrayList<String>();	
		if(SW.TestData("MemberPreference_NearElevator_CB").equalsIgnoreCase("Y")){ //Checking whether Near Elevator is provided as Y in testdata to be selected
			SW.CheckBox("NavigatorSearchPage_EditMemberPreferenceNearElevator_CB","ON");
			AccessPreferenceList.add("Near the elevator");
		}
		if(SW.TestData("MemberPreference_OnFloor_CB").equalsIgnoreCase("Y")){ //Checking whether On Floor is provided as Y in testdata to be selected
			SW.CheckBox("NavigatorSearchPage_EditMemberPreferenceLowFloor_CB","ON");
			AccessPreferenceList.add("On a low floor");
		}
		if(SW.TestData("MemberPreference_Running_CB").equalsIgnoreCase("Y")){ //Checking whether Running is provided as Y in testdata to be selected
			SW.CheckBox("NavigatorSearchPage_EditMemberPreferenceRunning_CB","ON");
			LifeStylePreferenceList.add("Running");
		}
		if(SW.TestData("MemberPreference_WorkingOut_CB").equalsIgnoreCase("Y")){ //Checking whether Working Out is provided as Y in testdata to be selected
			SW.CheckBox("NavigatorSearchPage_EditMemberPreferenceWorkingOut_CB","ON");
			LifeStylePreferenceList.add("Working out");
		}
		if(SW.TestData("MemberPreference_SpaceToWork_CB").equalsIgnoreCase("Y")){ //Checking whether Space To work is provided as Y in testdata to be selected
			SW.CheckBox("NavigatorSearchPage_EditMemberPreferenceSpaceToWork_CB","ON");
			LifeStylePreferenceList.add("Space to work");
		}

		SW.Click("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.WaitTillInvisibilityOfElement("NavigatorSearchPage_SaveInfoCardChanges_BT");
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
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
			if(	SW.CompareText("LifeStyleCompare_" , expectedLifeStyleValue, actualLifeStyleValue))
				Environment.loger.log(Level.INFO,"LifeStyle matched!!!!");
			else{
				Environment.loger.log(Level.ERROR,"LifeStyle not Matched in Navigator");
				SW.FailCurrentTest("Validation Fails in checking LifeStyle");
			}
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
