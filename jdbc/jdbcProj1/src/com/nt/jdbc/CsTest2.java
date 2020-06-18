/*  create or replace procedure p_get_EmpDetails(no in number,name out varchar2,salary out number)as
 	begin
 	select ename,sal into name,salary from emp where sno=no;
 	end;
 	
 	*/
package com.nt.jdbc;
import java.sql.*;
import java.util.*;

public class CsTest2 {
 public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	int no=0;
	String query=null;
	CallableStatement cs=null;
	
	 try {
		 	// read inputs
		 sc=new Scanner(System.in);
		 System.out.println("Enter no:");
		 no=sc.nextInt();
		 
		 // register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 
		 // Establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		 
		 // prepare query
		 query="{call p_get_EmpDetails(?,?,?,?)}";
		 
		 // Create CallableStatement object
		 if(con!=null)
			 cs=con.prepareCall(query);
		 if(cs!=null) {
			  // register out params with jdbc types
			 cs.registerOutParameter(2,Types.VARCHAR);
			 cs.registerOutParameter(3,Types.VARCHAR);
			 cs.registerOutParameter(4,Types.INTEGER);
			 
			
			//set value to IN param
			 cs.setInt(1,no);
		//Call PL/SQL procedure
			 cs.execute();
		 // gather result from OUT params
			 System.out.println("EmP name::"+cs.getString(2));
			 System.out.println("Emp job::"+cs.getString(3));
			 System.out.println("Emp salary::"+cs.getInt(4));
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
