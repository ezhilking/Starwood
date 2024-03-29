package testscripts.gcRegression;
/** Purpose		: This is to Validate the Property Admin screen to edit the enroll /Non-enroll, LITE or STD property features
 * TestCase Name: ValidatePropertyAdminScreen
 * Created By	: Sharanya Bannuru
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

public class GC_REG12_ValidatePropertyAdminScreen {

	CRM SW = new CRM();	
	String sMessage,UserName,Password,Plan;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	
	@Test(priority=1)
	public void GCValidatePropertyAdminScreen(){
		try{
			SW.GCLogin(UserName,Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_DT")){
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
			}
			SW.Click("GC_PropertyAdmin_LK");
			int iColCount=SW.WebTbl_GetColumnCount("GC_PropertyAdmin_PropTable_WT");
			System.out.println(iColCount);
			int iActvRow=SW.WebTbl_GetRowIndex("GC_PropertyAdmin_PropTable_WT",3, "Active");

			String sPropType=SW.WebTbl_GetText("GC_PropertyAdmin_PropTable_WT", iActvRow,4);
			
			if(sPropType.equals("STANDARD")||(sPropType.equals("Not Enrolled"))||(sPropType.equals("LITE")))
			{
				String sPropValue=SW.WebTbl_GetText("GC_PropertyAdmin_PropTable_WT", iActvRow,1);
				SW.EnterValue("GC_PropertyAdmin_PropId_EB",sPropValue);
				SW.Click("GC_PropertyAdmin_View_BT");
				SW.Click("GCUsrMngmnt_Edit_BT");
				Plan=SW.GetText("GC_PropertyAdmin_Plan_WT");
				System.out.println(Plan);
				SW.Click("GC_PropertyAdmin_UpdateEnroll_BT");
				SW.DropDown_SelectByText("GC_PropertyAdmin_Enroll_DD", "New Enrollment");
				if(SW.CompareText("LITE", Plan))
					SW.DropDown_SelectByText("GC_PropertyAdmin_Plan_DD", "STANDARD");
				else if(SW.CompareText("STANDARD", Plan))
					SW.DropDown_SelectByText("GC_PropertyAdmin_Plan_DD", "LITE");
				SW.EnterValue("GC_PropertyAdmin_EffDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
				
			//	SW.EnterValue("GC_PropertyAdmin_Date_EB", SW.GetTimeStamp("MM/dd/yyyy"));
				SW.Click("GCUsrMngmnt_Save_BT");
				if(SW.ObjectExists("GCHome_GreenMsg_DT")){
					String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
					boolean sValidation=sSuccessMessage.contains("The Property Details Have Been Successfully Saved.");
					System.out.println(sValidation);
					if(sValidation==true){
						Environment.loger.log(Level.INFO,"PropertyAdmin Screen has been edited successful for"+sPropType);
					}else{
						Environment.loger.log(Level.ERROR,"Edit of PropertyAdmin Screen has been Failed");
						SW.FailCurrentTest("Edit of PropertyAdmin Screen has been Failed");
					}						
				}
				
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
