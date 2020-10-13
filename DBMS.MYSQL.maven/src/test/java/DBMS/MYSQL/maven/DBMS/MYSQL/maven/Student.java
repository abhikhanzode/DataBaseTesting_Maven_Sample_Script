package DBMS.MYSQL.maven.DBMS.MYSQL.maven;
import java.sql.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class Student {
	public Connection conn;
	public Statement state;
	public ResultSet rs;


	@BeforeTest()
	public void setUP_Database() {
		String DB_url = "jdbc:mysql://localhost:3306/schooldata1";
		String user ="root";
		String pass ="root123";
		conn =null; 
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("Connecting to Database.....");
			conn = DriverManager.getConnection(DB_url,user, pass); 
			if (conn!=null) {
				System.out.println("Connection is Established.....");
			}
		}
		catch(SQLException ex) { 
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex) { 
			ex.printStackTrace();
		}
	}
	@Test(priority = 1)
	public void InserData() {

		try {
			String SqlCmd = "insert into School_details(Student_Name,STD,blood_group,CreatedOn)"
					+"values('sneha2',12,'B',now())";
			
			PreparedStatement pstmt = conn.prepareStatement(SqlCmd);
			
			int rowAffected = pstmt.executeUpdate();
			if (rowAffected ==1) {
				System.out.println("Data is Inserted");
			}
		}
		catch (SQLException ex){
			ex.printStackTrace();			
		}

	}
		

	@Test(priority = 3)
	public void ViewData() {
		try {
			String viewQuery = "select * from School_details";
			state = conn.createStatement();
			rs = state.executeQuery(viewQuery);
			while(rs.next()) {
				int stdid = rs.getInt("ID");
				String StudentName = rs.getString("Student_Name");
				int SSTD = rs.getInt("STD");
				String BG = rs.getString("blood_group");
				String co = rs.getString("CreatedOn");
				System.out.println(stdid +"\t  "+"|" + StudentName+"\t  "+"|" +SSTD +"\t  "+"|" + BG+"\t  "+"|" +co);

			}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() {
		if (conn != null) {
			try {
				System.out.println("Closing DB Connection.....");
				conn.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}

