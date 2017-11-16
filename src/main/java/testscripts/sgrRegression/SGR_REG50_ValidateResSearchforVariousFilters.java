package testscripts.sgrRegression;
/** Purpose		: Validate Res Search functionality for different filters 
 * TestCase Name: 1. Validate Res Search with Arrival and Departure date of the guest
 * 				  2. Validate Res Search with PMS number
 * 				  3. Validate Res Search with Challenging Guest
 * 				  4. Validate Res Search with VIP Level
 * 				  5. Validate Res Search with Guest First Name and Last Name
 * Created By	: Sachin G
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG50_ValidateResSearchforVariousFilters {
	CRM SW = new CRM();
	String SPG,FirstName, LastName, ArrivalDT, DepartureDT,PMSConf;

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void GetGuestDataFromInhouse(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("Check for guest availablity", "Guest Should present in Inhouse", "Guests are not present in Inhouse", "FAIL");
		}
		String FristNLastName=SW.GetText(FirstGuestLink);
		SW.Click(FirstGuestLink);
		//Update to challenging guest
		SW.CheckBox("SGRGuestProfile_ChallengingIndicator_CB", "ON");
		SW.Wait(3);// wait to save the changes 
		
		//Update the VIP Level
		SW.DropDown_SelectByText("SGRGuestProfile_VIPLevel_DD", "WHO 1");
		SW.Wait(3);// wait to save the changes 
		
		FirstName=FristNLastName.substring(FristNLastName.indexOf(",")+1, FristNLastName.length()).trim();
		LastName=FristNLastName.substring(0, FristNLastName.indexOf(",")).trim();
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewEvent_BT");
		ArrivalDT=SW.GetText("SGRGuestProfile_ArrivalDate_ST");
		DepartureDT=SW.GetText("SGRGuestProfile_DepartureDate_ST");
		PMSConf=SW.GetText("SGRGuestProfile_PMSConfNum_ST");
		
		Environment.loger.log(Level.INFO, "SPG Number name Selected is :"+SPG);
		Environment.loger.log(Level.INFO, "First name Selected is :"+FirstName);
		Environment.loger.log(Level.INFO, "Last name Selected is :"+LastName);
		Environment.loger.log(Level.INFO, "Guest Arrival Date :"+ArrivalDT);
		Environment.loger.log(Level.INFO, "Guest Departure Date :"+DepartureDT);
		Environment.loger.log(Level.INFO, "PMS Confirmation number :"+PMSConf);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.WaitTillElementToBeClickable("SGRResSearch_GuestLastName_EB");
	}
	@Test(priority=2, dependsOnMethods="GetGuestDataFromInhouse")
	public void ValidateProfileSearchFnameLname(){
		SW.EnterValue("SGRResSearch_ArrivalDateStart_EB", ArrivalDT);
		SW.EnterValue("SGRResSearch_ArrivalDateEnd_EB", DepartureDT);
		SW.EnterValue("SGRResSearch_GuestLastName_EB", LastName);
		SW.EnterValue("SGRResSearch_GuestFirstName_EB", FirstName);
		SW.Click("SGRResSearch_Submit_BT");
		if(SW.ObjectExists("//table[@id='resSearchResultsTBL']//td/a[contains(.,'"+FirstName+"')]")){
			Reporter.Write("Validate Guest Details", "Records should show for guest FName and LName", "Records Showed for guest FName and LName", "PASS");
		}else{
			Environment.loger.log(Level.ERROR, "Guest record is not displayed First name and Last name search");
			Reporter.Write("Validate Guest Details", "Records should show for guest FName and LName", "Records  Not Showed for guest FName and LName", "FAIL");
		}
		
	}
	@Test(priority=3, dependsOnMethods="GetGuestDataFromInhouse")
	public void ValidateProfileSearchPMS(){
		SW.ClearValue("SGRResSearch_GuestFirstName_EB");
		SW.ClearValue("SGRResSearch_GuestLastName_EB");
		SW.EnterValue("SGRResSearch_PMSConfNum_EB", PMSConf);
		SW.Click("SGRResSearch_Submit_BT");
		if(SW.ObjectExists("//table[@id='resSearchResultsTBL']//td[text()='"+PMSConf+"']")){
			Reporter.Write("Validate Guest Details", "Records should show for Guest PMS Number", "Records Showed for PMS Number", "PASS");
		}else{
			Reporter.Write("Validate Guest Details", "Records should show for guest PMS Number", "Records  Not Showed for guest PMS Number", "FAIL");
		}
	}
	@Test(priority=4, dependsOnMethods="GetGuestDataFromInhouse")
	public void ValidateProfileSearchVIP(){
		SW.ClearValue("SGRResSearch_PMSConfNum_EB");
		SW.DropDown_SelectByText("SGRResSearch_VIPLevel_DD", "WHO 1");
		
		SW.Click("SGRResSearch_Submit_BT");
		if(SW.ObjectExists("//table[@id='resSearchResultsTBL']//td/a[contains(.,'"+FirstName+"')]")){
			Reporter.Write("Validate Guest Details", "Records should show for Guest VIP Level", "Records Showed for VIP Level", "PASS");
		}else{
			Reporter.Write("Validate Guest Details", "Records should show for guest VIP Level", "Records  Not Showed for guest VIP Level", "FAIL");
		}
	}
	@Test(priority=5, dependsOnMethods="GetGuestDataFromInhouse")
	public void ValidateProfileSearchChallengingGuest(){
		SW.DropDown_SelectByText("SGRResSearch_VIPLevel_DD", "ANY");
		SW.CheckBox("SGRResSearch_ChallengingGuest_CB", "ON");
		SW.Click("SGRResSearch_Submit_BT");
		if(SW.ObjectExists("//table[@id='resSearchResultsTBL']//td/a[contains(.,'"+FirstName+"')]")){
			Reporter.Write("Validate Guest Details", "Records should show for Challenging Guest Filter", "Records Showed for Challenging Guest Filter", "PASS");
		}else{
			Reporter.Write("Validate Guest Details", "Records should show for Challenging Guest Filter", "Records  Not Showed for Challenging Guest Filter", "FAIL");
		}
	}
	@Test(priority=6, dependsOnMethods="GetGuestDataFromInhouse")
	public void ValidateProfileSearchArrivalDeparture(){
		SW.CheckBox("SGRResSearch_ChallengingGuest_CB", "OFF");
		SW.Click("SGRResSearch_Submit_BT");
		if(SW.ObjectExists("//table[@id='resSearchResultsTBL']//td/a[contains(.,'"+FirstName+"')]")){
			Reporter.Write("Validate Guest Details", "Records should show for Arrival And Departure Date Range", "Records Showed for Arrival And Departure Date Range", "PASS");
		}else{
			Reporter.Write("Validate Guest Details", "Records should show for Arrival And Departure Date Range", "Records  Not Showed for Arrival And Departure Date Range", "FAIL");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
