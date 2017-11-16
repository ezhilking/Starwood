package testscripts.SimplifiGRC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		:
 * TestCase Name: ELocker Matter 
 * Created By	: Kumari Nitu 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */
public class MUSTRUN04_eLocker_Matter {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SIMPLIFIGRCURL); 
	}
	@Test
	public void eLockerMatter(){
		SW.EnterValue("SimplifiGRCLogin_Username_LK","weblogic");
		SW.EnterValue("SimplifiGRCLogin_Password_LK","staRW00d11G");
		SW.Click("SimplifiGRCLogin_SignIn_BT");
		SW.Click("SimplifiGRCHome_AddDocument_LK");
		SW.Click("SimplifiGRCelocker_Matter_LK");
		String title=SW.RandomString(5);
		SW.EnterValue("SimplifiGRCelocker_MatterName_LK",title);
		SW.DropDown_SelectByIndex("SimplifiGRCelocker_MatterStatus_LK", SW.RandomNumber(1,SW.DropDown_GetSize("SimplifiGRCelocker_MatterStatus_LK")-1));
		SW.DropDown_SelectByIndex("SimplifiGRCelocker_ManagingTeam_LK", SW.RandomNumber(1,SW.DropDown_GetSize("SimplifiGRCelocker_ManagingTeam_LK")-1));
		SW.DropDown_SelectByIndex("SimplifiGRCelocker_Region_LK", SW.RandomNumber(1,SW.DropDown_GetSize("SimplifiGRCelocker_Region_LK")-1));
		SW.DropDown_SelectByIndex("SimplifiGRCelocker_MatterType_LK", SW.RandomNumber(1,SW.DropDown_GetSize("SimplifiGRCelocker_MatterType_LK")-1));
		SW.DropDown_SelectByIndex("SimplifiGRCelocker_MatterSubType_LK", SW.RandomNumber(1,SW.DropDown_GetSize("SimplifiGRCelocker_MatterSubType_LK")-1));
		SW.EnterValue("SimplifiGRCelocker_MatterNumber_LK", SW.RandomInteger(5));
		SW.Click("SimplifiGRCelocker_submit_LK");
		if(SW.CompareText("SimplifiGRCelocker_submitPage_LK",title))
		{
			System.out.println("Passed");
		}	
}
	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	}

}

