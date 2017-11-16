package testscripts.Wechat;
import java.util.Calendar;

import org.apache.log4j.Level;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

/** Purpose		: Validate the Reservation Confirmation in Wechat Application
 * TestCase Name: WeChat_REG01_ReservationConfirmation.java
 * Created By	: Sharmila Begam Hameed
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
public class REG01_ReservationConfirmation {
	CRM SW = new CRM();	
	String sOneID,sPropID="1960";
	String sUsername,sPassword,sGripUsrName,sGripPassword;
	String ConfirmationNumber;
	WebElement ActionObject=null;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowserWeChat();
		sOneID=SW.TestData("QA4_WechatOpenid");
		sUsername=SW.TestData("QA4_WechatUsername");
		sPassword=SW.TestData("QA4_WechatPassword");
		sGripUsrName=SW.TestData("QA4_GripUName");
		sGripPassword=SW.TestData("QA4_GripPWD");
	}
	@Test(priority=1)
	public void CreateReservationConfirm()
	{
		if(SW.ObjectExists("WCPost_OneId_EB"))
		{
			SW.EnterValue("WCPost_OneId_EB", sOneID);
			SW.Click("WCPost_Continue_BT");
			Environment.loger.log(Level.INFO,"The WECHAT Application has opened Successfully");
		}
		else
			Environment.loger.log(Level.ERROR,"Wechat Application has not opened");	
		SW.WeChatLogin(sUsername, sPassword);
		SW.EnterValue("WCHome_Destin_EB", "New York");
		SW.Click("WCHome_Calender_IC");
		String startdate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 1, Calendar.DATE);
		String Enddate=SW.DateAddDays(SW.GetTimeStamp("yyyy-MM-dd"), "yyyy-MM-dd", 3, Calendar.DATE);
		SW.Click("//td[@date='"+startdate+"']");
		SW.Click("//td[@date='"+Enddate+"']");
		SW.Click("WCHome_CalGo_BT");
		if(SW.ObjectExists("WCBooking_Result_LK"))
			SW.Click("WCBooking_Result_LK");
		SW.WaitForPageload();
		SW.Click("WCBooking_HotelName_DT");
		SW.NavigateTo(Environment.WECHATURL+sPropID);
		SW.WaitForPageload();
		SW.Click("WCBooking_HotelPlan_LK");
		SW.Click("WCBooking_Rateplan1960_LK");
		SW.Click("WCBooking_BookNow1960_LK");
		SW.Click("WCBooking_ConfirmReserve_LK");
		String SuccessMSG=SW.GetText("WCBooking_ConfrimMSG_DT");
		if(SW.CompareTextContained("Your reservation is complete", SuccessMSG))
		{
			SW.WaitTillElementToBeClickable("WCBooking_ConfirmationNum_DT");
			ConfirmationNumber=SW.GetText("WCBooking_ConfirmationNum_DT");
			Environment.loger.log(Level.INFO, "Reservation Confirmation Number= "+ConfirmationNumber);
		}
		else
		{
			Environment.loger.log(Level.ERROR,"Reservation not Created Successfully");
			SW.FailCurrentTest("Reservation has not confirmed");
		}
	}
	@Test(priority=2, dependsOnMethods="CreateReservationConfirm")
	public void CheckGrip()
	{

		boolean LoginGrip1=false;
		boolean LoginGrip2=false;
		SW.LaunchBrowser(Environment.GRIP1URL);
		if(SW.LoginToGrip(sGripUsrName,sGripPassword)){
			SW.Click("WC_GripQueue_LK");
		
			if(SW.ObjectExists("WC_GripQueue_Resume_BT"))
			{
				SW.Click("WC_GripQueue_Resume_BT");
				LoginGrip1=true;
				Environment.loger.log(Level.INFO, "The GRIP1 Resume State Enabled");
			}
			else{
				LoginGrip1=true;
				Environment.loger.log(Level.INFO, "The GRIP1 Already in resume State Enabled");
			}

		}
		SW.LaunchBrowser(Environment.GRIP2URL);
		if(SW.LoginToGrip(sGripUsrName,sGripPassword)){
			SW.Click("WC_GripQueue_LK");
			if(SW.ObjectExists("WC_GripQueue_Resume_BT"))
			{
				SW.Click("WC_GripQueue_Resume_BT");
				LoginGrip2=true;
				Environment.loger.log(Level.INFO, "The GRIP2 Resume State Enabled");
			}
			else{
				LoginGrip2=true;
				Environment.loger.log(Level.INFO, "The GRIP2 Already in resume State Enabled");
			}
		}
		if(LoginGrip1 && LoginGrip2)
		{
			Environment.loger.log(Level.INFO, "Both the Grips are in Resume State");
		}
	}
	@Test(priority=3, dependsOnMethods="CheckGrip")
	public void CheckSeas()
	{
		boolean SeasLogin1=false;
		boolean SeasLogin2=false;
		SW.LaunchBrowser(Environment.SEAS1URL);
		if(SW.LoginToSeas(sGripUsrName,sGripPassword)){
			SW.Click("WC_GripQueue_LK");
			if(SW.ObjectExists("WC_GripQueue_Resume_BT"))
			{
				SW.Click("WC_SeasQueue_Resume_BT");
				SeasLogin1=true;
				Environment.loger.log(Level.INFO, "The SEAS1 Resume State Enabled");
			}
			else{
				SeasLogin1=true;
				Environment.loger.log(Level.INFO, "The SEAS1 Already in resume State Enabled");
			}
		}
		SW.LaunchBrowser(Environment.SEAS2URL);
		if(SW.LoginToSeas(sGripUsrName,sGripPassword)){
			SW.Click("WC_GripQueue_LK");
			if(SW.ObjectExists("WC_GripQueue_Resume_BT"))
			{
				SW.Click("WC_SeasQueue_Resume_BT");
				SeasLogin2=true;
				Environment.loger.log(Level.INFO, "The SEAS2 Resume State Enabled");
			}
			else{
				SeasLogin2=true;
				Environment.loger.log(Level.INFO, "The SEAS2 Already in resume State Enabled");
			}
		}	
		if(SeasLogin1 && SeasLogin2)
		{
			Environment.loger.log(Level.INFO, "Both the SEAS are in Resume State");
		}
	}
	@Test(priority=4, dependsOnMethods="CheckSeas")
	public void DBValidation()
	{
		try{	

			SW.Wait(100);//for reflect into DB
			SW.EstablishConnection("qa4");
			String CheckQuery="select * from OFFER.outbound_message where reservation_confirmation_num in ("+ConfirmationNumber+")";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the OUT Bound table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="select * from AQADM.SPGPUSHNOTF_QUEUE_TABLE order by enq_time desc";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the table Before Batch job");
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Failed during db Validation",e);
			SW.FailCurrentTest("Failed during db Validation");
		}		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}
