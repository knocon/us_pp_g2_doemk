package klassen;

public class Topf {
	
	private int topfId;
	private String name;
	private double sollBestand;
	private double istBestand;
	
	public Topf(int id, String name, double sollBestand, double istBestand) {
		super();
		this.topfId = id;
		this.name = name;
		this.sollBestand = sollBestand;
		this.istBestand = istBestand;
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

	public int getTopfId() {
		return topfId;
	}

	public void setTopfId(int topfId) {
		this.topfId = topfId;
	}
	
	//// METHODEN ////
	
	
}
