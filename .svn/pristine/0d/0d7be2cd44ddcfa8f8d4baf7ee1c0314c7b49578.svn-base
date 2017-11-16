package testscripts.sgrRegression;
/** Purpose		: Validate SG Champion feature in Associates section   
 * TestCase Name: 1. Validate SG Champion when no champions are selected
			 	  2. Validate SG Champion when one Champion is selected
				  3. Validate SG Champion when two champions are selected
 * Created By	: Sachin G 
 * Modified By	:
 * Modified Date:
 * Reviewed By	: 
 * Reviewed Date: 
 */
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.CRM;

public class SGR_REG66_ValidateSGChampionFunctionalityInAssociateSection  {

	CRM SW = new CRM();
	String Message, FName, LName, FName2, LName2;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.SGRURL);
	}
	@Test(priority=1)
	public void SelectSGChampion(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.NormalClick("SGRAssociate_ChampionMessagePause_IC");
		if(SW.ObjectExists("//div[@id='messageStop' and text()='Your hotel has not designated a StarGuest Champion ']")){
			Reporter.WriteLog(Level.INFO, "No champion is selected for the property");
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_Associate_LK");	
			SW.DropDown_SelectByText("SGRAssociate_RoleFilter_DD", "Super-User");
			SW.Click("SGRAssociate_Filter_BT");
			SW.Click("SGRAssociate_FirstBlueUser_IC");
			FName= SW.GetText("SGRAssociate_FirstName_EB");
			LName= SW.GetText("SGRAssociate_LastName_EB");
			SW.CheckBox("SGRAssociate_ChampionFlag_CB", "ON");
			SW.Click("SGRAssociate_Save_BT");
			SW.CompareText("Validate Success Messge", FName+" "+LName+" was successfully updated!", SW.GetText("//span[@class='error']//li"));
			SW.NormalClick("SGRAssociate_ChampionMessagePlay_IC");
			SW.NormalClick("SGRAssociate_ChampionMessagePause_IC");
			Message= SW.GetText("SGRAssociate_ChampionMessage_DT");
			SW.CompareText("Validate Champion Message", FName+" "+LName+" is your StarGuest Champion", Message);
		}
		if(SW.ObjectExists("//div[@id='messageStop' and contains(.,'is your StarGuest Champion')]")){
			Reporter.WriteLog(Level.INFO, "There is one SGR Champion for this property");
			if(SW.ObjectExists("SGRAssociate_ChampionMessagePause_IC"))
				SW.NormalClick("SGRAssociate_ChampionMessagePause_IC");
			Message= SW.GetText("SGRAssociate_ChampionMessage_DT");
			//Get the first name and last name of the SG champion selected from the message
			FName= Message.substring(0, Message.indexOf(" ")).trim();
			LName= Message.substring(Message.indexOf(" "), Message.indexOf("is")).trim();
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_Associate_LK");	
			SW.DropDown_SelectByText("SGRAssociate_RoleFilter_DD", "Super-User");
			SW.Click("SGRAssociate_Filter_BT");
			//Click on the user which is not selected as the SG champion
			SW.Click("(//img[contains(@src,'user-blue.gif')]//ancestor::table[@id='userTable']//td[3][not(text()='"+FName+"')])[1]");
			FName2= SW.GetText("SGRAssociate_FirstName_EB");
			LName2= SW.GetText("SGRAssociate_LastName_EB");
			SW.CheckBox("SGRAssociate_ChampionFlag_CB", "ON");
			SW.Click("SGRAssociate_Save_BT");
			SW.CompareText("Validate Success Messge", FName2+" "+LName2+" was successfully updated!", SW.GetText("//span[@class='error']//li"));
			SW.NormalClick("SGRAssociate_ChampionMessagePlay_IC");
			SW.NormalClick("SGRAssociate_ChampionMessagePause_IC");
			Message= SW.GetText("SGRAssociate_ChampionMessage_DT");
			SW.CompareText("Validate Champion Message for two guests", FName2+" "+LName2+" and "+FName+" "+LName+" are your StarGuest Champions", Message);
		}
		if(SW.ObjectExists("//div[@id='messageStop' and contains(.,'are your StarGuest Champion')]")){
			Reporter.WriteLog(Level.INFO, "There are two SGR Champion for this property");
			if(SW.ObjectExists("SGRAssociate_ChampionMessagePause_IC"))
				SW.NormalClick("SGRAssociate_ChampionMessagePause_IC");
			Message= SW.GetText("SGRAssociate_ChampionMessage_DT");
			FName= Message.substring(0, Message.indexOf(" ")).trim();
			LName=Message.substring(Message.indexOf(" "), Message.indexOf("and")).trim();
			String FnameLname2=Message.substring(Message.indexOf("and")+3, Message.indexOf("are")).trim();
			FName2=FnameLname2.substring(0, FnameLname2.indexOf(" "));
			LName2=FnameLname2.substring(FnameLname2.indexOf(" "), FnameLname2.length()).trim();
			SW.Click("SGRNavigation_Admin_LK");
			SW.Click("SGRAdmin_Associate_LK");	
			SW.DropDown_SelectByText("SGRAssociate_RoleFilter_DD", "Super-User");
			SW.Click("SGRAssociate_Filter_BT");
			//Select a guest which is not selected as SG Champion
			SW.Click("(//img[contains(@src,'user-blue.gif')]//ancestor::table[@id='userTable']//td[3][not(text()='"+FName+"')][not(text()='"+FName2+"')])[1]");
			SW.IsEnabled("SGRAssociate_ChampionFlag_CB", "Disabled");
			SW.Click("(//img[contains(@src,'user-blue.gif')]//ancestor::table[@id='userTable']//td[3][(text()='"+FName+"')])[1]");
			SW.CheckBox("SGRAssociate_ChampionFlag_CB", "OFF");
			SW.Click("SGRAssociate_Save_BT");
			SW.Click("(//img[contains(@src,'user-blue.gif')]//ancestor::table[@id='userTable']//td[3][(text()='"+FName2+"')])[1]");
			SW.CheckBox("SGRAssociate_ChampionFlag_CB", "OFF");
			SW.Click("SGRAssociate_Save_BT");
			SW.NormalClick("SGRAssociate_ChampionMessagePlay_IC");
			SW.NormalClick("SGRAssociate_ChampionMessagePause_IC");
			Message= SW.GetText("SGRAssociate_ChampionMessage_DT");
			SW.CompareText("Validate Champion Message for two guests", "Your hotel has not designated a StarGuest Champion ", Message);

		}

	}

	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

