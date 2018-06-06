package klassen;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Auftrag {
	
	long datum;
	String dateString;
	private int aufId;
	private String titel;
	private String art;
	private String dateiname;
	private String rkosten;
	private String pkosten;
	private int persId;
	
	public Auftrag(int aufId,long datum, String titel, String art, String dateiname, String rkosten, String pkosten) {
		super();
		this.datum = datum;
		Date date = new Date(datum);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
		dateString = format.format(date);
		System.out.println(datum);
		System.out.println(dateString);
		this.aufId = aufId;
		this.titel = titel;
		this.art = art;
		this.dateiname = dateiname;
		this.setRkosten(rkosten);
		this.setPkosten(pkosten);
	}
	
	public String getDateString() {
		return dateString;
	}
	
	public long getDateLong() {
		return datum;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public int getPersId() {
		return persId;
	}

	public void setPersId(int persId) {
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

	/**
	 * @return the rkosten
	 */
	public String getRkosten() {
		return rkosten;
	}

	/**
	 * @param rkosten the rkosten to set
	 */
	public void setRkosten(String rkosten) {
		this.rkosten = rkosten;
	}

	/**
	 * @return the pkosten
	 */
	public String getPkosten() {
		return pkosten;
	}

	/**
	 * @param pkosten the pkosten to set
	 */
	public void setPkosten(String pkosten) {
		this.pkosten = pkosten;
	}

	
	//// METHODEN ////
	
	
}
