/*	Q.) Write a jdbc app that uses CallableStatement object to call PL/SQL procedure of authentication 
 
  	create or replace procedure Auth_pro(user in varchar,pass in varchar,result out varchar)
  		as cnt number(4);
  	begin
  	select count(*) into cnt from userlist where uname=user and pwd=pass;
  	if(cnt<>0)
  		result:='Valid credentials'
  	else
  		result:='InValid Credentials'
 	end;
 	
  
  
  */
package com.nt.jdbc;
import java.util.*;
import java.sql.*;

public class CsTest3 {
	public static void main(String[] args) {
		
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		CallableStatement cs=null;
		String result=null;
		
		try {
			// reads input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter user name:");
				user=sc.next();
				System.out.println("Enter password:");
				pass=sc.next();
			}
			
			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create callable statement object
			if(con!=null)
				cs=con.prepareCall("{call Auth_pro(?,?,?,?}");
			if(cs!=null) {
					// register OUT param with jdbc types
				cs.registerOutParameter(3,Types.VARCHAR);
				//Set IN param values
				cs.setString(1,user);
				cs.setString(2,pass);
				//execute PL/SQL procedure
				cs.execute();
				//gather results from OUT parameter
				result=cs.getString(3);
				System.out.println(result);
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
		
			// close jdbc object
			
			
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
