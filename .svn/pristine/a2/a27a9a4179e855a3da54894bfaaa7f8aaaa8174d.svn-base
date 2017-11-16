package testscripts.sgrRegression;
/** Purpose		: Validate the sort order on the Room Rate column in MAR
 * TestCase Name: Validate the sort order on the Room Rate column in MAR for a view which is saved with ascending and Descending order of Room Rate column
 * Created By	: Sachin G
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SGR_REG63_ValidateSortOrderOnRoomRateColumnMAR {
	CRM SW = new CRM();

	@BeforeClass
	public void Intialize(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test(priority=0)
	public void CreateViewWithRoomRate(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		//Click on default button for default column lists
		SW.ClickByJavascript("SGRArrivalReport_DefaultSort_LK");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.ClickByJavascript("SGRArrivalReport_Column_LK");
		SW.Click("SGRMAR_RoomRateAddColumns_LK");//Add Star point 
		List<WebElement> AllMinusimg= SW.GetAllElements("SGRMAR_AllMinusInAddColumns_LK");
		for(int i=0;i<AllMinusimg.size()-1;i++){//Click on all minus except last one to remove from the view 
			AllMinusimg.get(i).click();
		}
		SW.Wait(3);
		SW.Click("SGRArrivalReportSelectColumns_OK_BN");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		if(SW.ObjectExists("SGRMAR_RoomRateColumnHeader_WT")){
			Reporter.Write("Validate Room Rate Column", "Column should be added", "Column added", "PASS");
		}else{
			Reporter.Write("Validate Room Rate Column", "Column should be added", "Column not added", "FAIL");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateViewWithRoomRate")
	public void validateSortOrder(){
		SW.NormalClick("SGRMAR_RoomRateColumnHeader_WT");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(8);
		SW.NormalClick("SGRMAR_RoomRateColumnHeader_WT");
		//Get all the dates from the Departure Date Column
		List<String> RoomRatesFromUI = new ArrayList<String>(); 
		RoomRatesFromUI = SW.GetAllText("SGRMAR_RoomRates_DT");//get all star points columns from the report
		if(RoomRatesFromUI.size()==1){
			Environment.loger.log(Level.INFO, "No Records found in the MAR check for deferent property!");
			Reporter.Write("Validate MAR", "No Records found", "No Records found", "FAIL");
		}
		SW.ValidateIntegerSortOrder(RoomRatesFromUI, "D");
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
