package com.nt.jdbc;
import java.io.*;
import java.sql.*;
import java.util.*;
public class CLOBRetrive {
	public static void main(String[] args) {
		Scanner sc=null;
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Reader reader=null;
		 Writer writer=null;
		 int no=0;
		 char[]buffer=null;
		 int charsRead=0;
		 
		 try {
			 
			 sc=new Scanner(System.in);
			 if(sc!=null) {
			 System.out.println("Enter Employee no:");
			 no=sc.nextInt();
			 }
			 // Register jdbc driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			 // Eastablish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			 
			 // create prepared stmt obj
			 if(con!=null) 
			 ps=con.prepareStatement("SELECT * FROM AMRESH WHERE SNO=?");
			 // set param values
			 if(ps!=null) {
				 ps.setInt(1,no);
			 //execute the sql query
			 rs=ps.executeQuery();
			 }
			 //process the result set (Read BLOB values)
			 if(rs.next()) {
				 reader=rs.getCharacterStream(4);
			 }
			 // create o/p stream for dest file
			 writer=new FileWriter("E:\\downloads\\amresh_resume.doc");
			 
			 // write buffer based logic to copy file content
			 buffer=new char[2048];
			 while((charsRead=reader.read(buffer))!=-1) {
				 writer.write(buffer,0,charsRead);
			 }
			 System.out.println("CLOB retrived");
		
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
