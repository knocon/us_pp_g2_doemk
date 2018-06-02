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
	public ResultSet ladeAllePersonen() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Person"); 
			return resultSet;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addPerson(  String vorname, String nachname, String typ, String strasse, String hausnummer,
			String stadt, String telefon, String email) {
	
		String query = "INSERT INTO PERSON(  date,vorname, nachname,  typ, strasse, hausnummer, stadt,  telefon, email) VALUES("
				
				+ "'"+date.now()+"',"
				+ "'"+vorname+"',"
				+ "'"+nachname+"',"
				+ "'"+typ+"',"
				+ "'"+strasse+"',"
				+ "'"+hausnummer+"',"
				+ "'"+stadt+"',"
				+ "'"+telefon+"',"
				+ "'"+email+"')";
		System.out.println(query);
		
		try {
			statement.executeUpdate(query);
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	 public ResultSet updatePerson(String parameter, String value, int id){
    	String query = "UPDATE  Person SET " + parameter +  " = '" + value + "' WHERE persId="+ id;
		 try {
    		statement.executeUpdate(query);
				
		 } catch (SQLException e) {
				e.printStackTrace();
    	}
		 return null;
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
			Person p = new  Person(rs.getInt("persId"), rs.getDate("date"), rs.getString("vorname"),rs.getString("nachname"),rs.getString("typ"), rs.getString("strasse"), rs.getString("hausnummer"), rs.getString("stadt"), rs.getString("telefon"), rs.getString("email"));
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
	 /*public void exportPDF( int id) {
		 
		 Document name = new Document();
         PdfWriter.getInstance((com.itextpdf.text.Document) name, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
         ((PdfWriter) name).open();            
         //we have four columns in our table
         PdfPTable RechnungT = new PdfPTable(10);
         //create a cell object
         PdfPCell table_cell;
		 
         while (resultSet.next()) {    
        	 if(resultSet.getRowId(id).equals(id)) {
             String dept_id = resultSet.getString("Rechnung_rechId");
             table_cell=new PdfPCell(new Phrase("Rechnung_id:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_id));
             RechnungT.addCell(table_cell);
             
             String dept_rechnungsId = resultSet.getString("Rechnung_rechnungsDatum");
             table_cell=new PdfPCell(new Phrase("Rechnung_Datum:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_rechnungsId));
             RechnungT.addCell(table_cell);
             
             String dept_rechnungsName = resultSet.getString("Rechnung_rechnungsName");
             table_cell=new PdfPCell(new Phrase("Rechnung_Name:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_rechnungsName));
             RechnungT.addCell(table_cell);
             
             String dept_auftraggeber = resultSet.getString("Rechnung_auftraggeber");
             table_cell=new PdfPCell(new Phrase("Rechnung_Auftraggeber:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_auftraggeber));
             RechnungT.addCell(table_cell);
             
             String dept_ansprechpartner = resultSet.getString("Rechnung_ansprechpartner");
             table_cell=new PdfPCell(new Phrase("Rechnung_Ansprechpartner:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_ansprechpartner));
             RechnungT.addCell(table_cell);
            
             String dept_kassenId = resultSet.getString("Rechnung_kassenId");
             table_cell=new PdfPCell(new Phrase("Rechnung_Kasse:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_kassenId));
             RechnungT.addCell(table_cell);
             
             String dept_topfId = resultSet.getString("Rechnung_topfId");
             table_cell=new PdfPCell(new Phrase("Rechnung_Topf:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_topfId));
             RechnungT.addCell(table_cell);
             
             String dept_art = resultSet.getString("Rechnung_art");
             table_cell=new PdfPCell(new Phrase("Rechnung_Art:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_art));
             RechnungT.addCell(table_cell);
             
             String dept_kontoId = resultSet.getString("Rechnung_kontoId");
             table_cell=new PdfPCell(new Phrase("Rechnung_Konto:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_kontoId));
             RechnungT.addCell(table_cell);
             
             String dept_betrag = resultSet.getString("Rechnung_betrag");
             table_cell=new PdfPCell(new Phrase("Rechnung_Betrag:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_betrag));
             RechnungT.addCell(table_cell);
             
             String dept_status = resultSet.getString("Rechnung_status");
             table_cell=new PdfPCell(new Phrase("Rechnung_Status:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_status));
             RechnungT.addCell(table_cell);
             
             String dept_statusZeitstempel = resultSet.getString("Rechnung_statusZeitstempel");
             table_cell=new PdfPCell(new Phrase("Rechnung_Zeitstempel:"));
             RechnungT.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(dept_statusZeitstempel));
             RechnungT.addCell(table_cell);
             
             
        	 }
         
         }
         
         name.add(RechnungT);
         name.close();
	 }*/
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
    
    public static ResultSet filterByParameter( String parameter, String value, String name){
    	try {
			resultSet =  statement.executeQuery("SELECT * FROM "+ name
											+ " where " + parameter +  " = "
											+ "'" + value + "'");
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    
	
	
	
}