package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for 'Property Details - Rooms
 * TestCase Name: 'Property Details - rooms
 * Created By	: dhivya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG94_PropertyDetails_Rooms {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test(priority=0)
	public void CheckPropertyContent(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_PropertyID_EB");
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", SW.TestData("PropertyID")+" ");
		SW.Wait(2);
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", Keys.ENTER);
		SW.NormalClick("NavigatorHomePage_BeginSearch_BT");
		SW.NormalClick("NavigatorHomePage_PropertyNTB_LK");
		SW.Click("NavigatorHomePage_PropertyNTB_LK");
		SW.NormalClick("NavigatorPropPage_Rooms_LK");
		if(SW.CompareText("Comparing the Panel header", "Rooms", SW.GetText("NavigatorPropPage_RoomsHeader_DT"))){
			//Validating the header of the rooms
			SW.CompareText("Comparing the Rooms Header", "CODE", SW.GetText("NavigatorPropPage_Roomscode_DT"));
			SW.CompareText("Comparing the Rooms  Header", "TYPE", SW.GetText("NavigatorPropPage_RoomsType_DT"));
			SW.CompareText("Comparing the Rooms  Header", "BEDDING", SW.GetText("NavigatorPropPage_RoomsBedding_DT"));
			SW.Click("NavigatorPropPage_PropertySearchClose_BT");
		}else{
			Reporter.Write("Validation fails in comapre", "CCC Internal Resource Area", "Fail", "Fail");
		}
	}
	@AfterClass
	public void EndTest1(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}

@AfterClass
public void EndTest(){
	SW.NavigatorLogOut();
	Reporter.StopTest();		
}
}