package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		: Adding Activity and validating the success message.  
 * TestCase Name: Add Activity 
 * Created By	: Kumari Nitu 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: Ezhilarasan.S	
 * Reviewed Date: 31-05-2016
 */
public class MUSTRUN03_AddActivity {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		//SW.LaunchBrowser(Environment.ABCD);
		SW.LaunchBrowser(Environment.ABCD); 
	}
	@Test
	public void AddActivity(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		//SW.EnterValue("//input[@name='userName']", "poorman");
		//SW.EnterValue("//input[@name='password']", "password");
		SW.Click("//input[@name='Submit']");
		//		if(SW.IsAlertPresent()){ 
		//			SW.HandleAlert(true);
		//		}
		//Moving cursor on Activity 
		SW.MoveToObject("ABCD_Activities_LK");
		SW.Click("ABCD_AddActivity_LK");//Moving to Add Activity Page
		if(!SW.ObjectExists("ABCD_AddActivityType_DD")){//if dropdown object not exists navigate to the Add activity page directly.
			//SW.NavigateTo(Environment.ABCD+"/activities/ActivityAddAction.do?source.value=activity&action=add");
			SW.NavigateTo("https://phxabcqas61.nssd.star:9443/activities/ActivityAddAction.do?source.value=activity&action=add");
		}
		SW.DropDown_SelectByIndex("ABCD_AddActivityType_DD",3);//Select add activity in dropdown
		SW.RunJavaScript("document.getElementById('_js_item_activityDate').value='"+SW.GetTimeStamp("d-MMM-yyyy")+"';");//Set the current date
		SW.NormalClick("ABCD_AddAccountName_LK");//Account Name link
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("ABCD_AccountNameShowAll_LK");
		SW.Wait(5);
		int AccNameLinkSize = SW.GetAllVisibleElements("ABCDActivity_AccountName_LK").size();
		String AccNameLinkXpath = SW.GetXPath("ABCDActivity_AccountName_LK")+"["+SW.RandomNumber(1, AccNameLinkSize)+"]/a";
		SW.WaitTillElementToBeClickable(AccNameLinkXpath);
		SW.Click(AccNameLinkXpath);
		SW.WaitForWindowCount(1);
		SW.SwitchToWindow(1);//Switch back to the primary window

		SW.NormalClick("ABCD_DealName_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("ABCD_AccountNameShowAll_LK");
		SW.Wait(2);
		int LinkSize = SW.GetAllVisibleElements("ABCD_OneDealAcc_ST").size();
		String Xpath = SW.GetXPath("ABCD_OneDealAcc_ST")+"["+SW.RandomNumber(1, LinkSize)+"]";
		SW.WaitTillElementToBeClickable(Xpath);
		SW.Click(Xpath);

		SW.WaitForWindowCount(1);
		SW.SwitchToWindow(1);//Switch back to the primary window
		SW.ClickAndProceed("ABCD_SaveActivity_BT");
		SW.HandleAlert(true);//TODO
		SW.CompareText("AddActivitySuccesssfull", "You have successfully saved your changes", SW.GetText("ABCDAddActivity_SuccessMsg_DT"));
	}
	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}
}
