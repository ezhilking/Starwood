package testscripts.Discovery;
/** Purpose		: Validate Create New Survey 
 * TestCase Name: 1. Validate Create New Survey
 				  2. 15-Select and identify template used
 				  3. 14-Change templates
 				  4. Validate_Save_Cancel_functionality_for_Answer
 				  5. Validate_Save_Cancel_functionality_for_Question type
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

public class REG01_CreateSurveyValidateTemplateUsedAndQuestions {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String TempIntro, TempComplete, TempExpire, TempHeader,SurveyID,QuestionName;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");

		TempIntro="Intro_"+SW.RandomString(10);
		TempComplete="Complete_"+SW.RandomString(10);
		TempExpire="Expire_"+SW.RandomString(10);
		TempHeader="Header_"+SW.RandomString(10);

	}
	@Test(priority=1)
	public void EnterGeneralDetails(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscManageSurvey_CreateSurvey_BT");
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyName_EB", "Survey "+SW.RandomString(5));
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyOwner_EB", "Owner "+SW.RandomString(5));
		SW.Wait(2);
		SW.CheckBox("DiscCreateSurvey_SurveyDivisionAll_CB", "ON");
		SW.DropDown_SelectByIndex("DiscCreateSurvey_SurveyLanguage_DD", 5);
		SW.Wait(2);
		SW.DropDown_SelectByIndex("DiscCreateSurvey_SurveyChanel_DD", 1);
		SW.Wait(2);
		SW.CheckBox("DiscCreateSurvey_SurveyBookingChanel_CB", "ON");
		SW.DiscoveryEnterValue("DiscCreateSurvey_SurveyDescription_EB", "Description_ "+SW.RandomString(5));
		SW.Click("DiscCreateSurvey_NextTemplating_BT");
		if(SW.ObjectExists("DiscSelectTemplate_SelectTemplate_LK")){
			Environment.loger.log(Level.INFO, "Template Page Navigation successfull");
			SW.GetScreenshot("CreateSurveyPass");
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
		// Changes has done in Application hence no need to run these steps
		/*//Enter introduction in template 
		SW.NormalClick("DiscSelectTemplate_SelectTemplate_LK");
		SW.NormalClick("DiscSelectTemplate_SelectIntro_EB");
		SW.SwitchToFrame("DiscSelectTemplate_TempHeaderFrame_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", TempIntro);
		SW.SwitchToFrame("");
		SW.Click("DiscSelectTemplate_SelectHeaderOk_BT");

		//Enter Complete msg in template 
		SW.NormalClick("DiscSelectTemplate_SelectComplete_EB");
		SW.SwitchToFrame("DiscSelectTemplate_TempHeaderFrame_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", TempComplete);
		SW.SwitchToFrame("");
		SW.Click("DiscSelectTemplate_SelectHeaderOk_BT");

		//Enter Expire msg in template 
		SW.NormalClick("DiscSelectTemplate_SelectExpired_EB");
		SW.SwitchToFrame("DiscSelectTemplate_TempHeaderFrame_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", TempExpire);
		SW.SwitchToFrame("");
		SW.Click("DiscSelectTemplate_SelectHeaderOk_BT");

		//Enter Header msg in template 
		SW.NormalClick("DiscSelectTemplate_Selectheading_EB");
		SW.SwitchToFrame("DiscSelectTemplate_TempHeaderFrame_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", TempHeader);
		SW.SwitchToFrame("");
		SW.Click("DiscSelectTemplate_SelectHeaderOk_BT");*/
		//SW.Click("DiscSelectTemplate_SelectTemplate_BT");
		SW.WaitForPageload();
		SW.Click("DiscSelectTemplate_Next-Questions_BT");
		if(SW.ObjectExists("DiscSelectTemplate_TemplateSelectedMsg_DT")){
			Environment.loger.log(Level.ERROR, "Template is not selected successfully");
			SW.FailCurrentTest("Template is not selected successfully");
		}else{
			Environment.loger.log(Level.INFO, "Template selected successfully");
			SW.GetScreenshot("CreateSurveyPass");
		}
	}
	@Test(priority=3,dependsOnMethods="selectTemplate")
	public void selectQuestions(){
		//Add question to the survey
		SW.Click("DiscAddQuestion_AddQuestion_BT");
		SW.WaitTillPresenceOfElementLocated("DiscAddQuestion_AddQuestionText_FR");
		//Validate save ans cancel functionality for questions
		SW.Click("DiscAddQuestion_SaveQuestion_BT");
		if(SW.ObjectExists("DiscAddQuestion_Continue_BT")){
			Environment.loger.log(Level.INFO, "confirmation pop-up is displayed for empty Question");
			SW.Click("DiscAddQuestion_Continue_BT");
			if(SW.ObjectExists("DiscAddQuestion_EmptyQuestionCheck_WT")){
				Environment.loger.log(Level.INFO, "Empty Question is added");
				SW.GetScreenshot("CreateSurveyPass");
			}
			else{
				Environment.loger.log(Level.INFO, "Empty Question is not added");
				SW.FailCurrentTest("Empty Question is not added");
			}
		}else{
			Environment.loger.log(Level.ERROR, "confirmation pop-up is not displayed for empty Questions");
			SW.FailCurrentTest("confirmation pop-up is not displayed for empty Questions");
		}
		//Validate the cancel functionality for the empty questions 
		SW.Click("DiscAddQuestion_AddQuestion_BT");
		SW.WaitTillPresenceOfElementLocated("DiscAddQuestion_AddQuestionText_FR");
		SW.Click("DiscAddQuestion_SaveQuestion_BT");
		SW.Click("DiscAddQuestion_CancelAnswer_BT");
		if(SW.ObjectExists("DiscAddQuestion_AddQuestionText_FR"))
			Environment.loger.log(Level.INFO, "Question is not added after clicking on the cacel button");
		SW.SwitchToFrame("DiscAddQuestion_AddQuestionText_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");
		QuestionName="Sample Question _"+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", QuestionName);
		SW.SwitchToFrame("");
		SW.Click("DiscAddQuestion_SaveQuestion_BT");
		if(SW.ObjectExists("//th[contains(.,'"+QuestionName+"')]")){
			Environment.loger.log(Level.INFO, "Question added to the survey successfully");
			SW.GetScreenshot("CreateSurveyPass");
		}
			
		else{
			Environment.loger.log(Level.ERROR, "Question not added to the survey successfully");
			SW.FailCurrentTest("Question not added to the survey successfully");
		}
	}
	@Test(priority=3,dependsOnMethods="selectQuestions")
	public void selectAnswers(){
		//Add answer to the added question
		SW.Click("//th[contains(.,'"+QuestionName+"')]");
		SW.Click("DiscAddQuestion_AddAnswer_LK");//click on add an ans button 
		//Validate for continue button of popup
		SW.Click("DiscAddQuestion_SaveAnswer_BT");
		if(SW.ObjectExists("DiscAddQuestion_Continue_BT")){
			Environment.loger.log(Level.INFO, "confirmation pop-up is displayed for empty answers");
			SW.Click("DiscAddQuestion_Continue_BT");
			if(SW.ObjectExists("DiscAddQuestion_EmptyAnsCheck_WT")){
				Environment.loger.log(Level.INFO, "Empty Ans is added to the question");
				SW.GetScreenshot("CreateSurveyPass");
			}
			else{
				Environment.loger.log(Level.INFO, "Empty Ans is not added to the question");
				SW.FailCurrentTest("Empty Ans is not added to the question");
			}
		}else{
			Environment.loger.log(Level.ERROR, "confirmation pop-up is not displayed for empty answers");
			SW.FailCurrentTest("confirmation pop-up is not displayed for empty answers");
		}
		//Validate for Cancel button of popup
		SW.Click("//th[contains(.,'"+QuestionName+"')]");
		SW.Click("DiscAddQuestion_AddAnswer_LK");
		SW.Click("DiscAddQuestion_SaveAnswer_BT");
		SW.Click("DiscAddQuestion_CancelAnswer_BT");
		if(SW.ObjectExists("DiscAddQuestion_AddAnswerText_FR"))
			Environment.loger.log(Level.INFO, "Clicking on cancel didn't add any ans to the question ");
		SW.GetScreenshot("CreateSurveyPass");
		SW.SwitchToFrame("DiscAddQuestion_AddAnswerText_FR");
		SW.NormalClick("DiscSelectTemplate_Entertext_EB");
		String QnOption="Option_"+SW.RandomString(5);
		SW.DiscoveryEnterValue("DiscSelectTemplate_Entertext_EB", QnOption);
		SW.SwitchToFrame("");
		SW.Click("DiscAddQuestion_SaveAnswer_BT");
		if(SW.ObjectExists("//td[contains(.,'"+QnOption+"')]")){
			Environment.loger.log(Level.INFO, "Ans added for the question successfully");
			SW.GetScreenshot("CreateSurveyPass");
		}else{
			Environment.loger.log(Level.INFO, "Ans not added for the question!");
			SW.FailCurrentTest("Ans not added for the question!");
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

	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
