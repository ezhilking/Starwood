/* Purpose		:  Automation
 * TestCase Name:  MUSTRUN11_Reg_VP_VP_PropertyContent_QuickSelections
 * Created By	:  Muneeb
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN11_Reg_VP_PropertyContent_QuickSelections {
	CRS SW =new CRS();
	String SucessfullyCreated;
	String createdHotelServ= SW.RandomString(4).toUpperCase();

	//	String PolicyName="Automation Cancel Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");

	@BeforeClass 
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}
	@Test(priority=1)
	public void VPLogin(){

		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.EnterValue("VP_Username_EB", SW.TestData("VP_Username"));
		SW.EnterValue("VP_Password_EB", SW.TestData("VP_Password"));
		SW.VPClick("VP_Submit_BT");
	}
	@Test(priority=2)
	public void PropertyContent()
	{
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Quick Selections");
		SW.Click("VPPropContent_Quick Selections_BT");
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.Click("VPPropContent_HSCreate_BT");
		//	SW.RadiobuttonIsSelected("VPPropContent_QuickSelectionType_RB");/not req
	}

	@Test(priority=3)
	public void CreateQS(){
		SW.DropDown_SelectByValue("VPPropContent_getHotelCategoryTypeId_DD","94" );
		SW.EnterValue("VPPropContent_getName_EB", "test");
		SW.EnterValue("VPPropContent_getDescription_EB", "test");
		SW.DropDown_SelectByValue("VPPropContent_getAdvanceSearch_DD", "N");
		//	SW.Click("VPPropContent_HSCreate_BT");//not req
		SW.Click("VPPropContent_Brands_WT");
		SW.NormalClick("VPPropContent_add_BT");
		SW.Click("VPPropContent_CreateHotelServSave_BT");
	}
	@Test(priority=4)
	public void SearchQS(){
		SucessfullyCreated= SW.GetText("VPRates_RPinfoMsg_DT");
		Reporter.Write("created QuickSelections","SucessfullyCreated","created QAuickselections Successfull", "Successfull");
		SW.DropDown_SelectByValue("VPPropContent_searchHotelCategoryTypeId_DD","94" );
		SW.EnterValue("VPPropContent_searchName_EB", "test");
		SW.EnterValue("VPPropContent_searchDescription_EB", "test");
		SW.DropDown_SelectByValue("VPPropContent_searchAdvanceSearch_DD", "N");
		//	SW.Click("VPPropContent_HSCreate_BT");//not req
		SW.DropDown_SelectByValue("VPPropContent_searchBrands_WT","1");
		SW.Click("VPPropContent_SearchHotelServSave_BT");
		SW.SelectRadioButton("VPPropContent_QuickSelection_RB");//error at this line
		SW.Click("VPPropContent_CreateHotelView_BT");
	}

	@Test(priority=5)
	public  void Db()
	{
		try{	
			//SW.Wait(100);//for reflect into DB
			SW.EstablishConnection(Environment.getRunEnvironment());
			String query="select hotel_catg_name from hotel_catg_lkup where HOTEL_CATG_NAME='test'";
			//	Environment.loger.log(Level.INFO,query); 
			if(SW.RecordExists(query)){
				List<String> output=SW.GetColumnValues(query, 1);
				if (SW.CompareText(output.get(0), "test")) {
						Reporter.Write("New AttractionType is Been Created", "Attraction type should present in DB", "Attraction type is present in DB", "PASS");
					} else {
						Reporter.Write("Issue in creating new AttractiveType", "Attraction type should present in DB", "Attraction type not present in DB", "FAIL");
					}
				}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
			SW.FailCurrentTest("Failed during db Validation");
		} 
		SW.CloseDBConnection();
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
