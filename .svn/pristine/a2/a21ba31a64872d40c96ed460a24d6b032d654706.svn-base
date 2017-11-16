package testscripts.SPGLink;
/** Purpose		: This is to Post a Reimbursement in SPG Link 2.0 under Stay Poster Role
 * TestCase Name: Reimbursement
 * Created By	: Vaishali Krishna
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

public class SPGLink_REG30_Reimbursement {
	CRM SW = new CRM();
	int Room_Occ= SW.RandomInteger(3);
	int House_Aloc= SW.RandomInteger(2);
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	
	@Test(priority=0)
	public void Reimbursement() {
	
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		/*SW.SPGLinkChangeUserRole("SPG Reimbursement");*/
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLink_SPGReimbursement_DD");
		SW.Click("SPGLink_SPGReimbursement_Calender_BT");
		for (int i=2;i<=30;i++){
			if(SW.CompareText("-", SW.GetText("//*[@id='23251893297470503']/tbody/tr["+i+"]/td[2]"))){
				SW.DoubleClick("//*[@id='23251893297470503']/tbody/tr["+i+"]/td[1]/a");
				break;
			}
					
		}
		if(Room_Occ<House_Aloc)
			Room_Occ=Room_Occ+100;	
		SW.EnterValue("SPGLink_SPGReimbursement_Calender_RoomOcc_EB", Room_Occ);
		SW.EnterValue("SPGLink_SPGReimbursement_Calender_HouseUse_EB", House_Aloc);
		SW.NormalClick("SPGLink_SPGReimbursement_Calender_Certify_CB");
		SW.Click("SPGLink_SPGReimbursement_Calender_Submit_BT");
		SW.Wait(5);
		if(!SW.IsAlertPresent())
			SW.Click("SPGLink_SPGReimbursement_Calender_Submit_BT");
		SW.HandleAlert(true);
		SW.WaitForPageload();
		if (SW.ObjectExists("SPGLinkEventPosting_Success_DT")) {
			Environment.loger.log(Level.INFO, "Workbook has been successfully submitted");
		} else {
			Environment.loger.log(Level.ERROR, "Workbook not submitted");
			SW.FailCurrentTest("");
		}
	
		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
