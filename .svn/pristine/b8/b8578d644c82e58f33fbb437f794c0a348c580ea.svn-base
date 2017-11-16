package testscripts.gcRegression;
/** Purpose		: Validate that Property Description is displayed while creating AHBB SBMP offer Westin  In English language when GC setup has it
 * TestCase Name: Validate that Property Description is displayed while creating AHBB SBMP offer Westin  In English language when GC setup has it
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.Calendar;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class GC_REG29_ValidatePropertyDescriptionDisplayedAHBBD2Offer {

	CRM SW = new CRM();	
	String  OfferID, Property1Description,Property2Description;
	String Prop1="1965";
	String Prop2="1299";

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
	}
	@Test(priority=1)
	public void GetPropDescriptionFromGCSetup(){

		SW.GCLogin(SW.TestData("GCUsername"),SW.TestData("GCPassword"));
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}

		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetUp_Active_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", Prop1);
		SW.Click("GCSetUp_Apply_BN");
		if(!SW.ObjectExists("GCOffer_Overview_IC")){
			Environment.loger.log(Level.ERROR, "Overview Icon is not present");
			SW.FailCurrentTest("Overview Icon is not present");
		}
		SW.Click("GCOffer_Overview_IC");
		SW.WaitTillElementToBeClickable("GCSetUp_PropDesc_WT");
		SW.Wait(5);
		Property1Description=SW.GetText("GCSetUp_PropDesc_WT");
		SW.Wait(5);
		if(Property1Description.isEmpty()){
			Environment.loger.log(Level.ERROR, "Property 1 Description  is not selected ");
			SW.FailCurrentTest("Property 1 Description is not selected ");
		}else{
			Environment.loger.log(Level.INFO, "Selected Property 1 Description: " +Property1Description);
		}

		SW.Click("GCNavigation_GCSetup_LK");
		SW.Click("GCSetUp_Active_LK");
		SW.EnterValue("GCSetUp_PropertyID_EB", Prop2);
		SW.Click("GCSetUp_Apply_BN");
		if(!SW.ObjectExists("GCOffer_Overview_IC")){
			Environment.loger.log(Level.ERROR, "Overview Icon is not present");
			SW.FailCurrentTest("Overview Icon is not present");
		}
		SW.Click("GCOffer_Overview_IC");
		SW.WaitTillElementToBeClickable("GCSetUp_PropDesc_WT");
		SW.Wait(5);
		Property2Description=SW.GetText("GCSetUp_PropDesc_WT");
		SW.Wait(5);
		if(Property2Description.isEmpty()){
			Environment.loger.log(Level.ERROR, "Property 2 Description is not selected ");
			SW.FailCurrentTest("Property 2 Description is not selected ");
		}else{
			Environment.loger.log(Level.INFO, "Selected Property 2 Description: " +Property2Description);
		}


	}
	@Test(priority=2, dependsOnMethods="GetPropDescriptionFromGCSetup")
	public void validateAHBBPropDescription(){

		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreateAHBBSBMPOffer_LK");
		SW.EnterValue("GCAHBBMPMOffer_PropIDs_EB",Prop1+","+Prop2);
		SW.EnterValue("GCAHBBMPMOffer_InternalOfferName_EB", "AutomatedAHBBOfferName");
		SW.EnterValue("GCAHBBMPMOffer_PresentationStartDate_EB",  SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 7, Calendar.DATE));
		SW.EnterValue("GCAHBBMPMOffer_presentationEndDate_EB",  SW.DateAddDays(SW.GetTimeStamp("MM/dd/yyyy"), "MM/dd/yyyy", 7, Calendar.DATE));
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_Subject_EB", "Automation Email Subject");
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_PreHeader_EB", "Automation Email Header");
		SW.DropDown_SelectByIndex("GCAHBBMPMOffer_MessageSetupOfferSecHeading_DD", 1);
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop1);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", "Automatic Offer Header");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferSubHeading_EB", "Automatic Offer Sub Header");
		SW.Wait(15);
		SW.Click("GCAHBBSBMPOffer_RestoreDefault_BT");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Wait(15);
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		String UIDescriptionForProp1= SW.GetText("GCCreateBroadCastMsg_Message_EB");
		if(SW.CompareText(Property1Description, UIDescriptionForProp1)){

			Environment.loger.log(Level.INFO, "Property Description is loaded for 1st property");
		}else{
			Environment.loger.log(Level.ERROR, "Property Description is not loaded");
			SW.FailCurrentTest("Property Description is not loaded");
		}

		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_PropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_PropertyImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_PropertyImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_PropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_PropertyImageSelect_IC");
		
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_MobilePropertyImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageSelect_IC");
		
		SW.SelectRadioButton("GCAHBBMPMOffer_suppressCallToActionInd_RB");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT"))
		{
			Environment.loger.log(Level.INFO, "Primary offer created successfully");
		}else{
			Environment.loger.log(Level.INFO, "Primary offer is not created successfully");
			SW.FailCurrentTest("Primary offer is not created successfully");
		}
		// Create AMB second offer 
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop2);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", "Automatic Offer Header 2");
		SW.Wait(15);
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");

		String UIDescriptionForProp2= SW.GetText("GCCreateBroadCastMsg_Message_EB");
		if(SW.CompareText(Property2Description, UIDescriptionForProp2)){

			Environment.loger.log(Level.INFO, "Property Description is loaded for second property");
		}else{
			Environment.loger.log(Level.ERROR, "Property Description is not loaded");
			SW.FailCurrentTest("Property Description is not loaded");
		}
		SW.SwitchToFrame("");
	}

	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}
