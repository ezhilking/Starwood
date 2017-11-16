/* Purpose		:  Valhalla Portal Smoke Test
 * TestCase Name:  VP_SmokeTest08_Reg_Create_and_Modify_Room_Class_from_Valhalla_Portal
 * Created By	:  Yethendra Varma
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
package testscripts.vpRegression;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SMOKETEST08_CreateModifyRoomClassfromVP {

	CRS SW =new CRS();
	String Errmsg;
	String RcCode= SW.RandomString(4).toUpperCase();
	String PolicyName="Automation Cancel Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");
	@BeforeClass 
	public void StartTest(){
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.VPURL);


	}
	@Test
	public void VPLogin(){
		SW.VPLogin("VP_Username", "VP_Password");
		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));
		SW.Click("VP_PropClick_BT");
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Guest Room Product List");
		SW.Click("VPInventory_GuestRoomProductList_LK");		
		SW.SwitchToFrame("VP_MainFrame_FR");
		SW.DropDown_SelectByText("VPInventory_RoomProductType_DD", "Pricing Room Classes");
		SW.Click("VPInventory_CreateButton_BT");
		
		//Validation of Clear Button
		SW.EnterValue("VPInventory_RoomClassCode_EB", RcCode); 
		SW.VPClick("VPInventory_CPRCClear_BT");
		if(SW.GetText("VPInventory_RoomClassCode_EB").isEmpty()){
			Environment.loger.log(Level.INFO, "Clear Button Validated Sucessfully");
		}
		
		//Validation of Room class Mandatory Fields(Room Class Name,Room Class Code,Room Class Description)
		
		//Room Class Name Validation
		SW.EnterValue("VPInventory_RoomClassCode_EB", RcCode);
		SW.ClickAndProceed("VPInventory_CPRCSave_BT");		
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			Environment.loger.log(Level.INFO, "Room Class Name Error Message Validated Sucessfully");
		}else{
			SW.FailCurrentTest("Room Class Name Error Message Validation Failed");
		}
		
		//Room Class Code Validation
		SW.ClearValue("VPInventory_RoomClassCode_EB");
		SW.EnterValue("VPInventory_RoomClassName_EB", "Automated RC "+RcCode);
		SW.ClickAndProceed("VPInventory_CPRCSave_BT");
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			Environment.loger.log(Level.INFO, "Room Class Code Error Message Validated Sucessfully");
		}else{
			SW.FailCurrentTest("Room Class Code Error Message Validation Failed");
		}
		// Room Class Description Validation
		SW.EnterValue("VPInventory_RoomClassCode_EB", RcCode);
		SW.ClickAndProceed("VPInventory_CPRCSave_BT");
		if(SW.IsAlertPresent()){
			SW.HandleAlert(true);
			Environment.loger.log(Level.INFO, "Room Class Description Error Message Validated Sucessfully");
		}else{
			SW.FailCurrentTest("Room Class Description Error Message Validation Failed");
		}
		
		//Creating Room Class 
		SW.EnterValue("VPInventory_RoomClassDescription_EB", "Automated RC "+RcCode);
		SW.VPClick("VPInventory_CPRCSave_BT");
		SW.CompareText("VPInventory_RoomClassCreationSucessMsg_DT", "Room Class created with code: "+RcCode+".", SW.GetText("VPRates_RPinfoMsg_DT").trim());
		Environment.loger.log(Level.INFO, "Room Class created with code: "+RcCode+" Sucessfully");
		
		//DB Validations
		SW.EstablishConnection(Environment.getRunEnvironment());;
		SW.RecordExists("select PRDCT_CD from inventory.prdct where PRDCT_CD='"+RcCode+"' and PRDCT_SHORT_DESCP='Automated RC "+RcCode+"'");
		Environment.loger.log(Level.INFO, "Inventory DB is Verified Sucessfully");
		SW.RecordExists("select RM_Class_Name from prop.prop_gst_rm_class where RM_Class_Name='Automated RC "+RcCode+"' and Prop_id='"+SW.TestData("NonRosPropID_Opera")+"'");
		Environment.loger.log(Level.INFO, "Shopping DB is Verified Sucessfully");
		//Deleting the Room Class
		SW.UpdateQuery("delete from inventory.prdct where PRDCT_CD='"+RcCode+"' and PRDCT_SHORT_DESCP='Automated RC "+RcCode+"'");
		SW.UpdateQuery("delete from prop.prop_gst_rm_class where RM_Class_Name='Automated RC "+RcCode+"' and Prop_id='"+SW.TestData("NonRosPropID_Opera")+"'");
		SW.Wait(10);
		if(!(SW.RecordExists("select PRDCT_CD from inventory.prdct where PRDCT_CD='"+RcCode+"' and PRDCT_SHORT_DESCP='Automated RC "+RcCode+"'"))){
			Environment.loger.log(Level.INFO, "Recode deleted from Inventory DB Sucessfully");
		}else{
			Environment.loger.log(Level.INFO, "Recode wasnt deleted from Inventory DB Sucessfully");
		}
		if(!(SW.RecordExists("select RM_Class_Name from prop.prop_gst_rm_class where RM_Class_Name='Automated RC "+RcCode+"' and Prop_id='"+SW.TestData("NonRosPropID_Opera")+"'"))){
			Environment.loger.log(Level.INFO, "Recode deleted from Shopping DB Sucessfully");
		}else{
			Environment.loger.log(Level.INFO, "Recode wasnt deleted from Shopping DB Sucessfully");
		}
		SW.CloseDBConnection();
		SW.RefreshPage();
	}
	

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}