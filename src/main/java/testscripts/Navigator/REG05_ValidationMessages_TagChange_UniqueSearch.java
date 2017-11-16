package testscripts.Navigator;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: SPG Search -Screen Layout-SPG Search Validation Messages- Tag Change -Unique Search 
 * TestCase Name: REG05_ValidationMessages_TagChange_UniqueSearch.java
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG05_ValidationMessages_TagChange_UniqueSearch {
	CHANNELS SW=new CHANNELS() ;
	String Caption;
	String EnterMsg;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}
	@Test(priority=0)
	public void  ValidatingMessageByNumber()
	{
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		Caption=SW.GetText("NavigatorHomePage_BynumCaption_ST");
		if(SW.CompareTextContained("Compare the Text in By Number", "Only enter 1 of the following:",Caption))
			Environment.loger.log(Level.INFO,"The Caption is Presented As Expected in By Number");
		else{
			Environment.loger.log(Level.ERROR,"The Caption Doesnt Match");
			SW.FailCurrentTest("Validation Fails in checking Caption in By Number");
		}
		EnterMsg=SW.GetText("NavigatorHomePage_PressEnter_ST");
		if(SW.CompareTextContained("Compare the Enter Message in By Number", "Or press  ENTER  on your keyboard.",EnterMsg))
			Environment.loger.log(Level.INFO,"The Enter Message is Presented As Expected in By Number");
		else{
			Environment.loger.log(Level.ERROR,"The Caption Doesnt Match");
			SW.FailCurrentTest("Validation Fails in checking Enter message in By Number");
		}
	}

	@Test(priority=1)
	public void ValidateMessageByName()
	{
		SW.NormalClick("NavigatorHomePage_SearchByName_LK"); // CLicking on the link By Name
		SW.DoubleClick("NavigatorHomePage_SearchByName_LK");
		Caption=SW.GetText("NavigatorHomePage_ByNameCaption_ST");
		if(SW.CompareTextContained("Compare the Text in By Number", "Enter Last Name and 1 additional search option:",Caption))
			Environment.loger.log(Level.INFO,"The Caption is Presented As Expected in By Name");
		else{
			Environment.loger.log(Level.ERROR,"The Caption Doesnt Match");
			SW.FailCurrentTest("Validation Fails in checking Caption in By Name");
		}
		EnterMsg=SW.GetText("NavigatorHomePage_PressEnter_ST");
		if(SW.CompareTextContained("Compare the Enter Message in By Name", "Or press  ENTER  on your keyboard.",EnterMsg)){
			Environment.loger.log(Level.INFO,"The Enter Message is Presented As Expected in By Name");
		}else{
			Environment.loger.log(Level.ERROR,"The Caption Doesnt Match");
			SW.FailCurrentTest("Validation Fails in checking Enter message in By Name");
		}
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
