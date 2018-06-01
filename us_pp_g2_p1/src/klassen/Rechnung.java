package klassen;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Rechnung {
	
	private int rechId;
	private Date rechnungsDatum;
	private String rechnungsName;
	private String auftraggeber;
	private String ansprechpartner;
	private int kassenId;
	private int topfId;
	private String art;
	private int kontoId; 
	private double betrag;
	private String status;
	private LocalDate statusZeitstempel;
	
	public Rechnung(Date rechnungsDatum, String rechnungsName, String auftraggeber, String ansprechpartner,
			int kassenId, int topfId, String art, int kontoId, double betrag, String status) {
		super();
	
		this.rechnungsDatum = rechnungsDatum;
		this.rechnungsName = rechnungsName;
		this.auftraggeber = auftraggeber;
		this.ansprechpartner = ansprechpartner;
		this.kassenId = kassenId;
		this.topfId = topfId;
		this.art = art;
		this.kontoId = kontoId;
		this.betrag = betrag;
		this.status = status;
		this.statusZeitstempel = LocalDate.now();
	}

	public Date getRechnungsDatum() {
		return rechnungsDatum;
	}

	public void setRechnungsDatum(Date rechnungsDatum) {
		this.rechnungsDatum = rechnungsDatum;
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

	public int getKassenId() {
		return kassenId;
	}

	public void setKassenId(int kassenId) {
		this.kassenId = kassenId;
	}

	public int getTopfId() {
		return topfId;
	}

	public void setTopfId(int topfId) {
		this.topfId = topfId;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public int getKontoId() {
		return kontoId;
	}

	public void setKontoId(int kontoId) {
		this.kontoId = kontoId;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
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
