package klassen;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Rechnung {
	private int id;
	long datum;
	String dateString;
	private int rechId;
	private String rechnungsName;
	private String auftraggeber;
	private String ansprechpartner;
	private String kassenId;
	private String topf;
	private String art;
	private String kontoId;
	private String betrag;
	private String status;
	private String statusZeitstempel;
	
	public Rechnung(int id,long datum, String rechnungsName, String auftraggeber, String ansprechpartner,
			String kassenId, String topf, String art, String kontoId, String betrag, String status) {
		super();
		this.rechId= id;
		this.datum = datum;
		Date date = new Date(datum);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
		dateString = format.format(date);
		System.out.println(datum);
		System.out.println(dateString);
		this.rechnungsName = rechnungsName;
		this.auftraggeber = auftraggeber;
		this.ansprechpartner = ansprechpartner;
		this.kassenId = kassenId;
		this.topf = topf;
		this.art = art;
		this.kontoId = kontoId;
		this.betrag = betrag;
		this.status = status;
		
	}

	public String getDateString() {
		return dateString;
	}

	
	public long getDateLong() {
		return datum;
	}

	public String getRechnungsName() {
		return rechnungsName;
	}

	public void setRechnungsName(String rechnungsName) {
		this.rechnungsName = rechnungsName;
	}

	public String getAuftraggeber() {
		return auftraggeber;
	}

	public void setAuftraggeber(String auftraggeber) {
		this.auftraggeber = auftraggeber;
	}

	public String getAnsprechpartner() {
		return ansprechpartner;
	}

	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}

	public String getKassenId() {
		return kassenId;
	}

	public void setKassenId(String kassenId) {
		this.kassenId = kassenId;
	}

	public String getTopf() {
		return topf;
	}

	public void setTopf(String topf) {
		this.topf = topf;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getKontoId() {
		return kontoId;
	}

	public void setKontoId(String kontoId) {
		this.kontoId = kontoId;
	}

	public String getBetrag() {
		return betrag;
	}

	public void setBetrag(String betrag) {
		this.betrag = betrag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getRechId() {
		return rechId;
	}

	public void setRechId(int rechId) {
		this.rechId = rechId;
	}
	
	//// METHODEN ////
	
	
	
}
