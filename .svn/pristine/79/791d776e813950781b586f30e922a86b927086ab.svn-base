package testscripts.Navigator;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for Search For Guest with By Name And Verify Benefit Tracker For P50 Guest
 * TestCase Name: Search For Guest with By Name And Verify Benefit Tracker For P50 Guest
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG30_GuestByNameAndVerifyBenefitTrackerForP50Guest {
	CHANNELS SW = new CHANNELS();
	String color="rgba(86, 188, 76, 1)";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=0)
	public void SearchByName(){
		//Login into the application	
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		//Enter Name and Search
		SW.SelectCommunicationType();		
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); //CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK"); //Due to IE issue the link By Name is double clicked
		SW.EnterValue("NavigatorHomePage_LastName_EB", SW.TestData("SPGmember_LastName")); //Entering the last name
		SW.EnterValue("NavigatorHomePage_FirstName_EB", SW.TestData("SPGmember_FirstName")+Keys.TAB);
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_FirstName_DT");
		SW.NormalClick("NavigatorSearchPage_FirstName_DT");
		if(SW.ObjectExists("NavigatorSearchPage_CheckVerification_CB")){
			SW.Click("NavigatorSearchPage_CheckVerification_CB");
		}

	}
	@Test(priority=1, dependsOnMethods="SearchByName")
	public void checkBenifits(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable	
		String getNumOfStays = SW.GetText("NavigatorHomePage_NumStays_DT")+" YTD"; //Getting number of stays and concact YTD
		String getNumNights = SW.GetText("NavigatorHomePage_NumNights_DT") + " YTD"; //Getting NUmber of nights and concat YTD 
		SW.Click("NavigatorSearchPage_GuestNAme_DT"); //Clicking the guest name
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Benefit_LK");
		SW.Click("NavigatorSearchPage_Benefit_LK"); //Clicking the benefit button
		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_NumOfStays_DT");
		String actualNumOfStays = SW.GetText("NavigatorSearchPage_NumOfStays_DT");
		String actualNumOfNights = SW.GetText("NavigatorSearchPage_NumOfNights_DT");
		if(SW.CompareText("compareNumOfStays", getNumOfStays, actualNumOfStays)){
			Environment.loger.log(Level.INFO, "The Number of Stays are matched");
		}else {
			Environment.loger.log(Level.ERROR,"Number of Stays not Matched");
			SW.FailCurrentTest("Validation Fails in checking Number of Stay");
		}		
		if(SW.CompareText("compareNumOfNights", getNumNights, actualNumOfNights)){
			Environment.loger.log(Level.INFO, "The Number of Stays are matched");
		}else {
			Environment.loger.log(Level.ERROR,"Number of Stays not Matched");
			SW.FailCurrentTest("Validation Fails in checking Number of Stay");
		}	
		if(SW.ObjectExists("NavigatorSearchPage_Platinum_DT")){
			String scolor=SW.GetCSSValue("NavigatorSearchPage_Platinum_DT", "background-color");
			if(SW.CompareText("Checking enabled color", color, scolor)){
				Environment.loger.log(Level.INFO, "Platinum Benefits are enabled" );
				if(SW.ObjectExists("NavigatorSearchPage_Platinum50Benifit_IC")){
					scolor=SW.GetCSSValue("NavigatorSearchPage_Platinum50Benifit_IC", "background-color");
					if(SW.CompareText("Checking enabled color", color, scolor)){
						Environment.loger.log(Level.INFO, "Platinum Benefits 50 are enabled");
					}else {
						Environment.loger.log(Level.ERROR,"Platinum 50 benifits are not enabled and color not ");
						SW.FailCurrentTest("Validation Fails in checking Platinum 50 benifits");
					}
				}else {
					Environment.loger.log(Level.ERROR,"Platinum 50 benifits are not enabled");
					SW.FailCurrentTest("Validation Fails in checking Platinum 50 benifits");
				}
			}else {
				Environment.loger.log(Level.ERROR,"There is no Platinum benifit for the member");
				SW.FailCurrentTest("Validation Fails in checking platinum benifits");
			}	
		}else {
			Environment.loger.log(Level.ERROR,"There is no Platinum benifit for the member");
			SW.FailCurrentTest("Validation Fails in checking platinum benifits");
		}	
		SW.Click("NavigatorSearchPage_BenefitCancel_BT");
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
