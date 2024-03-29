package testscripts.gcRegression;
/** Purpose		: Validate that the GC Setup Property Admin Enrollment Status is displayed
 * TestCase Name: GC_REG99_ValidateGCSetupPropertyAdminEnrollmentStatusIsDisplayed
 * Created By	: Sindhu SR
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

public class GC_REG99_ValidateGCSetupPropertyAdminEnrollmentStatusIsDisplayed {
	CRM SW = new CRM();
	String Username, Password, Username1, Password1;
	String PropertyID="110", sMessage, OfferID;

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}
	
	@Test(priority=1)
	public void NotEnrolledStatusValidation(){
		try{
			SW.GCLogin(Username, Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_DT")){
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
			}
			SW.Click("//*[@id='tabmenu']//a[text()='Property Admin']");
			SW.WaitTillElementToBeVisible("//*[@id='propertyAdminPropertyListTable']//tr[1]/td[1]");
			PropertyID = SW.GetText("//*[@id='propertyAdminPropertyListTable']//tr[1]/td[1]");
			SW.Wait(4);
			SW.EnterValue("(//*[@id='nospace']//input[@name='propertyId'])[1]", PropertyID);
			SW.Click("(//input[@value='View'])[1]");
			SW.Click("(//*[@id='fullnoborder']//input[@value='Edit'])[1]");
			SW.Click("//*[@id='showfirst']//input[@value='Update Enrollment']");
			SW.WaitTillElementToBeVisible("//select[@id='enrollmentActionId']");
			SW.DropDown_SelectByText("//select[@id='enrollmentActionId']", "Cancellation");
			SW.WaitTillElementToBeVisible("//select[@id='subscriptionPlanId']");
			SW.DropDown_SelectByText("//select[@id='subscriptionPlanId']", "STANDARD");
			SW.EnterValue("//*[@id='effectiveDate']",  SW.GetTimeStamp("MM/dd/yyyy"));
			SW.Click("(//input[@value='Save'])[1]");
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				String message = SW.GetText("GCHome_GreenMsg_DT");
				Environment.loger.log(Level.INFO,"Property Admin Enrollment status updated successfully: "+message);
			}
			SW.Click("//*[@id='tabmenu']//a[text()='Property Admin']");
			String status = SW.GetText("//*[@id='propertyAdminPropertyListTable']//tr[1]/td[5]");
			String SubscriptionPlan = SW.GetText("//*[@id='propertyAdminPropertyListTable']//tr[1]/td[4]");
			SW.CompareText("Status Validation",status , "Cancelled");
			SW.CompareText("Status Validation",SubscriptionPlan , "STANDARD");
			
			/* ------------------------------- */
			
		}catch (Exception e) {
			Environment.loger.log(Level.INFO,e);
		}
	}

