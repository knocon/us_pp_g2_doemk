import java.util.ArrayList;

public class Topf {
	private String name;
	private int sollBestand;
	private int istBestand;
	private ArrayList<Rechnung> rechnungen = new ArrayList<>();
	
	Topf(String aName){
		this.name = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSollBestand() {
		return sollBestand;
	}

	public void setSollBestand(int sollBestand) {
		this.sollBestand = sollBestand;
	}

	public int getIstBestand() {
		return istBestand;
	}

	public void setIstBestand(int istBestand) {
		this.istBestand = istBestand;
	}

	public ArrayList<Rechnung> getRechnungen() {
		return rechnungen;
	}

	public void setRechnungen(ArrayList<Rechnung> rechnungen) {
		this.rechnungen = rechnungen;
	}

	
	
}
