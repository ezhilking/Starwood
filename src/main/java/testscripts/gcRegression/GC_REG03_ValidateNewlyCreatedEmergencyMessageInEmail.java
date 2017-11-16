package testscripts.gcRegression;
/** Purpose		: This is to Validate the newly created emeergency message is sent to the guest in an email and validate the report
 * TestCase Name: ValidateNewlyCreatedEmergencyMessageInEmail
 * Created By	: Sharanya Bannuru
 * Modified By	: sachin
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

public class GC_REG03_ValidateNewlyCreatedEmergencyMessageInEmail {

	CRM SW = new CRM();	
	String sMessage;
	String PropertyID="110";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
	}
	@Test(priority=1)
	public void GCCreateEmergencyMsg(){
		try{
			SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCHome_Admin_LK");
			SW.Click("GCAdmin_EmergencyMessage_LK");
			//SW.EnterValue("GC_EmergencyMessage_PropID_EB",PropertyID);
			SW.EnterValue("//*[@id='pId']", PropertyID);
			//SW.Click("GC_EmergencyMessage_AddPropID_BT");
			SW.Click("//input[@name='Add']");
			SW.SelectRadioButton("GC_EmergencyMsg_EmailList_RB");	
			SW.FileUpload("GC_EmergencyMsg_EmailList_BN","email.csv");
			sMessage = SW.RandomString(15);
			SW.EnterValue("GC_EmergencyMsg_Subject_EB","Automation Test");
			SW.SwitchToFrame("GC_EmergencyMsg_Message_FR");
			SW.Click("GC_EmergencyMsg_Message_EB");
			SW.EnterValue("GC_EmergencyMsg_Message_EB",sMessage);
			SW.SwitchToFrame("");
			SW.Click("GCEmergencyMsg_Approve_BN");
			SW.Wait(20);
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				Environment.loger.log(Level.INFO,"Message Creation is successful");	
				if(SW.ObjectExists("GCNavigation_SignOut_LK")){
					SW.Click("GCNavigation_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Message Creation is Failed");	
				SW.FailCurrentTest("Message Creation is Failed");
			}		
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Message Creation is Failed",e);
		}
	}
	
	@Test(priority=2,dependsOnMethods={"GCCreateEmergencyMsg"})
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
			SW.FailCurrentTest("Bop Refresh failed");
		}
	}
	
	@Test(priority=3,dependsOnMethods={"BopRefresh"})
	public void validateEmailStatus(){
		SW.LaunchBrowser(Environment.GCURL);
		SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCAdmin_EmergencyMessage_LK");
		SW.Click("GCEmergencyMsg_View_LK");
		int row = SW.WebTbl_GetRowIndex("GCEmergencyMsg_Table_WT", 6, " "+sMessage);
		String Status = SW.WebTbl_GetText("GCEmergencyMsg_Table_WT", row, 9);
		SW.CompareText("VerifyStatus_ST", Status, "Sent");
		Environment.loger.log(Level.INFO,"Message status is Sent");
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
