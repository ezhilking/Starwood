package testscripts.SPGBatch;

import java.util.Calendar;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;


public class SPGBatch_Emirates {
	CRM SW=new CRM();
	String Mbrshp_num1;
	int Extl_id1=SW.RandomInteger(8); // used as extl_mbrshp_num(for both EMIR n EMIRDC) and also as extl_mbrshp_id
	int Extl_id2=SW.RandomInteger(8); // used as extl_mbrshp_id
	int tms=SW.RandomInteger(9);
	int mrktg_pgm_acpt_num=SW.RandomInteger(7);
	int count;
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower = "CRM";
		Reporter.StartTest();
	}
	
	@Test(priority=0)
	public void SPGBatch_Emirates_DataSetup() {
		SW.EstablishConnection("QA3");
		
		/*----------------------------------------Initialization of All date--------------------------------------------------------------------------*/		
		String Post_tmstmp="to_timestamp('"+SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yy"), "dd-MMM-yy", -1,	 Calendar.DATE).toUpperCase()+" 12.00.00."+tms+" AM','DD-MON-RR HH.MI.SS.FF AM')"; //Txn_post_tmstmp should be one day before the execution day
		String Late_Qual_tmstmp=SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yyyy"), "dd-MMM-yyyy", -2,	 Calendar.DATE).toUpperCase(); //Parameter = Last_Qual_tmstmp should be 2 days before the execution
		String Actv_Date="to_timestamp('"+SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yy"), "dd-MMM-yy", -365,	 Calendar.DATE).toUpperCase()+"','DD-MON-RR HH.MI.SSXFF AM')"; //Setting Actv_start_date to one year before 
		String Arrival_date="to_timestamp('"+SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yy"), "dd-MMM-yy", -300,	 Calendar.DATE).toUpperCase()+"','DD-MON-RR HH.MI.SS.FF AM')"; //Stay Arrival Date ,after the activation date
		String Depature_date="to_timestamp('"+SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yy"), "dd-MMM-yy", -296,	 Calendar.DATE).toUpperCase()+"','DD-MON-RR HH.MI.SS.FF AM')"; //Star Departure Date, after the arrival date
		/*---------------------------------------------------------------------------------------------------------------------------*/
		
		/*Step 1: Query1 --> Select any active member from DB and store in variable Mbrshp_num1*/
		String Query1="select Mbrshp_num from freq_travel_mbrshp where mbrshp_lvl='A' and mbrshp_sts='A' and join_date > (sysdate-30) and src_cd='DIGA'";
		List<String> MemberShip=SW.GetColumnValues(Query1, "Mbrshp_num");
		Mbrshp_num1=MemberShip.get(0);
		/*---------------------------------------------------------------------------------------------------------------------------*/
		
		/*Step 2: Query2 --> Check whether member has any extl linked, if yes, delete it and link it with EMIR and EMIRDC*/
		String Query2="Select * from freq_travel_extl_mbrshp where mbrshp_num="+"'"+Mbrshp_num1+"'";		
		List<String> Extl_mrktg_pgm_id=SW.GetColumnValues(Query2, "extl_mrktg_pgm_id");
		count=Extl_mrktg_pgm_id.size();
		String Query3="Insert into freq_travel_extl_mbrshp (MBRSHP_NUM,MRKTG_PGM,EXTL_MRKTG_PGM_ID,TRANSFER_TYPE,EXTL_MBRSHP_NUM,EXTL_MBRSHP_LVL,CREATE_USER_ID,CREATE_DATE,UPDATE_USER_ID,UPDATE_DATE,ACTV_DATE,DEACTV_DATE,EXTL_MBRSHP_ID) values"+" ('"+Mbrshp_num1+"',5,'EMIR','N/A','"+Extl_id1+"',' ','AUTOMATE',to_timestamp('28-DEC-16','DD-MON-RR HH.MI.SSXFF AM'),null,null,"+Actv_Date+",null,"+Extl_id1+")";
		String Query4="Insert into freq_travel_extl_mbrshp (MBRSHP_NUM,MRKTG_PGM,EXTL_MRKTG_PGM_ID,TRANSFER_TYPE,EXTL_MBRSHP_NUM,EXTL_MBRSHP_LVL,CREATE_USER_ID,CREATE_DATE,UPDATE_USER_ID,UPDATE_DATE,ACTV_DATE,DEACTV_DATE,EXTL_MBRSHP_ID) values"+" ('"+Mbrshp_num1+"',5,'EMIRDC','DEFAULT','"+Extl_id1+"','3','AUTOMATE',to_timestamp('28-DEC-16','DD-MON-RR HH.MI.SSXFF AM'),null,null,"+Actv_Date+",null,"+Extl_id2+")";
		if(count>=1){
			String Query5="delete freq_travel_Extl_mbrshp where mbrshp_num="+"'"+Mbrshp_num1+"'";
			SW.UpdateQuery(Query5);	
		}
		SW.UpdateQuery(Query3);
		SW.UpdateQuery(Query4);
		/*-------------------------------------------------------------------------------------------------------------------------------*/
		
		/*Step 3: Query 6 --> insert a stay transaction on the previous day*/
		String Query6="Insert into freq_travel_txn (MBRSHP_NUM,MRKTG_PGM,POST_TMSTMP,MBRSHP_LVL,PROP_ID,LOC_ID,BONUS_NUM,AWD_ORDER_NUM,MRKTG_PGM_ACPT_NUM,TXN_TYPE,TXN_ADJ_TYPE,TOT_POINTS,PRNT_IND,INCNTV_PGM,BNFT_NUM,PROMO_NUM,MEDIA_CD,EXTL_MRKTG_PGM_ID,EXTL_MBRSHP_NUM,RES_ARV_DATE,RES_DPRT_DATE,RM_TYPE_CD,RM_RATE,RM_RVNU,FOOD_BEV_RVNU,TOT_RVNU,POINT_CAP_IND,RATE_PLAN_ID,RM_TYPE,CONF_NUM,BKNG_DATE,BKNG_TIME,BKNG_LOC_ID,SET_NUM,PMS_CONF_NUM,CREATE_USER_ID,CREATE_DATE,UPDATE_USER_ID,UPDATE_DATE,BNFT_CATG_ID,TOT_EARNED_AWD,AWD_GROUP,LOCAL_CUR_CD,RM_RVNU_LOCAL_AMT,FOOD_BEV_RVNU_LOCAL_AMT,TOT_RVNU_LOCAL_AMT,EXCHNG_RATE,UPDATE_APP_ID,BONUS_FCTR,AVG_RM_RATE_LOCAL_AMT,AVG_RM_RATE,MTG_RM_RENTAL_LOCAL_AMT,MTG_RM_RENTAL,RM_RATE_LOCAL_AMT,TXN_BATCH_ID,CREATE_APP_ID,BAL_BEFORE_POST_POINT,BAL_AFTER_POST_POINT,EVENT_ID,RM_NUM,BOOKED_FOR_MBRSHP_NUM,BOOKED_BY_MBRSHP_NUM,MBRSHP_TYPE_ID,LIFETIME_MBRSHP_LVL) values "
		+ "('"+Mbrshp_num1+"',5,"+Post_tmstmp+",'A',1005,300594,0,0,"+mrktg_pgm_acpt_num+",'S','S',50336,'Y',null,0,0,null,null,null,"+Arrival_date+","+Depature_date+",'R',2237,24607,301,25168,'N','RATE1','V',804575213,to_timestamp('21-JUN-16','DD-MON-RR HH.MI.SS.FF AM'),null,300594,0,'1675756','BTCHSTY5',to_timestamp('19-JUL-16','DD-MON-RR HH.MI.SS.FF AM'),'BTCHSTY5',to_timestamp('19-JUL-16','DD-MON-RR HH.MI.SS.FF AM'),0,0,null,'EUR',19194,235,19632,0.7800245772,11,0,0,0,0,null,0,62337864,11,131574,181910,null,'553',null,null,1,null)";
		SW.UpdateQuery(Query6);
		/*--------------------------------------------------------------------------------------------------------------------------------*/
		
		/*Step 4: Query 7 and Query 8 --> prc_ft_batch_parm date set up*/
		List<String> seq_num=SW.GetColumnValues("select seq_num from prc_ft_batch_parm where parm_name='QUAL_STAY_LAST_TMSTMP' and job_name='EKQUALST'", "seq_num");
		String seq_num1=seq_num.get(0);
		List<String> actv_seq_num=SW.GetColumnValues("select seq_num from prc_ft_batch_parm where parm_name='ACTV_START_DATE' and job_name='EKQUALST'", "seq_num");
		String actv_seq_num1=actv_seq_num.get(0);
		String Query7="update prc_ft_batch_parm set parm_value='"+SW.DateAddDays(SW.GetTimeStamp("dd-MMM-yy"), "dd-MMM-yy", -365,	 Calendar.DATE).toUpperCase()+"' where seq_num='"+actv_seq_num1+"' and job_name='EKQUALST'";
		SW.UpdateQuery(Query7);
		String Query8="update prc_ft_batch_parm set parm_value='"+Late_Qual_tmstmp+"' where seq_num='"+seq_num1+"' and job_name='EKQUALST'";
		SW.UpdateQuery(Query8);
		
	}
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();		
	}

}
