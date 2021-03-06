package testscripts.gcRegression;
/** Purpose		: Verify Reservations Related Offers Report
 * TestCase Name: Verify Reservations Related Offers Report
 * Created By	: Sachin
 * Modified By	: Sachin
 * Modified Date: 6/22/2016
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.ExcelUtil;
import functions.FileUtil;
import functions.Reporter;

public class GC_REG18_ValidateReservationReport {

	CRM SW = new CRM();	
	String sMessage;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
	}
	@Test(priority=1)
	public void GCValidateBroadCasteMsg(){
		try{
			SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCReporting_LK");
			SW.Click("GCReservationRelatedReport_LK");
			SW.EnterValue("GCResReport_StartDate_EB", SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", -1, Calendar.DATE));
			SW.EnterValue("GCResReport_EndDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
			SW.CheckBox("GCResReport_Owner_CB", "ON");
			SW.Click("GCResReport_RunReport_BT");
			
			//FileUtil ExternalFile=SW.DownloadFile("GCReservarionRelated","ResrvationRelatedReport.xlsx","");
			FileUtil ExternalFile = SW.DownloadFile("GCReservarionRelated","A.xlsx","");
			ExcelUtil Excel = ExternalFile.OpenExcel();
			//Excel validation starts here - Validate all column headers in the report 
			SW.CompareText("ValidateColumnHeader1", "Property ID", Excel.GetData(1, 1));
			SW.CompareText("ValidateColumnHeader2", "Offer ID", Excel.GetData(1, 2));
			SW.CompareText("ValidateColumnHeader3", "Version", Excel.GetData(1, 3));
			SW.CompareText("ValidateColumnHeader4", "Version ID", Excel.GetData(1, 4));
			SW.CompareText("ValidateColumnHeader5", "Tracking Code", Excel.GetData(1, 5));
			SW.CompareText("ValidateColumnHeader6", "Code", Excel.GetData(1, 6));
			SW.CompareText("ValidateColumnHeader7", "Internal Offer Name", Excel.GetData(1, 7));
			SW.CompareText("ValidateColumnHeader8", "Offer Title", Excel.GetData(1, 8));
			SW.CompareText("ValidateColumnHeader9", "Omniture Tag", Excel.GetData(1, 9));
			SW.CompareText("ValidateColumnHeader10", "Offer Author", Excel.GetData(1, 10));
			SW.CompareText("ValidateColumnHeader11", "Message Type", Excel.GetData(1, 11));
			SW.CompareText("ValidateColumnHeader12", "Offer Objective", Excel.GetData(1, 12));
			SW.CompareText("ValidateColumnHeader13", "Other Offer Objective", Excel.GetData(1, 13));
			SW.CompareText("ValidateColumnHeader14", "Offer Incentive", Excel.GetData(1, 14));
			SW.CompareText("ValidateColumnHeader15", "Language", Excel.GetData(1, 15));
			SW.CompareText("ValidateColumnHeader16", "Eligibility Criteria", Excel.GetData(1, 16));
			SW.CompareText("ValidateColumnHeader17", "Display Start    Date", Excel.GetData(1, 17));
			SW.CompareText("ValidateColumnHeader18", "Display End Date", Excel.GetData(1, 18));
			SW.CompareText("ValidateColumnHeader19", "Status", Excel.GetData(1, 19));
			SW.CompareText("ValidateColumnHeader20", "Presentation Start Date", Excel.GetData(1, 20));
			SW.CompareText("ValidateColumnHeader21", "Presentation End Date", Excel.GetData(1, 21));
			SW.CompareText("ValidateColumnHeader22", "Total Messages Sent", Excel.GetData(1, 22));
			SW.CompareText("ValidateColumnHeader23", "Total Bounced", Excel.GetData(1, 23));
			SW.CompareText("ValidateColumnHeader24", "Bounce Rate", Excel.GetData(1, 24));
			SW.CompareText("ValidateColumnHeader25", "Total Unique Clicks", Excel.GetData(1, 25));
			SW.CompareText("ValidateColumnHeader26", "Click-Through Rate", Excel.GetData(1, 26));
			SW.CompareText("ValidateColumnHeader27", "Opt Outs", Excel.GetData(1, 27));
			SW.CompareText("ValidateColumnHeader28", "Opt Out Rate", Excel.GetData(1, 28));
			SW.CompareText("ValidateColumnHeader29", "Total Unique Opens", Excel.GetData(1, 29));
			
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "asdfsafsad",e);
		}
	}	

	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GC_MyAccount_IC")){
			SW.NormalClick("GC_MyAccount_IC");
			SW.WaitTillElementToBeClickable("GC_MyAccount_SignOut_LK");
			if(SW.ObjectExists("GC_MyAccount_SignOut_LK")){
				SW.Click("GC_MyAccount_SignOut_LK");
			}
		}
		Reporter.StopTest();		
	}

}
