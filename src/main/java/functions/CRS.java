/* Purpose		:CRS-Application related reusable methods.
 * Developed By	:Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
package functions;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CRS extends DataBaseUtil{

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public WebDriver LaunchBrowser(String URL){
		super.LaunchBrowser(URL);
		if (GetBrowserInfo().startsWith("IE") && GetTitle().startsWith("Certificate Error")){
			driver.get("javascript:document.getElementById('overridelink').click();");
		}
		return driver;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public void VPWaitForPageload(){
		super.WaitTillInvisibilityOfElement("(//div[@id='loading']//img)[1]");//div[@id='loading']//img[@id='loadImage']
		super.WaitTillInvisibilityOfElement("(//div[@id='loading']//img)[2]");//div[@id='dialog']//img
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public void Click(String ObjectName){
		super.Click(ObjectName);
		this.VPWaitForPageload();
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public void VPClick(String ObjectName){
		try{
			WebElement ActionObject = GetObject(ObjectName);
			if(ActionObject.isEnabled() && ActionObject.isDisplayed()){
				if((ActionObject.getTagName().compareToIgnoreCase("input")==0 || ActionObject.getTagName().compareToIgnoreCase("button")==0)){
					ActionObject.sendKeys(Keys.SPACE);
				}else{
					Environment.loger.log(Level.FATAL, "Don't use VPClick() method for this Object-'"+ObjectName+"'");
				}
				super.WaitForPageload();
				this.VPWaitForPageload();
			}else{
				Environment.loger.log(Level.ERROR,ObjectName + " button is disabled");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception is captured check screenshot: "+GetScreenshot("Exception"),e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public boolean VPLogin(String username, String Password){
		SwitchToFrame("VP_MainFrame_FR");
		EnterValue("VP_Username_EB", username);	
		EnterValue("VP_Password_EB", Password);
		ClickAndProceed("VPInventory_Submit_BN");
		Wait(3);
		super.WaitForPageload();
		if(ObjectExists("VPInventory_Username_EB")){
			Environment.loger.log(Level.ERROR, "Login failed for "+username+","+Password);
			return false;
		}else{
			Environment.loger.log(Level.INFO, "Login successfull");
			return true;
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

}
