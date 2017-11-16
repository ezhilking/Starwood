package testscripts.gcRegression;
/** Purpose		: Validate View Emergency Message
 * TestCase Name: GC_REG93_ValidateViewEmergencyMessage
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
public class GC_REG93_ValidateViewEmergencyMessage {
	CRM SW = new CRM();
	String Username,Password;
	String sMessage;
	String TestCaseName= getClass().getName();
	String Property1="1925";

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	@Test(priority=1) 
	public void CreateEmergencyMessage(){
		try{
			SW.GCLogin(Username, Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_DT")){
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
			}
			/*SW.Click("GCHome_Admin_LK");
			SW.Click("GCAdmin_EmergencyMessage_LK");
			SW.EnterValue("GC_EmergencyMsg_PropID_EB", Property1);
			SW.Click("GC_EmergencyMsg_AddPropertyId_BT");
			SW.EnterValue("GC_EmergencyMsg_StartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.EnterValue("GC_EmergencyMsg_EndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.CheckBox("GC_EmergencyMsg_ReserSts_CB", "ON");
			sMessage = SW.RandomString(15);
			SW.EnterValue("GC_EmergencyMsg_Subject_EB","Automation Test");
			SW.SwitchToFrame("GC_EmergencyMsg_Message_FR");
			SW.Click("GC_EmergencyMsg_Message_EB");
			SW.EnterValue("GC_EmergencyMsg_Message_EB",sMessage);
			Environment.loger.log(Level.INFO,sMessage);
			SW.SwitchToFrame("");
			SW.Click("GCEmergencyMsg_Approve_BN");
			SW.Wait(20);
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				Environment.loger.log(Level.INFO,"Emergency Message Creation Is successful");	
				if(SW.ObjectExists("GCNavigation_SignOut_LK")){
					SW.Click("GCNavigation_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Emergency Message Creation Is Failed");	
				Reporter.Write("Validate Emergency Message Creation", "Emergency Message Creation Is successful", "Emergency Message Creation Is Failed", "Fail");
			}		
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Emergency Message Creation Is Failed",e);
		}
	}*/
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
			SW.Click("GCEmergencyMsg_Approve_BN");
			SW.Wait(20);
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				Environment.loger.log(Level.INFO,"Emergency Message Creation Is successful");	
				SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", "Emergency Message for Properties 1925,1965 has been sent");
				if(SW.ObjectExists("GCNavigation_SignOut_LK")){
					SW.Click("GCNavigation_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Emergency Message Creation Is Failed");	
				Reporter.Write("Validate Emergency Message Creation", "Emergency Message Creation Is successful", "Emergency Message Creation Is Failed", "Fail");
			}		
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Emergency Message Creation Is Failed",e);
		}
	}


	@Test(priority=2,dependsOnMethods="CreateEmergencyMessage")
	public void BopRefresh(){
		SW.LaunchBrowser(Environment.BOB);
		SW.BopLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		SW.Click("BopHome_GCAdmin_Lk");
		SW.WaitTillElementToBeClickable("BopAdmin_Misc_Lk");
		SW.NormalClick("BopAdmin_Misc_Lk");
		SW.WaitTillElementToBeClickable("BopMisc_BeanShell_LK");
		SW.NormalClick("BopMisc_BeanShell_LK");
		SW.EnterValue("BopBeanShell_Query_EB", "new com.starwood.gcp.app.bop.executor.EmergencyNofificationTask().run();");
		if(SW.ObjectExists("BopeBeanShell_Execute_BN")){
			SW.Click("BopeBeanShell_Execute_BN");
			Environment.loger.log(Level.INFO,"Bop Refreshed successfully");
		}else{
			Environment.loger.log(Level.ERROR,"Bop Refresh failed");
			Reporter.Write("Validate BOP Refresh", "Bop Refreshed successfully", "Bop Refresh failed", "Fail");
		}
	}
	//Validation of Emergency Message status after refreshing BOP
	@Test(priority=3,dependsOnMethods="BopRefresh")
	public void ValidateEmailStatus(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCAdmin_EmergencyMessage_LK");
		SW.Click("GCEmergencyMsg_View_LK");
	//	int row = SW.WebTbl_GetRowIndex("GCEmergencyMsg_Table_WT", 6, " "+sMessage);
		String Status = SW.WebTbl_GetText("GCEmergencyMsg_Table_WT", 1, 9);
		SW.CompareText("Verify the status of the Emergency Message", "Sent", Status);
		Environment.loger.log(Level.INFO,"Emergency Message status Is Sent");
	}

	//Validation of 'View Emergency Message' page

	@Test(priority=4, dependsOnMethods="ValidateEmailStatus")
	public void ViewEmergencyMessage(){
		SW.Click("//table[@id='list']//td[contains(.,'"+sMessage+"')]//..//input[@value='View']");
		SW.WaitForPageload();
		if(SW.ObjectExists("//*[@id='title']")){
			String ViewEmergencyMsgPageTitle = SW.GetText("//*[@id='title']");
			SW.CompareText("Validate whether user is navigated to 'View Emegrency Message' page" , "View Emergency Message", ViewEmergencyMsgPageTitle);
			Environment.loger.log(Level.INFO, "Navigation to 'View Emeergency Message' page is successfull");
			String EmergencyMessageSubject = SW.GetText("//*[@id='msgDtls']/tbody/tr[2]/td");
			String EmergencyMessageBody = SW.GetText("//*[@id='msgDtls']/tbody/tr[4]/td/div/p"); 
			SW.CompareText("Validate Emergency Message Subject ", "Automation Test", EmergencyMessageSubject);
			SW.CompareText("Validate Emergency Message Body", sMessage, EmergencyMessageBody);
			SW.CompareText("Validate if 'Back' button is present", "Back", SW.GetText("//*[@id='buttons']/table/tbody/tr/td/input[1]"));
			SW.CompareText("Validate if 'Copy' button is present", "Copy", SW.GetText("//*[@id='buttons']/table/tbody/tr/td/input[2]"));
		}else{
			Environment.loger.log(Level.ERROR, "Navigation to 'View Emeergency Message' page is failed");
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


