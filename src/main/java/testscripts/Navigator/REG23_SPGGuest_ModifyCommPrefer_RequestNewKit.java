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
/* Purpose		: This script for SPG Guest Needs To Modify Communication Preferences And Request New Kit 
 * TestCase Name: SPG Guest Needs To Modify Communication Preferences And Request New Kit
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG23_SPGGuest_ModifyCommPrefer_RequestNewKit {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");	
	}
	@Test(priority=0)
	public void editCommPrefAndReqestKit(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		//creating Kit Request
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_CommPreference_LK");
		SW.NormalClick("NavigatorSearchPage_CommPrefAddress_LK");
		if(!SW.ObjectExists("NavigatorSearchPage_CancelKitRequest_LK"))
		{
			SW.NormalClick("NavigatorSearchpage_CreateKitRequest_LK");
			if(SW.CompareText("Compare Successfull message","Successfully Requested", SW.GetText("NavigatorSearchPage_KitRequestMsg_DT")))
				Environment.loger.log(Level.INFO, "Kit request has created Successfully!!!");
			else {
				Environment.loger.log(Level.ERROR,"Kit Request not created");
				SW.FailCurrentTest("Validation Fails in checking Kit Request Success message");
			}
		}
		else
			Environment.loger.log(Level.INFO, "Kit Request has already created.......!!!!!");

		//Edit Company Information
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
		if(SW.CompareTextContained(SW.TestData("CommPre_Language"), SW.GetText("NavigatorSearchPage_PreferedLanguage_DT")))
			Environment.loger.log(Level.INFO, "Preference Updated Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Failed in validating status");
			SW.FailCurrentTest("Validation Fails in checking status");
		}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
