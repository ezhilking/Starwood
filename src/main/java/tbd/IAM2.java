package tbd;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.Environment;
import functions.Reporter;
import functions.SALES;

public class IAM2 {
	SALES SW = new SALES();
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "SALES";
		Reporter.StartTest();
//		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser("https://rws2-fap1364-idm.oracledemos.com/oam/server/obrareq.cgi?encquery%3DS3P80lruyRlAk5S98g3sKrCTxc3KAELG5THTWtNtoCwjSPrLfhTEiBYAVZP0xmKbuJjsdU5EJJvJgc%2FM5YCzycX00U3vj7oBRCX0hxrX2HZplK2iCFdymV82Ipvun7jlaN4maDxiVfj9zGz%2BxHFr%2Fc1FLbkXJpEPG6yZHh4%2FIICiFepfRLvmk3d1yZyu3JELiPNslqJupkbNuDOxc%2Bmi6NCYEMmUVp7LQzePnDirco5k6aCfOFDhnxsI42tNKnhnTMEOGYvbZBEPlWr930PTBQQoUoTnrakpuvbXulAb%2FY4pWL2wdhvEd%2FtS6py41CU2H224vR6Z2X4KntC5kDGPw5UBnpk1KpgK2uvK8Sf0qvBjZrSwCIGXKEFYL%2FSoBWcjguWQr1wxE6PZbSJq%2FjQpgnOE2HsM2bvXwR3izdjCXq7tYP7rD49JqMoz3WWKvda6S08BTRL%2Bf3vy5shAVmPtqsDRQqhwePMyjVf5F3x%2FFmCbSBVjalFeTCS1gjXFAxTf%20agentid%3DOraFusionApp_11AG%20ver%3D1%20crmethod%3D2");
	}

	String LastName,FirstName,Email; 
	@Test(priority=1)
	public void AMS(){
		
		SW.EnterValue("//input[@name='userid']", "arasdsa");
		SW.EnterValue("//input[@name='password']", "pioh");
		SW.EnterValue("AMSLogin_User_EB", "ANDRKON");
		SW.EnterValue("AMSLogin_Password_EB", "ZQ5AWB3B");
		SW.IAMClick("AMSLogin_LogOn_BN");
		
		String ExisitnHire= SW.TestData("ExtendedTitle");
		SW.IAMClick("//*[text()='"+ExisitnHire+"']");
	}
}
