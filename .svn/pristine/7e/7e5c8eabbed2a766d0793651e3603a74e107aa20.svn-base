package testscripts.Navigator;
/* Purpose		: Searching Guest Profile 
 * TestCase Name: Search Guest By SPG Number
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 02/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE037_VerifyAgentMessage {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}

	String todaysDate = SW.GetTimeStamp("MM/dd/YYYY");
	String endDate = "12/30/2017";
	String MessageText = "Test message created on - "+ todaysDate;

	@Test(priority=0)
	public void CreateMessageInVP() {		
		String expScriptCreationMessage = "Agent Script is Created Successfully";

		SW.SwitchToFrame("AgentMessageVP_LoginFrame_FR"); //Switching the frame
		SW.NormalClick("AgentMessageVP_Username_EB"); //CLicking on the Username text box
		SW.EnterValue("AgentMessageVP_Username_EB", SW.TestData("VP_username")); //Entering the Username
		SW.NormalClick("AgentMessageVP_Password_EB"); //Clicking on the password Edit box
		SW.EnterValue("AgentMessageVP_Password_EB", SW.TestData("VP_password")); //Entering the password
		SW.NormalClick("AgentMessageVP_Submit_BT"); // CLicking the login button
		SW.NormalClick("AgentMessageVP_MenuSearch_EB");  //Clicking on the Menu Search Edit box
		SW.EnterValue("AgentMessageVP_MenuSearch_EB", "Search Message"); //Entering the Search Message in Menu serach
		SW.NormalClick("AgentMessageVP_SearchMessage_LK"); //CLicking on the link of Message Search
		SW.SwitchToFrame("AgentMessageVP_LoginFrame_FR"); //Switching the frame
		//SW.WaitTillElementToBeClickable("AgentMessageVP_NewMessage_BT");
		SW.NormalClick("AgentMessageVP_NewMessage_BT"); //Clicking on the new Message Button
		SW.DropDown_SelectByText("AgentMessageVP_MessageType_DD", SW.TestData("VP_ScriptType_DD")); //Selecting the Agent Script
		SW.NormalClick("AgentMessageVP_StartDate_EB"); //CLicking the Start date
		SW.EnterValue("AgentMessageVP_StartDate_EB", todaysDate); //Provide todays date
		SW.NormalClick("AgentMessageVP_EndDate_EB"); //Clicking the end date
		SW.EnterValue("AgentMessageVP_EndDate_EB", endDate); //provide a end date
		SW.DropDown_SelectByText("AgentMessageVP_MessageCategory_DD", SW.TestData("VP_MessageCategory_DD")); // Selecting the Message category
		SW.EnterValue("AgentMessageVP_TextMessage_EB", MessageText); //Writing a test message
		SW.SelectRadioButton("AgentMessageVP_CallCenter_RB"); //Selecting the Radio Button of Call Center
		SW.WaitTillPresenceOfElementLocated("AgentMessageVP_CallCenterList_DD"); //wait to selecting the type of CallCentre
		SW.DropDown_SelectByText("AgentMessageVP_CallCenterList_DD", SW.TestData("VP_CallCenter_List_DD"));//selecting the type of CallCentre
		SW.DropDown_SelectByText("AgentMessageVP_CallCenterList_DD", SW.TestData("VP_CallCenter_List_DD")); //Again selecting the type of CallCentre
		SW.NormalClick("AgentMessageVP_SaveMessage_BT"); //Saving the message
		SW.WaitTillPresenceOfElementLocated("AgentMessageVP_ScriptCreationMessage_FT"); //Wait till message to appear
		String actualScriptCreationMessage = SW.GetText("AgentMessageVP_ScriptCreationMessage_FT"); //Getting the message generated
		SW.CompareText("MessageGenerated_", expScriptCreationMessage, actualScriptCreationMessage); //Comparing the message is successful
		SW.NormalClick("AgentMessageVP_Logout_BT"); //Logout from VP		
	}

	@Test(priority=1)
	public void CheckMessageInNavigator(){
		//Login into the application
		SW.NavigateTo(Environment.NAVIGATORURL);
		SW.SwitchToFrame("NavigatorLogin_Login_FR");
		SW.EnterValue("NavigatorLogin_Username_EB", SW.TestData("NavigatorUsername"));
		SW.EnterValue("NavigatorLogin_Password_EB", SW.TestData("NavigatorPassword"));
		SW.WaitTillElementToBeClickable("NavigatorLogin_Login_BT");
		SW.Click("NavigatorLogin_Login_BT");
		SW.WaitTillElementToBeClickable("NavigatorHomePage_Close_BT");
		String actualMessageInNavigator = SW.GetText("NavigatorHomePage_AssociateMessage_DT");
		SW.CompareText("Navigator_message_", MessageText, actualMessageInNavigator);
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
