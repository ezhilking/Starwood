package testscripts.resCon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: Verifying property id in the Master bill of  New SPG user Page 
 * TestCase Name:TC11_ Verify property id in select property dropdown list for Masterbill home_SPG member _ResCon
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
public class MUSTRUN25_MB_PropertyIDInNewSPG {
	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
		
		
	}@Test(priority=1)
	public void CreateMasterbillSPGinviteAdminUser(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHomepage_MBNewinvite_BT");
		SW.DropDown_SelectByText("ResconMB_Select_DD","SPG/SPP");
		SW.Click("ResconMB_Selectnext_BT");
		
		if(SW.DropDown_SelectByValue("ResconMB_SPGProperty_DD", "1513"))
		{
			Reporter.Write("ValidatingPropertydropdownOnMBNewSPGUserPage", "Propertydropdown should be present", "Propertydropdown is displayed", "Pass");
		}
		else
		{
			Reporter.Write("ValidatingPropertydropdownOnMBNewSPGUserPage","Propertydropdown should be present", "Propertydropdown is not displayed", "Fail");

		}

	}@AfterClass
	public void EndTest(){

		Reporter.StopTest();		
	}

}
