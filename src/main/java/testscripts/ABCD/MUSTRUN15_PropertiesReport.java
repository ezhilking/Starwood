package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN15_PropertiesReport {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	
	}
	@Test
	public void PropertiesReport(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.HandleAlert(true);

		//Moving to Report 
		SW.Wait(2);
		SW.MoveToObject("ABCDReorts_home_LK");
		/*WebDriver driver=new FirefoxDriver();
		WebElement menu = driver.findElements(ABCDReorts_home_LK);
		Actions builder = new Actions(driver);
		builder.moveToElement("ABCDReorts_home_LK").click(wait.until(ExpectedConditions.elementToBeClickable("ABCDReports_ReportDashboard_LK"))).perform();
		 */
		SW.WaitTillElementToBeClickable("ABCDReports_ReportDashboard_LK");
		SW.Click("ABCDReports_ReportDashboard_LK");

		//Click on properties Report
		SW.Click("ABCDReportDashboard_Properties_LK");
		//selecting Division
		SW.Click("ABCDReportFilter_Division_ST");
		SW.Click("ABCDReportFilter_DivisionClick_ST");
		SW.Click("ABCDReportFilter_PDFReport_ST");
		SW.Wait(5);
		SW.GetScreenshot("Property Report");
		//SW.NavigateTo(URL);

	}
	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}

}