
import java.time.LocalDate;

public class Person {
	
	private LocalDate date;
	private String name;
	private String nachname;
	private String typ;
	private String straﬂe;
	private String hausnummer;
	private String stadt;
	private String telefon;
	private String email;
	
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getStraﬂe() {
		return straﬂe;
	}
	public void setStraﬂe(String straﬂe) {
		this.straﬂe = straﬂe;
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
	
	
}
