package functions;
/*
 * Purpose		: Utility class for Sikuli
 * Author		: Ezhilarasan.S
 * Created Date	: 21-August-2015
 * Modified	on	: 08-September-2015
 */

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class SikuliUtil extends DataBaseUtil{

	static App a = null;
	static Region region = null;
	static Logger Log = Logger.getLogger(SikuliUtil.class.getName());
	static String sikuliImages = Environment.DataPath+"\\SikuliImages\\";
	final static int waitInSeconds = 60;//As of now wait is 60 seconds.
	final static int autowaitInSeconds = 60;//As of now wait is 60 seconds.
	Screen screen = new Screen();
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public boolean SikuliRegionObjectExists(String imageName){
		boolean value = false;
		try{
			region.setAutoWaitTimeout(3);//To avoid to wait till autowaitInSeconds 
			Pattern image = new Pattern(sikuliImages+imageName);
			if(region.find(image) != null){
				region.highlight(1);
				region.setAutoWaitTimeout(autowaitInSeconds);
				value = true;
			}

			//			if(region.exists(image) != null){
			//				value = true;
			//			}
		}catch(FindFailed e){
			region.setAutoWaitTimeout(autowaitInSeconds);
			//logger.info(e.getCause());
			if(e.getCause()==null){
				value = false;
				//				Environment.loger.log(Level.ERROR, imageName+" - Image is not present");
			}else{
				Environment.loger.log(Level.ERROR, "Unsupported for this type of cell");
				throw new RuntimeException("Some other exception occured!!! Instead of FindFailed exception.");
			}
		}catch(Exception e){
			region.setAutoWaitTimeout(autowaitInSeconds);
			throw e;
		}
		return value;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public boolean SikuliScreenObjectExists(String imageName){
		boolean value = false;
		try{
			screen.setAutoWaitTimeout(3);
			Pattern image = new Pattern(sikuliImages+imageName);
			if(screen.find(image) != null){
				screen.setAutoWaitTimeout(autowaitInSeconds);
				value = true;
			}
		}catch(FindFailed e){
			screen.setAutoWaitTimeout(autowaitInSeconds);
			//logger.info(e.getCause());
			if(e.getCause()==null){
				value = false;
				//				Environment.loger.log(Level.ERROR, imageName+" - Image is not present");
			}else{
				Environment.loger.log(Level.ERROR, "Unsupported for this type of cell");
				throw new RuntimeException("Some other exception occured!!! Instead of FindFailed exception.");			}
		}catch(Exception e){
			screen.setAutoWaitTimeout(autowaitInSeconds);
			throw e;
		}
		return value;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	private Match RegionFind(String imageName){
		try{
			Pattern image = new Pattern(sikuliImages+imageName+".png");
			return region.find(image);
		}catch(FindFailed e){
			Environment.loger.log(Level.ERROR, "'"+imageName+"'Image not found in the UI");
		}catch(Exception e){
			throw e;
		}
		return null;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	private Match ScreenFind(String imageName){
		try{	
			//			Screen screen = new Screen();
			screen.setAutoWaitTimeout(waitInSeconds);
			Pattern image = new Pattern(sikuliImages+imageName+".png");
			return screen.find(image);
		}catch(FindFailed e){
			Environment.loger.log(Level.ERROR, "'"+imageName+"'Image not found in the UI");
		}catch(Exception e){
			throw e;
		}
		return null;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void SikuliScreenType(String imageName, String Text){
		try{
			Pattern image = new Pattern(sikuliImages+imageName+".png");
			screen.type(image,Text);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void SikuliScreenType(String Text){
		try{
			screen.type(Text);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void SikuliScreenClick(String imageName){
		try{
			Pattern image = new Pattern(sikuliImages+imageName+".png");
			screen.click(image);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void SikuliFocusRegionWindow(){
		try{
			Wait(3);
			region = App.focusedWindow();//Identify the currently focused or the frontmost window and switch to it. Sikuli does not tell you, to which application this window belongs.
			region.highlight(1);
			region.setAutoWaitTimeout(autowaitInSeconds);
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public String SikuliCopyToClipboard(){
		String text="";
		try{
			Wait(3);
			region.type("c", Key.CTRL);//TODO
			Wait(1);
			text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			//			text = App.getClipboard().trim();
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return text;
	}

	public boolean SikuliCompareText(String StepName,String Expected, String Actual){
		boolean Return = false;
		if(CompareText(Expected, Actual)){
			Return = true;
			Write(StepName, Expected, Actual, "PASS");
			//			SikuliSaveScreenshot(StepName);
			//			Environment.loger.log(Level.INFO, StepName+": Pass:"+Expected+">"+Actual);//TODO Replace with results.
		}else{
			Write(StepName, Expected, Actual, "FAIL");
			//			Environment.loger.log(Level.ERROR, StepName+": Fail:"+Expected+">"+Actual);//TODO Replace with results.
			//			SikuliSaveScreenshot(StepName);
		}
		return Return;
	}

	public boolean SikluliCompareTextContained(String StepName,String Expected, String Actual){
		boolean Return = false;
		if(CompareTextContained(Expected, Actual)){
			Return = true;
			Write(StepName, Expected, Actual, "PASS");
			//			SikuliSaveScreenshot(StepName);
			//			Environment.loger.log(Level.INFO, "PASS: "+Expected+">"+Actual);
		}else{
			Write(StepName, Expected, Actual, "FAIL");
			//			Environment.loger.log(Level.ERROR, "FAIL: "+Expected+">"+Actual);
			//			SikuliSaveScreenshot(StepName);
			//			throw new RuntimeException("FAIL");
		}
		return Return;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void SikuliRegionWaitForObject(String imageName){
		try{
			Match element = RegionFind(imageName);
			element.highlight(2);
			//			while(RegionObjectExists(imageName) != true){//Wait till the element is not null.
			//				logger.info();
			//				Environment.loger.log(Level.INFO, "Waiting for expected image to appear!!!!!");
			//			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	public void SikuliRegionType(String Text){
		try{
			region.type(Text);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	public void SikuliRegionType(String imageName, String Text){
		try{
			Pattern image = new Pattern(sikuliImages+imageName+".png");
			region.type(image,Text);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void SikuliScreenWaitForObject(String imageName){
		try{
			Match element = ScreenFind(imageName);
			element.highlight(2);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public boolean SikuliClick(String imageName){
		try{
			//			if(RegionObjectExists(imageName)){
			Match element = RegionFind(imageName);
			element.highlight(1);
			element.hover();
			element.click();
			return true;
			//			}else{
			//				Environment.loger.log(Level.ERROR, imageName+" is not exists");
			//				return false;
			//			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
			return false;
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public boolean SikuliDoubleClick(String imageName){
		try{
			if(SikuliRegionObjectExists(imageName)){
				Match element  = RegionFind(imageName);
				element.highlight(1);
				element.hover();
				element.doubleClick();
				return true;
			}else{
				Environment.loger.log(Level.INFO, imageName+" is not exists");
				return false;
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
			return false;
		}
	}

	//	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	//	public static void FocusScreenWindow(){
	//		try{
	//			Thread.sleep(1000);
	//			r = App.focusedWindow();
	//			r.highlight(2);
	//		}catch (Exception e) {
	//			Environment.loger.log(Level.ERROR, "Exception occured",e);
	//		}
	//	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public String GetFileName(String imageName){
		return imageName.substring(imageName.lastIndexOf("/")+1, imageName.length());
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public static boolean CompareDate(String actual, String expected){
		boolean returnValue= false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			Date expectedDate = sdf.parse(expected);
			Date actualDate = sdf.parse(actual);
			returnValue = expectedDate.equals(actualDate);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return returnValue;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public String SikuliSaveScreenshot(String FileName){
		try{
			String fileName =FileName+GetTimeStamp("hhmmssSSS");
			fileName = region.saveScreenCapture(Reporter.ScreenshotPath,fileName);
			Environment.loger.log(Level.INFO, "Screenshot path - "+fileName);
			return new File(fileName).getName();
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return null;
	}

	private String AppendScreenshot(String StepName){
		return Reporter.logger.addScreenCapture("Screenshot/"+SikuliSaveScreenshot(StepName));
	}

	public void Write(String StepName,String Expected,String Actual,String Status) {
		//		String DebugMessage = (new StringBuffer()).append(Status).append(">").append(StepName).append(">").append(Expected).append(">").append(Actual).toString();
		if(Status.equalsIgnoreCase("FAIL")){
			Reporter.Write(StepName, Expected, Actual, "FAIL",AppendScreenshot(StepName));
			//			Reporter.logger.log(LogStatus.FAIL, "<b><font color='blue'>Expected:</font></b>"+Expected+"<b><font color='blue'>Actual:</font></b>"+Actual,AppendScreenshot(StepName));
			//			Reporter.WriteLog(Level.ERROR, DebugMessage);s
		}else if(Status.equalsIgnoreCase("PASS")){
			Reporter.Write(StepName, Expected, Actual, "PASS",AppendScreenshot(StepName));
			//			Reporter.logger.log(LogStatus.PASS, "<b><font color='blue'>Expected:</font></b>"+Expected+"<b><font color='blue'>Actual:</font></b>"+Actual,AppendScreenshot(StepName));
			//			Reporter.WriteLog(Level.DEBUG,DebugMessage);
		}else{
			//TODO
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public String SikuliGetText(String imagePath){
		try{
			//			Robot robot = new Robot();
			Click(imagePath);
			return SikuliCopyToClipboard();
			/*	String image = "CommentsSection.PNG";
				RegionFind(image).hover();
				RegionFind(image).click();*/

			//			
			//			int x = region.getLastMatch().getX() + 10;
			//			int y = region.getLastMatch().getY() + 10;
			//			//			robot.mousePress("");
			//			robot.keyPress(InputEvent.BUTTON1_MASK);

			//			region.type("a", Key.CTRL);
			//			region.type("c", Key.CTRL);

			//			expected  = region.find(image).right(100).text();

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return null;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void KeyboardStrokes(String keyStrokes){
		try{	
			//			Screen screen = new Screen();
			List<String> keyStroke= Arrays.asList(keyStrokes.split("\\|"));
			if(keyStroke.size()==2){
				String variableName = keyStroke.get(0).toUpperCase();// + letter.toUpperCase();
				Class<KeyModifier> clazz = KeyModifier.class;
				Field field = clazz.getField(variableName);
				//	     	int keyCode = field.getInt(null);
				//	        String keycode = field.toString();
				int keyCode = field.getInt(null);
				screen.type(keyStroke.get(1), keyCode);//KeyModifier only accept the integer as as parameters,nor Key wont accept it.
			}else if(keyStroke.size()==1){
				screen.type(keyStroke.get(0));
			}else{
				Environment.loger.log(Level.ERROR, "Invalid parameter(s)");
			}
			//	            screen.type("d".toString(),keycode.substring(keycode.indexOf("org"), keycode.length()));
			//				screen.type("d",Key.WIN);
			//	            robot.keyPress( keycode );
			//	            robot.keyPress(KeyEvent.VK_D);
			//	           
			//	            robot.keyRelease( keyCode );
			//				robot.keyPress(KeyEvent.VK_H);
			//				String c[]= keyStrokes.split(",");
			//				String az= "WIN";
			//				if(a.size()==2){
			//				r.type(c[1].toString(),Key.az);
			//				r.type(c[1].toString(),org.sikuli.api.robot.Key.);
			//				screen.type(c[1].toString(),c[0].toString());
			//				screen.type(a.get(1),a.get(0));
			//				}else if(a.size()==1){
			//				screen.type(a.get(0));
			//				}else{
			//				Environment.loger.log(Level.INFO, "Invalid paramaeter");
			//				}
		}catch(NoSuchFieldException e){
			Environment.loger.log(Level.ERROR, "Invalid parameter!!!Please pass the exact windows keywords i.e.,To press,Windows->WIN,Alt->alt etc..,");
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	public boolean OperaPMSLogin(String Username, String Password,String ReservationNumber){
		boolean IsPass = false;
		try{
			EnterValue("PMSLogin_Username_EB", Username);
			EnterValue("PMSLogin_Password_EB", Password);
			Click("PMSLogin_Submit_BN");
			WaitTillElementToBeClickable("PMSHomePage_PMS_LK");
			RunJavaScript("launch_app('PMS', 'opera_pms', 'operaoperads', false);");
			SikuliScreenWaitForObject("Opera_LoginWait_IM");
			SikuliFocusRegionWindow();//Initializing region object
			KeyboardStrokes("alt|l");
			if(SikuliRegionObjectExists("General_OK_BN"))//Handle Password expired page
				KeyboardStrokes("alt|o");
			SikuliRegionWaitForObject("OperaHomePage_Exit_BN");

			if(SikuliRegionObjectExists("OperaHomePage_Exit_BN")){
				Environment.loger.log(Level.INFO, "Logged successfully into PMS. Locating reservation number:"+ReservationNumber);
				SikuliSaveScreenshot("LoginSuccess");
				KeyboardStrokes("alt|r");
				Wait(2);
				KeyboardStrokes("u");
				Wait(3);
				SikuliFocusRegionWindow();
				for(int i=0;i<=4;i++){
					Wait(1);
					SikuliRegionType(Key.TAB);
				}
				SikuliRegionType(ReservationNumber);
				Wait(2);
				SikuliRegionType(Key.ENTER);

				if(!SikuliRegionObjectExists("Opera_UpdateReservationNotFound_PU")){
					SikuliSaveScreenshot("Located Reservation Number");
					IsPass = true;
				}else{
					SikuliSaveScreenshot("Login Failed");
					//			SikuliRegionObjectExists("OK_BN.PNG");
					KeyboardStrokes("alt|o");//Close the popup
					IsPass = false;
					Environment.loger.log(Level.INFO, "Reservation not found Popup displayed!");
					FailCurrentTest("Reservastion not found:");
				}
			}else{
				Environment.loger.log(Level.ERROR, "Not successfully Logged into PMS");
				SikuliSaveScreenshot("Login Failed");
				IsPass = false;
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return IsPass;
	}
	public boolean GalaxyPMSLogin(String ConfNumber){
		WaitForWindowCount(3);
		//		if(GetCurrentWindowsCount()==3){
		SwitchToWindow(3);
		if(GetTitle().startsWith("Terminal List")){
			ClickByJavascript("GalaxyPMS_GalaxyCustomerSupport_LK");
			ClickByJavascript("GalaxyPMS_OK_BN");
		}
		SwitchToWindow(2);
		if(GetTitle().startsWith("LSConfig IE 11"))
			CloseOnlyThisBrowser();
		SwitchToWindow(1);
		EnterValue("GalaxyPMS_AssociateID_EB", "IPSPSI");
		EnterValue("GalaxyPMS_Password_EB", "IPSpsi123*");
		Click("GalaxyPMS_SignIn_BN");
		if(ObjectExists("GalaxyPMS_SignIn_BN"))
			Click("GalaxyPMS_SignIn_BN");
		//		WaitTillInvisibilityOfElement("GalaxyPMS_Wait_IC");
		if(ObjectExists("GalaxyPMS_MainFrameSet_FR")){
			SwitchToFrame("GalaxyPMS_Main_FR");
			Environment.loger.log(Level.INFO, "Logged into Galaxy successfully!");
			ClickByJavascript("GalaxyPMS_OpenReservation_BN");
			Wait(10);
			SikuliFocusRegionWindow();//Initiating the Region object for Sikuli
			for(int i=0;i<=5;i++){
				Wait(1);
				SikuliRegionType(Key.TAB);
			}
			SikuliRegionType(ConfNumber);
			Wait(3);
			KeyboardStrokes("alt|s");
			Wait(3);
			KeyboardStrokes("alt|enter");//to open that reservation
			return true;
		}else{
			return false;
		}
	}

	//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Opera PMS reusable method starts from here*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

	/*//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	@Deprecated
	public void compareDatePMS(String passvalue){//String confirmationNumber,String expectedFromDate, String expectedToDate
		try{
			List<String> str = Arrays.asList(passvalue.split(","));
			String confirmationNumber = str.get(0);
			String expectedFromDate= str.get(1);
			String expectedToDate= str.get(2);
			Thread.sleep(3000);
			KeyboardStrokes("alt|r");
			//			r.type("r",Key.ALT);
			Wait(2);
			KeyboardStrokes("u");
			Wait(3);
			//			r.type("u");
			for(int i=0;i<=4;i++){
				Wait(3);
				region.type(Key.TAB);
				//				r.type(Key.TAB);//Move cursor to Conf/Cxl No Edit box
			}
			region.type(confirmationNumber);
			SikuliSaveScreenshot();
			Wait(2);
			region.type(Key.ENTER);
			Wait(8);
			if(!SikuliRegionObjectExists("ReservationNotFound.PNG")){
				Environment.loger.log(Level.INFO, "Reservation details displayed !!!!");
				region.type(Key.ENTER);//To pop out the reservation details
				Wait(30);
				String actualFromDate = SikuliCopyToClipboard();
				region.type(Key.TAB);
				Wait(2);
				region.type(Key.TAB);
				Wait(2);
				String actualToDate = SikuliCopyToClipboard();
				SikuliSaveScreenshot();
				//Comparing From date 
				if(CompareDate(expectedFromDate,actualFromDate)){
					Environment.loger.log(Level.INFO,"Equal"+actualFromDate+" <--> "+expectedFromDate);//TODO
				}else{
					Environment.loger.log(Level.INFO,"NOT equal"+actualFromDate+" <--> "+expectedFromDate);//TODO
				}

				//Comparing to date
				if(CompareDate(expectedToDate ,actualToDate)){
					Environment.loger.log(Level.INFO,"Equal"+actualToDate+" <--> "+expectedToDate);//TODO
				}else{
					Environment.loger.log(Level.INFO,"NOT equal"+actualToDate+" <--> "+expectedToDate);//TODO
				}

				//Click close button
				Click("ReservationClose_BN.PNG");
				Wait(5);
				Click("ReservationClose_BN.PNG");
			}else{
				SikuliSaveScreenshot();
				Environment.loger.log(Level.ERROR, "Reservation not found Popup displayed!! So ignoring it!!");
				Click("OK_BN.PNG");
			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	@Deprecated
	public void VerifyConfirmationNumber(String confirmationNumber){
		try{
			//Instead clicking an image made use of windows shortcuts.
			Wait(3);
			KeyboardStrokes("alt|r");
			//			r.type("r",Key.ALT);
			Wait(2);
			KeyboardStrokes("u");
			Wait(3);
			//			r.type("u");
			for(int i=0;i<=4;i++){
				Wait(1);
				region.type(Key.TAB);
				//				r.type(Key.TAB);//Move cursor to Conf/Cxl No Edit box
			}
			region.type(confirmationNumber);
			Wait(2);
			region.type(Key.ENTER);
			Wait(5);
			if(!SikuliRegionObjectExists("ReservationNotFound.PNG")){
				SikuliSaveScreenshot();
				Environment.loger.log(Level.INFO, "Reservatoin details displayed !!!!");
			}else{
				SikuliSaveScreenshot();
				Environment.loger.log(Level.ERROR, "Reservation not found Popup displayed!! So ignoring it!!");
				SikuliRegionObjectExists("OK_BN.PNG");
				Click("OK_BN.PNG");
			}
			Wait(5);
			Click("Traces_BN.PNG");

			for(int i=0;i<=5;i++){
				Wait(3);
				region.type(Key.TAB);
			}
			for(int i=0;i<=6;i++){
				Wait(3);
				region.type(Key.DOWN);	
			}
			Wait(3);
			String s= GetText("CommentsSection.PNG");
			Environment.loger.log(Level.ERROR, s);



			//Close the Reservation popup
			//			Click("ReservationClose_BN.PNG");
			//			//Reservation Popup 
			//			if(DoubleClick(path+"UpdateReservation_BN.PNG")){
			//				if(ObjectExists(path+"UpdateReservation_PopUp.PNG")){
			//					logger.info("Reservation plan displayed");
			//				}else{
			//					logger.info("Reservation plan not displayed");
			//				}
			//			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	@Deprecated
	public void Reinstate(String confirmationNumber){
		//Instead clicking an image made use of windows shortcuts.
		Wait(3);
		KeyboardStrokes("alt|r");
		//			r.type("r",Key.ALT);
		Wait(2);
		KeyboardStrokes("u");
		Wait(3);
		//			r.type("u");
		for(int i=0;i<=4;i++){
			Wait(3);
			region.type(Key.TAB);
			//				r.type(Key.TAB);//Move cursor to Conf/Cxl No Edit box
		}
		region.type(confirmationNumber);
		Wait(2);
		region.type(Key.ENTER);
		Wait(5);
		if(!SikuliRegionObjectExists("ReservationNotFound.PNG")){
			SikuliSaveScreenshot();
			Environment.loger.log(Level.INFO, "Reservatoin details displayed !!!!");
			if(SikuliRegionObjectExists("")){//TODO
				Environment.loger.log(Level.INFO, "Reinstate button is displayed");
			}else{
				Environment.loger.log(Level.ERROR, "Reinstate button is not displayed");
			}
		}else{
			SikuliSaveScreenshot();
			Environment.loger.log(Level.ERROR, "Reservation not found Popup displayed!!");
			Click("OK_BN.PNG");
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*



	public String GetCommentsForReservationNumber(String reservartionNumber){

		try{
			//Instead clicking an image made use of windows shortcuts.
			Wait(3);
			KeyboardStrokes("alt|r");
			//			r.type("r",Key.ALT);
			Wait(2);
			KeyboardStrokes("u");
			Wait(3);
			//			r.type("u");
			for(int i=0;i<=4;i++){
				Wait(1);
				region.type(Key.TAB);
				//				r.type(Key.TAB);//Move cursor to Conf/Cxl No Edit box
			}
			region.type(reservartionNumber);
			//			Wait(2);
			region.type(Key.ENTER);
			Wait(2);
			if(!SikuliRegionObjectExists("ReservationNotFound.PNG")){
				SikuliSaveScreenshot();
				Environment.loger.log(Level.INFO, "Reservatoin details displayed !!!!");
			}else{
				SikuliSaveScreenshot();
				Environment.loger.log(Level.ERROR, "Reservation not found Popup displayed!! So ignoring it!!");
				SikuliRegionObjectExists("OK_BN.PNG");
				Click("OK_BN.PNG");
			}
			Wait(2);
			Click("Traces_BN.PNG");

			for(int i=0;i<=5;i++){
				Wait(3);
				region.type(Key.TAB);
			}
			for(int i=0;i<=6;i++){
				Wait(3);
				region.type(Key.DOWN);	
			}
			Wait(3);
			String s = GetText("CommentsSection.PNG");
			Environment.loger.log(Level.INFO, s);
			return s;
			//Close the Reservation popup
			//			Click("ReservationClose_BN.PNG");
			//			//Reservation Popup 
			//			if(DoubleClick(path+"UpdateReservation_BN.PNG")){
			//				if(ObjectExists(path+"UpdateReservation_PopUp.PNG")){
			//					logger.info("Reservation plan displayed");
			//				}else{
			//					logger.info("Reservation plan not displayed");
			//				}
			//			}
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
		return null;

	}*/
}




