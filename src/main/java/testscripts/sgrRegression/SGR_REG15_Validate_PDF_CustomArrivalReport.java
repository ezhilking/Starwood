package testscripts.sgrRegression;
/* Purpose		: Validate the Validate the Custom Arrival Report_PDF format
 * TestCase Name: This is to validate the PDF version of the Custom arrival report and to make sure that the data in the web version and the PDF version matches
 * Created By	: Sachin G
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 * Prerequisite : We are assuming that '1965' property id has some guest in MAR.So not creating any reservation in branded web.
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.FileUtil;
import functions.Reporter;
import functions.CRM;

public class SGR_REG15_Validate_PDF_CustomArrivalReport {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	String GuestName, GuestSPGNumber, GuestPMSNumber;
	@Test(priority=0)
	public void generateCustomArrivalReport(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRMAR_CustomArrivalReport_LK");
		SW.CheckBox("SGRMAR_CustomArrivalInHouse_CB", "ON");
		SW.Click("SGRArrivalReport_GenerateReport_BN");		
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		if(!SW.ObjectExists("SGRCAR_FirstGuestName_LK")){
			Environment.loger.log(Level.ERROR, "There are no guests in the report to validate");
			SW.FailCurrentTest("There are no guests in the report to validate");
		}
		GuestName=SW.GetText("SGRCAR_FirstGuestName_LK");
		Environment.loger.log(Level.INFO, "In House Guest Custom Arrival Report First Guest Name : " +GuestName);

		GuestSPGNumber=SW.GetText("SGRCAR_FirstGuestSPGNumber_DT");
		Environment.loger.log(Level.INFO, "In House Guest Custom Arrival Report First Guest SPG Number : " +GuestSPGNumber);

		GuestPMSNumber=SW.GetText("SGRCAR_FirstGuestPMSnumber_DT");
		Environment.loger.log(Level.INFO, "In House Guest Custom Arrival Report First Guest PMS Number: " +GuestPMSNumber);
	}

	@Test(priority=1, dependsOnMethods="generateCustomArrivalReport")
	public void validateIN_PDF_ForAllGuest(){
		FileUtil PDF = SW.DownloadFile("Custom Arrival Report","Room Assignment_CustomArrival.pdf","In-House");
		PDF.ExtractText();
		
		PDF.ValidateText("Name Column", "Name");
		PDF.ValidateText("SPG # Column", "SPG#");
	    PDF.ValidateText("PMS Number column", "PMS Conf");
		PDF.ValidateText("Guest Name", GuestName);
		PDF.ValidateText("GuestSPGNumber", GuestSPGNumber);
		PDF.ValidateText("GuestPMSNumber", GuestPMSNumber);

	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
