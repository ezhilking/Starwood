package tbd;

import functions.DataBaseUtil;
import functions.Environment;
import functions.Reporter;
import functions.Utility;

public class db {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Environment.Tower="CRS";
		Reporter.StartTest();
		DataBaseUtil u = new DataBaseUtil();
		u.EstablishConnection("NDSQA3");
		//select * from freq_travel_mbrshp where alt_mbrshp_num < '111166622'
		System.out.println(u.GetColumnValues("select * from freq_travel_mbrshp where alt_mbrshp_num < '111166622'", 1));
//		u.GetColumnValues("select * from freq_travel_mbrshp LIMIT 1,15", 2);
		
		
		DataBaseUtil db = new DataBaseUtil("oracle", "oracle");

	}

}
