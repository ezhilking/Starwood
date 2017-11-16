package testscripts.Navigator;
/* Purpose		: New Enrollment,searching and updating of Guest Profile 
 * TestCase Name: Guest Enrollment
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 01/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class Navigator_pre_requisite {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
	}
	
	@Test()
	public void getCommunicationType(){
		
		List<String> CommGroups = Arrays.asList("CommTypeGroup1","CommTypeGroup2");
		CHANNELS.CommGroup = SW.GetRandomCommunications(CommGroups);
		Environment.loger.info("Communication group selected is - "+ CHANNELS.CommGroup);
		String getselectedCommGroupValues = SW.TestData(CHANNELS.CommGroup);
		
		List<String> selectedGroupCommList = Arrays.asList(getselectedCommGroupValues.split("\n"));
		CHANNELS.CommGroupItem = SW.GetRandomCommunications(selectedGroupCommList);
		Environment.loger.info("Communication Type selected is - "+ CHANNELS.CommGroupItem);
		SW.WriteToTestData("CommunicationType", CHANNELS.CommGroupItem);
		
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 

}
