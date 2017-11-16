package testscripts.ABCD;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN07_CreateDeal_Incremental {
	SALES SW = new SALES();


	@BeforeClass
	public void ABCD_PoC(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}
	
	@Test
	public void addingIncrementalDeal(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		//SW.HandleAlert(true);//TODO
		SW.MoveToObject("ABCD_DealsLink_LK");
		SW.Click("ABCD_DealsAddLink_LK");

		//Enter and select a value from the Auto suggestion list
		SW.EnterValue("ABCDDealAddInc_Incremental_EB", "MG");
		SW.WaitTillPresenceOfElementLocated("ABCDDealAdd_IncAuto_DD");
		SW.EnterValue("ABCDDealAddInc_Incremental_EB", Keys.DOWN);
		SW.EnterValue("ABCDDealAddInc_Incremental_EB", Keys.TAB);

		//Filling the Random text in Describe Opportunity text field
		SW.EnterValue("ABCDDealAdd_IncDescOpptyText_EB", "ABCD_"+SW.RandomString(50));

		//Selecting first Account from the suggestion list
		SW.EnterValue("ABCDDealAdd_IncMasterAccount_EB", "SAM");
		SW.WaitTillPresenceOfElementLocated("ABCDDealAdd_IncMasterAccountAuto_DD");
		SW.EnterValue("ABCDDealAdd_IncMasterAccount_EB", Keys.DOWN);
		SW.EnterValue("ABCDDealAdd_IncMasterAccount_EB", Keys.TAB);


		//Clicking on first Contacts last name hyper link
		SW.Click("ABCDDealAdd_IncOwnerContact_IC");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.Click("ABCDDealAdd_IncOwnerContactSelect_LK");
		SW.Click("ABCDContactDetails_IncCloseThisWin_BT");
		SW.SwitchToWindow(1);
		SW.DropDown_SelectByText("ABCDDealAdd_IncBrand_DD", "Four Points by Sheraton");
		SW.DropDown_SelectByText("ABCDDealAdd_IncDiv_DD", "NAD");
		SW.DropDown_SelectByText("ABCDDealAdd_IncDealType_DD", "Franchise");
		SW.Click("ABCDDealAdd_CreateDealButton_BT");

		//Capturing the Static Text
		String ActualTextInc = SW.GetText("ABCDDealAdd_IncSuccessMsg_ST");
		//System.out.printlnActualTex;
		String ExpectedTextInc = "Successfully created a deal";
		//Comparing Actual Text with Expected Text 
		SW.CompareText("DealCreated_Successfully", ExpectedTextInc, ActualTextInc);

	}
	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}

}
