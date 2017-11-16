package testscripts.gcRegression;
/** Purpose		: Validate Adhoc Bounce Back and Audience Sizing report in OMT
 * TestCase Name: Validate Adhoc Bounce Back and Audience Sizing report in OMT
 * Created By	: Sharanya Bannuru
 * Modified By	: Sindhu SR
 * Modified Date: 5/19/2017
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

public class GC_REG19_ValidateAdhocBounceBackAndAudienceSizingReport {

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
			if(SW.ObjectExists("GCHome_Message_Close_IC")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCReporting_LK");
			SW.Click("GCResReport_AhbbReport_LK");
			SW.EnterValue("GCResReport_StartDate_EB", SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", -1, Calendar.DATE));
			
			SW.EnterValue("GCResReport_EndDate_EB",SW.GetTimeStamp("MM/dd/yyyy"));
			SW.Click("GCResReport_RunReport_BT");
			
			FileUtil ExternalFile=SW.DownloadFile("GCAhbbRelated","AhbbRelatedReport.xlsx","");
			ExcelUtil Excel = ExternalFile.OpenExcel();
			SW.CompareText("ValidateColumnHeader1", "Property ID", Excel.GetData(1, 1));
			SW.CompareText("ValidateColumnHeader2", "AHBB ID", Excel.GetData(1, 2));
			SW.CompareText("ValidateColumnHeader3", "Offer ID", Excel.GetData(1, 3));
			SW.CompareText("ValidateColumnHeader4", "Offer Placement Type", Excel.GetData(1, 4));
			SW.CompareText("ValidateColumnHeader5", "Template ID", Excel.GetData(1, 5));
			SW.CompareText("ValidateColumnHeader6", "Version", Excel.GetData(1, 6));
			SW.CompareText("ValidateColumnHeader7", "Version ID", Excel.GetData(1, 7));
			SW.CompareText("ValidateColumnHeader8", "Tracking Code", Excel.GetData(1, 8));
			SW.CompareText("ValidateColumnHeader9", "Code", Excel.GetData(1, 9));
			SW.CompareText("ValidateColumnHeader10", "Internal Offer Name", Excel.GetData(1, 10));
			SW.CompareText("ValidateColumnHeader11", "Email Subject Line", Excel.GetData(1, 11));
			SW.CompareText("ValidateColumnHeader12", "Offer Title", Excel.GetData(1, 12));
			SW.CompareText("ValidateColumnHeader13", "Omniture Tag", Excel.GetData(1, 13));
			SW.CompareText("ValidateColumnHeader14", "Offer Author", Excel.GetData(1, 14));
			SW.CompareText("ValidateColumnHeader15", "Offer Objective", Excel.GetData(1, 15));
			SW.CompareText("ValidateColumnHeader16", "Other Offer Objective", Excel.GetData(1, 16));
			SW.CompareText("ValidateColumnHeader17", "Offer Incentive", Excel.GetData(1, 17));
			SW.CompareText("ValidateColumnHeader18", "Language", Excel.GetData(1, 18));
			SW.CompareText("ValidateColumnHeader19", "Eligibility Criteria", Excel.GetData(1, 19));
			SW.CompareText("ValidateColumnHeader20", "Status", Excel.GetData(1, 20));
			SW.CompareText("ValidateColumnHeader21", "Requested Mail Start Date", Excel.GetData(1, 21));
			SW.CompareText("ValidateColumnHeader22", "Requested Mail End Date", Excel.GetData(1, 22));
			SW.CompareText("ValidateColumnHeader23", "Sent Date", Excel.GetData(1, 23));
			SW.CompareText("ValidateColumnHeader24", "Total Messages Sent", Excel.GetData(1, 24));
			SW.CompareText("ValidateColumnHeader25", "Total Bounced", Excel.GetData(1, 25));
			SW.CompareText("ValidateColumnHeader26", "Bounce Rate", Excel.GetData(1, 26));
			SW.CompareText("ValidateColumnHeader27", "Total Unique Message Clicks", Excel.GetData(1, 27));
			SW.CompareText("ValidateColumnHeader28", "Click-Through Rate", Excel.GetData(1, 28));
			SW.CompareText("ValidateColumnHeader29", "Total Unique Offer Clicks", Excel.GetData(1, 29));
			SW.CompareText("ValidateColumnHeader30", "Opt Outs", Excel.GetData(1, 30));
			SW.CompareText("ValidateColumnHeader31", "Opt Out Rate", Excel.GetData(1, 31));
			SW.CompareText("ValidateColumnHeader32", "Total Unique Opens", Excel.GetData(1, 32));
			SW.CompareText("ValidateColumnHeader33", "Open Rate", Excel.GetData(1, 33));
			SW.CompareText("ValidateColumnHeader34", "Click-Through Rate based on Opens", Excel.GetData(1, 34));
			SW.CompareText("ValidateColumnHeader35", "Total Communication preference Clicks", Excel.GetData(1, 35));
			
									
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured - ",e);
		}
	}	
	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}

}
