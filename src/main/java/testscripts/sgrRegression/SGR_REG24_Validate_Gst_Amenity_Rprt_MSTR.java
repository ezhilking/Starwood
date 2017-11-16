package testscripts.sgrRegression;
/** Purpose		: Validate_Guest Reports_Amenity Report_Microstrategy
 * TestCase Name: Validate_Guest Reports_Amenity Report_Microstrategy
 * Created By	: Sachin G
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

public class SGR_REG24_Validate_Gst_Amenity_Rprt_MSTR {
	CRM SW = new CRM();
	String EventNotes,ReservationNo;
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void LocateGuestInMAR(){
	
	//		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");  	// STage
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1513");	//	QA3
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRNavigation_Aminity_LK");
			SW.DropDown_SelectByIndex("SGRAminity_Detail_DD", 0);
			String SelectedDetail=SW.GetText("SGRAminity_DetailsOption1_DD");
			SW.CheckBox("SGRAminity_Escalation_CB", "ON");
			SW.CheckBox("SGRAminity_Active_CB", "ON");
			SW.Click("SGRAminity_save_BT");
			if(SW.ObjectExists("SGRAminity_saveConfirmation_ST")){
				Environment.loger.log(Level.INFO, "Aminity Update saved successfully!");
				SW.GetScreenshot("AminitySaveConfirmation");
			}else{
				Environment.loger.log(Level.INFO, "Failed to update Aminity ");
				SW.FailCurrentTest("Failed to update Aminity");
			}
			SW.Click("SGRNavigation_Admin_LK");
			//Publish the changes
			SW.Click("SGRAdmin_Publish_LK");
			SW.Click("SGRPublish_PublishConfYes_BT");
			if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
				Environment.loger.log(Level.INFO, "Successfully published changes!");
				SW.GetScreenshot("PublishSuccess");
			}else{
				Environment.loger.log(Level.ERROR, "Publish failed!");
				SW.FailCurrentTest("Publish failed!");
			}
			SW.Click("SGRNavigation_Home_LK");
			SW.Wait(15);
			SW.SwitchToFrame("SGRHomepage_Arriving_FR");
			SW.SwitchToFrame("SGRHomepage_ArrivingSVOQI_FR");
			String FirstGuestLink="(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]";
			SW.WaitTillElementToBeClickable("(//div[@class='t']//a[contains(@href, 'resConf' and not (contains(@href,'gmp=0')))])[1]");
			if(!SW.ObjectExists(FirstGuestLink)){
				Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
				SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
			}

			ReservationNo=SW.GetAttributeValue(FirstGuestLink, "href");
			ReservationNo=ReservationNo.substring(ReservationNo.indexOf("resConf=")+8,ReservationNo.indexOf("&roomSeq"));
			Environment.loger.log(Level.INFO, "Reservation Number Selected is :"+ReservationNo);
			SW.Click(FirstGuestLink);
			SW.WaitTillElementToBeClickable("SGRGuestProfile_CreateNewAmenity_BT");
			SW.Click("SGRGuestProfile_CreateNewAmenity_BT");
			SW.EnterValue("SGRCreateEvent_Where_EB", "TBD");
			SW.EnterValue("SGRAddEvent_What_EB", "Amenity");
			SW.Wait(5);
			SW.Click("//ul[@class='ac_results']//li[1]/span");
			SW.DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
			SW.DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", 1);
			SW.CheckBox("SGRCreateEvent_Escalation_CB", "OFF");
			EventNotes=SW.RandomString(10);
			SW.EnterValue("SGRCreateEvent_Noted_EB", EventNotes);
			SW.Click("SGRCreateEvent_Save_BN");
			String EventID=SW.GetEventNumbeID();
			Environment.loger.log(Level.INFO, "Created Event ID- "+EventID);
			SW.Click("SGRNavigation_Reports_LK");
			SW.Click("SGRReports_GuestReports_LK");
			SW.DropDown_SelectByText("SGREventReports_ReportType_DD", "Amenity Report");
			SW.WaitForPageload();
			SW.CheckBox("SGRAmenity_Showhistory_CB", "OFF");
			SW.CheckBox("SGRReports_RunCachedReort_CB", "OFF");
			SW.Click("SGREventReports_GenerateReport_BN");
			SW.WaitForPageload();
			SW.WaitForWindowCount(3);
			SW.SwitchToWindow(3);
			SW.WaitForPageload();
			SW.Wait(30);
			String ReportText=SW.GetText("//div[@id='page']");
			SW.CompareTextContained("ValidateAmenity", SelectedDetail, ReportText);
			SW.CompareTextContained("ValidateAddedNotes", EventNotes, ReportText);
			SW.CloseOnlyThisBrowser();
			SW.SwitchToWindow(0);
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
