package testscripts.gcRegression;
/** Purpose		: Validate_that_Permanent_Property_access_do_not_allow_alpha/special chars_entry in the Property ID filed
 * TestCase Name: GC_REG97_ValidatePermanentPropertyAccessDoNotAllowAlphaOrSpecialCharsEntry
 * Created By	: Sindhu SR
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

import org.apache.log4j.Level;
import org.apache.xpath.operations.Equals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
public class GC_REG97_ValidatePermanentPropertyAccessDoNotAllowAlphaOrSpecialCharsEntry {

	CRM SW = new CRM();
	String Username, Password, Username1, Password1;
	String PropertyID="110", sMessage, OfferID;

	@BeforeClass
	public void StartTest(){
		Environment.Tower="CRM";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.GCURL);
		Username=SW.TestData("GCUsername");
		Password=SW.TestData("GCPassword");
	}


	@Test
	public void AlphaAndSpecialCharsValidation(){
		try{
			SW.GCLogin(Username, Password);
			if(SW.ObjectExists("GCHome_Message_DT")){
				SW.NormalClick("GCHome_Message_Close_IC");
				if(SW.ObjectExists("GCHome_Message_DT")){
					SW.DoubleClick("GCHome_Message_Close_IC");
				}
			}
			SW.Click("GCHome_Admin_LK");
			SW.Click("GCUsrMngmnt_LK");
			SW.Click("GCUsrMngmnt_Userid_EB");
			SW.EnterValue("GCUsrMngmnt_Userid_EB", Username);
			SW.Click("GCUsrMngmnt_Search_BT");
			SW.WaitTillElementToBeVisible("GCUsrMngmnt_Edit_BT");
			SW.Click("GCUsrMngmnt_Edit_BT");
		//	SW.CheckBox("GCAdmin_PropertyCheckBox_CB", "ON");
			SW.CheckBox("//input[@name='rolePropertyInd']", "ON");

			//Verifying Properties field allows only numbers & not Alpha/Special Characters in 'User Mgmt - User Search' screen

			//Validation with alpha characters
			SW.WaitTillElementToBeVisible("GCAdmin_UserManagement_PropertyId_EB");
			SW.EnterValue("GCAdmin_UserManagement_PropertyId_EB", "ABCD");
			String PropertyText = SW.GetText("GCAdmin_UserManagement_PropertyId_EB");
			if(PropertyText!= "ABCD"){
			//if(SW.CompareText("Validate Property ID Field Text", "ABCD", PropertyText) == false){
				Environment.loger.log(Level.INFO, "Alphabets cannot be entered in Property field");
				Reporter.Write("Validate Alphabet", "Alphabets cannot be entered in Property field", "Alphabets are not entered in Property field", "PASS");
			}
			//Validation with special characters
			SW.EnterValue("GCAdmin_UserManagement_PropertyId_EB", "!@#$");
			String PropertyText1 = SW.GetText("GCAdmin_UserManagement_PropertyId_EB");
			if(PropertyText!= "!@#$"){
			//if(SW.CompareText("Validate Property ID Field Text", "!@#$", PropertyText1) == false){
				Environment.loger.log(Level.INFO, "Special Characters cannot be entered in Property field");
				Reporter.Write("Validate Special Characters ", "Special Characters  cannot be entered in Property field", "Special Characters  are not entered in Property field", "PASS");
			}
			//Validation with numbers
			SW.EnterValue("GCAdmin_UserManagement_PropertyId_EB", "110");
			String PropertyId = SW.GetText("GCAdmin_UserManagement_PropertyId_EB");
			SW.CompareText("Validate Property ID", "110", PropertyId);
			Environment.loger.log(Level.INFO, "Property ID could be entered in Property field");

			SW.EnterValue("GCAdmin_UserManagement_PropertyId_EB", "110");
			SW.CheckBox("GCAdmin_FieldMarketerCheckBox_CB", "ON");
			SW.Click("GCAdmin_UserManagement_Save_BT");
			if(SW.ObjectExists("GCHome_GreenMsg_DT")){
				Environment.loger.log(Level.INFO,"Data is saved successfully");
				Reporter.Write("Validate Your Account Access Update status", " Updates to the user role shoule be saved", " Updates to the user role is saved", "PASS");
				if(SW.ObjectExists("GC_MyAccount_IC")){
					SW.NormalClick("GC_MyAccount_IC");
					SW.Click("GC_MyAccount_SignOut_LK");
				}
			}else{
				Environment.loger.log(Level.INFO,"Data is not saved");
				Reporter.Write("Validate Your Account Access Update status", " Updates to the user role is saved", "Updates to the user role is not saved", "Fail");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Alpha And Special Chars Validation in Properties Field Is Failed",e);
		}

	}

	@AfterClass
	public void EndTest(){
		if(SW.ObjectExists("GCNavigation_SignOut_LK")){
			SW.Click("GCNavigation_SignOut_LK");
		}
		Reporter.StopTest();		
	}
}
