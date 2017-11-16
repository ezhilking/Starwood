package testscripts.gcRegression;
/** Purpose		: Validate_Preview_BrodcastMessage
 * TestCase Name: GC_REG87_ValidatePreviewBrodcastMessage
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

public class GC_REG88_ValidatePreviewBrodcastMessage {
	CRM SW = new CRM();
	String Username,Password;
	String Subject, Message, BrodacastHeadline, BroadcastMessage, Subject1, Message1, BrodacastHeadline1, BroadcastMessage1;
	List<String> sSuccessMessage;

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	@Test(priority=1)
	public void PreviewBroadcastMessage(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCHome_BroadCastMsg_LK");
		SW.Wait(10);
		SW.EnterValue("GCCreateBroadCastMsg_StartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCCreateBroadCastMsg_EndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		Subject=SW.RandomString(20);
		SW.EnterValue("GCCreateBroadCastMsg_Headline_EB", Subject);
		Environment.loger.log(Level.INFO,  "Entered 'Broadcast and Internal Communications' Headline");
		SW.SwitchToFrame("GCCreateBroadCastMsg_Message_FR");
		SW.Click("GCCreateBroadCastMsg_Message_EB");
		Message=SW.RandomString(20);
		SW.EnterValue("GCCreateBroadCastMsg_Message_EB", Message);
		Environment.loger.log(Level.INFO,  "Entered 'Broadcast and Internal Communications' Message");
		SW.SwitchToFrame("");
		SW.Click("GC_PreviewBroadcastMessage_BT");
		SW.Wait(5);

		//Validation of Broadcast 'Headline' and 'Message' in preview window while creation
		if(SW.ObjectExists("GCHome_Message_DT")){
			BrodacastHeadline=SW.GetText("GC_CaptureCommunicationHeadline_ST");
			SW.CompareTextContained("Validate Broadcast Message Headline in Preview window from create page", Subject, BrodacastHeadline );
			SW.Wait(5);
			BroadcastMessage=SW.GetText("GC_CapturePreviewCommunicationMessage_ST");
			SW.CompareTextContained("Validate Broadcast Message in Preview window from create page", Message, BroadcastMessage);
			SW.Wait(5);
			SW.DoubleClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_Close_IC"))
			{
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
			SW.Wait(5);
			SW.Click("GCCreateBroadCastMsg_Publish_BN");
			SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
			if (SW.ObjectExists("GCHome_GreenMsg_DT"))
			{
				sSuccessMessage=SW.GetAllText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,sSuccessMessage.toString());
			}else{
				Environment.loger.log(Level.ERROR, "'Broadcast and Internal Communications' Message has not been Published");
			}
		}else{
			Reporter.Write("Validate Broadcast message in preview window", "Broadcast message in preview window should be displayed", "Broadcast message in preview window is not displayed", "Fail");
		}
		
		//Validation of Communication Message in Preview Window for existing Communication Message
//		Subject1=SW.GetText("GC_CaptureExistingBroadcastMessageSubject_ST");
		//	Message1=SW.GetText("GC_CaptureExistingBroadcastMessage_ST");
			Subject1=SW.GetText("//*[@id='list']//tr[1]/td[7]//td/textarea");
			SW.SwitchToFrame("GC_SwitchToMessageFrame_FR");
			Message1=SW.GetText("DiscSelectTemplate_Entertext_EB");
			SW.SwitchToFrame("");
			SW.Click("GC_CreatedBroadcastMessagePreview_BT");
			SW.Wait(5);
			//Validation of created Broadcast 'Headline' and 'Message' in preview window
			if(SW.ObjectExists("GCHome_Message_DT")){
				BrodacastHeadline1=SW.GetText("GC_CaptureCommunicationHeadline_ST");
				SW.CompareTextContained("Validate Created Broadcast Message Headline in Preview window", Message1, BrodacastHeadline1 );
				SW.Wait(5);
				BroadcastMessage1=SW.GetText("GC_CapturePreviewCommunicationMessage_ST");
				SW.CompareTextContained("Validate Created Broadcast Message in Preview window", Message1, BroadcastMessage1);
				SW.Wait(5);
				SW.DoubleClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_Close_IC"))
				{
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
				SW.Wait(5);
				SW.DoubleClick("GC_MyAccount_IC");
				if(SW.ObjectExists("GC_MyAccount_IC")){
					SW.DoubleClick("GC_MyAccount_IC");
				}
				SW.Click("GC_MyAccount_SignOut_LK");
			}
			else
			{
				Reporter.Write("Validate Created Broadcast message in preview window", "Created Broadcast message in preview window should be displayed", "Created Broadcast message in preview window is not displayed", "Fail");
			}
	}

	//Validation of Communication Message in Preview Window for existing Communication Message
/*	@Test(priority=2)
	public void CreatedBroadcastMessagePreviewValidation(){
	//	Subject1=SW.GetText("GC_CaptureExistingBroadcastMessageSubject_ST");
	//	Message1=SW.GetText("GC_CaptureExistingBroadcastMessage_ST");
		Subject1=SW.GetText("//*[@id='list']//tr[1]/td[7]//td/textarea");
		SW.SwitchToFrame("frameElement");
		Message1=SW.GetText("DiscSelectTemplate_Entertext_EB");
		SW.SwitchToFrame("");
		SW.Click("GC_CreatedBroadcastMessagePreview_BT");
		SW.Wait(5);
		//Validation of created Broadcast 'Headline' and 'Message' in preview window
		if(SW.ObjectExists("GCHome_Message_DT")){
			BrodacastHeadline1=SW.GetText("GC_CaptureCommunicationHeadline_ST");
			SW.CompareTextContained("Validate Created Broadcast Message Headline in Preview window", Subject1, BrodacastHeadline1 );
			SW.Wait(5);
			BroadcastMessage1=SW.GetText("GC_CapturePreviewCommunicationMessage_ST");
			SW.CompareTextContained("Validate Created Broadcast Message in Preview window", Message1, BroadcastMessage1);
			SW.Wait(5);
			SW.DoubleClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_Close_IC"))
			{
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
			SW.Wait(5);
			SW.DoubleClick("GC_MyAccount_IC");
			if(SW.ObjectExists("GC_MyAccount_IC")){
				SW.DoubleClick("GC_MyAccount_IC");
			}
			SW.Click("GC_MyAccount_SignOut_LK");
		}
		else
		{
			Reporter.Write("Validate Created Broadcast message in preview window", "Created Broadcast message in preview window should be displayed", "Created Broadcast message in preview window is not displayed", "Fail");
		}
	}	*/


	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}
}