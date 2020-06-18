/* create or replace procedure p_first_pro1(x in number,y out number)as
  begin
  y:=x*x;
  end;
  
  */
package com.nt.jdbc;
import java.util.*;
import java.sql.*;

public class SquareprogramUsingCs{
	public static void main(String[] args) {
		
		Scanner sc=null;
		int no=0;
		Connection con=null;
		String query=null;
		CallableStatement cs=null;
		int result=0;
		
		try {
			// read inputs
			sc=new Scanner(System.in);

		if(sc!=null) {
			System.out.println("Enter no:");
			no=sc.nextInt();
		}
		// register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// Eastablish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		
		// prepare SQL query callin PL/SQL procedure
		query="{call p_first_pro1(?,?)}";
		
		// create callable statement object
		if(con!=null)
			cs=con.prepareCall(query);
		if(cs!=null) {
				// registeer out parameters with jdbc types
			cs.registerOutParameter(2,Types.INTEGER);
			
			// set values to IN params
			cs.setInt(1, no);
			//execute PL/SQL procedure
			cs.execute();
		
			//gather result from OUT param
			result=cs.getInt(2);
			System.out.println("SQUARE value::"+result);
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
						
		}
		
	}
}











































