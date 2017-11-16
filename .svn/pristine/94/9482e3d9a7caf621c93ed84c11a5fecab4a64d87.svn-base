package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

public class SMOKE035_IssueAwardToGuest {
	CHANNELS SW = new CHANNELS();
	String SPGNum;
	String AwardID;
	//String SPGNum = SMOKE001_Enrollment.SPGNumberCreated;

	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		AwardID="TRANSFER";
		SPGNum=SW.TestData("SPGNum_LocateGuest");
	}

	@Test
	public void awardIssue() {	  
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into Saratoga

		//Searching Guest BY SPG num and selecting it
		SW.SearchGuestBySPGnumber(SPGNum);
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_GuestNAme_DT"); //Waiting for the GuestName to be clickable
		SW.NormalClick("NavigatorSearchPage_ShowGuest_FT"); //Clicking the guest > mark
		SW.WaitTillElementToBeClickable("NavigatorSearchPage_Award_LK");
		SW.NormalClick("NavigatorSearchPage_Award_LK"); //Clicking on the award link

		String getInitialStarpoints = SW.GetText("NavigatorInternalPointTransferPage_Starpoints_DT"); //Getting initial points in string
		int pointsTransfered = Integer.parseInt(SW.TestData("SPGnum_PointToTransfer")); //Getting the points to be transfered from test data
		int actualInitialPoints = Integer.parseInt(getInitialStarpoints.replace(",", "")); //removing the , from points and converting to integer
		int  actualFinalPoints = (actualInitialPoints - pointsTransfered); //Getting final points after subtracting the points from initial and converting to string 

		SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_AwardType_DD");// Waiting for the drop down Award type
		SW.DropDown_SelectByText("NavigatorSearchPage_AwardType_DD", "Internal Transfers"); //Selecting the award type
		SW.EnterValue("NavigatorAwardPage_AwardId_EB", AwardID);
		SW.NormalClick("NavigatorSearchPage_AwardSearch_BT"); // Clicking on the award search button
		SW.DoubleClick("NavigatorSearchPage_AwardSearch_BT");

		//SW.WaitTillPresenceOfElementLocated("NavigatorSearchPage_InternalPntTransfer_LK");
		SW.NormalClick("NavigatorSearchPage_InternalPntTransfer_LK"); //Clicking on the selected award
		/*SW.EnterValue("NavigatorSearchPage_InternalPntTransfer_LK", "T");
		SW.NormalClick("NavigatorSearchPage_InternalPntTransfer_LK");*/

		SW.EnterValue("NavigatorInternalPointTransferPage_ContactName_EB", "testuser"); //Providing the contact name
		SW.EnterValue("NavigatorInternalPointTransferPage_PointsToTransfer_EB", SW.TestData("SPGnum_PointToTransfer")); //Providing the points to transfer
		SW.EnterValue("NavigatorInternalPointTransferPage_SPGnum_EB", SW.TestData("SPGmemReceivePointTransfer")); //Providing SPG number
		SW.NormalClick("NavigatorInternalPointTransferPage_MemberSearch_BT"); //Click on search
		SW.WaitTillPresenceOfElementLocated("NavigatorInternalPointTransferPage_ConfSPGnum_DT");
		String actualSPGnum = SW.GetText("NavigatorInternalPointTransferPage_ConfSPGnum_DT");
		SW.CompareText("SPG_memberreceipient_comp", SW.TestData("SPGmemReceivePointTransfer"), actualSPGnum);
		SW.CheckBox("NavigatorAwardPage_VerifiyAddress_CB", "ON");
		SW.NormalClick("NavigatorInternalPointTransferPage_Order_BT");

		String awardOrderNum = SW.GetText("NavigatorInternalPointTransferPage_AwardOrder_DT");
		SW.NormalClick("NavigatorInternalPointTransferPage_CloseAwardOrder_BT");
		//SW.WaitTillPresenceOfElementLocated("NavigatorInternalPointTransferPage_Starpoints_DT");

		String getFinalStarpoints = SW.GetText("NavigatorInternalPointTransferPage_Starpoints_DT");
		int expectedStarpoints = Integer.parseInt(getFinalStarpoints.replace(",", ""));

		//Comparing points
		SW.CompareText("CompareTransferPoints", Integer.toString(expectedStarpoints), Integer.toString(actualFinalPoints));
	}

	//Quitting the instance of IE browser
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	} 
}
