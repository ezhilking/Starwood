package testscripts.Navigator;
/* Purpose		: This script for Load A Merged Account and Do Some Account Maintenance
 * TestCase Name: Load A Merged Account and Do Some Account Maintenance
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;


public class REG46_LoadMergedAccount_DoSomeAccountMaintenance {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,address1,address2,sPhno;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("MergedMember");
		sPhno="9090909090";
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
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void RemoveSetNumber(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name	  
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditCompanyInfo_LK");
		SW.NormalClick("NavigatorSearchPage_EditCompanyInfo_LK");
		SW.EnterValue("NavigatorSearchPage_SETnumber_EB", "");
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		if(!SW.ObjectExists("NavigatorSearchPage_GetSETnumber_DT")){
			Environment.loger.log(Level.INFO, "Set Number has Removed successfully");
		}else {
			Environment.loger.log(Level.ERROR,"Set Number Doesnt Removed!!!");
			SW.FailCurrentTest("Validation Fails in checking set number");
		}
	}
	@Test(priority=2,dependsOnMethods="RemoveSetNumber")
	public void modifyAddress_PhoneNumber(){  
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
		String getPhonenum=SW.GetText("NavigatorHomePage_HomePhone_DT");
		if(SW.CompareTextContained("CompareAddress1",address1.toUpperCase(),getAddress1))
			Environment.loger.log(Level.INFO,"Address are matched!!!! and Phone number "+getPhonenum);
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
	@Test(priority=3,dependsOnMethods="modifyAddress_PhoneNumber")
	public void RemoveCreditCard(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.NormalClick("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.CheckBox("NavigatorEditPage_CreditCardRemove_CB", "ON");
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg))//Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
	}
	@Test(priority=4,dependsOnMethods="RemoveCreditCard")
	public void EditCommunicationPreference(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_CommPreference_LK");
		SW.NormalClick("NavigatorSearchPage_CommPreference_LK");
		List<String> expectedResult = new ArrayList<String>();

		//For SPG E-Statements
		if(SW.TestData("CommPre_SPG_E_statements").equalsIgnoreCase("Y")){
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceE-state_Y_RB");
			expectedResult.add("Yes");
		}else{
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceE-state_N_RB");
			expectedResult.add("No");
		}
		//For Exclusive Offers & Promotions :
		if(SW.TestData("CommPre_ExclusiveOffers_Promotions").equalsIgnoreCase("Y")){
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceOffersAndPromo_Y_RB");
			expectedResult.add("Yes");
		}else{
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceOffersAndPromo_N_RB");
			expectedResult.add("No");
		}
		//For Property Specials & Features :
		if(SW.TestData("CommPre_PropertyFeatures").equalsIgnoreCase("Y")){
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceSpecialFeatures_Y_RB");
			expectedResult.add("Yes");
		}else{
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceSpecialFeatures_N_RB");
			expectedResult.add("No");
		}
		//For GuestStay Survey
		if(SW.TestData("CommPre_GuestStaySurvey").equalsIgnoreCase("Y")){
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceGuestStaySurvey_Y_RB");
			expectedResult.add("Yes");
		}else{
			SW.SelectRadioButton("NavigatorSearchPage_CommPreferenceGuestStaySurvey_N_RB");
			expectedResult.add("No");
		}

		SW.DropDown_SelectByText("NavigatorSearchPage_CommPreferenceLanguagePreferred_DD", SW.TestData("CommPre_Language"));
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		SW.NormalClick("NavigatorSearchPage_CommPrefEmail");
		if(SW.CompareText("SPG-EState_comp", SW.GetText("NavigatorSearchPage_GetCommPreferenceSPGeStatement_DT"), expectedResult.get(0)))
			Environment.loger.log(Level.INFO, "Preference Updated Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Failed in validating status");
			SW.FailCurrentTest("Validation Fails in checking status");
		}
		if(SW.CompareText("PromoAndOffers_comp", SW.GetText("NavigatorSearchPage_GetCommPreferenceOfferPromo_DT"), expectedResult.get(1)))
			Environment.loger.log(Level.INFO, "Preference Updated Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Failed in validating status");
			SW.FailCurrentTest("Validation Fails in checking status");
		}
		if(SW.CompareText("SpecialFeatures_comp", SW.GetText("NavigatorSearchPage_GetCommPreferencePropFeature_DT"), expectedResult.get(2)))
			Environment.loger.log(Level.INFO, "Preference Updated Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Failed in validating status");
			SW.FailCurrentTest("Validation Fails in checking status");
		}
		if(SW.CompareText("GuestStaySurvey_comp", SW.GetText("NavigatorSearchPage_GetCommPreferenceGuestStay_DT"), expectedResult.get(3)))
			Environment.loger.log(Level.INFO, "Preference Updated Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Failed in validating status");
			SW.FailCurrentTest("Validation Fails in checking status");
		}
		SW.NormalClick("NavigatorSearchPage_CommPrefLang_LK");
		SW.Click("NavigatorSearchPage_CommPrefLang_LK");
		if(SW.CompareTextContained(SW.TestData("CommPre_Language"), SW.GetText("NavigatorSearchPage_PreferedLanguage_DT")))
			Environment.loger.log(Level.INFO, "Preference Updated Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Failed in validating status");
			SW.FailCurrentTest("Validation Fails in checking status");
		}
	}
	@Test(priority=5,dependsOnMethods="EditCommunicationPreference")
	public void MemberPreference(){
		//Edit Preference Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditMemberPreference_LK"); //Waiting till the link is clickable
		SW.NormalClick("NavigatorSearchPage_EditMemberPreference_LK"); //Clicking the Edit preference link
		SW.DropDown_SelectByText("NavigatorSearchPage_EditMemberPreferenceSmoking_DD", SW.TestData("MemberSmokingPreference_DD")); //Selecting the smoking prefernce taking value from test data
		SW.DropDown_SelectByText("NavigatorSearchPage_EditMemberPreferenceAccesibility_DD", SW.TestData("MemberAccessibilityPreference_DD")); //Selecting the accessible preference - value from test data

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
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
