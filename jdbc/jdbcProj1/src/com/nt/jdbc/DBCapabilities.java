package com.nt.jdbc;
import java.sql.*;
public class DBCapabilities {
	public static void main(String[] args) {
		
		Connection con=null;
		DatabaseMetaData dbmd=null;
		
		try {
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			//get Metadat
			dbmd=con.getMetaData();
			
			//The db details are
			if(dbmd!=null) {
				System.out.println("Database name 		::"+dbmd.getDatabaseProductName());
				System.out.println("Database version	::"+dbmd.getDatabaseProductVersion());
				System.out.println("Database major version::"+dbmd.getDatabaseMajorVersion());
				System.out.println("Database minor version::"+dbmd.getDatabaseMinorVersion());
			
				System.out.println();
				
				System.out.println("JDBC major version::"+dbmd.getJDBCMajorVersion());
				System.out.println("JDBC minor version::"+dbmd.getJDBCMinorVersion());
				System.out.println("JDBC Driver name::"+dbmd.getDriverName());
				System.out.println("JDBC Driver major version::"+dbmd.getDriverMajorVersion());
				System.out.println("JDBC Driver minor version::"+dbmd.getDriverMinorVersion());
				
				System.out.println();
				
				System.out.println("All SQL Keywords::"+dbmd.getSQLKeywords());
				System.out.println("All numeric function::"+dbmd.getNumericFunctions());
				System.out.println("All SYstem function::"+dbmd.getSystemFunctions());
				System.out.println("All String function::"+dbmd.getStringFunctions());
				
				System.out.println();
				
				System.out.println("Max table name length::"+dbmd.getMaxTableNameLength());
				System.out.println("Max column name length::"+dbmd.getMaxColumnNameLength());
				System.out.println("Max Row size::"+dbmd.getMaxRowSize());
				System.out.println("Max columns in select Query::"+dbmd.getMaxColumnsInSelect());
				System.out.println("Max cols in Databse table::"+dbmd.getMaxColumnsInTable());
				System.out.println("Max Connection to database::"+dbmd.getMaxConnections());
				
				System.out.println("user::"+dbmd.getUserName());
				
			
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// close the jdbc object
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}

