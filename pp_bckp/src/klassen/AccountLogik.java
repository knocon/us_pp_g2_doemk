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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.sql.DriverManager;

public class AccountLogik {
	static Connection conn = null;
	static ResultSet resultSet;
	static java.sql.Statement statement;
	static java.sql.Statement statement2;
	static LocalDate date;


	public AccountLogik() {
		dbconnection();
		//addAccount("test","pwpw","rolle");
		login("knocon","admin");
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
	
	public void addAccount(String name, String pw, String rolle, int id) {
		String que = "SELECT * FROM Person WHERE persId = '"
				+ id +"'";
		try {
			ResultSet result = statement.executeQuery(que);
			if(result.next()==false) {
				Alert abfrage = new Alert(AlertType.ERROR,"Kein Nutzer mit dieser ID vorhanden", ButtonType.OK);
				abfrage.show();
			}
			else {
				String quer = "SELECT * FROM Account WHERE username = '"
						+ name +"'";
				try {
					ResultSet res = statement.executeQuery(quer);
					if(res.next()==true) {
						Alert abfrage = new Alert(AlertType.ERROR,"Nutzername bereits vergeben.", ButtonType.OK);
						abfrage.show();
					}
					else {
						String query = "INSERT INTO Account(username,pw,rolle,persId) VALUES("
								+ "'"+name+"',"
								+ "'"+pw+"',"
								+ "'"+rolle+"',"
								+ "'"+id+"')";

						try {
							statement.executeUpdate(query);
							Alert abfrage = new Alert(AlertType.INFORMATION,"Account wurde angelegt.", ButtonType.OK);
							abfrage.show();
							System.out.println("Query ausgefuehrt.");
						} catch (SQLException e) {
							Alert abfrage = new Alert(AlertType.ERROR,"Account konnte nicht angelegt werden.", ButtonType.OK);
							abfrage.show();
							e.printStackTrace();
						}
					}
				} catch (SQLException e1) {
					Alert abfrage = new Alert(AlertType.ERROR,"SQL ERROR", ButtonType.OK);
					abfrage.show();
					e1.printStackTrace();
				}
			}
		}
		catch(Exception e) {
			Alert abfrage = new Alert(AlertType.ERROR,"SQL ERROR", ButtonType.OK);
			abfrage.show();
			e.printStackTrace();
		}
		
	}
	
	public void aendereRolle(Account a) {
		String query = "UPDATE Account SET rolle = 'Verwalter' WHERE username = '"
					+ a.getName() + "'";
		if(a.getRolle().equals("Verwalter")) {
			query = "UPDATE Account SET rolle = 'Benutzer' WHERE username = '"
					+ a.getName() + "'";
		}
		System.out.println(query);
		try {
			statement.executeUpdate(query);
			Alert abfrage = new Alert(AlertType.INFORMATION,"Account Rolle wurde geändert.", ButtonType.OK);
			abfrage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
			Alert abfrage = new Alert(AlertType.ERROR,"SQL ERROR: Account Rolle konnte nicht geändert werden.", ButtonType.OK);
			abfrage.show();
		}
	}
	
	public void loescheAccount(Account a) {
		String nutzername = Controller.getEingeloggterAccountName();
		if(a.getName().equals(nutzername)) {
			Alert abfrage = new Alert(AlertType.ERROR,"Eingeloggter Account kann nicht gelöscht werden.", ButtonType.OK);
			abfrage.show();
		}
		else {
			String query = "DELETE FROM Account WHERE username = '"
					+ a.getName() +"'";
			try {
				statement.executeUpdate(query);
				Alert abfrage = new Alert(AlertType.INFORMATION,"Account wurde gelöscht.", ButtonType.OK);
				abfrage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
				Alert abfrage = new Alert(AlertType.ERROR,"SQL ERROR: Account konnte nicht gelöscht werden.", ButtonType.OK);
				abfrage.show();
			}
		}
	}
	
	public Account login(String name,String pw) {
		Account acc = null;
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM Account NATURAL JOIN Person "
					+ "WHERE username = '"+name+"' AND pw = '"+ pw +"'" );
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				System.out.println("Logged in");
				acc = new Account(
						result.getString("username"),
						result.getString("pw"),
						result.getString("rolle"),
						result.getString("vorname"),
						result.getString("nachname"),
						result.getString("email"));
				result.close();
				ps.close();
			}
			else {
				System.out.println("error");
			}
			return acc;
		} catch(SQLException ex) {
			ex.printStackTrace();
			return acc;
		}
		
	}
}