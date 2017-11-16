package testscripts.pmsRegression;
/* Purpose		: 
 * TestCase	Name: 
A) Add Special Service Code (non-SPG Preference) in Lightspeed 
B) Modify/ADD notes in SGR
C) Delete in Lightspeed
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

public class GalaxyPMS_REG03_Preference {
	CRM SW = new CRM();
	String ReservationNo = "898945499";
	String PropertyNo = "1055";
	String PreferenceText;

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void SGRPreferenceMappingPrerequisite(){
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PMSPreferenceMapping_LK");//Click PMS Preference Mapping

		int RowCount = SW.WebTbl_GetRowCount("SGRPreferenceMapping_Mapping_WT");
		if(RowCount<2){		//If preference is not available then map a preference and come back to the same page.
			SW.Click("SGRPreferenceMapping_NewMapping_BN");
			SW.DropDown_SelectByIndex("SGRPreferenceMapping_Type_DD", 0);
			int PreferenceSize = SW.DropDown_GetSize("SGRPreferenceMapping_Preference_DD");
			//			int onePreferenceSize = SW.DropDown_GetSize("SGRPreferenceMapping_Preference_DD");

			for(int i=0;i<5;i++){
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Preference_DD", SW.RandomNumber(0, PreferenceSize/2));//256 values in DropDown so, filtering/2
				String PreferenceSelectedText = SW.DropDown_GetSelectedText("SGRPreferenceMapping_Preference_DD");
				if(!PreferenceSelectedText.contains("MAPPED")){
					break;
				}
			}
			int GroupSize = SW.DropDown_GetSize("SGRPreferenceMapping_Group_DD");//Since its static not getting size again
			//Select Group, Comment, Detail until becomes not a duplicate one.
			do{
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Group_DD", SW.RandomNumber(0, GroupSize-1));

				int CategorySize = SW.DropDown_GetSize("SGRPreferenceMapping_Category_DD");
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Category_DD", SW.RandomNumber(0, CategorySize-1));

				int DetailSize = SW.DropDown_GetSize("SGRPreferenceMapping_Detail_DD");
				SW.DropDown_SelectByIndex("SGRPreferenceMapping_Detail_DD", SW.RandomNumber(0, DetailSize-1));

			}while(SW.HandleAlert(true));
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_PMSPreferenceMapping_LK");
		}
		PreferenceText = SW.WebTbl_GetText("SGRPreferenceMapping_Mapping_WT", 2, 1);
		try{
			PreferenceText = PreferenceText.split(",")[1].split("\n")[0].trim();
		}catch(Exception e){
			Environment.loger.log(Level.ERROR,"Exception while spilting preference");
		}
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=2,dependsOnMethods="SGRPreferenceMappingPrerequisite")
	public void GalaxyPreference(){
		SW.NavigateTo(Environment.GALAXYURL);
		SK.GalaxyPMSLogin(ReservationNo);
		SW.Click("GalaxyPMS_Add_BN");
		SK.KeyboardStrokes("alt|s");//Add comment
		SK.SikuliRegionWaitForObject("Galaxy_OK_BN");
		for(int i=0;i<=11;i++){//Select the 2D preference
			SW.Wait(2);
			SK.SikuliRegionType(Key.DOWN);
		}	
		SK.SikuliClick("Galaxy_Add_BN");
		SW.Wait(3);
		SK.SikuliClick("Galaxy_OK_BN");
	}

	@Test(priority=3,dependsOnMethods="GalaxyPreference")
	public void PrefernceValidationInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		SW.WaitTillInvisibilityOfElement("SGRSVO_LoadingMask_IC");
		SW.Click("SGRPreferenceMapping_PreferenceAdd_LK");//Click add button to create the preference
		Utility.CloseBrowser();
	}

	@Test(priority=4,dependsOnMethods="PrefernceValidationInSGR")
	public void GalaxyDeletePreference(){
		SW.LaunchBrowser(Environment.GALAXYURL);
		SK.GalaxyPMSLogin(ReservationNo);
		String AttriValue = SW.GetAttributeValue("GalaxyPMS_SpecialServiceRequestPlus_IC", "src");
		//TOOD Delete 
		if(AttriValue.endsWith("/plus.gif"))
			SW.ClickByJavascript("GalaxyPMS_CommentsPlus_IC");
		SW.ClickByJavascript("GalaxyPMS_SpecialServiceRequestDelete_IC");//Delete the preference
		SW.ClickByJavascript("GalaxyPMS_Save_BN");//Save
		SK.SikuliClick("Galaxy_Close_BN");
		Utility.CloseBrowser();
	}

	@Test(priority=5,dependsOnMethods="GalaxyDeletePreference")
	public void FinalValidationInSGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.WaitForPageload();
		SW.WaitTillInvisibilityOfElement("SGRSVO_LoadingMask_IC");
		if(SW.ObjectExists("SGRPreferenceMapping_PreferenceAdd_LK")){
			SW.FailCurrentTest("Preference not yet deleted in SGR");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
