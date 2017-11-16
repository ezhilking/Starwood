package testscripts.sgrRegression;
/* Purpose		: This test case is to map the VIP level mapping of SGR and validating.
 * TestCase Name: PMS Mapping screens_map one pref, one comment, one event dept, one VIP level_validate mapping is seen as saved/mapped
 * Created By	: Ezhilarasan.S
 * Modified By	: Sachin G
 * Modified Date: 04/06/2016
 * Reviewed By	:  
 * Reviewed Date: 
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG03_04_PMSMappingVIP {
	CRM SW = new CRM();
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void PMSMappingVIP(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SGRPropertyID"));
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_PMSVIPMapping_LK");//Click PMS VIP Mapping

			int DropDownCount = SW.GetAllElements("SGRVIPMapping_AllDropDown_DD").size();
			List<String> SelectedValues = new ArrayList<>(); 

			for(int index=1;index<=DropDownCount;index++){//Select all dropdown values in a random manner and store the selected text in list
				String Xpath = "("+SW.GetXPath("SGRVIPMapping_AllDropDown_DD")+")["+index+"]";
				int DropDownSize = SW.DropDown_GetSize(Xpath);
				SW.DropDown_SelectByIndex(Xpath, SW.RandomNumber(1, DropDownSize-1));

				String RadioXpath = "("+SW.GetXPath("SGRVIPMapping_AllRadioButton_RB")+")["+index+"]";
				SW.SelectRadioButton(RadioXpath);
				SelectedValues.add(SW.DropDown_GetSelectedText(Xpath));
			}
			SW.Click("SGRVIPMapping_Save_BN");//Click Save button

			for(int index=1;index<=DropDownCount;index++){
				String Xpath = "("+SW.GetXPath("SGRVIPMapping_AllDropDown_DD")+")["+index+"]";
				String Actual = SW.DropDown_GetSelectedText(Xpath);
				SW.CompareText("VIPDropDown"+index, SelectedValues.get(index-1), Actual);
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
