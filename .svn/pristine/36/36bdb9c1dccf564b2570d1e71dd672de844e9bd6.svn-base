package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for 'Property Details - facilities
 * TestCase Name: 'Property Details - facilities
 * Created By	: Dhivya
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG92_PropertyDetails_facilities {
	private static final String Text = null;
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
		SW.NormalClick("NavigatorPropPage_facilities_LK");
		if(SW.CompareText("Comparing the Panel header", "Facilities", SW.GetText("NavigatorPropPage_FacilitiesHeader_DT"))){
			//Validating the By Name Sort
			SW.NormalClick("NavigatorPropPage_NameList_DT");
				SW.WaitTillElementToBeClickable("NavigatorPropPage_CCCPopupMsg_DT");
				SW.CompareTextContained("Comparing the data list Header", Text, SW.GetText("NavigatorPropPage_CCCPopupMsg_DT"));
				SW.Click("NavigatorPropPage_Close_BT");
			}
		}
	}
		
		
	