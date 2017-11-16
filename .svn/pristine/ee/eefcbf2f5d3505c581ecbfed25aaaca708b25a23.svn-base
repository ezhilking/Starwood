package testscripts.CSF;
/** Purpose		: Validate that only numeric values are allowed in the Resconf field
 * TestCase Name: Verify the Error message when BRG approved GCD is selected and the status of the file is pending 
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG29_ValidateOnlyNumericValuesAllowedInResconfField {
	CRM SW = new CRM();
	String CSFFileNo;
	String sPropId="1965";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}

	@Test(priority=0)
	public void CSFCreateBRGFile(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		SW.Click("CSFHome_RESCongImg_IC");
		SW.EnterValue("CSFResSearch_ResConfNumber_EB", "sa3@");
		SW.Click("CSFResConf_Find_BT");
		if(SW.ObjectExists("//li[text()='Confirmation Number should be numeric.']")){
			Reporter.Write("Validate Error Message", "Error Message should be displayed", "Error Message is displayed", "PASS");
		}
		else{
			Reporter.Write("Validate Error Message", "Error Message should be displayed", "Error Message is not displayed", "FAIL");
		}
		SW.NormalClick("CSFResSearch_Close_IC");
		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
