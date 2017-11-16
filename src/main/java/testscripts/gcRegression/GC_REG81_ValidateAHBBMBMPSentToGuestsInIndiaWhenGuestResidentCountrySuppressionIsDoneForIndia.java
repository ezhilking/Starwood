package testscripts.gcRegression;
/** Purpose		: Validate_AHBB MBMP_English_sent to the guests in India when Guest_Resident Country Suppression_done for India and end date for suppression is before the time period_when_AHBB mail is sent to the guest
 * TestCase Name: GC_REG81_ValidateAHBBMBMPSentToGuestsInIndiaWhenGuestResidentCountrySuppressionIsDoneForIndia
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

public class GC_REG81_ValidateAHBBMBMPSentToGuestsInIndiaWhenGuestResidentCountrySuppressionIsDoneForIndia {

	CRM SW = new CRM();
	String PropertyID="9500,236";
	String Prop1, Prop2, EmailPreHeader="AHBB MPBMP Offer";
	String Username,Password, OfferID, GMPID;
	String TestCaseName= getClass().getName();

	@BeforeClass
	public void StarTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");

		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			Prop1="9500";
			Prop2="236";
			GMPID="40003930361";
		}
	}

	@Test(priority=1)
	public void CreateAHBBOffer(){
		SW.GCLogin(Username, Password);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}

		//AHBB MBMP Offer Creation

		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreateAHBBMPMOffer_LK");

		SW.EnterValue("GCAHBBMPMOffer_PropIDs_EB",Prop1+","+Prop2);
		SW.EnterValue("GCAHBBMPMOffer_InternalOfferName_EB", "AutomatedAHBBOfferName");
		SW.EnterValue("GCAHBBMPMOffer_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCAHBBMPMOffer_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("GCCreateResConf_Language_DD","English (United States)");
		SW.Click("GCAHBBMPMOffer_Continue_BN");

		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		SW.Click("GCAHBBMPMOffer_Continue_BN");

		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_Subject_EB", "AHBB MBMP Email Subject Line");
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_PreHeader_EB", "AHBB MBMP Email Pre-header");

		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");

		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop1);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");
		SW.RefreshPage();
		SW.Wait(10);
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", "AHBB MBMP Primary Offer Headline");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferSubHeading_EB", "AHBB MBMP Primary Offer Sub Headline");
		SW.Wait(5);
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Click("GCAHBBMPMOffer_MessageSetupOfferDescription_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferDescription_EB", "AHBB MBMP Primary Offer Description");
		SW.Wait(10);
		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_BrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_BrandImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_BrandImageSelect_IC");
		SW.Wait(10);
		SW.SwitchToFrame("");

		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MobileBrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobileBrandImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_MobileBrandImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_MobileBrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobileBrandImageSelect_IC");


		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.Google.com");
		SW.EnterValue("GCCreateResConf_Offername_EB", "AHBBMBMPTEST");
		SW.EnterValue("GCCreateResConf_Regionname_EB", "Test1234");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT")){
			Environment.loger.log(Level.INFO, "Primary offer created successfully");
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

			SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_BrandImageExpand_IC");
			SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
			if(!SW.ObjectExists("GCAHBBMPMOffer_BrandImageSelect_IC"))
				SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
			SW.NormalClick("GCAHBBMPMOffer_BrandImageSelect_IC");

			SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.Google.com");
			SW.EnterValue("GCCreateResConf_Offername_EB", "AHBBMBMPTEST");
			SW.EnterValue("GCCreateResConf_Regionname_EB", "Test1234");
			SW.Click("GCAHBBMPMOffer_Continue_BN");


			SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
			if(SW.ObjectExists("GCAHBBMPMOffer_Continue_BN")){
				Environment.loger.log(Level.INFO,"AHBB Secondary Offer created successfully");
				SW.Click("GCAHBBMPMOffer_Continue_BN");
				SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_OfferSubmit_BN");
				if(SW.ObjectExists("GCAHBBMPMOffer_OfferSubmit_BN")){
					Environment.loger.log(Level.INFO,"AHBB Offer navigation successfull");
					SW.Click("GCAHBBMPMOffer_OfferSubmit_BN");
					SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
					if (SW.ObjectExists("GCHome_GreenMsg_DT"))
					{
						String sSuccessMessage=SW.GetText("GCHome_GreenMsg_DT");
						Environment.loger.log(Level.INFO,sSuccessMessage );
						OfferID=sSuccessMessage.substring(sSuccessMessage.indexOf("Offer")+5, sSuccessMessage.indexOf("'s")).trim();
						Environment.loger.log(Level.INFO,"Offer is created successfully");
						Environment.loger.log(Level.INFO,"Created OfferId "+OfferID);

						// Offer Activation

						SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
						SW.Click("GCOfferCreate_Submit_BN");
						SW.WaitTillElementToBeClickable("GCOffer_ApproveIt_IC");
						if(SW.ObjectExists("GCOffer_ApproveIt_IC")){
							SW.Click("GCOffer_ApproveIt_IC");
							SW.ClickAndProceed("GCOffer_Approve_BT");
							SW.HandleAlert(true);
							SW.WaitForPageload();
							SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
							Environment.loger.log(Level.INFO, "Offer Is Approved Successfully");
							SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
							SW.Click("GCOfferCreate_Submit_BN");
							SW.WaitTillElementToBeClickable("GCOffer_Generate_IC");
							if(SW.ObjectExists("GCOffer_Generate_IC")){
								SW.Click("GCOffer_Generate_IC");
								SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
								Environment.loger.log(Level.INFO, "Offer Is Generated Successfully");
								SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
								SW.Click("GCOfferCreate_Submit_BN");
								SW.WaitTillElementToBeClickable("GCOffer_Publish_IC");
								if(SW.ObjectExists("GCOffer_Publish_IC")){
									SW.Click("GCOffer_Publish_IC");
									SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
									Environment.loger.log(Level.INFO, "Offer Is Published Successfully");
									SW.EnterValue("GCOffer_SearchCriteria_EB",OfferID);
									SW.Click("GCOfferCreate_Submit_BN");
									SW.WaitTillElementToBeClickable("GCOffer_Activate_IC");
									if(SW.ObjectExists("GCOffer_Activate_IC")){
										SW.Click("GCOffer_Activate_IC");
										SW.WaitTillElementToBeClickable("GCHome_GreenMsg_DT");
										Environment.loger.log(Level.INFO, "Offer Is Activated Successfully");

										//DACP cache refresh in OMT Admin Tab

										SW.Click("GCHome_Admin_LK");
										//SW.Click("GCAdmin_CacheAdmin_LK");
										SW.Click("//a[text()='GC Admin']");
										SW.WaitTillElementToBeClickable("//a[text()='com.starwood.util.DACPLookupCache']");
										if(SW.ObjectExists("//a[text()='com.starwood.util.DACPLookupCache']")){
											SW.Click("//a[text()='com.starwood.util.DACPLookupCache']");
											SW.Click("//input[@value='update']");
											SW.Wait(5);
											SW.Click("//a[@class='managecache']/span");
											Environment.loger.log(Level.INFO, "'util.DACPLookupCache' is refreshed in OMT");
											Reporter.Write("util.DACPLookupCacheRefresh", "util.DACPLookupCache should be refreshed in OMT", "util.DACPLookupCache is refreshed in OMT", "PASS");
											
											SW.WaitTillElementToBeClickable("//a[text()='com.starwood.gc.util.DACPLookupCache']");
											if(SW.ObjectExists("//a[text()='com.starwood.gc.util.DACPLookupCache']")){
												SW.Click("//a[text()='com.starwood.gc.util.DACPLookupCache']");
												SW.Click("//input[@value='update']");
												SW.Wait(5);
												SW.Click("//a[@class='managecache']/span");
												Environment.loger.log(Level.INFO, "'gc.util.DACPLookupCache' is refreshed in OMT");
												Reporter.Write("gc.util.DACPLookupCache Refresh", "gc.util.DACPLookupCache should be refreshed in OMT", "gc.util.DACPLookupCache is refreshed in OMT", "PASS");
											}
											else{
												Environment.loger.log(Level.ERROR, "'gc.util.DACPLookupCache' refresh failed");
												Reporter.Write("Validate whether the cache is refreshed", "'gc.util.DACPLookupCache' is refreshed", "'gc.util.DACPLookupCache' refresh failed", "Fail");
											}
										}else{
											Environment.loger.log(Level.ERROR, "'util.DACPLookupCache' refresh failed");
											Reporter.Write("Validate whether the cache is refreshed", "'util.DACPLookupCache' is refreshed", "'util.DACPLookupCache' refresh failed", "Fail");
										}

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
					}else{
						Environment.loger.log(Level.INFO, "AHBB Offer navigation is not successfull");
						Reporter.Write("Validate AHBB Offer creation flow", "AHBB Offer navigation is successfull", "AHBB Offer navigation is not successfull", "Fail");
					}
				}else{
					Environment.loger.log(Level.INFO, "AHBB Secondary Offer is not created successfull");
					Reporter.Write("Validate secondary offer is created", "AHBB Secondary Offer is created successfully", "AHBB Secondary Offer is not created successfully", "Fail");
				}
			}else{
				Environment.loger.log(Level.ERROR, "Primary offer is not created successfully");
				Reporter.Write("Validate primary offer", "Primary offer should be created successfully", "Primary offer is not created successfully", "Fail");
			}
		}

	}

	//DACP cache refresh in BOP Tab

	@Test(priority=2, dependsOnMethods="CreateAHBBOffer")
	public void DACPCacheRefreshInBOP(){
		try{
			SW.Wait(40);
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(Username,Password);
			SW.Click("BopHome_GCAdmin_Lk");
			SW.Wait(20);
			/*SW.WaitTillElementToBeClickable("GCBOP_DACPCache1_LK");
			if(SW.ObjectExists("GCBOP_DACPCache1_LK")){
				SW.Click("GCBOP_DACPCache1_LK");
				SW.Wait(15);
				SW.Click("GCBOP_DACPCache_Update_BT");
				SW.Wait(15);
				Environment.loger.log(Level.INFO, "'util.DACPLookupCache' is refreshed in BOP");
				SW.Click("GCBOP_Cache_LK");
				SW.WaitTillElementToBeClickable("GCBOP_DACPCache2_LK");
				if(SW.ObjectExists("GCBOP_DACPCache2_LK")){
					SW.Click("GCBOP_DACPCache2_LK");
					SW.Wait(15);
					SW.Click("GCBOP_DACPCache_Update_BT");
					SW.Wait(15);
					Environment.loger.log(Level.INFO, "'gc.util.DACPLookupCache' is refreshed in BOP"); */
			SW.WaitTillElementToBeClickable("//a[text()='com.starwood.util.DACPLookupCache']");
			if(SW.ObjectExists("//a[text()='com.starwood.util.DACPLookupCache']")){
				SW.Click("//a[text()='com.starwood.util.DACPLookupCache']");
				SW.Click("//input[@value='update']");
				SW.Wait(5);
				SW.Click("//a[@class='managecache']/span");
				Environment.loger.log(Level.INFO, "'util.DACPLookupCache' is refreshed in OMT");
				Reporter.Write("util.DACPLookupCacheRefresh", "util.DACPLookupCache should be refreshed in OMT", "util.DACPLookupCache is refreshed in OMT", "PASS");
				
				SW.WaitTillElementToBeClickable("//a[text()='com.starwood.gc.util.DACPLookupCache']");
				if(SW.ObjectExists("//a[text()='com.starwood.gc.util.DACPLookupCache']")){
					SW.Click("//a[text()='com.starwood.gc.util.DACPLookupCache']");
					SW.Click("//input[@value='update']");
					SW.Wait(5);
					SW.Click("//a[@class='managecache']/span");
					Environment.loger.log(Level.INFO, "'gc.util.DACPLookupCache' is refreshed in OMT");
					Reporter.Write("gc.util.DACPLookupCache Refresh", "gc.util.DACPLookupCache should be refreshed in OMT", "gc.util.DACPLookupCache is refreshed in OMT", "PASS");
				}
				else{
					Environment.loger.log(Level.ERROR, "'gc.util.DACPLookupCache' refresh failed");
					Reporter.Write("Validate whether the cache is refreshed", "'gc.util.DACPLookupCache' is refreshed", "'gc.util.DACPLookupCache' refresh failed", "Fail");
				}	
			}else{
				Environment.loger.log(Level.ERROR, "'util.DACPLookupCache' refresh failed");
				Reporter.Write("Validate whether the cache is refreshed", "'util.DACPLookupCache' is refreshed", "'util.DACPLookupCache' refresh failed", "Fail");
			}	
		}catch(Exception e){
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			Reporter.Write("Validate DACP Cache Refresh in BOP", "DACP Cache Refresh in BOP is Success", "DACP Cache Refresh in BOP is failed", "Fail");
		}
	}


	@Test(priority=3, dependsOnMethods="DACPCacheRefreshInBOP")
	public void DatabaseUpdateToClearAHBBEntries(){

		try{	
			//update the guest details in DB	
			SW.EstablishConnection(Environment.getRunEnvironment());
			String Query1="update offer.outbound_message set message_type_id=0 where message_type_id=9 and sent_date > trunc ( sysdate - 8 ) and email_address is not NULL";
			SW.UpdateQuery(Query1);
			String Query2="select * from offer.outbound_message where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address is not NULL";
			if(!SW.RecordExists(Query2)){
				Environment.loger.log(Level.INFO, "Old AHBB Entries Are Cleared From DB");
				Reporter.Write("Validate DB updation", "DB updation should be successfull", "DB updation is successfull", "PASS");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			Reporter.Write("Validate DB updation", "DB updation is successfull", "Failed during db Update", "Fail");
		}

	}	



	@Test(priority=4, dependsOnMethods="DatabaseUpdateToClearAHBBEntries")
	public void RunAHBBTask(){
		try{
			SW.Wait(40);
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(Username,Password);
			SW.Click("BopHome_GCAdmin_Lk");
			SW.NavigateTo(Environment.BoBTaskRunner);
			SW.Click("BoBConfigFactory_Lk");
			SW.DropDown_SelectByText("BoB_TaskRunner_PropFilter_DD", "com.starwood.gcc.service.propfilter.ProductionNoFilter");
			SW.DropDown_SelectByText("BoB_TaskRunner_MailFilter_DD","com.starwood.sgc.service.mockfilters.SendToAllRecipientFilter");
			SW.DropDown_SelectByText("BoB_TaskRunner_AHBBFilter_DD", "com.starwood.sgc.jobs.ahbb.SendToAllCandidateFilter");
			SW.CheckBox("BoB_TaskRunner_AHBBExpire_CB", "OFF");
			SW.NormalClick("BoB_TaskRunner_Update_BT");
			SW.Click("BoBTaskRunner_Lk");
			SW.EnterValue("BoB_TaskRunner_Pwd_EB","SGC");
			SW.Click("BOB_TaskRunner_AHBBTask_BT");
			String sSucessMessagge="BoB_TaskRunner_AHBBSuccessMsg_DT";
			if(SW.ObjectExists(sSucessMessagge)){
				Environment.loger.log(Level.INFO,"AHBB Task Run SuccessFull");
			}else
			{
				Environment.loger.log(Level.ERROR, "AHBB Task Run failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				Reporter.Write("Validate AHBB Task Run", "AHBB Task Run Success", "AHBB Task Run failed", "Fail");
			}

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			Reporter.Write("Validate AHBB Task Run", "AHBB Task Run Success", "AHBB Task Run failed", "Fail");
		}
	}




	@Test(priority=5, dependsOnMethods="RunAHBBTask")
	public void DatabaseUpdateToCheckTheNewEntry(){
		try{	
			//update the guest details in DB	
			SW.EstablishConnection(Environment.getRunEnvironment());
			String Query1="select * from offer.outbound_message where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address='SINDHU.SR@ACCENTURE.COM'";
			if(!SW.RecordExists(Query1)){
				Environment.loger.log(Level.INFO, "No Entry In DB With The GMPID '40003930361' ");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			Reporter.Write("Validate DB updation", "DB updation is successfull", "Failed during db Update", "Fail");
		}

	}	


	@AfterClass
	public void EndTest(){

		Reporter.StopTest();		
	}
}



