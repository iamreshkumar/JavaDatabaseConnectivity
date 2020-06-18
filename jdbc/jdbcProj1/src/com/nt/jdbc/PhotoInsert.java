package com.nt.jdbc;
import java.io.*;
import java.sql.*;
import java.util.*;

public class PhotoInsert{
	
	public static void main(String[] args) {
		
		Scanner sc=null;
		int no=0;
		String name=null,photoPath=null;
		float salary=0.0f;
		File file=null;
		InputStream is=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Number:");
				no=sc.nextInt();
				System.out.println("Enter name:");
				name=sc.next();
				System.out.println("Enter salary:");
				salary=sc.nextFloat();
				System.out.println("Enter the photo path");
				photoPath=sc.next();
			}
		// create InputStream by locating file based on photo path
			file =new File(photoPath);
			is=new FileInputStream(file);
			
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		
		// Create prepared statement object
		  if(con!=null)
			  ps=con.prepareStatement("insert into amresh values(?,?,?,?)");
		  //set values to query param values
		  if(ps!=null) {
			  ps.setInt(1,no);
			  ps.setString(2,name);
			  ps.setFloat(3,salary);
			  ps.setBinaryStream(4,is,file.length());
		  }
		  // Execute the sql query
		  if(ps!=null) {
			 result=ps.executeUpdate();
			
			// process the result set
			 if(result==0) 
				 System.out.println("Record not inserted");
			 else
				 System.out.println("Record inserted");
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
