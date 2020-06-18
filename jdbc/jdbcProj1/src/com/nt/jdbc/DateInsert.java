package com.nt.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateInsert {
	public static void main(String[] args) {
		
		Scanner sc=null;
		int no=0;
		String name=null,dob=null,doj=null;
		SimpleDateFormat sdf=null;
		java.util.Date udob=null;
		
		java.sql.Date sqdob=null,sqdoj=null;
		long ms=0;
		
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter no::");
				no=sc.nextInt();
				System.out.println("Enter name::");
				name=sc.next();
				System.out.println("Enter DOB(dd-MM-YYYY)");
				dob=sc.next();
				System.out.println("Enter DOJ(yyyy-MM-dd)");
				doj=sc.next();
			}
			// convert String date values to java.sql.Date class objects
			//FOR DOB
			sdf= new SimpleDateFormat("dd-MM-YYYY");
			if(sdf!=null)
				udob=sdf.parse(dob);
			if(udob!=null)
				ms=udob.getTime();
			sqdob=new java.sql.Date(ms);
			
			// FOR DOJ
			sqdoj=java.sql.Date.valueOf(doj);
			
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// Create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement("INSERT INTO PERSON_TAB VALUES(?,?,?,?)");
			//Set value query param values
			if(ps!=null) {
				ps.setInt(1,no);
				ps.setString(2,name);
				ps.setDate(3,sqdob);
				ps.setDate(4,sqdoj);
			}
				
		// execute the query
			
			if(ps!=null)
				result=ps.executeUpdate();
			//Process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
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
		
			// close jdbc object
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
			try {
				if(sc!=null) 
					sc.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
			
		}
		
	}
}
























