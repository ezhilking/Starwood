package testscripts.sgrRegression;
/* Purpose		: This test case is to map the Comments level mapping of SGR and validating.
 * TestCase Name: PMS Mapping screens_map one pref, one comment, one event dept, one VIP level_validate mapping is seen as saved/mapped
 * Created By	: Ezhilarasan.S
 * Modified By	: Sachin G
 * Modified Date: 04/06/2016
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

public class SGR_REG03_03_PMSMappingComments {
	CRM SW = new CRM();
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test
	public void MappingComments(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_PMSCommentMapping_LK");//Click PMS Comment Mapping

			int DefaultRowCount = SW.WebTbl_GetRowCount("SGREventMapping_MappedEvent_WT");

			int PMSDeptSize = SW.DropDown_GetSize("SGRCommentMapping_PMSDept_DD");
			SW.DropDown_SelectByIndex("SGRCommentMapping_PMSDept_DD", SW.RandomNumber(0, PMSDeptSize-1));
			String CommentTypeSelectedText = SW.DropDown_GetSelectedText("SGRCommentMapping_PMSDept_DD");

			int StarguestDeptSize = SW.DropDown_GetSize("SGRCommentMapping_StarguestDept_DD");
			SW.DropDown_SelectByIndex("SGRCommentMapping_StarguestDept_DD", SW.RandomNumber(0, StarguestDeptSize-1));
			String StarguestDeptSelectedText = SW.DropDown_GetSelectedText("SGRCommentMapping_StarguestDept_DD");

			SW.Click("SGREventMapping_MapDept_BN");

			SW.CompareText("TableRowCount_WT", String.valueOf(DefaultRowCount+1), String.valueOf(SW.WebTbl_GetRowCount("SGREventMapping_MappedEvent_WT")));

			//Mapping can be added at any row so iterating.
			int TableRowCount = SW.WebTbl_GetRowCount("SGREventMapping_MappedEvent_WT");
			boolean verified = false;
			int RowIndex;
			for(RowIndex = 1;RowIndex <= TableRowCount;RowIndex++){
				String PMSCommentTypeText = SW.WebTbl_GetText("SGREventMapping_MappedEvent_WT", RowIndex, 1);
				String StarguestDeptText = SW.WebTbl_GetText("SGREventMapping_MappedEvent_WT", RowIndex, 2);
				//				if(SW.CompareTextContained("GroupSelectedText_DT",ColText, GroupSelectedText) && SW.CompareTextContained("CategorySelectedText_DT",ColText, CategorySelectedText) && SW.CompareTextContained("DetailSelectedText_DT",ColText, DetailSelectedText)){
				if(SW.CompareText(CommentTypeSelectedText, PMSCommentTypeText) && SW.CompareText(StarguestDeptSelectedText, StarguestDeptText)){
					verified = true;
					break;
				}
			}

			//if its Added delete.
			if(verified){
				SW.TakeScreenshot("PMSCommentsMapping_BeforeDelete_WB");
				SW.WebTbl_Click("SGREventMapping_MappedEvent_WT", RowIndex, 3);
				SW.HandleAlert(true);
				SW.TakeScreenshot("PMSCommentsMapping_AfterDelete_WB");
				Environment.loger.log(Level.INFO, "Successfully Deleted the PMS Comments mapping");
			}else{
				SW.FailCurrentTest("PMSMappingComments Not Successfully Added!!");
			}

		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
