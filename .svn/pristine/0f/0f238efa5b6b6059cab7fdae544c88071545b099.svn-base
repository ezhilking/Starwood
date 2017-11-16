package testscripts.sgrRegression;
/* Purpose		: This test case is to map the Preference level mapping of SGR and validating.
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

public class SGR_REG03_02_PMSMappingPreference {
	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void MappingPreference(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_PMSPreferenceMapping_LK");
			SW.Click("SGRPreferenceMapping_NewMapping_BN");
			SW.DropDown_SelectByIndex("SGRPreferenceMapping_Type_DD", 0);
			int PreferenceSize = SW.DropDown_GetSize("SGRPreferenceMapping_Preference_DD");

			for(int i=0;i<5;i++){
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Preference_DD", SW.RandomNumber(0, PreferenceSize/2));//256 values in DropDown so, filtering/2
				String PreferenceSelectedText = SW.DropDown_GetSelectedText("SGRPreferenceMapping_Preference_DD");
				if(!PreferenceSelectedText.contains("MAPPED")){
					break;
				}
			}
			int GroupSize = SW.DropDown_GetSize("SGRPreferenceMapping_Group_DD");//Since its static not getting size again
			String GroupSelectedText = "";
			String CategorySelectedText = "";
			String DetailSelectedText = "";

			//Select Group, Comment, Detail until becomes not a duplicate one.
			do{
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Group_DD", SW.RandomNumber(0, GroupSize-1));
				GroupSelectedText = SW.DropDown_GetSelectedText("SGRPreferenceMapping_Group_DD");

				int CategorySize = SW.DropDown_GetSize("SGRPreferenceMapping_Category_DD");
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Category_DD", SW.RandomNumber(0, CategorySize-1));
				CategorySelectedText = SW.DropDown_GetSelectedText("SGRPreferenceMapping_Category_DD");

				int DetailSize = SW.DropDown_GetSize("SGRPreferenceMapping_Detail_DD");
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Detail_DD", SW.RandomNumber(0, DetailSize-1));
				DetailSelectedText = SW.DropDown_GetSelectedText("SGRPreferenceMapping_Detail_DD");

			}while(SW.HandleAlert(true));//If alert is not available SW.HandleAlert(true) will return false

			SW.Click("SGRPreferenceMapping_MapPreference_BN");
			SW.TakeScreenshot("PMSPreferenceMappingCreated");
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_PMSPreferenceMapping_LK");

			int TableRowCount = SW.WebTbl_GetRowCount("SGRPreferenceMapping_Main_WT");
			boolean verified = false;
			int RowIndex;
			for(RowIndex = 2;RowIndex <= TableRowCount;RowIndex++){//Validating Group,Category, Detail alone
				String ColText = SW.WebTbl_GetText("SGRPreferenceMapping_Main_WT", RowIndex, 2);
				//				if(SW.CompareTextContained("GroupSelectedText_DT",ColText, GroupSelectedText) && SW.CompareTextContained("CategorySelectedText_DT",ColText, CategorySelectedText) && SW.CompareTextContained("DetailSelectedText_DT",ColText, DetailSelectedText)){
				if(SW.CompareTextContained(ColText, GroupSelectedText) && SW.CompareTextContained(ColText, CategorySelectedText) && SW.CompareTextContained(ColText, DetailSelectedText)){//TODO Change one parameter to CompaterText to write in log or result
					verified = true;
					break;
				}
			}

			//if its successfully added delete for proper flow.
			if(verified){
				SW.WebTbl_Click("SGRPreferenceMapping_Main_WT", RowIndex, 3);
				//				SW.Click(SW.GetXPath("SGRPreferenceMapping_Main_WT")+"tbody/tr"+"["+RowIndex+"]/td[3]/input[1]");
				SW.TakeScreenshot("PMSPreferenceMapping_Deleted_WB");
				Environment.loger.log(Level.INFO, "Successfully Deleted the PMS preference mapping");
			}else{
				SW.FailCurrentTest("PMSMappingPreference Not Successfully Added!!");
				
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
