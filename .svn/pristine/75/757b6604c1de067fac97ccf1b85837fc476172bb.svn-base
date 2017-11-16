package testscripts.SPGCentro;
/** Purpose		: This is to validate the member lookup role in centro 
 * TestCase Name: MemberLookup
 * Created By	: Indushree Lokesh
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

public class SPGCentro_REG03_MemberLookup {
	
	CRM SW = new CRM ();
	String Mbrshp_num;
       
	@BeforeClass
	public void StartTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SPGCENTRO);
		
	}
    
	@Test(priority=1)
	public void getDataMemberLookup(){
		SW.EstablishConnection("QA3");
		String query="select * from freq_travel_mbrshp where mbrshp_lvl='A' and mbrshp_sts='A'and src_cd='1US'AND join_date='13-OCT-04'";
		Mbrshp_num=SW.GetColumnValues(query, "Mbrshp_num").get(1);
		System.out.println(Mbrshp_num);
	}
	
	@Test(priority=2,dependsOnMethods="getDataMemberLookup")
	public void MemberLookup(){
		SW.SPGCentroLogin(SW.TestData("SPGLinkUsername"),SW.TestData("SPGLinkPassword"));
		SW.Click("SPGCentro_FindSPGMember_BT");
		SW.EnterValue("SPGCentro_FindSPGMember_Mbrshpnum_EB",Mbrshp_num);
		SW.Click("SPGCentro_FindSPGMember_Search_BT");
	//	Environment.loger.log(Level.INFO, "Member Lookup Successful");
		String member = SW.GetText("//td[@headers='MBRSHP_NUM']");
		
		if(SW.CompareText("membership Validation", member, Mbrshp_num)){
			Environment.loger.log(Level.INFO, "Member Lookup Successful");
		}else{
			Environment.loger.log(Level.INFO, "Member Lookup is not Successful");
		}
		
	
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("//span[text()='Logout']");
		Reporter.StopTest();
	}
	
}
