import java.util.ArrayList;

public class Kasse {
	private String name;
	private int sollBestand;
	private int istBestand;
	private ArrayList<Topf> töpfe = new ArrayList<>();
	private String art;
	
	
	Kasse(String aName){
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



	public ArrayList<Topf> getTöpfe() {
		return töpfe;
	}



	public void setTöpfe(ArrayList<Topf> töpfe) {
		this.töpfe = töpfe;
	}



	public String getArt() {
		return art;
	}



	public void setArt(String art) {
		this.art = art;
	}
	
	
}
