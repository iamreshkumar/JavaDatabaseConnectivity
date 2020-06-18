package com.nt.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateRetrive {

	private static final String DATE_SELECT_QUERY="SELECT * FROM PERSON_TAB";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int id=0;
		String name=null;
		java.sql.Date sqdob=null,sqdoj=null;
		java.util.Date udob=null,udoj=null,udom=null;
		String sdob=null,sdoj=null;
		SimpleDateFormat sdf=null;
		boolean flag=false;
		
		try {
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create prepared stmt object
			if(con!=null)
				ps=con.prepareStatement(DATE_SELECT_QUERY);
		
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process  the result set
			if(rs!=null) {
				while (rs.next()) {
					flag=true;
					id=rs.getInt(1);
					name=rs.getString(2);
					sqdob=rs.getDate(3);
					sqdoj=rs.getDate(4);
					
					
				// convert java.sql.Date class object to java.util.Date
					// class objects
					udob=(java.util.Date)sqdob;
					udoj=(java.util.Date)sqdoj;
					
					
				// convert java.util.Date class objects to string date values
					sdf=new SimpleDateFormat("MM-dd-yyyy");
					sdob=sdf.format(udob);
					sdoj=sdf.format(udoj);
					
				
					System.out.println(id+"   "+name+"  "+sdob+"  "+sdoj);
				}
			}
			if(flag==false)
				System.out.println("Record not founds");
			}//try
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
						
		}
	
	}
}
