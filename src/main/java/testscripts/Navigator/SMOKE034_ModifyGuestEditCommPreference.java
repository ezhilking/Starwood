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

public class SMOKE034_ModifyGuestEditCommPreference {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}


	@Test(priority=0)
	public void editCommunicationPreference() {
		
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SW.TestData("SPGnum_created")); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		//SW.NormalClick("NavigatorSearchPage_ClosePopUp_BT"); //removed the alert by the developer

		//Edit Company Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_CommPreference_LK");
		SW.NormalClick("NavigatorSearchPage_CommPreference_LK");
		
		
		List<String> expectedResult = new ArrayList<String>();
		List<String> actualResult = new ArrayList<String>();
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
		
		
		SW.CompareText("SPG-EState_comp", SW.GetText("NavigatorSearchPage_GetCommPreferenceSPGeStatement_DT"), expectedResult.get(0));
		/*SW.CompareText("PromoAndOffers_comp", SW.GetText("NavigatorSearchPage_GetCommPreferenceOfferPromo_DT"), expectedResult.get(1));
		SW.CompareText("SpecialFeatures_comp", SW.GetText("NavigatorSearchPage_GetCommPreferencePropFeature_DT"), expectedResult.get(2));
		SW.CompareText("GuestStaySurvey_comp", SW.GetText("NavigatorSearchPage_GetCommPreferenceGuestStay_DT"), expectedResult.get(3));*/
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
