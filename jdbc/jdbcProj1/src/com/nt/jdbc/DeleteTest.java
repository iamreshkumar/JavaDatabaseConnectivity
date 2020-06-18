// write a jdbc application to delete record based on the given student number

package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int no=0;
		int result=0;
		
		try {
			// Read inputs 
			sc=new Scanner(System.in);
			System.out.println("Enter student no to delete");
			no=sc.nextInt();
			
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// Create Statement object
			if(con!=null)
				st=con.createStatement();
			
			// send and execute SQL query in DB s/w
			if(st!=null)
				result=st.executeUpdate("DELETE FROM STUDENT WHERE SNO="+no);
			
			// process the result
			if(result==0)
				System.out.println("No records found for deletion");
			else
				System.out.println(result+" records are found for deletion  and deleted");
		}// try
		
		catch(SQLException se) { //known exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) { // known exception
			cnf.printStackTrace();
		}
		catch(Exception e) { // unknown exception
			e.printStackTrace();
		}
		
		finally {
			// close all jdbc object
			
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
			catch(Exception se) {
				se.printStackTrace();
			}
			
		}//finally
	}//main
}// class