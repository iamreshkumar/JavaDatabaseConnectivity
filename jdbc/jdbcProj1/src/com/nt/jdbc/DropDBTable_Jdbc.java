/*  Write a jdbc App to create a DB table from jdbc..  */

package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropDBTable_Jdbc {
	public static void main(String[] args){
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			sc=new Scanner(System.in);
					
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastblish the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// Create statement object
			 if(con!=null)
				 st=con.createStatement();
			 int result=st.executeUpdate(" DROP TABLE MAYANK");
			 
			 if(result==0)
				 System.out.println(" Table is droped");
				 else
					 System.out.println(" Table not found");
			 
		}// try
		catch(SQLException se) {  // known exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){ //known exception
			cnf.printStackTrace();
		}
		catch(Exception e) {		//unknown exception
			e.printStackTrace();
		}
		
		finally {
			// Close jdbc All objects
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		} //finally
	}//main
}// class
