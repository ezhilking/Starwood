package testscripts.sgrRegression;
/* Purpose		: To validate the guest type update in in-house table 
 * TestCase Name: Validate the guest type under Guest In-house and guest Arriving today section by updating the Guest status from SVOGR
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: Ezhilarasan.S
 * Reviewed Date: 03/22/2016
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG06_Validate_GuestType_By_Updating_In_Guest_Profile {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test
	public void validate_GuestType(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SVOPropertyID"));
		SW.Click("SGRNavigation_Home_LK");
		SW.Wait(8);
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
		if(!SW.ObjectExists("SGRHomepage_InHouseSVOFirstGuest_LK")){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
		}
		String ExistingGuestType = SW.GetAttributeValue("SGRHomepage_InHouseSVOFirstGuestType_LK", "title");
		SW.Click("SGRHomepage_InHouseSVOFirstGuest_LK");
		SW.WaitTillElementToBeClickable("SGRHomepage_SVOStayDates_ST");
		SW.SwitchToFrame("SGRGuestProfile_LeadStatus_FR");

		// Select the Guest type apart from the selected option
		int GuestTypeDDSize = SW.DropDown_GetSize("SGRGuestDetails_GuestType_DD");
		List<String> GuestTypeDDText = new ArrayList<String>();
		GuestTypeDDText = SW.DropDown_GetText("SGRGuestDetails_GuestType_DD"); // Get all the text from Guest Type DD

		for(int CurrentIndex=1;CurrentIndex<GuestTypeDDSize;CurrentIndex++){
			if(!(SW.CompareText(ExistingGuestType, GuestTypeDDText.get(CurrentIndex)))){//Comparing with 'ExistingGuestType' value. If its not the same string then select the dropdown.
				SW.DropDown_SelectByIndex("SGRGuestDetails_GuestType_DD", CurrentIndex);
				break;
			}
		}

		// Get the selected text in guest details dropdown to validate in Homepage
		String SelectedTextinGuestDetails = SW.DropDown_GetSelectedText("SGRGuestDetails_GuestType_DD");
		SW.DropDown_SelectByIndex("SGRGuestProfile_ContactType_DD", 1);
		SW.DropDown_SelectByIndex("SGRGuestProfile_LeadStatus_DD", 1);
		SW.Click("SGRGuestProfile_SaveRemark_BN");
		SW.WaitTillElementToBeClickable("SGRGuestProfile_SaveRemark_BN");
		SW.SwitchToFrame(""); // Switch to default frame
		SW.Click("SGRNavigation_Home_LK");
		SW.SwitchToFrame("SGRHomepage_InHouse_FR");
		SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");

		// Get the current selected guest type and validate in the home page 
		String CurrentGuestType = SW.GetAttributeValue("SGRHomepage_InHouseSVOFirstGuestType_LK", "title");
		SW.CompareText("ValidateTheGuest", SelectedTextinGuestDetails,CurrentGuestType);
		SW.SwitchToFrame("");
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
