package testscripts.sgrRegression;
/** Purpose		: Validation of Ancillary
 * TestCase Name: Create Ancillary from GuestMasterProfile page in SGR
				  Modify Ancillary  from GuestMasterProfile page in SGR
				  Delete Ancillary  from GuestMasterProfile page in SGR
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

public class SGR_REG52_Validate_Create_Edit_Delete_Ancillary {

	CRM SW = new CRM();
	String Notes;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void CreateComment(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
		SW.Wait(2);
		SW.SwitchToFrame("SGRHomepage_Arriving_FR");
		SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
		String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
		if(!SW.ObjectExists(FirstGuestLink)){
			Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
			Reporter.Write("CheckForAvailableGuest", "GuestsShouldPresent", "GuestsAreNotPresent", "FAIL");
		}
		SW.Click(FirstGuestLink);
		SW.WaitTillElementToBeClickable("SGRGuestProfile_NewAncillaryItem_BN");
		SW.Click("SGRGuestProfile_NewAncillaryItem_BN");
		SW.DropDown_SelectByIndex("SGRAncillaryItem_AncillaryCategory_DD", 0);
		SW.EnterValue("SGRAncillaryItem_AncillaryItem_EB", "AutoAncillary");
		Notes=SW.RandomString(5);
		SW.EnterValue("SGRAncillaryItem_AncillaryNotes_EB", Notes);
		SW.DropDown_SelectByIndex("SGRGuestDetails_CreateAncillaryVisibility_DD", 1);
		SW.Click("SGRAncillaryItem_Save_BN");
		SW.Wait(5);
		if(SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Ancillary is created successfully and present in guest profile");
			Reporter.Write("validate Comment", "Created Ancillary Should Present", "Created Ancillary is Present", "PASS");
		}else{
			Reporter.Write("validate Comment", "Created Ancillary Should Present", "Created Ancillary is not Present", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateComment")
	public void EditComment(){
		SW.Click("//td[contains(.,'"+Notes+"')]//ancestor::tr//a[text()='Edit']");
		SW.WaitTillElementToBeClickable("SGRAncillaryItem_AncillaryNotes_EB");
		Notes="NotesUpdated_"+SW.RandomString(6);
		SW.EnterValue("SGRAncillaryItem_AncillaryNotes_EB", Notes);
		SW.DropDown_SelectByIndex("SGRGuestDetails_CreateAncillaryVisibility_DD", 1);//TODO remove this once the changes are done in application
		SW.Click("SGRAncillaryItem_Save_BN");
		if(SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Ancillary is Edited successfully and present in guest profile");
			Reporter.Write("validate Comment", "Edited Ancillary Should Present", "Edited Ancillary is Present", "PASS");
		}else{
			Reporter.Write("validate Comment", "Edited Ancillary Should Present", "Edited Ancillary is not Present", "FAIL");
		}
	}
	@Test(priority=3, dependsOnMethods="EditComment")
	public void DeleteComment(){
		SW.ClickAndProceed("//td[contains(.,'"+Notes+"')]//ancestor::tr//a[text()='Delete']");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		if(!SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Ancillary is Deleted successfully and not present in guest profile");
			Reporter.Write("validate Comment", "Created Ancillary should not present", "Created Ancillary is not present", "PASS");
		}else{
			Reporter.Write("validate Comment", "Created Ancillary should not present", "Created Ancillary is present", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
