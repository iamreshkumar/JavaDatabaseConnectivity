package com.nt.jdbc;
import java.sql.*;
public class ScrollableTest {
	public static void main(String[] args) {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			
			// create PreparedStatement object with type,mode values
			if(con!=null) {
				ps=con.prepareStatement("SELECT * FROM STUDENT",
														ResultSet.TYPE_SCROLL_SENSITIVE,
														ResultSet.CONCUR_READ_ONLY);
			}
			// create ResultSet object
			if(ps!=null)
				rs=ps.executeQuery("SELECT * FROM EMP");
			
			// display records (top-buttom)
			if(rs!=null) {
				System.out.println("Top-------Buttom");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			}
			System.out.println("Buttom ----Top");
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			}
			
			System.out.println("....................");
			// first record
			rs.first();
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			
			rs.last();
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			
			// 3rd record from top
			rs.absolute(3);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			
			// 3rd record from buttom
			rs.absolute(-3);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			
			//relative
			rs.relative(2);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			
			//relative
			rs.relative(-4);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
			
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
