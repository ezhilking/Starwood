package testscripts.sgrRegression;
/** Purpose		: Validate GCD defaults in Admin screen
 * TestCase Name: Validate GCD defaults in Admin screen
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

public class SGR_REG69_ValidateGCDDefaultsInAdminScreen  {

	CRM SW = new CRM();
	String Group,Categoty,Detail;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.SGRURL);
		
	}
	@Test(priority=1)
	public void AddGCDDefault(){
		SW.SGRLogin(SW.TestData("SGRUsername"), SW.TestData("SGRPassword"), "1965");
		SW.Wait(20);
		SW.Click("SGRNavigation_Admin_LK");
		SW.Click("SGRAdmin_GCDDefaults_LK");
		
		SW.DropDown_SelectByIndex("SGRGCDDefault_Group_DD", 1);
		SW.Wait(2);
		Group=SW.DropDown_GetSelectedText("SGRGCDDefault_Group_DD");
		SW.DropDown_SelectByIndex("SGRGCDDefault_Category_DD", 1);
		SW.Wait(2);
		Categoty=SW.DropDown_GetSelectedText("SGRGCDDefault_Category_DD");
		SW.DropDown_SelectByIndex("SGRGCDDefault_Detail_DD", 1);
		SW.Wait(2);
		Detail=SW.DropDown_GetSelectedText("SGRGCDDefault_Detail_DD");
		SW.WaitForPageload();
		SW.DropDown_SelectByText("SGRGCDDefault_DefaultCompensation_DD","10 Meal Coupon");
		SW.CheckBox("SGRGCDDefault_DefaultEscalation_CB", "ON");
		SW.Click("SGRAssociate_Save_BT");
		
		if(SW.ObjectExists("//div[@id='errors' and contains(.,'Your changes to this G/C/D were successfully saved!')]")){
			Reporter.Write("Validate Adding Default to GCD", "Defaults should be added successfully", "Defaults are added successfully", "PASS");
		}else{
			Reporter.Write("Validate Adding Default to GCD", "Defaults should be added successfully", "Defaults are not added successfully", "FAIL");
		}
		//publish the changes 
		
		SW.Click("SGRNavigation_Admin_LK");
		//Publish the changes
		SW.Click("SGRAdmin_Publish_LK");
		SW.Click("SGRPublish_PublishConfYes_BT");
		if(SW.ObjectExists("//td[text()='Successfully published changes!']")){
			Environment.loger.log(Level.INFO, "Successfully published changes!");
			SW.TakeScreenshot("PublishSuccess");
		}else{
			Environment.loger.log(Level.ERROR, "Publish failed!");
			Reporter.Write("NA", "Publish failed!", "Publish failed!","FAIL");
		}
		
	}
	@Test(priority=2, dependsOnMethods="AddGCDDefault")
	public void ValidateGCDInEvents(){
		SW.NormalClick("SGRCreateEvent_NewEvent_LK");
		SW.EnterValue("SGRAddEvent_WhatField_EB", Detail);
		SW.Click("//li[text()='"+Group+" / "+Categoty+" / ']");
		if(SW.CheckBoxIsSelected("SGRCreateEvent_Escalation_CB")){
			Reporter.Write("Validate Escalation check box", "Escalation should be checked", "Escalation Check box is selected ", "PASS");
		}else{
			Reporter.Write("Validate Escalation check box", "Escalation should be checked", "Escalation check box is not selected by default", "FAIL");
		}
		SW.NormalClick("SGREvent_ServiceOpAndCompensation+_IC");
		String SelectedComensation= SW.DropDown_GetSelectedText("SGRCreateEvent_CompensationType_DD");
		SW.CompareText("Validate Compensation", "10 Meal Coupon", SelectedComensation);
	}
	
	@AfterClass
	public void EndTest(){
		SW.Click("SGR_Logout_LK");
		Reporter.StopTest();		
	}
}

