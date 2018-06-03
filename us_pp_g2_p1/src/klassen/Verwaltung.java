package klassen;

import java.beans.Statement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.Document;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Verwaltung {
	static Connection conn = null;
	static ResultSet resultSet;
	static java.sql.Statement statement;
	static LocalDate date;
	static ObservableList<Person> listPerson ;
	static ObservableList<Rechnung> listRechnung ;
	
	public Verwaltung() {
		dbconnection();
		//updateRechnung("rechnungsName", "nein!!", 3);
		//deleteRechnung(3);
		//addRechnung( "abc", "person1", "person2", 2, 2, "chg", 20.00, "fertig");
		//filterByParameter("vorname", "Daman", "Person");
		//addPerson( "Ömer", "abc", "Kunde", "abcstrasse","5","abc","01292929999","ömer@web.de");
		//deletePerson(4);
		//updatePerson("vorname","�mer",1);
	}
	
	public Connection dbconnection() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database/db.db");
			statement =  conn.createStatement();
			System.out.println("funkt");
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Person
	public ObservableList<Person> ladeAllePersonen() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Person"); 
			ObservableList<Person> ret = givePerson(resultSet);
			return ret;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addPerson(Person p) {
	
		String query = "INSERT INTO PERSON(  datum,vorname, nachname,  typ, strasse, hausnummer, stadt,  telefon, email) VALUES("
				
				+ "'"+LocalDate.now()+"',"
				+ "'"+p.getVorname()+"',"
				+ "'"+p.getNachname()+"',"
				+ "'"+p.getTyp()+"',"
				+ "'"+p.getStrasse()+"',"
				+ "'"+p.getHausnummer()+"',"
				+ "'"+p.getStadt()+"',"
				+ "'"+p.getTelefon()+"',"
				+ "'"+p.getEmail()+"')";
		System.out.println(query);
		
		try {
			statement.executeUpdate(query);
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	 public void updatePerson(String parameter, String value, int id){
    	String query = "UPDATE  Person SET " + parameter +  " = '" + value + "' WHERE persId="+ id;
		 try {
    		statement.executeUpdate(query);
				
		 } catch (SQLException e) {
				e.printStackTrace();
    	}
	 }
	 
	 public void deletePerson(int id) {
		 String query="DELETE FROM Person WHERE persId ="+ id;
		 try {
			  statement.executeUpdate(query);
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public ObservableList<Person> givePerson(ResultSet rs) throws SQLException {
		listPerson = FXCollections.observableArrayList();
		while (rs.next()) {
			Person p = new  Person(rs.getInt("persId"), rs.getString("vorname"),rs.getString("nachname"),rs.getString("typ"), rs.getString("strasse"), rs.getString("hausnummer"), rs.getString("stadt"), rs.getString("telefon"), rs.getString("email"));
			listPerson.add(p);
		}
		return listPerson;
	}
	 
	 //Finanzen
	 
	 public void addRechnung( String rechnungsName, String auftraggeber, String ansprechpartner,
				int kassenId, int topfId, String art, double betrag, String status) {
		
			String query = "INSERT INTO Rechnung( rechnungsDatum, rechnungsName, auftraggeber, ansprechpar"
					+ "tner, kassenId, topfId, art, betrag, status) VALUES("
					+ "'"+date.now()+"',"
					+ "'"+rechnungsName+"',"
					+ "'"+auftraggeber+"',"
					+ "'"+ansprechpartner+"',"
					+ "'"+kassenId+"',"
					+ "'"+topfId+"',"
					+ "'"+art+"',"
					+ "'"+betrag+"',"
					+ "'"+status+"')";
			System.out.println(query);
			try {
				statement.executeUpdate(query);
			}catch (SQLException e ) {
				e.printStackTrace();
			}
			
		}
	 
	 public ResultSet updateRechnung(String parameter, String value, int id){
	    	try {
				statement.executeUpdate("UPDATE  Rechnung SET " + parameter +  " = '" + value + "' WHERE rechId="+ id);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	 
	 public void deleteRechnung(int id) {
		 try {
			 statement.executeUpdate("DELETE FROM Rechnung WHERE rechId="+ id);
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public ObservableList giveRechnung(ResultSet rs) throws SQLException {
		 listRechnung = FXCollections.observableArrayList();
			while (rs.next()) {
			Rechnung r = new Rechnung( rs.getDate("rechnungsDatum"),rs.getString("rechnungsName"), rs.getString("auftaggeber"), rs.getString("ansprechpartner"), rs.getInt("kassenId"), rs.getInt("topfId"), rs.getString("art"), rs.getInt("kontoId"), rs.getDouble("betrag"), rs.getString("status"));
			listRechnung.add(r);
			}
			return listRechnung;
		}
	 
	 //Fertigung
	
	public int getIDofRow(String name) {
    	int id = -1;
    	try {
			resultSet =   ((java.sql.Statement) statement).executeQuery("select * from Auftrag"
											+ " where name = " 
											+ "'" + name + "'");
			if(resultSet.next()) {
				id = resultSet.getInt("idproduction");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return id;
    }
    
    public void updateParameterByID(String parameter, String value, int id){
    	String query = "UPDATE Auftrag SET " + parameter + " = '" + value + "' WHERE aufId = " + id;
    	try {
			((java.sql.Statement) statement).executeUpdate(query);
			//readDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public ObservableList<Person> filterByParameter( String parameter, String value, String tabellenname){
    	String query = "SELECT * FROM "+ tabellenname + " where " + parameter +  " = " + "'" + value + "'";
    	System.out.println(query);
    	try {
			resultSet =  statement.executeQuery("SELECT * FROM "+ tabellenname
											+ " where " + parameter +  " = "
											+ "'" + value + "'");
			ObservableList<Person> list = givePerson(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
}