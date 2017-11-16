package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN02_DealNextStepNotesServices {
	SALES SW = new SALES();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void DealNextStepNotesServices(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.MoveToObject("ABCD_AdminLink_LK");
		SW.Click("ABCD_AdminNextStep_LK");		
		
		//Select value from Drop d
		int NoteDD = SW.DropDown_GetSize("ABCD_AdminDropDown_DD");
		SW.DropDown_SelectByIndex("ABCD_AdminDropDown_DD",NoteDD-2);//TODO Select using text
		SW.EnterValue("ABCD_AdminUserName_EB", "user_management");      
		SW.Click("ABCD_AdminTest_BT");
		String sEncryptedUsername = SW.GetText("ABCD_AdminOutput_EB");        
		SW.DropDown_SelectByIndex("ABCD_AdminDropDown_DD", NoteDD-5);//TODO Select using text 'Add note service' 
		SW.EnterValue("ABCD_AdminUserName_EB", sEncryptedUsername);
		SW.EnterValue("ABCD_AdminDealId_EB", 19);
		SW.EnterValue("ABCD_AdminSubId_EB", 199);
		SW.EnterValue("ABCD_AdminNotes_EB",  "ABCD_"+SW.RandomString(1000));
		SW.Click("ABCD_AdminTest_BT");

		String Actual = SW.GetText("ABCD_AdminOutput_EB");
		String Expected ="{"+"responseDetails"+":"+"Successfully added the note into ABCD"+","+"origRequestID"+":"+"null"+","+"responseCode"+":"+"SUCCESS"+"}";
		SW.CompareText("NoteSuccessMsg_DT", Expected, Actual);
	}

	@AfterClass
	public void StopTesT(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}
}
