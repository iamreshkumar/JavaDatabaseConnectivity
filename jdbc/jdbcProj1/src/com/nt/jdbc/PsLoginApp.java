//  write a Login App. without having SQL injection problem...
// using PreparedStatement objects..

package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PsLoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		String username=null,pwd=null;
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query=null;
		int count=0;
		
		try {
			// Read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter user Name");
				username=sc.nextLine();
				System.out.println("Enter password");
				pwd=sc.nextLine();
			}
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// prepare sql quer
			query="SELECT COUNT(*) FROM USERLIST WHERE USERNAME=? AND PWD=?";
			if(con!=null)
			ps=con.prepareStatement(query);
			
			// set param values
			if(ps!=null) {
				ps.setString(1,username);
				ps.setString(2,pwd);
			}
			
			// send and execute the SQL query
			
			if(ps!=null)
				rs=ps.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					count=rs.getInt(1);
				}
			}
			if(count==0)
				System.out.println("inValid credientals");
			else
				System.out.println("Valid credientals");
		}//try
		catch(SQLException se) {		// for sql exception
			System.out.println(" Record insertion failed");
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){   // for class not found exception
			System.out.println(" Record insertion failed");
			cnf.printStackTrace();
			}
		catch(Exception e) {				// for anytype exception
			System.out.println(" Record insertion failed");
			e.printStackTrace();
			}
		
		finally {
			// close all jdbc obj
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
				}
			try {
				if(ps!=null)
					ps.close();
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
		}// finally
	}//main
}//class

	