
package com.nt.jdbc;
import java.sql.*;

public class SensitiveTest {
	public static void main(String[] args) {
	
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		
		try {
			// register jdbc driver
			Class.forName("oracle.jdbc.OracleDriver");
			
			// Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create statement object
				if(con!=null)
					st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			// execute the query
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT");
			
			System.out.println("Modify data from SQL prompt");
		
			// Process the result
			if(rs!=null) {
				while(rs.next()) {
					if(cnt==0)
						Thread.sleep(6000); // during the sleep period modify db record table record
					rs.refreshRow();
			System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			cnt++;
				}
			}
		}
		catch(SQLException se) {  // known exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){ //known exception
			cnf.printStackTrace();
		}
		catch(Exception e) {		//unknown exception
			e.printStackTrace();
		}
		
		finally {
			// Close jdbc All objects
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
		} //finally
 }
}
