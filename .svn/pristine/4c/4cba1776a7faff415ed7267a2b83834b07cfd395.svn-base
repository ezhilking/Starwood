package testscripts.gcRegression;
/** Purpose		: Validate_Delete_in_ViewEmergencyMessage
 * TestCase Name: GC_REG94_ValidateDeleteButtonInViewEmergencyMessage
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
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
public class GC_REG94_ValidateDeleteButtonInViewEmergencyMessage {

	CRM SW = new CRM();
	String Username,Password;
	String sMessage;
	String Property1="1925";

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	//Create Emergency Message and  'Save as Draft'
	@Test(priority=1) 
	public void CreateEmergencyMessageandSaveAsDraft(){
		try{
			SW.GCLogin(Username, Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_DT")){
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
			}
			SW.Click("GCHome_Admin_LK");
			SW.Click("GCAdmin_EmergencyMessage_LK");
		//	SW.EnterValue("GC_EmergencyMsg_PropID_EB", Property1);
			SW.EnterValue("//input[@name='pId']", Property1);
		//	SW.Click("GC_EmergencyMsg_AddPropertyId_BT");
			SW.Click("//input[@name='Add']");
		//	SW.EnterValue("GC_EmergencyMsg_StartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("//input[@name='stDate']",  SW.GetTimeStamp("MM/dd/yyyy"));
		//	SW.EnterValue("GC_EmergencyMsg_EndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("//input[@name='enDate']",  SW.GetTimeStamp("MM/dd/yyyy"));
		//	SW.CheckBox("GC_EmergencyMsg_ReserSts_CB", "ON");
			SW.CheckBox("//input[@class='checkBoxAll']", "ON");
			sMessage = SW.RandomString(15);
			SW.EnterValue("GC_EmergencyMsg_Subject_EB","Automation Test");
		//	SW.EnterValue("//textarea[@name='messageSubject']","Automation Test");
			SW.SwitchToFrame("GC_EmergencyMsg_Message_FR");
			SW.Click("GC_EmergencyMsg_Message_EB");
			SW.EnterValue("GC_EmergencyMsg_Message_EB",sMessage);
			Environment.loger.log(Level.INFO,sMessage);
			SW.SwitchToFrame("");
			SW.Click("//input[@name='saveDraft']");
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				Environment.loger.log(Level.INFO,"Emergency Message Successfully Moved To Draft");
				if(SW.ObjectExists("GCNavigation_SignOut_LK")){
					SW.Click("GCNavigation_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Emergency Message Is Not Moved To Draft");	
				Reporter.Write("Validate Emergency Message Creation", "Emergency Message Successfully Moved To Draft", "Emergency Message Is Not Moved To Draft", "Fail");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Emergency Message Creation Is Failed",e);
		}
	}


	//Validation of Emergency Message status after clicking on 'Save as Draft'
	@Test(priority=2,dependsOnMethods="CreateEmergencyMessageandSaveAsDraft")
	public void ValidateEmergencyMessageStatusIsDraft(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCAdmin_EmergencyMessage_LK");
		SW.Click("GCEmergencyMsg_View_LK");
		//int row = SW.WebTbl_GetRowIndex("GCEmergencyMsg_Table_WT", 6, " "+sMessage);
		String Status = SW.WebTbl_GetText("GCEmergencyMsg_Table_WT", 1, 9);
		SW.CompareText("Verify the status of the Emergency Message", "Draft", Status);
		Environment.loger.log(Level.INFO,"Emergency Message status Is Draft");
	}

	//Validation of Delete Button functionality of a saved Emergency Message 
	@Test(priority=3, dependsOnMethods="ValidateEmergencyMessageStatusIsDraft")
	public void ValidateDeleteButton(){
		SW.Click("//table[@id='list']//td[contains(.,'"+sMessage+"')]//..//input[@value='Delete']");
		SW.WaitForPageload();
		if(SW.ObjectExists("GCHome_GreenMsg_DT")){
			Environment.loger.log(Level.INFO,"Emergency Message Deleted Successfully");
			if(SW.ObjectExists("GCNavigation_SignOut_LK")){
				SW.Click("GCNavigation_SignOut_LK");
			}
		}else{
			Environment.loger.log(Level.ERROR,"Emergency Message Has Not Been Deleted");	
			Reporter.Write("Validate Emergency Message Deletion", "Emergency Message Deleted Successfully", "Emergency Message Has Not Been Deleted", "Fail");
		}
	}	

	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}

}

