package klassen;

public class Account {
	
	private int accId;
	private String name;
	private String pw;
	private String rolle;
	private String vorname;
	private String nachname;
	private String email;
	
	public Account(String name, String pw, String rolle, String vorname, String nachname, String email) {
		super();
		this.name = name;
		this.pw = pw;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//// METHODEN ////
	
	
	
}
