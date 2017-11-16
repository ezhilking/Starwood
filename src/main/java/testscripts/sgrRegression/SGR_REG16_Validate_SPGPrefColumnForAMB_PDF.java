package testscripts.sgrRegression;
/* Purpose		: Validate the SPG preferences Column in MAR by adding SPG Preferences to AMB guests_Room Assignment 
 * tab_Ambassador tab_PDF version
 * TestCase Name: This is ti validate that the guests SPG preferences displays in the SPG preference s column of the 
 * Ambassador tab of the master arrival report
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.FileUtil;
import functions.Reporter;
import functions.Utility;

public class SGR_REG16_Validate_SPGPrefColumnForAMB_PDF {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.BWURL+"1965");
	}

	String ReservationNo, Date, GuestName;
	@Test(priority=0)
	public void createNewReservationForAMBGuest(){
		try{
			SW.NormalClick("BWLogin_SignInUserNameSPG_BN");
			SW.EnterValue("BWLogin_Username_SPGNo_EB", SW.TestData("BWAMBUsername"));
			SW.EnterValue("BWLogin_Password_EB", SW.TestData("BWAMBPassword"));
			SW.Click("BWLogin_SignIn_BN");
			
			// If Security Question appears give answer otherwise proceed 
			if(SW.ObjectExists("BWSecurityQuestions_VerbelPassword_TB")){

				String SecurityQn=SW.GetText("//form[@id='securityQForm']//div[@class='outerContainer']//p");
				String lastWord = SecurityQn.substring(SecurityQn.lastIndexOf(" ")+1, SecurityQn.indexOf("?"));
				SW.EnterValue("BWSecurityQuestions_VerbelPassword_TB", lastWord.trim());
				SW.Click("BWSecurityQuestions_Submit_BN");
			}
			
			if(!SW.ObjectExists("BWLogin_CheckInDate_TB")){
				
				Environment.loger.log(Level.ERROR, "login is not successfull");
				SW.FailCurrentTest("login is not successfull");
				
			}
				
			// Enter the Check-in and Check-out time
			SW.EnterValue("BWLogin_CheckInDate_TB", SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"),"MM/dd/yyyy",3,Calendar.DATE));
			SW.EnterValue("BWLogin_CheckOutDate_TB", SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"),"MM/dd/yyyy",4,Calendar.DATE)+Keys.TAB);
			SW.Click("BWLogin_BookNow_BN");
			if(SW.ObjectExists("BWGeneral_FeedBackPopUp_BN"))
				SW.Click("BWGeneral_FeedBackPopUp_BN");
			SW.Click("BWSelectRoom_SelectYourRate_BN");
			SW.NormalClick("BWSelectRoom_Reserve_BN");

			//Select Arrival time 
			SW.DropDown_SelectByIndex("BWReviewReservation_ReqArrivalTime_DD", 6);
			String SelectedArrivalTime = SW.DropDown_GetSelectedText("BWReviewReservation_ReqArrivalTime_DD");
			Environment.loger.log(Level.INFO, "Selected Arrival Time : "+SelectedArrivalTime);

			//Select Departure time 
			SW.DropDown_SelectByIndex("BWReviewReservation_ReqDepartureTime_DD", 5);
			String SelectedDepartureTime = SW.DropDown_GetSelectedText("BWReviewReservation_ReqDepartureTime_DD");
			Environment.loger.log(Level.INFO, "Selected Departure Time : "+SelectedDepartureTime);
			SW.NormalClick("BWReviewReservation_CompleteYourReservation_BN");
			if(SW.ObjectExists("BWGeneral_FeedBackPopUp_BN"))
				SW.Click("BWGeneral_FeedBackPopUp_BN");
			SW.WaitTillElementToBeClickable("BWReservationConfirmation_ConfirmationNum_DT");
			ReservationNo = SW.GetText("BWReservationConfirmation_ConfirmationNum_DT");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number is :"+ReservationNo);
			SW.Click("BWGeneral_UserName_ST");
			SW.Click("BWGeneral_SignOut_LK");
			Utility.CloseBrowser();
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
			SW.FailCurrentTest("Exception occured!!");
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-SGR Validation*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=1, dependsOnMethods="createNewReservationForAMBGuest")
	public void validateGuestPrefInSGR(){
		//ReservationNo="604091268";
		Environment.SetBrowserToUse("IE");
		SW.LaunchBrowser(Environment.SGRURL);//TODO
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.Wait(5);
		//if Reservation Not reached to SGR fail the test case
		if(!SW.ObjectExists("SGRResSearch_Results_WT")){
			Environment.loger.log(Level.ERROR, "Reservation is not reached to SGR ");
			SW.FailCurrentTest("Reservation is not reached to SGR");
		}
		
		SW.EstablishConnection("stage");//TODO Change to Stage once the DB issue resolved
		String Query = "call odsguest.pkg_guest_arvl_data_syncup.p_populate_by_resconf("+ReservationNo+")";
		SW.CallProcedure(Query);
		
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		Date = SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", 3, Calendar.DATE);
		SW.RunJavaScript("document.getElementsByName('beginDate3')[0].value='"+Date+"';");//Set the date
		SW.RunJavaScript("document.getElementsByName('endDate3')[0].value='"+Date+"';");//Set the date
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.RunJavaScript("SGRArrivalReport_GridTableBottom_WB","document.getElementsByClassName('ui-jqgrid-bdiv')[0].style['overflow'] = 'visible';");
		
		String GuestDetailsInMARXpath="//table[@id='report6']//td[3]//a[contains(@href,'"+ReservationNo+"')]";
		String GuestDEFPrefInMARXpath="//table[@id='report6']//td[3]//a[contains(@href,'"+ReservationNo+"')]//ancestor::tr//td[16]/table[@id='preftab']//th//*[text()='DEF']";
		String GuestNELPrefINMARXpath="//table[@id='report6']//td[3]//a[contains(@href,'"+ReservationNo+"')]//ancestor::tr//td[16]/table[@id='preftab']//th//*[text()='NEL']";
		if(SW.ObjectExists(GuestDetailsInMARXpath)){
			Environment.loger.log(Level.INFO, "Guest Is present in the MAR");
		}else{
			Environment.loger.log(Level.ERROR, "Guest Is not available in the MAR");
			SW.FailCurrentTest("Guest Is not available in the MAR");
		}
		if(SW.ObjectExists(GuestDEFPrefInMARXpath)){
			Environment.loger.log(Level.INFO, "Guest DEF Prefrence Is  Present in the MAR");
		}else{
			Environment.loger.log(Level.ERROR, "Guest DEF Prefrence Is not available in the MAR");
			SW.FailCurrentTest("Guest DEF Prefrence Is not available in the MAR");
		}
		if(SW.ObjectExists(GuestNELPrefINMARXpath)){
			Environment.loger.log(Level.INFO, "Guest NEL Prefrence is Present in the MAR");
		}else{
			Environment.loger.log(Level.ERROR, "Guest NEL Prefrence Is not available in the MAR");
			SW.FailCurrentTest("Guest NEL Prefrence Is not available in the MAR");
		}
	}

	@Test(priority=2, dependsOnMethods="createNewReservationForAMBGuest")
	public void validateGuestPrefInPDF(){
		
		GuestName=SW.GetText("//table[@id='report6']//td[3]//a[contains(@href,'"+ReservationNo+"')]//span");
		
		FileUtil PDF = SW.DownloadFile("Room Assignment","Room Assignment_Arrival.pdf","Arriving");
		PDF.ExtractText();
		PDF.ValidateText("U Rank Column", "U.Rank");
		PDF.ValidateText("GuestDetails column", "Guest Details");
		PDF.ValidateText("SPG column", "SPG Stay Confirmed");
		PDF.ValidateText("Brand column", "Brand Stay Confirmed");
		PDF.ValidateText("GuestURank", "1");
		PDF.ValidateText("Guest Name", GuestName);
		PDF.ValidateText("GuestDEFPrefrence", "DEF");
		PDF.ValidateText("GuestNELPrefrence", "NEL");
		FileUtil PDFAMB=SW.DownloadFile("Ambassador","Ambassador_Arrival.pdf","Arriving");
		PDFAMB.ExtractText();
		PDFAMB.ValidateText("Ambassador GuestDetails column", "Guest Details");
		PDFAMB.ValidateText("Ambassador SPG column", "SPG Stay Confirmed");
		PDFAMB.ValidateText("Ambassador Guest Name", GuestName);
		PDFAMB.ValidateText("Ambassador GuestDEFPrefrence", "DEF");
		PDFAMB.ValidateText("AmbassadorGuestNELPrefrence", "NEL");
		
	}

	@AfterClass
	public void EndTest(){

		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

