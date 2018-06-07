package klassen;

public class Bauteil {
	
	private String name;
	private String kategorie;
	private String link;
	private double epreis;
	private int bestandLager;
	private int bestandBestellt;
	private int bestandGeplant;
	private int teilId;
	
	public Bauteil(String name, String link, double epreis, int bestandLager, int bestandBestellt, int bestandGeplant) {
		super();
		this.name = name;
		this.link = link;
		this.epreis = epreis;
		this.bestandLager = bestandLager;
		this.bestandBestellt = bestandBestellt;
		this.bestandGeplant = bestandGeplant;
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

	public int getTeilId() {
		return teilId;
	}

	public void setTeilId(int teilId) {
		this.teilId = teilId;
	}

	public int getBestandLager() {
		return bestandLager;
	}

	public void setBestandLager(int bestandLager) {
		this.bestandLager = bestandLager;
	}

	public int getBestandBestellt() {
		return bestandBestellt;
	}

	public void setBestandBestellt(int bestandBestellt) {
		this.bestandBestellt = bestandBestellt;
	}

	public int getBestandGeplant() {
		return bestandGeplant;
	}

	public void setBestandGeplant(int bestandGeplant) {
		this.bestandGeplant = bestandGeplant;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	
	//// METHODEN ////
	
	

}
