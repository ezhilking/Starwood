package testscripts.gcRegression;
/** Purpose		: Validate character count limitation in GC SetUp screen 'Comments' section
 * TestCase Name: GC_REG84_ValidateCharacterCountGCSetup
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 	
 * Reviewed Date: 
 */

import java.util.List;
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import functions.CRM;
import functions.Environment;
import functions.Reporter;
public class GC_REG84_ValidateCharacterCountGCSetup {

	CRM SW = new CRM();
	String PropertyID="110";
	String EmailPreHeader="AHBB MPBMP Offer";
	String Username,Password,sMessage,sMessage1;
	String ErrorMsg="[ Error...Please Correct The Following:\nMaxlength For Comments Cannot Exceed 1000 Characters.]";
	List<String> ErrorMsg1;

	String TestCaseName= getClass().getName();

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	@Test
	public void OfferDescCharacterCount(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.DoubleClick("GCHome_Message_Close_IC");
			}
		}
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("(//span[@class='pagelinks']/a)[last()]");
		SW.Click("GCOffer_Overview_IC");
		SW.Wait(10);
		SW.Click("GCSetUp_Comments_EB");
		sMessage=SW.RandomString(1001);
		SW.EnterValue("GCSetUp_Comments_EB", sMessage);
		SW.Click("GCSetUp_Submit_BN");
		ErrorMsg1=SW.GetAllText("GC_PreHeaderErrorMsg_DT");
		SW.CompareText("Error message validation", ErrorMsg, ErrorMsg1.toString());

		SW.Wait(5);
		SW.ClearValue("GCSetUp_Comments_EB");
		SW.Click("GCSetUp_Comments_EB");
		sMessage=SW.RandomString(1000);
		SW.EnterValue("GCSetUp_Comments_EB", sMessage);
		SW.Click("GCSetUp_Submit_BN");
		SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
		if(SW.ObjectExists("GCHome_GreenMsg_DT")){
			String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
			Environment.loger.log(Level.INFO,sSuccessMessage );
			String sPropertyId=sSuccessMessage.substring(sSuccessMessage.indexOf("Property")+8, sSuccessMessage.indexOf("Status")).trim();
			Environment.loger.log(Level.INFO,sPropertyId);
			SW.EnterValue("GCSetUp_PropertyID_EB",sPropertyId );
			SW.Click("GCSetUp_Apply_BN");	
			SW.WaitTillElementToBeClickable("GCSetUp_Approve_LK");
			if(SW.ObjectExists("GCSetUp_Approve_LK")){
				Environment.loger.log(Level.INFO, "Approve icon is present");
				SW.Click("GCSetUp_Approve_LK");
				SW.WaitTillElementToBeClickable("GCSetUp_Activate_LK");
				if(SW.ObjectExists("GCSetUp_Activate_LK")){
					Environment.loger.log(Level.INFO, "Activate icon is present");
					SW.Click("GCSetUp_Activate_LK");	
					SW.WaitTillElementToBeClickable("GCSetUp_EditActive_BN");
					Environment.loger.log(Level.INFO, "GC SetUp for property is activated successfully.");
				}else
				{
					Environment.loger.log(Level.ERROR,"Approve icon is not present");
					Reporter.Write("Val'date Approve Icon", "Approve icon should be Present", "Approve icon is not present", "Fail");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Activate icon is not present");
				Reporter.Write("Val'date Activate Icon", "Activate icon should be Present", "Activate icon is not present", "Fail");
			}

		}else{
			Environment.loger.log(Level.ERROR, "GC Setup For Property Status Not Changed To Staging");
			Reporter.Write("Validate GCSetUp status", "GC Setup For Property Status Successfully Changed To Staging", "GC Setup For Property Status Not Changed To Staging", "Fail");
		}
	}


	@AfterClass
	public void EndTest(){

		Reporter.StopTest();		
	}		
}



