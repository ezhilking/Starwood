package testscripts.SPGLink;
/** Purpose		: This is to Post a Stay in SPG Link 2.0 under Stay Poster Role
 * TestCase Name: PostStaySPGMemberInvalidStatus
 * Created By	: Vaishali Krishna
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

public class SPGLink_REG27_PostStaySPGMemberInvalidStatus {
	CRM SW = new CRM();
	String Mbrshp_num;
	String FirstName;
	String LastName;
	int ResConfNum = SW.RandomInteger(6);
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SPGLINK);
	}
	@Test(priority=0)
	public void DataSetupPostStayMerge() {
		SW.EstablishConnection("QA3");
		String Query="select m.mbrshp_num , p.first_name , p.last_name from odsft.freq_travel_mbrshp_prof p where m.mrktg_pgm = 5 and m.mbrshp_lvl = 'A' and m.mbrshp_sts = 'E' and m.join_date > (sysdate-30) and m.gst_master_prof_id != 0 and m.mbrshp_num=p.mbrshp_num";
		Mbrshp_num=SW.GetColumnValues(Query, "mbrshp_num").get(0);
		FirstName=SW.GetColumnValues(Query, "First_Name").get(0);
		LastName=SW.GetColumnValues(Query, "Last_Name").get(0);
		
		if(Mbrshp_num != null)
			Environment.loger.log(Level.INFO, "Member Exists " +Mbrshp_num);
		else
			SW.FailCurrentTest("No Member Exists, Check Query criteria");
		
	}
	@Test(priority=1,dependsOnMethods="DataSetupPostStayMerge")
	public void PostStaySPGMemberInvalidStatus(){
		SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId"));
		SW.SPGLinkChangeUserRole("Stay Posting");
		SW.WaitForPageload();
		SW.Click("SPGLink_Home_BT");
		SW.Click("SPGLinkHome_StayPost_DD");
		SW.Click("SPGLinkHome_StayPost_BT");
		SW.DropDown_SelectByText("SPGLinkPostSPGStay_Program_DD", "SPG Pro");
		SW.EnterValue("SPGLinkPostSPGStay_MbrshpNum_EB", Mbrshp_num);
		SW.WaitForPageload();
		SW.EnterValue("SPGLinkPostSPGStay_LastName_EB",LastName);
		SW.EnterValue("SPGLinkPostSPGStay_FirstName_EB", FirstName);
		SW.EnterValue("SPGLinkPostSPGStay_StarwoodReservation_EB", ResConfNum);
		SW.EnterValue("SPGLinkPostSPGStay_ArrDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -12,	 Calendar.DATE));
		SW.EnterValue("SPGLinkPostSPGStay_DeptDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -10,	 Calendar.DATE));
		SW.EnterValue("SPGLinkPostSPGStay_RatePlan_EB", "RACK");
		SW.EnterValue("SPGLinkPostSPGStay_RoomRevenue_EB", "100");
		SW.EnterValue("SPGLinkPostSPGStay_FoodRevenue_EB", "100");
		SW.EnterValue("SPGLinkPostSPGStay_OtherRevenue_EB", "100");
		SW.Click("SPGLinkPostSPGStay_Submit_BT");
		SW.Wait(30);
		
		if (SW.ObjectExists("SPGLinkPostSPGStay_Success_ST")) {
			Environment.loger.log(Level.INFO, "Stay Posted successfully for the member");
			SW.FailCurrentTest("Invalid status");
		} else if (SW.ObjectExists("SPGLinkPostSPGStay_Error_DT")){
			String ErrorMsg = SW.GetText("SPGLinkPostSPGStay_Error_DT");
			Environment.loger.log(Level.ERROR, "Stay posting has error since invalid status" + ErrorMsg);
			
		}
}
	@AfterClass
	public void EndTest(){
		SW.CloseDBConnection();
		Reporter.StopTest();		
	}

}
