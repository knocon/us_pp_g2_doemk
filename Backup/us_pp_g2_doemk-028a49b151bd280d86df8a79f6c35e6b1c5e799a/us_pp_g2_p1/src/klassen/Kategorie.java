package klassen;

public class Kategorie {
	
	private String name;
	private int kId;
	
	Kategorie(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getkId() {
		return kId;
	}

	public void setkId(int kId) {
		this.kId = kId;
	}

}
