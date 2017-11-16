package testscripts.ABCD;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN13_AddSubmittalNotes {
	
	SALES SW = new SALES();

	@BeforeClass
	public void startTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void AddingSubmittalAndNotes(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.MoveToObject("ABCDSubmittal_DealsLink_LK");
		SW.Click("ABCDSubmittal_DealSearchLink_LK");
		SW.EnterValue("ABCDSubmittal_DealSearchPage_EB", 19);
		SW.Click("ABCDSubmittal_DealSearchPage_BT");
		SW.Click("ABCDSubmittal_NonDevelopment_Lk");
		SW.Click("ABCDSubmittal_PostExecution_Lk");
		SW.SwitchToFrame("");
		
		//Switch to Parent Iframe
		SW.SwitchToFrame("ABCDSubmittal_MainActivities_FR");
		SW.Click("ABCDSubmittal_ActivitiesButton_BT");
		//Switch to Child Iframe
		
		SW.SwitchToFrame("ABCDSubmittal_ActivitiesPopup_FR");
		SW.DropDown_SelectByText("ABCDSubmittal_ActivityType_DD", "A&D/NBT Next Step Note");
		SW.DropDown_SelectByText("ABCDSubmittal_ActivitySubType_DD", "45 Day Visit Note");
		SW.EnterValue("ABCDSubmittal_ActivityNotes_EB", "ABCD_"+ SW.RandomString(50));
		SW.Click("ABCDSubmittal_ActivitySave_BT");	
		
		//Switch to Parent Iframe
		SW.SwitchToFrame("ABCDSubmittal_MainActivities_FR");		
		String actualSuccessText = SW.GetText("ABCDSubmittal_ActivitySuccessText_ST");
		Environment.loger.log(Level.INFO, actualSuccessText);
		
		String expectedText = " The Activity/Note was added successfully";
		SW.CompareText("Sucessfully Created Notes", expectedText, actualSuccessText);	

}
	 	@AfterClass
	 	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}

}