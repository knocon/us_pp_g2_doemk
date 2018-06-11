package klassen;

public class Konto {
	
	private int kontoId;
	private String vorname;
	private String nachname;
	private int kontoNr;
	private String bank;
	
	public Konto(int id, String vorname, String nachname, int kontoNr, String bank) {
		super();
		this.kontoId = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.kontoNr = kontoNr;
		this.bank = bank;
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

	public int getKontoNr() {
		return kontoNr;
	}

	public void setKontoNr(int kontoNr) {
		this.kontoNr = kontoNr;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getKontoId() {
		return kontoId;
	}

	public void setKontoId(int kontoId) {
		this.kontoId = kontoId;
	}
	
	
}
