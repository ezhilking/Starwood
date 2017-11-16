package tbd;

import functions.CHANNELS;
import functions.Environment;
import functions.ExcelUtil;
import functions.Reporter;

public class SampleExcel {

	public static void main(String[] args) {
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		CHANNELS SW = new CHANNELS();
		ExcelUtil excelObj = SW.LoadExcel("InputDatasheet.xlsx","Reservation");
		String s = excelObj.GetData(1, 4);
		
	}
}
