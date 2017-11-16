package functions;
/* Purpose		:Reporter related util class.
 * Developed By	:Ezhilarasan.S
 * Modified By	:Ezhilarasan.S
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;



public class Reporter {
	public static String ResultFolder;
	public static String ScreenshotPath;
	static ExtentTest logger;
	private static ExtentReports report;

	public static void StartTest(){
		if(Environment.loger == null){
			SetUpFolder();
			Log.OpenLOG();
			String exeutionEnvironment = System.getProperty("exeutionEnvironment");
			if(exeutionEnvironment!=null){
				Environment.setRunEnvironment(exeutionEnvironment.toUpperCase());
				Environment.IsRunningFromMaven = true;
			}
			Environment.LoadURL();
			Environment.LoadSheetName();
			ExcelUtil.LoadExcel();
			report = new ExtentReports(Reporter.ResultFolder+"\\ExtentReport.html",NetworkMode.ONLINE);
			report.loadConfig(new File(Environment.DataPath+"\\Extent.xml"));
		}else{
			report = new ExtentReports(Reporter.ResultFolder+"\\ExtentReport.html",false,NetworkMode.ONLINE);
		}
		logger = report.startTest(GetCurrentTestName());
		Environment.loger.log(Level.INFO, "* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * -  * - * - * - * - * - * - * - * - * - ");
		Environment.loger.log(Level.INFO, "Execution Started for '"+GetCurrentTestName()+"' - "+ResultFolder);
	}

	public static void StopTest(){
		report.endTest(logger);
		report.flush();
		report.close();
		ExcelUtil.ORLocator.CloseSheet();
		ExcelUtil.TestData.CloseSheet();
		Utility.CloseBrowser();
		Environment.SetBrowserToUse("IE");//Set global browser as Internet Explorer, even for multiple execution(XML Execution).
		Environment.loger.log(Level.INFO, "Execution Ended for '"+GetCurrentTestName()+"' - "+ResultFolder);
		String TimeStampAppender = GetTimeStamp("ddMMMyyyy-hhmmssa");
		File EmailableReport = new File(Environment.ResultPath+"\\emailable-report.html");//Renaming the Emailable report.
		if(EmailableReport.exists()){//To rename the emailable report file.
			try{
				FileUtils.moveFile(EmailableReport, new File(Environment.ResultPath+"\\"+TimeStampAppender+"emailable-report.html"));
			}catch(Exception e){
				Environment.loger.log(Level.ERROR, "Exception occured",e);
			}
		}
		File SurefireReport = new File(Environment.ResultPath+"\\surefire-reports");//
		if(SurefireReport.exists()){
			try{
				FileUtils.moveDirectory(SurefireReport, new File(Environment.ResultPath+"\\"+TimeStampAppender+"surefire-reports"));
			}catch(Exception e){
				Environment.loger.log(Level.ERROR, "Exception occured",e);
			}
		}
	}

	private static void SetUpFolder(){
		File CreateFolderPath;
		boolean success = false;
		try{
			if(ResultFolder == null){
				ResultFolder = Environment.ResultPath+"\\Results\\"+GetTimeStamp("ddMMMyyyy-hhmmssa");
				ScreenshotPath = ResultFolder+"\\Screenshot\\";
				CreateFolderPath = new File(ScreenshotPath);
				success = CreateFolderPath.mkdirs();
				if(success){
					CreateFolderPath = new File(ResultFolder +"\\Data");
					CreateFolderPath.mkdir();
				}
			}	
		}catch(Exception e){
			Environment.loger.log(Level.INFO, "Unable to create directory",e);
		}finally{
			CreateFolderPath = null;
		}
	}

	public static void Write(String StepName,String Expected,String Actual,String Status){
		if(Status.equalsIgnoreCase("FAIL")){
			Write(StepName, Expected, Actual, "FAIL",AppendScreenshot(StepName));
		}else if(Status.equalsIgnoreCase("PASS")){
			Write(StepName, Expected, Actual, "PASS",AppendScreenshot(StepName));
		}else{
			//TODO
		}
	}
	public static void Write(String StepName,String Expected,String Actual,String Status,boolean TakeScreenshot){
		if(TakeScreenshot){
			Write(StepName, Expected, Actual, Status);
		}else{
			Write(StepName, Expected, Actual, Status, "NA");
		}
	}
	static void Write(String StepName,String Expected,String Actual,String Status,String ScreenshotName){
		String DebugMessage = (new StringBuffer()).append(Status).append(">").append(StepName).append(">").append(Expected).append(">").append(Actual).toString();
		if(Status.equalsIgnoreCase("FAIL")){
			logger.log(LogStatus.FAIL, "<br>"+StepName+"</br><b><font color='blue'>Expected:</font></b>"+Expected+"<br><b><font color='blue'>Actual:</font></b></br>"+Actual+"</br>",ScreenshotName);
			WriteLog(Level.ERROR, DebugMessage);
		}else if(Status.equalsIgnoreCase("PASS")){
			logger.log(LogStatus.PASS, "<br>"+StepName+"</br><b><font color='blue'>Expected:</font></b>"+Expected+"<br><b><font color='blue'>Actual:</font></b>"+Actual+"</br>",ScreenshotName);
			WriteLog(Level.DEBUG,DebugMessage);
		}else{
			//TODO
		}
	}

	private static String AppendScreenshot(String StepName){
		return logger.addScreenCapture("Screenshot/"+Utility.GetScreenshot(StepName));
	}

	public static void WriteLog(Level LogLevel,String Message){
		Environment.loger.log(LogLevel, Message);
	}

	public static void WriteLog(Level LogLevel,String Message,Throwable ForStacktrace){
		Environment.loger.log(Level.ERROR, Message,ForStacktrace);
	}

	private static String GetCurrentTestName(){
		StackTraceElement[] TestList = Thread.currentThread().getStackTrace();
		String CurrentTestName = "";
		CurrentTestName = TestList[3].getFileName();
		TestList = null;
		return CurrentTestName.replace(".java", "").trim();
	}

	static String GetTimeStamp(String DateFormat) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
		String formattedDate = sdf.format(date);
		date = null;
		sdf = null;
		return formattedDate;
	}


}


