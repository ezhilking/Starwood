package testscripts.Wechat;
/** Purpose		:	Notification Triggered when Points posted - Stays
 * TestCase Name:	REG14_PointsPosted_Stays.java	
 * Created By	:	Sharmila Begam Hameed
 * Modified By	: 	
 * Modified Date: 
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
import functions.Reporter;

public class REG14_PointsPosted_Stays {
	CRM SW = new CRM();	
	String sUsername,sPassword,sGripUsrName,sGripPassword, sAppname;
	String spgMem;//use A level SPG Member
	String sStartDate="2016-03-05";//Change the arrival date
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGCORONAURL);
		spgMem=SW.TestData("SPGMEMBER");
		sAppname=SW.TestData("QA4_CoronaAppName");
		sUsername=SW.TestData("QA4_CoronaUsername");
		sPassword=SW.TestData("QA4_CoronaPassword");
		sGripUsrName=SW.TestData("QA4_GripUName");
		sGripPassword=SW.TestData("QA4_GripPWD");
	}
	
	@Test(priority=1)
	public void PointPostedStay()
	{
		SW.CoronaLogin(sUsername, sPassword, sAppname);
		SW.Click("CoronaAdmin_APIDoc_LK");
		SW.Click("CoronaAdmin_PostMissStay_LK");
		SW.EnterValue("CoronaLogin_ApplName_EB", sAppname);
		SW.EnterValue("CoronaPostStay_MenberNum_EB",spgMem);
		SW.EnterValue("CoronaPostStay_Arrivaldate_EB", sStartDate);
		SW.EnterValue("CoronaPostStay_DepatureDate_EB", SW.DateAddDays(sStartDate, "yyyy-MM-dd", 2, Calendar.DATE));
		SW.EnterValue("CoronaPostStay_TotalRevenue_EB", "300");
		SW.EnterValue("CoronaPostStay_RoomRev_EB","100");
		SW.EnterValue("CoronaPostStay_FoodRev_EB", "100");
		SW.EnterValue("CoronaPostStay_OtherRev_EB", "100");
		SW.EnterValue("CoronaPostStay_LOS_EB", "2");
		SW.Click("CoronaPostStay_PostMiss_BT");
		String pageSource = SW.GetPageSource();
		String sAns = pageSource.substring(pageSource.indexOf("ApplicationReturnCode=")+23,pageSource.indexOf("ApplicationReturnCode=")+24).trim();
		System.out.println(sAns);
		if(SW.CompareTextContained("0", sAns))
		{
			Environment.loger.log(Level.INFO,"Application Return code =0 has dispalyed Successfully");
		}
		else
		{
			Environment.loger.log(Level.ERROR,"Failed in posting the nights ");
			SW.FailCurrentTest("Failed in posting the nights");
		}
		
	}
	@Test(priority=2, dependsOnMethods="PointPostedStay")
	public void dbValidtion()
	{
		try{	

			SW.Wait(10);//for reflect into DB
			SW.EstablishConnection("qa4");
			String CheckQuery="select * from odsft.freq_travel_notf_hist where mbrshp_num in ("+spgMem+") and message_type_id in ('891','894')";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Notification History table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from freq_travel_call_comm where call_comm_Cd='WCT' and mbrshp_num in ("+spgMem+")and call_comm_text in ('ACCOUNT UPGRADED OR RENEWED.','STARPOINTS POSTED FOR A STAY.')";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Freq_travel_call_comm  table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from AQADM.SPGPUSHNOTF_QUEUE_TABLE order by enq_time desc";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the table Before Batch job");
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
			SW.FailCurrentTest("Failed during db Validation");
		}		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
