package testscripts.SPGBatch;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions.CRM;
import functions.Environment;
import functions.Reporter;

public class SPGBatch_Airlines {
	CRM SW = new CRM();
	String Mbrshp_num1;
	int AWD_ORDER_NUM=SW.RandomInteger(6);// Used for award order number
	
	@BeforeClass
	public void StartTest(){
		Environment.Tower= "CRM";
		Reporter.StartTest();
	}
	
	@Test(priority=0)
	public void SPGBatch_Airlines_DataSetup(){
		SW.EstablishConnection("QA3");
		
		/*-----------------------------Data setup for PKG_JOB_AIR_VS4MTT  job------------------------------------------------------*/
		/*step 1 : Query1 ---> select any member from the database and store it for Mbrshp_num1*/
		String Query1="select * from freq_travel_order where awd_id='VIRGIN' and awd_type='VS' and mrktg_pgm=5 and num_point_awd='1000'";
		List<String> MemberShip=SW.GetColumnValues(Query1, "Mbrshp_num");
		Mbrshp_num1=MemberShip.get(1);
		
		/*step 2 : Query2 ---> insert a record into freq_travel_order table with the awd_sts as O*/
		String Query2="Insert into odsft.freq_travel_order (MRKTG_PGM,AWD_ORDER_NUM,MBRSHP_NUM,POST_TMSTMP,AWD_ID,AWD_STS,AWD_TYPE,PRNT_DATE,RDMPTN_DATE,NUM_POINT_AWD,NUM_POINT_RDMPTN,CMMT_TEXT,RDMPTN_LOC_ID,EXTL_MRKTG_PGM_ID,EXTL_MBRSHP_NUM,CREATE_USER_ID,CREATE_DATE,UPDATE_USER_ID,UPDATE_DATE,SNGL_MULTI_OPT,FAX_ORIG_OPT,AWD_ARV_DATE,CREATE_APP_ID,UPDATE_APP_ID) values "+"(5,"+AWD_ORDER_NUM+",'"+Mbrshp_num1+"',to_timestamp('15-SEP-06 12.16.31.462120000 PM','DD-MON-RR HH.MI.SS.FF AM'),'VIRGIN','O','VS',null,to_timestamp('15-SEP-06','DD-MON-RR HH.MI.SS.FF AM'),3206,0,'WWW ORDER',0,'VIRGIN','00648150704','SPGTXNADMIN',to_timestamp('15-SEP-06','DD-MON-RR HH.MI.SS.FF AM'),'STARLINK',to_timestamp('15-SEP-06','DD-MON-RR HH.MI.SS.FF AM'),null,null,null,null,null)";
		SW.UpdateQuery(Query2);
		
		/*step 3 : Query3 ---> update plsql_batch_scheduler tabel*/
		String Query3="update odsft.prc_ft_plsql_batch_scheduler set process_override_ind = 'N', deactivate_date = null where job_name = 'VSRDMT'";
		SW.UpdateQuery(Query3);
		
		/*step 4 : Query4 ---> update the parm table*/
		String Query4="update odsft.prc_ft_batch_parm set parm_value = 0 where job_name = 'VS4MTT' and parm_name = 'PRTNR_FILE_ID'";
		SW.UpdateQuery(Query4);
		
	}
	
	@AfterClass
	public void EndTest(){
		Reporter.StopTest();
	}

}
