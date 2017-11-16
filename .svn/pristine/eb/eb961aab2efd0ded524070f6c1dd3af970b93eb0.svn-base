package testscripts.CSF;

import org.apache.log4j.Level;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG32_ValidateCompetitorFieldsForCancelledBRG {
	CRM SW=new CRM();
	String a,b;
	
@BeforeTest
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test
	public void CancelledBRGMethod(){
		
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSF_EnterCSFID_EB",SW.TestData("CSFIDSearch"));
		SW.Click("CSF_EnterCSFIDGo_BN");
		
		a= SW.GetText("CSFBRG_CS_GuestQty_EB");
		b= SW.GetText("CSFBRG_CS_CompetitorID_EB");
		SW.CompareText("Validate Guest Quantity", "", a);
		SW.CompareText("Validate Competitor Id", "", b);
		
		
		SW.Click("CSF_Logout_LK");
		
		}
		
		@AfterClass
		public void EndTest(){
			Reporter.StopTest();
		}	
		
		}

