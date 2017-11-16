/* Purpose		:SALES-Application related reusable methods.
 * Developed By	:Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
package functions;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SALES extends Utility {

	public WebDriver LaunchBrowser(String URL){
		super.LaunchBrowser(URL);
		driver.manage().timeouts().pageLoadTimeout(30,  TimeUnit.SECONDS);//pageLoadTimeout Only for ABCD application
		if (GetBrowserInfo().startsWith("IE") && GetTitle().startsWith("Certificate Error")){
			driver.get("javascript:document.getElementById('overridelink').click();");
		}
		return driver;
	}

	public void WaitForPageload(){
		super.WaitForPageload();
	}

	public void IAMClick(String ObjectName){
		super.Click(ObjectName);
		long startTime, endTime, diffTime;
		//Get the starting Time
		startTime = System.currentTimeMillis();
		boolean ReturnValue = false;
		wait = new WebDriverWait(driver, 120);
		do{
			endTime = System.currentTimeMillis();
			diffTime = TimeUnit.MILLISECONDS.toMinutes(endTime - startTime);
			if(diffTime > 10){//10 minutes
				Environment.loger.log(Level.INFO,"After 10 minutes also window count is still 2. So quiting the wait.");
				break;
			}
			try{
				wait.until(ExpectedConditions.numberOfwindowsToBe(1));
				ReturnValue = true;
			}catch(TimeoutException e){
				ReturnValue = false;
				Environment.loger.log(Level.ERROR, "Still loading!!! Wait for a moment");
			}
		}while(!(ReturnValue));
		wait = new WebDriverWait(driver, EXPLICITWAIT);
	}

	public boolean ABCDLogin(String Username, String Password){
		EnterValue("//input[@name='userName']", "poorman");
		EnterValue("//input[@name='password']", "password");
		Click("//input[@name='Submit']");
		//		EnterValue("ABCD_Username_EB", Username);
		//		EnterValue("ABCD_Password_EB", Password);
		//		Click("ABCD_Submit_BN");
		if(ObjectExists("//input[@name='userName']")){
			Environment.loger.log(Level.ERROR, "Login failed for Username: "+Username+" Password: "+Password);
			throw new RuntimeException("Login failed for Username: "+Username+"Password: "+Password);
		}
		return true;
	}

	public FileUtil DownloadFile(String...Parameter){
		String Source = null;
		String FileModule  = Parameter[0];
		String FileName = Parameter[1];

		FileUtil File = new FileUtil();
		try{
			File.DestinationPath = Reporter.ScreenshotPath+"\\"+ GetTimeStamp("hhmmss")+"-"+FileName;
			if(FileModule.equalsIgnoreCase("Contact Report") && FileName.endsWith(".xls")){
				Source = "/report/orContactsReport.do";
			}else if(FileModule.equalsIgnoreCase("Status Report") && FileName.endsWith(".xls")){
				Source = "/report/orStatusReport.do";
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

}
