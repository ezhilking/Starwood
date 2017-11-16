/* Purpose		:  Valhalla Portal MustRun Suite Automation
 * TestCase Name:  Reg_Create Rate Plan Marketing and verify
 * Created By	:  Poorva Sharma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: Yethendra Varma
 * Reviewed Date: 04/11/17
 */

package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN18_CFN_ReferenceLevel {

	static CRS SW = new CRS();
	String ReferenceLevel;
	String modifyReferenceLevel;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}

	//****************************************** Create Reference Level **************************************************

	@Test(priority=0)
	public void createReferenceLevel(){

		// Login to VP
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.VPClick("VP_Submit_BT");

		//Searching Reference Level
		SW.WaitTillElementToBeClickable("VP_MenuSearch_EB");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Reference Level");
		SW.Click("VP_ReferenceLevel_LK");

		//Selecting the Rate Marketing Field Radio Button
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.MoveToObject("VPMainForm_RadioButton_FR");
		SW.SelectRadioButton("VPRateMarketing_RateMarketingField_RB");
		//		SW.RunJavaScript("VPRateMarketing_RateMarketingField_RB","document.getElementById('CForRMFForm:radioSearchOptions:1').checked=true;");

		ReferenceLevel = "Automation ReferenceLevel Text";

		// Enter the Value to search
		SW.EnterValue("VPRateMarketing_EB",ReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Search_BT");
		SW.Click("VPReferenceLevel_Search_BT");

		// Creating new reference variable text
		ReferenceLevel = ReferenceLevel + SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);

		SW.WaitTillElementToBeClickable("VPReferenceLevel_Create_BT");
		SW.Click("VPReferenceLevel_Create_BT");
		SW.EnterValue("VPReferenceLevel_Name_EB", ReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Save_BT");
		SW.VPClick("VPReferenceLevel_Save_BT");


		//Validating the Creation of Reference Level
		SW.CompareText("VPReferenceLevel_ValidationMessage_ST","Your changes have been saved. Modified text: "+ReferenceLevel, SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//Validating the DB for the text
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select * from rates.rp_txt where ref_text = '" + ReferenceLevel + "'")){
			Reporter.Write("DB Validation", "Record should be created sucessfully in DB", "Record has been created sucessfully in DB", "PASS");
		}else{
			Reporter.Write("DB Validation", "Record should be created sucessfully in DB", "Record Wasn't created sucessfully in DB", "FAIL");
		}

		SW.CloseDBConnection();

	}

	//****************************************** Modify Reference Level **************************************************

	@Test(priority=1, dependsOnMethods = { "createReferenceLevel" })
	public void updateReferenceLevel()
	{

		// Enter the Value to search
		SW.EnterValue("VPRateMarketing_EB",ReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Search_BT");
		SW.Click("VPReferenceLevel_Search_BT");

		SW.SelectRadioButton("VPReferenceLevel_Select_RB");
		SW.Click("VPReferenceLevel_Modify_BT");

		modifyReferenceLevel = ReferenceLevel+" Check Modify";
		SW.EnterValue("VPReferenceLevel_Name_EB", modifyReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Save_BT");
		SW.ClickAndProceed("VPReferenceLevel_Save_BT");
		SW.HandleAlert(true);

		//Validating the Creation of Reference Level
		SW.CompareText("VPReferenceLevel_ValidationMessage_ST","Your changes have been saved. Modified text: "+modifyReferenceLevel, SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//Validating the DB for the text
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select * from rates.rp_txt where ref_text = '" + modifyReferenceLevel + "'"))	{
			Reporter.Write("Modify Reference Level Test", "Your changes have been saved. Modified text: "+modifyReferenceLevel, "Your changes have been saved. Modified text: "+modifyReferenceLevel, "PASS");	
		}
		else{
			Reporter.Write("Modify Reference Level Test", "Your changes have been saved. Modified text: "+modifyReferenceLevel, "Your changes have been saved. Modified text: "+modifyReferenceLevel, "FAIL");	
		}

		SW.CloseDBConnection();
	}

	//****************************************** Delete Reference Level **************************************************

	@Test(priority=2, dependsOnMethods = { "createReferenceLevel" })
	public void deleteReferenceLevel()
	{	
		// Enter the Value to search
		SW.EnterValue("VPRateMarketing_EB",modifyReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Search_BT");
		SW.Click("VPReferenceLevel_Search_BT");

		SW.SelectRadioButton("VPReferenceLevel_Select_RB");  
		SW.Click("VPReferenceLevel_Delete_BT");

		//Validating the Creation of Reference Level
		SW.CompareText("VPReferenceLevel_ValidationMessage_ST","The reference level text \""+ modifyReferenceLevel +"\" has been deleted", SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//Validating the DB for the text
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select * from rates.rp_txt where ref_text = '" + modifyReferenceLevel + "'"))	{
			Reporter.Write("Modify Reference Level Test", "Your changes have been saved. Deleted: "+modifyReferenceLevel, "Your changes have been saved. Deleted: "+ReferenceLevel, "FAIL");	
		}
		else{
			Reporter.Write("Modify Reference Level Test", "Your changes have been saved. Deleted: "+modifyReferenceLevel, "Your changes have been saved. Deleted: "+ReferenceLevel, "PASS");	
		}
	}

	@AfterClass
	public void EndTest(){
		CRS.CloseBrowser();
		Reporter.StopTest();		
	}
}
