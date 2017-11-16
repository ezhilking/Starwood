package testscripts.Navigator;
/* Purpose		: Verify Negative Build Version window 
 * TestCase Name: REG76_ValidateAboutNavigatorWindow 
 * Created By	: Pradeep kumar
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class REG76_ValidateAboutNavigatorWindow{
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){ 
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void VerifyNavigator_build(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword"));
		SW.SelectCommunicationType();
		SW.NormalClick("NavigatorHomePage_CallCountry_BT"); //Clicking on the select a type
		SW.EnterValue("NavigatorHomePage_CallCountry_EB", SW.TestData("CallfromCountry")+ Keys.ENTER); //entering the entire-value and pressing tab
		SW.Click("NavigatorHomePage_AboveNavigator_IC");
		//Verify Alternate language drop down in Cog Menu.
		if(SW.ObjectExists("NavigatorHomePage_AlternateLangauge_DT")) {
			if(SW.ObjectExists("NavigatorHomePage_AboveNavigator_LK"))
				SW.Click("NavigatorHomePage_AboveNavigator_LK");
			else
				Environment.loger.log(Level.ERROR,"Above Navigator link does not Exist");
			if(SW.CompareText("Validating NAvigator Version Window","About Navigator",SW.GetText("NavigatorHomePage_AboveNavigatorVersion_ST"))){
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_Userid_ST"))
					Reporter.Write("Verify NAvigator Version", "User Name", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Userid_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator Version", "User Name", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Userid_ST"), "Fail");
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_Locid_ST"))
					Reporter.Write("Verify NAvigator Version", "Location id", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Locid_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator Version", "Location id", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Locid_ST"), "Fail");
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_CurrentTime_ST"))
					Reporter.Write("Verify NAvigator Version", "Current Time", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_CurrentTime_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator Version", "Location id", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_CurrentTime_ST"), "Fail");
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_Builddate_ST"))
					Reporter.Write("Verify NAvigator Version", "Build Date", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Builddate_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator Version", "Build Date", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Builddate_ST"), "Fail");
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_BuildVersion_ST"))
					Reporter.Write("Verify NAvigator ", "Build Version", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_BuildVersion_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator ", "Build Version", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_Builddate_ST"), "Fail");
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_HostName_ST"))
					Reporter.Write("Verify NAvigator ", "Host Name", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_HostName_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator ", "Host Name", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_HostName_ST"), "Fail");
				if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_ReleaseName_ST"))
					Reporter.Write("Verify NAvigator ", "Release Name", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_ReleaseName_ST"), "PASS");
				else
					Reporter.Write("Verify NAvigator ", "Release Name", SW.GetText("NavigatorHomePage_AboveNavigatorVersion_ReleaseName_ST"), "Fail");
			}else 
				Reporter.Write("Navigator Login ", "User Name not same as ", SW.GetText("NavigatorUsername"), "Fail");
		}
		if(SW.ObjectExists("NavigatorHomePage_AboveNavigatorVersion_Close_BT")){
			SW.NormalClick("NavigatorHomePage_AboveNavigatorVersion_Close_BT"); //Close Navigator Version Window
			Reporter.Write("Verify NAvigator ", "Version window close button", "Closed", "PASS");
		}else 
			Reporter.Write("Verify Navigator", "Version Window Close Button ", SW.GetText("NavigatorUsername"), "Fail");
	}


	@AfterClass()
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();
	}
}

