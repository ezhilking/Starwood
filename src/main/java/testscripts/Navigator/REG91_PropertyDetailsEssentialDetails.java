package testscripts.Navigator;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CHANNELS;
import functions.Environment;
import functions.Reporter;

/* Purpose		: This script for 'Property Details - Essential Details
 * TestCase Name: 'Property Details - Essential Details
 * Created By	: Sharmila Begam
 * Modified By	: 
 * Modified Date: 
 * Reviewed By	:	
 * Reviewed Date:
 */

public class REG91_PropertyDetailsEssentialDetails {
	CHANNELS SW = new CHANNELS();
	String SPGNUMBER;
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CHANNELS";
		Reporter.StartTest();
		SW.LaunchBrowser(Environment.NAVIGATORURL);
		SPGNUMBER=SW.TestData("SPGNum_LocateGuest");
	}
	@Test(priority=0)
	public void CheckPropertyContent(){
		SW.NavigatorLogin(SW.TestData("NavigatorUsername"),SW.TestData("NavigatorPassword")); //Login into the application
		SW.SelectCommunicationType();//selecting communication type
		SW.NormalClick("NavigatorHomePage_PropertyID_EB");
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", SW.TestData("PropertyID")+" ");
		SW.Wait(2);
		SW.EnterValue("NavigatorHomePage_PropertyID_EB", Keys.ENTER);
		SW.NormalClick("NavigatorHomePage_BeginSearch_BT");
		SW.NormalClick("NavigatorHomePage_PropertyNTB_LK");
		SW.Click("NavigatorHomePage_PropertyNTB_LK");
		SW.NormalClick("NavigatorPropPage_EssentialDetail_LK");
		if(SW.CompareText("Comparing the Panel header", "Essential Details", SW.GetText("NavigatorPropPage_EssentialHeader_DT"))){
			if(SW.ObjectExists("NavigatorPropPage_PropertyCatogery_DT"))
				if(SW.ObjectExists("NavigatorPropPage_MakeaGreen_DT"))		
					if(SW.ObjectExists("NavigatorPropPage_Address_DT"))
						if(SW.ObjectExists("NavigatorPropPage_Phone_DT"))
							if(SW.ObjectExists("NavigatorPropPage_Fax_DT"))
								if(SW.ObjectExists("NavigatorPropPage_Email_DT"))
									if(SW.ObjectExists("NavigatorPropPage_PCOComplainNum_DT"))
										if(SW.ObjectExists("NavigatorPropPage_CheckinTime_DT"))
											if(SW.ObjectExists("NavigatorPropPage_CheckOutTime_DT"))
												if(SW.ObjectExists("NavigatorPropPage_LocalPropertyTime_DT"))
													if(SW.ObjectExists("NavigatorPropPage_MinimumAge_DT"))
														if(SW.ObjectExists("NavigatorPropPage_PrimaryCurrency_DT"))
															if(SW.ObjectExists("NavigatorPropPage_YearBuilt_DT"))
																if(SW.ObjectExists("NavigatorPropPage_LastYearRenovated_DT"))
																	if(SW.ObjectExists("NavigatorPropPage_NoOfGuestRoom_DT"))
																		if(SW.ObjectExists("NavigatorPropPage_NoOfFloor_DT"))
																			if(SW.ObjectExists("NavigatorPropPage_GuestRoomFloor_DT"))
																				if(SW.ObjectExists("NavigatorPropPage_Voltage_DT"))
																					if(SW.ObjectExists("NavigatorPropPage_IndoorPools_DT"))
																						if(SW.ObjectExists("NavigatorPropPage_OutDoorPools_DT"))
																							if(SW.ObjectExists("NavigatorPropPage_KidPools_DT"))
																								Reporter.Write("Checking all the UI Data in the Essential page", "All data are found", "All data are found", "PASS");
																							else
																								Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_KidPools_DT"), "Fail");
																						else
																							Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_OutDoorPools_DT"), "Fail");
																					else
																						Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_IndoorPools_DT"), "Fail");
																				else
																					Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_Voltage_DT"), "Fail");
																			else
																				Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_GuestRoomFloor_DT"), "Fail");
																		else
																			Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_NoOfFloor_DT"), "Fail");
																	else
																		Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_NoOfGuestRoom_DT"), "Fail");
																else
																	Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_LastYearRenovated_DT"), "Fail");
															else
																Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_YearBuilt_DT"), "Fail");
														else
															Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_PrimaryCurrency_DT"), "Fail");
													else
														Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_MinimumAge_DT"), "Fail");
												else
													Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_LocalPropertyTime_DT"), "Fail");
											else
												Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_CheckOutTime_DT"), "Fail");
										else
											Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_CheckinTime_DT"), "Fail");
									else
										Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_PCOComplainNum_DT"), "Fail");
								else
									Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_Email_DT"), "Fail");
							else
								Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_Fax_DT"), "Fail");
						else
							Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_Phone_DT"), "Fail");
					else
						Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_Address_DT"), "Fail");
				else
					Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_MakeaGreen_DT"), "Fail");
			else
				Reporter.Write("Checking all the UI Data in the Essential page", "Object not found", SW.GetLocator("NavigatorPropPage_PropertyCatogery_DT"), "Fail");
		}
		else{
			Reporter.Write("Validation fails in compare", "CCC Internal Resource Area", "Fail", "Fail");
		}
	}
	@AfterClass
	public void EndTest(){
		SW.NavigatorLogOut();
		Reporter.StopTest();		
	}
}
