package testscripts.Discovery;
/** Purpose		: Validate the adding of follow up question for basic likert scale
 * TestCase Name: Validate the adding of follow up question for basic likert scale
 * 					1. Validate the adding of follow up question for basic likert scale
 * 					2. Validate the adding of no follow up rules in follow up question of basic likert scale
 * 					3. Validate the display of follow up question for basic likert scale when viewed from  generated Submission link
 * 					4. Validate the display of follow up question for basic likert scale when viewed from Preview Survey From Database
 * 					5. Validate the display of follow up question for basic likert scale when viewed from Preview Survey From Session
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

public class REG03_ValidateAddingFollowUpQuestionForBasicLikertScale {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String TempIntro, TempComplete, TempExpire, TempHeader,SurveyID,QuestionName,FollowUpQn;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");

	}
	@Test(priority=1)
	public void EnterGeneralDetails(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscManageSurvey_CreateSurvey_BT");
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyName_EB", "Survey "+SW.RandomString(5));
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyOwner_EB", "Owner "+SW.RandomString(5));
		SW.DropDown_SelectByIndex("DiscCreateSurvey_SurveyLanguage_DD", 5);
		SW.CheckBox("DiscCreateSurvey_SurveyDivisionAll_CB", "ON");
		SW.DropDown_SelectByIndex("DiscCreateSurvey_SurveyChanel_DD", 1);
		SW.CheckBox("DiscCreateSurvey_SurveyBookingChanel_CB", "ON");
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyDescription_EB", "Description_ "+SW.RandomString(5));
		SW.Click("DiscCreateSurvey_NextTemplating_BT");
		if(SW.ObjectExists("DiscSelectTemplate_SelectTemplate_LK")){
			Environment.loger.log(Level.INFO, "Template Page Navigation successfull");
		}else{
			Environment.loger.log(Level.ERROR, "Template Page Navigation failed");
			SW.FailCurrentTest("Template Page Navigation failed");
		}
	}
	@Test(priority=2,dependsOnMethods="EnterGeneralDetails")
	public void selectTemplate(){
		//Validate the template selection label before template selection
		String TempSelection=SW.GetText("DiscSaveSuvey_TemplateSelectionLabel_DT");
		SW.CompareText("CompareTemplatelabel ","No template has been selected.", TempSelection);
		SW.Click("DiscSaveSuvey_FirstTemplateSelection_LK");//Click on first template
		SW.Click("DiscSelectTemplate_SelectTemplate_BT");
		//Validate after template selection
		String SelectedTempName=SW.GetText("DiscSaveSuvey_FirstTemplateSelection_LK");
		TempSelection=SW.GetText("DiscSaveSuvey_TemplateSelectionLabel_DT");
		SW.CompareTextContained("CompareTemplateName", SelectedTempName, TempSelection);
		SW.Click("DiscSaveSuvey_SaveSurvey_BT");
		SW.Click("DiscSelectTemplate_Next-Questions_BT");
		if(SW.ObjectExists("DiscSelectTemplate_TemplateSelectedMsg_DT")){
			Environment.loger.log(Level.ERROR, "Template is not selected successfully");
			SW.FailCurrentTest("Template is not selected successfully");
		}else{
			Environment.loger.log(Level.INFO, "Template selected successfully");
		}
	}
	@Test(priority=3,dependsOnMethods="selectTemplate")
	public void selectQuestions(){
		//Add question to the survey
		SW.Click("DiscAddQuestion_AddQuestion_BT");
		SW.WaitTillPresenceOfElementLocated("DiscAddQuestion_AddQuestionText_FR");
		SW.SwitchToFrame("DiscAddQuestion_AddQuestionText_FR");
		SW.Click("DiscSelectTemplate_Entertext_EB");
		QuestionName="Sample Question _"+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", QuestionName);
		SW.SwitchToFrame("");

		SW.DropDown_SelectByValue("DiscAddQuestion_SelectQuestionType_DD", "10");
		SW.Click("DiscAddQuestion_SaveQuestion_BT");
		if(SW.ObjectExists("//th[contains(.,'"+QuestionName+"')]")){
			Environment.loger.log(Level.INFO, "Question added to the survey successfully");
			SW.Click("DiscSaveSuvey_SaveSurvey_BT");
		}
		else{
			Environment.loger.log(Level.ERROR, "Question not added to the survey successfully");
			SW.FailCurrentTest("Question not added to the survey successfully");
		}
	}
	@Test(priority=4,dependsOnMethods="selectQuestions")
	public void AddFollwupQuestion(){
		SW.Click("DiscAddQuestion_BLPlaceholder_LK");
		SW.Click("DiscAddQuestion_BLAddFollowupQn_LK");
		SW.SwitchToFrame("DiscAddFollowupQuestion_BLAddFollowupQn_FR");
		SW.Click("DiscSelectTemplate_Entertext_EB");//Click on the edit box
		FollowUpQn="Followup question_"+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", FollowUpQn);
		SW.SwitchToFrame("");
		SW.ClickAndProceed("DiscAddQuestion_SaveAnswer_BT");
		if(SW.IsAlertPresent()){
			Environment.loger.log(Level.INFO, "Alert message is displayed when tried to save followup without rule");
			SW.HandleAlert(true);
			SW.GetScreenshot("AlertForEmptyRule");
		}
		SW.DropDown_SelectByValue("DiscAddFollowupQuestion_FollowupRules_DD", "2");
		SW.Click("DiscAddFollowupQuestion_FollowupRulesAdd_LK");
		Environment.loger.log(Level.INFO, "First Followup rule is added");
		SW.DropDown_SelectByValue("DiscAddFollowupQuestion_FollowupRules_DD", "3");
		SW.Click("DiscAddFollowupQuestion_FollowupRulesAdd_LK");
		Environment.loger.log(Level.INFO, "Second Followup rule is added");
		SW.Click("DiscAddQuestion_SaveAnswer_BT");
		if(SW.ObjectExists("//th[contains(.,'"+FollowUpQn+"')]")){
			Environment.loger.log(Level.INFO, "Follw Up question with rules are added successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Follw Up question is not added");
			SW.FailCurrentTest("Follw Up question is not added");
		}

		SW.Click("DiscSaveSuvey_SaveSurvey_BT");//Save survey 
		SW.WaitTillElementToBeClickable("DiscSaveSuvey_SurveySuccessMessage_DT");
		if(SW.ObjectExists("DiscSaveSuvey_SurveySuccessMessage_DT")){
			SurveyID=SW.GetText("DiscSaveSuvey_SurveySuccessMessage_DT");
			SurveyID=SurveyID.substring(SurveyID.indexOf("#")+1, SurveyID.length());
			Environment.loger.log(Level.INFO, "Survey Created successfully -- "+SurveyID);
			SW.GetScreenshot("CreateSurveyPass");
		}else{
			Environment.loger.log(Level.ERROR, "Survey Creation failed");
			SW.FailCurrentTest("Survey Creation failed");
		}

	}
	@Test(priority=5,dependsOnMethods="AddFollwupQuestion")
	public void ValidatePreviewSurveyFromSession(){
		SW.Click("DiscAddQuestion_PrvSurveyFromSession_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		if(SW.ObjectExists("//p[text()='"+QuestionName+"']")){
			Environment.loger.log(Level.INFO, "Added Basic Likert qn is present in the survey preview ");
			SW.GetScreenshot("BasicLiekrtScaleQn");
		}else{
			Environment.loger.log(Level.ERROR, "Added question is not present in the survey preview");
			SW.FailCurrentTest("Added question is not present in the survey preview");
		}
		SW.SelectRadioButton("DiscSurveyPreview_AgreeOption_RB");
		SW.Wait(2);
		if(SW.ObjectExists("//p[text()='"+FollowUpQn+"']")){
			Environment.loger.log(Level.INFO, "Followup Qn is apeared after clicking on the Agree button");
			SW.GetScreenshot("BasicLiekrtScaleQn");
		}else{
			Environment.loger.log(Level.ERROR, "Follow up Qn is not displayed ");
			SW.FailCurrentTest("Follow up Qn is not displayed ");
		}
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
	}
	@Test(priority=6,dependsOnMethods="AddFollwupQuestion")
	public void ValidatePreviewSurveyFromDB(){
		SW.Click("DiscAddQuestion_PrvSurveyFromDB_LK");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		if(SW.ObjectExists("//p[text()='"+QuestionName+"']")){
			Environment.loger.log(Level.INFO, "Added Basic Likert qn is present in the survey preview ");
			SW.GetScreenshot("BasicLiekrtScaleQn");
		}else{
			Environment.loger.log(Level.ERROR, "Added question is not present in the survey preview");
			SW.FailCurrentTest("Added question is not present in the survey preview");
		}
		SW.SelectRadioButton("DiscSurveyPreview_AgreeOption_RB");
		SW.Wait(2);
		if(SW.ObjectExists("//p[text()='"+FollowUpQn+"']")){
			Environment.loger.log(Level.INFO, "Followup Qn is apeared after clicking on the Agree button");
			SW.GetScreenshot("BasicLiekrtScaleQn");
		}else{
			Environment.loger.log(Level.ERROR, "Follow up Qn is not displayed ");
			SW.FailCurrentTest("Follow up Qn is not displayed ");
		}
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);

	}
	@Test(priority=7,dependsOnMethods="AddFollwupQuestion")
	public void ValidateSurveyFromGeneratedLink(){
		SW.Click("DiscNavigationPanel_ADMIN_LK");
		SW.DropDown_SelectByValue("DiscAdminPage_Type_DD", "sur");
		SW.DiscoveryEnterValue("DiscAdminPage_SurveyID_EB", SurveyID);
		SW.DropDown_SelectByValue("DiscAdminPage_Locale_DD", "en-US");
		SW.Click("DiscAdminPage_GenerateSubmission_BT");
		if(SW.ObjectExists("DiscAdminPage_GeneratedLink_BT"))
			SW.Click("DiscAdminPage_GeneratedLink_BT");
		else{
			Environment.loger.log(Level.ERROR, "Submission link generation failed ");
			SW.FailCurrentTest("Submission link generation failed ");
		}
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		if(SW.ObjectExists("//p[text()='"+QuestionName+"']")){
			Environment.loger.log(Level.INFO, "Added Basic Likert qn is present in the survey preview ");
			SW.GetScreenshot("BasicLiekrtScaleQn");
		}else{
			Environment.loger.log(Level.ERROR, "Added question is not present in the survey preview");
			SW.FailCurrentTest("Added question is not present in the survey preview");
		}
		SW.SelectRadioButton("DiscSurveyPreview_AgreeOption_RB");
		SW.Wait(2);
		if(SW.ObjectExists("//p[text()='"+FollowUpQn+"']")){
			Environment.loger.log(Level.INFO, "Followup Qn is apeared after clicking on the Agree button");
			SW.GetScreenshot("BasicLiekrtScaleQn");
		}else{
			Environment.loger.log(Level.ERROR, "Follow up Qn is not displayed ");
			SW.FailCurrentTest("Follow up Qn is not displayed ");
		}
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);

	}

	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
