package klassen;

public class statusRechnung {
	private String bearbeitung;
	private String eingereicht;
	private String abgewickelt;
	private String ausstehend;
	private int rechId;
	
	public statusRechnung(String bearbeitung,String eingereicht, String abgewickelt, String ausstehend, int rechId) {
		this.bearbeitung = bearbeitung;
		this.eingereicht = eingereicht;
		this.abgewickelt = abgewickelt;
		this.ausstehend = ausstehend;
		this.rechId= rechId;
	}

	public String getBearbeitung() {
		return bearbeitung;
	}

	public String getEingereicht() {
		return eingereicht;
	}

	public String getAbgewickelt() {
		return abgewickelt;
	}

	public String getAusstehend() {
		return ausstehend;
	}

	
	public int getRechId() {
		return rechId;
	}
	
	

}



