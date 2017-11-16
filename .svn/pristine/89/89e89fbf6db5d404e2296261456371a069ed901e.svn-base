package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
/* Purpose		: This script for 'Property Content - CCC Internal Resource Area
 * TestCase Name: 'Property Content - CCC Internal Resource Area
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG89_PropertyDetailsAssociateList {
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
		SW.NormalClick("NavigatorPropPage_AssociateList_LK");
		if(SW.CompareText("Comparing the Panel header", "Associate List", SW.GetText("NavigatorPropPage_AssociateHeader_DT"))){
			//Validating the header of the associate list
			SW.CompareText("Comparing the AssociateList Header", "NAME", SW.GetText("NavigatorPropPage_AssociateName_DT"));
			SW.CompareText("Comparing the Associate  List Header", "TITLE", SW.GetText("NavigatorPropPage_AssosiateTitle_DT"));
			SW.CompareText("Comparing the Associate List Header", "PHONE NUMBER", SW.GetText("NavigatorPropPage_AssociatePhno_DT"));
			SW.Click("NavigatorPropPage_PropertySearchClose_BT");
		}else{
			Reporter.Write("Validation fails in comapre", "CCC Internal Resource Area", "Fail", "Fail");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
