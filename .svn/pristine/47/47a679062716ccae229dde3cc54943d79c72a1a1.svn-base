package testscripts.sgrRegression;
/** Purpose		: Validate saving and Deleting view in MAR
 * TestCase Name: Validate saving view in MAR
 * Created By	: Sachin G
 * Modified By	: 
 * Modified Date: 
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

public class SGR_REG30_ValidateSavingViewInMAR {
	CRM SW = new CRM();
	String ViewName;
	@BeforeClass
	public void Intialize(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test(priority=0)
	public void CreateViewWithStarpoint(){
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
		//save the View 
		SW.Click("SGRMAR_SaveThisView_LK");
		ViewName= SW.RandomString(5);
		SW.EnterValue("SGRMAR_ViewName_EB", ViewName);
		SW.Click("SGRMAR_MarViewSubmit_BT");
		SW.WaitTillElementToBeClickable("SGRMAR_SaveThisView_LK");
		if(SW.ObjectExists("SGRMAR_StarpointsColumnHeader_WT")){
			Environment.loger.log(Level.INFO, "View Saved Successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Saving View Failed");
			SW.FailCurrentTest("Saving View Failed");
		}
		
	}
	@Test(priority=2, dependsOnMethods="CreateViewWithStarpoint")
	public void ValidateSavedView(){
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.DropDown_SelectByText("SGRMAR_MarSelectView_DD", ViewName);
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.WaitTillInvisibilityOfElement("SGRMAR_LoadImage_IC");
		if(SW.ObjectExists("SGRMAR_StarpointsColumnHeader_WT")){
			Environment.loger.log(Level.INFO, "Saved View MAR generated Successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Saving View MAR generation Failed");
			SW.FailCurrentTest("Saving View MAR generation Failed");
		}
	}
	@Test(priority=3, dependsOnMethods="ValidateSavedView")
	public void DeleteSavedView(){
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRMAR_ManageMARView_LK");
		SW.CheckBox("//input[@value='"+ViewName+"']", "ON");
		SW.ClickAndProceed("SGRMAR_DeleteView_BT");
		SW.HandleAlert(true);
		
		if(SW.ObjectExists("//label[@id='comments' and text()='"+ViewName+" has been deleted successfully.']")){
			Environment.loger.log(Level.INFO, "Saved View MAR Deleted Successfully");
			SW.Click("SGRMAR_CancelView_BT");
		}else{
			Environment.loger.log(Level.ERROR, "Saving View MAR Delete Failed");
			SW.FailCurrentTest("Saving View MAR Delete Failed");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
