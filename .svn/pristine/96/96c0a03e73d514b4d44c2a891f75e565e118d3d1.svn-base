package testscripts.CEM_AMB;
/** Purpose		:Validate Adding Non AMB id in the add contact section
 * TestCase Name: Validate Adding Non AMB id in the add contact section 
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




/** Purpose		: Validate Create Activity functionality 
 * TestCase Name: Validate Create Activity functionality 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class CEM_REG_07_ValidateAddingNonAMBInAddContactSection {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String Notes;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
	}
	@Test(priority=1)
	public void AddUserForProperty(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Wait(5);
		SW.EnterValue("CEMAMBSearch_Search_EB", "W HONG KONG");
		SW.WaitTillElementToBeClickable("CEMAMBSearch_WHongKong_LK");
		SW.Click("CEMAMBSearch_WHongKong_LK");
		SW.Wait(15);
		SW.Click("CEMAMBProperty_AddContact_LK");
		String FirstName="Fname_"+SW.RandomString(5);
		SW.EnterValue("CEMAMBAddContact_FirstName_EB",FirstName );
		SW.EnterValue("CEMAMBAddContact_LastName_EB", "LastName_"+SW.RandomString(5));
		SW.EnterValue("CEMAMBAddContact_WorkEmail_EB", "sampleemail@acc.com");
		SW.EnterValue("CEMAMBAddContact_WorkPhone_EB", "9874563210");
		SW.EnterValue("CEMAMBAddContact_Notes_EB", "Sample Notes "+SW.RandomString(5));
		SW.Click("CEMAMBAddContact_Save_BT");
		SW.Wait(15);
		if(SW.ObjectExists("//div[@class='tb ui-accordion ui-accordion-content']//tr[contains(.,'"+FirstName+"')]")){
			Environment.loger.log(Level.INFO, "Contact added successfully!!!  Added contact is present in the list");
		}else{
			Environment.loger.log(Level.ERROR, "Added Contact not present in the contact list");
			SW.FailCurrentTest("Added Contact not present in the contact list");
		}

	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
