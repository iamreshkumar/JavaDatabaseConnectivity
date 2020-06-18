package com.nt.jdbc;
import java.sql.*;
public class PMDTest {
	
	public static void main(String[] args) {
		
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int count=0;
		
		try {
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create prepared statement
			if(con!=null)
				ps=con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?)");
			// CREATE parameterMetaData object
			if(ps!=null)
				pmd=ps.getParameterMetaData();
			// Display Various details about the params
			if(pmd!=null) {
				count=pmd.getParameterCount();
				for(int i=1;i<=count;++i) {
					System.out.println("Parameter number:"+i);
				//	System.out.println("Parameter mode::"+pmd.getParameterMode(i));
					System.out.println("Paramtetr type name::"+pmd.getParameterTypeName(i));
					System.out.println("Parameter is signed :"+pmd.isSigned(i));
					System.out.println("Paramter is nullable:"+pmd.isNullable(i));
					System.out.println("Parameter scale area:"+pmd.getScale(i));
					System.out.println("Paramter precision area:"+pmd.getPrecision(i));
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
