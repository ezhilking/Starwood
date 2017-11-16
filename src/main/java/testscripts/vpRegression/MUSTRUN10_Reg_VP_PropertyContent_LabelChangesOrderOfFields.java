/* Purpose		:  Automation
 * TestCase Name:  Reg01_VP_PropertyContent_LabelChangesOrderOfFields
 * Created By	:  Muneeb
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */

package testscripts.vpRegression;




import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN10_Reg_VP_PropertyContent_LabelChangesOrderOfFields {

	CRS SW =new CRS();
	String SucessfullyCreated;
	String createdHotelServ= SW.RandomString(4).toUpperCase();

	String PolicyName="Automation Cancel Policy on"+ SW.GetTimeStamp("yyyy/MM/dd HH:mm:ss");

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
		//		SW.EnterValue("VP_PropertyID_EB",SW.TestData("NonRosPropID_Opera"));//_galaxt
		//		SW.Click("VP_PropClick_BT");
		//		Validation of Clear Button
		//		SW.EnterValue("VPInventory_RoomClassCode_EB", RcCode); 
		//		SW.VPClick("VPInventory_CPRCClear_BT");
		//		if(SW.GetText("VPInventory_RoomClassCode_EB").isEmpty()){
		//			Environment.loger.log(Level.INFO, "Clear Button Validated Sucessfully");
		//		}
	}


	@Test(priority=2)
	public void PropertyContent()
	{
		SW.NormalClick("VP_MenuSearch_EB");
		SW.EnterValue("VP_MenuSearch_EB","Hotel Services (Primary)");
		SW.ClickAndProceed("VP_MenuSearch_EB");
		SW.Click("VPPropContent_HotelServices(Primary)_BT");
		SW.SwitchToFrame("VP_MainFrame_FR");

	}

	@Test(priority=3)
	public void CreateNewHotelService()
	{
		SW.Click("VPPropContent_HSCreate_BT");
		SW.DropDown_SelectByText("VPPropContent_HotelServType_DD", "Sport/Recreation");
		SW.EnterValue("VPPropContent_HotelServName_ET", createdHotelServ);
		SW.Click("VPPropContent_CreateHotelServSave_BT");
		System.out.println(createdHotelServ);
//		int Row = SW.WebTbl_GetRowIndex("service", 2, createdHotelServ);
//		if(Row==-1){
//			Reporter.Write("Brij", "failure", "failure", "FAIL");
//		}else{
//			Reporter.Write("Brij", "sucess", "sucess", "PASS");
//		}
	}


	//		SW.GetText("createdHotelServ");
	//		String SucessfullyCreated =SW.GetText("VPRates_RPinfoMsg_DT");
	//		Reporter.WriteLog(Level.INFO,SucessfullyCreated);
	//	   String createdHotelServ1=SW.GetAttributeValue("VPPropContent_HotelServName_DT","value");
	//		Reporter.WriteLog(Level.INFO, createdHotelServ1);
	//	SW.DropDown_SelectByText("VPInventory_RoomProductType_DD", "Pricing Room Classes");
	//	SW.Click("VPInventory_CreateButton_BT");

	@Test(priority=4)
	public  void DB(){
		{
			try {
				
			SW.EstablishConnection(Environment.getRunEnvironment());
			String query="select service_name from service_lkup where service_name='"+ createdHotelServ +"'";
			Reporter.WriteLog(Level.INFO, query);

			if(SW.RecordExists(query)){
				List<String> output = SW.GetColumnValues(query, 1);{
					if (SW.CompareText(output.get(0), createdHotelServ)) {
						Reporter.Write("Service Created", "Service type should present in DB", "Service type is present in DB", "PASS");
					} else {
						Reporter.Write("Service not been Created", "Service type should present in DB", "Service type not present in DB", "FAIL");
					}
				}
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
		} 
		SW.CloseDBConnection();
				
			}
	}
	



	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
