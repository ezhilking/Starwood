package testscripts.CEM_AMB;
/** Purpose		: Validate My Guests tab when a guest is tagged to different ambassador 
 * TestCase Name: Validate My Guests tab when a guest is tagged to different ambassador 
 * Created By	: Sachin
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

public class CEM_REG_05_ValidateMyGuestsTabGuestTaggedDifferentAmbassador {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String SPGNum;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
		
	}
	@Test(priority=1)
	public void ValidateMyGuestTabByTaggingAmb(){
		
		SW.CEMAMBLogin(UserName,Password);
		SPGNum=SW.GetText("CEMAMBMyGuests_SPGNumFirstGuest_ST");
		
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(20);
		SW.Click("CEMAMBMyGuests_Ambasidor_LK");
		SW.Wait(5);
		SW.DropDown_SelectByIndex("CEMAMBMyGuests_NewAmbasidor_DD", 1);
		SW.Click("CEMAMBMyGuests_SubmitTickMark_BT");
		SW.Wait(5);
		SW.Click("CEMAMBMyGuests_MyGuests_LK");
		SW.Click("CEMAMBMyGuests_ReloadGuest_LK");
		SW.Wait(10);
		SW.EnterValue("CEMAMBMyGuests_Search_EB", SPGNum);
		SW.Wait(15);
		if(!SW.ObjectExists("//table[@id='myGuestTable']/tbody/tr[1]//td[contains(.,'"+SPGNum+"')]")){
			Environment.loger.log(Level.INFO, "Guest Tagged to diffrent Ambasidor successfully and not showing in the guest list");
		}else{
			Environment.loger.log(Level.ERROR, "Guest is still present in the list after changing the Ambasidor");
			SW.FailCurrentTest("Guest is still present in the list after changing the Ambasidor");
		}
		
		
	}
	@Test(priority=2, dependsOnMethods="ValidateMyGuestTabByTaggingAmb")
	public void ValidateMyGuestTabByReTaggingAmb(){
		
		SW.Click("CEMAMBMyGuests_OpenedGuestTab_ST");
		
		SW.Click("CEMAMBMyGuests_Ambasidor_LK");
		SW.Wait(5);
		SW.DropDown_SelectByValue("CEMAMBMyGuests_NewAmbasidor_DD", UserName);
		SW.Click("CEMAMBMyGuests_SubmitTickMark_BT");
		SW.Wait(5);
		SW.Click("CEMAMBMyGuests_MyGuests_LK");
		SW.Click("CEMAMBMyGuests_ReloadGuest_LK");
		SW.Wait(10);
		SW.EnterValue("CEMAMBMyGuests_Search_EB", SPGNum);
		SW.Wait(15);
		if(SW.ObjectExists("//table[@id='myGuestTable']/tbody/tr[1]//td[contains(.,'"+SPGNum+"')]")){
			Environment.loger.log(Level.INFO, "Guest Tagged back to same Ambasidor successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Guest is not present in the list after changing to old Ambasidor");
			SW.FailCurrentTest("Guest is not present in the list after changing to old Ambasidor");
		}
		
	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
