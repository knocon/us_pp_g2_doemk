package klassen;

import java.beans.Statement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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

public class BauteileLogik {
	static Connection conn = null;
	static ResultSet resultSet;
	static java.sql.Statement statement;
	static java.sql.Statement statement2;
	static LocalDate date;
	static ObservableList<Bauteil> listBauteil;
	
	/*
	 * kategorie spalte muss erstellt werden
	 * kategorie tabelle muss erstellt werden
	 */
	
	public BauteileLogik(){
		dbconnection();
	}

	public Connection dbconnection() {

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
	 * Bauteile hinzufuegen, loeschen,bearbeiten
	 */

	public void addBauteil(Bauteil b) {
		String query = "INSERT INTO Bauteil(name,link,epreis,bestandLager,bestandBestellt,bestandGeplant) VALUES("

				+ "'" + b.getName() + "'," + "'" + b.getLink() + "'," + "'" + b.getEpreis() + "'," + "'"
				+ b.getBestandLager() + "'," + "'" + b.getBestandBestellt() + "'," + "'" + b.getBestandGeplant() + "')";

		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteBauteil(int id) {
		String query = "DELETE FROM Bauteil WHERE teilId =" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBauteil(String parameter, String value, int id) {
		String query = "UPDATE  Bauteil SET " + parameter + " = '" + value + "' WHERE teilId=" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("edit ausgefuehrt");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Kategorie anlegen,loeschen,bearbeiten
	 */

	public void addKategorie(Kategorie k) {
		String query = "INSERT INTO Kategorie(name) VALUES" + "("+ "'" + k.getName() + "')" ;

		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void deleteKategorie(int id) {
		String query = "DELETE FROM Kategorie WHERE kId =" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void renameKategorie(String value, int id) {
		String query = "UPDATE  Kategorie SET name = '" + value + "' WHERE kId=" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("edit ausgefuehrt");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Bestand inkrementieren, dekrementieren
	 * Beim dekrementieren das Objekt in Warenkorb laden
	 */
	
	public static void inkrementLager(int id) {
		String query = "UPDATE Bauteil SET bestandLager = bestandLager + 1 WHERE teilId=" + id;
		try {
			statement.executeUpdate(query);
			System.out.println(id+" inkrementiert");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inkrementBestellt(int id) {
		String query = "UPDATE Bauteil SET bestandBestellt= bestandBestellt + 1 WHERE teilId=" + id;
		try {
			statement.executeUpdate(query);
			System.out.println(id+" inkrementiert");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inkrementGeplant(int id) {
		String query = "UPDATE Bauteil SET bestandGeplant = bestandGeplant + 1 WHERE teilId=" + id;
		try {
			statement.executeUpdate(query);
			System.out.println(id+" inkrementiert");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * dekrementBestellt und dekrementGeplant sollen nur vom admin kontrollierbar sein
	 */
	
	public void dekrementBestellt(int id){
		String query = "UPDATE Bauteil SET bestandBestellt = bestandBestellt - 1 WHERE teilId =" + id;
		try{
			statement.executeUpdate(query);
			System.out.println(id+"dekrementiert");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void dekrementGeplant(int id){
		String query = "UPDATE Bauteil SET bestandGeplant = bestandGeplant -1 WHERE teilId =" + id;
		try{
			statement.executeUpdate(query);
			System.out.println(id+"dekrementiert");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * dekrementieren koennen admin und benutzer
	 * datum muss gefixed werden
	 */
	
	public static void dekrementLager(Bauteil b){
		String timeStamp = new SimpleDateFormat("dd.MM").format(new java.util.Date());
		String query = "UPDATE Bauteil SET bestandLager = bestandLager -1 WHERE teilId =" + b.getTeilId();
		String query2 = "INSERT INTO Warenkorb(bauteilId,preis) VALUES("
				+b.getTeilId() + "," + "" + b.getEpreis() + ")";
		String query3 = "INSERT INTO BauteilRechnung(datum,summe) VALUES("+timeStamp+",(SELECT sum(preis) FROM Warenkorb GROUP BY preis))";
		String query4 = "DELETE FROM Warenkorb";
		try {
			statement.executeUpdate(query);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.executeUpdate(query4);
		}catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	
}
