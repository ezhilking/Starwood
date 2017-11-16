package testscripts.sgrRegression;
/* Purpose		: To validate the SVO fields
 * TestCase Name: Validate the reservation details section when svo user login into a  svo prop
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: Ezhilarasan.S
 * Reviewed Date: 03-21-2016
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG04_Validate_ResDetailsSVOUser {

	CRM SW = new CRM();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void VerifySVO(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SVOPropertyID"));
			SW.Wait(20);
			SW.Click("SGRNavigation_Home_LK");
			SW.SwitchToFrame("SGRHomepage_InHouse_FR");
			SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");
			if(!SW.ObjectExists("SGRHomepage_InHouseSVOFirstGuest_LK")){
				Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
				SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
			}
			SW.Click("SGRHomepage_InHouseSVOFirstGuest_LK");
			SW.WaitTillElementToBeClickable("SGRHomepage_SVOStayDates_ST");
			// Verify for the objects if exists proceed 
			if(SW.ObjectExists("SGRHomepage_SVOStayDates_ST") && SW.ObjectExists("SGRHomepage_SVOLos_ST") && SW.ObjectExists("SGRHomepage_SVORomeTypeAndNo_ST")){
				SW.CompareText("Pass_CheckingForSVOUniqueObjects", "All Objects should display", "All Objects should display");
			}else{
				SW.CompareText("Fail_CheckingForSVOUniqueObjects", "All Objects should display", "All Objects are not displayed");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

