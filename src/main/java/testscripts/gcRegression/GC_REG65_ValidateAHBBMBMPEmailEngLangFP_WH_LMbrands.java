package testscripts.gcRegression;
/** Purpose		: Validate AHBB MBMP email in English language for FP, W hotels and LM brands
 * TestCase Name: Validate AHBB MBMP email in English language for FP, W hotels and LM brands
 * Created By	: Sharmila Begam
 * Modified By	: Sachin
 * Modified Date: 7/29/2016
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
public class GC_REG65_ValidateAHBBMBMPEmailEngLangFP_WH_LMbrands {
	CRM SW = new CRM();	
	String  OfferID,sUsername,sPassword;
	String Prop1;
	String Prop2;
	String Prop3;
	String GMPID;
	String EmailID;
	List<String> BrandID1;
	List<String> BrandID2;
	List<String> BrandID3;
	String EmailSub,EmailPreHeader;
	String OfferHead,OfferSubHead;//For first Prop
	String OfferHead2,OfferHead3;//for second prop & third prop
	String TestCaseName= getClass().getName();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		TestCaseName=TestCaseName.substring(TestCaseName.lastIndexOf(".")+1, TestCaseName.length()).trim();
		sUsername=SW.TestData("GCUsername");
		sPassword=SW.TestData("GCPassword");
		EmailID=SW.TestData("EmailID");

		if(Environment.getRunEnvironment().equalsIgnoreCase("QA3")){
			Prop1="1397";
			Prop2="1246";
			Prop3="1905";
			GMPID="40575987810";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			Prop1="";
			Prop2="";
			Prop3="";
			GMPID="";
		}
	}
	@Test(priority=1)
	public void CreateAHBBMBMPOfferEnglishLang(){
		SW.GCLogin(sUsername,sPassword);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreateAHBBMPMOffer_LK");
		SW.EnterValue("GCAHBBMPMOffer_PropIDs_EB",Prop1+","+Prop2+","+Prop3);
		SW.EnterValue("GCAHBBMPMOffer_InternalOfferName_EB", "AutomatedAHBBOfferName");
		SW.EnterValue("GCAHBBMPMOffer_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCAHBBMPMOffer_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("GCCreateResConf_Language_DD","English (United States)");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");

		SW.Click("GCAHBBMPMOffer_Continue_BN");
		if(SW.ObjectExists("GCAHBBMPMOffer_EmailSetup_Subject_EB")){
			Environment.loger.log(Level.INFO, "Offer Creation page navigation successfull");
		}else{
			Environment.loger.log(Level.ERROR, "Offer Creation page navigation failed");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Offer Creation page navigation failed");
		}
		EmailSub ="EmailSub"+SW.RandomString(15);
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_Subject_EB", EmailSub);
		EmailPreHeader="EmailPreHeader"+SW.RandomString(10);
		SW.EnterValue("GCAHBBMPMOffer_EmailSetup_PreHeader_EB", EmailPreHeader);
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop1);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");
		OfferHead ="AHBBMBMPOffer"+SW.RandomString(10);
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", OfferHead);
		OfferSubHead="OfferSubHead"+SW.RandomString(10);
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferSubHeading_EB", OfferSubHead);
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Click("GCAHBBMPMOffer_MessageSetupOfferDescription_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferDescription_EB", OfferHead+"Description");
		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_BrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_BrandImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_BrandImageSelect_IC");

		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MobileBrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobileBrandImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_MobileBrandImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_MobileBrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobileBrandImageSelect_IC");
		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.sample.com");

		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT")){
			Environment.loger.log(Level.INFO, "Primary offer created successfully");
		}else{
			Environment.loger.log(Level.ERROR, "Primary offer is not created successfully");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Primary offer is not created successfully");
		}
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyId_EB");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop2);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");
		OfferHead2="Secondary Prop OfferHead "+SW.RandomString(10);
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", OfferHead2);
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Click("GCAHBBMPMOffer_MessageSetupOfferDescription_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferDescription_EB", OfferHead2+"Description");
		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_BrandImageExpand_IC");

		SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_BrandImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_BrandImageSelect_IC");
		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.sample.com");

		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_Continue_BN")){
			Environment.loger.log(Level.INFO,"AHBB Secondary Offer created successfull");
		}else{
			Environment.loger.log(Level.INFO, "AHBB Secondary Offer is not created successfull");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("AHBB Secondary Offer is not created successfull");
		}
		SW.Click("GCAHBBMPMOffer_EmailSetup_CreateOffer_BT");
		SW.WaitTillElementToBeClickable("GCCreateResConf_PropertyId_EB");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop3);
		SW.SelectRadioButton("GCCreateResConf_offerObjctvType_RB");
		SW.CheckBox("GCCreateResConf_InstayBenefits_CB","ON");
		SW.DropDown_SelectByText("GCCreateResConf_GeoScope_DD","Global");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MessageSetupOfferHeading_EB");
		OfferHead3="Secondary Prop OfferHead2 "+SW.RandomString(10);
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferHeading_EB", OfferHead3);
		SW.SwitchToFrame("GCAHBBMPMOffer_MessageSetupOfferDescription_FR");
		SW.Click("GCAHBBMPMOffer_MessageSetupOfferDescription_EB");
		SW.EnterValue("GCAHBBMPMOffer_MessageSetupOfferDescription_EB", OfferHead3+"Description");
		SW.SwitchToFrame("");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_BrandImageExpand_IC");

		SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_BrandImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_BrandImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_BrandImageSelect_IC");
		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.sample.com");

		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_Continue_BN")){
			Environment.loger.log(Level.INFO,"AHBB Secondary2 Offer created successfull");
		}else{
			Environment.loger.log(Level.INFO, "AHBB Secondary2 Offer is not created successfull");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("AHBB Secondary Offer is not created successfull");
		}
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.Wait(5);
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_OfferSubmit_BN");
		if(SW.ObjectExists("GCAHBBMPMOffer_OfferSubmit_BN")){
			Environment.loger.log(Level.INFO,"AHBB Offer navigation successfull");
		}else{
			Environment.loger.log(Level.INFO, "AHBB Offer navigation is not successfull");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("AHBB Offer navigation is not successfull");
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
							SW.WaitTillElementToBeClickable("GCNavigation_SignOut_LK");
							SW.WriteToEmailTestData(TestCaseName, "EmailSubjectLine", EmailSub);
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString1", EmailPreHeader);
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString2", OfferHead);
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString3", OfferSubHead);
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString4", OfferHead2);
							SW.WriteToEmailTestData(TestCaseName, "ValiadtionString5", OfferHead3);
							if(SW.ObjectExists("GCNavigation_SignOut_LK")){
								SW.Click("GCNavigation_SignOut_LK");
							}
						}else{
							Environment.loger.log(Level.ERROR,"Offer Id Activation Failed");
							SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
							SW.FailCurrentTest("Offer Id Activation Failed");
						}
					}else
					{
						Environment.loger.log(Level.ERROR,"Offer Id Publish Failed");
						SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
						SW.FailCurrentTest("Offer Id Publish Failed");
					}
				}else{
					Environment.loger.log(Level.ERROR,"Offer Id Generation Failed");
					SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
					SW.FailCurrentTest("Offer Id Generation Failed");
				}
			}else{
				Environment.loger.log(Level.ERROR,"Offer Id Approval Failed");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Offer Id Approval Failed");
			}

		}
	}
	@Test(priority=2, dependsOnMethods="CreateAHBBMBMPOfferEnglishLang")
	public void databaseUpdateforAHBB(){

		try{	
			//update the guest details in DB	
			SW.EstablishConnection(Environment.getRunEnvironment());

			String Query1="select brand_id from ODSPROP.property_master where property_master_id in ("+Prop1+")";
			BrandID1=SW.GetColumnValues(Query1, "BRAND_ID");
			String Query2="select brand_id from ODSPROP.property_master where property_master_id in ("+Prop2+")";
			BrandID2=SW.GetColumnValues(Query2, "BRAND_ID");
			String Query3="select brand_id from ODSPROP.property_master where property_master_id in ("+Prop3+")";
			BrandID3=SW.GetColumnValues(Query3, "BRAND_ID");

			SW.CloseDBConnection();

			//Update the table to remove the existing guests 
			SW.EstablishConnection("DWH");
			String UpdateQuery="update CRMSTG.GC_PROP_STAY set LAST_STAY_DT='25-Feb-12' where PROP_MASTER_ID in ("+Prop1+","+Prop2+","+Prop3+") and CURRENT_GST_MASTER_PROF_ID in (SELECT GG.CURRENT_GST_MASTER_PROF_ID FROM CRMSTG.GC_PROP_STAY GPS,CRMSTG.GC_BRAND_STAY GBS,CRMSTG.GC_GUEST GG WHERE GG.CURRENT_GST_MASTER_PROF_ID = GPS.CURRENT_GST_MASTER_PROF_ID AND GG.CURRENT_GST_MASTER_PROF_ID = GBS.CURRENT_GST_MASTER_PROF_ID AND GG.EMAIL_ADDR_TEXT IS NOT NULL AND GG.EMAIL_ADDR_TEXT NOT LIKE '%AOL.COM' AND GPS.LAST_STAY_DT > =TRUNC( sysdate - 555) AND GPS.PROP_MASTER_ID IN  ("+Prop1+","+Prop2+","+Prop3+") AND GBS.BRAND_ID IN ("+BrandID1.get(0)+","+BrandID2.get(0)+","+BrandID3.get(0)+"))";
			SW.UpdateQuery(UpdateQuery);
			String UpdateQuery2="update CRMSTG.GC_BRAND_STAY set LAST_STAY_DT='25-Feb-12' where BRAND_ID in ("+BrandID1.get(0)+","+BrandID2.get(0)+","+BrandID3.get(0)+") and CURRENT_GST_MASTER_PROF_ID in (SELECT GG.CURRENT_GST_MASTER_PROF_ID FROM CRMSTG.GC_PROP_STAY GPS,CRMSTG.GC_BRAND_STAY GBS,CRMSTG.GC_GUEST GG WHERE GG.CURRENT_GST_MASTER_PROF_ID = GPS.CURRENT_GST_MASTER_PROF_ID AND GG.CURRENT_GST_MASTER_PROF_ID = GBS.CURRENT_GST_MASTER_PROF_ID AND GG.EMAIL_ADDR_TEXT IS NOT NULL AND GG.EMAIL_ADDR_TEXT NOT LIKE '%AOL.COM' AND GPS.LAST_STAY_DT > =TRUNC( sysdate - 555) AND GPS.PROP_MASTER_ID IN  ("+Prop1+","+Prop2+","+Prop3+") AND GBS.BRAND_ID IN ("+BrandID1.get(0)+","+BrandID2.get(0)+","+BrandID3.get(0)+"))";
			SW.UpdateQuery(UpdateQuery2);

			//Check for the existence of the GMP ID in the tables 
			String SelectQuery="select count(*) from CRMSTG.GC_BRAND_STAY  where CURRENT_GST_MASTER_PROF_ID= "+GMPID+" and BRAND_ID = "+BrandID1.get(0)+"";
			boolean result=SW.RecordExists(SelectQuery);
			if(result){
				String UpdateQuery3="update CRMSTG.GC_BRAND_STAY set LAST_STAY_DT='24-Jun-16' where CURRENT_GST_MASTER_PROF_ID in ("+GMPID+") and BRAND_ID="+BrandID1.get(0)+"";
				SW.UpdateQuery(UpdateQuery3);
				Environment.loger.log(Level.INFO,"Record exists for given GMP ID and Updated the same");
			}else{//if record doesn't exists insert new record

				String InsertQuery="Insert into CRMSTG.GC_BRAND_STAY(CURRENT_GST_MASTER_PROF_ID,BRAND_ID,LAST_STAY_DT,TOTAL_STAYS) values ("+GMPID+","+BrandID1.get(0)+",'05-MAY-2016',5)";
				SW.UpdateQuery(InsertQuery);

				Environment.loger.log(Level.INFO,"Inserted records for the given GMP ID");
			}

			String SelectQuery2="select count(*) from CRMSTG.GC_PROP_STAY where CURRENT_GST_MASTER_PROF_ID= "+GMPID+" and PROP_MASTER_ID = "+Prop1+"";
			boolean result2=SW.RecordExists(SelectQuery2);
			if(result2){
				String UpdateQuery4="update CRMSTG.GC_PROP_STAY set LAST_STAY_DT='25-Feb-16' where CURRENT_GST_MASTER_PROF_ID in ("+GMPID+") and PROP_MASTER_ID in ("+Prop1+")";
				SW.UpdateQuery(UpdateQuery4);
				Environment.loger.log(Level.INFO,"Record exists for given GMP ID and Updated the same");
			}else{//if record doesn't exists insert new record
				String InsertQuery2="Insert into CRMSTG.GC_PROP_STAY(CURRENT_GST_MASTER_PROF_ID,PROP_MASTER_ID,LAST_STAY_DT,TOTAL_STAYS,ROLLING_12_STAYS,ROLLING_12_NIGHTS,VIP_LEVEL_CD,RATE_PLANS) values ("+GMPID+","+Prop1+",'05-MAY-2016',1,0,4,NULL,NULL)";
				SW.UpdateQuery(InsertQuery2);
				Environment.loger.log(Level.INFO,"Inserted records for the given GMP ID");
			}

			//Update the Mail ID and Language code 
			String UpdateMailID="update CRMSTG.GC_GUEST set EMAIL_ADDR_TEXT='"+EmailID+"' where CURRENT_GST_MASTER_PROF_ID in ("+GMPID+")";
			SW.UpdateQuery(UpdateMailID);

			String UpdateLanguageCode="update CRMSTG.GC_GUEST set LANGUAGE_CD='ENG' where CURRENT_GST_MASTER_PROF_ID in ("+GMPID+")";
			SW.UpdateQuery(UpdateLanguageCode);
			Environment.loger.log(Level.INFO,"Updated the Email ID and Laguage for the guest");

			SW.CloseDBConnection();
			SW.EstablishConnection(Environment.getRunEnvironment());
			//if entry exists in the outbound table update the 
			String CheckOutBoundQuery="select count(*) from offer.outbound_message where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address = '"+EmailID+"'";
			boolean RecordExists= SW.RecordExists(CheckOutBoundQuery);
			if(RecordExists){
				Environment.loger.log(Level.INFO, "Record exists in the outbound table and removing same");
				String UpdateOutBoundQuery="update offer.outbound_message set message_type_id=0 where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address ='"+EmailID+"'";
				SW.UpdateQuery(UpdateOutBoundQuery);
			}
			SW.CloseDBConnection();
			SW.EstablishConnection("DWH");
			String ValidateEligibleGuest="SELECT GG.CURRENT_GST_MASTER_PROF_ID,GG.EMAIL_ADDR_TEXT,GG.COUNTRY_TWO_CHAR_CD,GG.GST_FIRST_NAME,GG.GST_LAST_NAME FROM CRMSTG.GC_PROP_STAY GPS,CRMSTG.GC_BRAND_STAY GBS,CRMSTG.GC_GUEST GG WHERE GG.CURRENT_GST_MASTER_PROF_ID = GPS.CURRENT_GST_MASTER_PROF_ID AND GG.CURRENT_GST_MASTER_PROF_ID = GBS.CURRENT_GST_MASTER_PROF_ID AND GG.EMAIL_ADDR_TEXT IS NOT NULL AND GG.EMAIL_ADDR_TEXT NOT LIKE '%AOL.COM' AND GPS.LAST_STAY_DT > =TRUNC( sysdate - 555) AND GPS.PROP_MASTER_ID IN ("+Prop1+") AND GBS.BRAND_ID IN ("+BrandID1.get(0)+")";
			if(!SW.RecordExists(ValidateEligibleGuest)){
				Environment.loger.log(Level.ERROR, "Guest not update properly In th DB");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Brand codes are not same");
			}

			SW.CloseDBConnection();

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Update",e);
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("Failed during db Update");
		}	

	}
	@Test(priority=3, dependsOnMethods="databaseUpdateforAHBB")
	public void RunAHBBTask(){
		try{
			SW.Wait(40);
			SW.LaunchBrowser(Environment.BOB);
			SW.BopLogin(sUsername,sPassword);
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
				SW.FailCurrentTest("AHBB Task Run failed");
			}

		}catch(Exception e){
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("AHBB Task Run failed");
		}
	}
	@Test(priority=4, dependsOnMethods="databaseUpdateforAHBB")
	public void RemoveEligibleGuest(){//Remove the eligible guest and expire the created offer

		//Update the table to remove the existing guests
		SW.EstablishConnection("DWH");
		SW.Wait(40);
		String UpdateQuery="update CRMSTG.GC_PROP_STAY set LAST_STAY_DT='25-Feb-12' where PROP_MASTER_ID in ("+Prop1+","+Prop2+","+Prop3+") and CURRENT_GST_MASTER_PROF_ID in (SELECT GG.CURRENT_GST_MASTER_PROF_ID FROM CRMSTG.GC_PROP_STAY GPS,CRMSTG.GC_BRAND_STAY GBS,CRMSTG.GC_GUEST GG WHERE GG.CURRENT_GST_MASTER_PROF_ID = GPS.CURRENT_GST_MASTER_PROF_ID AND GG.CURRENT_GST_MASTER_PROF_ID = GBS.CURRENT_GST_MASTER_PROF_ID AND GG.EMAIL_ADDR_TEXT IS NOT NULL AND GG.EMAIL_ADDR_TEXT NOT LIKE '%AOL.COM' AND GPS.LAST_STAY_DT > =TRUNC( sysdate - 555) AND GPS.PROP_MASTER_ID IN  ("+Prop1+","+Prop2+","+Prop3+") AND GBS.BRAND_ID IN ("+BrandID1.get(0)+","+BrandID2.get(0)+","+BrandID3.get(0)+"))";
		SW.UpdateQuery(UpdateQuery);
		String UpdateQuery2="update CRMSTG.GC_BRAND_STAY set LAST_STAY_DT='25-Feb-12' where BRAND_ID in ("+BrandID1.get(0)+","+BrandID2.get(0)+","+BrandID3.get(0)+") and CURRENT_GST_MASTER_PROF_ID in (SELECT GG.CURRENT_GST_MASTER_PROF_ID FROM CRMSTG.GC_PROP_STAY GPS,CRMSTG.GC_BRAND_STAY GBS,CRMSTG.GC_GUEST GG WHERE GG.CURRENT_GST_MASTER_PROF_ID = GPS.CURRENT_GST_MASTER_PROF_ID AND GG.CURRENT_GST_MASTER_PROF_ID = GBS.CURRENT_GST_MASTER_PROF_ID AND GG.EMAIL_ADDR_TEXT IS NOT NULL AND GG.EMAIL_ADDR_TEXT NOT LIKE '%AOL.COM' AND GPS.LAST_STAY_DT > =TRUNC( sysdate - 555) AND GPS.PROP_MASTER_ID IN  ("+Prop1+","+Prop2+","+Prop3+") AND GBS.BRAND_ID IN ("+BrandID1.get(0)+","+BrandID2.get(0)+","+BrandID3.get(0)+"))";
		SW.UpdateQuery(UpdateQuery2);

		String ValidateEligibleGuest="SELECT GG.CURRENT_GST_MASTER_PROF_ID,GG.EMAIL_ADDR_TEXT,GG.COUNTRY_TWO_CHAR_CD,GG.GST_FIRST_NAME,GG.GST_LAST_NAME FROM CRMSTG.GC_PROP_STAY GPS,CRMSTG.GC_BRAND_STAY GBS,CRMSTG.GC_GUEST GG WHERE GG.CURRENT_GST_MASTER_PROF_ID = GPS.CURRENT_GST_MASTER_PROF_ID AND GG.CURRENT_GST_MASTER_PROF_ID = GBS.CURRENT_GST_MASTER_PROF_ID AND GG.EMAIL_ADDR_TEXT IS NOT NULL AND GG.EMAIL_ADDR_TEXT NOT LIKE '%AOL.COM' AND GPS.LAST_STAY_DT > =TRUNC( sysdate - 555) AND GPS.PROP_MASTER_ID IN ("+Prop1+") AND GBS.BRAND_ID IN ("+BrandID1.get(0)+")";;
		if(!SW.RecordExists(ValidateEligibleGuest)){
			Environment.loger.log(Level.INFO, "Eligible guest removed from DB");
		}
		SW.CloseDBConnection();
		SW.EstablishConnection(Environment.getRunEnvironment());
		//Expire the created Active offer 
		String UpdateOfferStatusQuery="update offer.offer_version set status_id=10 where offer_id in ("+OfferID+")";
		SW.UpdateQuery(UpdateOfferStatusQuery);
		SW.CloseDBConnection();
	}

	@Test(priority=5, dependsOnMethods="RunAHBBTask")
	public void validateInDB(){
		SW.Wait(60);//Explicit wait to reflect in DB
		String ValidateQuery="select outbound_message_id, property_master_id, guest_master_profile_id,email_address,obm_status_id from offer.outbound_message where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address = '"+EmailID+"'";
		boolean RecordExists=SW.RecordExists(ValidateQuery);
		if(RecordExists){
			Environment.loger.log(Level.INFO, "Record Exists in the Table");
		}else{
			Environment.loger.log(Level.ERROR, "AHBB Record is not exists in the DB");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("AHBB Record is not exists in the DB");
		}
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
