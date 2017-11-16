package testscripts.gcRegression;
/** Purpose		: Validate_MarkAsRead_BroadcastMessage
 * TestCase Name: GC_REG87_ValidateMarkAsReadBroadcastMessage
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: Sachin	
 * Reviewed Date: 13/04/2017
 */

import java.util.List;



import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG89_ValidateMarkAsReadBroadcastMessage {
	CRM SW = new CRM();
	String Username, Password, Headline, Message;
	List<String> sSuccessMessage;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	//Create broadcast message if no message exists
	@Test(priority=1)
	public void CreateBroadcastMessage(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			Environment.loger.log(Level.INFO,"'Broadcast and Internal Communications' message exists");
			SW.Wait(5);
			SW.DoubleClick("GCHome_Message_Close_IC");
			SW.Wait(5);
			if(SW.ObjectExists("GCHome_Message_Close_IC")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
			SW.DoubleClick("GC_MyAccount_IC");
			if(!SW.ObjectExists("GC_MyAccount_SignOut_LK")){
				SW.DoubleClick("GC_MyAccount_IC");
				SW.Click("GC_MyAccount_SignOut_LK");
			}
			SW.Click("GC_MyAccount_SignOut_LK");
		}else{
			Environment.loger.log(Level.INFO,"'Broadcast and Internal Communications' message does not exists, so creating new message");
			SW.Click("GCHome_Admin_LK");
			SW.Click("GCHome_BroadCastMsg_LK");
			SW.Wait(10);
			SW.EnterValue("GCCreateBroadCastMsg_StartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateBroadCastMsg_EndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			Headline=SW.RandomString(20);
			SW.EnterValue("GCCreateBroadCastMsg_Headline_EB", Headline);
			Environment.loger.log(Level.INFO,  "Entered 'Broadcast and Internal Communications' Headline");
			SW.Wait(10);
			SW.SwitchToFrame("GCCreateBroadCastMsg_Message_FR");
			SW.Click("GCCreateBroadCastMsg_Message_EB");
			Message=SW.RandomString(20);
			SW.EnterValue("GCCreateBroadCastMsg_Message_EB", Message);
			Environment.loger.log(Level.INFO,  "Entered 'Broadcast and Internal Communications' Message");
			SW.SwitchToFrame("");
			SW.Click("GCCreateBroadCastMsg_Publish_BN");
			SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
			String GreenText = SW.GetText("GCHome_GreenMsg_DT");
			
			if (SW.CompareTextContained("GC_Home_GreenMsg Validation", "The Communication Message Has Been Created Successfully", GreenText)){
				sSuccessMessage=SW.GetAllText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,sSuccessMessage.toString());
				SW.DoubleClick("GC_MyAccount_IC");
				SW.Click("GC_MyAccount_SignOut_LK");
			}else{
				Environment.loger.log(Level.ERROR, "'Broadcast and Internal Communications' Message has not been Published");
			}
		}
	}


	//Mark all the broadcast message as read 
	@Test(priority=2, dependsOnMethods="CreateBroadcastMessage")
	public void MarkAsReadBroadcastMessage(){
		SW.GCLogin(Username, Password);
		SW.Wait(5);
		SW.WaitTillPresenceOfElementLocated("GC_BroadcastMessageCount_ST");
		String MessagesCount=SW.GetText("GC_BroadcastMessageCount_ST");
		String MaxMsgsCount=MessagesCount.substring(MessagesCount.indexOf("of")+2, MessagesCount.length()).trim();
		int IntCount=Integer.parseInt(MaxMsgsCount);
		Environment.loger.log(Level.INFO, IntCount);
		//If only 1 broadcast message exists, then below loop executes only once
		if(IntCount==1){
			SW.IsEnabled("GC_NextButtonInBroadcastWindow_BT", "Disabled");
			SW.CheckBox("GC_SelectCheckBoxMarkAsRead_CB", "ON");
			SW.DoubleClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_Close_IC")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
			Environment.loger.log(Level.INFO,"Marked the message as read and closed the 'Broadcast and Internal Communications' window");
			SW.DoubleClick("GC_MyAccount_IC");
			SW.Click("GC_MyAccount_SignOut_LK");
		}else{
			//If multiple broadcast messages exists, then below loop executes based on integer value 
			for(int i=1; i<=IntCount; i++){
				SW.CheckBox("//div[@id='message"+i+"']//input[@type='checkbox']", "ON");
				if(SW.ObjectExists("GC_NextButtonBroadcastWindow_BT"))
					SW.NormalClick("GC_NextButtonBroadcastWindow_BT");
			}
			SW.Wait(5);
			SW.DoubleClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_Close_IC")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
			Environment.loger.log(Level.INFO,"All messages are marked as read and closed the 'Broadcast and Internal Communications' window");
			SW.DoubleClick("GC_MyAccount_IC");
			if(!SW.ObjectExists("GC_MyAccount_SignOut_LK")){
				SW.DoubleClick("GC_MyAccount_IC");
			}
			SW.Click("GC_MyAccount_SignOut_LK");
		}

	}

	//validate once all message are marked a read, again when same user logs in broadcast window should not be displayed
	@Test(priority=3, dependsOnMethods="MarkAsReadBroadcastMessage")
	public void VerifyBroadcastMessageWindow(){
		SW.GCLogin(Username, Password);
		if(!SW.ObjectExists("GCHome_Message_DT")){
			Environment.loger.log(Level.INFO, "'Broadcast and Internal Communications' message window is not shown as it was marked as read");
			SW.DoubleClick("GC_MyAccount_IC");
			SW.Click("GC_MyAccount_SignOut_LK");
		}else{
			Environment.loger.log(Level.ERROR,"'Broadcast and Internal Communications' message window is shown again");
			Reporter.Write(" Validate 'Broadcast and Internal Communications' window" , "'Broadcast and Internal Communications' window should not be shown", "'Broadcast and Internal Communications' message window is shown again", "Fail");
		}
	}


	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}