/*	@Test(priority=1)
	public void NotEnrolledStatusValidation(){
		try{
			SW.GCLogin(Username, Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_DT")){
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
			}
			SW.Click("GCNavigation_GCSetup_LK");
		//	SW.Click("GCSetUp_Overview_IC");
			SW.Click("GCOffer_Overview_IC");
			//Validation of Enrollment Status in Work In Progress stage and capturing Property ID and Enrollment Status
			String PropertyID = SW.GetText("GC_GetPropertyIDOfGetSetup_ST");
			Environment.loger.log(Level.INFO, "Property ID = "+PropertyID+"");
			String EnrollmentStatus = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
			Environment.loger.log(Level.INFO, "Enrollment Status of "+PropertyID+" in Work In Progress stage = "+EnrollmentStatus+"");
			//Validation of Enrollment Status of the same above property in all stages of GC Setup
			SW.Click("GCNavigation_GCSetup_LK");
			SW.EnterValue("GCSetUp_PropertyID_EB", PropertyID);
			SW.Click("GCSetUp_Apply_BN");
			//Change The Property Status To Staging
			SW.Click("GCSetUp_Submit_LK");
			if(SW.ObjectExists("GCSetUp_SuccessMsg_DT")){
				Environment.loger.log(Level.INFO, "GC Setup for Property Status Successfully Changed to 'Staging' Stage");
				//Validation Of Enrollment Status In Staging
				SW.EnterValue("GCSetUp_PropertyID_EB", PropertyID);
				SW.Click("GCSetUp_Apply_BN");	
				SW.Click("GCSetUp_Overview_IC");
				String EnrollmentStatusInStaging = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
				SW.CompareText("Validate Enrollment Status in 'Staging' Stage", EnrollmentStatus, EnrollmentStatusInStaging);
				//Change The Property Status to Approve
				SW.Click("GCNavigation_GCSetup_LK");
				SW.Click("GCSetUp_Staging_LK");
				SW.Click("GCSetUp_Approve_LK");
				if(SW.ObjectExists("GCSetUp_SuccessMsg_DT")){
					Environment.loger.log(Level.INFO, "GC Setup for Property Status Successfully Changed to 'Approved' Stage");
					//Validation Of Enrollment Status In Approved
					SW.Click("GCSetUp_Overview_IC");
					String EnrollmentStatusInApproved = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
					SW.CompareText("Validate Enrollment Status in 'Approved' Stage", EnrollmentStatus, EnrollmentStatusInApproved);
					//Activate The Property Status
					SW.Click("GCNavigation_GCSetup_LK");
					SW.Click("GCSetup_ApprovedStage_LK");
					SW.Click("GCSetUp_Activate_LK");
					if(SW.ObjectExists("GCSetUp_SuccessMsg_DT")){
						Environment.loger.log(Level.INFO, "GC Setup for Property Status Successfully Changed to 'Active' Stage");
						//Validation Of Enrollment Status In Approved
						SW.Click("GCSetUp_Overview_IC");
						String EnrollmentStatusInActive= SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
						SW.CompareText("Validate Enrollment Status in 'Active' Stage", EnrollmentStatus, EnrollmentStatusInActive);
					}else{
						Reporter.Write("Validate Property Status Changed To Active", "Property Status is changed to Active", "Property Status Is Not Changed To Active", "Fail");
					}
				}else{
					Reporter.Write("Validate Property Status Changed To Approved", "Property Status is changed to Approved", "Property Status Is Not Changed To Approved", "Fail");
				}

			}else{
				Reporter.Write("Validate Property Status Changed To Staging", "Property Status is changed to Staging", "Property Status Is Not Changed To Staging", "Fail");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Alpha And Special Chars Validation in Properties Field Of 'Property Access Expiration' Window Is Failed",e);
		}
	}

	@Test(priority=2, dependsOnMethods="NotEnrolledStatusValidation")
	public void OtherEnrollmemtStatusValidation(){

		//Validation of 'Enrolled ' Enrollment Status
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetup_ActiveStage_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", "15");
		SW.Click("GCSetUp_Apply_BN");
		SW.Click("GCSetUp_Overview_IC");
		String EnrolledEnrollmentStatus = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
		SW.CompareText("Validate Enrollment Status", "Enrolled", EnrolledEnrollmentStatus);

		//Validation of 'Deactivated' Enrollment Status
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetup_ActiveStage_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", "13");
		SW.Click("GCSetUp_Apply_BN");
		SW.Click("GCSetUp_Overview_IC");
		String DeactivatedEnrollmentStatus = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
		SW.CompareText("Validate Enrollment Status", "Deactivated", DeactivatedEnrollmentStatus);

		//Validation of 'Cancelled' Enrollment Status
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetup_ActiveStage_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", "2003");
		SW.Click("GCSetUp_Apply_BN");
		SW.Click("GCSetUp_Overview_IC");
		String CancelledEnrollmentStatus = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
		SW.CompareText("Validate Enrollment Status", "Cancelled", CancelledEnrollmentStatus);

		//Validation of 'Cancelled' Enrollment Status
		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetup_ActiveStage_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", "4542");
		SW.Click("GCSetUp_Apply_BN");
		SW.Click("GCSetUp_Overview_IC");
		String PreEnrolledEnrollmentStatus = SW.GetText("GC_GetEnrollmentStatusOfGetSetup_ST");
		SW.CompareText("Validate Enrollment Status", "Pre-Enrolled", PreEnrolledEnrollmentStatus);

	} */

	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}