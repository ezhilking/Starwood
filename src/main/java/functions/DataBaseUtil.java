package functions;

/* Purpose		:Database related util class.
 * Developed By	:Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

public class DataBaseUtil extends Utility {

	// Database
	private Connection dbConnection;
	private Statement dbStatement;
	private ResultSet dbResultSet;

	public void EstablishConnection(String dbName) {
		String dbUrl = null;
		String username = null;
		String password = null;
		if (dbName.equalsIgnoreCase("qa3")) {
			dbUrl = "jdbc:oracle:thin:@phxndsqdb-orascan.nssd.star:1521/RESPONSE_Q3";
			username = "sg_batch_app";
			password = "Straw1234";
		} else if (dbName.equalsIgnoreCase("stage")) {
			dbUrl = "jdbc:oracle:thin:@phxndssydb-scan.ssd.star:1521/SGSRVC";
			username = "sg_batch_app";
			password = "batch_app01";
		} else if (dbName.equalsIgnoreCase("MTGQA3")) {
			dbUrl = "jdbc:oracle:thin:@10.132.173.23/LOGGER_Q3";
			username = "logger_app_qa";
			password = "Straw1234";
		} else if (dbName.equalsIgnoreCase("MTGSTAGE")) {
			dbUrl = "jdbc:oracle:thin:@10.132.173.23/LOGGER";
			username = "Shubbir";
			password = "Newuser#234";
			username = "sg_batch_app";   
			password = "batch_app01"; 
		}else if(dbName.equalsIgnoreCase("qa4")){
			dbUrl = "jdbc:oracle:thin:@phxndsqdb-orascan.nssd.star:1521/RESPONSE_Q4";
			username = "sg_batch_app";   
			password = "Straw1234"; 
		}else if(dbName.equalsIgnoreCase("qa2")){
			dbUrl = "jdbc:oracle:thin:@phxndsqdb-orascan.nssd.star:1521/RESPONSE_Q2";
			username = "sg_batch_app";   
			password = "Straw1234";
		}else if(dbName.equalsIgnoreCase("DWH")){
			dbUrl = "jdbc:oracle:thin:@gw-dwh.qa1.pii.star/ADHOCQA1";
			username = "crm_etl";   
			password = "fifa06";
		}else if(dbName.equalsIgnoreCase("NDSQA3")){
			dbUrl = "jdbc:oracle:thin:@phxndsqdb-orascan.nssd.star:1521/NDSQA3";
			username = "sg_batch_app";   
			password = "straw1234";
		}else{
			dbUrl = "jdbc:oracle:thin:@10.130.170.232:1521/opera";
			username = "starwood";   
			password = "ips";
		}
		/*
		 * else if(dbName.equalsIgnoreCase("galaxy")){ dbUrl =
		 * "jdbc:oracle:thin:@10.132.68.142:1521/Opera"; username = "ittcsc";
		 * password = "moid"; }
		 */
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		// String dbUrl =
		// "jdbc:mysql://phxndsqdb-orascan.nssd.star:1521/RESPONSE_Q3";

		try {
			// // Load Oracle JDBC Driver
			// DriverManager.registerDriver(new
			// oracle.jdbc.driver.OracleDriver());
			// // Connect to Oracle Database
			// Connection con = DriverManager.getConnection(dbUrl, username,
			// password);
			// Statement statement = con.createStatement();
			// ResultSet rs = statement.executeQuery(query);
			// if (rs.next()) {
			// Date currentDate = rs.getDate(1); // get first column returned
			// System.out.println("Current Date from Oracle is : "+currentDate);
			// }
			// rs.close();
			// statement.close();
			// con.close();

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				Environment.loger.log(Level.ERROR, "ClassNotFoundException occured! Check with the dependencies");
			}
			// Create Connection to DB
			dbConnection = DriverManager.getConnection(dbUrl, username, password);
			Environment.loger.log(Level.INFO, "Connection successfully established with DB!");
			// Create Statement Object
			dbStatement = dbConnection.createStatement();
		} catch (SQLSyntaxErrorException e) {
			Environment.loger.log(Level.ERROR, "Syntax error! Pass the proper SQL query");
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured", e);
			throw e;
		}
	}
	public DataBaseUtil(){
		//Default constructor	
	}
	public DataBaseUtil(String DBClassName, String dbName){
		String dbUrl = null;
		String username = null;
		String password = null;

		if(dbName.equalsIgnoreCase("mysql")){
			dbUrl = "jdbc:mysql://localhost:3306/benerator";
			username = "root";   
			password = "root";
		}else if(dbName.equalsIgnoreCase("oracle")){
			dbUrl = "jdbc:oracle:thin:@phxndsqdb-orascan.nssd.star:1521/Rates_Q3";
			username = "spg_batch_app";
			password = "Straw1234";
		}
		try {
			try {
				if(DBClassName.equalsIgnoreCase("mysql")){
					Class.forName("com.mysql.jdbc.Driver");
				}else if(DBClassName.equalsIgnoreCase("oracle")){
					Class.forName("oracle.jdbc.driver.OracleDriver");
				}
			} catch (ClassNotFoundException e) {
				Environment.loger.log(Level.ERROR, "ClassNotFoundException occured! Check with the dependencies");
			}
			dbConnection = DriverManager.getConnection(dbUrl, username, password);
			Environment.loger.log(Level.INFO, "Connection successfully established with DB-"+dbName);
			//Create Statement Object
			dbStatement = dbConnection.createStatement();
		} catch (SQLSyntaxErrorException e) {
			Environment.loger.log(Level.ERROR, "Syntax error! Pass the proper SQL query");
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured", e);
			throw e;
		}
	}

	public List<String> GetColumnValues(String query, String ColumnName) {
		List<String> ReturnText = new ArrayList<>();
		try {
			dbResultSet = ExecuteQuery(query);
			while (dbResultSet.next()) {
				ReturnText.add(dbResultSet.getString(ColumnName));
			}
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		}
		return ReturnText;
	}

	public boolean RecordExists(String queryWithCount) {
		boolean exists;
		try {
			dbResultSet = ExecuteQuery(queryWithCount);
			if (dbResultSet.next()) {
				exists = dbResultSet.getBoolean(1);
				if (exists)
					Environment.loger.log(Level.INFO, "Record exists in DB");
				else
					Environment.loger.log(Level.INFO, "Record doesn't exists in DB");
				return exists;
			}

		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		}
		return false;
	}

	public List<String> GetColumnValues(String query, int ColumnIndex) {
		List<String> ReturnText = new ArrayList<>();
		try {
			dbResultSet = ExecuteQuery(query);
			while (dbResultSet.next()) {
				ReturnText.add(dbResultSet.getString(ColumnIndex));
			}
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		}
		return ReturnText;
	}

	private ResultSet ExecuteQuery(String query) {
		try {
			dbResultSet = dbStatement.executeQuery(query);
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		}
		return dbResultSet;
	}

	public ResultSet ExecuteMultipleQuery(String query) {
		try {
			Statement statement = dbConnection.createStatement();
			dbResultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured",e);
		}
		return dbResultSet;
	}

	public void UpdateQuery(String query) {
		try {
			int ReturnValue = dbStatement.executeUpdate(query);
			if (ReturnValue == 1) {
				Environment.loger.log(Level.INFO, "Query Update Successfully:" + query);
			} else {
				Environment.loger.log(Level.ERROR, "Update not success" + query);
			}
			// dbConnection.commit();
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured", e);
		}
	}

	public void AddBatch(String Query){ 
		try {
			dbStatement.addBatch(Query);
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured", e);
		}
	}

	public boolean ExecuteBatch() throws BatchUpdateException,SQLException{
		boolean returnValue = false;
		dbStatement.executeBatch();
		returnValue = true;
		return returnValue;
	}

	public int CallProcedure(String procedure) {// TODO
		CallableStatement cstmt = null;
		int sReturn = -1;
		try {
			cstmt = dbConnection.prepareCall(procedure);
			// cstmt.setString(1, "1965");
			sReturn = cstmt.executeUpdate();
			// cstmt.getString(0);
		} catch (SQLException e) {
			Environment.loger.log(Level.ERROR, "SQLException Exception occured" + e.getMessage());
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				Environment.loger.log(Level.ERROR, "Unable to close the callprocedure" + e.getMessage());
			}
		}
		return sReturn;
	}

	public void CloseDBConnection(){
		try{
			if(dbConnection!=null)
				dbConnection.close();
			if(dbStatement!=null)
				dbStatement.close();
			if(dbResultSet!=null)
				dbResultSet.close();
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured" +e);
		}
	}
}
