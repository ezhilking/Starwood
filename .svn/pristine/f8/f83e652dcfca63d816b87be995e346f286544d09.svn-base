package testscripts.Navigator;
/* Purpose		: Search Guest Profile 
 * TestCase Name: Searching Guest Profile by First ANd Last NAme
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

public class SMOKE030_ModifyGuestEditPaymentInformation {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}


	@Test(priority=0)
	public void editPaymentInfo() {
		String CreditCardNumber = SW.TestData("CreditCardNumber");
		String expectedCreditCardLast4digits = CreditCardNumber.substring(12);
		String expectedExpiryYear = "Expires Dec2020";
		
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SW.TestData("SPGnum_created")); //Search Guest by SPG number		
		//Navigating to Edit  Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.Click("NavigatorReservationSearchPage_Ack_BT");
		SW.Click("NavigatorSearchPage_GuestNAme_DT");
		//SW.NormalClick("NavigatorSearchPage_ClosePopUp_BT"); //removed the alert by the developer

		//Edit Information
		//SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.NormalClick("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.DropDown_SelectByText("NavigatorSearchPage_CredeitCardType_DD", SW.TestData("CreditCardType"));
		SW.NormalClick("NavigatorSearchPage_CredeitCardNumber_EB");
		SW.EnterValue("NavigatorSearchPage_CredeitCardNumber_EB", SW.TestData("CreditCardNumber"));
		SW.DropDown_SelectByText("NavigatorSearchPage_CredeitCardExpMonth_DD", SW.TestData("CreditCardExpMonth"));
		SW.DropDown_SelectByText("NavigatorSearchPage_CredeitCardExpYear_DD", SW.TestData("CreditCardExpYear"));
		SW.NormalClick("NavigatorSearchPage_CredeitCardPrimaryType_RB");
		SW.NormalClick("NavigatorSearchPage_CreditCardAdd_BT");
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		String getCardInformation = SW.GetText("NavigatorSearchPage_GetLastFourDigitCardNumber_DT"); //Get the credit card information after save
		String actual_last4DigitCardNumber = getCardInformation.substring(18,22); //Get the last 4 digit of card as diplayed in page
		SW.CompareText("CrCardLastFourDigit_comparoision", expectedCreditCardLast4digits, actual_last4DigitCardNumber); //Comparing the card number

		String actualExpiryYear = SW.GetText("NavigatorSearchPage_GetCardExpiryDate_DT");
		SW.CompareText("ExpiryYear_comparision", expectedExpiryYear, actualExpiryYear);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
