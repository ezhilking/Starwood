package testscripts.sgrRegression;
/* Purpose		: Download and validate the excel in MAR-Room assignment 
 * TestCase Name: Validate Export to Excel hyperlink_SPG Tab_Master Arrival report
 * Created By	: Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.ExcelUtil;
import functions.FileUtil;
import functions.Reporter;


public class SGR_REG13_ExcelValidationRoomAssignment {

	CRM SW = new CRM();
	FileUtil PDF= new FileUtil();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}

	@Test
	public void ExportExcelAndValidate(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
			SW.Click("SGRNavigation_Reports_LK");
			SW.Click("SGRNavigation_ArrivalReport_LK");
			String Date = SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", 4, Calendar.DATE);
			SW.RunJavaScript("document.getElementsByName('endDate3')[0].value='"+Date+"';");//Set the date
			SW.Click("SGRArrivalReport_GenerateReport_BN");
			SW.WaitTillInvisibilityOfElement("SGR_PleaseWaitLoadingIcon_IC");
			SW.Wait(20);
			String GuestName = SW.GetText("SGRMAR_FirstGuestName_LK");

			FileUtil ExternalFile = SW.DownloadFile("Room Assignment","RoomAssignment.xls","");
			ExcelUtil Excel = ExternalFile.OpenExcel();

			SW.CompareText("Title_ST", "Title: Master Arrivals Report - SPG Tab", Excel.GetData(1, 1));
			String GenerateDate  = Excel.GetData(2, 1);
			String[] DateSplit = GenerateDate.split(":", 2);

			SW.CompareText("DateGenerate_ST", "Date Generated", DateSplit[0]);
			String Time = DateSplit[1].trim().split(" ")[0];
			SW.CompareText("DateGenerate_ST", SW.GetTimeStamp("dd-MM-yyyy"),Time);
			SW.CompareText("FirstName_DT", "First Name", Excel.GetData(4, 1));
			SW.CompareText("LastName_DT", "Last Name", Excel.GetData(4, 2));
			SW.CompareText("Starpts_DT", "Starpts", Excel.GetData(4, 3));

			Pattern RemoveWords = Pattern.compile("DR |MISS |MR |MRS |MS ");
			Matcher matcher = RemoveWords.matcher(GuestName);
			String[] GuestFName = matcher.replaceAll("").trim().split(" ");

			SW.CompareText("GusetFirstName_DT", GuestFName[0], Excel.GetData(5, 1));			
			SW.CompareText("GusetLastName_DT", GuestFName[GuestFName.length-1], Excel.GetData(5, 2));

		}catch(Exception e){
			Environment.loger.log(Level.INFO, "Exception:",e);
		}
	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}
