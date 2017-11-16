package testscripts.sgrRegression;
/** Purpose		: Validate the newly added GCD in GCD admin screen and in new events screen by adding the new GCD in Add GCDs admin Screen in SGR
 * TestCase Name: Validate the newly added GCD in GCD admin screen and in new events screen by adding the new GCD in Add GCDs admin Screen in SGR
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

public class SGR_REG20_ValidateAddGCD  {

	CRM SW = new CRM();
	String Group,Categoty,Detail;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
		
	}
	@Test(priority=1)
	public void AddGCD(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Wait(20);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_AddGCD_LK");
		Group="AutomationGroup_"+SW.RandomString(3);
		Categoty="AutomationCategory_"+SW.RandomString(3);
		Detail="AutomationDetail_"+SW.RandomString(3);
		SW.EnterValue("SGRAddGCD_Group_EB", Group);
		SW.Click("SGRAddGCD_Group_BT");
		SW.EnterValue("SGRAddGCD_Category_EB", Categoty);
		SW.Click("SGRAddGCD_Category_BT");
		SW.EnterValue("SGRAddGCD_Detail_EB", Detail);
		SW.Click("SGRAddGCD_Detail_BT");
		SW.DropDown_SelectByText("SGRAddGCD_GroupID_DD", Group);
		SW.DropDown_SelectByText("SGRAddGCD_CategoryID_DD", Categoty);
		SW.DropDown_SelectByText("SGRAddGCD_DetailID_DD", Detail);
		int detailCode=SW.RandomNumber(100000, 999999);
		SW.EnterValue("SGRAddGCD_DetailCode_EB", detailCode);
		SW.DropDown_SelectByValue("SGRAddGCD_DefaultDepartment_DD", "14");
		SW.CheckBox("SGRAddGCD_isEvent_CB", "ON");
		SW.Wait(5);
		SW.CheckBox("SGRAddGCD_isEventMappable_CB", "ON");
		SW.Click("SGRAddGCD_AddSelectedGCD_BT");
		if(SW.ObjectExists("//div[@id='info' and contains(.,'A new GCD was created successfully')]")){
			Environment.loger.log(Level.INFO, "New GCD is added successfully");
			SW.GetScreenshot("AddNewGCDSuccess");
		}else{
			Environment.loger.log(Level.ERROR, "Failed to add GCD");
			SW.FailCurrentTest("Failed to add GCD");
		}
		
	}

	@Test(priority=2, dependsOnMethods="AddGCD")
	public void RefreshCache(){
		Group="Automationasda";//TODO Add refresh cache code and update the Group to created GCD 
	}
	@Test(priority=3, dependsOnMethods="RefreshCache")
	public void ValidateAddedGCD(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_GCD_LK");
		if(SW.ObjectExists("//a[text()='"+Group+"']")){
			SW.Click("//a[text()='Automationasda']");
			SW.CheckBox("//td[text()='"+Group+"']//..//input[@name='actv0']", "ON");
			SW.Click("SGRAddGCD_Save_BT");
			Environment.loger.log(Level.INFO, "GCD is Enabled");
		}else{
			Environment.loger.log(Level.ERROR, "Added GCD is not available in the list");
			SW.FailCurrentTest("Added GCD is not available in the list");
		}
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Environment.loger.log(Level.INFO, "Successfully published changes!");
			SW.GetScreenshot("PublishSuccess");
		}else{
			Environment.loger.log(Level.ERROR, "Publish failed!");
			SW.FailCurrentTest("Publish failed!");
		}
	}
	@Test(priority=4, dependsOnMethods="ValidateAddedGCD")
	public void ValidateMapGCD(){
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_Admin_LK");
		SW.Click("SGRAdmin_GCDRules_LK");
		SW.DropDown_SelectByText("SGRGCDRules_Group_DD", Group);
		SW.DropDown_SelectByText("SGRGCDRules_Category_DD", Group);
		SW.DropDown_SelectByText("SGRGCDRules_Detail_DD", Group);
		SW.CheckBox("SGRGCDRules_DefectGuest_CB", "ON");
		SW.SelectRadioButton("SGRGCDRules_DefectGuestIsPrimary_RB");
		SW.Click("SGRGCDRules_Save_BT");
		if(SW.ObjectExists("//span[text()='Successfully saved GCD Rule...']")){
			Environment.loger.log(Level.INFO, "Successfully saved GCD Rule...");
			SW.GetScreenshot("GCDRuleSuccess");
		}else{
			Environment.loger.log(Level.ERROR, "Adding GCD Rule failed!");
			SW.FailCurrentTest("Adding GCD Rule failed!");
		}
	}
	@Test(priority=5, dependsOnMethods="ValidateMapGCD")
	public void ValidateGCDInEvents(){
		SW.Wait(10);
		SW.NormalClick("SGRAdmin_SGAdminEvents_IC");
		SW.EnterValue("SGRAddEvent_WhatField_EB", Group);
		SW.Wait(5);
		if(SW.ObjectExists("//span[text()='"+Group+"']")){
			
			Environment.loger.log(Level.INFO, "Created GCD Loaded");
			SW.GetScreenshot("GCDCreateEventSuccess");
			SW.Click("//span[text()='"+Group+"']");
			}else{
				Environment.loger.log(Level.ERROR, "Created GCD is not loaded in create event screen");
				SW.FailCurrentTest("Created GCD is not loaded in create event screen");
			}
		String SelectedEventType=SW.DropDown_GetSelectedText("SGRNewEvent_EventType_DD");
		String SelectedEventOrigin=SW.DropDown_GetSelectedText("");
		SW.CompareText("CompEventType", "Defect", SelectedEventType);
		SW.CompareText("CompEventOrigin", "Guest", SelectedEventOrigin);
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

