package testscripts.Discovery;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		: Validating the Templating Details
 * TestCase Name: 1.Validate_Templating Details_Active locked Survey
 * 				  2.Validate_Templating Details_Archived locked Survey
 * Created By	: Sharmila Begam
 * Modified By	: Sachin G 
 * Modified Date: 2/23/2017
 * Reviewed By	:	
 * Reviewed Date:
 */
public class REG10_Validate_TempDetails_ActiveLocked_ArchivedSurvey {
	CRM SW = new CRM();
	String UserName;
	String Password;
	String QuestionName,SurveyName;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");
		SurveyName="Survey "+SW.RandomString(5);

	}
	@Test(priority=1)
	public void EnterGeneralDetails(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscManageSurvey_CreateSurvey_BT");

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
	@Test(priority=4,dependsOnMethods="selectTemplate")
	public void LockedActive(){
		// Locking the Active Survey
		SW.Click("DiscStatusApproval_StatusApproval_LK");
		SW.DropDown_SelectByText("DiscStatusApproval_Status_DD", "Released");
		SW.Click("DiscStatusApproval_LockSurvey_LK");
		SW.WaitTillElementToBeClickable("DiscStatusApproval_LockConfirm_CB");
		SW.CheckBox("DiscStatusApproval_LockConfirm_CB", "ON");
		SW.Click("DiscStatusApproval_Lock_BT");
		if(SW.CompareTextContained("This survey has been locked and cannot be modified further.", SW.GetText("DiscStatusApproval_LockConfirm_DT"))){
			Environment.loger.log(Level.INFO, "Survey has locked successfully!!!");
			SW.Click("DiscTemplating_Templating_LK");
			if(SW.ObjectExists("DiscTemplating_TemplateDisable_LK")){
				Environment.loger.log(Level.INFO, "Templates are disabled!!! with Message" +SW.GetText("DiscStatusApproval_LockConfirm_DT"));
			}else{
				Environment.loger.log(Level.ERROR, "Templates are enabled");
				SW.FailCurrentTest("Validation failed in checking template disabled!!");
			}
		}else{
			Environment.loger.log(Level.ERROR, "The Survey is not Locked");
			SW.FailCurrentTest("Validation failed in checkings the survey is locked or not!!");
		}
	}

	@Test(priority=5, dependsOnMethods="LockedActive")
	public void ArchivedLocked(){
		//Checking the locked archived survey
		SW.Click("DiscNavigationPanel_ManageSurvey_LK");
		SW.Click("DiscHomePage_SurveyRefresh_LK");
		SW.ClickAndProceed("//*[@id='surveyListTable']/tbody/tr/td[2]/a[text()='"+SurveyName+"']//ancestor::tr//td[8]/a[text()='Archive']");
		SW.Wait(3);
		SW.HandleAlert(true);
		SW.Click("DiscNavigationPanel_Archived_LK");
		SW.Click("//*[@id='surveyListTable']/tbody/tr/td[2]/a[text()='"+SurveyName+"']");
		if(SW.ObjectExists("DiscSurveyOpen_Confirmation_LK"))
			SW.Click("DiscSurveyOpen_Confirmation_LK");
		if(SW.CompareTextContained("This survey has been locked and cannot be modified further.", SW.GetText("DiscStatusApproval_LockConfirm_DT"))){
			Environment.loger.log(Level.INFO, "Survey has locked successfully!!!");
			SW.Click("DiscTemplating_Templating_LK");
			if(SW.ObjectExists("DiscTemplating_TemplateDisable_LK")){
				Environment.loger.log(Level.INFO, "Templates are disabled!!! with Message" +SW.GetText("DiscStatusApproval_LockConfirm_DT"));
			}else{
				Environment.loger.log(Level.ERROR, "Templates are enabled");
				SW.FailCurrentTest("Validation failed in checking template disabled!!");
			}
		}else{
			Environment.loger.log(Level.ERROR, "The Survey is not Locked");
			SW.FailCurrentTest("Validation failed in checkings the survey is locked or not!!");
		}	
	}
	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
