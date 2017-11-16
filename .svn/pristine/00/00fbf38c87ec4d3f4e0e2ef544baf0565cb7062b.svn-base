package testscripts.gcRegression;
/** Purpose		: This is to Validate Scrubber batch job
 * TestCase Name: ValidateScrubberjob
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

public class GC_REG11_ValidateScrubberjob {

	CRM SW = new CRM();	
	String sMessage,UserName, Password;
	int ActiveMsgCount;
	int UpdatedCount;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	@Test(priority=1)
	public void GCValidateScrubberjob(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
			}
			SW.Click("GC_Scrubber_LK");
			SW.Click("GC_ScrubberView_LK");
			if(SW.ObjectExists("GC_ScrubberGrid_TB")){//
				ActiveMsgCount = SW.WebTbl_GetRowCount("GC_ScrubberGrid_TB");
				Environment.loger.log(Level.INFO,"Number of scrubber job already present:"+ActiveMsgCount);		
				
			}else{
				Environment.loger.log(Level.INFO,"No Active scrubber jobs present");
			}
			SW.Click("GC_AddScrubber_Lk");
			SW.DropDown_SelectByIndex("GC_Scrubber_ConfId_DD",1);
			SW.DropDown_SelectByIndex("GC_Scrubber_EmailPrd_DD", 1);
			SW.FileUpload("GC_Scrubber_FileUpload_EB","file.csv");
			SW.Click("GCUsrMngmnt_Save_BT");
			if(SW.ObjectExists("GC_ScrubberGrid_TB")){
				UpdatedCount= SW.WebTbl_GetRowCount("GC_ScrubberGrid_TB");
				if (UpdatedCount>=ActiveMsgCount)
				Environment.loger.log(Level.INFO,"Adding scurbberjob is success");
				else
					Environment.loger.log(Level.ERROR, "Adding scurbberjob is failed");
			}
			
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured-",e);
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
