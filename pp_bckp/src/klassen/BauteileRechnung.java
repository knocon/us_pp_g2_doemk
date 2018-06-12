package klassen;

import java.util.Date;

public class BauteileRechnung {

	private int brId;
	private String datum;
	private double summe;
	private String kaeuferId;
	private String status;
	
	public BauteileRechnung(int brId, String datum, double summe, String kaeuferId, String status) {
		super();
		this.brId = brId;
		this.datum = datum;
		this.summe = summe;
		this.kaeuferId = kaeuferId;
		this.status = status;
	}

	public int getBrId() {
		return brId;
	}

	public void setBrId(int brId) {
		this.brId = brId;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public double getSumme() {
		return summe;
	}

	public void setSumme(double summe) {
		this.summe = summe;
	}

	public String getKaeuferId() {
		return kaeuferId;
	}

	public void setKaeuferId(String kaeuferId) {
		this.kaeuferId = kaeuferId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
