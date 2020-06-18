// write a jdbc application that update student details with given new adress based on the given student number ......
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest{

	public static void main(String[] args){
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int no=0;
		String newName=null,newAddrs=null;
		String query=null;
		int result=0;
		
		try {
			// Read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println(" Enter existing student number");
				no=sc.nextInt();	// gives 101
				System.out.println("Enter new name for student");
				newName=sc.next();
				System.out.println("Enter new address for student");
				newAddrs=sc.next();
			}
		
		// convert input values as required for SQL query
		
		newName="'"+newName+"'";	// gives 'preeti'
		newAddrs="'"+newAddrs+"'"; // gives mumbai
		
		// Register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// Eastblish the connection
		
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		
		// Create Statement object
		
		if(con!=null)
			st=con.createStatement();
		
		// Prepare SQL query
		// update student set sname='newRaja',sadd='preeti' where sno=101
		
		query="update student set sname="+newName+",sadd="+newAddrs+" where sno="+no;
		
		// send and excute SQL query in db s/w
		if(st!=null)
			result=st.executeUpdate(query);
		// process the result
		if(result==0)
			System.out.println("Record not found to update");
		else
			System.out.println(" Record found to update  \n  Record updated");
			
	 }//try
		
		catch(SQLException se) { // to known exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){	// to knwn exception
			cnf.printStackTrace();
		}
		catch(Exception e) {	// unknown exception
			e.printStackTrace();
		}
		
		finally {
			// close jdbc objects
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
}
