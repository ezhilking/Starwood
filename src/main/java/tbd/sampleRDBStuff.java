package tbd;

import java.sql.BatchUpdateException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functions.CRS;
import functions.DataBaseUtil;
import functions.Environment;
import functions.Reporter;
import functions.Utility;

public class sampleRDBStuff {
	private static ResultSet itinResultSet,gst_confResultSet,resResultSet,resrvd_productResultSet,resrvd_product_rateResultSet,gstconf_resrvdprdct_xrefResultSet,access_logResultSet;

	public static void main(String[] args){
		int TotalCount =0;
		functions.Environment.Tower = "CRS";
		Reporter.StartTest();
		Utility SW = new Utility();
		StringBuilder resID = new StringBuilder();
		try{
			DataBaseUtil StarwoodDB = new DataBaseUtil("oracle", "oracle");
			DataBaseUtil SQLDB = new DataBaseUtil("mysql", "mysql");
			itinResultSet = SQLDB.ExecuteMultipleQuery("select * from ITIN");
			gst_confResultSet = SQLDB.ExecuteMultipleQuery("select * from gst_conf");
			resResultSet = SQLDB.ExecuteMultipleQuery("select * from res");
			resrvd_productResultSet = SQLDB.ExecuteMultipleQuery("select * from resrvd_product");
			resrvd_product_rateResultSet = SQLDB.ExecuteMultipleQuery("select * from resrvd_product_rate");
			gstconf_resrvdprdct_xrefResultSet = SQLDB.ExecuteMultipleQuery("select * from  gstconf_resrvdprdct_xref");
			access_logResultSet = SQLDB.ExecuteMultipleQuery("select * from access_log");

			ResultSetMetaData RSitin = itinResultSet.getMetaData();
			ResultSetMetaData RSgst_conf = gst_confResultSet.getMetaData();
			ResultSetMetaData RSres = resResultSet.getMetaData();
			ResultSetMetaData RSresrvd_product = resrvd_productResultSet.getMetaData();
			ResultSetMetaData RSresrvd_product_rate = resrvd_product_rateResultSet.getMetaData();
			ResultSetMetaData RSgstconf_resrvdprdct_xref = gstconf_resrvdprdct_xrefResultSet.getMetaData();
			ResultSetMetaData RSaccess_log = access_logResultSet.getMetaData();

			List<String> itinColValues = new ArrayList<String>(),gst_confColValues = new ArrayList<String>(),resColValues = new ArrayList<String>(),
					resrvd_productColValues = new ArrayList<String>(),resrvd_product_rateColValues = new ArrayList<String>(),
					gstconf_resrvdprdct_xrefColValues = new ArrayList<String>(),access_logColValues = new ArrayList<String>();

			int itinColumnCount = RSitin.getColumnCount();
			int gst_confColumnCount = RSgst_conf.getColumnCount();
			int resColumnCount = RSres.getColumnCount();
			int resrvd_productColumnCount = RSresrvd_product.getColumnCount();
			int resrvd_product_rateColumnCount = RSresrvd_product_rate.getColumnCount();
			int gstconf_resrvdprdct_xrefColumnCount = RSgstconf_resrvdprdct_xref.getColumnCount();
			int access_logColumnCount = RSaccess_log.getColumnCount();

			for (int i = 1; i <= itinColumnCount; i++) {
				itinColValues.add(RSitin.getColumnName(i));
			}

			for (int i = 1; i <= gst_confColumnCount; i++) {
				gst_confColValues.add(RSgst_conf.getColumnName(i));
			}

			for (int i = 1; i <= resColumnCount; i++) {
				resColValues.add(RSres.getColumnName(i));
			}

			for (int i = 1; i <= resrvd_productColumnCount; i++) {
				resrvd_productColValues.add(RSresrvd_product.getColumnName(i));
			}

			for (int i = 1; i <= resrvd_product_rateColumnCount; i++) {
				resrvd_product_rateColValues.add(RSresrvd_product_rate.getColumnName(i));
			}

			for (int i = 1; i <= gstconf_resrvdprdct_xrefColumnCount; i++) {
				gstconf_resrvdprdct_xrefColValues.add(RSgstconf_resrvdprdct_xref
						.getColumnName(i));
			}

			for (int i = 1; i <= access_logColumnCount; i++) {
				access_logColValues.add(RSaccess_log.getColumnName(i));
			}

			boolean resultSet = true;

			while (resultSet == itinResultSet.next() && gst_confResultSet.next()	&& resResultSet.next() && resrvd_productResultSet.next()
					&& resrvd_product_rateResultSet.next() && gstconf_resrvdprdct_xrefResultSet.next() && access_logResultSet.next()) {

				resID.append(resResultSet.getString("res_id")+",");
				//				if(!resultSet){
				//					Environment.loger.log(Level.ERROR, "All resultSet doesnt have valid data");
				//					System.exit(0);
				//				}

				StringBuilder itinQuery = new StringBuilder(), gst_confQuery = new StringBuilder(),resQuery = new StringBuilder(),
						resrvd_productQuery = new StringBuilder(),resrvd_product_rateQuery = new StringBuilder(),gstconf_resrvdprdct_xrefQuery = new StringBuilder(),access_logQuery = new StringBuilder();
				// String ArrivalDate = SW.DateAddDays(SW.GetTimeStamp("dd-MM-yy"),
				// "dd-MM-yy", 1, Calendar.DATE);
				// String DepartureDate =
				// SW.DateAddDays(SW.GetTimeStamp("dd-MM-yy"), "dd-MM-yy", 2,
				// Calendar.DATE);
				itinQuery.append("Insert into BOOKING2.ITIN(ITIN_ID,CHNL_CD,USERID,ITIN_NAME,CREATE_TMSTMP,PRIM_CONTACT_LAST_NAME,PRIM_CONTACT_MTHD_CD,STS_CD,PRIM_CONTACT_INFO,PRIM_CONTACT_MI,PRIM_CONTACT_SUFFIX,PRIM_CONTACT_FIRST_NAME,PRIM_CONTACT_PREFIX,PRIM_CONTACT_TITLE,EXTL_ENTITY_ID,STARLINK_ITIN_ID,PRC_STATE_TYPE_ID) values (");
				gst_confQuery.append("Insert into BOOKING2.gst_conf (GST_CONF_ID,TOT_COST_BEFORE_DISC_AMT,GST_RES_START_TIME,GST_RES_END_TIME,CREATE_TMSTMP,TOT_TAX_AMT,TOT_COST_AFTER_DISC_AMT,GST_CONF_STS_CD,COST_CUR_CD,TAX_CUR_CD,GST_ARV_DATE,GST_DPRT_DATE,PSPT_NUM,PSPT_NUM_ID,PSPT_NUM_HASH,ENC_TYPE,PSPT_CNTRY_CD,RES_ID,GST_MASTER_PROF_ID,GST_CONF_CHNL_CD,CREATE_USER_ID,MATCH_MERGE_REASON_TEXT,PROP_ID,PRIM_LANG_CD,GST_CONF_CREATE_LOC_ID,PMS_GST_PROFILE_ID,PRIM_GST_CONF_IND,VIP_IND,CUST_PROF_ID,GST_NAME_PREFIX_CD,GST_NAME_TITLE_CD,GST_FIRST_NAME,GST_MI_NAME,GST_LAST_NAME,GST_NAME_SUFFIX_CD,PRIM_GST_IND,ADDR_TYPE_CD,ADDR_LN_1_TEXT,ADDR_LN_2_TEXT,ADDR_LN_3_TEXT,ADDR_LN_4_TEXT,CITY_NAME,STATE_PROV_CD,ZIP_POSTAL_CD,CNTRY_CD,GST_ORIGIN_CNTRY_CD,GST_AGE,SPG_MBRSHP_ID,VIP_LEVEL_CD,SEND_CONF_CD,GST_COMPANY_NAME,ALT_GST_COMPANY_NAME,ALT_GST_NAME_TITLE_CD,ALT_GST_NAME_PREFIX_CD,ALT_GST_FIRST_NAME,ALT_GST_MI_NAME,ALT_GST_LAST_NAME,ALT_GST_NAME_SUFFIX_CD,ALT_ADDR_TYPE_CD,ALT_ADDR_LN_1_TEXT,ALT_ADDR_LN_2_TEXT,ALT_ADDR_LN_3_TEXT,ALT_ADDR_LN_4_TEXT,ALT_CITY_NAME,ALT_STATE_PROV_CD,ALT_ZIP_POSTAL_CD,ALT_CNTRY_NAME,ALT_GST_ORIGIN_CNTRY_NAME,GST_DTL_LOCALE_CD,LOYALTY_SEGMENT_DESC) values (");
				resQuery.append("Insert into BOOKING2.res (RES_ID,CREATE_USER_ID,PRIM_CONTACT_LAST_NAME,PRIM_CONTACT_MTHD_CD,PRIM_CONTACT_PREFIX_CD,RES_STS_CD,RES_CREATE_DATE,PRIM_CONTACT_FIRST_NAME,PRIM_CONTACT_MI_NAME,PRIM_CONTACT_TITLE_CD,ITIN_ID,RES_CHNL_CD,PRIM_CONTACT_SUFFIX_CD,PROP_ID,PRIM_CONTACT_INFO,CREATE_RES_LOC_ID,PROP_VIEW_CD,CALL_SRCE_CNTRY_CD,EMAIL_CD,PRIM_CHOICE_IND,LAST_MOD_LOC_ID,RES_ARV_DATE,RES_DPRT_DATE,ORIG_PROP_ID,RES_CREATE_MBRSHP_ID,RES_CREATE_MRKTG_PGM,PRC_STATE_TYPE_ID,LOCALE_CD,CHNL_RATE_CD,TLT_CANCEL_IND,TOUR_CD) values (");
				resrvd_productQuery.append("Insert into BOOKING2.resrvd_product (RESRVD_PRODUCT_ID,RES_ID,PRDCT_BKNG_START_TMSTMP,PRDCT_CD,PRDCT_BKNG_END_TMSTMP,PRDCT_COST_AMT,COST_UNIT_CD,STS_CD,TOT_TAX_AMT,COST_CUR_CD,TAX_CUR_CD,PKG_TYPE_CD,RATE_DSPL_IND,PRODUCT_ID,GUAR_IND,GUAR_TYPE_ID,PROP_ID,ADLT_GST_QTY,CHLD_GST_QTY,UPGRADE_PRODUCT_ID,UPGRADE_PRODUCT_CD,ADLT_ROLWY_QTY,CHLD_ROLWY_QTY,CRIB_QTY,PRDCT_BED_TYPE_CD,UPGRADE_PRDCT_BED_TYPE_CD,ROOM_NUM,AVG_PRDCT_RATE,UPGRADE_TP_ROOM_TYPE_CD,THIRD_PARTY_RM_TYPE_CD,GUAR_MTHD_CD,ADLT_GST_BASE_QTY,CHLD_GST_BASE_QTY,CHARG_PRDCT_ID,CHARG_PRDCT_CD,CHARG_TP_RM_TYPE_CD,CHARG_PRDCT_BED_TYPE_CD,RM_TYPE_DSPL_IND,EXTENDED_BLOCK_RSN_CD,EXTENDED_BLOCK_RSN_DESC,RM_CARD_TRACK_3_DATA) values (");
				resrvd_product_rateQuery.append("Insert into BOOKING2.resrvd_product_rate (RESRVD_PRODUCT_ID,RATE_ID,RATE_START_DATE,RATE_END_DATE,RATE_NET_AMT,RATE_GROSS_AMT,CUR_CD,RATE_CHNG_REASON_CD,EXTRA_PRSN_NET_RATE_AMT,CHLD_NET_RATE_AMT,CHLD_GROSS_RATE_AMT,CRIB_NET_RATE_AMT,CRIB_GROSS_RATE_AMT,ROLLWY_NET_RATE_AMT,ROLLWY_GROSS_RATE_AMT,EXTRA_PRSN_GROSS_RATE_AMT,RATE_SEQ_ID,GROUP_BLOCK,FLOAT_RATE_ID,TAX_AMT,TAX_CUR_CDE,CORP_MKTSG_CD,PROP_MKTSG_CD,RATE_CATG_ID,RATE_DSPL_IND,PKG_RP_IND,FLOAT_RATE_AMT,RATE_OVRD_IND,EXTL_DRR_PROMO_CD) values (");
				gstconf_resrvdprdct_xrefQuery.append("Insert into BOOKING2.gstconf_resrvdprdct_xref (RESRVD_PRDCT_ID,GST_CONF_ID,CREATE_DATE,CREATE_USER_ID,UPDATE_DATE,UPDATE_USER_ID) values (");
				access_logQuery.append("Insert into booking2.access_log (ACCESS_ID,ITIN_ID,CHNL_CD,USER_ID,ACCESS_TYPE_CD,RES_ID,ACCESS_TMSTMP,GST_CONF_ID,LOC_ID,SESSION_ID,VDN_NUM,RES_DPRT_DATE,BKNG_PRC_TMSTMP,TXN_ID,USER_AGENT) values (");

				StarwoodDB.AddBatch(ConstructQuery(itinColValues, itinResultSet, itinQuery).toString());
				StarwoodDB.AddBatch(ConstructQuery(gst_confColValues, gst_confResultSet, gst_confQuery).toString());
				StarwoodDB.AddBatch(ConstructQuery(resColValues, resResultSet, resQuery).toString());
				StarwoodDB.AddBatch(ConstructQuery(resrvd_productColValues, resrvd_productResultSet, resrvd_productQuery).toString());
				StarwoodDB.AddBatch(ConstructQuery(resrvd_product_rateColValues, resrvd_product_rateResultSet, resrvd_product_rateQuery).toString());
				StarwoodDB.AddBatch(ConstructQuery(gstconf_resrvdprdct_xrefColValues, gstconf_resrvdprdct_xrefResultSet, gstconf_resrvdprdct_xrefQuery).toString());
				StarwoodDB.AddBatch(ConstructQuery(access_logColValues, access_logResultSet, access_logQuery).toString());
				try{
					StarwoodDB.ExecuteBatch();
					TotalCount++;
				}catch(BatchUpdateException e){
					String ID;
					if(e.getMessage().trim().endsWith("invalid number")){
						Environment.loger.log(Level.ERROR, "NULL value created in local SQL database!!So stopping the execution");
						System.exit(1);
					}

					if(e.getMessage().contains("BOOKING2.IDX4_GST_CONF")){
						ID = GetIDFromQuery(itinQuery);
						StarwoodDB.ExecuteMultipleQuery("Delete from booking2.itin where itin_id='"+ID+"'");

					}else if(e.getMessage().contains("BOOKING2.PK_RES")){
						ID = GetIDFromQuery(gst_confQuery);
						StarwoodDB.ExecuteMultipleQuery("Delete from booking2.gst_conf where gst_conf_id='"+ID+"'");

						ID = GetIDFromQuery(itinQuery);
						StarwoodDB.ExecuteMultipleQuery("Delete from booking2.itin where itin_id='"+ID+"'");

					}else if(e.getMessage().contains("BOOKING2.PK_RESRVD_PRODUCT")){
						ID = GetIDFromQuery(itinQuery);
						StarwoodDB.ExecuteMultipleQuery("Delete from booking2.itin where itin_id='"+ID+"'");

						ID = GetIDFromQuery(gst_confQuery);
						StarwoodDB.ExecuteMultipleQuery("Delete from booking2.gst_conf where gst_conf_id='"+ID+"'");

						ID = GetIDFromQuery(resQuery);
						StarwoodDB.ExecuteMultipleQuery("Delete from booking2.res where res_id='"+ID+"'");
					}
				}
				Environment.loger.log(Level.INFO, "Table inserted-"+TotalCount);
			}
			Environment.loger.log(Level.INFO, "Total count is- "+TotalCount);

			//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
			Environment.SetBrowserToUse("FF");
			WebDriver driver = SW.LaunchBrowser(Environment.BOOKING);
			SW.Click("//a[@href='/BookingWeb/retriggerMsgToPMS.jsp']");
			List<WebElement> ReTrigger = driver.findElements(By.xpath("//a[@href='/BookingWeb/retriggerMsgToPMS.jsp']"));
			ReTrigger.get(0).sendKeys(Keys.ENTER);

			SW.EnterValue("//input[@ame='retriggerBatchSize']", "100");//Retrigger Res batch size 
			SW.DropDown_SelectByText("//select[@name='sendPMS']", "Y");//Send to PMS dropdown
			//			SW.Click("//a[@href='/BookingWeb/retriggerMsgToPMS.jsp']");
			SW.EnterValue("//*[@name='anyIds']", resID.toString());
			Environment.loger.log(Level.INFO, resID.toString());
			//			List<WebElement> ResID_EB = SW.GetAllVisibleElements("//*[@name='anyIds']");
			//			ResID_EB.get(0).sendKeys(resID.toString());
			Reporter.Write("AllReservationNumber", "Entered all reservation numbers into booking application", "Entered all reservation numbers into booking application", "PASS");
			SW.Click("//input[@name='reTrigger']");
//			SW.WaitForPageload();
		}catch(SQLException e){
			Environment.loger.log(Level.ERROR,e);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR,"Exception occured",e);
		}finally{
			Reporter.StopTest();
		}
	}
	public static StringBuilder ConstructQuery(List<String> ColumnValues,ResultSet dbResultSet, StringBuilder sb ){
		try{
			for (int i = 1; i<=ColumnValues.size(); i++) {
				sb.append("'");
				sb.append(dbResultSet.getString(i) + "'");
				if (i <= ColumnValues.size()-1) {
					sb.append(",");
				}
			}
			sb.append(")");
		}catch(SQLException e){
			Environment.loger.log(Level.ERROR, e);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, e);
		}
		Environment.loger.log(Level.INFO, sb);
		return sb;
	}


	public static String GetIDFromQuery(StringBuilder str){
		String ReturnValue = null;
		String ValueOf = "values ('";
		try{
			ReturnValue = str.substring(str.lastIndexOf(ValueOf)+ValueOf.length(), str.indexOf("',")).trim();
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, e);
		}
		return ReturnValue;
	}
}
