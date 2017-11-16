package testscripts.Navigator;
/* Purpose		: Searching Guest Profile 
 * TestCase Name: Search Guest By SPG Number
 * Created By	: sagar
 * Modified By	: 
 * Modified Date: 02/09/2016
 * Reviewed By	:	
 * Reviewed Date:
 */


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE039_SecurityVerifyByDMAndAMA {
	CHANNELS SW = new CHANNELS();

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
	}

	@Test
	public void SearchGuestSecurityVerify() {		
		SW.NavigatorLogin(SW.TestData("DM_username"),SW.TestData("DM_password"));//Login into the application
		SW.SearchGuestBySPGnumber(SW.TestData("SPG_Num_SecurityVerify")); //Searching the Guest by SPG number
		SW.NormalClick("NavigatorSearchPage_SecurityStatus_BT"); //CLicking the Verify Account

		for(int cnt=0;cnt<3;cnt++){
			String question = SW.GetText("NavigatorSearchPage_SecurityQuesMessage_DT");
			switch(question){

			case "Can you tell me the last 4 digits of a phone number on your SPG Profile?":
				SW.NormalClick("NavigatorSearchPage_SecurityAnswer_EB");
				SW.EnterValue("NavigatorSearchPage_SecurityAnswer_EB", SW.TestData("SPGmember_PhoneNum").substring(6));
				SW.Click("NavigatorSearchPage_SecurityAnswerSubmit_BT");
				if(SW.GetText("NavigatorSearchPage_NextQues_BT").equalsIgnoreCase("Next")){
					SW.NormalClick("NavigatorSearchPage_NextQues_BT");
				}				
				break;

			case "Can you tell me the primary email address on your SPG Profile?" :
				String emailXpath="//span[contains(text(),'" + SW.TestData("SPGmember_EmailAddress").toLowerCase() + "')]";
				SW.NormalClick(emailXpath);
				SW.WaitTillElementToBeClickable("NavigatorSearchPage_NextQues_BT");
				if(SW.GetText("NavigatorSearchPage_NextQues_BT").equalsIgnoreCase("Next")){
					SW.NormalClick("NavigatorSearchPage_NextQues_BT");
				}
				break;

			case "Do you happen to know your current SPG membership level?" :
				SW.Click("NavigatorSearchPage_SPGlevelChoose_LK");
				if(SW.GetText("NavigatorSearchPage_NextQues_BT").equalsIgnoreCase("Next")){
					SW.NormalClick("NavigatorSearchPage_NextQues_BT");
				}
				break;

			case "Do you remember what year you enrolled in the SPG program?" :
				SW.NormalClick("NavigatorSearchPage_SPGCreationYear_LK");
				if(SW.GetText("NavigatorSearchPage_NextQues_BT").equalsIgnoreCase("Next")){
					SW.NormalClick("NavigatorSearchPage_NextQues_BT");
				}

			default:
				Environment.loger.info("Other questions appeared - ");				
			}			
		}

		String SecurityVerifyStatus = SW.GetText("NavigatorSearchPage_SecurityStatus_BT");
		SW.CompareText("SecurityStatus", "Security Verified", SecurityVerifyStatus);


	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
