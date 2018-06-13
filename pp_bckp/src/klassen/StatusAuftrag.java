package klassen;

public class StatusAuftrag {

	private String angenommen;
	private String gefertigt;
	private String kostenK;
	private String abgeholt;
	private String abgerechnet;
	private String warten;
	private String fertigungU;
	private int id;
	
	public StatusAuftrag(String angenommen,String gefertigt, String kostenK, String abgeholt, String abgerechnet, String warten, String fertigungU,  int id) {
		this.angenommen = angenommen;
		this.gefertigt = gefertigt;
		this.kostenK = kostenK;
		this.abgeholt = abgeholt;
		this.abgerechnet = abgerechnet;
		this.warten = warten;
		this.fertigungU = fertigungU;
		this.id= id;
	}

	public String getAngenommen() {
		return angenommen;
	}

	public String getGefertigt() {
		return gefertigt;
	}

	public String getKostenK() {
		return kostenK;
	}

	public String getAbgeholt() {
		return abgeholt;
	}

	public String getAbgerechnet() {
		return abgerechnet;
	}

	public String getWarten() {
		return warten;
	}

	public String getFertigungU() {
		return fertigungU;
	}

	public int getId() {
		return id;
	}
	
	

}
