package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Mask Credit Card on Profile Payment Screen-Less than 5 digits should not mask-Negative
 * TestCase Name: Mask Credit Card on Profile Payment Screen-Less than 5 digits should not mask-Negative
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG52_MaskCreditCardLessThan5Digits_Negative {
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
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14).trim(); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
		}
	}

	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void editPaymentInfo() {
		String CreditCardNumber = "4444";
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.NormalClick("NavigatorSearchPage_EditPaymentInformation_LK");
		SW.DropDown_SelectByText("NavigatorSearchPage_CredeitCardType_DD", SW.TestData("CreditCardType"));
		SW.NormalClick("NavigatorSearchPage_CredeitCardNumber_EB");
		SW.EnterValue("NavigatorSearchPage_CredeitCardNumber_EB", CreditCardNumber);
		SW.DropDown_SelectByText("NavigatorSearchPage_CredeitCardExpMonth_DD", SW.TestData("CreditCardExpMonth"));
		SW.DropDown_SelectByText("NavigatorSearchPage_CredeitCardExpYear_DD", SW.TestData("CreditCardExpYear"));
		SW.NormalClick("NavigatorSearchPage_CredeitCardPrimaryType_RB");
		String getCardInformation = SW.GetText("NavigatorSearchPage_CredeitCardNumber_EB"); //Get the credit card information after save
		if(SW.CompareText("CrCardLastFourDigit_comparoision", CreditCardNumber, getCardInformation)) //Comparing the card number
			Environment.loger.log(Level.INFO,"The Credit card number mot masked as expected");
		else {
			Environment.loger.log(Level.ERROR,"The Credit card Number has  masked");
		}
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	} 
}
