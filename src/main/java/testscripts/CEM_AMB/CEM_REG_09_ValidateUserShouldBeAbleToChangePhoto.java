package testscripts.CEM_AMB;
/** Purpose		: Validate that user should be able to change photo
 * TestCase Name: Validate that user should be able to change photo
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

public class CEM_REG_09_ValidateUserShouldBeAbleToChangePhoto {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String FilePath=Environment.Documents+"\\CRM\\GuestPic.jpg";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
	}
	@Test
	public void UpdateGuestPic(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(5);
		SW.Click("CEMAMBMyGuests_ChangePhoto_BT");
		SW.Wait(10);
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		if(!SW.ObjectExists("CEMAMBFileUpload_BrowsePhoto_EB")){
			Environment.loger.log(Level.ERROR, "File Upload button is not available");
			SW.FailCurrentTest("File Upload button is not available");
		}
		SW.EnterValue("CEMAMBFileUpload_BrowsePhoto_EB", FilePath);
		SW.EnterValue("CEMAMBFileUpload_Permisson_EB", "SamplePermisson");
		SW.Click("CEMAMBFileUpload_Upload_BT");
		SW.Wait(15);
		SW.WaitForWindowCount(1);
		SW.SwitchToWindow(1);

		if(SW.ObjectExists("CEMAMBMyGuests_GuestPhoto_IC")){
			Environment.loger.log(Level.INFO, "Guest Photo Updated Successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to Update guest photo");
			SW.FailCurrentTest("Failed to Update guest photo");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
