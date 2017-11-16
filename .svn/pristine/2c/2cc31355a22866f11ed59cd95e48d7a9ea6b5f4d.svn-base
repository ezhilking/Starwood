package testscripts.ABCD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN06_PropertyIntelReport {
	SALES SW = new SALES();

	@BeforeClass
	public void startTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void generatePropertyIntelReport(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));
		SW.MoveToObject("ABCDPropIntel_PropertiesTab_LK");
		SW.Click("ABCDPropIntel_PropertiesSearch_LK");
		int Division = SW.DropDown_GetSize("ABCDPropIntel_DivisionSelect_DD");
		SW.DropDown_SelectByIndex("ABCDPropIntel_DivisionSelect_DD", SW.RandomNumber(0, Division-2));
		SW.Click("ABCDPropIntel_DivisionSelectArrow_DD");
		SW.Click("ABCDPropIntel_SearchButton_BT");

		//SW.Click("ABCDPropIntel_SearchResult_WT");

		SW.WebTbl_Click("ABCDPropIntel_SearchResult_WT", 1, 1);
		SW.SwitchToFrame("ABCDPropIntel_PropIntel_FR");
		SW.Click("ABCDPropIntel_PropIntel_LK");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("ABCDPropIntel_PropIntelPDF_FR");
		String actualText = SW.GetText("ABCDPropIntel_PDFPropIntel_ST");
		System.out.println("actualText");		
		String expectedText = "Property Intel Report";
		SW.CompareText("Property Intel Report Generated Successfully", expectedText, actualText);





	}

	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}




}




