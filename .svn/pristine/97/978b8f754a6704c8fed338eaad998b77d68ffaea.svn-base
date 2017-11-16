package testscripts.pmsRegression;
/* Purpose		: Comments validation in SGR and PMS
 * TestCase Name: Test Case 1) Opera:
A) Create Comment in Opera (specifically for AMB guest)
B) Copy to SG in SGR
C) Modify in SGR
D) Delete Comment in OPERA
Test case 2) Create comment in SGR and validate the flow to Opera
 * Created By	: Ezhilarasan.S 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.apache.log4j.Level;
import org.sikuli.script.Key;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;
import functions.Utility;

public class OperaPMS_REG01_CreateComment {
	CRM SW = new CRM();
	String ReservationNo = "404112910";
	String PropertyNo = "1965";
	String PMSComment,SGRComment,NewCommentInSGR,CopiedCommentsInSGR;

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test(priority=1)
	public void SGRMappingPrerequisite(){
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PMSCommentMapping_LK");//Click PMS Comment Mapping
		if(SW.DropDown_SelectByText("SGRCommentMapping_PMSDept_DD", "Displayed After Check In")){//if mapping not was mapped already. This method will return false
			SW.DropDown_SelectByIndex("SGRCommentMapping_StarguestDept_DD", 1);
			SW.Click("SGREventMapping_MapDept_BN");
		}
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=2)
	public void AddCommentsInPMS(){
		SW.NavigateTo(Environment.PMS_1965);
		SK.OperaPMSLogin(SW.TestData("PMSUsername"), SW.TestData("PMSPassword"),ReservationNo);
		SW.Wait(10);
		SK.SikuliClick("UpdateReservation_Comment_BN");
		SW.Wait(6);
		SK.KeyboardStrokes("alt|n");
		SW.Wait(5);
		SK.SikuliRegionType("IN HOUSE");//Comment type-which is mapped in SGR.(IN HOUSE- Displayed After Check In)
		SK.SikuliRegionType(Key.TAB);
		SK.SikuliRegionType(Key.TAB);
		PMSComment = "PMSAutomationComment: "+SW.RandomString(10);
		SK.SikuliRegionType(PMSComment);
		SW.Wait(2);
		SK.KeyboardStrokes("alt|o");
		SW.Wait(2);
		SK.KeyboardStrokes("alt|c");
		SW.Wait(2);
		SK.KeyboardStrokes("alt|c");//Reservation close button
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-SGR Add Comments part*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=3,dependsOnMethods="AddCommentsInPMS")
	public void CopiedAndNewCommentsInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), "1965");
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		if(SW.ObjectExists("//table[@id='guestCommentsTBL']/tbody//tr[td//text()[contains(., '"+PMSComment+"')]]/td[2]")){//check for the comment in the sgr comment list
			Environment.loger.log(Level.INFO, "PMS Comment is reached in SGR");
			
			SW.Click("//table[@id='guestCommentsTBL']/tbody//tr[td//text()[contains(., '"+PMSComment+"')]]/td[5]/a");// click on the corresponding add to sg link 
			SW.Wait(10);
			CopiedCommentsInSGR = "UpdatedAutomationCommentInSGR:"+SW.RandomString(10);
			SW.EnterValue("SGRGuestDetails_AddComment_EB", CopiedCommentsInSGR);
			SW.Click("SGRGuestDetails_AddCommentSave_BN");
			SW.WaitTillInvisibilityOfElement("SGRGuestDetails_AddCommentSave_BN");
		}else{
			Environment.loger.log(Level.INFO, "PMS Comment is not reached to SGR");
			SW.FailCurrentTest("PMS Comment is not reached to SGR");
		}
		//SGRComment = SW.WebTbl_GetText("SGRGuestDetails_Comments_WT", 1, 2);
		//SW.CompareTextContained("CommentsInSGR",PMSComment,SGRComment);
		//Create duplicate comments
		
		SW.Wait(4);

		//Add new comment for another testcase coverage
		SW.Click("SGRGuestDetails_CreateNewComment_BN");
		SW.DropDown_SelectByText("SGRGuestDetails_CreateCommentDept_DD", "FRONT DESK");
		NewCommentInSGR = "New Automation Comment in SGR:"+SW.RandomString(10);
		SW.EnterValue("SGRGuestDetails_AddComment_EB", NewCommentInSGR);
		SW.DropDown_SelectByText("SGRGuestDetails_CreateCommentVisibility_DD", "Global");
		SW.Click("SGRGuestDetails_AddCommentSave_BN");
		Utility.CloseBrowser();
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-PMS Validation and Delete Comments*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=4,dependsOnMethods="CopiedAndNewCommentsInSGR")
	public void DeleteAndFinalValidationInPMS(){
		SW.LaunchBrowser(Environment.PMS_1965);
		SK.OperaPMSLogin(SW.TestData("PMSUsername"), SW.TestData("PMSPassword"),ReservationNo);
//		
//		
//		//Bring back the control to PMS
//		SW.KeyboardStrokes("alt|r");
//		SW.Wait(2);
//		SW.KeyboardStrokes("u");
//		SW.Wait(3);
//		SW.SikuliFocusRegionWindow();
//		for(int i=0;i<=4;i++){
//			SW.Wait(1);
//			SW.SikuliRegionType(Key.TAB);
//		}
//		SW.SikuliRegionType(ReservationNo);
//		SW.Wait(2);
//		SW.SikuliRegionType(Key.ENTER);
		SK.SikuliClick("UpdateReservation_Comment_BN");
		SW.Wait(6);
		SK.SikuliSaveScreenshot("PMSCommentsValidation");
		//Validate the Updated comments which added in SGR

//		SW.SikuliCompareText("CopiedComments Which Added InSGR", CopiedCommentsInSGR,"");//TODO

		//Validate the New comments which added in SGR
		SK.KeyboardStrokes(Key.DOWN);

//		SW.SikuliCompareText("NewComment Which Added InSGR", NewCommentInSGR,"");	//TODO

		//Delete the comment which added in PMS and validate whether it's deleted or not.
		SK.KeyboardStrokes(Key.DOWN);

		SK.KeyboardStrokes("alt|d");//Delete comments
		SW.Wait(5);
		SK.KeyboardStrokes("alt|y");//Click yes button
		Environment.loger.log(Level.INFO, "Comments has been deleted successfully");

		//		SW.KeyboardStrokes("alt|e");//Edit the comment to copy to clipboard.
		//		SW.Wait(6);
		//		String Comment = SW.SikuliCopyToClipboard();
		//		SW.CompareText("CommentsValidation_DT", CopiedCommentsInSGR, Comment);
		//		SW.KeyboardStrokes("alt|o");
		//		SW.Wait(6);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-SGR Add Comments part*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=5,dependsOnMethods="DeleteAndFinalValidationInPMS")
	public void FinalValidationInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		if(!SW.ObjectExists("//table[@id='guestCommentsTBL']/tbody//tr[td//text()[contains(., '"+PMSComment+"')]]/td[5]/a")){
			SW.CompareText("Pass", "Comments Deleted successfully", "Comments Deleted successfully");
		}else{
			SW.FailCurrentTest("Deleted comments in OPeraPMS still availble in SGR");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
