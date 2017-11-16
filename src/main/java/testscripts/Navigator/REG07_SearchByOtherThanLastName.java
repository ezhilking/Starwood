package testscripts.Navigator;
/* Purpose		: SPG Search -Screen Layout-SPG Search Validation Messages-Search ByOther Than Last Name-Negative Test Case 
 * TestCase Name: REG07_SearchByOtherThanLastName
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG07_SearchByOtherThanLastName {
	CHANNELS SW=new CHANNELS() ;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=0)
	public void searchOtherThanLastName()
	{
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"), SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();//selecting the communication type.
		SW.NormalClick("NavigatorHomePage_SearchByName_LK");
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		//Entering the value other than the last name.
		SW.EnterValue("NavigatorHomePage_FirstName_EB", "Test");// First name
		SW.Click("NavigatorHomePage_CityOrZipCode_EB");// City
		SW.EnterValue("NavigatorHomePage_CityOrZipCode_EB", "Banglore"+Keys.TAB);
		SW.Click("NavigatorHomePage_Search_BT");
		SW.Click("NavigatorHomePage_Search_BT");
		if(SW.CompareText("Compare the error message", "This search requires a last name. Please enter last name and try again.", SW.GetText("NavigatorHomePage_ErrorMessageLastName_DT"))){
			Environment.loger.log(Level.INFO,"The Error message has displayed as Expected");
			SW.GetScreenshot("ErrorMessage");
			SW.ClearValue("NavigatorHomePage_FirstName_EB");
			SW.ClearValue("NavigatorHomePage_CityOrZipCode_EB");
			Environment.loger.log(Level.INFO, "Display message as Require LastName");
		}else{
			Environment.loger.log(Level.INFO,"Error messgae has not present");
			SW.FailCurrentTest("Validation Fails in checking Error message");
		}
	}
	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
