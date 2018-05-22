import java.util.ArrayList;

public class Pot {
	private String name;
	private int shouldExist;	//soll-Bestand
	private int isExist;	//ist-Bestand
	private ArrayList<Bill> bills = new ArrayList<>();
	
	Pot(String aName){
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

	public ArrayList<Bill> getBills() {
		return bills;
	}

	public void setBills(ArrayList<Bill> bills) {
		this.bills = bills;
	}
	
}
