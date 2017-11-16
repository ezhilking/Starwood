package testscripts.ABCD;
/* Purpose		: 
 * TestCase Name: 
 * Created By	: 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 	
 * Reviewed Date: 
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class MUSTRUN01_AddOpportunity {
	SALES SW = new SALES();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.ABCD);
	}

	@Test
	public void AddOpportunity(){
		SW.ABCDLogin(SW.TestData("ABCD_Username"), SW.TestData("ABCD_Password"));

		//Moving Cursor on Opportunity tab
		SW.MoveToObject("ABCD_Opportunity_LK");
		SW.Click("ABCD_OpptyAdd_LK");

		//Referred by Section
		String FirstName = "ABCD_"+SW.RandomString(5);
		SW.EnterValue("ABCD_OpptySubFormFN_EB",FirstName);
		SW.EnterValue("ABCD_OpptySubFormMN_EB", "AB_"+SW.RandomString(5));
		String LastName = "AB_"+SW.RandomString(5);
		SW.EnterValue("ABCD_OpptySubFormLN_EB",LastName );

		SW.EnterValue("ABCD_OpptySubFormDpt_EB","QA_"+SW.RandomString(5) );
		int Division = SW.DropDown_GetSize("ABCD_OpptySubFormDvsn_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormDvsn_DD", Division-1);
		SW.EnterValue("ABCD_OpptySubFormPhne_EB", "1234567890");
		SW.EnterValue("ABCD_OpptySubFormemail_EB", "ABCD@starwoodhotels.com");
		int RegionDropdown = SW.DropDown_GetSize("ABCD_OpptySubFormRegion_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormRegion_DD", SW.RandomNumber(1, RegionDropdown-1));//TODO Dropwon index update

		//Development Contact Section
		SW.EnterValue("ABCD_OpptySubFormCmpnyName_EB", "ABCD_"+SW.RandomString(5));
		SW.EnterValue("ABCD_OpptySubFormDevFN_EB", "AA_"+SW.RandomString(5));
		SW.EnterValue("ABCD_OpptySubFormDevMN_EB", "AA_"+SW.RandomString(5));
		SW.EnterValue("ABCD_OpptySubFormDevLN_EB", "AA_"+SW.RandomString(5));		
		SW.EnterValue("ABCD_OpptySubFormAddr1_EB", "Eco Space");
		SW.EnterValue("ABCD_OpptySubFormAddr2_EB", "Bellandur");
		SW.EnterValue("ABCD_OpptySubFormCity_EB", "Bangalore");
		SW.EnterValue("ABCD_OpptySubFormState_EB", "Karnataka");
		SW.EnterValue("ABCD_OpptySubFormPcode_EB", "123456");
		int CountryDD = SW.DropDown_GetSize("ABCD_OpptySubFormCountry_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormCountry_DD", CountryDD-9);//TODO Use random or select a specific text
		int CompanyType = SW.DropDown_GetSize("ABCD_OpptySubFormCmpType_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormCmpType_DD", SW.RandomNumber(1,CompanyType-1));//Selecting dropdown using random.
		SW.EnterValue("ABCD_OpptySubFormTitle_EB", "QA TEAM_"+SW.RandomString(5));

		int PersonRole = SW.DropDown_GetSize("ABCD_OpptySubFormProle_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormProle_DD", SW.RandomNumber(1, PersonRole-1));
		SW.EnterValue("ABCD_OpptySubFormDevph_EB", "123456789");
		SW.EnterValue("ABCD_OpptySubFormDevEmail_EB", "ABCDQA@starwoodhotels.com");

		//Opportunity Section
		SW.EnterValue("ABCD_OpptySubFormOppAd1_EB", "Test Addess1_"+SW.RandomString(5));
		SW.EnterValue("ABCD_OpptySubFormOppAd2_EB", "Test Addess2_"+SW.RandomString(5));
		SW.EnterValue("ABCD_OpptySubFormOppCity_EB", "Bangalore");
		SW.EnterValue("ABCD_OpptySubFormOppState_EB", "Karnataka");
		SW.EnterValue("ABCD_OpptySubFormOppCode_EB", "123456");
		SW.EnterValue("ABCD_OpptySubFormOppCountry_EB", "India");

		//Passing Drop Down values
		int BrandPref = SW.DropDown_GetSize("ABCD_OpptySubFormOppBrand_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppBrand_DD", SW.RandomNumber(1, BrandPref-1));
		int ConType = SW.DropDown_GetSize("ABCD_OpptySubFormOppConType_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppConType_DD", SW.RandomNumber(1, ConType-1));
		int ProjType = SW.DropDown_GetSize("ABCD_OpptySubFormOppProType_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppProType_DD", SW.RandomNumber(1, ProjType-1));
		int ProdType = SW.DropDown_GetSize("ABCD_OpptySubFormOppProdType_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppProdType_DD", SW.RandomNumber(1, ProdType-1) );		
		int LandSec = SW.DropDown_GetSize("ABCD_OpptySubFormOppLand_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppLand_DD", SW.RandomNumber(1, LandSec-1));
		int PlanPerm = SW.DropDown_GetSize("ABCD_OpptySubFormOppPermits_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppPermits_DD", SW.RandomNumber(1, PlanPerm-1));
		int FinPlace = SW.DropDown_GetSize("ABCD_OpptySubFormOppFincng_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppFincng_DD", SW.RandomNumber(1, FinPlace-1));

		//New Build Only Section
		SW.EnterValue("ABCD_OpptySubFormOppLand _EB", "2016");
		int Unit = SW.DropDown_GetSize("ABCD_OpptySubFormOppUnit _DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppUnit _DD", SW.RandomNumber(0, Unit-1));
		SW.EnterValue("ABCD_OpptySubFormOppFacilities _EB", SW.RandomString(500));
		int ThirdParty = SW.DropDown_GetSize("ABCD_OpptySubFormOpp3rd _DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOpp3rd _DD", SW.RandomNumber(1, ThirdParty-1) );
		SW.EnterValue("ABCD_OpptySubFormOppPrjct _EB", "100");
		SW.EnterValue("ABCD_OpptySubFormOppAvgStdRoomSize _EB", "123");
		int AvgUnit = SW.DropDown_GetSize("ABCD_OpptySubFormOppNbUnit_EB");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppNbUnit_EB", SW.RandomNumber(0, AvgUnit-1));

		//Conversion Only Section
		int CurrentBrand = SW.DropDown_GetSize("ABCD_OpptySubFormOppCrntBrand_DD");
		SW.DropDown_SelectByIndex("ABCD_OpptySubFormOppCrntBrand_DD", SW.RandomNumber(1, CurrentBrand-1));
		SW.EnterValue("ABCD_OpptySubFormOppOthrBrand_EB", "STARWOOD"+SW.RandomString(4) );
		SW.EnterValue("ABCD_OpptySubFormOppRooms_EB", SW.RandomNumber(5000, 10000));
		SW.EnterValue("ABCD_OpptySubFormOppFloors_EB", SW.RandomNumber(20, 30));
		SW.CheckBox("ABCD_OpptySubFormOppSprinklers _CB", "ON");
		SW.EnterValue("ABCD_OpptySubFormOppBltYear _EB", SW.RandomNumber(1990, 2016));

		//Uploading a Document 
		SW.Click("ABCD_OpptySubFormOppAddCommnt _BT");
		SW.WaitForWindowCount(2);
		SW.SwitchToWindow(2);
		SW.EnterValue("ABCD_OpptySubFormOppDocpp _EB", "ABCD_TEST"+SW.RandomString(50));
		SW.FileUpload("ABCD_OpptySubFormOppBrowse_EB","ABCD_TestReport.pdf" );//TODO
		SW.ClickAndProceed("ABCD_OpptySubFormOppSave_IC");
		SW.HandleAlert(true);
		SW.SwitchToWindow(1);		
		SW.EnterValue("ABCD_OpptySubFormOppComments _EB", SW.RandomString(100));
		SW.ClickAndProceed("ABCD_OpptySubFormOppSubmit _EB");
		SW.HandleAlert(true);
		SW.GetScreenshot("SuccessfullOppt");
		SW.Click("ABCD_Opportunity_LK");

		int RowCount = SW.WebTbl_GetRowCount("ABCD_OpportunitySearch_WT");
		String Submitter = SW.WebTbl_GetText("ABCD_OpportunitySearch_WT", RowCount, 9);
		SW.CompareText("Submitter_DT", FirstName+" "+LastName, Submitter);
	}

	@AfterClass
	public void StopTes(){
		SW.Click("ABCD_Logout_LK");
		Reporter.StopTest();
	}

}