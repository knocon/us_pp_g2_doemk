package klassen;

import java.beans.Statement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.DriverManager;

public class AccountLogik {
	static Connection conn = null;
	static ResultSet resultSet;
	static java.sql.Statement statement;
	static java.sql.Statement statement2;
	static LocalDate date;


	public static void main(String[] args) {
		dbconnection();
		//addAccount("test","pwpw","rolle");
		login("test","pwpw");
	}

	public static Connection dbconnection() {

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database/db.db");
			statement = conn.createStatement();
			System.out.println("funkt");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Registration
	 */
	
	public static void addAccount(String name, String pw, String rolle) {
		String query = "INSERT INTO Account(name,pw,rolle) VALUES("
				+ "'"+name+"',"
				+ "'"+pw+"',"
				+ "'"+rolle+"')";

		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void login(String name,String pw) {
		
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("SELECT name,pw FROM Account WHERE name = '"+name+"' AND pw = '"+ pw +"'" );
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				System.out.println("Logged in");
			}
			else {
				System.out.println("error");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}

		
	}
}