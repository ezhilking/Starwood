package testscripts.pmsRegression;
/* Purpose		: 
 * TestCase name:Test Case 1)Galaxy: 
A) Create Comment in Lightspeed (specifically for AMB guest)
B) Copy to SG in SGR
C) Modify in SGR
D) Delete Comment in Lightspeed
Test Case 2) Create comment in SGR and validate the flow to Galaxy
 * Created By	: Ezhilarasan	.S 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.sikuli.script.Key;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;
import functions.Utility;

public class GalaxyPMS_REG02_CreateComment {
	String GCD;
	String PMSConfNum="964102122";
	String PropertyNo = "1055";
	String GalaxyComment,SGRComment,NewCommentInSGR,CopiedCommentsInSGR;
	CRM SW = new CRM();
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
		if(SW.DropDown_SelectByText("SGRCommentMapping_PMSDept_DD", "Displayed After Check In")){//Mapping if mapping not was mapped already.
			SW.DropDown_SelectByIndex("SGRCommentMapping_StarguestDept_DD", 1);
			SW.Click("SGREventMapping_MapDept_BN");
		}
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=2,dependsOnMethods="SGRMappingPrerequisite")	
	public void GalaxyLogin(){
		SW.NavigateTo(Environment.GALAXYURL);
		SK.GalaxyPMSLogin(PMSConfNum);

		//		SW.SikuliRegionType(Key.ENTER);
		SW.Click("GalaxyPMS_Add_BN");

		SK.KeyboardStrokes("alt|c");//Add comment
		if(SK.SikuliRegionObjectExists("Galaxy_OK_BN"))
			SK.KeyboardStrokes(Key.ENTER);

		SW.Wait(5);
		SK.SikuliClick("Galaxy_Type_DD");

		for(int i=1;i<=4;i++){
			SK.SikuliRegionType(Key.DOWN);//Select the Type dropdown value
		}
		SW.Wait(5);
		for(int i=0;i<=3;i++){
			SK.KeyboardStrokes(Key.TAB);
			SW.Wait(3);
		}
		GalaxyComment = SW.RandomString(5);
		SK.SikuliRegionType("GalaxyComment"+GalaxyComment);
		SW.Wait(4);
		SK.KeyboardStrokes("alt|a");//Click add button
		SK.SikuliClick("Galaxy_OK_BN");
		SW.Wait(5);
		SW.ClickByJavascript("GalaxyPMS_Save_BN");
		SK.SikuliClick("Galaxy_Close_BN");
		Utility.CloseBrowser();
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-SGR Add Comments part*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=3,dependsOnMethods="AddCommentsInPMS")
	public void CopiedAndNewCommentsInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), "1965");
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", PMSConfNum);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SGRComment = SW.WebTbl_GetText("SGRGuestDetails_Comments_WT", 1, 2);
		SW.CompareTextContained("CommentsInSGR",GalaxyComment,SGRComment);
		//Create duplicate comments
		SW.Click("SGRGuestDetails_CopyToSG_LK");
		CopiedCommentsInSGR = "UpdatedAutomationCommentInSGR:"+SW.RandomString(10);
		SW.EnterValue("SGRGuestDetails_AddComment_EB", CopiedCommentsInSGR);
		SW.Click("SGRGuestDetails_AddCommentSave_BN");
		SW.WaitTillInvisibilityOfElement("SGRGuestDetails_AddCommentSave_BN");
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
	@Test(priority=4,dependsOnMethods="CopiedAndNewCommentsInSGR")
	public void DeleteAndFinalValidationInGalaxy(){
		SW.LaunchBrowser(Environment.GALAXYURL);
		SK.GalaxyPMSLogin(PMSConfNum);
		String AttriValue = SW.GetAttributeValue("GalaxyPMS_CommentsPlus_IC", "src");
		//TOOD Delete 
		if(AttriValue.endsWith("/plus.gif"))
			SW.ClickByJavascript("GalaxyPMS_CommentsPlus_IC");

		SW.ClickByJavascript("GalaxyPMS_CommentsDelete_IC");//Delete the comment
		SK.SikuliClick("Galaxy_Close_BN");//Handle the popup
		SW.ClickByJavascript("GalaxyPMS_Save_BN");
		SK.SikuliClick("Galaxy_Close_BN");
		Utility.CloseBrowser();
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-SGR Add Comments part*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Test(priority=5,dependsOnMethods="DeleteAndFinalValidationInPMS")
	public void FinalValidationInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", PMSConfNum);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		if(!SW.ObjectExists("SGRGuestDetails_CopyToSG_LK")){
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


