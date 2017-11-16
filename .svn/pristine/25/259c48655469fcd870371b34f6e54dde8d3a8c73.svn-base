package testscripts.SPGLink;
/** Purpose		: This is to post stay for the member under designStayPoster role in SPGLink 2.0 design hotels
 * TestCase Name: Designstayposter
 * Created By	: Indushree Lokesh
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

public class SPGLink_REG40_Designstayposter {

	CRM SW = new CRM();
    String Mbrshp_num;
    String FirstName;
    String LastName;
    int ResConfNum = SW.RandomInteger(6);
    
    @BeforeClass
    public void StartTest(){
                    Environment.Tower = "CRM";
                    Reporter.StartTest();
                    /*Environment.SetBrowserToUse("FF");*/
                    SW.LaunchBrowser(Environment.SPGLINK);
    }
    @Test(priority=0)
    public void DataSetupDesignstayposter() {
                    SW.EstablishConnection("QA3");
                    String Query="select m.mbrshp_num , p.first_name , p.last_name from odsft.freq_travel_mbrshp_prof p, odsft.freq_travel_mbrshp m where m.mrktg_pgm = 5 and m.mbrshp_lvl = 'A' and m.mbrshp_sts = 'A' and m.join_date > (sysdate-5) and m.gst_master_prof_id != 0 and m.mbrshp_num=p.mbrshp_num";
                    Mbrshp_num=SW.GetColumnValues(Query, "Mbrshp_num").get(2);
                    FirstName=SW.GetColumnValues(Query, "First_Name").get(2);
                    LastName=SW.GetColumnValues(Query, "Last_Name").get(2);
                    
                    if(Mbrshp_num != null)
                                    Environment.loger.log(Level.INFO, "MemberExsists "+Mbrshp_num);
                    else
                                    SW.FailCurrentTest("No Member Exists, Change Query Criteria");
    }
    @Test(priority=1,dependsOnMethods="DataSetupDesignstayposter")
    public void Designstayposter(){
                    SW.SPGLinkLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"),SW.TestData("SPGLinkPropId_DesignHotel"));
                    SW.SPGLinkChangeUserRole("designStayPoster");//Change user role
                    SW.WaitForPageload();
                    SW.Click("SPGLink_Home_BT");
                    SW.Click("SPGLinkHome_DesignStayPost_DD");
                    SW.Click("SPGLinkHome_StayPost_BT");
                    SW.DropDown_SelectByText("SPGLinkPostSPGStay_Program_DD", "SPG");
                    SW.EnterValue("SPGLinkPostSPGStay_MbrshpNum_EB", Mbrshp_num);
                    SW.WaitForPageload();
                    SW.EnterValue("SPGLinkPostSPGStay_LastName_EB",LastName);
                    SW.EnterValue("SPGLinkPostSPGStay_FirstName_EB", FirstName);
                    SW.EnterValue("SPGLinkPostSPGStay_StarwoodReservation_EB", ResConfNum);
                    SW.EnterValue("SPGLinkPostSPGStay_ArrDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -12,     Calendar.DATE));
                    SW.EnterValue("SPGLinkPostSPGStay_DeptDate_EB", SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -10,     Calendar.DATE));
                    SW.EnterValue("SPGLinkPostSPGStay_RatePlan_EB", "RACK");
                    SW.EnterValue("SPGLinkPostSPGStay_RoomRevenue_EB", "100");
                    SW.EnterValue("SPGLinkPostSPGStay_FoodRevenue_EB", "100");
                    SW.EnterValue("SPGLinkPostSPGStay_OtherRevenue_EB", "100");
                    SW.Click("SPGLink_DesignPostStay_Submit_BT");
                    if(SW.ObjectExists("SPGLink_DesignPostStay_Submit_BT"))
                    	  SW.Click("SPGLink_DesignPostStay_Submit_BT");
                    SW.Wait(15);
                    if (SW.ObjectExists("SPGLinkPostSPGStay_Success_ST")) {
                                    Environment.loger.log(Level.INFO, "Stay Posted successfully for the member");
                    } else if (SW.ObjectExists("SPGLinkPostSPGStay_Error_DT")){
                                    String ErrorMsg = SW.GetText("SPGLinkPostSPGStay_Error_DT");
                                    Environment.loger.log(Level.ERROR, "Stay posting has error" + ErrorMsg);
                                    SW.FailCurrentTest(ErrorMsg);
                    }
}
    @AfterClass
    public void EndTest(){
                    SW.Click("SPGLink_LogOut_BT");
                    Reporter.StopTest();                      
    }

}
