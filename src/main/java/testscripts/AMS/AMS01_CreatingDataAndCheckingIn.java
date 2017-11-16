package testscripts.AMS;

import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class AMS01_CreatingDataAndCheckingIn {

	SALES SW = new SALES();
	String Position;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.AMSURL);
	}
	@Test
	public void CreatingDataAndCheckingInIAM(){
			SW.EnterValue("AMS_UserName_EB", SW.TestData("AMSUserID"));
			SW.EnterValue("AMS_Password_EB", SW.TestData("AMSPassword"));
			SW.Click("AMS_LogOn_BT");
			SW.WaitTillElementToBeClickable("AMS_RegionalDirectorHR_LK");
   		    SW.SwitchToFrame("AMS_OrganizationStructureFrame_FR");
			SW.Click("AMS_RegionalDirectorHR_LK");
			SW.Wait(40);
			SW.WaitForWindowCount(1);
			SW.SwitchToFrame("");
			SW.SwitchToFrame("AMS_PositionFrame_FR");
			SW.WaitTillElementToBeClickable("AMS_CreateNewPostion_BT");
			SW.NormalClick("AMS_CreateNewPostion_BT");
			SW.SwitchToFrame("");
			SW.SwitchToFrame("AMS_DetailsFrame_FR");
			SW.WaitTillElementToBeClickable("AMS_EnterDate_ST");
			//SW.EnterValue("AMS_EnterDateST", "06/20/2014");
			String Date = "07/20/2015";
//			SW.RunJavaScript("document.getElementsByName('PWE1')[0].value='"+Date+"';");
			SW.DropDown_SelectByIndex("AMS_Propertydetail_DD", 3);
			//String exceldata=SW.GetSalesTestData("AMS_NewPositionDetails_DD","AreaCategory");
			SW.DropDown_SelectByIndex("AMS_AreaCategoryDetail_DD",2 );
			//SW.GetSalesTestData("AMS_NewPositionDetails_DD","Function");
			SW.DropDown_SelectByIndex("AMS_FunctionDetail_DD",4);
			//SW.GetSalesTestData("AMS_NewPositionDetails_DD","Subfunction");
			SW.DropDown_SelectByIndex("AMS_SubfunctionalDetail_DD",1);
			SW.DropDown_SelectByIndex("AMS_StandardTitleDetail_DD",7);
			//SW.EnterValue("AMS_NewPositionDetails_DD", "Title");
			Position=SW.RandomString(5);
			SW.EnterValue("AMS_ExtendedTitleDetails_EB",Position);
			SW.DropDown_SelectByIndex("AMS_LevelDetail_DD",1);
			SW.DropDown_SelectByIndex("AMS_ContractType_DD",1);
			SW.DropDown_SelectByIndex("AMS_ContractSubType_DD",1);
			SW.ClearValue("AMS_EnterDate_ST");
			SW.HandleAlert(true);
			SW.EnterValue("AMS_EnterDate_ST","07/20/2015");
			SW.Click("AMS_Save_LK");
			if(SW.ObjectExists("AMS_EnterDate_ST"))
				SW.Click("AMS_Save_LK");
			SW.WaitForWindowCount(1);
			SW.Wait(5);
			SW.SwitchToFrame("");
			SW.SwitchToFrame("AMS_TreeFrame_FR");
			//SW.wait(30);
			//Position=SW.RandomString(5);
			if(SW.ObjectExists("//a[text()='"+Position+"']")){
				SW.Click("//a[text()='"+Position+"']");
			}else{
				Environment.loger.log(Level.ERROR, "Position is not present in the list");
				SW.FailCurrentTest("Position is not present in the list");
			}
			SW.Wait(30);
			SW.SwitchToFrame("");
			SW.SwitchToFrame("AMS_PositionFrame_FR");
			SW.NormalClick("AMS_AssignNewHire_BT");
			SW.SwitchToFrame("");
			SW.SwitchToFrame("AMS_DetailsFrame_FR");
			SW.WaitTillElementToBeClickable("AMS_LastName_EB");
			SW.EnterValue("AMS_LastName_EB","Nikkie");
			SW.EnterValue("AMS_FirstName_EB","Singh");
			SW.DropDown_SelectByIndex("AMS_Gender_DD",1);
			//String Date1="06/20/1990" ,Date2="08/20/2015";
			//SW.RunJavaScript("document.getElementsById('P0002____GBDAT')[0].value='"+Date1+"';");
			//SW.EnterValue("AMS_BirthDate_EB","Nikkie");
			SW.DropDown_SelectByIndex("AMS_Nationality_DD",8);
			SW.EnterValue("AMS_HireDate_EB","07/07/1992");
			//SW.RunJavaScript("document.getElementsById('P0041____DAT01')[0].value='"+Date2+"';");
			SW.EnterValue("AMS_EmailId_EB","ruchikasi@gmail.com");
			/*SW.ClearValue("AMS_BirthDate_EB");
			SW.HandleAlert(true);*/
			SW.EnterValue("AMS_BirthDate_EB","07/07/1992");
			SW.Click("AMS_SaveLink_LK");
			SW.WaitForWindowCount(1);
			SW.WaitTillElementToBeClickable("AMS_ActionDetail_DD");
			SW.DropDown_SelectByIndex("AMS_ActionDetail_DD",1);
			SW.DropDown_SelectByText("AMS_ReasonDetail_DD","New Hire");
			SW.EnterValue("AMS_DateDetail_EB","07/20/2015");
			//SW.RunJavaScript("document.getElementsByName('P0000____BEGDA')[0].value='"+Date+"';");
			SW.Click("AMS_SavePosition_LK");
	}
            
	@AfterClass
	public void StopTest(){
		SW.Click("AMS_Logout_LK");
		Reporter.StopTest();
	}
}


