package testscripts.Wechat;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
/** Purpose		: Enrolling New WeChat Member in Wechat Application
 * TestCase Name: REG04_EnrollNewWeChatMember.java
 * Created By	: Sharmila Begam Hameed
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */
public class REG04_EnrollNewWeChatMember {
	CRM SW = new CRM();	
	String sOneID,sConfrimNum;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowserWeChat();
		sOneID=SW.TestData("QA4_WechatOpenid");
		
		}
	@Test(priority=1)
	public void EnrollnewMember()
	{
		if(SW.ObjectExists("WCEnroll_OneId_EB"))
		{
			SW.EnterValue("WCEnroll_OneId_EB", sOneID);
			SW.Click("WCEnroll_Continue_BT");
			Environment.loger.log(Level.INFO,"The WECHAT Application has opened Successfully");
		}
		SW.EnterValue("WCEnroll_Email_EB","dinesh.soundararajan@starwoodhotels.com");
		SW.Click("WCEnroll_EmailSubmit_BT");
		if(!SW.ObjectExists("WCEnroll_EmailError_DT")){
			SW.DropDown_SelectByText("WCEnroll_Title_DD", "Mr.");
			SW.EnterValue("WCEnroll_FirstName_EB", "Dinesh");
			SW.EnterValue("WCEnroll_LastName_EB", "Soundra");
			SW.EnterValue("WCEnroll_Street_EB", "14 Bellandur");
			SW.EnterValue("WCEnroll_City_EB", "Anchorage");
			SW.DropDown_SelectByText("WCEnroll_State_DD", "Alaska");
			SW.EnterValue("WCEnroll_Zipcode_EB", "99501");
			SW.DropDown_SelectByIndex("WCEnroll_Phone_DD", 2);
			SW.EnterValue("WCEnroll_PhoneNum_EB", "78945612337");
			SW.EnterValue("WCEnroll_Extension_EB", "78542");
		//WC.DropDown_SelectByText("WCEnroll_Birthmonth_DD", "January");
		//WC.DropDown_SelectByText("WCEnroll_BirthDate_DD", "01");
			SW.Click("WCEnroll_StepOneSubmit_BT");
			SW.DropDown_SelectByIndex("WCEnroll_Smoke_DD", 2);
			SW.DropDown_SelectByIndex("WCEnroll_Preference_DD", 1);
			SW.Click("WCEnroll_LowFloor_CB");
			SW.Click("WCEnroll_Space_CB");
	//	WC.EnterValue("WCEnroll_Company_EB", "Accenture");
		//WC.EnterValue("WCEnroll_Set_EB", "12345");
			SW.Click("WCEnroll_SteptwoSubmit_BT");
		String sName=SW.RandomString(8);
		String sPwd=SW.RandomString(9);
		SW.EnterValue("WCEnroll_Username_EB", sName);
		SW.EnterValue("WCEnroll_Password_EB", sPwd);
		SW.EnterValue("WCEnroll_RetypePWD_EB",sPwd );
		Environment.loger.log(Level.INFO,"Username "+sName+"and PWD:"+sPwd);
		SW.DropDown_SelectByIndex("WCEnroll_SecQuesOne_DD",1);
		String sQuesone=SW.DropDown_GetSelectedText("WCEnroll_SecQuesOne_DD");
		String sAns = sQuesone.substring(sQuesone.lastIndexOf(" ")+1,sQuesone.indexOf("?")).trim();
		System.out.println(sAns);
		SW.EnterValue("WCEnroll_SecAnsOne_EB", sAns);
		SW.DropDown_SelectByIndex("WCEnroll_SecQuesTwo_DD", 2); 
		 sQuesone=SW.DropDown_GetSelectedText("WCEnroll_SecQuesTwo_DD");
		 sAns = sQuesone.substring(sQuesone.lastIndexOf(" ")+1,sQuesone.indexOf("?")).trim();
		 SW.EnterValue("WCEnroll_SecAnsTwo_EB", sAns);
		 SW.DropDown_SelectByIndex("WCEnroll_SecQuesThree_DD", 3);
		 sQuesone=SW.DropDown_GetSelectedText("WCEnroll_SecQuesThree_DD");
		 sAns = sQuesone.substring(sQuesone.lastIndexOf(" ")+1,sQuesone.indexOf("?")).trim();
		 SW.EnterValue("WCEnroll_SecAnsThree_EB", sAns);
		 SW.DropDown_SelectByIndex("WCEnroll_SecQuesFour_DD", 4);
		 sQuesone=SW.DropDown_GetSelectedText("WCEnroll_SecQuesFour_DD");
		 sAns = sQuesone.substring(sQuesone.lastIndexOf(" ")+1,sQuesone.indexOf("?")).trim();
		 SW.EnterValue("WCEnroll_SecAnsFour_EB", sAns);
		 SW.Click("WCEnroll_Submit_BT");
		if(!SW.ObjectExists("WCEnroll_ErrorLog_DT"))
		{
			if(SW.ObjectExists("WCEnroll_Confrim_DT"))
			{
				String ConfMsg=SW.GetText("WCEnroll_Confrim_DT");
				if(SW.CompareTextContained("Your Starwood Preferred Guest member number is:", ConfMsg))
				sConfrimNum=ConfMsg.substring(ConfMsg.lastIndexOf(" ")+2).trim();
			}
			Environment.loger.log(Level.INFO,"Member has Enrolled Successfully");
		}
		else
		{
			Environment.loger.log(Level.ERROR,"Member not Enrolled");
			SW.FailCurrentTest("Enrollment Failed");
		}
		}
		else
		{
			Environment.loger.log(Level.ERROR,"Email Id is already Registered or check the Mail Id");
			SW.FailCurrentTest("EmailId id already register");
		}
	}
	@Test(priority=2,dependsOnMethods={"EnrollnewMember"})
	public void DBValidation()
	{
		try{	

			SW.Wait(100);//for reflect into DB
			SW.EstablishConnection("qa4");
			String CheckQuery="Select * from freq_travel_mbrshp where mbrshp_num in ("+sConfrimNum+")";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Freq_Travel_Mbrshp table");
			else{
				Environment.loger.log(Level.ERROR,"Record not exists in the table");
				SW.FailCurrentTest("Record not exists in the table");
			}
			CheckQuery="Select * from freq_travel_extl_mbrshp where mbrshp_num in ("+sConfrimNum+")";
			if(SW.RecordExists(CheckQuery))
				Environment.loger.log(Level.INFO, "Record exists in the Freq_travel_call_comm  table");
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
