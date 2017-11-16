package testscripts.gcRegression;
/** Purpose		: Validate accessibility of the OMT application when user access is updated as Field Marketer
 * TestCase Name: GC_REG86_ValidateEditBroadcastMessage
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 	
 * Reviewed Date: 
 */

import java.util.List;



import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG86_ValidateEditBroadcastMessage {
	CRM SW = new CRM();
	String Username, Password;
	String Headline, Message, BrodacastHeadline, BroadcastMessage;
	List<String> sSuccessMessage;

	@BeforeClass
	public void StartTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	@Test(priority=1)
	public void EditBroadcastMessage(){
		SW.GCLogin(Username, Password);
		//Mark all the messages as read and close the broadcast window beofore proceeding with he actual script execution
		if(SW.ObjectExists("GCHome_Message_DT")){
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
			}else{
				//If multiple broadcast messages exists, then below loop executes based on integer value 
				for(int i=1; i<=IntCount; i++){
					SW.CheckBox("//div[@id='message"+i+"']//input[@type='checkbox']", "ON");
					if(SW.ObjectExists("GC_NextButtonInBroadcastWindow_BT"))
						SW.NormalClick("GC_NextButtonInBroadcastWindow_BT");
				}
				SW.Click("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_Close_IC")){
					SW.NormalClick("GCHome_Message_Close_IC");
				}
				Environment.loger.log(Level.INFO,"All messages are marked as read and closed the 'Broadcast and Internal Communications' window");
			}
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCHome_BroadCastMsg_LK");
		SW.Click("GCActvBroadCastMsg_LK");
		if(!SW.ObjectExists("GCEmergencyMsg_Table_WT")){
			SW.Click("GCCreateBroadCastMsg_LK");
			SW.Wait(10);
			SW.EnterValue("GCCreateBroadCastMsg_StartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateBroadCastMsg_EndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			Headline=SW.RandomString(20);
			SW.EnterValue("GCCreateBroadCastMsg_Headline_EB", Headline);
			Environment.loger.log(Level.INFO,  "Entered 'Broadcast and Internal Communications' Headline");
			SW.SwitchToFrame("GCCreateBroadCastMsg_Message_FR");
			SW.Click("GCCreateBroadCastMsg_Message_EB");
			Message=SW.RandomString(20);
			SW.EnterValue("GCCreateBroadCastMsg_Message_EB", Message);
			Environment.loger.log(Level.INFO,  "Entered 'Broadcast and Internal Communications' Message");
			SW.SwitchToFrame("");
			SW.Click("GCCreateBroadCastMsg_Publish_BN");
			SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
			if (SW.ObjectExists("GCHome_GreenMsg_DT"))
			{
				sSuccessMessage=SW.GetAllText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,sSuccessMessage.toString());
				if(SW.ObjectExists("GC_MyAccount_IC")){
					SW.DoubleClick("GC_MyAccount_IC");
					SW.NormalClick("GC_MyAccount_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR, "'Broadcast and Internal Communications' Message has not been Published");
			}
		}else{
			Environment.loger.log(Level.INFO,"'Broadcast and Internal Communications' message exists");
			SW.Click("GC_EditBroadCastMessage_IC");
			SW.Wait(10);
			SW.EnterValue("GCCreateBroadCastMsg_StartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GCCreateBroadCastMsg_EndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			Headline=SW.RandomString(20);
			SW.EnterValue("GCCreateBroadCastMsg_Headline_EB", Headline);
			Environment.loger.log(Level.INFO,  "Updated 'Broadcast and Internal Communications' Headline");
			SW.SwitchToFrame("GCCreateBroadCastMsg_Message_FR");
			SW.Click("GCCreateBroadCastMsg_Message_EB");
			Message=SW.RandomString(20);
			SW.EnterValue("GCCreateBroadCastMsg_Message_EB", Message);
			SW.SwitchToFrame("");
			SW.Click("GC_BroadCastMessageUpdate_BT");
			Environment.loger.log(Level.INFO,  "Updated 'Broadcast and Internal Communications' Message");
			SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");;
			if (SW.ObjectExists("GCHome_GreenMsg_DT"))
			{
				sSuccessMessage=SW.GetAllText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,sSuccessMessage.toString());
				if(SW.ObjectExists("GC_MyAccount_IC")){
					SW.DoubleClick("GC_MyAccount_IC");
					SW.Click("GC_MyAccount_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR, "'Broadcast and Internal Communications' Message has not been updated");
			}
		}
	}


	@Test(priority=2, dependsOnMethods="EditBroadcastMessage")
	public void UpdatedCommunicationMessageValidation(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			BrodacastHeadline=SW.GetText("//*[@id='message1']//tr[@class='communicationTitle']//font");
			SW.CompareTextContained("Validate Communication Message Headline", Headline, BrodacastHeadline );
			SW.Wait(5);
			BroadcastMessage=SW.GetText("//*[@id='message1']//tr[@class='communicationBody']//div");
			SW.CompareTextContained("Validate Communication Message", Message, BroadcastMessage);
			SW.Wait(5);
		}else{
			Environment.loger.log(Level.INFO, "Updated communication Message is not displayed in the broadcast window");
			Reporter.Write("Validate The Updated Broadcast Bessage", "Updated Brodcast Message has been displayed", "Updated Brodcast Message has not been displayed", "Fail");
		}
		SW.CheckBoxIsSelected("GC_SelectCheckBoxMarkAsRead_CB");
		SW.NormalClick("GCHome_Message_Close_IC");
		if(SW.ObjectExists("GC_MyAccount_IC")){
			SW.DoubleClick("GC_MyAccount_IC");
			SW.NormalClick("GC_MyAccount_SignOut_LK");
		}
	}


	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}




