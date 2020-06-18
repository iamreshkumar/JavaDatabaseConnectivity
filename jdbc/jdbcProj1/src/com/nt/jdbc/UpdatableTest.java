package com.nt.jdbc;
import java.sql.*;

public class UpdatableTest {
	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		
		try {
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create stmt object
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			//execute the query
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT");
			
			// REAd records
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
				}
		
		
		 //update opreation
		   rs.absolute(3);
		   rs.updateString(2,"amre");
		   rs.updateRow();
		   System.out.println("3rd record updated");
		   
		    rs.moveToInsertRow();
		    rs.updateInt(1,142);
		    rs.updateString(2,"raja");
		    rs.updateString(3,"hyd");
		    
		    rs.insertRow();
		    System.out.println("Record inserted");
		 
	/*
		// To delete record
			rs.absolute(2);
			rs.deleteRow();
			System.out.println("Record deleted");
	*/
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
			
		} //finally		
	}
}
