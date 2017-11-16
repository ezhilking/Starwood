package support;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.sikuli.script.Key;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;
import functions.SikuliUtil;

public class SUP02_Wave {

	@Test
	public void Wave() {
		CRM SW = new CRM();
		Environment.Tower = "CRM";
		Reporter.StartTest();
		//		SW.LaunchBrowser("http://wave.webaim.org/report#/http://www.marriott.com/");
		SW.LaunchBrowser("http://www.marriott.com/");
		SW.WaitForPageload();
		SikuliUtil SK = new SikuliUtil();
		SK.SikuliFocusRegionWindow();
		SK.SikuliClick("WAVE");
		for(int i=0;i<9;i++){
			SK.KeyboardStrokes(Key.DOWN);
			//			SK.Write("NA", "q", "w", "Pass");
		}
		SW.Wait(1);
		SK.KeyboardStrokes(Key.ENTER);
		SW.Wait(1);
		SW.WaitForPageload();
		SW.Wait(4);
		SW.WaitTillInvisibilityOfElement("//div[@id='wave5_loading']/img");
		List<WebElement> AllElements = SW.GetAllVisibleElements("//div[@id='numbers']/ul/li");
		String ErrorMessage = AllElements.get(0).getText();
		if(ErrorMessage.startsWith("0")){
			Reporter.Write("Errors_ST", "0 Errors", AllElements.get(0).getText(), "PASS");
		}else{
			Reporter.Write("Errors_ST", "0 Errors", AllElements.get(0).getText(), "FAIL");
		}
		Reporter.Write("Alerts_ST", "0 Alerts", AllElements.get(1).getText(), "PASS");
		Reporter.Write("Features_ST", "0 Features", AllElements.get(2).getText(), "PASS");
		Reporter.Write("Structural Elements_ST", "0 Structural Elements", AllElements.get(3).getText(), "PASS");
		Reporter.Write("HTML5 and ARIA_ST", "0 HTML5 and ARIA", AllElements.get(4).getText(), "PASS");

		String ContrastError = AllElements.get(5).getText();
		if(ContrastError.startsWith("0")){
			Reporter.Write("Contrast Errors_ST", "0 Contrast Errors", ContrastError, "PASS");
		}else{
			Reporter.Write("Contrast Errors_ST", "0 Contrast Errors", ContrastError, "FAIL");
		}

		//		Reporter.Write("Contrast Errors", "0", AllElements.get(5).getText(), "PASS");//TODO


		SW.Click("//a[@id='tab-details']");

		//		SW.RunJavaScript("//div[@id='details']", "arguments[0].scrollIntoView();");
		List<WebElement> Error  = SW.GetAllVisibleElements("//ul[@id='iconlist']/li[@class='icon_group']//ul[@id='group_list_error']//h4");
		if(Error.size()>0){
			for(WebElement W:Error){
				Reporter.Write("ErrorDetails_ST", "", W.getText(), "PASS");
			}
		}
		List<WebElement> Alerts  = SW.GetAllVisibleElements("//ul[@id='iconlist']/li[@class='icon_group']//ul[@id='group_list_alert']//h4");
		if(Alerts.size()>0){
			for(WebElement W:Alerts){
				Reporter.Write("AlertsDetails_ST", "", W.getText(), "PASS");
			}
		}

		List<WebElement> Feature  = SW.GetAllVisibleElements("//ul[@id='iconlist']/li[@class='icon_group']//ul[@id='group_list_feature']//h4");
		if(Feature.size()>0){
			for(WebElement W:Feature){
				Reporter.Write("FeatureDetails_ST", "", W.getText(), "PASS");
			}
		}

		List<WebElement> StructuralElements = SW.GetAllVisibleElements("//ul[@id='iconlist']/li[@class='icon_group']//ul[@id='group_list_structure']//h4");
		if(StructuralElements.size()>0){
			for(WebElement W:StructuralElements){
				Reporter.Write("StructuralElementsDetails_ST", "", W.getText(), "PASS");
			}
		}

		Reporter.StopTest();
		//		SW.GetText("//div[@id='numbers']/ul//li");	

	}
}
