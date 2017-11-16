package testscripts.CSF;
/** Purpose		: Validate Comments in CSV files  
 * TestCase Name: Validate Comments in CSV files  
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG25_CreateCSVFileWithSingleLineComment {
	CRM SW = new CRM();
	String CSFFileNo,InternalComment, CustomerComment, AssociateComment,CaedComment;
	String sPropId = "1965";
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.CSFURL);
	}
	@Test(priority=0)
	public void CreateCSVWithComments(){
		SW.CSFLogin(SW.TestData("CSFUsername"), SW.TestData("CSFPassword"));
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.EnterValue("CSFHome_Firstname_EB", SW.RandomString(5));
		SW.EnterValue("CSFHome_Lastaname_EB", SW.RandomString(5));
		SW.SelectRadioButton("CSFHome_GuestYes_RB");
		SW.EnterValue("CSFHome_PropertyID_EB", SW.TestData("PropertyID"));
		SW.Click("CSFHome_Find_BN");
		SW.DropDown_SelectByText("CSFHome_TypeCreate_DD", "Customer Service");
		SW.Click("CSFHome_CreateNewCSF_BN");
		SW.WaitForAppLoad();
		if(SW.ObjectExists("CSF_AcknowledgePopUp_LK")){
			SW.Click("CSF_AcknowledgePopUp_LK");
		}
		SW.DropDown_SelectByText("CSFSummary_IntialContact_DD", "Email");
		int phnumber=SW.RandomInteger(10);
		SW.EnterValue("CSFSummary_PrimaryPhoneNo_EB", phnumber);
		SW.EnterValue("CSFSummary_Email_EB", "test@accenture.com");
		SW.DropDown_SelectByText("CSFSummary_NoRESconf_DD", "No res conf# found");
		SW.Click("CSFNavigation_Details_LK");
		SW.SelectRadioButton("CSFDetails_Compliment_RB");
		SW.DropDown_SelectByIndex("CSFDetail_Top10Sel_DD", 1);
		String GCDText=SW.DropDown_GetSelectedText("CSFDetail_Top10Sel_DD");
		SW.Click("CSFDetails_AddGCD_BN");
		SW.WaitTillElementToBeClickable("CSFDetail_AddGCD_WT");
		if(SW.CompareTextContained("GCD ADDED ", GCDText, SW.GetText("CSFDetail_AddGCD_WT")))
			Environment.loger.log(Level.INFO, "GCD has Added ");
		//Add comment
		SW.Click("CSFDetails_AddComments_BT");
		InternalComment=SW.RandomString(5);
		CustomerComment=SW.RandomString(5);
		AssociateComment=SW.RandomString(5);
		CaedComment=SW.RandomString(5);
		SW.EnterValue("CSFDetails_InternalComment_EB", InternalComment);
		SW.EnterValue("CSFDetails_CustomerComment_EB", CustomerComment);
		SW.EnterValue("CSFDetails_AssociateComment_EB", AssociateComment);
		SW.EnterValue("CSFDetails_CaedComment_EB", CaedComment);
		SW.NormalClick("CSFDetails_CloseComment_IC");
		SW.ClickAndProceed("CSFDetails_SaveAndExit_BN");
		SW.Wait(8);
		SW.HandleAlert(true);
		SW.WaitTillElementToBeClickable("CSFHome_CSFNumber_LK");
		SW.Wait(10);
		String CSFText = SW.GetText("CSFHome_CSFNumber_LK");
		CSFFileNo = CSFText.substring(CSFText.indexOf("(")+1, CSFText.indexOf(")"));
		Environment.loger.log(Level.INFO, "Your CSF File Number is "+CSFFileNo);


	}
	@Test(priority=1, dependsOnMethods="CreateCSVWithComments")
	public void validateComments(){
		SW.Click("CSFHome_CSFNumber_LK");
		SW.CompareText("ValidateInternalComment", InternalComment, SW.GetText("CSFSummary_InternalComment_DT"));
		SW.CompareText("ValidateCustomerComment", CustomerComment, SW.GetText("CSFSummary_CustomerComment_DT"));
		SW.CompareText("ValidateInternalComment", AssociateComment, SW.GetText("CSFSummary_AssociateComment_DT"));
		SW.CompareText("ValidateInternalComment", CaedComment, SW.GetText("CSFSummary_CaedComment_DT"));

		SW.ClickAndProceed("CSF_Logout_LK");
		SW.HandleAlert(true);
		SW.Wait(3);
		SW.HandleAlert(true);
	}
	@AfterClass
	public void EndTest() {
		Reporter.StopTest();
	}
}
