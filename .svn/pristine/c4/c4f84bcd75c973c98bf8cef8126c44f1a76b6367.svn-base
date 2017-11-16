package testscripts.gcRegression;
/** Purpose		: Validate St.Regis AHBB SBSP email in Chinese language with offer eligibility to  Include single value of Departure Month of last property stay All guests
 * TestCase Name: Validate St.Regis AHBB SBSP email in Chinese language with offer eligibility to  Include single value of Departure Month of last property stay All guests
 * Created By	: Sachin
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
public class GC_REG69_ValidateStRegisAHBBSBSPChineseEligibilityIncludeSingleValueOfDepMonth {
	CRM SW = new CRM();	
	String  OfferID,sUsername,sPassword;
	String Prop1="110";
	List<String> BrandID1;
	String GMPID="40575987810";
	String EmailID;
	String EmailSub,EmailPreHeader;
	String OfferHead,OfferSubHead;
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
			Prop1="110";
			GMPID="40575987810";
		}
		if(Environment.getRunEnvironment().equalsIgnoreCase("QA2")){
			Prop1="";
			GMPID="";
		}
	}
	@Test(priority=1)
	public void CreateAHBBSBSPOfferChineseLang(){
		SW.GCLogin(sUsername,sPassword);
		if(SW.ObjectExists("GCHome_Message_DT")){
			SW.NormalClick("GCHome_Message_Close_IC");
		}
		SW.WaitTillElementToBeClickable("GCCreateOffer_LK");
		SW.Click("GCCreateOffer_LK");
		SW.Click("GCCreatePropertyOffer_LK");
		SW.Click("GCCreateD1Offer_LK");
		SW.EnterValue("GCCreateResConf_PropertyId_EB",Prop1);
		SW.EnterValue("GCAHBBMPMOffer_InternalOfferName_EB", "AutomatedAHBBOfferName");
		SW.EnterValue("GCAHBBMPMOffer_PresentationStartDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.EnterValue("GCAHBBMPMOffer_presentationEndDate_EB",  SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("GCCreateResConf_Language_DD","Chinese (Traditional)");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		
		SW.WaitTillElementToBeClickable("GCCreateResConf_Eligibility_DD");
		if(SW.ObjectExists("GCCreateResConf_Eligibility_DD")){
			Environment.loger.log(Level.INFO,"OfferSetUp Page naviagation SuccessFull");	
		}else{
			Environment.loger.log(Level.ERROR,"OfferSetUp Page naviagation Failed");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("OfferSetUp Page naviagation Failed");
		}
		SW.DropDown_SelectByText("GCCreateResConf_Eligibility_DD","Departure Month of Last Property Stay");	
		SW.WaitForPageload();
		SW.Wait(10);
		SW.WaitTillElementToBeClickable("GCCreateResConf_EligPlacehold_DD");
		SW.DropDown_SelectByText("GCCreateResConf_EligPlacehold_DD", "Jun");
		SW.Click("GCCreateResConf_EligibleAdd_BT");
		
		SW.Click("GCCreateResConf_EligibleInclude_BT");
		SW.Wait(6);
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

		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		if(!SW.ObjectExists("GCAHBBMPMOffer_MobilePropertyImageSelect_IC"))
			SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageExpand_IC");
		SW.NormalClick("GCAHBBMPMOffer_MobilePropertyImageSelect_IC");
		SW.EnterValue("GCAHBBMPMOffer_SecondaryPageUrl_EB", "https://www.samp.com");
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.WaitTillElementToBeClickable("GCAHBBMPMOffer_Continue_BN");
		SW.Wait(5);
		if(SW.ObjectExists("GCAHBBMPMOffer_Continue_BN")){
			Environment.loger.log(Level.INFO,"AHBB Primary Offer created successfull");
		}else{
			Environment.loger.log(Level.INFO, "AHBB Primary Offer is not created successfull");
			SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
			SW.FailCurrentTest("AHBB Primary Offer is not created successfull");
		}
		SW.Click("GCAHBBMPMOffer_Continue_BN");
		SW.Wait(5);
		SW.WaitTillElementToBeClickable("GCCreateResConf_Rank_EB");
		if(SW.ObjectExists("GCCreateResConf_Rank_EB")){
			Environment.loger.log(Level.INFO,"MessageSetup Page naviagation SuccessFull");
			SW.EnterValue("GCCreateResConf_Rank_EB","1");
			SW.Click("GCCreateResConf_RankMove_BT");
		}
		SW.Click("GCAHBBMPMOffer_Continue_BN");
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

	@Test(priority=2, dependsOnMethods="CreateAHBBSBSPOfferChineseLang")
	public void databaseUpdateforAHBB(){

		try{	
			//update the guest details in DB	
			SW.EstablishConnection(Environment.getRunEnvironment());

			String Query1="select brand_id from ODSPROP.property_master where property_master_id in ("+Prop1+")";
			BrandID1=SW.GetColumnValues(Query1, "BRAND_ID");

			//Update the table to remove the existing guests 
			String UpdateQuery="update consumption_property_summary set last_stay_dt='25-Feb-12' where property_master_id in ("+Prop1+") and guest_master_profile_id in (SELECT g.GUEST_MASTER_PROFILE_ID FROM CONSUMPTION_PROPERTY_SUMMARY cps,CONSUMPTION_BRAND_SUMMARY cBs,guest_master_profile g WHERE cps.guest_master_profile_id = g.guest_master_profile_id AND cBs.guest_master_profile_id = g.guest_master_profile_id AND g.PRIMARY_EMAIL_ADDRESS IS NOT NULL AND g.PRIMARY_EMAIL_ADDRESS NOT LIKE '%AOL.COM' AND cps.last_stay_dt > =TRUNC( sysdate - 555) AND cps.property_master_id  IN ("+Prop1+") AND cBs.brand_id IN ("+BrandID1.get(0)+"))";
			SW.UpdateQuery(UpdateQuery);
			String UpdateQuery2="update consumption_brand_summary set last_stay_dt='25-Feb-12'where  brand_id in ("+BrandID1.get(0)+") and guest_master_profile_id in (SELECT g.GUEST_MASTER_PROFILE_ID FROM CONSUMPTION_PROPERTY_SUMMARY cps,CONSUMPTION_BRAND_SUMMARY cBs,guest_master_profile g WHERE cps.guest_master_profile_id = g.guest_master_profile_id AND cBs.guest_master_profile_id = g.guest_master_profile_id AND g.PRIMARY_EMAIL_ADDRESS IS NOT NULL AND g.PRIMARY_EMAIL_ADDRESS NOT LIKE '%AOL.COM' AND cps.last_stay_dt > =TRUNC( sysdate - 555) AND cps.property_master_id  IN ("+Prop1+") AND cBs.brand_id IN ("+BrandID1.get(0)+"))";
			SW.UpdateQuery(UpdateQuery2);

			//Check for the existence of the GMP ID in the tables 
			String SelectQuery="select count(*) from CONSUMPTION_BRAND_SUMMARY where guest_master_profile_id= "+GMPID+" and brand_id = "+BrandID1.get(0)+"";
			boolean result=SW.RecordExists(SelectQuery);
			if(result){
				String UpdateQuery3="update consumption_brand_summary set last_stay_dt='24-Jun-16' where guest_master_profile_id in ("+GMPID+") and brand_id="+BrandID1.get(0)+"";
				SW.UpdateQuery(UpdateQuery3);
				Environment.loger.log(Level.INFO,"Record exists for given GMP ID and Updated the same");
			}else{//if record doesn't exists insert new record

				String InsertQuery="Insert into CONSUMPTION_BRAND_SUMMARY(GUEST_MASTER_PROFILE_ID,BRAND_ID,TOTAL_STAYS,TOTAL_NIGHTS,YTD_STAYS,YTD_NIGHTS,ROLLING_12_STAYS,ROLLING_12_NIGHTS,TOTAL_STAYS_LAST_YEAR,TOTAL_SPENT_LAST_YEAR,CURRENCY_CD,TOTAL_AMOUNT_SPENT,AVG_NIGHTS_PER_STAY,AVG_ROOM_RATE_PER_STAY,TOTAL_FOOD_BEVERAGE,AVG_FOOD_BEVERAGE,TOTAL_OTHER_SPENT,AVG_OTHER_SPENT,FIRST_STAY_DT,LAST_STAY_DT,TOTAL_CANCELLATIONS,TOTAL_NOSHOWS,SPG_YTD_STAYS,SPG_YTD_NIGHTS,SPG_ROLLING_12_STAYS,SPG_ROLLING_12_NIGHTS,SPG_TOTAL_STAYS,SPG_TOTAL_NIGHTS,CREATE_USER_ID,CREATE_DT,UPDATE_USER_ID,UPDATE_DT,TOTAL_ROOM_REV) values ("+GMPID+","+BrandID1.get(0)+",1,1,1,1,1,1,1,1000,'USD',200,200,200,200,20,30,12,to_date('21-MAR-16','DD-MON-RR'),to_date('16-FEB-16','DD-MON-RR'),null,null,null,null,null,null,null,null,'SGC_GRP_APP',to_date('04-MAR-03','DD-MON-RR'),'CNSMP_SUM',to_date('12-MAR-12','DD-MON-RR'),100)";
				SW.UpdateQuery(InsertQuery);

				Environment.loger.log(Level.INFO,"Inserted records for the given GMP ID");
			}

			String SelectQuery2="select count(*) from CONSUMPTION_PROPERTY_SUMMARY where guest_master_profile_id= "+GMPID+" and property_master_id = "+Prop1+"";
			boolean result2=SW.RecordExists(SelectQuery2);
			if(result2){
				String UpdateQuery4="update consumption_property_summary set last_stay_dt='24-Jun-16' where guest_master_profile_id in ("+GMPID+") and property_master_id in ("+Prop1+")";
				SW.UpdateQuery(UpdateQuery4);
				Environment.loger.log(Level.INFO,"Record exists for given GMP ID and Updated the same");
			}else{//if record doesn't exists insert new record
				String InsertQuery2="Insert into CONSUMPTION_PROPERTY_SUMMARY (GUEST_MASTER_PROFILE_ID,PROPERTY_MASTER_ID,TOTAL_STAYS,TOTAL_NIGHTS,YTD_STAYS,YTD_NIGHTS,ROLLING_12_STAYS,ROLLING_12_NIGHTS,TOTAL_STAYS_LAST_YEAR,TOTAL_SPENT_LAST_YEAR,CURRENCY_CD,TOTAL_AMOUNT_SPENT,AVG_NIGHTS_PER_STAY,AVG_ROOM_RATE_PER_STAY,TOTAL_FOOD_BEVERAGE,AVG_FOOD_BEVERAGE,TOTAL_OTHER_SPENT,AVG_OTHER_SPENT,FIRST_STAY_DT,LAST_STAY_DT,TOTAL_CANCELLATIONS,TOTAL_NOSHOWS,SPG_YTD_STAYS,SPG_YTD_NIGHTS,SPG_ROLLING_12_STAYS,SPG_ROLLING_12_NIGHTS,SPG_TOTAL_STAYS,SPG_TOTAL_NIGHTS,CREATE_USER_ID,CREATE_DT,UPDATE_USER_ID,UPDATE_DT,TOTAL_ROOM_REV) values ("+GMPID+","+Prop1+",1,1,1,1,1,null,1,100,'USD',1000,1,20,25,0,2.5,50,to_date('21-MAR-16','DD-MON-RR'),to_date('16-FEB-16','DD-MON-RR'),null,null,null,null,null,null,null,null,'CNSMP_SUM',to_date('04-MAR-12','DD-MON-RR'),'CNSMP_SUM',to_date('12-MAR-12','DD-MON-RR'),200)";
				SW.UpdateQuery(InsertQuery2);
				Environment.loger.log(Level.INFO,"Inserted records for the given GMP ID");
			}

			//Update the Mail ID and Language code 
			String UpdateMailID="update guest_master_profile set primary_email_address='"+EmailID+"' where guest_master_profile_id in ("+GMPID+")";
			SW.UpdateQuery(UpdateMailID);

			String UpdateLanguageCode="update guest_secondary_profile set guest_language_cd='ZHO' where guest_master_profile_id in ("+GMPID+")";
			SW.UpdateQuery(UpdateLanguageCode);
			Environment.loger.log(Level.INFO,"Updated the Email ID and Laguage for the guest");
			//if entry exists in the outbound table update the 
			String CheckOutBoundQuery="select count(*) from offer.outbound_message where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address = '"+EmailID+"'";
			boolean RecordExists= SW.RecordExists(CheckOutBoundQuery);
			if(RecordExists){
				Environment.loger.log(Level.INFO, "Record exists in the outbound table and removing same");
				String UpdateOutBoundQuery="update offer.outbound_message set message_type_id=0 where message_type_id=9 and sent_date > trunc (sysdate-8) and email_address ='"+EmailID+"'";
				SW.UpdateQuery(UpdateOutBoundQuery);
			}
			String ValidateEligibleGuest="SELECT g.GUEST_MASTER_PROFILE_ID,g.PRIMARY_EMAIL_ADDRESS,g.primary_country,g.guest_first_name,g.guest_last_name FROM CONSUMPTION_PROPERTY_SUMMARY cps,CONSUMPTION_BRAND_SUMMARY cBs,guest_master_profile g WHERE cps.guest_master_profile_id = g.guest_master_profile_id AND cBs.guest_master_profile_id= g.guest_master_profile_id AND g.PRIMARY_EMAIL_ADDRESS IS NOT NULL AND g.PRIMARY_EMAIL_ADDRESS NOT LIKE '%AOL.COM' AND cps.last_stay_dt > =TRUNC(sysdate - 555)AND cps.property_master_id IN ("+Prop1+") AND cBs.brand_id IN ("+BrandID1.get(0)+")";
			if(!SW.RecordExists(ValidateEligibleGuest)){
				Environment.loger.log(Level.ERROR, "Guest not update properly In th DB");
				SW.WriteToEmailTestData(TestCaseName, "ExecutionFlag", "N");
				SW.FailCurrentTest("Brand codes are not same");
			}



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
		SW.Wait(40);
		String UpdateQuery="update consumption_property_summary set last_stay_dt='25-Feb-12' where property_master_id in ("+Prop1+") and guest_master_profile_id in (SELECT g.GUEST_MASTER_PROFILE_ID FROM CONSUMPTION_PROPERTY_SUMMARY cps,CONSUMPTION_BRAND_SUMMARY cBs,guest_master_profile g WHERE cps.guest_master_profile_id = g.guest_master_profile_id AND cBs.guest_master_profile_id = g.guest_master_profile_id AND g.PRIMARY_EMAIL_ADDRESS IS NOT NULL AND g.PRIMARY_EMAIL_ADDRESS NOT LIKE '%AOL.COM' AND cps.last_stay_dt > =TRUNC( sysdate - 555) AND cps.property_master_id  IN ("+Prop1+") AND cBs.brand_id IN ("+BrandID1.get(0)+"))";
		SW.UpdateQuery(UpdateQuery);
		String UpdateQuery2="update consumption_brand_summary set last_stay_dt='25-Feb-12'where  brand_id in ("+BrandID1.get(0)+") and guest_master_profile_id in (SELECT g.GUEST_MASTER_PROFILE_ID FROM CONSUMPTION_PROPERTY_SUMMARY cps,CONSUMPTION_BRAND_SUMMARY cBs,guest_master_profile g WHERE cps.guest_master_profile_id = g.guest_master_profile_id AND cBs.guest_master_profile_id = g.guest_master_profile_id AND g.PRIMARY_EMAIL_ADDRESS IS NOT NULL AND g.PRIMARY_EMAIL_ADDRESS NOT LIKE '%AOL.COM' AND cps.last_stay_dt > =TRUNC( sysdate - 555) AND cps.property_master_id  IN ("+Prop1+") AND cBs.brand_id IN ("+BrandID1.get(0)+"))";
		SW.UpdateQuery(UpdateQuery2);

		String ValidateEligibleGuest="SELECT g.GUEST_MASTER_PROFILE_ID,g.PRIMARY_EMAIL_ADDRESS,g.primary_country,g.guest_first_name,g.guest_last_name FROM CONSUMPTION_PROPERTY_SUMMARY cps,CONSUMPTION_BRAND_SUMMARY cBs,guest_master_profile g WHERE cps.guest_master_profile_id = g.guest_master_profile_id AND cBs.guest_master_profile_id= g.guest_master_profile_id AND g.PRIMARY_EMAIL_ADDRESS IS NOT NULL AND g.PRIMARY_EMAIL_ADDRESS NOT LIKE '%AOL.COM' AND cps.last_stay_dt > =TRUNC(sysdate - 555)AND cps.property_master_id IN ("+Prop1+") AND cBs.brand_id IN ("+BrandID1.get(0)+")";
		if(!SW.RecordExists(ValidateEligibleGuest)){
			Environment.loger.log(Level.INFO, "Eligible guest removed from DB");
		}
		//Expire the created Active offer 
		String UpdateOfferStatusQuery="update offer.offer_version set status_id=10 where offer_id in ("+OfferID+")";
		SW.UpdateQuery(UpdateOfferStatusQuery);
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
