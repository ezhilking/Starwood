package testscripts.resCon;

/* Purpose		: verifying property drop down on the MP upload list page
 * TestCase Name:TC10_ Verify property id in select property dropdown list for Masterbill home _ResCon
 * Created By	: Shalini.jaikumar
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;
public class MUSTRUN23_MBPropertyidInMBHome {
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
	public void CreateMasterbillSPGinviteModify(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHome_MBUploadList_LK");
		if(SW.DropDown_SelectByValue("ResconMB_SPGUploadlist_DD", "1513"))
		{
			Reporter.Write("ValidatingPropertydropdownOnMBuploadlistpage", "Propertydropdown should be present", "Propertydropdown is displayed", "Pass");
		}
		else
		{
			Reporter.Write("ValidatingPropertydropdownOnMBuploadlistpage","Propertydropdown should be present", "Propertydropdown is not displayed", "Fail");

		}

	}@AfterClass
	public void EndTest(){

		Reporter.StopTest();		
	}

}
