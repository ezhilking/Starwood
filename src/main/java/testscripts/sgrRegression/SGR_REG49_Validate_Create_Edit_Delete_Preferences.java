package testscripts.sgrRegression;
/** Purpose		: Validation of Preference
 * TestCase Name: Create Preference from GuestMasterProfile page in SGR
				  Modify Preference  from GuestMasterProfile page in SGR
				  Delete Preference  from GuestMasterProfile page in SGR
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

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG49_Validate_Create_Edit_Delete_Preferences {

	CRM SW = new CRM();
	String Notes;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void CreatePreference(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
		SW.Wait(8);

		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";

		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_CreatePreference_BT");
		SW.Click("SGRGuestProfile_CreatePreference_BT");
		SW.DropDown_SelectByIndex("SGRPreferenceMapping_Group_DD", 0);
		SW.DropDown_SelectByIndex("SGRPreferenceMapping_Category_DD", 0);
		SW.DropDown_SelectByIndex("SGRPreferenceMapping_Detail_DD", 0);
		
		Notes=SW.RandomString(5);
		SW.EnterValue("SGRPreference_Notes_EB", Notes);
		SW.DropDown_SelectByIndex("SGRPreference_Visibility_DD", 1);
		SW.Click("SGRPreference_Save_BT");
		
		if(SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Preference is created successfully and present in guest profile");
			Reporter.Write("validate Preference", "Created Preference Should Present", "Created Preference is Present", "PASS");
		}else{
			Reporter.Write("validate Preference", "Created Preference Should Present", "Created Preference is not Present", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreatePreference")
	public void EditPreference(){
		SW.Click("//td[contains(.,'"+Notes+"')]//ancestor::tr//a[text()=' Edit ']");
		Notes="NotesUpdated_"+SW.RandomString(6);
		SW.EnterValue("SGRPreference_Remarks_EB", Notes);
		SW.Click("SGRPreference_EditSave_BT");
		if(SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Preference is Edited successfully and present in guest profile");
			Reporter.Write("validate Preference", "Edited Preference Should Present", "Edited Preference is Present", "PASS");
		}else{
			Reporter.Write("validate Preference", "Edited Preference Should Present", "Edited Preference is not Present", "FAIL");
		}
	}
	@Test(priority=3, dependsOnMethods="EditPreference")
	public void DeletePreference(){
		
		SW.ClickAndProceed("//td[contains(.,'"+Notes+"')]//ancestor::tr//a[text()='Delete']");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		if(!SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Preference is Deleted successfully and not present in guest profile");
			Reporter.Write("validate Preference", "Created Preference should not present", "Created Preference is not present", "PASS");
		}else{
			Reporter.Write("validate Preference", "Created Preference should not present", "Created Preference is present", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
