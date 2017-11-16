/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_Smoke05_Reg_Inventory_Modify_the_Manage_Daily_Inventory_By_Date_Range
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import java.util.List;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST05_ModifyManageDailyInventoryByDate_Range {
	CRS SW = new CRS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);
	}

	@Test(priority=0)
	public void CSFCreateFile(){
		
		SW.VPLogin("VP_Username", "VP_Password");
		SW.EnterValue("VP_PropertyID_EB", SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Manage Daily Inventory");
		SW.Click("VPInventory_MouseOverMDI_LK");		
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.SelectRadioButton("VPInventory_DateRangeSelection_RB");
		SW.EnterValue("VPInventory_ToDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("VPInventory_ProductType_DD", "Room Types");
		int ProductChoosed=SW.RandomNumber(0, SW.DropDown_GetSize("VPInventory_ProductCodes_DD")-1);
		SW.DropDown_SelectByIndex("VPInventory_ProductCodes_DD", ProductChoosed);		
		String ProductName=SW.DropDown_GetSelectedText("VPInventory_ProductCodes_DD");
		Environment.loger.log(Level.INFO, "Name of the Product Choosed="+ProductName);
		SW.Click("VPInventory_ViewModify_BT");		
		String RoomCount=SW.GetText("VPInventory_ProductLevelOBAllotment_EB").trim();		
		int ProductRoomCount=Integer.parseInt(RoomCount);
		Environment.loger.log(Level.INFO, "Remaining ProductRoomCount Before Modify="+ProductRoomCount);
		SW.Wait(2);
		SW.EnterValue("VPInventory_ProductLevelOBAllotment_EB", "50"+Keys.TAB);		
		SW.Click("VPInventory_Save_BT");
		SW.Wait(5);
		String ProductCode=SW.GetText("VPInventory_ProductCode_DT").trim();
		Environment.loger.log(Level.INFO, ProductCode);
		SW.CompareText("SaveProductInventory", "Product inventory saved successfully", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		RoomCount=SW.GetText("VPInventory_ProductLevelOBAllotment_EB").trim();	
		int FinalProductRoomCount = Integer.parseInt(RoomCount);
		if(FinalProductRoomCount==ProductRoomCount){
			Environment.loger.log(Level.INFO, "Inventory was updated for the product Successfully and UI Validation was successful");
		}else{
			SW.FailCurrentTest("Inventory was not updated sucessfully for the product "+ProductName);
		}
		   
		SW.EstablishConnection(Environment.getRunEnvironment());
		 List<String> DBResults =SW.GetColumnValues("select ovrbkd_qty from inventory.prdct_inv where prdct_id in(select prdct_id from inventory.prdct where prdct_cd='"+ProductCode+"') and use_start_dtme like SYSDATE", 1);
		 String OBquantity=DBResults.get(0).trim();
		 Environment.loger.log(Level.INFO,"OB Quantity Updated in DB="+OBquantity);
		 if(OBquantity.equals("50")){			 
			 Environment.loger.log(Level.INFO, "DB Validation was Successful");
		 }else{
			 SW.FailCurrentTest("DB Validation was Unsuccessful");
		 }
						
		
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}

}
