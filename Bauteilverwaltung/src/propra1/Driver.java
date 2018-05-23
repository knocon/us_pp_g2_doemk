package propra1;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
	
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bauteilverwaltung?useSSL=false", "root", "fenerbahce34");
			
					Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from bauverwaltung");
			
			while(myRs.next()) {
				System.out.println(myRs.getString("name")+ ", " + myRs.getString("link")+", " + myRs.getDouble("einzelpreis")+ " € , " + myRs.getInt("menge")
				+ ", " + myRs.getString("lagerort"));
			}
		}
		catch (Exception exc) {
			
			exc.printStackTrace();
		}

	}

}
