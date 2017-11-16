package testscripts.gcRegression;
/** Purpose		:Validate the newly created broadcast message is displayed when user is logged into the application
 * TestCase Name: Validate that in Chinese OMT the newly created broadcast message are displayed
 * Created By	: Sharanya
 * Modified By	: Sachin	
 * Modified Date: 6/20/2016
 * Reviewed By	: 	
 * Reviewed Date: 
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG02_ValidatethatinChineseOMTthenewlycreatedbroadcastmessagearedisplayed {

	CRM SW = new CRM();	
	
	String sMessage,UserName,Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	@Test(priority=1)
	public void GCValidateBroadCasteMsg(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCHome_Admin_LK");
			SW.Click("GCHome_BroadCastMsg_LK");
			SW.Click("GCActvBroadCastMsg_LK");
			Environment.loger.log(Level.INFO,"Checking for Existing Active Broadcaste Messages and deleting");

			if(SW.ObjectExists("GCActBroadCastMsg_GridTable_TB")){
				int ActiveMsgCount = SW.WebTbl_GetRowCount("GCActBroadCastMsg_GridTable_TB");
				Environment.loger.log(Level.INFO,"Row count:"+ActiveMsgCount);		
				while(SW.ObjectExists("GCActBroadCastMsg_GridTableDelete_BN")){
					SW.ClickAndProceed("GCActBroadCastMsg_GridTableDelete_BN");
					SW.HandleAlert(true);
					SW.WaitForPageload();
					SW.Click("GCActvBroadCastMsg_LK");
				}
			}else{
				Environment.loger.log(Level.INFO,"No Active Broadcaste Messages present");
			}

			SW.Click("GCCreateBroadCastMsg_LK");
			Environment.loger.log(Level.INFO,"Creating new Broadcaste Messages");
			SW.DropDown_SelectByIndex("//*[@id='langcd']", 2);			//Modified, change language from english to chinese
			SW.Wait(5);
			SW.EnterValue("GCCreateBroadCastMsg_StartDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateBroadCastMsg_EndDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));

			String sSubject = SW.RandomString(10);
			sMessage = SW.RandomString(15);

			SW.EnterValue("GCCreateBroadCastMsg_Headline_EB",sSubject);
			SW.SwitchToFrame("GCCreateBroadCastMsg_Message_FR");
			SW.Click("GCCreateBroadCastMsg_Message_EB");
			SW.EnterValue("GCCreateBroadCastMsg_Message_EB",sMessage);
			SW.SwitchToFrame("");
			SW.NormalClick("GCCreateBroadCastMsg_Publish_BN");

			if(SW.ObjectExists("//div[@id='errorMessagesGreen']")){
				Environment.loger.log(Level.INFO,"Message Validation is successful");
				SW.NormalClick("GC_MyAccount_IC");
				SW.WaitTillElementToBeClickable("GC_MyAccount_SignOut_LK");
				if(SW.ObjectExists("GC_MyAccount_SignOut_LK")){
					SW.Click("GC_MyAccount_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Message Validation is Failed");	
				SW.FailCurrentTest("Message Validation is Failed");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured - ",e);
		}
	}
	
	//This method executes when message creation is success to remove them at the end of the script
	@Test(priority=2,dependsOnMethods={"GCValidateBroadCasteMsg"})
	public void GCBroadCasteMsgDeletion(){		
		SW.LaunchBrowser(Environment.GCURL);
		//SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		
		SW.EnterValue("GCLogin_Username_EB", UserName);
		SW.EnterValue("GCLogin_Password_EB", Password);
		SW.DropDown_SelectByIndex("//select[@name='language']", 1);
		SW.WaitForPageload();
		SW.Click("GCLogin_Login_BN");
		if(SW.ObjectExists("//div[@id='message1']")){
			Environment.loger.log(Level.INFO,"Previous message exists");
			String sMessagetext = SW.GetText("//div[@id='message1']//div");
			SW.CompareText("Message Validation",sMessage, sMessagetext);
			SW.Wait(5);
			SW.NormalClick(".//*[@id='message1']//img");  // changing from GC_CloseButtonInChineseBroadCastWindow_BT to .//*[@id='message1']//img
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.DoubleClick(".//*[@id='message1']//img");
			}
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCHome_BroadCastMsg_LK");
		SW.Click("GCActvBroadCastMsg_LK");
		Environment.loger.log(Level.INFO,"Checking for Existing Active Broadcaste Messages and deleting");

		if(SW.ObjectExists("GCActBroadCastMsg_GridTable_TB")){//
			int ActiveMsgCount = SW.WebTbl_GetRowCount("GCActBroadCastMsg_GridTable_TB");
			Environment.loger.log(Level.INFO,"Row count:"+ActiveMsgCount);		
			while(SW.ObjectExists("GCActBroadCastMsg_GridTableDelete_BN")){
				SW.ClickAndProceed("GCActBroadCastMsg_GridTableDelete_BN");
				SW.HandleAlert(true);
				SW.WaitForPageload();
				SW.Click("GCActvBroadCastMsg_LK");
			}
		}else{
			Environment.loger.log(Level.ERROR,"No Active Broadcaste Messages present");
		}

	}
	@AfterClass
	public void EndTest(){
		SW.NormalClick("GC_MyAccount_IC");
		SW.WaitTillElementToBeClickable("GC_MyAccount_SignOut_LK");
		if(SW.ObjectExists("GC_MyAccount_SignOut_LK")){
			SW.Click("GC_MyAccount_SignOut_LK");
		}
		Reporter.StopTest();		
	}

}
