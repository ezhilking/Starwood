package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for 'Property Details - Attractions
 * TestCase Name: 'Property Details - Attractions
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG90_PropertyDetails_Attractions {
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
		SW.NormalClick("NavigatorPropPage_Attractions_LK");
		if(SW.CompareText("Comparing the Panel header", "Attractions", SW.GetText("NavigatorPropPage_AttractionHeader_DT"))){
			String sText=SW.GetText("NavigatorPropPage_SortNameList_DT");
			SW.SelectRadioButton("NavigatorPropPage_SortDistance_RB");
			//validating the By Distance Sort 
			if(!sText.equalsIgnoreCase(SW.GetText("NavigatorPropPage_SortDistanceList_DT"))){
				Reporter.Write("Validation the attraction list By Distance",sText, SW.GetText("NavigatorPropPage_SortDistanceList_DT"), "PASS");
				String Text=SW.GetText("NavigatorPropPage_SortDistanceList_DT");
				SW.DoubleClick("NavigatorPropPage_SortDistanceList_DT");
				SW.WaitTillElementToBeClickable("NavigatorPropPage_CCCPopupMsg_DT");
				SW.CompareTextContained("Comparing the data list Header", Text, SW.GetText("NavigatorPropPage_CCCPopupMsg_DT"));
				SW.Click("NavigatorPropPage_Close_BT");
			}
			else
				Reporter.Write("Validation fails in comapre", "SortBy Distance", "Fail", "Fail");
			//Validating the By Name Sort
			sText=SW.GetText("NavigatorPropPage_SortDistanceList_DT");
			SW.SelectRadioButton("NavigatorPropPage_SortName_RB");
			if(!sText.equalsIgnoreCase(SW.GetText("NavigatorPropPage_SortNameList_DT"))){
				Reporter.Write("Validation the attraction list By Distance",sText, SW.GetText("NavigatorPropPage_SortNameList_DT"), "PASS");
				String Text=SW.GetText("NavigatorPropPage_SortNameList_DT");
				SW.NormalClick("NavigatorPropPage_SortNameList_DT");
				SW.WaitTillElementToBeClickable("NavigatorPropPage_CCCPopupMsg_DT");
				SW.CompareTextContained("Comparing the data list Header", Text, SW.GetText("NavigatorPropPage_CCCPopupMsg_DT"));
				SW.Click("NavigatorPropPage_Close_BT");
			}
			//validating the By Type Sort
			sText=SW.GetText("NavigatorPropPage_SortNameList_DT");
			SW.SelectRadioButton("NavigatorPropPage_SortType_RB");
			if(!sText.equalsIgnoreCase(SW.GetText("NavigatorPropPage_SortTypeList_DT"))){
				Reporter.Write("Validation the attraction list By Distance",sText, SW.GetText("NavigatorPropPage_SortTypeList_DT"), "PASS");
				SW.Click("NavigatorPropPage_SortTypeList_DT");
			}
		}
		else{
			Reporter.Write("Validation fails in comapre", "CCC Internal Resource Area", "Fail", "Fail");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
