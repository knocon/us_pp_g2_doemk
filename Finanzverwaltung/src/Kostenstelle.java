
public class Kostenstelle extends Kasse{
	private String zahlenfolge;	//6- oder 16stellige Zahlenfolge

	Kostenstelle(String aName) {
		super(aName);
	}

	public String getZahlenfolge() {
		return zahlenfolge;
	}

	public void setZahlenfolge(String zahlenfolge) {
		this.zahlenfolge = zahlenfolge;
	}


}
