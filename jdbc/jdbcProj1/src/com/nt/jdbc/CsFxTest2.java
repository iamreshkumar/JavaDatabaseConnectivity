/* 
 	create or replace function GET_FX_STUDENT_FOR_DELETION(no in number,cnt out number)
 		return sys_refcursor(return type)
 	as
 	details sys_refcursor;
 	(local variable)
 	begin
 	open details for
 		select * from emp where sno=no;
 		delete from student where sno=no;
 		cnt:=SQL%ROWCOUNT;
 		return details;
 		end;
 		
  
 */
package com.nt.jdbc;
import java.sql.*;
import java.util.*;

import oracle.jdbc.OracleTypes;
public class CsFxTest2 {
public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	String query=null;
	CallableStatement cs=null;
	ResultSet rs=null;
	int result=0;
	int no=0;
	
	try {
		sc=new Scanner(System.in);
		if(sc!=null) {
			System.out.println("Enter student no:");
			no=sc.nextInt();
		}
		// register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		
		// prepare query
		query="{?=call GET_FX_STUDENT_FOR_DELETION(?,?)}";
		//create CallableStatement object'
		if (con!=null)
			cs=con.prepareCall(query);
		if(cs!=null) {
			// register OUT params with jdbc types
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			cs.registerOutParameter(2,Types.INTEGER);
			// set values to IN params
			cs.setInt(2,no);
			// call PL/SQL function
			cs.execute();
			//gather results
			rs=(ResultSet)cs.getObject(1);
			// process the resultset
			if(rs!=null) {
				if(rs.next()) {
					System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
				}
			}
			result=cs.getInt(3);
			if(result==0)
				System.out.println("Record not deleted");
			else
				System.out.println("Record deleted");
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
