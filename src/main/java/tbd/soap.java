package tbd;

import functions.Environment;
import functions.SoapUtility;

public class soap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Environment.Tower="CHANNELS";
		SoapUtility s = new SoapUtility();
		s.GetValueFromExcel("CCC_Reservations8");
	}

}
