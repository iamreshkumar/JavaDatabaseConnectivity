package com.nt.jdbc;
import java.io.*;
import java.sql.*;
import java.util.*;


public class PhotoRetrive {
	 public static void main(String[] args) {
		
	
	Scanner sc=null;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	InputStream is=null;
	OutputStream os=null;
	int no=0;
	byte[] buffer=null;
	int bytesRead=0;
	
	try {
		 sc=new Scanner(System.in);
		 if(sc!=null)
			 System.out.println("Enter employee no:");
		 no=sc.nextInt();
	
		 // register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 
		 // Eastablish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		 
		 // Create prepared stmt obj
		 if(con!=null)
			 ps=con.prepareStatement("SELECT * FROM AMRESH WHERE NO=?");
		 // set param values
		 	if(ps!=null) {
		 		ps.setInt(1,no);
		 //Execute the SQL query
		 	 rs=ps.executeQuery();
		 	}
		 // process the result set (read BLOB value)
		 	if(rs.next()) {
		 		is=rs.getBinaryStream(4);
		 	}
		 // creat o/p stream for destination file
		 	os= new FileOutputStream("F:\\Memories\\oppo\\photo.jpg");
		 // Write buffer based logic to copy file content
		 	buffer =new byte[9096];
		 	while((bytesRead=is.read(buffer))!=-1) {
		 		os.write(buffer,0,bytesRead);
		 	}
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

