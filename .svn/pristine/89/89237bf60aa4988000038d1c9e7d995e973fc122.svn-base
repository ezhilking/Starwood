package testscripts.CEM_AMB;
/** Purpose		: Validate downloading Multiple Message when Messages are selected and download option is selected from Action dropdown
 * TestCase Name: Validate downloading Multiple Message when Messages are selected and download option is selected from Action dropdown
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

public class CEM_REG_10_ValidateDownloadingMultipleMessage {
	CRM SW = new CRM();
	String UserName;
	String Password;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CEM_AMBURL);
		UserName=SW.TestData("CEMAMBUsername");
		Password=SW.TestData("CEMAMBPassword");
	}
	@Test
	public void DownloadMessage(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_Messaging_LK");
		SW.CheckBox("CEMAMBMessaging_includeAllQueues_CB", "ON");
		SW.Click("//select[@id='queueIdSelect']/option[not(contains(.,'(0)'))][2]");//xpath will select all the options which are having one or more emails
		SW.Wait(15);
		SW.WaitTillElementToBeClickable("CEMAMBMessaging_SelectFirstMessage_CB");
		
		SW.CheckBox("CEMAMBMessaging_SelectFirstMessage_CB", "ON");
		//SW.CheckBox("(//input[@name='btSelectItem'])[2]", "ON");
		//SW.CheckBox("CEMAMBMessaging_SelectAllMessages_CB", "ON");
		
		SW.Click("CEMAMBMessaging_DownloadMessages_BT");
		SW.Wait(15);
		if(SW.ObjectExists("CEMAMBMessaging_MessageDownloadConfirmation_BT")){
			Environment.loger.log(Level.INFO, "Messages Downloaded successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to Download messages");
			SW.FailCurrentTest("Failed to Download messages");
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
