package com.nt.jdbc;

import java.sql.*;
import java.util.*;
public class LoginApp1 {

	public static void main(String[] args) {
		
		Scanner sc=null;
		String user =null;
		String pass =null;
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query =null;
		int count=0;
		
		try {
			// read inputs 
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter user Name");
				user=sc.nextLine();
				System.out.println("Enter password");
			
				pass=sc.nextLine();
				
				// register jdbc driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// Establish the connection 
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
				// Prepare sql query 
				query= "SELECT COUNT(*) FROM USERLIST WHERE USERNAME=? AND PWD=?";
				
				if(con!=null) {
					ps=con.prepareStatement(query);
					//set param values
					if(ps!=null) {
					ps.setString(1,user);
					ps.setString(2,pass);
					
				}
				// Send and execute the sql  query
				if(ps!=null)
					rs=ps.executeQuery();
				if(rs!=null) {
					if(rs.next())
					{
						count=rs.getInt(1);
						}
				}
				if(count==0)
					System.out.println("Invalid Credientals");
				else
					System.out.println("Valid Credentials");
			}
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
			// close JDBC objects
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}

















