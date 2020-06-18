// write a jdbc App that gather student details from end user an dinsert into student table.. 
package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class InsertTest {

	public static void main(String[] args){
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int no=0;
		String name=null,addrs=null;
		int result=0;
		
		try {
			// read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student no::");
				no=sc.nextInt();	// gives 101
				System.out.println("Enter name");
				name=sc.next();	// gives Amresh
				System.out.println("Enter address");
				addrs=sc.next();//gives hyd
			}// if
			
			// Convert input vvalues as required for the SQL query
			name= "'"+name+"'";
			addrs="'"+addrs+"'";
			
		// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
				
		// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create statement object
			if(con!=null)
				st=con.createStatement();
			// Prepare sQL query
			// formate>> insert into student values(1001,'amresh','hyd')
			
			String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+")";
			
			System.out.println(query);
			
			// send and execute SQL query in DB s/w
			
			if(st!=null)
				result=st.executeUpdate(query);
			// Process the result
			if(result==0)
				System.out.println("Record insreryion failed");
			else
				System.out.println(" Record inserted properly sucessed");
		}//try
		
		catch(SQLException se) { //known exception
			se.printStackTrace();
			System.out.println("REcord insertion faild");
		}
		catch(ClassNotFoundException cnf) { // known exception
			System.out.println("REcord insertion faild");
			cnf.printStackTrace();
		}
		catch(Exception e) { // unknown exception
			System.out.println("REcord insertion faild");
			e.printStackTrace();
		}
		
		finally {
			// close all jdbc object
			
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
				if(sc!=null)
					sc.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
