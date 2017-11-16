package testscripts.gcRegression;
/** Purpose		: Validate Email Pre-header mandatory field in AHBB MBMP offer creation flow
 * TestCase Name: GC_REG77_ValidatePreheaderfieldAHBBMBMP
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: Sachin	
 * Reviewed Date: 10/03/2017
 */
import java.util.List;
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
public class GC_REG77_ValidatePreheaderfieldAHBBMBMP {

	CRM SW = new CRM();
	String Username,Password, OfferID;
	String Prop1, Prop2, EmailSub, EmailPreHeader="AHBB MPBMP Offer";
	String ErrorMsg="[Error...Please Correct The Following:\nEmail Pre-header Is Required.]";
	List<String> ErrorMsg1;

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");

		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			Prop1="1925"; //1471
			Prop2="1105";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			Prop1="110";
			Prop2="1960";	
		}
	}

	@Test 
	public void PreHeaderMandatoryFieldValidation(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreateAHBBMPMOffer_LK");

		/*Email Setup page*/
		SW.EnterValue("GCAHBBMPMOffer_PropIDs_EB",Prop1+","+Prop2);
		SW.EnterValue("GCAHBBMPMOffer_InternalOfferName_EB", "AutomatedAHBBOfferName");
		SW.EnterValue("GCAHBBMPMOffer_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCAHBBMPMOffer_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("GCCreateResConf_Language_DD","English (United States)");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		/*Eligibility Criteria page*/
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		/*Create/Edit Offer page - Email Pre-header mandatory field validation with Error message*/
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_Subject_EB", "AHBB MBMP Email Subject Line");
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		ErrorMsg1=SW.GetAllText("GC_PreHeaderErrorMsg_DT");
		SW.CompareText("Error message validation", ErrorMsg, ErrorMsg1.toString());

		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_Subject_EB", "AHBB MBMP Email Subject Line");
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_PreHeader_EB", "AHBB MBMP Email Pre-header");

		/*Create primary offer*/
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");

		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop1);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");

		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", "AHBB MBMP Primary Offer Headline");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferSubHeading_EB", "AHBB MBMP Primary Offer Sub Headline");

		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Click("GCAHBBMPMOffer_MessageSetupOfferDescription_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferDescription_EB", "AHBB MBMP Primary Offer Description");
		SW.Wait(10);
		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_PropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_PropertyImageExpand_IC");
		SW.Wait(5);
		if(!SW.ObjectExists("GCAHBBMPMOffer_PropertyImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_PropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_PropertyImageSelect_IC");


		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.Wait(5);
		if(!SW.ObjectExists("GCAHBBMPMOffer_MobilePropertyImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageSelect_IC");

		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.Google.com");
		SW.EnterValue("GCCreateResConf_Offername_EB", "AHBBMBMPTEST");
		SW.EnterValue("GCCreateResConf_Regionname_EB", "Test1234");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT")){
			Environment.loger.log(Level.INFO, "Primary offer created successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Primary offer is not created successfully");
			Reporter.Write("Validate primary offer", "Primary offer should be created successfully", "Primary offer is not created successfully", "Fail");
		}

		/*Create secondary offer*/
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");

		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop2);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");

		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", "AHBB MBMP Secondary Offer Headline");
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Click("GCAHBBMPMOffer_MessageSetupOfferDescription_EB"
				+ "");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferDescription_EB", "AHBB MBMP Secondary Offer Description");
		SW.SwitchToFrame("");

		SW.WaitTillElementToBeClickable("//li[@id='select.brandimagesHead']/span");
		SW.NormalClick("//li[@id='select.brandimagesHead']/span");
		if(!SW.ObjectExists("(//li[@id='select.brandimagesHead']//ul//li//a)[1]"))
			SW.NormalClick("//li[@id='select.brandimagesHead']/span");
		SW.NormalClick("(//li[@id='select.brandimagesHead']//ul//li//a)[1]");

		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.Google.com");
		SW.EnterValue("GCCreateResConf_Offername_EB", "AHBBMBMPTEST");
		SW.EnterValue("GCCreateResConf_Regionname_EB", "Test1234");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		if(SW.ObjectExists("GCAHBBMPMOffer_Continue_BN")){
			Environment.loger.log(Level.INFO,"AHBB Secondary Offer created successfull");
		}else{
			Environment.loger.log(Level.INFO, "AHBB Secondary Offer is not created successfull");
			Reporter.Write("Validate secondary offer is created", "AHBB Secondary Offer is created successfully", "AHBB Secondary Offer is not created successfully", "Fail");
		}
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_OfferSubmit_BN");
		if(SW.ObjectExists("GCAHBBMPMOffer_OfferSubmit_BN")){
			Environment.loger.log(Level.INFO,"AHBB Offer navigation successfull");
		}else{
			Environment.loger.log(Level.INFO, "AHBB Offer navigation is not successfull");
			Reporter.Write("Validate AHBB Offer creation flow", "AHBB Offer navigation is successfull", "AHBB Offer navigation is not successfull", "Fail");
		}
		SW.Click("GCAHBBMPMOffer_OfferSubmit_BN");




		SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
		if (SW.ObjectExists("GCHome_GreenMsg_DT"))
		{
			String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
			Environment.loger.log(Level.INFO,sSuccessMessage );
			OfferID=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
			Environment.loger.log(Level.INFO,"Offer is created successfully");
			SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
			SW.Click("GCOfferCreate_Submit_BN");
			SW.WaitTillElementToBeClickable("GCOffer_ApproveIt_IC");
			if(SW.ObjectExists("GCOffer_ApproveIt_IC")){
				SW.Click("GCOffer_ApproveIt_IC");
				SW.ClickAndProceed("GCOffer_Approve_BT");
				SW.HandleAlert(true);
				SW.WaitForPageload();
				SW.WaitTillElementToBeClickable("GCOffer_Generate_IC");
				if(SW.ObjectExists("GCOffer_Generate_IC")){
					SW.Click("GCOffer_Generate_IC");
					SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
					SW.Click("GCOfferCreate_Submit_BN");
					SW.WaitTillElementToBeClickable("GCOffer_Publish_IC");
					if(SW.ObjectExists("GCOffer_Publish_IC")){
						SW.Click("GCOffer_Publish_IC");
						SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
						SW.Click("GCOfferCreate_Submit_BN");
						SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
						if(SW.ObjectExists("GCOffer_Activate_IC")){
							SW.Click("GCOffer_Activate_IC");
							Environment.loger.log(Level.INFO,"Created OfferId "+OfferID);
					//		SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
						/*	if(SW.ObjectExists("GC_MyAccount_IC")){
								SW.NormalClick("GC_MyAccount_IC");
								SW.NormalClick("GCNavigation_SignOut_LK");
								//SW.Click("GCNavigation_SignOut_LK");
							}*/
						}else{
							Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
							Reporter.Write("Validate Offer Activation", "Offer should be activation","Offer activation failed", "Fail");
						}
					}else
					{
						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
						Reporter.Write("Validate Offer Publishment", "Offer should be published","Offer publishment failed", "Fail");
					}
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
					Reporter.Write("Validate Offer Generation", "Offer should be generated","Offer generation failed", "Fail");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
				Reporter.Write("Validate Offer Approval", "Offer should be approved","Offer approval failed", "Fail");
			}

		}

	}


	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}





