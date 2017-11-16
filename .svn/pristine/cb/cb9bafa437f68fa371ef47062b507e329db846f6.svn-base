package functions;
/* Purpose		:To Log a message.
 * Developed By	:Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log{

	public static Logger logger;
	static String Logfilepath;

	public static void OpenLOG(){
		logger = Logger.getLogger(Log.class.getName());
		if(Logfilepath == null){
			Logfilepath = Reporter.ResultFolder+"\\"+Reporter.GetTimeStamp("ddMMMyyyy-hhmmssa")+"_"+System.getProperty("user.name").toLowerCase() +".log";
		}

		FileAppender FA = null;
		try {
			FA = new FileAppender(new PatternLayout("%d{dd-MMM-yyyy hh:mm:ss:SSS a} %-10C{1} %-20M %-4L %-5p: %m%n"),Logfilepath, true);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		ConsoleAppender CA = new ConsoleAppender(new PatternLayout("%d{ISO8601} %-20C{1} %-20M %-4L %-5p: %m%n"), "System.out");

		FileAppender HL = null;
		Logfilepath = Reporter.ResultFolder+"\\"+Reporter.GetTimeStamp("ddMMMyyyy-hhmmssa")+"_"+System.getProperty("user.name").toLowerCase() +".html";
		try {
			HTMLLayout HTL = new HTMLLayout();
			HTL.setTitle("Automation Report");
			HTL.setLocationInfo(true);
			HL = new  FileAppender(HTL, Logfilepath);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		logger.setAdditivity(false);
		logger.setLevel(Level.ALL);
		logger.addAppender(FA);
		logger.addAppender(CA);
		logger.addAppender(HL);
		Environment.loger = logger; 
		logger = null;
	}

	public static void CloseLog(){//TODO

	}
}