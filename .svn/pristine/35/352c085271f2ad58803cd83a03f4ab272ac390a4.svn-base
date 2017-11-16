package testscripts.vpRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN27_VPLoggerScreen_TransactionSummery {
	CRS SW = new CRS();
	int Categoryid = SW.RandomNumber(0, 999);
	String StartDate= SW.GetTimeStamp("MM/dd/yyyy");
		
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}
	
	@Test(priority=0)
    public void TransientRatePlan(){
	SW.VPLogin("VP_Username", "VP_Password");
	SW.Click("VP_MenuSearch_EB");
	SW.EnterValue("VP_MenuSearch_EB","Logger");
	SW.Click("VP_CorpSupport_Logger_LK");
	SW.WaitForObject("VP_MainFrame_FR");
	SW.SwitchToFrame("VP_MainFrame_FR");
	
	String Disabled = SW.GetAttributeValue("VP_CorpVPLogger_Search_BN", "disabled");
	if(Disabled.equalsIgnoreCase("true")){
		Reporter.Write("Search Type Madatory Field validation", "Search Button Should be disabled", "Search Button was disabled", "PASS");
	}else{
		Reporter.Write("Search Type Madatory Field validation", "Search Button Should be disabled", "Search Button wasn't disabled", "FAIL");
	}
	SW.DropDown_SelectByIndex("VP_CorpVPLogger_SearchType_DD", 3);
	
	String StartDate = SW.GetAttributeValue("VP_CorpVPLogger_StartTime_EB", "value");
	SW.ClearValue("VP_CorpVPLogger_StartTime_EB");
	SW.ClickAndProceed("VP_CorpVPLogger_Search_BN");
	if(SW.IsAlertPresent()){
		SW.HandleAlert(true);
		Reporter.Write("Start Date Madatory Field validation", "Popup should appear Stating Start time required", "Popup appeared Sucessfully Stating Start time required", "PASS");
	}else{
		Reporter.Write("Start Date Madatory Field validation", "Popup should appear Stating Start time required", "Popup didnt appeare Sucessfully Stating Start time required", "FAIL");
	
	}
	SW.EnterValue("VP_CorpVPLogger_StartTime_EB", StartDate);
	SW.VPClick("VP_CorpVPLogger_Search_BN");
	if(SW.ObjectExists("VP_CorpVPLogger_SelectResult_RB")){		
		Reporter.Write("Transaction Results validation", "Results Should get returned Sucessfully", "Results  returned Sucessfully", "PASS");
	}else{
		Reporter.Write("Transaction Results validation", "Results Should get returned Sucessfully", "Results Didnt returned Sucessfully", "FAIL");
		}
	SW.SelectRadioButton("VP_CorpVPLogger_SelectResult_RB");
	SW.VPClick("VP_CorpVPLogger_ViewSearchQuery_BN");
	if(SW.ObjectExists("VP_CorpVPLogger_PayloadTextArea_DT")){		
		Reporter.Write("Payload validation", "Payload Should get Returned sucessfully", "Payload  Returned sucessfully", "PASS");
	}else{
		Reporter.Write("Payload validation", "Payload Should get Returned sucessfully", "Payload Didnt returned Sucessfully", "FAIL");
		}
	
	SW.VPClick("VP_CorpVPLogger_ViewDetails_BN");
	if(SW.ObjectExists("VP_CorpVPLogger_TxnDetails_WT")){		
		Reporter.Write("Transaction Details validation", "Transaction Details Should get Returned sucessfully", "Transaction Details  Returned sucessfully", "PASS");
	}else{
		Reporter.Write("Transaction Details validation", "Transaction Details Should get Returned sucessfully", "Transaction Details Didnt returned Sucessfully", "FAIL");
		}
	
	SW.Click("VP_CorpVPLogger_ViewAuditPayload_BN");
	if(SW.ObjectExists("VP_CorpVPLogger_PayloadTextArea_DT")){		
		Reporter.Write("Audit Payload validation", "Payload Should get Returned sucessfully", "Payload  Returned sucessfully", "PASS");
	}else{
		Reporter.Write("Audit Payload validation", "Payload Should get Returned sucessfully", "Payload Didnt returned Sucessfully", "FAIL");
		}
	
	SW.Click("VP_CorpVPLogger_Back_BN");
	if(SW.ObjectExists("VP_CorpVPLogger_SearchType_DD")){		
		Reporter.Write("Back Button validation", "Logger screen should get loaded sucessfully", "Logger screen got loaded sucessfully", "PASS");
	}else{
		Reporter.Write("Back Button validation", "Logger screen should get loaded sucessfully", "Logger screen didnt get loaded sucessfully", "FAIL");
		}
	
	}
	
//	@AfterClass
//	public void EndTest(){
//		Reporter.StopTest();
//	}
	
}
