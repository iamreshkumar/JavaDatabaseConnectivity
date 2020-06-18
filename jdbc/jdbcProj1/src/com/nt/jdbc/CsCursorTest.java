/*  create or replace procedure fetch_AllEmpDetails(initChars in varchar,details out sys_refcursor)as(Cursor datatype)
 	begin
 	open details for
 		select * from emp where ename like initChars;
 	end;
 */
package com.nt.jdbc;
import java.sql.*;
import java.util.*;
import oracle.jdbc.OracleTypes;

public class CsCursorTest {
	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter inital chars of Emp Name");
				initChars=sc.next().toUpperCase()+"%";
			}
		
			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create callable statement object
			if(con!=null)
				cs=con.prepareCall("{call fetch_AllEmpDetails(?,?}");
			if(cs!=null) {
					// register OUT params with jdbc types
				cs.registerOutParameter(2,OracleTypes.CURSOR);
			//set IN param value
			cs.setString(1,initChars);
			//execute pl/sql procedure
			cs.execute();
			//gather result from OUT param
			rs=(ResultSet)cs.getObject(2);
		}
			// process the resultSet
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3)+""+rs.getInt(4));
				}
				if(flag==false)
					System.out.println("No record founds");
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
