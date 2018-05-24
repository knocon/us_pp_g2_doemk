package klassen;

import java.util.Calendar;

public class Auftrag {
	
	private int aufId;
	private String titel;
	private String art;
	private String dateiname;
	private double kosten;
	private String status;
	private Calendar statusZeitstempel = Calendar.getInstance();
	private int persId;
	
	public Auftrag(int aufId, String titel, String art, String dateiname, double kosten, String status,
			Calendar statusZeitstempel, int persId) {
		super();
		this.aufId = aufId;
		this.titel = titel;
		this.art = art;
		this.dateiname = dateiname;
		this.kosten = kosten;
		this.status = status;
		this.statusZeitstempel = statusZeitstempel;
		this.persId = persId;
	}

	public int getAufId() {
		return aufId;
	}

	public void setAufId(int aufId) {
		this.aufId = aufId;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getDateiname() {
		return dateiname;
	}

	public void setDateiname(String dateiname) {
		this.dateiname = dateiname;
	}

	public double getKosten() {
		return kosten;
	}

	public void setKosten(double kosten) {
		this.kosten = kosten;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getStatusZeitstempel() {
		return statusZeitstempel;
	}

	public void setStatusZeitstempel(Calendar statusZeitstempel) {
		this.statusZeitstempel = statusZeitstempel;
	}

	public int getPersId() {
		return persId;
	}

	public void setPersId(int persId) {
		this.persId = persId;
	}
	
	//// METHODEN ////
	
	
}
