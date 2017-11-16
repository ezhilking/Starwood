package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN28_CFN_RateMarketingInfo {

	static CRS SW = new CRS();
	String ReferenceLevel;
	String	RatePlanID, Channel, Chain, Division;

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

		// Searching Reference Level
		SW.WaitTillElementToBeClickable("VP_MenuSearch_EB");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Reference Level");
		SW.Click("VP_ReferenceLevel_LK");

		// Selecting the Rate Marketing Field Radio Button
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.MoveToObject("VPMainForm_RadioButton_FR");
		SW.SelectRadioButton("VPReferenceLevel_RatePlanName_RB");
		//		SW.RunJavaScript("VPRateMarketing_RateMarketingField_RB","document.getElementById('CForRMFForm:radioSearchOptions:1').checked=true;");

		ReferenceLevel = "Automation RMI";

		// Enter the Value to search
		SW.EnterValue("VPRateMarketing_EB",ReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Search_BT");
		SW.Click("VPReferenceLevel_Search_BT");

		// Creating new reference variable text
		ReferenceLevel = ReferenceLevel + SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);

		SW.WaitTillElementToBeClickable("VPReferenceLevel_Create_BT");
		SW.Click("VPReferenceLevel_Create_BT");
		SW.EnterValue("VPReferenceLevel_RatePlanText1_EB", ReferenceLevel+" text1");
		SW.EnterValue("VPReferenceLevel_RatePlanText2_EB", ReferenceLevel+" text2" );
		SW.EnterValue("VPReferenceLevel_RatePlanText3_EB", ReferenceLevel+" text3");
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Save_BT");
		SW.VPClick("VPReferenceLevel_Save_BT");


		//Validating the Creation of Reference Level
		SW.CompareTextContained("VPReferenceLevel_ValidationMessage_ST","Your changes have been saved. Modified text:", SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//Validating the DB for the text
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(SW.RecordExists("select * from rates.rp_txt where ref_text Like '%" + ReferenceLevel + "%'")){
			Reporter.Write("DB Validation", "Record should be created sucessfully in DB", "Record has been created sucessfully in DB", "PASS");
		}else{
			Reporter.Write("DB Validation", "Record should be created sucessfully in DB", "Record Wasn't created sucessfully in DB", "FAIL");
		}

		SW.CloseDBConnection();

	}

	@Test(priority=1, dependsOnMethods = { "createReferenceLevel" })
	public void createConsumerFacingRatePlanInfo()
	{
		RatePlanID="Automation_RMI" + SW.RandomString(2).toUpperCase()+SW.RandomInteger(2);
		Channel = "WEB - Branded Websites";
		Chain = "WESTIN HOTELS & RESORTS";
		Division = "ASIA/PACIFIC DIV";


		SW.SwitchToFrame("");
		SW.Click("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Create Consumer Facing, Rate Marketing Info");
		SW.Click("VPReferenceLevel_RatePlanInfoText_LK");
		SW.VPWaitForPageload();
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.MoveToObject("VPMainForm_RadioButton_FR");
		SW.SelectRadioButton("VPReferenceLevel_RatePlanName_RB");
		SW.EnterValue("VPRateMarketing_EB",ReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Search_BT");
		SW.Click("VPReferenceLevel_Search_BT");

		SW.SelectRadioButton("VPReferenceLevel_ConsumerFacingField_RB");
		SW.EnterValue("VPReferenceLevel_RatePlanID_EB", RatePlanID);
		SW.DropDown_SelectByText("VPReferenceLevel_Channel_DD", Channel);
		SW.DropDown_SelectByText("VPReferenceLevel_Chain_DD", Chain);
		SW.VPWaitForPageload();
		SW.DropDown_SelectByText("VPReferenceLevel_Division_DD", Division);
		if(SW.RadiobuttonIsSelected("VPReferenceLevel_ConsumerFacingField_RB"))
		{

		}else {
			SW.SelectRadioButton("VPReferenceLevel_ConsumerFacingField_RB");
		}
		SW.ClickAndProceed("VPReferenceLevel_SaveConfiguration_BT");
		if(SW.IsAlertPresent())
		{
			String str = SW.GetAlertText();
			if(str.contains("Please select a row to Associate"))
				SW.HandleAlert(true);
			SW.DropDown_SelectByText("VPReferenceLevel_Division_DD", Division);
			SW.SelectRadioButton("VPReferenceLevel_ConsumerFacingField_RB");
			
			SW.ClickAndProceed("VPReferenceLevel_SaveConfiguration_BT");
		}
		if(SW.IsAlertPresent())
		{
			SW.HandleAlert(true);
		}
		SW.CompareTextContained("VPReferenceLevel_ValidationMessage_ST","Reference level text has been associated to Chain:", SW.GetText("VPReferenceLevel_ValidationMessage_ST").trim());
	}


	@Test(priority=2, dependsOnMethods = { "createReferenceLevel", "createConsumerFacingRatePlanInfo" })
	public void deleteConsumerFacingRatePlanInfo()
	{

		SW.SelectRadioButton("VPReferenceLevel_RatePlanNameDelete_RB");
		SW.EnterValue("VPReferenceLevel_RatePlanIDDelete_EB", RatePlanID);
		SW.DropDown_SelectByText("VPReferenceLevel_ChannelDelete_DD", Channel);
		SW.CheckBox("VPReferenceLevel_ChainDelete_CB", "ON");
		SW.CheckBox("VPReferenceLevel_DivisionDelete_CB", "ON");
		SW.Click("VPReferenceLevel_SearchDeleteRatePlan_BT");
		if(SW.RadiobuttonIsSelected("VPReferenceLevel_RatePlan_RB"))
		{	
			System.out.println("Radio Button Selected");
		}else{
			SW.SelectRadioButton("VPReferenceLevel_RatePlan_RB");
		}
		SW.ClickAndProceed("VPReferenceLevel_RatePlanDelete_BT");
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
		}

		SW.CompareText("VPReferenceLevel_ValidationMessage_ST","Consumer Facing Rate Plan Name record was Deleted successfully!",SW.GetText("VPReferenceLevel_ValidationMessage_ST").trim());

		//Validating the DB for the text
//		SW.EstablishConnection(Environment.getRunEnvironment());
//		if(SW.RecordExists("select * from rates.rp_txt where ref_text Like '%" + ReferenceLevel + "%'")){
//			Reporter.Write("DB Validation", "Record should be deleted sucessfully in DB", "Record is not deleted sucessfully from DB", "FAIL");
//		}else{
//			Reporter.Write("DB Validation", "Record should be deleted sucessfully in DB", "Record has been deleted sucessfully from DB", "PASS");
//		}

//		SW.CloseDBConnection();
	}

	@Test(priority=3, dependsOnMethods = { "createReferenceLevel", "deleteConsumerFacingRatePlanInfo"})
	public void deleteReferenceLevel(){

		// Searching Reference Level
		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("VP_MenuSearch_EB");
		SW.Click("VP_MenuSearch_EB");
		SW.ClearValue("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Reference Level");SW.Click("VP_MenuSearch_EB");
		SW.Click("VP_ReferenceLevel_LK");
		SW.Click("VP_MenuSearch_EB");
		// Selecting the Rate Marketing Field Radio Button
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.MoveToObject("VPMainForm_RadioButton_FR");
		SW.RunJavaScript("VPRateMarketing_RateMarketingField_RB","document.getElementById('CForRMFForm:radioSearchOptions:1').checked=true;");
		SW.SelectRadioButton("VPRateMarketing_RateMarketingField_RB");
		SW.SelectRadioButton("VPReferenceLevel_RatePlanName_RB");

		// Enter the Value to search
		SW.EnterValue("VPRateMarketing_EB",ReferenceLevel);
		SW.WaitTillElementToBeClickable("VPReferenceLevel_Search_BT");
		SW.Click("VPReferenceLevel_Search_BT");

		SW.SelectRadioButton("VPReferenceLevel_Select_RB"); //CForRMFForm:j_id_jsp_2026667852_12:0:radioButtonForRow:0

		SW.Click("VPReferenceLevel_Delete_BT");
		if(SW.IsAlertPresent())
			SW.HandleAlert(true);

		//Validating the Creation of Reference Level
		SW.CompareTextContained("VPReferenceLevel_ValidationMessage_ST","The reference level text has been deleted.", SW.GetText("VPReferenceLevel_ValidationMessage_ST"));

		//Validating the DB for the text
		SW.EstablishConnection(Environment.getRunEnvironment());
		if(!SW.RecordExists("select * from rates.rp_txt where ref_text Like '%" + ReferenceLevel + "%'")){
			Reporter.Write("DB Validation", "Record should be created sucessfully in DB", "Record has been created sucessfully in DB", "PASS");
		}else{
			Reporter.Write("DB Validation", "Record should be created sucessfully in DB", "Record Wasn't created sucessfully in DB", "FAIL");
		}

		SW.CloseDBConnection();

	}

	@AfterClass
	public void EndTest(){
		CRS.CloseBrowser();
		Reporter.StopTest();
	}
}
