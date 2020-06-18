/*	Write a jdbc application that gives emp deatils who having highest salary?
  */


package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest3{

	public static void main(String args[]){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		
		try {
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			//Create statement
			if(con!=null)
				st=con.createStatement();
			
			// Send and execute Sql query in database software
			if(st!=null)
				rs=st.executeQuery("SELECT ENAME,SAL,JOB FROM EMP)");
			
			// process the resultset
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getString(1)+"   "+rs.getInt(2)+"   "+rs.getString(3));
					
					flag=true;
				}//while
			}// if
			if(!flag) {
				System.out.println("Record not Found");
			}//if
			
		}// try
		
		catch(SQLException se){ //known exception
		se.printStackTrace();
		}
		
		catch(ClassNotFoundException cnf) { // known exception
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
		}// finally
	}//main
}// class
