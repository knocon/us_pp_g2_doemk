package klassen;

public class BauteilWk {
	
	private String name;
	private double epreis;
	private int teilId;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BauteilWk(int id,int teilId,String name, double epreis) {
		super();
		this.id = id;
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