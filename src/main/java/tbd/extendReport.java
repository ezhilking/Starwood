package tbd;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import functions.Environment;

public class extendReport {
	static ExtentTest logger;
	private static ExtentReports report;
	public static void main(String[] args) {

		report = new ExtentReports("C:\\Deploy\\ExtentReport.html",NetworkMode.ONLINE);
//		report.loadConfig(new File(Environment.DataPath+"\\Extent.xml"));
		logger = report.startTest("abc");
		
		report.endTest(logger);
		report.flush();
		report.close();
	}
}
