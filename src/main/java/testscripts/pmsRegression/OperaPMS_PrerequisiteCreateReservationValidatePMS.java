package testscripts.pmsRegression;

import java.util.Calendar;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.Utility;

public class OperaPMS_PrerequisiteCreateReservationValidatePMS {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("GC");
		SW.LaunchBrowser(Environment.BWURL+"1965");
	}

	String ReservationNo, AmbFileID;
	@Test(priority=0)
	public void createNewReservationForAMBGuest(){
		try{
			SW.NormalClick("BWLogin_SignInUserNameSPG_BN");
			SW.EnterValue("BWLogin_Username_SPGNo_EB", SW.TestData("BWAMBUsername"));
			SW.EnterValue("BWLogin_Password_EB", SW.TestData("BWAMBPassword"));
			SW.NormalClick("BWLogin_SignIn_BN");
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
			SW.EnterValue("BWLogin_CheckInDate_TB", SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"),"MM/dd/yyyy",5,Calendar.DATE));
			SW.EnterValue("BWLogin_CheckOutDate_TB", SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"),"MM/dd/yyyy",6,Calendar.DATE)+Keys.TAB);
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
			if(SW.ObjectExists("BWGeneral_FeedBackPopUp_BN"))
				SW.Click("BWGeneral_FeedBackPopUp_BN");
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
	public void validateResrvationInSGR(){
		Environment.SetBrowserToUse("IE");
		SW.LaunchBrowser(Environment.SGRURL);//TODO
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), "1965");
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		if(!SW.ObjectExists("SGRResSearch_Results_WT")){
			Environment.loger.log(Level.ERROR, "Reservation is not reached to SGR ");
			SW.FailCurrentTest("Reservation is not reached to SGR");
		}else{
			Environment.loger.log(Level.INFO, " Reservation No: "+ReservationNo+" is available SGR ");
		}
		SW.Click("SGR_Logout_LK");
	}

	@Test(priority=2, dependsOnMethods="validateResrvationInSGR")
	public void validateAMBGuestInPMS(){
		//TODO include steps for PMS and Middleware validation 
		SW.WriteToTestData("PMSReserConfNo",ReservationNo );
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
