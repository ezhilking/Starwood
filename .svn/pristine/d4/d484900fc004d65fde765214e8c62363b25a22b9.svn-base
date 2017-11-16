package testscripts.sgrRegression;
/* Purpose		: This is to add/remove the columns in the search result of the master arrival report
 * TestCase Name: Validate the columns in Master Arrival Report under Room Assignment tab by adding or removing the columns from column picker.
 * Created By	: Ezhilarasan.S
 * Modified By	: Sachin G
 * Modified Date: 04/07/2016
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.List;

import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;


public class SGR_REG02_ValidateColumns_MasterArrivalReport {
	CRM SW = new CRM();

	@BeforeClass
	public void Intialize(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=0)
	public void AddOneMoreColumnInMAR(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1047");
			SW.Click("SGRNavigation_Reports_LK");
			SW.Click("SGRNavigation_ArrivalReport_LK");
			SW.Click("SGRArrivalReport_GenerateReport_BN");
			SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
			SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
			//Click on default button for default column lists
			SW.ClickByJavascript("SGRArrivalReport_DefaultSort_LK");
			SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
			//SW.RunJavaScript(SW.GetObject("SGRArrivalReport_GridTableHeader_TB"),"arguments[0].setAttribute('style.overflow-x', 'visible');");
			//Remove the scroll bar to get all the columns in visible state 
			SW.RunJavaScript("SGRArrivalReport_GridTableHeader_WB","document.getElementsByClassName('ui-state-default ui-jqgrid-hdiv')[0].style['overflow-x'] = 'visible';");

			List<WebElement> DefaultTableHeader = SW.GetAllVisibleElements(SW.GetXPath("SGRArrivalReport_GridTable_TB")+"/thead/tr/th//div");
			SW.TakeScreenshot("BeforeAddingNewColumn");
			int DefaultColSize = DefaultTableHeader.size();			
			SW.ClickByJavascript("SGRArrivalReport_Column_LK");
			//MAR adding new column popup
			String AddedTitle = SW.GetAttributeValue("SGRArrivalReportColumns_UnselectedFirstValue_LK", "title");
			SW.Click("SGRArrivalReportColumns_UnselectedFirstValuePlusIcon_LK");
			SW.Click("SGRArrivalReportSelectColumns_OK_BN");
			SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
			SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");

			List<WebElement> ModifiedTableHeader = SW.GetAllVisibleElements(SW.GetXPath("SGRArrivalReport_GridTable_TB")+"/thead/tr/th//div");	
			int size  = ModifiedTableHeader.size();
			//Validate the added column text in MAR with the expected value 
			SW.CompareText("UpdatedColumnCount_WT",String.valueOf(DefaultColSize+1), String.valueOf(size));
			String LastColTitle = SW.GetText(ModifiedTableHeader.get(ModifiedTableHeader.size()-1));
			SW.CompareText("UpdatedColumnText_WT",AddedTitle, LastColTitle);
			SW.Click("SGRArrivalReport_DefaultSort_LK");

		}catch(Exception e){
			Environment.loger.log(Level.INFO, "Exception Occurred!!",e);
		}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
