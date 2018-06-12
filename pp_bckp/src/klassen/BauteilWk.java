package klassen;

public class BauteilWk {
	
	private String name;
	private double epreis;
	private int teilId;
	
	public BauteilWk(int teilId,String name, double epreis) {
		super();
		this.name = name;
		this.epreis = epreis;

		this.teilId = teilId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public double getEpreis() {
		return epreis;
	}

	public void setEpreis(double epreis) {
		this.epreis = epreis;
	}

	public int getTeilId() {
		return teilId;
	}

	public void setTeilId(int teilId) {
		this.teilId = teilId;
	}


	
	//// METHODEN ////
	
	

}