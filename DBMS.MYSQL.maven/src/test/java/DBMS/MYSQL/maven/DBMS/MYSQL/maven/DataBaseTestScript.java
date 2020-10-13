package DBMS.MYSQL.maven.DBMS.MYSQL.maven;

import java.sql.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/* Preconditions:
 * Java db connectivity (JDBC) is a JAVA API used to connect and interact with DB
 * How To connect:
 * 1. download MySQL connector jar
 * 2. Load & Registration driver
 * 3. Create Statement Object
 * 4. Execute the statement
 * 5. Closing connection 
 */


public class DataBaseTestScript {
	public Connection conn;
	public Statement state;
	public ResultSet rs;


	@BeforeTest()
	public void setUP_Database() {
		String DB_url = "jdbc:mysql://localhost:3306/compabc";
		String user ="root";
		String pass ="root123";
		conn =null; //1
		try {
			Class.forName("com.mysql.jdbc.Driver"); // define driver
			System.out.println("Connecting to Database.....");
			conn = DriverManager.getConnection(DB_url,user, pass); // define url
			if (conn!=null) {
				System.out.println("Connection is Established.....");
			}
		}
		catch(SQLException ex) { // handle sql exception
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex) { // handle class exception
			ex.printStackTrace();
		}
	}
	@Test(priority = 1)
	public void InserData() {

		try {
			String SqlCmd = "insert into empinfo(name,city,email,mobile,salary)"
					+"values('harry','dubai','harry@gmail.com','8987677867',245000)";
			
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
	
	@Test(priority = 2)
	public void UpdateData() {
		try {
			String SqlCmd = "UPDATE empinfo SET name = 'kumar' WHERE id = 2 ";
					
			
			PreparedStatement pstmt = conn.prepareStatement(SqlCmd);
			
			int rowAffected = pstmt.executeUpdate();
			if (rowAffected ==1) {
				System.out.println(String.format("Row Affected is %d", rowAffected));
			}
		}
		catch (SQLException ex){ 
			ex.printStackTrace();			
		}
	}
	
	@Test(priority = 2)
	public void DeleteData() {
		try {
			String SqlCmd = "DELETE FROM empinfo WHERE id = 5 ";
					
			
			PreparedStatement pstmt = conn.prepareStatement(SqlCmd);
			
			int rowAffected = pstmt.executeUpdate();
			if (rowAffected ==1) {
				System.out.println("Record is Deleted");
			}
		}
		catch (SQLException ex){ 
			ex.printStackTrace();			
		}
	}

	@Test(priority = 3)
	public void ViewData() {
		try {
			String viewQuery = "select * from empinfo";
			state = conn.createStatement();
			rs = state.executeQuery(viewQuery);
			while(rs.next()) {
				int EmpID = rs.getInt("id");
				String EmpName = rs.getString("name");
				String EmpCity = rs.getString("city");
				String EmpEmail = rs.getString("email");
				String EmpMobile = rs.getString("mobile");
				Double EmpSalary = rs.getDouble("salary");
				System.out.println(EmpID +"\t" + EmpName+"\t"+EmpCity +"\t"+ EmpEmail+"\t"+EmpMobile+"\t"+EmpSalary);

			}
		}catch (SQLException e) {
			// TODO: handle exception
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
