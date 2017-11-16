package testscripts.sgrRegression;
/* Purpose		: This test case is to map the VIP level mapping of SGR and validating.
 * TestCase Name: PMS Mapping screens_map one pref, one comment, one event dept, one VIP level_validate mapping is seen as saved/mapped
 * Created By	: Ezhilarasan.S 
 * Modified By	: Sachin. G
 * Modified Date: 04/08/2016
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

public class SGR_REG08_ValidatingLeadDetailExplorerPreview {
	CRM SW = new CRM();
	@BeforeClass
	public void StartTest() {
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test
	public void validatePriviewPackageInGuest(){
		try{
			SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), SW.TestData("SVOPropertyID"));
			int NumberOfGuests=0;
			int GuestCount=1;
			boolean GuestAvailable=false;
			String ConfNo=null;
			SW.WaitTillElementToBeClickable("SGRNavigation_Home_LK");
			SW.Click("SGRNavigation_Home_LK");
			SW.Wait(10);
			SW.SwitchToFrame("SGRHomepage_InHouse_FR");
			SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");

			//Get the number of guests who are not having SVO image in In-House list
			NumberOfGuests=SW.GetAllElements("(//table[@id='eventCountsTBL']//td[3]//a[@target='sgr' and not(following-sibling::img)])").size();

			if(NumberOfGuests==0){
				Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list for the selected property");
				SW.FailCurrentTest("No Guest present in Inhouse list for the selected property");
			}	

			// Iterate through till get the Guest not having Preview Package
			for(GuestCount=1;GuestCount<NumberOfGuests;GuestCount++){				
				SW.Click("(//table[@id='eventCountsTBL']//td[3]//a[@target='sgr' and not(following-sibling::img)])["+GuestCount+"]");	
				SW.Wait(5);
				SW.WaitTillInvisibilityOfElement("SGRSVO_LoadingMask_IC");
				SW.SwitchToFrame("SGRSVO_ResSearchLeadStatus_FR");
				
				SW.Wait(10);
				if(SW.ObjectExists("SGRResSearch_PreviewPackageNull_WT") && SW.ObjectExists("SGRResSearch_PreviewPackage_BN")){
					Environment.loger.log(Level.INFO, "Guest Don't have Priview Package");
					//SW.FailCurrentTest("Preview Package is already there for the selected guest ");
					GuestAvailable=true;
					break;					
				}
				SW.SwitchToFrame("");
				SW.Click("SGRNavigation_Home_LK");
				SW.SwitchToFrame("SGRHomepage_InHouse_FR");
				SW.SwitchToFrame("SGRHomepage_InHouseSVOQI_FR");				
			}

			//If Guest is not there who is not having Preview Package Fail the Test case  
			if(GuestAvailable==false){
				Environment.loger.log(Level.ERROR, "No Guest present in Inhouse list who is not having Preview Package");
				SW.FailCurrentTest("No Guest present in Inhouse list who is not having Preview Package");
			}

			SW.SwitchToFrame("");
			SW.WaitTillElementToBeClickable("SGRSVO_ZoomOut_IM");//Wait for the Zoom out icon to display.
			SW.NormalClick("SGRSVO_ZoomOut_IM");//Click wont work, because Zoomout tag name is 'img'.
			SW.WaitTillElementToBeClickable("SGRSVO_ReservationNo_ST");
			ConfNo = SW.GetText("SGRSVO_ReservationNo_ST");
			SW.SwitchToFrame("SGRSVO_ResSearchLeadStatus_FR");

			// Add the Preview Package 			
			if(SW.ObjectExists("SGRResSearch_PreviewPackageNull_WT")){//If this object present then it 'null'. i.e., Not having any data in table. So adding new package.
				SW.Click("SGRResSearch_PreviewPackage_BN");
				SW.SwitchToFrame("");
				SW.ClearValue("SGRGuestProfile_SearchCreteriaFirstname_EB");
				SW.EnterValue("SGRGuestProfile_SearchCreteriaLastname_EB", "ADAM");
				SW.Click("SGRGuestProfile_SearchCreteriaSearch_BN");
				SW.WaitTillInvisibilityOfElement("SGRGuestProfile_Loading_IC");
				SW.Click("SGRGuestProfile_SearchCreteriaLink_BN");
				SW.Wait(3);
				SW.SwitchToFrame("SGRSVO_ResSearchLeadStatus_FR");

			}
			SW.WaitTillElementToBeClickable("SGRResSearch_PreviewPackageNotNull_WT");

			//Validate After adding Preview Package to the guest 
			if(SW.ObjectExists("SGRResSearch_PreviewPackageNotNull_WT")){
				Environment.loger.log(Level.INFO, "SVOPreview Package is displayed");
			}else{
				SW.CompareText("SVOPreview should display", "SVOPreview should display", "SVOPreview is not display");
				Environment.loger.log(Level.ERROR, "SVOPreview Package is not displayed");
			}
			SW.SwitchToFrame("");
			// Change the guest type to reflect in home page in-house list so that next time it should not select same guest 
			SW.SwitchToFrame("SGRGuestProfile_LeadStatus_FR");
			SW.DropDown_SelectByText("SGRGuestProfile_GuestType_DD", "Preview Package TNR");
			SW.DropDown_SelectByIndex("SGRGuestProfile_LeadStatus_DD", SW.RandomNumber(1, SW.DropDown_GetSize("SGRGuestProfile_LeadStatus_DD")));
			SW.DropDown_SelectByIndex("SGRGuestProfile_ContactType_DD", SW.RandomNumber(1, SW.DropDown_GetSize("SGRGuestProfile_ContactType_DD")));
			SW.Click("SGRGuestProfile_LeadStatusSave_BN");
			SW.Wait(5);//Explicit thread wait for table load with new comment 
			
			
			SW.SwitchToFrame("");
			if(ConfNo!=null){
			SW.Click("SGRNavigation_ResSearch_LK");
			SW.EnterValue("SGRResSearch_StarLinkConf_EB", ConfNo);
			SW.Click("SGRResSearch_Submit_BN");
			SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
			SW.WaitForPageload();
			SW.WaitTillInvisibilityOfElement("SGRSVO_LoadingMask_IC");
			SW.SwitchToFrame("SGRSVO_ResSearchLeadStatus_FR");
			SW.WaitTillElementToBeClickable("SGRResSearch_PreviewPackageNotNull_WT");
			SW.Wait(3);
			if(SW.ObjectExists("SGRResSearch_PreviewPackageNotNull_WT")){
				Environment.loger.log(Level.INFO, "SVOPreview Package is displayed");
			}else{
				SW.CompareText("SVOPreview should display", "SVOPreview should display", "SVOPreview is not display");
				Environment.loger.log(Level.INFO, "SVOPreview Package is not displayed");
			}
			}
			SW.SwitchToFrame("");
			
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured: "+e.getMessage());
			SW.FailCurrentTest("Exception occured!!");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();	
	}
}
