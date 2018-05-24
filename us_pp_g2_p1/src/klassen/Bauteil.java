package klassen;

public class Bauteil {
	
	private String name;
	private String link;
	private double epreis;
	private int menge;
	private String status;
	private String lager;
	private int teilId;
	
	public Bauteil(String name, String link, double epreis, int menge, String status, String lager, int teilId) {
		super();
		this.name = name;
		this.link = link;
		this.epreis = epreis;
		this.menge = menge;
		this.status = status;
		this.lager = lager;
		this.teilId = teilId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public double getEpreis() {
		return epreis;
	}

	public void setEpreis(double epreis) {
		this.epreis = epreis;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLager() {
		return lager;
	}

	public void setLager(String lager) {
		this.lager = lager;
	}

	public int getTeilId() {
		return teilId;
	}

	public void setTeilId(int teilId) {
		this.teilId = teilId;
	}
	
	//// METHODEN ////
	
	

}
