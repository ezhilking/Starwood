package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for Guest Want to Do Some Account Maintainence And Want To Cancel Upcoming Res  
 * TestCase Name: Guest Want to Do Some Account Maintainence And Want To Cancel Upcoming Res 
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG36_Guest_DoSomeAccountMaintainence {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,expected_SETnum,expected_CmpnyName,sPhno;
	String color="rgba(78, 129, 194, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
		expected_SETnum = SW.TestData("Company_SET_num");
		expected_CmpnyName = SW.TestData("Compant_Name");
		sPhno="9090909090";
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
	public void addPhoneNumber(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		SW.Click("NavigatorSearchPage_ContactInformation_FT"); //Clicking the Contact Information clickable
		SW.SelectRadioButton("NavigatorEditPage_PrimaryPhone_RB");
		SW.EnterValue("NavigatorEditPage_HomePhone_EB", sPhno);
		SW.Click("NavigatorSearchPage_SaveEditContactInfo_BT"); //Clicking the save changes button
		String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
		else{
			Environment.loger.log(Level.ERROR,"Profile not updated");
			SW.FailCurrentTest("Validation Fails in checking profile update message");	
		}
		SW.WaitTillPresenceOfElementLocated("NavigatorSerachPage_HomeLocationDetails_DT"); //Waiting for the updated details to appear
	}
	@Test(priority=2,dependsOnMethods="addPhoneNumber")
	public void AddSetNumber(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditCompanyInfo_LK");
		SW.NormalClick("NavigatorSearchPage_EditCompanyInfo_LK");
		SW.EnterValue("NavigatorSearchPage_SETnumber_EB", SW.TestData("Company_SET_num"));
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		String actualSETnumber = SW.GetText("NavigatorSearchPage_GetSETnumber_DT");
		if(SW.CompareText("SETnum_COmparision", expected_SETnum, actualSETnumber)){
			Environment.loger.log(Level.INFO, "Expected Set Number changed");
		}else {
			Environment.loger.log(Level.ERROR,"Expected set number not changed");
			SW.FailCurrentTest("Validation Fails in checking set number");
		}
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetCompanyName_DT");
		String actualCmpnyName = SW.GetText("NavigatorSearchPage_GetCompanyName_DT");
		if(SW.CompareText("CompnyName_comparision", expected_CmpnyName, actualCmpnyName)){
			Environment.loger.log(Level.INFO, "Expected Company name has changed");
		}else {
			Environment.loger.log(Level.ERROR,"Expected Company Name has not changed");
			SW.FailCurrentTest("Validation Fails in checking Expected company name");
		}
	}
	@Test(priority=3,dependsOnMethods="AddSetNumber")
	public void checkPartnership(){
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
	}
	@Test(priority=4,dependsOnMethods="checkPartnership")
	public void cancelUpcomingReservation(){
		SW.EnterValue("NavigatorHome_ContactNumber_EB", SW.TestData("SPGmember_PhoneNum"));
		SW.Click("NavigatorHomePage_UpcommingStays_LK");
		if(SW.ObjectExists("NavigatorReservationSearchPage_ConfirmCard_LK")){
			Environment.loger.log(Level.INFO,"Reservation Found by name tab");
			SW.NormalClick("NavigatorReservationSearchPage_ConfirmCard_LK");
			SW.Wait(3);
			SW.WaitTillElementToBeClickable("NavigatorReservationDetailSearchPage_Cancel_BT");
			SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
			if(!SW.IsAlertPresent()){
				SW.ClickAndProceed("NavigatorReservationDetailSearchPage_Cancel_BT");
			}
			SW.HandleAlert(true);
			SW.Wait(3);
			
			SW.SwitchToFrame("SaratogaCreateReservationPage_Frame_FR");
			 String CancelNum =SW.GetText("SaratogaReservationPage_CancelNumber_DT");
			if(SW.CompareTextContained("Your cancellation number is", CancelNum)){
				String can=CancelNum.trim().substring(24);
				Environment.loger.log(Level.INFO, "Cancellation Number has generated as"+can);
			}
			else {
				Environment.loger.log(Level.ERROR,"Cancellation number not generated");
				SW.FailCurrentTest("Validation Fails in checking Cancellation number");
			}
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
