package testscripts.sgrRegression;
/* Purpose		: To Create a Synergy GCD 
 * TestCase Name: Validate the Notification triggered when the Assignee is manually assigned for a event created for Synergy GCD in SGR and event type is Request
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: Ezhilarasan.S
 * Reviewed Date: 03-21-2016
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG05_Validate_Notification_Synergy_GCD {

	CRM SW = new CRM();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void CreateEventWithSynergyGCD(){
		try {
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "3200");
			
			SW.Click("SGRCreateEvent_NewEvent_LK");
			SW.DropDown_SelectByText("SGRCreateEvent_EventType_DD", "Request");
			SW.EnterValue("SGRCreateEvent_LocationDetail_EB", "karthick15");
			SW.DropDown_SelectByText("SGRCreateEvent_Group_DD", "Hotel Services");
			SW.DropDown_SelectByText("SGRCreateEvent_Category_DD", "Bathroom Amenities");
			int DetailSize = SW.DropDown_GetSize("SGRCreateEvent_Detail_DD");
			//Select a Detail by iterating until Synergy Assignee is selected 
			for(int i=0;i<DetailSize;i++){
				SW.DropDown_SelectByIndex("SGRCreateEvent_Detail_DD", i);
				String AssignToSelectedText = SW.DropDown_GetSelectedText("SGRCreateEvent_AssignTo_DD");
				if(!AssignToSelectedText.isEmpty())
					break;
			}
			String Notes = SW.RandomString(6);
			SW.EnterValue("SGRCreateEvent_Noted_EB", "Sample Notes "+Notes);
			SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "FOOD AND BEVERAGE");
			SW.DropDown_SelectByText("SGRCreateEvent_AssignTo_DD", "Venkadesan, Karthick");
			SW.WaitTillElementToBeClickable("SGRCreateEvent_EscalationDisable_BN");
			if(SW.ObjectExists("SGRCreateEvent_EscalationDisable_BN"))
				SW.Click("SGRCreateEvent_EscalationDisable_BN");

			SW.Click("SGRCreateEvent_Save_BN");
			SW.CheckBox("SGREvents_CheckRefresh_CB", "ON");

			String ActualMessage = SW.GetText("General_ErrorMessage_DT");

			if(SW.CompareTextContained("EventSuccesfull_DT","was saved", ActualMessage)){
				String EventID = SW.GetEventNumbeID();
				Environment.loger.log(Level.INFO, "Event Created:"+EventID);
			}else{
				SW.CompareText("Event Not created", "Passed", "Failed");
			}
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
			SW.FailCurrentTest("Exception Accurred!! ");
		}

	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}

}
