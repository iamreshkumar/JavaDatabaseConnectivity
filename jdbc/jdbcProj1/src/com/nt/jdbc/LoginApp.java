/* write a jdbc Login Application 
 	
 	check identitiy of user by verifying username and password (authantication) db table in oracle......
 	*/

package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class LoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		String username=null,pwd=null;
		Connection con=null;
		ResultSet rs=null;
		Statement st=null;
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
			// convert  input values as required for the SQL query
			username="'"+username+"'";
			pwd="'"+pwd+"'";
			
			// Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Eastablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create statement object
			if(con!=null)
				st=con.createStatement();
			// Preprare SQL query
			// select count(*) from userlist where username='raja' and pwd='rani';
			
			query="SELECT COUNT(*) FROM USERLIST WHERE USERNAME="+username+" and pwd="+pwd;
			
			System.out.println(query);
			// execute the query
			if(st!=null)
				rs=st.executeQuery(query);
			
			// Process the reultSet
			if(rs!=null) {
				if(rs.next())
					count=rs.getInt(1);
				System.out.println(count);
			}
			if(count==0)
				System.out.println(" Password Invalid or Credentials");
			else
				System.out.println("Passwor valid or Credentials");
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
				if(st!=null)
					st.close();
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