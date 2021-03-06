package klassen;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Auftrag {
	private int aufId;
	long datum;
	String dateString;
	private int id;
	private String titel;
	private String art;
	private String dateiname;
	private String rkosten;
	private String pkosten;
	private String persId;
	private String status;
	private String dateiName;
	
	public Auftrag( int id,String titel, String art, String dateiname, String rkosten, String pkosten, long datum, String status) {
		super();
		this.aufId= id;
		this.datum = datum;
		Date date = new Date(datum);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
		dateString = format.format(date);
		System.out.println(datum);
		System.out.println(dateString);
		this.titel = titel;
		this.art = art;
		this.dateiname = dateiname;
		this.status = status;
		this.setRkosten(rkosten);
		this.setPkosten(pkosten);
	}
	
	public String getRKostenString() {
		String output = String.valueOf(rkosten);
		return output;
	}
	
	public String getPKostenString() {
		String output = String.valueOf(pkosten);
		return output;
	}
	
	

	public String getDateiName() {
		return dateiName;
	}

	public void setDateiName(String dateiName) {
		this.dateiName = dateiName;
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

	public String getPersId() {
		return persId;
	}

	public void setPersId(String persId) {
		this.persId = persId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
