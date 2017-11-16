package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Add Phone Number And SET Number to profile Then Validate That it Reflects In Guest Panel Or Not 
 * TestCase Name: Add Phone Number And SET Number to profile Then Validate That it Reflects In Guest Panel Or Not 
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG37_GuestWantsRemoveSET_Want_Know_PartnershipBenifits {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER,expected_SETnum,expected_CmpnyName;
	String color="rgba(78, 129, 194, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("Platinum100Member");
		expected_SETnum = SW.TestData("Company_SET_num");
		expected_CmpnyName = SW.TestData("Compant_Name");
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
	public void ModifySetNumber(){
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
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
