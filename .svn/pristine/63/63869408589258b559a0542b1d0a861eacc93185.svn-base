/* Purpose		: verifying updated email content in the customize pop up for spg user 
 * TestCase Name: TC1_ Verify Updated Master Bill mail content in the Customize Email message For SPG user _ResCon
 * Created By	: shalini.jaikumar
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */
package testscripts.resCon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class MUSTRUN27_MBUpdatedContentCustomizePopUpNONSPG{

	CHANNELS SW = new CHANNELS();
	String Number;
	String cnfcNumber;
	String lastName = SW.RandomString(5);

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		Environment.SetBrowserToUse("FF");
		SW.LaunchBrowser(Environment.RESCON);
	}

	@Test(priority=1)
	public void CreateMasterbillNonSPGinvite(){
		SW.ResConLogin(SW.TestData("SGP_UserName"), SW.TestData("SGP_Password"));
		SW.MoveToObject("ResconHomepage_Masterbill_BT");
		SW.Click("ResconHomepage_MBNewinvite_BT");
		SW.DropDown_SelectByText("ResconMB_Select_DD","Non-SPG/Non-SPP");
		SW.Click("ResconMB_Selectnext_BT");
		SW.Click("ResconMB_CustomizeLinkSPG_LK");
		SW.SwitchToWindow(2);
		String CustomizecontentSPG = SW.GetText("ResconMB_CustomizePopUpContentSPG_ST");
		String Expected = "Dear Planner,\n\nThank-you for choosing Starwood Hotels & Resorts to host your recent event. Starwood`s online eBill service allows you on-line access to your master bill 24 hours a day, seven days a week. This service is simple & efficient. With added features like an Excel version of your master account and the ability to view recent master bills from multiple Starwood hotels, you can save time and effort in reconciling your group`s charges.\n\nAre you ready to get started? All you need to do is three simple steps.\n\nTo access the secure website:\nClick on the URL below for the past event: {EventName}.\n\nhttps://qa3.starwoodmeeting.nssd.star/ResConWeb/ebill\n\nYou will see a link on this web page to access your Online Master Bill.\n\nYour login is the email address to which you have received this email ({NonSPGEmail})\n{NonSPGPwd}\n\nSelect the eBill you would like to view and open all related attachments.\n\nYou will be able to view, download or print your eBill until {ValidUntilDate} (GMT).\nPlease see your master bill document for payment terms and payment due date.\n\nIf you have any questions or would like to request an original copy of your master bill be sent to you, please contact your Starwood billing representative.\n\nNote: You may receive this e-mail again if there are new or revised attachments.\n\n** Please do not reply to this email **";
		SW.CompareText("ResconMB_CustomizePopUpContentSPG_ST", Expected, CustomizecontentSPG);
	}

	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}


}
