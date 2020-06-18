package com.nt.jdbc;
import java.io.*;
import java.sql.*;
import java.util.*;

public class CLOBInsert {
	public static void main(String[] args) {
		
	 int no=0;
	 String name=null;
	 String addrs=null;
	 String resumePath=null;
	 Scanner sc=null;
	 Connection con=null;
	 PreparedStatement ps=null;
	 Reader reader=null;
	 File file=null;
	 int result=0;
	 
	 try {
		 sc=new Scanner(System.in);
		 if(sc!=null)
			 System.out.println("Enter no:");
		 no=sc.nextInt();
		 System.out.println("Enter name:");
		 name=sc.next();
		 System.out.println("ENter addrs::");
		 addrs=sc.next();
		 System.out.println("Enter the resume path:");
		 resumePath=sc.next();
		 
		 // Register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 
		 // Eastablish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		 
		 // create prepared stmt obj
		 if(con!=null)
			 ps=con.prepareStatement("INSERT INTO AMRESH VALUES(?,?,?,?)");
		 
		 // create Reader object to hold resume value(CLOB value)
		 file=new File(resumePath);
		 reader=new FileReader(file);
		 
		 //set values to query param values
		 if(ps!=null) {
			 ps.setInt(1,no);
			 ps.setString(2, name);
			 ps.setString(3, addrs);
			 ps.setCharacterStream(4,reader,(int)file.length());
		 }
		 // execute the query
		 if(ps!=null)
			 result=ps.executeUpdate();
		 //Process the result
		 if(result==0)
			 System.out.println("Recordd Not Inserted");
		 else
			 System.out.println("Record Inserted");
		 
	 }
	 catch(SQLException se) {
		 System.out.println("Record Insertion filed");
		 se.printStackTrace();
	 }
	 catch(ClassNotFoundException cnf) {
		 System.out.println("Record Insertion failled");
		 cnf.printStackTrace();
	 }
	 catch(Exception e) {
		 System.out.println("Record Insertion falied ");
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
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 try {
			 if(reader!=null)
				 reader.close();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	}
}
