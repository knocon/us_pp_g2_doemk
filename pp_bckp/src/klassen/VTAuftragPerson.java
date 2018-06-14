package klassen;

public class VTAuftragPerson {

	private int id;
	private int aufId;
	private String aufName;
	private int perId;
	private String perName;
	
	public VTAuftragPerson(int aufId, String aufName, int perId, String perName) {
		this.aufId= aufId;
		this.aufName = aufName;
		this.perId = perId;
		this.perName = perName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAufId() {
		return aufId;
	}

	public void setAufId(int aufId) {
		this.aufId = aufId;
	}

	public String getAufName() {
		return aufName;
	}

	public void setAufName(String aufName) {
		this.aufName = aufName;
	}

	public int getPerId() {
		return perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}
	
	
}
