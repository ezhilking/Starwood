package testscripts.Wechat;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class DataPower {
	CRM SW = new CRM();	
	String sResponse,sRequest,sOpenid;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser("http://phxcrsqmq61.nssd.star:9156/datapower/tranlog.html");
		sOpenid="oflwNxEqmFniWdMp2qYaAUfQJPU0";//change the open id which u r using
	}
	@Test(priority=1)
	public void datapowerStatusCheck()
	{
		SW.DropDown_SelectByText("DataPower_Engine_DD", "thirdParty");
		SW.DropDown_SelectByText("DataPower_Service_DD", "Get Services");
		SW.Wait(2);
		SW.DropDown_SelectByText("DataPower_Service_DD", "VeryStar");
		SW.DropDown_SelectByText("DataPower_Operation_DD", "Get Operations");
		SW.Wait(2);
		SW.DropDown_SelectByText("DataPower_Operation_DD", "pushNotification");
		SW.DropDown_SelectByText("DataPower_Date_DD",SW.GetTimeStamp("MMddYYYY"));// pass the date in MMDDYYYY format like "09232016"
		SW.EnterValue("DataPower_StartTine_EB","0000");//Start time 
		SW.EnterValue("DataPower_EndTime_EB", SW.GetTimeStamp("HHmm"));//End Time
		SW.Click("DataPower_Transaction_BT");
		if(SW.ObjectExists("DataPowerTrans_Transaction_LK"))
		{
			SW.Click("DataPowerTrans_Transaction_LK");
			SW.Click("DataPowerTrans_Request_LK");
			sRequest=SW.GetText("DataPowerTrans_RequestResponse_DT");
			if(SW.CompareTextContained(sOpenid, sRequest))
			{
				Environment.loger.log(Level.INFO,"Request Transaction is "+sRequest);
				Environment.loger.log(Level.INFO," The Open id in Requested Transaction is "+sOpenid);
			}
			SW.Click("DataPowerTrans_TransTab_LK");
			SW.Click("DataPowerTrans_Response_LK");
			sResponse=SW.GetText("DataPowerTrans_RequestResponse_DT");
			if(SW.CompareTextContained("Response Status", "200", sResponse))
				Environment.loger.log(Level.INFO,"Notificataion has triggered Successfull and Response is 200");
			else
			{
				Environment.loger.log(Level.ERROR,"Response Status not matched ");
				SW.FailCurrentTest("Failed in Matching Response");
			}
		}
		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
