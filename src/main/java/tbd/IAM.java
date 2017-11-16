package tbd;

import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class IAM {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		//		SW.WriteToTestData("ExtendedTitle", "tyagiTheboss");
		SW.LaunchBrowser("http://stardb2.pii.star:8002/sap/bc/bsp/ehr/pdm/start.htm");
	}

	String LastName,FirstName,Email; 
	@Test(priority=1)
	public void AMS(){
		SW.EnterValue("AMSLogin_User_EB", "ANDRKON");
		SW.EnterValue("AMSLogin_Password_EB", "ZQ5AWB3B");
		SW.IAMClick("AMSLogin_LogOn_BN");
		SW.SwitchToFrame("AMSHome_FirstLink_FR");
		SW.IAMClick("(//div[@class='tree_trig'])[1]//a");
		SW.SwitchToFrame("");
		SW.SwitchToFrame("AMSHome_Menu_FR");
		SW.Click("AMSHome_NewPosition_BN");//Click new position
		SW.SwitchToFrame("");
		SW.SwitchToFrame("AMSHome_Detail_FR");
		//SW.DropDown_SelectByIndex("AMSNewPosition_AreaCategory_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSNewPosition_AreaCategory_DD")-1));
		
		//int index = 0;
		//do{
		//	SW.DropDown_SelectByIndex("AMSNewPosition_Function_DD", ++index);
		//}while(!(SW.DropDown_GetSelectedText("AMSNewPosition_Function_DD").contains("/")||(SW.DropDown_GetSelectedText("AMSNewPosition_Function_DD").contains("-"))));
		
		SW.DropDown_SelectByIndex("AMSNewPosition_AreaCategory_DD", 1);
		SW.Wait(30);
		SW.DropDown_SelectByIndex("AMSNewPosition_Function_DD", 9);
		SW.Wait(30);

		//TODO Handle the loop if the dropdown doesn't contain '/'
		SW.DropDown_SelectByIndex("AMSNewPosition_SubFunction_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSNewPosition_SubFunction_DD")-1));
		SW.DropDown_SelectByIndex("AMSNewPosition_StandardTitle_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSNewPosition_StandardTitle_DD")-1));

		String ExtendedTitle = SW.RandomString(5);
		SW.EnterValue("AMSNewPosition_ExtendedTitle_EB", ExtendedTitle);
		SW.DropDown_SelectByIndex("AMSNewPosition_Level_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSNewPosition_Level_DD")-1));
		SW.DropDown_SelectByIndex("AMSNewPosition_ContractType_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSNewPosition_ContractType_DD")-1));
		SW.DropDown_SelectByIndex("AMSNewPosition_ContractSubType_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSNewPosition_ContractSubType_DD")-1));
		SW.EnterValue("AMSNewPosition_NoOfCopies_EB", 1);
		SW.IAMClick("AMSNewPosition_Save_BN");


		SW.WriteToTestData("ExtendedTitle", ExtendedTitle);
		SW.SwitchToFrame("");
		SW.SwitchToFrame("AMSHome_FirstLink_FR");
		SW.IAMClick("//*[text()='"+ExtendedTitle+"']");

		SW.SwitchToFrame("");
		SW.SwitchToFrame("AMSHome_Menu_FR");
		SW.IAMClick("AMSHome_NewHire_BN");

		SW.SwitchToFrame("");
		SW.SwitchToFrame("AMSHome_Detail_FR");
		LastName = SW.RandomString(5);
		SW.EnterValue("AMSAssignNewHire_LastName_EB", LastName);
		FirstName = SW.RandomString(5);
		SW.EnterValue("AMSAssignNewHire_FirstName_EB", FirstName);
		SW.EnterValue("AMSAssignNewHire_MiddleName_EB", SW.RandomString(5));
		SW.EnterValue("AMSAssignNewHire_BirthDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
		SW.DropDown_SelectByText("AMSAssignNewHire_Gender_DD", "Male");
		SW.DropDown_SelectByText("AMSAssignNewHire_Nationality_DD", "Indian");
		SW.EnterValue("AMSAssignNewHire_HireDate_EB", SW.GetTimeStamp("MM/dd/yyyy"));
		Email = SW.RandomString(4);
		SW.EnterValue("AMSAssignNewHire_OtherEmail_EB", Email+"abcd@accenure.com");
		SW.IAMClick("AMSAssignNewHire_Save_BN");

		SW.DropDown_SelectByText("AMSAssignNewHire_Action_DD", "New Hire");
		SW.DropDown_SelectByIndex("AMSAssignNewHire_Reason_DD", SW.RandomNumber(1, SW.DropDown_GetSize("AMSAssignNewHire_Reason_DD")-1));
		SW.IAMClick("AMSAssignNewHire_Save2_BN");//Need to check xpath
		SW.SwitchToFrame("");
		SW.SwitchToFrame("AMSHome_Menu_FR");
		Environment.loger.log(Level.INFO, SW.GetText("AMSAssignNewHire_SuccessMsg_DT"));//New hire creation
		SW.Wait(600);
		//Logout
		//		SW.Click("AMSHome_HumanResourseManager_LK");
		//		SW.WaitForWindowCount(1);
		//		SW.Click("AMSHome_NewPosition_BN");
		//		

	}
	@Test(priority=2)
	public void IAMAdmin(){
		SW.NavigateTo("https://stage.identity.starwoodhotels.com/sysadmin");
		SW.EnterValue("IAMAdmin_Username_EB", "padmyal");
		SW.EnterValue("IAMAdmin_Password_EB", "Woods#3");
		SW.Click("IAMAdmin_SignIn_BN");
		SW.Click("IAMAdmin_Scheduler_LK");
		SW.SwitchToWindow(2);
		SW.Wait(30);
		SW.EnterValue("IAMScheduler_Search_EB", "*SAP*");
		SW.Click("IAMScheduler_Search_BN");

		//Running move to Stage
		SW.Click("IAMScheduler_MoveToJob_LK");
		SW.Click("IAMScheduler_RunNow_BN");
		do{
			SW.Click("IAMScheduler_Refresh_BN");
			SW.Wait(10);
		}while(!SW.CompareText("Stopped", SW.WebTbl_GetText("IAMScheduler_JobHistory_WT", 1, 3)));

		//Running move to Stage
		SW.Click("IAMScheduler_ReconFrmJob_LK");
		SW.Click("IAMScheduler_RunNow_BN");
		do{
			SW.Click("IAMScheduler_Refresh_BN");
			SW.Wait(10);
		}while(!SW.CompareText("Stopped", SW.WebTbl_GetText("IAMScheduler_JobHistory_WT", 1, 3)));


		//Running BackFeed to Stage
		SW.Click("BackFeed");
		SW.Click("IAMScheduler_RunNow_BN");
		do{
			SW.Click("IAMScheduler_Refresh_BN");
			SW.Wait(10);
		}while(!SW.CompareText("Stopped", SW.WebTbl_GetText("IAMScheduler_JobHistory_WT", 1, 3)));
		SW.CloseOnlyThisBrowser();
	}


	@Test(priority=3)
	public void IAMIdentity(){
		SW.NavigateTo("https://stage.identity.starwoodhotels.com/identity");

		SW.Click("IAMIdentity_Manage_BN");
		SW.Click("IAMIdentity_Users_LK");
		SW.DropDown_SelectByText("IAMIdentityUsers_Search_DD", "First Name");
		SW.EnterValue("IAMIdentityUsers_Search_EB", FirstName);
		SW.Click("IAMIdentityUsers_Search_BN");
		SW.WebTbl_Click("IAMIdentityUsers_Summary_WT", 1, 2);
		SW.WaitForPageload();
		SW.Click("IAMIdentityUsers_Attributes_LK");
		SW.CompareText("IAMIdentityAttributes_IdentityStatus_ST", "Active", SW.GetText("IAMIdentityAttributes_IdentityStatus_ST"));
		SW.CompareText("IAMIdentityAttributes_AccountStatus_ST", "Unlocked", SW.GetText("IAMIdentityAttributes_AccountStatus_ST"));



		//Validation in accounts tab
		SW.Click("IAMIdentityUsers_Accoutns_LK");
		SW.CompareText("", "Enterprise LDAP", SW.WebTbl_GetText("IAMIdentityUsers_Accoutns_WT", 1, 1));
		SW.CompareText("", "Provisioned", SW.WebTbl_GetText("IAMIdentityUsers_Accoutns_WT", 1, 5));

	}
}


