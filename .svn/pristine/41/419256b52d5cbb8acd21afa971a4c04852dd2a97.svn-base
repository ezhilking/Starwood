package testscripts.pmsRegression;

import java.util.Calendar;
import java.util.List;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.sikuli.script.Key;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;
import functions.SoapUtility;
/* Purpose		: TestCase :Validate the Corporate alerts in opera in both modiy mode and check in mode for the guest having
birthday/Anniversary details within 7 days of arrival date
AMB status
at least one unmet SPG Preference
at least one met SPG Preference
one of the  milestone stays for brand, Starwood and property (10th, 25th, 50th, 100th, 500th, 1000th)
 * Created By	: Ezhilarasan.S 
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
public class OperaPMS_REG04_Alerts {
	CRM SW = new CRM();
	String PropertyNo = "1967";
	String ReservationNo;

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
	}
	@Test(priority=1)
	public void SGRVIPAndOptIn(){
		SW.EstablishConnection("qa3");//TODO
		List<String> brandID = SW.GetColumnValues("select * from ODSPROP.property_master where property_master_id="+PropertyNo, "brand_id");

		List<String> MasterProfileID = SW.GetColumnValues("select * from odsguest.gst_pinstripe where current_rcrd_ind='Y' "
				+ "and engagement_lvl_cd='ENG' and pinstripe_grp_id=888 "
				+ "and gst_master_prof_id in (select guest_master_profile_id from ODSCNSMP.consumption_brand_summary where brand_id="+brandID.get(0)+")"
				+ "and gst_master_prof_id in (select guest_master_profile_id from ODSCNSMP.consumption_property_summary where property_master_id="+PropertyNo+")"
				+ "and gst_master_prof_id in (select  guest_master_profile_id from ODSCNSMP.consumption_starwood_summary)"
				, "gst_master_prof_id");

		List<String> MembershipID = SW.GetColumnValues("select * from odsguest.gst_pinstripe where current_rcrd_ind='Y' "
				+ "and engagement_lvl_cd='ENG' and pinstripe_grp_id=888 "
				+ "and gst_master_prof_id in (select guest_master_profile_id from ODSCNSMP.consumption_brand_summary where brand_id="+brandID.get(0)+")"
				+ "and gst_master_prof_id in (select guest_master_profile_id from ODSCNSMP.consumption_property_summary where property_master_id="+PropertyNo+")"
				+ "and gst_master_prof_id in (select  guest_master_profile_id from ODSCNSMP.consumption_starwood_summary)"
				, "mbrshp_id");


		SW.UpdateQuery("Update ODSCNSMP.consumption_property_summary set total_stays=9,total_nights=9,ytd_stays=9,ytd_nights=9,rolling_12_stays=9,"
				+ "rolling_12_nights=9 where guest_master_profile_id="+MasterProfileID.get(0)+" and property_master_id="+PropertyNo);

		SW.UpdateQuery("update ODSCNSMP.consumption_brand_summary set total_stays=24,total_nights=24,ytd_stays=24,ytd_nights=24,"
				+ "rolling_12_stays=24,rolling_12_nights=24 where guest_master_profile_id="+MasterProfileID.get(0)+" and brand_id="+brandID.get(0));

		SW.UpdateQuery("update ODSCNSMP.consumption_starwood_summary set total_stays=99,total_nights=99,ytd_stays=99,ytd_nights=99,"
				+ " rolling_12_nights=99 where guest_master_profile_id="+MasterProfileID.get(0));

		//Create reservation fusing SOAP request
		//Create reservation 
		String RequestXMLFile="CreateAMBReservation_QA3.xml";

		//String EndPointURL = "http://booking.stg1.ssd.star:9245/BookingWeb/services/BookingPort";
		String EndPointURL = "http://booking.qa3.nssd.star:9245/BookingWeb/services/BookingPort";

		//call to soap server 
		SOAPMessage soapRequest=SoapUtility.getSOAPRequest(RequestXMLFile);
		//Modify the SOAP Request as per the requirement 
		SOAPMessage newSoapMessage=SW.ChangeTransactionIDInSoapRequest(soapRequest);

		String FutureArrivalDate= SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 5, Calendar.DATE);
		String FutureDepartureDate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 6, Calendar.DATE);

		newSoapMessage=SW.ChangeArrivalDepartureDateINSoapRequest(newSoapMessage,FutureArrivalDate,FutureDepartureDate);

		SOAPMessage soapResponse=SoapUtility.getSOAPResponse(newSoapMessage, EndPointURL);
		boolean result = SoapUtility.validateSOAPResponseForFault(soapResponse);
		// Process the SOAP Response
		if(result){
			String ResNum=SoapUtility.getXMLElementText(soapResponse, "BinderDTO", "binderId");
			Environment.loger.log(Level.INFO,"Reservation Confirmation Number="+ResNum);
		}else{
			SoapUtility.printSOAPResponse(soapResponse);
			//			SW.FailCurrentTest("Error in SOAP Response see response file for more details");
		}
	}
	@Test(priority=2)
	public void SGR(){
		SW.LaunchBrowser(Environment.SGRURL);
		SW.SGRLogin(SW.TestData("STG_SGRUsername"), SW.TestData("STG_SGRPassword"), PropertyNo);
		SW.Click("SGRNavigation_ResSearch_LK");
		SW.EnterValue("SGRResSearch_StarLinkConf_EB", ReservationNo);
		SW.Click("SGRResSearch_Submit_BN");
		SW.WebTbl_Click("SGRResSearch_Results_WT", 1, 1);
		SW.Click("SGRGuestProfile_NewAncillaryItem_BN");
		SW.DropDown_SelectByText("SGRAncillaryItem_AncillaryCategory_DD", "Anniversary");
		SW.EnterValue("SGRAncillaryItem_AncillaryItem_EB", SW.GetTimeStamp("dd-MMM-yyyy")+Keys.TAB);
		SW.EnterValue("SGRAncillaryItem_AncillaryNotes_EB", "Sample Notes");
		SW.Click("SGRAncillaryItem_Save_BN");

		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_PMSIntegrationAdmin_LK");

		SW.DropDown_SelectByText("SGRIntegrationType_SyncType_DD", "Pre Stay");
		SW.DropDown_SelectByText("SGRIntegrationType_InputType_DD", "Res/Conf Number(s)");
		SW.EnterValue("SGRIntegrationType_InputType_EB", ReservationNo);
		SW.CheckBox("SGRIntegrationType_SyncPreference_CB", "OFF");
		SW.CheckBox("SGRIntegrationType_SyncComments_CB", "OFF");
		SW.CheckBox("SGRIntegrationType_SyncEvents_CB", "OFF");
		SW.CheckBox("SGRIntegrationType_SyncAlert_CB", "ON");
		SW.CheckBox("SGRIntegrationType_SyncVIPInfo_CB", "OFF");
		SW.CheckBox("SGRIntegrationType_SyncDORInfo_CB", "OFF");
		SW.CheckBox("SGRIntegrationType_SyncOptInInfo_CB", "OFF");
		SW.Click("SGRIntegrationType_Sync_BN");
		SW.Click("SGR_Logout_LK");
	}
	SikuliUtil SK = new SikuliUtil();
	@Test(priority=3)
	public void PMSAlertCheck(){
		SW.NavigateTo(Environment.PMS_1967);
		SK.OperaPMSLogin(SW.TestData("PMSUsername"), SW.TestData("PMSPassword"),ReservationNo);
		SW.Wait(3);
		if(SK.SikuliRegionObjectExists("ReservationDetails_Alert_IC")){
			SK.SikuliRegionType(Key.ENTER);//Instead of double click do a enter.
			SW.Wait(3);
			SK.KeyboardStrokes("alt|o");//Click ok button
			if(SK.SikuliRegionObjectExists("ReservationDetails_Alert_IC")){
				SK.KeyboardStrokes("alt|c");//Close button
				SW.Wait(5);
				SK.KeyboardStrokes("alt|c");//Close button
			}else{
				SW.FailCurrentTest("Alert not present!");
			}
		}else{
			SW.FailCurrentTest("Alert not present!");
		}
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();	
	}
}
