package com.nt.jdbc;
import java.sql.*;
public class ResultSetMetaApp {
  public static void main(String[] args) {
	
	  Connection con=null;
	  Statement st=null;
	  ResultSet rs=null;
	  
	  ResultSetMetaData rsmd=null;
	  int colCnt=0;
	  
	  try {
		  // register jdbc driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute query
			if(st!=null) 
				rs=st.executeQuery("SELECT * FROM STUDENT");
			if(rs!=null) {
		// create ResultSet MetaData object
				rsmd=rs.getMetaData();
		//get column count
				colCnt=rsmd.getColumnCount();
		//display colNames
				for(int i=1;i<=colCnt;i++) {
					System.out.println("column Lable::"+rsmd.getColumnLabel(i));
				}
				
				System.out.println();
		
				// display col types
				for(int i=1;i<=colCnt;i++) {
					System.out.println("Column Type::"+rsmd.getColumnTypeName(i));
				}
				
				System.out.println();
			// process the result Set 
				while(rs.next()) {
					for(int i=1;i<=colCnt;++i) {
						System.out.println("  "+rs.getString(i));
					}
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
