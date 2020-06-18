/*  write a jdbc application that gives record count from db*/

package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest4 {

	public static void main(String[] args){
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
					
			// create statement
					if(con!=null)
						st=con.createStatement();
			
			// send and execute SQL  query in database
			if(st!=null)
				rs=st.executeQuery("SELECT COUNT(*) FROM EMP");
			
			// process the resultset
			if(rs!=null){
				if(rs.next()) {
					count=rs.getInt("count(*)");
		//		System.out.println("count"+rs.getInt(I));
			}//if
		}//if
			System.out.println("Records count"+count);
		}//try

		catch(SQLException se){ //known exception 
			se.printStackTrace();
		}
		
		catch(ClassNotFoundException cnf) {  // known exception
			cnf.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			// close all jdbc object
			try {
				if(rs!=null)
					rs.close();
			}
			
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}
			
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}// class
