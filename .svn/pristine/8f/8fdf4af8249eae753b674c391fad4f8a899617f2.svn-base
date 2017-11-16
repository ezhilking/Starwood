package functions;
/* Purpose		:CRM- Application related reusable methods.
 * Developed By	:Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CRM extends DataBaseUtil{

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public WebDriver LaunchBrowser(String URL){
		super.LaunchBrowser(URL);
		if (GetBrowserInfo().startsWith("IE") && GetTitle().startsWith("Certificate Error")){
			driver.get("javascript:document.getElementById('overridelink').click();");
		}
		return driver;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public void WaitForPageload(){
		super.WaitForPageload();
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	@Deprecated
	public WebDriver LaunchSecondaryBrowser(String URL){
		try{
			String BrowserName = "FF";//Secondary will be always Firefox.
			if(driver == null){
				driver = GetBrowser(BrowserName);
				mainWindowHandle = driver.getWindowHandle();
				driver.manage().timeouts().implicitlyWait(IMPLICITWAIT, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver, EXPLICITWAIT);
				UserAction = new Actions(driver);
				driver.manage().window().maximize();
				driver.get(URL);
			}else{
				Environment.loger.log(Level.FATAL, "Previous borwser instance not closed");
			}
			//Override link for Internet Explorer
			//			if (GetBrowserInfo().startsWith("IE") && driver.getTitle().startsWith("Certificate Error")){
			//				driver.get("javascript:document.getElementById('onverridelink').click();");
			//			}
		}catch(WebDriverException e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return driver;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean SGRLogin(String Username, String Password, String PropertyID){
		EnterValue("SGRLogin_Username_EB", Username);
		EnterValue("SGRLogin_Password_EB", Password);
		if(ObjectExists("SGRLogin_UsePreviousSelection_CB")){
			CheckBox("SGRLogin_UsePreviousSelection_CB", "OFF");
		}
		Click("SGRLogin_LoginOrContinue_BN");
		if(ObjectExists("SGRLogin_Password_EB")){
			String ErrorMessage = GetText("General_ErrorMessage_DT");
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			DropDown_SelectByValue("SGRLogin_Property_DD", PropertyID);
			Click("SGRLogin_LoginOrContinue_BN");
			if(ObjectExists("SGRHomePage_JumpTo_EB")){
				Environment.loger.log(Level.INFO, "SGR Login Success");
				return true;
			}
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean CSFLogin(String Username, String Password){
		EnterValue("CSFLogin_Username_BN", Username);
		EnterValue("CSFLogin_Password_BN", Password);
		Click("CSFLogin_Submit_BN");
		if(ObjectExists("CSFLogin_Username_BN")){
			String ErrorMessage = GetText("General_ErrorMessage_DT"); //TODO Xpath update.
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			if(ObjectExists("CSF_EnterCSFID_EB")){
				Environment.loger.log(Level.INFO, "CSF Login Success");
				return true;
			}
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean GCLogin(String Username, String Password){
		EnterValue("GCLogin_Username_EB", Username);
		EnterValue("GCLogin_Password_EB", Password);
		Click("GCLogin_Login_BN");
		if(ObjectExists("GCLogin_Username_EB")){
			String ErrorMessage = GetText("General_ErrorMessage_DT"); //TODO Xpath update.
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			if(ObjectExists("GCHome_Search_EB")){
				Environment.loger.log(Level.INFO, "GC Login Success");
				return true;
			}
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean EmailLogin(String Username, String Password){
		EnterValue("EmailLogin_Username_EB", Username);
		Click("GCLogin_Login_BN");
		if(ObjectExists("EmailLogin_Username_EB")){
			String ErrorMessage = GetText("General_ErrorMessage_DT");//TODO Xpath update. 
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			if(ObjectExists("GCHome_Search_EB")){
				Environment.loger.log(Level.INFO, "GC Login Success");
				return true;
			}
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean BopLogin(String Username, String Password){
		EnterValue("BopLogin_UserName_EB", Username);
		EnterValue("BopLogin_Password_EB", Password);
		Click("BopLogin_Login_BN");
		if(ObjectExists("BopLogin_UserName_EB")){
			String ErrorMessage = GetText("General_ErrorMessage_DT"); //TODO Xpath update.
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			WaitTillElementToBeClickable("BopHome_GCAdmin_Lk");
			if(ObjectExists("BopHome_GCAdmin_Lk")){
				Environment.loger.log(Level.INFO, "BOP Login Success");
				return true;
			}
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*Login to Grip and seas-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean LoginToGrip(String UserName, String Password){
		boolean result=false;
		if(ObjectExists("//input[@name='username']")&& ObjectExists("//input[@name='password']")){
			EnterValue("//input[@name='username']", UserName);
			EnterValue("//input[@name='password']", Password);
			Click("//input[@value='submit']");
			if(ObjectExists("//a[text()='SGCADMIN']")){
				Click("//a[text()='SGCADMIN']");
				Environment.loger.log(Level.INFO, "Grip Login successful");
				result=true;
			}else{
				Environment.loger.log(Level.INFO, "Grip Login Failed");
				result=false;
			}
		}else result=false;
		return result;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean LoginToSeas(String UserName, String Password){
		boolean result=false;
		if(ObjectExists("//input[@name='username']")&& ObjectExists("//input[@name='password']")){

			EnterValue("//input[@name='username']", UserName);
			EnterValue("//input[@name='password']", Password);
			Click("//input[@value='submit']");
			if(ObjectExists("//a[text()='SGCADMIN']")){
				Click("//a[text()='SGCADMIN']");
				Environment.loger.log(Level.INFO, "Seas Login successful");
				result=true;
			}else{
				Environment.loger.log(Level.INFO, "Seas Login Failed");
				result=false;
			}
		}else result= false;

		return result;

	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*Grip Cache Refresh -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean GripCacheRefresh(String CacheName){

		Click("//ul[@class='menu']//a[@class='managecache']");
		if(ObjectExists("GC_CacheRefreshTable_TB")){
			WaitTillElementToBeClickable("//table[@id='cache']//tbody//a[text()='"+CacheName+"']");
			Click("//table[@id='cache']//tbody//a[text()='"+CacheName+"']");
			Click("//div[@id='content']/a");
			Click("//input[@value='update']");
			Environment.loger.log(Level.INFO, CacheName+" - Cache refreshed successfully");
			return true;
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*Seas Cache Refresh -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean SeasCacheRefresh(String CacheName){

		Click("//ul[@class='menu']//a[@class='managecache']");
		if(ObjectExists("GC_CacheRefreshTable_TB")){
			WaitTillElementToBeClickable("//table[@id='cache']//tbody//a[text()='"+CacheName+"']");
			Click("//table[@id='cache']//tbody//a[text()='"+CacheName+"']");
			Click("//div[@id='content']/a");
			Click("//input[@value='update']");
			Environment.loger.log(Level.INFO, CacheName+" - Cache refreshed successfully");
			return true;
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*Email Login-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean LoginToEmail(String EmailID, String UserName, String Password){
		if(ObjectExists("GCOutlookWebMail_LoginEmail_EB")){
			EnterValue("GCOutlookWebMail_LoginEmail_EB", EmailID+Keys.TAB);
		}
		WaitForPageload();
		WaitTillInvisibilityOfElement("GCOutlookWebMail_Redirecting_ST");
		EnterValue("GCOutlookWebMail_UserName_EB", UserName);
		EnterValue("GCOutlookWebMail_Password_EB", Password);
		Click("GCOutlookWebMail_Submit_BN");
		WaitForPageload();
		if(ObjectExists("GCOutlookWebMail_ErrorLogin_DT")){
			Environment.loger.log(Level.ERROR, "Invalid Username: "+UserName+",Password:"+Password+"");
			return false;
		}
		WaitTillElementToBeClickable("GCOutlookWebMail_SearchField_LT");
		if(!ObjectExists("GCOutlookWebMail_SearchField_LT")){
			Environment.loger.log(Level.ERROR, "Email login failed!!!");
			return false;
		}else{
			Environment.loger.log(Level.INFO, "Email login Successfull!!!");
			return true;
		}

	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*Email LogOut-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean LogoutFromEmail(){
		if(ObjectExists("GCOutlookWebMail_Logoutpanel_LK")){
			Click("GCOutlookWebMail_Logoutpanel_LK");
			NormalClick("GCOutlookWebMail_SignOut_LK");
			Environment.loger.log(Level.INFO, "Email logout Successfull!!!");
			return true;
		}
		return false;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean CreateEvent(String EventType,String RoomNumber, String LocationDetail,String Group, String Category, String Detail,String Department,String AssignTo,String CompensationType){
		if(!ObjectExists("SGRCreateEvent_EventType_DD")){

		}
		DropDown_SelectByText("SGRCreateEvent_EventType_DD", EventType);
		//RoomNumber & Location details
		if(!RoomNumber.isEmpty()){
			if(GetText("SGRCreateEvent_RoomNumber_EB").isEmpty())//If any pre populated value is available don't perform any action.
				EnterValue("SGRCreateEvent_RoomNumber_EB", RoomNumber); 
			if(GetText("SGRCreateEvent_LocationDetail_EB").isEmpty())//If any pre populated value is available don't perform any action.
				EnterValue("SGRCreateEvent_LocationDetail_EB", LocationDetail);
		}else{
			EnterValue("SGRCreateEvent_RoomNumber_EB", "tbd");
			EnterValue("SGRCreateEvent_LocationDetail_EB", "tbd");
		}

		//Group, Category and Detail.
		if(!Group.isEmpty()){
			DropDown_SelectByText("SGRCreateEvent_Group_DD", Group);
		}else{
			DropDown_SelectByText("SGRCreateEvent_Group_DD", "Billing");
		}

		if(!Category.isEmpty()){
			DropDown_SelectByText("SGRCreateEvent_Category_DD", Category);
		}else{
			int size = DropDown_GetSize("SGRCreateEvent_Category_DD");
			DropDown_SelectByIndex("SGRCreateEvent_Category_DD", RandomNumber(0, size-1));
		}

		if(!Detail.isEmpty()){
			DropDown_SelectByText("SGRCreateEvent_Detail_DD", Detail);
		}else{
			int size = DropDown_GetSize("SGRCreateEvent_Detail_DD");
			DropDown_SelectByIndex("SGRCreateEvent_Detail_DD", RandomNumber(0, size-1));
		}

		//Notes section
		EnterValue("SGRCreateEvent_Noted_EB", "Comment"+RandomString(5));

		//Department
		if(!Department.isEmpty()){
			DropDown_SelectByText("SGRCreateEvent_Department_DD", Department.toUpperCase());
		}else{
			DropDown_SelectByText("SGRCreateEvent_Department_DD", "ACCOUNTING");
		}

		//Assign to
		String AssignToSelectedText = DropDown_GetSelectedText("SGRCreateEvent_AssignTo_DD");
		if(AssignToSelectedText.isEmpty()){
			if(!AssignTo.isEmpty()){
				DropDown_SelectByText("SGRCreateEvent_AssignTo_DD", AssignTo);
			}else{
				int size = DropDown_GetSize("SGRCreateEvent_AssignTo_DD");
				DropDown_SelectByIndex("SGRCreateEvent_AssignTo_DD", RandomNumber(1, size-1));
			}

		}
		CheckBox("SGRCreateEvent_NotifyAssignee_CB", "OFF");
		if(ObjectExists("SGRCreateEvent_EscalationDisable_BN")){
			Click("SGRCreateEvent_EscalationDisable_BN");
		}

		//Compensation Type
		if(!CompensationType.isEmpty()){
			DropDown_SelectByText("SGRCreateEvent_CompensationType_DD", CompensationType);
		}else{
			if(!DropDown_SelectByText("SGRCreateEvent_CompensationType_DD", "No Guest Recovery"))
				DropDown_SelectByIndex("SGRCreateEvent_CompensationType_DD", RandomNumber(1,DropDown_GetSize("SGRCreateEvent_CompensationType_DD")-1));
		}
		Click("SGRCreateEvent_Save_BN");
		if(ObjectExists("SGRCreateEvent_Group_DD")){//If this dropdwon appears events has not been created successfully.
			String ErrorMessge = GetText("SGRCreateEvent_ErrorMessage_DT");
			CompareText("SGRCreateEvent_ErrorMessage_DT", "Event Should get created",ErrorMessge);
			return false;
		}
		String EventSuccess = GetText("General_ErrorMessage_DT");
		Environment.loger.log(Level.INFO, EventSuccess);
		return CompareTextContained("Event ID", EventSuccess);
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public Boolean ValidateIntegerSortOrder(List<String> UIData , String SortOrderAorD){
		boolean result=true;
		List<String> tempUIData = new ArrayList<String>();
		List<Integer>IntValues=new ArrayList<Integer>();
		try{
			for(int i=0; i<UIData.size();i++){
				if(UIData.get(i).equals(" ")){
					UIData.remove(i);
					break;
				}else
					IntValues.add(Integer.parseInt(UIData.get(i)));
			}
			if(SortOrderAorD.equalsIgnoreCase("A")){
				Collections.sort(IntValues);// sorts in ascending order
				Environment.loger.log(Level.INFO, "Data Sorted in Ascending order");

			}else if(SortOrderAorD.equalsIgnoreCase("D")){ 
				Collections.sort(IntValues, Collections.reverseOrder()); // sorts data in descending order
				Environment.loger.log(Level.INFO, "Data Sorted in Descending order");
			}	
			for(Integer I:IntValues)//Convert sorted integer values to strings 
				tempUIData.add(String.valueOf(I));

			for(int rowindex=0;rowindex<tempUIData.size();rowindex++){//Compare sorted values to UI values
				if(!CompareText(UIData.get(rowindex), tempUIData.get(rowindex))){
					result=false;
					Environment.loger.log(Level.ERROR, "Values are not in Sorted order");
					Reporter.Write("ValidateIntegerSort", tempUIData.toString(),UIData.toString(), "FAIL");
					break;
				}
			}
			if(result){
				Environment.loger.log(Level.INFO, "UI Values are in sorted Order");
				Reporter.Write("ValidateSortOrder", tempUIData.toString(), UIData.toString(), "PASS");
			}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured"+e);
		}

		return result;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public Boolean ValidateStringSortOrder(List<String> UIData , String SortOrderAorD){
		boolean result=true;
		List<String> tempUIData = new ArrayList<String>();
		// Take copy of UI Data and Sort it from collections
		for(String s:UIData)
			tempUIData.add(s);
		try{
			if(SortOrderAorD.equalsIgnoreCase("A")){
				Collections.sort(UIData);// sorts in ascending order
				Environment.loger.log(Level.INFO, "Data Sorted in Ascending order");

			}else if(SortOrderAorD.equalsIgnoreCase("D")){ 
				Collections.sort(tempUIData, Collections.reverseOrder()); // sorts data in descending order
				Environment.loger.log(Level.INFO, "Data Sorted in Descending order");
			}	
			for(int rowindex=0;rowindex<UIData.size();rowindex++){//Compare sorted values to UI values
				if(!CompareText(UIData.get(rowindex), tempUIData.get(rowindex))){
					result=false;
					Environment.loger.log(Level.ERROR, "Values are not in Sorted order");
					Reporter.Write("ValidateIntegerSort", tempUIData.toString(), UIData.toString(), "FAIL");
					break;
				}
			}
			if(result){
				Environment.loger.log(Level.INFO, "UI Values are in sorted Order");
				Reporter.Write("ValidateSortOrder", tempUIData.toString(), UIData.toString(), "PASS");
			}
		}
		catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception Occured"+e);
		}

		return result;
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public String GetEventNumbeID(){
		String EventSuccess = GetText("General_ErrorMessage_DT");
		return EventSuccess.substring(EventSuccess.indexOf("ID")+2, EventSuccess.indexOf("was")).trim();
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public FileUtil DownloadFile(String...Parameter){
		String Source = null;
		String FileModule  = Parameter[0];
		String FileName = Parameter[1];
		String Filter  = Parameter[2];

		FileUtil File = new FileUtil();
		try{
			File.DestinationPath = Reporter.ScreenshotPath+"\\"+ GetTimeStamp("hhmmss")+"-"+FileName;
			if(FileModule.equalsIgnoreCase("Room Assignment") && (Filter.equalsIgnoreCase("All Guests") || Filter.equalsIgnoreCase("In-House") ||Filter.equalsIgnoreCase("Arriving")) && FileName.endsWith(".pdf")){
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=MASTER_PDF&preColumnOrder=30^2^12^32&ambColumnOrder=NO_VALUE&expColumnOrder=NO_VALUE&exportUPG=false&exportSPG=false&exportAMB=false&exportALL=false&exportEXP=false&exportPRE=true";
			}else if(FileModule.equalsIgnoreCase("Custom Arrival Report") && FileName.endsWith(".pdf")){
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=ADHOC_PDF";
			}else if(FileModule.equalsIgnoreCase("Ambassador") && FileName.endsWith(".pdf")){
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=MASTER_PDF&preColumnOrder=NO_VALUE&ambColumnOrder=101^109^110^102&expColumnOrder=NO_VALUE&exportUPG=false&exportSPG=false&exportAMB=true&exportALL=false&exportEXP=false&exportPRE=false";
			}else if(FileModule.equalsIgnoreCase("Room Assignment") && FileName.endsWith(".xls")){//Room assignment(PRE)-Excel
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=MASTER_EXCEL&exportEmail=aravind.reddy@accenture.com&preColumnOrder=NO_VALUE&ambColumnOrder=NO_VALUE&expColumnOrder=NO_VALUE&exportUPG=false&exportSPG=true&exportAMB=false&exportALL=false&exportEXP=false&exportPRE=false";
			}else if(FileModule.equalsIgnoreCase("GCAhbbRelated") && FileName.endsWith(".xlsx")){//GC Excel - Reservation related 
				Source = "/SGCommunications/viewReport.svc?reportName=Perf_adhoc_"+DateAddDays(GetTimeStamp("yyyyMMdd"), "yyyyMMdd", -1, Calendar.DATE)+"_"+GetTimeStamp("yyyyMMdd")+"_PROP"+".xlsx";
			}
			else if(FileModule.equalsIgnoreCase("GCReservarionRelated") && FileName.endsWith(".xlsx")){//GC Excel - Reservation related 
				Source = "/SGCommunications/viewReport.svc?reportName=Perf_resrelated_false_"+DateAddDays(GetTimeStamp("yyyyMMdd"), "yyyyMMdd", -1, Calendar.DATE)+"_"+GetTimeStamp("yyyyMMdd")+".xlsx";

				//TODO Part
			}else if(FileModule.equalsIgnoreCase("Room Assignment") && Filter.equalsIgnoreCase("In-House") && FileName.endsWith(".pdf")){//TODO
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=MASTER_PDF&exportEmail=aravind.reddy@accenture.com&preColumnOrder=30^2^3^29^4^5^31^6^7^8^9^10^11^51^12^32^13^14^15^16^17^18^19^20^21^22^23&ambColumnOrder=NO_VALUE&expColumnOrder=NO_VALUE&exportUPG=false&exportSPG=false&exportAMB=false&exportALL=false&exportEXP=false&exportPRE=true";
			}else if(FileModule.equalsIgnoreCase("Ambassador") && FileName.endsWith(".pdf")){//Ambassador(AMB)
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=MASTER_PDF&exportEmail=aravind.reddy@accenture.com&preColumnOrder=NO_VALUE&ambColumnOrder=101^121^102^103^104^105^106^107^109^110^111^112^113^114^115^116^117&expColumnOrder=NO_VALUE&exportUPG=false&exportSPG=false&exportAMB=true&exportALL=false&exportEXP=false&exportPRE=false";
			}else if(FileModule.equalsIgnoreCase("Sevice Opportunities") && FileName.endsWith(".pdf")){//Service Opportunities(EXP)
				Source = "/sgr/arrivalReportExportAction.do?exportReportType=MASTER_PDF&exportEmail=aravind.reddy@accenture.com&preColumnOrder=NO_VALUE&ambColumnOrder=NO_VALUE&expColumnOrder=201^202^203^204^205^206^207^208^209^210^211^212&exportUPG=false&exportSPG=false&exportAMB=false&exportALL=false&exportEXP=true&exportPRE=false";
			}


			URL AppURL = new URL(driver.getCurrentUrl());
			File.SourcePath = AppURL.getProtocol() + "://" + AppURL.getAuthority()+Source;
			if(File.DispatchFile()){
				return File;
			}else{
				Environment.loger.log(Level.ERROR, "File was not downloaded:"+FileName);
			}
			return File;
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return null;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public WebDriver LaunchBrowserWeChat(){
		String URL = Environment.DataPath+"\\Documents\\"+Environment.Tower+"\\WeChat_To_Starwood_EMW_Connnector_en_US.HTML";
		return LaunchBrowser(URL);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	//Write to TestData.xls
	public void WriteToEmailTestData(String TestCaseName, String TextID,String TextToWrite){//TODO
		ExcelUtil.TestData.WriteToEmailTestData(TestCaseName, TextID, TextToWrite);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	//Retrieve TextID from TestData.xls 
	public String GetEmailTestData(String TestCaseName, String TextID){//TODO
		return ExcelUtil.TestData.GetEmailTestData(TestCaseName,TextID);
	}

	//*-*-*-*-*-*-*-*-*-Reservation SOAP Request Modification*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public SOAPMessage ChangeTransactionIDInSoapRequest(SOAPMessage SoapRequest){

		String TransactionID=SoapUtility.getXMLElementText(SoapRequest,"p977:RequestContextBean", "p357:RequestTransactionID");
		String NewTransactionID=TransactionID.substring(0, 25)+GetTimeStamp("yyyyMMddhhmmss");
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "p977:RequestContextBean", "p357:RequestTransactionID",NewTransactionID);

		return SoapRequest;
	}

	public SOAPMessage ChangeArrivalDepartureDateINSoapRequest(SOAPMessage SoapRequest, String FutureArrivalDate,String FutureDepartureDate ){

		//Get Arrival Date from Request 
		String actualArrivalDateStamp=SoapUtility.getXMLElementText(SoapRequest, "ConfirmationDTO", "arrivalDate");
		String actualArrivalTimeStamp=actualArrivalDateStamp.substring(10, actualArrivalDateStamp.length());

		//Get DepartureDate from Request
		String actualDepartureDateStamp=SoapUtility.getXMLElementText(SoapRequest, "ConfirmationDTO", "departureDate");
		String actualDepartureTimeStamp=actualDepartureDateStamp.substring(10, actualDepartureDateStamp.length());

		String NewArrivalTimeStamp=FutureArrivalDate+actualArrivalTimeStamp;

		String NewDepartureTimeStamp=FutureDepartureDate+actualDepartureTimeStamp;
		//Set Arrival dates
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "ConfirmationDTO", "arrivalDate", NewArrivalTimeStamp);
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "ConfirmationDTO", "startTime", NewArrivalTimeStamp);
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "ProductDTO", "startTime", NewArrivalTimeStamp);
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "RateDTO", "startDate", NewArrivalTimeStamp);
		//Set Departure dates
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "ConfirmationDTO", "departureDate", NewDepartureTimeStamp);
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "ConfirmationDTO", "endTime", NewDepartureTimeStamp);
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "ProductDTO", "endTime", NewDepartureTimeStamp);
		SoapRequest=SoapUtility.setXMLElementText(SoapRequest, "RateDTO", "endDate", NewArrivalTimeStamp);

		return SoapRequest;
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public void WaitForAppLoad(){
		//WaitTillInvisibilityOfElement("CSF_PleaseWaitLoadingIcon_IC");
	}


	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean WeChatLogin(String Username, String Password){
		EnterValue("WCLogin_Username_EB", Username);
		EnterValue("WCLogin_Pwd_EB", Password);
		Click("WCLogin_Submit_BT");
		String sSecQue=GetText("WCLogin_SecQues_DT");
		String lastWord = sSecQue.substring(sSecQue.lastIndexOf(" ")+1);
		String sAnswer=lastWord.substring(0,lastWord.indexOf("?")).trim();
		//	System.out.println(sAnswerString);
		EnterValue("WCLogin_SecQuesAns_EB",sAnswer);
		Click("WCLogin_Continue_BT");
		if(ObjectExists("WCLogin_Error_DT")){
			String ErrorMessage = GetText("WCLogin_Error_DT"); //TODO Xpath update.
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			if(ObjectExists("WCHome_Destin_EB")){
				Environment.loger.log(Level.INFO, "WEChat Login Success!!!");
				return true;
			}
			else 
				return false;
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean CoronaLogin(String Username, String Password, String Appname) {
		boolean result = false;
		EnterValue("CoronaLogin_ApplName_EB", Appname);
		EnterValue("CoronaLogin_Username_EB", Username);
		EnterValue("CoronaLogin_Password_EB", Password);
		Click("CoronaLogin_Login_BT");
		if (ObjectExists("CoronaAdmin_APIDoc_LK")) {
			Environment.loger.log(Level.INFO, "Corona login Successfully!!!!");
			result = true;
		} else {
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+ Username + "Password: " + Password);
			throw new RuntimeException("Login failed for Username: " + Username + "Password: "+ Password);
		}
		return result;
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean CEMAMBLogin(String Username, String Password){
		boolean result;
		EnterValue("CEMAMBlogin_UserName_EB", Username);
		EnterValue("CEMAMBlogin_Password_EB", Password);
		Click("CEMAMBlogin_SignIn_BT");
		if(ObjectExists("//div[@class='error']")){
			Environment.loger.log(Level.ERROR, "Login Failed!!");
			result= false;
		}else{
			Environment.loger.log(Level.INFO, "Logged in to CEM AMB Application successfully");
			result=true;
		}
		return result;
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean DiscoveryLogin(String Username, String Password){
		EnterValue("DiscLogin_UserName_EB", Username);
		EnterValue("DiscLogin_Password_EB", Password);
		Click("DiscLogin_Login_BT");
		if(ObjectExists("DiscLogin_UserName_EB")){
			String ErrorMessage = GetText("General_ErrorMessage_DT"); //TODO Xpath update.
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password+" Error: "+ErrorMessage);
		}else{
			if(ObjectExists("DiscManageSurvey_CreateSurvey_BT")){
				Environment.loger.log(Level.INFO, "Discovery Login Success");
				return true;
			}
		}
		return false;
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public void DiscoveryEnterValue(String Identifier,CharSequence Text){
		try{
			WebElement ActionObject = GetObject(Identifier);
			ActionObject.sendKeys(Text);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Unable to enter the value in "+Identifier);
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean SPGLinkLogin(String Username, String Password, String Propid){
		boolean result;
		//Click("SPGLink_continueToWebsite_LK");
		EnterValue("SPGLinkLogin_PropId_EB", Propid);
		EnterValue("SPGLinkLogin_UserName_EB", Username);
		EnterValue("SPGLinkLogin_Password_EB", Password);
		Click("SPGLinkLogin_Button_BT");
		if(ObjectExists("SPGLinkLogin_error_DT")){
			Environment.loger.log(Level.ERROR, "Login Failed!!");
			result= false;
		}else{
			Environment.loger.log(Level.INFO, "Logged in to SPGLink Application successfully");
			result=true;
		}
		return result;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean SPGLinkChangeUserRole(String Role) {
		boolean result = false;
		Click("SPGLink_Home_BT");
		Click("SPGLink_Administration_BT");
		Click("SPGLinkAdmin_SuperUserSelect_BT");
		DropDown_SelectByText("SPGLinkAdmin_RolesList_DD", Role);
		DropDown_SelectByText("SPGLinkAdmin_RoleChangeAlert_DD", "Yes");
		Click("SPGLinkAdmin_ChangeRoleSet_BT");
		if (ObjectExists("SPGLinkAdmin_RoleChangeAlert_DT")) {
			Environment.loger.log(Level.INFO, "Role Changed Successfully!!");
			result = true;
		} else {
			Environment.loger.log(Level.ERROR, "Role not changed");
			result = false;
		}
		return result;
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	public boolean SPGCentroLogin(String UserName, String Password) {
		// TODO Auto-generated method stub
		boolean result = false;
		EnterValue("SPGCentro_UserName_EB", UserName);
		EnterValue("SPGCentro_Password_EB", Password);
		Click("SPGCentro_Login_BT");
		if(ObjectExists("SPGCentro_LoginError_DT")){
			Environment.loger.log(Level.ERROR, "Login Failed!!");
			result= false;
		}else{
			Environment.loger.log(Level.INFO, "Logged in to SPGLink Application successfully");
			result=true;
		}
		return result;

	} 
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	public boolean SPGLightLogin(String LightUserName , String Password) {
		boolean result;
		EnterValue("SPGLight_UserName_EB", LightUserName);
		EnterValue("SPGLight_Password_EB", Password);
		Click("SPGLight_Login_BT");
		if(!ObjectExists("SPGLight_Login_BT")){
			Environment.loger.log(Level.INFO, "Logged in to SPGLight Application successfully");
			result= true;
		}else{
			Environment.loger.log(Level.ERROR, "Login Failed!!");
			result= false;
		}

		return result;
	}  


}
