package testscripts.sgrRegression;
/* Purpose		: This test case is to map the Event level mapping of SGR and validating.
 * TestCase Name: PMS Mapping screens_map one pref, one comment, one event dept, one VIP level_validate mapping is seen as saved/mapped
 * Created By	: Ezhilarasan.S
 * Modified By	:  Sachin G
 * Modified Date:  04/07/2016
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

public class SGR_REG03_01_PMSMappingEvent {
	CRM SW = new CRM();

	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test
	public void MappEvent(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_PMSEvenetDeptMapping_LK");

			int DefaultRowCount = SW.WebTbl_GetRowCount("SGREventMapping_MappedEvent_WT");
			int PMSDeptSize = SW.DropDown_GetSize("SGREventMapping_PMSDept_DD");
			SW.DropDown_SelectByIndex("SGREventMapping_PMSDept_DD", SW.RandomNumber(0, PMSDeptSize-1));
			String PMSDeptSelectedText = SW.DropDown_GetSelectedText("SGREventMapping_PMSDept_DD");

			int StarguestDeptSize = SW.DropDown_GetSize("SGREventMapping_StarguestDept_DD");
			SW.DropDown_SelectByIndex("SGREventMapping_StarguestDept_DD", SW.RandomNumber(0, StarguestDeptSize-1));
			String StarguestDeptText = SW.DropDown_GetSelectedText("SGREventMapping_StarguestDept_DD");

			SW.Click("SGREventMapping_MapDept_BN");

			SW.CompareText("TableRowCount", String.valueOf(DefaultRowCount+1), String.valueOf(SW.WebTbl_GetRowCount("SGREventMapping_MappedEvent_WT")));

			//Mapping will be added to the 1st row so, not iterating.//TODO include the iteration

			int TableRowCount = SW.WebTbl_GetRowCount("SGREventMapping_MappedEvent_WT");
			boolean IsPass = false;
			int RowIndex;
			for(RowIndex = 1;RowIndex <= TableRowCount;RowIndex++){
				String PMSDeptSelectedActual = SW.WebTbl_GetText("SGREventMapping_MappedEvent_WT", RowIndex, 1);
				String StarguestDeptActual = SW.WebTbl_GetText("SGREventMapping_MappedEvent_WT", RowIndex, 2);
				if(SW.CompareText(PMSDeptSelectedText, PMSDeptSelectedActual) && SW.CompareText(StarguestDeptText, StarguestDeptActual)){
					IsPass = true;
					break;
				}
			}

			if(IsPass){
				SW.GetScreenshot("PMSMappingEventBeforeDeleting");
				SW.WebTbl_Click("SGREventMapping_MappedEvent_WT", RowIndex, 3);
				SW.HandleAlert(true);
				SW.GetScreenshot("PMSMappingEventAfterDeleted");
			}else{
				SW.FailCurrentTest("PMSMappingEvent Not Successfully Added!!");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
