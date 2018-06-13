package klassen;

public class Topf {
	
	private int topfId;
	private String name;
	private String sollBestand;
	private String  istBestand;
	
	public Topf(int id, String name, String sollBestand, String istBestand) {
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

	public String getSollBestand() {
		return sollBestand;
	}

	public void setSollBestand(String sollBestand) {
		this.sollBestand = sollBestand;
	}

	public String getIstBestand() {
		return istBestand;
	}

	public void setIstBestand(String istBestand) {
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
