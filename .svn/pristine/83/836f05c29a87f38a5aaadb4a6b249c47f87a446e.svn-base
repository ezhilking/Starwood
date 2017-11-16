package testscripts.SimplifiGRC;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		:
 * TestCase Name: ELocker Matter Search
 * Created By	: Kumari Nitu 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */
public class MUSTRUN05_eLockerMatterSearch {
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
		SW.Click("SimplifiGRCelocker_eLockerImage_LK");
		SW.EnterValue("SimplifiGRCelocker_MatterName_LK", "dgfdg");
		SW.Click("SimplifiGRCelocker_ElockerFind_LK");
		if(SW.CompareText("SimplifiGRCelocker_ElockerSearchResult_LK", "dgfdg"))
		{
			System.out.println("Test Passed");
		}
	}
	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	}

}
