package testscripts.Wechat;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		:	Notification Triggered when Points posted - Stays
 * TestCase Name:	REG15_SPGFlightsNotification.java
 * Created By	:	Sharmila Begam Hameed
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
public class REG15_SPGFlightsNotification {
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
		sAppname="SPGWEB";
		sUsername=SW.TestData("QA4_CoronaUsername");
		sPassword="1_@m_pr3tty";
		sGripUsrName=SW.TestData("QA4_GripUName");
		sGripPassword=SW.TestData("QA4_GripPWD");
	}
	@Test(priority=1)
	public void PointPostedStay()
	{
		SW.CoronaLogin(sUsername, sPassword, sAppname);
		SW.Click("CoronaAdmin_APIDoc_LK");
		SW.Click("CoronaAdmin_CreateAirlineAward_LK");
		SW.EnterValue("CoronaLogin_ApplName_EB", sAppname);
		SW.EnterValue("CoronaPostStay_MenberNum_EB", spgMem);
		SW.Click("CoronaCreateAirlineAwd_CreateAirlineAwd_BT");
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
			String CheckQuery="select * from odsft.freq_travel_notf_hist where mbrshp_num in ("+spgMem+") and message_type_id = '896'";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Notification History table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from freq_travel_txn where mbrshp_num in("+spgMem+") and tot_points='-30000' and POST_TMSTMP>=sysdate-1 ";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Points are Reduced in SPG Member");
			else{
				Environment.loger.log(Level.ERROR,"Points Does not Transferred");
				SW.FailCurrentTest("Points Does not Transferred");
			}
			CheckQuery="select * from freq_travel_call_comm where call_comm_Cd='WCT' and mbrshp_num in ("+spgMem+") and call_comm_text = 'AWARDS SPG FLIGHTS AWARD ISSUED.'";
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
