package testscripts.CEM_AMB;
/** Purpose		: Validate Create Property Alert functionality 
 * TestCase Name: Validate Create Property Alert functionality 
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

public class CEM_REG_04_ValidatePropertyAlert {
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
	public void createPropAlert(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Wait(5);
		SW.EnterValue("CEMAMBSearch_Search_EB", "W HONG KONG");
		SW.WaitTillElementToBeClickable("CEMAMBSearch_WHongKong_LK");
		SW.Click("CEMAMBSearch_WHongKong_LK");
		SW.Wait(15);
	//	SW.RunJavaScript("scroll(0,1000)");
		SW.Click("CEMAMBPropertyAlert_PropertAlert_LK");
		//SW.RunJavaScript("scroll(0,1000)");
		SW.Click("CEMAMBPropertyAlert_CreateAlert_BT");
		SW.WaitTillElementToBeClickable("CEMAMBPropertyAlert_AlertText_EB");
		String AlertText=SW.RandomString(8);
		SW.EnterValue("CEMAMBPropertyAlert_AlertText_EB", AlertText);
		SW.Click("CEMAMBPropertyAlert_Save_BT");
		SW.Wait(10);
		if(SW.ObjectExists("//td//div[contains(.,'"+AlertText+"')]")){
			Environment.loger.log(Level.INFO, "Property Alert added successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Property Alert Not added successfully!!!");
			SW.FailCurrentTest("Property Alert Not added!!!");                                  
		}
		//SW.Click("CEMAMBPropertyAlert_Refresh_BT");
		SW.RefreshPage();
		SW.EnterValue("CEMAMBSearch_Search_EB", "W HONG KONG");
		SW.WaitTillElementToBeClickable("CEMAMBSearch_WHongKong_LK");
		SW.Click("CEMAMBSearch_WHongKong_LK");
		SW.Wait(15);
		if(SW.ObjectExists("//div[@class='alert alert-danger'][2]//div[contains(.,'"+AlertText+"')]")){
			Environment.loger.log(Level.INFO, "Property Alert is displayed in alert section");
		}else{
			Environment.loger.log(Level.ERROR, "Property Alert is not displayed in alert section!!!");
			SW.FailCurrentTest("Property Alert is not displayed in alert section!!!");
		}

	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}
}
