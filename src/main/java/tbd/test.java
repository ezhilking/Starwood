package tbd;

import functions.CRM;
import functions.Environment;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Environment.Tower="CRM";
		CRM SW= new CRM();
		SW.EstablishConnection("QA3");

	}

}
