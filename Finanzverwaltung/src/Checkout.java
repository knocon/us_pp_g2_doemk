import java.util.ArrayList;

public class Checkout {
	private String name;
	private int shouldExist;	//soll-Bestand
	private int isExist;	//ist-Bestand
	private ArrayList<Pot> pots = new ArrayList<>();
	private String kind;
	//private String numberSequence;	//6- oder 16stellige Zahlenfolge
	
	
	
	Checkout(String aName){
		this.name = aName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShouldExist() {
		return shouldExist;
	}

	public void setShouldExist(int shouldExist) {
		this.shouldExist = shouldExist;
	}

	public int getIsExist() {
		return isExist;
	}

	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}

	public ArrayList<Pot> getPots() {
		return pots;
	}

	public void setPots(ArrayList<Pot> pots) {
		this.pots = pots;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
