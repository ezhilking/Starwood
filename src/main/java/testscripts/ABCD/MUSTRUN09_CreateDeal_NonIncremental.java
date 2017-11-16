package testscripts.ABCD;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN09_CreateDeal_NonIncremental {
	SALES SW = new SALES();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void AddingNonIncrementalDeal(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.HandleAlert(true);//TODO
		SW.MoveToObject("ABCD_DealsLink_LK");
		SW.ClickAndProceed("ABCD_DealsAddLink_LK");
		SW.HandleAlert(true);

		//Enter Valid PIN ID in Non Incremental Text field 
		SW.EnterValue("ABCDDealAdd_NonIncremental_EB", "10");

		//selecting first Property from the suggestion list
		SW.WaitTillPresenceOfElementLocated("ABCDDealAdd_NonIncSelectAuto_FR");		
		SW.EnterValue("ABCDDealAdd_NonIncremental_EB", Keys.DOWN);
		SW.EnterValue("ABCDDealAdd_NonIncremental_EB", Keys.TAB);
		//Filling Describe Opportunity text field with Random characters
		SW.EnterValue("ABCDDealAdd_DescOpptyText_EB", "ABCD_"+SW.RandomString(10));
		//Selecting first Account from the suggestion list
		SW.EnterValue("ABCDDealAdd_MasterAccount_DD", "SAM");
		SW.WaitTillPresenceOfElementLocated("ABCDDealAdd_MasterAccountAuto_DD");
		SW.EnterValue("ABCDDealAdd_MasterAccount_DD", Keys.DOWN);
		SW.EnterValue("ABCDDealAdd_MasterAccount_DD", Keys.TAB);
		//Clicking on first Contacts last name hyper link
		SW.Click("ABCDDealAdd_OwnerContact_IC");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("ABCDDealAdd_OwnerContactSelect_LK");
		SW.Click("ABCDDealAdd_CloseWindow_BT");
		SW.SwitchToWindow(1);
		SW.Click("ABCDDealAdd_CreateDealButton_BT");
		//Capturing the Static Text
		String ActualText = SW.GetText("ABCDDealAdd_SuccessMsg_ST");
		//System.out.printlnActualTex);
		String ExpectedText = "Successfully created a deal";
		SW.CompareText("DealCreated_Successfully", ExpectedText, ActualText);
	}
	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}

}