package testscripts.Discovery;
/** Purpose		: Validate Submission of survey
 * TestCase Name: 1. Validate_survey opening page_guest_hasn t_answered_mandatory question_submitted_survey
 * 				  2. Validate_survey response_survey submission table_survey submission_ans table_only mandatory questions_answered
 * 				  3. Validate_survey_opening_guest_completed_submitted_survey previously
 * 				  4. Verify the Survey response for the survey with mandatory questions
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

public class REG12_ValidateSurveyResponseMandatoryQuestions {
	CRM SW = new CRM();
	String UserName;
	String Password,SurveyID,QuestionName1, QuestionName2,Q2Ans;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");// Script will not work in IE Driver because of IE Driver issue
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");
	}
	
	@Test(priority=1)
	public void EnterGeneralDetails(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscManageSurvey_CreateSurvey_BT");
		String SurveyName="Survey "+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyName_EB", SurveyName);
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
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");//click inside question frame
		QuestionName1="Sample Question_"+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", QuestionName1);
		SW.SwitchToFrame("");
		SW.CheckBox("DiscAddQuestion_IsRequired_CB", "ON");
		SW.DropDown_SelectByValue("DiscAddQuestion_SelectQuestionType_DD", "10");
		SW.Click("DiscAddQuestion_SaveQuestion_BT");
		//Add second question
		SW.Click("DiscAddQuestion_AddQuestion_BT");
		SW.WaitTillPresenceOfElementLocated("DiscAddQuestion_AddQuestionText_FR");
		SW.SwitchToFrame("DiscAddQuestion_AddQuestionText_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");//click inside question frame
		QuestionName2="Sample Question_"+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", QuestionName2);
		SW.SwitchToFrame("");

		SW.DropDown_SelectByValue("DiscAddQuestion_SelectQuestionType_DD", "7");
		SW.SelectRadioButton("DiscAddQuestion_AllCharacters_RB");
		SW.Click("DiscAddQuestion_SaveQuestion_BT");

		if(SW.ObjectExists("//th[contains(.,'"+QuestionName1+"')]")&& SW.ObjectExists("//th[contains(.,'"+QuestionName2+"')]")){
			Environment.loger.log(Level.INFO, "Question added to the survey successfully");
			SW.Click("DiscSaveSuvey_SaveSurvey_BT");
			SurveyID=SW.GetText("DiscSaveSuvey_SurveySuccessMessage_DT");
			SurveyID=SurveyID.substring(SurveyID.indexOf("#")+1, SurveyID.length());
			Environment.loger.log(Level.INFO, "Survey Created successfully -- "+SurveyID);
		}
		else{
			Environment.loger.log(Level.ERROR, "Question not added to the survey successfully");
			SW.FailCurrentTest("Question not added to the survey successfully");
		}
	}
	
	@Test(priority=4,dependsOnMethods="selectQuestions")
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
		if(SW.ObjectExists("//p[text()='"+QuestionName1+"']")&&SW.ObjectExists("//p[text()='"+QuestionName2+"']")){
			Environment.loger.log(Level.INFO, "Added Basic Likert qn is present in the survey preview ");
			SW.GetScreenshot("BasicLiekrtScaleQn");

		}else{
			Environment.loger.log(Level.ERROR, "Added question is not present in the survey preview");
			SW.FailCurrentTest("Added question is not present in the survey preview");
		}
	}
	
	@Test(priority=5,dependsOnMethods="ValidateSurveyFromGeneratedLink")
	public void ValidateErrorMessage(){
		Q2Ans="Question2Ans_"+SW.RandomString(5);
		SW.DiscoveryEnterValue("(//div[contains(.,'"+QuestionName2+"')])[last()]//input", Q2Ans);
		SW.Click("DiscSurvey_Submit_BT"); 
		//Check for error message
		if(SW.ObjectExists("DiscSubmissionReport_ErrorMessage_DT")){
			Environment.loger.log(Level.INFO, "Error message displayed for un-answered mandatory questions");
			SW.GetScreenshot("SurveySubmissionError");
		}else{
			Environment.loger.log(Level.ERROR, "Error message is not displayed for unanswered qestions");
			SW.FailCurrentTest("Error message is not displayed for unanswered qestions");
		}
	}
	@Test(priority=6,dependsOnMethods="ValidateErrorMessage")
	public void ValidateSubmittedSurveyPreviously(){
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		SW.Click("DiscAdminPage_GeneratedLink_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		String AnsweredQn= SW.GetText("(//div[contains(.,'"+QuestionName2+"')])[last()]//input");
		if(AnsweredQn.isEmpty()){
			Environment.loger.log(Level.INFO, "Previously answered text is not available in the survey");
			SW.GetScreenshot("SurveySubmissionError");
		}else{
			Environment.loger.log(Level.ERROR, "Some Text is present the priviously answered question");
			SW.FailCurrentTest("Some Text is present the priviously answered question");
		}
	}
	
	@Test(priority=7,dependsOnMethods="ValidateSubmittedSurveyPreviously")
	public void ValidateSurveySubmisson(){	

		SW.SelectRadioButton("DiscSurveyPreview_AgreeOption_RB");
		SW.Wait(2);
		SW.Click("DiscSurvey_Submit_BT");
		if(!SW.ObjectExists("DiscSurvey_Submit_BT")){
			Environment.loger.log(Level.INFO, "Survey Submitted successfully after answering mandatory questions");
			SW.GetScreenshot("SurveySubmission");
		}else{
			Environment.loger.log(Level.ERROR, "Survey Submission Failed");
			SW.FailCurrentTest("Failed to submit survey");
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
