package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: Thsi script for Load SPG Guest And Verify Contact Name Is Filled Or Not
 * TestCase Name: Load SPG Guest And Verify Contact Name Is Filled Or Not
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

	
		
public class REG17_LoadSPGGuestAndVerifyContactNameIsFilledOrNot {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String expected_SETnum;
	String expected_CmpnyName;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
		expected_SETnum = SW.TestData("Company_SET_num");
		expected_CmpnyName = SW.TestData("Compant_Name");
	}
	@Test(priority=0)
	public void ValidateContactDetails()
	{
		
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNUMBER);
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		if(!SW.CompareTextContained("", SW.GetText("NavigatorSearchPage_GuestNAme_DT")))
			Environment.loger.log(Level.INFO, "The contact name is displayed in navigator as "+SW.GetText("NavigatorSearchPage_GuestNAme_DT"));
		else{
			Environment.loger.log(Level.ERROR,"Contact name not available");
			SW.FailCurrentTest("Validation Fails in checking Contact name");	
		}
		if(SW.ObjectExists("NavigatorSearchPage_PhoneNumber_DT"))
		{
			Environment.loger.log(Level.INFO, "Phone number is present");
			String sPhNo=SW.GetText("NavigatorSearchPage_PhoneNumber_DT");
			if(SW.CompareTextContained("+", sPhNo))
				Environment.loger.log(Level.INFO, "The phone number has a country code "+sPhNo);
			else{
				Environment.loger.log(Level.ERROR,"The Country code is not present");
				SW.FailCurrentTest("Validation Fails in checking Country Code");
			}
		}
		else
			Environment.loger.log(Level.INFO, "There is no Contact number for this member");
	}
	@Test(priority=1)
	public void updateSetNumber()
	{
		
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_ContactInformation_FT"); //Waiting for the edit button of the Contact Information to be clickable
		//Edit Company Information
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditCompanyInfo_LK");
		SW.NormalClick("NavigatorSearchPage_EditCompanyInfo_LK");
		SW.EnterValue("NavigatorSearchPage_SETnumber_EB", SW.TestData("Company_SET_num"));
		SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
		//SW.Wait(2);
		String actualSETnumber = SW.GetText("NavigatorSearchPage_GetSETnumber_DT");
		if(SW.CompareText("SETnum_Comparision", expected_SETnum, actualSETnumber)){
			Environment.loger.log(Level.INFO, "The Set number has updated"+actualSETnumber);
		}else{
			Environment.loger.log(Level.ERROR,"Set number not updated");
			SW.FailCurrentTest("Validation Fails in checking set number updated");
		}
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetCompanyName_DT");
		String actualCmpnyName = SW.GetText("NavigatorSearchPage_GetCompanyName_DT");
		if(SW.CompareText("Company name compare", expected_CmpnyName, actualCmpnyName))
			Environment.loger.log(Level.INFO, "The company name has updated"+actualCmpnyName);
		else{
			Environment.loger.log(Level.ERROR,"company name not updated");
			SW.FailCurrentTest("Validation Fails in checking company name updated");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
