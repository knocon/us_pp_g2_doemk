package klassen;

public class Kasse {
	
	private int kasId;
	private String name;
	private double sollBestand;
	private double istBestand;
	private String art;
	private int zahlenfolge;
	
	public Kasse(int id, String name, double sollBestand, double istBestand,String art,
			int zahlenfolge) {
		super();
		this.kasId = id;
		this.name = name;
		this.sollBestand = sollBestand;
		this.istBestand = istBestand;
		this.art = art;
		this.zahlenfolge = zahlenfolge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSollBestand() {
		return sollBestand;
	}

	public void setSollBestand(double sollBestand) {
		this.sollBestand = sollBestand;
	}

	public double getIstBestand() {
		return istBestand;
	}

	public void setIstBestand(double istBestand) {
		this.istBestand = istBestand;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public int getZahlenfolge() {
		return zahlenfolge;
	}

	public void setZahlenfolge(int zahlenfolge) {
		this.zahlenfolge = zahlenfolge;
	}

	public int getKasId() {
		return kasId;
	}

	public void setKasId(int kasId) {
		this.kasId = kasId;
	}
	
	//// METHODEN ////
	
	
}
