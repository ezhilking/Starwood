package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;
/* Purpose		: 
 * TestCase Name: Add Contacts 
 * Created By	: kumari Nitu 
 * Modified By	:
 * Modified Date:
  * Reviewed By	: Ezhilarasan.S	
 * Reviewed Date: 31-05-2016
 */

public class MUSTRUN08_AddContacts {
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
	public void AddContacts(){
		//SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.EnterValue("//input[@name='userName']", "poorman");
		SW.EnterValue("//input[@name='password']", "password");
		SW.Click("//input[@name='Submit']");
	//	if(SW.IsAlertPresent()){
	//		SW.HandleAlert(true);
	//	}
		SW.MoveToObject("ABCDHomepage_Contacts_LK");//Moving to Contacts
		SW.WaitTillElementToBeClickable("ABCDContacts_Add_LK");
		SW.Click("ABCDContacts_Add_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);

		SW.Wait(3);
		SW.EnterValue("ABCDContactAdd_FirstName_EB","FName_"+SW.RandomString(4));
		SW.EnterValue("ABCDContactAdd_LastName_EB","LName_"+SW.RandomString(4));
		SW.Click("ABCDContactAdd_Continue_BT");
		SW.Click("ABCDContactAdd_NoMatch_BT");

		//Moving to contact account
		SW.WaitTillPresenceOfElementLocated("ABCDContactAdd_Accountname_ST");
		SW.EnterValue("ABCDContactAdd_Accountname_ST", "sam");
		//Click Submit 
		SW.Click("ABCDContactAdd_Submit_BT");
		SW.Wait(5);
		//Select from Account List
		int LinkSize = SW.GetAllVisibleElements("ABCDContactAdd_AccountList_ST").size();
		String Xpath = SW.GetXPath("ABCDContactAdd_AccountList_ST")+"["+SW.RandomNumber(1, LinkSize)+"]//a";
		SW.WaitTillElementToBeClickable(Xpath);
		SW.Click(Xpath);

		SW.Wait(3);
		SW.EnterValue("ABCDContactDetails_CompanyName_ST", "Startup");
		SW.EnterValue("ABCDContactDetails_Phone_ST", "236547348");
		SW.EnterValue("ABCDContactDetail_MobileNumber_ST", "957466348");
		SW.EnterValue("ABCDContactDetail_HomeNumber_ST", "7466348");
		SW.EnterValue("ABCDContactDetail_Emailid_ST", "wrfedf@starwood.com");

		//Select the first Value from Boards
		SW.Click("ABCDContactDetail_Board_ST");
		SW.Click("ABCDContactDetail_BoardClick_LK");
		//Select the first value from Division mailing List
		SW.Click("ABCDContactDetails_DivisionMailingList_ST");
		SW.Click("ABCDContactDetails_DivisionMailingListClick_LK");

		//Select the first value  value from area of Expertise
		SW.Click("ABCDContactDetail_AreaofExp_ST");
		SW.Click("ABCDContactDetail_AreaofExpClick_LK");
		//Click on Add Contact
		SW.Click("ABCDContactDetail_AddContact_BT");
		SW.CompareText("SuccessMessage", "Contact Saved Successfully", SW.GetText("ABCDAddContact_SuccessMsg_DT"));
		SW.Click("ABCDAddContact_Close_BN");
		SW.WaitForWindowCount(1);
		SW.SwitchToWindow(1);
	}
	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}
}

