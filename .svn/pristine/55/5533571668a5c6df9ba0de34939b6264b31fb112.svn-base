package testscripts.sgrRegression;
/** Purpose		: Validation of Comments
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

public class SGR_REG51_Validate_Create_Edit_Delete_Comments {

	CRM SW = new CRM();
	String Notes;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void CreateComment(){
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
		SW.WaitTillElementToBeClickable("SGRGuestDetails_CreateNewComment_BN");
		SW.Click("SGRGuestDetails_CreateNewComment_BN");
		SW.DropDown_SelectByIndex("SGRGuestDetails_CreateCommentDept_DD", 1);
		Notes=SW.RandomString(5);
		SW.EnterValue("SGRGuestDetails_AddComment_EB", Notes);
		SW.DropDown_SelectByIndex("SGRGuestDetails_CreateCommentVisibility_DD", 1);
		SW.Click("SGRGuestDetails_AddCommentSave_BN");
		SW.Wait(5);
		if(SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Comment is created successfully and present in guest profile");
			Reporter.Write("validate Comment", "Created Comment Should Present", "Created Comment is Present", "PASS");
		}else{
			Reporter.Write("validate Comment", "Created Comment Should Present", "Created Comment is not Present", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateComment")
	public void EditComment(){
		SW.Click("//td[contains(.,'"+Notes+"')]//ancestor::tr//a[text()='Edit']");
		SW.WaitTillElementToBeClickable("SGRGuestDetails_AddComment_EB");
		Notes="NotesUpdated_"+SW.RandomString(6);
		SW.EnterValue("SGRGuestDetails_AddComment_EB", Notes);
		SW.Click("SGRGuestDetails_AddCommentSave_BN");
		if(SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Comment is Edited successfully and present in guest profile");
			Reporter.Write("validate Comment", "Edited Comment Should Present", "Edited Comment is Present", "PASS");
		}else{
			Reporter.Write("validate Comment", "Edited Comment Should Present", "Edited Comment is not Present", "FAIL");
		}
	}
	@Test(priority=3, dependsOnMethods="EditComment")
	public void DeleteComment(){
		
		SW.ClickAndProceed("//td[contains(.,'"+Notes+"')]//ancestor::tr//a[text()='Delete']");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		if(!SW.ObjectExists("//td[contains(.,'"+Notes+"')]")){
			Environment.loger.log(Level.INFO, "Comment is Deleted successfully and not present in guest profile");
			Reporter.Write("validate Comment", "Created Comment should not present", "Created Comment is not present", "PASS");
		}else{
			Reporter.Write("validate Comment", "Created Comment should not present", "Created Comment is present", "FAIL");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
