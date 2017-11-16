package testscripts.gcRegression;
/** Purpose		: To Validate Email part for All GC Regression scripts 
 * TestCase Name: General
 * Created By	: Sachin & Sharanya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_EmailValidationWebMail {
	CRM SW = new CRM();	
	String TestCaseName;


	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser("https://outlook.office365.com/owa/");
	}

	@Test(priority=1)
	public void EmailLogin(){
		//Login to the email
		if(!SW.LoginToEmail(SW.TestData("EmailID"), SW.TestData("EmailUserName"), SW.TestData("EmailPassword"))){
			SW.FailCurrentTest("Login Failed!!! ");
		}
	}
	@Test(priority=2, dependsOnMethods="EmailLogin")
	public void EmailValidationScript26(){
		TestCaseName="GC_REG26_ValidateEditingActiveGCSetupAddingSocialMediaIconsManagerTitleInReservationEmail";
		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate the offer image 
		if(SW.ObjectExists("//img[contains(@src,'"+	SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"')]")){
			Environment.loger.log(Level.INFO, "Selected Offer Image is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Offer image not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Offer image not present in the email");
		}

		//Validate the social Media Icon 
		if(SW.ObjectExists("//img[contains(@src,'Twitter_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Social Media Icon is not present in the Email");
		}

		//Validate Manager Title 
		String UIManagerTitle=SW.GetText("(//tr[td//text()[contains(.,'Greetings')]])[last()]");
		if(SW.CompareTextContained(SW.GetEmailTestData(TestCaseName,"ValiadtionString2"), UIManagerTitle)){
			Environment.loger.log(Level.INFO, "Modified Manager title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Modified Manager title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Modified Manager title is not present in the email");
		}
		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=3, dependsOnMethods="EmailLogin")
	public void EmailValidationScript32(){


		TestCaseName="GC_REG32_ValidateDesignHotelsReservationEmailEnglish2SocialMediaIcons";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate the social Media Icons 
		//Validate Twitter
		if(SW.ObjectExists("//img[contains(@src,'Twitter_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Twitter Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Twitter Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Twitter Social Media Icon is not present in the Email");
		}
		//Validate Facebook
		if(SW.ObjectExists("//img[contains(@src,'FB_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}


		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=4, dependsOnMethods="EmailLogin")
	public void EmailValidationScript33(){


		TestCaseName="GC_REG33_ValidateLMYour24ReservationEmailChineseLanguageWithSPGOffer";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate Y24 Link 

		if(SW.ObjectExists("//tr[2]//a[text()='å�•å‡»æ­¤å¤„']")){
			Environment.loger.log(Level.INFO, "Your 24 link is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Your 24 link is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Your 24 link is not present in the email");
		}

		//Validate SPG offer 
		String SPGOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+SPGOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "SPG Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=5, dependsOnMethods="EmailLogin")
	public void EmailValidationScript36(){


		TestCaseName="GC_REG36_ValidateSVOdisclaimerInSheratonReservationEmailArabicLanguage";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate SVO Disclaimer text in mail
		String ExpectedDisclaimer=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		String SampleText=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		if(!SW.ObjectExists("(//tr[td//text()[contains(.,'"+SampleText+"')]])[last()]")){
			Environment.loger.log(Level.ERROR, "Failed to get the UI Text from the mail");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Failed to get the UI Text from the mail");
		}
		String UIDisclaimerText=SW.GetText("(//tr[td//text()[contains(.,'"+SampleText+"')]])[last()]");

		if(SW.CompareTextContained(ExpectedDisclaimer, UIDisclaimerText)){
			Environment.loger.log(Level.INFO, "SVO Disclaimer text is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SVO Disclaimer text is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SVO Disclaimer text is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=6, dependsOnMethods="EmailLogin")
	public void EmailValidationScript37(){


		TestCaseName="GC_REG37_ValidateAtlasAndOmnitureTagsElementResEmailTradChinese";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate atlas tag in mail
		if(SW.ObjectExists("//img[contains(@id,'"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"')]")){
			Environment.loger.log(Level.INFO, "atlas Tag is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "atlas tag is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("atlas tag is not present in the email");
		}
		//validate omniture tag in mail
		if(SW.ObjectExists("//img[contains(@id,'"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"')]")){
			Environment.loger.log(Level.INFO, "atlas Tag is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "atlas tag is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("atlas tag is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=7, dependsOnMethods="EmailLogin")
	public void EmailValidationScript38(){


		TestCaseName="GC_REG38_ValidateSWAQCodesinResconfEmailwithSPGoffer";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(15);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		String sReservationNumber=SW.GetEmailTestData(TestCaseName,"ReservationNumber");
		System.out.println(sReservationNumber);
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate SPG offer present in the mail 
		String PropertyOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+PropertyOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Property Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Property Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}
		//Validate SWAQ  in mail
		String code=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		if(SW.ObjectExists("//a[contains(@href,'"+code+"')]")){
			Environment.loger.log(Level.INFO, "SWAQ Code is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SWAQ Code is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SWAQ code not present in the email");
		}
		//SWAC Codes

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=8, dependsOnMethods="EmailLogin")
	public void EmailValidationScript31(){

		TestCaseName="GC_REG31_ValidateStRegisPromoReservation";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(15);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate SPG offer 
		/*//if(SW.ObjectExists("//a[contains(@href,'"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"')]")){
		String SPGOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");

		if(SW.ObjectExists("//table[@class='x_rate1']//*[text()='"+SPGOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "SPG Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}*/

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}


	}
	@Test(priority=9, dependsOnMethods="EmailLogin")
	public void EmailValidationScript39(){

		TestCaseName="GC_REG39_ValidateAloftTCPMultiGuestRollawayResEmailMAGCDisclaimerEngLangPropBrandSurvey4SocialIcons";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}
		//Validate property offer present in the mail 
		String PropertyOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+PropertyOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Property Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Property Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		//Validate the social Media Icons 
		//Validate Twitter
		if(SW.ObjectExists("//img[contains(@src,'Twitter_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Twitter Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Twitter Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Twitter Social Media Icon is not present in the Email");
		}
		//Validate Facebook
		if(SW.ObjectExists("//img[contains(@src,'FB_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		//Validate flicker
		if(SW.ObjectExists("//img[contains(@src,'Flickr_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Flicker Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Flicker Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Flicker Social Media Icon is not present in the Email");
		}
		//Validate Instagram
		if(SW.ObjectExists("//img[contains(@src,'Instagram_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Instagram Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Instagram Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Instagram Social Media Icon is not present in the Email");
		}


		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=10, dependsOnMethods="EmailLogin")
	public void EmailValidationScript43(){

		TestCaseName="GC_REG43_ValidateSNAWestinCancellationEmailPortugueseSPGOffer4SocialMedia";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}
		//Validate the cancellation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "CancellationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Cancellation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Cancellation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate property offer present in the mail 
		String SPGOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+SPGOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "SPG Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}
		//Validate suite night award description
		String SNADescription=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'Devido')]])[last()]")){
			String UISNADescription=SW.GetText("(//tr[td//text()[contains(.,'Devido')]])[last()]");
			SW.CompareTextContained("ValidateSNADescription", UISNADescription, SNADescription);
			if(SW.ObjectExists("(//tr[td//text()[contains(.,'Devido')]]//a)[last()-5]")){
				Environment.loger.log(Level.INFO, " Click link is present in the SNA award description");
				SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
			}
			Environment.loger.log(Level.INFO, "SNA award description present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SNA award description not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		if(SW.ObjectExists("//img[contains(@src,'Twitter_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Twitter Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Twitter Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Twitter Social Media Icon is not present in the Email");
		}
		//Validate Facebook
		if(SW.ObjectExists("//img[contains(@src,'FB_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		//Validate flicker
		if(SW.ObjectExists("//img[contains(@src,'Flickr_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Flicker Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Flicker Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Flicker Social Media Icon is not present in the Email");
		}
		//Validate Instagram
		if(SW.ObjectExists("//img[contains(@src,'Instagram_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Instagram Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Instagram Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Instagram Social Media Icon is not present in the Email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=11, dependsOnMethods="EmailLogin")
	public void EmailValidationScript44(){

		TestCaseName="GC_REG44_ValidatePromoFPCancellationEmailTurkishLanguage";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}
		//Validate the cancellation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "CancellationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Cancellation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Cancellation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate Promo Award
		String PromoName=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+PromoName+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "Promo award is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Promo award is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=12, dependsOnMethods="EmailLogin")
	public void EmailValidationScript40(){

		TestCaseName="GC_REG40_ValidateWhotelsResCanEmailwithLOS4NightsEnglish";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}
		//Validate the cancellation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "CancellationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Cancellation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Cancellation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate Property Offer
		String PromoName=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+PromoName+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "Promo award is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Promo award is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}
		//Validate Indian Disclaimer 
		String indianDisclaimer=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+indianDisclaimer+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "Indian Disclaimer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Indian Disclaimer is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=13, dependsOnMethods="EmailLogin")
	public void EmailValidationScript46(){

		TestCaseName="GC_REG46_ValidateTCPMultiGuestRollawaySheratonCancellationEmail";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}
		//Validate the cancellation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "CancellationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Cancellation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Cancellation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate Roll away 
		//TODO 

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=14, dependsOnMethods="EmailLogin")
	public void EmailValidationScript47(){//pre stay

		TestCaseName="GC_REG47_ValidateWestinPreStayEmailBahasaLang";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ReservationNumber")+" "+SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(15);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}


	}
	@Test(priority=15, dependsOnMethods="EmailLogin")
	public void EmailValidationScript45(){//pre stay

		TestCaseName="GC_REG45_ValidateP100CheckInTimeWestinPreStayEnglishWithSPGPreferencesOffer";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ReservationNumber")+" "+SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(15);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate SPG pref Offer 
		String SPGPrefOfferName=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+SPGPrefOfferName+"']")){
			Environment.loger.log(Level.INFO, "SPG Pref offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Pref offer is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Pref Offer Title is not present in the email");
		}
		//Validate Check in and Check out times 
		String CheckIn=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		String CheckOut=SW.GetEmailTestData(TestCaseName, "ValiadtionString3");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+CheckIn+"')]])[last()]")&& SW.ObjectExists("(//tr[td//text()[contains(.,'"+CheckOut+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "Check in and Check out time is  present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Check in and Check out time is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Check in and Check out time is not present in the email");
		}
		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=16, dependsOnMethods="EmailLogin")
	public void EmailValidationScript49(){//Pre stay


		TestCaseName="GC_REG49_ValidateSVODisclaimerElementPreStayFrenchLanguage";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ReservationNumber")+" "+SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}
		//Validate SVO Disclaimer text in mail
		String ExpectedDisclaimer=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		//(//tr[td//text()[contains(., 'En raison de la nature spÃ©ciale du tarif rÃ©servÃ©')]])[last()]
		if(!SW.ObjectExists("(//tr[td//text()[contains(., 'En raison de la nature')]])[last()]")){
			Environment.loger.log(Level.ERROR, "Failed to get the UI Text from the mail");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Failed to get the UI Text from the mail");
		}
		String UIDisclaimerText=SW.GetText("(//tr[td//text()[contains(., 'En raison de la nature')]])[last()]");

		if(SW.CompareTextContained(ExpectedDisclaimer, UIDisclaimerText)){
			Environment.loger.log(Level.INFO, "SVO Disclaimer text is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SVO Disclaimer text is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SVO Disclaimer text is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=17, dependsOnMethods="EmailLogin")
	public void EmailValidationScript35(){

		TestCaseName="GC_REG35_ValidateSuitNightAwardReservationWithEgnOfr";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}

		//Validate EGN offer 
		String EGNOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+EGNOfferTitle+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "EGN Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "EGN Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=18, dependsOnMethods="EmailLogin")
	public void EmailValidationScript50(){//Pre stay

		TestCaseName="GC_REG50_ValidateSTARHOTTributePreStayKoreanWithEGN";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ReservationNumber")+" "+SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}

		//Validate EGN offer 
		//Validate EGN offer 
		String EGNOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+EGNOfferTitle+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "EGN Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "EGN Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=19, dependsOnMethods="EmailLogin")
	public void EmailValidationScript34(){

		TestCaseName="GC_REG34_ValidateLMStarhotReservationWithBrandNCrmOffer";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}

		//Validate Brand offer 
		String BrandOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+BrandOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Brand Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Brand Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Brand Offer Title is not present in the email");
		}
		//Validate CRM offer
		String CRMOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		if(SW.ObjectExists("//*[text()='"+CRMOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "CRM Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "CRM Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("CRM Offer Title is not present in the email");
		}
		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=20, dependsOnMethods="EmailLogin")
	public void EmailValidationScript54(){//Post stay

		TestCaseName="GC_REG54_ValidateWestinPostStayEmailinSimpChineselanguage"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Property offer 
		String sPropOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//span[text()='"+sPropOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Property Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Property Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("PostStay Offer Title is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=21, dependsOnMethods="EmailLogin")
	public void EmailValidationScript48(){//Pre stay

		TestCaseName="GC_REG48_ValidateSNAStRegisPreStaySpanishLangSPGOffer3SocialMedia";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ReservationNumber")+" "+SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate the social Media Icons 
		//Validate Flicker
		if(SW.ObjectExists("//img[contains(@src,'Flickr_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Twitter Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Twitter Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Twitter Social Media Icon is not present in the Email");
		}
		//Validate Facebook
		if(SW.ObjectExists("//img[contains(@src,'FB_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		//Validate Instagram
		if(SW.ObjectExists("//img[contains(@src,'Instagram_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		//Validate the SPG Offer
		String SPGOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+SPGOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "SPG Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=22, dependsOnMethods="EmailLogin")
	public void EmailValidationScript55(){//Post stay

		TestCaseName="GC_REG55_ValidateSheratonPostStayEmailJapaneselangGEIandSPGoffer"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate GEI offer 
		String GEIOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//span[text()='"+GEIOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "GEI Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "GEI Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("GEI Offer Title is not present in the email");
		}
		// Validating SPG PostStay offer
		String sSPGOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString2");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+sSPGOfferTitle+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "SPG Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}
		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=23, dependsOnMethods="EmailLogin")
	public void EmailValidationScript56(){//Post stay

		TestCaseName="GC_REG56_ValidateNonBrandPostStayGermanPropertyOfferEligibility3Guests"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Property offer
		String sPropOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+sPropOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Property Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Property Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Property Offer Title is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=24, dependsOnMethods="EmailLogin")
	public void EmailValidationScript51(){//Post stay

		TestCaseName="GC_REG51_ValidateGeiPostayEmailforAloft"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate GEI offer 
		String GEIOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//span[text()='"+GEIOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "GEI Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "GEI Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("GEI Offer Title is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=25, dependsOnMethods="EmailLogin")
	public void EmailValidationScript57(){//Post stay

		TestCaseName="GC_REG57_ValidateP100ElementPostStayEmailEnglangPropOffand3icons"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Property offer
		String sPropOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+sPropOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Property Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Property Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("PostStay Offer Title is not present in the email");
		}
		//Validate the social Media Icons 
		//Validate Flicker
		if(SW.ObjectExists("//img[contains(@src,'Flickr_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Twitter Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Twitter Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Twitter Social Media Icon is not present in the Email");
		}
		//Validate Facebook
		if(SW.ObjectExists("//img[contains(@src,'FB_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		//Validate Instagram
		if(SW.ObjectExists("//img[contains(@src,'Instagram_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=26, dependsOnMethods="EmailLogin")
	public void EmailValidationScript53(){//Pre stay

		TestCaseName="GC_REG53_ValidateP100FPPreStayItalianPropertyOfferSWNightsEligGreaterThan75";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ReservationNumber")+" "+SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}

		//Validate Property offer 
		String PropertyOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+PropertyOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "SPG Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "SPG Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("SPG Offer Title is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=27, dependsOnMethods="EmailLogin")
	public void EmailValidationScript62(){//AHBB

		TestCaseName="GC_REG62_ValidateFPAHBB_SBMPItalianLanguage";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		//Validate AHBB secondary prop offer header  

		String ActualString=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB secondary prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB secondary prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB secondary prop offer header is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=28, dependsOnMethods="EmailLogin")
	public void EmailValidationScript17(){

		TestCaseName="GC_REG17_ValidateMultiRateResResconwithspgPromo";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate the confirmation number 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"']")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Reservation Number is not present in the email");
		}

		//Validate Property offer 
		String OfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		if(SW.ObjectExists("//*[text()='"+OfferTitle+"']")){
			Environment.loger.log(Level.INFO, "Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Offer Title is not present in the email");
		}
		//Validate the social Media Icons 
		//Validate Twitter
		if(SW.ObjectExists("//img[contains(@src,'Twitter_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Twitter Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Twitter Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Twitter Social Media Icon is not present in the Email");
		}
		//Validate Facebook
		if(SW.ObjectExists("//img[contains(@src,'FB_blue_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Facebook Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Facebook Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Facebook Social Media Icon is not present in the Email");
		}
		//Validate flicker
		if(SW.ObjectExists("//img[contains(@src,'Flickr_32.png')]")){
			Environment.loger.log(Level.INFO, "Selected Flicker Social Media Icon is present in the Email");
		}else{
			Environment.loger.log(Level.ERROR, "Selected Flicker Social Media Icon is not present in the Email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Selected Flicker Social Media Icon is not present in the Email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=29, dependsOnMethods="EmailLogin")
	public void EmailValidationScript41(){//Post stay

		TestCaseName="GC_REG41_ValidateGEiPostStayEmailforFP"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate GEI offer 
		String GEIOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+GEIOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "GEI Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "GEI Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("GEI Offer Title is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=30, dependsOnMethods="EmailLogin")
	public void EmailValidationScript42(){//Post stay

		TestCaseName="GC_REG42_ValidateGeiPostayEmailforWhotels"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate GEI offer 
		String GEIOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+GEIOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "GEI Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "GEI Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("GEI Offer Title is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=31, dependsOnMethods="EmailLogin")
	public void EmailValidationScript52(){//Post stay

		TestCaseName="GC_REG52_ValidateGeiPostayEmailforLC"; 

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"ValiadtionString1");//search with the Offer Title since post stay mails won't have reservation number 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate GEI offer 
		String GEIOfferTitle=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//*[text()='"+GEIOfferTitle+"']")){
			Environment.loger.log(Level.INFO, "GEI Offer Title is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "GEI Offer Title is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("GEI Offer Title is not present in the email");
		}

		// email validation ends here 
		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=32, dependsOnMethods="EmailLogin")
	public void EmailValidationScript60(){//AHBB

		TestCaseName="GC_REG60_ValidateElementAHBBSBMPEmailSpanishLang";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		//Validate AHBB secondary prop offer header  

		String ActualString=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB secondary prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB secondary prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB secondary prop offer header is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=33, dependsOnMethods="EmailLogin")
	public void EmailValidationScript61(){//AHBB

		TestCaseName="GC_REG61_ValidateAloftAHBBSBMPEnglishSPGPromoOfferEligibility";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		//Validate AHBB secondary prop offer header  

		String ActualString=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB secondary prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB secondary prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB secondary prop offer header is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=34, dependsOnMethods="EmailLogin")
	public void EmailValidationScript64(){//AHBB

		TestCaseName="GC_REG64_ValidateAHBBMBMPEmailJapanlangTributeandDesignbrands";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		//Validate AHBB secondary prop offer header  

		String ActualString=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB secondary prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB secondary prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB secondary prop offer header is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=35, dependsOnMethods="EmailLogin")
	public void EmailValidationScript65(){//AHBB

		TestCaseName="GC_REG65_ValidateAHBBMBMPEmailEngLangFP_WH_LMbrands";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		//Validate AHBB secondary prop offer header  

		String ActualString=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB secondary prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB secondary prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB secondary prop offer header is not present in the email");
		}

		//Validate AHBB third offer header  

		String ActualString2=SW.GetEmailTestData(TestCaseName, "ValiadtionString5");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString2+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB Third prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB Third prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB Third prop offer header is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}

	@Test(priority=36, dependsOnMethods="EmailLogin")
	public void EmailValidationScript66(){//AHBB

		TestCaseName="GC_REG66_ValidateAHBBMBMPEmailFrenchlangAloft_NB_StRegis";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		//Validate AHBB secondary prop offer header  

		String ActualString=SW.GetEmailTestData(TestCaseName, "ValiadtionString4");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB secondary prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB secondary prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB secondary prop offer header is not present in the email");
		}

		//Validate AHBB third offer header  

		String ActualString2=SW.GetEmailTestData(TestCaseName, "ValiadtionString5");
		//List<String> UIText=SW.GetAllText("(//tr[td//text()='"+ActualString+"'])[last()]");
		if(SW.ObjectExists("(//tr[td//text()[contains(.,'"+ActualString2+"')]])[last()]")){
			Environment.loger.log(Level.INFO, "AHBB Third prop offer header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB Third prop offer header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB Third prop offer header is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}


	@Test(priority=37, dependsOnMethods="EmailLogin")
	public void EmailValidationScript69(){//AHBB

		TestCaseName="GC_REG69_ValidateStRegisAHBBSBSPChineseEligibilityIncludeSingleValueOfDepMonth";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}
	@Test(priority=38, dependsOnMethods="EmailLogin")
	public void EmailValidationScript67(){//AHBB

		TestCaseName="GC_REG67_ValidateSheratonAHBBSBSPGuestEmailEngLangNDS";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution becouse execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		String SearchText=SW.GetEmailTestData(TestCaseName,"EmailSubjectLine");
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SearchText+Keys.ENTER);

		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation 
		//Validate Email Pre header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString1")+"']")){
			Environment.loger.log(Level.INFO, "Email Pre Header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Email Pre Header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Email Pre Header is not present in the email");
		}

		//Validate AHBB Offer  
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer is not present in the email");
		}
		//Validate AHBB Offer Sub Header 
		if(SW.ObjectExists("//span[text()='"+SW.GetEmailTestData(TestCaseName, "ValiadtionString3")+"']")){
			Environment.loger.log(Level.INFO, "AHBB offer sub header is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB offer  sub header is present is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("AHBB offer sub header is not present in the email");
		}
		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}


	@Test(priority=39, dependsOnMethods="EmailLogin")
	public void EmailValidationScript78(){

		TestCaseName="GC_REG78_ValidateUnsubscribeLinkForTheSPGReservationCancellationFromSaratoga";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution because execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}
		//Validate the cancellation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "CancellationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Cancellation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Cancellation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate Unsubscribe Link
		String UnsubscribeLink=SW.GetEmailTestData(TestCaseName, "ValiadtionString1");
		if(SW.ObjectExists("//a[text()='Unsubscribe']")){
			Environment.loger.log(Level.INFO, "UnSubscribe Link is present in the SPG ResCan email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
			SW.Click("//a[text()='Unsubscribe']");
			Environment.loger.log(Level.INFO, "UnSubscribe Link is clicked");
		}else{
			Environment.loger.log(Level.ERROR, "UnSubscribe Link is not present in the SPG ResCan email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}


	@Test(priority=40, dependsOnMethods="EmailLogin")
	public void EmailValidationScript79(){

		TestCaseName="GC_REG79_ValidateResconfEmailDoneWithMultiRooms";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution because execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		//Validate Currency Codes of both the rooms
		String Room1Currency;
		if(SW.ObjectExists("ResConfEmail_Room1Currency_DT")){
			Room1Currency=SW.GetText("ResConfEmail_Room1Currency_DT");
			SW.CompareTextContained("Validate Room1 Currency Code", "US DOLLARS", Room1Currency);
			Environment.loger.log(Level.INFO, "Currency Code for Room1 is US Dollars");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Currency Code for Room1 is not US Dollars");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
		}

		String Room2Currency;
		if(SW.ObjectExists("ResConfEmail_Room2Currency_DT")){
			Room2Currency=SW.GetText("ResConfEmail_Room2Currency_DT");
			SW.CompareTextContained("Validate Room2 Currency Code", "US DOLLARS", Room2Currency);
			Environment.loger.log(Level.INFO, "Currency Code for Room2 is US Dollars");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Currency Code for Room2 is not US Dollars");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
		}
		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}



	@Test(priority=41, dependsOnMethods="EmailLogin")
	public void EmailValidationScript80(){

		TestCaseName="GC_REG80_ValidateSplitReservationFor2GuestFor2Room";

		String ExecutionFlag=SW.GetEmailTestData(TestCaseName,"ExecutionFlag");
		if(ExecutionFlag.equals("N")){
			throw new SkipException("Skipping the execution because execution flag is N");
		}
		Environment.loger.log(Level.INFO, "****Execution Started for the test case--"+TestCaseName);
		SW.WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//First Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Reservation Number of Multi Room reservation is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Reservation Number of Multi Room reservation is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	
	
		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"EmailSubjectLine")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();
		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectSecondMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Second Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ReservationNumber")+"')]")){
			Environment.loger.log(Level.INFO, "Confirmation number of Split Reservation1 is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Confirmation number of Split Reservation1 is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}


		SW.Click("GCOutlookWebMail_SearchField_LT");
		SW.Wait(3);
		//Pass the subject line to search 
		SW.EnterValue("GCOutlookWebMail_SearchField_EB", SW.GetEmailTestData(TestCaseName,"ValiadtionString2")+Keys.ENTER);
		//SW.Click("GCOutlookWebMail_Search_IC");
		SW.Wait(5);
		SW.WaitForPageload();

		if(SW.ObjectExists("//span[contains(.,'return any results')]")){
			Environment.loger.log(Level.ERROR, "Mail is not present in the mail box");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");

			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Mail is not present in the mail box");
		}
		SW.DoubleClick("GCOutlookWebMail_SelectFirstMail_LK");// Double click on the first mail
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.WaitForPageload();

		//Second Email validation here

		//Validate the confirmation number 
		if(SW.ObjectExists("//span[contains(.,'"+SW.GetEmailTestData(TestCaseName, "ValiadtionString2")+"')]")){
			Environment.loger.log(Level.INFO, "Confirmation number of Split Reservation2 is present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Pass");
		}else{
			Environment.loger.log(Level.ERROR, "Confirmation number of Split Reservation2 is not present in the email");
			SW.WriteToEmailTestData(TestCaseName, "Result", "Fail");
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(1);
			if(SW.ObjectExists("//span[@aria-label='Exit search']")){
				SW.Click("//span[@aria-label='Exit search']");
			}
			SW.FailCurrentTest("Search text is not present in the email");
		}

		// email validation ends here 

		SW.CloseOnlyThisBrowser();
		SW.SwitchToWindow(1);
		if(SW.ObjectExists("//span[@aria-label='Exit search']")){
			SW.Click("//span[@aria-label='Exit search']");
		}
	}



	@AfterClass
	public void EndTest(){
		SW.SwitchToWindow(1);
		if(SW.LogoutFromEmail()){
			Reporter.StopTest();
		}
	}
}
