package testscripts.CEM_AMB;
/** Purpose		: Validate Create Activity functionality 
 * TestCase Name: Validate Create Activity functionality 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class CEM_REG_01_ValidateCreateActivity {
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
	public void createActivity(){
		SW.CEMAMBLogin(UserName,Password);
		SW.Click("CEMAMBMyGuests_FirstGuest_LK");
		SW.Wait(5);
		SW.RunJavaScript("scroll(0,600)");//Scroll down for visibility of the link 
		SW.Click("CEMAMBMyGuests_GuestActivity_LK");
		SW.Click("CEMAMBMyGuests_CreateActivity_BT");
		String Addedsub= "Sample Subject "+SW.RandomString(5);
		SW.EnterValue("CEMAMBAddActivity_Subject_EB",Addedsub);
		SW.DropDown_SelectByIndex("CEMAMBAddActivity_Group_DD", 1);
		SW.Wait(3);
		SW.DropDown_SelectByIndex("CEMAMBAddActivity_Category_DD", 1);
		SW.Wait(3);
		SW.DropDown_SelectByIndex("CEMAMBAddActivity_Detail_DD", 1);
		SW.EnterValue("CEMAMBAddActivity_Comments_EB", "Sample Comment");
		SW.EnterValue("CEMAMBAddActivity_AssignUserID_EB", UserName+Keys.ENTER);
		SW.Click("CEMAMBAddActivity_Save_BT");
		SW.Wait(8);
		SW.RunJavaScript("scroll(0,300)");
		SW.Click("CEMAMBMyGuests_ActivityRefresh_LK");
		SW.Wait(5);
		//Verify added activity
		String TableSubjectLine=SW.GetText("//div[@class='actData']//div//tr[1]/td[3]");
		SW.CompareText("AddedSubject", Addedsub, TableSubjectLine);

	}

	@AfterClass
	public void EndTest(){
		SW.Click("CEMAMB_Menu_DD");
		SW.Click("CEMAMB_LogOut_LK");
		Reporter.StopTest();		
	}

}
