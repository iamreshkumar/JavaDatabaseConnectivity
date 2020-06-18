/* Write a jdbc app that gives emp,name,salary,desg based on the given empno by
  					calling PL/ SQL function of db s/w
  					
  	create or replace function FX_GET_EMP_DETAILS(no in number,name out varchar,salary out number)
  	as
  	desg varchar(5);
  	begin
  	select name,sal,job into name,salary,desg from emp where empno=no;
  	return desg;
  	end;
  	
  	*/

package com.nt.jdbc;
import java.sql.*;
import java.util.*;

public class CsFxTest {
 public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	int no=0;
	String query=null;
	CallableStatement cs=null;
	int salary=0;
	String desg=null,name=null;
	
	
	try {
		sc=new Scanner(System.in);
		if(sc!=null) {
			System.out.println("Enter emp no:");
			no=sc.nextInt();
		}
	// register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
	//Establish the connection 
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		
	// prepare query
		query="{?=call FX_GET_EMP_DETAILS(?,?,?,?)}";
	// create callable stmt 
		if(con!=null) {
			cs=con.prepareCall(query);
		}
		if(cs!=null) {
			// register return parameters ,out params with jdbc types
			cs.registerOutParameter(1,Types.VARCHAR);
			cs.registerOutParameter(2,Types.VARCHAR);
			cs.registerOutParameter(3,Types.INTEGER);
			
			// set values in param 
			cs.setInt(2,no);
			// call PL/SQL function
			cs.execute();
			//gather result from return,out params
			desg=cs.getString(1);
			name=cs.getString(3);
			salary=cs.getInt(4);
			
			System.out.println("Emp name:"+name);
			System.out.println("Emp salary:"+salary);
			System.out.println("Emp Desg:"+desg);
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
