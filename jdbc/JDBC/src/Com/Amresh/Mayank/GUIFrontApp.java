/*	Mini Project (uses all the 3 jdbc statements object)*/

package Com.Amresh.Mayank;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;

public class GUIFrontApp extends JFrame implements ActionListener {

	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	private JLabel lno,lname,ladd,lres;
	private JTextField tno,tname,tadd;
	private JButton bInsert;
	private Connection con;
	private PreparedStatement ps;
	
	public GUIFrontApp() {
		System.out.println("GUIFRONTApps:0-param constructor");
	// super class methods can be invoked in sub class directly
		setTitle("GUI FrontEndAPP");
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setSize(200,200);
		
		// add comps
		lno=new JLabel("Student number::");
		add(lno);
		
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("Student name::");
		add(lname);
		
		tname=new JTextField(10);
		add(tname);
		
		ladd=new JLabel("Student Address::");
		add(ladd);
		
		tadd=new JTextField(10);
		add(tadd);
		
		bInsert=new JButton("insert");
		add(bInsert);
		
		// register ActionEvent Listener on Buttom component
		bInsert.addActionListener(this);
		
		lres=new JLabel();
		lres.setForeground(Color.red);
		add(lres);
		
		// register window listner for the frame
		addWindowListener(new MyAdapter());
		
		// close frame window when(x) buttom is cicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set visible
		setVisible(true);
		makeConnection();
	}// cons
	
	private void makeConnection() {
		System.out.println("Make connection() method");
		try {
			// create jdbc connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCl","scott","tiger");
			
			// create prepared statement object
			ps=con.prepareStatement(INSERT_STUDENT_QUERY);
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	/*	catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	*/	catch(Exception e) {
			e.printStackTrace();
		}
	}//makeConnection
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("GUIFrontApp:actionPerformed(-)");
		int no=0;
		String name=null,addrs=null;
		int result=0;
		
		try {
			// read from data/text boxes data
			no=Integer.parseInt(tno.getText());
			name=tname.getText();
			addrs=tadd.getText();
			
			// set values to query params
			ps.setInt(1,no);
			ps.setString(2,name);
			ps.setString(3,addrs);
			
			// execute the query
			result=ps.executeUpdate();
			if(result==0)
				lres.setText("Student registration failed");
			else
				lres.setText("Student registration sucess");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}//actionPerofrmed(-)
	
	public static void main(String[] args) {
		System.out.println("Main method");
		
		new GUIFrontApp();
	}//main
	
	private class MyAdapter extends WindowAdapter{
		@Override
		
		public void windowClosing(WindowEvent e) {
			System.out.println("window closing(-)");
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException en) {
				en.printStackTrace();
			}
		}
	}



}
