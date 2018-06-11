package klassen;

public class Account {
	
	private int accId;
	private String name;
	private String pw;
	private String rolle;
	
	public Account(int accId, String name, String pw, String rolle) {
		super();
		this.accId = accId;
		this.name = name;
		this.pw = pw;
		this.rolle = rolle;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	
	//// METHODEN ////
	
	
	
}
