package testscripts.sgrRegression;
/** Purpose		: Validate the sort order on the StarPts column in MAR
 * TestCase Name: Validate the sort order on the StarPts column in MAR for a view which is saved with ascending and Descending order of StarPts column
 * Created By	: Sachin G
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG29_ValidateSortOrderOnStarPtsColumnMAR {
	CRM SW = new CRM();

	@BeforeClass
	public void Intialize(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test(priority=0)
	public void CreateViewWithStarpoint(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1967");	//	Stage :	1965
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.Wait(5);
	//	SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
	//	SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		//Click on default button for default column lists
		SW.ClickByJavascript("SGRArrivalReport_DefaultSort_LK");
	//	SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.ClickByJavascript("SGRArrivalReport_Column_LK");
		SW.Click("SGRMAR_StarPointsAddColumns_LK");//Add Star point 
		List<WebElement> AllMinusimg= SW.GetAllElements("SGRMAR_AllMinusInAddColumns_LK");
		for(int i=0;i<AllMinusimg.size()-1;i++){//Click on all minus except last one to remove from the view 
			AllMinusimg.get(i).click();
		}
		SW.Wait(3);
		SW.Click("SGRArrivalReportSelectColumns_OK_BN");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		if(SW.ObjectExists("SGRMAR_StarpointsColumnHeader_WT")){
			Environment.loger.log(Level.INFO, "Star points column added to MAR");
		}else{
			Environment.loger.log(Level.ERROR, "Star points column failed to add in MAR");
			SW.FailCurrentTest("Star points column failed to add in MAR");
		}

	}
	@Test(priority=1, dependsOnMethods="CreateViewWithStarpoint")
	public void validateSortOrder(){
		/*SW.NormalClick("SGRMAR_StarpointsColumnHeader_WT");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(8);
		SW.NormalClick("SGRMAR_StarpointsColumnHeader_WT");*/
		//Get all the dates from the Departure Date Column
		List<String> StarPointsFromUI = new ArrayList<String>(); 
		StarPointsFromUI = SW.GetAllText("SGRMAR_StarPoints_DT");//get all star points columns from the report
		if(StarPointsFromUI.size()==1){
			Environment.loger.log(Level.INFO, "No Records found in the MAR check for deferent property!");
			SW.FailCurrentTest("No Records found in the MAR check for deferent property!");
		}
		SW.ValidateIntegerSortOrder(StarPointsFromUI, "D");
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
