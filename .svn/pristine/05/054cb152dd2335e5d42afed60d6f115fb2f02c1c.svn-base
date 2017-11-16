package testscripts.Navigator;
/* Purpose		: This script for Non-SPG Guest Wants To Get Enrolled
 * TestCase Name: Non-SPG Guest Wants To Get Enrolled
 * Created By	: Roshan
 * Modified By	: Sharmila Begam
 * Modified Date: 06/02/17
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG43_NonSpgGuestEnrolled {
    CHANNELS SW = new CHANNELS();
    String SPGNUMBER;
    @BeforeClass
    public void StartTest()
    {
          Environment.Tower = "CHANNELS";
          Reporter.StartTest();
          SW.LaunchBrowser(Environment.NAVIGATORURL);
          SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
    }
    @Test
    public void NonSpgGetsEnrolled (){
          String expected_SETnum = SW.TestData("Company_SET_num");
          String expected_CmpnyName = SW.TestData("Compant_Name");
          SW.GuestEnrollment();
          SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
          SW.Click("NavigatorSearchPage_GuestNAme_DT");
          SW.Click("NavigatorSearchPage_EditCompanyInfo_LK");
          SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_SETnumber_EB");
          SW.EnterValue("NavigatorSearchPage_SETnumber_EB", SW.TestData("Company_SET_num"));
          SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
          String updateMsg = SW.GetText("NavigatorSearchPage_ContactUpdateMsg_FT").trim(); //Getting the message generated
  		if(SW.CompareText("UpdateMessage", "Updates to profile have been saved!", updateMsg)) //Comparing the message with the expected
  			Environment.loger.log(Level.INFO, "The profile has updated successfully!!!");
  		else{
  			Environment.loger.log(Level.ERROR,"Profile not updated");
  			SW.FailCurrentTest("Validation Fails in checking profile update message");	
  		}
          String actualSETnumber = SW.GetText("NavigatorSearchPage_GetSETnumber_DT");
          if(SW.CompareText("SETnum_COmparision", expected_SETnum, actualSETnumber))
                Environment.loger.log(Level.INFO,"Set Number Get Updated");
          else {
                Environment.loger.log(Level.INFO,"Set Number not updated");
                SW.FailCurrentTest("Validation Fails in Checking set number");
          }
          SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetCompanyName_DT");
          String actualCmpnyName = SW.GetText("NavigatorSearchPage_GetCompanyName_DT");
          if(SW.CompareText("CompnyName_comparision", expected_CmpnyName, actualCmpnyName))
                Environment.loger.log(Level.INFO,"Company Name Get Updated");
          else {
                Environment.loger.log(Level.INFO,"Company Name not updated");
                SW.FailCurrentTest("Validation Fails in Checking Company name");
          }	  
    } 
    @AfterClass
    public void EndTest(){
    	SW.NavigatorLogOut();
        Reporter.StopTest();
    }

}
