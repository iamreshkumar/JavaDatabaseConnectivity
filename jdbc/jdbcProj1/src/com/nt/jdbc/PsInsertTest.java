package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {
	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		String query=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null;
		String addrs=null;
		int result=0;
		try {
			// Read inputs
			sc=new Scanner(System.in);
			System.out.println("Enter student count");
			if(sc!=null)
				count=sc.nextInt();
			//Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Eastblish the connetcion
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			//prepare sql query
			query="insert into student values(?,?,?)";
			
			if(con!=null)
				ps=con.prepareStatement(query);
			//read multiple set of input values from end user
			if(sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter"+i+"Student details");
					System.out.println("Enter number");
					no=sc.nextInt();
					System.out.println("Enter Name");
					name=sc.next();
					System.out.println("Enter address");
					addrs=sc.next();
					
				// Set the input values read from enduserr to query params
					ps.setInt(1,no);
					ps.setString(2,name);
					ps.setString(3,addrs);
					
				// Exceute the query
					result=ps.executeUpdate();
					if(result==0)
						System.out.println(i+"Student details are not inserted");
					else
						System.out.println(i+"Student details ares inseted");
				}// for
			}//if
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
			// close all the jdbc objs
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
		}// finaly
	}//main
}//class
