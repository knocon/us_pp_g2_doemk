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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

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

public class Verwaltung {
	static Connection conn = null;
	static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static PreparedStatement pst = null;
	static LocalDate date;
	static ObservableList<Person> listPerson;
	static ObservableList<Rechnung> listRechnung;
	static ObservableList<Bauteil> listBauteil;
	static ObservableList<Bauteil> listBauteilWk;
	static ObservableList options = FXCollections.observableArrayList();
	static ObservableList topf = FXCollections.observableArrayList();

	public Verwaltung() {
		dbconnection();

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

	// Person
	public ObservableList<Person> ladeAllePersonen() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");
			ObservableList<Person> ret = getPerson(resultSet);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addPerson(Person p) {
		String query = "INSERT INTO PERSON(  date,vorname, nachname,  typ, strasse, hausnummer, stadt,  telefon, email) VALUES("

				+ "'" + p.getDateLong() + "'," + "'" + p.getVorname() + "'," + "'" + p.getNachname() + "'," + "'"
				+ p.getTyp() + "'," + "'" + p.getStrasse() + "'," + "'" + p.getHausnummer() + "'," + "'" + p.getStadt()
				+ "'," + "'" + p.getTelefon() + "'," + "'" + p.getEmail() + "')";
		System.out.println(query);

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updatePerson(String parameter, String value, int id) {
		String query = "UPDATE  Person SET " + parameter + " = '" + value + "' WHERE persId=" + id;
		try {
			statement.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePerson(int id) {
		String query = "DELETE FROM Person WHERE persId =" + id;
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Person> getPerson(ResultSet rs) throws SQLException {
		listPerson = FXCollections.observableArrayList();
		while (rs.next()) {
			System.out.println(rs.getLong("date"));
			Person p = new Person(rs.getInt("persId"), rs.getLong("date"), rs.getString("vorname"),
					rs.getString("nachname"), rs.getString("typ"), rs.getString("strasse"), rs.getString("hausnummer"),
					rs.getString("stadt"), rs.getString("telefon"), rs.getString("email"));
			listPerson.add(p);
		}
		return listPerson;
	}

	// Finanzen
	public void addRechnung(Rechnung r) {
		String query = "INSERT INTO Rechnung( date, rechnungsName, auftraggeber, ansprechpartner, kassenId, topf, art, kontoId, betrag, status) VALUES(" 
	+ "'" + r.getDateLong() + "'," +  "'"
				 + r.getRechnungsName() + "'," + "'" + r.getAuftraggeber() + "'," + "'" + r.getAnsprechpartner() + "',"
				+ "'" + r.getKassenId() + "'," + "'" + r.getTopf() + "'," + "'" + r.getArt() + "'," + "'"
				+ r.getKontoId() + "'," + "'" + r.getBetrag()+ "'," + "'" + r.getStatus() + "')";
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet updateRechnung(String parameter, String value, int id) {
		try {
			statement.executeUpdate("UPDATE  Rechnung SET " + parameter + " = '" + value + "' WHERE rechId=" + id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteRechnung(int id) {
		try {
			statement.executeUpdate("DELETE FROM Rechnung WHERE rechId=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Rechnung> getRechnung(ResultSet rs) throws SQLException {
		try {
			listRechnung = FXCollections.observableArrayList();
			while (rs.next()) {
				Rechnung r = new Rechnung(rs.getInt("rechId"), rs.getLong("date"),
						
						rs.getString("rechnungsName"), rs.getString("auftraggeber"), rs.getString("ansprechpartner"),
						rs.getString("kassenId"), rs.getString("topf"), rs.getString("art"),
						rs.getString("kontoId"),rs.getString("betrag"), rs.getString("status") );
				listRechnung.add(r);
			}
			return listRechnung;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ObservableList<Rechnung> ladeAlleRechnungen() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Rechnung");
			ObservableList<Rechnung> rech = getRechnung(resultSet);
			return rech;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void statusRechnung(String status, int id) {
		try {
			statement.executeUpdate("UPDATE Rechnung Set status = '" + status + "' WHERE rechId=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	// Fertigung
	
	public int sucheAuftrag(Auftrag a) {
		
		return a.getAufId();
	}

	public  ObservableList<VTAuftragPerson> personenindieTabelle(Auftrag a) throws SQLException {
		ObservableList<VTAuftragPerson> listPersonen = FXCollections.observableArrayList();
		resultSet = statement
				.executeQuery("SELECT * FROM VTAuftragPerson where aufId = " + "'" + a.getAufId() + "'");
		listPersonen = getVTAPerson(resultSet);
		return listPersonen;
	}

	public boolean peronDa(String nachname, int persId) throws SQLException {
		boolean wert = false;
		ObservableList<Person> per = FXCollections.observableArrayList();
		resultSet = statement.executeQuery("SELECT * FROM Person");
		per = (ObservableList<Person>) getPerson(resultSet);
		Iterator<Person> it = per.iterator();
		while(it.hasNext()) {
			Person nue = it.next();
			if(nue.getPersId()==persId&&nue.getTyp().equals("Betreuer")) {
				wert = true;
			}
		}
		return wert;
	}

	public ObservableList<VTAuftragPerson> getVTAPerson(ResultSet rs) throws SQLException {
		try {
			ObservableList<VTAuftragPerson> per =FXCollections.observableArrayList(); ;
			while (rs.next()) {
				// System.out.println(rs.getLong("date"));
				VTAuftragPerson va= new VTAuftragPerson(rs.getInt("aufId"), rs.getString("aufName"), rs.getInt("perId"), rs.getString("perName"));
				per.add(va);
			}
			return per;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void auftragHinzufügen(int aufid,String titel, int id) throws SQLException {
		statement.executeUpdate("UPDATE  VTAuftrag SET aufId = '" + aufid + "' WHERE id = '" + id);
		statement.executeUpdate("UPDATE  VTAuftrag SET aufName = '" + titel + "' WHERE id = '" + id);
		
	}
	public void betreuerHinzufügen(String nachname, int perid, String titel, int aufid) {
		String query = "INSERT INTO VTAuftragPerson (aufId, aufName, perId, perName) VALUES("
				+"'"+aufid+"',"
				+"'"+titel+"',"
				+"'"+perid+"',"
				+"'"+nachname+"')";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		
	}

	public ObservableList<VTAuftragPerson> filterVTAPerson(String parameter, int value, String tabellenname) {
		String query = "SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ObservableList<VTAuftragPerson> list = getVTAPerson(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void datumEingabe(String time, Auftrag a, String status, int aufId) throws SQLException {
		statement.executeUpdate("UPDATE  DatumAuftrag SET " + status + " = '" + time + "' WHERE aufId=" + aufId);
	}

	public void addDatum(Auftrag a) {
		String query1= "INSERT INTO DatumAuftrag (angenommen, gefertig, kostenK, abgeholt, abgerechnet, warten, fertigungU, aufId) VALUES("
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+"'"+ a.getAufId()+"')";
		System.out.println(query1);
		try {
			
			statement.executeUpdate(query1);
			System.out.println("AufID" + a.getAufId() ); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<StatusAuftrag> getDatumAuftrag(ResultSet rs) throws SQLException {
		try {
			ArrayList<StatusAuftrag> listDatumAuftrag = new ArrayList<StatusAuftrag>();
			while (rs.next()) {
				// System.out.println(rs.getLong("date"));
				StatusAuftrag sa = new StatusAuftrag(rs.getString("angenommen"), rs.getString("gefertig"), rs.getString("kostenK"), rs.getString("abgeholt"), rs.getString("abgerechnet"), rs.getString("warten"), rs.getString("fertigungU"), rs.getInt("aufId"));
				listDatumAuftrag.add(sa);
			}
			return listDatumAuftrag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<StatusAuftrag> filterDatum(String parameter, int value, String tabellenname) {
		String query = "SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ArrayList<StatusAuftrag> list = (ArrayList<StatusAuftrag>) getDatumAuftrag(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	/*public void exportAuftrag(Auftrag a) {
		long time = System.currentTimeMillis();
		Rechnung r = new Rechnung(1,a.getTitel(), " ", " ",
				" ", " ", " ", "",a.getRkosten()," ");
		addRechnung(r);
	}*/

	public ObservableList<Auftrag> ladeAlleAuftraege() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Auftrag");
			ObservableList<Auftrag> ret = getAuftraege(resultSet);
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ObservableList<Auftrag> getAuftraege(ResultSet rs) throws SQLException {
		try {
			ObservableList<Auftrag> listAuftraege = FXCollections.observableArrayList();
			while (rs.next()) {
				// System.out.println(rs.getLong("date"));
				Auftrag a = new Auftrag(rs.getInt("aufId"), rs.getString("titel"), rs.getString("art"),
						rs.getString("dateiname"), rs.getString("rkosten"), rs.getString("pkosten"),
						rs.getLong("statusZeitstempel"), rs.getString("status"));
				listAuftraege.add(a);
			}
			return listAuftraege;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addAuftrag(Auftrag a) {
		String query = "INSERT INTO Auftrag(  titel,art, dateiname,  rkosten, statusZeitstempel, pkosten) VALUES("

				+ "'" + a.getTitel() + "'," + "'" + a.getArt() + "'," + "'" + a.getDateiname() + "'," + "'"
				+ a.getRkosten() + "'," + "'" + a.getDateLong() + "'," + "'" + a.getPkosten() + "')";
		System.out.println(query);

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet updateAuftrag(String parameter, String value, int id) {
		try {
			statement.executeUpdate("UPDATE  Auftrag SET " + parameter + " = '" + value + "' WHERE aufId=" + id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteAuftrag(int id) {
		try {
			statement.executeUpdate("DELETE FROM Auftrag WHERE aufId=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void statusAuftrag(String status, int id) {
		try {
			statement.executeUpdate("UPDATE Auftrag Set status = '" + status + "' WHERE aufId=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getIDofRow(String name) {
		int id = -1;
		try {
			resultSet = ((java.sql.Statement) statement)
					.executeQuery("select * from Auftrag" + " where name = " + "'" + name + "'");
			if (resultSet.next()) {
				id = resultSet.getInt("idproduction");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void updateParameterByID(String parameter, String value, int id) {
		String query = "UPDATE Auftrag SET " + parameter + " = '" + value + "' WHERE aufId = " + id;
		try {
			((java.sql.Statement) statement).executeUpdate(query);
			// readDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Person> filterByParameterPerson(String parameter, String value, String tabellenname) {
		String query = "SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ObservableList<Person> list = getPerson(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ObservableList<Auftrag> filterByParameterAuftrag(String parameter, String value, String tabellenname) {
		String query = "SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ObservableList<Auftrag> list = getAuftraege(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Rechnung
	
	public void datumEingabeRech(String time, Rechnung r, String status, int rechId) throws SQLException {
		statement.executeUpdate("UPDATE  DatumRechnung SET " + status + " = '" + time + "' WHERE rechId=" + rechId);
	}
	
	public void addDatumRech(Rechnung r) {
		String query1= "INSERT INTO DatumRechnung (bearbeitung, eingereicht, abgewickelt, ausstehend,rechId) VALUES("
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+ "'"+null+"',"
				+"'"+ r.getRechId()+"')";
		System.out.println(query1);
		try {
			
			statement.executeUpdate(query1);
			System.out.println("RechId" + r.getRechId() ); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public ArrayList<statusRechnung> getDatumRechnung(ResultSet rs) throws SQLException {
		try {
			ArrayList<statusRechnung> listDatumRechnung = new ArrayList<statusRechnung>();
			while (rs.next()) {
				// System.out.println(rs.getLong("date"));
				statusRechnung sa = new statusRechnung(rs.getString("bearbeitung"), rs.getString("eingereicht"), rs.getString("abgewickelt"), rs.getString("ausstehend"),  rs.getInt("rechId"));
				listDatumRechnung.add(sa);
			}
			return listDatumRechnung;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<statusRechnung> filterDatumRech(String parameter, int value, String tabellenname) {
		String query = "SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ArrayList<statusRechnung> list = (ArrayList<statusRechnung>) getDatumRechnung(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ObservableList<Rechnung> filterByParameterRechnung(String parameter, String value, String tabellenname) {
		String query = "SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ObservableList<Rechnung> list = getRechnung(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void namen(String parameter, String value, String tabellenname, Auftrag a) {
		String name = null;
		int id= 0;
		String rolle = null;
		
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM " + tabellenname + " where " + parameter + " = " + "'" + value + "'");
			ObservableList<Person> list = getPerson(resultSet);
			Iterator<Person> it = list.iterator();
			while ( it.hasNext()) {
				Person p = it.next();
				System.out.print(p.getVorname()+",");
				id = p.getPersId();
				rolle = p.getTyp();
			}
			String query = "INSERT INTO VTAuftragPersonen(aufId, persId, rolle) VALUES("
					+ "'"+a.getAufId()+"',"
					+"'"+id+"',"
					+"'"+rolle+"')";
			statement.executeUpdate(query);
			}
			
		 catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ZUWEISUNGSMETHODEN ( VERBINDUNGSTABELLEN )
	 * 
	 */

	public void personZuweisen(int auftragId, Person p, String rolle) {
		String query = "INSERT INTO VTAuftragPersonen(aufId,persId,rolle) VALUES("

				+ "'" + auftragId + "'," + "'" + p.getPersId() + "'," + "'" + rolle + "')";

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<Bauteil> getListBauteil() {
		return listBauteil;
	}

	public static void setListBauteil(ObservableList<Bauteil> listBauteil) {
		Verwaltung.listBauteil = listBauteil;
	}

	public void kasseTopfZuweisen(Kasse k, int topfId) {
		String query = "INSERT INTO VTKasseToepfe(kasId,topfId) VALUES("

				+ "'" + k.getKasId() + "'," + "'" + topfId + "')";

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void topfRechnungZuweisen(Topf t, int kassenId) {
		String query = "INSERT INTO VTTopfRechnungen(topfId,rechId) VALUES("

				+ "'" + t.getTopfId() + "'," + "'" + kassenId + "')";

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Toepfe erstellen,Loeschen, Kassen erstellen,loeschen
	 * 
	 */

	public static void addTopf(Topf t) {
		String query = "INSERT INTO Topf(name,sollBestand,istBestand) VALUES("

				+ "'" + t.getName() + "'," + "'" + t.getSollBestand() + "'," + "'" + t.getIstBestand() + "')";
		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static ObservableList fillComboBoxTopf() {
		try {
			String query = "SELECT name FROM Topf";
			pst = conn.prepareStatement(query);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				topf.add(resultSet.getString("name"));
				System.out.println(resultSet.getString("name"));
				
			}
			pst.close();
			resultSet.close();
		}catch(SQLException ex) {
			System.out.println("LOL");
		}
		return topf;
	}


	
	public static void deleteZugehoerigenTopf(String name) {
		 
		String query = "DELETE FROM Rechnung WHERE topf ='"+name+"'";
		try {
			statement.executeUpdate(query);
			System.out.println("zugehoerigkeitweg");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		}

		public static void deleteTopf(String name) {
		String query = "DELETE FROM Topf WHERE name ='" + name+"'";
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}

	public static void addKasse(Kasse k) {
		String query = "INSERT INTO Kasse(name,sollBestand,istBestand,art,zahlenfolge) VALUES("

				+ "'" + k.getName() + "'," + "'" + k.getSollBestand() + "'," + "'" + k.getIstBestand() + "'," + "'"
				+ k.getArt() + "'," + "'" + k.getZahlenfolge() + "')";
		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteKasse(int id) {
		String query = "DELETE FROM Kasse WHERE kasId =" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * lade Content in Tabelle bauteile
	 */
		public ObservableList<Bauteil> getBauteil(ResultSet rs) throws SQLException {
		try {
			listBauteil = FXCollections.observableArrayList();
			while (rs.next()) {
				Bauteil b = new Bauteil(rs.getString("name"), rs.getString("kate"),
						rs.getString("link"), rs.getDouble("epreis"), rs.getString("lagerort"),
						rs.getInt("bestandLager"), rs.getInt("bestandBestellt"), rs.getInt("bestandGeplant"), rs.getInt("teilId"));
				listBauteil.add(b);
			}
			return listBauteil;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public ObservableList<Bauteil> ladeAlleBauteile() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Bauteil");
			ObservableList<Bauteil> baut = getBauteil(resultSet);
			return baut;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void inkrementLager(int id) {
		String query = "UPDATE Bauteil SET bestandLager = bestandLager + 1 WHERE teilId=" + id;
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dekrementLager(Bauteil b){
		String query = "UPDATE Bauteil SET bestandLager = bestandLager -1 WHERE teilId =" + b.getTeilId();
		String query2 = "INSERT INTO Warenkorb(bauteilId, bauteilName, preis) VALUES('"+b.getTeilId() + "','" + b.getName() +"'," +b.getEpreis() + ")";
		//String query3 = "INSERT INTO BauteilRechnung(datum,summe) VALUES("+timeStamp+",(SELECT sum(preis) FROM Warenkorb GROUP BY preis))";
		//String query4 = "DELETE FROM Warenkorb";
		try {
			statement.executeUpdate(query);
			statement.executeUpdate(query2);
			//statement.executeUpdate(query3);
			//statement.executeUpdate(query4);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void confirmRechnung() {
		String timeStamp = new SimpleDateFormat("dd.MM").format(new java.util.Date());
		String query = "INSERT INTO BauteilRechnung(datum,summe,kaeuferId,status) VALUES("+timeStamp+",(SELECT sum(preis) FROM Warenkorb GROUP BY preis),'"+ Controller.getEingeloggterAccountName()+"','AUSSTEHEND')";
		String query2 = "DELETE FROM Warenkorb";
		
		try {
			statement.executeUpdate(query);
			statement.executeUpdate(query2);
			
		}catch(SQLException e) {
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
	
	public static void deleteBauteil(int id) {
		String query = "DELETE FROM Bauteil WHERE teilId =" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteWarenkorb(int id) {
		String query = "DELETE FROM Warenkorb WHERE id =" + id;
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addBauteil(Bauteil b) {
		String query = "INSERT INTO Bauteil(name,kate,link,lagerort,epreis,bestandLager,bestandBestellt,bestandGeplant) VALUES("

				+ "'" + b.getName() + "'," + "'" + b.getKate() + "'," + "'" + b.getLink() + "'," + "'" + b.getLagerort() + "'," + b.getEpreis() + ","
				+ b.getBestandLager() + "," +  b.getBestandBestellt() + "," + "" + b.getBestandGeplant() + ")";

		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
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
	
	public void inkrementBestellt(int id) {
		String query = "UPDATE Bauteil SET bestandBestellt= bestandBestellt + 1 WHERE teilId=" + id;
		try {
			statement.executeUpdate(query);
			System.out.println(id+" inkrementiert");

		} catch (SQLException e) {
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
	
	public void dekrementBestellt(int id){
		String query = "UPDATE Bauteil SET bestandBestellt = bestandBestellt - 1 WHERE teilId =" + id;
		try{
			statement.executeUpdate(query);
			System.out.println(id+"dekrementiert");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void deleteZugehoerigeKategorie(String name) {
		String query = "DELETE FROM Bauteil WHERE kate ='"+name+"'";
		try {
			statement.executeUpdate(query);
			System.out.println("zugehoerigkeitweg");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteKategorie(String name) {
		String query = "DELETE FROM Kategorie WHERE name ='" + name+"'";
		try {
			statement.executeUpdate(query);
			System.out.println("geloescht");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Bauteil> filterByParameterBauteil(String parameter) {
		String query = "SELECT * FROM Bauteil WHERE kate ='"+parameter+"'";
		System.out.println(query);
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM Bauteil WHERE kate = '" + parameter  + "'");
			ObservableList<Bauteil> list = getBauteil(resultSet);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void renameKategorie(String value, String name) {
		String query = "UPDATE  Kategorie SET name = '" + value + "' WHERE name='" + name+"'";
		try {
			statement.executeUpdate(query);
			System.out.println("edit ausgefuehrt");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList fillComboBoxKategorie() {
		try {
			String query = "SELECT name FROM Kategorie";
			pst = conn.prepareStatement(query);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				options.add(resultSet.getString("name"));
				System.out.println(resultSet.getString("name"));
			}
			pst.close();
			resultSet.close();
		}catch(SQLException ex) {
			System.out.println("LOL");
		}
		return options;
	}
	
	public static void addKategorie(Kategorie k) {
		String query = "INSERT INTO Kategorie(name) VALUES" + "("+ "'" + k.getName() + "')" ;

		try {
			statement.executeUpdate(query);
			System.out.println("Query ausgefuehrt.");
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR,"Constraint verletzt. Kategorie kann nicht angelegt werden.", ButtonType.OK);
			alert.showAndWait();
		}

	}
	
	//WARENKORB 
	
	public ObservableList<Bauteil> ladeWarenkorb() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Warenkorb");
			ObservableList<Bauteil> ret = getBauteil(resultSet);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public static ObservableList<Bauteil> getListBauteilWk() {
		return listBauteilWk;
	}

	public static void setListBauteilWk(ObservableList<Bauteil> listBauteilWk) {
		Verwaltung.listBauteilWk = listBauteilWk;
	}
	
	
	//VERWALTUNG
	
	public static void setzeBezahlt(BauteileRechnung br) {
		String query = "UPDATE BauteilRechnung SET status = 'BEZAHLT' WHERE brId = "+br.getBrId()+"";
		try {
			statement.executeUpdate(query);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setzeAusstehend(BauteileRechnung br) {
		String query = "UPDATE BauteilRechnung SET status = 'AUSSTEHEND' WHERE brId = "+br.getBrId()+"";
		try {
			statement.executeUpdate(query);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateKategorienBauteile(String newname,String oldname) {
		String query = "UPDATE Bauteil SET kate = '"+newname+"' WHERE kate='"+oldname+"'";
		try {
			statement.executeUpdate(query);
			System.out.println("umbenannt");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteBauteileRechnung(int brId) {
		String query = "DELETE FROM BauteilRechnung WHERE brId =" + brId;
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}