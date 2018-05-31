package klassen;

import java.sql.Date;
import java.time.LocalDate;

public class Person {
	
	private int persId;
	private Date date;
	private String vorname;
	private String nachname;
	private String typ;
	private String strasse;
	private String hausnummer;
	private String stadt;
	private String telefon;
	private String email;
	
	public Person(int id, Date date2,String vorname, String nachname, String typ, String strasse, String hausnummer,
			String stadt, String telefon, String email) {
		super();
		this.date = date2;
		this.persId = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.typ = typ;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.stadt = stadt;
		this.telefon = telefon;
		this.email = email;
	}
	
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPersId() {
		return persId;
	}

	public void setPersId(int persId) {
		this.persId = persId;
	}
	
	//// METHODEN ////
	
	
}
