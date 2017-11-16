package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE010_VerifyPropComm {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test
	public void VerifyPropCommFunc()  {
		//SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga
		SW.NavigatorLogin(SW.TestData("DM_username"),SW.TestData("DM_password")); //Login into Saratoga
		SW.SelectCommunicationType();//selecting communication type
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_SearchReservation_LK"); // Clicking the reservation link

		SW.WaitTillPresenceOfElementLocated("NavigatorReservationSearchPage_ConfirmationNum_EB"); //WAiting for the confirmation tab to be present
		SW.EnterValue("NavigatorReservationSearchPage_ConfirmationNum_EB", SW.TestData("ConfNum_LocateGuest")+ Keys.TAB); //Entering the confirmation number
		SW.Click("NavigatorReservationSearchPage_BeginSearch_BT"); //Search
		SW.WaitTillElementToBeClickable("NavigatorReservationSearchPage_Ack_BT"); //Wait for the acknoledgement
		SW.Click("NavigatorReservationSearchPage_Ack_BT"); //Click on Ack

		//Actual Values
		String actualConfNum=SW.TestData("ConfNum_LocateGuest");
		String SPGnum = SW.GetText("NavigatorReservationDetailSearchPage_SPGnum_DT");
		String actualSPGnum = SPGnum.substring(SPGnum.indexOf(":")+1).trim();
		String ArrivalDay = SW.GetText("NavigatorReservationDetailSearchPage_ArrivalDate_DT");
		String actalArrivalDate = ArrivalDay.substring(ArrivalDay.indexOf("-")+1).trim();
		String DepartDay = SW.GetText("NavigatorReservationDetailSearchPage_DepartDate_DT");
		String actualDepartDate = DepartDay.substring(DepartDay.indexOf("-")+1).trim();
		String actualPropertyID = SW.GetText("NavigatorReservationDetailSearchPage_PropertyID_DT");


		//Navigating to Prop Comm
		SW.Click("NavigatorHomePage_MainMenu_BT"); //CLicking on Main-menu
		SW.Click("NavigatorHomePage_PropComm_LK"); //Clicking on Prop Comm
		SW.SwitchToWindow(1); //Switching to the new window
		
		if(SW.ObjectExists("NavigatorSearchPage_StarguestCSSAPassword_EB")){
			SW.EnterValue("NavigatorSearchPage_StarguestCSSAPassword_EB", SW.TestData("NavigatorPassword"));
			SW.NormalClick("NavigatorSearchPage_StarguestCSSALogin_BT");
		}
		

		//SW.WaitTillPresenceOfElementLocated("NavigatorReservationStarGuestPage_ResConfNumber_DT");
		String ResConfNumber = SW.GetText("NavigatorReservationStarGuestPage_ResConfNumber_DT");
		String ArrivalDate = SW.GetText("NavigatorReservationStarGuestPage_ArrivalDate_DT").trim();
		String DepartureDate = SW.GetText("NavigatorReservationStarGuestPage_DepartDate_DT").trim();
		String PropertyID = SW.GetText("NavigatorReservationStarGuestPage_PropertyID_DT");
		String expectedPropertyID = (PropertyID.replace("(", "")).replace(")", "");
		/*String GuestFirstName = SW.GetAttributeValue("NavigatorReservationStarGuestPage_GuestFirstName_DT", "value");
		String GuestLastName = SW.GetAttributeValue("NavigatorReservationStarGuestPage_GuestLastName_DT", "value");
		String PrimaryPhoneNumber = SW.GetAttributeValue("NavigatorReservationStarGuestPage_GuestPhnNum_DT", "value");*/
		String SPGNumber = SW.GetAttributeValue("NavigatorReservationStarGuestPage_SPGNum_DT", "value");

		//Comparing values
		SW.CompareText("ConfNumber_verification", ResConfNumber, actualConfNum);
		SW.CompareText("SPGnum_verification", SPGNumber, actualSPGnum);
		SW.CompareText("ArrivalDate_verification", ArrivalDate, actalArrivalDate);
		SW.CompareText("DEpartDate_verification", DepartureDate, actualDepartDate);
		SW.CompareText("PropertyID_verification", expectedPropertyID, actualPropertyID);

	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
