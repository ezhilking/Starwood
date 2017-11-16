package testscripts.Navigator;
/* Purpose		: This script for Load A Guest And Do Activity That Need NOT Any Security Verification
 * TestCase Name: Load A Guest And Do Activity That Need NOT Any Security Verification
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG41_LoadGuest_DoActivity_NeedNOTAnySecVerifiy {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	String sEmailId;
	String expected_SPGLabel = "SPG Preferred :";
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("WebUserID");	
		sEmailId="sample@abc.com";
	}
	@Test(priority=0)
	public void LocateGuestByNumber(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SearchGuestBySPGnumber(SPGNUMBER); //Search Guest by SPG number		
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		if(SW.ObjectExists("NavigatorReservationSearchPage_Ack_BT"))
			SW.NormalClick("NavigatorReservationSearchPage_Ack_BT");
		String Nav_SPGRetrieved = SW.GetText("NavigatorHomePage_SPGPreferredNum_DT");
		String actual_SPG_num = Nav_SPGRetrieved.substring(14); // retrieving the number from the entire text
		if(SW.CompareTextContained("SPGnum_validationInNavigator",SPGNUMBER, actual_SPG_num))
			Environment.loger.log(Level.INFO,"SPG Number In Navigator are matched!!!!");
		else{
			Environment.loger.log(Level.ERROR,"SPG Number not Matched in Navigator");
			SW.FailCurrentTest("Validation Fails in checking SPG Number");
		}
	}
	@Test(priority=1,dependsOnMethods="LocateGuestByNumber")
	public void updateWebPassword(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT");
		SW.NormalClick("NavigatorSearchPage_GuestNAme_DT");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_AccAccessInfo_LK");	
		SW.NormalClick("NavigatorSearchPage_AccAccessInfo_LK");
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_AccAccessMail_DT");
		if(SW.CompareTextContained("Compare mail sent", "Email Sent", SW.GetText("NavigatorSearchPage_AccAccessMail_DT")))
			Environment.loger.log(Level.INFO, "Mail has sent Successfully!!!");
		else {
			Environment.loger.log(Level.ERROR,"Unable to sent a mail for this user");
			SW.FailCurrentTest("Validation Fails in checking mail sent  Success message");
		}
	}
	@Test(priority=2,dependsOnMethods="updateWebPassword")
	public void UpdateStatus(){
				SW.WaitTillElementToBeClickable("NavigatorSearchPage_EditSPGInformation_LK");
				SW.NormalClick("NavigatorSearchPage_EditSPGInformation_LK");
				SW.DropDown_SelectByText("NavigatorSearchPage_SPGLevel_DD", SW.TestData("SPGLevelToUpdate"));
				SW.Wait(3);
				SW.DropDown_SelectByIndex("NavigatorSearchPage_SPGChangeReason_DD", 1);
				SW.NormalClick("NavigatorSearchPage_SaveInfoCardChanges_BT");
				SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_GetSPGType_DT");
				String actual_SPGType = SW.GetText("NavigatorSearchPage_GetSPGType_DT");
				SW.CompareText("SPGType_comparision", expected_SPGLabel, actual_SPGType);
	}
	@Test(priority=3,dependsOnMethods="UpdateStatus")
	public void kitRequest(){
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_CommPreference_LK");
		SW.NormalClick("NavigatorSearchPage_CommPrefAddress_LK");
		if(SW.CompareText("REQUEST NEW KIT", SW.GetText("NavigatorSearchpage_CreateKitRequest_LK"))){
			SW.NormalClick("NavigatorSearchpage_CreateKitRequest_LK");
			if(SW.CompareText("Compare Successfull message","Successfully Requested", SW.GetText("NavigatorSearchPage_KitRequestMsg_DT")))
				Environment.loger.log(Level.INFO, "Kit request has created Successfully!!!");
			else {
				Environment.loger.log(Level.ERROR,"Kit Request not created");
				SW.FailCurrentTest("Validation Fails in checking Kit Request Success message");
			}
		}else
			Environment.loger.log(Level.INFO, "Kit Request has already created.......!!!!!");
		if(SW.CompareText("Cancel Kit Request", SW.GetText("NavigatorSearchpage_CreateKitRequest_LK"))){
			SW.NormalClick("NavigatorSearchpage_CreateKitRequest_LK");
			if(SW.CompareText("Compare Successfull message","Kit Request Canceled", SW.GetText("NavigatorEditPage_CancelKit_DT")))
				Environment.loger.log(Level.INFO, "Kit request has Cancelled Successfully!!!");
			else {
				Environment.loger.log(Level.ERROR,"Kit Request not Cancelled");
				SW.FailCurrentTest("Validation Fails in checking Kit Request Cancel message");
			}
		}else
			Environment.loger.log(Level.INFO, "Kit Request has already Cancelled.......!!!!!");
		}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
