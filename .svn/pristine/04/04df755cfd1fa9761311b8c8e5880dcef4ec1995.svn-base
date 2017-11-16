/* Purpose		: //TODO
 * TestCase Name: Footer Validation in all booking page for corporate website
 * Created By	: Brij 
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */

package testscripts.meetings;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MEETINGS_REG23_CorporateFooterValidation {

	CHANNELS SW = new CHANNELS();
	String cnfcNumber;
	String firstName = SW.RandomString(5);
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)
	public void FooterVaidationCorporate(){
		String url = SW.TestData("URLCorporate");
		SW.LaunchBrowser(url);	
		SW.Click("MeetingsCorporate_FooterPage_LK");	
		if((SW.ObjectExists("MeetingsCorporate_FooterMarriottPage_BT"))){
			Environment.loger.log(Level.INFO, "Object exists");
		}else{
			Environment.loger.log(Level.INFO, "Object doesn't exists");
		}	
		SW.NavigateTo(url);		
		SW.Click("MeetingsBooking_ClickonBook_BT");

		String ModifiedURL = SW.GetCurrentURL();
		SW.Click("MeetingsCorporate_FooterPage_LK");	
		if((SW.ObjectExists("MeetingsCorporate_FooterMarriottPage_BT"))){
			Environment.loger.log(Level.INFO, "Object exists");
		}else{
			Environment.loger.log(Level.INFO, "Object doesn't exists");
		}

		SW.NavigateTo(ModifiedURL);
		SW.EnterValue("MeetingsBooking_CheckIn_EB", SW.TestData("SGP_CheckIn"));
		SW.EnterValue("MeetingsBooking_CheckOut_EB", SW.TestData("SGP_CheckOut"));
		SW.Click("MeetingsBooking_Search_BT");	
		//		String ModifiedURL1 = SW.GetCurrentURL();
		//		SW.Click("MeetingsCorporate_FooterPage_LK");	
		//		if((SW.ObjectExists("MeetingsCorporate_FooterMarriottPage_BT"))){
		//			Environment.loger.log(Level.INFO, "Object exists");
		//		}else{
		//			Environment.loger.log(Level.INFO, "Object doesn't exists");
		//		}
		//		SW.NavigateTo(ModifiedURL1);

		SW.Click("MeetingsBooking_reserveLink_BT");
		String ModifiedURL2 = SW.GetCurrentURL();	
		SW.Click("MeetingsCorporate_FooterPage_LK");	
		if((SW.ObjectExists("MeetingsCorporate_FooterMarriottPage_BT"))){
			Environment.loger.log(Level.INFO, "Object exists");
		}else{
			Environment.loger.log(Level.INFO, "Object doesn't exists");
		}
		SW.NavigateTo(ModifiedURL2);

		SW.DropDown_SelectByValue("MeetingsBooking_Title_DD", "Mr.");
		SW.EnterValue("MeetingsBooking_FirstName_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsBooking_LastName_EB", lastName);
		SW.EnterValue("MeetingsBooking_Address_EB", SW.RandomString(5));
		SW.EnterValue("MeetingsBooking_city_EB", SW.RandomString(5));
		SW.DropDown_SelectByValue("MeetingsBooking_state_DD", "PA");
		SW.EnterValue("MeetingsBooking_zipCode_EB", "98562");
		SW.DropDown_SelectByValue("MeetingsBooking_telephone_DD", "0");
		SW.EnterValue("MeetingsBooking_phn_EB", "9985632103");
		SW.DropDown_SelectByValue("MeetingsBooking_cardType_DD", "VI");
		SW.EnterValue("MeetingsBooking_cardNumber_DD", "4111111111111111");
		SW.DropDown_SelectByValue("MeetingsBooking_month_DD", "09");
		SW.DropDown_SelectByValue("MeetingsBooking_year_DD", "2020");
		SW.CheckBox("MeetingsBooking_Agree_BT", "ON");	
		SW.Click("MeetingsBooking_reviewReservation_BT");

		String ModifiedURL3 = SW.GetCurrentURL();	
		SW.Click("MeetingsCorporate_FooterPage_LK");	
		if((SW.ObjectExists("MeetingsCorporate_FooterMarriottPage_BT"))){
			Environment.loger.log(Level.ERROR, "Object exists");
		}else{
			Environment.loger.log(Level.ERROR, "Object doesn't exists");
		}
		SW.NavigateTo(ModifiedURL3);

		SW.Click("MeetingsBooking_confirmButton_BT");

		try{String ConfNumber = SW.GetText("MeetingsBooking_confNo_BT");	
		String Isindex = "is";
		int StartingIndex = ConfNumber.indexOf(Isindex);
		int EndIndex  = ConfNumber.indexOf(".");
		cnfcNumber = ConfNumber.substring(StartingIndex+Isindex.length(), EndIndex).trim();
		Environment.loger.log(Level.INFO, "Confirnmation Number:"+cnfcNumber);
		if(SW.CompareText("BookingConfirnmationNumberMessage_DT", "Your confirmation number is "+cnfcNumber+".", SW.GetText("MeetingsBooking_confNo_BT")))
		{
			Reporter.WriteLog(Level.ERROR, "PASS");
		}
		else
		{
			Reporter.WriteLog(Level.ERROR, "FAIL");
		}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured in",e);
		}

		String ModifiedURL4 = SW.GetCurrentURL();	
		SW.Click("MeetingsCorporate_FooterPage_LK");	
		if((SW.ObjectExists("MeetingsCorporate_FooterMarriottPage_BT"))){
			Environment.loger.log(Level.ERROR, "Object exists");
		}else{
			Environment.loger.log(Level.ERROR, "Object doesn't exists");
		}

		SW.NavigateTo(ModifiedURL4);


	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2,dependsOnMethods="FooterVaidationCorporate")

	public void FooterVaidationCancel(){
		String cancelNumber;

		SW.Click("MeetingsLocate_checkReservation_BT");
		SW.EnterValue("MeetingsLocate_cnfcNumber_EB", cnfcNumber);
		SW.EnterValue("MeetingsLocate_lastname_BT", lastName);
		SW.Click("MeetingsLocate_Submit_BT");
		SW.ClickAndProceed("MeetingsCancel_CancelButton_BT");
		SW.HandleAlert(true);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
