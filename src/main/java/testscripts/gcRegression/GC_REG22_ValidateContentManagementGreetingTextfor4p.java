package testscripts.gcRegression;
/** Purpose		: Validate the image loading while editing brand rescan offer in Staging state and on selecting new image while editing
 * TestCase Name: Validate the image loading while editing brand rescan offer in Staging state and on selecting new image while editing
 * Created By	: Sharanya
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

public class GC_REG22_ValidateContentManagementGreetingTextfor4p {

	CRM SW = new CRM();	
	String UserName, Password;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		UserName=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}

	@Test(priority=1)
	public void UpdateGreetingTextinDB() {

		try{	
	    	//update the guest details in DB				
			SW.EstablishConnection("qa3");
			String UpdateQuery="update OFFER.BRAND_GREETING_MLANG set greeting_text='Thank you for staying with us. We hope you had a great stay and will return again soon.  If there is anything else that we can do for you, just let us know. <br/><br/>Hope to see you again soon,'where  brand_cd='4P' and locale_cd = 'en-US' and message_type_id=8 and division_id='831000'";
			SW.UpdateQuery(UpdateQuery);
			String query="select greeting_text from OFFER.BRAND_GREETING_MLANG where brand_cd='4P' and locale_cd = 'en-US' and message_type_id=8 and division_id='831000'";
			List<String> sGreetingText=SW.GetColumnValues(query, "greeting_text") ;
			Environment.loger.log(Level.INFO,"Greeting Text :"+sGreetingText);
			
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}		
	}
	
	@Test(priority=2, dependsOnMethods="UpdateGreetingTextinDB")
	public void RefreshGCSetupCache(){
		SW.GCLogin(UserName,Password);
		SW.Click("GCHome_Admin_LK");
		SW.Click("GC_GCAdmin_Lk");
		if(SW.ObjectExists("GC_CacheRefreshTable_TB")){
			SW.Click("GCSetup_SetupXMLCache_LK");
			//SW.Click("GCSetup_Content_LK");
			SW.Click("GCSetup_update_BT");
			Environment.loger.log(Level.INFO, "Cache refreshed successfully");
			SW.Click("GCSetup_Logout_LK");
			Environment.loger.log(Level.INFO, "logout Successfull");
		}

	}
	
	@Test(priority=3, dependsOnMethods="RefreshGCSetupCache")
	public void ValidateGreetingText(){
		SW.GCLogin(UserName,Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCHome_Admin_LK");
		SW.Click("GCAdmin_ContentMngmt_LK");
		SW.DropDown_SelectByText("GCContentMngmnt_FileType_DD","Greeting Text");
		SW.DropDown_SelectByText("GCContentMngmnt_LaguageType_DD","English (United States)");
		SW.DropDown_SelectByText("GCContentMngmnt_MsgType_DD","Post Stay Marketing");
		SW.DropDown_SelectByText("GCContentMngmnt_Division_DD","NORTH AMERICA DIV");
		SW.DropDown_SelectByText("GCContentMngmnt_Brand_DD","FOUR POINTS");
		SW.Click("GCContentMngmnt_Apply_BN");
		List<String> sGreetingTxt=SW.WebTbl_GetText("GCContentMngmnt_Result_TB",5);
		Environment.loger.log(Level.INFO, "Greeting Text: "+sGreetingTxt);
		
	}
	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}
