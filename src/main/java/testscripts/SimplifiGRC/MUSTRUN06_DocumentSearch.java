package testscripts.SimplifiGRC;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

/* Purpose		:
 * TestCase Name: Document Search
 * Created By	: Kumari Nitu 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date:
 */
public class MUSTRUN06_DocumentSearch {
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
		SW.EnterValue("SimplifiGRCDocument_Titlename_LK", "Testng_17");
		SW.Click("SimplifiGRCDocument_Find_LK");
		if(SW.CompareText("Testng_17","SimplifiGRCDocument_SearchResult_LK"))
			{
			System.out.println("Test Passed");
	}
	}
	@AfterClass
	public void StopTes(){
		Reporter.StopTest();
	}

}
