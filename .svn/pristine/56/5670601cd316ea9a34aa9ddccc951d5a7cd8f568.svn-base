package testscripts.Wechat;

import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		:	Points deducted / awards - Charity
 * TestCase Name:	REG05_Charity.java
 * Created By	: 	Sharmila Begam Hameed
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
public class REG05_Charity {
	CRM WC = new CRM();	
	String sOneID;
	String sUsername,sPassword,sGripUsrName,sGripPassword;
	String ConfirmationNumber;
	WebElement ActionObject=null;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		WC.LaunchBrowserWeChat();
		sOneID=WC.TestData("QA4_WechatOpenid");
		sUsername=WC.TestData("QA4_WechatUsername");
		sPassword=WC.TestData("QA4_WechatPassword");
		sGripUsrName=WC.TestData("QA4_GripUName");
		sGripPassword=WC.TestData("QA4_GripPWD");
	}
	@Test(priority=1)
	public void addingCharity()
	{
		if(WC.ObjectExists("WCPost_OneId_EB"))
		{
			WC.EnterValue("WCPost_OneId_EB", sOneID);
			WC.Click("WCPost_Continue_BT");
			Environment.loger.log(Level.INFO,"The WECHAT Application has opened Successfully");
		}
		else
			Environment.loger.log(Level.ERROR,"Wechat Application has not opened");	
			WC.WeChatLogin(sUsername, sPassword);
			WC.Click("WCHome_FullSite_LK");
			WC.DoubleClick("WCHome_BookandRedeem_LK");
			WC.Click("WCHome_Redeem_LK");
			WC.Click("WCHome_Moreoption_LK");
			WC.Click("WCRedeem_Charity_LK");
			WC.Click("WCRedeem_Donate_LK");
			WC.SwitchToFrame("WCSPG_DonateNow_FR");
			WC.Wait(10);
			WC.WaitTillElementToBeClickable("WCRedeem_Points_RB");
			WC.SelectRadioButton("WCRedeem_Points_RB");
			WC.CheckBox("WCRedeem_TNC_CB","ON");
			WC.Click("WCRedeem_Submit_BT");
			WC.Click("WCRedeem_Complete_BT");
		if(WC.ObjectExists("WCRedeem_Transfer_DT"))
		{
			String sConfrim=WC.GetText("WCRedeem_Transfer_DT");
			if(WC.CompareTextContained("YOU HAVE TRANSFERRED",sConfrim ))
			{
				Environment.loger.log(Level.INFO,"Charity points are transfered successfully");
			}
			else
			{
				Environment.loger.log(Level.ERROR,"Charity points are not transfered ");
				WC.FailCurrentTest("Charity transfered failed");
			}
		}
		else{
			Environment.loger.log(Level.ERROR,"Charity points are not transfered ");
			WC.FailCurrentTest("Charity transfered failed");
		}
		
	}
	@Test(priority=2)
	public void dbValidtion()
	{
		try{	

			WC.Wait(100);//for reflect into DB
			WC.EstablishConnection("qa4");
			String CheckQuery="select * from odsft.freq_travel_notf_hist where mbrshp_num in ("+sUsername+") and message_type_id='896'";
			if(WC.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Notification History table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				WC.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from freq_travel_call_comm where call_comm_Cd='WCT' and mbrshp_num in ("+sUsername+")and call_comm_text='AWARDS RED CROSS ISSUED.'";
			if(WC.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Freq_travel_call_comm  table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				WC.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from AQADM.SPGPUSHNOTF_QUEUE_TABLE order by enq_time desc";
			if(WC.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the table Before Batch job");
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
			WC.FailCurrentTest("Failed during db Validation");
		}		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
