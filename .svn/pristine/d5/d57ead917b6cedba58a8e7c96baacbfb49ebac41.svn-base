package testscripts.Wechat;

import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		:	Points deducted / awards - Merchandise
 * TestCase Name:	REG06_Merchandise.java
 * Created By	:	Sharmila Begam Hameed
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
public class REG06_Merchandise {
	
	CRM SW = new CRM();	
	String sOneID;
	String sUsername,sPassword,sGripUsrName,sGripPasWCord;
	String ConfirmationNumber;
	WebElement ActionObject=null;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowserWeChat();
		sOneID=SW.TestData("QA4_WechatOpenid");
		sUsername=SW.TestData("QA4_WechatUsername");
		sPassword=SW.TestData("QA4_WechatPassword");
		sGripUsrName=SW.TestData("QA4_GripUName");
		sGripPasWCord=SW.TestData("QA4_GripPWD");
	}
	@Test(priority=1)
	public void addingCharity()
	{
		if(SW.ObjectExists("WCPost_OneId_EB"))
		{
			SW.EnterValue("WCPost_OneId_EB", sOneID);
			SW.Click("WCPost_Continue_BT");
			Environment.loger.log(Level.INFO,"The WECHAT Application has opened Successfully");
		}
		else
			Environment.loger.log(Level.ERROR,"Wechat Application has not opened");	
		SW.WeChatLogin(sUsername, sPassword);
			SW.Click("WCHome_FullSite_LK");
			SW.DoubleClick("WCHome_BookandRedeem_LK");
			SW.Click("WCHome_Redeem_LK");
			SW.Click("WCHome_Moreoption_LK");
			SW.Click("WCRedeem_Merchant_LK");
			SW.Click("WCRedeem_Giftcard_LK");
			SW.SwitchToFrame("WCSPG_DonateNow_FR");
			SW.Wait(10);
			SW.WaitTillElementToBeClickable("WCRedeem_Points_RB");
			SW.SelectRadioButton("WCRedeem_Points_RB");
			SW.CheckBox("WCRedeem_TNC_CB","ON");
			SW.Click("WCRedeem_Submit_BT");
			SW.Click("WCRedeem_Complete_BT");
			SW.WaitTillElementToBeClickable("WCRedeem_Transfer_DT");
		if(SW.ObjectExists("WCRedeem_Transfer_DT"))
		{
			String sConfrim=SW.GetText("WCRedeem_Transfer_DT");
			if(SW.CompareTextContained("YOU HAVE TRANSFERRED",sConfrim ))
			{
				Environment.loger.log(Level.INFO,"Charity points are transfered successfully");
			}
			else
			{
				Environment.loger.log(Level.ERROR,"Charity points are not transfered ");
				SW.FailCurrentTest("Charity transfered failed");
			}
		}
		else{
			Environment.loger.log(Level.ERROR,"Charity points are not transfered ");
			SW.FailCurrentTest("Charity transfered failed");
		}
		
	}
	@Test(priority=2, dependsOnMethods="addingCharity")
	public void dbValidtion()
	{
		try{	

			SW.Wait(100);//for reflect into DB
			SW.EstablishConnection("qa4");
			String CheckQuery="select * from odsft.freq_travel_notf_hist where mbrshp_num in ("+sUsername+") and message_type_id='896'";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Notification History table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from freq_travel_call_comm where call_comm_Cd='WCT' and mbrshp_num in ("+sUsername+")and call_comm_text='AWARDS AMAZON $100 GIFT CERT ISSUED.'";
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
