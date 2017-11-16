package testscripts.sgrRegression;
/* Purpose		: Validate the Master Arrival Report_PDF format_All Guests and In-House
 * TestCase Name: This is to validate the PDF version of the Master arrival report for the guests in Room assignment tab. 
 * This is to make sure that the data in the web version and the PDF version matches
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

public class SGR_REG14_Validate_PDF_InhouseAndALLGuests {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	String GuestName, GuestSPGNumber, GuestPMSNumber, GuestStatusDetails;
	@Test(priority=0)
	public void generateMAR_AllGuest(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1047");
		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRArrivalReport_GenerateReport_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.DropDown_SelectByValue("SGRMAR_FilterByStatus_DD", "ALL");
		SW.Click("SGRMAR_Refresh_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		if(!SW.ObjectExists("SGRMAR_FirstGuestName_LK")){
			Environment.loger.log(Level.ERROR, "There are no guests in the report to validate");
			SW.FailCurrentTest("There are no guests in the report to validate");
		}
		GuestName=SW.GetText("SGRMAR_FirstGuestName_LK");
		GuestName=GuestName.trim();
		Environment.loger.log(Level.INFO, "All Guests MAR First Guest Name : " +GuestName);

		GuestSPGNumber=SW.GetText("SGRMAR_FirstGuestSPGNumber_DT");
		GuestSPGNumber=GuestSPGNumber.substring(GuestSPGNumber.indexOf("SPG:")+4, GuestSPGNumber.indexOf("Lifetime")).trim();
		Environment.loger.log(Level.INFO, "All Guests MAR First Guest SPG Number : " +GuestSPGNumber);


		GuestPMSNumber=SW.GetText("SGRMAR_FirstGuestPMSNumber_DT");
		GuestPMSNumber=GuestPMSNumber.substring(GuestPMSNumber.indexOf("Conf#")+6).trim();
		Environment.loger.log(Level.INFO, "All Guests MAR First Guest PMS Number: " +GuestPMSNumber);

		// IF guest status is Arriving PMS number will not be there hence there will be only two spans available which will contain other details 
		if(!SW.ObjectExists("SGRMAR_FirstGuestOtherDetails_DT")){

			GuestStatusDetails=SW.GetText("(//table[@id='report6']//td[@aria-describedby='report6_guestName'])[1]//span[2]");
			GuestStatusDetails=GuestStatusDetails.substring(GuestStatusDetails.lastIndexOf(" ")+1).trim();
			Environment.loger.log(Level.INFO, "All Guests MAR First Guest Status Detals : " +GuestStatusDetails);

		}else{
			GuestStatusDetails=SW.GetText("SGRMAR_FirstGuestOtherDetails_DT");
			GuestStatusDetails=GuestStatusDetails.substring(GuestStatusDetails.lastIndexOf(" ")+1).trim();
			Environment.loger.log(Level.INFO, "All Guests MAR First Guest Status Detals : " +GuestStatusDetails);
		}

	}

	@Test(priority=1, dependsOnMethods="generateMAR_AllGuest")
	public void validateIN_PDF_ForAllGuest(){
		FileUtil PDF = SW.DownloadFile("Room Assignment","Room Assignment_All.pdf","All Guests");
		PDF.ExtractText();
		PDF.ValidateText("U Rank Column", "U.Rank");
		PDF.ValidateText("GuestDetails column", "Guest Details");
		PDF.ValidateText("SPG column", "SPG Stay Confirmed");
		PDF.ValidateText("Brand column", "Brand Stay Confirmed");
		PDF.ValidateText("GuestURank", "1");
		PDF.ValidateText("Guest Name", GuestName);
		PDF.ValidateText("GuestSPGNumber", GuestSPGNumber);
		if(SW.ObjectExists("SGRMAR_FirstGuestOtherDetails_DT")){
			
			PDF.ValidateText("GuestPMSNumber", GuestPMSNumber);
		}
		PDF.ValidateText("GuestStatus", GuestStatusDetails);
	}

	@Test(priority=2)
	public void generateMAR_InHouse(){

		SW.Click("SGRNavigation_Reports_LK");
		SW.Click("SGRNavigation_ArrivalReport_LK");
		SW.Click("SGRArrivalReport_GenerateReport_BN"); 
		SW.DropDown_SelectByValue("SGRMAR_FilterByStatus_DD", "INH");
		SW.Click("SGRMAR_Refresh_BN");
		SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
		SW.Wait(5);
		if(!SW.ObjectExists("SGRMAR_FirstGuestName_LK")){
			Environment.loger.log(Level.ERROR, "There are no guests in the report to validate");
			SW.FailCurrentTest("There are no guests in the report to validate");
		}
		GuestName=SW.GetText("SGRMAR_FirstGuestName_LK");
		GuestName=GuestName.trim();
		Environment.loger.log(Level.INFO, "In-House First Guest Name : " +GuestName);

		GuestSPGNumber=SW.GetText("SGRMAR_FirstGuestSPGNumber_DT");
		GuestSPGNumber=GuestSPGNumber.substring(GuestSPGNumber.indexOf("SPG:")+4, GuestSPGNumber.indexOf("Lifetime")).trim();
		Environment.loger.log(Level.INFO, "In-House First Guest SPG Number : " +GuestSPGNumber);

		GuestPMSNumber=SW.GetText("SGRMAR_FirstGuestPMSNumber_DT");
		GuestPMSNumber=GuestPMSNumber.substring(GuestPMSNumber.indexOf("Conf#")+6).trim();
		Environment.loger.log(Level.INFO, " In-House First Guest PMS Number : " +GuestPMSNumber);

		GuestStatusDetails=SW.GetText("SGRMAR_FirstGuestOtherDetails_DT");
		GuestStatusDetails=GuestStatusDetails.substring(GuestStatusDetails.lastIndexOf(" ")+1).trim();
		Environment.loger.log(Level.INFO, " In-House First Guest Status Detals: " +GuestStatusDetails);
	}

	@Test(priority=3, dependsOnMethods="generateMAR_InHouse")
	public void validateIN_PDF_ForInHouseGuest(){
		FileUtil PDF = SW.DownloadFile("Room Assignment","Room Assignment_InHouse.pdf","In-House");
		PDF.ExtractText();
		PDF.ValidateText("U Rank Column", "U.Rank");
		PDF.ValidateText("GuestDetails column", "Guest Details");
		PDF.ValidateText("SPG column", "SPG Stay Confirmed");
		PDF.ValidateText("Brand column", "Brand Stay Confirmed");
		PDF.ValidateText("GuestURank", "1");
		PDF.ValidateText("Guest Name", GuestName);
		PDF.ValidateText("GuestSPGNumber", GuestSPGNumber);
		PDF.ValidateText("GuestPMSNumber", GuestPMSNumber);
		PDF.ValidateText("GuestStatus", GuestStatusDetails);
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
