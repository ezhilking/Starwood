package support;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functions.CRS;
import functions.Environment;
import functions.Reporter;

public class SUP03_CRSBookingTrgger {
	public static void main(String[] args) {
		CRS SW= new CRS();
		Environment.Tower = "CRS";
		Reporter.StartTest();
		SW.EstablishConnection("NDSQA3");
		List<String> res_id = SW.GetColumnValues("select res_id from BOOKING2.res where  prop_id = 1036", 1);
		if(res_id.size()>0){
			Environment.SetBrowserToUse("FF");
			WebDriver d=SW.LaunchBrowser(Environment.BOOKING);
			List<WebElement> bn =d.findElements(By.xpath("//a[@href='/BookingWeb/retriggerMsgToPMS.jsp']"));
			bn.get(0).sendKeys(Keys.ENTER);

			//			SW.Click("//a[@href='/BookingWeb/retriggerMsgToPMS.jsp']");
			List<WebElement> ResID = SW.GetAllVisibleElements("//*[@name='anyIds']");
			List<String> Str = new ArrayList<>();
			Str.addAll(res_id);
			StringBuilder f = new StringBuilder();
			for(int i=501;i<600;i++){
				f.append(res_id.get(i)+",");
			}
			Environment.loger.log(Level.INFO, f.toString());
			ResID.get(0).sendKeys(f.toString());

			//			for(int i=0;i<res_id.size();i++){
			//				ResID.get(0).sendKeys(res_id.get(i)+",");
			//			}
			//			SW.WaitForPageToLoad();
			Reporter.Write("AllReservationNumber", "Entered all reservation numbers into booking application", "Entered all reservation numbers into booking application", "PASS");
			SW.Click("//input[@name='reTrigger']");
		}else{
			Reporter.WriteLog(Level.FATAL, "SQL query which is executed doesn't return any value");
		}
		Reporter.StopTest();
	}

}
