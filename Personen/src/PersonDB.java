
import java.sql.*;


public class PersonDB {

	static Connection connection = null;
	static Statement st = null;
	static ResultSet rs = null;
	static String user = "test";
	static String password = "test";
	static String url = "jdbc:mysql://localhost:3307/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
			creatConnection();
			
	}
	public static   Connection creatConnection() throws ClassNotFoundException {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("Connected");
			return connection;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
