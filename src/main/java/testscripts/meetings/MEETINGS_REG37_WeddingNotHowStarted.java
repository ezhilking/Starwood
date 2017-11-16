/* Purpose		: //TODO
 * TestCase Name: Wedding how all it started not present - booking landing page for english & japanese language
 * Created By	: Brij
 * Modified By	: 	
 * Modified Date: 
 * Reviewed By	: 
 * Reviewed Date: 
 */


package testscripts.meetings;

import org.apache.commons.lang3.text.WordUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MEETINGS_REG37_WeddingNotHowStarted {

	CHANNELS SW = new CHANNELS(); 

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
	}

	@Test(priority=1)	
	public void WeddingNotStartedDescriptionEnglish(){

		String url = SW.TestData("URLWedding");
		SW.LaunchBrowser(url);

		if(!(SW.ObjectExists("MeetingsBookingLandingPage_AddInfo_ST"))){
			Reporter.Write("HowStartedWeddingEnglish_DD", "Not Presernt: As Expected", "Not Presernt: As Expected", "PASS");
		}
		else
			Reporter.Write("HowStartedWeddingEnglish_DD", "Presernt: Not as Expected", "Presernt: Not as Expected", "FAIL");
	}
	
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Test(priority=2)

	public void WeddingNotStartedDescriptionJapanese(){

		SW.MoveToObject("MeetingsBooking_ChangeLanguage_BT");		
		SW.Click("MeetingsBooking_ChangeLanguage_BT1");

		if(!(SW.ObjectExists("MeetingsBookingLandingPage_AddInfo_ST"))){
			Reporter.Write("HowStartedWeddingJapanese_DD", "Not Presernt: As Expected", "Not Presernt: As Expected", "PASS");
		}
		else
			Reporter.Write("HowStartedWeddingJapanese_DD", "Presernt: Not as Expected", "Presernt: Not as Expected", "FAIL");
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}
}


