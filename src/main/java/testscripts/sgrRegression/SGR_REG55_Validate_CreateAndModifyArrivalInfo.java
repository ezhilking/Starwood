package testscripts.sgrRegression;
/** Purpose		: Validate Arrival Info functionality
 * TestCase Name: Create Arrival info from GuestMasterProfile page in SGR
 * 				  Modify Arrival Info from GuestMasterProfile page in SGR
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

public class SGR_REG55_Validate_CreateAndModifyArrivalInfo {
	CRM SW = new CRM();
	String EventNotes,EventID;
	
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("GC");
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateArrivalInfo(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1967");	//	Stage : 1965
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(15);
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[5]";
		SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[3]");
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("ValidateGuestAvailability", "Guest should present in list", "Guest is not present in the list", "FAIL");
		}
		SW.Click(FirstGuestLink);
		SW.Click("SGRGuestProfile_ArrivalInfo_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.DropDown_SelectByText("SGRArrivalInfo_AMBStatus_DD", "Could not capture info");
		SW.Click("SGRArrivalInfo_Save_BT");
		SW.Wait(5);
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("SGRGuestProfile_ArrivalInfoInComp_IC")){
			Reporter.Write("Validate Incomplete Icon in Guest profile ", "In Complete Icon Should present", "In Complete Icon is present", "PASS");
		}else{
			Reporter.Write("Validate Incomplete Icon in Guest profile ", "In Complete Icon Should present", "In Complete Icon is not present", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateArrivalInfo")
	public void ModifyArrivalInfo(){
		SW.Click("SGRGuestProfile_ArrivalInfo_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.DropDown_SelectByText("SGRArrivalInfo_AMBStatus_DD", "Info Complete");
		SW.Wait(3);
		SW.DropDown_SelectByText("SGRArrivalInfo_HotelArrival_DD", "TBD - I dont know");
		SW.Click("SGRArrivalInfo_Save_BT");
		SW.Wait(5);
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("SGRGuestProfile_ArrivalInfoComp_IC")){
			Reporter.Write("Validate complete Icon in Guest profile ", "Complete Icon Should present", "Complete Icon is present", "PASS");
		}else{
			Reporter.Write("Validate complete Icon in Guest profile ", "Complete Icon Should present", "Complete Icon is not present", "FAIL");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
