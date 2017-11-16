package testscripts.Discovery;
/** Purpose		: Verify_Options_Language_Brand_Division_Survey channel_Booking Channel_Dropdowns
 * TestCase Name: Verify_Options_Language_Brand_Division_Survey channel_Booking Channel_Dropdowns
 * Created By	: Sachin
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class REG09_VerifyOptionsLanguageBrandDivisionSurveyChannelBookingChannel {
	CRM SW = new CRM();
	String UserName;
	String Password;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");// Script will not work in IE Driver because of IE Driver issue
		SW.LaunchBrowser(Environment.DISCOVERYURL);
		UserName=SW.TestData("DisUsername");
		Password=SW.TestData("DisPassword");
	
	}

	@Test(priority=1)
	public void ValidateLangugaesDropDown(){
		SW.DiscoveryLogin(UserName, Password);
		SW.Click("DiscManageSurvey_CreateSurvey_BT");
		
		List<String> UILanguages= SW.DropDown_GetText("DiscCreateSurvey_SurveyLanguage_DD");
		List<String> ExpLanguages=new ArrayList<String>();
		//Add expected languages in the list
		ExpLanguages.add("");
		ExpLanguages.add("Arabic(ar-AE)");
		ExpLanguages.add("Chinese(zh-CN)");
		ExpLanguages.add("Chinese(zh-TW)");
		ExpLanguages.add("Dutch(nl-NL)");
		ExpLanguages.add("English(en-US)");
		ExpLanguages.add("French(fr-FR)");
		ExpLanguages.add("German(de-DE)");
		ExpLanguages.add("Indonesian(id-ID)");
		ExpLanguages.add("Italian(it-IT)");
		ExpLanguages.add("Japanese(ja-JP)");
		ExpLanguages.add("Korean(ko-KR)");
		ExpLanguages.add("Polish(pl-PL)");
		ExpLanguages.add("Portuguese(pt-BR)");
		ExpLanguages.add("Russian(ru-RU)");
		ExpLanguages.add("Spanish(es-ES)");
		ExpLanguages.add("Thai(th-TH)");
		ExpLanguages.add("Turkish(tr-TR)");
		
		if(UILanguages.equals(ExpLanguages)){
			Environment.loger.log(Level.INFO, "All Languages are present in the list ");
		}else{
			Environment.loger.log(Level.ERROR, "All languages are not present in the list ");
			SW.FailCurrentTest("All languages are not present in the list ");
		}
	}
	
	@Test(priority=2)
	public void ValidateBrandDropDown(){
			
		List<String> UIBrands= SW.DropDown_GetText("DiscCreateSurvey_SurveyBrand_DD");
		List<String> ExpBrands=new ArrayList<String>();
		//Add expected languages in the list
		ExpBrands.add("");
		ExpBrands.add("Aloft");
		ExpBrands.add("CCC");
		ExpBrands.add("Corp");
		ExpBrands.add("Element");
		ExpBrands.add("Four Points");
		ExpBrands.add("LeMeridien");
		ExpBrands.add("Luxury Collection");
		ExpBrands.add("Non-Branded");
		ExpBrands.add("Sheraton");
		ExpBrands.add("SPG");
		ExpBrands.add("St.Regis");
		ExpBrands.add("W Hotels");
		ExpBrands.add("Westin");
		
		if(UIBrands.equals(ExpBrands)){
			Environment.loger.log(Level.INFO, "All Brands are present in the list ");
		}else{
			Environment.loger.log(Level.ERROR, "All Brands are not present in the list ");
			SW.FailCurrentTest("All Brands are not present in the list ");
		}
	}
	@Test(priority=2)
	public void ValidateSurveyChanelDropDown(){
			
		List<String> UIChanel= SW.DropDown_GetText("DiscCreateSurvey_SurveyChanel_DD");
		List<String> ExpChanel=new ArrayList<String>();
		//Add expected languages in the list
		ExpChanel.add("");
		ExpChanel.add("Post-Stay");
		ExpChanel.add("Pre-Stay");
		ExpChanel.add("Reservation Conf/Cxl");
		ExpChanel.add("Targeted Email");
		ExpChanel.add("N/A");
		
		if(UIChanel.equals(ExpChanel)){
			Environment.loger.log(Level.INFO, "All Chanels are present in the list ");
		}else{
			Environment.loger.log(Level.ERROR, "All Chanels are not present in the list ");
			SW.FailCurrentTest("All Chanels are not present in the list ");
		}
	}
	
	
	@AfterClass
	public void EndTest(){
		SW.Click("Disclogout_logout_BT");
		Reporter.StopTest();		
	}
}
