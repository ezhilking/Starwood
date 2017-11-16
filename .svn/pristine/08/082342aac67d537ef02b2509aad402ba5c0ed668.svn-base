package testscripts.ABCD;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN05_GDCCommitteeMeeting {
	SALES SW = new SALES();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void AddGDCMeeting(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.HandleAlert(true);//TODO
		SW.Click("ABCD_Deals_LK");
		SW.EnterValue("ABCD_DealsId_EB", 200);
		SW.ClickAndProceed("ABCD_DealsSearch_BT");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		SW.HandleAlert(true);//TODO
		SW.Click("ABCDGDC_Contract_LK");
		SW.Click("ABCDGDC_Legal_LK");		
		SW.Click("ABCDGDC_CommitteeMeeting_LK");
		SW.Click("ABCDGDC_CommitteeAddMeeting_BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.EnterValue("ABCDGDC_Date_LK",SW.GetTimeStamp("dd-MMM-yyyy"));
		SW.DropDown_SelectByText("ABCDGDC_Committee_DD", "GDC");
		SW.EnterValue("ABCDGDC_ElectronicDoc_LK",SW.GetTimeStamp("dd-MMM-yyyy"));
		SW.EnterValue("ABCDGDC_PhysicalDoc_LK",SW.GetTimeStamp("dd-MMM-yyyy"));
		//SW.FileUpload("ABCDGDC_Brwose_BT", "C:\\Users\\t.b.natarajan\\Desktop\\Test New 1.txt");
		String Notes = "ABCD_"+SW.RandomString(50);
		SW.EnterValue("ABCDGDC_Notes_BT",Notes);
		SW.ClickAndProceed("ABCDGDC_SaveIcon_BT");
		SW.HandleAlert(true);
		SW.SwitchToWindow(1);

		List<String> AllNotesText = SW.GetAllText("ABCDGDC_GDCNotesText_WT");
		boolean Ispass =  false;

		for(int i=0;i<AllNotesText.size();i++){
			SW.CompareText("Notes: "+Notes, AllNotesText.get(i));
			Ispass = true;
		}

		if(Ispass){
			SW.CompareText("NotesMatched", "Pass", "Pass");
		}else{
			SW.FailCurrentTest("Notes");
		}
	}	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}

}
