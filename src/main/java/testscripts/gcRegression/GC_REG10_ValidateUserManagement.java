package testscripts.gcRegression;
/** Purpose		: This is to Validate user management, by editing user access or adding new user
 * TestCase Name: ValidateUserManagement
 * Created By	: Sharanya Bannuru
 * Modified By	: Sachin
 * Modified Date: 6/21/2016
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

public class GC_REG10_ValidateUserManagement {

	CRM SW = new CRM();	
	String sMessage,UserName, Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	
	@Test(priority=1)
	public void GCValidateUserManagement(){
		try{
			SW.GCLogin(UserName,Password);
			
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GCHome_Admin_LK");
			SW.Click("GCUsrMngmnt_LK");
			SW.EnterValue("GCUsrMngmnt_Userid_EB",SW.TestData("GCUsername"));
			SW.Click("GCUsrMngmnt_Search_BT");
			SW.Click("GCUsrMngmnt_Edit_BT");
			String sChecked=SW.GetAttributeValue("GCUsrMngmnt_RB","checked");
			if(sChecked!=null){
				SW.CheckBox("GCUsrMngmnt_RB","OFF");
			}else{
				SW.CheckBox("GCUsrMngmnt_RB", "ON");
			}
			SW.Click("GCUsrMngmnt_Save_BT");
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				Environment.loger.log(Level.INFO,"User Access has been edited and SPG user access role is assigned successfully");	
			}else{
				Environment.loger.log(Level.ERROR,"User Access edit has been failed ");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured",e);
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
