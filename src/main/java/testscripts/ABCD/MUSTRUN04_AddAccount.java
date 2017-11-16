package testscripts.ABCD;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN04_AddAccount {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void AddAccount(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));

		//MOVING CURSOR
		SW.MoveToObject("ABCD_Account_LK");
		SW.Click("ABCD_AccountAdd_LK");
		SW.SwitchToWindow(2);
		SW.EnterValue("ABCD_AccName_EB", "ABCD_"+SW.RandomString(4));
		int AccTypeDropDownSize = SW.DropDown_GetSize("ABCD_AccType_DD");
		SW.DropDown_SelectByIndex("ABCD_AccType_DD", AccTypeDropDownSize-5);
		SW.Click("ABCD_AccTypeArror_LK");
		SW.EnterValue("ABCD_AccAdd1_EB", "Eco Space");
		SW.EnterValue("ABCD_AccAdd2_EB", "Bangalore");
		SW.DropDown_SelectByText("ABCD_AccCountry_DD", "India");
		SW.EnterValue("ABCD_AccPostal1_EB", "123456");
		SW.EnterValue("ABCD_Accphone#_EB", "123456789");
		SW.EnterValue("ABCD_AccphoneExt#_EB", "1234");
		SW.EnterValue("ABCD_AccFax#_EB", "7896547");
		SW.EnterValue("ABCD_AccFaxExt#_EB", "456");
		SW.EnterValue("ABCD_AccWebUrl_EB", "https://stg-phx-abcd.starwoodhotels.com");
		SW.EnterValue("ABCD_AccGenEmail_EB", "ABCD@starwoodhotels.com");
		SW.EnterValue("ABCD_AccNote_EB", SW.RandomString(1000));
		SW.Click("ABCD_AccAdd_BT");
		SW.Click("ABCD_AccClose_BT");
		SW.SwitchToWindow(1);
		SW.GetScreenshot("ABCD_AccountDetails");
		SW.SwitchToFrame("ABCD_AccountDetails_FR");

		if(SW.ObjectExists("ABCD_AccountID_DT")){
			String AccountID = SW.GetText("ABCD_AccountID_DT");
			Environment.loger.log(Level.INFO, "Account ID:"+AccountID);
		}else{
			SW.FailCurrentTest("Account no not generated!!");
		}
	}

	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	}

}
